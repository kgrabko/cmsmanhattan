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


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.CreateShopBean;
import com.cbsinc.cms.CurrencyHash;
import com.cbsinc.cms.OperationAmountBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;

public class InstallAction implements IAction 
{


	
	HttpSession session ;
	CurrencyHash CurrencyHashBeanId  = CurrencyHash.getInstance() ;
	OperationAmountBean OperationAmountBeanId ;
	java.util.HashMap messageMail ;
	AuthorizationPageBean AuthorizationPageBeanId ;
	// ResourceBundle resources = null ;
	AuthorizationPageFaced authorizationPageFaced  ;
	CreateShopBean createShopBean  = null ;
	
	public InstallAction() {
		createShopBean = new CreateShopBean();
	}

	   
	public void doPost(HttpServletRequest request, HttpServletResponse response , ServletContext  servletContext) throws Exception 
	{
		action( request,  response,  servletContext) ;	    
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		session = request.getSession();
		  OperationAmountBeanId = (com.cbsinc.cms.OperationAmountBean)session.getAttribute("OperationAmountBeanId");
		  messageMail = (java.util.HashMap)session.getAttribute("messageMail");
		  AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		  if(authorizationPageFaced == null)  authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();

		AuthorizationPageBeanId.setSelect_city(authorizationPageFaced.getXMLDBList("Authorization.jsp?city_id","city", AuthorizationPageBeanId.getCity_id()  ,"select  city_id , name  from  city where country_id =" + AuthorizationPageBeanId.getCountry_id())) ;
	    AuthorizationPageBeanId.setSelect_country(authorizationPageFaced.getXMLDBList("Authorization.jsp?country_id","country",  AuthorizationPageBeanId.getCountry_id()   ,"select country_id ,name from country" )) ;
	    //AuthorizationPageBeanId.setSelect_currency(authorizationPageFaced.getXMLDBList("Authorization.jsp?currency_id","currency", "3"  ,"SELECT currency_id , currency_desc FROM currency  WHERE active = true")) ;
	    //AuthorizationPageBeanId.setSelect_site(authorizationPageFaced.getXMLDBList("Authorization.jsp?site_id","site",AuthorizationPageBeanId.getSite_id(),"SELECT  site_id, host FROM  site WHERE  active = true" )) ;

	}


	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
	
		  session = request.getSession();
		  OperationAmountBeanId = (com.cbsinc.cms.OperationAmountBean)session.getAttribute("OperationAmountBeanId");
		  messageMail = (java.util.HashMap)session.getAttribute("messageMail");
		  AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		  if(authorizationPageFaced == null)  authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();
  	      //if( resources == null )  resources = PropertyResourceBundle.getBundle("localization", response.getLocale());

  	      request.setCharacterEncoding("UTF-8");
  	      response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
  	      response.setHeader("Pragma","no-cache"); //HTTP 1.0
  	      response.setDateHeader ("Expires", 0); 
  	      
		  //if( request.getParameter("Login") != null)  AuthorizationPageBeanId.setStrLogin(request.getParameter("Login"));
		  //if( request.getParameter("Passwd1") !=null ) AuthorizationPageBeanId.setStrPasswd(request.getParameter("Passwd1"));
		  //if( request.getParameter("Message") !=null ) AuthorizationPageBeanId.setStrMessage( "" + request.getParameter("Message"));
		  //if( request.getParameter("site_id") != null) { AuthorizationPageBeanId.setSite_id(request.getParameter("site_id"),authorizationPageFaced) ;  }
		  //if( request.getParameter("lang_id") != null) AuthorizationPageBeanId.setLang_id(request.getParameter("lang_id")) ;

		  if(request.getParameter("Login") != null) AuthorizationPageBeanId.setStrLogin(request.getParameter("Login"));
	        if(request.getParameter("Passwd1") != null)AuthorizationPageBeanId.setStrPasswd(request.getParameter("Passwd1"));
	        if(request.getParameter("Passwd2") != null) AuthorizationPageBeanId.setStrCPasswd(request.getParameter("Passwd2"));
	        if(request.getParameter("FName") != null)  AuthorizationPageBeanId.setStrFirstName(request.getParameter("FName"));
	        if(request.getParameter("LName") != null) AuthorizationPageBeanId.setStrLastName(request.getParameter("LName"));
	        if(request.getParameter("Company") != null)AuthorizationPageBeanId.setStrCompany( request.getParameter("Company"));
	        if(request.getParameter("EMail") != null) AuthorizationPageBeanId.setStrEMail(request.getParameter("EMail"));
	        if(request.getParameter("Phone") != null) AuthorizationPageBeanId.setStrPhone(request.getParameter("Phone"));
	        if(request.getParameter("MPhone") != null) AuthorizationPageBeanId.setStrMPhone(request.getParameter("MPhone"));
	        if(request.getParameter("Fax") != null)  AuthorizationPageBeanId.setStrFax( request.getParameter("Fax"));
	        if(request.getParameter("Address") != null) AuthorizationPageBeanId.setAddress(request.getParameter("Address"));
	        if(request.getParameter("Website") != null) AuthorizationPageBeanId.setStrWebsite( request.getParameter("Website"));
	        if(request.getParameter("Question") != null) AuthorizationPageBeanId.setStrQuestion(request.getParameter("Question"));
	        if(request.getParameter("Answer") != null) AuthorizationPageBeanId.setStrAnswer( request.getParameter("Answer"));
	        if(request.getParameter("country_id") != null)  AuthorizationPageBeanId.setCountry_id( request.getParameter("country_id"));
	        if(request.getParameter("city_id") != null) AuthorizationPageBeanId.setCity_id( request.getParameter("city_id"));
	        if(request.getParameter("currency_id") != null) AuthorizationPageBeanId.setCurrency_id( request.getParameter("currency_id"));
	        //if( request.getParameter("site_id") != null) { AuthorizationPageBeanId.setSite_id(request.getParameter("site_id"),authorizationPageFaced )  ;}
	       // if( request.getParameter("create_cabinet") != null) { create_cabinet = request.getParameter("create_cabinet")  ;}

		  

		    createShopBean.addSite(AuthorizationPageBeanId);
		    

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
			AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.login1.text")) ;
			authorizationPageFaced.initUserSite(AuthorizationPageBeanId.getIntUserID(), AuthorizationPageBeanId) ;
			response.sendRedirect("Productlist.jsp?offset=0" );
			// forward not usered
			//request.getRequestDispatcher("Productlist.jsp?offset=0").forward(request, response);

		  }
		  ///else AuthorizationPageBeanId.setStrPasswd("");

		  	if(AuthorizationPageBeanId.getIntLogined() == 2 && AuthorizationPageBeanId.getRezalt_reg() == 0 && !AuthorizationPageBeanId.getStrPasswd().equals(SiteRole.GUEST_PASSWORD) )
		    {
		    AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.login1.text") + " " + AuthorizationPageBeanId.getStrLogin() + " " + AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.login3.wrong"));
	        AuthorizationPageBeanId.setIntUserID(SiteRole.GUEST_ID);
	        AuthorizationPageBeanId.setIntLevelUp(SiteRole.GUEST_ROLE_ID);
	        AuthorizationPageBeanId.setStrPasswd(SiteRole.GUEST_PASSWORD);
	        AuthorizationPageBeanId.setStrLogin(SiteRole.GUEST);
		    }


		  
		    
		
	}

}
