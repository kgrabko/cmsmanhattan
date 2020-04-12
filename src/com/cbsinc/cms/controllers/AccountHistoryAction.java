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

import com.cbsinc.cms.AccountHistoryBean;
import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.faceds.OrderFaced;

public class AccountHistoryAction  implements IAction
{

	
	
	OrderFaced orderFaced ;
	
	
	
	
	public AccountHistoryAction() {
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
		AuthorizationPageBean authorizationPageBeanId ;
		AccountHistoryBean accountHistoryBeanId ;
		HttpSession session ;
		boolean isInternet = true ;
		AccountHistoryBean accountHistoryBean = null ;
		if(orderFaced == null) orderFaced = ServiceLocator.getInstance().getOrderFaced();
		session = request.getSession();
		accountHistoryBean = (AccountHistoryBean)session.getAttribute("accountHistoryBeanId");
		authorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		if( authorizationPageBeanId == null ||  accountHistoryBean == null || orderFaced == null  ) return ;

		request.setCharacterEncoding("UTF-8"); 
		if( request.getParameter("searchquery") !=null && isNumber(request.getParameter("searchquery")) ) 
		{
			accountHistoryBean.setSearchquery(request.getParameter("searchquery"));
			if( !accountHistoryBean.getSearchquery().equals(request.getParameter("searchquery")) ) accountHistoryBean.setOffset(0);
		}
		
		if( accountHistoryBean.getSearchquery().equals("2") )
		{
			if(request.getParameter("datefrom") != null) accountHistoryBean.setStrDateFrom(request.getParameter("datefrom")) ;
			if(request.getParameter("dateto")!= null) accountHistoryBean.setStrDateTo(request.getParameter("dateto")) ;
			accountHistoryBean.setSelectAccountHistoryXML(orderFaced.getPaymentlistByDate(authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getIntLevelUp(),accountHistoryBean ));
			//orderListBean.setSearchquery("0");
			return ;
		}
		
		accountHistoryBean.setSelectAccountHistoryXML(orderFaced.getPaymentlist(authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getIntLevelUp(),accountHistoryBean ));
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
