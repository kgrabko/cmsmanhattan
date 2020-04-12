<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page errorPage="error.jsp" %>
<%
  response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
  response.setHeader("Pragma","no-cache"); //HTTP 1.0
  response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
  request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Set up a hostname for your internet shop</title>
<LINK id="style2" rel="stylesheet" type="text/css" href="xsl/shops.online-spb.com/style2.css">
</head>
<body>
<TABLE cellSpacing="0" cellPadding="0" width="100%"  border="0" rightmargin="0" leftmargin="0" topmargin="0" bordercolor="#ECEFF8" >
<TR>
<TD bgcolor="#ECEFF8" style="border: 1px solid #ECEFF8"></TD>
<TD vAlign="top" Align="center" width="1030" style="border: 0px solid #ECEFF8" >

<a class="skipnav" href="#documentContent">Skip to content</a>
<div>
<div class="top">
<TABLE cellSpacing="0" cellPadding="10" height="120" width="100%" class="bacgraundTop" border="0" rightmargin="0" leftmargin="0" topmargin="0">
<TBODY>
<TR>
<TD vAlign="top" Align="left" width="20%"></TD><TD vAlign="center" Align="right" width="80%">
<form name="searchform" action="Productlist.jsp">
<br>
<input id="search_value" name="search_value" type="text" size="20" alt="<%= AuthorizationPageBeanId.getLocalization(application).getString("find_good_by_name") %>" title="<%= AuthorizationPageBeanId.getLocalization(application).getString("find_good_by_name") %>" tabindex="30001" value=""><input class="searchButton" type="submit" size="20" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("Search") %>" tabindex="30002">
</form>
</TD>
</TR>
</TBODY>
</TABLE>
</div>
<hr size="" class="netscape4">
<div class="tabs">
<A href="Productlist.jsp?catalog_id=-2" class="plain"><font size="2"><%=AuthorizationPageBeanId.getLocalization(application).getString("main_page")%></font></A><A href="Domain.jsp" class="plain"><font size="2">Registration of domains</font></A><a href="webmail/" class="plain"><font size="2">mail</font></a><A href="Productlist.jsp?action=logoff" class="plain"><font size="2"><%=AuthorizationPageBeanId.getLocalization(application).getString("exit")%></font></A>
</div>
<div class="personalBar">
<a href="Authorization.jsp"><img src="images/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0">
					<%=AuthorizationPageBeanId.getLocalization(application).getString("login")%>: <%=AuthorizationPageBeanId.getStrLogin()%></a>
</div>
<hr size="" class="netscape4">
</div>
<table class="columns">
<tbody>
<tr>
<td class="left">
<TABLE style="height: 20px" cellSpacing="4" cellPadding="0" width="159" class="bacgraundBoxTitle">
<TBODY>
<TR>
<TD vAlign="center"><FONT color="white"><B>Authorization</B></FONT></TD>
</TR>
</TBODY>
</TABLE>
<div class="box">
<div class="body">
<div class="odd">
<form action="Authorization.jsp" method="post">
<strong><%=AuthorizationPageBeanId.getLocalization(application).getString("username")%></strong>
<br>
<INPUT title="<%= AuthorizationPageBeanId.getLocalization(application).getString("username") %>" tabindex="10001" SIZE="16" AUTOCOMPLETE="off" TYPE="TEXT" NAME="Login" value="newuser">
<br>
<strong><%=AuthorizationPageBeanId.getLocalization(application).getString("password")%></strong>
<br>
<INPUT title="<%= AuthorizationPageBeanId.getLocalization(application).getString("password") %>" tabindex="10002" SIZE="16" AUTOCOMPLETE="off" TYPE="PASSWORD" NAME="Passwd1">
<br>
<br>
<input type="submit" name="submit" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("login") %>" tabindex="10003" class="context searchButton">
</form>
</div>
<div class="even">
<a href=""><img src="xsl/shops.online-spb.com/images/linkTransparent.gif">
                   <%=AuthorizationPageBeanId.getLocalization(application).getString("send_password_by_email")%>
                </a>
</div>
</div>
</div>

</td><td class="main">
<br>
<div class="box">
<div class="body">
<div class="content">
<p>




<br>
<center>
<table    border="0" cellspacing="0" cellpadding="5" >
					<tr>
						<td colspan="2">&nbsp;<br/><font size="3"><b> Install new domain for your internet site</b></font><font size="2"></font></td>
                    </tr>
                    <tr>
						<td colspan="2">&nbsp;<br/><font color="red"><%=request.getAttribute("message") == null?"":request.getAttribute("message")%></font></td>
                    </tr>
                    <tr>
						<td width="400" align="center">
						<form action="SetDomain.jsp"  method="post" >
						<input type="text" size="50" name="domain" value="<%= AuthorizationPageBeanId.getHost() %>"  />  
						<input type="submit"  name="submit" value="Save" />  						
						</form>
						</td>
					</tr>
					
					<tr>
						<td height="500"  colspan="2">&nbsp;</td>
					</tr>

				</table>

</center>
</div>
</div>
</div>
<div class="listingBar">
		<span class="next"><a HREF="#" onClick="javascript:history.back()"><strong>	
		<%=AuthorizationPageBeanId.getLocalization(application).getString("back")%>
		</strong></a></span>
</div>
</td><td class="right">
<TABLE style="height: 20px" cellSpacing="4" cellPadding="0" width="159" class="bacgraundBoxTitle">
<TBODY>
<TR>
<TD vAlign="center"><FONT color="white"><B>Help</B></FONT></TD>
</TR>
</TBODY>
</TABLE>
<div class="box">
<div class="body">
<div class="even" align="left">
<TABLE >
<TBODY>
<TR><TD > You need make a record "A" in DNS server </TD> <TD ></TD>
</TR>
<TR>
<TD > and link this record with your IP 54.201.41.182 </TD> <TD ></TD>
</TR>
<TR>
<TD >this way your internet site will become accessible from internet, </TD> <TD> </TD>
</TR>
<TR>
<TD > of course if would be write this host name in internet browser.</TD> <TD ></TD>
</TR>
<TR>
<TD >If you wish we can help to do that but</TD> <TD ></TD>
</TR>
<TR>
<TD >you have to pay for additional support .</TD> <TD ></TD>
</TR>
</TBODY>
</TABLE>
</div>
</div>
</div>
</td>
</tr>
</tbody>
</table>
<hr size="" class="netscape4">
<div class="footer">
<br>
Internet shop . Copyright 2010 FDIS Center Business Solutions Inc . 
 <%=AuthorizationPageBeanId.getLocalization(application).getString("all_rights_reserved")%>
<hr size="" class="netscape4">
<strong class="netscape4">
for user netscape </strong>
</div>
</TD>
<TD bgcolor="#ECEFF8" style="border: 1px solid #ECEFF8"></TD>
</TR>
</TABLE>
</body>
</html>
