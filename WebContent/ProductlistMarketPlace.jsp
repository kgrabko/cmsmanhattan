
<jsp:useBean id="productlistBeanId" scope="request" class="com.cbsinc.cms.ProductlistBean" />
<jsp:useBean id="authorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:useBean id="catalogListBeanId" scope="session" class="com.cbsinc.cms.CatalogListBean" />
<%@page import="java.util.PropertyResourceBundle,java.util.ResourceBundle,java.io.*"%>

<%
response.setCharacterEncoding("UTF-8");
response.setContentType("text/xml");
%>
 <?xml version="1.0" encoding="UTF-8"?>
<document>
   <version>1.0</version>
   <name>Catalog</name>

   <title><%=  authorizationPageBeanId.getHost() %> </title>
   <reklama>Here may be your reklama</reklama>
   <subject_site><%=  authorizationPageBeanId.getNick_site() %></subject_site>
   <site_name><%=  authorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  authorizationPageBeanId.getSite_dir() %></host>
   <message><%= authorizationPageBeanId.getStrMessage() %></message>
   <login><%=  authorizationPageBeanId.getStrLogin() %></login>
   <passwdord><%=  authorizationPageBeanId.getStrCPasswd() %></passwdord>
   <balans><%=  authorizationPageBeanId.getBalance() %></balans>
   <search_value><%=  productlistBeanId.getSearchValueArg() %></search_value>
   <search_query><%=  productlistBeanId.getSearchquery() %></search_query	>
   <fromcost> <%=  authorizationPageBeanId.getFromCost() %> </fromcost>
   <tocost> <%=  authorizationPageBeanId.getToCost() %>  </tocost>
   <to_navigator>wCatalog.jsp</to_navigator>
   <to_navigator_location>NavigatorLocation.jsp</to_navigator_location>
   <to_account_history>AccountHistory.jsp</to_account_history>
   <to_pay>PrePay.jsp</to_pay>
   <to_order>Order.jsp</to_order>
   <to_order_hist>OrderList.jsp</to_order_hist>
   <to_login>Authorization.jsp</to_login>
   <to_registration>RegPage.jsp</to_registration>
   <owner_user_id><%=  authorizationPageBeanId.getIntUserID() %></owner_user_id>
   <role_id><%=  authorizationPageBeanId.getIntLevelUp() %></role_id>
   <user_site_id><%=  authorizationPageBeanId.getUser_site() %></user_site_id>
   <site_id><%=  authorizationPageBeanId.getSite_id() %></site_id>
   <![CDATA[]]>
   <path><%= catalogListBeanId.getCatalogUrlPath(authorizationPageBeanId) %></path>

   <dialog><%= productlistBeanId.getDialog() %></dialog>
   <is_advanced_search_open><%= productlistBeanId.getAdvancedSearchOpen() %></is_advanced_search_open>
   <is_forum_open><%= productlistBeanId.getForumOpen() %></is_forum_open>
   <internet><%= productlistBeanId.isInternet %></internet>


   <admin>
   <post_manager><%=  productlistBeanId.getTrueValue("PostManager.jsp","",authorizationPageBeanId.getIntLevelUp()==2) %></post_manager>
   <post_manager_img><%=  productlistBeanId.getTrueValue("images/post.jpg","",authorizationPageBeanId.getIntLevelUp()==2) %></post_manager_img>
   <post_manager_text><%=  productlistBeanId.getTrueValue("Post","",authorizationPageBeanId.getIntLevelUp()==2) %></post_manager_text>
   </admin>

   <xslstyle>
<!-- javascript:window.open('xsl.html' ,'New','width=400,height=210,scrollbars=yes,screenX=100,screenY=100'); -->
   <xsl_url><%= productlistBeanId.getTrueValue("xsl.jsp","",authorizationPageBeanId.getIntLevelUp()==2) %></xsl_url>
   <xsl_url_text><%=  productlistBeanId.getTrueValue("change style","",authorizationPageBeanId.getIntLevelUp()==2) %></xsl_url_text>
   </xslstyle>



<%=  productlistBeanId.getProductSimpleList( authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id()) %>
<%=  productlistBeanId.getRecommentedItems(authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id()) %>
<%=  productlistBeanId.getSponsoredBySellersItems(authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id()) %>
<%=  productlistBeanId.getItemReviewTopList(authorizationPageBeanId.getSite_id()) %>
<%=  productlistBeanId.getNewArrivalItems(authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id()) %>
<%=  productlistBeanId.getFooterLinksList(authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id()) %>

   <!-- ????????? ?????? ???????? ??? ??? ?  -->
   <empty_page_co1><%=  productlistBeanId.getPagecount_co1() == 0 %></empty_page_co1>
   <!-- ????????? ?????? ???????? ??? ???   -->
   <empty_page_co2><%=  productlistBeanId.getPagecount_co2() == 0 %></empty_page_co2>
   <!-- ????????? ?????? ???????? ??? ???   -->
   <empty_page><%=  productlistBeanId.getPagecount() == 0 %></empty_page>
   <!-- ?????????? ???????    -->
   <quantity_products><%=  productlistBeanId.getAllFoundProducts() %></quantity_products>
   <!-- ?????????? Offset   -->
   <offset><%=  productlistBeanId.getOffset() %></offset>

<next><%=  productlistBeanId.getListup() %></next>
<prev><%=  productlistBeanId.getListdown() %></prev>

<criteria1_label><%= productlistBeanId.getCriteria1_label() %></criteria1_label>
<criteria2_label><%= productlistBeanId.getCriteria2_label() %></criteria2_label>
<criteria3_label><%= productlistBeanId.getCriteria3_label() %></criteria3_label>
<criteria4_label><%= productlistBeanId.getCriteria4_label() %></criteria4_label>
<criteria5_label><%= productlistBeanId.getCriteria5_label() %></criteria5_label>
<criteria6_label><%= productlistBeanId.getCriteria6_label() %></criteria6_label>
<criteria7_label><%= productlistBeanId.getCriteria7_label() %></criteria7_label>
<criteria8_label><%= productlistBeanId.getCriteria8_label() %></criteria8_label>
<criteria9_label><%= productlistBeanId.getCriteria9_label() %></criteria9_label>
<criteria10_label><%= productlistBeanId.getCriteria10_label() %></criteria10_label>


<%=  productlistBeanId.getSelect_currency_cd() %>
<% // productlistBeanId.getTreeXMLDBList("Productlist.jsp?catalog_id","catalog", productlistBeanId.getCatalog_id() ,"select catalog_id , lable   from catalog   where  active = true and site_id = " + authorizationPageBeanId.getSite_id() + " and parent_id = " + productlistBeanId.getCatalogParent_id() ,"select catalog_id , lable   from catalog   where  active = true and parent_id = " + productlistBeanId.getCatalog_id()  )	;  %>
<%=  productlistBeanId.getSelect_tree_catalog()	 %>
<%=  productlistBeanId.getSelect_menu_catalog()	 %>
<%=  productlistBeanId.getSelect_creteria1_id() %>
<%=  productlistBeanId.getSelect_creteria2_id() %>
<%=  productlistBeanId.getSelect_creteria3_id() %>
<%=  productlistBeanId.getSelect_creteria4_id() %>
<%=  productlistBeanId.getSelect_creteria5_id() %>
<%=  productlistBeanId.getSelect_creteria6_id() %>
<%=  productlistBeanId.getSelect_creteria7_id() %>
<%=  productlistBeanId.getSelect_creteria8_id() %>
<%=  productlistBeanId.getSelect_creteria9_id() %>
<%=  productlistBeanId.getSelect_creteria10_id() %>

<%=  productlistBeanId.getSelect_dayfrom_id() %>
<%=  productlistBeanId.getSelect_mountfrom_id() %>
<%=  productlistBeanId.getSelect_yearfrom_id() %>

<%=  productlistBeanId.getSelect_dayto_id() %>
<%=  productlistBeanId.getSelect_mountto_id() %>
<%=  productlistBeanId.getSelect_yearto_id() %>

<%=  catalogListBeanId.getCatalogXMLUrlPath("Productlist.jsp?catalog_id","parent",authorizationPageBeanId.getCatalogParent_id(),authorizationPageBeanId.getCatalog_id(),authorizationPageBeanId) %>



</document>

