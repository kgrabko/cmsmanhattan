<%@ page errorPage="error.jsp" %>
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:useBean id="policyBeanId" scope="request" class="com.cbsinc.cms.PolicyBean" />
<%@page import="java.util.PropertyResourceBundle,java.util.ResourceBundle,java.io.*"%>

<%
response.setCharacterEncoding("UTF-8");
response.setContentType("text/xml");
String url ;
String xsltUrl =  "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/"  +  AuthorizationPageBeanId.getLocale() + "/" + "policy.xsl" ; 
String xsltUrl_default = "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/" + "policy.xsl" ; 

String xsltpath =  "xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/"  +  AuthorizationPageBeanId.getLocale() + "/" + "policy.xsl" ; 
String xsltpath_default = "xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/" + "policy.xsl" ; 

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

   <title><%=  AuthorizationPageBeanId.getHost() %></title>
   <subject_site><%=  AuthorizationPageBeanId.getSubject_site() %></subject_site>
   <site_name><%=  AuthorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  AuthorizationPageBeanId.getSite_dir() %></host>
   <domain><%=  AuthorizationPageBeanId.getHost() %></domain>
   <admin>
   <post_manager><%=  policyBeanId.getTrueValue("PolicyManager.jsp","",AuthorizationPageBeanId.getIntLevelUp()==2) %></post_manager>
   <post_manager_img><%=  policyBeanId.getTrueValue("images/post.jpg","",AuthorizationPageBeanId.getIntLevelUp()==2) %></post_manager_img>
   <post_manager_text><%=  policyBeanId.getTrueValue("Post","",AuthorizationPageBeanId.getIntLevelUp()==2) %></post_manager_text>
   </admin>

   <role_id><%=  AuthorizationPageBeanId.getIntLevelUp() %></role_id>
   <user_site_id><%=  AuthorizationPageBeanId.getUser_site() %></user_site_id>
   <internet><%= policyBeanId.isInternet() %></internet>
   <login><%=  AuthorizationPageBeanId.getStrLogin() %></login>
   <shoping_url>Productlist.jsp</shoping_url>
   <message><%= AuthorizationPageBeanId.getStrMessage() %></message>
   <shoping_url>Productlist.jsp</shoping_url>
   <balans><%= "" + policyBeanId.getBalans() %></balans>
   <to_account_history>AccountHistory.jsp</to_account_history>
   <to_login>Authorization.jsp</to_login>
   <to_registration>RegPage.jsp</to_registration>
   <to_order>Order.jsp</to_order>
   <to_order_hist>OrderList.jsp</to_order_hist>
   <to_pay>PrePay.jsp</to_pay>
   <owner_user_id><%=  AuthorizationPageBeanId.getIntUserID() %></owner_user_id>
   <site_id><%=  AuthorizationPageBeanId.getSite_id() %></site_id>
   <show_blog><%=  policyBeanId.getStrShow_forum() %></show_blog>
   <show_rating1><%=  policyBeanId.getStrShow_ratimg1() %></show_rating1> 
   <show_rating2><%=  policyBeanId.getStrShow_ratimg2() %></show_rating2>
   <show_rating3><%=  policyBeanId.getStrShow_ratimg3() %></show_rating3>   

   

<product>
<page_url>http://<%= request.getServerName() %>:<%=request.getServerPort()%>/Policy.jsp?policy_byproductid=<jsp:getProperty name="policyBeanId" property="product_id" /></page_url>
<product_id><jsp:getProperty name="policyBeanId" property="product_id" /></product_id>
<name><jsp:getProperty name="policyBeanId" property="productName" /></name>
<file_exist><jsp:getProperty name="policyBeanId" property="file_exist" /></file_exist>
<icon><jsp:getProperty name="policyBeanId" property="imgURL" /></icon>
<image><jsp:getProperty name="policyBeanId" property="bigimgURL" /></image>
<image_type><%= policyBeanId.getBigimgURL().substring(policyBeanId.getBigimgURL().indexOf(".") + 1 ) %></image_type>
<product_url>http://<%= request.getServerName() %>:<%=request.getServerPort()%>/<jsp:getProperty name="policyBeanId" property="productURL" /></product_url>
<back_url><jsp:getProperty name="policyBeanId" property="back_url" /></back_url>
<description><jsp:getProperty name="policyBeanId" property="productDescription" /></description>
<amount><jsp:getProperty name="policyBeanId" property="productCost" /></amount>
<currency>
 <code><jsp:getProperty name="policyBeanId" property="currency_cd" /></code>
 <description><jsp:getProperty name="policyBeanId" property="currency_desc" /></description>
</currency>
<statistic><%= policyBeanId.getStatistic() %></statistic>  
<cdate><%= policyBeanId.getStrCDate() %></cdate>  
<creator_info_user_id><%=  policyBeanId.getCreator_info_user_id() %></creator_info_user_id>
</product>

<%=  policyBeanId.getRating1_xml() %>
<%=  policyBeanId.getSelect_currencies() %>
<%=  policyBeanId.getExtPolicyOneProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id(),policyBeanId.getProduct_id() ) %>
<%=  policyBeanId.getExtPolicyTwoProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id(),policyBeanId.getProduct_id() ) %>

<%=  policyBeanId.getExtPolicyFilesProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id(),policyBeanId.getProduct_id() ) %>
<%=  policyBeanId.getExtPolicyTabsProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id(),policyBeanId.getProduct_id() ) %>

<%=  policyBeanId.getBlogExtPolicyProductlist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id(),policyBeanId.getProduct_id() ) %>
<%=  policyBeanId.getNewslist("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id()) %>
<%=  policyBeanId.getBottomList("" + AuthorizationPageBeanId.getIntUserID(),AuthorizationPageBeanId.getSite_id()) %>

   <!-- ????????? ?????? ???????? ??? ??? ? ?   -->
   <empty_page_ext1><%=  policyBeanId.getPagecount_ext1() == 0 %></empty_page_ext1>
   <!-- ????????? ?????? ???????? ??? ???   -->
   <empty_page_ext2><%=  policyBeanId.getPagecount_ext2() == 0 %></empty_page_ext2>
   
   <%=  policyBeanId.getSelect_tree_catalog()	 %>
   <%=  policyBeanId.getSelectCatalogXMLUrlPath() %>
   <%=  policyBeanId.getSelect_menu_catalog()	 %>

</document>

