<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java" >
<xsl:output method="html" indent="yes"/>
<xsl:output encoding="UTF-8" />
<xsl:strip-space elements="*"/>

<xsl:template match="/">

<html>

<head>
     <title> GBS Portal </title>
     <style type="text/css" media="screen"> @import url(style2.css);</style>
    
   <SCRIPT language="JavaScript">
   <![CDATA[
   <!--

   function search( char ) 
    { 
	 document.forms[0].method = 'post' ;
     document.forms[0].search_char.value = char ;
     document.forms[0].search_value.value = '' ;
     document.forms[0].searchquery.value = '2' ;
     document.forms[0].submit();
    }

    function search_word() 
    { 
     document.forms[0].method = 'post' ;
     document.forms[0].search_char.value = '' ;
     document.forms[0].searchquery.value = '1' ;
     document.forms[0].submit();
    }


    function search_cre() 
    { 
    document.forms[0].method = 'post' ;
    document.forms[0].search_char.value = '' ;
    document.forms[0].search_value.value = '' ;
    document.forms[0].searchquery.value = '3' ;
    }


    function search_cre_bronirovanie() 
    { 
    document.forms[0].method = 'post' ;
    document.forms[0].search_char.value = '' ;
    document.forms[0].search_value.value = '' ;
    document.forms[0].searchquery.value = '4' ;
    }


   // -->
  ]]>
  </SCRIPT>


</head>

<body>

<TABLE cellSpacing="0" cellPadding="0" width="100%"  border="1" rightmargin="0" leftmargin="0" topmargin="0" bordercolor="#ECEFF8" >
<TR>
<TD bgcolor="#ECEFF8" ></TD>
<TD vAlign="top" Align="center" width="1010" >


<a class="skipnav" href="#documentContent">Skip to content</a>

<div>

     

        <div class="top">


<TABLE cellSpacing="0" cellPadding="10"  height="120"  width="100%" background="index.files/bg.jpg" border="0" rightmargin="0" leftmargin="0" topmargin="0">
  <TBODY>
  <TR>
    <TD vAlign="top" Align="left"  width="20%">
    </TD>
    <TD vAlign="center" Align="right"  width="80%">

              <form name="searchform"  action="Productlist.jsp" >
		<br />
                    <input id="search_value"  name="search_value" type="text"  size="20" alt="Поиск по имени товара"   title="Поиск по имени товара" tabindex="30001" ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input>
		    <input id="search_char"  name="search_char" type="hidden" ></input>
		    <input id="searchquery"  name="searchquery" type="hidden" ></input>
                    <input class="context searchButton"  type="button" size="20" value="Поиск"  tabindex="30002" onClick="return top.search_word();return true"   />
               </form>
</TD>
  </TR>
  </TBODY>
</TABLE>

        </div>

        <hr size="" class="netscape4" />

        <div class="tabs">
           <a href="Productlist.jsp?catalog_id=-1" class="plain" ><font size="2"> Новости </font> </a>
           <a href="Productlist.jsp?catalog_id=-2" class="plain" ><font size="2"> Главная страница </font> </a>
           <A href="Authorization.jsp?Login=newuser" class="plain" > <font size="2">Регистрация клиента </font></A>
           <a href="about.html" class="plain"><font size="2">  О компании </font></a>
        </div>

        <div class="personalBar">


            <a href="Authorization.jsp">
                <img src="xsl/plita.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
		Login is <xsl:value-of select="document/login"/>@<xsl:value-of select="document/host"/>
            </a>

            
        </div>

        <div class="pathBar">
            <span>
                <span> . </span>
            </span>

        </div>

        <hr size="" class="netscape4" />

    </div>



<table class="columns">

    <tbody>
        <tr>
            <td class="left">

	  <!-- head for porlet login   --> 
          <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
              <TBODY>
              <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Покупателям</B></FONT>
               </TD>
              </TR>
  	     </TBODY>
  	   </TABLE>

	  <!-- porlet login   --> 

    <div class="box">
        <div class="body">
        
            <div class="content odd">
                <form action="Authorization.jsp"
                      method="post">
    
                    
    
                    <strong>Пользователь</strong> <br />



                     <INPUT  title="Пользователь" tabindex="10001" SIZE="12" AUTOCOMPLETE="off" TYPE="TEXT" NAME="Login" >
			<xsl:attribute name="value">
				<xsl:value-of select="document/login"/>
			</xsl:attribute>		
        	     </INPUT>
    
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
                   <img src="xsl/plita.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
                   Выслать пароль по почте
                </a>
            </div>
        </div>
    </div>


<div>
    <div class="portlet">
    <h5><strong>Новости</strong></h5>
      <div class="body">
        <div class="portletContent odd">
....
        </div>
        <div class="portletContent even">
...
        </div>
      </div>
    </div>
</div>






            </td>

            <td class="main">

            <!-- News part -->

	    <h1>Детальное описание вашей денежной операции</h1>
<br/>



		<div class="box">
		  <div class="body">
		    <div >
			<table >
			    <tbody>
			       <TR><TD></TD><TD></TD></TR>
	                       <TR><TD>Остаток на счете до операции        </TD><TD  align="right" ><xsl:value-of select="document/payment/old_amount"   /></TD><TD  align="left" ><xsl:value-of select="document/payment/currency_old_lable"   /></TD></TR>
	                       <TR><TD>Сумма операции                      </TD><TD  align="right" ><xsl:value-of select="document/payment/add_amount"   /></TD><TD  align="left" ><xsl:value-of select="document/payment/currency_add_lable"   /></TD></TR>
	                       <TR><TD>Остаток на счете после операции     </TD><TD  align="right" ><xsl:value-of select="document/payment/total_amount" /></TD><TD  align="left" ><xsl:value-of select="document/payment/currency_total_lable" /></TD></TR>
			    </tbody>
			</table>
		    </div>
		  </div>
		</div>



		<div class="box">
		  <div class="body">
		    <div >
			<table >
			    <tbody>
                               <TR><TD></TD><TD></TD></TR>
	                       <TR><TD width="40%" >Дата начала операции                </TD><TD width="60%" ><xsl:value-of select="document/payment/date_input"   /></TD></TR>
	                       <TR><TD>Дата окончания операции             </TD><TD ><xsl:value-of select="document/payment/date_end"     /></TD></TR>
	                       <TR><TD>Описание операции                   </TD><TD ><xsl:value-of select="document/payment/decsription"  /></TD></TR>
	                       <TR><TD width="100%"  colspan="2" ><h4>IP Адрес клиента <xsl:value-of select="document/payment/user_ip"  /></h4></TD></TR>
	                       <TR><TD>Полная информация об этом IP </TD><TD > <A href="http://www.ripn.net:8082/nic/whois/" >служба Whois </A></TD></TR>
	                       <TR><TD>Данные от системе клиента </TD><TD ><xsl:value-of select="document/payment/user_header"  /></TD></TR>
	                       <TR><TD><h4>Статуc выполнения операции </h4></TD><TD width="60%" >
				
				<h4> 				
				<xsl:if test="document/payment/rezult_cd = '3'"> <STRONG><FONT color="#000099">Платеж не прошол</FONT></STRONG></xsl:if>
				<xsl:if test="document/payment/rezult_cd = '2'"> <STRONG><FONT color="#000099">Платеж прошол</FONT></STRONG></xsl:if>
				<xsl:if test="document/payment/rezult_cd = '1'"> <STRONG><FONT color="#000099">Ожидаем ответ из банка</FONT></STRONG></xsl:if>

				<xsl:if test="document/payment/rezult_cd = ''"> 
				<xsl:if test="document/payment/complete = 't'"> <STRONG><FONT color="#000099">Выполненная </FONT></STRONG></xsl:if><xsl:if test="document/payment/complete = 'f'"> <STRONG><FONT color="#000099">Ожидаем результат </FONT></STRONG></xsl:if>
				</xsl:if>
				</h4></TD></TR>
	                       <TR><TD><IMG height="80" alt=""  width="80" border="0"  src="xsl/plita.gvidons.net/img/index_00.gif"></IMG></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR> 
			    </tbody>
			</table>

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

        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Купить</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/shoping_url"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/plita.gvidons.net/img/user.gif" width="20"></IMG>Вернуться в каталог товаров для продолжения покупок</A>
	    </div>
	  </div>
	</div>


        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Ваш баланс</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_account_history"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/plita.gvidons.net/img/user.gif" width="20"></IMG>Ваши денежные операции .<br/>Наличие ваших денежных средств в магазине <br/> <xsl:value-of select="document/balans"/> рублей .</A>
	    </div>
	  </div>
	</div>


        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Все Ваши заказы</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_order_hist"/></xsl:attribute><IMG border="0" name="Nindex_14_03" src="xsl/plita.gvidons.net/img/user.gif" height="20"   width="20"></IMG>Заказы</A>
	    </div>
	  </div>
	</div>


        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Текущий заказ</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_order"/></xsl:attribute><IMG border="0"  name="Nindex_14_03" src="xsl/plita.gvidons.net/img/user.gif" height="20"  width="20"></IMG>Заказ</A>
	    </div>
	  </div>
	</div>


        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Оплатить заказ</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" ><br/><IMG border="0" height="20" name="Nindex_14_03" src="xsl/plita.gvidons.net/img/user.gif" width="20"></IMG> Вы можете перевести денежные средства в магазин для оплаты заказа, используя кредитные карты или виртуальные кошельки, такие как Web money , Яндекс деньги , E-Port и другие  <br/><br/>
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_pay"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/plita.gvidons.net/img/credit-cards.jpg" width="130"></IMG></A>
	    </div>
	  </div>
	</div>

            </td>
        </tr>
    </tbody>
</table>


<hr size="" class="netscape4" />

<div class="footer">


<br />

Grabko Business Solution - Internet shop 

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
</xsl:template>
</xsl:stylesheet>