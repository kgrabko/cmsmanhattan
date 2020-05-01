package com.cbsinc.cms.controllers;

import java.util.Map;

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
import com.cbsinc.cms.CatalogAddBean;
import com.cbsinc.cms.CatalogEditBean;
import com.cbsinc.cms.CatalogListBean;
import com.cbsinc.cms.ProductlistBean;
import com.cbsinc.cms.PublisherBean;
import com.cbsinc.cms.faceds.ProductPostAllFaced;
import com.cbsinc.cms.faceds.ProductlistFaced;
import com.cbsinc.cms.jms.controllers.MessageSender;
import com.cbsinc.cms.jms.controllers.Message;
import com.cbsinc.cms.jms.controllers.SendMailMessageBean;

public class ProductUserPostMusicAction implements IAction 
{

	PublisherBean publisherBeanId  ;
	CatalogListBean catalogListBeanId ;
	CatalogEditBean catalogEditBeanId ;
	CatalogAddBean catalogAddBeanId ;
	AuthorizationPageBean authorizationPageBeanId ;
	HttpSession session ;
	Map messageMail ;
	ProductPostAllFaced productPostAllFaced ;
	String gen_code = "" ;
	ProductlistBean  productlistBeanId ;
	String notselected = "" ;
	

	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception {

		action( request,  response,  servletContext) ;
		
			if ( request.getParameter("gen_number") != null )
			{
				String val1 = request.getParameter("gen_number").trim() ;
				if(!val1.equals(gen_code.trim())) 
				{
					authorizationPageBeanId.setStrMessage(authorizationPageBeanId.getLocalization(servletContext).getString("wrong_gen_number"));
					response.sendRedirect("ProductUserPostMusic.jsp" );
				}
			}
			else
			{
				authorizationPageBeanId.setStrMessage(authorizationPageBeanId.getLocalization(servletContext).getString("wrong_gen_number"));
				response.sendRedirect("ProductUserPostMusic.jsp" );
			}

			
		if ( request.getParameter("bigimage_id") == null ) {  publisherBeanId.setBigimage_id( "-1" ); }
		if ( request.getParameter("image_id") == null ) {  publisherBeanId.setImage_id( "-1" ); }	
		publisherBeanId.setSite_id(authorizationPageBeanId.getSite_id());
		if(publisherBeanId.getSoft_id().compareTo("-1")==0) 
		{
			if(productPostAllFaced.isLimmitPostedMessages(authorizationPageBeanId,true))
			 {
				 authorizationPageBeanId.setStrMessage(authorizationPageBeanId.getLocalization(servletContext).getString("global_has_limmit_forsite")); 
				 response.sendRedirect("PostManager.jsp");
				 return ;
			 }
			
			productPostAllFaced.saveInformationWithCheck(publisherBeanId,authorizationPageBeanId);
		}
		else productPostAllFaced.updateInformationWithCheck(publisherBeanId,authorizationPageBeanId);

		messageMail.clear();
		messageMail.put("@FirstName", authorizationPageBeanId.getStrFirstName() ) ;
		messageMail.put("@LastName", authorizationPageBeanId.getStrLastName() ) ;
		messageMail.put("@Site", authorizationPageBeanId.getSite_dir() ) ;

		String sitePath = (String)request.getSession().getAttribute("site_path");
		String addinfo = sitePath +"\\mail\\addinfo.txt" ;
		String attachFile =  sitePath + "\\mail\\info.txt" ;
		//System.out.println("rezalt: " + sendMailAgent.putMessageInPool(null ,"Has added new info on site ",   addinfo   , attachFile, messageMail  ) ) ;

		MessageSender mqSender = new MessageSender( request.getSession(),SendMailMessageBean.messageQuery) ;
		Message message = new Message();
		message.put("to" , null  ) ;
		message.put("subject" , "Has added new info on site " ) ;
		message.put("pathmessage" , addinfo ) ;
		message.put("attachFile" , attachFile ) ;
		message.put("fields" , messageMail ) ;
		mqSender.send(message);
		
		response.sendRedirect("Productlist.jsp?offset=" + 0 + "&catalog_id=" + authorizationPageBeanId.getCatalog_id()  );
		
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		ProductPostAllFaced productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced().get();	
	    action( request,  response,  servletContext) ;
		productPostAllFaced.initPage(request.getParameter("product_id"),  publisherBeanId , authorizationPageBeanId);
		if(productPostAllFaced.isLimmitPostedMessages(authorizationPageBeanId,false) && publisherBeanId.getSoft_id().compareTo("-1")==0 )
		 {
			 authorizationPageBeanId.setStrMessage(authorizationPageBeanId.getLocalization(servletContext).getString("global_has_limmit_forsite")); 
			 response.sendRedirect("PostManager.jsp");
			 return ;
		 }

	}

	
	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{

		session = request.getSession();
		gen_code = (String)request.getSession().getAttribute("gen_number");
		publisherBeanId = (PublisherBean)session.getAttribute("publisherBeanId");
		catalogListBeanId = (CatalogListBean)session.getAttribute("catalogListBeanId");
		catalogEditBeanId = (CatalogEditBean)session.getAttribute("catalogEditBeanId");
		catalogAddBeanId = (CatalogAddBean)session.getAttribute("catalogAddBeanId");
		authorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("authorizationPageBeanId");
		messageMail = (Map)session.getAttribute("messageMail");
		productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced().get();
		productlistBeanId = (ProductlistBean)session.getAttribute("ProductlistBeanId");
		if(publisherBeanId == null || catalogListBeanId == null || catalogEditBeanId == null || catalogAddBeanId == null || authorizationPageBeanId == null || messageMail == null ||  productPostAllFaced == null ) return ;
	
		//if( resources == null )  resources = PropertyResourceBundle.getBundle("localization", response.getLocale());
		if(notselected.length() == 0 ) notselected = authorizationPageBeanId.getLocalization(servletContext).getString("notselected") ;
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
		
//		 Start Novigator ---
		
		
		
		if( request.getParameter("parent_id") != null)
		{
			authorizationPageBeanId.setCatalogParent_id(request.getParameter("parent_id"));
		}

		if( request.getParameter("row") != null)
		{
		int index =  catalogListBeanId.stringToInt(request.getParameter("row")) ;
		catalogListBeanId.setIndx_select(index);
		}
		if( request.getParameter("del") != null)
		{
		int index =  catalogListBeanId.stringToInt(request.getParameter("del")) ;
		String catalog_id = catalogListBeanId.rows[index][0] ;
		if(catalog_id != null)catalogListBeanId.delete(catalog_id,authorizationPageBeanId) ;
		request.setAttribute("del",null);
		}
		if( request.getParameter("offset") != null)
		{
		catalogListBeanId.setOffset(  catalogListBeanId.stringToInt(request.getParameter("offset")));
		}
//		 End Novigator ---


		if( request.getParameter("creteria1_id") !=null ) publisherBeanId.setCreteria1_id( request.getParameter("creteria1_id"));
		if( request.getParameter("creteria2_id") !=null ) publisherBeanId.setCreteria2_id( request.getParameter("creteria2_id"));
		if( request.getParameter("creteria3_id") !=null ) publisherBeanId.setCreteria3_id( request.getParameter("creteria3_id"));
		if( request.getParameter("creteria4_id") !=null ) publisherBeanId.setCreteria4_id( request.getParameter("creteria4_id"));
		if( request.getParameter("creteria5_id") !=null ) publisherBeanId.setCreteria5_id( request.getParameter("creteria5_id"));
		if( request.getParameter("creteria6_id") !=null ) publisherBeanId.setCreteria6_id( request.getParameter("creteria6_id"));
		if( request.getParameter("creteria7_id") !=null ) publisherBeanId.setCreteria7_id( request.getParameter("creteria7_id"));
		if( request.getParameter("creteria8_id") !=null ) publisherBeanId.setCreteria8_id( request.getParameter("creteria8_id"));
		if( request.getParameter("creteria9_id") !=null ) publisherBeanId.setCreteria9_id( request.getParameter("creteria9_id"));
		if( request.getParameter("creteria10_id") !=null ) publisherBeanId.setCreteria10_id( request.getParameter("creteria10_id"));


		String   softname  = request.getParameter("softname");
		if ( softname != null ) {  publisherBeanId.setStrSoftName( softname ); }

		String   softname2  = request.getParameter("softname2");
		if ( softname2 != null ) {  publisherBeanId.setStrSoftName2( softname2 ); }

		String   jsp_url  = request.getParameter("jsp_url");
		if ( jsp_url != null ) {  publisherBeanId.setJsp_url( jsp_url ); }
				
		String catalog_id  = request.getParameter("catalog_id") ;
		if(catalog_id  != null){ authorizationPageBeanId.setCatalog_id( catalog_id) ; }

		if(request.getParameter("type_id")  != null){ publisherBeanId.setType_id( request.getParameter("type_id")) ; }

		String softcost  = request.getParameter("softcost") ;
		if(softcost  != null){ publisherBeanId.setStrSoftCost( softcost) ; }

		String currency_id  = request.getParameter("currency_id") ;
		if(currency_id  != null){ publisherBeanId.setStrCurrency( currency_id) ; }

		String   description  = request.getParameter("description");
		if ( description != null ) {  publisherBeanId.setStrSoftDescription( description ); }


		String   fulldescription  = request.getParameter("fulldescription");
		if ( fulldescription != null ) {  publisherBeanId.setProduct_fulldescription( fulldescription ); }


		String   imagename  = request.getParameter("imagename");
		if ( imagename != null ) {  publisherBeanId.setImgname( imagename ); }


		String   image_id  = request.getParameter("image_id");
		if ( image_id != null ) {  publisherBeanId.setImage_id( image_id ); }

		if ( request.getParameter("portlettype_id") != null ) {  publisherBeanId.setPortlettype_id( request.getParameter("portlettype_id")); }

		String   file_id  = request.getParameter("file_id");
		if ( file_id != null ) {  publisherBeanId.setFile_id( file_id ); }

		String   filename  = request.getParameter("filename");
		if ( filename != null ) {  publisherBeanId.setFilename( filename ); }

		String   bigimagename  = request.getParameter("bigimagename");
		if ( bigimagename != null ) {  publisherBeanId.setBigimgname( bigimagename ); }


		String   bigimage_id  = request.getParameter("bigimage_id");
		if ( bigimage_id != null ) {  publisherBeanId.setBigimage_id( bigimage_id ); }


		if( request.getParameter("salelogic_id") !=null ) publisherBeanId.setProgname_id( request.getParameter("salelogic_id"));

		if( authorizationPageBeanId.getIntUserID() == 0 ){
			authorizationPageBeanId.setStrMessage(authorizationPageBeanId.getLocalization(servletContext).getString("session_time_out"));
		response.sendRedirect("Authorization.jsp" );
		}
		else publisherBeanId.setUser_id("" + authorizationPageBeanId.getIntUserID()) ;

		if( authorizationPageBeanId.getIntLevelUp() != 2 ){
			authorizationPageBeanId.setStrMessage(authorizationPageBeanId.getLocalization(servletContext).getString("post_message_notaccess_admin"));
		response.sendRedirect("Authorization.jsp" );
		}


	}
}
