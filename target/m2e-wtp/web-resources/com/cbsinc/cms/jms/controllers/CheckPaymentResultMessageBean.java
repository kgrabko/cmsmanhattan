package com.cbsinc.cms.jms.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;


public class CheckPaymentResultMessageBean extends AbstractMessageBean {


	static public String messageQuery = "mq_check_payment_result" ;

	public void onMessage(Message message, ServletContext applicationContext, HttpSession httpSession) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
