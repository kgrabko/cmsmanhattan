package com.cbsinc.cms.jms.controllers;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.james.domainlist.api.DomainListManagementMBean;
import org.apache.james.user.api.UsersRepositoryManagementMBean;
import org.apache.log4j.Logger;


public class AddUserToMailMessageBean extends AbstractMessageBean {



	 private Logger log = Logger.getLogger(SendMailMessageBean.class);
	
	
	
	static public String messageQuery = "mq_adduser_to_mail" ;
	
	transient private ResourceBundle resources_cms_settings = null ;
	
	private String  login ; 
	private String  password ;
	private String  host ;

	
	public AddUserToMailMessageBean()
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
			
  		  //mailSettingsSession.exec(login ,password ,user_login ,user_password.substring(0,4)+ user_login,host);	 
  		  addUser(login ,password ,user_login ,user_password.substring(0,4)+ user_login,host , "9999") ;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			log.error(e);
		}
		
		
	}
	
	
	
	 void addUser(String jlogin , String jpassword,  String email , String password, String host , String port  ){
	    try{
	        String serverUrl = "service:jmx:rmi:///jndi/rmi://"+host+":"+port+"/jmxrmi"; // default port 9999
	        String beanNameUser = "org.apache.james:type=component,name=usersrepository";
	        String beanNameDomain = "org.apache.james:type=component,name=domainlist";

	        MBeanServerConnection server = JMXConnectorFactory.connect(new JMXServiceURL(serverUrl)).getMBeanServerConnection();

	        UsersRepositoryManagementMBean userBean =  MBeanServerInvocationHandler.newProxyInstance(server, new ObjectName(beanNameUser), UsersRepositoryManagementMBean.class, false);
	        DomainListManagementMBean domainBean =  MBeanServerInvocationHandler.newProxyInstance(server, new ObjectName(beanNameDomain), DomainListManagementMBean.class, false);

	        if(domainBean.containsDomain(email.split("@")[1])
	                && !userBean.verifyExists(email)){
	            System.out.println("creating email : "+email );
	            userBean.addUser(email,password);
	            System.out.println(" email : "+email + "was created" );
	        }else{
	            log.error("domain does not exist or user already exists !!");
	        }

	    }catch (Exception e){
	        e.printStackTrace();
			log.error("Something went wrong",e);
	    }
	}
	

}
