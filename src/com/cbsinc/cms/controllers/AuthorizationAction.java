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


import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;


public class AuthorizationAction implements IAction 
{


	
	
	
	transient ResourceBundle locale_resource = null ;
	AuthorizationPageFaced authorizationPageFaced  ;

	   
	public void doPost(HttpServletRequest request, HttpServletResponse response , ServletContext  servletContext) throws Exception 
	{
		action( request,  response,  servletContext) ;	    
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		action( request,  response,  servletContext) ;
	}


	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
	
		  HttpSession session ;
		  java.util.HashMap messageMail ;
		  AuthorizationPageBean AuthorizationPageBeanId ;
		
		  session = request.getSession();
		  messageMail = (java.util.HashMap)session.getAttribute("messageMail");
		  AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		  if(authorizationPageFaced == null)  authorizationPageFaced  = ServiceLocator.getInstance().getAuthorizationPageFaced();
  	      if( locale_resource == null )  locale_resource = PropertyResourceBundle.getBundle("localization", response.getLocale());

  	      //AuthorizationPageBeanId.setBalance("" +authorizationPageFaced.getBalans(AuthorizationPageBeanId.getIntUserID()));
  	      
  	      request.setCharacterEncoding("UTF-8");
  	      response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
  	      response.setHeader("Pragma","no-cache"); //HTTP 1.0
  	      response.setDateHeader ("Expires", 0); 
  	      
		  if( request.getParameter("Login") != null)  AuthorizationPageBeanId.setStrLogin(request.getParameter("Login"));
		  if( request.getParameter("Passwd1") !=null ) AuthorizationPageBeanId.setStrPasswd(request.getParameter("Passwd1"));
		  if( request.getParameter("Message") !=null ) AuthorizationPageBeanId.setStrMessage( "" + request.getParameter("Message"));
		  if( request.getParameter("site_id") != null) { AuthorizationPageBeanId.setSite_id(request.getParameter("site_id"),authorizationPageFaced) ;  }
//		  AuthorizationPageBeanId.setLang_id(request.getParameter("lang_id")


		    messageMail.clear();
		    messageMail.put("@FirstName", AuthorizationPageBeanId.getStrFirstName() ) ;
		    messageMail.put("@LastName", AuthorizationPageBeanId.getStrLastName() ) ;
//		    messageMail.put("@NumberOfOrder", orderBeanId.getOrder_id()  ) ;
//		    messageMail.put("@ContactPerson", orderBeanId.getContact_person()  ) ;
		    messageMail.put("@Balans", "" +  authorizationPageFaced.getBalans(AuthorizationPageBeanId.getIntUserID())  ) ;
		    messageMail.put("@Phone", AuthorizationPageBeanId.getStrPhone() ) ;
//		    messageMail.put("@Address", orderBeanId.getshipment_address() ) ;
		    messageMail.put("@City", AuthorizationPageBeanId.getStrCity() ) ;
		    messageMail.put("@Contry", AuthorizationPageBeanId.getStrCountry() ) ;
		    messageMail.put("@CustomerEmail", AuthorizationPageBeanId.getStrEMail() ) ;
		    messageMail.put("@CustomerFax", AuthorizationPageBeanId.getStrFax() ) ;

//		    messageMail.put("@CustomerCommentariy", orderBeanId.getshipment_description() ) ;
//		    messageMail.put("@ProductCount", "" +  orderBeanId.getProductsListSize(request)  ) ;
		    String session_id = authorizationPageFaced.getCokieSessionId((HttpServletRequest) request, (HttpServletResponse)response );
		  
		  if( authorizationPageFaced.isLoginCorrect(AuthorizationPageBeanId.getStrLogin() , AuthorizationPageBeanId.getStrPasswd() , AuthorizationPageBeanId, session_id )  && AuthorizationPageBeanId.getStrLogin().length() != 0 )
		  {
			//AuthorizationPageBeanId.setStrMessage("You welcom to portal. You have success  authorization . ") ;
			AuthorizationPageBeanId.setStrMessage(locale_resource.getString("reg.login1.text")) ;
			authorizationPageFaced.initUserSite(AuthorizationPageBeanId.getIntUserID(), AuthorizationPageBeanId) ;
			
//			if(AuthorizationPageBeanId.getIntLevelUp() == SiteRole.ADMINISTRATOR_ROLE_ID || AuthorizationPageBeanId.getIntLevelUp() == SiteRole.MEMBER_ROLE_ID )
//			{
//			authorizationPageFaced.loadOldSessionbyLogin("" + AuthorizationPageBeanId.getIntUserID(), session, authorizationPageFaced.getSession_scope());
//			String lastPage = AuthorizationPageBeanId.getLastVisitedPage() ;
//				if(!lastPage.equals(""))
//				{
//				((HttpServletRequest) request).getRequestDispatcher(lastPage).forward( request,  response) ;
//				return ;
//				}
//			}
			
			response.sendRedirect("Productlist.jsp?offset=0" );
			return ;
			//servletContext.getRequestDispatcher("/Productlist.jsp?offset=0").forward( request,  response);
		  }
		  ///else AuthorizationPageBeanId.setStrPasswd("");

		  	if(AuthorizationPageBeanId.getIntLogined() == 2 && AuthorizationPageBeanId.getRezalt_reg() == 0 && !AuthorizationPageBeanId.getStrPasswd().equals(SiteRole.GUEST_PASSWORD) )
		    {
		    AuthorizationPageBeanId.setStrMessage(locale_resource.getString("reg.login1.text") + " " + AuthorizationPageBeanId.getStrLogin() + " " + locale_resource.getString("reg.login3.wrong"));
	        AuthorizationPageBeanId.setIntUserID(SiteRole.GUEST_ID);
	        AuthorizationPageBeanId.setIntLevelUp(SiteRole.GUEST_ROLE_ID);
	        AuthorizationPageBeanId.setStrPasswd(SiteRole.GUEST_PASSWORD);
	        AuthorizationPageBeanId.setStrLogin(SiteRole.GUEST);
		    }


		  
		    AuthorizationPageBeanId.setSelect_city(authorizationPageFaced.getXMLDBList("Authorization.jsp?city_id","city", AuthorizationPageBeanId.getCity_id()  ,"select  city_id , name  from  city where country_id =" + AuthorizationPageBeanId.getCountry_id() + " and locale = '" + AuthorizationPageBeanId.getLocale() + "' "  )) ;
		    AuthorizationPageBeanId.setSelect_country(authorizationPageFaced.getXMLDBList("Authorization.jsp?country_id","country",  AuthorizationPageBeanId.getCountry_id()   ,"select country_id ,name from country  where locale = '" + AuthorizationPageBeanId.getLocale() + "' "  )) ;
		    AuthorizationPageBeanId.setSelect_currency(authorizationPageFaced.getXMLDBList("Authorization.jsp?currency_id","currency", "3"  ,"SELECT currency_id , currency_desc FROM currency  WHERE active = true")) ;
		    AuthorizationPageBeanId.setSelect_site(authorizationPageFaced.getXMLDBList("Authorization.jsp?site_id","site",AuthorizationPageBeanId.getSite_id(),"SELECT  site_id, host FROM  site WHERE  active = true" )) ;
		    AuthorizationPageBeanId.setSelect_menu_catalog(authorizationPageFaced.getMenuXMLDBList("Productlist.jsp?catalog_id","menu", AuthorizationPageBeanId.getCatalog_id()  ,"select catalog_id , lable , parent_id  from catalog   where  active = true and parent_id = 0 and site_id = " + AuthorizationPageBeanId.getSite_id()  +" and lang_id = " + AuthorizationPageBeanId.getLang_id()  +" or parent_id in (select catalog_id   from catalog   where  active = true and site_id = " + AuthorizationPageBeanId.getSite_id()  +"  and parent_id = 0 )" ) ) ;

		
	}

}
