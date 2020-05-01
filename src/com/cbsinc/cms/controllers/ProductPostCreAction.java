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

public class ProductPostCreAction implements IAction {

	private ProductPostAllFaced productPostAllFaced;
	private boolean is_criteria_by_catalog = false;
	private String notselected = "";
	private transient ResourceBundle setup_resources = null;

	public ProductPostCreAction() {

		if (setup_resources == null)
			setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources");
		is_criteria_by_catalog = setup_resources.getString("is_criteria_by_catalog").equals("true");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {

		HttpSession session = request.getSession();
		PublisherBean publisherBeanId = (PublisherBean) session.getAttribute("publisherBeanId");
		CatalogListBean catalogListBeanId = (CatalogListBean) session.getAttribute("catalogListBeanId");
		CatalogEditBean catalogEditBeanId = (CatalogEditBean) session.getAttribute("catalogEditBeanId");
		CatalogAddBean catalogAddBeanId = (CatalogAddBean) session.getAttribute("catalogAddBeanId");
		AuthorizationPageBean authorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");

		action(request, response, servletContext);

		if (request.getParameter("show_rating1") != null) {
			publisherBeanId.setStrShow_ratimg1_checked("CHECKED");
			publisherBeanId.setStrShow_ratimg1("true");
		} else {
			publisherBeanId.setStrShow_ratimg1_checked("");
			publisherBeanId.setStrShow_ratimg1("false");
		}

		if (request.getParameter("show_blog") != null) {
			publisherBeanId.setShow_forum_checked("CHECKED");
			publisherBeanId.setStrShow_forum("true");
		} else {
			publisherBeanId.setShow_forum_checked("");
			publisherBeanId.setStrShow_forum("false");
		}

		if (request.getParameter("action") != null) {
			publisherBeanId.setAction(request.getParameter("action"));

			if (publisherBeanId.getAction().compareTo("add") == 0) {

				if (request.getParameter("name") != null) {
					catalogAddBeanId.setName(request.getParameter("name"));
				} else
					catalogAddBeanId.setName("");

				if (request.getMethod().toUpperCase().compareTo("POST") == 0) {
					catalogAddBeanId.addCatalog(authorizationPageBeanId);
					publisherBeanId.setAction("");
					response.sendRedirect("ProductPostCre.jsp");
					return;
				}
			}

			if (publisherBeanId.getAction().compareTo("edit") == 0) {

				if (request.getParameter("row") != null) {
					int index = catalogListBeanId.stringToInt(request.getParameter("row"));
					catalogEditBeanId.setIndx_select(index);
				}
				if (request.getParameter("name") != null) {
					catalogEditBeanId.setName(request.getParameter("name"));
				}

				if (request.getParameter("catalog_id") != null) {
					authorizationPageBeanId.setCatalog_id(request.getParameter("catalog_id"));
				}

				if (request.getMethod().toUpperCase().compareTo("POST") == 0) {
					catalogEditBeanId.editCatalog(authorizationPageBeanId);
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
						authorizationPageBeanId.setStrMessage(authorizationPageBeanId.getLocalization(servletContext).getString("global_has_limmit_forsite"));
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
					// request.
				}
			}

		} else
			publisherBeanId.setAction("");
//				 end novigator action ---

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {

		HttpSession session = request.getSession();
		PublisherBean publisherBeanId = (PublisherBean) session.getAttribute("publisherBeanId");
		CatalogListBean catalogListBeanId = (CatalogListBean) session.getAttribute("catalogListBeanId");
		CatalogEditBean catalogEditBeanId = (CatalogEditBean) session.getAttribute("catalogEditBeanId");
		CatalogAddBean catalogAddBeanId = (CatalogAddBean) session.getAttribute("catalogAddBeanId");
		AuthorizationPageBean authorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
		ProductPostAllFaced productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced().get();
		
		action(request, response, servletContext);
		
		productPostAllFaced.initPage(request.getParameter("product_id"), publisherBeanId, authorizationPageBeanId);

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

				if (request.getParameter("name") != null) {
					catalogAddBeanId.setName(request.getParameter("name"));
				} else
					catalogAddBeanId.setName("");

				if (request.getMethod().toUpperCase().compareTo("POST") == 0) {
					catalogAddBeanId.addCatalog(authorizationPageBeanId);
					publisherBeanId.setAction("");
					response.sendRedirect("ProductPostCre.jsp");
					return;
				}
			}

			if (publisherBeanId.getAction().compareTo("edit") == 0) {

				if (request.getParameter("row") != null) {
					int index = catalogListBeanId.stringToInt(request.getParameter("row"));
					catalogEditBeanId.setIndx_select(index);
				}
				if (request.getParameter("name") != null) {
					catalogEditBeanId.setName(request.getParameter("name"));
				}

				if (request.getParameter("catalog_id") != null) {
					authorizationPageBeanId.setCatalog_id(request.getParameter("catalog_id"));
				}

				if (request.getMethod().toUpperCase().compareTo("POST") == 0) {
					catalogEditBeanId.editCatalog(authorizationPageBeanId);
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
		publisherBeanId.setNameOfPage("ProductPostCre.jsp");
		if (jsf_admin)
			response.sendRedirect("admin.jsf");
	}

	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		HttpSession session = request.getSession();
		PublisherBean publisherBeanId = (PublisherBean) session.getAttribute("publisherBeanId");
		CatalogListBean catalogListBeanId = (CatalogListBean) session.getAttribute("catalogListBeanId");
		AuthorizationPageBean authorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");

		productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced().get();
		if (publisherBeanId == null || catalogListBeanId == null || authorizationPageBeanId == null
				|| productPostAllFaced == null)
			return;

		if (notselected.length() == 0)
			notselected = authorizationPageBeanId.getLocalization(servletContext).getString("notselected");

		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0);

		publisherBeanId.setSite_id(authorizationPageBeanId.getSite_id());
//		 Start Novigator ---
		if (request.getParameter("parent_id") != null) {
			authorizationPageBeanId.setCatalogParent_id(request.getParameter("parent_id"));
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
		} else
			catalogListBeanId.setOffset(0);

//		 End Novigator ---

//		 start novigator action ---

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

		String softname = request.getParameter("softname");
		if (softname != null) {
			publisherBeanId.setStrSoftName(softname);
		}

		String softname2 = request.getParameter("softname2");
		if (softname2 != null) {
			publisherBeanId.setStrSoftName2(softname2);
		}

		String jsp_url = request.getParameter("jsp_url");
		if (jsp_url != null) {
			publisherBeanId.setJsp_url(jsp_url);
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

		String file_id = request.getParameter("file_id");
		if (file_id != null) {
			publisherBeanId.setFile_id(file_id);
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
			authorizationPageBeanId.setStrMessage(
					authorizationPageBeanId.getLocalization(servletContext).getString("post_message_notaccess_admin"));
			response.sendRedirect("Authorization.jsp");
		}

	}

}
