<%@ page errorPage="error.jsp" %>
<jsp:useBean id="merchantBeanId" scope="session" class="com.cbsinc.cms.MerchantBean" />
<jsp:useBean id="authorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:useBean id="messageMail" scope="session" class="java.util.HashMap" type="java.util.HashMap"/>
<%@page import="java.util.PropertyResourceBundle,java.util.ResourceBundle,java.io.*"%>

<%
  response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
  response.setHeader("Pragma","no-cache"); //HTTP 1.0
  response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
  request.setCharacterEncoding("UTF-8");
%>

<%
response.setCharacterEncoding("UTF-8");
response.setContentType("text/xml");
String url ;
String xsltUrl =  "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  authorizationPageBeanId.getSite_dir() + "/"  +  authorizationPageBeanId.getLocale() + "/" + "merchant.xsl" ; 
String xsltUrl_default = "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  authorizationPageBeanId.getSite_dir() + "/" + "merchant.xsl" ; 

String xsltpath =  "xsl/" +  authorizationPageBeanId.getSite_dir() + "/"  +  authorizationPageBeanId.getLocale() + "/" + "merchant.xsl" ; 
String xsltpath_default = "xsl/" +  authorizationPageBeanId.getSite_dir() + "/" + "merchant.xsl" ; 

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
   <name>Marchant application</name>

   <title><%=  authorizationPageBeanId.getHost() %></title>
   <subject_site><%=  authorizationPageBeanId.getNick_site() %></subject_site>
   <site_name><%=  authorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  authorizationPageBeanId.getSite_dir() %></host>
   <domain><%=  authorizationPageBeanId.getHost() %></domain>
   <login><%= authorizationPageBeanId.getStrLogin() %></login>
   <role_id><%=  authorizationPageBeanId.getIntLevelUp() %></role_id>
   <shoping_url>Productlist.jsp</shoping_url>
   <message><%= authorizationPageBeanId.getStrMessage() %></message>
   
   <c_name><%= merchantBeanId.getcName() %></c_name>
   <trademark><%= merchantBeanId.getTradeMark() %></trademark>
   <biz_description><%= merchantBeanId.getBizDescription() %></biz_description>
   <formation_date><%= merchantBeanId.getFormationDate() %></formation_date>
   <taxid><%= merchantBeanId.getTaxid() %></taxid>
   <biz_address><%= merchantBeanId.getBizAddress() %></biz_address>
   <biz_city><%= merchantBeanId.getBizCity() %></biz_city>
   <biz_country><%= merchantBeanId.getBizCountry() %></biz_country>
   <biz_zip><%= merchantBeanId.getBizZip() %></biz_zip>
   <biz_phone><%= merchantBeanId.getBizPhone() %></biz_phone>
   <biz_email><%= merchantBeanId.getBizEmail() %></biz_email>
   
   <f_o_name><%= merchantBeanId.getFoName() %></f_o_name>
   <f_o_address><%= merchantBeanId.getFoAddress() %></f_o_address>
   <f_o_city><%= merchantBeanId.getFoCity() %></f_o_city>
   <f_o_country><%= merchantBeanId.getFoCountry() %></f_o_country>
   <f_o_zip><%= merchantBeanId.getFoZip() %></f_o_zip>
   <f_o_ownership><%= merchantBeanId.getFoOwnership() %></f_o_ownership>
   <f_o_phone><%= merchantBeanId.getFoPhone() %></f_o_phone>
   <f_o_email><%= merchantBeanId.getFoEmail() %></f_o_email>
   
   <s_o_name><%= merchantBeanId.getSoName() %></s_o_name>
   <s_o_address><%= merchantBeanId.getSoAddress() %></s_o_address>
   <s_o_city><%= merchantBeanId.getSoCity() %></s_o_city>
   <s_o_country><%= merchantBeanId.getSoCountry() %></s_o_country>
   <s_o_zip><%= merchantBeanId.getSoZip() %></s_o_zip>
   <s_o_ownership><%= merchantBeanId.getSoOwnership() %></s_o_ownership>
   <s_o_phone><%= merchantBeanId.getSoPhone() %></s_o_phone>
   <s_o_email><%= merchantBeanId.getSoEmail() %></s_o_email>
   
   <bank_name><%= merchantBeanId.getBankName() %></bank_name>
   <bank_account_number><%= merchantBeanId.getBankAccountNumber() %></bank_account_number>
   <bank_routing_number><%= merchantBeanId.getBankRoutingNumber() %></bank_routing_number>
   <bank_address><%= merchantBeanId.getBankAddress() %></bank_address>
   <bank_city><%= merchantBeanId.getBankCity() %></bank_city>
   <bank_country><%= merchantBeanId.getBankCountry() %></bank_country>
   <bank_zip><%= merchantBeanId.getBankZip() %></bank_zip>
   <bank_phone><%= merchantBeanId.getBankPhone() %></bank_phone>
   

</document>