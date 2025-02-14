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

import com.cbsinc.cms.AccountHistoryBean;
import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.annotations.PageController;
import com.cbsinc.cms.annotations.PageXsltView;
import com.cbsinc.cms.annotations.Type;
import com.cbsinc.cms.faceds.OrderFaced;
import org.perf4j.aop.Profiled ;


@PageController( jspName = "AccountHistory.jsp" )
public  class AccountHistoryAction extends TemplateAction {


	private static final String CLASS_NAME = "com.cbsinc.cms.controllers.AccountHistoryAction" ;
	
	public AccountHistoryAction() {

	}
	

	@Override
	@Profiled(logger = CLASS_NAME , tag = "AccountHistoryAction" , message = "HttpServletRequest: {$0} , HttpServletResponse: { $1 } , ServletContext: { $2 } , actionResponse: {@ retrun }"  )
	public  void action(HttpServletRequest request , HttpServletResponse  response , ServletContext servletContextOpts) 
			throws Exception {
		

		AuthorizationPageBean authorizationPageBeanId = this.getAuthorizationPageBean() ;
		AccountHistoryBean accountHistoryBeanId = this.getAccountHistoryBean() ;
		OrderFaced 	orderFaced = ServiceLocator.getInstance().getOrderFaced();

		//if (authorizationPageBeanId == null || accountHistoryBeanId == null || orderFaced.empty()) return;
		if( authorizationPageBeanId == null ||  accountHistoryBeanId == null || orderFaced == null  ) return ;

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
					.setSelectAccountHistoryXML( orderFaced.getPaymentlistByDate(authorizationPageBeanId.getIntUserID(),
							authorizationPageBeanId.getIntLevelUp(), accountHistoryBeanId));
			// orderListBean.setSearchquery("0");
			return;
		}

		accountHistoryBeanId.setSelectAccountHistoryXML(orderFaced.getPaymentlist(authorizationPageBeanId.getIntUserID(),
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
