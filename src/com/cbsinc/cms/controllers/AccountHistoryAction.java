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

import com.cbsinc.cms.AccountHistoryBean;
import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.faceds.OrderFaced;

public class AccountHistoryAction extends TemplateAction {


	public AccountHistoryAction() {

	}
	

	@Override
	public void action(Optional<HttpServletRequest> requestOpts, Optional<HttpServletResponse> responseOpts, Optional<ServletContext> servletContextOpts)
			throws Exception {
		
		HttpServletRequest request  = requestOpts.get() ;
		AuthorizationPageBean authorizationPageBeanId = this.getAuthorizationPageBean().get() ;
		AccountHistoryBean accountHistoryBeanId = this.getAccountHistoryBean().get() ;
		Optional <OrderFaced> 	orderFaced = ServiceLocator.getInstance().getOrderFaced();

		if (this.getAuthorizationPageBean().isEmpty()  || this.getAccountHistoryBean().isEmpty() || orderFaced.isEmpty())
			return;

		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("searchquery") != null && isNumber(request.getParameter("searchquery"))) {
			accountHistoryBeanId.setSearchquery(request.getParameter("searchquery"));
			if (!accountHistoryBeanId.getSearchquery().equals(request.getParameter("searchquery")))
				accountHistoryBeanId.setOffset(0);
		}

		if (accountHistoryBeanId.getSearchquery().equals("2")) {
			if (request.getParameter("datefrom") != null)
				accountHistoryBeanId.setStrDateFrom(request.getParameter("datefrom"));
			if (request.getParameter("dateto") != null)
				accountHistoryBeanId.setStrDateTo(request.getParameter("dateto"));
			accountHistoryBeanId
					.setSelectAccountHistoryXML( orderFaced.get().getPaymentlistByDate(authorizationPageBeanId.getIntUserID(),
							authorizationPageBeanId.getIntLevelUp(), accountHistoryBeanId));
			// orderListBean.setSearchquery("0");
			return;
		}

		accountHistoryBeanId.setSelectAccountHistoryXML(orderFaced.get().getPaymentlist(authorizationPageBeanId.getIntUserID(),
				authorizationPageBeanId.getIntLevelUp(), accountHistoryBeanId));
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
