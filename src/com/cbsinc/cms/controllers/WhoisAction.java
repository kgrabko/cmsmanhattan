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

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.jms.controllers.MessageSender;
import com.cbsinc.cms.jms.controllers.Message;
import com.cbsinc.cms.jms.controllers.SendMailMessageBean;
import com.cbsinc.cms.services.net.FingerClient;
import com.cbsinc.cms.services.whois.DomainState;

public class WhoisAction implements IAction {

	String[] hight_domains = { ".org.uk", ".net", ".us", ".tel", ".biz", ".org", ".de", ".net.uk", ".or.at", ".info",
			".lc", ".eu", ".plc.uk", ".mobi", ".bz", ".name", ".at", ".ag", ".mn", ".hn", ".vc", ".sc", ".me", ".asia",
			".me.uk", ".cn", ".co.at", ".co.uk", ".com", ".ltd.uk" };

	AuthorizationPageFaced authorizationPageFaced;

	public WhoisAction() {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		action(request, response, servletContext);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {
		HttpSession session;
		List<DomainState> domainList = null;
		AuthorizationPageBean authorizationPageBeanId = null;
		session = request.getSession();
		domainList = (List<DomainState>) session.getAttribute("domainList");
		authorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
		if (authorizationPageFaced == null)
			authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced().get();
	}

	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {

		HttpSession session;
		java.util.LinkedList<DomainState> domainList = null;
		AuthorizationPageBean authorizationPageBeanId = null;
		String handle = "domain:";
		String domain = "";
		String regdomain = "";

		session = request.getSession();
		if (authorizationPageFaced == null)
			authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced().get();

		domainList = (java.util.LinkedList) request.getAttribute("domainList");
		if (domainList == null) {
			domainList = new java.util.LinkedList();
			request.setAttribute("domainList", domainList);
		}
		authorizationPageBeanId = (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");

		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0);

		regdomain = request.getParameter("regdomain");
		if (regdomain != null) {
			MessageSender mqSender = new MessageSender(request.getSession(), SendMailMessageBean.messageQuery);
			Message message = new Message();
			message.put("to", authorizationPageFaced.getResources_cms_settings().getString("support_mail"));
			message.put("mailfrom", authorizationPageBeanId.getStrEMail());
			message.put("subject", "Inform about buy domain");
			message.put("body", regdomain + "\r\n Phone: " + authorizationPageBeanId.getStrPhone() 
			+ "\r\n Loing: "+ authorizationPageBeanId.getStrLogin());
			message.put("fromperson",authorizationPageBeanId.getStrFirstName() + "" + authorizationPageBeanId.getStrLastName());
			mqSender.send(message);
			request.setAttribute("domain", regdomain);
			request.setAttribute("message",
					authorizationPageBeanId.getLocalization(servletContext).getString("your_application_has_send"));
			return;
		}

		domain = request.getParameter("domain");
		String[] domain_parts = domain.split("\\.");
		if (domain_parts.length > 1) {
			int level = getWhatLevelDomain(domain);
			switch (level) {
			case 0: {
				request.setAttribute("message",
						authorizationPageBeanId.getLocalization(servletContext).getString("unknown_zone"));
				request.setAttribute("domain", domain);
				return;
			}
			case 1:
				domain = domain_parts[domain_parts.length - 2].concat(".")
						.concat(domain_parts[domain_parts.length - 1]);
				break;
			case 2:
				domain = domain_parts[domain_parts.length - 3].concat(".").concat(domain_parts[domain_parts.length - 2])
						.concat(".").concat(domain_parts[domain_parts.length - 1]);
				break;
			}

		} else {
			request.setAttribute("message",
					authorizationPageBeanId.getLocalization(servletContext).getString("unknown_zone"));
			request.setAttribute("domain", domain);
			return;
		}

		request.setAttribute("domain", domain);

		if (domainList.size() > 20)
			domainList.removeFirst();

		FingerClient finger = new FingerClient();
		// We want to timeout if a response takes longer than 60 seconds
		finger.setDefaultTimeout(60000);
		try {
			InetAddress address = InetAddress.getByName("whois.joker.com");
			finger.connect(address, 4343);
			String[] status = finger.query(false, handle + domain).split(" ");
			DomainState domainState = new DomainState();
			domainState.setDomain(domain);
			domainState.setStatus(authorizationPageBeanId.getLocalization(servletContext).getString(status[1].trim()));
			domainState.setFree(status[1].trim().startsWith("free"));
			domainList.add(domainState);
		} catch (IOException e) {
			authorizationPageBeanId.setStrMessage(e.getLocalizedMessage());
			System.err.println("Error I/O exception: " + e.getMessage());

		} finally {
			try {
				finger.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	int getWhatLevelDomain(String domain) {
		int level = 0;

		for (int i = 0; hight_domains.length > i; i++) {
			if (domain.endsWith(hight_domains[i])) {
				if (domain.endsWith(".uk"))
					return hight_domains[i].split(".").length;
				level = level + 1;
			}
		}

		return level;
	}

}
