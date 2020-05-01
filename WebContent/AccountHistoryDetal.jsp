<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="accountHistoryDetalBeanId" scope="session" class="com.cbsinc.cms.AccountHistoryDetalBean" />
<jsp:setProperty name="accountHistoryDetalBeanId" property="*" />
<jsp:useBean id="authorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<%  request.setCharacterEncoding("UTF-8"); %>


<?xml version="1.0" encoding="UTF-8"?>
<document>
   <version>1.0</version>
   <name>GBS ltd.</name>

   <title><%=  authorizationPageBeanId.getHost() %></title>
   <subject_site><%=  authorizationPageBeanId.getSubject_site() %></subject_site>
   <site_name><%=  authorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  authorizationPageBeanId.getSite_dir() %></host>
   <domain><%=  authorizationPageBeanId.getHost() %></domain>
   <login><%= authorizationPageBeanId.getStrLogin() %></login>
   <passwdord></passwdord>
   <shoping_url>Productlist.jsp</shoping_url>
   <message><%= authorizationPageBeanId.getStrMessage() %></message>
   <shoping_url>Productlist.jsp</shoping_url>
   <balans><%=  accountHistoryDetalBeanId.getStrBalans(authorizationPageBeanId.getIntUserID()) %></balans>
   <to_navigator>wCatalog.jsp</to_navigator>
   <to_navigator_location>NavigatorLocation.jsp</to_navigator_location>
   <to_account_history>AccountHistory.jsp</to_account_history>
   <to_login>Authorization.jsp</to_login>
   <to_registration>RegPage.jsp</to_registration>
   <to_order>Order.jsp</to_order>
   <to_order_hist>OrderList.jsp</to_order_hist>
   <to_pay>PrePay.jsp</to_pay>
   <%=accountHistoryDetalBeanId.getSelectAccountHistoryDetalXML()%>

</document>