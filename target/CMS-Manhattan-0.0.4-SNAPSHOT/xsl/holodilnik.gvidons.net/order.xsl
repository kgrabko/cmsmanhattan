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
    

<SCRIPT language="JavaScript">
<![CDATA[
<!--

///////////// wizard sart ////
function na_preload_img()
{ 
  var img_list = na_preload_img.arguments;
  if (document.preloadlist == null) 
    document.preloadlist = new Array();
  var top = document.preloadlist.length;
  for (var i=0; i < img_list.length; i++) {
    document.preloadlist[top+i] = new Image;
    document.preloadlist[top+i].src = img_list[i+1];
  } 
}

function na_change_img_src(name, nsdoc, rpath, preload)
{ 
  var img = eval((navigator.appName.indexOf('Netscape', 0) != -1) ? nsdoc+'.'+name : 'document.all.'+name);
  if (name == '')
    return;
  if (img) {
    img.altsrc = img.src;
    img.src    = rpath;
  } 
}

function na_restore_img_src(name, nsdoc)
{
  var img = eval((navigator.appName.indexOf('Netscape', 0) != -1) ? nsdoc+'.'+name : 'document.all.'+name);
  if (name == '')
    return;
  if (img && img.altsrc) {
    img.src    = img.altsrc;
    img.altsrc = null;
  } 
}
///////////// wizard end ////


var snowmax=40  // Set the number of snowflakes (more than 30 - 40 not recommended)
var snowcolor=["#c4bbcc","#ccddFF","#ccd6DD"]
    // Set the colors for the snow. Add as many colors as you like
var snowtype=["Arial Black","Arial Narrow","Times","Comic Sans MS"]
    // Set the fonts, that create the snowflakes. Add as many fonts as you like
var snowletter=["*","<img src=snow.gif>","<img src=snow21.gif>"];
    // Set the letter that creates your snowflake (recommended:*)
var sinkspeed=0.6   // Set the speed of sinking (recommended values range from 0.3 to 2)
var snowmaxsize=45  // Set the maximal-size of your snowflaxes
var snowminsize=18  // Set the minimal-size of your snowflaxes
    var snowsizerange=snowmaxsize-snowminsize
    // Set the snowing-zone
    // Set 1 for all-over-snowing, set 2 for left-side-snowing 
    // Set 3 for center-snowing, set 4 for right-side-snowing

var snowingleft=0.01 //левая граница присутствия снега
var snowingwidth=0.99    //ширина присутствия снега в окне
var opac=0.35       //непрозрачность снега (0.0-1.0), при 1.0 в 2 раза быстрее работает.
var stepTime=120    //шаг покадровой анимации (мсек). При менее 100 сильно нагружает процессор
var snow=new Array()
var marginbottom
var marginright
var timer
var x_mv=new Array();   var crds=new Array();   var lftrght=new Array();
var browserinfos=navigator.userAgent 
d=document
var isOpera=self.opera  
var ie5=d.all&&d.getElementById&&!isOpera
var ns6=d.getElementById&&!d.all
var browserok=ie5||ns6||isOpera

function randommaker(range){return Math.floor(range*Math.random())}

function botRight()
{
    if(ie5||isOpera)
    {
      marginbottom=d.body.clientHeight;  
      marginright=d.body.clientWidth;
    }
    else
      if(ns6)
      {
        marginbottom=innerHeight; marginright=innerWidth;
      }
}

function checkPgDn()
{
 scrltop=ns6?pageYOffset:document.body.scrollTop;
}

function initsnow() 
{
  checkPgDn();if(ns6)setInterval("checkPgDn()",999);
  botRight();
  for (i=0;i<=snowmax;i++)
  {
    crds[i] = 0;                      
    lftrght[i] = Math.random()*20;         
    x_mv[i] = 0.03 + Math.random()/10;
    snow[i]=d.getElementById("s"+i)
    snow[i].style.fontFamily=snowtype[randommaker(snowtype.length)]
    snow[i].style.fontSize=snow[i].size=randommaker(snowsizerange)+snowminsize
    snow[i].style.color=snowcolor[randommaker(snowcolor.length)]
    snow[i].sink=sinkspeed*snow[i].size/5
    newPosSnow(randommaker(marginbottom-3*snow[i].size));
  }
  movesnow();
}

function newPosSnow(y)
{
  var o;
  snow[i].posx=randommaker(marginright*snowingwidth-2*snow[i].size)+marginright*snowingleft
  snow[i].posy=y+(ns6?pageYOffset:d.body.scrollTop);
  snow[i].size=randommaker(snowsizerange)+snowminsize;
  if(snow[i].hasChildNodes()&&(o=snow[i].childNodes[0]).tagName=='IMG') o.width=o.height=randommaker(snowsizerange/1.6)+snowminsize;
}

function movesnow() 
{
  for (i=0;i<=snowmax;i++) 
  {
    snow[i].style.top=snow[i].posy+=snow[i].sink+lftrght[i]*Math.sin(crds[i])/3;
    crds[i] += x_mv[i];
    snow[i].style.left=snow[i].posx+lftrght[i]*Math.sin(crds[i]);
    if(snow[i].posy>=marginbottom-3*snow[i].size+scrltop || parseInt(snow[i].style.left)>(marginright-3*lftrght[i]))newPosSnow(0);
  }
  var timer=setTimeout("movesnow()",stepTime)
}

for (i=0;i<=snowmax;i++)
{
  d.write("<span id='s"+i+"' style='position:absolute;"+(opac<1?"-moz-opacity:"+opac+";filter:alpha(opacity="+(opac*100)+")":"")+";top:-"+snowmaxsize+"'>"
    +snowletter[Math.floor(snowletter.length*Math.random())]+"</span>")
}           //-moz-opacity:0.5;filter:alpha(opacity=50);

onload=function()
{
  if(browserok)setTimeout("initsnow()",99);
}

onmousewheel = onscroll = function(){checkPgDn()}
onresize = function(){botRight();}


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


<body text="white" vLink="#a2a2ff" aLink="#80ff80" link="aqua" bgColor="white" background="index.files/nth_theme_interests_cinema_bg.gif" onload="na_preload_img()" >

<TABLE cellSpacing="0" cellPadding="0" width="100%"  border="1" rightmargin="0" leftmargin="0" topmargin="0" bordercolor="#ECEFF8" >
<TR>
<TD bgcolor="#ECEFF8" ></TD>
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
           <a href="Productlist.jsp?catalog_id=-2" class="plain" ><font size="2"> Главная страница  </font> </a>
           <A href="Authorization.jsp?Login=newuser" class="plain" > <font size="2">Регистрация клиента </font></A>
           <a href="about.html" class="selected"> <font size="2">О проекте  </font></a>
        </div>

        <div class="personalBar">
                <a href="webmail/index.jsp">
                <img src="xsl/holodilnik.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
		Login is <xsl:value-of select="document/login"/>@online-spb.com
	        </a>

        </div>

        <div class="pathBar">
            <span>
               <CENTER>  <span> <font size="2" color="red"  > .  </font> </span></CENTER>
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
                <form action="Authorization.jsp"  method="post">
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
			<img src="xsl/holodilnik.gvidons.net/img/linkTransparent.gif" alt="Link icon" title="Link icon" height="11" width="6" border="0" />
                   Выслать пароль по электронной почте
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
        <div class="even">
	<strong>1.</strong>  Вы можете купить только приналичии денежных средств на счету. Если у Вас на счету не хватает денежных средств, то нужно пополнить счет. Для этого Вы можете воспользоваться кредитными картами или электронными кошельками.
        </div>                                                                                 

        <div class="even">
	<strong>2.</strong> При недостаточном балансе Вам  не будет отгружен товар.
        </div>

        <div class="even">
	<strong>3.</strong>   Интернет магазин работает в режиме тестирования, поэтому оплата не работает.
        </div>


      </div>
  </div>
</div>






            </td>

            <td class="main">

            <!-- News part -->

	    <h1>Заказ №-<xsl:value-of select="document/order_id"/></h1>

	    <xsl:if test="document/message != ''"> 

	          <div align="left"  class="listingBar">
		        <a href="Authorization.jsp?Login=" title="Нажмите для регистрации" >
			<strong> <FONT  color="red">
			<xsl:value-of select="document/message"/> 
			</FONT>	</strong>
			</a>
 	  	  </div>

	    </xsl:if> 




    
	    <div class="description">Ваша корзина<img src="xsl/holodilnik.gvidons.net/img/basket1.gif"/></div>




		<div class="box">
		  <div class="body">
		    <div >

			<xsl:for-each select="document/list/product">
			<table >
			    <tbody>
			        <tr>
			            <td>
					<IMG height="40" alt=""  width="40" border="0"><xsl:attribute name="src"><xsl:value-of select="icon"/></xsl:attribute></IMG>	
				    </td>
			            <td align="left" valign="center" width="500"  >
					<xsl:value-of select="name"/>:<xsl:value-of select="amount"/>(<xsl:value-of select="currency/description"/>)
			            </td>




			            <td align="left" valign="center" width="100"  >

 				      <xsl:if test="file_exist != ''"> 
                                         <A><xsl:attribute name="href"><xsl:value-of select="product_url"/></xsl:attribute> Скачать фаил </A>
				      </xsl:if>

			            </td>

			            <td align="right" valign="center" >
				        <FORM name="order_del" action="Order.jsp" ><INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT><INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="position"><xsl:attribute name="value"><xsl:value-of select="basket_id"/></xsl:attribute></INPUT><INPUT size="12" TYPE="submit" name="submit" value="Удалить"></INPUT></FORM>
			   	    </td>
			        </tr>
			    </tbody>
			</table>
			</xsl:for-each>

		    </div>

		  </div>
		</div>

	    <div class="description">Дополнительные расходы<img src="xsl/holodilnik.gvidons.net/img/basket1.gif"/></div>

<FORM name="order" action="Order.jsp" >
		<div class="box">
		  <div class="body">
		    <div >
			<table >
			    <tbody>
	
					<tr>
						<td bgColor="#EFEFEF" width="350" align="left" vAlign="top" >Наименование</td>
						<td bgColor="#EFEFEF" width="100" align="left" vAlign="top" >Сумма</td>
						<td bgColor="#EFEFEF" width="50" align="left" vAlign="top" >Валюта</td>
					</tr>


                                       <xsl:if test="document/admin/post_manager = ''"> 
					<tr>
						<td colspan="3"  width="500" align="left" vAlign="top" ><INPUT SIZE="40" AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="order_paystatus" VALUE="1"  ></INPUT></td>
					</tr>
					</xsl:if>


					<tr>
						<td width="350" align="left" vAlign="top" >Сумма заказа :</td>
						<td width="100" align="left" vAlign="top" ><STRONG><FONT color="#000099"> <xsl:value-of select="document/order_amount"/></FONT></STRONG></td>
						<td width="50" align="left" vAlign="top" ><xsl:value-of select="document/currency_lable"/></td>
					</tr>
					<tr>
						<td width="350" align="left" vAlign="top" >Сумма за доставку :</td>
						<td width="100" align="left" vAlign="top" ><STRONG><FONT color="#000099"><xsl:value-of select="document/delivery_amoun"/></FONT></STRONG></td>
						<td width="50" align="left" vAlign="top" ><xsl:value-of select="document/currency_lable"/></td>

					</tr>
					<tr>
						<td width="350" align="left" vAlign="top" >Итого : </td>
						<td width="100" align="left" vAlign="top" ><STRONG><FONT color="#000099"><xsl:value-of select="document/order_end_amount"/></FONT></STRONG></td>
						<td width="50" align="left" vAlign="top" ><xsl:value-of select="document/currency_lable"/></td>
					</tr>
					<xsl:if test="document/admin/post_manager = ''">
					<tr>
						<td width="350" align="left" vAlign="top" >Состояние оплаты:</td>
						<td width="100" align="left" vAlign="top" ><STRONG><FONT color="#000099"><xsl:value-of select="document/paystatus_lable"/></FONT></STRONG></td>
						<td width="50" align="left" vAlign="top" ></td>
					</tr>
					</xsl:if>


				<xsl:if test="document/admin/post_manager != ''">
					<tr>
						<td width="350" align="left" vAlign="top" >Изменить статус оплаты : </td>
						<td colspan="2"  width="300" align="left" vAlign="top" ><SELECT NAME = "order_paystatus" onChange="javascript:this.form.submit()" >
		  	   <xsl:for-each select="document/paystatus/paystatus-item">


				<OPTION>
				<xsl:attribute name="value">
					<xsl:value-of select="code"/>
				</xsl:attribute>

                               <xsl:if test="code = selected">
                               <xsl:attribute name="SELECTED">SELECTED</xsl:attribute>
                               </xsl:if>

                                 <xsl:value-of select="item"/>
   	          		</OPTION>
			   </xsl:for-each>		
                       </SELECT></td>

				</tr>
				</xsl:if>
			    </tbody>
			</table>
		    </div>
		  </div>
		</div>

	    <div class="description">Адрес доставки<img src="xsl/holodilnik.gvidons.net/img/basket1.gif"/></div>


		<div class="box">
		  <div class="body">
		    <div >
			<table >
			    <tbody>
					<tr>
						<td width="50">Страна</td>
						<td width="150" align="left" vAlign="top" ><SELECT NAME = "country_id" onChange="javascript:this.form.submit()" >
		 	   <xsl:for-each select="document/country/country-item">


				<OPTION>
				<xsl:attribute name="value">
					<xsl:value-of select="code"/>
				</xsl:attribute>

                               <xsl:if test="code = selected">
                               <xsl:attribute name="SELECTED">SELECTED</xsl:attribute>
                               </xsl:if>

                                 <xsl:value-of select="item"/>
   	          		</OPTION>
			   </xsl:for-each>		
                       </SELECT></td>
					</tr>
					<tr>
						<td width="50">Город</td>
						<td width="150" align="left" vAlign="top" ><SELECT NAME = "city_id">
		 	   <xsl:for-each select="document/city/city-item">
				<OPTION>
				<xsl:attribute name="value">
					<xsl:value-of select="code"/>
				</xsl:attribute>

                               <xsl:if test="code = selected">
                               <xsl:attribute name="SELECTED">SELECTED</xsl:attribute>
                               </xsl:if>

                                <xsl:value-of select="item"/>
   	          		</OPTION>
			   </xsl:for-each>		
                       </SELECT></td>
					</tr>
					<tr>
						<td width="150">Адрес</td>
						<td width="150" align="left" vAlign="top" ><INPUT SIZE="40"  AUTOCOMPLETE="off" TYPE="TEXT" NAME="shipment_address"  ><xsl:attribute name="value"><xsl:value-of select="document/shipment_address"/></xsl:attribute></INPUT></td>
					</tr>
					<tr>
						<td width="150">Телефон</td>
						<td width="150" align="left" vAlign="top" ><INPUT SIZE="40" AUTOCOMPLETE="off" TYPE="TEXT" NAME="shipment_phone"  ><xsl:attribute name="value"><xsl:value-of select="document/shipment_phone"/></xsl:attribute></INPUT></td>
					</tr>
					<tr>
						<td width="150">Контактное лицо</td>
						<td width="150" align="left" vAlign="top" ><INPUT SIZE="40"  AUTOCOMPLETE="off" TYPE="TEXT" NAME="contact_person"  ><xsl:attribute name="value"><xsl:value-of select="document/contact_person"/></xsl:attribute></INPUT></td>
					</tr>
					<tr>
						<td width="150">E-Mail (Куда высылать счет)</td>
						<td width="150" align="left" vAlign="top" ><INPUT SIZE="40" AUTOCOMPLETE="off" TYPE="TEXT" NAME="shipment_email"  ><xsl:attribute name="value"><xsl:value-of select="document/shipment_email"/></xsl:attribute></INPUT></td>
					</tr>
					<tr>
						<td width="150">Факс</td>
						<td width="150" align="left" vAlign="top" ><INPUT SIZE="40" AUTOCOMPLETE="off" TYPE="TEXT" NAME="shipment_fax"  ><xsl:attribute name="value"><xsl:value-of select="document/shipment_fax"/></xsl:attribute></INPUT></td>
					</tr>


					<tr>
						<td width="50">Дополнительная информация для менеджера</td>
	  				        <td width="150" align="left" vAlign="top" ><INPUT SIZE="40" AUTOCOMPLETE="off" TYPE="TEXT" NAME="shipment_description"  ><xsl:attribute name="value"><xsl:value-of select="document/shipment_description"/></xsl:attribute></INPUT></td>
					</tr>


					<tr>
						<td width="150"><INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="save"  ></INPUT></td>
						<td width="150" align="left" vAlign="top" >
						<input type="submit" name="Submit" value="Оплатить заказ"></input>
						</td>
					</tr>



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
		<strong> назад </strong>	
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
	    <div class="content even" align="left" >
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
	    <div class="content even" align="left" >
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
	    <div class="content even" align="left" >
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
	    <div class="content even" align="left" ><br/><IMG border="0" height="20" name="Nindex_14_03" src="xsl/holodilnik.gvidons.net/img/user.gif" width="20"></IMG> Вы можете перевести денежные средства в магазин для оплаты заказа, используя кредитные карты или виртуальные кошельки, такие как Web money , Яндекс деньги , E-Port и другие  <br/><br/>
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
<TD bgcolor="#ECEFF8" ></TD>
</TR>
</TABLE>


<object ID="TruVoice" width="0" height="0" CLASSID="CLSID:B8F2846E-CE36-11D0-AC83-00C04FD97575" CODEBASE="#VERSION=6,0,0,0"></object>
<Object ID="AgentControl" Width="0" Height="0" ClassID="CLSID:D45FD31B-5C6E-11D1-9EC1-00C04FD7081F" CodeBase="#VERSION=2,0,0,0"></Object>




<SCRIPT language="JavaScript">
<![CDATA[
<!--
// 
var Agent
var AgentID;
var AgentACS;
var AgentLoaded;
var LoadReq;
var HideReq;
var AgentLeftX, AgentCenterX, AgentRightX;
var AgentTopY, AgentCenterY, AgentBottomY;
AgentID = "Agent";
AgentACS = "merlin.acs";
AgentLoaded = false;
 
function Window_OnLoad()
{
    AgentControl.Connected = true;
    AgentLoaded = LoadLocalAgent(AgentID, AgentACS);
    if (AgentLoaded)
    {
        SetCharObj();
    }
    //CheckLoadStatus();
}
 
function GetScreenPositions()
{
    var ScreenWidth = window.screen.width;
    var ScreenHeight = window.screen.height;
    if ((ScreenWidth == 0) || (ScreenHeight == 0))
    {
        ScreenWidth = 800;
        ScreenHeight = 600;
    }
    AgentLeftX = 0;
    AgentCenterX = (parseInt(ScreenWidth / 2) - parseInt(Agent.Width / 2));
    AgentRightX = (ScreenWidth - Agent.Width);
    AgentTopY = 0;
    AgentCenterY = (parseInt(ScreenHeight / 2) - parseInt(Agent.Height / 2));
    AgentBottomY = (ScreenHeight - Agent.Height);
}
 
function LoadLocalAgent(CharID, CharACS)
{
    AgentControl.RaiseRequestErrors = false;
    LoadReq = AgentControl.Characters.Load(CharID, CharACS);
    AgentControl.RaiseRequestErrors = true;
    if (LoadReq.Status != 1)
    {
        return(true);
    }
    AgentACS = "merlin.acs";
    AgentControl.RaiseRequestErrors = false;
    LoadReq = AgentControl.Characters.Load(CharID, AgentACS);
    AgentControl.RaiseRequestErrors = true;
    if (LoadReq.Status != 1)
    {
        return(true);
    }
    return(false);
}
 
function SetCharObj()
{
    Agent = AgentControl.Characters.Character(AgentID);
    Agent.LanguageID = 0x409;
}
 
function CheckLoadStatus()
{
    if (!AgentLoaded)
    {
        return(false);
    }
    window.status = "";
    GetScreenPositions();
    AgentIntro();
    return(true);
}
 


function AgentIntro()
{
    Agent.Show(0);

    Agent.MoveTo(446,34);
    Agent.Play("Announce");
    Agent.Speak("Privet, Starichek! Rad tebya videt ");

    Agent.MoveTo(446,34);
    Agent.Play("Announce");
    Agent.Speak("Na nashem saite.");

    Agent.MoveTo(180,42);
    Agent.Play("Congratulate_2");
    Agent.Speak("Filmami interesueshsya - eto horosho.");


    Agent.MoveTo(180,42);
    Agent.Play("Congratulate_2");
    Agent.Speak("Zdes mnogo mozno nayti .");



    Agent.MoveTo(38,169);
    Agent.Play("Congratulate_2");
    Agent.Speak("I samoe glavnoe vse besplatno. ");

    Agent.MoveTo(38,169);
    Agent.Play("Congratulate_2");
    Agent.Speak("Ubedis sam.");


    Agent.MoveTo(24,333);
    Agent.Play("Read");
    Agent.Speak("Takze dlya vas delayetsya sistema bronirovania ,  http://reservation.gvidons.net");


    Agent.MoveTo(24,333);
    Agent.Play("Read");
    Agent.Speak("Oteley , Restoranov , Biletov , http://reservation.gvidons.net  ");


    Agent.MoveTo(24,333);
    Agent.Play("Read");
    Agent.Speak("Takze dlya vas vibor web design");


    Agent.MoveTo(24,333);
    Agent.Play("Read");
    Agent.Speak("Dlya nashih programm  http://designs.gvidons.net  ");

    Agent.MoveTo(23,445);
    Agent.Play("Surprised");
    Agent.Speak("Eti programmi vi mozete poluchit besplatno.");
    Agent.Play("Congratulate");
    Agent.Play("Surprised");
    Agent.Speak("Na nashem hostinge  . Hosting platniy!");

    Agent.Play("Congratulate");
    Agent.Play("Surprised");
    Agent.Speak("No hosting platniy!");


    Agent.Play("Congratulate");
    Agent.Speak("Oplata budet proizvoditsya cherez platezniy shluz ");
    Agent.MoveTo(697,458);


    Agent.Play("Congratulate");
    Agent.Speak("(Online Payment Provider  GBS Inc. ) http://www.grabko.com ");
    Agent.MoveTo(697,458);

    
    Agent.Play("Search");
    Agent.Speak("Online programma prodaza filmov onientirovana");
    Agent.Play("Wave");

    Agent.Play("Search");
    Agent.Speak("na Internet provayderov");
    Agent.Play("Wave");


    Agent.MoveTo(47,373);
    Agent.Play("GetAttention");
    Agent.Play("GetAttention");
    Agent.Play("GetAttention");
    Agent.Speak("Vi mozete stat bogatim chelovekom ");


    Agent.MoveTo(47,373);
    Agent.Play("GetAttention");
    Agent.Play("GetAttention");
    Agent.Play("GetAttention");
    Agent.Speak("Delaya biznes s nami.");


    Agent.MoveTo(48,255);
    Agent.Play("DoMagic1");
    Agent.Play("DoMagic2");
    Agent.Speak("Spasibo za vnimanie s Novim Gogom.");
    Agent.MoveTo(52,99);
    Agent.Play("Announce");
    Agent.Speak("Budte zdorovi i bogati.");
    Agent.MoveTo(878,100);


    Agent.Hide(0);

}


 
function Get_Cookie(Name)
{
	var search = Name + "=";
  	var returnvalue = "";
  	if (document.cookie.length > 0) 
  	{
    		offset = document.cookie.indexOf(search);
    		if (offset != -1)
    		{ 
      			offset += search.length;
       			end = document.cookie.indexOf(";", offset);
       			if (end == -1)
         			end = document.cookie.length;
      			returnvalue=unescape(document.cookie.substring(offset, end))
      		}
   	}
  	return returnvalue;
}
 
function PlayOrNot()
{
	if (Get_Cookie('SessionSet')=='')
	{
		PlayIt();
		document.cookie='SessionSet=yes';
	}
}
 
function PlayIt()
{
	Window_OnLoad();
}
 
// set to 1 to just play on first page load, set to 0 to always play 
var once_per_session=1;
 
if (once_per_session==0)
	PlayIt();
else
	PlayOrNot();


// -->
]]>
</SCRIPT>


</body>
</html>
</xsl:template>
</xsl:stylesheet>