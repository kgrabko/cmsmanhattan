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
import com.cbsinc.cms.PrePayBean;
import com.cbsinc.cms.PublisherBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;

public abstract class TemplateAction implements IAction {

	private Optional<PublisherBean> publisherBean = Optional.empty();
	private Optional<CatalogListBean> catalogListBean = Optional.empty();
	private Optional<CatalogEditBean> catalogEditBean = Optional.empty();
	private Optional<CatalogAddBean> catalogAddBean = Optional.empty();
	private Optional<AuthorizationPageBean> authorizationPageBean = Optional.empty();
	private Optional<AccountHistoryDetalBean> accountHistoryDetalBean = Optional.empty();
	private Optional<AccountHistoryBean> accountHistoryBean = Optional.empty();
	private Optional<OrderBean> orderBean = Optional.empty();
	private Optional<PayBean> payBean = Optional.empty();
	private Optional<OperationAmountBean> operationAmountBean = Optional.empty();
	private Optional<PrePayBean> prePayBean = Optional.empty();
	private Optional<Map> messageMail = Optional.empty();
	

	// private Optional<ProductlistBean> productlistBean = Optional.empty();

	public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {

		HttpSession session = request.getSession();

		publisherBean = Optional.ofNullable((PublisherBean) session.getAttribute("publisherBeanId"));
		catalogListBean = Optional.ofNullable((CatalogListBean) session.getAttribute("catalogListBeanId"));
		catalogEditBean = Optional.ofNullable((CatalogEditBean) session.getAttribute("catalogEditBeanId"));
		catalogAddBean = Optional.ofNullable((CatalogAddBean) session.getAttribute("catalogAddBeanId"));
		authorizationPageBean = Optional.ofNullable((AuthorizationPageBean) session.getAttribute("authorizationPageBeanId"));
		accountHistoryDetalBean = Optional.ofNullable((AccountHistoryDetalBean) session.getAttribute("accountHistoryDetalBeanId"));
		accountHistoryBean = Optional.ofNullable((AccountHistoryBean) session.getAttribute("accountHistoryBeanId"));
		orderBean = Optional.ofNullable((OrderBean) session.getAttribute("orderBeanId"));
		payBean = Optional.ofNullable((PayBean) session.getAttribute("payBeanId"));
		messageMail = Optional.ofNullable((Map) session.getAttribute("messageMail"));

		// productlistBeanId =
		// Optional.ofNullable((ProductlistBean)session.getAttribute("productlistBeanId"));//request

		action(Optional.of(request), Optional.of(response), Optional.of(servletContext));
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws Exception {

		HttpSession session = request.getSession();

		publisherBean = Optional.ofNullable((PublisherBean) session.getAttribute("publisherBeanId"));
		catalogListBean = Optional.ofNullable((CatalogListBean) session.getAttribute("catalogListBeanId"));
		catalogEditBean = Optional.ofNullable((CatalogEditBean) session.getAttribute("catalogEditBeanId"));
		catalogAddBean = Optional.ofNullable((CatalogAddBean) session.getAttribute("catalogAddBeanId"));
		authorizationPageBean = Optional.ofNullable((AuthorizationPageBean) session.getAttribute("authorizationPageBeanId"));
		accountHistoryDetalBean = Optional.ofNullable((AccountHistoryDetalBean) session.getAttribute("accountHistoryDetalBeanId"));
		accountHistoryBean = Optional.ofNullable((AccountHistoryBean) session.getAttribute("accountHistoryBeanId"));
		orderBean = Optional.ofNullable((OrderBean) session.getAttribute("orderBeanId"));
		payBean = Optional.ofNullable((PayBean) session.getAttribute("payBeanId"));
		messageMail = Optional.ofNullable((Map) session.getAttribute("messageMail"));
		// productlistBeanId =
		// Optional.ofNullable((ProductlistBean)session.getAttribute("productlistBeanId"));//request
		action(Optional.of(request), Optional.of(response), Optional.of(servletContext));
	}


	public abstract void action(Optional<HttpServletRequest> requestOpts, Optional<HttpServletResponse> responseOpts, Optional<ServletContext> servletContextOpts) throws Exception ;
	
	
	public Optional<Map> getMessageMail() {
		return messageMail;
	}

	public void setMessageMail(Optional<Map> messageMail) {
		this.messageMail = messageMail;
	}

	public Optional<PublisherBean> getPublisherBean() {
		return publisherBean;
	}

	public void setPublisherBean(Optional<PublisherBean> publisherBean) {
		this.publisherBean = publisherBean;
	}

	public Optional<CatalogListBean> getCatalogListBean() {
		return catalogListBean;
	}

	public void setCatalogListBean(Optional<CatalogListBean> catalogListBean) {
		this.catalogListBean = catalogListBean;
	}

	public Optional<CatalogEditBean> getCatalogEditBean() {
		return catalogEditBean;
	}

	public void setCatalogEditBean(Optional<CatalogEditBean> catalogEditBean) {
		this.catalogEditBean = catalogEditBean;
	}

	public Optional<CatalogAddBean> getCatalogAddBean() {
		return catalogAddBean;
	}

	public void setCatalogAddBean(Optional<CatalogAddBean> catalogAddBean) {
		this.catalogAddBean = catalogAddBean;
	}

	public Optional<AuthorizationPageBean> getAuthorizationPageBean() {
		return authorizationPageBean;
	}

	public void setAuthorizationPageBean(Optional<AuthorizationPageBean> authorizationPageBean) {
		this.authorizationPageBean = authorizationPageBean;
	}

	public Optional<AccountHistoryDetalBean> getAccountHistoryDetalBean() {
		return accountHistoryDetalBean;
	}

	public void setAccountHistoryDetalBean(Optional<AccountHistoryDetalBean> accountHistoryDetalBean) {
		this.accountHistoryDetalBean = accountHistoryDetalBean;
	}

	public Optional<AccountHistoryBean> getAccountHistoryBean() {
		return accountHistoryBean;
	}

	public void setAccountHistoryBean(Optional<AccountHistoryBean> accountHistoryBean) {
		this.accountHistoryBean = accountHistoryBean;
	}

	public Optional<OrderBean> getOrderBean() {
		return orderBean;
	}

	public void setOrderBean(Optional<OrderBean> orderBean) {
		this.orderBean = orderBean;
	}

	public Optional<PayBean> getPayBean() {
		return payBean;
	}

	public void setPayBean(Optional<PayBean> payBean) {
		this.payBean = payBean;
	}

	public Optional<OperationAmountBean> getOperationAmountBean() {
		return operationAmountBean;
	}

	public void setOperationAmountBean(Optional<OperationAmountBean> operationAmountBean) {
		this.operationAmountBean = operationAmountBean;
	}

	public Optional<PrePayBean> getPrePayBean() {
		return prePayBean;
	}

	public void setPrePayBean(Optional<PrePayBean> prePayBean) {
		this.prePayBean = prePayBean;
	}

}
