package com.cbsinc.cms.controllers;

import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbsinc.cms.AccountHistoryBean;
import com.cbsinc.cms.AccountHistoryDetalBean;
import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.CatalogAddBean;
import com.cbsinc.cms.CatalogEditBean;
import com.cbsinc.cms.CatalogListBean;
import com.cbsinc.cms.OperationAmountBean;
import com.cbsinc.cms.OrderBean;
import com.cbsinc.cms.PayBean;
import com.cbsinc.cms.PayGatewayBean;
import com.cbsinc.cms.PayGatewayListBean;
import com.cbsinc.cms.PrePayBean;
import com.cbsinc.cms.PublisherBean;
import com.cbsinc.cms.RequestFilter;

public abstract class TemplateAction implements IAction {

	private HttpSession session = null ;
	
	public HttpServletRequest  getHttpServletRequest() 
	{
		return RequestFilter.getRequest() ;
	}
	


	public ServletContext  getServletContext() 
	{
		return RequestFilter.getServletContext() ;
	}
	
	public HttpServletResponse  getHttpServletRsponse() 
    {
        return RequestFilter.getResponse();
    }

	// private Optional<ProductlistBean> productlistBean = Optional.empty();

	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {

		session = request.getSession();
		action(request,response, servletContext);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {

		session = request.getSession();
		action(request,response, servletContext);
	}


	public abstract void action(HttpServletRequest request , HttpServletResponse  response , ServletContext servletContextOpts) throws Exception ;

	
	public Map getMessageMail() {
		return(Map) session.getAttribute("messageMail") ;
	}



	public PublisherBean getPublisherBean() {
		return (PublisherBean) session.getAttribute("publisherBeanId");
	}



	public CatalogListBean getCatalogListBean() {
		return (CatalogListBean) session.getAttribute("catalogListBeanId");
	}



	public CatalogEditBean getCatalogEditBean() {
		return (CatalogEditBean) session.getAttribute("catalogEditBeanId");
	}



	public CatalogAddBean getCatalogAddBean() {
		return (CatalogAddBean) session.getAttribute("catalogAddBeanId");
	}



	public AuthorizationPageBean getAuthorizationPageBean() {
		return (AuthorizationPageBean) session.getAttribute("authorizationPageBeanId");
	}



	public AccountHistoryDetalBean getAccountHistoryDetalBean() {
		return (AccountHistoryDetalBean) session.getAttribute("accountHistoryDetalBeanId");
	}



	public AccountHistoryBean getAccountHistoryBean() {
		return (AccountHistoryBean) session.getAttribute("accountHistoryBeanId");
	}



	public OrderBean getOrderBean() {
		return (OrderBean) session.getAttribute("orderBeanId");
	}


	public PayBean getPayBean() {
		return (PayBean) session.getAttribute("payBeanId");
	}


	
	public OperationAmountBean getOperationAmountBean() {
		return (OperationAmountBean) session.getAttribute("operationAmountBeanId");
	}



	public PrePayBean getPrePayBean() {
		return (PrePayBean) session.getAttribute("prePayBeanId");
	}

	public PayGatewayListBean getPayGatewayListBean() {
		return (PayGatewayListBean) session.getAttribute("payGatewayListBeanId");
	}
	
	public PayGatewayBean getPayGatewayBean() {
		return (PayGatewayBean) session.getAttribute("payGatewayBeanId");
	}

	
}
