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
import com.cbsinc.cms.CatalogAddBean;
import com.cbsinc.cms.CatalogEditBean;
import com.cbsinc.cms.CatalogListBean;
import com.cbsinc.cms.PublisherBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.faceds.ProductPostAllFaced;

public class BottomListPostAction implements IAction {

	// ResourceBundle resources = null ;
	ProductPostAllFaced productPostAllFaced;
	transient ResourceBundle setup_resources = null;

	public BottomListPostAction() {

		if (setup_resources == null)
			setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		action(request, response, servletContext);
		HttpSession session = request.getSession();
		PublisherBean publisherBeanId = (PublisherBean) session.getAttribute("publisherBeanId");
		AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean) session
				.getAttribute("authorizationPageBeanId");

		if (request.getParameter("action") != null) {
			publisherBeanId.setAction(request.getParameter("action"));

			if (publisherBeanId.getAction().compareTo("save") == 0) {

				if (request.getParameter("bigimage_id") == null) {
					publisherBeanId.setBigimage_id("-1");
				}
				if (request.getParameter("image_id") == null) {
					publisherBeanId.setImage_id("-1");
				}

				publisherBeanId.setSite_id(AuthorizationPageBeanId.getSite_id());

				publisherBeanId.setStrSoftDescription("");
				if (publisherBeanId.getSoft_id().compareTo("-1") == 0) {
					if (productPostAllFaced.isLimmitPostedMessages(AuthorizationPageBeanId, true)) {
						AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(servletContext)
								.getString("global_has_limmit_forsite"));
						response.sendRedirect("PostManager.jsp");
						return;
					}

					productPostAllFaced.saveDescSoft(publisherBeanId, AuthorizationPageBeanId);
					publisherBeanId.setAction("");
					response.sendRedirect("Productlist.jsp?offset=" + 0);
				} else {
					productPostAllFaced.updateDescSoft(publisherBeanId, AuthorizationPageBeanId);
					publisherBeanId.setAction("");
					response.sendRedirect("Productlist.jsp?offset=" + AuthorizationPageBeanId.getOffsetLastPage()
							+ "&catalog_id=" + AuthorizationPageBeanId.getCatalog_id());
				}
			}

		} else
			publisherBeanId.setAction("");

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		ProductPostAllFaced productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced().get();
		HttpSession session = request.getSession();
		PublisherBean publisherBeanId = (PublisherBean) session.getAttribute("publisherBeanId");
		AuthorizationPageBean AuthorizationPageBeanId = (AuthorizationPageBean) session
				.getAttribute("authorizationPageBeanId");

		action(request, response, servletContext);
		productPostAllFaced.initPage(request.getParameter("product_id"), publisherBeanId, AuthorizationPageBeanId);
		// if insert and limmit not add message
		if (productPostAllFaced.isLimmitPostedMessages(AuthorizationPageBeanId, false)
				&& publisherBeanId.getSoft_id().compareTo("-1") == 0) {
			AuthorizationPageBeanId.setStrMessage(
					AuthorizationPageBeanId.getLocalization(servletContext).getString("global_has_limmit_forsite"));
			response.sendRedirect("PostManager.jsp");
			return;
		}

		if (request.getParameter("action") != null) {

			publisherBeanId.setAction(request.getParameter("action"));

		} else
			publisherBeanId.setAction("");

		boolean jsf_admin = false;
		AuthorizationPageFaced authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced().get();
		String jsf_admin_key = authorizationPageFaced.getResources_cms_settings().getString("jsf_admin");
		if (jsf_admin_key == null || jsf_admin_key.equals(""))
			jsf_admin = false;
		jsf_admin_key = jsf_admin_key.trim();
		jsf_admin = jsf_admin_key.equals("true");
		publisherBeanId.setNameOfPage("BottomListPost.jsp");
		if (jsf_admin)
			response.sendRedirect("admin.jsf");

	}

	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {

		PublisherBean publisherBeanId;
		CatalogListBean catalogListBeanId;
		AuthorizationPageBean authorizationPageBeanId;
		HttpSession session;

		session = request.getSession();
		publisherBeanId = (PublisherBean) session.getAttribute("publisherBeanId");
		catalogListBeanId = (CatalogListBean) session.getAttribute("catalogListBeanId");
		authorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
		productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced().get();
	
		if (publisherBeanId == null || catalogListBeanId == null || authorizationPageBeanId == null
				|| productPostAllFaced == null)
			return;

		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0);


		if (request.getParameter("parent_id") != null) {

			authorizationPageBeanId.setCatalogParent_id(request.getParameter("parent_id"));
		}

		if (request.getParameter("type_id") != null) {
			publisherBeanId.setType_id(request.getParameter("type_id"));
		}

		if (request.getParameter("row") != null) {
			int index = catalogListBeanId.stringToInt(request.getParameter("row"));
			catalogListBeanId.setIndx_select(index);
		}
		if (request.getParameter("del") != null) {
			int index = catalogListBeanId.stringToInt(request.getParameter("del"));
			String catalog_id = catalogListBeanId.rows[index][0];
			if (catalog_id != null)
				catalogListBeanId.delete(catalog_id, authorizationPageBeanId);
			request.setAttribute("del", null);
		}
		if (request.getParameter("offset") != null) {
			catalogListBeanId.setOffset(catalogListBeanId.stringToInt(request.getParameter("offset")));
		}
//		 End Novigator ---

		if (request.getParameter("creteria1_id") != null)
			publisherBeanId.setCreteria1_id(request.getParameter("creteria1_id"));
		if (request.getParameter("creteria2_id") != null)
			publisherBeanId.setCreteria2_id(request.getParameter("creteria2_id"));
		if (request.getParameter("creteria3_id") != null)
			publisherBeanId.setCreteria3_id(request.getParameter("creteria3_id"));
		if (request.getParameter("creteria4_id") != null)
			publisherBeanId.setCreteria4_id(request.getParameter("creteria4_id"));
		if (request.getParameter("creteria5_id") != null)
			publisherBeanId.setCreteria5_id(request.getParameter("creteria5_id"));
		if (request.getParameter("creteria6_id") != null)
			publisherBeanId.setCreteria6_id(request.getParameter("creteria6_id"));
		if (request.getParameter("creteria7_id") != null)
			publisherBeanId.setCreteria7_id(request.getParameter("creteria7_id"));
		if (request.getParameter("creteria8_id") != null)
			publisherBeanId.setCreteria8_id(request.getParameter("creteria8_id"));
		if (request.getParameter("creteria9_id") != null)
			publisherBeanId.setCreteria9_id(request.getParameter("creteria9_id"));
		if (request.getParameter("creteria10_id") != null)
			publisherBeanId.setCreteria10_id(request.getParameter("creteria10_id"));

		if (request.getParameter("insert") != null) {
			if (request.getParameter("insert").compareTo("true") == 0)
				publisherBeanId.setSoft_id("-1");

		}

		String softname = request.getParameter("softname");
		if (softname != null) {
			publisherBeanId.setStrSoftName(softname);
		}

		String catalog_id = request.getParameter("catalog_id");
		if (catalog_id != null) {
			authorizationPageBeanId.setCatalog_id(catalog_id);
		}

		if (request.getParameter("type_id") != null) {
			publisherBeanId.setType_id(request.getParameter("type_id"));
		}

		String softcost = request.getParameter("softcost");
		if (softcost != null) {
			publisherBeanId.setStrSoftCost(softcost);
		}

		String currency_id = request.getParameter("currency_id");
		if (currency_id != null) {
			publisherBeanId.setStrCurrency(currency_id);
		}

		String description = request.getParameter("description");
		if (description != null) {
			publisherBeanId.setStrSoftDescription(description);
		}

		String fulldescription = request.getParameter("fulldescription");
		if (fulldescription != null) {
			publisherBeanId.setProduct_fulldescription(fulldescription);
		}

		String imagename = request.getParameter("imagename");
		if (imagename != null) {
			publisherBeanId.setImgname(imagename);
		}

		String image_id = request.getParameter("image_id");
		if (image_id != null) {
			publisherBeanId.setImage_id(image_id);
		}

		if (request.getParameter("portlettype_id") != null) {
			publisherBeanId.setPortlettype_id(request.getParameter("portlettype_id"));
		}

		String filename = request.getParameter("filename");
		if (filename != null) {
			publisherBeanId.setFilename(filename);
		}

		String bigimagename = request.getParameter("bigimagename");
		if (bigimagename != null) {
			publisherBeanId.setBigimgname(bigimagename);
		}

		String bigimage_id = request.getParameter("bigimage_id");
		if (bigimage_id != null) {
			publisherBeanId.setBigimage_id(bigimage_id);
		}

		if (request.getParameter("salelogic_id") != null)
			publisherBeanId.setProgname_id(request.getParameter("salelogic_id"));

		if (authorizationPageBeanId.getIntUserID() == 0) {
			authorizationPageBeanId.setStrMessage(
					authorizationPageBeanId.getLocalization(servletContext).getString("session_time_out"));
			response.sendRedirect("Authorization.jsp");
		} else
			publisherBeanId.setUser_id("" + authorizationPageBeanId.getIntUserID());

		if (authorizationPageBeanId.getIntLevelUp() != 2) {
			authorizationPageBeanId
					.setStrMessage("You don't have access to add position , send mail to grabko@mail.ru for access");
			response.sendRedirect("Authorization.jsp");
		}

	}

}
