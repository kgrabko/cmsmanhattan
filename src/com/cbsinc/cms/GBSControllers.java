package com.cbsinc.cms;



/**
 * <p>
 * Title: Content Manager System
 * </p>
 * <p>
 * Description: System building web application develop by Konstantin Grabko.
 * Konstantin Grabko is Owner and author this code.
 * You can not use it and you cannot change it without written permission from Konstantin Grabko
 * Email: konstantin.grabko@yahoo.com or konstantin.grabko@gmail.com
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002-2014
 * </p>
 * <p>
 * Company: CENTER BUSINESS SOLUTIONS INC 
 * </p>
 * 
 * @author Konstantin Grabko
 * @version 1.0
 */



import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.controllers.IAction;
import com.cbsinc.cms.controllers.ProductlistAction;
import com.cbsinc.cms.controllers.ServiceLocator;
import com.cbsinc.cms.controllers.SiteRole;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.jms.controllers.MQSender;
import com.cbsinc.cms.jms.controllers.Message;
import com.cbsinc.cms.jms.controllers.StoreCashMessageBean;





/**
 * Filter class
 * 
 * @web.filter name="GBSControllers" display-name="Name for GBSControllers" description="Description  for action classes"
 * @web.filter-mapping url-pattern="*.jsp"
 * 
 */

public class GBSControllers implements Filter , ITransformationService {
	
	private static final long serialVersionUID = 1878852994297969407L;

	

	private FilterConfig filterConfig;

	transient static private Logger log = Logger.getLogger(GBSControllers.class);
	
	
	transient float beginClearMemory = 30 ;
	transient float beginReloadProgramm = 10 ;
	transient long cash_time_expired = 60000  ;
	
	ProductlistAction productlistAction = null ;
	AuthorizationPageFaced authorizationPageFaced = null ;
	transient ResourceBundle application_scope = null ;
	ServletContext  servletContext ;
	
	transient Runtime runtimec ;
    //String path_startup = "/etc/init.d/cmsbo restart" ;
    //String path_shutdown  = "";
	String path_restart_script =  "/etc/init.d/cmsbo1" ;

    public GBSControllers() {
    	if( application_scope == null )  application_scope = PropertyResourceBundle.getBundle("application_scope");
	}
	
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
		servletContext =  filterConfig.getServletContext() ;
		servletContext.setAttribute("user_locale_en" , PropertyResourceBundle.getBundle("localization", new java.util.Locale("en")));
		servletContext.setAttribute("user_locale_ru" , PropertyResourceBundle.getBundle("localization", new java.util.Locale("ru")));
		
		if( System.getProperty("restart")!= null ) path_restart_script =  System.getProperty("restart") ;
		
		//path_restart_script
		servletContext.setAttribute("ITransformationService",(ITransformationService)this) ;
		
		setCashDir( servletContext) ;
		setSessionDir( servletContext) ;
		loadClassesApplicationScope(servletContext);
		try {
			authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced().get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if( !authorizationPageFaced.getSetup_resources().getString("begin_clear_memory").equals("")) beginClearMemory = Float.parseFloat(authorizationPageFaced.getSetup_resources().getString("begin_clear_memory").trim()) ;
		if( !authorizationPageFaced.getSetup_resources().getString("begin_reload_programm").equals("")) beginReloadProgramm = Float.parseFloat(authorizationPageFaced.getSetup_resources().getString("begin_reload_programm").trim()) ;
		if( !authorizationPageFaced.getSetup_resources().getString("cash_time_expired").equals("")) cash_time_expired = Long.parseLong(authorizationPageFaced.getSetup_resources().getString("cash_time_expired").trim()) ;
		
		if( !authorizationPageFaced.getSetup_resources().getString("cookies_dir").equals("")) setCookiesDir( servletContext ,authorizationPageFaced.getSetup_resources().getString("cookies_dir").trim()) ;		
		
		if( !authorizationPageFaced.getSetup_resources().getString("use_cookies").equals("")) setUserCookies( servletContext ,authorizationPageFaced.getSetup_resources().getString("use_cookies").trim()) ;		
		
	}
	
	
	

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) {
		Transformer transformer;
		AuthorizationPageBean authorizationPageBeanId = null;

		try 
		{
			HttpSession hsession = ((HttpServletRequest) request).getSession(false);
			if (hsession == null) hsession = ((HttpServletRequest) request).getSession(true);
			
			boolean isTransformble = true ; 
            boolean isActions = true ;
			//++++++++++++++++++++++ start +++++++++++++++++++++++++++++++++++
			String path = ((HttpServletRequest) request).getRequestURI() ;
			  
			int index = path.lastIndexOf("/") + 1 ;
			path = path.substring( index ) ;
			
			
			
			isTransformble = authorizationPageFaced.getXslt_resources().containsKey(path);
		    isActions =  authorizationPageFaced.getActions_resources().containsKey(path);

				if (hsession.getAttribute("authorizationPageBeanId") instanceof AuthorizationPageBean) 
				{
					 authorizationPageBeanId = ((AuthorizationPageBean) hsession.getAttribute("authorizationPageBeanId"));

					 if (authorizationPageBeanId.getStrLogin().length() == 0 || (authorizationPageBeanId.getIntLevelUp() == 0 && !authorizationPageBeanId.getStrLogin().equals("user") ) ) 
					 {
						authorizationPageBeanId.setIntUserID(1);
						authorizationPageBeanId.setIntLevelUp(0);
						authorizationPageBeanId.setStrPasswd("user");
						authorizationPageBeanId.setStrLogin("user");
					 }
					 
				}
				else 
				{
					    
						String session_id = authorizationPageFaced.getCokieSessionId((HttpServletRequest) request, (HttpServletResponse)response );
						hsession.setAttribute("cokie_session_id", session_id);
//						System.out.println("session: " + session_id );
						if(authorizationPageFaced.isCokieSessionIdExists((HttpServletRequest) request, (HttpServletResponse)response) &&  authorizationPageFaced.isLoginFromCookie_new1( session_id ,  hsession , authorizationPageFaced.getSession_scope() ) )
//						//if( authorizationPageFaced.isLoginFromCookieFromDir( session_id ,  hsession , servletContext , session_scope ) )
						{
							authorizationPageBeanId = ((AuthorizationPageBean) hsession.getAttribute("authorizationPageBeanId"));
						}
						else
						{ 
							loadClassesSessionScope(hsession) ;
							authorizationPageBeanId = ((AuthorizationPageBean) hsession.getAttribute("authorizationPageBeanId"));
							
							String host = ((HttpServletRequest) request).getServerName() ;
							String siteId =  authorizationPageFaced.getSiteIdByHost( host);
							authorizationPageBeanId.setSite_id(siteId,authorizationPageFaced);
//							authorizationPageBeanId.setSite_id(SiteType.MAIN_SITE,authorizationPageFaced);
							authorizationPageBeanId.setIntUserID(1);
							authorizationPageBeanId.setIntLevelUp(0);
							authorizationPageBeanId.setStrLogin(SiteRole.GUEST);
							authorizationPageBeanId.setStrPasswd(SiteRole.GUEST_PASSWORD);

						
						}

						//authorizationPageBeanId.setLocale("ru",servletContext);
						authorizationPageBeanId.setLocale("en",servletContext);
						String sitePath = filterConfig.getServletContext().getRealPath("/") + "xsl" + File.separatorChar + authorizationPageBeanId.getSite_dir(); 
						hsession.setAttribute("site_path",sitePath);  
						
						if(authorizationPageBeanId.getSite_id().equals("2")) authorizationPageBeanId.setStrMessage( authorizationPageBeanId.getLocalization(servletContext).getString("message_for_new_session")) ;
					
				}

//				if(((HttpServletRequest) request).getQueryString() != null)
//				{
//				authorizationPageBeanId.setLastVisitedPage(((HttpServletRequest) request).getRequestURI() + "?" + ((HttpServletRequest) request).getQueryString()) ;
//				}

			
				if(!isTransformble && !isActions  )
				{
				  filterChain.doFilter(request, response);
				  return  ; 
				}  
		
			//++++++++++++++++++++  end +++++++++++++++++++++++++++++++++++++			

			//String pathInfo = path  +  ".html";
			String pathInfo = "" ;
			if(authorizationPageBeanId.getStrLogin().equals("user") && authorizationPageFaced.getSetup_resources().getString("docash").equals("true") && ((HttpServletRequest) request).getMethod().equals("GET") && ((HttpServletRequest) request).getParameter("site") == null  )
			{
				pathInfo = path  + "_" + buildCashPageName( (HttpServletRequest)request,  path) + ".html";
				String cashPage = getCashDir(servletContext) + File.separatorChar + pathInfo  ;
					File fileCashPage = new File(cashPage) ;
					
					
					if( fileCashPage.exists() ) 
					{
						long expiredTime = fileCashPage.lastModified() + cash_time_expired ;
						long curentTime = new Date().getTime() ;
						if(expiredTime > curentTime) 
						{
						((HttpServletRequest) request).getRequestDispatcher("cashes" + File.separatorChar + pathInfo).forward( request,  response) ;
						//filterChain.doFilter(request, response);
					    return ;
						}
					}
			}
			

			if (hsession != null) 
			{

				  ((HttpServletResponse) response).setHeader("Cache-Control","no-cache"); //HTTP 1.1
				  ((HttpServletResponse) response).setHeader("Pragma","no-cache"); //HTTP 1.0
				  ((HttpServletResponse) response).setDateHeader ("Expires", 0); //prevents caching at the proxy server

				  if(isActions)
				  {
				  Object obj = null ;
				     if(!authorizationPageFaced.getControllerMap().containsKey(path))
				     {
				     String className = authorizationPageFaced.getActions_resources().getString(path);
				     obj =  createObject( className) ;
				     if(obj == null) throw new Exception("Class " + className + " is not found." );
				     authorizationPageFaced.getControllerMap().put(path,obj);
				     }
				     else
				     {
				    	 obj =  authorizationPageFaced.getControllerMap().get(path);
				     }
				     
				     if(obj != null)
				     {
				    	 if(response.isCommitted()) return ;
				    	 if(((HttpServletRequest) request).getMethod().toUpperCase().compareTo("POST") == 0)  
					    ((IAction)obj).doPost((HttpServletRequest) request, (HttpServletResponse) response , servletContext) ;
					    else ((IAction)obj).doGet((HttpServletRequest) request, (HttpServletResponse) response , servletContext) ;
				     }
				  
				  isClearMemory();
				  }
				  
				  if(isTransformble)
				  {
					  
					  if(response.isCommitted()) return ;
					  
					  Templates cachedXSLT = null ;
					  if(!authorizationPageFaced.getTransformerMap().containsKey(authorizationPageBeanId.getSite_dir() + File.separatorChar  +path + authorizationPageBeanId.getLocale() ))
				    {
						 String xslt_page_default =  "xsl" + File.separatorChar + authorizationPageBeanId.getSite_dir() + File.separatorChar + authorizationPageFaced.getXslt_resources().getString(path);
						 String xsltpath_default  = filterConfig.getServletContext().getRealPath("/" +xslt_page_default);
						 String xslt_page =  "xsl" + File.separatorChar + authorizationPageBeanId.getSite_dir() + File.separatorChar  +  authorizationPageBeanId.getLocale() + File.separatorChar + authorizationPageFaced.getXslt_resources().getString(path);
						 String xsltpath  = filterConfig.getServletContext().getRealPath("/" +xslt_page);
						 Source styleSource = null ;
						try
						{
							File file = new File(xsltpath) ;
							if( file == null  || !file.exists() ) file  = new File(xsltpath_default) ;
							styleSource = new StreamSource(file);
								 
						}
						 catch (Exception e) 
						{
							 throw e ;
						}
						 TransformerFactory transformerFactory = TransformerFactory.newInstance();
						
						try 
						{
						 cachedXSLT = transformerFactory.newTemplates(styleSource);
						}
						catch (TransformerConfigurationException e) 
						{
							throw e ;
						}
				     
				     if(cachedXSLT == null) throw new Exception("Class Templates == null for xslt page " + path  );
				     authorizationPageFaced.getTransformerMap().put(authorizationPageBeanId.getSite_dir() + File.separatorChar + path + authorizationPageBeanId.getLocale() ,cachedXSLT);
				    }
				    else	cachedXSLT =  (Templates)authorizationPageFaced.getTransformerMap().get(authorizationPageBeanId.getSite_dir() + File.separatorChar + path + authorizationPageBeanId.getLocale());
					  
					  
					  
					  
					  try 
					    {
							transformer = cachedXSLT.newTransformer();
						}
					    catch (TransformerConfigurationException e) 
					    {
							throw e ;
						}
					    
					    String htmlData = "" ;
					    String xmlData = "" ;
					    byte[] htmlBytes = new byte[0];
						
					    response.setCharacterEncoding("UTF-8");
						ServletOutputStream out = response.getOutputStream();
						CharResponseWrapper responseWrapper = new CharResponseWrapper((HttpServletResponse) response);
						filterChain.doFilter(request, responseWrapper);
						xmlData = new String(responseWrapper.getData()).trim() ; 
						
						xmlData = xmlData.replaceAll("<r>","<r><![CDATA[ ") ;
						xmlData = xmlData.replaceAll("</r>"," ]]></r>") ;
						
						//saveXMLFile("xml_" + path  , xmlData ) ; // save
						
						StringReader sr = new StringReader(xmlData);
						CharArrayWriter caw = new CharArrayWriter();
						StreamResult result = new StreamResult(caw);
						try 
						{
							Source xmlSource = new StreamSource(sr);
							transformer.transform(xmlSource, result);
							htmlData = caw.toString();
							htmlData = htmlData.replaceAll("&lt;","<") ;
							htmlData = htmlData.replaceAll("&gt;",">") ;
							htmlBytes = htmlData.getBytes("UTF-8") ;
							if(out != null )out.write(htmlBytes);
							//out.flush();

							if(authorizationPageBeanId.getStrLogin().equals("user") && authorizationPageFaced.getSetup_resources().getString("docash").equals("true") && ((HttpServletRequest) request).getMethod().equals("GET") && ((HttpServletRequest) request).getParameter("site") == null  )
							{
							MQSender mqSender = new MQSender(hsession,StoreCashMessageBean.messageQuery) ;
							Message message = new Message();
							message.put("cashPageName" , pathInfo  ) ;
							message.put("cashPageBodyByteArray" , htmlBytes ) ;
							mqSender.send(message);
							}
							
						} catch (Exception ex) {
							System.out.println(xmlData);
							System.out.println(ex.toString());
							throw ex ;
							//out.write(responseWrapper.toString());
						}
						finally
						{
							
							//System.out.println(xmlData);
							caw.close();
							sr.close();
							out.close();
							responseWrapper.close();
						}
					 return ; 
				  }
				  
				  
				 filterChain.doFilter(request, response);
				 //hsession.invalidate();
				  
			}

		}
		catch (IOException iox) {
			//log.error(iox) ;
			iox.printStackTrace() ;
			filterConfig.getServletContext().log(iox.getMessage());
			System.err.println("url: " + ((HttpServletRequest) request).getRequestURI()) ;
			System.err.println("client ip: " + ((HttpServletRequest) request).getRemoteAddr()) ;
			//notifyAll() ;
			System.gc();
		}
		catch (Exception iox) {
			//log.error(iox) ;
			iox.printStackTrace() ;
			filterConfig.getServletContext().log(iox.getMessage());
			//notifyAll() ;
			System.err.println("url: " + ((HttpServletRequest) request).getRequestURI()) ;
			System.err.println("client ip: " + ((HttpServletRequest) request).getRemoteAddr()) ;
			System.gc();
		}
		
		catch (OutOfMemoryError e) 
		{
			///reloadServer();
			//System.err.println("url: " + ((HttpServletRequest) request).getRequestURI()) ;
			//System.err.println("client ip: " + ((HttpServletRequest) request).getRemoteAddr()) ;
			log.error(e) ;
			filterConfig.getServletContext().log(e.getMessage());
			System.out.println("== clear ===");
			//if( authorizationPageBeanId != null )System.out.println("Site = " + authorizationPageBeanId.getSite_dir() );
			//notifyAll() ;
			System.gc();
			reloadServer() ;
		}
		finally
		{
			
		}
		
		//isClearMemory();
	}

	
	public Object createObject(String className) {
	Object obj = null;
	try 
	{
	Class cls = Class.forName(className);
	obj = cls.newInstance();
    }
	catch (Exception ex) 
	{
	log.error(ex) ;
	}
	return obj;
	}
	
	
	public Object createObjectFromThread(String className) {
		Object obj = null;
		try 
		{
			final Class<?> serviceImplClass = 	Thread.currentThread().getContextClassLoader().loadClass(className);

//				if (!clazz.isAssignableFrom(serviceImplClass)) {
//				throw new ServiceFactoryException("Service " + serviceImplClassName + " does not implement "
//				+ clazz.getCanonicalName());
//				}
			obj = ((Class) serviceImplClass).newInstance();
		}
		catch (Exception ex) 
		{
		log.error(ex) ;
		}
		
		
		return obj;
		}

	 
	
//	boolean isExistKey(Enumeration en , String key)
//	{
//		while(en.hasMoreElements())
//		{
//		String _key = (String)en.nextElement() ;
//		if( _key.equals(key)) return true ;
//		}
//		
//		return false ;
//	}
	
	
	
	
	public boolean isClearMemory()
	{
    long max ;
    long total ;
    long free ;
    boolean result = false ;
    
	if(runtimec == null)   runtimec = Runtime.getRuntime();
	
    max = runtimec.maxMemory() ;
    total = runtimec.totalMemory() ;
    free = runtimec.freeMemory() ;
    
    //String sitePath = filterConfig.getServletContext().getRealPath("/") ;
    //int cut = sitePath.indexOf("webapps") ;
    //sitePath = sitePath.substring(0,cut) ;
    //path_startup = sitePath + "bin\\startup.bat" ;
    //path_shutdown  =  sitePath + "bin\\shutdown.bat" ;

    float persent = (float)free/(float)total * 100 ;
    System.out.println("=======");
    System.out.println("max: " + max);
    System.out.println("total: " + total);
    System.out.println("free: " + free);
    System.out.println("free memory persent: " + persent); 
    System.out.println("========");

    if(persent < beginClearMemory)    	
    {
        System.out.println("== clear ===");
    	System.gc();
    	result = true ;
    }
    
    
    if(persent < beginReloadProgramm )    	
    {
        System.out.println("== reload ===");
        
        try 
        {
        	System.out.println("begin start: " + path_restart_script) ;
        	///////////runtimec.exec(path_restart_script);
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
			log.error(e) ;
		}
        result = true ;
    }
		
	return result ;
	}
	
	
	
	public boolean reloadServer()
	{
    
	if(runtimec == null)   runtimec = Runtime.getRuntime();
	
    System.out.println("=== server reload ===");
    
        try 
        {
        	//System.out.println(path_startup);
        	System.out.println("begin restart: " + path_restart_script) ;
        	runtimec.exec(path_restart_script);
		} 
        catch (Exception e) 
        {
            System.out.println("=== problem server reload ===");
            log.error(e) ;
        	return false ;
		}
    	return true ;
	}
	
	
	
	void loadClassesApplicationScope( ServletContext  application  ) 
	{
		String key = "";
		Enumeration enumeration;
		try {
			enumeration = application_scope.getKeys();
			while (enumeration.hasMoreElements()) 
			{
				key = (String) enumeration.nextElement();
				Object obj =  createObject(application_scope.getString(key).trim());
				application.setAttribute(key,obj) ;
			}
		}
		catch (Exception e) 
		{
			log.error(e) ;
		}
	}
	
	
	void loadClassesSessionScope( HttpSession  session  ) 
	{
		String key = "";
		Enumeration enumeration;
		try {
			enumeration = authorizationPageFaced.getSession_scope().getKeys();
			while (enumeration.hasMoreElements()) 
			{
				key = (String) enumeration.nextElement();
				Object obj =  createObject(authorizationPageFaced.getSession_scope().getString(key).trim());
				session.setAttribute(key,obj) ;
			}
		}
		catch (Exception e) 
		{
			log.error(e) ;
		}
	}
	
	
	public void destroy()
	{
		
//		StringBuffer hosts_for_dns = new StringBuffer();
//		File file =  new File("/") ;
//		 String server_config = System.getProperty("conf");
//		 if(server_config == null || server_config.length() == 0) 
//		 {
//			 String dir = System.getProperty("user.dir");
//			 dir = dir.substring(0 ,dir.indexOf("bin")) + "conf" + File.separatorChar+"server.xml" ;
//			//File f = new File("C:/apache-tomcat-6.0.14/conf/server.xml") ;
//			file = new File(dir) ;
//		 }
//		 else 
//		 {
//			 file = new File(server_config) ; 
//		 }
//
//		QueryManager Adp = null ;
//		AddAliase addAliase = new AddAliase();
//		Document doc = null ;
//		String query = "select host  from site" ;
//		//String query = "select site_dir  from site" ;
//		//String host = "www.online-spb.com" ;
//		String host = "www.siteoneclick.com" ;
//		String aliase = "" ;
//		
//		try 
//		{
//			
//
//			
//			Adp = new QueryManager();
//			if(Adp == null)return ; 
//			doc = addAliase.loadConfig(file);
//			addAliase.RemoveAllAliase(doc, host) ;
//			
//			Adp.executeQuery(query);
//			for (int i = 0; Adp.rows().size() > i; i++) 
//			{
//				aliase =  Adp.getValueAt(i, 0);
//				addAliase.AddAliaseToHost(doc, host, aliase) ;
//				int indexof = aliase.indexOf(".irr.bz");
//				if(indexof != -1)
//				{
//					hosts_for_dns.append(aliase.substring(0,indexof));	
//					hosts_for_dns.append(" A 0 78.37.191.193 86400\n");	
//				}
//			}
//			addAliase.writeXmlFile(doc,file);	 
//		}
//		catch (SQLException e) 
//		{
//			log.error(e);
//		}
//		catch (Exception e) 
//		{
//			System.out.println(" server config: " + file.getPath());
//			log.error(e);
//		}
//		finally
//		{
//			if(Adp != null )
//			{
//			Adp.close();
//			DomainRegister r = new DomainRegister();
//			//r.getSiteIdByHost("");
//			String auth = r.getAuth() ;
//			r.setDomainAliases("irr.bz" , hosts_for_dns.toString() ,auth );
//			System.out.println(r.getPostCommand(auth));
//			}
//			
//		}

	}
	
	
	
	
	void setCashDir( ServletContext  servletContext )
	{
		try
 		{

	 		String pageStorePath = this.getClass().getResource("").getPath();
	 		pageStorePath = pageStorePath.substring(1, pageStorePath.indexOf("/WEB-INF/"));
			File file = new File(pageStorePath + File.separatorChar + "cashes");
 			if(!file.exists())
 			{ 
 			   file.mkdirs();
 			}
 			servletContext.setAttribute("cash_dir", file.getPath()) ;
		} 
		catch (Exception e) 
		{
		log.error(e) ;
		}

	}
	
	String getCashDir(ServletContext  servletContext )
	{
		return  (String)servletContext.getAttribute("cash_dir") ;
	}
	

	void setSessionDir( ServletContext  servletContext )
	{
		try
		{
		String pageStorePath = this.getClass().getResource("").getPath();
 		pageStorePath = pageStorePath.substring(1, pageStorePath.indexOf("/WEB-INF/"));
		File file = new File(pageStorePath + File.separatorChar + "sessions");
		if(!file.exists())
 		{ 
 		   file.mkdirs();
		}

		servletContext.setAttribute("session_dir", file.getPath()) ;
		} 
		catch (Exception e) 
		{
			log.error(e) ;
		}
	}


	String getSessionDir(ServletContext  servletContext )
	{
		return  (String)servletContext.getAttribute("session_dir") ;
	}

	String buildCashPageName(HttpServletRequest request,  String path)
	{
		HttpSession hsession =  request.getSession(false);
		ProductlistBean  productlistBeanId = null ;
		AuthorizationPageBean	authorizationPageBeanId = ((AuthorizationPageBean) hsession.getAttribute("authorizationPageBeanId"));
		StringBuffer buff = new StringBuffer();
		if(path.equals("Productlist.jsp"))
		{
			
			
			
			if (hsession.getAttribute("ProductlistBeanId") instanceof ProductlistBean) 
			{
			
			productlistBeanId =  (ProductlistBean)hsession.getAttribute("ProductlistBeanId")  ;	
			String  dayfrom_id = "" ;
			String  mountfrom_id = "" ;
			String  yearfrom_id = "" ;
			String  fromcost = "" ;
			String  tocost = "" ;
			String  dayto_id = "" ;
			String  mountto_id = "" ;
			String  yearto_id = "" ;
			String  creteria1_id = "" ;
			String  creteria2_id = "" ;
			String  creteria3_id = "" ;
			String  creteria4_id = "" ;
			String  creteria5_id = "" ;
			String  creteria6_id = "" ;
			String  creteria7_id = "" ;
			String  creteria8_id = "" ;
			String  creteria9_id = "" ;
			String  creteria10_id = "" ;
			String  offset = "" ;
			String  searchquery = "" ;  
			String catalog_id = "" ;
			
			 
			//response.setCharacterEncoding("UTF-8");
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//request.setCharacterEncoding(("UTF-8");
			if( request.getParameter("catalog_id") != null) catalog_id = request.getParameter("catalog_id");
			else catalog_id = authorizationPageBeanId.getCatalog_id() ;
			
			if( request.getParameter("fromcost") != null) fromcost = request.getParameter("fromcost");
			else fromcost = authorizationPageBeanId.getFromCost().toString(); 
			
			if( request.getParameter("tocost") != null) tocost = request.getParameter("tocost");
			else tocost = authorizationPageBeanId.getToCost().toString();
			
			if( request.getParameter("dayfrom_id") != null) dayfrom_id = request.getParameter("dayfrom_id") ;
			else dayfrom_id = Long.toString(authorizationPageBeanId.getDayfrom_id());   
			
			if( request.getParameter("mountfrom_id") != null) mountfrom_id = request.getParameter("mountfrom_id");
			else mountfrom_id = Long.toString(authorizationPageBeanId.getMountfrom_id()); 
			
			if( request.getParameter("yearfrom_id") != null) yearfrom_id = request.getParameter("yearfrom_id");
			else yearfrom_id =  Long.toString(authorizationPageBeanId.getYearfrom_id()); 

			if( request.getParameter("dayto_id") != null) dayto_id = request.getParameter("dayto_id") ;
			else dayto_id = Long.toString(authorizationPageBeanId.getDayto_id());  
			
			if( request.getParameter("mountto_id") != null) mountto_id = request.getParameter("mountto_id");
			else mountto_id = Long.toString(authorizationPageBeanId.getMountto_id()); 
			
			if( request.getParameter("yearto_id") != null)     yearto_id = request.getParameter("yearto_id");
			else yearto_id  = Long.toString(authorizationPageBeanId.getYearto_id()); 
			
			//if( request.getParameter("action") !=null ) action = request.getParameter("action");
			//else action =  productlistBeanId.getAction() ;
			
			if( request.getParameter("creteria1_id") !=null ) creteria1_id =  request.getParameter("creteria1_id");
			else creteria1_id =  Long.toString(authorizationPageBeanId.getCreteria1_id()); 

			if( request.getParameter("creteria2_id") !=null ) creteria2_id =  request.getParameter("creteria2_id");
			else creteria2_id =  Long.toString(authorizationPageBeanId.getCreteria2_id()); 

			if( request.getParameter("creteria3_id") !=null ) creteria3_id =  request.getParameter("creteria3_id");
			else creteria3_id =  Long.toString(authorizationPageBeanId.getCreteria3_id()); 

			if( request.getParameter("creteria4_id") !=null ) creteria4_id =  request.getParameter("creteria4_id");
			else creteria4_id =  Long.toString(authorizationPageBeanId.getCreteria4_id()); 

			if( request.getParameter("creteria5_id") !=null ) creteria5_id =  request.getParameter("creteria5_id");
			else creteria5_id =  Long.toString(authorizationPageBeanId.getCreteria5_id()); 
			
			if( request.getParameter("creteria6_id") !=null ) creteria6_id =  request.getParameter("creteria6_id");
			else creteria6_id = Long.toString( authorizationPageBeanId.getCreteria6_id()); 

			if( request.getParameter("creteria7_id") !=null ) creteria7_id =  request.getParameter("creteria7_id");
			else creteria7_id =  Long.toString(authorizationPageBeanId.getCreteria7_id()); 
			
			if( request.getParameter("creteria8_id") !=null ) creteria8_id =  request.getParameter("creteria8_id");
			else creteria8_id =  Long.toString(authorizationPageBeanId.getCreteria8_id()); 
			
			if( request.getParameter("creteria9_id") !=null ) creteria9_id =  request.getParameter("creteria9_id");
			else creteria9_id =  Long.toString(authorizationPageBeanId.getCreteria9_id()); 

			if( request.getParameter("creteria10_id") !=null ) creteria10_id =  request.getParameter("creteria10_id");
			else creteria10_id =  Long.toString(authorizationPageBeanId.getCreteria10_id()); 

			if( request.getParameter("offset") != null  ) offset = request.getParameter("offset"); 
			else offset = "" + productlistBeanId.getOffset() ;
			
			if( request.getParameter("searchquery") !=null  )  searchquery = request.getParameter("searchquery");
			else searchquery = "" +  productlistBeanId.getSearchquery();	
			
			
			//if( request.getParameter("locale") !=null  )  searchquery = request.getParameter("locale");
			//else searchquery = "" +  productlistBeanId.getSearchquery();	
			
			buff.append("_df_") ;
			buff.append(dayfrom_id) ;
			buff.append("_mf_") ;
			buff.append(mountfrom_id);
			buff.append("_yf_") ;
			buff.append(yearfrom_id);
			buff.append("_fc_") ;
			buff.append(fromcost);
			buff.append("_tc_") ;
			buff.append(tocost);
			buff.append("_dy_") ;
			buff.append(dayto_id);
			buff.append("_mt_") ;
			buff.append(mountto_id);
			buff.append("_yt_") ;
			buff.append(yearto_id);
			buff.append("_c1_") ;
			buff.append(creteria1_id);
			buff.append("_c2_") ;
			buff.append(creteria2_id);
			buff.append("_c3_") ;
			buff.append(creteria3_id);
			buff.append("_c4_") ;
			buff.append(creteria4_id);
			buff.append("_c5_") ;
			buff.append(creteria5_id);	
			buff.append("_c6_") ;
			buff.append(creteria6_id);
			buff.append("_c7_") ;
			buff.append(creteria7_id);
			buff.append("_c8_") ;
			buff.append(creteria8_id);
			buff.append("_c9_") ;
			buff.append(creteria9_id);
			buff.append("_c10_") ;
			buff.append(creteria10_id);	
			buff.append("_off_") ;
			buff.append(offset);
			buff.append("_sq_") ;
			buff.append(searchquery);
			buff.append("_catalog_");
			buff.append(catalog_id);
			buff.append("_l_");
			buff.append(authorizationPageBeanId.getLocale());
			buff.append("_s_");
			buff.append(authorizationPageBeanId.getSite_id());
			//return  Long.toString(buff.toString().hashCode());
			return  buff.toString();
			}
		} 
		else if(path.equals("Policy.jsp"))
		{
			//return Long.toString(((HttpServletRequest) request).getQueryString().concat(request.getLocale().toString()).concat(authorizationPageBeanId.getSite_id()).hashCode());
			return ((HttpServletRequest) request).getQueryString().concat(authorizationPageBeanId.getLocale().toString()).concat(authorizationPageBeanId.getSite_id());
		}
		
		
		return  "" ;
		}


	public void clearAllXSLTemplates() 
	{
		authorizationPageFaced.getTransformerMap().clear() ;
	}

	public boolean isAllowLocale(String locale) {
		if (locale == null) return false;
		String[] IntField = { "en","ru" } ;
		for (int i = 0; i < IntField.length ; i++) 
		{
			if (IntField[i].compareTo(locale) == 0 ) 
			{
					return true;
			}
		}
		return false;
	}
	
	public void saveXMLFile(String filename ,  String xmlData   ) 
	{
		try 
		{
			FileOutputStream fout = new  FileOutputStream(filename);
			 
			if (fout == null)	return;

			fout.write(xmlData.getBytes());
			fout.close();
			return;
		} 
		catch (java.lang.Exception e) 
		{
			log.error(e) ;
			System.out.println(e.toString());
		}
	}

	boolean getUserCookies(ServletContext servletContext ) 
	{
		return (Boolean)servletContext.getAttribute("use_cookies");
	}
	
	void setUserCookies(ServletContext servletContext , String use_cookies) 
	{
		servletContext.setAttribute("use_cookies", Boolean.parseBoolean(use_cookies));
	}
	
	void setCookiesDir(ServletContext servletContext , String cookies_dir) 
	{
		servletContext.setAttribute("cookies_dir", cookies_dir);
	}
	String  getCookiesDir(ServletContext servletContext) 
	{
		return (String)servletContext.getAttribute("cookies_dir");
	}
	
	
	
	
	
}