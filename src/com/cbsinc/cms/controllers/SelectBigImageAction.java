package com.cbsinc.cms.controllers;

import java.util.Optional;

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
import com.cbsinc.cms.PublisherBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.faceds.ProductPostAllFaced;


public class SelectBigImageAction extends TemplateAction 
{


	@Override
	public void action(Optional<HttpServletRequest> requestOpts, Optional<HttpServletResponse> responseOpts,
			Optional<ServletContext> servletContextOpts) throws Exception {
		
		PublisherBean publisherBeanId = getPublisherBean().get() ;
		ProductPostAllFaced productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced().get();
		AuthorizationPageBean authorizationPageBeanId = getAuthorizationPageBean().get() ;
		StringBuffer sbuff = new StringBuffer(); 
		
		HttpServletResponse response = responseOpts.get() ;
		HttpServletRequest request  = requestOpts.get() ;

	  		request.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
			response.setHeader("Pragma","no-cache"); //HTTP 1.0
			response.setDateHeader ("Expires", 0); 
	  		
	        if( request.getParameter("bigimage_id") != null) 
	        {
		        String  bigimage_id  = request.getParameter("bigimage_id");
		        if ( bigimage_id != null ) {  publisherBeanId.setBigimage_id( bigimage_id );  productPostAllFaced.setBigImageNameByImage_ID(bigimage_id,publisherBeanId); }
	        }
	        
	        publisherBeanId.setSelect_big_images(productPostAllFaced.getComboBoxWithJavaScriptBigImage("bigimage_id", publisherBeanId.getBigimage_id() ,"SELECT big_images_id,imgname FROM big_images WHERE user_id = "+ authorizationPageBeanId.getIntUserID()  + " ORDER BY big_images_id DESC ", "onChange=\"changeImage()\"" , publisherBeanId ) );

	        if(publisherBeanId.getBigimgname().lastIndexOf(".") != -1){
	        sbuff = new StringBuffer(); 
	        sbuff.append("big_imgpositions/") ;
	        sbuff.append(publisherBeanId.getBigimage_id()) ;
	        sbuff.append(publisherBeanId.getBigimgname().substring(publisherBeanId.getBigimgname().lastIndexOf("."))) ;
	        publisherBeanId.setSelect_big_image_url(sbuff.toString());
	        }
	        else
	        {
	        	publisherBeanId.setSelect_big_image_url("big_imgpositions/empty.gif");
	        }
	        
		
	}

	

}
