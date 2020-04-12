<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="productlistFaced" scope="application" class="com.cbsinc.cms.faceds.ProductlistFaced" />
<jsp:useBean id="calendar_listBean" scope="session" class="com.cbsinc.cms.Calendar_listBean" />
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"
      lang="en">
<head>
    <title>GBS Portal</title>
     <style type="text/css" media="screen"> @import url(style2.css);</style>
</head>

<%
if( request.getParameter("mount_id") != null)calendar_listBean.setMount_id( request.getParameter("mount_id"));
if( request.getParameter("year_id") != null)calendar_listBean.setYear_id( request.getParameter("year_id"));
if( request.getParameter("product_id") != null)
{
  calendar_listBean.setProduct_id( request.getParameter("product_id"));
  calendar_listBean.setDescrition( productlistFaced.getProductName( request.getParameter("product_id")));
}
//ProductlistBeanId.getProduct()

calendar_listBean.setSite_id(AuthorizationPageBeanId.getSite_id());
if( request.getParameter("row") != null)
{
int index =  calendar_listBean.stringToInt(request.getParameter("row")) ;
calendar_listBean.setIndx_select(index);
}
if( request.getParameter("del") != null)
{
int index =  calendar_listBean.stringToInt(request.getParameter("del")) ;
int g =  calendar_listBean.rows.length ;
String calendar_id = calendar_listBean.rows[index][0] ;
if(calendar_id != null)calendar_listBean.delete(calendar_id) ;
request.setAttribute("del",null);
}
if( request.getParameter("offset") != null){
calendar_listBean.setOffset(  calendar_listBean.stringToInt(request.getParameter("offset")));
}



if(request.getMethod().toUpperCase().compareTo("POST") == 0)
{
 calendar_listBean.calendar_change();
 response.sendRedirect("calendar_list.jsp" );
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
                <span> Управление сайтом </span>
            </span>

        </div>

        <hr size="" class="netscape4" />

    </div>

<table class="columns">

    <tbody>
        <tr>
            <td class="left">
    <div class="box">

        <h5>Вход</h5>

        <div class="body">

            <div class="content odd">
                <form action="Authorization.jsp"
                      method="post">



                    <strong>Пользователь</strong> <br />



                     <INPUT  title="Пользователь" tabindex="10001" SIZE="12" AUTOCOMPLETE="off" TYPE="TEXT" NAME="Login" ></INPUT>

                    <br />

                    <strong>Пароль</strong>
		    <br />
		    <INPUT title="Пароль" tabindex="10002"  SIZE="12" AUTOCOMPLETE="off" TYPE="PASSWORD" NAME="Passwd1" ></INPUT>
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
                   Выслать пароль по почте
                </a>
            </div>
        </div>
    </div>


<div>
    <div class="portlet">
    <h5><strong>Помощ</strong></h5>
      <div class="body">
        <div class="portletContent odd">
         Это главное меню управления сайтом .
        </div>
        <div class="portletContent even">
	Здесь можно добавлять товары ,
	новости на саит  , и  рубрики
	для обьединения товаров в группы
        </div>
      </div>
    </div>
</div>






            </td>

            <td class="main">

            <!-- News part -->

	    <h1>Список брони для  <%= calendar_listBean.getDescrition() %> </h1>
        <br/>


		<div class="box">
		  <div class="body">
		    <div >
		    <form method="post"   name="calendar_list"  ACTION="calendar_list.jsp" >
                     <TABLE>
                     <TR><TD></TD><TD><input type="hidden" name="calendar_id"  value = "<%= calendar_listBean.rows[calendar_listBean.getIndx_select()][0] %>"  />
                     <TR><TD>На месяц:* </TD><TD><TABLE> <%= calendar_listBean.getComboBoxMount("mount_id", calendar_listBean.getMount_id()  ) %><%= calendar_listBean.getComboBoxYear("year_id", calendar_listBean.getYear_id()  ) %> <input type="submit" name="Submit" value="Выбрать"></TABLE><TD>
                     </TABLE>
             </form>
              <%= calendar_listBean.getTable( AuthorizationPageBeanId.getIntLevelUp()) %>
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

Product Media@Shop  - Internet shop. Copyright 2005 <A HREF="http://www.grabko.com">Grabko Business Solution</A> . Все права защищены.

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