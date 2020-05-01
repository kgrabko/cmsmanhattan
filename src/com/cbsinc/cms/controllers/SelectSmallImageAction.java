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


public class SelectSmallImageAction extends TemplateAction 
{
	
	
	@Override
	public void action(Optional<HttpServletRequest> requestOpts, Optional<HttpServletResponse> responseOpts,
			Optional<ServletContext> servletContextOpts) throws Exception {


		HttpServletResponse response = responseOpts.get() ;
		HttpServletRequest request  = requestOpts.get() ;
		
		PublisherBean publisherBeanId = getPublisherBean().get() ;
		ProductPostAllFaced productPostAllFaced =  ServiceLocator.getInstance().getProductPostAllFaced().get() ;
		AuthorizationPageBean authorizationPageBeanId = getAuthorizationPageBean().get() ;
		StringBuffer sbuff = new StringBuffer(); 

  		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setDateHeader ("Expires", 0); 
  		
        if( request.getParameter("image_id") != null) 
        {
	        String  image_id  = request.getParameter("image_id");
	        if ( image_id != null ) {  publisherBeanId.setImage_id( image_id );  productPostAllFaced.setImageNameByImage_ID(image_id,publisherBeanId); }
        }
        
        publisherBeanId.setSelect_small_images(productPostAllFaced.getComboBoxWithJavaScriptSmallImage("image_id", publisherBeanId.getImage_id() ,"SELECT image_id,imgname FROM images WHERE user_id = "+ authorizationPageBeanId.getIntUserID() + " ORDER BY image_id DESC ", "onChange=\"changeImage()\"" , publisherBeanId ) );
        
        if(publisherBeanId.getImgname().lastIndexOf(".") != -1){
        sbuff = new StringBuffer(); 
        sbuff.append("imgpositions/") ;
        sbuff.append(publisherBeanId.getImage_id()) ;
        sbuff.append(publisherBeanId.getImgname().substring(publisherBeanId.getImgname().lastIndexOf("."))) ;
        publisherBeanId.setSelect_small_image_url(sbuff.toString());
        }
        else
        {
        	publisherBeanId.setSelect_small_image_url("imgpositions/empty.gif");
        }
		
	}

}
