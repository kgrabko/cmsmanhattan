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
import com.cbsinc.cms.SoftPostBean;
import com.cbsinc.cms.faceds.ProductPostAllFaced;

public class ExtFilesProductPostAction implements IAction  
{

	
	
	
	//ResourceBundle resources = null ;
	ProductPostAllFaced productPostAllFaced ;

	
	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		action( request,  response,  servletContext) ;
		HttpSession session = request.getSession();
	    SoftPostBean SoftPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");
	    AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
	    //AuthorizationPageBeanId.getLastProductId();
		if ( request.getParameter("bigimage_id") == null ) {  SoftPostBeanId.setBigimage_id( "-1" ); }
		if ( request.getParameter("image_id") == null ) {  SoftPostBeanId.setImage_id( "-1" ); }
		SoftPostBeanId.setSite_id(AuthorizationPageBeanId.getSite_id());
		if(SoftPostBeanId.getSoft_id().compareTo("-1")==0)
		{
			if(productPostAllFaced.isLimmitPostedMessages(AuthorizationPageBeanId,true))
			{  
			  AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("global_has_limmit_forsite")); 
			  response.sendRedirect("PolicyManager.jsp");
			  return ;
			}
			productPostAllFaced.insertRowWithParent("" + AuthorizationPageBeanId.getLastProductId(),SoftPostBeanId,AuthorizationPageBeanId);
		}
		else productPostAllFaced.updateRowWithParent("" + AuthorizationPageBeanId.getLastProductId(),SoftPostBeanId,AuthorizationPageBeanId);
//		response.sendRedirect("Productlist.jsp?offset=" + 0 + "&catalog_id=" + AuthorizationPageBeanId.getCatalog_id()  );
		response.sendRedirect("Policy.jsp?policy_byproductid=" + AuthorizationPageBeanId.getLastProductId());
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
	
		ProductPostAllFaced productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced();	
		
		action( request,  response,  servletContext) ;
		HttpSession session = request.getSession();
	    SoftPostBean SoftPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");

//		if( request.getParameter("product_id") == null )
//		{
//			SoftPostBeanId.setSoft_id("-1");
//			return ;
//		}
			
	    AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
	    productPostAllFaced.initPage(request.getParameter("product_id"),  SoftPostBeanId , AuthorizationPageBeanId);
	    //		if insert and limmit not add message
	    if(productPostAllFaced.isLimmitPostedMessages(AuthorizationPageBeanId,false) && SoftPostBeanId.getSoft_id().compareTo("-1")==0 )
		 {
			 AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("global_has_limmit_forsite")); 
			 response.sendRedirect("PolicyManager.jsp");
			 return ;
		 }

	}

	
	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		
		SoftPostBean SoftPostBeanId  ;
		//Catalog_listBean catalog_listBean ;
		//PolicyBean policyBeanId ;
		
		HttpSession session = request.getSession();
		SoftPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");
		//catalog_listBean = (Catalog_listBean)session.getAttribute("catalog_listBean");
		//policyBeanId = (PolicyBean)session.getAttribute("policyBeanId");
		AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced();
		
		if(SoftPostBeanId == null  ||  AuthorizationPageBeanId == null || productPostAllFaced == null  ) return ;
		//if( resources == null )  resources = PropertyResourceBundle.getBundle("localization", response.getLocale());
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setDateHeader ("Expires", 0); 
		
		
		String   softname  = request.getParameter("softname");
		if ( softname != null ) {  SoftPostBeanId.setStrSoftName( softname ); }
 


		///if( policyBeanId.getCatalog_id() != null )AuthorizationPageBeanId.setCatalog_id( policyBeanId.getCatalog_id()) ;
			
		//String catalog_id  = request.getParameter("catalog_id") ;
		//if(catalog_id  != null){ SoftPostBeanId.setCatalog_id( catalog_id) ; }

		
		
		
		if(request.getParameter("type_id")  != null){ SoftPostBeanId.setType_id( request.getParameter("type_id")) ; }


		String softcost  = request.getParameter("softcost") ;
		if(softcost  != null){ SoftPostBeanId.setStrSoftCost( softcost) ; }

		String currency_id  = request.getParameter("currency_id") ;
		if(currency_id  != null){ SoftPostBeanId.setStrCurrency( currency_id) ; }

		String   description  = request.getParameter("description");
		if ( description != null ) {  SoftPostBeanId.setStrSoftDescription( description ); }


		String   fulldescription  = request.getParameter("fulldescription");
		if ( fulldescription != null ) {  SoftPostBeanId.setProduct_fulldescription( fulldescription ); }


		String   imagename  = request.getParameter("imagename");
		if ( imagename != null ) {  SoftPostBeanId.setImgname( imagename ); }


		String   image_id  = request.getParameter("image_id");
		if ( image_id != null ) {  SoftPostBeanId.setImage_id( image_id ); }

		if ( request.getParameter("portlettype_id") != null ) {  SoftPostBeanId.setPortlettype_id( request.getParameter("portlettype_id")); }

		String   filename  = request.getParameter("filename");
		if ( filename != null ) {  SoftPostBeanId.setSample( filename ); }
		else{ SoftPostBeanId.setSample(""); }
		filename = null ;



		String   bigimagename  = request.getParameter("bigimagename");
		if ( bigimagename != null ) {  SoftPostBeanId.setBigimgname( bigimagename ); }


		String   bigimage_id  = request.getParameter("bigimage_id");
		if ( bigimage_id != null ) {  SoftPostBeanId.setBigimage_id( bigimage_id ); }


		if( request.getParameter("salelogic_id") !=null ) SoftPostBeanId.setProgname_id( request.getParameter("salelogic_id"));

		if( AuthorizationPageBeanId.getIntUserID() == 0 ){
			AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("session_time_out"));
		response.sendRedirect("Authorization.jsp" );
		}
		else SoftPostBeanId.setUser_id("" + AuthorizationPageBeanId.getIntUserID()) ;

		if( AuthorizationPageBeanId.getIntLevelUp() != 2 ){
			AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("post_message_notaccess_admin"));
		response.sendRedirect("Authorization.jsp" );
		}
	
	}
	
}
