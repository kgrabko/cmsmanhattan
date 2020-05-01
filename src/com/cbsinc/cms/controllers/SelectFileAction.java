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
import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.PublisherBean;
import com.cbsinc.cms.faceds.ProductPostAllFaced;


public class SelectFileAction extends TemplateAction 
{


	@Override
	public void action(Optional<HttpServletRequest> requestOpts, Optional<HttpServletResponse> responseOpts,
			Optional<ServletContext> servletContextOpts) throws Exception {
		
		
		PublisherBean publisherBeanId = getPublisherBean().get() ;
		ProductPostAllFaced productPostAllFaced =  ServiceLocator.getInstance().getProductPostAllFaced().get() ;
		AuthorizationPageBean authorizationPageBeanId = getAuthorizationPageBean().get() ;
		
		HttpServletResponse response = responseOpts.get() ;
		HttpServletRequest request  = requestOpts.get() ;

  		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
		response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setDateHeader ("Expires", 0); 
  		
        if( request.getParameter("file_id") != null) 
        {
	        String  file_id  = request.getParameter("file_id");
	        if ( file_id != null ) {  publisherBeanId.setFile_id( file_id );  productPostAllFaced.setFileNameByFile_ID(file_id,publisherBeanId); }
        }
        
        publisherBeanId.setSelect_files(productPostAllFaced.getComboBoxWithJavaScriptBigImage("file_id", publisherBeanId.getFile_id() ,"select file_id , name  from file  where  user_id = "+ authorizationPageBeanId.getIntUserID() + " ORDER BY file_id DESC" , "onChange=\"changeImage()\"" , publisherBeanId  ) );
	}
	

}
