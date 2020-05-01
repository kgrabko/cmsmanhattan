<%@page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<%@page import="java.util.PropertyResourceBundle,java.util.ResourceBundle"%>
<jsp:useBean id="searchBeanId" scope="request" class="com.cbsinc.cms.SearchBean" />
<jsp:useBean id="authorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:useBean id="catalogListBeanId" scope="session" class="com.cbsinc.cms.CatalogListBean" />

<?xml version="1.0" encoding="UTF-8"?>
<document>
   <version>1.0</version>
   <name>Catalog</name>

   <title>Catalog test </title>
   <reklama>Here may be your reklama</reklama>
   <subject_site><%=  authorizationPageBeanId.getNick_site() %></subject_site>
   <site_name><%=  authorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  authorizationPageBeanId.getSite_dir() %></host>
   <message><%= authorizationPageBeanId.getStrMessage() %></message>
   <login><%=  authorizationPageBeanId.getStrLogin() %></login>
   <passwdord><%=  authorizationPageBeanId.getStrCPasswd() %></passwdord>
   <balans><%=  authorizationPageBeanId.getBalance() %></balans>
   <search_value><%=  searchBeanId.getSearchValueArg() %></search_value>
   <search_query><%=  searchBeanId.getSearchquery() %></search_query	>
   <fromcost> <%=  authorizationPageBeanId.getFromCost() %> </fromcost>
   <tocost> <%=  authorizationPageBeanId.getToCost() %>  </tocost>
   <to_navigator>wCatalog.jsp</to_navigator>
   <to_navigator_location>NavigatorLocation.jsp</to_navigator_location>
   <to_account_history>wAccountHist.jsp</to_account_history>
   <to_pay>PrePay.jsp</to_pay>
   <to_order>Order.jsp</to_order>
   <to_order_hist>OrderList.jsp</to_order_hist>
   <to_login>Authorization.jsp</to_login>
   <to_registration>RegPage.jsp</to_registration>
   <owner_user_id><%=  authorizationPageBeanId.getIntUserID() %></owner_user_id>
   <role_id><%=  authorizationPageBeanId.getIntLevelUp() %></role_id>
   <user_site_id><%=  authorizationPageBeanId.getUser_site() %></user_site_id>
   <site_id><%=  authorizationPageBeanId.getSite_id() %></site_id>
   <country_id><%= authorizationPageBeanId.getCountry_id() %></country_id>
   <city_id><%= authorizationPageBeanId.getCity_id() %></city_id>
   <country><%= authorizationPageBeanId.getStrCountry() %></country>
   <city><%= authorizationPageBeanId.getStrCity() %></city>
 
   <![CDATA[]]>
   <path><%= catalogListBeanId.getCatalogUrlPath(authorizationPageBeanId) %></path>

   <dialog><%= searchBeanId.getDialog() %></dialog>
   <is_advanced_search_open><%= searchBeanId.getAdvancedSearchOpen() %></is_advanced_search_open>
   <is_forum_open><%= searchBeanId.getForumOpen() %></is_forum_open>
   <internet><%= searchBeanId.isInternet %></internet>


   <admin>
   <post_manager><%=  searchBeanId.getTrueValue("PostManager.jsp","",authorizationPageBeanId.getIntLevelUp()==2) %></post_manager>
   <post_manager_img><%=  searchBeanId.getTrueValue("images/post.jpg","",authorizationPageBeanId.getIntLevelUp()==2) %></post_manager_img>
   <post_manager_text><%=  searchBeanId.getTrueValue("Post","",authorizationPageBeanId.getIntLevelUp()==2) %></post_manager_text>
   </admin>

   <xslstyle>
<!-- javascript:window.open('xsl.html' ,'New','width=400,height=210,scrollbars=yes,screenX=100,screenY=100'); -->
   <xsl_url><%= searchBeanId.getTrueValue("xsl.jsp","",authorizationPageBeanId.getIntLevelUp()==2) %></xsl_url>
   <xsl_url_text><%=  searchBeanId.getTrueValue("change style","",authorizationPageBeanId.getIntLevelUp()==2) %></xsl_url_text>
   </xslstyle>



<%=  searchBeanId.getProductlist("" + authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id()) %>
<%=  searchBeanId.getProductSimpleList("" + authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id()) %>
<%=  searchBeanId.getCoOneProductlist("" + authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id()) %>
<%=  searchBeanId.getCoTwoProductlist("" + authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id()) %>
<%=  authorizationPageBeanId.getSelect_country() %>
<%=  authorizationPageBeanId.getSelect_city() %>

   <!-- ????????? ?????? ???????? ??? ???   -->
   <empty_page_co1><%=  searchBeanId.getPagecount_co1() == 0 %></empty_page_co1>
   <!-- ????????? ?????? ???????? ??? ???   -->
   <empty_page_co2><%=  searchBeanId.getPagecount_co2() == 0 %></empty_page_co2>
   <!-- ????????? ?????? ???????? ??? ???   -->
   <empty_page><%=  searchBeanId.getPagecount() == 0 %></empty_page>
   <!-- ?????????? ???????    -->
   <quantity_products><%=  searchBeanId.getAllFoundProducts() %></quantity_products>
   <!-- ?????????? Offset   -->
   <offset><%=  searchBeanId.getOffset() %></offset>

<next><%=  searchBeanId.getListup() %></next>
<prev><%=  searchBeanId.getListdown() %></prev>


<%=  searchBeanId.getSelect_currency_cd() %>




</document>

