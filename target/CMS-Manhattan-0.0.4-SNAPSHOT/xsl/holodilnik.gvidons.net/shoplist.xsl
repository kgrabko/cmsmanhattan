<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
<xsl:output method="html" indent="yes"/>
<xsl:output encoding="UTF-8"/>
<xsl:strip-space elements="*"/>

<xsl:template match="/">

<html>
<head>
     <title>GBS Portal</title>
     <style type="text/css" media="screen"> @import url(style2.css);</style>
    
</head>

<body>

<a class="skipnav" href="#documentContent">Skip to content</a>

<div>

     

        <div class="top">

<TABLE cellSpacing="0" cellPadding="10"  height="120"  width="100%" background="index.files/bg.jpg" border="0" rightmargin="0" leftmargin="0" topmargin="0">
  <TBODY>
  <TR>
    <TD vAlign="top" Align="left"  width="20%">
<!--      <img src="xsl/holodilnik.gvidons.net/img/logotip.gif" border="0" height="70" width="90"   /> -->
    </TD>
    <TD vAlign="center" Align="right"  width="80%">
                <form name="searchform"  action="Productlist.jsp" >
		<br />
                    <input id="search_value"  name="search_value" type="text"  size="20" alt="Поиск по имени товара"   title="Поиск по имени товара" tabindex="30001" ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input>
                    <input class="context searchButton"  type="submit" size="20" value="Поиск"  tabindex="30002" />
                </form>








</TD>
  </TR>
  </TBODY>
</TABLE>

<!--

            <div class="searchBox">
                <form name="searchform"
                      action="">

                    <input id="searchGadget"
                           name="SearchableText" type="text"
                           size="20" alt="Search"
                           title="Search" tabindex="30001" />

                    <input class="context searchButton"
                           type="submit" size="20" value="Поиск"
                           tabindex="30002" />
                </form>
            </div>
<br />
<br />


	<div class="box">
	  <div class="body">
	    <div class="content even"> <img src="xsl/holodilnik.gvidons.net/img/logo5.jpg" border="0" ><font size="3"><b> Интернет магазин <xsl:value-of select="document/site_name"/></b></font></img></div>
	  </div>
	</div>


-->

<!--	
            <a href="http://www.jabber.ru/login_form">
                <img src="xsl/localhost/img/index_00.gif" alt="Link icon" title="Link icon" height="11" width="6" border="0" />
            </a>
-->

        </div>

        <hr size="" class="netscape4" />

        <div class="tabs">



           <a href="Productlist.jsp?catalog_id=-1" class="plain" ><font size="2"> Новости </font> </a>
           <a href="Productlist.jsp?catalog_id=-2" class="plain" ><font size="2"> Главанная страница </font> </a>
           <A href="Authorization.jsp?Login=newuser" class="plain" ><font size="2"> Регистрация покупателя </font> </A>
<!--
	   <A href="CreateShop.jsp" class="plain" ><font size="2"> Создать магазин </font> </A>
-->
	   <A href="ShopList.jsp" class="selected" ><font size="2"> Список магазинов  </font></A>

	   <A href="http://www.joker.com" class="plain" ><font size="2"> Получить www адрес  </font></A>

<!--	   <A href="" class="plain" ><font size="2"> Форум </font> </A> -->

           <a href="about.html" class="plain"><font size="2">  О компании  </font></a>


        </div>

        <div class="personalBar">

            <a href="Authorization.jsp">
                <img src="xsl/holodilnik.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
		Login is <xsl:value-of select="document/login"/>@<xsl:value-of select="document/host"/>
            </a>
            
        </div>

        <div class="pathBar">
            <span>
                <span> Купи себе магазин в интернет: факс: +7 (812) 463-4411 , тел: +7 (812) 463-5983 -  Разработка програмного обеспечения под заказ  </span>
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
        <div class="body" >
            <div class="odd" >
                <form action="Authorization.jsp"   method="post">
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
                   <img src="xsl/holodilnik.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="20" width="20" border="0" />
                   Выслать пароль по почте
                </a>
            </div>
        </div>
    </div>

<div>

         <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Помощь</B></FONT>
            </TD>
            </TR>
           </TBODY>
         </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="even" align="left" >
		<IMG border="0" height="20" name="Nindex_14_03" src="xsl/holodilnik.gvidons.net/img/user.gif" width="20"/>
		<strong>1.</strong>При нажатии на кнопку "В банк", Вы попадаете в платежный шлюз банка.               
                <br/><strong>2.</strong> После проведения платежа в банковском шлюзе, 
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

	    <h1>Подключенные интернет  магазины</h1>
<br/>

				<table width="148" border="0" cellspacing="0" cellpadding="0" bgcolor="#ffffff">
			     	    <xsl:for-each select="document/catalog/catalog-item">
				   	 <TR><TD onmouseover="&#13;&#10;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&#9;style.backgroundColor='#FFFFFF'; &#13;&#10;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&#9;style.cursor='hand'" 
onmouseout="&#13;&#10;&#9;&#9;&#9;&#9;&#9;&#9;&#9;&#9;style.backgroundColor='#EFEFEF'"
  hight="60" align="left" bgColor="#EFEFEF"  >
			                 <A><xsl:attribute name="HREF"><xsl:value-of select="url"/></xsl:attribute><IMG border="0" height="14" name="Nindex_14_03" src="xsl/holodilnik.gvidons.net/dir.gif" width="15"></IMG><STRONG><FONT size="2" color="#103d43">.<xsl:value-of select="item"/></FONT></STRONG></A> 
				            </TD></TR>   	          		
				    </xsl:for-each>
  			   	 <TR><TD bgColor="#EFEFEF"  >.
 			               </TD></TR>   	          		

				</table>


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

Grabko Business Solution - Internet shop 


<hr size="" class="netscape4" />

<strong class="netscape4">
for user netscape 
</strong>
 
</div>

</body>
</html>
</xsl:template>
</xsl:stylesheet>