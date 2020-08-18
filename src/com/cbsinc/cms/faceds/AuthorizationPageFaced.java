package com.cbsinc.cms.faceds;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.CreateShopBean;
import com.cbsinc.cms.GetValueTool;
import com.cbsinc.cms.QueryManager;
import com.cbsinc.cms.controllers.SiteType;
import com.cbsinc.cms.services.tomcat.AddAliase;
import com.cbsinc.cms.services.tomcat.DomainRegister;

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
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: CENTER BUSINESS SOLUTIONS INC 
 * </p>
 * 
 * @author Konstantin Grabko
 * @version 1.0
 */


public class AuthorizationPageFaced extends com.cbsinc.cms.WebControls implements
		java.io.Serializable {

	
	private static final long serialVersionUID = -6909056318575957347L;
	
	final CreateShopBean  createShopBean = new CreateShopBean();
//	sequences_rs = PropertyResourceBundle.getBundle("sequence");
	transient final private ResourceBundle sequences_rs = PropertyResourceBundle.getBundle("sequence");
	transient final private ResourceBundle setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources") ;
	transient final private ResourceBundle session_scope = PropertyResourceBundle.getBundle("session_scope") ;
	
	final ConcurrentHashMap controllerMap = new ConcurrentHashMap(1024);
	final ConcurrentHashMap transformerMap = new ConcurrentHashMap(1024);
	
	transient final ResourceBundle actions_resources = PropertyResourceBundle.getBundle("web_actions");
	transient final ResourceBundle xslt_resources = PropertyResourceBundle.getBundle("web_xslt");
	
	final Document doc = null ;
	//transient ResourceBundle application_scope = null ;
	
//	public ResourceBundle getApplication_scope() {
//		return application_scope;
//	}
//
//	public void setApplication_scope(ResourceBundle application_scope) {
//		this.application_scope = application_scope;
//	}

	public AuthorizationPageFaced() 
	{
		//if( sequences_rs == null )  sequences_rs = PropertyResourceBundle.getBundle("sequence");
		//if( setup_resources == null ) setup_resources =  PropertyResourceBundle.getBundle("SetupApplicationResources");
		//if( session_scope == null )  session_scope = PropertyResourceBundle.getBundle("session_scope");

		//if( actions_resources == null )  actions_resources = PropertyResourceBundle.getBundle("web_actions");
		//if( xslt_resources == null )  xslt_resources = PropertyResourceBundle.getBundle("web_xslt");
		

	}
	
	

	final static private Logger log = Logger.getLogger(AuthorizationPageFaced.class);

	final public String getCokieSessionId(final HttpServletRequest request, final HttpServletResponse response )
	{
		Cookie[] cookies =  request.getCookies();
		if(cookies != null)
		{
	 	 for(int i = 0 ; i < cookies.length ; i++ )
		  {
			if(cookies[i].getName().equals("session_id"))
			{
				return cookies[i].getValue() ;
			}
		  }
		}
		HttpSession httpSession =  request.getSession() ;
		response.addCookie(new Cookie("session_id",httpSession.getId()));
		return httpSession.getId() ;
	}

	final public boolean isCokieSessionIdExists(final HttpServletRequest request, final HttpServletResponse response )
	{
		boolean exists = false ;
		Cookie[] cookies =  request.getCookies();
		if(cookies != null)
		{
	 	 for(int i = 0 ; i < cookies.length ; i++ )
		  {
			if(cookies[i].getName().equals("session_id"))
			{
				return true ;
			}
		  }
		}

		return exists ;
	}
	
	final public boolean isLoginCorrect(final String i_strLogin, final String i_strPasswd , final AuthorizationPageBean authorizationBean, String idsession) {
		QueryManager Adp = null;
		String query ="";
		try {
			if (i_strLogin == null || i_strLogin.length() == 0)	return false;
			if (i_strPasswd == null || i_strPasswd.length() == 0)	return false;
			
			
			Adp = new QueryManager();
			Adp.BeginTransaction();
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  FROM tuser  where  login = '"
					+ i_strLogin + "'   and  passwd = '" + i_strPasswd + "' and  site_id = " + authorizationBean.getSite_id() ;
			Adp.executeQuery(query);
			if (Adp.rows().size() == 1) {
				authorizationBean.setIntUserID(  Long.parseLong((String) Adp.getValueAt(0, 0)));
				authorizationBean.setStrLogin( (String) Adp.getValueAt(0, 1));
				authorizationBean.setStrPasswd ((String) Adp.getValueAt(0, 2));
				authorizationBean.setStrFirstName( (String) Adp.getValueAt(0, 3));
				authorizationBean.setStrLastName((String) Adp.getValueAt(0, 4));
				authorizationBean.setStrEMail((String) Adp.getValueAt(0, 5));
				authorizationBean.setStrPhone((String) Adp.getValueAt(0, 6));
				authorizationBean.setStrMPhone((String) Adp.getValueAt(0, 7));
				authorizationBean.setStrFax((String) Adp.getValueAt(0, 8));
				authorizationBean.setStrIcq((String) Adp.getValueAt(0, 9));
				authorizationBean.setStrWebsite((String) Adp.getValueAt(0, 10));
				authorizationBean.setStrQuestion((String) Adp.getValueAt(0, 11));
				authorizationBean.setStrAnswer((String) Adp.getValueAt(0, 12));
				authorizationBean.setIntLevelUp( Integer.parseInt((String) Adp.getValueAt(0, 13))) ;
				authorizationBean.setStrCompany((String) Adp.getValueAt(0, 15));
				authorizationBean.setCountry_id ((String) Adp.getValueAt(0, 16));
				authorizationBean.setCity_id((String) Adp.getValueAt(0, 17));
				authorizationBean.setCurrency_id( (String) Adp.getValueAt(0, 18));
				authorizationBean.setIntLogined(1);
				
				
				//query = "UPDATE tuser set idsession = '"+  authorizationBean.getId_session() +"'  where  (login = '" + i_strLogin + "' )  and  (passwd = '" + i_strPasswd	+ "') and  (site_id = " + authorizationBean.getSite_id() + ")";
				query = "UPDATE tuser set idsession = '"+  idsession +"'  where  user_id =" + authorizationBean.getIntUserID() ;
				
				Adp.executeUpdate(query);
				Adp.commit();
				return true;
			}
			authorizationBean.setIntLogined(2);
			return false;
		} 
		catch (SQLException ex) {
			Adp.rollback();
			log.error(query,ex);
			return false;
		}
		catch (Exception ex) {
			Adp.rollback();
			log.error(ex);
			return false;
		}
		finally 
		{
			if(Adp != null)	Adp.close();
		}
	}
	
	final public String getSiteIdByHost(final String host)
	{
		
		String siteId = "2" ;
		QueryManager Adp =  null ;
		//String query = "select site_id  from site where site_dir = '" + host + "'" ;
		String query = "select site_id  from site where host = '" + host + "' order by site_id DESC limit 1 " ;
		try 
		{
			Adp = new QueryManager();
			Adp.executeQuery(query);
			if ( Adp.rows().size() > 0) 
			{
				siteId =  Adp.getValueAt(0, 0);
			}
		}
		catch (SQLException e) 
		{
			log.error(e);
		}
		finally
		{
			Adp.close();
		}

	  return siteId ;
	}
	
	final void loadClassesSessionScopeFromBase(final HttpSession  session , final Integer user_id   ) 
	{
		//HttpServletRequest servletRequest = 
			//session.getServletContext().getNamedDispatcher("/").  
		
		QueryManager Adp = new QueryManager();
		String query = "" ;
		String key = "";
		//AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
		try {
		query = "select USER_ID , TYPE , CLASSBODY  from store_session WHERE  USER_ID = " + user_id ;
		ResultSet rs  = Adp.executeQueryResultSet(query);
		//ResultSet rs = ps.executeQuery("SELECT ENGINEERS FROM PERSONNEL");
		//ResultSetMetaData metaData = rs.getMetaData();
		//int columnCount = metaData.getColumnCount() ;
		
		//metaData.get
		while (rs.next()) 
		{
			//Engineer eng = (Engineer)rs.getObject("CLASSBODY");
			//System.out.println(eng.lastName + ", " + eng.firstName);
			Enumeration enumeration = session_scope.getKeys();
			while (enumeration.hasMoreElements()) 
			{
				key = (String) enumeration.nextElement();
				String type = session_scope.getString(key).trim();
				String typedb =  rs.getString("TYPE");
				if(typedb  != null ) if(typedb.equals(type))
				{
					//System.out.println("type: " + metaData.getColumnType(3));
					Object obj = rs.getObject("CLASSBODY") ;
//					rs.get
					session.setAttribute(key, obj);
					System.out.println("load key: " + key + " object: " + obj.getClass().getName()  );
				}
			}
		}
		
	
		}
		catch (Exception e) 
		{
			log.error(e) ;
		}
		finally
		{
			if(Adp != null)	Adp.close();
		}
	}
	
	final public void clearCookieFromBD( final AuthorizationPageBean authorizationBean, final String cokie_session_id) {
		QueryManager Adp = null;
		if(cokie_session_id == null) return ;
		String query ="";
		try 
		{

			Adp = new QueryManager();
			Adp.BeginTransaction();
			//query = "UPDATE tuser set idsession = '"+  authorizationBean.getId_session() +"'  where  (login = '" + i_strLogin + "' )  and  (passwd = '" + i_strPasswd	+ "') and  (site_id = " + authorizationBean.getSite_id() + ")";
			//query = "UPDATE tuser set idsession = ''  where  user_id =" + authorizationBean.getIntUserID() ;
			query = "DELETE  FROM  store_session WHERE  idsession_hash1 = " + getIdsessionHash1(cokie_session_id) + "  and idsession_hash2 = " +  getIdsessionHash2(cokie_session_id) + " and idsession_hash3 = " +  getIdsessionHash3(cokie_session_id) + " and idsession_hash4 = " +  getIdsessionHash4(cokie_session_id)  ;
			Adp.executeUpdate(query);
			Adp.commit();
			if(authorizationBean != null )authorizationBean.setIntLogined(2);
		} 
		catch (SQLException ex) {
			Adp.rollback();
			log.error(query,ex);
		}
		catch (Exception ex) {
			Adp.rollback();
			log.error(ex);
		}
		finally 
		{
			if(Adp != null)	Adp.close();
		}
	}
	
	final public boolean isLoginCorrect(final String id_session , final AuthorizationPageBean authorizationBean) {
		QueryManager Adp = null;
		String query ="";
		try {
			if (id_session == null || id_session.length() == 0)	return false;
			
			
			Adp = new QueryManager();
			// query = "SELECT user_id , login , passwd , first_name , last_name
			// , e_mail , phone ,
			// mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd ,
			// company , country_id , city_id , currency_id FROM tuser where
			// (login = '" + i_strLogin + "' ) and (passwd = '" + i_strPasswd +
			// "')" ;
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  FROM tuser  where  " +
					" idsession = '" + id_session + "'" ;
			
			Adp.executeQuery(query);
			if (Adp.rows().size() == 1) {
				authorizationBean.setIntUserID(  Integer.parseInt((String) Adp.getValueAt(0, 0)));
				authorizationBean.setStrLogin( (String) Adp.getValueAt(0, 1));
				authorizationBean.setStrPasswd ((String) Adp.getValueAt(0, 2));
				authorizationBean.setStrFirstName( (String) Adp.getValueAt(0, 3));
				authorizationBean.setStrLastName((String) Adp.getValueAt(0, 4));
				authorizationBean.setStrEMail((String) Adp.getValueAt(0, 5));
				authorizationBean.setStrPhone((String) Adp.getValueAt(0, 6));
				authorizationBean.setStrMPhone((String) Adp.getValueAt(0, 7));
				authorizationBean.setStrFax((String) Adp.getValueAt(0, 8));
				authorizationBean.setStrIcq((String) Adp.getValueAt(0, 9));
				authorizationBean.setStrWebsite((String) Adp.getValueAt(0, 10));
				authorizationBean.setStrQuestion((String) Adp.getValueAt(0, 11));
				authorizationBean.setStrAnswer((String) Adp.getValueAt(0, 12));
				authorizationBean.setIntLevelUp( Integer.parseInt((String) Adp.getValueAt(0, 13))) ;
				authorizationBean.setStrCompany((String) Adp.getValueAt(0, 15));
				authorizationBean.setCountry_id ((String) Adp.getValueAt(0, 16));
				authorizationBean.setCity_id((String) Adp.getValueAt(0, 17));
				authorizationBean.setCurrency_id( (String) Adp.getValueAt(0, 18));
				authorizationBean.setIntLogined(1);

				// Adp.close();
				return true;
			}
			authorizationBean.setIntLogined(2);
			// Adp.close();
			return false;
		} 
		catch (SQLException ex) {
			log.error(query,ex);
			return false;
		}
		catch (Exception ex) {
			log.error(ex);
			return false;
		}
		finally 
		{
			if(Adp != null)	Adp.close();
		}
	}
	
	
	
	final public boolean isLoginFromCookie(final String id_session ,final HttpSession  session ,final  ServletContext  servletContext  , ResourceBundle session_scope  ) {
		boolean result = false ;
		QueryManager Adp = new QueryManager();
		String query ="";
		if(!session.isNew()) return session.isNew() ;
		try {
			if (id_session == null || id_session.length() == 0)	return false;
		
			//StandardSession ss = (StandardSession)session ;
			
			String key = "";
			query = "select USER_ID , TYPE , CLASSBODY  from store_session WHERE  USER_ID IN (SELECT user_id  FROM tuser where idsession = '" + id_session + "' ) " ;
			ResultSet rs  = Adp.executeQueryResultSet(query);
			while (rs.next()) 
			{
				result = true ;
				Enumeration enumeration = session_scope.getKeys();
				while (enumeration.hasMoreElements()) 
				{
					key = (String) enumeration.nextElement();
					String type = session_scope.getString(key).trim();
					String typedb =  rs.getString("TYPE");
					if(typedb  != null ) if(typedb.equals(type))
					{
						Object obj = rs.getObject("CLASSBODY") ;
						Object newobj =  createObject(type);
						BeanUtils.copyProperties(newobj, obj);
						session.setAttribute(key, newobj);
						obj = null ;
						//System.out.println("load key: " + key + " object: " + obj.getClass().getName()  );
					}
				}
			}

			
		} 
		catch (SQLException ex) {
			log.error(query,ex);
			result = false;
		}
		catch (Exception ex) {
			log.error(ex);
			result =  false;
		}
		finally 
		{
			if(Adp != null)	Adp.close();
		}
		
		return result;
	}
	
	final String  getCookiesDir(final ServletContext servletContext) 
	{
		return (String)servletContext.getAttribute("cookies_dir");
	}
	
	
	final public boolean isLoginFromCookieFromDir(final String id_session , final HttpSession  session , final ServletContext  servletContext  , final ResourceBundle session_scope  ) {
		boolean result = false ;
		
		
		String path_dir =  getCookiesDir(servletContext);
		if(!session.isNew()) return session.isNew() ;
		try {
			if (id_session == null || id_session.length() == 0)	return false;
			String file_cookies = path_dir  +  File.separatorChar + id_session ;
			File file = new File(file_cookies);
			if(file.exists())
			{
				Map map = deserializeObject(file) ;
				Set keyList = map.keySet() ;
				Iterator iterator = keyList.iterator() ;
				String type = "" ;
				while(iterator.hasNext())
				{
					String key = (String)iterator.next() ;
					Object obj = map.get(key);
					type = session_scope.getString(key).trim();
					Object newobj =  createObject(type);
					if(newobj == null || obj == null) continue ; 
					BeanUtils.copyProperties(newobj, obj);
					session.setAttribute(key, obj);
					System.out.println("key " + key);
					System.out.println("object_new " + newobj);
					System.out.println("object " + obj);
				}
				
			}
			
		
		} 
		catch (Exception ex) {
			log.error(ex);
			result =  false;
		}
		finally 
		{
			
		}
		
		return result;
	}
	
	
//	long getIdsessionHash1(String id_session )
//	{
//		String hash = id_session.substring(0, 10) ;
//		return  hash.hashCode() ;
//	}
//
//	long getIdsessionHash2(String id_session )
//	{
//		String hash = id_session.substring(10, 20) ;
//		return  hash.hashCode() ;
//	}
//
//	long getIdsessionHash3(String id_session )
//	{
//		String hash = id_session.substring(20, 30) ;
//		return  hash.hashCode() ;
//	}
//
//	long getIdsessionHash4(String id_session )
//	{
//		String hash = id_session.substring(30) ;
//		if(hash.indexOf(".") != -1 )hash = hash.substring(0 ,hash.indexOf(".")) ;
//		return  hash.hashCode() ;
//	}

	
final	public boolean isLoginFromCookie_new(final String id_session , final HttpSession  session , final ResourceBundle session_scope  ) {
		boolean result = false ;
		QueryManager Adp = new QueryManager();
		String query ="";
		//if(!session.isNew()) return session.isNew() ;
		try {
			if (id_session == null || id_session.length() == 0)	return false;
		
			//StandardSession ss = (StandardSession)session ;
			
			String key = "";
			query = "select USER_ID , TYPE , CLASSBODY  from store_session WHERE  idsession_hash1 = " + getIdsessionHash1(id_session) + "  and idsession_hash2 = " +  getIdsessionHash2(id_session) + " and idsession_hash3 = " +  getIdsessionHash3(id_session) + " and idsession_hash4 = " +  getIdsessionHash4(id_session)  ;
			ResultSet rs  = Adp.executeQueryResultSet(query);
			while (rs.next()) 
			{
				result = true ;
				Enumeration enumeration = session_scope.getKeys();
				while (enumeration.hasMoreElements()) 
				{
					key = (String) enumeration.nextElement();
					String type = session_scope.getString(key).trim();
					String typedb =  rs.getString("TYPE");
					if(typedb  != null ) if(typedb.equals(type))
					{
						Object obj = rs.getObject("CLASSBODY") ;
						Object newobj =  createObject(type);
						BeanUtils.copyProperties(newobj, obj);
						session.setAttribute(key, newobj);
						obj = null ;
						//System.out.println("load key: " + key + " object: " + obj.getClass().getName()  );
					}
				}
			}

			
		} 
		catch (SQLException ex) {
			log.error(query,ex);
			result = false;
		}
		catch (Exception ex) {
			log.error(ex);
			result =  false;
		}
		finally 
		{
			if(Adp != null)	Adp.close();
		}
		
		return result;
	}
	


final	public boolean isLoginFromCookie_new1(final String id_session , final HttpSession  session , final ResourceBundle session_scope  ) {
	boolean result = false ;
	QueryManager Adp = new QueryManager();
	String query ="";
	ByteArrayInputStream bais = null ;
	ObjectInputStream ois = null ;
	//if(!session.isNew()) return session.isNew() ;

	
	try {
		if (id_session == null || id_session.length() == 0)	return false;
	
		//StandardSession ss = (StandardSession)session ;
		
		String key = "";
		query = "select USER_ID , TYPE , BCLASSBODY  from store_session WHERE  idsession_hash1 = " + getIdsessionHash1(id_session) + "  and idsession_hash2 = " +  getIdsessionHash2(id_session) + " and idsession_hash3 = " +  getIdsessionHash3(id_session) + " and idsession_hash4 = " +  getIdsessionHash4(id_session)  ;
		ResultSet rs  = Adp.executeQueryResultSet(query);
		while (rs.next()) 
		{
			result = true ;
			Enumeration enumeration = session_scope.getKeys();
			while (enumeration.hasMoreElements()) 
			{
				key = (String) enumeration.nextElement();
				String type = session_scope.getString(key).trim();
				String typedb =  rs.getString("TYPE");
				if(typedb  != null ) if(typedb.equals(type))
				{
					 bais = new ByteArrayInputStream(rs.getBytes("BCLASSBODY"));
					 ois = new  ObjectInputStream(bais);
					 Object obj = ois.readObject() ;
					session.setAttribute(key, obj);
				}
			}
		}

		
	} 
	catch (SQLException ex) {
		log.error(query,ex);
		result = false;
	}
	catch (Exception ex) {
		log.error(ex);
		result =  false;
	}
	finally 
	{
		try {
			if(bais != null ) bais.close();
			if(ois != null ) ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(Adp != null)	Adp.close();
	}
	
	return result;
}

final	public boolean loadOldSessionbyLogin(final String user_id , final HttpSession  session , final ResourceBundle session_scope  ) {
	boolean result = false ;
	QueryManager Adp = new QueryManager();
	String query ="";
	//if(!session.isNew()) return session.isNew() ;
	try {
		if (user_id == null || user_id.length() == 0)	return false;
	
		//StandardSession ss = (StandardSession)session ;
		
		String key = "";
		query = "select USER_ID , TYPE , CLASSBODY  from store_session WHERE  USER_ID = " + user_id   ;
		ResultSet rs  = Adp.executeQueryResultSet(query);
		while (rs.next()) 
		{
			result = true ;
			Enumeration enumeration = session_scope.getKeys();
			while (enumeration.hasMoreElements()) 
			{
				key = (String) enumeration.nextElement();
				String type = session_scope.getString(key).trim();
				String typedb =  rs.getString("TYPE");
				if(typedb  != null ) if(typedb.equals(type))
				{
					Object obj = rs.getObject("CLASSBODY") ;
					Object newobj =  createObject(type);
					BeanUtils.copyProperties(newobj, obj);
					session.setAttribute(key, newobj);
					obj = null ;
					//System.out.println("load key: " + key + " object: " + obj.getClass().getName()  );
				}
			}
		}

		
	} 
	catch (SQLException ex) {
		log.error(query,ex);
		result = false;
	}
	catch (Exception ex) {
		log.error(ex);
		result =  false;
	}
	finally 
	{
		if(Adp != null)	Adp.close();
	}
	
	return result;
}
	
	final public Object createObject( final String className) {
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
	
	final public Map deserializeObject(final File fileName) {
		
		Map map = null ;
		ObjectInputStream in  = null ;
		try 
		{
 		in = new ObjectInputStream(new FileInputStream(fileName));
 		map = (Map)in.readObject();

	    }
		catch (Exception ex) 
		{
		log.error(ex) ;
		}
		finally
		{
			if(in != null)
				try 
				{
					in.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
		}
		return map;
		}
	
	 final public AuthorizationPageBean getAuthorizationBean(final String userId ) {
		QueryManager Adp = null;
		AuthorizationPageBean authorization = new AuthorizationPageBean();
		String query ="";
		try {
			if (userId == null || userId.length() == 0)	return null;
			
			
			Adp = new QueryManager();
			// query = "SELECT user_id , login , passwd , first_name , last_name
			// , e_mail , phone ,
			// mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd ,
			// company , country_id , city_id , currency_id FROM tuser where
			// (login = '" + i_strLogin + "' ) and (passwd = '" + i_strPasswd +
			// "')" ;
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  " +
					"FROM tuser  where  user_id = "	+ userId + "" ;
			Adp.executeQuery(query);
			if (Adp.rows().size() == 1) {
				authorization.setIntUserID(  Integer.parseInt((String) Adp.getValueAt(0, 0)));
				authorization.setStrLogin( (String) Adp.getValueAt(0, 1));
				authorization.setStrPasswd ((String) Adp.getValueAt(0, 2));
				authorization.setStrFirstName( (String) Adp.getValueAt(0, 3));
				authorization.setStrLastName((String) Adp.getValueAt(0, 4));
				authorization.setStrEMail((String) Adp.getValueAt(0, 5));
				authorization.setStrPhone((String) Adp.getValueAt(0, 6));
				authorization.setStrMPhone((String) Adp.getValueAt(0, 7));
				authorization.setStrFax((String) Adp.getValueAt(0, 8));
				authorization.setStrIcq((String) Adp.getValueAt(0, 9));
				authorization.setStrWebsite((String) Adp.getValueAt(0, 10));
				authorization.setIntLevelUp( Integer.parseInt((String) Adp.getValueAt(0, 13))) ;
				authorization.setStrCompany((String) Adp.getValueAt(0, 15));
				authorization.setCountry_id ((String) Adp.getValueAt(0, 16));
				authorization.setCity_id((String) Adp.getValueAt(0, 17));
				authorization.setCurrency_id( (String) Adp.getValueAt(0, 18));
				return authorization;
			}
			return authorization;
		} 
		catch (SQLException ex) {
			log.error(query,ex);
			return authorization;
		}
		catch (Exception ex) {
			log.error(ex);
			return authorization;
		}
		finally 
		{
			if(Adp != null)	Adp.close();
		}
	}

	
	final public AuthorizationPageBean getAuthorizationBeanOfRoleAdmin(final String siteId ) {
		QueryManager Adp = null;
		AuthorizationPageBean authorization = new AuthorizationPageBean();
		String query ="";
		try {
			if (siteId == null || siteId.length() == 0)	return null;
			
			
			Adp = new QueryManager();
			// query = "SELECT user_id , login , passwd , first_name , last_name
			// , e_mail , phone ,
			// mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd ,
			// company , country_id , city_id , currency_id FROM tuser where
			// (login = '" + i_strLogin + "' ) and (passwd = '" + i_strPasswd +
			// "')" ;
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  " +
					"FROM tuser  where  levelup_cd = 2 and site_id = "	+ siteId + "" ;
			Adp.executeQuery(query);
			if (Adp.rows().size() == 1) {
				authorization.setIntUserID(  Long.parseLong((String) Adp.getValueAt(0, 0)));
				authorization.setStrLogin( (String) Adp.getValueAt(0, 1));
				authorization.setStrPasswd ((String) Adp.getValueAt(0, 2));
				authorization.setStrFirstName( (String) Adp.getValueAt(0, 3));
				authorization.setStrLastName((String) Adp.getValueAt(0, 4));
				authorization.setStrEMail((String) Adp.getValueAt(0, 5));
				authorization.setStrPhone((String) Adp.getValueAt(0, 6));
				authorization.setStrMPhone((String) Adp.getValueAt(0, 7));
				authorization.setStrFax((String) Adp.getValueAt(0, 8));
				authorization.setStrIcq((String) Adp.getValueAt(0, 9));
				authorization.setStrWebsite((String) Adp.getValueAt(0, 10));
				authorization.setIntLevelUp( Integer.parseInt((String) Adp.getValueAt(0, 13))) ;
				authorization.setStrCompany((String) Adp.getValueAt(0, 15));
				authorization.setCountry_id ((String) Adp.getValueAt(0, 16));
				authorization.setCity_id((String) Adp.getValueAt(0, 17));
				authorization.setCurrency_id( (String) Adp.getValueAt(0, 18));
				return authorization;
			}
			return authorization;
		} 
		catch (SQLException ex) {
			log.error(query,ex);
			return authorization;
		}
		catch (Exception ex) {
			log.error(ex);
			return authorization;
		}
		finally 
		{
			if(Adp != null)	Adp.close();
		}
	}

	
final	public AuthorizationPageBean getFromMainSiteUserAuthorizationBean(final String login ) {
		QueryManager Adp = null;
		AuthorizationPageBean authorization = new AuthorizationPageBean();
		String query ="";
		try {
			if (login == null || login.length() == 0)	return null;
			
			
			Adp = new QueryManager();
			// query = "SELECT user_id , login , passwd , first_name , last_name
			// , e_mail , phone ,
			// mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd ,
			// company , country_id , city_id , currency_id FROM tuser where
			// (login = '" + i_strLogin + "' ) and (passwd = '" + i_strPasswd +
			// "')" ;
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  " +
					"FROM tuser  where  user_id = '" + login + "' and  site_id = " + SiteType.MAIN_SITE  ;
			Adp.executeQuery(query);
			if (Adp.rows().size() == 1) {
				authorization.setIntUserID(  Long.parseLong((String) Adp.getValueAt(0, 0)));
				authorization.setStrLogin( (String) Adp.getValueAt(0, 1));
				authorization.setStrPasswd ((String) Adp.getValueAt(0, 2));
				authorization.setStrFirstName( (String) Adp.getValueAt(0, 3));
				authorization.setStrLastName((String) Adp.getValueAt(0, 4));
				authorization.setStrEMail((String) Adp.getValueAt(0, 5));
				authorization.setStrPhone((String) Adp.getValueAt(0, 6));
				authorization.setStrMPhone((String) Adp.getValueAt(0, 7));
				authorization.setStrFax((String) Adp.getValueAt(0, 8));
				authorization.setStrIcq((String) Adp.getValueAt(0, 9));
				authorization.setStrWebsite((String) Adp.getValueAt(0, 10));
				authorization.setIntLevelUp( Integer.parseInt((String) Adp.getValueAt(0, 13))) ;
				authorization.setStrCompany((String) Adp.getValueAt(0, 15));
				authorization.setCountry_id ((String) Adp.getValueAt(0, 16));
				authorization.setCity_id((String) Adp.getValueAt(0, 17));
				authorization.setCurrency_id( (String) Adp.getValueAt(0, 18));
				return authorization;
			}
			return authorization;
		} 
		catch (SQLException ex) {
			log.error(query,ex);
			return authorization;
		}
		catch (Exception ex) {
			log.error(ex);
			return authorization;
		}
		finally 
		{
			if(Adp != null)	Adp.close();
		}
	}
	
	
	final public int getFromMainSiteUserId(final String login ) {
		QueryManager Adp = null;
		String query ="";
		try {
			if (login == null || login.length() == 0)	return 0;
			Adp = new QueryManager();
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  " +
					"FROM tuser  where  user_id = '" + login + "' and  site_id = " + SiteType.MAIN_SITE  ;
			Adp.executeQuery(query);
			if (Adp.rows().size() == 1) {
				//authorization.setIntUserID(  Integer.parseInt((String) Adp.getValueAt(0, 0)));
				return Integer.parseInt((String) Adp.getValueAt(0, 0));
			}
			return 0;
		} 
		catch (SQLException ex) {
			log.error(query,ex);
			return 0;
		}
		catch (Exception ex) {
			log.error(ex);
			return 0;
		}
		finally 
		{
			if(Adp != null)	Adp.close();
		}
	}
	
	final public int isRegCorrect(final String i_strLogin, final String i_strPasswd,
			final String i_strCPasswd,final String i_strFName,final String i_strLName,
			final String i_strCompany,final String i_strEMail,final String i_strPhone,
			final String i_strMPhone,final String i_strFax, final String i_strIcq,
			final String i_strWebsite,final String i_strQuestion,final String i_strAnswer,
			final String i_strBirthday,final String i_strCountry_id,final String i_strCity_id,
			final String i_strCurrency_id ,final String cookie_session_id  , final AuthorizationPageBean authorizationBean) {

		QueryManager Adp = null;
		String query = "";
		try {
			if (i_strLogin == null || i_strLogin.length() == 0)
				return 1;
			if (i_strPasswd == null || i_strPasswd.length() == 0)
				return 2;
			if (i_strPasswd.length() < 6)	return 12;
			
			if (i_strCPasswd == null || i_strPasswd.length() == 0)
				return 10;
			if (i_strFName == null || i_strFName.length() == 0)
				return 3;
			if (i_strLName == null || i_strLName.length() == 0)
				return 4;
			if (i_strEMail == null || i_strEMail.length() == 0)
				return 5;
			if (i_strCity_id == null || i_strCity_id.length() == 0  )
				return 11;
			if (i_strEMail.indexOf("@") == -1)
				return 8;
			if (i_strPasswd.compareTo(i_strCPasswd) != 0)
				return 9;
			if (!isEnglish(i_strLogin))	return 13;
			
			Adp = new QueryManager();
			Adp.BeginTransaction();
			query = "SELECT user_id  FROM tuser where  (login = '" + i_strLogin	+ "' )  and  (passwd = '" + i_strPasswd + "' )";
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0) {
				authorizationBean.setIntUserID( Long.parseLong((String) Adp.getValueAt(0, 0))) ;
				query = "update tuser set " + " login = '" + i_strLogin
						+ "' , " + "  passwd = '" + i_strPasswd + "' , "
						+ "  first_name = '" + i_strFName + "' , "
						+ "  last_name = '" + i_strLName + "' , "
						+ "  e_mail = '" + i_strEMail + "' , " + "  phone = '"
						+ i_strPhone + "' , " + "  mobil_phone = '"
						+ i_strMPhone + "' , " + "  fax = '" + i_strFax
						+ "' , " + "  icq = '" + i_strIcq + "' , "
						+ "  website = '" + i_strWebsite + "' , "
						+ "  question = '" + i_strQuestion + "' , "
						+ "  answer = '" + i_strAnswer + "' , "
						+ "  company  = '" + i_strCompany + "' , "
						+ "  country_id  = " + i_strCountry_id + " , "
						+ "  city_id  = " + i_strCity_id + " , "
						+ "  currency_id = " + i_strCurrency_id + " "
						+ "  where user_id = " + authorizationBean.getIntUserID();
				Adp.executeUpdate(query);
				authorizationBean.setIntLogined(1);
				Adp.commit();
				return 0;
			}

			/*
			 * else { intLogined = 2 ; intUserID = 0 ; strLogin = "" ;
			 * Adp.commit(); Adp.close(); return -1 ; }
			 */

			query = "SELECT user_id  FROM tuser  where login  = '" + i_strLogin	+ "'";
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0) {
				authorizationBean.setIntUserID(Long.parseLong((String) Adp.getValueAt(0, 0)));
				authorizationBean.setIntLogined(3);
				authorizationBean.setIntUserID(0);
				authorizationBean.setStrLogin("");
				Adp.commit();
				return -1;
			}


			//query = "SELECT NEXT VALUE FOR tuser_user_id_seq  AS ID  FROM ONE_SEQUENCES";
			query = sequences_rs.getString("tuser");
			Adp.executeQuery(query);
			authorizationBean.setIntUserID(Long.parseLong((String) Adp.getValueAt(0, 0)));

			query = "insert into tuser ( user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,acvive_session ,active ,regdate ,levelup_cd ,bank_cd , company , country_id , city_id , currency_id , site_id , idsession ) "
				+ " values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " ;

			Map args = Adp.getArgs();
			args.put("user_id", authorizationBean.getIntUserID()) ;
			args.put("login", i_strLogin) ;
			args.put("passwd", i_strPasswd) ;
			args.put("first_name", i_strFName) ;
			args.put("last_name", i_strLName) ;
			args.put("e_mail", i_strEMail) ;
			args.put("phone", i_strPhone ) ;
			args.put("mobil_phone", i_strMPhone ) ;
			args.put("fax", i_strFax ) ;
			args.put("icq", i_strIcq ) ;
			args.put("website", i_strWebsite ) ;
			args.put("question", i_strQuestion ) ;
			args.put("answer", i_strAnswer ) ;
			args.put("acvive_session" ,  true )  ;
			args.put("active" ,  true  ) ;
			args.put("regdate" ,   new java.util.Date()) ;
			args.put("levelup_cd" ,  1  );
			args.put("bank_cd" ,  0 );
			args.put("company" ,  i_strCompany ) ;
			args.put("country_id" ,  Long.parseLong(i_strCountry_id) ) ;
			args.put("city_id" ,  Long.parseLong(i_strCity_id) ) ;
			args.put("currency_id" ,  Long.parseLong(i_strCurrency_id) ) ;
			args.put("site_id",  Long.parseLong(authorizationBean.getSite_id())) ; 
			args.put("idsession",  cookie_session_id) ;
			Adp.executeInsertWithArgs(query, args);
			
			
			//+ "  idsession = '" + cookie_session_id + "' , "
			
			//query = "SELECT NEXT VALUE FOR account_id_seq  AS ID  FROM ONE_SEQUENCES";
			query = sequences_rs.getString("account");
			Adp.executeQuery(query);
			String account_id = (String) Adp.getValueAt(0, 0);

			query = "insert into account ( account_id , user_id , amount , curr , date_input ,  description ,  currency_id ) "
				+ " values (  ? , ? , ? , ? , ? ,  ? ,  ?  ) " ;
				
			args = Adp.getArgs();
			args.put("account_id" ,   Long.parseLong(account_id)  );
			args.put("user_id" ,  authorizationBean.getIntUserID()  );
			args.put("amount" ,  0  );
			args.put("curr" ,  3  );
			args.put("date_input" ,  new java.util.Date() );
			args.put("description" ,  " new_account "  );
			args.put("currency_id" ,  Long.parseLong(i_strCurrency_id)  );
			
			Adp.executeInsertWithArgs(query, args);
			
			authorizationBean.setIntLogined(1);
			authorizationBean.setIntLevelUp(1);
			Adp.commit();
			return 0;

		 } 
		 catch (SQLException ex) 
		 {
				log.error(query,ex);
				Adp.rollback();
				return -2;
		}
		catch (Exception ex) {
			log.error(ex);
			Adp.rollback();
			return -2;
		}

		finally 
		{
			if(Adp != null)	Adp.close();
		}

	}

	

	



	final public String getDateControl(final String name1, final String name2, final String name3,
			String query, int at, int to) {
		return super.getDateControl(name1, name2, name3, query, at, to);
	}

	


	

	final public void initSiteDir(final String site_id , final AuthorizationPageBean authorizationBean) {
		String query = "select site_dir, subject_site , nick_site , company_name , host from site where site_id = "
				+ site_id;
		QueryManager Adp = new QueryManager();
		// Adp.BeginTransaction();
		try {
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0) {
				authorizationBean.setSite_dir((String) Adp.getValueAt(0, 0)); // + " " +
				authorizationBean.setSubject_site((String) Adp.getValueAt(0, 1));
				authorizationBean.setNick_site((String) Adp.getValueAt(0, 2));
				authorizationBean.setCompany_name((String) Adp.getValueAt(0, 3));
				authorizationBean.setHost((String) Adp.getValueAt(0, 4));
			}
		}
		catch (SQLException ex) {
			log.error(query,ex);
		}
		catch (Exception ex) {
			log.error(ex);
		}

		finally {
			Adp.close();
		}

	}


	
	

	
		
	final public void initUserSite(final long user_id , final AuthorizationPageBean authorizationBean ) 
	{
		String query = "select site_id  from site where site.owner = " + user_id + " order by site.site_id desc ";
		
		QueryManager Adp = new QueryManager();

		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0) {
				authorizationBean.setUser_site((String) Adp.getValueAt(0, 0)); // + " " +
			}
			else authorizationBean.setUser_site("-1") ;
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
		}
		catch (Exception ex) 
		{
			log.error(ex);
		}

		finally {
			Adp.close();
		}

	}
	
	final public int getLengId(final String locale) 
	{
		String leng_id = "-1" ;
		String query = "select lang_id  from lang where lable = '" + locale + "'" ;
		QueryManager Adp = new QueryManager();

		try 
		{
			Adp.executeQuery(query);
			if (Adp.rows().size() != 0) {
				leng_id = (String) Adp.getValueAt(0, 0); // + " " +
			}
			
		}
		catch (SQLException ex) 
		{
			log.error(query,ex);
		}
		catch (Exception ex) 
		{
			log.error(ex);
		}

		finally {
			Adp.close();
		}
		
		return  Integer.parseInt(leng_id);
	}
	
	final public void initPaySys_Shop_cd(final String site_id , final AuthorizationPageBean authorizationBean ) {
		String query = "select  shop_cd from shop where site_id = " + site_id;
		QueryManager Adp = new QueryManager();
		Adp.BeginTransaction();
		try 
		{
			Adp.executeQuery(query);

			if (Adp.rows().size() != 0) {
				authorizationBean.setPaysys_shop_cd((String) Adp.getValueAt(0, 0));
				if (authorizationBean.getPaysys_shop_cd() == null)
					log.assertLog(false,
							"ERROR: select shop_cd  from shop where site_id = "
									+ site_id + " \n   shop_cd = null ");
			} else
				log.assertLog(false,
						"ERROR: select shop_cd  from shop where site_id = "
								+ site_id + " \n   shop_cd = null ");

		} 
		catch (SQLException ex) 
		{
			log.error(ex);
		}
		catch (Exception ex) 
		{
			log.error(ex);
		}
		finally 
		{
			Adp.close();
		}

	}

	
	
	
	
	
	final public String getUserList( final AuthorizationPageBean authorizationBean ) {
		StringBuffer table = new StringBuffer() ;
		QueryManager Adp = null;
		String query ="";
		
		if(authorizationBean.getIntLevelUp() != 2 ) return "" ;
		try {
			Adp = new QueryManager();
			// query = "SELECT user_id , login , passwd , first_name , last_name
			// , e_mail , phone ,
			// mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd ,
			// company , country_id , city_id , currency_id FROM tuser where
			// (login = '" + i_strLogin + "' ) and (passwd = '" + i_strPasswd +
			// "')" ;
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone , regdate FROM tuser  where	site_id = " + authorizationBean.getSite_id() + "";
			
			Adp.executeQuery(query);
			table.append("<table class=\"columns\">\n");
			table.append("<tbody>\n");
			
			table
			.append("<TR BGCOLOR=\"#8CACBB\" >"
					+ "<TD WIDTH=\"10%\" >№ </TD>"
					+ "<TD WIDTH=\"10%\" >Login  </TD>"
					+ "<TD WIDTH=\"10%\" >Password</TD>"
					+ "<TD WIDTH=\"10%\" >First Name</TD>"
					+ "<TD WIDTH=\"10%\" >Last Name</TD>"
					+ "<TD WIDTH=\"20%\" >E-Mail</TD>"
					+ "<TD WIDTH=\"10%\" >Phone</TD>"
					+ "<TD WIDTH=\"10%\" >Mobl-Phone</TD>"
					+ "<TD WIDTH=\"10%\" >Date</TD>"
					+ "</TR>\n");

			
			for (int i = 0; Adp.rows().size() > i; i ++) {
				table.append("<tr>") ;
				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 0));
				table.append("</td>");

				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 1));
				table.append("</td>");

				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 2));
				table.append("</td>");

				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 3));
				table.append("</td>");


				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 4));
				table.append("</td>");

				table.append("<td>"); 
				table.append("<a href='mailto:" +  Adp.getValueAt(i, 5) + " '  >" +  Adp.getValueAt(i, 5) + "</a>" );
				table.append("</td>");

				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 6));
				table.append("</td>");

				
				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 7));
				table.append("</td>");

				table.append("<td>"); 
				table.append( ((String)Adp.getValueAt(i, 8)).substring(0,16));
				table.append("</td>");

				
				table.append("</tr>") ;
			}
			table.append("</tbody>\n");
			table.append("</table>") ;
		} 
		catch (SQLException ex) {
			log.error(query,ex);
			
		}
		catch (Exception ex) {
			log.error(ex);
		}
		finally 
		{
			if(Adp != null)	Adp.close();
		}
		
	 return table.toString() ;
	}
	
	final public String getUserListAll( final AuthorizationPageBean authorizationBean ) {
		StringBuffer table = new StringBuffer() ;
		QueryManager Adp = null;
		String query ="";
		
		if(authorizationBean.getIntLevelUp() != 2 ) return "" ;
		try {
			Adp = new QueryManager();
			// query = "SELECT user_id , login , passwd , first_name , last_name
			// , e_mail , phone ,
			// mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd ,
			// company , country_id , city_id , currency_id FROM tuser where
			// (login = '" + i_strLogin + "' ) and (passwd = '" + i_strPasswd +
			// "')" ;
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone , regdate , site_id FROM tuser " ;
			
			Adp.executeQuery(query);
			table.append("<table class=\"columns\">\n");
			table.append("<tbody>\n");
			
			table
			.append("<TR BGCOLOR=\"#8CACBB\" >"
					+ "<TD WIDTH=\"10%\" >№ </TD>"
					+ "<TD WIDTH=\"10%\" >Login  </TD>"
					+ "<TD WIDTH=\"10%\" >Password</TD>"
					+ "<TD WIDTH=\"10%\" >First Name</TD>"
					+ "<TD WIDTH=\"10%\" >Last Name</TD>"
					+ "<TD WIDTH=\"20%\" >E-Mail</TD>"
					+ "<TD WIDTH=\"10%\" >Phone</TD>"
					+ "<TD WIDTH=\"10%\" >Mobl-Phone</TD>"
					+ "<TD WIDTH=\"10%\" >Date</TD>"
					+ "<TD WIDTH=\"10%\" >Site ID</TD>"
					+ "</TR>\n");

			
			for (int i = 0; Adp.rows().size() > i; i ++) {
				table.append("<tr>") ;
				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 0));
				table.append("</td>");

				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 1));
				table.append("</td>");

				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 2));
				table.append("</td>");

				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 3));
				table.append("</td>");


				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 4));
				table.append("</td>");

				table.append("<td>"); 
				table.append("<a href='mailto:" +  Adp.getValueAt(i, 5) + " '  >" +  Adp.getValueAt(i, 5) + "</a>" );
				table.append("</td>");

				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 6));
				table.append("</td>");

				
				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 7));
				table.append("</td>");

				table.append("<td>"); 
				table.append( ((String)Adp.getValueAt(i, 8)).substring(0,16));
				table.append("</td>");

				table.append("<td>"); 
				table.append(Adp.getValueAt(i, 9));
				table.append("</td>");
				
				table.append("</tr>") ;
			}
			table.append("</tbody>\n");
			table.append("</table>") ;
		} 
		catch (SQLException ex) {
			log.error(query,ex);
			
		}
		catch (Exception ex) {
			log.error(ex);
		}
		finally 
		{
			if(Adp != null)	Adp.close();
		}
		
	 return table.toString() ;
	}
	
	final public boolean isNumber(final String tmp) {
		if (tmp == null)
			return false;
		String IntField = "0123456789.";
		for (int i = 0; i < tmp.length(); i++) {

			if (IntField.indexOf(tmp.charAt(i)) == -1) {
				if (tmp.charAt(i) != '-' && i != 0)
					return false;
			}
		}
		return true;
	}

	final public boolean isEnglish( String tmp) {
		if (tmp == null)
			return false;
		tmp = tmp.toLowerCase() ;
		String IntField = "0123456789qwertyuiopasdfghjklzxcvbnm_-";
		for (int i = 0; i < tmp.length(); i++) {

			if (IntField.indexOf(tmp.charAt(i)) == -1) {
				if (tmp.charAt(i) != '-' && i != 0)
					return false;
			}
		}
		return true;
	}

	
	public final void saveNewDomain(final String host , final String site_id  ) 
	{
		QueryManager Adp = new QueryManager();
		String query = "" ;
		Adp.BeginTransaction();
		try {
					query = "UPDATE site SET HOST = ?  where SITE_ID =  " + site_id ;
					Map args = Adp.getArgs();
					args.put("HOST" , host );
					Adp.executeUpdateWithArgs(query, args);
					Adp.commit() ;		
		}
		catch (Exception e) 
		{
			Adp.rollback();
			log.error(e) ;
		}
		finally
		{
			if(Adp != null)	Adp.close();
		}
	}
	
	
	final void saveClassesSessionScope(final HttpSession  session  ) 
	{
		AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
		if( AuthorizationPageBeanId == null ) return ;
		//if( AuthorizationPageBeanId.getStrLogin().equals(SiteRole.GUEST)) return ;
		//if( AuthorizationPageBeanId.getIntLevelUp() == 0 ) return ;
		QueryManager Adp = new QueryManager();
		String query = "" ;
		String key = "";
		Adp.BeginTransaction();
		Enumeration enumeration;
		String type = null ;
		Object obj = null ;
		
		try {
			enumeration = session_scope.getKeys();
			while (enumeration.hasMoreElements()) 
			{
				key = (String) enumeration.nextElement();
				 type = session_scope.getString(key).trim();
				 obj = session.getAttribute(key) ;
				 if(obj == null ) continue ;
				 
				query = "select USER_ID from store_session WHERE USER_ID = " + AuthorizationPageBeanId.getIntUserID() + " AND TYPE = '" +type+ "'" ;
				Adp.executeQuery(query);
				
				if (Adp.rows().size() != 0) 
				{
					query = "update store_session  set  USER_ID = ? , TYPE = ? , CLASSBODY = ? , " +
					" ACTIVE = ? where USER_ID = " + AuthorizationPageBeanId.getIntUserID() + " AND TYPE = '" +type+ "'" ;
					Map args = Adp.getArgs();
					args.put("USER_ID" , AuthorizationPageBeanId.getIntUserID()  );
					args.put("TYPE" , type );
					args.put("CLASSBODY" ,  obj);
					args.put("ACTIVE" , true );
					Adp.executeUpdateWithArgs(query, args);
				
				}
				else
				{

					query = "insert into store_session ( USER_ID ,  TYPE ,  CLASSBODY ,  ACTIVE ) "
						+ " VALUES ( ? ,  ? ,  ? , ? )" ;
					Map args = Adp.getArgs();
					args.put("USER_ID" , AuthorizationPageBeanId.getIntUserID()  );
					args.put("TYPE" , type );
					args.put("CLASSBODY" ,  obj);
					args.put("ACTIVE" , true );
					Adp.executeInsertWithArgs(query, args);
					
				} 
				
				
				//session.setAttribute(key,obj) ;
			}
			Adp.commit() ;		
		}
		catch (Exception e) 
		{
			Adp.rollback();
			log.error(e) ;
		}
		finally
		{
			if(Adp != null)	Adp.close();
		}
	}
	
final	long getIdsessionHash1(final String id_session )
	{
		String hash = id_session.substring(0, 10) ;
		return  hash.hashCode() ;
	}

final	long getIdsessionHash2(final String id_session )
	{
		String hash = id_session.substring(10, 20) ;
		return  hash.hashCode() ;
	}

final	long getIdsessionHash3(final String id_session )
	{
		String hash = id_session.substring(20, 30) ;
		return  hash.hashCode() ;
	}

final	long getIdsessionHash4(final String id_session )
	{
		String hash = id_session.substring(30) ;
		if(hash.indexOf(".") != -1 )hash = hash.substring(0 ,hash.indexOf(".")) ;
		return  hash.hashCode() ;
	}
	
final	public void saveClassesSessionScope_new(final HttpSession  session  ) 
	{
		AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
		if( AuthorizationPageBeanId == null ) return ;
		//if( AuthorizationPageBeanId.getStrLogin().equals(SiteRole.GUEST)) return ;
		//if( AuthorizationPageBeanId.getIntLevelUp() == 0 ) return ;
		QueryManager Adp = new QueryManager();
		String query = "" ;
		String key = "";
		Adp.BeginTransaction();
		Enumeration enumeration;
		String type = null ;
		Object obj = null ;
		String cokie_session_id = (String) session.getAttribute("cokie_session_id");
		session.removeAttribute(cokie_session_id);
		try {
			enumeration = session_scope.getKeys();
			while (enumeration.hasMoreElements()) 
			{
				key = (String) enumeration.nextElement();
				 type = session_scope.getString(key).trim();
				 obj = session.getAttribute(key) ;
				 if(obj == null ) continue ;
				 query = "select USER_ID  from store_session WHERE  idsession_hash1 = " + getIdsessionHash1(cokie_session_id) + "  and idsession_hash2 = " +  getIdsessionHash2(cokie_session_id) + " and idsession_hash3 = " +  getIdsessionHash3(cokie_session_id) + " and idsession_hash4 = " +  getIdsessionHash4(cokie_session_id) + " AND TYPE = '" +type+ "'" ;
				//query = "select USER_ID from store_session WHERE USER_ID = " + AuthorizationPageBeanId.getIntUserID() + " AND TYPE = '" +type+ "'" ;
				Adp.executeQuery(query);
				
				if (Adp.rows().size() > 0) 
				{
					query = "update store_session  set  USER_ID = ? , TYPE = ? , CLASSBODY = ? , " +
					" ACTIVE = ? WHERE  idsession_hash1 = " + getIdsessionHash1(cokie_session_id) + "  and idsession_hash2 = " +  getIdsessionHash2(cokie_session_id) + " and idsession_hash3 = " +  getIdsessionHash3(cokie_session_id) + " and idsession_hash4 = " +  getIdsessionHash4(cokie_session_id) + " AND TYPE = '" +type+ "'" ;
					Map args = Adp.getArgs();
					args.put("USER_ID" , AuthorizationPageBeanId.getIntUserID()  );
					args.put("TYPE" , type );
					args.put("CLASSBODY" ,  obj);
					args.put("ACTIVE" , true );
					Adp.executeUpdateWithArgs(query, args);
				
				}
				else
				{

					query = "insert into store_session ( USER_ID ,  TYPE ,  CLASSBODY ,  ACTIVE , idsession_hash1 , idsession_hash2 , idsession_hash3 , idsession_hash4 ) "
						+ " VALUES ( ? ,  ? ,  ? , ? , ? ,  ? ,  ? , ?)" ;
					Map args = Adp.getArgs();
					args.put("USER_ID" , AuthorizationPageBeanId.getIntUserID()  );
					args.put("TYPE" , type );
					args.put("CLASSBODY" ,  obj);
					args.put("ACTIVE" , true );
					args.put("idsession_hash1" , getIdsessionHash1(cokie_session_id) );
					args.put("idsession_hash2" , getIdsessionHash2(cokie_session_id) );
					args.put("idsession_hash3" , getIdsessionHash3(cokie_session_id) );
					args.put("idsession_hash4" , getIdsessionHash4(cokie_session_id) );
					Adp.executeInsertWithArgs(query, args);
					
				} 
				
				
				//session.setAttribute(key,obj) ;
			}
			Adp.commit() ;		
		}
		catch (Exception e) 
		{
			Adp.rollback();
			log.error(e) ;
		}
		finally
		{
			if(Adp != null)	Adp.close();
		}
	}
	

final	public void saveClassesSessionScope_new1(final HttpSession  session  ) 
{
	AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
	if( AuthorizationPageBeanId == null ) return ;
	//if( AuthorizationPageBeanId.getStrLogin().equals(SiteRole.GUEST)) return ;
	//if( AuthorizationPageBeanId.getIntLevelUp() == 0 ) return ;
	QueryManager Adp = new QueryManager();
	String query = "" ;
	String key = "";
	Adp.BeginTransaction();
	Enumeration enumeration;
	String type = null ;
	Object obj = null ;
	String cokie_session_id = (String) session.getAttribute("cokie_session_id");
	session.removeAttribute(cokie_session_id);
	try {
		enumeration = session_scope.getKeys();
		while (enumeration.hasMoreElements()) 
		{
			key = (String) enumeration.nextElement();
			 type = session_scope.getString(key).trim();
			 obj = session.getAttribute(key) ;
			 if(obj == null ) continue ;
			 query = "select USER_ID  from store_session WHERE  idsession_hash1 = " + getIdsessionHash1(cokie_session_id) + "  and idsession_hash2 = " +  getIdsessionHash2(cokie_session_id) + " and idsession_hash3 = " +  getIdsessionHash3(cokie_session_id) + " and idsession_hash4 = " +  getIdsessionHash4(cokie_session_id) + " AND TYPE = '" +type+ "'" ;
			//query = "select USER_ID from store_session WHERE USER_ID = " + AuthorizationPageBeanId.getIntUserID() + " AND TYPE = '" +type+ "'" ;
			Adp.executeQuery(query);
			
			if (Adp.rows().size() > 0) 
			{
				query = "update store_session  set  USER_ID = ? , TYPE = ? , BCLASSBODY = ? , " +
				" ACTIVE = ? WHERE  idsession_hash1 = " + getIdsessionHash1(cokie_session_id) + "  and idsession_hash2 = " +  getIdsessionHash2(cokie_session_id) + " and idsession_hash3 = " +  getIdsessionHash3(cokie_session_id) + " and idsession_hash4 = " +  getIdsessionHash4(cokie_session_id) + " AND TYPE = '" +type+ "'" ;
				Map args = Adp.getArgs();
				args.put("USER_ID" , AuthorizationPageBeanId.getIntUserID()  );
				args.put("TYPE" , type );
				args.put("BCLASSBODY" , objectToBytes(obj));
				args.put("ACTIVE" , true );
				Adp.executeUpdateWithArgs(query, args);
			
			}
			else
			{

				query = "insert into store_session ( USER_ID ,  TYPE ,  BCLASSBODY ,  ACTIVE , idsession_hash1 , idsession_hash2 , idsession_hash3 , idsession_hash4 ) "
					+ " VALUES ( ? ,  ? ,  ? , ? , ? ,  ? ,  ? , ?)" ;
				Map args = Adp.getArgs();
				args.put("USER_ID" , AuthorizationPageBeanId.getIntUserID()  );
				args.put("TYPE" , type );
				args.put("BCLASSBODY" , objectToBytes(obj));
				args.put("ACTIVE" , true );
				args.put("idsession_hash1" , getIdsessionHash1(cokie_session_id) );
				args.put("idsession_hash2" , getIdsessionHash2(cokie_session_id) );
				args.put("idsession_hash3" , getIdsessionHash3(cokie_session_id) );
				args.put("idsession_hash4" , getIdsessionHash4(cokie_session_id) );
				Adp.executeInsertWithArgs(query, args);
				
			} 
			
			
			//session.setAttribute(key,obj) ;
		}
		Adp.commit() ;		
	}
	catch (Exception e) 
	{
		Adp.rollback();
		log.error(e) ;
	}
	finally
	{
		if(Adp != null)	Adp.close();
	}
}


final	public byte[] objectToBytes(final Object obj ) {
	ByteArrayOutputStream baos = null ;
	ObjectOutputStream oos = null ;
	byte[] result = null ;
	//if(!session.isNew()) return session.isNew() ;
	try {
	
		baos = new ByteArrayOutputStream();
		oos = new  ObjectOutputStream(baos);
		oos.writeObject(obj) ;
		result = baos.toByteArray() ;
		
	} 
	catch (Exception ex) {
		log.error(ex);
	}
	finally 
	{
		try {
			if(baos != null ) baos.close();
			if(oos != null ) oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	return result;
}
	
final	public void saveClassesSessionScopeByLogin(final HttpSession  session  ) 
{
	
	
	AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
	if( AuthorizationPageBeanId == null ) return ;
	//if( AuthorizationPageBeanId.getStrLogin().equals(SiteRole.GUEST)) return ;
	//if( AuthorizationPageBeanId.getIntLevelUp() == 0 ) return ;
	QueryManager Adp = new QueryManager();
	//String user_id = AuthorizationPageBeanId.getIntUserID() ;
	String query = "" ;
	String key = "";
	Adp.BeginTransaction();
	Enumeration enumeration;
	String type = null ;
	Object obj = null ;
	String cokie_session_id = (String) session.getAttribute("cokie_session_id");
	session.removeAttribute(cokie_session_id);
	try {
		enumeration = session_scope.getKeys();
		while (enumeration.hasMoreElements()) 
		{
			key = (String) enumeration.nextElement();
			type = session_scope.getString(key).trim();
			obj = session.getAttribute(key) ;
			if(obj == null ) continue ;
			query = "select USER_ID  from store_session WHERE  USER_ID = " + AuthorizationPageBeanId.getIntUserID() + " AND TYPE = '" +type+ "'" ;
			Adp.executeQuery(query);
			
			if (Adp.rows().size() > 0) 
			{
				query = "update store_session  set  USER_ID = ? , TYPE = ? , CLASSBODY = ? , " +
				" ACTIVE = ? WHERE  USER_ID = " + AuthorizationPageBeanId.getIntUserID() + "  AND TYPE = '" +type+ "'" ;
				Map args = Adp.getArgs();
				args.put("USER_ID" , AuthorizationPageBeanId.getIntUserID()  );
				args.put("TYPE" , type );
				args.put("CLASSBODY" ,  obj);
				args.put("ACTIVE" , true );
				Adp.executeUpdateWithArgs(query, args);
			
			}
			else
			{

				query = "insert into store_session ( USER_ID ,  TYPE ,  CLASSBODY ,  ACTIVE  )  VALUES ( ? ,  ? ,  ? , ? )" ;
				Map args = Adp.getArgs();
				args.put("USER_ID" , AuthorizationPageBeanId.getIntUserID()  );
				args.put("TYPE" , type );
				args.put("CLASSBODY" ,  obj);
				args.put("ACTIVE" , true );
				Adp.executeInsertWithArgs(query, args);
				
			} 
			
			
			//session.setAttribute(key,obj) ;
		}
		Adp.commit() ;		
	}
	catch (Exception e) 
	{
		Adp.rollback();
		log.error(e) ;
	}
	finally
	{
		if(Adp != null)	Adp.close();
	}
}


	final public void AddAliases(final ServletContextEvent sce) {
		
		 
		//SELECT  LAST_DATE  FROM DOMAIN_PROC WHERE DOMAIN_PROC_ID = 0
		GetValueTool tool = new GetValueTool() ;
		Date curent_date = new Date() ;
		Date last_post = new Date() ;
		QueryManager Adp = null ;
		AddAliase addAliase;
		Document doc ;
		String query = "select host  from site group by host" ;
		String count_query = "select count(host) from site where host in ( select host from site group by host )" ;
		String host = "www.siteoneclick.com" ;
		String aliase = "" ;
		
		StringBuffer hosts_for_dns = new StringBuffer();
		File file =  new File("/") ;
		 String server_config = System.getProperty("conf");
		 if(server_config == null || server_config.length() == 0) 
		 {
			 String dir = System.getProperty("user.dir");
			 dir = dir.substring(0 ,dir.indexOf("bin")) + "conf" + File.separatorChar+"server.xml" ;
			//File f = new File("C:/apache-tomcat-6.0.14/conf/server.xml") ;
			file = new File(dir) ;
		 }
		 else 
		 {
			 file = new File(server_config) ; 
		 }

		
		
		try 
		{
			
			
			
			Adp = new QueryManager();
			if(Adp == null)return ; 
			Adp.executeQuery("SELECT  LAST_DATE  FROM DOMAIN_PROC WHERE DOMAIN_PROC_ID = 0");
			if(Adp.rows().size() > 0) 
			{
			String date = Adp.getValueAt(0, 0);
			last_post = Adp.getSimpleDateFormat().parse(date);
			}

			addAliase = new AddAliase();
			doc = addAliase.loadConfig(file);
			addAliase.RemoveAllAliase(doc, host) ;
			
			long size = 0 ;
			int limmit = 20 ;
			Adp.executeQuery(count_query);
			if (Adp.rows().size() > 0) size = Long.parseLong(Adp.getValueAt(0, 0));
			
			
			for (int offset = 0; size > offset; offset = offset + 20)
			{ 
				List list = Adp.executeQueryList(query, limmit, offset);
				
				for (int i = 0; list.size() > i; i++) 
				{
					//aliase =  Adp.getValueAt(i, 0);
					aliase =  tool.getValueAt(list, i, 0);
					int indexof = aliase.indexOf(".irr.bz");
					//if(indexof != -1 && i < 280 && isAnsi(aliase) )
					if(indexof != -1 && isAnsi(aliase) )
					{
					addAliase.AddAliaseToHost(doc, host, aliase) ;
					hosts_for_dns.append(aliase.substring(0,indexof));	
					hosts_for_dns.append(" A 0 78.37.191.193 86400\n");	
					}
				}
			}
			
			addAliase.writeXmlFile(doc,file);	 
			
			if(curent_date.getHours() != last_post.getHours() )
			{
			DomainRegister r = new DomainRegister();
			String auth = r.getAuth() ;
			r.setDomainAliases("irr.bz" , hosts_for_dns.toString() ,auth );
			System.out.println(r.getPostCommand(auth));
			Adp.getArgs().clear();
			Adp.getArgs().put("LAST_DATE", new Date());
			Adp.executeUpdateWithArgs("UPDATE DOMAIN_PROC SET LAST_DATE  = ? WHERE DOMAIN_PROC_ID = 0", Adp.getArgs());
			}
			

			//System.exit(0);
		}
		catch (SQLException e) 
		{
			log.error(e);
		}
		catch (Exception e) 
		{
			System.out.println(" server config: " + file.getPath());
			log.error(e);
		}
		finally
		{
			
			
			if(Adp != null )
			{
			Adp.close();
			}
			
			
		}

		
	}

	
	final public void PostToDNSServerAliases() {
		
		 
		//SELECT  LAST_DATE  FROM DOMAIN_PROC WHERE DOMAIN_PROC_ID = 0
		Date curent_date = new Date() ;
		Date last_post = new Date() ;
		QueryManager Adp = null ;
		//AddAliase addAliase;
		//Document doc ;
		String query = "select host  from site group by host" ;
		//String host = "www.siteoneclick.com" ;
		String aliase = "" ;
		StringBuffer hosts_for_dns = new StringBuffer();
		
		try 
		{
			Adp = new QueryManager();
			if(Adp == null)return ; 
			Adp.executeQuery("SELECT  LAST_DATE  FROM DOMAIN_PROC WHERE DOMAIN_PROC_ID = 0");
			if(Adp.rows().size() > 0) 
			{
			String date = Adp.getValueAt(0, 0);
			last_post = Adp.getSimpleDateFormat().parse(date);
			}

			
			
			Adp.executeQuery(query);
			for (int i = 0; Adp.rows().size() > i; i++) 
			{
				aliase =  Adp.getValueAt(i, 0);
				int indexof = aliase.indexOf(".irr.bz");
				//if(indexof != -1 && i < 280 && isAnsi(aliase) )
				if(indexof != -1 && isAnsi(aliase) )
				{
					
					hosts_for_dns.append(aliase.substring(0,indexof));	
					hosts_for_dns.append(" A 0 78.37.191.193 86400\n");	
				}
			}
			
			
			if(curent_date.getHours() != last_post.getHours() )
			{
			DomainRegister r = new DomainRegister();
			String auth = r.getAuth() ;
			r.setDomainAliases("irr.bz" , hosts_for_dns.toString() ,auth );
			System.out.println(r.getPostCommand(auth));
			Adp.getArgs().clear();
			Adp.getArgs().put("LAST_DATE", new Date());
			Adp.executeUpdateWithArgs("UPDATE DOMAIN_PROC SET LAST_DATE  = ? WHERE DOMAIN_PROC_ID = 0", Adp.getArgs());
			}
			

			//System.exit(0);
		}
		catch (SQLException e) 
		{
			log.error(e);
		}
		catch (Exception e) 
		{
			//System.out.println(" server config: " + file.getPath());
			log.error(e);
		}
		finally
		{
			
			
			if(Adp != null )
			{
			Adp.close();
			}
			
			
		}

		
	}
	
	final public void AddAliasesInFile() {
		
		 
		//SELECT  LAST_DATE  FROM DOMAIN_PROC WHERE DOMAIN_PROC_ID = 0
		
		QueryManager Adp = null ;
		AddAliase addAliase;
		Document doc ;
		String query = "select host  from site group by host" ;
		String host = "www.siteforyou.net" ;
		String aliase = "" ;
		
		//StringBuffer hosts_for_dns = new StringBuffer();
		File file =  new File("/") ;
		 String server_config = System.getProperty("conf");
		 if(server_config == null || server_config.length() == 0) 
		 {
			 String dir = System.getProperty("user.dir");
			 dir = dir.substring(0 ,dir.indexOf("bin")) + "conf" + File.separatorChar+"server.xml" ;
			//File f = new File("C:/apache-tomcat-6.0.14/conf/server.xml") ;
			file = new File(dir) ;
		 }
		 else 
		 {
			 file = new File(server_config) ; 
		 }

		try 
		{
			Adp = new QueryManager();
			if(Adp == null)return ; 
			
			addAliase = new AddAliase();
			doc = addAliase.loadConfig(file);
			addAliase.RemoveAllAliase(doc, host) ;
			
			Adp.executeQuery(query);
			for (int i = 0; Adp.rows().size() > i; i++) 
			{
				aliase =  Adp.getValueAt(i, 0);
				int indexof = aliase.indexOf(".irr.bz");
				//if(indexof != -1 && i < 280 && isAnsi(aliase) )
				if(indexof != -1 && isAnsi(aliase) )
				{
					addAliase.AddAliaseToHost(doc, host, aliase) ;
				}
			}
			addAliase.writeXmlFile(doc,file);	 
			
			//System.exit(0);
		}
		catch (SQLException e) 
		{
			log.error(e);
		}
		catch (Exception e) 
		{
			System.out.println(" server config: " + file.getPath());
			log.error(e);
		}
		finally
		{
			if(Adp != null )
			{
			Adp.close();
			}
		}
	}
	
final	public boolean isAnsi(String tmp) {
		if (tmp == null)
			return false;
		tmp = tmp.toLowerCase();
		String IntField = "0123456789qwertyuiopasdfghjklzxcvbnm_-.";
		for (int i = 0; i < tmp.length(); i++) {

			if (IntField.indexOf(tmp.charAt(i)) == -1) {
				if (tmp.charAt(i) != '-' && i != 0)
					return false;
			}
		}
		return true;
	}
	
final	public ResourceBundle getResources_cms_settings() {
		return setup_resources;
	}


//	public void setResources_cms_settings(ResourceBundle resources_cms_settings) {
//		this.setup_resources = resources_cms_settings;
//	}

final public Map getControllerMap() {
		return controllerMap;
	}

//	public void setControllerMap(HashMap controllerMap) {
//		this.controllerMap = controllerMap;
//	}

final	public Map getTransformerMap() {
		return transformerMap;
	}

//	public void setTransformerMap(HashMap transformerMap) {
//		this.transformerMap = transformerMap;
//	}

	final public ResourceBundle getActions_resources() {
		return actions_resources;
	}

//	public void setActions_resources(ResourceBundle actions_resources) {
//		this.actions_resources = actions_resources;
//	}

final	public ResourceBundle getXslt_resources() {
		return xslt_resources;
	}

//	public void setXslt_resources(ResourceBundle xslt_resources) {
//		this.xslt_resources = xslt_resources;
//	}

final	public ResourceBundle getSession_scope() {
		return session_scope;
	}

//	public void setSession_scope(ResourceBundle session_scope) {
//		this.session_scope = session_scope;
//	}

final	public ResourceBundle getSetup_resources() {
		return setup_resources;
	}

//	public void setSetup_resources(ResourceBundle setup_resources) {
//		this.setup_resources = setup_resources;
//	}

	final public CreateShopBean getCreateShopBean() {
		return createShopBean;
	}

//	public void setCreateShopBean(CreateShopBean createShopBean) {
//		this.createShopBean = createShopBean;
//	}
}
