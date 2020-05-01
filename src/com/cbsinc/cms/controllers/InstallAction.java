package com.cbsinc.cms.controllers;

import java.util.Map;

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
import com.cbsinc.cms.CreateShopBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;

public class InstallAction implements IAction {

	private HttpSession session;
	private Map messageMail;
	private AuthorizationPageBean authorizationPageBeanId;
	private AuthorizationPageFaced authorizationPageFaced;
	private CreateShopBean createShopBean = null;

	public InstallAction() {
		createShopBean = new CreateShopBean();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		action(request, response, servletContext);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		session = request.getSession();

		messageMail = (Map) session.getAttribute("messageMail");
		authorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
		if (authorizationPageFaced == null)
			authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced().get();

		authorizationPageBeanId.setSelect_city(authorizationPageFaced.getXMLDBList("Authorization.jsp?city_id", "city",
				authorizationPageBeanId.getCity_id(),
				"select  city_id , name  from  city where country_id =" + authorizationPageBeanId.getCountry_id()));
		authorizationPageBeanId.setSelect_country(authorizationPageFaced.getXMLDBList("Authorization.jsp?country_id",
				"country", authorizationPageBeanId.getCountry_id(), "select country_id ,name from country"));
		

	}

	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {

		session = request.getSession();
		messageMail = (Map) session.getAttribute("messageMail");
		authorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
		if (authorizationPageFaced == null)
			authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced().get();

		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0);


		if (request.getParameter("Login") != null)
			authorizationPageBeanId.setStrLogin(request.getParameter("Login"));
		if (request.getParameter("Passwd1") != null)
			authorizationPageBeanId.setStrPasswd(request.getParameter("Passwd1"));
		if (request.getParameter("Passwd2") != null)
			authorizationPageBeanId.setStrCPasswd(request.getParameter("Passwd2"));
		if (request.getParameter("FName") != null)
			authorizationPageBeanId.setStrFirstName(request.getParameter("FName"));
		if (request.getParameter("LName") != null)
			authorizationPageBeanId.setStrLastName(request.getParameter("LName"));
		if (request.getParameter("Company") != null)
			authorizationPageBeanId.setStrCompany(request.getParameter("Company"));
		if (request.getParameter("EMail") != null)
			authorizationPageBeanId.setStrEMail(request.getParameter("EMail"));
		if (request.getParameter("Phone") != null)
			authorizationPageBeanId.setStrPhone(request.getParameter("Phone"));
		if (request.getParameter("MPhone") != null)
			authorizationPageBeanId.setStrMPhone(request.getParameter("MPhone"));
		if (request.getParameter("Fax") != null)
			authorizationPageBeanId.setStrFax(request.getParameter("Fax"));
		if (request.getParameter("Address") != null)
			authorizationPageBeanId.setAddress(request.getParameter("Address"));
		if (request.getParameter("Website") != null)
			authorizationPageBeanId.setStrWebsite(request.getParameter("Website"));
		if (request.getParameter("Question") != null)
			authorizationPageBeanId.setStrQuestion(request.getParameter("Question"));
		if (request.getParameter("Answer") != null)
			authorizationPageBeanId.setStrAnswer(request.getParameter("Answer"));
		if (request.getParameter("country_id") != null)
			authorizationPageBeanId.setCountry_id(request.getParameter("country_id"));
		if (request.getParameter("city_id") != null)
			authorizationPageBeanId.setCity_id(request.getParameter("city_id"));
		if (request.getParameter("currency_id") != null)
			authorizationPageBeanId.setCurrency_id(request.getParameter("currency_id"));
		

		createShopBean.addSite(authorizationPageBeanId);

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
			authorizationPageBeanId.setStrMessage(
					authorizationPageBeanId.getLocalization(servletContext).getString("reg.login1.text"));
			authorizationPageFaced.initUserSite(authorizationPageBeanId.getIntUserID(), authorizationPageBeanId);
			response.sendRedirect("Productlist.jsp?offset=0");

		}

		if (authorizationPageBeanId.getIntLogined() == 2 && authorizationPageBeanId.getRezalt_reg() == 0
				&& !authorizationPageBeanId.getStrPasswd().equals(SiteRole.GUEST_PASSWORD)) {
			authorizationPageBeanId
					.setStrMessage(authorizationPageBeanId.getLocalization(servletContext).getString("reg.login1.text")
							+ " " + authorizationPageBeanId.getStrLogin() + " "
							+ authorizationPageBeanId.getLocalization(servletContext).getString("reg.login3.wrong"));
			authorizationPageBeanId.setIntUserID(SiteRole.GUEST_ID);
			authorizationPageBeanId.setIntLevelUp(SiteRole.GUEST_ROLE_ID);
			authorizationPageBeanId.setStrPasswd(SiteRole.GUEST_PASSWORD);
			authorizationPageBeanId.setStrLogin(SiteRole.GUEST);
		}

	}

}
