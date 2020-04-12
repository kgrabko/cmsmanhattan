<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="accountHistoryBeanId" scope="session" class="com.cbsinc.cms.AccountHistoryBean" />
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:setProperty name="accountHistoryBeanId" property="*" />
<%  request.setCharacterEncoding("UTF-8"); %>
<?xml version="1.0" encoding="UTF-8"?>
<document>
   <version>1.0</version>
   <name>GBS ltd.</name>

   <title><%=  AuthorizationPageBeanId.getHost() %></title>
   <subject_site><%=  AuthorizationPageBeanId.getSubject_site() %></subject_site>
   <site_name><%=  AuthorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  AuthorizationPageBeanId.getSite_dir() %></host>
   <domain><%=  AuthorizationPageBeanId.getHost() %></domain>
   <login><%= AuthorizationPageBeanId.getStrLogin() %></login>
   <passwdord></passwdord>
   <shoping_url>Productlist.jsp</shoping_url>
   <message><%= AuthorizationPageBeanId.getStrMessage() %></message>
   <shoping_url>Productlist.jsp</shoping_url>
   <balans><%=  accountHistoryBeanId.getStrBalans(AuthorizationPageBeanId.getIntUserID()) %></balans>
   <to_navigator>wCatalog.jsp</to_navigator>
   <to_navigator_location>NavigatorLocation.jsp</to_navigator_location>
   <to_account_history>AccountHistory.jsp</to_account_history>
   <to_login>Authorization.jsp</to_login>
   <to_registration>RegPage.jsp</to_registration>
   <to_order>Order.jsp</to_order>
   <to_order_hist>OrderList.jsp</to_order_hist>
   <to_pay>PrePay.jsp</to_pay>

   <%=accountHistoryBeanId.getSelectAccountHistoryXML()  %>

<next><jsp:getProperty name="accountHistoryBeanId" property="listup" /></next>
<prev><jsp:getProperty name="accountHistoryBeanId" property="listdown" /></prev>

</document>