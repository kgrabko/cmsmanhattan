<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="payBeanId" scope="request" class="com.cbsinc.cms.PayBean" />
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<%  request.setCharacterEncoding("UTF-8"); %>
<?xml version="1.0" encoding="UTF-8"?>
<document>
   <version>1.0</version>
   <name>GBS ltd.</name>
   <title>SELECT PAY SYSTEM</title>
   <subject_site><%=  AuthorizationPageBeanId.getNick_site() %></subject_site>
   <site_name><%=  AuthorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  AuthorizationPageBeanId.getSite_dir() %></host>
   <login><%= AuthorizationPageBeanId.getStrLogin() %></login>
   <passwdord></passwdord>
   <shoping_url>Productlist.jsp</shoping_url>
   <message><%= AuthorizationPageBeanId.getStrMessage() %></message>
   <shoping_url>Productlist.jsp</shoping_url>
   <balans><%=  payBeanId.getStrBalans(AuthorizationPageBeanId.getIntUserID()) %></balans>
   <to_navigator>wCatalog.jsp</to_navigator>
   <to_navigator_location>NavigatorLocation.jsp</to_navigator_location>
   <to_account_history>AccountHistory.jsp</to_account_history>
   <to_login>Authorization.jsp</to_login>
   <to_registration>RegPage.jsp</to_registration>
   <to_order>Order.jsp</to_order>
   <to_order_hist>OrderList.jsp</to_order_hist>
   <to_pay>PrePay.jsp</to_pay>


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
   <amount><%= payBeanId.getAmount() %></amount>
   <currency_paysys_id><%= payBeanId.getCurrency_cd()   %></currency_paysys_id>
   <account_history_id><%= payBeanId.getAccount_hist_id() %></account_history_id>
   <shop_paysys_id><%= AuthorizationPageBeanId.getPaysys_shop_cd() %></shop_paysys_id>
   <type_creditcard><%= payBeanId.getChoosenTypeCreditCard()%></type_creditcard>
   <card_payment><%= payBeanId.getCardPayment()%></card_payment>
   <wallet_payment><%= payBeanId.getWalletPayment()%></wallet_payment>
   <webmoney_payment><%= payBeanId.getWebMoneyPayment()%></webmoney_payment>
   <papida_payment><%= payBeanId.getRapidaPayment()%></papida_payment>
   <paycash_payment><%= payBeanId.getPayCashPayment()%></paycash_payment>
   <eport_payment><%= payBeanId.getEPortPayment()%></eport_payment>
   <kredit_pilotpayment><%=payBeanId.getKreditPilotPayment()%></kredit_pilotpayment>
   <url_rezalt_banking_ok><%= "http://" + request.getServerName() + ":" + request.getServerPort() + "/AccountHistory.jsp"   %></url_rezalt_banking_ok>
   <url_rezalt_banking_no><%= "http://" + request.getServerName() + ":" + request.getServerPort() + "/Productlist.jsp?catalog_id=-2"   %></url_rezalt_banking_no>
   <decsription><%=  payBeanId.getDescription() %></decsription>

</document>

