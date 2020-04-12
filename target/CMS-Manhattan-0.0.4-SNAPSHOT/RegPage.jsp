<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:setProperty name="AuthorizationPageBeanId" property="*" />
<?xml version="1.0" encoding="UTF-8"?>
<document>
   <version>1.0</version>
   <name>Authorization</name>

   <title><%=  AuthorizationPageBeanId.getHost() %></title>
   <subject_site><%=  AuthorizationPageBeanId.getSubject_site() %></subject_site>
   <site_name><%=  AuthorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  AuthorizationPageBeanId.getSite_dir() %></host>
   <domain><%=  AuthorizationPageBeanId.getHost() %></domain>
   <login><%= AuthorizationPageBeanId.getStrLogin() %></login>
   <passwdord><%=  AuthorizationPageBeanId.getStrCPasswd() %></passwdord>
   <firstname><%= AuthorizationPageBeanId.getStrFirstName() %></firstname>
   <lastname><%= AuthorizationPageBeanId.getStrLastName() %></lastname>
   <company><%= AuthorizationPageBeanId.getStrCompany() %></company>
   <email><%= AuthorizationPageBeanId.getStrEMail() %></email>
   <phone><%= AuthorizationPageBeanId.getStrPhone() %></phone>
   <mphone><%= AuthorizationPageBeanId.getStrMPhone() %></mphone>
   <fax><%= AuthorizationPageBeanId.getStrFax() %></fax>
   <icq><%= AuthorizationPageBeanId.getStrIcq() %></icq>
   <website><%= AuthorizationPageBeanId.getStrWebsite() %></website>
   <question><%= AuthorizationPageBeanId.getStrQuestion() %></question>
   <answer><%= AuthorizationPageBeanId.getStrAnswer() %></answer>
   <country><%= AuthorizationPageBeanId.getStrCountry() %></country>
   <city><%= AuthorizationPageBeanId.getStrCity() %></city>
   <site><%= AuthorizationPageBeanId.getSite_id() %></site>
   <message><%= AuthorizationPageBeanId.getStrMessage() %></message>
   <country_id><%= AuthorizationPageBeanId.getCountry_id() %></country_id>
   <city_id><%= AuthorizationPageBeanId.getCity_id() %></city_id>
   <currency_id><%= AuthorizationPageBeanId.getCurrency_id() %></currency_id>

	<%=  AuthorizationPageBeanId.getSelect_site() %>
	<%=  AuthorizationPageBeanId.getSelect_country() %>
	<%=  AuthorizationPageBeanId.getSelect_city() %>
	<%=  AuthorizationPageBeanId.getSelect_currency() %>
	<!--  AuthorizationPageBeanId.getXMLDBList("Authorization.jsp?currency_id","currency", AuthorizationPageBeanId.getCurrency_id()  ,"SELECT currency_id , currency_desc FROM currency  WHERE active = true") -->

</document>