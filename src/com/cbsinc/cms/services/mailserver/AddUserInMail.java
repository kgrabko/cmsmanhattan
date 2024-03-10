package com.cbsinc.cms.services.mailserver;

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


import java.net.*;
import java.util.*;
import java.io.*;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;

import com.cbsinc.cms.services.james.client.user.CreateUserRequest;
import org.springframework.http.HttpHeaders ;
import org.springframework.http.HttpEntity ;
import com.cbsinc.cms.services.james.client.domain.ResponseUsers ;

public class AddUserInMail
{

	
   private String rootPassword = "root" ;
   private String rootLogin = "root" ;
   private String addUserLogin = "root" ;
   private String addUserPassword = "root" ;
	
   String szStr = "";
	
 public void exec(String rootLogin , String rootPassword  , String addUserLogin , String addUserPassword  , String host  ) throws Exception 
 {

	
	 this.rootLogin = rootLogin ;
	 this.rootPassword = rootPassword ;
	 this.addUserLogin = addUserLogin ;
	 this.addUserPassword = addUserPassword ;
	 
	 
	 System.out.println("* Socket Mail Server *");
	 Socket s = null;
	 InputStream is = null;
	 OutputStream os = null;
	 
	 try
	 {
	   s = new Socket(host, 4555);   
	   int nPort = s.getLocalPort();
	   System.out.println("Local Port: " + nPort);
	   is = s.getInputStream();
	   os = s.getOutputStream();
	 
	  
	   while(true)
	   {
	     
		 
		 szStr = getCommand() ;
	     sendString(os, szStr);
	     os.flush();
		 Thread.currentThread().yield();		
		 if(szStr.equals("quit"))  break;    
		  
		 szStr = recvString(is);
		
		 System.out.println(szStr);
	   }
	   
	 }  
	 catch(Exception ex)
	 {
	   //System.out.println(ex.toString());
	   throw ex ;
	 }  
	 finally
	 {
		   try 
		   {
			if(is != null) is.close();
			if(os != null) os.close();
			if(s != null) s.close();
		   }
		   catch (IOException e) 
		   {
			e.printStackTrace();
		   }
	 }
	 
 }	
	
public  void main1(String args[])
{
 System.out.println("* Socket Mail Server *");
   
 Socket s = null;
 
 try
 {
   s = new Socket("localhost", 4555);
 }  
 catch(Exception ex)
 {
   System.out.println(ex.toString());
   System.exit(0);
 }  
  
 int nPort = s.getLocalPort();
 System.out.println("Local Port: " + nPort);
   
 InputStream is;
 OutputStream os;
 
 try
 {
   is = s.getInputStream();
   os = s.getOutputStream();
 
   String szStr;
   while(true)
   {
     szStr = getKbdStringNew();
	
     sendString(os, szStr);
	 
     os.flush();
	
	 if(szStr.equals("quit"))  break;    
	  
	 szStr = recvString(is);

	 System.out.println(szStr);
   }
   
   is.close();
   os.close();
   s.close();
 }  
 catch(Exception ex)
 {
   System.out.println(ex.toString());
 }  
}  

// ============================================
// sendString
// ============================================
 void sendString(OutputStream os,  String s) throws IOException
{
   for(int i = 0; i < s.length(); i++)
   {
    os.write((byte)s.charAt(i));
   }
 os.write('\n');
 os.flush();
}

// ============================================
// recvString
// ============================================
 String recvString(InputStream is)
 throws IOException
{
 String szBuf = "";
 int ch = is.read();

 while (ch >= 0 && ch != '\n')
 {
   szBuf += (char)ch;
   ch = is.read();
 }
 return szBuf;
}
	  
// ============================================
// getKbdString
// ============================================
 public String getKbdString()
{
 byte bKbd[] = new byte[256];
 int iCnt = 0;
 String szStr = "";
 
 try
 {
   iCnt = System.in.read(bKbd);
 }
 catch(Exception ex)
 {
   System.out.println(ex.toString()); 
 }
 
   
 szStr = new String(bKbd, 0, iCnt);
 szStr = szStr.trim();
 return szStr;
}

 public String getKbdStringNew()
 {
  byte[] bKbd = new byte[256];
  int iCnt = 0;
  String szStr = "";
  
  try
  {
	  bKbd = new byte[System.in.available()];
	  iCnt = System.in.read(bKbd);
  }
  catch(Exception ex)
  {
    System.out.println(ex.toString()); 
  }
  
    
  //szStr = new String(bKbd, 0, iCnt);
  szStr = new String(bKbd) ;
  szStr = szStr.trim();
  return szStr;
 }

//============================================
//getKbdString
//============================================
 public String getCommand()
{
    
	if(szStr.indexOf("Login id:") != -1 ) return rootLogin ;
	if(szStr.indexOf("Password:") != -1 ) return rootPassword ;
	
	if(szStr.indexOf("Welcome root. HELP for a list of commands") != -1 ) return "adduser "+addUserLogin+" "+addUserPassword ;
	if(szStr.indexOf("Welcome root") != -1 ) return "adduser "+addUserLogin+" "+addUserPassword ;
	
	if(szStr.indexOf("User "+addUserLogin+" added") != -1 ) return "quit" ;
	
	if(szStr.indexOf("User "+addUserLogin+" already exists") != -1 ) return "quit" ;

	String result= "\n" ;
    return result ;
}

static void addUser( String userName , String password )
{
	RestTemplate restTemplate = new RestTemplate();
	String endPoint = "http://localhost:8000//users/{user}";
	CreateUserRequest createUserRequest = new CreateUserRequest(password) ;
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	headers.setContentType(MediaType.APPLICATION_JSON);

    Map<String, String> vars = new HashMap<>();
    vars.put("user", userName);
    HttpEntity<CreateUserRequest> request = new HttpEntity<>(createUserRequest, headers);
    restTemplate.put(endPoint , request  , vars );	

}

static void addDomain( String host  )
{
	RestTemplate restTemplate = new RestTemplate();
	String endPoint = "http://localhost:8000/domains/{host}";
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	headers.setContentType(MediaType.APPLICATION_JSON);

    Map<String, String> vars = new HashMap<>();
    vars.put("host", host);
    HttpEntity<CreateUserRequest> request = new HttpEntity<>( headers);
    restTemplate.put(endPoint , request  , vars );	

}

static List<String>  getDomains()
{
	RestTemplate restTemplate = new RestTemplate();
	String endPoint = "http://localhost:8000//domains";
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<CreateUserRequest> request = new HttpEntity<>( headers);
	ResponseEntity<String[]> response = restTemplate.getForEntity(endPoint, String[].class , request);
	return Arrays.asList(response.getBody()); 

}


static List<ResponseUsers>  getUsers()
{
	RestTemplate restTemplate = new RestTemplate();
	String endPoint = "http://localhost:8000//users";
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<CreateUserRequest> request = new HttpEntity<>( headers);
	ResponseEntity<ResponseUsers[]> response = restTemplate.getForEntity(endPoint, ResponseUsers[].class , request);
	return Arrays.asList(response.getBody()); 

}


public static void main(String[] args) {
	
	//addUser( "guest@cmsmanhattan.com" , "guest" ) ;
	//addDomain( "cmsmanhattan2.com"  ) ;
	 //List<String> list =  getDomains() ;
	 List<ResponseUsers> list =  getUsers() ;
	 for(ResponseUsers row : list ) 
	 {
		 System.out.println(row);
	 }
	
}


}
