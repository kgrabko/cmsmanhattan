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
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.jms.controllers.AddUserToMailMessageBean;
import com.cbsinc.cms.jms.controllers.MQSender;
import com.cbsinc.cms.jms.controllers.Message;

public class RegistrationAction implements IAction 
{


	
	HttpSession session ;
	//ResourceBundle resources = null ;
	//ResourceBundle setup_resources = null ;
	AuthorizationPageFaced authorizationPageFaced = null ;
	AuthorizationPageBean AuthorizationPageBeanId = null ;
	String  create_cabinet = "" ;
	String gen_code = "" ;
	
	   
	public void doPost(HttpServletRequest request, HttpServletResponse response , ServletContext  servletContext) throws Exception {
        
		
		action( request,  response,  servletContext) ;
		
//		if ( request.getParameter("gen_number") != null )
//		{
//			String val1 = request.getParameter("gen_number").trim() ;
//			if(!val1.equals(gen_code.trim())) 
//			{
//			AuthorizationPageBeanId.setStrMessage(resources.getString("wrong_gen_number"));
//			response.sendRedirect("ProductUserPostTransport.jsp" );
//			}
//		}
//		else
//		{
//			AuthorizationPageBeanId.setStrMessage(resources.getString("wrong_gen_number"));
//			response.sendRedirect("ProductUserPostTransport.jsp" );
//		}
		
		
	   String session_id = authorizationPageFaced.getCokieSessionId((HttpServletRequest) request, (HttpServletResponse)response );
       int rezalt_reg =  authorizationPageFaced.isRegCorrect( AuthorizationPageBeanId.getStrLogin() , AuthorizationPageBeanId.getStrPasswd() , AuthorizationPageBeanId.getStrCPasswd() , AuthorizationPageBeanId.getStrFirstName() , AuthorizationPageBeanId.getStrLastName() , AuthorizationPageBeanId.getStrCompany() , AuthorizationPageBeanId.getStrEMail() , AuthorizationPageBeanId.getStrPhone() , AuthorizationPageBeanId.getStrMPhone(), AuthorizationPageBeanId.getStrFax() , AuthorizationPageBeanId.getStrIcq() , AuthorizationPageBeanId.getStrWebsite() ,  AuthorizationPageBeanId.getStrQuestion() , AuthorizationPageBeanId.getStrAnswer() , null , AuthorizationPageBeanId.getCountry_id() , AuthorizationPageBeanId.getCity_id() ,  AuthorizationPageBeanId.getCurrency_id() , session_id , AuthorizationPageBeanId) ;
       AuthorizationPageBeanId.setRezalt_reg(rezalt_reg);
       if(rezalt_reg == 0 )
      {
      
    	   if( authorizationPageFaced.getResources_cms_settings().getString("james_register").contains("true") )
    	   {
//    		  String  login = authorizationPageFaced.getResources_cms_settings().getString("james_login").trim() ;
//    		  String  password = authorizationPageFaced.getResources_cms_settings().getString("james_password").trim() ;
//    		  String  host = authorizationPageFaced.getResources_cms_settings().getString("james_host").trim() ;
//    		  AddUserInMail mailSettingsSession = new AddUserInMail();
//    		  mailSettingsSession.exec(login ,password ,AuthorizationPageBeanId.getStrLogin() ,AuthorizationPageBeanId.getStrPasswd().substring(0,4)+ AuthorizationPageBeanId.getStrLogin(),host);	   
    		  MQSender mqSender = new MQSender( request.getSession(),AddUserToMailMessageBean.messageQuery) ;
   			  Message message = new Message();
   			  message.put("user_login" , AuthorizationPageBeanId.getStrLogin()) ;
   			  message.put("user_password" , AuthorizationPageBeanId.getStrPasswd() ) ;
   			  mqSender.send(message);
    	   }
      
      authorizationPageFaced.initUserSite(AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId) ;  
      AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("welcom_after_registration")) ;
     
      //servletContext.getRequestDispatcher("Productlist.jsp?action=create_site").forward( request,  response);
      if(create_cabinet.equals("true") )servletContext.getRequestDispatcher("Productlist.jsp?action=create_site").forward( request,  response);
      else 
      {
    	  //servletContext.getRequestDispatcher("/Productlist.jsp?offset=0").forward( request,  response);
    	  response.sendRedirect("Productlist.jsp?offset=0" );
      }
      
      return ;
      }
      else
      {


         switch (rezalt_reg)
         {
         case 1:     AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.login.empty")); break ;
         case 2:     AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.password1.empty")); break ;
         case 12:    AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.password1.short")); break ;
         case 10:    AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.password1.empty")); break ;
         case 13:    AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.login.not_english")); break ;
         case 11:    AuthorizationPageBeanId.setStrMessage("select city"); break ;
         case 3:     AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.firstname.empty"));break ;
         case 4:     AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.lastname.empty"));break ;
         case 5:     AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.email.empty"));break ;
         case 6:     AuthorizationPageBeanId.setStrMessage("Field Question  is empty");break ;
         case 7:     AuthorizationPageBeanId.setStrMessage("Field Answer  is empty");break ;
         case 8:     AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.email.wrong"));break ;
         case 9:     AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.password.wrong"));break ;
         case -1:    AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.login1.text") + AuthorizationPageBeanId.getStrLogin() + AuthorizationPageBeanId.getLocalization(servletContext).getString("reg.login2.text"));break ;

       }
      

         
      }

       
	   //AuthorizationPageBeanId.setSelect_city(authorizationPageFaced.getXMLDBList("Authorization.jsp?city_id","city", AuthorizationPageBeanId.getCity_id()  ,"select  city_id , name  from  city where country_id =" + AuthorizationPageBeanId.getCountry_id())) ;
	   //AuthorizationPageBeanId.setSelect_country(authorizationPageFaced.getXMLDBList("Authorization.jsp?country_id","country",  AuthorizationPageBeanId.getCountry_id()   ,"select country_id ,name from country" )) ;
	   
	   AuthorizationPageBeanId.setSelect_city(authorizationPageFaced.getXMLDBList("Authorization.jsp?city_id","city", AuthorizationPageBeanId.getCity_id()  ,"select  city_id , name  from  city where country_id =" + AuthorizationPageBeanId.getCountry_id() + " and locale = '" + AuthorizationPageBeanId.getLocale() + "' "  )) ;
	   AuthorizationPageBeanId.setSelect_country(authorizationPageFaced.getXMLDBList("Authorization.jsp?country_id","country",  AuthorizationPageBeanId.getCountry_id()   ,"select country_id ,name from country  where locale = '" + AuthorizationPageBeanId.getLocale() + "' "  )) ;
	    
	   
	   AuthorizationPageBeanId.setSelect_currency(authorizationPageFaced.getXMLDBList("Authorization.jsp?currency_id","currency", "3"  ,"SELECT currency_id , currency_desc FROM currency  WHERE active = true")) ;
	   AuthorizationPageBeanId.setSelect_site(authorizationPageFaced.getXMLDBList("Authorization.jsp?site_id","site",AuthorizationPageBeanId.getSite_id(),"SELECT  site_id, host FROM  site WHERE  active = true" )) ;

       
       AuthorizationPageBeanId.setIntUserID(1);
       AuthorizationPageBeanId.setIntLevelUp(1);
       AuthorizationPageBeanId.setStrPasswd("");
       AuthorizationPageBeanId.setStrLogin("");
       //response.sendRedirect("RegPage.jsp");


		   
		    
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		action( request,  response,  servletContext) ;
	}

	
	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{

			
		
			session = request.getSession();
			gen_code = (String)request.getSession().getAttribute("gen_number");
			authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();
			AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
	  	    //if( resources == null )  resources = PropertyResourceBundle.getBundle("localization", response.getLocale());
	  		//if( setup_resources == null )  setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources" );

	  		request.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
			response.setHeader("Pragma","no-cache"); //HTTP 1.0
			response.setDateHeader ("Expires", 0); 
	  		
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
	        if(request.getParameter("Icq") != null) AuthorizationPageBeanId.setStrIcq(request.getParameter("Icq"));
	        if(request.getParameter("Website") != null) AuthorizationPageBeanId.setStrWebsite( request.getParameter("Website"));
	        if(request.getParameter("Question") != null) AuthorizationPageBeanId.setStrQuestion(request.getParameter("Question"));
	        if(request.getParameter("Answer") != null) AuthorizationPageBeanId.setStrAnswer( request.getParameter("Answer"));
	        if(request.getParameter("country_id") != null)  AuthorizationPageBeanId.setCountry_id( request.getParameter("country_id"));
	        if(request.getParameter("city_id") != null) AuthorizationPageBeanId.setCity_id( request.getParameter("city_id"));
	        if(request.getParameter("currency_id") != null) AuthorizationPageBeanId.setCurrency_id( request.getParameter("currency_id"));
	        if( request.getParameter("site_id") != null) { AuthorizationPageBeanId.setSite_id(request.getParameter("site_id"),authorizationPageFaced )  ;}
	        //if( request.getParameter("create_cabinet") != null) { create_cabinet = request.getParameter("create_cabinet")  ;}

		
	}

	

}
