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
import com.cbsinc.cms.faceds.AuthorizationPageFaced;


public class SetDomainAction  implements IAction
{

	AuthorizationPageBean authorizationPageBean ;
	AuthorizationPageFaced authorizationPageFaced ;
	HttpSession session ;
	public boolean isInternet = true ;
	
	public SetDomainAction() {
		// TODO Auto-generated constructor stub
	}
	
	
	
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
		if(authorizationPageFaced == null)  authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();
		authorizationPageBean = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		if( authorizationPageBean == null  ) return ;
		request.setCharacterEncoding("UTF-8"); 
		String host = request.getParameter("domain") ;
		if(host != null){
		authorizationPageBean.setHost(host);
		authorizationPageFaced.saveNewDomain(authorizationPageBean.getHost(),authorizationPageBean.getSite_id() ) ;
		request.setAttribute("message","host name changed successfully.");
		}
		
	}
	
	
}
