
<jsp:useBean id="ProductlistBeanId" scope="request" class="com.cbsinc.cms.ProductlistBean" />
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:useBean id="catalog_listBean" scope="session" class="com.cbsinc.cms.Catalog_listBean" />
<%@page import="java.util.PropertyResourceBundle,java.util.ResourceBundle,java.io.*"%>

<%
response.setCharacterEncoding("UTF-8");
response.setContentType("text/xml");
String url ;
String xsltUrl =  "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/"  +  AuthorizationPageBeanId.getLocale() + "/" + "Productlist.xsl" ; 
String xsltUrl_default = "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/" + "Productlist.xsl" ; 

String xsltpath =  "xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/"  +  AuthorizationPageBeanId.getLocale() + "/" + "Productlist.xsl" ; 
String xsltpath_default = "xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/" + "Productlist.xsl" ; 

xsltpath = request.getServletContext().getRealPath("/" +xsltpath);
xsltpath_default = request.getServletContext().getRealPath("/" +xsltpath_default);

try
{
	File file = new File(xsltpath) ;
	if( file == null  || !file.exists() ) url = xsltUrl_default ;
	else url = xsltUrl ;
		 
}
 catch (Exception e) 
{
	 throw e ;
}

PrintWriter printWriter = response.getWriter();
String tmp ="<?xml-stylesheet type=\"text/xsl\" href=\""+url+"\"?>" ;
printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
printWriter.println(tmp);

%>
 
<document>
   <version>1.0</version>
   <name>Catalog</name>

   <title><%=  AuthorizationPageBeanId.getHost() %> </title>
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
   <to_account_history>AccountHistory.jsp</to_account_history>
   <to_pay>PrePay.jsp</to_pay>
   <to_order>Order.jsp</to_order>
   <to_order_hist>OrderList.jsp</to_order_hist>
   <to_login>Authorization.jsp</to_login>
   <to_registration>RegPage.jsp</to_registration>
   <owner_user_id><%=  AuthorizationPageBeanId.getIntUserID() %></owner_user_id>
   <role_id><%=  AuthorizationPageBeanId.getIntLevelUp() %></role_id>
   <user_site_id><%=  AuthorizationPageBeanId.getUser_site() %></user_site_id>
   <site_id><%=  AuthorizationPageBeanId.getSite_id() %></site_id>
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



<%=  ProductlistBeanId.getProductSimpleList("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id()) %>
<%=  ProductlistBeanId.getCoOneProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id()) %>
<%=  ProductlistBeanId.getCoTwoProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id()) %>
<%=  ProductlistBeanId.getBlogTopProductlist(AuthorizationPageBeanId.getSite_id()) %>
<%=  ProductlistBeanId.getNewslist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id()) %>
<%=  ProductlistBeanId.getBottomList("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id()) %>

   <!-- ????????? ?????? ???????? ??? ??? ?  -->
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

<criteria1_label><%= ProductlistBeanId.getCriteria1_label() %></criteria1_label>
<criteria2_label><%= ProductlistBeanId.getCriteria2_label() %></criteria2_label>
<criteria3_label><%= ProductlistBeanId.getCriteria3_label() %></criteria3_label>
<criteria4_label><%= ProductlistBeanId.getCriteria4_label() %></criteria4_label>
<criteria5_label><%= ProductlistBeanId.getCriteria5_label() %></criteria5_label>
<criteria6_label><%= ProductlistBeanId.getCriteria6_label() %></criteria6_label>
<criteria7_label><%= ProductlistBeanId.getCriteria7_label() %></criteria7_label>
<criteria8_label><%= ProductlistBeanId.getCriteria8_label() %></criteria8_label>
<criteria9_label><%= ProductlistBeanId.getCriteria9_label() %></criteria9_label>
<criteria10_label><%= ProductlistBeanId.getCriteria10_label() %></criteria10_label>


<%=  ProductlistBeanId.getSelect_currency_cd() %>
<% // ProductlistBeanId.getTreeXMLDBList("Productlist.jsp?catalog_id","catalog", ProductlistBeanId.getCatalog_id() ,"select catalog_id , lable   from catalog   where  active = true and site_id = " + AuthorizationPageBeanId.getSite_id() + " and parent_id = " + ProductlistBeanId.getCatalogParent_id() ,"select catalog_id , lable   from catalog   where  active = true and parent_id = " + ProductlistBeanId.getCatalog_id()  )	;  %>
<%=  ProductlistBeanId.getSelect_tree_catalog()	 %>
<%=  ProductlistBeanId.getSelect_menu_catalog()	 %>
<%=  ProductlistBeanId.getSelect_creteria1_id() %>
<%=  ProductlistBeanId.getSelect_creteria2_id() %>
<%=  ProductlistBeanId.getSelect_creteria3_id() %>
<%=  ProductlistBeanId.getSelect_creteria4_id() %>
<%=  ProductlistBeanId.getSelect_creteria5_id() %>
<%=  ProductlistBeanId.getSelect_creteria6_id() %>
<%=  ProductlistBeanId.getSelect_creteria7_id() %>
<%=  ProductlistBeanId.getSelect_creteria8_id() %>
<%=  ProductlistBeanId.getSelect_creteria9_id() %>
<%=  ProductlistBeanId.getSelect_creteria10_id() %>

<%=  ProductlistBeanId.getSelect_dayfrom_id() %>
<%=  ProductlistBeanId.getSelect_mountfrom_id() %>
<%=  ProductlistBeanId.getSelect_yearfrom_id() %>

<%=  ProductlistBeanId.getSelect_dayto_id() %>
<%=  ProductlistBeanId.getSelect_mountto_id() %>
<%=  ProductlistBeanId.getSelect_yearto_id() %>

<%=  catalog_listBean.getCatalogXMLUrlPath("Productlist.jsp?catalog_id","parent",AuthorizationPageBeanId.getCatalogParent_id(),AuthorizationPageBeanId.getCatalog_id(),AuthorizationPageBeanId) %>



</document>

