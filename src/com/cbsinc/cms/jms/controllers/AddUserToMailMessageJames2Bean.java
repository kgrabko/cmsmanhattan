package com.cbsinc.cms.jms.controllers;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import com.cbsinc.cms.services.mailserver.AddUserInMail;


public class AddUserToMailMessageJames2Bean extends AbstractMessageBean {



	 private Logger log = Logger.getLogger(SendMailMessageBean.class);
	
	
	
	static public String messageQuery = "mq_adduser_to_mail" ;
	
	transient private ResourceBundle resources_cms_settings = null ;
	
	private String  login ; 
	private String  password ;
	private String  host ;

	
	public AddUserToMailMessageJames2Bean()
	{
		if( resources_cms_settings == null ) resources_cms_settings =  PropertyResourceBundle.getBundle("SetupApplicationResources");
		    login = resources_cms_settings.getString("james_login").trim() ;
  		    password = resources_cms_settings.getString("james_password").trim() ;
  		    host = resources_cms_settings.getString("james_host").trim() ;

	}
	

	
	
	

	
	public void onMessage(com.cbsinc.cms.jms.controllers.Message message, ServletContext applicationContext, HttpSession httpSession) 
	{

		String user_login = "" ;
		String user_password = "" ;
		if( message.get("user_login") instanceof  String ) user_login = (String) message.get("user_login") ;
		if( message.get("user_password") instanceof  String ) user_password = (String) message.get("user_password") ;
		
		try 
		{
			
  		  AddUserInMail mailSettingsSession = new AddUserInMail();
  		  mailSettingsSession.exec(login ,password ,user_login ,user_password.substring(0,4)+ user_login,host);	   
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			log.error(e);
		}
		
		
	}
	

}
