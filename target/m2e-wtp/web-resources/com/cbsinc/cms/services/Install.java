package com.cbsinc.cms.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.CreateShopBean;
import com.cbsinc.cms.QueryManager;
import com.cbsinc.cms.controllers.SiteRole;
import com.cbsinc.cms.controllers.SpecialCatalog;


/**
 * Servlet Class
 *
 * @web.servlet              name="Install"
 *                           display-name="Name for Install"
 *                           description="Description for Install"
 * @web.servlet-mapping      url-pattern="/Install"
 * @web.servlet-init-param   name="A parameter"
 *                           value="A value"
 */
public class Install extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CreateShopBean createShopBean  = null ;
	transient ResourceBundle sequences_rs = null ;
	
	public Install() 
	{
		
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException 
	{
		
		createShopBean = new CreateShopBean();
		
	}

	
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException,
		IOException {
		createShopBean.addSite(2);
		resp.getWriter().write("<html>") ;
		resp.getWriter().write("<head>") ;
		resp.getWriter().write("</head>") ;
		resp.getWriter().write("<body>") ;
		resp.getWriter().write("<h1>") ;
		resp.getWriter().write("install complete") ;
		resp.getWriter().write("</h1>") ;
		resp.getWriter().write("</body>") ;
		resp.getWriter().write("</html>") ;
		super.doGet(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException,
		IOException {
		// TODO Auto-generated method stub
	}

	
		
}
