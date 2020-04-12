<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<html>
<head>
     <title>GBS Portal</title>
     <style type="text/css" media="screen"> @import url(style2.css);</style>
</head>
<jsp:useBean id="calendar_editBean" scope="session" class="com.cbsinc.cms.Calendar_editBean" />
<jsp:useBean id="calendar_listBean" scope="session" class="com.cbsinc.cms.Calendar_listBean" />
<%
request.setCharacterEncoding("UTF-8");
if( request.getParameter("row") != null)
{
int index  =  calendar_listBean.stringToInt(request.getParameter("row")) ;
calendar_editBean.setIndx_select(index);
}
if( request.getParameter("holddate") != null)
{
calendar_editBean.setHolddate(  request.getParameter("holddate"));
}

if( request.getParameter("day_id") != null)calendar_editBean.setDay_id( request.getParameter("day_id"));
if( request.getParameter("mount_id") != null)calendar_editBean.setMount_id( request.getParameter("mount_id"));
if( request.getParameter("year_id") != null)calendar_editBean.setYear_id( request.getParameter("year_id"));
if( request.getParameter("product_id") != null)calendar_editBean.setProduct_id( request.getParameter("product_id"));

if( request.getParameter("first_name") != null)calendar_editBean.setFirst_name( request.getParameter("first_name"));
if( request.getParameter("last_name") != null)calendar_editBean.setLast_name( request.getParameter("last_name"));
if( request.getParameter("father_name") != null)calendar_editBean.setFather_name( request.getParameter("father_name"));
if( request.getParameter("document_number") != null)calendar_editBean.setDocument_number( request.getParameter("document_number"));
if( request.getParameter("document_type") != null)calendar_editBean.setDocument_type( request.getParameter("document_type"));
if( request.getParameter("age") != null)calendar_editBean.setAge( request.getParameter("age"));
if( request.getParameter("note") != null)calendar_editBean.setNote( request.getParameter("note"));

if( request.getParameter("calendar_id") != null)
{
calendar_editBean.setCalendar_id(  request.getParameter("calendar_id"));
}



if(request.getMethod().toUpperCase().compareTo("POST") == 0)
{
calendar_editBean.editCalendar();
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

	    <h1>Изменение раздела</h1>
<br/>


		<div class="box">
		  <div class="body">
		    <div >
					<% calendar_editBean.init_calendar(calendar_listBean.rows[calendar_editBean.getIndx_select()][1]) ; %>
                     <form method="post"   name="calendar_edit"  ACTION="calendar_edit.jsp" >
					 <input type="hidden"  name="product_id" value="<%= calendar_listBean.getProduct_id()%>" >
                     <TABLE>
                     <TR><TD></TD><TD><input type="hidden" name="calendar_id"  value = "<%= calendar_listBean.rows[calendar_editBean.getIndx_select()][0] %>"  />
                     <TR><TD width="40" >Дата:* </TD><TD><TABLE> <%= calendar_editBean.getComboBoxDay("day_id", calendar_editBean.getDay_id()  ) %><%= calendar_editBean.getComboBoxMount("mount_id", calendar_editBean.getMount_id()  ) %><%= calendar_editBean.getComboBoxYear("year_id", calendar_editBean.getYear_id()  ) %> </TABLE><TD>
                     <TR><TD width="40" >Имя</TD> <TD><input type="text" name="first_name" value="<%= calendar_editBean.getFirst_name()%>"> </TD></TR>
                     <TR><TD width="40" >Фамилия</TD> <TD><input type="text" name="last_name" value="<%= calendar_editBean.getLast_name()%>"> </TD></TR>
                     <TR><TD width="40" >Отчество</TD> <TD><input type="text" name="father_name" value="<%= calendar_editBean.getFather_name()%>"> </TD></TR> 
                     <TR><TD width="40" >Номер документа</TD> <TD><input type="text" name="document_number" value="<%= calendar_editBean.getDocument_number()%>"> </TD></TR>
                     <TR><TD width="40" >Тип документа</TD> <TD><input type="text" name="document_type" value="<%= calendar_editBean.getDocument_type()%>"> </TD></TR>
                     <TR><TD width="40" >Возраст</TD> <TD><input type="text" name="age" value="<%= calendar_editBean.getAge()%>"> </TD></TR> 
                     <TR><TD width="40" >Дополнительная информация</TD> <TD><input type="text" name="note" value="<%= calendar_editBean.getNote()%>"> </TD></TR>
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