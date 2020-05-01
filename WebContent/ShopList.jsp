<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="authorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:useBean id="authorizationPageFaced" scope="application" class="com.cbsinc.cms.faceds.AuthorizationPageFaced" />
<?xml version="1.0" encoding="UTF-8"?>
<document>
   <version>1.0</version>
   <name>Catalog</name>

   <title>Catalog test </title>

   <subject_site><%=  authorizationPageBeanId.getNick_site() %></subject_site>
   <site_name><%=  authorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  authorizationPageBeanId.getSite_dir() %></host>

   <login><%=  authorizationPageBeanId.getStrLogin() %></login>
   <passwdord><%=  authorizationPageBeanId.getStrCPasswd() %></passwdord>
   <balans><%=  authorizationPageFaced.getBalans(authorizationPageBeanId.getIntUserID()) %></balans>
   <to_navigator>wCatalog.jsp</to_navigator>
   <to_navigator_location>NavigatorLocation.jsp</to_navigator_location>
   <to_account_history>wAccountHist.jsp</to_account_history>
   <to_pay>PrePay.jsp</to_pay>
   <to_order>Order.jsp</to_order>
   <to_order_hist>OrderList.jsp</to_order_hist>
   <to_login>Authorization.jsp</to_login>
   <to_registration>RegPage.jsp</to_registration>
<%=  authorizationPageFaced.getXMLDBList("Authorization.jsp?site_id","site",authorizationPageBeanId.getSite_id(),"SELECT  site_id, host FROM  site WHERE  active = true" ) %>
</document>