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

public class Co2ProductPostUserAction implements IAction   
{

	boolean is_criteria_by_catalog = false ;
    //ResourceBundle resources = null ;
	ProductPostAllFaced productPostAllFaced ;
	transient ResourceBundle setup_resources = null ;

	public Co2ProductPostUserAction()
	{
		if( setup_resources == null )  setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources");
		is_criteria_by_catalog = setup_resources.getString("is_criteria_by_catalog").equals("true") ;
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		action( request,  response,  servletContext) ;
		HttpSession session = request.getSession();
		SoftPostBean SoftPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");
	    AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
	    Catalog_listBean catalog_listBean = (Catalog_listBean)session.getAttribute("catalog_listBean");
	    Catalog_editBean catalog_editBean = (Catalog_editBean)session.getAttribute("catalog_editBean");
	    Catalog_addBean catalog_addBean = (Catalog_addBean)session.getAttribute("catalog_addBean");
		
		//	POST
		if( request.getParameter("action") != null)
		{
		 SoftPostBeanId.setAction(request.getParameter("action"));	
		 
		 if( SoftPostBeanId.getAction().compareTo("add") == 0 )
		 {
			 //catalog_addBean.setParent_id(AuthorizationPageBeanId.getCatalogParent_id());
			 //catalog_addBean.setSite_id(AuthorizationPageBeanId.getSite_id());

			 if( request.getParameter("name") != null)
			 {
			 catalog_addBean.setName(  request.getParameter("name"));
			 }
			 else catalog_addBean.setName("");
				 
			 if(request.getMethod().toUpperCase().compareTo("POST") == 0)
			 {
			 catalog_addBean.addCatalog(AuthorizationPageBeanId);
			 SoftPostBeanId.setAction("");	
			 response.sendRedirect("ProductPostCre.jsp" );
			 return ;
			 }
		 }

		 if(  SoftPostBeanId.getAction().compareTo("edit") == 0 )
		 {


			 //catalog_editBean.setParent_id(catalog_listBean.getParent_id());
			 //catalog_editBean.setSite_id(AuthorizationPageBeanId.getSite_id());
			 if( request.getParameter("row") != null)
			 {
			 int index  =  catalog_listBean.stringToInt(request.getParameter("row")) ;
			 catalog_editBean.setIndx_select(index);
			 }
			 if( request.getParameter("name") != null)
			 {
			 catalog_editBean.setName(  request.getParameter("name"));
			 }

			 if( request.getParameter("catalog_id") != null)
			 {
				 AuthorizationPageBeanId.setCatalog_id(  request.getParameter("catalog_id"));
			 }

			 if(request.getMethod().toUpperCase().compareTo("POST") == 0)
			 {
			 catalog_editBean.editCatalog(AuthorizationPageBeanId);
			 SoftPostBeanId.setAction("");	
			 response.sendRedirect("ProductPostCre.jsp" );
			 return ;
			 }
			 
		  }
		 if( SoftPostBeanId.getAction().compareTo("save") == 0 )
		 {
			  
			   if ( request.getParameter("bigimage_id") == null ) {  SoftPostBeanId.setBigimage_id( "-1" ); }
			   if ( request.getParameter("image_id") == null ) {  SoftPostBeanId.setImage_id( "-1" ); }
					
			   SoftPostBeanId.setSite_id(AuthorizationPageBeanId.getSite_id());
				
			   if(SoftPostBeanId.getSoft_id().compareTo("-1")==0)
			   {
				   
				   if(productPostAllFaced.isLimmitPostedMessages(AuthorizationPageBeanId,true))
				   {
					 AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("global_has_limmit_forsite")); 
					 response.sendRedirect("PostManager.jsp");
					 return ;
				   }
				   
				   productPostAllFaced.saveDescSoft(SoftPostBeanId,AuthorizationPageBeanId);
				   SoftPostBeanId.setAction("");	
				   response.sendRedirect("Productlist.jsp?offset=" + 0 + "&catalog_id=" + AuthorizationPageBeanId.getCatalog_id()  );
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
	    //if( resources == null )  resources = PropertyResourceBundle.getBundle("localization", response.getLocale());
	    String notselected  = AuthorizationPageBeanId.getLocalization(servletContext).getString("notselected") ;
	    Catalog_listBean catalog_listBean = (Catalog_listBean)session.getAttribute("catalog_listBean");
	    Catalog_editBean catalog_editBean = (Catalog_editBean)session.getAttribute("catalog_editBean");
	    Catalog_addBean catalog_addBean = (Catalog_addBean)session.getAttribute("catalog_addBean");
		
	    action( request,  response,  servletContext) ;

		productPostAllFaced.initPage(request.getParameter("product_id"),  SoftPostBeanId , AuthorizationPageBeanId);
	    //		if insert and limmit not add message
	    if(productPostAllFaced.isLimmitPostedMessages(AuthorizationPageBeanId,false) && SoftPostBeanId.getSoft_id().compareTo("-1")==0 )
		 {
			 AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("global_has_limmit_forsite")); 
			 response.sendRedirect("PostManager.jsp");
			 return ;
		 }


		
		SoftPostBeanId.setCriteria1_label(productPostAllFaced.getOneLabel("select  label   from creteria1   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) ));
		SoftPostBeanId.setCriteria2_label(productPostAllFaced.getOneLabel("select  label   from creteria2   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) ));
		SoftPostBeanId.setCriteria3_label(productPostAllFaced.getOneLabel("select  label   from creteria3   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) ));
		SoftPostBeanId.setCriteria4_label(productPostAllFaced.getOneLabel("select  label   from creteria4   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) ));
		SoftPostBeanId.setCriteria5_label(productPostAllFaced.getOneLabel("select  label   from creteria5   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) ));
		SoftPostBeanId.setCriteria6_label(productPostAllFaced.getOneLabel("select  label   from creteria6   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) ));
		SoftPostBeanId.setCriteria7_label(productPostAllFaced.getOneLabel("select  label   from creteria7   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) ));
		SoftPostBeanId.setCriteria8_label(productPostAllFaced.getOneLabel("select  label   from creteria8   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) ));
		SoftPostBeanId.setCriteria9_label(productPostAllFaced.getOneLabel("select  label   from creteria9   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) ));
		SoftPostBeanId.setCriteria10_label(productPostAllFaced.getOneLabel("select  label   from creteria10   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) ));
		

		
		SoftPostBeanId.setSelect_creteria1_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria1_id",SoftPostBeanId.getCreteria1_id(), notselected ,  "select creteria1_id , name   from creteria1   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) )) ;
		SoftPostBeanId.setSelect_creteria2_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria2_id",SoftPostBeanId.getCreteria2_id(), notselected ,"select creteria2_id , name   from creteria2   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria1_id()+ " ) " )) ;
		SoftPostBeanId.setSelect_creteria3_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria3_id",SoftPostBeanId.getCreteria3_id(), notselected ,"select creteria3_id , name   from creteria3   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria2_id()+ " ) " )) ;
		SoftPostBeanId.setSelect_creteria4_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria4_id",SoftPostBeanId.getCreteria4_id(), notselected ,"select creteria4_id , name   from creteria4   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria3_id()+ " ) " )) ;
		SoftPostBeanId.setSelect_creteria5_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria5_id",SoftPostBeanId.getCreteria5_id(), notselected ,"select creteria5_id , name   from creteria5   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria4_id()+ " ) " )) ;
		SoftPostBeanId.setSelect_creteria6_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria6_id",SoftPostBeanId.getCreteria6_id(), notselected ,"select creteria6_id , name   from creteria6   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria5_id()+ " ) " )) ;
		SoftPostBeanId.setSelect_creteria7_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria7_id",SoftPostBeanId.getCreteria7_id(), notselected ,"select creteria7_id , name   from creteria7   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria6_id()+ " ) " )) ;
		SoftPostBeanId.setSelect_creteria8_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria8_id",SoftPostBeanId.getCreteria8_id(), notselected ,"select creteria8_id , name   from creteria8   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria7_id()+ " ) " )) ;
		SoftPostBeanId.setSelect_creteria9_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria9_id",SoftPostBeanId.getCreteria9_id(), notselected ,"select creteria9_id , name   from creteria9   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria8_id()+ " ) " )) ;
		SoftPostBeanId.setSelect_creteria10_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria10_id",SoftPostBeanId.getCreteria10_id(), notselected ,"select creteria10_id , name   from creteria10   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), is_criteria_by_catalog) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria9_id()+ " ) " )) ;

		if( request.getParameter("action") != null)
		{

		 SoftPostBeanId.setAction(request.getParameter("action"));	


		 if( SoftPostBeanId.getAction().compareTo("add") == 0 )
		 {
			 //catalog_addBean.setParent_id(catalog_listBean.getParent_id());
			 //catalog_addBean.setSite_id(AuthorizationPageBeanId.getSite_id());

			 if( request.getParameter("name") != null)
			 {
			 catalog_addBean.setName(  request.getParameter("name"));
			 }
			 else catalog_addBean.setName("");
				 
			 if(request.getMethod().toUpperCase().compareTo("POST") == 0)
			 {
			 catalog_addBean.addCatalog(AuthorizationPageBeanId);
			 SoftPostBeanId.setAction("");	
			 response.sendRedirect("ProductPostCre.jsp" );
			 return ;
			 }
		 }

		 if(  SoftPostBeanId.getAction().compareTo("edit") == 0 )
		 {


			 //catalog_editBean.setParent_id(catalog_listBean.getParent_id());
			 //catalog_editBean.setSite_id(AuthorizationPageBeanId.getSite_id());
			 if( request.getParameter("row") != null)
			 {
			 int index  =  catalog_listBean.stringToInt(request.getParameter("row")) ;
			 catalog_editBean.setIndx_select(index);
			 }
			 if( request.getParameter("name") != null)
			 {
			 catalog_editBean.setName(  request.getParameter("name"));
			 }

			 if( request.getParameter("catalog_id") != null)
			 {
				 AuthorizationPageBeanId.setCatalog_id(  request.getParameter("catalog_id"));
			 }

			 if(request.getMethod().toUpperCase().compareTo("POST") == 0)
			 {
			 catalog_editBean.editCatalog(AuthorizationPageBeanId);
			 SoftPostBeanId.setAction("");	
			 response.sendRedirect("ProductPostCre.jsp" );
			 return ;
			 }
			 
		  }
		 
		} else 	 SoftPostBeanId.setAction("");	 
		
		boolean jsf_admin = false ;
		AuthorizationPageFaced  authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();
		String jsf_admin_key =  authorizationPageFaced.getResources_cms_settings().getString("jsf_admin") ;
		if( jsf_admin_key == null || jsf_admin_key.equals("") ) jsf_admin = false ;
		jsf_admin_key = jsf_admin_key.trim() ;
		jsf_admin =  jsf_admin_key.equals("true") ;
		SoftPostBeanId.setNameOfPage("Co2ProductPost.jsp");
		if(jsf_admin) response.sendRedirect("admin.jsf") ; 
	}

	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		SoftPostBean SoftPostBeanId  ;
		Catalog_listBean catalog_listBean ;
		AuthorizationPageBean AuthorizationPageBeanId ;
		HttpSession session ;

		session = request.getSession();
		SoftPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");
		catalog_listBean = (Catalog_listBean)session.getAttribute("catalog_listBean");
		AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced();
		//if( resources == null )  resources = PropertyResourceBundle.getBundle("localization", response.getLocale());


		
		if(SoftPostBeanId == null || catalog_listBean == null ||  AuthorizationPageBeanId == null || productPostAllFaced == null  ) return ;
	
		
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

		if(request.getParameter("type_id")  != null){ SoftPostBeanId.setType_id( request.getParameter("type_id")) ; }

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





		if( request.getParameter("creteria1_id") !=null ) SoftPostBeanId.setCreteria1_id( request.getParameter("creteria1_id"));
		if( request.getParameter("creteria2_id") !=null ) SoftPostBeanId.setCreteria2_id( request.getParameter("creteria2_id"));
		if( request.getParameter("creteria3_id") !=null ) SoftPostBeanId.setCreteria3_id( request.getParameter("creteria3_id"));
		if( request.getParameter("creteria4_id") !=null ) SoftPostBeanId.setCreteria4_id( request.getParameter("creteria4_id"));
		if( request.getParameter("creteria5_id") !=null ) SoftPostBeanId.setCreteria5_id( request.getParameter("creteria5_id"));
		if( request.getParameter("creteria6_id") !=null ) SoftPostBeanId.setCreteria6_id( request.getParameter("creteria6_id"));
		if( request.getParameter("creteria7_id") !=null ) SoftPostBeanId.setCreteria7_id( request.getParameter("creteria7_id"));
		if( request.getParameter("creteria8_id") !=null ) SoftPostBeanId.setCreteria8_id( request.getParameter("creteria8_id"));
		if( request.getParameter("creteria9_id") !=null ) SoftPostBeanId.setCreteria9_id( request.getParameter("creteria9_id"));
		if( request.getParameter("creteria10_id") !=null ) SoftPostBeanId.setCreteria10_id( request.getParameter("creteria10_id"));
//		if( request.getParameter("save") !=null ) 
//		{
//			if(request.getParameter("save").compareTo("true")== 0) SoftPostBeanId.setSave( request.getParameter("save"));
//			else SoftPostBeanId.setSave("false"); 
//		}
//		else SoftPostBeanId.setSave("false"); 
		
		if( request.getParameter("insert") !=null ) 
		{
			if(request.getParameter("insert").compareTo("true")== 0) SoftPostBeanId.setSoft_id("-1") ;

		}

		


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
//		else SoftPostBeanId.setImage_id( "-1" );

		if ( request.getParameter("portlettype_id") != null ) {  SoftPostBeanId.setPortlettype_id( request.getParameter("portlettype_id")); }

		String   filename  = request.getParameter("filename");
		if ( filename != null ) {  SoftPostBeanId.setFilename( filename ); }


		String   bigimagename  = request.getParameter("bigimagename");
		if ( bigimagename != null ) {  SoftPostBeanId.setBigimgname( bigimagename ); }


		String   bigimage_id  = request.getParameter("bigimage_id");
		if ( bigimage_id != null ) {  SoftPostBeanId.setBigimage_id( bigimage_id ); }
//		else SoftPostBeanId.setBigimage_id( "-1" );


		if( request.getParameter("salelogic_id") !=null ) SoftPostBeanId.setProgname_id( request.getParameter("salelogic_id"));

		if( AuthorizationPageBeanId.getIntUserID() == 0 ){
			AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("session_time_out"));
		response.sendRedirect("Authorization.jsp" );
		}
		else SoftPostBeanId.setUser_id("" + AuthorizationPageBeanId.getIntUserID()) ;

		if( AuthorizationPageBeanId.getIntLevelUp() == 0 )
		{
			AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("post_message_notaccess_admin"));
			response.sendRedirect("Authorization.jsp" );
		}
	
	}
		

	
}
