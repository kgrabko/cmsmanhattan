<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
<xsl:output method="xml" indent="yes"/>
<xsl:output encoding="ISO-8859-1"/>
<xsl:strip-space elements="*"/>


<xsl:template match="/">
<!--
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
-->

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"
      lang="en">

<head>
     <META http-equiv="Page-Enter" content="BlendTrans(Duration=0.5)" />
     <meta name="keywords" lang="de" content="Internet Shop, Shop , Mainframe , Business , Solution, Mainframe Solution , Mainframe Shop"/>
     <meta name="keywords" lang="en-us" content="Internet Shop, Shop , Mainframe , Business , Solution, Mainframe Solution , Mainframe Shop"/>
     <meta name="keywords" lang="en" content="Internet Shop, Shop , Mainframe , Business , Solution, Mainframe Solution , Mainframe Shop"/>
     <meta name="keywords" lang="fr" content="Internet Shop, Shop , Mainframe , Business , Solution, Mainframe Solution , Mainframe Shop"/>
     <meta name="description" content="++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"/>
     <meta name="description" content="Internet Shop - Commercial product for JBoss and other higth performens  platform."/>
     <meta name="author" content="Konstantin Grabko" />
     <meta name="generator" content="Internet Shop Mainframe Business Solution"/>
     <meta name="description" content="++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"/>
     <meta http-equiv="cache-control" content="no-cache"/>
     <meta http-equiv="pragma" content="no-cache"/>
     <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1"/>

     <title>Ishop  - Grabko Konstantin </title>
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
	<!--      <img src="xsl/www.gvidons.com/img/logotip.gif" border="0" height="70" width="90"   /> -->
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
	    <div class="content even"> <img src="xsl/www.gvidons.com/img/logo5.jpg" border="0" ><font size="3"><b> Интернет магазин <xsl:value-of select="document/site_name"/></b></font></img></div>
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
           <a href="Productlist.jsp?catalog_id=-2" class="plain" ><font size="2"> Главная страница </font> </a>
           <A href="Authorization.jsp?Login=newuser" class="plain" ><font size="2"> Регистрация покупателя </font></A>
<!--
	   <A href="CreateShop.jsp" class="plain" ><font size="2">  Создать магазин </font></A>

	   <A href="ShopList.jsp" class="plain" ><font size="2"> Список магазинов </font></A>
	   <A href="http://www.joker.com" class="plain" ><font size="2">  Получить www адрес</font></A>
-->

<!--	   <A href="" class="plain" ><font size="2"> Форум </font></A>  -->

           <a href="hosting.html" class="plain"> <font size="2"> Аренда интернет магазинов </font></a>
          <a href="pogoda.html" class="plain"> <font size="2">О погоде </font></a>
           <a href="about.html" class="plain"> <font size="2">О компании  </font></a>





        </div>

        <div class="personalBar">
            
            <a href="Authorization.jsp">
                <img src="xsl/www.gvidons.com/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
		Login is <xsl:value-of select="document/login"/>@<xsl:value-of select="document/host"/>
            </a>
            
        </div>

        <div class="pathBar">
            <span>
              <CENTER>  <span> <font size="2" color="red"  >  ВНИМАНИЕ  !!! ИНСТРУКЦИЮ ПО СКАЧИВАНИЮ ФИЛЬМОВ СМОТРИТЕ В НОВОСТЯХ . </font> </span></CENTER>
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
 	  	   <img src="xsl/www.gvidons.com/img/linkTransparent.gif" alt="Link icon" title="Link icon" height="11" width="6" border="0" />
                   Выслать пароль по электронной почте
                </a>
            </div>
        </div>
    </div>

       <TABLE class="text"  height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
              <TBODY>
              <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Назад</B></FONT>
               </TD>
              </TR>
  	     </TBODY>
         </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="center" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/shoping_url"/></xsl:attribute><IMG border="0" height="14" name="Nindex_14_03" src="xsl/www.gvidons.com/img/user.gif" width="15"></IMG>Продолжить покупки</A>
	    </div>
	  </div>
	</div>



<div>
    <div class="portlet">

           <TABLE class="text"  height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
              <TBODY>
              <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Новости</B></FONT>
               </TD>
              </TR>
  	     </TBODY>
  	   </TABLE>

           <TABLE height="9" cellSpacing="0" cellPadding="0" width="159"   background="index.files/bg34.gif">
              <TBODY>
              <TR><TD></TD></TR>
              </TBODY>
           </TABLE>                                                    

           <TABLE cellSpacing="0" cellPadding="0" width="159" align="center"  background="index.files/bgleft.gif" border="0" rightmargin="0"  leftmargin="0" topmargin="0">
           <TBODY>
            <!-- News Iten start -->
	    <xsl:for-each select="document/newslist/news">
                <TR>
                <TD width="5"></TD>
                <TD width="145">
                <A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><br/>
		      <!-- Обрабатываем перевод строки  -->
                      <U>
   		      <xsl:for-each select="description/r">
		      <xsl:value-of select="."/> <br/>
		      </xsl:for-each>
                      </U>
		</A>
		</TD>
                <TD width="5">
                </TD>
                </TR>
              <TR><TD width="155" background="index.files/bgline.gif" colSpan="3"  height="10" ></TD></TR>
  	    </xsl:for-each>
            <!-- News Iten end -->
              <TR>
                <TD align="right" width="155"  colSpan="3" ><A class="menu"  title="Все новости" 
                  href="Productlist.jsp?catalog_id=-1"><FONT  class="text" ><U>Архив новостей</U></FONT></A></TD>
              </TR>
              
              <TR>
                <TD width="155" background="index.files/bgline2.gif" colSpan="3" height="11" ></TD>
              </TR>
             </TBODY>
             </TABLE>





    </div>
</div>






            </td>

            <td class="main">

            <!-- News part -->

	    <h1>Подробности</h1>

    
	    <div class="description">это подробное описание</div>
            <!-- News -->

            <!-- News Iten start -->
            <div class="box">

<!--
                <h6>
                    <img src="newsitem_icon.gif"
                         alt="News Item" style="border: 0" />
			
                </h6>

-->
                <div class="body">
                    <div class="content">
    
                        <h2><xsl:value-of select="document/product/name"/></h2>  
			<br/><xsl:value-of select="document/message"/> 
        
                        <p>

  <FORM  name="order" action="Order.jsp">
  <INPUT AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="position"  ><xsl:attribute name="value"><xsl:value-of select="document/product/product_id"/></xsl:attribute></INPUT>
  <INPUT AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="add" ></INPUT>


<table border="0" >
    <tbody>
        <tr>
	    <xsl:if test="document/product/image != ''"> 
             <td  valign="top"  >
		<IMG height="80" alt=""  width="80" border="0"><xsl:attribute name="src"><xsl:value-of select="document/product/image"/></xsl:attribute></IMG> 
		<br/>

	      <xsl:if test="document/product/file_exist != ''"> 
   	        <xsl:if test="document/product/amount = '0'"> 
                <A><xsl:attribute name="href"><xsl:value-of select="document/product/product_url"/></xsl:attribute> Скачать фаил </A>
	        </xsl:if> 
	      </xsl:if> 


 	    </td>
	    </xsl:if> 

   	    <xsl:if test="document/product/image = ''">
            <td width="650" >
  	    <!-- Обрабатываем перевод строки  --> 
		  <xsl:for-each select="document/product/description/r">
			<xsl:value-of select="."/> <br/>
	  	  </xsl:for-each>

            </td>
	    </xsl:if>

   	    <xsl:if test="document/product/image != ''">
            <td width="450" >
  	    <!-- Обрабатываем перевод строки  --> 
		  <xsl:for-each select="document/product/description/r">
			<xsl:value-of select="."/> <br/>
	  	  </xsl:for-each>

            </td>
	    </xsl:if>


        </tr>
        <tr >
            <td colspan="2"  align="right"  valign="bottom" >
   	    </td>
        </tr>

        <tr >
  	    <xsl:if test="document/product/amount != '0'"> 
            <td colspan="2"  align="right"  valign="bottom" >
                   <INPUT AUTOCOMPLETE="off" TYPE="Submit" NAME="Submit" VALUE="Положить в корзину" ></INPUT>
   	    </td>
            </xsl:if>
        </tr>

    </tbody>
</table>

  </FORM>

			
			</p>
    


                        
                    </div>

                </div>
                
            </div>

            <!-- News Iten end -->


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
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/shoping_url"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/www.gvidons.com/img/user.gif" width="20"></IMG>Вернуться в каталог товаров для продолжения покупок</A>
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
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_account_history"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/www.gvidons.com/img/user.gif" width="20"></IMG>Ваши денежные операции .<br/>Наличие ваших денежных средств в магазине <br/> <xsl:value-of select="document/balans"/> рублей .</A>
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
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_order_hist"/></xsl:attribute><IMG border="0" name="Nindex_14_03" src="xsl/www.gvidons.com/img/user.gif" height="20"   width="20"></IMG>Заказы</A>
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
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_order"/></xsl:attribute><IMG border="0"  name="Nindex_14_03" src="xsl/www.gvidons.com/img/user.gif" height="20"  width="20"></IMG>Заказ</A>
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
	    <div class="even" align="left" ><br/><IMG border="0" height="20" name="Nindex_14_03" src="xsl/www.gvidons.com/img/user.gif" width="20"></IMG> Вы можете перевести денежные средства в магазин для оплаты заказа, используя кредитные карты или виртуальные кошельки, такие как Web money , Яндекс деньги , E-Port и другие  <br/><br/>
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_pay"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/www.gvidons.com/img/credit-cards.jpg" width="130"></IMG></A>
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