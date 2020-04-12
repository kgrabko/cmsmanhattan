<%@ page errorPage="error.jsp" %>
<jsp:useBean id="orderListBeanId" scope="session" class="com.cbsinc.cms.OrderListBean" />
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<%@page import="java.util.PropertyResourceBundle,java.util.ResourceBundle,java.io.*"%>

<%
response.setCharacterEncoding("UTF-8");
response.setContentType("text/xml");
String url ;
String xsltUrl =  "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/"  +  AuthorizationPageBeanId.getLocale() + "/" + "orderlist.xsl" ; 
String xsltUrl_default = "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/" + "orderlist.xsl" ; 

String xsltpath =  "xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/"  +  AuthorizationPageBeanId.getLocale() + "/" + "orderlist.xsl" ; 
String xsltpath_default = "xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/" + "orderlist.xsl" ; 

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
   <name>GBS ltd.</name>

   <title><%=  AuthorizationPageBeanId.getHost() %></title>
   <subject_site><%=  AuthorizationPageBeanId.getNick_site() %></subject_site>
   <site_name><%=  AuthorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  AuthorizationPageBeanId.getSite_dir() %></host>
   <domain><%=  AuthorizationPageBeanId.getHost() %></domain>
   <login><%= AuthorizationPageBeanId.getStrLogin() %></login>
   <role_id><%=  AuthorizationPageBeanId.getIntLevelUp() %></role_id>
   <passwdord></passwdord>
   <shoping_url>Productlist.jsp</shoping_url>
   <message><%= AuthorizationPageBeanId.getStrMessage() %></message>
   <shoping_url>Productlist.jsp</shoping_url>
   <balans><%=  orderListBeanId.getStrBalans(AuthorizationPageBeanId.getIntUserID()) %></balans>
   <to_navigator>wCatalog.jsp</to_navigator>
   <to_navigator_location>NavigatorLocation.jsp</to_navigator_location>
   <to_account_history>AccountHistory.jsp</to_account_history>
   <to_login>Authorization.jsp</to_login>
   <to_registration>RegPage.jsp</to_registration>
   <to_order>Order.jsp</to_order>
   <to_order_hist>OrderList.jsp?searchquery=0</to_order_hist>
   <to_pay>PrePay.jsp</to_pay>
   <datefrom_formated><%=orderListBeanId.getFormatedDateFrom(request.getLocale()) %></datefrom_formated>
   <dateto_formated><%=orderListBeanId.getFormatedDateFrom(request.getLocale()) %></dateto_formated>
   <datefrom><%=orderListBeanId.getDateFrom() %></datefrom>
   <dateto><%=orderListBeanId.getDateTo() %></dateto>
   <date_format><%=orderListBeanId.getDatePattern()%></date_format>
   <%=orderListBeanId.getSelectOrderlistXML()  %>
   <%=orderListBeanId.getSelect_paystatus() %>
   <%=orderListBeanId.getSelect_deliverystatus() %>
 
<next><jsp:getProperty name="orderListBeanId" property="listup" /></next>
<prev><jsp:getProperty name="orderListBeanId" property="listdown" /></prev>

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
   
 <%=  orderListBeanId.getSelect_menu_catalog()	 %>

</document>