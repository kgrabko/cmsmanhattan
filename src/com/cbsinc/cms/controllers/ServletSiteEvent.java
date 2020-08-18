package com.cbsinc.cms.controllers;

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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;

/*
 *   <listener>
 *   <listener-class>mobilesoft.ServletSiteEvent</listener-class>
 *   </listener>
 *
 */

public class ServletSiteEvent extends HttpServlet implements HttpSessionListener, ServletContextListener ,  ServletRequestListener{

	private static final long serialVersionUID = -2594377223913825222L;
	transient private Logger log = Logger.getLogger(ServletSiteEvent.class);
	transient ResourceBundle session_scope = null;
	ServletContext servletContext;

	transient ResourceBundle setup_resources = null;
	transient long delay = 60000;
	transient AuthorizationPageFaced authorizationPageFaced = null;

	public ServletSiteEvent() {
		super();
		if (session_scope == null)
			session_scope = PropertyResourceBundle.getBundle("session_scope");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		servletContext = config.getServletContext();
		super.init(config);
	}

//	void loadClassesSessionScope( HttpSession  session  ) 
//	{
//		//HttpServletRequest servletRequest = 
//			//session.getServletContext().getNamedDispatcher("/").  
//		
//		QueryManager Adp = new QueryManager();
//		String query = "" ;
//		String key = "";
//		AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
//		try {
//		query = "select USER_ID , TYPE , CLASSBODY  from STORE_SESSION WHERE where USER_ID = " + AuthorizationPageBeanId.getIntUserID() ;
//		ResultSet rs  = Adp.executeQueryResultSet(query);
//		//ResultSet rs = ps.executeQuery("SELECT ENGINEERS FROM PERSONNEL");
//		while (rs.next()) 
//		{
//			//Engineer eng = (Engineer)rs.getObject("CLASSBODY");
//			//System.out.println(eng.lastName + ", " + eng.firstName);
//			Enumeration enumeration = session_scope.getKeys();
//			while (enumeration.hasMoreElements()) 
//			{
//				key = (String) enumeration.nextElement();
//				String type = session_scope.getString(key).trim();
//				String typedb =  rs.getString("TYPE");
//				if(typedb  != null ) if(typedb.equals(type))
//				{
//					Object obj = rs.getObject("CLASSBODY") ;
//					session.setAttribute(key, obj);
//				}
//			}
//		}
//		
//	
//		}
//		catch (Exception e) 
//		{
//			log.error(e) ;
//		}
//		finally
//		{
//			if(Adp != null)	Adp.close();
//		}
//	}

	public void serializeObject(File fileName, Object obj) {

		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(obj);

		} catch (Exception ex) {
			log.error(ex);
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return;
	}

	String getCookiesDir(ServletContext servletContext) {
		return (String) servletContext.getAttribute("cookies_dir");
	}

	void saveClassesSessionScopeFromDir(HttpSession session, ServletContext servletContext) {

		String cookiesDir = getCookiesDir(servletContext);
		AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean) session
				.getAttribute("authorizationPageBeanId");
		if (AuthorizationPageBeanId == null)
			return;

		String key = "";

		Enumeration enumeration;
		String type = null;
		Object obj = null;
		String cokie_session_id = (String) session.getAttribute("cokie_session_id");
		session.removeAttribute(cokie_session_id);
		try {
			enumeration = session_scope.getKeys();
			HashMap map = new HashMap();
			while (enumeration.hasMoreElements()) {
				key = (String) enumeration.nextElement();
				obj = session.getAttribute(key);
				if (obj == null)
					continue;
				map.put(key, obj);
			}
			File file = new File(cookiesDir + File.separatorChar + cokie_session_id);
			serializeObject(file, map);
		} catch (Exception e) {

			log.error(e);
		} finally {

		}
	}

	public void sessionCreated(HttpSessionEvent se) {

		System.out.println("load session");

		try {
			authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced().get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc == null)
			return;
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
		HttpSession hsession = se.getSession();

		String session_id = authorizationPageFaced.getCokieSessionId((HttpServletRequest) request,
				(HttpServletResponse) response);
		hsession.setAttribute("cokie_session_id", session_id);
//		System.out.println("session: " + session_id );
		if (authorizationPageFaced.isCokieSessionIdExists((HttpServletRequest) request, (HttpServletResponse) response)
				&& authorizationPageFaced.isLoginFromCookie_new1(session_id, hsession,
						authorizationPageFaced.getSession_scope()))
//		//if( authorizationPageFaced.isLoginFromCookieFromDir( session_id ,  hsession , servletContext , session_scope ) )
		{
			// authorizationPageBeanId = ((AuthorizationPageBean)
			// hsession.getAttribute("authorizationPageBeanId"));
			System.out.println("A session was loaded");
		}

		/*
		 * Cookie[] cookies = request.getCookies(); for (int i = 0; i < cookies.length;
		 * i++) { Cookie c = cookies[i]; String name = c.getName(); String value =
		 * c.getValue(); out.println(name + " = " + value); }
		 */

		// se
		// Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		// Cookie cookie = new Cookie("","");
		// ((HttpServletResponse) response).addCookie(cookie);

	}

	public void sessionDestroyed(HttpSessionEvent se) {

		// System.gc();
//		FacesContext fc = FacesContext.getCurrentInstance();
//		HttpServletRequest request = (HttpServletRequest)fc.getExternalContext().getRequest();
//		String lastPage = request.getQueryString() ;
		// -- Удаляем всех кто не на сайте из контакт листа
		AuthorizationPageFaced authorizationPageFaced = null;
		Map hashMap = null;
		HttpSession httpSession = se.getSession();
		if (httpSession == null)
			return;
		// expiring

		//////////// saveSession( httpSession ) ;

		ServletContext servletContext = httpSession.getServletContext();
		if (!(se.getSession().getAttribute("authorizationPageBeanId") instanceof AuthorizationPageBean))
			return;
		AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean) se.getSession()
				.getAttribute("authorizationPageBeanId");

		// saveClassesSessionScopeFromDir(httpSession , servletContext) ;
		if (AuthorizationPageBeanId.getIntLevelUp() == SiteRole.ADMINISTRATOR_ROLE_ID
				|| AuthorizationPageBeanId.getIntLevelUp() == SiteRole.MEMBER_ROLE_ID) {
			if (servletContext == null)
				return;
			try {
				authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced().get();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// authorizationPageFaced.saveClassesSessionScopeByLogin(httpSession) ;
			authorizationPageFaced.saveClassesSessionScope_new1(httpSession);
		}

		if (!(servletContext.getAttribute("userlist") instanceof Map))
			return;
		hashMap = (Map) servletContext.getAttribute("userlist");
		hashMap.remove(AuthorizationPageBeanId.getStrLogin());

	}

	void saveSession(HttpSession httpSession) {

		ObjectOutputStream outputToServlet = null;
		FileOutputStream fileOutputStream = null;
		String FileName = httpSession.getId() + ".ser";
		String sessionStorePath = this.getClass().getResource("").getPath();
		sessionStorePath = sessionStorePath.substring(1, sessionStorePath.indexOf("/WEB-INF/"));
		File file = new File(sessionStorePath + "//sessions");
		try {

			if (!file.exists()) {
				if (file.mkdirs()) {
					file = new File(file.getPath(), FileName);
					file.createNewFile();
				}
			} else {
				file = new File(file.getPath(), FileName);
				if (!file.exists())
					file.createNewFile();
			}

			fileOutputStream = new FileOutputStream(file);

			Hashtable hashtableSessionVaribal = new Hashtable();

			Enumeration enumeration = httpSession.getAttributeNames();

			while (enumeration.hasMoreElements()) {
				String name = (String) enumeration.nextElement();
				Object obj = httpSession.getAttribute(name);
				if (obj instanceof Serializable) {
					hashtableSessionVaribal.put(name, obj);
				}
			}

			outputToServlet = new ObjectOutputStream(fileOutputStream);

			outputToServlet.writeObject(hashtableSessionVaribal);

			outputToServlet.flush();

		} catch (IOException e) {
			log.error(e);
		} finally {
			if (outputToServlet != null) {
				try {
					outputToServlet.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
	}

	void loadSession(HttpSession httpSession) {

		ObjectInputStream inputToServlet = null;
		Hashtable hashtableSessionVaribal = null;
		FileInputStream fileInputStream = null;
		File file = null;
		String search = "webapps";
		String FileName = httpSession.getId() + ".ser";
		String sessionStorePath = this.getClass().getResource("").getPath();
		sessionStorePath = sessionStorePath.substring(1, sessionStorePath.indexOf("/WEB-INF/"));
		file = new File(sessionStorePath + "//sessions", FileName);

		if (!file.exists())
			return;

		try {

			fileInputStream = new FileInputStream(file);
			inputToServlet = new ObjectInputStream(fileInputStream);

			Object obj = inputToServlet.readObject();

			if (obj instanceof Hashtable) {
				hashtableSessionVaribal = (Hashtable) obj;
				Enumeration enumeration = hashtableSessionVaribal.keys();
				while (enumeration.hasMoreElements()) {
					String name = (String) enumeration.nextElement();
					Object data = hashtableSessionVaribal.get(name);
					httpSession.setAttribute(name, data);
				}
			}

		} catch (Exception e) {
			log.error(e);
		} finally {
			if (inputToServlet != null) {
				try {
					inputToServlet.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {

//		ServletContext servletContext =  sce.getServletContext() ;
//		AuthorizationPageFaced authorizationPageFaced = null;
//		try {
//			authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		authorizationPageFaced.AddAliasesInFile(sce);

	}

	public void contextInitialized(ServletContextEvent sce) {

		/// Uncoment if you like hibernate
		///// new Configuration().configure().buildSessionFactory() ;

	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		ServletRequest servletRequest = sre.getServletRequest();
		//servletRequest.getServletContext().
		 System.out.println("ServletRequest requestDestroyed. Remote IP="+servletRequest.getRemoteAddr());
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		ServletRequest servletRequest = sre.getServletRequest();
		System.out.println("ServletRequest initialized. Remote IP="+servletRequest.getRemoteAddr());
	}

}
