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
import com.cbsinc.cms.Catalog_addBean;
import com.cbsinc.cms.Catalog_editBean;
import com.cbsinc.cms.Catalog_listBean;
import com.cbsinc.cms.SoftPostBean;
import com.cbsinc.cms.faceds.ProductPostAllFaced;
import com.cbsinc.cms.faceds.ProductlistFaced;
import com.cbsinc.cms.jms.controllers.MQSender;
import com.cbsinc.cms.jms.controllers.Message;
import com.cbsinc.cms.jms.controllers.SendMailMessageBean;


public class ProductUserPostVideoAction implements IAction 
{

	SoftPostBean SoftPostBeanId  ;
	Catalog_listBean catalog_listBean ;
	Catalog_editBean catalog_editBean ;
	Catalog_addBean catalog_addBean ;
	AuthorizationPageBean AuthorizationPageBeanId ;
	HttpSession session ;
	//ResourceBundle resources = null ;
	java.util.HashMap messageMail ;
	//SendMailAgent sendMailAgent ;
	ProductPostAllFaced productPostAllFaced ;
	String gen_code = "" ;
	String notselected = "" ;
	

	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception {

		action( request,  response,  servletContext) ;
		
		
			if ( request.getParameter("gen_number") != null )
			{
				String val1 = request.getParameter("gen_number").trim() ;
				if(!val1.equals(gen_code.trim())) 
				{
					AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("wrong_gen_number"));
					response.sendRedirect("ProductUserPostMusic.jsp" );
				}
			}
			else
			{
				AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("wrong_gen_number"));
				response.sendRedirect("ProductUserPostMusic.jsp" );
			}

			
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
			productPostAllFaced.saveInformationWithCheck(SoftPostBeanId,AuthorizationPageBeanId);
		}
		else productPostAllFaced.updateInformationWithCheck(SoftPostBeanId,AuthorizationPageBeanId);

		messageMail.clear();
		messageMail.put("@FirstName", AuthorizationPageBeanId.getStrFirstName() ) ;
		messageMail.put("@LastName", AuthorizationPageBeanId.getStrLastName() ) ;
		messageMail.put("@Site", AuthorizationPageBeanId.getSite_dir() ) ;

		String sitePath = (String)request.getSession().getAttribute("site_path");
		String addinfo = sitePath +"\\mail\\addinfo.txt" ;
		String attachFile =  sitePath + "\\mail\\info.txt" ;
		//System.out.println("rezalt: " + sendMailAgent.putMessageInPool(null ,"Has added new info on site ",   addinfo   , attachFile, messageMail  ) ) ;
		MQSender mqSender = new MQSender( request.getSession(),SendMailMessageBean.messageQuery) ;
		Message message = new Message();
		message.put("to" , null  ) ;
		message.put("subject" , "Has added new info on site " ) ;
		message.put("pathmessage" , addinfo ) ;
		message.put("attachFile" , attachFile ) ;
		message.put("fields" , messageMail ) ;
		mqSender.send(message);
		response.sendRedirect("Productlist.jsp?offset=" + 0 + "&catalog_id=" + AuthorizationPageBeanId.getCatalog_id()  );
		
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		ProductPostAllFaced productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced();	
	    ProductlistFaced productlistFaced = ServiceLocator.getInstance().getProductlistFaced();
		
		action( request,  response,  servletContext) ;
		productPostAllFaced.initPage(request.getParameter("product_id"),  SoftPostBeanId , AuthorizationPageBeanId);
		if(productPostAllFaced.isLimmitPostedMessages(AuthorizationPageBeanId,false))
		 {
			 AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("global_has_limmit_forsite")); 
			 response.sendRedirect("PostManager.jsp");
			 return ;
		 }


	}

	
	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		session = request.getSession();
		gen_code = (String)request.getSession().getAttribute("gen_number");
		SoftPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");
		catalog_listBean = (Catalog_listBean)session.getAttribute("catalog_listBean");
		catalog_editBean = (Catalog_editBean)session.getAttribute("catalog_editBean");
		catalog_addBean = (Catalog_addBean)session.getAttribute("catalog_addBean");
		AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		messageMail = (java.util.HashMap)session.getAttribute("messageMail");
		productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced();
		if(SoftPostBeanId == null || catalog_listBean == null || catalog_editBean == null || catalog_addBean == null || AuthorizationPageBeanId == null || messageMail == null || productPostAllFaced == null ) return ;
	
		//if( resources == null )  resources = PropertyResourceBundle.getBundle("localization", response.getLocale());
		if(notselected.length() == 0 ) notselected = AuthorizationPageBeanId.getLocalization(servletContext).getString("notselected") ;
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
		
//		 Start Novigator ---
		//catalog_listBean.setSite_id(AuthorizationPageBeanId.getSite_id());
		//catalog_listBean.setIntLevelUp(AuthorizationPageBeanId.getIntLevelUp());
		if( request.getParameter("parent_id") != null)
		{
////		--catalog_listBean.setParent_id(request.getParameter("parent_id"));
			//AuthorizationPageBeanId.setCatalog_id(request.getParameter("parent_id"));
			AuthorizationPageBeanId.setCatalogParent_id(request.getParameter("parent_id"));
		}

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


		String   softname  = request.getParameter("softname");
		if ( softname != null ) {  SoftPostBeanId.setStrSoftName( softname ); }


		String catalog_id  = request.getParameter("catalog_id") ;
		if(catalog_id  != null){ AuthorizationPageBeanId.setCatalog_id( catalog_id) ; }



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
		else SoftPostBeanId.setBigimgname( "-1" );

		String   bigimage_id  = request.getParameter("bigimage_id");
		if ( bigimage_id != null ) {  SoftPostBeanId.setBigimage_id( bigimage_id ); }


		if( request.getParameter("salelogic_id") !=null ) SoftPostBeanId.setProgname_id( request.getParameter("salelogic_id"));

		if( AuthorizationPageBeanId.getIntUserID() == 0 ){
			AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("session_time_out"));
		response.sendRedirect("Authorization.jsp" );
		}
		else SoftPostBeanId.setUser_id("" + AuthorizationPageBeanId.getIntUserID()) ;

		if( AuthorizationPageBeanId.getIntLevelUp() == 0 ){
			AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext).getString("post_message_notaccess_admin"));
			response.sendRedirect("Authorization.jsp" );
			}
		
	}

}
