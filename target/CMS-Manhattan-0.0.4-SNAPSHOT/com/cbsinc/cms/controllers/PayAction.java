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


import java.io.File;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbsinc.cms.AuthorizationPageBean;
import com.cbsinc.cms.OperationAmountBean;
import com.cbsinc.cms.OrderBean;
import com.cbsinc.cms.PayBean;
import com.cbsinc.cms.faceds.AuthorizationPageFaced;
import com.cbsinc.cms.faceds.OrderFaced;

public class PayAction  implements IAction
{

	
	AuthorizationPageBean authorizationPageBean ;
	AuthorizationPageFaced authorizationPageFaced ;
	OrderFaced orderFaced ;
	HttpSession session ;
	java.util.HashMap messageMail ;
	OrderBean orderBeanId ;
	OperationAmountBean operationAmountBean ;
	public boolean isInternet = true ;
	
	public PayAction() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response , ServletContext  servletContext) throws Exception
	{
		action( request,  response,  servletContext) ;
	}



	public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		action( request,  response,  servletContext) ;
	}


	public void action(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception 
	{
		PayBean payBeanId = null ;
		orderFaced = ServiceLocator.getInstance().getOrderFaced();
		session = request.getSession();
		orderBeanId = (OrderBean)session.getAttribute("orderBeanId");
		payBeanId = (PayBean)session.getAttribute("payBeanId");
		operationAmountBean = (com.cbsinc.cms.OperationAmountBean)session.getAttribute("OperationAmountBeanId");
		
		messageMail = (java.util.HashMap)session.getAttribute("messageMail");
  	    if(authorizationPageFaced == null)  authorizationPageFaced = ServiceLocator.getInstance().getAuthorizationPageFaced();
		
		authorizationPageBean = (AuthorizationPageBean)session.getAttribute("AuthorizationPageBeanId");
		if( authorizationPageBean == null ||  payBeanId == null || orderFaced == null  ) return ;
		request.setCharacterEncoding("UTF-8"); 
		
		
		ResourceBundle resources = null ;
		if( resources == null )  resources = PropertyResourceBundle.getBundle("localization", response.getLocale());

		String   Amount  = request.getParameter("Amount");
		
		if ( Amount != null ) {  payBeanId.setAmount( Amount ); }
		else{ payBeanId.setAmount("0"); }
		Amount = null ;

		String   currency_id  = request.getParameter("currency_id");
		if ( currency_id != null )
		{
		  payBeanId.setCurrency_id( currency_id );
		  payBeanId.setCurrency_cd(payBeanId.getCurrency_code(currency_id));
		}
		else{ payBeanId.setCurrency_id("0"); }
		currency_id = null ;


		String   paysystem_id  = request.getParameter("paysystem_id");
		if ( paysystem_id != null )
		{
		payBeanId.setPaysystem_id( paysystem_id );
		payBeanId.setPaysystem_cd(payBeanId.getPaySystem_code(paysystem_id));
		}
		else{ payBeanId.setAmount("0"); }
		paysystem_id = null ;

//		if( payBeanId.getCurrency_id()
		authorizationPageFaced.initPaySys_Shop_cd(authorizationPageBean.getSite_id(),authorizationPageBean);
//		payBeanId.setDescription("Payment of order  N " + orderBeanId.getOrder_id() );
		payBeanId.setDescription(resources.getString("input_money"));
//		payBeanId.setAccount_hist_id(OperationAmountBeanId.addMoneyStart(payBeanId.getDescription() ,  payBeanId.getAmount() , payBeanId.getCurrency_id() , AuthorizationPageBeanId.getIntUserID() ) );
//		String user_ip , String user_port , String user_os , String uset_browser ) {
			String user_os = "" ;
			String header_name = "" ;
			String header_value = "" ;
			java.util.Enumeration en = request.getHeaderNames() ;
			while(en.hasMoreElements())
			{
				header_name =(String)en.nextElement() ;
				header_value =  request.getHeader(header_name);
				user_os = user_os.concat( header_name + "=" + header_value + "\n" );
			}
			payBeanId.setAccount_hist_id(operationAmountBean.addMoneyStart(payBeanId.getDescription() , new Double( payBeanId.getAmount()) , payBeanId.getCurrency_id() , authorizationPageBean.getIntUserID(),request.getRemoteAddr() , user_os , orderBeanId.getOrder_id() ) );

			//payBeanId.setOrder_IDP(payBeanId.getAccount_hist_id());
			//CkeckCurrencyId.getCurrencyRate_usd()
			
			messageMail.clear();
			messageMail.put("@FirstName", authorizationPageBean.getStrFirstName() ) ;
			messageMail.put("@LastName", authorizationPageBean.getStrLastName() ) ;
			messageMail.put("@NumberOfOrder", orderBeanId.getOrder_id()  ) ;
			messageMail.put("@ContactPerson", orderBeanId.getContact_person()  ) ;
			messageMail.put("@Balans", "" +  orderFaced.getBalans(authorizationPageBean.getIntUserID())  ) ;
			messageMail.put("@Phone", orderBeanId.getshipment_phone() ) ;
			messageMail.put("@Address", orderBeanId.getshipment_address() ) ;
			messageMail.put("@City", orderBeanId.getcity_fullname() ) ;
			messageMail.put("@Contry", orderBeanId.getcountry_fullname() ) ;
			messageMail.put("@CustomerEmail", orderBeanId.getshipment_email() ) ;
			messageMail.put("@CustomerFax", orderBeanId.getshipment_fax() ) ;
			messageMail.put("@CustomerCommentariy", orderBeanId.getshipment_description() ) ;
			messageMail.put("@ProductCount", "" +  orderFaced.getProductsListSize(request,orderBeanId)  ) ;
			messageMail.put("@IPAddress", "" +  request.getRemoteAddr()  ) ;
			messageMail.put("@HTTPHEAD", "" +  user_os  ) ;

			System.out.println("code pay: "  + payBeanId.getPaysystem_cd()   ) ;
			System.out.println("code pay1: "  + resources.getString(payBeanId.getPaysystem_cd())   ) ;
			messageMail.put("@PaySystem", "" +  resources.getString(payBeanId.getPaysystem_cd())   ) ;
			messageMail.put("@Amount", "" +  payBeanId.getAmount()   ) ;
			messageMail.put("@Currency", "" +  payBeanId.getCurrency_lable( payBeanId.getCurrency_id())  ) ;

			String sitePath = (String)request.getSession().getAttribute("site_path");
			String shopOrder = sitePath  +File.separatorChar +"mail"+File.separatorChar+"ShopPayment.txt" ;
			String clientOrder = sitePath +File.separatorChar +"mail"+File.separatorChar+"ClientPayment.txt" ;
			String attachFile =  sitePath +File.separatorChar +"mail"+File.separatorChar+"info.txt" ;

			
//			MQSender mqSender = new MQSender( request.getSession(),SendMailMessageBean.messageQuery) ;
//			Message message = new Message();
//			message.put("to" , ownerShop.getStrEMail()  ) ;
//			message.put("subject" , resources.getString("order.order_number.text")) ;
//			message.put("pathmessage" , shopOrder ) ;
//			message.put("attachFile" , attachFile ) ;
//			message.put("fields" , messageMail ) ;
//			mqSender.send(message);
//
//			message = new Message();
//			message.put("to" , authorizationPageBean.getStrEMail()  ) ;
//			message.put("mailfrom" , ownerShop.getStrEMail() ) ;
//			message.put("subject" , resources.getString("order.order_number.text")) ;
//			message.put("pathmessage" , clientOrder ) ;
//			message.put("attachFile" , attachFile ) ;
//			message.put("fields" , messageMail ) ;
//			mqSender.send(message);
//			authorizationPageBean.setStrMessage("Order was send by email");
			
			
		System.out.println("path: "  + shopOrder   ) ; 
		System.out.println("path: "  + resources.getString("pay.subject_mail_client")   ) ; 
//		System.out.println("rezalt: " + sendMailAgent.putMessageInPool(null ,resources.getString("pay.subject_mail_client"),   shopOrder   , attachFile, messageMail  ) ) ;
//		System.out.println("rezalt: " + sendMailAgent.putMessageInPool(orderBeanId.getshipment_email() ,resources.getString("pay.subject_mail_shop"), clientOrder , attachFile , messageMail  ) ) ;

			payBeanId.setStatusInrocess(orderBeanId.getOrder_id()) ;
		
		
		//if( request.getParameter("amount_id") !=null ) accountHistoryDetalBean.setAmount_id("" + request.getParameter("amount_id"));
		//accountHistoryDetalBean.setSelectAccountHistoryDetalXML(orderFaced.getPayment(authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getIntLevelUp(),accountHistoryDetalBean ));
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
