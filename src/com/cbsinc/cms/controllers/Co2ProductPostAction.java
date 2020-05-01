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

public class Co2ProductPostAction implements IAction {

	boolean is_criteria_by_catalog = false;
	ProductPostAllFaced productPostAllFaced;
	transient ResourceBundle setup_resources = null;

	public Co2ProductPostAction() {

		if (setup_resources == null)
			setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources");
		is_criteria_by_catalog = setup_resources.getString("is_criteria_by_catalog").equals("true");

	}

	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {

		PublisherBean publisherBeanId;
		CatalogListBean catalogListBeanId;
		// Catalog_editBean catalog_editBean ;
		// Catalog_addBean catalog_addBean ;
		AuthorizationPageBean authorizationPageBeanId;
		HttpSession session;
		// String notselected = "" ;

		session = request.getSession();
		publisherBeanId = (PublisherBean) session.getAttribute("publisherBeanId");
		catalogListBeanId = (CatalogListBean) session.getAttribute("catalogListBeanId");
		// catalog_editBean =
		// (Catalog_editBean)session.getAttribute("catalog_editBean");
		// catalog_addBean = (Catalog_addBean)session.getAttribute("catalog_addBean");
		authorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
		productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced().get();
		// if( resources == null ) resources =
		// PropertyResourceBundle.getBundle("localization", response.getLocale());

		if (publisherBeanId == null || catalogListBeanId == null || authorizationPageBeanId == null
				|| productPostAllFaced == null)
			return;

		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0);

//		 Start Novigator ---
		// catalogListBeanId.setSite_id(authorizationPageBeanId.getSite_id());
		// catalogListBeanId.setIntLevelUp(authorizationPageBeanId.getIntLevelUp());
		if (request.getParameter("parent_id") != null) {
////		--catalogListBeanId.setParent_id(request.getParameter("parent_id"));
			// authorizationPageBeanId.setCatalog_id(request.getParameter("parent_id"));
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
//		if( request.getParameter("save") !=null ) 
//		{
//			if(request.getParameter("save").compareTo("true")== 0) publisherBeanId.setSave( request.getParameter("save"));
//			else publisherBeanId.setSave("false"); 
//		}
//		else publisherBeanId.setSave("false"); 

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
//		else publisherBeanId.setImage_id( "-1" );

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
//		else publisherBeanId.setBigimage_id( "-1" );

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

	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		ProductPostAllFaced productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced().get();
		HttpSession session = request.getSession();
		PublisherBean publisherBeanId = (PublisherBean) session.getAttribute("publisherBeanId");
		AuthorizationPageBean authorizationPageBeanId = (AuthorizationPageBean) session
				.getAttribute("authorizationPageBeanId");
		// if( resources == null ) resources =
		// PropertyResourceBundle.getBundle("localization", response.getLocale());
		String notselected = authorizationPageBeanId.getLocalization(servletContext).getString("notselected");
		CatalogListBean catalogListBeanId = (CatalogListBean) session.getAttribute("catalogListBeanId");
		CatalogEditBean catalog_editBean = (CatalogEditBean) session.getAttribute("catalog_editBean");
		CatalogAddBean catalog_addBean = (CatalogAddBean) session.getAttribute("catalog_addBean");

		action(request, response, servletContext);
		productPostAllFaced.initPage(request.getParameter("product_id"), publisherBeanId, authorizationPageBeanId);
		// if insert and limmit not add message
		if (productPostAllFaced.isLimmitPostedMessages(authorizationPageBeanId, false)
				&& publisherBeanId.getSoft_id().compareTo("-1") == 0) {
			authorizationPageBeanId.setStrMessage(
					authorizationPageBeanId.getLocalization(servletContext).getString("global_has_limmit_forsite"));
			response.sendRedirect("PostManager.jsp");
			return;
		}

		publisherBeanId.setCriteria1_label(
				productPostAllFaced.getOneLabel("select  label   from creteria1   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)));
		publisherBeanId.setCriteria2_label(
				productPostAllFaced.getOneLabel("select  label   from creteria2   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)));
		publisherBeanId.setCriteria3_label(
				productPostAllFaced.getOneLabel("select  label   from creteria3   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)));
		publisherBeanId.setCriteria4_label(
				productPostAllFaced.getOneLabel("select  label   from creteria4   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)));
		publisherBeanId.setCriteria5_label(
				productPostAllFaced.getOneLabel("select  label   from creteria5   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)));
		publisherBeanId.setCriteria6_label(
				productPostAllFaced.getOneLabel("select  label   from creteria6   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)));
		publisherBeanId.setCriteria7_label(
				productPostAllFaced.getOneLabel("select  label   from creteria7   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)));
		publisherBeanId.setCriteria8_label(
				productPostAllFaced.getOneLabel("select  label   from creteria8   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)));
		publisherBeanId.setCriteria9_label(
				productPostAllFaced.getOneLabel("select  label   from creteria9   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)));
		publisherBeanId.setCriteria10_label(
				productPostAllFaced.getOneLabel("select  label   from creteria10   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)));

		publisherBeanId.setSelect_creteria1_id(
				productPostAllFaced.getComboBoxAutoSubmitLocale("creteria1_id", publisherBeanId.getCreteria1_id(),
						notselected, "select creteria1_id , name   from creteria1   where  active = true "
								+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)));
		publisherBeanId.setSelect_creteria2_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria2_id",
				publisherBeanId.getCreteria2_id(), notselected,
				"select creteria2_id , name   from creteria2   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)
						+ " and ( link_id = 0 or link_id = " + publisherBeanId.getCreteria1_id() + " ) "));
		publisherBeanId.setSelect_creteria3_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria3_id",
				publisherBeanId.getCreteria3_id(), notselected,
				"select creteria3_id , name   from creteria3   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)
						+ " and ( link_id = 0 or link_id = " + publisherBeanId.getCreteria2_id() + " ) "));
		publisherBeanId.setSelect_creteria4_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria4_id",
				publisherBeanId.getCreteria4_id(), notselected,
				"select creteria4_id , name   from creteria4   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)
						+ " and ( link_id = 0 or link_id = " + publisherBeanId.getCreteria3_id() + " ) "));
		publisherBeanId.setSelect_creteria5_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria5_id",
				publisherBeanId.getCreteria5_id(), notselected,
				"select creteria5_id , name   from creteria5   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)
						+ " and ( link_id = 0 or link_id = " + publisherBeanId.getCreteria4_id() + " ) "));
		publisherBeanId.setSelect_creteria6_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria6_id",
				publisherBeanId.getCreteria6_id(), notselected,
				"select creteria6_id , name   from creteria6   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)
						+ " and ( link_id = 0 or link_id = " + publisherBeanId.getCreteria5_id() + " ) "));
		publisherBeanId.setSelect_creteria7_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria7_id",
				publisherBeanId.getCreteria7_id(), notselected,
				"select creteria7_id , name   from creteria7   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)
						+ " and ( link_id = 0 or link_id = " + publisherBeanId.getCreteria6_id() + " ) "));
		publisherBeanId.setSelect_creteria8_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria8_id",
				publisherBeanId.getCreteria8_id(), notselected,
				"select creteria8_id , name   from creteria8   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)
						+ " and ( link_id = 0 or link_id = " + publisherBeanId.getCreteria7_id() + " ) "));
		publisherBeanId.setSelect_creteria9_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria9_id",
				publisherBeanId.getCreteria9_id(), notselected,
				"select creteria9_id , name   from creteria9   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)
						+ " and ( link_id = 0 or link_id = " + publisherBeanId.getCreteria8_id() + " ) "));
		publisherBeanId.setSelect_creteria10_id(productPostAllFaced.getComboBoxAutoSubmitLocale("creteria10_id",
				publisherBeanId.getCreteria10_id(), notselected,
				"select creteria10_id , name   from creteria10   where  active = true "
						+ publisherBeanId.getPartCriteria(publisherBeanId.getSite_id(), is_criteria_by_catalog)
						+ " and ( link_id = 0 or link_id = " + publisherBeanId.getCreteria9_id() + " ) "));

		if (request.getParameter("action") != null) {

			publisherBeanId.setAction(request.getParameter("action"));

			if (publisherBeanId.getAction().compareTo("add") == 0) {
				// catalog_addBean.setParent_id(catalogListBeanId.getParent_id());
				// catalog_addBean.setSite_id(authorizationPageBeanId.getSite_id());

				if (request.getParameter("name") != null) {
					catalog_addBean.setName(request.getParameter("name"));
				} else
					catalog_addBean.setName("");

				if (request.getMethod().toUpperCase().compareTo("POST") == 0) {
					catalog_addBean.addCatalog(authorizationPageBeanId);
					publisherBeanId.setAction("");
					response.sendRedirect("ProductPostCre.jsp");
					return;
				}
			}

			if (publisherBeanId.getAction().compareTo("edit") == 0) {

				// catalog_editBean.setParent_id(catalogListBeanId.getParent_id());
				// catalog_editBean.setSite_id(authorizationPageBeanId.getSite_id());
				if (request.getParameter("row") != null) {
					int index = catalogListBeanId.stringToInt(request.getParameter("row"));
					catalog_editBean.setIndx_select(index);
				}
				if (request.getParameter("name") != null) {
					catalog_editBean.setName(request.getParameter("name"));
				}

				if (request.getParameter("catalog_id") != null) {
					authorizationPageBeanId.setCatalog_id(request.getParameter("catalog_id"));
				}

				if (request.getMethod().toUpperCase().compareTo("POST") == 0) {
					catalog_editBean.editCatalog(authorizationPageBeanId);
					publisherBeanId.setAction("");
					response.sendRedirect("ProductPostCre.jsp");
					return;
				}

			}

		} else
			publisherBeanId.setAction("");

		boolean jsf_admin = false;
		AuthorizationPageFaced authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced().get();
		String jsf_admin_key = authorizationPageFaced.getResources_cms_settings().getString("jsf_admin");
		if (jsf_admin_key == null || jsf_admin_key.equals(""))
			jsf_admin = false;
		jsf_admin_key = jsf_admin_key.trim();
		jsf_admin = jsf_admin_key.equals("true");
		publisherBeanId.setNameOfPage("Co2ProductPost.jsp");
		if (jsf_admin)
			response.sendRedirect("admin.jsf");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		action(request, response, servletContext);
		HttpSession session = request.getSession();
		PublisherBean publisherBeanId = (PublisherBean) session.getAttribute("publisherBeanId");
		AuthorizationPageBean authorizationPageBeanId = (AuthorizationPageBean) session
				.getAttribute("authorizationPageBeanId");
		CatalogListBean catalogListBeanId = (CatalogListBean) session.getAttribute("catalogListBeanId");
		CatalogEditBean catalog_editBean = (CatalogEditBean) session.getAttribute("catalog_editBean");
		CatalogAddBean catalog_addBean = (CatalogAddBean) session.getAttribute("catalog_addBean");

		if (request.getParameter("action") != null) {
			publisherBeanId.setAction(request.getParameter("action"));

			if (publisherBeanId.getAction().compareTo("add") == 0) {
				// catalog_addBean.setParent_id(authorizationPageBeanId.getCatalogParent_id());
				// catalog_addBean.setSite_id(authorizationPageBeanId.getSite_id());

				if (request.getParameter("name") != null) {
					catalog_addBean.setName(request.getParameter("name"));
				} else
					catalog_addBean.setName("");

				if (request.getMethod().toUpperCase().compareTo("POST") == 0) {
					catalog_addBean.addCatalog(authorizationPageBeanId);
					publisherBeanId.setAction("");
					response.sendRedirect("ProductPostCre.jsp");
					return;
				}
			}

			if (publisherBeanId.getAction().compareTo("edit") == 0) {

				// catalog_editBean.setParent_id(catalogListBeanId.getParent_id());
				// catalog_editBean.setSite_id(authorizationPageBeanId.getSite_id());
				if (request.getParameter("row") != null) {
					int index = catalogListBeanId.stringToInt(request.getParameter("row"));
					catalog_editBean.setIndx_select(index);
				}
				if (request.getParameter("name") != null) {
					catalog_editBean.setName(request.getParameter("name"));
				}

				if (request.getParameter("catalog_id") != null) {
					authorizationPageBeanId.setCatalog_id(request.getParameter("catalog_id"));
				}

				if (request.getMethod().toUpperCase().compareTo("POST") == 0) {
					catalog_editBean.editCatalog(authorizationPageBeanId);
					publisherBeanId.setAction("");
					response.sendRedirect("ProductPostCre.jsp");
					return;
				}

			}
			if (publisherBeanId.getAction().compareTo("save") == 0) {

				if (request.getParameter("bigimage_id") == null) {
					publisherBeanId.setBigimage_id("-1");
				}
				if (request.getParameter("image_id") == null) {
					publisherBeanId.setImage_id("-1");
				}

				publisherBeanId.setSite_id(authorizationPageBeanId.getSite_id());

				if (publisherBeanId.getSoft_id().compareTo("-1") == 0) {

					if (productPostAllFaced.isLimmitPostedMessages(authorizationPageBeanId, true)) {
						authorizationPageBeanId.setStrMessage(authorizationPageBeanId.getLocalization(servletContext)
								.getString("global_has_limmit_forsite"));
						response.sendRedirect("PostManager.jsp");
						return;
					}

					productPostAllFaced.saveDescSoft(publisherBeanId, authorizationPageBeanId);
					publisherBeanId.setAction("");
					response.sendRedirect(
							"Productlist.jsp?offset=" + 0 + "&catalog_id=" + authorizationPageBeanId.getCatalog_id());
				} else {
					productPostAllFaced.updateDescSoft(publisherBeanId, authorizationPageBeanId);
					publisherBeanId.setAction("");
					response.sendRedirect("Productlist.jsp?offset=" + authorizationPageBeanId.getOffsetLastPage()
							+ "&catalog_id=" + authorizationPageBeanId.getCatalog_id());
				}
			}

		} else
			publisherBeanId.setAction("");

	}

}
