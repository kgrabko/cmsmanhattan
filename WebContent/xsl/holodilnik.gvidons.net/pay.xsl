<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
<xsl:output method="xml" indent="yes"/>
<xsl:output encoding="UTF-8"/>
<xsl:strip-space elements="*"/>
<xsl:template match="/">

<html>
<head>

     <title>GBS Portal</title>
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
<TD bgcolor="#ECEFF8"  ></TD>
<TD vAlign="top" Align="center" width="1010">


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
           <A href="Authorization.jsp?Login=newuser" class="plain" ><font size="2"> Регистрация покупателя </font></A>
           <a href="about.html" class="plain"><font size="2">  О компании </font></a>
        </div>

        <div class="personalBar">
            <a href="webmail/index.jsp">
            <img src="xsl/holodilnik.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
	     Login is <xsl:value-of select="document/login"/>@online-spb.com
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
            <div class="odd">
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
    
            <div class="even"> 
                <a href="">
                   <img src="xsl/holodilnik.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
                   Выслать пароль по почте
                </a>
            </div>
        </div>
    </div>


<div>
    <div class="portlet">
    <h5><strong>Помощь</strong></h5>
      <div class="body">
        <div class="portletContent odd">
<strong>1.</strong>При нажатии на кнопку "В банк", Вы попадаете в платежный шлюз банка. 
        </div>
        <div class="portletContent even">
<strong>2.</strong> После проведения платежа в банковском шлюзе, 
Вы должны нажать кнопку в банке "Вернутся в интернет магазин" и 
ожидать ответа из банка в расширенном мониторинге ответов банка (форма Счет) 
в нашем магазине. При положительном ответе из банка Ваш заказ получит статус 
оплачен.

        </div>
      </div>
    </div>
</div>






            </td>

            <td class="main">

            <!-- News part -->

	    <h1>Посылка данных в банк</h1>
<br/>


<!--  <FORM ACTION="https://secure.assist.ru/shops/cardpayment.cfm" METHOD="POST">  -->
		<FORM ACTION="https://pgate.grabko.com/authorizationPay.do" METHOD="POST">  
		<div class="box">
		  <div class="body">
		    <div >
			<table border="0" >
			    <tbody>
			<!-- <xsl:value-of select="document/currency_paysys_id"/> -->
			<TR><TD align="left"  colspan="5"  width="210" >Платеж на сумму  - <xsl:value-of select="document/amount"/></TD><TD></TD><TD width="300" align="LEFT">
			<INPUT TYPE="HIDDEN" NAME="Shop_IDP" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/shop_paysys_id"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Order_IDP" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/account_history_id"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" SIZE="10" NAME="Subtotal_P" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/amount"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Delay" VALUE="0"></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Language" VALUE="0"></INPUT>
			<INPUT TYPE="HIDDEN" NAME="URL_RETURN_OK" VALUE="http://www.gvidons.net/Order.jsp"></INPUT>
			<INPUT TYPE="HIDDEN" NAME="URL_RETURN_NO" VALUE="http://www.gvidons.net/AccountHistory.jsp"></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Currency" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/currency_paysys_id"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Comment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/decsription"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="LastName" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/lastname"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="FirstName" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/firstname"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="MiddleName" VALUE=""></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Email" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/email"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Address" VALUE=""></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Phone" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/mphone"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Country" VALUE=""></INPUT>
			<INPUT TYPE="HIDDEN" NAME="State" VALUE=""></INPUT>
			<INPUT TYPE="HIDDEN" NAME="City" VALUE=""></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Zip" VALUE=""></INPUT>
			<INPUT TYPE="HIDDEN" NAME="ChoosenCardType" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/type_creditcard"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="IsFrame" VALUE="1"></INPUT>
			<INPUT TYPE="HIDDEN" NAME="f_Email" VALUE="1"></INPUT>
			<INPUT TYPE="HIDDEN" NAME="CardPayment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/card_payment"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="WalletPayment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/wallet_payment"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="WebMoneyPayment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/webmoney_payment"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="RapidaPayment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/papida_payment"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="PayCashPayment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/paycash_payment"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="EPortPayment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/eport_payment"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="KreditPilotPayment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/kredit_pilotpayment"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="ListParameters" VALUE=""></INPUT>
     		      <!--	<INPUT TYPE="HIDDEN" NAME="DemoResult" VALUE="AS000" ID="Hidden1"></INPUT> -->
			
			</TD><TD  align="rigth" ><input type="submit" name="Submit" value="В Банк"></input></TD></TR>


			    </tbody>
			</table>
		    </div>
		  </div>
		</div>
	</FORM>


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
	    <div class="even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/shoping_url"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/holodilnik.gvidons.net/img/user.gif" width="20"></IMG>Вернуться в каталог товаров для продолжения покупок</A>
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
	    <div class="even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_account_history"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/holodilnik.gvidons.net/img/user.gif" width="20"></IMG>Ваши денежные операции .<br/>Наличие ваших денежных средств в магазине <br/> <xsl:value-of select="document/balans"/> рублей .</A>
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
	    <div class="even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_order_hist"/></xsl:attribute><IMG border="0" name="Nindex_14_03" src="xsl/holodilnik.gvidons.net/img/user.gif" height="20"   width="20"></IMG>Заказы</A>
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
	    <div class="even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_order"/></xsl:attribute><IMG border="0"  name="Nindex_14_03" src="xsl/holodilnik.gvidons.net/img/user.gif" height="20"  width="20"></IMG>Заказ</A>
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
	    <div class="even" align="left" ><br/><IMG border="0" height="20" name="Nindex_14_03" src="xsl/holodilnik.gvidons.net/img/user.gif" width="20"></IMG> Вы можете перевести денежные средства в магазин для оплаты заказа, используя кредитные карты или виртуальные кошельки, такие как Web money , Яндекс деньги , E-Port и другие  <br/><br/>
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_pay"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/holodilnik.gvidons.net/img/credit-cards.jpg" width="130"></IMG></A>
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

Internet shop. Copyright 2005 <A HREF="http://www.grabko.com">Grabko Business Solution</A> . Все права защищены.

<hr size="" class="netscape4" />

<strong class="netscape4">
for user netscape 
</strong>
 
</div>
</TD>
<TD  bgcolor="#ECEFF8" ></TD>
</TR>
</TABLE>


</body>
</html>
</xsl:template>
</xsl:stylesheet>