<%@ page errorPage="error.jsp" %>
<jsp:useBean id="merchantBean" scope="session" class="com.cbsinc.cms.MerchantBean" />
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
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
String xsltUrl =  "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/"  +  AuthorizationPageBeanId.getLocale() + "/" + "merchant.xsl" ; 
String xsltUrl_default = "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/" + "merchant.xsl" ; 

String xsltpath =  "xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/"  +  AuthorizationPageBeanId.getLocale() + "/" + "merchant.xsl" ; 
String xsltpath_default = "xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/" + "merchant.xsl" ; 

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

   <title><%=  AuthorizationPageBeanId.getHost() %></title>
   <subject_site><%=  AuthorizationPageBeanId.getNick_site() %></subject_site>
   <site_name><%=  AuthorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  AuthorizationPageBeanId.getSite_dir() %></host>
   <domain><%=  AuthorizationPageBeanId.getHost() %></domain>
   <login><%= AuthorizationPageBeanId.getStrLogin() %></login>
   <role_id><%=  AuthorizationPageBeanId.getIntLevelUp() %></role_id>
   <shoping_url>Productlist.jsp</shoping_url>
   <message><%= AuthorizationPageBeanId.getStrMessage() %></message>
   
   <c_name><%= merchantBean.getcName() %></c_name>
   <trademark><%= merchantBean.getTradeMark() %></trademark>
   <biz_description><%= merchantBean.getBizDescription() %></biz_description>
   <formation_date><%= merchantBean.getFormationDate() %></formation_date>
   <taxid><%= merchantBean.getTaxid() %></taxid>
   <biz_address><%= merchantBean.getBizAddress() %></biz_address>
   <biz_city><%= merchantBean.getBizCity() %></biz_city>
   <biz_country><%= merchantBean.getBizCountry() %></biz_country>
   <biz_zip><%= merchantBean.getBizZip() %></biz_zip>
   <biz_phone><%= merchantBean.getBizPhone() %></biz_phone>
   <biz_email><%= merchantBean.getBizEmail() %></biz_email>
   
   <f_o_name><%= merchantBean.getFoName() %></f_o_name>
   <f_o_address><%= merchantBean.getFoAddress() %></f_o_address>
   <f_o_city><%= merchantBean.getFoCity() %></f_o_city>
   <f_o_country><%= merchantBean.getFoCountry() %></f_o_country>
   <f_o_zip><%= merchantBean.getFoZip() %></f_o_zip>
   <f_o_ownership><%= merchantBean.getFoOwnership() %></f_o_ownership>
   <f_o_phone><%= merchantBean.getFoPhone() %></f_o_phone>
   <f_o_email><%= merchantBean.getFoEmail() %></f_o_email>
   
   <s_o_name><%= merchantBean.getSoName() %></s_o_name>
   <s_o_address><%= merchantBean.getSoAddress() %></s_o_address>
   <s_o_city><%= merchantBean.getSoCity() %></s_o_city>
   <s_o_country><%= merchantBean.getSoCountry() %></s_o_country>
   <s_o_zip><%= merchantBean.getSoZip() %></s_o_zip>
   <s_o_ownership><%= merchantBean.getSoOwnership() %></s_o_ownership>
   <s_o_phone><%= merchantBean.getSoPhone() %></s_o_phone>
   <s_o_email><%= merchantBean.getSoEmail() %></s_o_email>
   
   <bank_name><%= merchantBean.getBankName() %></bank_name>
   <bank_account_number><%= merchantBean.getBankAccountNumber() %></bank_account_number>
   <bank_routing_number><%= merchantBean.getBankRoutingNumber() %></bank_routing_number>
   <bank_address><%= merchantBean.getBankAddress() %></bank_address>
   <bank_city><%= merchantBean.getBankCity() %></bank_city>
   <bank_country><%= merchantBean.getBankCountry() %></bank_country>
   <bank_zip><%= merchantBean.getBankZip() %></bank_zip>
   <bank_phone><%= merchantBean.getBankPhone() %></bank_phone>
   

</document>