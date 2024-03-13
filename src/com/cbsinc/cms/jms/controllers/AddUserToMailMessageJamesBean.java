package com.cbsinc.cms.jms.controllers;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import com.cbsinc.cms.services.james.client.domain.faceds.DomainApiClient;
import com.cbsinc.cms.services.james.client.user.faceds.UserApiClient;
import com.cbsinc.cms.services.mailserver.AddUserInMail;


public class AddUserToMailMessageJamesBean extends AbstractMessageBean {



	 private Logger log = Logger.getLogger(SendMailMessageBean.class);
	
	
	
	static public String messageQuery = "mq_adduser_to_mail" ;
	
	private ResourceBundle resources_cms_settings = null ;
	
	private String  login ; 
	private String  password ;
	private String  host ;
	private String  defaultDomain ;

	
	public AddUserToMailMessageJamesBean()
	{
		if( resources_cms_settings == null ) resources_cms_settings =  PropertyResourceBundle.getBundle("appconfig");
		    login = resources_cms_settings.getString("james_login").trim() ;
  		    password = resources_cms_settings.getString("james_password").trim() ;
  		    host = resources_cms_settings.getString("james_host").trim() ;
  			defaultDomain = resources_cms_settings.getString("james_domain").trim();

	}
	

	
	
	

	
	public void onMessage(com.cbsinc.cms.jms.controllers.Message message, ServletContext applicationContext, HttpSession httpSession) 
	{

		String user_login = "" ;
		String user_password = "" ;
		String user_domain = "" ;
		if( message.get("user_login") instanceof  String ) user_login = (String) message.get("user_login") ;
		if( message.get("user_domain") instanceof  String ) user_domain = (String) message.get("user_domain") ;
		if( message.get("user_password") instanceof  String ) user_password = (String) message.get("user_password") ;
		
		try 
		{
			
			//UserApiClient.getInstanse().existUser(user_login + "@" + user_domain ) ;
			if(user_domain.equals("localhost")) user_domain = defaultDomain ;
			
			if(DomainApiClient.getInstanse().existDomain(user_domain) ) 
			{
				UserApiClient.getInstanse().addUser(user_login + "@" + user_domain , user_password) ;
			}
			else 
			{
				DomainApiClient.getInstanse().addDomain(user_domain);
				UserApiClient.getInstanse().addUser(user_login + "@" + user_domain , user_password) ;
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			log.error(e);
		}
		
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		String user_login = "gust11" ;
		String user_password = "gust11" ;
		String user_domain = "cmsmanhattan3.com" ;
		
		if(DomainApiClient.getInstanse().existDomain(user_domain) ) 
		{
			UserApiClient.getInstanse().addUser(user_login + "@" + user_domain , user_password) ;
		}
		else 
		{
			DomainApiClient.getInstanse().addDomain(user_domain);
			UserApiClient.getInstanse().addUser(user_login + "@" + user_domain , user_password) ;
		}
		
	}

}
