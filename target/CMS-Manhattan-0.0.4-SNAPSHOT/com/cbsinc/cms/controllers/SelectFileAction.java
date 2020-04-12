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


public class SelectFileAction implements IAction 
{


	
	HttpSession session ;
	//ResourceBundle resources = null ;
	//ResourceBundle setup_resources = null ;
	SoftPostBean softPostBeanId = null ; ;
	ProductPostAllFaced productPostAllFaced = null ;
	AuthorizationPageFaced authorizationPageFaced = null ;
	AuthorizationPageBean AuthorizationPageBeanId = null ;
	String  create_cabinet = "" ;
	StringBuffer sbuff = new StringBuffer(); 
	
	   
	public void doPost(HttpServletRequest request, HttpServletResponse response , ServletContext  servletContext) throws Exception 
	{
		action( request,  response,  servletContext) ;
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		action( request,  response,  servletContext) ;
	}

	
	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{

		    session = request.getSession();
 
			authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();
			AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
			softPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");
			productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced();
	  	    //if( resources == null )  resources = PropertyResourceBundle.getBundle("localization", response.getLocale());
	  		//if( setup_resources == null )  setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources" );

	  		request.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
			response.setHeader("Pragma","no-cache"); //HTTP 1.0
			response.setDateHeader ("Expires", 0); 
	  		
	        if( request.getParameter("file_id") != null) 
	        {
		        String  file_id  = request.getParameter("file_id");
		        if ( file_id != null ) {  softPostBeanId.setFile_id( file_id );  productPostAllFaced.setFileNameByFile_ID(file_id,softPostBeanId); }
	        }
	        
	        
	        //softPostBeanId.setSelect_files(productPostAllFaced.getComboBox("file_id", softPostBeanId.getFile_id() ,"select file_id , name  from file  where  user_id = "+ AuthorizationPageBeanId.getIntUserID() ) );
	        softPostBeanId.setSelect_files(productPostAllFaced.getComboBoxWithJavaScriptBigImage("file_id", softPostBeanId.getFile_id() ,"select file_id , name  from file  where  user_id = "+ AuthorizationPageBeanId.getIntUserID() + " ORDER BY file_id DESC" , "onChange=\"changeImage()\"" , softPostBeanId  ) );
	        //softPostBeanId.setSelect_big_images(productPostAllFaced.getComboBoxWithJavaScriptBigImage("bigimage_id", softPostBeanId.getBigimage_id() ,"SELECT big_images_id,imgname FROM big_images WHERE user_id = "+ AuthorizationPageBeanId.getIntUserID()  + " ORDER BY big_images_id DESC ", "onChange=\"changeImage()\"" , softPostBeanId ) );

	        
	        
	        
	        
	}

	

}
