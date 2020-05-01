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

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.tomcat.util.http.mapper.Mapper;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.CatalogListBean;
import com.cbsinc.cms.ProductlistBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.faceds.PolicyFaced;
import com.cbsinc.cms.faceds.ProductPostAllFaced;
import com.cbsinc.cms.faceds.ProductlistFaced;
import com.cbsinc.cms.jms.controllers.MessageSender;
import com.cbsinc.cms.jms.controllers.Message;
import com.cbsinc.cms.jms.controllers.SendMailMessageBean;

public class ProductlistAction implements IAction {

	private AuthorizationPageFaced authorizationPageFaced;

	public ProductlistAction() {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		doGet(request, response, servletContext);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		ProductlistBean productlistBeanId;
		AuthorizationPageBean authorizationPageBeanId;
		HttpSession session;
		boolean is_criteria_by_catalog = false;
		String notselected = "";
		boolean isInternet = true;
		ServiceLocator.getInstance().getProductPostAllFaced();
		authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced().get();

		is_criteria_by_catalog = authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog")
				.equals("true");

		if (request.getRemoteAddr().startsWith("192."))
			isInternet = false;
		if (request.getRemoteAddr().startsWith("10."))
			isInternet = false;
		session = request.getSession();
		authorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");

		if (notselected.length() == 0)
			notselected = authorizationPageBeanId.getLocalization(servletContext).getString("notselected");

		ProductlistFaced productlistFaced = ServiceLocator.getInstance().getProductlistFaced().get();
		PolicyFaced policyFaced = ServiceLocator.getInstance().getPolicyFaced().get();
		productlistBeanId = new ProductlistBean();
		request.setAttribute("productlistBeanId", productlistBeanId);

		if (policyFaced == null || productlistBeanId == null || authorizationPageBeanId == null
				|| authorizationPageFaced == null)
			return;

		request.setCharacterEncoding("UTF-8");

		productlistBeanId.isInternet = isInternet;
		authorizationPageBeanId.setBalance(ServiceLocator.getInstance().getAuthorizationPageFaced().get()
				.getStrBalans(authorizationPageBeanId.getIntUserID()));

		if (request.getParameter("offset") != null && isNumber(request.getParameter("offset"))) {
			productlistBeanId.setOffset(productlistBeanId.stringToInt(request.getParameter("offset")));
			authorizationPageBeanId.setOffsetLastPage(Long.parseLong(request.getParameter("offset")));
		}

		if (request.getParameter("catalog_id") != null && isNumber(request.getParameter("catalog_id"))) {
			if (authorizationPageBeanId.getCatalog_id().compareTo(request.getParameter("catalog_id")) != 0) {
				productlistBeanId.setOffset(0);
			}
			authorizationPageBeanId.setCatalog_id(request.getParameter("catalog_id"));
			authorizationPageBeanId.setCatalogParent_id(request.getParameter("catalog_id"));

		}
		productlistBeanId.setIntLevelUp(authorizationPageBeanId.getIntLevelUp());

		// Open or closed dialog window
		if (request.getParameter("dialog") != null)
			productlistBeanId.setDialog(request.getParameter("dialog"));
		if (request.getParameter("is_advanced_search_open") != null)
			productlistBeanId.setAdvancedSearchOpen(request.getParameter("is_advanced_search"));
		if (request.getParameter("is_forum_open") != null)
			productlistBeanId.setForumOpen(request.getParameter("is_forum_open"));

		if (request.getParameter("dayfrom_id") != null && isNumber(request.getParameter("dayfrom_id")))
			authorizationPageBeanId.setDayfrom_id(Integer.parseInt(request.getParameter("dayfrom_id")));
		if (request.getParameter("mountfrom_id") != null && isNumber(request.getParameter("mountfrom_id")))
			authorizationPageBeanId.setMountfrom_id(Integer.parseInt(request.getParameter("mountfrom_id")));
		if (request.getParameter("yearfrom_id") != null && isNumber(request.getParameter("yearfrom_id")))
			authorizationPageBeanId.setYearfrom_id(Integer.parseInt(request.getParameter("yearfrom_id")));

		if (request.getParameter("fromcost") != null && isFloat(request.getParameter("fromcost")))
			authorizationPageBeanId.setStrFromCost(request.getParameter("fromcost").trim());
		if (request.getParameter("tocost") != null && isFloat(request.getParameter("tocost")))
			authorizationPageBeanId.setStrToCost(request.getParameter("tocost").trim());

		if (request.getParameter("dayto_id") != null && isNumber(request.getParameter("dayto_id")))
			authorizationPageBeanId.setDayto_id(Integer.parseInt(request.getParameter("dayto_id")));
		if (request.getParameter("mountto_id") != null && isNumber(request.getParameter("mountto_id")))
			authorizationPageBeanId.setMountto_id(Integer.parseInt(request.getParameter("mountto_id")));
		if (request.getParameter("yearto_id") != null && isNumber(request.getParameter("yearto_id")))
			authorizationPageBeanId.setYearto_id(Integer.parseInt(request.getParameter("yearto_id")));

		if (request.getParameter("action") != null) {
			productlistBeanId.setAction(request.getParameter("action"));
		}
		if (request.getParameter("creteria1_id") != null && isNumber(request.getParameter("creteria1_id")))
			authorizationPageBeanId.setStrCreteria1_id(request.getParameter("creteria1_id"));
		if (request.getParameter("creteria2_id") != null && isNumber(request.getParameter("creteria2_id")))
			authorizationPageBeanId.setStrCreteria2_id(request.getParameter("creteria2_id"));
		if (request.getParameter("creteria3_id") != null && isNumber(request.getParameter("creteria3_id")))
			authorizationPageBeanId.setStrCreteria3_id(request.getParameter("creteria3_id"));
		if (request.getParameter("creteria4_id") != null && isNumber(request.getParameter("creteria4_id")))
			authorizationPageBeanId.setStrCreteria4_id(request.getParameter("creteria4_id"));
		if (request.getParameter("creteria5_id") != null && isNumber(request.getParameter("creteria5_id")))
			authorizationPageBeanId.setStrCreteria5_id(request.getParameter("creteria5_id"));
		if (request.getParameter("creteria6_id") != null && isNumber(request.getParameter("creteria6_id")))
			authorizationPageBeanId.setStrCreteria6_id(request.getParameter("creteria6_id"));
		if (request.getParameter("creteria7_id") != null && isNumber(request.getParameter("creteria7_id")))
			authorizationPageBeanId.setStrCreteria7_id(request.getParameter("creteria7_id"));
		if (request.getParameter("creteria8_id") != null && isNumber(request.getParameter("creteria8_id")))
			authorizationPageBeanId.setStrCreteria8_id(request.getParameter("creteria8_id"));
		if (request.getParameter("creteria9_id") != null && isNumber(request.getParameter("creteria9_id")))
			authorizationPageBeanId.setStrCreteria9_id(request.getParameter("creteria9_id"));
		if (request.getParameter("creteria10_id") != null && isNumber(request.getParameter("creteria10_id")))
			authorizationPageBeanId.setStrCreteria10_id(request.getParameter("creteria10_id"));
		if (request.getParameter("search_value") != null)
			productlistBeanId.setSearchValueArg(request.getParameter("search_value"));
		if (request.getParameter("searchquery") != null && isNumber(request.getParameter("searchquery")))
			productlistBeanId.setSearchquery(Integer.parseInt(request.getParameter("searchquery")));
		else if (request.getParameter("offset") == null)
			productlistBeanId.setSearchquery(0);

		String cl_locale = request.getParameter("locale");
		if (cl_locale != null) {

			if (isAllowLocale(cl_locale)) {

				if (authorizationPageBeanId.getLocale().compareTo(cl_locale) != 0) {
					productlistBeanId.setOffset(0);
					authorizationPageBeanId.setCatalog_id("" + SpecialCatalog.OUTPUT_PAGES_SORT_BY_SOFT_ID);
				}

				authorizationPageBeanId.setLocale(cl_locale, servletContext);
			}

		}

		if (productlistBeanId.getSearchquery() == 2 && request.getParameter("search_char") != null
				&& request.getParameter("search_char").length() > 0) {
			// if new char then start with 0
			if (productlistBeanId.getSearchValueArg().compareTo(request.getParameter("search_char")) != 0) {
				productlistBeanId.setOffset(0);
			}

			productlistBeanId.setSearchValueArg(request.getParameter("search_char"));
		} else if (productlistBeanId.getSearchquery() == 1 && request.getParameter("search_value") != null
				&& request.getParameter("search_value").length() > 0) {
			productlistBeanId.setSearchValueArg(request.getParameter("search_value"));
		} else if (productlistBeanId.getSearchquery() == 0) {
			productlistBeanId.setSearchValueArg("");
		}

		if (productlistBeanId.getAction().compareTo("del") == 0) {
			productlistBeanId.setAction("");
			if (authorizationPageBeanId.getIntLevelUp() == 0) {
				authorizationPageBeanId.setStrMessage("Access denny  for login user , You must be membership");
				// response.sendRedirect("Authorization.jsp" );
				servletContext.getRequestDispatcher("/Authorization.jsp").forward(request, response);
			}

			if (request.getParameter("product_id") != null && isNumber(request.getParameter("product_id"))) {

				if (authorizationPageBeanId.getIntLevelUp() != 2) {
					int user_id = productlistFaced.getWhoseProduct(request.getParameter("product_id"));
					if (authorizationPageBeanId.getIntUserID() != user_id) {
						authorizationPageBeanId.setStrMessage(
								"Access denny  for login " + authorizationPageBeanId + " , You must be owner message");
						// response.sendRedirect("Authorization.jsp" );
						servletContext.getRequestDispatcher("/Authorization.jsp").forward(request, response);
					}
				}

				if (authorizationPageBeanId.getSite_id()
						.compareTo(productlistFaced.getSiteByProduct(request.getParameter("product_id"))) == 0)
					productlistFaced.deletePosition(request.getParameter("product_id"));
				else
					authorizationPageBeanId.setStrMessage(
							"Access denny  for login " + authorizationPageBeanId + " , You must be owner message");
			}
		}

		if (productlistBeanId.getAction().compareTo("up_position") == 0) {
			int user_id = 0;
			productlistBeanId.setAction("");
			if (authorizationPageBeanId.getIntLevelUp() == 0) {
				authorizationPageBeanId.setStrMessage("Access denny  for login user , You must be membership");
				// response.sendRedirect("Authorization.jsp" );
				servletContext.getRequestDispatcher("/Authorization.jsp").forward(request, response);
				return;
			}

			if (request.getParameter("product_id") != null) {

				if (authorizationPageBeanId.getIntLevelUp() != 2) {
					user_id = productlistFaced.getWhoseProduct(request.getParameter("product_id"));
					if (user_id == -1)
						return; // Пользователя для этой записи не сущетсвует
					if (authorizationPageBeanId.getIntUserID() != user_id) {
						authorizationPageBeanId.setStrMessage("Access denny  for login "
								+ authorizationPageBeanId.getStrLogin() + " , You must be owner message");
						// response.sendRedirect("Authorization.jsp" );
						servletContext.getRequestDispatcher("/Authorization.jsp").forward(request, response);
						return;
					}
				}

				/*
				 * float balans = authorizationPageFaced.getBalans(user_id) ; float cost =
				 * productlistFaced.getProductCost(request.getParameter("product_id")) ; if(cost
				 * > balans) { authorizationPageBeanId.setStrMessage("Access denny  for login "+
				 * authorizationPageBeanId.getStrLogin() +" , You must be owner message") ;
				 * response.sendRedirect("Authorization.jsp" ); return ; }
				 */

				productlistFaced.upPosition(request.getParameter("product_id"));
			}

			// request.setAttribute("product_id",null);
			// request.setAttribute("action",null);
		}

		if (productlistBeanId.getAction().compareTo("set_color") == 0) {
			productlistBeanId.setAction("");
			if (authorizationPageBeanId.getIntLevelUp() == 0) {
				authorizationPageBeanId.setStrMessage("Access denny  for login user , You must be membership");
				// response.sendRedirect("Authorization.jsp" );
				servletContext.getRequestDispatcher("/Authorization.jsp").forward(request, response);
				return;
			}

			if (request.getParameter("product_id") != null) {

				if (authorizationPageBeanId.getIntLevelUp() != 2) {
					int user_id = productlistFaced.getWhoseProduct(request.getParameter("product_id"));
					if (authorizationPageBeanId.getIntUserID() != user_id) {
						authorizationPageBeanId.setStrMessage(
								"Access denny  for login " + authorizationPageBeanId + " , You must be owner message");
						// response.sendRedirect("Authorization.jsp" );
						servletContext.getRequestDispatcher("/Authorization.jsp").forward(request, response);
						return;
					}
				}
				productlistFaced.colorPosition(request.getParameter("product_id"), "#FCF8CF");
			}
		}

		if (productlistBeanId.getAction().compareTo("edit") == 0) {

			if (isNumber(request.getParameter("product_id"))) {
				productlistBeanId.setAction("");

				// authorizationPageBeanId.setCatalog_id(productlistFaced.getCatalogId(request.getParameter("product_id")));

//		        	  productPostAllFaced.initPage(request.getParameter("product_id"),  SoftPostBeanId , authorizationPageBeanId);
				// Для того чтобы правильно отрывался раздел когда открываеш из другово раздела
				// authorizationPageBeanId.setCatalogParent_id("" +
				// productlistFaced.getCatalogParentId(authorizationPageBeanId));

				if (request.getParameter("element").compareTo("forum") == 0) {
					response.sendRedirect("ProductUserPost.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("userinfo") == 0) {
					response.sendRedirect(
							"ProductUserPostWithOutCatalog.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("edit_biz_info") == 0) {
					response.sendRedirect(
							"ProductUserPostBusiness.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("edit_transport") == 0) {
					response.sendRedirect(
							"ProductUserPostTransport.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("edit_realty") == 0) {
					response.sendRedirect("ProductUserPostRealty.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("addvideo") == 0) {
					response.sendRedirect("ProductUserPostVideo.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("addmusics") == 0) {
					response.sendRedirect("ProductUserPostMusic.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("ext1_user") == 0) {
					response.sendRedirect("Ext1ProductPostUser.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("ext2_user") == 0) {
					response.sendRedirect("Ext2ProductPostUser.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("ext_files_user") == 0) {
					response.sendRedirect(
							"ExtFilesProductPostUser.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("ext_ofice_files_user") == 0) {
					response.sendRedirect(
							"ExtOficeFilesProductPostUser.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("ext_music_files_user") == 0) {
					response.sendRedirect(
							"ExtOficeFilesProductPostUser.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("product") == 0) {
					response.sendRedirect("ProductPostCre.jsp?product_id=" + request.getParameter("product_id")
							+ "&parent_id=" + authorizationPageBeanId.getCatalog_id());
					return;
				}
				if (request.getParameter("element").compareTo("news") == 0) {
					response.sendRedirect("NewsBlockPostCre.jsp?product_id=" + request.getParameter("product_id")
							+ "&parent_id=" + authorizationPageBeanId.getCatalog_id());
					return;
				}
				// if(request.getParameter("element").compareTo("news") == 0 )
				// response.sendRedirect("ProductPost.jsp") ;
				if (request.getParameter("element").compareTo("co1") == 0) {
					response.sendRedirect("Co1ProductPost.jsp?product_id=" + request.getParameter("product_id")
							+ "&parent_id=" + authorizationPageBeanId.getCatalog_id());
					return;
				}
				if (request.getParameter("element").compareTo("co2") == 0) {
					response.sendRedirect("Co2ProductPost.jsp?product_id=" + request.getParameter("product_id")
							+ "&parent_id=" + authorizationPageBeanId.getCatalog_id());
					return;
				}
				if (request.getParameter("element").compareTo("bottom") == 0) {
					response.sendRedirect("BottomListPost.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("ext1") == 0) {
					response.sendRedirect("Ext1ProductPost.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("ext2") == 0) {
					response.sendRedirect("Ext2ProductPost.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("ext_files") == 0) {
					response.sendRedirect("ExtFilesProductPost.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("ext_tabls") == 0) {
					response.sendRedirect("ExtTabsProductPost.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("ext_service_page") == 0) {
					response.sendRedirect("ServicePagePost.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("blog") == 0) {
					authorizationPageBeanId.setLastProductId(Long.parseLong(request.getParameter("product_parent_id")));
					response.sendRedirect("BlogExtProductPost.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
//		        	  {
//		        		 policyFaced.mergePolicyBean(authorizationPageBeanId.getIntUserID(), request.getParameter("product_parent_id") , policyBeanId) ;
//		        		 policyBeanId.setType_page("product_parent_id") ;
//		        		 policyBeanId.setBack_url("Policy.jsp?policy_byproductid=" + request.getParameter("product_parent_id")) ;
//		        		 policyBeanId.setIntUserID(authorizationPageBeanId.getIntUserID());
//		        		 response.sendRedirect("BlogExtProductPost.jsp?product_id="+request.getParameter("product_id"));   
//		        		 return ;
//		        	  }

			}
		}
//        else
//       {
//        	SoftPostBeanId.setSoft_id("-1") ;
//       }

		if (productlistBeanId.getAction().compareTo("create_site") == 0) {
			productlistBeanId.setAction("");
			authorizationPageFaced.getCreateShopBean().setLogin(authorizationPageBeanId.getStrLogin());
			authorizationPageFaced.getCreateShopBean().setPasswd(authorizationPageBeanId.getStrPasswd());
			authorizationPageFaced.getCreateShopBean().setAddress("no created");

			String domain = authorizationPageBeanId.getSite_dir()
					.substring(authorizationPageBeanId.getSite_dir().indexOf("."));

			authorizationPageFaced.getCreateShopBean().setCompany_name(authorizationPageBeanId.getStrCompany());
			authorizationPageFaced.getCreateShopBean().setSite_dir(authorizationPageBeanId.getStrLogin() + domain);
			authorizationPageFaced.getCreateShopBean().setNick_site(authorizationPageBeanId.getStrLogin() + domain);
			authorizationPageFaced.getCreateShopBean().setSubject_site("Cabinet");
			authorizationPageFaced.getCreateShopBean().setPerson(
					authorizationPageBeanId.getStrFirstName() + " " + authorizationPageBeanId.getStrLastName());
			authorizationPageFaced.getCreateShopBean().setPhone(authorizationPageBeanId.getStrPhone());

			if (authorizationPageBeanId.getIntLevelUp() == 0) {
				authorizationPageBeanId.setStrMessage(authorizationPageBeanId.getLocalization(servletContext)
						.getString("you_can_not_to_create_shop"));
				// response.sendRedirect("Authorization.jsp?Login=newuser" );
				servletContext.getRequestDispatcher("/Authorization.jsp?Login=newuser").forward(request, response);
				return;
			}
			authorizationPageFaced.getCreateShopBean().addSite(authorizationPageBeanId.getIntUserID());
			servletContext
					.getRequestDispatcher(
							"/Authorization.jsp?site_id=" + authorizationPageFaced.getCreateShopBean().getSite_id()
									+ "&Login=" + authorizationPageFaced.getCreateShopBean().getLogin() + "&Passwd1="
									+ authorizationPageFaced.getCreateShopBean().getPasswd())
					.forward(request, response);
			return;
			// response.sendRedirect("Authorization.jsp?site_id=" +
			// authorizationPageFaced.getCreateShopBean().getSite_id() + "&Login=" +
			// authorizationPageFaced.getCreateShopBean().getLogin()+ "&Passwd1=" +
			// authorizationPageFaced.getCreateShopBean().getPasswd() );

		}

		// if( productlistBeanId.getAction().compareTo("create_site2") == 0 )
		if (request.getParameter("create_site_by_id") != null) {
			productlistBeanId.setAction("");
			authorizationPageFaced.getCreateShopBean().setLogin(authorizationPageBeanId.getStrLogin());
			authorizationPageFaced.getCreateShopBean().setPasswd(authorizationPageBeanId.getStrPasswd());
			authorizationPageFaced.getCreateShopBean().setAddress("no created");

			String domain = authorizationPageBeanId.getSite_dir()
					.substring(authorizationPageBeanId.getSite_dir().indexOf("."));

			authorizationPageFaced.getCreateShopBean().setCompany_name(authorizationPageBeanId.getStrCompany());
			authorizationPageFaced.getCreateShopBean().setSite_dir(authorizationPageBeanId.getStrLogin() + domain);
			authorizationPageFaced.getCreateShopBean()
					.setHost("www." + authorizationPageBeanId.getStrLogin() + ".irr.bz");
			authorizationPageFaced.getCreateShopBean().setNick_site(authorizationPageBeanId.getStrLogin() + domain);
			authorizationPageFaced.getCreateShopBean().setSubject_site("internet shop");
			authorizationPageFaced.getCreateShopBean().setPerson(
					authorizationPageBeanId.getStrFirstName() + " " + authorizationPageBeanId.getStrLastName());
			authorizationPageFaced.getCreateShopBean().setPhone(authorizationPageBeanId.getStrPhone());

			if (authorizationPageBeanId.getIntLevelUp() == 0) {
				authorizationPageBeanId.setStrMessage(authorizationPageBeanId.getLocalization(servletContext)
						.getString("you_can_not_to_create_shop"));
				servletContext.getRequestDispatcher("/Authorization.jsp?Login=").forward(request, response);
				// response.sendRedirect("Authorization.jsp?Login=" );
				return;
			}

			if (authorizationPageBeanId.getLang_id() == 1)
				authorizationPageFaced.getCreateShopBean().addShopWithExtract(authorizationPageBeanId,
						request.getParameter("create_site_by_id"), servletContext);
			else
				authorizationPageFaced.getCreateShopBean().addShopWithExtract_en(authorizationPageBeanId,
						request.getParameter("create_site_by_id"), servletContext);

			// authorizationPageFaced.getCreateShopBean().addShopWithExtract_allLang(authorizationPageBeanId,request.getParameter("create_site_by_id"),servletContext);

			if (authorizationPageBeanId.getUser_site().equals("-1")) {
				HashMap messageMail = (HashMap) session.getAttribute("messageMail");
				String sitePath = (String) request.getSession().getAttribute("site_path");
				String shop = sitePath + File.separatorChar + "mail" + File.separatorChar + "newshop.txt";
				String policy = sitePath + File.separatorChar + "mail" + File.separatorChar + "Policy.pdf";
				messageMail.put("@FirstName", authorizationPageBeanId.getStrFirstName());
				messageMail.put("@LastName", authorizationPageBeanId.getStrLastName());
				messageMail.put("@EmailPassword", authorizationPageBeanId.getEmailPassword());
				messageMail.put("@Password", authorizationPageBeanId.getStrPasswd());
				messageMail.put("@Login", authorizationPageBeanId.getStrLogin());
				// messageMail.put("@Shop", "http://www.siteforyou.net/Productlist.jsp?site=" +
				// authorizationPageFaced.getCreateShopBean().getSite_id() ) ;
				// messageMail.put("@Policy", "http://www.siteforyou.net/Policy.pdf" ) ;

				MessageSender mqSender = new MessageSender(request.getSession(), SendMailMessageBean.messageQuery);
				Message message = new Message();
				message.put("to", authorizationPageBeanId.getStrEMail());
				message.put("subject", "My Internet shop ");
				message.put("pathmessage", shop);
				message.put("fields", messageMail);
				// message.put("@Policy", "http://www.siteoneclick.com/Policy.pdf" ) ;
				// message.put("attachFile", policy ) ;
				mqSender.send(message);
			}

			// authorizationPageBeanId.
			authorizationPageBeanId.setUser_site(authorizationPageFaced.getCreateShopBean().getSite_id());

			// get current tomcat server, engine and context objects
			MBeanServer mBeanServer = MBeanServerFactory.findMBeanServer(null).get(0);
			ObjectName name = new ObjectName("Catalina", "type", "Server");
			Server server = (Server) mBeanServer.getAttribute(name, "managedResource");
			Service[] services = server.findServices();
			StandardEngine engine = (StandardEngine) services[0].getContainer();
			StandardContext context = (StandardContext) engine.findChild(engine.getDefaultHost())
					.findChild(servletContext.getContextPath());
			Mapper mapper = context.getMapper();
			mapper.addHostAlias(engine.getDefaultHost(), authorizationPageFaced.getCreateShopBean().getHost());

			// response.sendRedirect("Authorization.jsp?site_id=" +
			// authorizationPageFaced.getCreateShopBean().getSite_id() + "&Login=" +
			// authorizationPageFaced.getCreateShopBean().getLogin()+ "&Passwd1=" +
			// authorizationPageFaced.getCreateShopBean().getPasswd() );
			response.sendRedirect("Productlist.jsp?action=login_usersite");
			return;
		}

		if (productlistBeanId.getAction().compareTo("login_usersite") == 0) {
			productlistBeanId.setAction("");
			authorizationPageBeanId.setCatalog_id("-2");
			if (authorizationPageBeanId.getUser_site().compareTo("-1") != 0) {
				String cokie_session_id = (String) session.getAttribute("cokie_session_id");
				authorizationPageFaced.clearCookieFromBD(authorizationPageBeanId, cokie_session_id);
				authorizationPageBeanId.setSite_id(authorizationPageBeanId.getUser_site(), authorizationPageFaced);
				String session_id = authorizationPageFaced.getCokieSessionId((HttpServletRequest) request,
						(HttpServletResponse) response);
				if (authorizationPageFaced.isLoginCorrect(authorizationPageBeanId.getStrLogin(),
						authorizationPageBeanId.getStrPasswd(), authorizationPageBeanId, session_id)
						&& authorizationPageBeanId.getStrLogin().length() != 0) {
					// response.sendRedirect("Productlist.jsp?offset=0" );
					// servletContext.getRequestDispatcher("/Productlist.jsp?offset=0").forward(
					// request, response);
					productlistBeanId.setOffset(0);
				}
			} else
				authorizationPageBeanId.setStrMessage(
						authorizationPageBeanId.getLocalization(servletContext).getString("you_not_have_site"));

		}

///////+++
		if (request.getParameter("site") != null && isNumber(request.getParameter("site"))) {

			productlistBeanId.setAction("");
			String site = request.getParameter("site");

			if (authorizationPageBeanId.getSite_id().compareTo(site) != 0) {
				productlistBeanId.setOffset(0);
				authorizationPageBeanId.setCatalog_id("" + SpecialCatalog.OUTPUT_PAGES_SORT_BY_SOFT_ID);
			}

			String cokie_session_id = (String) session.getAttribute("cokie_session_id");
			authorizationPageFaced.clearCookieFromBD(authorizationPageBeanId, cokie_session_id);
			authorizationPageBeanId.setSite_id(site, authorizationPageFaced);
			String session_id = authorizationPageFaced.getCokieSessionId((HttpServletRequest) request,
					(HttpServletResponse) response);
			if (authorizationPageFaced.isLoginCorrect(authorizationPageBeanId.getStrLogin(),
					authorizationPageBeanId.getStrPasswd(), authorizationPageBeanId, session_id)
					&& authorizationPageBeanId.getStrLogin().length() != 0) {
				// response.sendRedirect("Authorization.jsp?site_id=" +
				// authorizationPageFaced.getCreateShopBean().getSite_id() + "&Login=" +
				// authorizationPageFaced.getCreateShopBean().getLogin()+ "&Passwd1=" +
				// authorizationPageFaced.getCreateShopBean().getPasswd() );
				// productlistBeanId.setSite_id(request.getParameter("site"));
				// response.sendRedirect("Productlist.jsp?offset=0" );
				// servletContext.getRequestDispatcher("/Productlist.jsp?offset=0").forward(
				// request, response);
				productlistBeanId.setOffset(0);
			} else {
				authorizationPageBeanId.setStrPasswd(SiteRole.GUEST);
				authorizationPageBeanId.setStrLogin(SiteRole.GUEST);
				authorizationPageBeanId.setSite_id(site, authorizationPageFaced);
				if (authorizationPageFaced.isLoginCorrect(authorizationPageBeanId.getStrLogin(),
						authorizationPageBeanId.getStrPasswd(), authorizationPageBeanId, "")
						&& authorizationPageBeanId.getStrLogin().length() != 0) {
					// response.sendRedirect("Productlist.jsp?offset=0" );
					// servletContext.getRequestDispatcher("/Productlist.jsp?offset=0").forward(
					// request, response);
					productlistBeanId.setOffset(0);
					// return ;
					// response.sendRedirect("Authorization.jsp?site_id=" +
					// authorizationPageFaced.getCreateShopBean().getSite_id() + "&Login=" +
					// authorizationPageFaced.getCreateShopBean().getLogin()+ "&Passwd1=" +
					// authorizationPageFaced.getCreateShopBean().getPasswd() );
				}
			}

		}

///////+++//
		if (request.getParameter("logoff_site") != null) {
			productlistBeanId.setAction("");
			String cokie_session_id = (String) session.getAttribute("cokie_session_id");
			authorizationPageFaced.clearCookieFromBD(authorizationPageBeanId, cokie_session_id);
			authorizationPageBeanId.setStrPasswd(SiteRole.GUEST);
			authorizationPageBeanId.setStrLogin(SiteRole.GUEST);
			authorizationPageBeanId.setSite_id(request.getParameter("logoff_site"), authorizationPageFaced);
			if (authorizationPageFaced.isLoginCorrect(authorizationPageBeanId.getStrLogin(),
					authorizationPageBeanId.getStrPasswd(), authorizationPageBeanId, "")
					&& authorizationPageBeanId.getStrLogin().length() != 0) {

				// response.sendRedirect("Productlist.jsp?offset=0" );
				// servletContext.getRequestDispatcher("/Productlist.jsp?offset=0").forward(
				// request, response);
				productlistBeanId.setOffset(0);
				// return ;
				// response.sendRedirect("Authorization.jsp?site_id=" +
				// authorizationPageFaced.getCreateShopBean().getSite_id() + "&Login=" +
				// authorizationPageFaced.getCreateShopBean().getLogin()+ "&Passwd1=" +
				// authorizationPageFaced.getCreateShopBean().getPasswd() );
			}
		}

		if (productlistBeanId.getAction().compareTo("logoff") == 0
				|| productlistBeanId.getAction().compareTo("logoff_usersite") == 0) {
			String site_id = authorizationPageBeanId.getSite_id();
			String cokie_session_id = (String) session.getAttribute("cokie_session_id");
			session.invalidate();
			authorizationPageFaced.clearCookieFromBD(authorizationPageBeanId, cokie_session_id);
			// clearCookieFromBD(AuthorizationPageBean authorizationBean)
			response.sendRedirect(
					"Productlist.jsp?site=" + site_id + "&" + "locale=" + authorizationPageBeanId.getLocale());
			// servletContext.getRequestDispatcher("/Productlist.jsp?site=" + site_id + "&"
			// + "locale=" + authorizationPageBeanId.getLocale() ).forward( request,
			// response);
			return;
		}

		// if( productlistBeanId.getAction().compareTo("logoff") == 0 )
		// {
		// session.invalidate();
		// response.sendRedirect("index.jsp" );
		// return ;
		// }

		//// -- productlistBeanId.Adp = productlistFaced.getProductlist("" +
		//// authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id(),
		//// productlistBeanId ) ;
		productlistBeanId.Adp = productlistFaced.getProductlist("" + authorizationPageBeanId.getIntUserID(),
				authorizationPageBeanId.getSite_id(), Long.parseLong(authorizationPageBeanId.getCatalog_id()),
				productlistBeanId, authorizationPageBeanId);
		productlistFaced.getQuantityProducts(productlistBeanId);
		productlistBeanId.co1Adp = productlistFaced.getCoOneProductlist("" + authorizationPageBeanId.getIntUserID(),
				authorizationPageBeanId.getSite_id(), authorizationPageBeanId.getCatalog_id(), productlistBeanId,
				authorizationPageBeanId);
		productlistBeanId.co2Adp = productlistFaced.getCoTwoProductlist("" + authorizationPageBeanId.getIntUserID(),
				authorizationPageBeanId.getSite_id(), authorizationPageBeanId.getCatalog_id(), productlistBeanId,
				authorizationPageBeanId);
		productlistBeanId.newsAdp = productlistFaced.getNewslist("" + authorizationPageBeanId.getIntUserID(),
				authorizationPageBeanId.getSite_id(), authorizationPageBeanId);
		productlistBeanId.blogExtAdp = productlistFaced.getBlogTopProductlist(authorizationPageBeanId.getSite_id(),
				productlistBeanId, authorizationPageBeanId);

		productlistBeanId.bottomAdp = productlistFaced.getBottomlist("" + authorizationPageBeanId.getIntUserID(),
				authorizationPageBeanId.getSite_id(), authorizationPageBeanId);

		authorizationPageBeanId.setCatalogParent_id("" + productlistFaced.getCatalogParentId(authorizationPageBeanId));

		productlistBeanId.setCriteria1_label(productlistFaced
				.getOneLabel("select  label   from creteria1   where  active = true " + productlistBeanId
						.getPartCriteria(authorizationPageBeanId.getSite_id(), is_criteria_by_catalog)));
		productlistBeanId.setCriteria2_label(productlistFaced
				.getOneLabel("select  label   from creteria2   where  active = true " + productlistBeanId
						.getPartCriteria(authorizationPageBeanId.getSite_id(), is_criteria_by_catalog)));
		productlistBeanId.setCriteria3_label(productlistFaced
				.getOneLabel("select  label   from creteria3   where  active = true " + productlistBeanId
						.getPartCriteria(authorizationPageBeanId.getSite_id(), is_criteria_by_catalog)));
		productlistBeanId.setCriteria4_label(productlistFaced
				.getOneLabel("select  label   from creteria4   where  active = true " + productlistBeanId
						.getPartCriteria(authorizationPageBeanId.getSite_id(), is_criteria_by_catalog)));
		productlistBeanId.setCriteria5_label(productlistFaced
				.getOneLabel("select  label   from creteria5   where  active = true " + productlistBeanId
						.getPartCriteria(authorizationPageBeanId.getSite_id(), is_criteria_by_catalog)));
		productlistBeanId.setCriteria6_label(productlistFaced
				.getOneLabel("select  label   from creteria6   where  active = true " + productlistBeanId
						.getPartCriteria(authorizationPageBeanId.getSite_id(), is_criteria_by_catalog)));
		productlistBeanId.setCriteria7_label(productlistFaced
				.getOneLabel("select  label   from creteria7   where  active = true " + productlistBeanId
						.getPartCriteria(authorizationPageBeanId.getSite_id(), is_criteria_by_catalog)));
		productlistBeanId.setCriteria8_label(productlistFaced
				.getOneLabel("select  label   from creteria8   where  active = true " + productlistBeanId
						.getPartCriteria(authorizationPageBeanId.getSite_id(), is_criteria_by_catalog)));
		productlistBeanId.setCriteria9_label(productlistFaced
				.getOneLabel("select  label   from creteria9   where  active = true " + productlistBeanId
						.getPartCriteria(authorizationPageBeanId.getSite_id(), is_criteria_by_catalog)));
		productlistBeanId.setCriteria10_label(productlistFaced
				.getOneLabel("select  label   from creteria10   where  active = true " + productlistBeanId
						.getPartCriteria(authorizationPageBeanId.getSite_id(), is_criteria_by_catalog)));

		productlistBeanId.setSelect_currency_cd(productlistFaced.getXMLDBList("Productlist.jsp?currency_cd",
				"currencies", productlistBeanId.getCurrency_cd(),
				"SELECT currency_cd , currency_desc  FROM currency  WHERE active = true"));
		productlistBeanId.setSelect_tree_catalog(productlistFaced.getTreeXMLDBList("Productlist.jsp?catalog_id",
				"catalog", authorizationPageBeanId.getCatalog_id(),
				"select catalog_id , lable   from catalog   where  active = true and lang_id = "
						+ authorizationPageBeanId.getLang_id() + " and site_id = "
						+ authorizationPageBeanId.getSite_id() + " and parent_id = "
						+ authorizationPageBeanId.getCatalogParent_id(),
				"select catalog_id , lable   from catalog   where  active = true and parent_id = "
						+ authorizationPageBeanId.getCatalog_id()));

		productlistBeanId.setSelect_menu_catalog(productlistFaced.getMenuXMLDBList("Productlist.jsp?catalog_id", "menu",
				authorizationPageBeanId.getCatalog_id(),
				"select catalog_id , lable , parent_id  from catalog   where  active = true and parent_id = 0 and site_id = "
						+ authorizationPageBeanId.getSite_id() + " and lang_id = "
						+ authorizationPageBeanId.getLang_id()
						+ " or parent_id in (select catalog_id   from catalog   where  active = true and site_id = "
						+ authorizationPageBeanId.getSite_id() + "  and parent_id = 0 )"));


		productlistBeanId.setSelect_creteria1_id(productlistFaced.getXMLDBCriteriaListLocale(
				"Productlist.jsp?creteria1_id", "creteria1", "" + authorizationPageBeanId.getCreteria1_id(),
				notselected, "select creteria1_id , name   from creteria1   where  active = true " + productlistBeanId
						.getPartCriteria(authorizationPageBeanId.getSite_id(), is_criteria_by_catalog)));
		productlistBeanId.setSelect_creteria2_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria2_id",
						"creteria2", "" + authorizationPageBeanId.getCreteria2_id(), notselected,
						"select creteria2_id , name   from creteria2   where  active = true "
								+ productlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(),
										is_criteria_by_catalog)
								+ " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria1_id()
								+ " ) "));
		productlistBeanId.setSelect_creteria3_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria3_id",
						"creteria3", "" + authorizationPageBeanId.getCreteria3_id(), notselected,
						"select creteria3_id , name   from creteria3   where  active = true "
								+ productlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(),
										is_criteria_by_catalog)
								+ " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria2_id()
								+ " ) "));
		productlistBeanId
				.setSelect_creteria4_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria4_id",
						"creteria4", "" + authorizationPageBeanId.getCreteria4_id(), notselected,
						"select creteria4_id , name   from creteria4   where  active = true "
								+ productlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(),
										is_criteria_by_catalog)
								+ " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria3_id()
								+ " ) "));
		productlistBeanId
				.setSelect_creteria5_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria5_id",
						"creteria5", "" + authorizationPageBeanId.getCreteria5_id(), notselected,
						"select creteria5_id , name   from creteria5   where  active = true "
								+ productlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(),
										is_criteria_by_catalog)
								+ " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria4_id()
								+ " ) "));
		productlistBeanId
				.setSelect_creteria6_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria6_id",
						"creteria6", "" + authorizationPageBeanId.getCreteria6_id(), notselected,
						"select creteria6_id , name   from creteria6   where  active = true "
								+ productlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(),
										is_criteria_by_catalog)
								+ " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria5_id()
								+ " ) "));
		productlistBeanId
				.setSelect_creteria7_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria7_id",
						"creteria7", "" + authorizationPageBeanId.getCreteria7_id(), notselected,
						"select creteria7_id , name   from creteria7   where  active = true "
								+ productlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(),
										is_criteria_by_catalog)
								+ " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria6_id()
								+ " ) "));
		productlistBeanId
				.setSelect_creteria8_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria8_id",
						"creteria8", "" + authorizationPageBeanId.getCreteria8_id(), notselected,
						"select creteria8_id , name   from creteria8   where  active = true "
								+ productlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(),
										is_criteria_by_catalog)
								+ " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria7_id()
								+ " ) "));
		productlistBeanId
				.setSelect_creteria9_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria9_id",
						"creteria9", "" + authorizationPageBeanId.getCreteria9_id(), notselected,
						"select creteria9_id , name   from creteria9   where  active = true "
								+ productlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(),
										is_criteria_by_catalog)
								+ " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria8_id()
								+ " ) "));
		productlistBeanId.setSelect_creteria10_id(productlistFaced.getXMLDBCriteriaListLocale("Productlist.jsp?creteria10_id",
						"creteria10", "" + authorizationPageBeanId.getCreteria10_id(), notselected,
						"select creteria10_id , name   from creteria10   where  active = true "
								+ productlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(),
										is_criteria_by_catalog)
								+ " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria9_id()
								+ " ) "));

		productlistBeanId.setSelect_dayfrom_id(productlistFaced.getXMLListDateDay("Productlist.jsp?dayfrom_id",
				"dayfrom", "" + authorizationPageBeanId.getDayfrom_id()));
		productlistBeanId.setSelect_mountfrom_id(productlistFaced.getXMLListDateMount("Productlist.jsp?mountfrom_id",
				"mountfrom", "" + authorizationPageBeanId.getMountfrom_id()));
		productlistBeanId.setSelect_yearfrom_id(productlistFaced.getXMLListDateYear("Productlist.jsp?yearfrom_id",
				"yearfrom", "" + authorizationPageBeanId.getYearfrom_id()));

		productlistBeanId.setSelect_dayto_id(productlistFaced.getXMLListDateDay("Productlist.jsp?dayto_id", "dayto",
				"" + authorizationPageBeanId.getDayto_id()));
		productlistBeanId.setSelect_mountto_id(productlistFaced.getXMLListDateMount("Productlist.jsp?mountto_id",
				"mountto", "" + authorizationPageBeanId.getMountto_id()));
		productlistBeanId.setSelect_yearto_id(productlistFaced.getXMLListDateYear("Productlist.jsp?yearto_id", "yearto",
				"" + authorizationPageBeanId.getYearto_id()));

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

	public boolean isFloat(String tmp) {
		if (tmp == null)
			return false;
		String IntField = "0123456789.";
		for (int i = 0; i < tmp.length(); i++) {

			if (IntField.indexOf(tmp.charAt(i)) == -1) {
				if (tmp.charAt(i) != '-' && i != 0)
					return false;
			}
		}
		return true;
	}

	public boolean isAllowLocale(String locale) {
		if (locale == null)
			return false;
		String[] IntField = { "en", "ru" };
		for (int i = 0; i < IntField.length; i++) {
			if (IntField[i].compareTo(locale) == 0) {
				return true;
			}
		}
		return false;
	}

}
