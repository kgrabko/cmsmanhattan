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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.SearchBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.faceds.PolicyFaced;
import com.cbsinc.cms.faceds.ProductPostAllFaced;
import com.cbsinc.cms.faceds.ProductlistFaced;
import com.cbsinc.cms.jms.controllers.Message;
import com.cbsinc.cms.jms.controllers.MessageSender;
import com.cbsinc.cms.jms.controllers.SendMailMessageBean;

public class SearchAction implements IAction {

	ProductPostAllFaced productPostAllFaced;
	AuthorizationPageFaced authorizationPageFaced;

	public SearchAction() {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		doGet(request, response, servletContext);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		SearchBean searchBeanId;
		AuthorizationPageBean authorizationPageBeanId;
		HttpSession session;
		String notselected = "";
		boolean isInternet = true;
		productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced().get();
		authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced().get();

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
		searchBeanId = new SearchBean();
		request.setAttribute("searchBeanId", searchBeanId);

		if (policyFaced == null || searchBeanId == null || authorizationPageBeanId == null
				|| authorizationPageFaced == null)
			return;

		request.setCharacterEncoding("UTF-8");

		searchBeanId.isInternet = isInternet;
		authorizationPageBeanId.setBalance(ServiceLocator.getInstance().getAuthorizationPageFaced().get()
				.getStrBalans(authorizationPageBeanId.getIntUserID()));

		if (request.getParameter("offset") != null && isNumber(request.getParameter("offset"))) {
			searchBeanId.setOffset(searchBeanId.stringToInt(request.getParameter("offset")));
			authorizationPageBeanId.setOffsetLastPage(Long.parseLong(request.getParameter("offset")));
		}

		if (request.getParameter("catalog_id") != null && isNumber(request.getParameter("catalog_id"))) {
			if (authorizationPageBeanId.getCatalog_id().compareTo(request.getParameter("catalog_id")) != 0) {
				searchBeanId.setOffset(0);
			}
			authorizationPageBeanId.setCatalog_id(request.getParameter("catalog_id"));

		}
		searchBeanId.setIntLevelUp(authorizationPageBeanId.getIntLevelUp());

		// Open or closed dialog window
		if (request.getParameter("dialog") != null)
			searchBeanId.setDialog(request.getParameter("dialog"));
		if (request.getParameter("is_advanced_search_open") != null)
			searchBeanId.setAdvancedSearchOpen(request.getParameter("is_advanced_search"));
		if (request.getParameter("is_forum_open") != null)
			searchBeanId.setForumOpen(request.getParameter("is_forum_open"));

		if (request.getParameter("fromcost") != null && isFloat(request.getParameter("fromcost")))
			authorizationPageBeanId.setStrFromCost(request.getParameter("fromcost").trim());
		if (request.getParameter("tocost") != null && isFloat(request.getParameter("tocost")))
			authorizationPageBeanId.setStrToCost(request.getParameter("tocost").trim());

		if (request.getParameter("action") != null) {
			searchBeanId.setAction(request.getParameter("action"));
		}
		if (request.getParameter("search_value") != null)
			searchBeanId.setSearchValueArg(request.getParameter("search_value"));
		if (request.getParameter("searchquery") != null && isNumber(request.getParameter("searchquery")))
			searchBeanId.setSearchquery(Integer.parseInt(request.getParameter("searchquery")));
		else if (request.getParameter("offset") == null)
			searchBeanId.setSearchquery(0);

		String cl_locale = request.getParameter("locale");
		if (cl_locale != null) {

			if (isAllowLocale(cl_locale)) {

				if (authorizationPageBeanId.getLocale().compareTo(cl_locale) != 0) {
					searchBeanId.setOffset(0);
					authorizationPageBeanId.setCatalog_id("" + SpecialCatalog.OUTPUT_PAGES_SORT_BY_SOFT_ID);
				}

				authorizationPageBeanId.setLocale(cl_locale, servletContext);
			}

		}

		if (searchBeanId.getSearchquery() == 2 && request.getParameter("search_char") != null
				&& request.getParameter("search_char").length() > 0) {
			// if new char then start with 0
			if (searchBeanId.getSearchValueArg().compareTo(request.getParameter("search_char")) != 0) {
				searchBeanId.setOffset(0);
			}

			searchBeanId.setSearchValueArg(request.getParameter("search_char"));
		} else if (searchBeanId.getSearchquery() == 1 && request.getParameter("search_value") != null
				&& request.getParameter("search_value").length() > 0) {
			searchBeanId.setSearchValueArg(request.getParameter("search_value"));
		} else if (searchBeanId.getSearchquery() == 0) {
			searchBeanId.setSearchValueArg("");
		}

		if (searchBeanId.getAction().compareTo("del") == 0) {
			searchBeanId.setAction("");
			if (authorizationPageBeanId.getIntLevelUp() == 0) {
				authorizationPageBeanId.setStrMessage("Access denny  for login user , You must be membership");
				servletContext.getRequestDispatcher("/Authorization.jsp").forward(request, response);
			}

			if (request.getParameter("product_id") != null && isNumber(request.getParameter("product_id"))) {

				if (authorizationPageBeanId.getIntLevelUp() != 2) {
					int user_id = productlistFaced.getWhoseProduct(request.getParameter("product_id"));
					if (authorizationPageBeanId.getIntUserID() != user_id) {
						authorizationPageBeanId.setStrMessage(
								"Access denny  for login " + authorizationPageBeanId + " , You must be owner message");
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

		if (searchBeanId.getAction().compareTo("up_position") == 0) {
			int user_id = 0;
			searchBeanId.setAction("");
			if (authorizationPageBeanId.getIntLevelUp() == 0) {
				authorizationPageBeanId.setStrMessage("Access denny  for login user , You must be membership");
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
						servletContext.getRequestDispatcher("/Authorization.jsp").forward(request, response);
						return;
					}
				}


				productlistFaced.upPosition(request.getParameter("product_id"));
			}

		}

		if (searchBeanId.getAction().compareTo("set_color") == 0) {
			searchBeanId.setAction("");
			if (authorizationPageBeanId.getIntLevelUp() == 0) {
				authorizationPageBeanId.setStrMessage("Access denny  for login user , You must be membership");
				servletContext.getRequestDispatcher("/Authorization.jsp").forward(request, response);
				return;
			}

			if (request.getParameter("product_id") != null) {

				if (authorizationPageBeanId.getIntLevelUp() != 2) {
					int user_id = productlistFaced.getWhoseProduct(request.getParameter("product_id"));
					if (authorizationPageBeanId.getIntUserID() != user_id) {
						authorizationPageBeanId.setStrMessage(
								"Access denny  for login " + authorizationPageBeanId + " , You must be owner message");
						servletContext.getRequestDispatcher("/Authorization.jsp").forward(request, response);
						return;
					}
				}
				productlistFaced.colorPosition(request.getParameter("product_id"), "#FCF8CF");
			}
		}

		if (searchBeanId.getAction().compareTo("edit") == 0) {

			if (isNumber(request.getParameter("product_id"))) {
				searchBeanId.setAction("");


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
					response.sendRedirect("ProductPostCre.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("news") == 0) {
					response.sendRedirect("ProductPostCre.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("co1") == 0) {
					response.sendRedirect("Co1ProductPost.jsp?product_id=" + request.getParameter("product_id"));
					return;
				}
				if (request.getParameter("element").compareTo("co2") == 0) {
					response.sendRedirect("Co2ProductPost.jsp?product_id=" + request.getParameter("product_id"));
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

			}
		}

		if (searchBeanId.getAction().compareTo("create_site") == 0) {
			searchBeanId.setAction("");
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
				authorizationPageBeanId.setStrMessage("не регистрированный пользователь  не может создовать магазин");
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

		}

		if (request.getParameter("create_site_by_id") != null) {
			searchBeanId.setAction("");
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
			authorizationPageFaced.getCreateShopBean().setSubject_site("Cabinet");
			authorizationPageFaced.getCreateShopBean().setPerson(
					authorizationPageBeanId.getStrFirstName() + " " + authorizationPageBeanId.getStrLastName());
			authorizationPageFaced.getCreateShopBean().setPhone(authorizationPageBeanId.getStrPhone());

			if (authorizationPageBeanId.getIntLevelUp() == 0) {
				authorizationPageBeanId.setStrMessage(authorizationPageBeanId.getLocalization(servletContext)
						.getString("you_can_not_to_create_shop"));
				servletContext.getRequestDispatcher("/Authorization.jsp?Login=").forward(request, response);
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
				messageMail.put("@Shop", "http://www.siteoneclick.com/Productlist.jsp?site="
						+ authorizationPageFaced.getCreateShopBean().getSite_id());
				messageMail.put("@Policy", "http://www.siteoneclick.com/Policy.pdf");

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

			authorizationPageBeanId.setUser_site(authorizationPageFaced.getCreateShopBean().getSite_id());

			response.sendRedirect("Productlist.jsp?action=login_usersite");
			return;
		}

		if (searchBeanId.getAction().compareTo("login_usersite") == 0) {
			searchBeanId.setAction("");
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
					searchBeanId.setOffset(0);
				}
			} else
				authorizationPageBeanId.setStrMessage(
						authorizationPageBeanId.getLocalization(servletContext).getString("you_not_have_site"));

		}

///////+++
		if (request.getParameter("site") != null && isNumber(request.getParameter("site"))) {

			searchBeanId.setAction("");
			String site = request.getParameter("site");

			if (authorizationPageBeanId.getSite_id().compareTo(site) != 0) {
				searchBeanId.setOffset(0);
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
				searchBeanId.setOffset(0);
			} else {
				authorizationPageBeanId.setStrPasswd(SiteRole.GUEST);
				authorizationPageBeanId.setStrLogin(SiteRole.GUEST);
				authorizationPageBeanId.setSite_id(site, authorizationPageFaced);
				if (authorizationPageFaced.isLoginCorrect(authorizationPageBeanId.getStrLogin(),
						authorizationPageBeanId.getStrPasswd(), authorizationPageBeanId, "")
						&& authorizationPageBeanId.getStrLogin().length() != 0) {
					searchBeanId.setOffset(0);
				}
			}

		}

		if (request.getParameter("logoff_site") != null) {
			searchBeanId.setAction("");
			String cokie_session_id = (String) session.getAttribute("cokie_session_id");
			authorizationPageFaced.clearCookieFromBD(authorizationPageBeanId, cokie_session_id);
			authorizationPageBeanId.setStrPasswd(SiteRole.GUEST);
			authorizationPageBeanId.setStrLogin(SiteRole.GUEST);
			authorizationPageBeanId.setSite_id(request.getParameter("logoff_site"), authorizationPageFaced);
			if (authorizationPageFaced.isLoginCorrect(authorizationPageBeanId.getStrLogin(),
					authorizationPageBeanId.getStrPasswd(), authorizationPageBeanId, "")
					&& authorizationPageBeanId.getStrLogin().length() != 0) {

				searchBeanId.setOffset(0);
			}
		}

		if (searchBeanId.getAction().compareTo("logoff") == 0
				|| searchBeanId.getAction().compareTo("logoff_usersite") == 0) {
			String site_id = authorizationPageBeanId.getSite_id();
			String cokie_session_id = (String) session.getAttribute("cokie_session_id");
			session.invalidate();
			authorizationPageFaced.clearCookieFromBD(authorizationPageBeanId, cokie_session_id);
			servletContext.getRequestDispatcher("/Productlist.jsp?site=" + site_id).forward(request, response);
			return;
		}

		searchBeanId.Adp = productlistFaced.getSearchList("" + authorizationPageBeanId.getIntUserID(),
				authorizationPageBeanId.getSite_id(), Long.parseLong(authorizationPageBeanId.getCatalog_id()),
				searchBeanId, authorizationPageBeanId);
		productlistFaced.getQuantitySearch(searchBeanId);
		searchBeanId.co1Adp = productlistFaced.getCoOneSearchDirect("" + authorizationPageBeanId.getIntUserID(),
				authorizationPageBeanId.getSite_id(), authorizationPageBeanId.getCatalog_id(), searchBeanId,
				authorizationPageBeanId);
		searchBeanId.co2Adp = productlistFaced.getCoTwoSearchDirect("" + authorizationPageBeanId.getIntUserID(),
				authorizationPageBeanId.getSite_id(), authorizationPageBeanId.getCatalog_id(), searchBeanId,
				authorizationPageBeanId);

		authorizationPageBeanId.setCatalogParent_id("" + productlistFaced.getCatalogParentId(authorizationPageBeanId));

		searchBeanId.setSelect_currency_cd(productlistFaced.getXMLDBList("Productlist.jsp?currency_cd",
				"currencies", searchBeanId.getCurrency_cd(),
				"SELECT currency_cd , currency_desc  FROM currency  WHERE active = true"));

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
