<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean"   />
<jsp:setProperty name="AuthorizationPageBeanId" property="*" />
<%@page import="java.util.PropertyResourceBundle,java.util.ResourceBundle,java.io.*"%>

<%
response.setCharacterEncoding("UTF-8");
response.setContentType("text/xml");
String url ;
String xsltUrl =  "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/"  +  AuthorizationPageBeanId.getLocale() + "/" + "authorization.xsl" ; 
String xsltUrl_default = "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/" + "authorization.xsl" ; 

String xsltpath =  "xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/"  +  AuthorizationPageBeanId.getLocale() + "/" + "authorization.xsl" ; 
String xsltpath_default = "xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/" + "authorization.xsl" ; 

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
   <name>Authorization</name>

   <title><%=  AuthorizationPageBeanId.getHost() %></title>
   <subject_site><%=  AuthorizationPageBeanId.getSubject_site() %></subject_site>
   <site_name><%=  AuthorizationPageBeanId.getNick_site() %></site_name>
    <host><%=  AuthorizationPageBeanId.getSite_dir() %></host>
    <domain><%=  AuthorizationPageBeanId.getHost() %></domain>
   <login><%=  AuthorizationPageBeanId.getStrLogin() %></login>
   <passwdord><%=  AuthorizationPageBeanId.getStrCPasswd() %></passwdord>
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

   <!--  for members -->
   <do_form_1>
                <form-header>
                <name>login</name>
		<method>post</method>
		<action>Authorization.jsp</action>
                </form-header>

                <fields>
                <ref_1>/login</ref_1>
                <ref_2>/passwdord</ref_2>
                <ref_3>/lang_cd</ref_3>
                <ref_4>product/currency_cd</ref_4>
                </fields>
   </do_form_1>


   <do_form_2>
   <!-- become new member -->
                <form-header>
                <name>registration</name>
		<method>post</method>
		<action>RegPage.jsp</action>
                </form-header>
   </do_form_2>

<%=  AuthorizationPageBeanId.getSelect_site() %>
<%=  AuthorizationPageBeanId.getSelect_country() %>
<%=  AuthorizationPageBeanId.getSelect_city() %>
<%=  AuthorizationPageBeanId.getSelect_currency() %>
<!--  AuthorizationPageBeanId.getXMLDBList("Authorization.jsp?currency_id","currency", AuthorizationPageBeanId.getCurrency_id()  ,"SELECT currency_id , currency_desc FROM currency  WHERE active = true") -->
<%=  AuthorizationPageBeanId.getSelect_menu_catalog()	 %>
</document>