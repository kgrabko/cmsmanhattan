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

import com.cbsinc.cms.AccountHistoryBean;
import com.cbsinc.cms.AccountHistoryDetalBean;
import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.faceds.OrderFaced;

public class AccountHistoryDetalAction  extends TemplateAction 
{
	
	public AccountHistoryDetalAction() {
	}

	@Override
	public void action(Optional<HttpServletRequest> requestOpts, Optional<HttpServletResponse> responseOpts, Optional<ServletContext> servletContextOpts) throws Exception
	{
		
		HttpServletRequest request  = requestOpts.get() ;
		AuthorizationPageBean authorizationPageBeanId = getAuthorizationPageBean().get() ;
		AccountHistoryDetalBean accountHistoryDetalBean = null ;
		OrderFaced orderFaced = ServiceLocator.getInstance().getOrderFaced().get();

		if( authorizationPageBeanId == null ||  accountHistoryDetalBean == null || orderFaced == null  ) return ;
		request.setCharacterEncoding("UTF-8"); 
		if( request.getParameter("amount_id") !=null ) accountHistoryDetalBean.setAmount_id(request.getParameter("amount_id"));
		accountHistoryDetalBean.setSelectAccountHistoryDetalXML(orderFaced.getPayment(authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getIntLevelUp(),accountHistoryDetalBean ));
	}

	
	public boolean isNumber(String tmp) {
		if (tmp == null)
			return false;
		String IntField = "0123456789";
		for (int i = 0; i < tmp.length(); i++) {

			if (IntField.indexOf(tmp.charAt(i)) == -1) {
				if (tmp.charAt(i) != '-' && i != 0)
					return false;
			}
		}
		return true;
	}
}
