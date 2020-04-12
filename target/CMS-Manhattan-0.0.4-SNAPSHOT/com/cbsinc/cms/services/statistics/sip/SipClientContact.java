package com.cbsinc.cms.services.statistics.sip;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbsinc.cms.AuthorizationPageBean;


/**
 * Servlet Class
 *
 * @web.servlet              name="sipclientcontact"
 *                           display-name="Name for sipclientcontact"
 *                           description="Description for sipclientcontact"
 * @web.servlet-mapping      url-pattern="/sipclientcontact"
 * @web.servlet-init-param   name="A parameter"
 *                           value="A value"
 */
public class SipClientContact extends HttpServlet {

	public SipClientContact() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException,
		IOException {
		resp.setContentType("text/plain");
		resp.setHeader("Cache-Control", "no-cache");
		//resp.setCharacterEncoding("UTF-8");
		HashMap hashMap = null ; 
		ServletContext servletContext =  getServletContext() ;
		hashMap = (HashMap)servletContext.getAttribute("userlist") ;
		//String full_name = AuthorizationPageBeanId.getStrFirstName() +  " " + AuthorizationPageBeanId.getStrLastName() ;
		Iterator iterator  =  hashMap.values().iterator();
		 while(iterator.hasNext())
		 {
			  resp.getWriter().write((String)iterator.next()) ;
		 }
	}

}
