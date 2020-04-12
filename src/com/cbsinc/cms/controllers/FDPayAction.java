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

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.OperationAmountBean;
import com.cbsinc.cms.OrderBean;
import com.cbsinc.cms.PayBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.faceds.OrderFaced;

public class FDPayAction  implements IAction
{

	AuthorizationPageBean authorizationPageBean ;
	AuthorizationPageFaced authorizationPageFaced ;
	OrderFaced orderFaced ;
	HttpSession session ;
	OrderBean orderBeanId ;
	OperationAmountBean operationAmountBean ;
	public boolean isInternet = true ;
	
	public FDPayAction() {
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
		PayBean payBeanId = null ;
		orderFaced = ServiceLocator.getInstance().getOrderFaced();
		session = request.getSession();
		orderBeanId = (OrderBean)session.getAttribute("orderBeanId");
		payBeanId = (PayBean)session.getAttribute("payBeanId");
		operationAmountBean = (com.cbsinc.cms.OperationAmountBean)session.getAttribute("OperationAmountBeanId");

		if(authorizationPageFaced == null)  authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();
		
		authorizationPageBean = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		if( authorizationPageBean == null ||  payBeanId == null || orderFaced == null  ) return ;
		request.setCharacterEncoding("UTF-8"); 
		
		
		ResourceBundle resources = null ;
		if( resources == null )  resources = PropertyResourceBundle.getBundle("localization", response.getLocale());
		payBeanId.setDescription(resources.getString("Purchase"));
		
			String user_os = "" ;
			String header_name = "" ;
			String header_value = "" ;
			java.util.Enumeration en = request.getHeaderNames() ;
			while(en.hasMoreElements())
			{
				header_name =(String)en.nextElement() ;
				header_value =  request.getHeader(header_name);
				user_os = user_os.concat( header_name + "=" + header_value + "\n" );
			}
			payBeanId.setAccount_hist_id(operationAmountBean.addMoneyStart("Purchase" , new Double( orderBeanId.getorder_amount()) , orderBeanId.getOrder_currency_id() , authorizationPageBean.getIntUserID(),request.getRemoteAddr() , user_os , orderBeanId.getOrder_id() ) );
			payBeanId.setStatusInrocess(orderBeanId.getOrder_id()) ;
		}

	
	
}
