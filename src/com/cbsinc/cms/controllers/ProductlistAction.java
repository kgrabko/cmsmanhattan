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
import java.util.List;
import java.util.Map;


import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.tomcat.util.http.mapper.Mapper;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.Catalog_listBean;
import com.cbsinc.cms.ProductlistBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.faceds.PolicyFaced;
import com.cbsinc.cms.faceds.ProductPostAllFaced;
import com.cbsinc.cms.faceds.ProductlistFaced;
import com.cbsinc.cms.jms.controllers.MQSender;
import com.cbsinc.cms.jms.controllers.Message;
import com.cbsinc.cms.jms.controllers.SendMailMessageBean;

public class ProductlistAction  implements IAction
{

	
	
	//CreateShopBean 	    createShopBeanId = new CreateShopBean();
	ProductPostAllFaced productPostAllFaced ;
	//ResourceBundle setup_resources = null ;
	//ResourceBundle localization = null ;
	AuthorizationPageFaced authorizationPageFaced ;
	//PolicyBean policyBeanId ;
	

	
	
	public ProductlistAction() 
	{
		//if( setup_resources == null )  setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources");
	}
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response , ServletContext  servletContext ) throws Exception
	{
		doGet( request,  response,  servletContext) ;
	}



	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception {
		ProductlistBean  ProductlistBeanId ;
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
	    ProductlistBeanId = new ProductlistBean();
	    request.setAttribute("ProductlistBeanId", ProductlistBeanId) ;
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
		    AuthorizationPageBeanId.setCatalogParent_id(request.getParameter("catalog_id"));

		}
		ProductlistBeanId.setIntLevelUp( AuthorizationPageBeanId.getIntLevelUp() ) ;

		// Open or closed dialog window 
		if( request.getParameter("dialog") != null)ProductlistBeanId.setDialog( request.getParameter("dialog"));
		if( request.getParameter("is_advanced_search_open") != null)ProductlistBeanId.setAdvancedSearchOpen( request.getParameter("is_advanced_search"));
		if( request.getParameter("is_forum_open") != null)ProductlistBeanId.setForumOpen( request.getParameter("is_forum_open"));
		
		if( request.getParameter("dayfrom_id") != null && isNumber(request.getParameter("dayfrom_id")) )AuthorizationPageBeanId.setDayfrom_id( Integer.parseInt(request.getParameter("dayfrom_id")));
		if( request.getParameter("mountfrom_id") != null && isNumber(request.getParameter("mountfrom_id")) )AuthorizationPageBeanId.setMountfrom_id(Integer.parseInt( request.getParameter("mountfrom_id")));
		if( request.getParameter("yearfrom_id") != null && isNumber(request.getParameter("yearfrom_id")) )AuthorizationPageBeanId.setYearfrom_id(Integer.parseInt( request.getParameter("yearfrom_id")));
		
		if( request.getParameter("fromcost") != null && isFloat(request.getParameter("fromcost")) )AuthorizationPageBeanId.setStrFromCost( request.getParameter("fromcost").trim());
		if( request.getParameter("tocost") != null && isFloat(request.getParameter("tocost")) )AuthorizationPageBeanId.setStrToCost( request.getParameter("tocost").trim());

		if( request.getParameter("dayto_id") != null && isNumber(request.getParameter("dayto_id")) )AuthorizationPageBeanId.setDayto_id( Integer.parseInt(request.getParameter("dayto_id")));
		if( request.getParameter("mountto_id") != null && isNumber(request.getParameter("mountto_id")) )AuthorizationPageBeanId.setMountto_id(Integer.parseInt( request.getParameter("mountto_id")));
		if( request.getParameter("yearto_id") != null && isNumber(request.getParameter("yearto_id")) )AuthorizationPageBeanId.setYearto_id(Integer.parseInt( request.getParameter("yearto_id")));
			
		if( request.getParameter("action") !=null ) { ProductlistBeanId.setAction( request.getParameter("action"));  } 
		if( request.getParameter("creteria1_id") !=null  && isNumber(request.getParameter("creteria1_id")) ) AuthorizationPageBeanId.setStrCreteria1_id( request.getParameter("creteria1_id"));
		if( request.getParameter("creteria2_id") !=null  && isNumber(request.getParameter("creteria2_id")) ) AuthorizationPageBeanId.setStrCreteria2_id( request.getParameter("creteria2_id"));
		if( request.getParameter("creteria3_id") !=null  && isNumber(request.getParameter("creteria3_id")) ) AuthorizationPageBeanId.setStrCreteria3_id( request.getParameter("creteria3_id"));
		if( request.getParameter("creteria4_id") !=null  && isNumber(request.getParameter("creteria4_id")) ) AuthorizationPageBeanId.setStrCreteria4_id( request.getParameter("creteria4_id"));
		if( request.getParameter("creteria5_id") !=null  && isNumber(request.getParameter("creteria5_id")) ) AuthorizationPageBeanId.setStrCreteria5_id( request.getParameter("creteria5_id"));
		if( request.getParameter("creteria6_id") !=null && isNumber(request.getParameter("creteria6_id")) ) AuthorizationPageBeanId.setStrCreteria6_id( request.getParameter("creteria6_id"));
		if( request.getParameter("creteria7_id") !=null && isNumber(request.getParameter("creteria7_id")) ) AuthorizationPageBeanId.setStrCreteria7_id( request.getParameter("creteria7_id"));
		if( request.getParameter("creteria8_id") !=null && isNumber(request.getParameter("creteria8_id")) ) AuthorizationPageBeanId.setStrCreteria8_id( request.getParameter("creteria8_id"));
		if( request.getParameter("creteria9_id") !=null && isNumber(request.getParameter("creteria9_id")) ) AuthorizationPageBeanId.setStrCreteria9_id( request.getParameter("creteria9_id"));
		if( request.getParameter("creteria10_id") !=null && isNumber(request.getParameter("creteria10_id")) ) AuthorizationPageBeanId.setStrCreteria10_id( request.getParameter("creteria10_id"));
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
		        	  if(request.getParameter("element").compareTo("product") == 0 ){  response.sendRedirect("ProductPostCre.jsp?product_id="+request.getParameter("product_id")+ "&parent_id=" + AuthorizationPageBeanId.getCatalog_id());   return ;}
		        	  if(request.getParameter("element").compareTo("news") == 0 ){   response.sendRedirect("NewsBlockPostCre.jsp?product_id="+request.getParameter("product_id")+ "&parent_id=" + AuthorizationPageBeanId.getCatalog_id());   return ;}
		        	  //if(request.getParameter("element").compareTo("news") == 0 ) response.sendRedirect("ProductPost.jsp") ;
		        	  if(request.getParameter("element").compareTo("co1") == 0 ){  response.sendRedirect("Co1ProductPost.jsp?product_id="+request.getParameter("product_id") + "&parent_id=" + AuthorizationPageBeanId.getCatalog_id());   return ;}
		        	  if(request.getParameter("element").compareTo("co2") == 0 ){ response.sendRedirect("Co2ProductPost.jsp?product_id="+request.getParameter("product_id") + "&parent_id=" + AuthorizationPageBeanId.getCatalog_id() );   return ;}
		        	  if(request.getParameter("element").compareTo("bottom") == 0 ){  response.sendRedirect("BottomListPost.jsp?product_id="+request.getParameter("product_id"));   return ;}
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
			AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("you_can_not_to_create_shop"));
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
			authorizationPageFaced.getCreateShopBean().setSubject_site("internet shop");
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
			//messageMail.put("@Shop", "http://www.siteforyou.net/Productlist.jsp?site=" + authorizationPageFaced.getCreateShopBean().getSite_id() ) ;
			//messageMail.put("@Policy", "http://www.siteforyou.net/Policy.pdf"  ) ;
			
		
			
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
			
			
			// get current tomcat server, engine and context objects
			MBeanServer mBeanServer = MBeanServerFactory.findMBeanServer(null).get(0);
			ObjectName name = new ObjectName("Catalina", "type", "Server");
			Server server = (Server) mBeanServer.getAttribute(name,	"managedResource");
			Service[] services = server.findServices();
			StandardEngine engine = (StandardEngine) services[0].getContainer();
			StandardContext context = (StandardContext) engine.findChild(engine.getDefaultHost()).findChild(servletContext.getContextPath());
			Mapper mapper = context.getMapper();
			mapper.addHostAlias(engine.getDefaultHost(),authorizationPageFaced.getCreateShopBean().getHost());
			

			
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
			response.sendRedirect("Productlist.jsp?site=" + site_id + "&" + "locale=" + AuthorizationPageBeanId.getLocale() );
			//servletContext.getRequestDispatcher("/Productlist.jsp?site=" + site_id + "&" + "locale=" + AuthorizationPageBeanId.getLocale() ).forward( request,  response);
			return ;
		}
		
		//if( ProductlistBeanId.getAction().compareTo("logoff") == 0  )
		//{
		//session.invalidate();
		//response.sendRedirect("index.jsp" );
		//return ;
		//}


		////-- ProductlistBeanId.Adp = productlistFaced.getProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id(), ProductlistBeanId ) ;
		ProductlistBeanId.Adp = productlistFaced.getProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id(), Long.parseLong(AuthorizationPageBeanId.getCatalog_id()) ,  ProductlistBeanId , AuthorizationPageBeanId ) ;
		productlistFaced.getQuantityProducts( ProductlistBeanId);
		ProductlistBeanId.co1Adp = productlistFaced.getCoOneProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id() , AuthorizationPageBeanId.getCatalog_id() ,  ProductlistBeanId , AuthorizationPageBeanId )  ;
		ProductlistBeanId.co2Adp = productlistFaced.getCoTwoProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id() , AuthorizationPageBeanId.getCatalog_id() , ProductlistBeanId , AuthorizationPageBeanId )  ;
		ProductlistBeanId.newsAdp = productlistFaced.getNewslist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id(), AuthorizationPageBeanId ) ;
		ProductlistBeanId.blogExtAdp = productlistFaced.getBlogTopProductlist(AuthorizationPageBeanId.getSite_id() ,ProductlistBeanId , AuthorizationPageBeanId)  ;
	
		ProductlistBeanId.bottomAdp = productlistFaced.getBottomlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id() ,  AuthorizationPageBeanId )  ;

		
		AuthorizationPageBeanId.setCatalogParent_id("" + productlistFaced.getCatalogParentId(AuthorizationPageBeanId));
	
		ProductlistBeanId.setCriteria1_label(productlistFaced.getOneLabel("select  label   from creteria1   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) ));
		ProductlistBeanId.setCriteria2_label(productlistFaced.getOneLabel("select  label   from creteria2   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) ));
		ProductlistBeanId.setCriteria3_label(productlistFaced.getOneLabel("select  label   from creteria3   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) ));
		ProductlistBeanId.setCriteria4_label(productlistFaced.getOneLabel("select  label   from creteria4   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) ));
		ProductlistBeanId.setCriteria5_label(productlistFaced.getOneLabel("select  label   from creteria5   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) ));
		ProductlistBeanId.setCriteria6_label(productlistFaced.getOneLabel("select  label   from creteria6   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) ));
		ProductlistBeanId.setCriteria7_label(productlistFaced.getOneLabel("select  label   from creteria7   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) ));
		ProductlistBeanId.setCriteria8_label(productlistFaced.getOneLabel("select  label   from creteria8   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) ));
		ProductlistBeanId.setCriteria9_label(productlistFaced.getOneLabel("select  label   from creteria9   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) ));
		ProductlistBeanId.setCriteria10_label(productlistFaced.getOneLabel("select  label   from creteria10   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) ));
		

		ProductlistBeanId.setSelect_currency_cd(productlistFaced.getXMLDBList("Productlist.jsp?currency_cd","currencies",ProductlistBeanId.getCurrency_cd(),"SELECT currency_cd , currency_desc  FROM currency  WHERE active = true") ) ;
		ProductlistBeanId.setSelect_tree_catalog(productlistFaced.getTreeXMLDBList("Productlist.jsp?catalog_id","catalog", AuthorizationPageBeanId.getCatalog_id()  ,"select catalog_id , lable   from catalog   where  active = true and lang_id = " + AuthorizationPageBeanId.getLang_id()  +" and site_id = " + AuthorizationPageBeanId.getSite_id()  +" and parent_id = " + AuthorizationPageBeanId.getCatalogParent_id()  ,"select catalog_id , lable   from catalog   where  active = true and parent_id = " + AuthorizationPageBeanId.getCatalog_id()  ) ) ;
		
		ProductlistBeanId.setSelect_menu_catalog(productlistFaced.getMenuXMLDBList("Productlist.jsp?catalog_id","menu", AuthorizationPageBeanId.getCatalog_id()  ,"select catalog_id , lable , parent_id  from catalog   where  active = true and parent_id = 0 and site_id = " + AuthorizationPageBeanId.getSite_id()  +" and lang_id = " + AuthorizationPageBeanId.getLang_id()  +" or parent_id in (select catalog_id   from catalog   where  active = true and site_id = " + AuthorizationPageBeanId.getSite_id()  +"  and parent_id = 0 )" ) ) ;
		//StringBuilder query = new StringBuilder();
		//query.append("(select catalog_id , lable , parent_id , catalog_id as ref  from catalog   where  active = true and parent_id = 0 and site_id = ");
		//query.append(AuthorizationPageBeanId.getSite_id());
		//query.append(" and lang_id = ") ;
		//query.append(AuthorizationPageBeanId.getLang_id());
		//query.append(" ) union (select catalog_id , lable , parent_id , parent_id as ref  from catalog   where  parent_id in ( select catalog_id   from catalog   where  active = true and site_id = ") ;
		//query.append(AuthorizationPageBeanId.getSite_id());
		//query.append(" and lang_id = ") ;
		//query.append(AuthorizationPageBeanId.getLang_id()) ;
		//query.append(" and parent_id = 0 ))  ORDER BY ref " ) ;
		//ProductlistBeanId.setSelect_menu_catalog(productlistFaced.getMenuXMLDBList("Productlist.jsp?catalog_id","menu", AuthorizationPageBeanId.getCatalog_id()  ,query.toString() ) ) ;
		
		ProductlistBeanId.setSelect_creteria1_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria1_id","creteria1","" + AuthorizationPageBeanId.getCreteria1_id(), notselected ,  "select creteria1_id , name   from creteria1   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) )) ;
		ProductlistBeanId.setSelect_creteria2_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria2_id","creteria2","" +AuthorizationPageBeanId.getCreteria2_id(), notselected ,"select creteria2_id , name   from creteria2   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + AuthorizationPageBeanId.getCreteria1_id()+ " ) " )) ;
		ProductlistBeanId.setSelect_creteria3_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria3_id","creteria3","" +AuthorizationPageBeanId.getCreteria3_id(), notselected ,"select creteria3_id , name   from creteria3   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + AuthorizationPageBeanId.getCreteria2_id()+ " ) " )) ;
		ProductlistBeanId.setSelect_creteria4_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria4_id","creteria4","" +AuthorizationPageBeanId.getCreteria4_id(), notselected ,"select creteria4_id , name   from creteria4   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + AuthorizationPageBeanId.getCreteria3_id()+ " ) " )) ;
		ProductlistBeanId.setSelect_creteria5_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria5_id","creteria5","" +AuthorizationPageBeanId.getCreteria5_id(), notselected ,"select creteria5_id , name   from creteria5   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + AuthorizationPageBeanId.getCreteria4_id()+ " ) " )) ;
		ProductlistBeanId.setSelect_creteria6_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria6_id","creteria6","" +AuthorizationPageBeanId.getCreteria6_id(), notselected ,"select creteria6_id , name   from creteria6   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + AuthorizationPageBeanId.getCreteria5_id()+ " ) " )) ;
		ProductlistBeanId.setSelect_creteria7_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria7_id","creteria7","" +AuthorizationPageBeanId.getCreteria7_id(), notselected ,"select creteria7_id , name   from creteria7   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + AuthorizationPageBeanId.getCreteria6_id()+ " ) " )) ;
		ProductlistBeanId.setSelect_creteria8_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria8_id","creteria8","" +AuthorizationPageBeanId.getCreteria8_id(), notselected ,"select creteria8_id , name   from creteria8   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + AuthorizationPageBeanId.getCreteria7_id()+ " ) " )) ;
		ProductlistBeanId.setSelect_creteria9_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria9_id","creteria9","" +AuthorizationPageBeanId.getCreteria9_id(), notselected ,"select creteria9_id , name   from creteria9   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + AuthorizationPageBeanId.getCreteria8_id()+ " ) " )) ;
		ProductlistBeanId.setSelect_creteria10_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria10_id","creteria10","" +AuthorizationPageBeanId.getCreteria10_id(), notselected ,"select creteria10_id , name   from creteria10   where  active = true " + ProductlistBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + AuthorizationPageBeanId.getCreteria9_id()+ " ) " )) ;
		  
		ProductlistBeanId.setSelect_dayfrom_id(productlistFaced.getXMLListDateDay("Productlist.jsp?dayfrom_id","dayfrom","" +AuthorizationPageBeanId.getDayfrom_id())) ;  
		ProductlistBeanId.setSelect_mountfrom_id(productlistFaced.getXMLListDateMount("Productlist.jsp?mountfrom_id","mountfrom","" +AuthorizationPageBeanId.getMountfrom_id())) ;  
		ProductlistBeanId.setSelect_yearfrom_id(productlistFaced.getXMLListDateYear("Productlist.jsp?yearfrom_id","yearfrom","" +AuthorizationPageBeanId.getYearfrom_id())) ;
		
		ProductlistBeanId.setSelect_dayto_id(productlistFaced.getXMLListDateDay("Productlist.jsp?dayto_id","dayto","" +AuthorizationPageBeanId.getDayto_id())) ;
		ProductlistBeanId.setSelect_mountto_id(productlistFaced.getXMLListDateMount("Productlist.jsp?mountto_id","mountto","" +AuthorizationPageBeanId.getMountto_id())) ;
		ProductlistBeanId.setSelect_yearto_id(productlistFaced.getXMLListDateYear("Productlist.jsp?yearto_id","yearto","" +AuthorizationPageBeanId.getYearto_id())) ;

		
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
