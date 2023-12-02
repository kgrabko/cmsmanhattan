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
import org.perf4j.aop.Profiled;
import org.w3c.dom.Document;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.CreateShopBean;
import com.cbsinc.cms.GetValueTool;
import com.cbsinc.cms.QueryManager;
import com.cbsinc.cms.annotations.SingletonBean;
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


public class AuthorizationPageFaced extends com.cbsinc.cms.WebControls  {

	
	final private static  String CLASS_NAME = "com.cbsinc.cms.faceds.AuthorizationPageFaced" ;
	final static private Logger log = Logger.getLogger(AuthorizationPageFaced.class);
	
	final CreateShopBean  createShopBean = new CreateShopBean();
//	sequences_rs = PropertyResourceBundle.getBundle("sequence");
	final private ResourceBundle sequences_rs = PropertyResourceBundle.getBundle("sequence");
	final private ResourceBundle setup_resources = PropertyResourceBundle.getBundle("appconfig") ;
	final private ResourceBundle session_scope = PropertyResourceBundle.getBundle("session_scope") ;
	
	final ConcurrentHashMap controllerMap = new ConcurrentHashMap(1024);
	final ConcurrentHashMap transformerMap = new ConcurrentHashMap(1024);
	
	final ResourceBundle actionsResources = PropertyResourceBundle.getBundle("web_actions");
	final ResourceBundle xsltResources = PropertyResourceBundle.getBundle("web_xslt");
	final Document doc = null ;


	public AuthorizationPageFaced() 
	{
	}
	

	@Profiled(logger = CLASS_NAME  , tag = "getCokieSessionId" , message = "HttpServletRequest: {$0} , HttpServletResponse: { $1 }  , getCokieSessionId: {@ retrun }"  )
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
		
		//HttpSession httpSession =  request.getSession() ;
		HttpSession httpSession =  request.getSession(false);
		if(httpSession == null ) return "";
		response.addCookie(new Cookie("session_id",httpSession.getId()));
		return httpSession.getId() ;
	}

	
	@Profiled(logger = CLASS_NAME  , tag = "isCokieSessionIdExists" , message = "HttpServletRequest: {$0} , HttpServletResponse: { $1 }  , isCokieSessionIdExists: {@ retrun }"  )
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
	
	
	@Profiled(logger = CLASS_NAME , tag = "isLoginCorrect" , message = "login: {$0} , passwd: { $1 } , authorizationBean: { $2 } , , idsession: { $3 } , isLoginCorrect: {@ retrun }"  )
	final public boolean isLoginCorrect(final String login, final String passwd , final AuthorizationPageBean authorizationBean, String idsession) {
		QueryManager qm = null;
		String query ="";
		try {
			if (login == null || login.length() == 0)	return false;
			if (passwd == null || passwd.length() == 0)	return false;
			
			
			qm = new QueryManager();
			qm.beginTransaction();
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  FROM tuser  where  login = '"
					+ login + "'   and  passwd = '" + passwd + "' and  site_id = " + authorizationBean.getSite_id() ;
			qm.executeQuery(query);
			if (qm.rows().size() == 1) {
				authorizationBean.setIntUserID(  Long.parseLong((String) qm.getValueAt(0, 0)));
				authorizationBean.setStrLogin( (String) qm.getValueAt(0, 1));
				authorizationBean.setStrPasswd ((String) qm.getValueAt(0, 2));
				authorizationBean.setStrFirstName( (String) qm.getValueAt(0, 3));
				authorizationBean.setStrLastName((String) qm.getValueAt(0, 4));
				authorizationBean.setStrEMail((String) qm.getValueAt(0, 5));
				authorizationBean.setStrPhone((String) qm.getValueAt(0, 6));
				authorizationBean.setStrMPhone((String) qm.getValueAt(0, 7));
				authorizationBean.setStrFax((String) qm.getValueAt(0, 8));
				authorizationBean.setStrIcq((String) qm.getValueAt(0, 9));
				authorizationBean.setStrWebsite((String) qm.getValueAt(0, 10));
				authorizationBean.setStrQuestion((String) qm.getValueAt(0, 11));
				authorizationBean.setStrAnswer((String) qm.getValueAt(0, 12));
				authorizationBean.setIntLevelUp( Integer.parseInt((String) qm.getValueAt(0, 13))) ;
				authorizationBean.setStrCompany((String) qm.getValueAt(0, 15));
				authorizationBean.setCountry_id ((String) qm.getValueAt(0, 16));
				authorizationBean.setCity_id((String) qm.getValueAt(0, 17));
				authorizationBean.setCurrency_id( (String) qm.getValueAt(0, 18));
				authorizationBean.setIntLogined(1);
				query = "UPDATE tuser set idsession = '"+  idsession +"'  where  user_id =" + authorizationBean.getIntUserID() ;
				
				qm.executeUpdate(query);
				qm.commit();
				return true;
			}
			authorizationBean.setIntLogined(2);
			return false;
		} 
		catch (SQLException ex) {
			qm.rollback();
			log.error(query,ex);
			return false;
		}
		catch (Exception ex) {
			qm.rollback();
			log.error(ex);
			return false;
		}
		finally 
		{
			if(qm != null)	qm.close();
		}
	}
	
	
	@Profiled(logger = CLASS_NAME , tag = "getSiteIdByHost" , message = "host: {$0}  , getSiteIdByHost: {@ retrun }"  )
	final public String getSiteIdByHost(final String host)
	{
		
		String siteId = "2" ;
		QueryManager Adp =  null ;
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
	
	
	@Profiled(logger = CLASS_NAME , tag = "loadClassesSessionScopeFromBase" , message = "httpSession: {$0}  , userId: {@1}"  )
	final void loadClassesSessionScopeFromBase(final HttpSession  httpSession , final Integer userId   ) 
	{
		QueryManager qm = new QueryManager();
		String query = "" ;
		String key = "";
		try {
		query = "select USER_ID , TYPE , CLASSBODY  from store_session WHERE  USER_ID = " + userId ;
		ResultSet rs  = qm.executeQueryResultSet(query);
		while (rs.next()) 
		{
			Enumeration enumeration = session_scope.getKeys();
			while (enumeration.hasMoreElements()) 
			{
				key = (String) enumeration.nextElement();
				String type = session_scope.getString(key).trim();
				String typedb =  rs.getString("TYPE");
				if(typedb  != null ) if(typedb.equals(type))
				{
					Object obj = rs.getObject("CLASSBODY") ;
					httpSession.setAttribute(key, obj);
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
			if(qm != null)	qm.close();
		}
	}
	
	
	@Profiled(logger = CLASS_NAME , tag = "clearCookieFromBD" , message = "authorizationBean: {$0}  , cokieSessionId: {@1}"  )
	final public void clearCookieFromBD( final AuthorizationPageBean authorizationBean, final String cokieSessionId) {
		QueryManager Adp = null;
		if(cokieSessionId == null) return ;
		String query ="";
		try 
		{
			Adp = new QueryManager();
			Adp.beginTransaction();
			query = "DELETE  FROM  store_session WHERE  idsession_hash1 = " + getIdsessionHash1(cokieSessionId) + "  and idsession_hash2 = " +  getIdsessionHash2(cokieSessionId) + " and idsession_hash3 = " +  getIdsessionHash3(cokieSessionId) + " and idsession_hash4 = " +  getIdsessionHash4(cokieSessionId)  ;
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
	
	
	@Profiled(logger = CLASS_NAME , tag = "isLoginCorrect" , message = "sessionId: {$0}  , authorizationBean: {@1}"  )
	final public boolean isLoginCorrect(final String sessionId , final AuthorizationPageBean authorizationBean) {
		QueryManager qm = null;
		String query ="";
		try {
			if (sessionId == null || sessionId.length() == 0)	return false;
			
			
			qm = new QueryManager();
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  FROM tuser  where  " +
					" idsession = '" + sessionId + "'" ;
			
			qm.executeQuery(query);
			if (qm.rows().size() == 1) {
				authorizationBean.setIntUserID(  Integer.parseInt((String) qm.getValueAt(0, 0)));
				authorizationBean.setStrLogin( (String) qm.getValueAt(0, 1));
				authorizationBean.setStrPasswd ((String) qm.getValueAt(0, 2));
				authorizationBean.setStrFirstName( (String) qm.getValueAt(0, 3));
				authorizationBean.setStrLastName((String) qm.getValueAt(0, 4));
				authorizationBean.setStrEMail((String) qm.getValueAt(0, 5));
				authorizationBean.setStrPhone((String) qm.getValueAt(0, 6));
				authorizationBean.setStrMPhone((String) qm.getValueAt(0, 7));
				authorizationBean.setStrFax((String) qm.getValueAt(0, 8));
				authorizationBean.setStrIcq((String) qm.getValueAt(0, 9));
				authorizationBean.setStrWebsite((String) qm.getValueAt(0, 10));
				authorizationBean.setStrQuestion((String) qm.getValueAt(0, 11));
				authorizationBean.setStrAnswer((String) qm.getValueAt(0, 12));
				authorizationBean.setIntLevelUp( Integer.parseInt((String) qm.getValueAt(0, 13))) ;
				authorizationBean.setStrCompany((String) qm.getValueAt(0, 15));
				authorizationBean.setCountry_id ((String) qm.getValueAt(0, 16));
				authorizationBean.setCity_id((String) qm.getValueAt(0, 17));
				authorizationBean.setCurrency_id( (String) qm.getValueAt(0, 18));
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
			if(qm != null)	qm.close();
		}
	}
	
	
	@Profiled(logger = CLASS_NAME , tag = "isLoginFromCookie" , message = "sessionId: {$0}  , httpSession: {@1} , servletContext: {@2} , sessionScope: {@3} , isLoginFromCookie: {@retrun}"  )
	final public boolean isLoginFromCookie(final String sessionId ,final HttpSession  httpSession ,final  ServletContext  servletContext  , ResourceBundle sessionScope  ) {
		boolean result = false ;
		QueryManager qm = new QueryManager();
		String query ="";
		if(!httpSession.isNew()) return httpSession.isNew() ;
		try {
			if (sessionId == null || sessionId.length() == 0)	return false;
		
			//StandardSession ss = (StandardSession)session ;
			
			String key = "";
			query = "select USER_ID , TYPE , CLASSBODY  from store_session WHERE  USER_ID IN (SELECT user_id  FROM tuser where idsession = '" + sessionId + "' ) " ;
			ResultSet rs  = qm.executeQueryResultSet(query);
			while (rs.next()) 
			{
				result = true ;
				Enumeration enumeration = sessionScope.getKeys();
				while (enumeration.hasMoreElements()) 
				{
					key = (String) enumeration.nextElement();
					String type = sessionScope.getString(key).trim();
					String typedb =  rs.getString("TYPE");
					if(typedb  != null ) if(typedb.equals(type))
					{
						Object obj = rs.getObject("CLASSBODY") ;
						Object newobj =  createObject(type);
						BeanUtils.copyProperties(newobj, obj);
						httpSession.setAttribute(key, newobj);
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
			if(qm != null)	qm.close();
		}
		
		return result;
	}
	
	
	@Profiled(logger = CLASS_NAME , tag = "getCookiesDir" , message = "servletContext: {$0}  , getCookiesDir: {@retrun}  "  )
	final String  getCookiesDir(final ServletContext servletContext) 
	{
		return (String)servletContext.getAttribute("cookies_dir");
	}
	
	@Profiled(logger = CLASS_NAME , tag = "isLoginFromCookieFromDir" , message = "servletContext: {$0}  , getCookiesDir: {@retrun}  "  )
	final public boolean isLoginFromCookieFromDir(final String sessionId , final HttpSession  httpSession , final ServletContext  servletContext  , final ResourceBundle sessionScope  ) {
		boolean result = false ;
		
		
		String path_dir =  getCookiesDir(servletContext);
		if(!httpSession.isNew()) return httpSession.isNew() ;
		try {
			if (sessionId == null || sessionId.length() == 0)	return false;
			String file_cookies = path_dir  +  File.separatorChar + sessionId ;
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
					type = sessionScope.getString(key).trim();
					Object newobj =  createObject(type);
					if(newobj == null || obj == null) continue ; 
					BeanUtils.copyProperties(newobj, obj);
					httpSession.setAttribute(key, obj);
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

	
	@Profiled(logger = CLASS_NAME , tag = "isLoginFromCookieNew" , message = "servletContext: {$0}  , getCookiesDir: {@retrun}  "  )	
	final public boolean isLoginFromCookieNew(final String sessionId , final HttpSession  httpSession , final ResourceBundle sessionScope  ) {
		boolean result = false ;
		QueryManager qm = new QueryManager();
		String query ="";
		try {
			if (sessionId == null || sessionId.length() == 0)	return false;
			
			String key = "";
			query = "select USER_ID , TYPE , CLASSBODY  from store_session WHERE  idsession_hash1 = " + getIdsessionHash1(sessionId) + "  and idsession_hash2 = " +  getIdsessionHash2(sessionId) + " and idsession_hash3 = " +  getIdsessionHash3(sessionId) + " and idsession_hash4 = " +  getIdsessionHash4(sessionId)  ;
			ResultSet rs  = qm.executeQueryResultSet(query);
			while (rs.next()) 
			{
				result = true ;
				Enumeration enumeration = sessionScope.getKeys();
				while (enumeration.hasMoreElements()) 
				{
					key = (String) enumeration.nextElement();
					String type = sessionScope.getString(key).trim();
					String typedb =  rs.getString("TYPE");
					if(typedb  != null ) if(typedb.equals(type))
					{
						Object obj = rs.getObject("CLASSBODY") ;
						Object newobj =  createObject(type);
						BeanUtils.copyProperties(newobj, obj);
						httpSession.setAttribute(key, newobj);
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
			if(qm != null)	qm.close();
		}
		
		return result;
	}
	

	@Profiled(logger = CLASS_NAME , tag = "isLoginFromCookieNew1" , message = "sessionId: {$0}  , httpSession: {@1} , sessionScope: {@2} , isLoginFromCookieNew1: {@retrun} "  )
	final public boolean isLoginFromCookieNew1(final String sessionId , final HttpSession  httpSession , final ResourceBundle sessionScope  ) {
	boolean result = false ;
	QueryManager qm = new QueryManager();
	String query ="";
	ByteArrayInputStream bais = null ;
	ObjectInputStream ois = null ;
	
	try {
		if (sessionId == null || sessionId.length() == 0)	return false;
	
		String key = "";
		query = "select USER_ID , TYPE , BCLASSBODY  from store_session WHERE  idsession_hash1 = " + getIdsessionHash1(sessionId) + "  and idsession_hash2 = " +  getIdsessionHash2(sessionId) + " and idsession_hash3 = " +  getIdsessionHash3(sessionId) + " and idsession_hash4 = " +  getIdsessionHash4(sessionId)  ;
		ResultSet rs  = qm.executeQueryResultSet(query);
		while (rs.next()) 
		{
			result = true ;
			Enumeration enumeration = sessionScope.getKeys();
			while (enumeration.hasMoreElements()) 
			{
				key = (String) enumeration.nextElement();
				String type = sessionScope.getString(key).trim();
				String typedb =  rs.getString("TYPE");
				if(typedb  != null ) if(typedb.equals(type))
				{
					 bais = new ByteArrayInputStream(rs.getBytes("BCLASSBODY"));
					 ois = new  ObjectInputStream(bais);
					 Object obj = ois.readObject() ;
					httpSession.setAttribute(key, obj);
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
		if(qm != null)	qm.close();
	}
	
	return result;
}

	@Profiled(logger = CLASS_NAME , tag = "loadOldSessionbyLogin" , message = "userId: {$0}  , httpSession: {@1} , sessionScope: {@2} , loadOldSessionbyLogin: {@retrun} "  )
	final public boolean loadOldSessionbyLogin(final String userId , final HttpSession  httpSession , final ResourceBundle sessionScope  ) {
	boolean result = false ;
	QueryManager qm = new QueryManager();
	String query ="";
	try {
		if (userId == null || userId.length() == 0)	return false;
		String key = "";
		query = "select USER_ID , TYPE , CLASSBODY  from store_session WHERE  USER_ID = " + userId   ;
		ResultSet rs  = qm.executeQueryResultSet(query);
		while (rs.next()) 
		{
			result = true ;
			Enumeration enumeration = sessionScope.getKeys();
			while (enumeration.hasMoreElements()) 
			{
				key = (String) enumeration.nextElement();
				String type = sessionScope.getString(key).trim();
				String typedb =  rs.getString("TYPE");
				if(typedb  != null ) if(typedb.equals(type))
				{
					Object obj = rs.getObject("CLASSBODY") ;
					Object newobj =  createObject(type);
					BeanUtils.copyProperties(newobj, obj);
					httpSession.setAttribute(key, newobj);
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
		if(qm != null)	qm.close();
	}
	
	return result;
}
	@Profiled(logger = CLASS_NAME , tag = "createObject" , message = "userId: {$0}  , createObject: {@retrun} "  )
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
	
	@Profiled(logger = CLASS_NAME , tag = "deserializeObject" , message = "fileName: {$0}  , deserializeObject: {@retrun} "  )
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
	
	 
	@Profiled(logger = CLASS_NAME , tag = "getAuthorizationBean" , message = "userId: {$0}  , getAuthorizationBean: {@retrun} "  )
	final public AuthorizationPageBean getAuthorizationBean(final String userId ) {
		QueryManager qm = null;
		AuthorizationPageBean authorization = new AuthorizationPageBean();
		String query ="";
		try {
			if (userId == null || userId.length() == 0)	return null;
			qm = new QueryManager();
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  " +
					"FROM tuser  where  user_id = "	+ userId + "" ;
			qm.executeQuery(query);
			if (qm.rows().size() == 1) {
				authorization.setIntUserID(  Integer.parseInt((String) qm.getValueAt(0, 0)));
				authorization.setStrLogin( (String) qm.getValueAt(0, 1));
				authorization.setStrPasswd ((String) qm.getValueAt(0, 2));
				authorization.setStrFirstName( (String) qm.getValueAt(0, 3));
				authorization.setStrLastName((String) qm.getValueAt(0, 4));
				authorization.setStrEMail((String) qm.getValueAt(0, 5));
				authorization.setStrPhone((String) qm.getValueAt(0, 6));
				authorization.setStrMPhone((String) qm.getValueAt(0, 7));
				authorization.setStrFax((String) qm.getValueAt(0, 8));
				authorization.setStrIcq((String) qm.getValueAt(0, 9));
				authorization.setStrWebsite((String) qm.getValueAt(0, 10));
				authorization.setIntLevelUp( Integer.parseInt((String) qm.getValueAt(0, 13))) ;
				authorization.setStrCompany((String) qm.getValueAt(0, 15));
				authorization.setCountry_id ((String) qm.getValueAt(0, 16));
				authorization.setCity_id((String) qm.getValueAt(0, 17));
				authorization.setCurrency_id( (String) qm.getValueAt(0, 18));
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
			if(qm != null)	qm.close();
		}
	}

	
	@Profiled(logger = CLASS_NAME , tag = "getAuthorizationBeanOfRoleAdmin" , message = "siteId: {$0}  , getAuthorizationBeanOfRoleAdmin: {@retrun} "  )
	final public AuthorizationPageBean getAuthorizationBeanOfRoleAdmin(final String siteId ) {
		QueryManager qm = null;
		AuthorizationPageBean authorization = new AuthorizationPageBean();
		String query ="";
		try {
			if (siteId == null || siteId.length() == 0)	return null;
			qm = new QueryManager();
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  " +
					"FROM tuser  where  levelup_cd = 2 and site_id = "	+ siteId + "" ;
			qm.executeQuery(query);
			if (qm.rows().size() == 1) {
				authorization.setIntUserID(  Long.parseLong((String) qm.getValueAt(0, 0)));
				authorization.setStrLogin( (String) qm.getValueAt(0, 1));
				authorization.setStrPasswd ((String) qm.getValueAt(0, 2));
				authorization.setStrFirstName( (String) qm.getValueAt(0, 3));
				authorization.setStrLastName((String) qm.getValueAt(0, 4));
				authorization.setStrEMail((String) qm.getValueAt(0, 5));
				authorization.setStrPhone((String) qm.getValueAt(0, 6));
				authorization.setStrMPhone((String) qm.getValueAt(0, 7));
				authorization.setStrFax((String) qm.getValueAt(0, 8));
				authorization.setStrIcq((String) qm.getValueAt(0, 9));
				authorization.setStrWebsite((String) qm.getValueAt(0, 10));
				authorization.setIntLevelUp( Integer.parseInt((String) qm.getValueAt(0, 13))) ;
				authorization.setStrCompany((String) qm.getValueAt(0, 15));
				authorization.setCountry_id ((String) qm.getValueAt(0, 16));
				authorization.setCity_id((String) qm.getValueAt(0, 17));
				authorization.setCurrency_id( (String) qm.getValueAt(0, 18));
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
			if(qm != null)	qm.close();
		}
	}

	
	@Profiled(logger = CLASS_NAME , tag = "getFromMainSiteUserAuthorizationBean" , message = "login: {$0}  , getFromMainSiteUserAuthorizationBean: {@retrun} "  )	
	final public AuthorizationPageBean getFromMainSiteUserAuthorizationBean(final String login ) {
		QueryManager qm = null;
		AuthorizationPageBean authorization = new AuthorizationPageBean();
		String query ="";
		try {
			if (login == null || login.length() == 0)	return null;
			
			
			qm = new QueryManager();
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  " +
					"FROM tuser  where  user_id = '" + login + "' and  site_id = " + SiteType.MAIN_SITE  ;
			qm.executeQuery(query);
			if (qm.rows().size() == 1) {
				authorization.setIntUserID(  Long.parseLong((String) qm.getValueAt(0, 0)));
				authorization.setStrLogin( (String) qm.getValueAt(0, 1));
				authorization.setStrPasswd ((String) qm.getValueAt(0, 2));
				authorization.setStrFirstName( (String) qm.getValueAt(0, 3));
				authorization.setStrLastName((String) qm.getValueAt(0, 4));
				authorization.setStrEMail((String) qm.getValueAt(0, 5));
				authorization.setStrPhone((String) qm.getValueAt(0, 6));
				authorization.setStrMPhone((String) qm.getValueAt(0, 7));
				authorization.setStrFax((String) qm.getValueAt(0, 8));
				authorization.setStrIcq((String) qm.getValueAt(0, 9));
				authorization.setStrWebsite((String) qm.getValueAt(0, 10));
				authorization.setIntLevelUp( Integer.parseInt((String) qm.getValueAt(0, 13))) ;
				authorization.setStrCompany((String) qm.getValueAt(0, 15));
				authorization.setCountry_id ((String) qm.getValueAt(0, 16));
				authorization.setCity_id((String) qm.getValueAt(0, 17));
				authorization.setCurrency_id( (String) qm.getValueAt(0, 18));
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
			if(qm != null)	qm.close();
		}
	}
	
	@Profiled(logger = CLASS_NAME , tag = "getFromMainSiteUserId" , message = "login: {$0}  , getFromMainSiteUserId: {@retrun} "  )	
	final public int getFromMainSiteUserId(final String login ) {
		QueryManager qm = null;
		String query ="";
		try {
			if (login == null || login.length() == 0)	return 0;
			qm = new QueryManager();
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,levelup_cd ,bank_cd , company , country_id , city_id ,  currency_id  " +
					"FROM tuser  where  user_id = '" + login + "' and  site_id = " + SiteType.MAIN_SITE  ;
			qm.executeQuery(query);
			if (qm.rows().size() == 1) {
				//authorization.setIntUserID(  Integer.parseInt((String) Adp.getValueAt(0, 0)));
				return Integer.parseInt((String) qm.getValueAt(0, 0));
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
			if(qm != null)	qm.close();
		}
	}
	
	
	
	
	@Profiled(logger = CLASS_NAME , tag = "isRegCorrect" , message = "login: {$0} , passwd: {$1} , cPasswd: {$2}  , firstName: {$3} , lastName: {$4} , companyName: {$5}, email: {$6} ,  phone: {$7}, mobilePhone: {$8} , faxNumber: {$9} , messageId: {$10} , websiteHost: {$11} , secureQuestion: {$12} ,  secureAnswer , {$13} birthday: {$14} , countryId: {$15} , cityId: {$16} , currencyId: {$17} , cookieSessionId: {$18} ,  authorizationBean {$19}  , isRegCorrect: {@retrun} "  )	
	final public int isRegCorrect(final String login, final String passwd,
			final String cPasswd,final String firstName,final String lastName,
			final String companyName,final String email,final String phone,
			final String mobilePhone,final String faxNumber, final String messageId,
			final String websiteHost,final String secureQuestion,final String secureAnswer,
			final String birthday,final String countryId,final String cityId,
			final String currencyId ,final String cookieSessionId  , final AuthorizationPageBean authorizationBean) {

		QueryManager qm = null;
		String query = "";
		try {
			if (login == null || login.length() == 0)
				return 1;
			if (passwd == null || passwd.length() == 0)
				return 2;
			if (passwd.length() < 6)	return 12;
			
			if (cPasswd == null || passwd.length() == 0)
				return 10;
			if (firstName == null || firstName.length() == 0)
				return 3;
			if (lastName == null || lastName.length() == 0)
				return 4;
			if (email == null || email.length() == 0)
				return 5;
			if (cityId == null || cityId.length() == 0  )
				return 11;
			if (email.indexOf("@") == -1)
				return 8;
			if (passwd.compareTo(cPasswd) != 0)
				return 9;
			if (!isEnglish(login))	return 13;
			
			qm = new QueryManager();
			qm.beginTransaction();
			query = "SELECT user_id  FROM tuser where  (login = '" + login	+ "' )  and  (passwd = '" + passwd + "' )";
			qm.executeQuery(query);
			if (qm.rows().size() != 0) {
				authorizationBean.setIntUserID( Long.parseLong((String) qm.getValueAt(0, 0))) ;
				query = "update tuser set " + " login = '" + login
						+ "' , " + "  passwd = '" + passwd + "' , "
						+ "  first_name = '" + firstName + "' , "
						+ "  last_name = '" + lastName + "' , "
						+ "  e_mail = '" + email + "' , " + "  phone = '"
						+ phone + "' , " + "  mobil_phone = '"
						+ mobilePhone + "' , " + "  fax = '" + faxNumber
						+ "' , " + "  icq = '" + messageId + "' , "
						+ "  website = '" + websiteHost + "' , "
						+ "  question = '" + secureQuestion + "' , "
						+ "  answer = '" + secureAnswer + "' , "
						+ "  company  = '" + companyName + "' , "
						+ "  country_id  = " + countryId + " , "
						+ "  city_id  = " + cityId + " , "
						+ "  currency_id = " + currencyId + " "
						+ "  where user_id = " + authorizationBean.getIntUserID();
				qm.executeUpdate(query);
				authorizationBean.setIntLogined(1);
				qm.commit();
				return 0;
			}

			/*
			 * else { intLogined = 2 ; intUserID = 0 ; strLogin = "" ;
			 * Adp.commit(); Adp.close(); return -1 ; }
			 */

			query = "SELECT user_id  FROM tuser  where login  = '" + login	+ "'";
			qm.executeQuery(query);
			if (qm.rows().size() != 0) {
				authorizationBean.setIntUserID(Long.parseLong((String) qm.getValueAt(0, 0)));
				authorizationBean.setIntLogined(3);
				authorizationBean.setIntUserID(0);
				authorizationBean.setStrLogin("");
				qm.commit();
				return -1;
			}


			//query = "SELECT NEXT VALUE FOR tuser_user_id_seq  AS ID  FROM ONE_SEQUENCES";
			query = sequences_rs.getString("tuser");
			qm.executeQuery(query);
			authorizationBean.setIntUserID(Long.parseLong((String) qm.getValueAt(0, 0)));

			query = "insert into tuser ( user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone,fax,icq,website,question,answer,acvive_session ,active ,regdate ,levelup_cd ,bank_cd , company , country_id , city_id , currency_id , site_id , idsession ) "
				+ " values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " ;

			Map args = qm.getArgs();
			args.put("user_id", authorizationBean.getIntUserID()) ;
			args.put("login", login) ;
			args.put("passwd", passwd) ;
			args.put("first_name", firstName) ;
			args.put("last_name", lastName) ;
			args.put("e_mail", email) ;
			args.put("phone", phone ) ;
			args.put("mobil_phone", mobilePhone ) ;
			args.put("fax", faxNumber ) ;
			args.put("icq", messageId ) ;
			args.put("website", websiteHost ) ;
			args.put("question", secureQuestion ) ;
			args.put("answer", secureAnswer ) ;
			args.put("acvive_session" ,  true )  ;
			args.put("active" ,  true  ) ;
			args.put("regdate" ,   new java.util.Date()) ;
			args.put("levelup_cd" ,  1  );
			args.put("bank_cd" ,  0 );
			args.put("company" ,  companyName ) ;
			args.put("country_id" ,  Long.parseLong(countryId) ) ;
			args.put("city_id" ,  Long.parseLong(cityId) ) ;
			args.put("currency_id" ,  Long.parseLong(currencyId) ) ;
			args.put("site_id",  Long.parseLong(authorizationBean.getSite_id())) ; 
			args.put("idsession",  cookieSessionId) ;
			qm.executeInsertWithArgs(query, args);
			
			
			//+ "  idsession = '" + cookie_session_id + "' , "
			
			//query = "SELECT NEXT VALUE FOR account_id_seq  AS ID  FROM ONE_SEQUENCES";
			query = sequences_rs.getString("account");
			qm.executeQuery(query);
			String account_id = (String) qm.getValueAt(0, 0);

			query = "insert into account ( account_id , user_id , amount , curr , date_input ,  description ,  currency_id ) "
				+ " values (  ? , ? , ? , ? , ? ,  ? ,  ?  ) " ;
				
			args = qm.getArgs();
			args.put("account_id" ,   Long.parseLong(account_id)  );
			args.put("user_id" ,  authorizationBean.getIntUserID()  );
			args.put("amount" ,  0  );
			args.put("curr" ,  3  );
			args.put("date_input" ,  new java.util.Date() );
			args.put("description" ,  " new_account "  );
			args.put("currency_id" ,  Long.parseLong(currencyId)  );
			
			qm.executeInsertWithArgs(query, args);
			
			authorizationBean.setIntLogined(1);
			authorizationBean.setIntLevelUp(1);
			qm.commit();
			return 0;

		 } 
		 catch (SQLException ex) 
		 {
				log.error(query,ex);
				qm.rollback();
				return -2;
		}
		catch (Exception ex) {
			log.error(ex);
			qm.rollback();
			return -2;
		}

		finally 
		{
			if(qm != null)	qm.close();
		}

	}

	

	


	@Profiled(logger = CLASS_NAME , tag = "getDateControl" , message = "name1: {$0}  , name2: {$1}  , name3: {$2} , query: {$3}, at: {$4}, to: {$5}, getDateControl: {$retrun} "  )	
	final public String getDateControl(final String name1, final String name2, final String name3,
			String query, int at, int to) {
		return super.getDateControl(name1, name2, name3, query, at, to);
	}

	


	
	@Profiled(logger = CLASS_NAME , tag = "initSiteDir" , message = "site_id: {$0}  , authorizationBean: {$1} "  )	
	final public void initSiteDir(final String site_id , final AuthorizationPageBean authorizationBean) {
		String query = "select site_dir, subject_site , nick_site , company_name , host from site where site_id = "
				+ site_id;
		QueryManager qm = new QueryManager();
		// Adp.BeginTransaction();
		try {
			qm.executeQuery(query);
			if (qm.rows().size() != 0) {
				authorizationBean.setSite_dir((String) qm.getValueAt(0, 0)); // + " " +
				authorizationBean.setSubject_site((String) qm.getValueAt(0, 1));
				authorizationBean.setNick_site((String) qm.getValueAt(0, 2));
				authorizationBean.setCompany_name((String) qm.getValueAt(0, 3));
				authorizationBean.setHost((String) qm.getValueAt(0, 4));
			}
		}
		catch (SQLException ex) {
			log.error(query,ex);
		}
		catch (Exception ex) {
			log.error(ex);
		}

		finally {
			qm.close();
		}

	}


	
	

	
	@Profiled(logger = CLASS_NAME , tag = "initSiteDir" , message = "site_id: {$0}  , authorizationBean: {$1} "  )		
	final public void initUserSite(final long userId , final AuthorizationPageBean authorizationBean ) 
	{
		String query = "select site_id  from site where site.owner = " + userId + " order by site.site_id desc ";
		
		QueryManager qm = new QueryManager();

		try 
		{
			qm.executeQuery(query);
			if (qm.rows().size() != 0) {
				authorizationBean.setUser_site((String) qm.getValueAt(0, 0)); // + " " +
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
			qm.close();
		}

	}
	
	
	@Profiled(logger = CLASS_NAME , tag = "getLengId" , message = "locale: {$0}  , getLengId: {$retrun} "  )		
	final public int getLengId(final String locale) 
	{
		String leng_id = "-1" ;
		String query = "select lang_id  from lang where lable = '" + locale + "'" ;
		QueryManager qm = new QueryManager();

		try 
		{
			qm.executeQuery(query);
			if (qm.rows().size() != 0) {
				leng_id = (String) qm.getValueAt(0, 0); // + " " +
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
			qm.close();
		}
		
		return  Integer.parseInt(leng_id);
	}
	
	
	@Profiled(logger = CLASS_NAME , tag = "initPaySysShopCd" , message = "siteId: {$0}  , authorizationBean: {$1}  , initPaySysShopCd: {$retrun} "  )
	final public void initPaySysShopCd(final String siteId , final AuthorizationPageBean authorizationBean ) {
		String query = "select  shop_cd from shop where site_id = " + siteId;
		QueryManager qm = new QueryManager();
		qm.beginTransaction();
		try 
		{
			qm.executeQuery(query);

			if (qm.rows().size() != 0) {
				authorizationBean.setPaysys_shop_cd((String) qm.getValueAt(0, 0));
				if (authorizationBean.getPaysys_shop_cd() == null)
					log.assertLog(false,
							"ERROR: select shop_cd  from shop where site_id = "
									+ siteId + " \n   shop_cd = null ");
			} else
				log.assertLog(false,
						"ERROR: select shop_cd  from shop where site_id = "
								+ siteId + " \n   shop_cd = null ");

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
			qm.close();
		}

	}

	
	
	
	
	@Profiled(logger = CLASS_NAME , tag = "getUserList" , message = "authorizationBean: {$0}  , getUserList: {$retrun} "  )
	final public String getUserList( final AuthorizationPageBean authorizationBean ) {
		StringBuffer table = new StringBuffer() ;
		QueryManager qm = null;
		String query ="";
		
		if(authorizationBean.getIntLevelUp() != 2 ) return "" ;
		try {
			qm = new QueryManager();
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone , regdate FROM tuser  where	site_id = " + authorizationBean.getSite_id() + "";
			
			qm.executeQuery(query);
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

			
			for (int i = 0; qm.rows().size() > i; i ++) {
				table.append("<tr>") ;
				table.append("<td>"); 
				table.append(qm.getValueAt(i, 0));
				table.append("</td>");

				table.append("<td>"); 
				table.append(qm.getValueAt(i, 1));
				table.append("</td>");

				table.append("<td>"); 
				table.append(qm.getValueAt(i, 2));
				table.append("</td>");

				table.append("<td>"); 
				table.append(qm.getValueAt(i, 3));
				table.append("</td>");


				table.append("<td>"); 
				table.append(qm.getValueAt(i, 4));
				table.append("</td>");

				table.append("<td>"); 
				table.append("<a href='mailto:" +  qm.getValueAt(i, 5) + " '  >" +  qm.getValueAt(i, 5) + "</a>" );
				table.append("</td>");

				table.append("<td>"); 
				table.append(qm.getValueAt(i, 6));
				table.append("</td>");

				
				table.append("<td>"); 
				table.append(qm.getValueAt(i, 7));
				table.append("</td>");

				table.append("<td>"); 
				table.append( ((String)qm.getValueAt(i, 8)).substring(0,16));
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
			if(qm != null)	qm.close();
		}
		
	 return table.toString() ;
	}
	
	
	@Profiled(logger = CLASS_NAME , tag = "getUserListAll" , message = "authorizationBean: {$0}  , getUserListAll: {$retrun} "  )
	final public String getUserListAll( final AuthorizationPageBean authorizationBean ) {
		StringBuffer table = new StringBuffer() ;
		QueryManager qm = null;
		String query ="";
		
		if(authorizationBean.getIntLevelUp() != 2 ) return "" ;
		try {
			qm = new QueryManager();
			query = "SELECT user_id , login , passwd , first_name , last_name , e_mail , phone , mobil_phone , regdate , site_id FROM tuser " ;
			
			qm.executeQuery(query);
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

			
			for (int i = 0; qm.rows().size() > i; i ++) {
				table.append("<tr>") ;
				table.append("<td>"); 
				table.append(qm.getValueAt(i, 0));
				table.append("</td>");

				table.append("<td>"); 
				table.append(qm.getValueAt(i, 1));
				table.append("</td>");

				table.append("<td>"); 
				table.append(qm.getValueAt(i, 2));
				table.append("</td>");

				table.append("<td>"); 
				table.append(qm.getValueAt(i, 3));
				table.append("</td>");


				table.append("<td>"); 
				table.append(qm.getValueAt(i, 4));
				table.append("</td>");

				table.append("<td>"); 
				table.append("<a href='mailto:" +  qm.getValueAt(i, 5) + " '  >" +  qm.getValueAt(i, 5) + "</a>" );
				table.append("</td>");

				table.append("<td>"); 
				table.append(qm.getValueAt(i, 6));
				table.append("</td>");

				
				table.append("<td>"); 
				table.append(qm.getValueAt(i, 7));
				table.append("</td>");

				table.append("<td>"); 
				table.append( ((String)qm.getValueAt(i, 8)).substring(0,16));
				table.append("</td>");

				table.append("<td>"); 
				table.append(qm.getValueAt(i, 9));
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
			if(qm != null)	qm.close();
		}
		
	 return table.toString() ;
	}
	
	@Profiled(logger = CLASS_NAME , tag = "isNumber" , message = "number: {$0}  , isNumber: {$retrun} "  )
	final public boolean isNumber(final String number) {
		if (number == null)
			return false;
		String IntField = "0123456789.";
		for (int i = 0; i < number.length(); i++) {

			if (IntField.indexOf(number.charAt(i)) == -1) {
				if (number.charAt(i) != '-' && i != 0)
					return false;
			}
		}
		return true;
	}

	@Profiled(logger = CLASS_NAME , tag = "isEnglish" , message = "word: {$0}  , isEnglish: {$retrun} "  )
	final public boolean isEnglish( String word) {
		if (word == null)
			return false;
		word = word.toLowerCase() ;
		String IntField = "0123456789qwertyuiopasdfghjklzxcvbnm_-";
		for (int i = 0; i < word.length(); i++) {

			if (IntField.indexOf(word.charAt(i)) == -1) {
				if (word.charAt(i) != '-' && i != 0)
					return false;
			}
		}
		return true;
	}

	@Profiled(logger = CLASS_NAME , tag = "saveNewDomain" , message = "host: {$0} ,  siteId: {$0}  , saveNewDomain: {$retrun} "  )
	public final void saveNewDomain(final String host , final String siteId  ) 
	{
		QueryManager qm = new QueryManager();
		String query = "" ;
		qm.beginTransaction();
		try {
					query = "UPDATE site SET HOST = ?  where SITE_ID =  " + siteId ;
					Map args = qm.getArgs();
					args.put("HOST" , host );
					qm.executeUpdateWithArgs(query, args);
					qm.commit() ;		
		}
		catch (Exception e) 
		{
			qm.rollback();
			log.error(e) ;
		}
		finally
		{
			if(qm != null)	qm.close();
		}
	}
	
	@Profiled(logger = CLASS_NAME , tag = "saveClassesSessionScope" , message = "httpSession: {$0} , saveClassesSessionScope: {$retrun} "  )
	final void saveClassesSessionScope(final HttpSession  httpSession  ) 
	{
		AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean) httpSession.getAttribute("authorizationPageBeanId");
		if( AuthorizationPageBeanId == null ) return ;
		//if( AuthorizationPageBeanId.getStrLogin().equals(SiteRole.GUEST)) return ;
		//if( AuthorizationPageBeanId.getIntLevelUp() == 0 ) return ;
		QueryManager qm = new QueryManager();
		String query = "" ;
		String key = "";
		qm.beginTransaction();
		Enumeration enumeration;
		String type = null ;
		Object obj = null ;
		
		try {
			enumeration = session_scope.getKeys();
			while (enumeration.hasMoreElements()) 
			{
				key = (String) enumeration.nextElement();
				 type = session_scope.getString(key).trim();
				 obj = httpSession.getAttribute(key) ;
				 if(obj == null ) continue ;
				 
				query = "select USER_ID from store_session WHERE USER_ID = " + AuthorizationPageBeanId.getIntUserID() + " AND TYPE = '" +type+ "'" ;
				qm.executeQuery(query);
				
				if (qm.rows().size() != 0) 
				{
					query = "update store_session  set  USER_ID = ? , TYPE = ? , CLASSBODY = ? , " +
					" ACTIVE = ? where USER_ID = " + AuthorizationPageBeanId.getIntUserID() + " AND TYPE = '" +type+ "'" ;
					Map args = qm.getArgs();
					args.put("USER_ID" , AuthorizationPageBeanId.getIntUserID()  );
					args.put("TYPE" , type );
					args.put("CLASSBODY" ,  obj);
					args.put("ACTIVE" , true );
					qm.executeUpdateWithArgs(query, args);
				
				}
				else
				{

					query = "insert into store_session ( USER_ID ,  TYPE ,  CLASSBODY ,  ACTIVE ) "
						+ " VALUES ( ? ,  ? ,  ? , ? )" ;
					Map args = qm.getArgs();
					args.put("USER_ID" , AuthorizationPageBeanId.getIntUserID()  );
					args.put("TYPE" , type );
					args.put("CLASSBODY" ,  obj);
					args.put("ACTIVE" , true );
					qm.executeInsertWithArgs(query, args);
					
				} 
				
				
				//session.setAttribute(key,obj) ;
			}
			qm.commit() ;		
		}
		catch (Exception e) 
		{
			qm.rollback();
			log.error(e) ;
		}
		finally
		{
			if(qm != null)	qm.close();
		}
	}
	
	final	long getIdsessionHash1(final String sessionId )
	{
		String hash = sessionId.substring(0, 10) ;
		return  hash.hashCode() ;
	}

	final	long getIdsessionHash2(final String sessionId )
	{
		String hash = sessionId.substring(10, 20) ;
		return  hash.hashCode() ;
	}

	final	long getIdsessionHash3(final String sessionId )
	{
		String hash = sessionId.substring(20, 30) ;
		return  hash.hashCode() ;
	}

	final	long getIdsessionHash4(final String sessionId )
	{
		String hash = sessionId.substring(30) ;
		if(hash.indexOf(".") != -1 )hash = hash.substring(0 ,hash.indexOf(".")) ;
		return  hash.hashCode() ;
	}
	
	@Profiled(logger = CLASS_NAME , tag = "saveClassesSessionScopeNew" , message = "httpSession: {$0} , saveClassesSessionScopeNew: {$retrun} "  )
	final	public void saveClassesSessionScopeNew(final HttpSession  httpSession  ) 
	{
		AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean) httpSession.getAttribute("authorizationPageBeanId");
		if( AuthorizationPageBeanId == null ) return ;
		QueryManager Adp = new QueryManager();
		String query = "" ;
		String key = "";
		Adp.beginTransaction();
		Enumeration enumeration;
		String type = null ;
		Object obj = null ;
		String cokie_session_id = (String) httpSession.getAttribute("cokie_session_id");
		httpSession.removeAttribute(cokie_session_id);
		try {
			enumeration = session_scope.getKeys();
			while (enumeration.hasMoreElements()) 
			{
				key = (String) enumeration.nextElement();
				 type = session_scope.getString(key).trim();
				 obj = httpSession.getAttribute(key) ;
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
	
	@Profiled(logger = CLASS_NAME , tag = "saveClassesSessionScopeNew1" , message = "httpSession: {$0} , saveClassesSessionScopeNew1: {$retrun} "  )
	final	public void saveClassesSessionScopeNew1(final HttpSession  httpSession  ) 
	{
	AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean) httpSession.getAttribute("authorizationPageBeanId");
	if( AuthorizationPageBeanId == null ) return ;
	//if( AuthorizationPageBeanId.getStrLogin().equals(SiteRole.GUEST)) return ;
	//if( AuthorizationPageBeanId.getIntLevelUp() == 0 ) return ;
	QueryManager Adp = new QueryManager();
	String query = "" ;
	String key = "";
	Adp.beginTransaction();
	Enumeration enumeration;
	String type = null ;
	Object obj = null ;
	String cokie_session_id = (String) httpSession.getAttribute("cokie_session_id");
	httpSession.removeAttribute(cokie_session_id);
	try {
		enumeration = session_scope.getKeys();
		while (enumeration.hasMoreElements()) 
		{
			key = (String) enumeration.nextElement();
			 type = session_scope.getString(key).trim();
			 obj = httpSession.getAttribute(key) ;
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

	@Profiled(logger = CLASS_NAME , tag = "objectToBytes" , message = "obj: {$0} , objectToBytes: {$retrun} "  )
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
	
	@Profiled(logger = CLASS_NAME , tag = "saveClassesSessionScopeByLogin" , message = "httpSession: {$0} , saveClassesSessionScopeByLogin: {$retrun} "  )
	final	public void saveClassesSessionScopeByLogin(final HttpSession  httpSession  ) 
	{
	AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean) httpSession.getAttribute("authorizationPageBeanId");
	if( AuthorizationPageBeanId == null ) return ;
	QueryManager qm = new QueryManager();
	String query = "" ;
	String key = "";
	qm.beginTransaction();
	Enumeration enumeration;
	String type = null ;
	Object obj = null ;
	String cokie_session_id = (String) httpSession.getAttribute("cokie_session_id");
	httpSession.removeAttribute(cokie_session_id);
	try {
		enumeration = session_scope.getKeys();
		while (enumeration.hasMoreElements()) 
		{
			key = (String) enumeration.nextElement();
			type = session_scope.getString(key).trim();
			obj = httpSession.getAttribute(key) ;
			if(obj == null ) continue ;
			query = "select USER_ID  from store_session WHERE  USER_ID = " + AuthorizationPageBeanId.getIntUserID() + " AND TYPE = '" +type+ "'" ;
			qm.executeQuery(query);
			
			if (qm.rows().size() > 0) 
			{
				query = "update store_session  set  USER_ID = ? , TYPE = ? , CLASSBODY = ? , " +
				" ACTIVE = ? WHERE  USER_ID = " + AuthorizationPageBeanId.getIntUserID() + "  AND TYPE = '" +type+ "'" ;
				Map args = qm.getArgs();
				args.put("USER_ID" , AuthorizationPageBeanId.getIntUserID()  );
				args.put("TYPE" , type );
				args.put("CLASSBODY" ,  obj);
				args.put("ACTIVE" , true );
				qm.executeUpdateWithArgs(query, args);
			
			}
			else
			{

				query = "insert into store_session ( USER_ID ,  TYPE ,  CLASSBODY ,  ACTIVE  )  VALUES ( ? ,  ? ,  ? , ? )" ;
				Map args = qm.getArgs();
				args.put("USER_ID" , AuthorizationPageBeanId.getIntUserID()  );
				args.put("TYPE" , type );
				args.put("CLASSBODY" ,  obj);
				args.put("ACTIVE" , true );
				qm.executeInsertWithArgs(query, args);
				
			} 
			
			
			//session.setAttribute(key,obj) ;
		}
		qm.commit() ;		
	}
	catch (Exception e) 
	{
		qm.rollback();
		log.error(e) ;
	}
	finally
	{
		if(qm != null)	qm.close();
	}
}

	@Profiled(logger = CLASS_NAME , tag = "addAliases" , message = "sce: {$0} , addAliases: {$retrun} "  )
	final public void addAliases(final ServletContextEvent sce) {
		
		 
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

	@Profiled(logger = CLASS_NAME , tag = "postToDNSServerAliases" , message = "postToDNSServerAliases: {$retrun} "  )
	final public void postToDNSServerAliases() {
		
		 
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
	
	@Profiled(logger = CLASS_NAME , tag = "addAliasesInFile" , message = "addAliasesInFile: {$retrun} "  )
	final public void addAliasesInFile() {
		
		 
		//SELECT  LAST_DATE  FROM DOMAIN_PROC WHERE DOMAIN_PROC_ID = 0
		
		QueryManager qm = null ;
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
			qm = new QueryManager();
			if(qm == null)return ; 
			
			addAliase = new AddAliase();
			doc = addAliase.loadConfig(file);
			addAliase.RemoveAllAliase(doc, host) ;
			
			qm.executeQuery(query);
			for (int i = 0; qm.rows().size() > i; i++) 
			{
				aliase =  qm.getValueAt(i, 0);
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
			if(qm != null )
			{
			qm.close();
			}
		}
	}
	
	@Profiled(logger = CLASS_NAME , tag = "isAnsi" , message = "word: {$0} , isAnsi: {$retrun} "  )	
	final	public boolean isAnsi(String word) {
		if (word == null)
			return false;
		word = word.toLowerCase();
		String IntField = "0123456789qwertyuiopasdfghjklzxcvbnm_-.";
		for (int i = 0; i < word.length(); i++) {

			if (IntField.indexOf(word.charAt(i)) == -1) {
				if (word.charAt(i) != '-' && i != 0)
					return false;
			}
		}
		return true;
	}
	
	final	public ResourceBundle getResources_cms_settings() {
		return setup_resources;
	}



	final public Map getControllerMap() {
		return controllerMap;
	}



	final	public Map getTransformerMap() {
		return transformerMap;
	}



	final public ResourceBundle getActionsResources() {
		return actionsResources;
	}



	final	public ResourceBundle getXsltResources() {
		return xsltResources;
	}



	final	public ResourceBundle getSessionScope() {
		return session_scope;
	}



	final	public ResourceBundle getSetupResources() {
		return setup_resources;
	}



	final public CreateShopBean getCreateShopBean() {
		return createShopBean;
	}


}
