<%@page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<%@page import="java.util.PropertyResourceBundle,java.util.ResourceBundle"%>
<jsp:useBean id="ProductlistBeanId" scope="request" class="com.cbsinc.cms.ProductlistBean" />
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:useBean id="catalog_listBean" scope="session" class="com.cbsinc.cms.Catalog_listBean" />

<?xml version="1.0" encoding="UTF-8"?>
<document>
   <version>1.0</version>
   <name>Catalog</name>

   <title>Catalog test </title>
   <reklama>Here may be your reklama</reklama>
   <subject_site><%=  AuthorizationPageBeanId.getNick_site() %></subject_site>
   <site_name><%=  AuthorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  AuthorizationPageBeanId.getSite_dir() %></host>
   <message><%= AuthorizationPageBeanId.getStrMessage() %></message>
   <login><%=  AuthorizationPageBeanId.getStrLogin() %></login>
   <passwdord><%=  AuthorizationPageBeanId.getStrCPasswd() %></passwdord>
   <balans><%=  AuthorizationPageBeanId.getBalance() %></balans>
   <search_value><%=  ProductlistBeanId.getSearchValueArg() %></search_value>
   <search_query><%=  ProductlistBeanId.getSearchquery() %></search_query	>
   <fromcost> <%=  AuthorizationPageBeanId.getFromCost() %> </fromcost>
   <tocost> <%=  AuthorizationPageBeanId.getToCost() %>  </tocost>
   <to_navigator>wCatalog.jsp</to_navigator>
   <to_navigator_location>NavigatorLocation.jsp</to_navigator_location>
   <to_account_history>wAccountHist.jsp</to_account_history>
   <to_pay>PrePay.jsp</to_pay>
   <to_order>Order.jsp</to_order>
   <to_order_hist>OrderList.jsp</to_order_hist>
   <to_login>Authorization.jsp</to_login>
   <to_registration>RegPage.jsp</to_registration>
   <owner_user_id><%=  AuthorizationPageBeanId.getIntUserID() %></owner_user_id>
   <role_id><%=  AuthorizationPageBeanId.getIntLevelUp() %></role_id>
   <user_site_id><%=  AuthorizationPageBeanId.getUser_site() %></user_site_id>
   <site_id><%=  AuthorizationPageBeanId.getSite_id() %></site_id>
   <country_id><%= AuthorizationPageBeanId.getCountry_id() %></country_id>
   <city_id><%= AuthorizationPageBeanId.getCity_id() %></city_id>
   <country><%= AuthorizationPageBeanId.getStrCountry() %></country>
   <city><%= AuthorizationPageBeanId.getStrCity() %></city>
 
   <![CDATA[]]>
   <path><%= catalog_listBean.getCatalogUrlPath(AuthorizationPageBeanId) %></path>

   <dialog><%= ProductlistBeanId.getDialog() %></dialog>
   <is_advanced_search_open><%= ProductlistBeanId.getAdvancedSearchOpen() %></is_advanced_search_open>
   <is_forum_open><%= ProductlistBeanId.getForumOpen() %></is_forum_open>
   <internet><%= ProductlistBeanId.isInternet %></internet>


   <admin>
   <post_manager><%=  ProductlistBeanId.getTrueValue("PostManager.jsp","",AuthorizationPageBeanId.getIntLevelUp()==2) %></post_manager>
   <post_manager_img><%=  ProductlistBeanId.getTrueValue("images/post.jpg","",AuthorizationPageBeanId.getIntLevelUp()==2) %></post_manager_img>
   <post_manager_text><%=  ProductlistBeanId.getTrueValue("Post","",AuthorizationPageBeanId.getIntLevelUp()==2) %></post_manager_text>
   </admin>

   <xslstyle>
<!-- javascript:window.open('xsl.html' ,'New','width=400,height=210,scrollbars=yes,screenX=100,screenY=100'); -->
   <xsl_url><%= ProductlistBeanId.getTrueValue("xsl.jsp","",AuthorizationPageBeanId.getIntLevelUp()==2) %></xsl_url>
   <xsl_url_text><%=  ProductlistBeanId.getTrueValue("change style","",AuthorizationPageBeanId.getIntLevelUp()==2) %></xsl_url_text>
   </xslstyle>



<%=  ProductlistBeanId.getProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id()) %>
<%=  ProductlistBeanId.getProductSimpleList("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id()) %>
<%=  ProductlistBeanId.getCoOneProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id()) %>
<%=  ProductlistBeanId.getCoTwoProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id()) %>
<%=  AuthorizationPageBeanId.getSelect_country() %>
<%=  AuthorizationPageBeanId.getSelect_city() %>

   <!-- ????????? ?????? ???????? ??? ???   -->
   <empty_page_co1><%=  ProductlistBeanId.getPagecount_co1() == 0 %></empty_page_co1>
   <!-- ????????? ?????? ???????? ??? ???   -->
   <empty_page_co2><%=  ProductlistBeanId.getPagecount_co2() == 0 %></empty_page_co2>
   <!-- ????????? ?????? ???????? ??? ???   -->
   <empty_page><%=  ProductlistBeanId.getPagecount() == 0 %></empty_page>
   <!-- ?????????? ???????    -->
   <quantity_products><%=  ProductlistBeanId.getAllFoundProducts() %></quantity_products>
   <!-- ?????????? Offset   -->
   <offset><%=  ProductlistBeanId.getOffset() %></offset>

<next><%=  ProductlistBeanId.getListup() %></next>
<prev><%=  ProductlistBeanId.getListdown() %></prev>


<%=  ProductlistBeanId.getSelect_currency_cd() %>




</document>

