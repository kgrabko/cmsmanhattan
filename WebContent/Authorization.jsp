<jsp:useBean id="authorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean"   />
<jsp:setProperty name="authorizationPageBeanId" property="*" />
<%@page import="java.util.PropertyResourceBundle,java.util.ResourceBundle,java.io.*"%>

<%
response.setCharacterEncoding("UTF-8");
response.setContentType("text/xml");
String url ;
String xsltUrl =  "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  authorizationPageBeanId.getSite_dir() + "/"  +  authorizationPageBeanId.getLocale() + "/" + "authorization.xsl" ; 
String xsltUrl_default = "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  authorizationPageBeanId.getSite_dir() + "/" + "authorization.xsl" ; 

String xsltpath =  "xsl/" +  authorizationPageBeanId.getSite_dir() + "/"  +  authorizationPageBeanId.getLocale() + "/" + "authorization.xsl" ; 
String xsltpath_default = "xsl/" +  authorizationPageBeanId.getSite_dir() + "/" + "authorization.xsl" ; 

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

   <title><%=  authorizationPageBeanId.getHost() %></title>
   <subject_site><%=  authorizationPageBeanId.getSubject_site() %></subject_site>
   <site_name><%=  authorizationPageBeanId.getNick_site() %></site_name>
    <host><%=  authorizationPageBeanId.getSite_dir() %></host>
    <domain><%=  authorizationPageBeanId.getHost() %></domain>
   <login><%=  authorizationPageBeanId.getStrLogin() %></login>
   <passwdord><%=  authorizationPageBeanId.getStrCPasswd() %></passwdord>
   <firstname><%= authorizationPageBeanId.getStrFirstName() %></firstname>
   <lastname><%= authorizationPageBeanId.getStrLastName() %></lastname>
   <company><%= authorizationPageBeanId.getStrCompany() %></company>
   <email><%= authorizationPageBeanId.getStrEMail() %></email>
   <phone><%= authorizationPageBeanId.getStrPhone() %></phone>
   <mphone><%= authorizationPageBeanId.getStrMPhone() %></mphone>
   <fax><%= authorizationPageBeanId.getStrFax() %></fax>
   <icq><%= authorizationPageBeanId.getStrIcq() %></icq>
   <website><%= authorizationPageBeanId.getStrWebsite() %></website>
   <question><%= authorizationPageBeanId.getStrQuestion() %></question>
   <answer><%= authorizationPageBeanId.getStrAnswer() %></answer>
   <country><%= authorizationPageBeanId.getStrCountry() %></country>
   <city><%= authorizationPageBeanId.getStrCity() %></city>
   <site><%= authorizationPageBeanId.getSite_id() %></site>
   <message><%= authorizationPageBeanId.getStrMessage() %></message>
   <country_id><%= authorizationPageBeanId.getCountry_id() %></country_id>
   <city_id><%= authorizationPageBeanId.getCity_id() %></city_id>
   <currency_id><%= authorizationPageBeanId.getCurrency_id() %></currency_id>

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

<%=  authorizationPageBeanId.getSelect_site() %>
<%=  authorizationPageBeanId.getSelect_country() %>
<%=  authorizationPageBeanId.getSelect_city() %>
<%=  authorizationPageBeanId.getSelect_currency() %>
<!--  authorizationPageBeanId.getXMLDBList("Authorization.jsp?currency_id","currency", authorizationPageBeanId.getCurrency_id()  ,"SELECT currency_id , currency_desc FROM currency  WHERE active = true") -->
<%=  authorizationPageBeanId.getSelect_menu_catalog()	 %>
</document>