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

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.annotations.PageController;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;

@PageController( jspName = "SetDomain.jsp" )
public class SetDomainAction  extends TemplateAction {

	public boolean isInternet = true;

	public SetDomainAction() {
	}


	@Override
	public  void action(HttpServletRequest request , HttpServletResponse  response , ServletContext servletContextOpts) throws Exception {


		AuthorizationPageBean authorizationPageBean = getAuthorizationPageBean();
		AuthorizationPageFaced authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();

		
		if (authorizationPageBean == null) return;
		request.setCharacterEncoding("UTF-8");
		String host = request.getParameter("domain");
		if (host != null) {
			authorizationPageBean.setHost(host);
			authorizationPageFaced.saveNewDomain(authorizationPageBean.getHost(), authorizationPageBean.getSite_id());
			request.setAttribute("message", "host name changed successfully.");
		}
		
	}

}
