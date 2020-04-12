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



import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.Catalog_listBean;
import com.cbsinc.cms.PolicyBean;
import com.cbsinc.cms.faceds.PolicyFaced;
import com.cbsinc.cms.faceds.ProductlistFaced;


public class PolicyAction  implements IAction
{

	
	transient static private Logger log = Logger.getLogger(PolicyAction.class);
	PolicyFaced policyFaced = null ;
	ProductlistFaced productlistFaced = null ;
	
	
	
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response , ServletContext  servletContext) throws Exception
	{
		doGet( request,  response,  servletContext) ;
	}




	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		
		
		
		
		AuthorizationPageBean AuthorizationPageBeanId ;
		PolicyBean policyBeanId = null ;
		HttpSession session ;
		boolean isInternet = true ;

	    if(policyFaced == null) policyFaced = ServiceLocator.getInstance().getPolicyFaced();
	    if(productlistFaced == null) productlistFaced = ServiceLocator.getInstance().getProductlistFaced();
	    policyBeanId = new PolicyBean() ;
		session = request.getSession();
		 
		AuthorizationPageBeanId = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		request.setAttribute("policyBeanId", policyBeanId);

	    
		
		
		
		if(request.getRemoteAddr().startsWith("192."))isInternet = false ;
		if(request.getRemoteAddr().startsWith("10."))isInternet = false ;
		
		if(AuthorizationPageBeanId == null || policyBeanId == null  ) return ;
		/////+if(ProductlistBeanId == null || AuthorizationPageBeanId == null || accountHistoryBeanId == null || catalog_listBean == null || policyBeanId == null  ) return ;
		

		if( request.getParameter("policy_byproductid") ==null  && request.getParameter("page") ==null  ) 
		{
			//policyBeanId.setProduct_id(request.getParameter("policy_byproductid"));
			if( request.getParameter("rate") !=null  && isNumber(request.getParameter("rate")) )
	 		{
				policyBeanId.setProduct_id(Long.toString(AuthorizationPageBeanId.getLastProductId()));
				int rate = Integer.parseInt(request.getParameter("rate")) ;
	 			policyFaced.setRatring1(rate,policyBeanId.getProduct_id()) ;
	 			response.sendRedirect("Policy.jsp?policy_byproductid=" + policyBeanId.getProduct_id() );
				return ;
	 		}
			
			response.sendRedirect("Productlist.jsp?catalog_id=-2" );
			return ;
		}	

		
		
		
		 request.setCharacterEncoding("UTF-8"); 
		 
		 policyBeanId.internet = isInternet ;

		 
		 if( request.getParameter("policy_byproductid") !=null && isNumber(request.getParameter("policy_byproductid")) )
		 {
		 policyFaced.mergePolicyBean(AuthorizationPageBeanId.getIntUserID(), request.getParameter("policy_byproductid") , policyBeanId) ;
		 policyBeanId.setBack_url("Policy.jsp?policy_byproductid=" + policyBeanId.getParent_product_id()) ;
		 policyBeanId.setType_page("policy_byproductid") ;
		 policyBeanId.setIntUserID(AuthorizationPageBeanId.getIntUserID());
		 AuthorizationPageBeanId.setLastProductId(Long.parseLong(policyBeanId.getParent_product_id()));
		 
		 		if( request.getParameter("rate") !=null  && isNumber(request.getParameter("rate")) )
		 		{
		 			int rate = Integer.parseInt(request.getParameter("rate")) ;
		 			policyFaced.setRatring1(rate,policyBeanId.getProduct_id()) ;
		 		}
		 
		 		policyBeanId.setRating1_xml( policyFaced.getRatring1XML( policyBeanId.getProduct_id() ) ) ;
		 		policyBeanId.setBalans("" + policyFaced.getBalans(AuthorizationPageBeanId.getIntUserID())) ;
		 
				try{
		 		if(policyBeanId.isFistOpen()) 
				{
					 
					policyFaced.payMoneyForShowPage(policyBeanId, AuthorizationPageBeanId, request.getRemoteAddr());
					 policyBeanId.setFistOpen(false);
				}
				else policyFaced.incrementShowPage(policyBeanId) ;
				}
				catch (Exception ex) 
				{
					log.error("Billing is not working ", ex);
				}

		 }
		 else  if( request.getParameter("page") !=null && request.getParameter("page").compareTo("about") ==  0  )
		 {
		 policyFaced.mergePolicyBeanForAboutPage(AuthorizationPageBeanId.getSite_id(), policyBeanId);//(AuthorizationPageBeanId.getIntUserID(), request.getParameter("policy_byproductid") , policyBeanId) ;
		 AuthorizationPageBeanId.setLastProductId(Long.parseLong(policyBeanId.getParent_product_id()));
		 policyBeanId.setBack_url("Productlist.jsp") ;
		 policyBeanId.setType_page("about") ;
		 policyBeanId.setIntUserID(AuthorizationPageBeanId.getIntUserID());
		 policyBeanId.setRating1_xml( policyFaced.getRatring1XML( policyBeanId.getProduct_id() ) ) ;
		 policyBeanId.setBalans("" + policyFaced.getBalans(AuthorizationPageBeanId.getIntUserID())) ;
				 	
		 			if(policyBeanId.isFistOpen()) 
				 	{
					 policyFaced.payMoneyForShowPage(policyBeanId, AuthorizationPageBeanId, request.getRemoteAddr());
					 policyBeanId.setFistOpen(false);
				 	}
				 	else policyFaced.incrementShowPage(policyBeanId) ;
		 }
		 else  if( request.getParameter("page") !=null && request.getParameter("page").compareTo("pay") ==  0  )
		 {
		 policyFaced.mergePolicyBeanForPayPageInfo(AuthorizationPageBeanId.getSite_id(), policyBeanId);//(AuthorizationPageBeanId.getIntUserID(), request.getParameter("policy_byproductid") , policyBeanId) ;
		 AuthorizationPageBeanId.setLastProductId(Long.parseLong(policyBeanId.getParent_product_id()));
		 policyBeanId.setBack_url("Productlist.jsp") ;
		 policyBeanId.setType_page("pay") ;
		 policyBeanId.setIntUserID(AuthorizationPageBeanId.getIntUserID());
		 policyBeanId.setRating1_xml( policyFaced.getRatring1XML( policyBeanId.getProduct_id() ) ) ;
		 policyBeanId.setBalans("" + policyFaced.getBalans(AuthorizationPageBeanId.getIntUserID())) ;
				 	
		 			if(policyBeanId.isFistOpen()) 
				 	{
					 policyFaced.payMoneyForShowPage(policyBeanId, AuthorizationPageBeanId, request.getRemoteAddr());
					 policyBeanId.setFistOpen(false);
				 	}
				 	else policyFaced.incrementShowPage(policyBeanId) ;

		 }
		 

	 
		 policyBeanId.setSelectCatalogXMLUrlPath((new Catalog_listBean(servletContext)).getCatalogXMLUrlPath("Productlist.jsp?catalog_id","parent",AuthorizationPageBeanId.getCatalog_id(),AuthorizationPageBeanId)) ;
		 policyBeanId.setSelect_tree_catalog(productlistFaced.getTreeXMLDBList("Productlist.jsp?catalog_id","catalog", AuthorizationPageBeanId.getCatalog_id()  ,"select catalog_id , lable   from catalog   where  active = true and site_id = " + AuthorizationPageBeanId.getSite_id()  +" and parent_id = " + AuthorizationPageBeanId.getCatalogParent_id()  ,"select catalog_id , lable   from catalog   where  active = true and parent_id = " + AuthorizationPageBeanId.getCatalog_id()  ) ) ;
		 policyBeanId.setSelect_currencies(policyFaced.getXMLDBList("Policy.jsp","currencies",policyBeanId.getCurrency_cd(),"SELECT currency_cd , currency_desc  FROM currency  WHERE active = true")) ;
		 policyBeanId.ext1Adp = productlistFaced.getExtPolicyOneProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id() , policyBeanId.getParent_product_id(),policyBeanId  )  ;
		 policyBeanId.ext2Adp = productlistFaced.getExtPolicyTwoProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id() , policyBeanId.getParent_product_id() ,policyBeanId )  ;
		 policyBeanId.extFilesAdp =  productlistFaced.getExtPolicyFilesProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id() , policyBeanId.getParent_product_id() ,policyBeanId )  ;
		 policyBeanId.extTabsAdp = productlistFaced.getExtPolicyTabsProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id() , policyBeanId.getParent_product_id() ,policyBeanId )  ;
		 policyBeanId.blogExtAdp = productlistFaced.getBlogExtPolicyProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id() , policyBeanId.getParent_product_id() ,policyBeanId )  ;
		// if( policyBeanId.getPortlettype_id() != Layout.PORTLET_TYPE_BOTTOM )
		 policyBeanId.newsAdp  = productlistFaced.getNewslist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id(),AuthorizationPageBeanId ) ;
		 policyBeanId.bottomAdp = productlistFaced.getBottomlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id() , AuthorizationPageBeanId )  ;

		 policyBeanId.setSelect_menu_catalog(productlistFaced.getMenuXMLDBList("Productlist.jsp?catalog_id","menu", AuthorizationPageBeanId.getCatalog_id()  ,"select catalog_id , lable , parent_id  from catalog   where  active = true and parent_id = 0 and site_id = " + AuthorizationPageBeanId.getSite_id()  +" and lang_id = " + AuthorizationPageBeanId.getLang_id()  +" or parent_id in (select catalog_id   from catalog   where  active = true and site_id = " + AuthorizationPageBeanId.getSite_id()  +"  and parent_id = 0 )" ) ) ;
			
		 
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
}
