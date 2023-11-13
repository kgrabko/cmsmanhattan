package com.cbsinc.cms.controllers;

import java.util.Enumeration;
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

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.OperationAmountBean;
import com.cbsinc.cms.OrderBean;
import com.cbsinc.cms.PayBean;
import com.cbsinc.cms.faceds.OrderFaced;

public class FDPayAction extends TemplateAction {

	
	
	public boolean isInternet = true;

	public FDPayAction() {
	}

	@Override
	public  void action(HttpServletRequest request , HttpServletResponse  response , ServletContext servletContextOpts) throws Exception {


		AuthorizationPageBean authorizationPageBean = getAuthorizationPageBean() ;
		OrderBean orderBeanId = getOrderBean() ;
		OperationAmountBean operationAmountBean = getOperationAmountBean();
		PayBean payBeanId = getPayBean();
		OrderFaced orderFaced = ServiceLocator.getInstance().getOrderFaced();
		ResourceBundle	resources = PropertyResourceBundle.getBundle("localization", response.getLocale());

		
		//if (authorizationPageBean.empty() || payBeanId.empty() || orderFaced.empty())return;
		
		request.setCharacterEncoding("UTF-8");
		payBeanId.setDescription(resources.getString("Purchase"));

		String user_os = "";
		String header_name = "";
		String header_value = "";
		Enumeration en = request.getHeaderNames();
		while (en.hasMoreElements()) {
			header_name = (String) en.nextElement();
			header_value = request.getHeader(header_name);
			user_os = user_os.concat(header_name + "=" + header_value + "\n");
		}
		payBeanId.setAccount_hist_id(operationAmountBean.addMoneyStart("Purchase",
				Double.parseDouble(orderBeanId.getorder_amount()), orderBeanId.getOrder_currency_id(),
				authorizationPageBean.getIntUserID(), request.getRemoteAddr(), user_os, orderBeanId.getOrder_id()));
		payBeanId.setStatusInrocess(orderBeanId.getOrder_id());
	}

}
