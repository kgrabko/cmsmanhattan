package com.cbsinc.cms.controllers;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
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
import javax.servlet.http.HttpSession;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;

public class AuthorizationAction extends TemplateAction {


	@Override
	public void action(Optional<HttpServletRequest> requestOpts, Optional<HttpServletResponse> responseOpts, Optional<ServletContext> servletContextOpts) throws Exception {


		HttpServletResponse response = responseOpts.get() ;
		HttpServletRequest request  = requestOpts.get() ;
		AuthorizationPageFaced authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced().get() ;
		AuthorizationPageBean authorizationPageBeanId = getAuthorizationPageBean().get() ;
		Map messageMail = getMessageMail().get() ;
		ResourceBundle	locale_resource = PropertyResourceBundle.getBundle("localization", response.getLocale());

		
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0);

		if (request.getParameter("Login") != null)
			authorizationPageBeanId.setStrLogin(request.getParameter("Login"));
		if (request.getParameter("Passwd1") != null)
			authorizationPageBeanId.setStrPasswd(request.getParameter("Passwd1"));
		if (request.getParameter("Message") != null)
			authorizationPageBeanId.setStrMessage("" + request.getParameter("Message"));
		if (request.getParameter("site_id") != null) {
			authorizationPageBeanId.setSite_id(request.getParameter("site_id"), authorizationPageFaced);
		}

		messageMail.clear();
		messageMail.put("@FirstName", authorizationPageBeanId.getStrFirstName());
		messageMail.put("@LastName", authorizationPageBeanId.getStrLastName());
//		    messageMail.put("@NumberOfOrder", orderBeanId.getOrder_id()  ) ;
//		    messageMail.put("@ContactPerson", orderBeanId.getContact_person()  ) ;
		messageMail.put("@Balans", "" + authorizationPageFaced.getBalans(authorizationPageBeanId.getIntUserID()));
		messageMail.put("@Phone", authorizationPageBeanId.getStrPhone());
//		    messageMail.put("@Address", orderBeanId.getshipment_address() ) ;
		messageMail.put("@City", authorizationPageBeanId.getStrCity());
		messageMail.put("@Contry", authorizationPageBeanId.getStrCountry());
		messageMail.put("@CustomerEmail", authorizationPageBeanId.getStrEMail());
		messageMail.put("@CustomerFax", authorizationPageBeanId.getStrFax());

//		    messageMail.put("@CustomerCommentariy", orderBeanId.getshipment_description() ) ;
//		    messageMail.put("@ProductCount", "" +  orderBeanId.getProductsListSize(request)  ) ;
		String session_id = authorizationPageFaced.getCokieSessionId((HttpServletRequest) request,
				(HttpServletResponse) response);

		if (authorizationPageFaced.isLoginCorrect(authorizationPageBeanId.getStrLogin(),
				authorizationPageBeanId.getStrPasswd(), authorizationPageBeanId, session_id)
				&& authorizationPageBeanId.getStrLogin().length() != 0) {

			authorizationPageBeanId.setStrMessage(locale_resource.getString("reg.login1.text"));
			authorizationPageFaced.initUserSite(authorizationPageBeanId.getIntUserID(), authorizationPageBeanId);



			response.sendRedirect("Productlist.jsp?offset=0");
			return;

		}


		if (authorizationPageBeanId.getIntLogined() == 2 && authorizationPageBeanId.getRezalt_reg() == 0
				&& !authorizationPageBeanId.getStrPasswd().equals(SiteRole.GUEST_PASSWORD)) {
			authorizationPageBeanId.setStrMessage(locale_resource.getString("reg.login1.text") + " "
					+ authorizationPageBeanId.getStrLogin() + " " + locale_resource.getString("reg.login3.wrong"));
			authorizationPageBeanId.setIntUserID(SiteRole.GUEST_ID);
			authorizationPageBeanId.setIntLevelUp(SiteRole.GUEST_ROLE_ID);
			authorizationPageBeanId.setStrPasswd(SiteRole.GUEST_PASSWORD);
			authorizationPageBeanId.setStrLogin(SiteRole.GUEST);
		}

		authorizationPageBeanId.setSelect_city(authorizationPageFaced.getXMLDBList("Authorization.jsp?city_id", "city",
				authorizationPageBeanId.getCity_id(),
				"select  city_id , name  from  city where country_id =" + authorizationPageBeanId.getCountry_id()
						+ " and locale = '" + authorizationPageBeanId.getLocale() + "' "));
		authorizationPageBeanId.setSelect_country(authorizationPageFaced.getXMLDBList("Authorization.jsp?country_id",
				"country", authorizationPageBeanId.getCountry_id(),
				"select country_id ,name from country  where locale = '" + authorizationPageBeanId.getLocale() + "' "));
		authorizationPageBeanId.setSelect_currency(authorizationPageFaced.getXMLDBList("Authorization.jsp?currency_id",
				"currency", "3", "SELECT currency_id , currency_desc FROM currency  WHERE active = true"));
		authorizationPageBeanId.setSelect_site(authorizationPageFaced.getXMLDBList("Authorization.jsp?site_id", "site",
				authorizationPageBeanId.getSite_id(), "SELECT  site_id, host FROM  site WHERE  active = true"));
		authorizationPageBeanId.setSelect_menu_catalog(authorizationPageFaced.getMenuXMLDBList(
				"Productlist.jsp?catalog_id", "menu", authorizationPageBeanId.getCatalog_id(),
				"select catalog_id , lable , parent_id  from catalog   where  active = true and parent_id = 0 and site_id = "
						+ authorizationPageBeanId.getSite_id() + " and lang_id = "
						+ authorizationPageBeanId.getLang_id()
						+ " or parent_id in (select catalog_id   from catalog   where  active = true and site_id = "
						+ authorizationPageBeanId.getSite_id() + "  and parent_id = 0 )"));

	}

}
