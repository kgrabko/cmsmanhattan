<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"
      lang="en">

<head>
     <title>GBS Portal</title>
     <style type="text/css" media="screen"> @import url(style2.css);</style>
</head>
<jsp:useBean id="catalog_editBean" scope="session" class="com.cbsinc.cms.Catalog_editBean" />
<jsp:useBean id="catalog_listBean" scope="session" class="com.cbsinc.cms.Catalog_listBean" />
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<%
request.setCharacterEncoding("UTF-8");
//catalog_editBean.setParent_id(catalog_listBean.getParent_id());
//catalog_editBean.setSite_id(AuthorizationPageBeanId.getSite_id());
if( request.getParameter("row") != null)
{
int index  =  catalog_listBean.stringToInt(request.getParameter("row")) ;
catalog_editBean.setIndx_select(index);
}
if( request.getParameter("name") != null)
{
catalog_editBean.setName(  request.getParameter("name"));
}

if( request.getParameter("catalog_id") != null)
{
	AuthorizationPageBeanId.setCatalog_id(  request.getParameter("catalog_id"));
}



if(request.getMethod().toUpperCase().compareTo("POST") == 0)
{
catalog_editBean.editCatalog(AuthorizationPageBeanId);
response.sendRedirect("catalog_list.jsp" );
}
%>
<body>
<TABLE cellSpacing="0" cellPadding="0" width="100%"  border="1" rightmargin="0" leftmargin="0" topmargin="0" bordercolor="#ECEFF8" >
<TR>
<TD bgcolor="#ECEFF8" ></TD>
<TD vAlign="top" Align="center" width="1010">
<a class="skipnav" href="#documentContent">Skip to content</a>

<div>



        <div class="top">

        </div>

        <hr size="" class="netscape4" />


        <div class="pathBar">
            <span>
                <span> <%=AuthorizationPageBeanId.getLocalization(application).getString("control_of_site")%> </span>
            </span>

        </div>

        <hr size="" class="netscape4" />

    </div>



<table class="columns">

    <tbody>
        <tr>
            <td class="left">
    <div class="box">

        <h5><%=AuthorizationPageBeanId.getLocalization(application).getString("enter_on_site")%></h5>

        <div class="body">

            <div class="content odd">
                <form action="Authorization.jsp"
                      method="post">



                    <strong><%=AuthorizationPageBeanId.getLocalization(application).getString("username")%></strong> <br />



                     <INPUT  title="<%= AuthorizationPageBeanId.getLocalization(application).getString("username") %>" tabindex="10001" SIZE="12" AUTOCOMPLETE="off" TYPE="TEXT" NAME="Login" >
        	     </INPUT>

                    <br />

                    <strong><%=AuthorizationPageBeanId.getLocalization(application).getString("password")%></strong>
		    <br />
		    <INPUT title="<%= AuthorizationPageBeanId.getLocalization(application).getString("password") %>" tabindex="10002"  SIZE="12" AUTOCOMPLETE="off" TYPE="PASSWORD" NAME="Passwd1" ></INPUT>
                    <br />
                    <br />

                    <input class="context searchButton"
                           type="submit" name="submit"
                           value="Log in" tabindex="10003" />
                </form>
            </div>

            <div class="content even">
                <a href="">
                   <img src="xsl/www.gvidons.com/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
                   <%=AuthorizationPageBeanId.getLocalization(application).getString("send_password_by_email")%>
                </a>
            </div>
        </div>
    </div>









            </td>

            <td class="main">

            <!-- News part -->

	    <h1>Изменение раздела</h1>
<br/>


		<div class="box">
		  <div class="body">
		    <div >

                     <form method="post"   name="catalog_edit"  ACTION="catalog_edit.jsp" >
                     <TABLE>
                     <TR><TD></TD><TD><input type="hidden" name="catalog_id"  value = "<%= catalog_listBean.rows[catalog_editBean.getIndx_select()][0] %>"  />
                     <TR><TD>Раздел:* </TD><TD> <input name="name" size="80" value = "<%= catalog_listBean.rows[catalog_editBean.getIndx_select()][1] %>"  onBlur="checkEmpty(this.value)" />
                     <TR><TD></TD> <TD><input type="submit" name="Submit" value="Сохранить"> <input type="reset" value="Сброс"></TD></TR>
                     </TABLE>
                     </form>

		     </div>
		  </div>
		</div>


        <!-- Navigation -->
        <div class="listingBar">
	    <span class="next">
                <a HREF = "#" onClick="javascript:history.back()"  >
		<strong>
		назад
		</strong>
	        </a>
	    </span>
	</div>


            </td>

            <td class="right">




            </td>
        </tr>
    </tbody>
</table>


<hr size="" class="netscape4" />

<div class="footer">


<br />

 <%=AuthorizationPageBeanId.getLocalization(application).getString("all_rights_reserved")%>

<hr size="" class="netscape4" />

<strong class="netscape4">
for user netscape
</strong>

</div>
</TD>
<TD bgcolor="#ECEFF8" ></TD>
</TR>
</TABLE>
</body>
</html>