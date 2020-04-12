<%@ page errorPage="error.jsp" %>
<jsp:useBean id="orderBeanId" scope="request" class="com.cbsinc.cms.OrderBean" />
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:useBean id="messageMail" scope="session" class="java.util.HashMap" type="java.util.HashMap"/>
<%@page import="java.util.PropertyResourceBundle,java.util.ResourceBundle,java.io.*"%>

<%
response.setCharacterEncoding("UTF-8");
response.setContentType("text/xml");
String url ;
String xsltUrl =  "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/"  +  AuthorizationPageBeanId.getLocale() + "/" + "order.xsl" ; 
String xsltUrl_default = "http://" + request.getServerName() +  ":"+request.getServerPort() + request.getContextPath() + "/xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/" + "order.xsl" ; 

String xsltpath =  "xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/"  +  AuthorizationPageBeanId.getLocale() + "/" + "order.xsl" ; 
String xsltpath_default = "xsl/" +  AuthorizationPageBeanId.getSite_dir() + "/" + "order.xsl" ; 

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
   <role_id><%=  AuthorizationPageBeanId.getIntLevelUp() %></role_id>
   <title><%=  AuthorizationPageBeanId.getHost() %></title>
   <admin>
   <post_manager><%=  orderBeanId.getTrueValue("PostManager.jsp","",AuthorizationPageBeanId.getIntLevelUp()==2) %></post_manager>
   </admin>
   <subject_site><%=  AuthorizationPageBeanId.getNick_site() %></subject_site>
   <site_name><%=  AuthorizationPageBeanId.getNick_site() %></site_name>
   <host><%=  AuthorizationPageBeanId.getSite_dir() %></host>
   <domain><%=  AuthorizationPageBeanId.getHost() %></domain>
   <login><%= AuthorizationPageBeanId.getStrLogin() %></login>
   <passwdord></passwdord>
   <message><%= AuthorizationPageBeanId.getStrMessage() %></message>
   <shoping_url>Productlist.jsp</shoping_url>
   <balans><%=  orderBeanId.getStrBalans() %></balans>
   <to_navigator>wCatalog.jsp</to_navigator>
   <to_navigator_location>NavigatorLocation.jsp</to_navigator_location>
   <to_account_history>AccountHistory.jsp</to_account_history>
   <to_login>Authorization.jsp</to_login>
   <to_registration>Authorization.jsp?Login=</to_registration>
   <to_order>Order.jsp</to_order>
   <to_order_hist>OrderList.jsp</to_order_hist>
   <to_pay>PrePay.jsp</to_pay>

<shipment_phone><jsp:getProperty name="orderBeanId" property="shipment_phone" /></shipment_phone>
<contact_person><%= orderBeanId.getContact_person() %></contact_person>
<shipment_email><jsp:getProperty name="orderBeanId" property="shipment_email" /></shipment_email>



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

   <%=orderBeanId.getProductList()%>


<!-- ????????? ?????? ???????? ??? ???   -->
   <empty_page><%=  orderBeanId.getPagecount() == 0 %></empty_page>
<!-- ????????? ?????? ??????? ??? ???   -->
   <empty_basket><%=  orderBeanId.isEmpty_basket() %></empty_basket>
<!-- ?????????? ??????? ???????   -->
   <quantity_product><%=  orderBeanId.getQuantity_product() %></quantity_product>
<!-- ??? ?????? ????????   -->
    <offset><%=  orderBeanId.getOffset() %></offset>


<next><jsp:getProperty name="orderBeanId" property="listup" /></next>
<prev><jsp:getProperty name="orderBeanId" property="listdown" /></prev>

<action><jsp:getProperty name="orderBeanId" property="action" /></action>
<imgname><jsp:getProperty name="orderBeanId" property="imgname" /></imgname>
<image_id><jsp:getProperty name="orderBeanId" property="image_id" /></image_id>
<img_url><jsp:getProperty name="orderBeanId" property="img_url" /></img_url>
<city_id><jsp:getProperty name="orderBeanId" property="city_id" /></city_id>
<country_id><jsp:getProperty name="orderBeanId" property="country_id" /></country_id>
<order_end_amount><jsp:getProperty name="orderBeanId" property="end_amount" /></order_end_amount>
<order_amount><jsp:getProperty name="orderBeanId" property="order_amount" /></order_amount>
<order_tax><jsp:getProperty name="orderBeanId" property="order_tax" /></order_tax>
<order_id><jsp:getProperty name="orderBeanId" property="order_id" /></order_id>
<order_currency_id><jsp:getProperty name="orderBeanId" property="order_currency_id" /></order_currency_id>
<order_paystatus><jsp:getProperty name="orderBeanId" property="order_paystatus" /></order_paystatus>
<order_status><jsp:getProperty name="orderBeanId" property="deliverystatus_id" /></order_status>
<order_status_lable><jsp:getProperty name="orderBeanId" property="order_status" /></order_status_lable>
<delivery_amoun><jsp:getProperty name="orderBeanId" property="delivery_amoun" /></delivery_amoun>
<delivery_timeend><jsp:getProperty name="orderBeanId" property="delivery_timeend" /></delivery_timeend>
<delivery_long><jsp:getProperty name="orderBeanId" property="order_delivery_long" /></delivery_long>
<delivery_start><jsp:getProperty name="orderBeanId" property="delivery_start" /></delivery_start>
<cards_name><jsp:getProperty name="orderBeanId" property="cards_name" /></cards_name>
<city_fullname><jsp:getProperty name="orderBeanId" property="city_fullname" /></city_fullname>
<country_fullname><jsp:getProperty name="orderBeanId" property="country_fullname" /></country_fullname>
<currency_lable><jsp:getProperty name="orderBeanId" property="currency_lable" /></currency_lable>
<img_url><jsp:getProperty name="orderBeanId" property="img_url" /></img_url>

<shipment_address><jsp:getProperty name="orderBeanId" property="shipment_address" /></shipment_address>
<shipment_fax><jsp:getProperty name="orderBeanId" property="shipment_fax" /></shipment_fax>
<shipment_description><jsp:getProperty name="orderBeanId" property="shipment_description" /></shipment_description>
<city_name><jsp:getProperty name="orderBeanId" property="city_name" /></city_name>
<country_name><jsp:getProperty name="orderBeanId" property="country_name" /></country_name>

<country_telcode><jsp:getProperty name="orderBeanId" property="country_telcode" /></country_telcode>
<currency_rate><jsp:getProperty name="orderBeanId" property="currency_rate" /></currency_rate>
<city_telcode><jsp:getProperty name="orderBeanId" property="order_city_telcode" /></city_telcode>
<cdate><jsp:getProperty name="orderBeanId" property="cdate" /></cdate>
<paystatus_lable><jsp:getProperty name="orderBeanId" property="paystatus_lable" /></paystatus_lable>

<internet><%= orderBeanId.isInternet %></internet>


<%=  orderBeanId.getSelect_country() %>
<%=  orderBeanId.getSelect_city() %>
<%=  orderBeanId.getSelect_paystatus() %>
<%=  orderBeanId.getSelect_deliverystatus() %>
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

<%=  orderBeanId.getSelect_menu_catalog()	 %>

</document>