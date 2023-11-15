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
import com.cbsinc.cms.OrderBean;
import com.cbsinc.cms.PrePayBean;
import com.cbsinc.cms.annotations.PageController;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;

@PageController( jspName = "PrePay.jsp" )
public class PrePayAction  extends TemplateAction {
	
	
	public boolean isInternet = true ;
	
	public PrePayAction() {
	}

	
	@Override
	public  void action(HttpServletRequest request , HttpServletResponse  response , ServletContext servletContextOpts) throws Exception {

		PrePayBean prePayBean  = getPrePayBean();
		OrderBean orderBean = getOrderBean();
		AuthorizationPageFaced authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();
		
		if( authorizationPageFaced == null ||  prePayBean == null || orderBean == null  ) return ;
		request.setCharacterEncoding("UTF-8"); 
		prePayBean.setSelectCurrencyListXML(authorizationPageFaced.getXMLDBList("Pay.jsp?currency_id","currency", orderBean.getOrder_currency_id()  ,"SELECT currency_id , currency_desc FROM currency  WHERE active = true")); 
		prePayBean.setSelectPaysystemListXML(authorizationPageFaced.getXMLDBList("Pay.jsp?paysystem_id","paysystem", "1"  ,"SELECT paysystem.paysystem_id , paysystem.description FROM  paysystem WHERE paysystem.active = true"));
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
