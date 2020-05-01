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

import java.text.SimpleDateFormat;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.OrderListBean;
import com.cbsinc.cms.faceds.OrderFaced;

public class OrderListAction implements IAction {

	OrderFaced orderFaced;
	SimpleDateFormat formatter;

	public boolean isInternet = true;

	public OrderListAction() {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		doGet(request, response, servletContext);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {

		AuthorizationPageBean authorizationPageBeanId;
		HttpSession session;
		OrderListBean orderListBean = null;
		orderFaced = ServiceLocator.getInstance().getOrderFaced().get();
		session = request.getSession();
		orderListBean = (OrderListBean) session.getAttribute("orderListBeanId");
		authorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
		if (authorizationPageBeanId == null || orderListBean == null || orderFaced == null)
			return;

		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("offset") != null && isNumber(request.getParameter("offset"))) {
			orderListBean.setOffset(Integer.parseInt(request.getParameter("offset")));
		}
		if (request.getParameter("searchquery") != null && isNumber(request.getParameter("searchquery"))) {
			if (!orderListBean.getSearchquery().equals(request.getParameter("searchquery")))
				orderListBean.setOffset(0);
			orderListBean.setSearchquery(request.getParameter("searchquery"));
		}


		if (orderListBean.getSearchquery().equals("2")) {

			if (request.getParameter("datefrom") != null) {
				if (isNumber(request.getParameter("datefrom")))
					orderListBean.setDateFrom(Long.parseLong(request.getParameter("datefrom")));
				else if (isDatePattern(request.getParameter("date_format")))
					orderListBean.setDateFrom(request.getParameter("datefrom"), request.getParameter("date_format"),
							request.getLocale());
			}
			if (request.getParameter("dateto") != null) {
				if (isNumber(request.getParameter("dateto")))
					orderListBean.setDateTo(Long.parseLong(request.getParameter("dateto")));
				else if (isDatePattern(request.getParameter("date_format")))
					orderListBean.setDateTo(request.getParameter("dateto"), request.getParameter("date_format"),
							request.getLocale());
			}
			orderListBean.setSelectOrderlistXML(orderFaced.getOrderlistByDate(authorizationPageBeanId.getIntUserID(),
					orderListBean, request.getLocale(), authorizationPageBeanId.getIntLevelUp(),
					authorizationPageBeanId.getSite_id()));
			orderListBean.setSearchquery("0");
			return;
		}

		if (orderListBean.getSearchquery().equals("3")) {

			if (request.getParameter("order_paystatus") != null) {
				orderListBean.setOrder_paystatus_id(request.getParameter("order_paystatus"));
			}
			if (request.getParameter("order_status") != null) {
				orderListBean.setDeliverystatus_id(request.getParameter("order_status"));
			}

			orderListBean.setSelectOrderlistXML(orderFaced.getOrderlistByStatus(authorizationPageBeanId.getSite_id(),
					orderListBean, request.getLocale()));
			orderListBean.setSelect_paystatus(orderFaced.getXMLDBList("OrderList.jsp?order_paystatus", "paystatus",
					orderListBean.getOrder_paystatus_id(),
					"select  paystatus_id , lable  from  paystatus  where  active  = true"));
			orderListBean.setSelect_deliverystatus(orderFaced.getXMLDBList("OrderList.jsp?order_deliverystatus",
					"deliverystatus", orderListBean.getDeliverystatus_id(),
					"select  deliverystatus_id , lable  from  deliverystatus  where  active  = true and lang = '"
							+ authorizationPageBeanId.getLocale() + "' "));
			orderListBean.setSearchquery("0");
			return;
		}

		orderListBean.setSelectOrderlistXML(
				orderFaced.getOrderlist(authorizationPageBeanId.getIntUserID(), orderListBean, request.getLocale()));
		orderListBean.setSelect_paystatus(orderFaced.getXMLDBList("OrderList.jsp?order_paystatus", "paystatus",
				orderListBean.getOrder_paystatus_id(),
				"select  paystatus_id , lable  from  paystatus  where  active  = true"));
		orderListBean.setSelect_deliverystatus(orderFaced.getXMLDBList("OrderList.jsp?order_deliverystatus",
				"deliverystatus", orderListBean.getDeliverystatus_id(),
				"select  deliverystatus_id , lable  from  deliverystatus  where  active  = true and lang = '"
						+ authorizationPageBeanId.getLocale() + "' "));

		orderListBean.setSelect_menu_catalog(orderFaced.getMenuXMLDBList("Productlist.jsp?catalog_id", "menu",
				authorizationPageBeanId.getCatalog_id(),
				"select catalog_id , lable , parent_id  from catalog   where  active = true and parent_id = 0 and site_id = "
						+ authorizationPageBeanId.getSite_id() + " and lang_id = "
						+ authorizationPageBeanId.getLang_id()
						+ " or parent_id in (select catalog_id   from catalog   where  active = true and site_id = "
						+ authorizationPageBeanId.getSite_id() + "  and parent_id = 0 )"));

	}

	public boolean isNumber(String tmp) {
		if (tmp == null || tmp.length() == 0)
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

	public boolean isDatePattern(String tmp) {
		if (tmp == null || tmp.length() == 0)
			return false;
		String IntField = "dm/yMDY:.";
		for (int i = 0; i < tmp.length(); i++) {

			if (IntField.indexOf(tmp.charAt(i)) == -1) {
				if (tmp.charAt(i) != '-' && i != 0)
					return false;
			}
		}
		return true;
	}
}
