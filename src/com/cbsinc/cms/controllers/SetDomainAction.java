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
import com.cbsinc.cms.faceds.AuthorizationPageFaced;

public class SetDomainAction  extends TemplateAction {

	public boolean isInternet = true;

	public SetDomainAction() {
	}


	@Override
	public void action(Optional<HttpServletRequest> requestOpts, Optional<HttpServletResponse> responseOpts,
			Optional<ServletContext> servletContextOpts) throws Exception {

		AuthorizationPageBean authorizationPageBean = getAuthorizationPageBean().get() ;
		AuthorizationPageFaced authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced().get();
		HttpServletRequest request  = requestOpts.get() ;
		
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
