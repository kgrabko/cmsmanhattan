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
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.Catalog_listBean;
import com.cbsinc.cms.ProductlistBean;
import com.cbsinc.cms.SearchBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.faceds.PolicyFaced;
import com.cbsinc.cms.faceds.ProductPostAllFaced;
import com.cbsinc.cms.faceds.ProductlistFaced;
import com.cbsinc.cms.jms.controllers.MQSender;
import com.cbsinc.cms.jms.controllers.Message;
import com.cbsinc.cms.jms.controllers.SendMailMessageBean;

public class SearchAction  implements IAction
{

	
	
	//CreateShopBean 	    createShopBeanId = new CreateShopBean();
	ProductPostAllFaced productPostAllFaced ;
	//ResourceBundle setup_resources = null ;
	//ResourceBundle localization = null ;
	AuthorizationPageFaced authorizationPageFaced ;
	//PolicyBean policyBeanId ;
	

	
	
	public SearchAction() 
	{
		//if( setup_resources == null )  setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources");
	}
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response , ServletContext  servletContext ) throws Exception
	{
		doGet( request,  response,  servletContext) ;
	}



	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception {
		SearchBean  ProductlistBeanId ;
		AuthorizationPageBean AuthorizationPageBeanId ;
		HttpSession session ;
		boolean is_criteria_by_catalog = false ;
		String notselected = "" ;
		Catalog_listBean catalog_listBean ;
		boolean isInternet = true ;
		productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced();
		authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();

		is_criteria_by_catalog = authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true") ;

		if(request.getRemoteAddr().startsWith("192."))isInternet = false ;
		if(request.getRemoteAddr().startsWith("10."))isInternet = false ;
		session = request.getSession();
		AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		
		//if( localization == null )  localization = PropertyResourceBundle.getBundle("localization", response.getLocale());
		if(notselected.length() == 0 ) notselected = AuthorizationPageBeanId.getLocalization(servletContext).getString("notselected") ;
		//session.setAttribute(PageConstant.LAST_URL_PAGE ,request.getRequestURL() ) ;
		
	    ProductlistFaced productlistFaced = ServiceLocator.getInstance().getProductlistFaced();
	    PolicyFaced policyFaced = ServiceLocator.getInstance().getPolicyFaced();
	    //policyBeanId = (PolicyBean)session.getAttribute("policyBeanId");
	    //ProductlistBeanId = (ProductlistBean)session.getAttribute("ProductlistBeanId");
	    //ProductlistBeanId = (ProductlistBean)request.getAttribute("ProductlistBeanId");
	    ProductlistBeanId = new SearchBean();
	    request.setAttribute("SearchBeanId", ProductlistBeanId) ;
	   // createShopBeanId.setServletContext(servletContext);
	    //createShopBeanId = (CreateShopBean)session.getAttribute("createShopBeanId");
		//SoftPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");
//	    catalog_listBean = (Catalog_listBean)session.getAttribute("catalog_listBean");
//	    if( catalog_listBean  == null)
//	    {
//	    	catalog_listBean = new Catalog_listBean();
//	    	//request.setAttribute("catalog_listBean",catalog_listBean);
//	    }
		
	
		if( policyFaced == null ||   ProductlistBeanId == null ||   AuthorizationPageBeanId == null || authorizationPageFaced == null  ) return ;
		
		
		request.setCharacterEncoding("UTF-8");
		
		
		ProductlistBeanId.isInternet = isInternet ;
		AuthorizationPageBeanId.setBalance(ServiceLocator.getInstance().getAuthorizationPageFaced().getStrBalans(AuthorizationPageBeanId.getIntUserID()));

		if( request.getParameter("offset") != null && isNumber(request.getParameter("offset")) )
		{
		ProductlistBeanId.setOffset(  ProductlistBeanId.stringToInt(request.getParameter("offset")));
		AuthorizationPageBeanId.setOffsetLastPage(Long.parseLong(request.getParameter("offset")));
		}
		
		if( request.getParameter("catalog_id") !=null  && isNumber(request.getParameter("catalog_id"))  )
		{
		    if( AuthorizationPageBeanId.getCatalog_id().compareTo(request.getParameter("catalog_id"))!= 0)
		   {
		    ProductlistBeanId.setOffset(0);
		   }
		    AuthorizationPageBeanId.setCatalog_id( request.getParameter("catalog_id"));

		}
		ProductlistBeanId.setIntLevelUp( AuthorizationPageBeanId.getIntLevelUp() ) ;

		// Open or closed dialog window 
		if( request.getParameter("dialog") != null)ProductlistBeanId.setDialog( request.getParameter("dialog"));
		if( request.getParameter("is_advanced_search_open") != null)ProductlistBeanId.setAdvancedSearchOpen( request.getParameter("is_advanced_search"));
		if( request.getParameter("is_forum_open") != null)ProductlistBeanId.setForumOpen( request.getParameter("is_forum_open"));
		
		if( request.getParameter("fromcost") != null && isFloat(request.getParameter("fromcost")) )AuthorizationPageBeanId.setStrFromCost( request.getParameter("fromcost").trim());
		if( request.getParameter("tocost") != null && isFloat(request.getParameter("tocost")) )AuthorizationPageBeanId.setStrToCost( request.getParameter("tocost").trim());
			
		if( request.getParameter("action") !=null ) { ProductlistBeanId.setAction( request.getParameter("action"));  } 
		if( request.getParameter("search_value") !=null ) ProductlistBeanId.setSearchValueArg( request.getParameter("search_value"));
		if( request.getParameter("searchquery") !=null && isNumber(request.getParameter("searchquery")) ) ProductlistBeanId.setSearchquery(Integer.parseInt(request.getParameter("searchquery")));
		else if( request.getParameter("offset") ==null  ) ProductlistBeanId.setSearchquery(0);
		
				
		String cl_locale = request.getParameter("locale") ;
		if( cl_locale !=null  ) 
		{
			
			
			if(isAllowLocale(cl_locale))
			{
				
				 if( AuthorizationPageBeanId.getLocale().compareTo(cl_locale)!= 0)
				   {
				    ProductlistBeanId.setOffset(0);
				    AuthorizationPageBeanId.setCatalog_id("" + SpecialCatalog.OUTPUT_PAGES_SORT_BY_SOFT_ID);
				   }
				
				AuthorizationPageBeanId.setLocale( cl_locale,servletContext);
			}
			
		}
		
		if( ProductlistBeanId.getSearchquery() == 2 && request.getParameter("search_char") !=null && request.getParameter("search_char").length() > 0 ) 
		{
		    // if new char then start with 0
			if( ProductlistBeanId.getSearchValueArg().compareTo(request.getParameter("search_char"))!= 0)
		   {
		    ProductlistBeanId.setOffset(0);
		   }

		 ProductlistBeanId.setSearchValueArg(request.getParameter("search_char") );
		}
		else if( ProductlistBeanId.getSearchquery() == 1 && request.getParameter("search_value") !=null && request.getParameter("search_value").length() > 0 ) 
		{
		 ProductlistBeanId.setSearchValueArg( request.getParameter("search_value"));
		}
		else if( ProductlistBeanId.getSearchquery() == 0 )
		{
		 ProductlistBeanId.setSearchValueArg("");
		}



		if( ProductlistBeanId.getAction().compareTo("del") == 0 )
		{
		   	    ProductlistBeanId.setAction("");
				if( AuthorizationPageBeanId.getIntLevelUp() == 0 ){
				AuthorizationPageBeanId.setStrMessage("Access denny  for login user , You must be membership") ;
				//response.sendRedirect("Authorization.jsp" );
				servletContext.getRequestDispatcher("/Authorization.jsp").forward( request,  response); 
				}
			
		         if( request.getParameter("product_id") != null && isNumber(request.getParameter("product_id"))  )
		         {

		        	 if( AuthorizationPageBeanId.getIntLevelUp() != 2 )
		        	 {
		               int user_id = productlistFaced.getWhoseProduct(request.getParameter("product_id")) ;
		        	    if( AuthorizationPageBeanId.getIntUserID() != user_id )
		        	    {
		        		  AuthorizationPageBeanId.setStrMessage("Access denny  for login "+ AuthorizationPageBeanId +" , You must be owner message") ;
		        		  //response.sendRedirect("Authorization.jsp" );
		        		  servletContext.getRequestDispatcher("/Authorization.jsp").forward( request,  response); 
		        		}
		        	 }
		        	 
		        	 if(AuthorizationPageBeanId.getSite_id().compareTo(productlistFaced.getSiteByProduct(request.getParameter("product_id"))) == 0) productlistFaced.deletePosition(request.getParameter("product_id")) ;
		        	 else AuthorizationPageBeanId.setStrMessage("Access denny  for login "+ AuthorizationPageBeanId +" , You must be owner message") ;
		         }
		}
		    
	
		 
		if( ProductlistBeanId.getAction().compareTo("up_position") == 0 )
		{
			    int user_id = 0 ;
				ProductlistBeanId.setAction("");
				if( AuthorizationPageBeanId.getIntLevelUp() == 0 ){
				AuthorizationPageBeanId.setStrMessage("Access denny  for login user , You must be membership") ;
				//response.sendRedirect("Authorization.jsp" );
				servletContext.getRequestDispatcher("/Authorization.jsp").forward( request,  response); 
      		    return ;
				}
			
		         if( request.getParameter("product_id") != null )
		         {

		        	 if( AuthorizationPageBeanId.getIntLevelUp() != 2 )
		        	 {
		                user_id = productlistFaced.getWhoseProduct(request.getParameter("product_id")) ;
		        	    if(user_id == -1) return ; // Пользователя для этой записи не сущетсвует
		                if( AuthorizationPageBeanId.getIntUserID() != user_id )
		        	    {
		        		  AuthorizationPageBeanId.setStrMessage("Access denny  for login "+ AuthorizationPageBeanId.getStrLogin() +" , You must be owner message") ;
		        		  //response.sendRedirect("Authorization.jsp" );
		        		  servletContext.getRequestDispatcher("/Authorization.jsp").forward( request,  response); 
		        		  return ;
		        		}
		        	 }

		        	 
		        	 /*
		        	 float  balans = authorizationPageFaced.getBalans(user_id) ;
		        	 float  cost = productlistFaced.getProductCost(request.getParameter("product_id")) ;
		        	 if(cost > balans)
		        	 {
		        		  AuthorizationPageBeanId.setStrMessage("Access denny  for login "+ AuthorizationPageBeanId.getStrLogin() +" , You must be owner message") ;
		        		  response.sendRedirect("Authorization.jsp" );
		        		  return ;
		        	 }		        		 
		        	*/
		        	 
		        	 productlistFaced.upPosition(request.getParameter("product_id")) ;
		         }
		
		        //request.setAttribute("product_id",null);
		        //request.setAttribute("action",null);
		}

		if( ProductlistBeanId.getAction().compareTo("set_color") == 0 )
		{
		   	    ProductlistBeanId.setAction("");
				if( AuthorizationPageBeanId.getIntLevelUp() == 0 ){
				AuthorizationPageBeanId.setStrMessage("Access denny  for login user , You must be membership") ;
				//response.sendRedirect("Authorization.jsp" );
				servletContext.getRequestDispatcher("/Authorization.jsp").forward( request,  response); 
				return;
				}
			
		         if( request.getParameter("product_id") != null )
		         {

		        	 if( AuthorizationPageBeanId.getIntLevelUp() != 2 )
		        	 {
		               int user_id = productlistFaced.getWhoseProduct(request.getParameter("product_id")) ;
		        	    if( AuthorizationPageBeanId.getIntUserID() != user_id )
		        	    {
		        		  AuthorizationPageBeanId.setStrMessage("Access denny  for login "+ AuthorizationPageBeanId +" , You must be owner message") ;
		        		  //response.sendRedirect("Authorization.jsp" );
		        		  servletContext.getRequestDispatcher("/Authorization.jsp").forward( request,  response); 
		        		  return ;
		        		}
		        	 }
		        	 productlistFaced.colorPosition(request.getParameter("product_id"),"#FCF8CF") ;
		         }
		}
		
		if( ProductlistBeanId.getAction().compareTo("edit") == 0 )
		{
			

		         if( isNumber(request.getParameter("product_id")) )
		         {
		        	  ProductlistBeanId.setAction("");
		        	  
		        	  //AuthorizationPageBeanId.setCatalog_id(productlistFaced.getCatalogId(request.getParameter("product_id")));
		        	  
//		        	  productPostAllFaced.initPage(request.getParameter("product_id"),  SoftPostBeanId , AuthorizationPageBeanId);
		        	  // Для того чтобы правильно отрывался раздел когда открываеш из другово раздела
		        	  //AuthorizationPageBeanId.setCatalogParent_id("" + productlistFaced.getCatalogParentId(AuthorizationPageBeanId));
		        	  
		        	  if(request.getParameter("element").compareTo("forum") == 0 ){  response.sendRedirect("ProductUserPost.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("userinfo") == 0 ){   response.sendRedirect("ProductUserPostWithOutCatalog.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("edit_biz_info") == 0 ){   response.sendRedirect("ProductUserPostBusiness.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("edit_transport") == 0 ){  response.sendRedirect("ProductUserPostTransport.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("edit_realty") == 0 ){   response.sendRedirect("ProductUserPostRealty.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("addvideo") == 0 ){   response.sendRedirect("ProductUserPostVideo.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("addmusics") == 0 ){   response.sendRedirect("ProductUserPostMusic.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("ext1_user") == 0 ){   response.sendRedirect("Ext1ProductPostUser.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("ext2_user") == 0 ){   response.sendRedirect("Ext2ProductPostUser.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("ext_files_user") == 0 ){   response.sendRedirect("ExtFilesProductPostUser.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("ext_ofice_files_user") == 0 ){  response.sendRedirect("ExtOficeFilesProductPostUser.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("ext_music_files_user") == 0 ){  response.sendRedirect("ExtOficeFilesProductPostUser.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("product") == 0 ){  response.sendRedirect("ProductPostCre.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("news") == 0 ){   response.sendRedirect("ProductPostCre.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  //if(request.getParameter("element").compareTo("news") == 0 ) response.sendRedirect("ProductPost.jsp") ;
		        	  if(request.getParameter("element").compareTo("co1") == 0 ){  response.sendRedirect("Co1ProductPost.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("co2") == 0 ){ response.sendRedirect("Co2ProductPost.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("ext1") == 0 ){  response.sendRedirect("Ext1ProductPost.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("ext2") == 0 ){  response.sendRedirect("Ext2ProductPost.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("ext_files") == 0 ){  response.sendRedirect("ExtFilesProductPost.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("ext_tabls") == 0 ){   response.sendRedirect("ExtTabsProductPost.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("ext_service_page") == 0 ){   response.sendRedirect("ServicePagePost.jsp?product_id="+request.getParameter("product_id"));   return ;}
		        	  if(request.getParameter("element").compareTo("blog") == 0 ) 
		        	  {
		        		  AuthorizationPageBeanId.setLastProductId( Long.parseLong(request.getParameter("product_parent_id")));
		        		  response.sendRedirect("BlogExtProductPost.jsp?product_id="+request.getParameter("product_id") );  
		        		  return ;
		        	  }
//		        	  {
//		        		 policyFaced.mergePolicyBean(AuthorizationPageBeanId.getIntUserID(), request.getParameter("product_parent_id") , policyBeanId) ;
//		        		 policyBeanId.setType_page("product_parent_id") ;
//		        		 policyBeanId.setBack_url("Policy.jsp?policy_byproductid=" + request.getParameter("product_parent_id")) ;
//		        		 policyBeanId.setIntUserID(AuthorizationPageBeanId.getIntUserID());
//		        		 response.sendRedirect("BlogExtProductPost.jsp?product_id="+request.getParameter("product_id"));   
//		        		 return ;
//		        	  }
		        	  

		       }
		 }
//        else
//       {
//        	SoftPostBeanId.setSoft_id("-1") ;
//       }

		if( ProductlistBeanId.getAction().compareTo("create_site") == 0 )
		{
			ProductlistBeanId.setAction("");
			authorizationPageFaced.getCreateShopBean().setLogin(AuthorizationPageBeanId.getStrLogin());
			authorizationPageFaced.getCreateShopBean().setPasswd(AuthorizationPageBeanId.getStrPasswd());
			authorizationPageFaced.getCreateShopBean().setAddress("no created");

			String domain = AuthorizationPageBeanId.getSite_dir().substring(AuthorizationPageBeanId.getSite_dir().indexOf(".")) ;
			
			authorizationPageFaced.getCreateShopBean().setCompany_name(AuthorizationPageBeanId.getStrCompany());
			authorizationPageFaced.getCreateShopBean().setSite_dir(AuthorizationPageBeanId.getStrLogin() +  domain );
			authorizationPageFaced.getCreateShopBean().setNick_site(AuthorizationPageBeanId.getStrLogin() +  domain );
			authorizationPageFaced.getCreateShopBean().setSubject_site("Cabinet");
			authorizationPageFaced.getCreateShopBean().setPerson(AuthorizationPageBeanId.getStrFirstName() + " " + AuthorizationPageBeanId.getStrLastName() );
			authorizationPageFaced.getCreateShopBean().setPhone(AuthorizationPageBeanId.getStrPhone());
			
			if(AuthorizationPageBeanId.getIntLevelUp()==0)
			{
			AuthorizationPageBeanId.setStrMessage("не регистрированный пользователь  не может создовать магазин");
			//response.sendRedirect("Authorization.jsp?Login=newuser" );
			servletContext.getRequestDispatcher("/Authorization.jsp?Login=newuser").forward( request,  response);
			return ;
			}
			authorizationPageFaced.getCreateShopBean().addSite(AuthorizationPageBeanId.getIntUserID());
			servletContext.getRequestDispatcher("/Authorization.jsp?site_id=" + authorizationPageFaced.getCreateShopBean().getSite_id() + "&Login=" + authorizationPageFaced.getCreateShopBean().getLogin()+ "&Passwd1=" + authorizationPageFaced.getCreateShopBean().getPasswd()).forward( request,  response);
			return ;
			//response.sendRedirect("Authorization.jsp?site_id=" + authorizationPageFaced.getCreateShopBean().getSite_id() + "&Login=" + authorizationPageFaced.getCreateShopBean().getLogin()+ "&Passwd1=" + authorizationPageFaced.getCreateShopBean().getPasswd() );

		}

		
		//if( ProductlistBeanId.getAction().compareTo("create_site2") == 0 )
		if( request.getParameter("create_site_by_id") != null )		
		{
			ProductlistBeanId.setAction("");
			authorizationPageFaced.getCreateShopBean().setLogin(AuthorizationPageBeanId.getStrLogin());
			authorizationPageFaced.getCreateShopBean().setPasswd(AuthorizationPageBeanId.getStrPasswd());
			authorizationPageFaced.getCreateShopBean().setAddress("no created");

			String domain = AuthorizationPageBeanId.getSite_dir().substring(AuthorizationPageBeanId.getSite_dir().indexOf(".")) ;
			
			authorizationPageFaced.getCreateShopBean().setCompany_name(AuthorizationPageBeanId.getStrCompany());
			authorizationPageFaced.getCreateShopBean().setSite_dir(AuthorizationPageBeanId.getStrLogin() +  domain );
			authorizationPageFaced.getCreateShopBean().setHost("www." + AuthorizationPageBeanId.getStrLogin() + ".irr.bz") ;
			authorizationPageFaced.getCreateShopBean().setNick_site(AuthorizationPageBeanId.getStrLogin() +  domain );
			authorizationPageFaced.getCreateShopBean().setSubject_site("Cabinet");
			authorizationPageFaced.getCreateShopBean().setPerson(AuthorizationPageBeanId.getStrFirstName() + " " + AuthorizationPageBeanId.getStrLastName() );
			authorizationPageFaced.getCreateShopBean().setPhone(AuthorizationPageBeanId.getStrPhone());
			
			
			if(AuthorizationPageBeanId.getIntLevelUp()==0)
			{
			AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("you_can_not_to_create_shop"));
			servletContext.getRequestDispatcher("/Authorization.jsp?Login=").forward( request,  response);
			//response.sendRedirect("Authorization.jsp?Login=" );
			return ;
			}
			
			
			
			if(AuthorizationPageBeanId.getLang_id() == 1)	authorizationPageFaced.getCreateShopBean().addShopWithExtract(AuthorizationPageBeanId,request.getParameter("create_site_by_id"),servletContext);
			else authorizationPageFaced.getCreateShopBean().addShopWithExtract_en(AuthorizationPageBeanId,request.getParameter("create_site_by_id"),servletContext);
			
			//authorizationPageFaced.getCreateShopBean().addShopWithExtract_allLang(AuthorizationPageBeanId,request.getParameter("create_site_by_id"),servletContext);
			
			if(AuthorizationPageBeanId.getUser_site().equals("-1"))
			{
			HashMap messageMail = (HashMap)session.getAttribute("messageMail");
			String sitePath = (String)request.getSession().getAttribute("site_path");
			String shop = sitePath +File.separatorChar+"mail" + File.separatorChar + "newshop.txt" ;
			String policy = sitePath +File.separatorChar+"mail" + File.separatorChar + "Policy.pdf" ;			
			messageMail.put("@FirstName",AuthorizationPageBeanId.getStrFirstName() ) ;
			messageMail.put("@LastName",  AuthorizationPageBeanId.getStrLastName() ) ;
			messageMail.put("@EmailPassword", AuthorizationPageBeanId.getEmailPassword() ) ;
			messageMail.put("@Password", AuthorizationPageBeanId.getStrPasswd() ) ;
			messageMail.put("@Login", AuthorizationPageBeanId.getStrLogin() ) ;
			messageMail.put("@Shop", "http://www.siteoneclick.com/Productlist.jsp?site=" + authorizationPageFaced.getCreateShopBean().getSite_id() ) ;
			messageMail.put("@Policy", "http://www.siteoneclick.com/Policy.pdf"  ) ;
			
		
			
	      	MQSender mqSender = new MQSender( request.getSession(),SendMailMessageBean.messageQuery) ;
			Message message = new Message();
			message.put("to" , AuthorizationPageBeanId.getStrEMail()) ;
			message.put("subject" , "My Internet shop " ) ;
			message.put("pathmessage" , shop ) ;
			message.put("fields" , messageMail ) ;
			//message.put("@Policy", "http://www.siteoneclick.com/Policy.pdf"  ) ;
			//message.put("attachFile", policy ) ;
			mqSender.send(message);
			}
			
			//AuthorizationPageBeanId.
			AuthorizationPageBeanId.setUser_site(authorizationPageFaced.getCreateShopBean().getSite_id());
			
			//response.sendRedirect("Authorization.jsp?site_id=" + authorizationPageFaced.getCreateShopBean().getSite_id() + "&Login=" + authorizationPageFaced.getCreateShopBean().getLogin()+ "&Passwd1=" + authorizationPageFaced.getCreateShopBean().getPasswd() );
			response.sendRedirect("Productlist.jsp?action=login_usersite" );
			return ;
		}

		if( ProductlistBeanId.getAction().compareTo("login_usersite") == 0 )
		{
		ProductlistBeanId.setAction("");
		AuthorizationPageBeanId.setCatalog_id("-2");
			if(AuthorizationPageBeanId.getUser_site().compareTo("-1")!=0)
			{
				String cokie_session_id = (String) session.getAttribute("cokie_session_id");
				authorizationPageFaced.clearCookieFromBD(AuthorizationPageBeanId,cokie_session_id);
				AuthorizationPageBeanId.setSite_id(AuthorizationPageBeanId.getUser_site(),authorizationPageFaced);
				String session_id = authorizationPageFaced.getCokieSessionId((HttpServletRequest) request, (HttpServletResponse)response );
				if( authorizationPageFaced.isLoginCorrect(AuthorizationPageBeanId.getStrLogin() , AuthorizationPageBeanId.getStrPasswd(), AuthorizationPageBeanId, session_id )  && AuthorizationPageBeanId.getStrLogin().length() != 0  )
				{
					//response.sendRedirect("Productlist.jsp?offset=0" );
					//servletContext.getRequestDispatcher("/Productlist.jsp?offset=0").forward( request,  response);
				ProductlistBeanId.setOffset(0);
				}
			}
			else AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("you_not_have_site"));
			
		}

///////+++
		if( request.getParameter("site") != null && isNumber(request.getParameter("site")) )
		{
		
		
			
		ProductlistBeanId.setAction("");
		String site = request.getParameter("site") ;
		
		if( AuthorizationPageBeanId.getSite_id().compareTo(site)!= 0)
		   {
		    ProductlistBeanId.setOffset(0);
		    AuthorizationPageBeanId.setCatalog_id("" + SpecialCatalog.OUTPUT_PAGES_SORT_BY_SOFT_ID);
		   }
		
		String cokie_session_id = (String) session.getAttribute("cokie_session_id");
		authorizationPageFaced.clearCookieFromBD(AuthorizationPageBeanId,cokie_session_id);
		AuthorizationPageBeanId.setSite_id(site,authorizationPageFaced);
		String session_id = authorizationPageFaced.getCokieSessionId((HttpServletRequest) request, (HttpServletResponse)response );
			if( authorizationPageFaced.isLoginCorrect(AuthorizationPageBeanId.getStrLogin() , AuthorizationPageBeanId.getStrPasswd(), AuthorizationPageBeanId, session_id)  && AuthorizationPageBeanId.getStrLogin().length() != 0  )
			{
			//response.sendRedirect("Authorization.jsp?site_id=" + authorizationPageFaced.getCreateShopBean().getSite_id() + "&Login=" + authorizationPageFaced.getCreateShopBean().getLogin()+ "&Passwd1=" + authorizationPageFaced.getCreateShopBean().getPasswd() );
				//ProductlistBeanId.setSite_id(request.getParameter("site"));
				//response.sendRedirect("Productlist.jsp?offset=0" );
				//servletContext.getRequestDispatcher("/Productlist.jsp?offset=0").forward( request,  response);
				ProductlistBeanId.setOffset(0);
			}
			else 
			{
				AuthorizationPageBeanId.setStrPasswd(SiteRole.GUEST);
				AuthorizationPageBeanId.setStrLogin(SiteRole.GUEST);
				AuthorizationPageBeanId.setSite_id(site,authorizationPageFaced);
				if( authorizationPageFaced.isLoginCorrect(AuthorizationPageBeanId.getStrLogin() , AuthorizationPageBeanId.getStrPasswd() , AuthorizationPageBeanId, "" )  && AuthorizationPageBeanId.getStrLogin().length() != 0  )
					{
					//response.sendRedirect("Productlist.jsp?offset=0" );
					//servletContext.getRequestDispatcher("/Productlist.jsp?offset=0").forward( request,  response);
					ProductlistBeanId.setOffset(0);
					//return ;
					//response.sendRedirect("Authorization.jsp?site_id=" + authorizationPageFaced.getCreateShopBean().getSite_id() + "&Login=" + authorizationPageFaced.getCreateShopBean().getLogin()+ "&Passwd1=" + authorizationPageFaced.getCreateShopBean().getPasswd() );
					}
			}
			
		}

///////+++//
		if( request.getParameter("logoff_site") != null  )
		{
		ProductlistBeanId.setAction("");
		String cokie_session_id = (String) session.getAttribute("cokie_session_id");
		authorizationPageFaced.clearCookieFromBD(AuthorizationPageBeanId,cokie_session_id);
		AuthorizationPageBeanId.setStrPasswd(SiteRole.GUEST);
		AuthorizationPageBeanId.setStrLogin(SiteRole.GUEST);
		AuthorizationPageBeanId.setSite_id(request.getParameter("logoff_site"),authorizationPageFaced);
		if( authorizationPageFaced.isLoginCorrect(AuthorizationPageBeanId.getStrLogin() , AuthorizationPageBeanId.getStrPasswd() , AuthorizationPageBeanId, "" )  && AuthorizationPageBeanId.getStrLogin().length() != 0  )
			{
			
			//response.sendRedirect("Productlist.jsp?offset=0" );
			//servletContext.getRequestDispatcher("/Productlist.jsp?offset=0").forward( request,  response);
			ProductlistBeanId.setOffset(0);
			//return ;
			//response.sendRedirect("Authorization.jsp?site_id=" + authorizationPageFaced.getCreateShopBean().getSite_id() + "&Login=" + authorizationPageFaced.getCreateShopBean().getLogin()+ "&Passwd1=" + authorizationPageFaced.getCreateShopBean().getPasswd() );
			}
		}


		
		if(ProductlistBeanId.getAction().compareTo("logoff") == 0 || ProductlistBeanId.getAction().compareTo("logoff_usersite") == 0 )
		{
			String  site_id = AuthorizationPageBeanId.getSite_id();
			String cokie_session_id = (String) session.getAttribute("cokie_session_id");
			session.invalidate();
			authorizationPageFaced.clearCookieFromBD(AuthorizationPageBeanId,cokie_session_id);
			//clearCookieFromBD(AuthorizationPageBean authorizationBean)
			//response.sendRedirect("Productlist.jsp?site=" + site_id );
			servletContext.getRequestDispatcher("/Productlist.jsp?site=" + site_id).forward( request,  response);
			return ;
		}
		
		//if( ProductlistBeanId.getAction().compareTo("logoff") == 0  )
		//{
		//session.invalidate();
		//response.sendRedirect("index.jsp" );
		//return ;
		//}


		////-- ProductlistBeanId.Adp = productlistFaced.getProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id(), ProductlistBeanId ) ;
		ProductlistBeanId.Adp = productlistFaced.getSearchList("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id(), Long.parseLong(AuthorizationPageBeanId.getCatalog_id()) ,  ProductlistBeanId , AuthorizationPageBeanId ) ;
		productlistFaced.getQuantitySearch( ProductlistBeanId);
		ProductlistBeanId.co1Adp = productlistFaced.getCoOneSearchDirect("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id() , AuthorizationPageBeanId.getCatalog_id() ,  ProductlistBeanId , AuthorizationPageBeanId )  ;
		ProductlistBeanId.co2Adp = productlistFaced.getCoTwoSearchDirect("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id() , AuthorizationPageBeanId.getCatalog_id() , ProductlistBeanId , AuthorizationPageBeanId )  ;
		
		
		AuthorizationPageBeanId.setCatalogParent_id("" + productlistFaced.getCatalogParentId(AuthorizationPageBeanId));
		

		ProductlistBeanId.setSelect_currency_cd(productlistFaced.getXMLDBList("Productlist.jsp?currency_cd","currencies",ProductlistBeanId.getCurrency_cd(),"SELECT currency_cd , currency_desc  FROM currency  WHERE active = true") ) ;

		
	}



	
	public boolean isNumber(String tmp) {
		if (tmp == null)
			return false;
		String IntField = "0123456789";
		for (int i = 0; i < tmp.length(); i++) {

			if (IntField.indexOf(tmp.charAt(i)) == -1) {
				if (tmp.charAt(i) != '-' && i != 0)
					return false;
			}
		}
		return true;
	}

	public boolean isFloat(String tmp) {
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
	
	
}
