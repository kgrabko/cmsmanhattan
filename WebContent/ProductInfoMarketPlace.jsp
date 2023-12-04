<%@ page errorPage="error.jsp" %>
<jsp:useBean id="authorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:useBean id="itemDescriptionBeanId" scope="request" class="com.cbsinc.cms.ItemDescriptionBean" />
<%@page import="java.util.PropertyResourceBundle,java.util.ResourceBundle,java.io.*"%>

<%
response.setCharacterEncoding("UTF-8");
response.setContentType("text/xml");
String url ;
String xsltUrl =  "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  authorizationPageBeanId.getSite_dir() + "/"  +  authorizationPageBeanId.getLocale() + "/" + "policy.xsl" ; 
String xsltUrl_default = "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  authorizationPageBeanId.getSite_dir() + "/" + "policy.xsl" ; 

String xsltpath =  "xsl/" +  authorizationPageBeanId.getSite_dir() + "/"  +  authorizationPageBeanId.getLocale() + "/" + "policy.xsl" ; 
String xsltpath_default = "xsl/" +  authorizationPageBeanId.getSite_dir() + "/" + "policy.xsl" ; 

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
   <name>Policy</name>

   <title><%=  authorizationPageBeanId.getHost() %></title>
   <subject_site><%=  authorizationPageBeanId.getSubject_site() %></subject_site>
   <site_name><%=  authorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  authorizationPageBeanId.getSite_dir() %></host>
   <domain><%=  authorizationPageBeanId.getHost() %></domain>
   <admin>
   <post_manager><%=  itemDescriptionBeanId.getTrueValue("PolicyManager.jsp","",authorizationPageBeanId.getIntLevelUp()==2) %></post_manager>
   <post_manager_img><%=  itemDescriptionBeanId.getTrueValue("images/post.jpg","",authorizationPageBeanId.getIntLevelUp()==2) %></post_manager_img>
   <post_manager_text><%=  itemDescriptionBeanId.getTrueValue("Post","",authorizationPageBeanId.getIntLevelUp()==2) %></post_manager_text>
   </admin>

   <role_id><%=  authorizationPageBeanId.getIntLevelUp() %></role_id>
   <user_site_id><%=  authorizationPageBeanId.getUser_site() %></user_site_id>
   <internet><%= itemDescriptionBeanId.isInternet() %></internet>
   <login><%=  authorizationPageBeanId.getStrLogin() %></login>
   <shoping_url>Productlist.jsp</shoping_url>
   <message><%= authorizationPageBeanId.getStrMessage() %></message>
   <shoping_url>Productlist.jsp</shoping_url>
   <balans><%= "" + itemDescriptionBeanId.getBalans() %></balans>
   <to_account_history>AccountHistory.jsp</to_account_history>
   <to_login>Authorization.jsp</to_login>
   <to_registration>RegPage.jsp</to_registration>
   <to_order>Order.jsp</to_order>
   <to_order_hist>OrderList.jsp</to_order_hist>
   <to_pay>PrePay.jsp</to_pay>
   <owner_user_id><%=  authorizationPageBeanId.getIntUserID() %></owner_user_id>
   <site_id><%=  authorizationPageBeanId.getSite_id() %></site_id>
   <show_blog><%=  itemDescriptionBeanId.getStrShow_forum() %></show_blog>
   <show_rating1><%=  itemDescriptionBeanId.getStrShow_ratimg1() %></show_rating1> 
   <show_rating2><%=  itemDescriptionBeanId.getStrShow_ratimg2() %></show_rating2>
   <show_rating3><%=  itemDescriptionBeanId.getStrShow_ratimg3() %></show_rating3>   

   

<product>
<page_url>http://<%= request.getServerName() %>:<%=request.getServerPort()%>/Policy.jsp?policy_byproductid=<jsp:getProperty name="itemDescriptionBeanId" property="product_id" /></page_url>
<product_id><jsp:getProperty name="itemDescriptionBeanId" property="product_id" /></product_id>
<name><jsp:getProperty name="itemDescriptionBeanId" property="productName" /></name>
<file_exist><jsp:getProperty name="itemDescriptionBeanId" property="file_exist" /></file_exist>
<icon><jsp:getProperty name="itemDescriptionBeanId" property="imgURL" /></icon>
<image><jsp:getProperty name="itemDescriptionBeanId" property="bigimgURL" /></image>
<image_type><%= itemDescriptionBeanId.getBigimgURL().substring(itemDescriptionBeanId.getBigimgURL().indexOf(".") + 1 ) %></image_type>
<product_url>http://<%= request.getServerName() %>:<%=request.getServerPort()%>/<jsp:getProperty name="itemDescriptionBeanId" property="productURL" /></product_url>
<back_url><jsp:getProperty name="itemDescriptionBeanId" property="back_url" /></back_url>
<description><jsp:getProperty name="itemDescriptionBeanId" property="productDescription" /></description>
<amount><jsp:getProperty name="itemDescriptionBeanId" property="productCost" /></amount>
<currency>
 <code><jsp:getProperty name="itemDescriptionBeanId" property="currency_cd" /></code>
 <description><jsp:getProperty name="itemDescriptionBeanId" property="currency_desc" /></description>
</currency>
<statistic><%= itemDescriptionBeanId.getStatistic() %></statistic>  
<cdate><%= itemDescriptionBeanId.getStrCDate() %></cdate>  
<creator_info_user_id><%=  itemDescriptionBeanId.getCreator_info_user_id() %></creator_info_user_id>
</product>

<%=  itemDescriptionBeanId.getRating1_xml() %>
<%=  itemDescriptionBeanId.getSelect_currencies() %>
<%=itemDescriptionBeanId.getProductInfoColumnOne("" + authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id(),itemDescriptionBeanId.getProduct_id() )%>
<%=itemDescriptionBeanId.getProductInfoColumnTwo("" + authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id(),itemDescriptionBeanId.getProduct_id() )%>

<%=itemDescriptionBeanId.getProductInfoAttchedFiles("" + authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id(),itemDescriptionBeanId.getProduct_id() )%>
<%=itemDescriptionBeanId.getProductInfoDescriptionTabs("" + authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id(),itemDescriptionBeanId.getProduct_id() )%>

<%=itemDescriptionBeanId.getProductInfoReviewMessages("" + authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id(),itemDescriptionBeanId.getProduct_id() )%>
<%=itemDescriptionBeanId.getNewArrivalItems("" + authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id())%>
<%=itemDescriptionBeanId.getFooterLinksList("" + authorizationPageBeanId.getIntUserID(),authorizationPageBeanId.getSite_id())%>

   <!-- ????????? ?????? ???????? ??? ??? ? ?   -->
   <empty_page_ext1><%=  itemDescriptionBeanId.getPagecount_ext1() == 0 %></empty_page_ext1>
   <!-- ????????? ?????? ???????? ??? ???   -->
   <empty_page_ext2><%=  itemDescriptionBeanId.getPagecount_ext2() == 0 %></empty_page_ext2>
   
   <%=  itemDescriptionBeanId.getSelect_tree_catalog()	 %>
   <%=  itemDescriptionBeanId.getSelectCatalogXMLUrlPath() %>
   <%=  itemDescriptionBeanId.getSelect_menu_catalog()	 %>

</document>

