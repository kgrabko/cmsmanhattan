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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbsinc.cms.SoftPostBean;
import com.cbsinc.cms.controllers.ServiceLocator;
import com.cbsinc.cms.faceds.ProductPostAllFaced;

/**
 * Servlet Class
 *
 * @web.servlet              name="postcreteria"
 *                           display-name="Name for postcreteria"
 *                           description="Description for postcreteria"
 * @web.servlet-mapping      url-pattern="/postcreteria"
 * @web.servlet-init-param   name="A parameter"
 *                           value="A value"
 */
public class PostCreteriaServlet extends HttpServlet {
	
	transient ResourceBundle setup_resources = null ;

	public PostCreteriaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		if( setup_resources == null )  setup_resources = PropertyResourceBundle.getBundle("SetupApplicationResources", response.getLocale());
		//HttpSession session  = request.getSession(false);
		HttpSession session  = request.getSession();

		ProductPostAllFaced productPostAllFaced = null;
		try {
			productPostAllFaced = ServiceLocator.getInstance().getProductPostAllFaced();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//if( session.getAttribute("SoftPostBeanId") == null ) return ;  
		SoftPostBean SoftPostBeanId = (SoftPostBean)session.getAttribute("SoftPostBeanId");
		//if(SoftPostBeanId == null) SoftPostBeanId = new SoftPostBean();
		
		if( request.getParameter("softname") !=null ) SoftPostBeanId.setStrSoftName(request.getParameter("softname"));
		//if( request.getParameter("catalog_id") !=null ) SoftPostBeanId.set( request.getParameter("catalog_id"));
		if( request.getParameter("softname2") !=null ) SoftPostBeanId.setStrSoftName2( request.getParameter("softname2"));
		if( request.getParameter("imagename") !=null ) SoftPostBeanId.setImgname(request.getParameter("imagename"));
		if( request.getParameter("softcost") !=null ) SoftPostBeanId.setStrSoftCost(request.getParameter("softcost"));
		if( request.getParameter("description") !=null ) SoftPostBeanId.setStrSoftDescription(request.getParameter("description"));
		if( request.getParameter("bigimagename") !=null ) SoftPostBeanId.setBigimgname( request.getParameter("bigimagename"));
		if( request.getParameter("fulldescription") !=null ) SoftPostBeanId.setProduct_fulldescription(request.getParameter("fulldescription"));
		if( request.getParameter("filename") !=null ) SoftPostBeanId.setFilename( request.getParameter("filename"));
		if ( request.getParameter("show_rating1") != null ) 
		{
			SoftPostBeanId.setStrShow_ratimg1_checked("CHECKED") ;
			SoftPostBeanId.setStrShow_ratimg1( "true" ); 
		}
		else 
		{
			SoftPostBeanId.setStrShow_ratimg1_checked("") ;
			SoftPostBeanId.setStrShow_ratimg1( "false" ); 
		}
		if ( request.getParameter("show_blog") != null ) 
		{
			SoftPostBeanId.setShow_forum_checked("CHECKED") ;
			SoftPostBeanId.setStrShow_forum( "true" ); 
		}
		else 
		{
			SoftPostBeanId.setShow_forum_checked("") ;
			SoftPostBeanId.setStrShow_forum( "false" ); 
		}
		if( request.getParameter("type_id") !=null ) SoftPostBeanId.setType_id(request.getParameter("type_id"));
		if( request.getParameter("portlettype_id") !=null ) SoftPostBeanId.setPortlettype_id( request.getParameter("portlettype_id"));
		if( request.getParameter("image_id") !=null ) SoftPostBeanId.setImage_id( request.getParameter("image_id"));
		if( request.getParameter("bigimage_id") !=null ) SoftPostBeanId.setBigimage_id(request.getParameter("bigimage_id"));
		
		
		if( request.getParameter("creteria1_id") !=null ) SoftPostBeanId.setCreteria1_id( request.getParameter("creteria1_id"));
		if( request.getParameter("creteria2_id") !=null ) SoftPostBeanId.setCreteria2_id( request.getParameter("creteria2_id"));
		if( request.getParameter("creteria3_id") !=null ) SoftPostBeanId.setCreteria3_id( request.getParameter("creteria3_id"));
		if( request.getParameter("creteria4_id") !=null ) SoftPostBeanId.setCreteria4_id( request.getParameter("creteria4_id"));
		if( request.getParameter("creteria5_id") !=null ) SoftPostBeanId.setCreteria5_id( request.getParameter("creteria5_id"));
		if( request.getParameter("creteria6_id") !=null ) SoftPostBeanId.setCreteria6_id( request.getParameter("creteria6_id"));
		if( request.getParameter("creteria7_id") !=null ) SoftPostBeanId.setCreteria7_id( request.getParameter("creteria7_id"));
		if( request.getParameter("creteria8_id") !=null ) SoftPostBeanId.setCreteria8_id( request.getParameter("creteria8_id"));
		if( request.getParameter("creteria9_id") !=null ) SoftPostBeanId.setCreteria9_id( request.getParameter("creteria9_id"));
		if( request.getParameter("creteria10_id") !=null ) SoftPostBeanId.setCreteria10_id( request.getParameter("creteria10_id"));
		if( request.getParameter("dialog") !=null ) session.setAttribute("dialog" , request.getParameter("dialog"));
		
		response.getWriter().write("<document>") ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria1_id","creteria1",SoftPostBeanId.getCreteria1_id(),"select creteria1_id , name   from creteria1   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria2_id","creteria2",SoftPostBeanId.getCreteria2_id(),"select creteria2_id , name   from creteria2   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria1_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria3_id","creteria3",SoftPostBeanId.getCreteria3_id(),"select creteria3_id , name   from creteria3   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria2_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria4_id","creteria4",SoftPostBeanId.getCreteria4_id(),"select creteria4_id , name   from creteria4   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria3_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria5_id","creteria5",SoftPostBeanId.getCreteria5_id(),"select creteria5_id , name   from creteria5   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria4_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria6_id","creteria6",SoftPostBeanId.getCreteria6_id(),"select creteria6_id , name   from creteria6   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria5_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria7_id","creteria7",SoftPostBeanId.getCreteria7_id(),"select creteria7_id , name   from creteria7   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria6_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria8_id","creteria8",SoftPostBeanId.getCreteria8_id(),"select creteria8_id , name   from creteria8   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria7_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria9_id","creteria9",SoftPostBeanId.getCreteria9_id(),"select creteria9_id , name   from creteria9   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria8_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria10_id","creteria10",SoftPostBeanId.getCreteria10_id(),"select creteria10_id , name   from creteria10   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria9_id()+ " ) " )) ;
		
		response.getWriter().write("<dialog>") ;
		if(session.getAttribute("dialog") instanceof String)  response.getWriter().write((String)session.getAttribute("dialog")) ;
		else response.getWriter().write("true") ;
		response.getWriter().write("</dialog>") ;
		
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria1_id","creteria_lable_1",SoftPostBeanId.getCreteria1_id(),"select creteria1_id , label   from creteria1   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria2_id","creteria_lable_2",SoftPostBeanId.getCreteria2_id(),"select creteria2_id , label   from creteria2   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria1_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria3_id","creteria_lable_3",SoftPostBeanId.getCreteria3_id(),"select creteria3_id , label   from creteria3   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria2_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria4_id","creteria_lable_4",SoftPostBeanId.getCreteria4_id(),"select creteria4_id , label   from creteria4   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria3_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria5_id","creteria_lable_5",SoftPostBeanId.getCreteria5_id(),"select creteria5_id , label   from creteria5   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria4_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria6_id","creteria_lable_6",SoftPostBeanId.getCreteria6_id(),"select creteria6_id , label   from creteria6   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria5_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria7_id","creteria_lable_7",SoftPostBeanId.getCreteria7_id(),"select creteria7_id , label   from creteria7   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria6_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria8_id","creteria_lable_8",SoftPostBeanId.getCreteria8_id(),"select creteria8_id , label   from creteria8   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria7_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria9_id","creteria_lable_9",SoftPostBeanId.getCreteria9_id(),"select creteria9_id , label   from creteria9   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria8_id()+ " ) " )) ;
		response.getWriter().write(productPostAllFaced.getAjaxDBList("Productlist.jsp?creteria10_id","creteria_lable_10",SoftPostBeanId.getCreteria10_id(),"select creteria10_id , label   from creteria10   where  active = true " + SoftPostBeanId.getPartCriteria(SoftPostBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria9_id()+ " ) " )) ;
		//<%= SoftPostBeanId.getOneLabel("select  label   from creteria7   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getSite_id(), setup_resources.getString("is_criteria_by_catalog").equals("true"))  ) %>
		response.getWriter().write("</document>") ;
	}
	
	
}
