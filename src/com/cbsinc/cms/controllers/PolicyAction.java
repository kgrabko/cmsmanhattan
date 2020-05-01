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

import org.apache.log4j.Logger;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.CatalogListBean;
import com.cbsinc.cms.ItemDescriptionBean;
import com.cbsinc.cms.faceds.PolicyFaced;
import com.cbsinc.cms.faceds.ProductlistFaced;

public class PolicyAction implements IAction {

	transient static private Logger log = Logger.getLogger(PolicyAction.class);
	private PolicyFaced policyFaced = null;
	private ProductlistFaced productlistFaced = null;

	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		doGet(request, response, servletContext);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {

		AuthorizationPageBean authorizationPageBeanId;
		ItemDescriptionBean itemDescriptionBeanId = null;
		HttpSession session;
		boolean isInternet = true;

		if (policyFaced == null)
			policyFaced = ServiceLocator.getInstance().getPolicyFaced().get();
		if (productlistFaced == null)
			productlistFaced = ServiceLocator.getInstance().getProductlistFaced().get();
		itemDescriptionBeanId = new ItemDescriptionBean();
		session = request.getSession();

		authorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
		request.setAttribute("itemDescriptionBeanId", itemDescriptionBeanId);

		if (request.getRemoteAddr().startsWith("192."))
			isInternet = false;
		if (request.getRemoteAddr().startsWith("10."))
			isInternet = false;

		if (authorizationPageBeanId == null || itemDescriptionBeanId == null)
			return;

		if (request.getParameter("policy_byproductid") == null && request.getParameter("page") == null) {

			if (request.getParameter("rate") != null && isNumber(request.getParameter("rate"))) {
				itemDescriptionBeanId.setProduct_id(Long.toString(authorizationPageBeanId.getLastProductId()));
				int rate = Integer.parseInt(request.getParameter("rate"));
				policyFaced.setRatring1(rate, itemDescriptionBeanId.getProduct_id());
				response.sendRedirect("Policy.jsp?policy_byproductid=" + itemDescriptionBeanId.getProduct_id());
				return;
			}

			response.sendRedirect("Productlist.jsp?catalog_id=-2");
			return;
		}

		request.setCharacterEncoding("UTF-8");

		itemDescriptionBeanId.internet = isInternet;

		if (request.getParameter("policy_byproductid") != null
				&& isNumber(request.getParameter("policy_byproductid"))) {
			policyFaced.mergePolicyBean(authorizationPageBeanId.getIntUserID(),
					request.getParameter("policy_byproductid"), itemDescriptionBeanId);
			itemDescriptionBeanId.setBack_url("Policy.jsp?policy_byproductid=" + itemDescriptionBeanId.getParent_product_id());
			itemDescriptionBeanId.setType_page("policy_byproductid");
			itemDescriptionBeanId.setIntUserID(authorizationPageBeanId.getIntUserID());
			authorizationPageBeanId.setLastProductId(Long.parseLong(itemDescriptionBeanId.getParent_product_id()));

			if (request.getParameter("rate") != null && isNumber(request.getParameter("rate"))) {
				int rate = Integer.parseInt(request.getParameter("rate"));
				policyFaced.setRatring1(rate, itemDescriptionBeanId.getProduct_id());
			}

			itemDescriptionBeanId.setRating1_xml(policyFaced.getRatring1XML(itemDescriptionBeanId.getProduct_id()));
			itemDescriptionBeanId.setBalans("" + policyFaced.getBalans(authorizationPageBeanId.getIntUserID()));

			try {
				if (itemDescriptionBeanId.isFistOpen()) {

					policyFaced.payMoneyForShowPage(itemDescriptionBeanId, authorizationPageBeanId, request.getRemoteAddr());
					itemDescriptionBeanId.setFistOpen(false);
				} else
					policyFaced.incrementShowPage(itemDescriptionBeanId);
			} catch (Exception ex) {
				log.error("Billing is not working ", ex);
			}

		} else if (request.getParameter("page") != null && request.getParameter("page").compareTo("about") == 0) {
			policyFaced.mergePolicyBeanForAboutPage(authorizationPageBeanId.getSite_id(), itemDescriptionBeanId);// (authorizationPageBeanId.getIntUserID(),
																										// request.getParameter("policy_byproductid")
																										// ,
																										// policyBeanId)
																										// ;
			authorizationPageBeanId.setLastProductId(Long.parseLong(itemDescriptionBeanId.getParent_product_id()));
			itemDescriptionBeanId.setBack_url("Productlist.jsp");
			itemDescriptionBeanId.setType_page("about");
			itemDescriptionBeanId.setIntUserID(authorizationPageBeanId.getIntUserID());
			itemDescriptionBeanId.setRating1_xml(policyFaced.getRatring1XML(itemDescriptionBeanId.getProduct_id()));
			itemDescriptionBeanId.setBalans("" + policyFaced.getBalans(authorizationPageBeanId.getIntUserID()));

			if (itemDescriptionBeanId.isFistOpen()) {
				policyFaced.payMoneyForShowPage(itemDescriptionBeanId, authorizationPageBeanId, request.getRemoteAddr());
				itemDescriptionBeanId.setFistOpen(false);
			} else
				policyFaced.incrementShowPage(itemDescriptionBeanId);
		} else if (request.getParameter("page") != null && request.getParameter("page").compareTo("pay") == 0) {
			policyFaced.mergePolicyBeanForPayPageInfo(authorizationPageBeanId.getSite_id(), itemDescriptionBeanId);// (authorizationPageBeanId.getIntUserID(),
																											// request.getParameter("policy_byproductid")
																											// ,
																											// policyBeanId)
																											// ;
			authorizationPageBeanId.setLastProductId(Long.parseLong(itemDescriptionBeanId.getParent_product_id()));
			itemDescriptionBeanId.setBack_url("Productlist.jsp");
			itemDescriptionBeanId.setType_page("pay");
			itemDescriptionBeanId.setIntUserID(authorizationPageBeanId.getIntUserID());
			itemDescriptionBeanId.setRating1_xml(policyFaced.getRatring1XML(itemDescriptionBeanId.getProduct_id()));
			itemDescriptionBeanId.setBalans("" + policyFaced.getBalans(authorizationPageBeanId.getIntUserID()));

			if (itemDescriptionBeanId.isFistOpen()) {
				policyFaced.payMoneyForShowPage(itemDescriptionBeanId, authorizationPageBeanId, request.getRemoteAddr());
				itemDescriptionBeanId.setFistOpen(false);
			} else
				policyFaced.incrementShowPage(itemDescriptionBeanId);

		}

		itemDescriptionBeanId.setSelectCatalogXMLUrlPath(
				(new CatalogListBean(servletContext)).getCatalogXMLUrlPath("Productlist.jsp?catalog_id", "parent",
						authorizationPageBeanId.getCatalog_id(), authorizationPageBeanId));
		itemDescriptionBeanId.setSelect_tree_catalog(productlistFaced.getTreeXMLDBList("Productlist.jsp?catalog_id", "catalog",
				authorizationPageBeanId.getCatalog_id(),
				"select catalog_id , lable   from catalog   where  active = true and site_id = "
						+ authorizationPageBeanId.getSite_id() + " and parent_id = "
						+ authorizationPageBeanId.getCatalogParent_id(),
				"select catalog_id , lable   from catalog   where  active = true and parent_id = "
						+ authorizationPageBeanId.getCatalog_id()));
		itemDescriptionBeanId.setSelect_currencies(
				policyFaced.getXMLDBList("Policy.jsp", "currencies", itemDescriptionBeanId.getCurrency_cd(),
						"SELECT currency_cd , currency_desc  FROM currency  WHERE active = true"));
		itemDescriptionBeanId.ext1Adp = productlistFaced.getExtPolicyOneProductlist("" + authorizationPageBeanId.getIntUserID(),
				authorizationPageBeanId.getSite_id(), itemDescriptionBeanId.getParent_product_id(), itemDescriptionBeanId);
		itemDescriptionBeanId.ext2Adp = productlistFaced.getExtPolicyTwoProductlist("" + authorizationPageBeanId.getIntUserID(),
				authorizationPageBeanId.getSite_id(), itemDescriptionBeanId.getParent_product_id(), itemDescriptionBeanId);
		itemDescriptionBeanId.extFilesAdp = productlistFaced.getExtPolicyFilesProductlist(
				"" + authorizationPageBeanId.getIntUserID(), authorizationPageBeanId.getSite_id(),
				itemDescriptionBeanId.getParent_product_id(), itemDescriptionBeanId);
		itemDescriptionBeanId.extTabsAdp = productlistFaced.getExtPolicyTabsProductlist(
				"" + authorizationPageBeanId.getIntUserID(), authorizationPageBeanId.getSite_id(),
				itemDescriptionBeanId.getParent_product_id(), itemDescriptionBeanId);
		itemDescriptionBeanId.blogExtAdp = productlistFaced.getBlogExtPolicyProductlist(
				"" + authorizationPageBeanId.getIntUserID(), authorizationPageBeanId.getSite_id(),
				itemDescriptionBeanId.getParent_product_id(), itemDescriptionBeanId);
		// if( policyBeanId.getPortlettype_id() != Layout.PORTLET_TYPE_BOTTOM )
		itemDescriptionBeanId.newsAdp = productlistFaced.getNewslist("" + authorizationPageBeanId.getIntUserID(),
				authorizationPageBeanId.getSite_id(), authorizationPageBeanId);
		itemDescriptionBeanId.bottomAdp = productlistFaced.getBottomlist("" + authorizationPageBeanId.getIntUserID(),
				authorizationPageBeanId.getSite_id(), authorizationPageBeanId);

		itemDescriptionBeanId.setSelect_menu_catalog(productlistFaced.getMenuXMLDBList("Productlist.jsp?catalog_id", "menu",
				authorizationPageBeanId.getCatalog_id(),
				"select catalog_id , lable , parent_id  from catalog   where  active = true and parent_id = 0 and site_id = "
						+ authorizationPageBeanId.getSite_id() + " and lang_id = "
						+ authorizationPageBeanId.getLang_id()
						+ " or parent_id in (select catalog_id   from catalog   where  active = true and site_id = "
						+ authorizationPageBeanId.getSite_id() + "  and parent_id = 0 )"));

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
