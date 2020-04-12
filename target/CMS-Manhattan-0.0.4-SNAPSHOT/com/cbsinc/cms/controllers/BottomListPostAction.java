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
import com.cbsinc.cms.Catalog_addBean;
import com.cbsinc.cms.Catalog_editBean;
import com.cbsinc.cms.Catalog_listBean;
import com.cbsinc.cms.SoftPostBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.faceds.ProductPostAllFaced;


public class BottomListPostAction implements IAction   
{
	
	

    //ResourceBundle resources = null ;
	ProductPostAllFaced productPostAllFaced ;
	transient ResourceBundle setup_resources = null ;

	public BottomListPostAction()
	{

		if( setup_resources == null )  setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources");

		
	}
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		action( request,  response,  servletContext) ;
		HttpSession session = request.getSession();
		SoftPostBean SoftPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");
	    AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		
		if( request.getParameter("action") != null)
		{
		 SoftPostBeanId.setAction(request.getParameter("action"));	

		 if( SoftPostBeanId.getAction().compareTo("save") == 0 )
		 {

			   if ( request.getParameter("bigimage_id") == null ) {  SoftPostBeanId.setBigimage_id( "-1" ); }
			   if ( request.getParameter("image_id") == null ) {  SoftPostBeanId.setImage_id( "-1" ); }
					
			   SoftPostBeanId.setSite_id(AuthorizationPageBeanId.getSite_id());
				
			   SoftPostBeanId.setStrSoftDescription("");
			   if(SoftPostBeanId.getSoft_id().compareTo("-1")==0)
			   {
				   if(productPostAllFaced.isLimmitPostedMessages(AuthorizationPageBeanId,true))
				   {  
					  AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("global_has_limmit_forsite")); 
					  response.sendRedirect("PostManager.jsp");
					  return ;
				   }
				   
				   productPostAllFaced.saveDescSoft(SoftPostBeanId, AuthorizationPageBeanId);
				   SoftPostBeanId.setAction("");	
				   response.sendRedirect("Productlist.jsp?offset=" + 0  );
			   }
			   else 
			   {
				   productPostAllFaced.updateDescSoft(SoftPostBeanId,AuthorizationPageBeanId);
				   SoftPostBeanId.setAction("");	
				   response.sendRedirect("Productlist.jsp?offset=" +  AuthorizationPageBeanId.getOffsetLastPage() + "&catalog_id=" + AuthorizationPageBeanId.getCatalog_id()  );
			   }
		 }
		 
		} else 	 SoftPostBeanId.setAction("");	 
		
		
		
	}
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		ProductPostAllFaced productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced();	
	    HttpSession session = request.getSession();
	    SoftPostBean SoftPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");
	    AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");

	    action( request,  response,  servletContext) ;
		productPostAllFaced.initPage(request.getParameter("product_id"),  SoftPostBeanId , AuthorizationPageBeanId);
		//		if insert and limmit not add message
	    if(productPostAllFaced.isLimmitPostedMessages(AuthorizationPageBeanId,false) && SoftPostBeanId.getSoft_id().compareTo("-1")==0 )
		 {
			 AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("global_has_limmit_forsite")); 
			 response.sendRedirect("PostManager.jsp");
			 return ;
		 }



		if( request.getParameter("action") != null)
		{

		 SoftPostBeanId.setAction(request.getParameter("action"));	
		 
		} else 	 SoftPostBeanId.setAction("");	 
		
	   		
		boolean jsf_admin = false ;
		AuthorizationPageFaced  authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();
		String jsf_admin_key =  authorizationPageFaced.getResources_cms_settings().getString("jsf_admin") ;
		if( jsf_admin_key == null || jsf_admin_key.equals("") ) jsf_admin = false ;
		jsf_admin_key = jsf_admin_key.trim() ;
		jsf_admin =  jsf_admin_key.equals("true") ;
		SoftPostBeanId.setNameOfPage("BottomListPost.jsp");
		if(jsf_admin) response.sendRedirect("admin.jsf") ; 
	
	}

	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		
		SoftPostBean softPostBeanId  ;
		Catalog_listBean catalog_listBean ;
		AuthorizationPageBean AuthorizationPageBeanId ;
		HttpSession session ;

		session = request.getSession();
		softPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");
		catalog_listBean = (Catalog_listBean)session.getAttribute("catalog_listBean");
		AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced();
		//if( resources == null )  resources = PropertyResourceBundle.getBundle("localization", response.getLocale());


		
		if(softPostBeanId == null || catalog_listBean == null ||  AuthorizationPageBeanId == null || productPostAllFaced == null  ) return ;
	
		
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setDateHeader ("Expires", 0);
		
//		 Start Novigator ---
		//catalog_listBean.setSite_id(AuthorizationPageBeanId.getSite_id());
		//catalog_listBean.setIntLevelUp(AuthorizationPageBeanId.getIntLevelUp());
		if( request.getParameter("parent_id") != null)
		{
////		--catalog_listBean.setParent_id(request.getParameter("parent_id"));
			//AuthorizationPageBeanId.setCatalog_id(request.getParameter("parent_id"));
			AuthorizationPageBeanId.setCatalogParent_id(request.getParameter("parent_id"));
		}

		if(request.getParameter("type_id")  != null){ softPostBeanId.setType_id( request.getParameter("type_id")) ; }

		if( request.getParameter("row") != null)
		{
		int index =  catalog_listBean.stringToInt(request.getParameter("row")) ;
		catalog_listBean.setIndx_select(index);
		}
		if( request.getParameter("del") != null)
		{
		int index =  catalog_listBean.stringToInt(request.getParameter("del")) ;
		String catalog_id = catalog_listBean.rows[index][0] ;
		if(catalog_id != null)catalog_listBean.delete(catalog_id,AuthorizationPageBeanId) ;
		request.setAttribute("del",null);
		}
		if( request.getParameter("offset") != null)
		{
		catalog_listBean.setOffset(  catalog_listBean.stringToInt(request.getParameter("offset")));
		}
//		 End Novigator ---



		if( request.getParameter("creteria1_id") !=null ) softPostBeanId.setCreteria1_id( request.getParameter("creteria1_id"));
		if( request.getParameter("creteria2_id") !=null ) softPostBeanId.setCreteria2_id( request.getParameter("creteria2_id"));
		if( request.getParameter("creteria3_id") !=null ) softPostBeanId.setCreteria3_id( request.getParameter("creteria3_id"));
		if( request.getParameter("creteria4_id") !=null ) softPostBeanId.setCreteria4_id( request.getParameter("creteria4_id"));
		if( request.getParameter("creteria5_id") !=null ) softPostBeanId.setCreteria5_id( request.getParameter("creteria5_id"));
		if( request.getParameter("creteria6_id") !=null ) softPostBeanId.setCreteria6_id( request.getParameter("creteria6_id"));
		if( request.getParameter("creteria7_id") !=null ) softPostBeanId.setCreteria7_id( request.getParameter("creteria7_id"));
		if( request.getParameter("creteria8_id") !=null ) softPostBeanId.setCreteria8_id( request.getParameter("creteria8_id"));
		if( request.getParameter("creteria9_id") !=null ) softPostBeanId.setCreteria9_id( request.getParameter("creteria9_id"));
		if( request.getParameter("creteria10_id") !=null ) softPostBeanId.setCreteria10_id( request.getParameter("creteria10_id"));
//		if( request.getParameter("save") !=null ) 
//		{
//			if(request.getParameter("save").compareTo("true")== 0) SoftPostBeanId.setSave( request.getParameter("save"));
//			else SoftPostBeanId.setSave("false"); 
//		}
//		else SoftPostBeanId.setSave("false"); 
		
		if( request.getParameter("insert") !=null ) 
		{
			if(request.getParameter("insert").compareTo("true")== 0) softPostBeanId.setSoft_id("-1") ;

		}

		


		String   softname  = request.getParameter("softname");
		if ( softname != null ) {  softPostBeanId.setStrSoftName( softname ); }


		String catalog_id  = request.getParameter("catalog_id") ;
		if(catalog_id  != null){ AuthorizationPageBeanId.setCatalog_id( catalog_id) ; }

		if(request.getParameter("type_id")  != null){ softPostBeanId.setType_id( request.getParameter("type_id")) ; }

		String softcost  = request.getParameter("softcost") ;
		if(softcost  != null){ softPostBeanId.setStrSoftCost( softcost) ; }

		String currency_id  = request.getParameter("currency_id") ;
		if(currency_id  != null){ softPostBeanId.setStrCurrency( currency_id) ; }

		String   description  = request.getParameter("description");
		if ( description != null ) {  softPostBeanId.setStrSoftDescription( description ); }


		String   fulldescription  = request.getParameter("fulldescription");
		if ( fulldescription != null ) {  softPostBeanId.setProduct_fulldescription( fulldescription ); }


		String   imagename  = request.getParameter("imagename");
		if ( imagename != null ) {  softPostBeanId.setImgname( imagename ); }


		String   image_id  = request.getParameter("image_id");
		if ( image_id != null ) {  softPostBeanId.setImage_id( image_id ); }
//		else SoftPostBeanId.setImage_id( "-1" );

		if ( request.getParameter("portlettype_id") != null ) {  softPostBeanId.setPortlettype_id( request.getParameter("portlettype_id")); }

		String   filename  = request.getParameter("filename");
		if ( filename != null ) {  softPostBeanId.setFilename( filename ); }


		String   bigimagename  = request.getParameter("bigimagename");
		if ( bigimagename != null ) {  softPostBeanId.setBigimgname( bigimagename ); }


		String   bigimage_id  = request.getParameter("bigimage_id");
		if ( bigimage_id != null ) {  softPostBeanId.setBigimage_id( bigimage_id ); }
//		else SoftPostBeanId.setBigimage_id( "-1" );


		if( request.getParameter("salelogic_id") !=null ) softPostBeanId.setProgname_id( request.getParameter("salelogic_id"));

		if( AuthorizationPageBeanId.getIntUserID() == 0 ){
		AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("session_time_out"));
		response.sendRedirect("Authorization.jsp" );
		}
		else softPostBeanId.setUser_id("" + AuthorizationPageBeanId.getIntUserID()) ;

		if( AuthorizationPageBeanId.getIntLevelUp() != 2 ){
		AuthorizationPageBeanId.setStrMessage("You don't have access to add position , send mail to grabko@mail.ru for access") ;
		response.sendRedirect("Authorization.jsp" );
		}
	
	}
		
	
}
