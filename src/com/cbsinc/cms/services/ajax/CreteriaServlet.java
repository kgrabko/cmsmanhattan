package com.cbsinc.cms.services.ajax;

/**
 * <p>
 * Title: Content Manager System
 * </p>
 * <p>
 * Description: System building web application develop by Konstantin Grabko.
 * Konstantin Grabko is Owner and author this code.
 * Программный код написан Грабко Константином Владимировичем и является его интеллектуальной 
 * собственностью.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: Grabko Business (Предприниматель Грабко Константин Владимирович)  
 * </p>
 * 
 * @author Konstantin Grabko
 * @version 1.0
 */


import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.ProductlistBean;
import com.cbsinc.cms.controllers.ServiceLocator;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.faceds.ProductlistFaced;


/**
 * Servlet Class
 *
 * @web.servlet              name="creteria"
 *                           display-name="Name for creteria"
 *                           description="Description for creteria"
 * @web.servlet-mapping      url-pattern="/creteria"
 * @web.servlet-init-param   name="A parameter"
 *                           value="A value"
 */
public class CreteriaServlet extends HttpServlet {
	
	transient ResourceBundle setup_resources = null ;
	transient ResourceBundle localization = null ;

	public CreteriaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		if( setup_resources == null )  setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources", response.getLocale());
		if( localization == null )  localization = PropertyResourceBundle.getBundle("localization", response.getLocale());
		//HttpSession session  = request.getSession(false);
		HttpSession session  = request.getSession();
		//AuthorizationPageFaced authorizationPageFaced = null ;
		ProductlistFaced productlistFaced = null;
		try {
			productlistFaced = ServiceLocator.getInstance().getProductlistFaced().get();
			//authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductlistBean ProductlistBeanId = (ProductlistBean)session.getAttribute("ProductlistBeanId");
		AuthorizationPageBean authorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("authorizationPageBeanId");
		if(ProductlistBeanId == null )ProductlistBeanId = new ProductlistBean();
		if(authorizationPageBeanId == null )authorizationPageBeanId = new AuthorizationPageBean();
		
		if( request.getParameter("creteria1_id") !=null ) authorizationPageBeanId.setStrCreteria1_id( request.getParameter("creteria1_id"));
		if( request.getParameter("creteria2_id") !=null ) authorizationPageBeanId.setStrCreteria2_id( request.getParameter("creteria2_id"));
		if( request.getParameter("creteria3_id") !=null ) authorizationPageBeanId.setStrCreteria3_id( request.getParameter("creteria3_id"));
		if( request.getParameter("creteria4_id") !=null ) authorizationPageBeanId.setStrCreteria4_id( request.getParameter("creteria4_id"));
		if( request.getParameter("creteria5_id") !=null ) authorizationPageBeanId.setStrCreteria5_id( request.getParameter("creteria5_id"));
		if( request.getParameter("creteria6_id") !=null ) authorizationPageBeanId.setStrCreteria6_id( request.getParameter("creteria6_id"));
		if( request.getParameter("creteria7_id") !=null ) authorizationPageBeanId.setStrCreteria7_id( request.getParameter("creteria7_id"));
		if( request.getParameter("creteria8_id") !=null ) authorizationPageBeanId.setStrCreteria8_id( request.getParameter("creteria8_id"));
		if( request.getParameter("creteria9_id") !=null ) authorizationPageBeanId.setStrCreteria9_id( request.getParameter("creteria9_id"));
		if( request.getParameter("creteria10_id") !=null ) authorizationPageBeanId.setStrCreteria10_id( request.getParameter("creteria10_id"));
		
		response.getWriter().write("<document>") ;
		response.getWriter().write(productlistFaced.getAjaxDBListLocale("Productlist.jsp?creteria1_id","creteria1","" + authorizationPageBeanId.getCreteria1_id(), localization.getString("notselected") ,"select creteria1_id , name   from creteria1   where  active = true " + ProductlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) )) ;
		response.getWriter().write(productlistFaced.getAjaxDBListLocale("Productlist.jsp?creteria2_id","creteria2","" + authorizationPageBeanId.getCreteria2_id(), localization.getString("notselected") , "select creteria2_id , name   from creteria2   where  active = true " + ProductlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria1_id()+ " ) " )) ;
		response.getWriter().write(productlistFaced.getAjaxDBListLocale("Productlist.jsp?creteria3_id","creteria3","" + authorizationPageBeanId.getCreteria3_id(), localization.getString("notselected") , "select creteria3_id , name   from creteria3   where  active = true " + ProductlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria2_id()+ " ) " )) ;
		response.getWriter().write(productlistFaced.getAjaxDBListLocale("Productlist.jsp?creteria4_id","creteria4","" + authorizationPageBeanId.getCreteria4_id(), localization.getString("notselected") , "select creteria4_id , name   from creteria4   where  active = true " + ProductlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria3_id()+ " ) " )) ;
		response.getWriter().write(productlistFaced.getAjaxDBListLocale("Productlist.jsp?creteria5_id","creteria5","" + authorizationPageBeanId.getCreteria5_id(), localization.getString("notselected") , "select creteria5_id , name   from creteria5   where  active = true " + ProductlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria4_id()+ " ) " )) ;
		response.getWriter().write(productlistFaced.getAjaxDBListLocale("Productlist.jsp?creteria6_id","creteria6","" + authorizationPageBeanId.getCreteria6_id(), localization.getString("notselected") , "select creteria6_id , name   from creteria6   where  active = true " + ProductlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria5_id()+ " ) " )) ;
		response.getWriter().write(productlistFaced.getAjaxDBListLocale("Productlist.jsp?creteria7_id","creteria7","" + authorizationPageBeanId.getCreteria7_id(), localization.getString("notselected") , "select creteria7_id , name   from creteria7   where  active = true " + ProductlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria6_id()+ " ) " )) ;
		response.getWriter().write(productlistFaced.getAjaxDBListLocale("Productlist.jsp?creteria8_id","creteria8","" + authorizationPageBeanId.getCreteria8_id(), localization.getString("notselected") , "select creteria8_id , name   from creteria8   where  active = true " + ProductlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria7_id()+ " ) " )) ;
		response.getWriter().write(productlistFaced.getAjaxDBListLocale("Productlist.jsp?creteria9_id","creteria9","" + authorizationPageBeanId.getCreteria9_id(), localization.getString("notselected") , "select creteria9_id , name   from creteria9   where  active = true " + ProductlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria8_id()+ " ) " )) ;
		response.getWriter().write(productlistFaced.getAjaxDBListLocale("Productlist.jsp?creteria10_id","creteria10","" + authorizationPageBeanId.getCreteria10_id(), localization.getString("notselected") , "select creteria10_id , name   from creteria10   where  active = true " + ProductlistBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + authorizationPageBeanId.getCreteria9_id()+ " ) " )) ;
		response.getWriter().write("</document>") ;
	}
	
	
}
