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
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.faceds.ProductPostAllFaced;


public class BlogExtProductPostAction implements IAction  
{

	
	

	//ResourceBundle resources = null ;
	ProductPostAllFaced productPostAllFaced ;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{

		
		
		HttpSession session = request.getSession();
		String gen_code  = (String)request.getSession().getAttribute("gen_number");
		SoftPostBean SoftPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");
		AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		if(SoftPostBeanId == null || AuthorizationPageBeanId == null || gen_code == null  ) return ;
		
		String path = ((HttpServletRequest) request).getRequestURI() ;
		if ( request.getParameter("gen_number") != null )
		{
			String val1 = request.getParameter("gen_number").trim() ;
			
			if(!val1.equals(gen_code.trim())) 
			{
			AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("wrong_gen_number"));
			response.sendRedirect(path);
			return ;
			}
		}
		else
		{
			AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("wrong_gen_number"));
			response.sendRedirect(path);
			return ;
			//request.
		}
		
		action( request,  response,  servletContext) ;
		
		if ( request.getParameter("bigimage_id") == null ) {  SoftPostBeanId.setBigimage_id( "-1" ); }
		if ( request.getParameter("image_id") == null ) {  SoftPostBeanId.setImage_id( "-1" ); }
			
		SoftPostBeanId.setSite_id(AuthorizationPageBeanId.getSite_id());
		if(SoftPostBeanId.getSoft_id().compareTo("-1")==0) productPostAllFaced.insertRowWithParent("" +AuthorizationPageBeanId.getLastProductId(),SoftPostBeanId,AuthorizationPageBeanId);
		else productPostAllFaced.updateRowWithParent(""+AuthorizationPageBeanId.getLastProductId(),SoftPostBeanId, AuthorizationPageBeanId);
		//response.sendRedirect("Productlist.jsp?offset=" + 0 + "&catalog_id=" + SoftPostBeanId.getCatalog_id()  );
		response.sendRedirect("Policy.jsp" );
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception {
		
		ProductPostAllFaced productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced();	
		HttpSession session = request.getSession();
		SoftPostBean SoftPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");
		AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");

		action( request,  response,  servletContext) ;
		productPostAllFaced.initPage(request.getParameter("product_id"),  SoftPostBeanId , AuthorizationPageBeanId);

		if( AuthorizationPageBeanId.getIntLevelUp() == 2 ){
		
		boolean jsf_admin = false ;
		AuthorizationPageFaced  authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();
		String jsf_admin_key =  authorizationPageFaced.getResources_cms_settings().getString("jsf_admin") ;
		if( jsf_admin_key == null || jsf_admin_key.equals("") ) jsf_admin = false ;
		jsf_admin_key = jsf_admin_key.trim() ;
		jsf_admin =  jsf_admin_key.equals("true") ;
		SoftPostBeanId.setNameOfPage("BlogExtProductPost.jsp");
		if(jsf_admin) response.sendRedirect("admin.jsf") ; 
		
		}
	}



	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{

		
		AuthorizationPageBean AuthorizationPageBeanId ;
		HttpSession session ;
		SoftPostBean SoftPostBeanId = null  ;
		session = request.getSession();
		
		AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced();
		SoftPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");
		if(SoftPostBeanId == null || AuthorizationPageBeanId == null || productPostAllFaced == null  ) return ;
	
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setDateHeader ("Expires", 0); 
		
		//if( resources == null )  resources = PropertyResourceBundle.getBundle("localization", response.getLocale());
		
		String   softname  = request.getParameter("softname");
		if ( softname != null ) {  SoftPostBeanId.setStrSoftName( softname ); }


		String catalog_id  = request.getParameter("catalog_id") ;
		if(catalog_id  != null){ AuthorizationPageBeanId.setCatalog_id( catalog_id) ; }

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
		//post_forum_notaccess
		if( AuthorizationPageBeanId.getIntUserID() == 0 ){
		AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("session_time_out"));
		response.sendRedirect("Policy.jsp" );
		return ;
		}
		else SoftPostBeanId.setUser_id("" + AuthorizationPageBeanId.getIntUserID()) ;

		if( AuthorizationPageBeanId.getIntLevelUp() == 0 ){
		AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("post_forum_notaccess"));
		response.sendRedirect("Policy.jsp" );
		return ;
		}

		if( AuthorizationPageBeanId.getStrLogin().compareTo("user") == 0 )
		{
			AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("post_forum_notaccess"));
			response.sendRedirect("Policy.jsp" );
			return ;
		}

		
	}


}
