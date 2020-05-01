<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<jsp:useBean id="authorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:setProperty name="authorizationPageBeanId" property="*" />
<%



%>
<?xml version="1.0" encoding="UTF-8"?>
<document>
   <version>1.0</version>
   <name>Authorization</name>

   <title>Authorization page</title>
   <subject_site><%=  authorizationPageBeanId.getSubject_site() %></subject_site>
   <site_name><%=  authorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  authorizationPageBeanId.getSite_dir() %></host>
   <login><%= authorizationPageBeanId.getStrLogin() %></login>
   <passwdord><%=  authorizationPageBeanId.getStrCPasswd() %></passwdord>
   <firstname><%= authorizationPageBeanId.getStrFirstName() %></firstname>
   <lastname><%= authorizationPageBeanId.getStrLastName() %></lastname>
   <company><%= authorizationPageBeanId.getStrCompany() %></company>
   <email><%= authorizationPageBeanId.getStrEMail() %></email>
   <phone><%= authorizationPageBeanId.getStrPhone() %></phone>
   <mphone><%= authorizationPageBeanId.getStrMPhone() %></mphone>
   <fax><%= authorizationPageBeanId.getStrFax() %></fax>
   <icq><%= authorizationPageBeanId.getStrIcq() %></icq>
   <website><%= authorizationPageBeanId.getStrWebsite() %></website>
   <question><%= authorizationPageBeanId.getStrQuestion() %></question>
   <answer><%= authorizationPageBeanId.getStrAnswer() %></answer>
   <country><%= authorizationPageBeanId.getStrCountry() %></country>
   <city><%= authorizationPageBeanId.getStrCity() %></city>
   <site><%= authorizationPageBeanId.getSite_id() %></site>
   <message><%= authorizationPageBeanId.getStrMessage() %></message>
   <country_id><%= authorizationPageBeanId.getCountry_id() %></country_id>
   <city_id><%= authorizationPageBeanId.getCity_id() %></city_id>
   <currency_id><%= authorizationPageBeanId.getCurrency_id() %></currency_id>

   <!--  for members -->
   <do_form_1>
                <form-header>
                <name>login</name>
		<method>post</method>
		<action>Authorization.jsp</action>
                </form-header>

                <fields>
                <ref_1>/login</ref_1>
                <ref_2>/passwdord</ref_2>
                <ref_3>/lang_cd</ref_3>
                <ref_4>product/currency_cd</ref_4>
                </fields>
   </do_form_1>


   <do_form_2>
   <!-- become new member -->
                <form-header>
                <name>registration</name>
		<method>post</method>
		<action>RegPage.jsp</action>
                </form-header>
   </do_form_2>

<%=  authorizationPageBeanId.getSelect_site() %>
<%=  authorizationPageBeanId.getSelect_country() %>
<%=  authorizationPageBeanId.getSelect_city() %>
<%=  authorizationPageBeanId.getSelect_currency() %>
<!--  authorizationPageBeanId.getXMLDBList("Authorization.jsp?currency_id","currency", authorizationPageBeanId.getCurrency_id()  ,"SELECT currency_id , currency_desc FROM currency  WHERE active = true") -->

</document>