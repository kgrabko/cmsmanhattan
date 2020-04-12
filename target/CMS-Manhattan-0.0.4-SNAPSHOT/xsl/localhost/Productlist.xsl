<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
<xsl:output method="html" indent="yes"/>
<xsl:output encoding="windows-1251"/>
<xsl:strip-space elements="*"/>



<xsl:template match="/">

<html >

<head>
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
     <title>Ishop  - Grabko Konstantin </title>
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
document.forms[0].search_char.value = char ;
document.forms[0].search_value.value = '' ;
document.forms[0].searchquery.value = '2' ;
document.forms[0].submit();
}

function search_word() 
{ 
document.forms[0].search_char.value = '' ;
document.forms[0].searchquery.value = '1' ;
document.forms[0].submit();
}


function search_cre() 
{ 
document.forms[0].search_char.value = '' ;
document.forms[0].search_value.value = '' ;
document.forms[0].searchquery.value = '3' ;
}


function search_cre_bronirovanie() 
{ 
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
<TD vAlign="top" Align="center" width="1020">


<a class="skipnav" href="#documentContent">Skip to content</a>

<div>

        <div class="top">


<TABLE cellSpacing="0" cellPadding="10"  height="120"  width="100%" background="index.files/bg.jpg" border="0" rightmargin="0" leftmargin="0" topmargin="0">
  <TBODY>
  <TR>
    <TD vAlign="top" Align="left"  width="20%">
       <a  onclick="this.style.behavior='url(#default#homepage)'; this.setHomePage('http://www.gviodns.net'); return false;" href="#" ><font color="white" ><b>www.gvidons.net  </b></font></a> <br/>   <a href="javascript:window.external.AddFavorite('http://10.6.0.106','www.gvidons.net')" ><font color="white" > <b>Добавить в избранное</b></font> </a> <br/>
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
           <a href="Productlist.jsp?catalog_id=-2" class="selected" ><font size="2"> Главная страница </font> </a>
           <A href="Authorization.jsp?Login=newuser" class="plain" ><font size="2"> Регистрация покупателя </font> </A>
           <a href="webmail/index.jsp"  class="plain"> <font size="2"  > ПОЧТА </font></a>
           <a href="index_english.jsp"  class="plain"> <font size="2" color="red" > English </font></a>



          <!-- <a href="pogoda.html" class="plain"> <font size="2">О погоде </font></a> -->
           <a href="irc/irc1.html" class="plain"> <font size="2">Чат</font></a>
           <a href="about.html" class="selected"> <font size="2">О проекте  </font></a>
        </div>

        <div class="personalBar">
            
                <a href="webmail/index.jsp">
                <img src="xsl/www.gvidons.com/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
		Login is <xsl:value-of select="document/login"/>@online-spb.com
            </a>
            
        </div>


        <div class="pathBar">
            <span>
             <CENTER><font size="1"  >Поиск по первой букве  : </font>  <font size="1"  >
                <a href="#" onClick="return top.search('А');return true" >А</a> . <a href="#" onClick="return top.search('Б');return true">Б</a> . <a href="#" onClick="return top.search('В');return true" >В</a> . <a href="#" onClick="return top.search('Г');return true" >Г</a> . <a href="#" onClick="return top.search('Д');return true" >Д</a>       
             .  <a href="#" onClick="return top.search('Е');return true" >Е</a> . <a href="#" onClick="return top.search('Ж');return true" >Ж</a> . <a href="#" onClick="return top.search('З');return true" >З</a> . <a href="#" onClick="return top.search('И');return true" >И</a> . <a href="#" onClick="return top.search('К');return true" >К</a>       
             .  <a href="#" onClick="return top.search('Л');return true" >Л</a> . <a href="#" onClick="return top.search('М');return true" >М</a> . <a href="#" onClick="return top.search('Н');return true" >Н</a> . <a href="#" onClick="return top.search('О');return true" >О</a> . <a href="#" onClick="return top.search('П');return true" >П</a>       
             .  <a href="#" onClick="return top.search('Р');return true" >Р</a> . <a href="#" onClick="return top.search('С');return true" >С</a> . <a href="#" onClick="return top.search('Т');return true" >Т</a> . <a href="#" onClick="return top.search('У');return true" >У</a> . <a href="#" onClick="return top.search('Ф');return true" >Ф</a>       
             .  <a href="#" onClick="return top.search('Х');return true" >Х</a> . <a href="#" onClick="return top.search('Ц');return true" >Ц</a> . <a href="#" onClick="return top.search('Ч');return true" >Ч</a> . <a href="#" onClick="return top.search('Ш');return true" >Ш</a> . <a href="#" onClick="return top.search('Щ');return true" >Щ</a>       
             .  <a href="#" onClick="return top.search('Э');return true" >Э</a> . <a href="#" onClick="return top.search('Ю');return true" >Ю</a> . <a href="#" onClick="return top.search('Я');return true" >Я</a> . <a href="Productlist.jsp?search_char=1">1</a> . <a href="Productlist.jsp?search_char=2">2</a>       
             .  <a href="Productlist.jsp?search_char=3">3</a> . <a href="Productlist.jsp?search_char=4">4</a> . <a href="Productlist.jsp?search_char=5">5</a> . <a href="Productlist.jsp?search_char=6">6</a> . <a href="Productlist.jsp?search_char=7">7</a>       
             .  <a href="Productlist.jsp?search_char=8">8</a> . <a href="Productlist.jsp?search_char=9">9</a> . <a href="Productlist.jsp?search_char=0">0</a>
	     </font>
		</CENTER>
            </span>
        </div>





<!--

        <div class="pathBar">
            <span>
              <CENTER>
                <font size="2"  color="red"  >
                 Мы не продаем фильмы , мы разрабатываем программное обеспечение для электронной коммерции . У нас можно скачать видео файл для отладки нашей высокотехнологичной системы - так мы работаем базами данных где счет идет на терабайты информации .
	        </font>
	       </CENTER>
            </span>
        </div>
-->

        <div class="pathBar">
            <span>
              <CENTER>
                <font size="2"  color="red"  >
                 Для юзеров ижоры телеком  у нас меняется IP адрес  с 15 числа 10.6.0.106 на 10.6.1.106
	        </font>
	       </CENTER>
            </span>
        </div>


        <div class="pathBar">
            <span>
              <CENTER>
                <font size="2"  color="red"  >
                 Все фильмы можно качать бесплатно , для этого не нужно заходить под своей учетной записью .
	        </font>
	       </CENTER>
            </span>
        </div>



        <div class="pathBar">
            <span>
              <CENTER>
                <font size="2"  color="red"  >
                 Эти проекты разрабатываются на продукте  media portal - (для разрешения дисплея 1024x768)
	        </font>
	       </CENTER>
            </span>
        </div>

        <div class="pathBar">
            <span>
              <CENTER>
                <font size="2"  color="red"  >
                  Оплата не работает идет тестирование
	        </font>
	       </CENTER>
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

       <div class="box" > 
          <div class="body">
            <div class="content odd">
                <form action="Authorization.jsp"  method="post">
                   <font size="1" > <strong>Пользователь</strong></font> <br />
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
                    <input class="context searchButton"   type="submit" name="submit"  value="Log in" tabindex="10003" />

                </form>
            </div>
    
            <div class="content even"> 
                <a href="Authorization.jsp?Login=newuser">
                   <img src="xsl/www.gvidons.com/img/linkTransparent.gif" alt="Link icon" title="Link icon" height="11" width="6" border="0" />
                   Выслать пароль по электронной почте
                </a>
            </div>
        </div>
    </div>


<xsl:if test="document/empty_page_co1 = 'false'" > 
<div class="box"  >
    <div>


           <TABLE class="text"  height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
              <TBODY>
              <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Дополнительно</B></FONT>
               </TD>
              </TR>
  	     </TBODY>
  	   </TABLE>

           <TABLE height="9" cellSpacing="4" cellPadding="0" width="159"   background="index.files/bg34.gif">
              <TBODY>
              <TR><TD></TD></TR>
              </TBODY>
           </TABLE>                                                    

           <TABLE cellSpacing="0" cellPadding="0" width="159" align="center"  background="index.files/bgleft.gif" border="0" rightmargin="0"  leftmargin="0" topmargin="0">
           <TBODY>
            <!-- News Iten start -->
	    <xsl:for-each select="document/coproductlist1/coproduct1">
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

<br/>
   <!-- Кнопки удаления  , начало -->
	<xsl:if test="/document/admin/post_manager != ''">
		                <form name="product_del"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Удалить"></INPUT>
		                </form>
		                <form name="product_edit"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="co1" ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Изменить"></INPUT>
		                </form>
	</xsl:if>
   <!-- Кнопки удаления  , конец  -->


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
</xsl:if>

<div class="box"  >
    <div>

    <form name="searchcreform"  action="Productlist.jsp" >	  


  	   <INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="searchquery" VALUE="3"  ></INPUT>


           <TABLE class="text"  height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
              <TBODY>
              <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Поиск</B></FONT>
               </TD>
              </TR>
  	     </TBODY>
  	   </TABLE>

           <TABLE height="9" cellSpacing="4" cellPadding="0" width="159"   background="index.files/bg34.gif">
              <TBODY>
              <TR><TD></TD></TR>
              </TBODY>
           </TABLE>                                                    

           <TABLE cellSpacing="0" cellPadding="0" width="159" align="center"  background="index.files/bgleft.gif" border="0" rightmargin="0"  leftmargin="0" topmargin="0">
           <TBODY>
            <!-- Creteria Iten start -->
                <TR>
                <TD width="5"></TD>
                <TD width="145">

 	    <table width="150"  ><tr>
             <td width="50"  > Жанр: </td>
	     <td align="right" > <SELECT NAME = "creteria1_id" >
		 	   <xsl:for-each select="document/creteria1/creteria1-item">


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
                       </SELECT>
		</td>
              </tr></table>


		</TD>
                <TD width="5">
                </TD>
                </TR>
              <TR><TD width="155" background="index.files/bgline.gif" colSpan="3"  height="10" ></TD></TR>
            <!-- Creteria Iten end -->


            <!-- Creteria Iten start -->
                <TR>
                <TD width="5"></TD>
                <TD width="145">


 	    <table width="150"  ><tr>
             <td width="50"  > Страны: </td>
             <td align="right" >   <SELECT NAME = "creteria2_id" >
		 	   <xsl:for-each select="document/creteria2/creteria2-item">
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
                       </SELECT>
		</td>
              </tr></table>

		</TD>
                <TD width="5">
                </TD>
                </TR>
              <TR><TD width="155" background="index.files/bgline.gif" colSpan="3"  height="10" ></TD></TR>
            <!-- Creteria Iten end -->

            <!-- Creteria Iten start -->
                <TR>
                <TD width="5"></TD>
                <TD width="145">

 	    <table width="150"  ><tr>
             <td width="50"  > Выпуск: </td>
             <td align="right" > <SELECT NAME = "creteria3_id"  >
		 	   <xsl:for-each select="document/creteria3/creteria3-item">


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
                       </SELECT>
		</td>
              </tr></table>

		</TD>
                <TD width="5">
                </TD>
                </TR>
              <TR><TD width="155" background="index.files/bgline.gif" colSpan="3"  height="10" ></TD></TR>
            <!-- Creteria Iten end -->
            <!-- Creteria Iten start -->
                <TR>
                <TD width="5"></TD>
                <TD width="145">

 	    <table width="150"  ><tr>
             <td width="50"  > Формат: </td>
             <td align="right" >  <SELECT NAME = "creteria4_id"  >
		 	   <xsl:for-each select="document/creteria4/creteria4-item">


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
                       </SELECT>

		</td>
              </tr></table>

		</TD>
                <TD width="5">
                </TD>
                </TR>
              <TR><TD width="155" background="index.files/bgline.gif" colSpan="3"  height="10" ></TD></TR>
            <!-- Creteria Iten end -->


              <TR>
                <TD align="right" width="155"  colSpan="3" >
		<input class="context searchButton"  type="submit" size="20" value="Искать"  tabindex="30002"   />
		</TD>
              </TR>
              
              <TR>
                <TD width="155" background="index.files/bgline2.gif" colSpan="3" height="11" ></TD>
              </TR>
             </TBODY>
             </TABLE>

     </form>


       </div>
     </div>


<div class="box"  >
    <div>


           <TABLE class="text"  height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
              <TBODY>
              <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Новости</B></FONT>
               </TD>
              </TR>
  	     </TBODY>
  	   </TABLE>

           <TABLE height="9" cellSpacing="4" cellPadding="0" width="159"   background="index.files/bg34.gif">
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
<br/>
   <!-- Кнопки удаления  , начало -->
	<xsl:if test="/document/admin/post_manager != ''">
		<table>
		    <tbody>
		        <tr>
		         <td>
		                <form name="product_del"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Удалить"></INPUT>
		                </form>
		         </td>
		         <td>

		                <form name="product_edit"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="news"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Изменить"></INPUT>
		                </form>
		         </td>
		        </tr>
		    </tbody>
		</table>


	</xsl:if>
   <!-- Кнопки удаления  , конец  -->

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


<!-- Reklamma -->
        <div class="tabs">
           <a href="/designs/" class="plain" ><font size="2"> Дизайны Media@Shop </font> </a>
           <a href="/travel/" class="selected" ><font size="2"> Туристический портал </font> </a>
<!--           <a href="http://billing.grabko.com:88/Account.jsp" class="selected" ><font size="2"> платежный шлюз </font> </a>  -->
           <a href="/vision/" class="selected" ><font size="2"> Аптека </font> </a>
        </div>

        <div class="tabs">
           <a href="/holodilnik/" class="selected" ><font size="2"> Холодильники </font> </a>
           <a href="/tehnika/" class="selected" ><font size="2"> Стиральный машины </font> </a>
<!--       <a href="/vityazka/" class="selected" ><font size="2"> Вытяжки </font> </a>  -->
           <a href="/reservation/" class="selected" ><font size="2" color="red" > Бронирование отеля </font> </a>
        </div>



            <!-- News part -->
	    <h1>

		<xsl:if test="document/search_query = '0'">   <!--  не показывать если поиск -->

                           <xsl:for-each select="document/catalog/catalog-item">
				<xsl:attribute name="value">
					<xsl:value-of select="code"/>
				</xsl:attribute>

                               <xsl:if test="code = selected">
                               <xsl:value-of select="item"/>
                               </xsl:if>
			   </xsl:for-each> . Страница №
 		<xsl:if test="document/offset = '0'">1</xsl:if>
		<xsl:if test="document/offset = '10'">2</xsl:if>
		<xsl:if test="document/offset = '20'">3</xsl:if>
		<xsl:if test="document/offset = '30'">4</xsl:if>
		<xsl:if test="document/offset = '40'">5</xsl:if>
		<xsl:if test="document/offset = '50'">6</xsl:if>
		<xsl:if test="document/offset = '60'">7</xsl:if>
		<xsl:if test="document/offset = '70'">8</xsl:if>
		<xsl:if test="document/offset = '80'">9</xsl:if>
		<xsl:if test="document/offset = '90'">10</xsl:if>
 		<xsl:if test="document/offset = '100'">11</xsl:if>
		<xsl:if test="document/offset = '110'">12</xsl:if>
		<xsl:if test="document/offset = '120'">13</xsl:if>
		<xsl:if test="document/offset = '130'">14</xsl:if>
		<xsl:if test="document/offset = '140'">15</xsl:if>
		<xsl:if test="document/offset = '150'">16</xsl:if>
		<xsl:if test="document/offset = '160'">17</xsl:if>
		<xsl:if test="document/offset = '170'">18</xsl:if>
		<xsl:if test="document/offset = '180'">19</xsl:if>
		<xsl:if test="document/offset = '190'">20</xsl:if>
   	        </xsl:if>


		<xsl:if test="document/search_query != '0'">   <!--  показывать если поиск -->
                 Результаты поиска  страница №
 		<xsl:if test="document/offset = '0'">1</xsl:if>
		<xsl:if test="document/offset = '10'">2</xsl:if>
		<xsl:if test="document/offset = '20'">3</xsl:if>
		<xsl:if test="document/offset = '30'">4</xsl:if>
		<xsl:if test="document/offset = '40'">5</xsl:if>
		<xsl:if test="document/offset = '50'">6</xsl:if>
		<xsl:if test="document/offset = '60'">7</xsl:if>
		<xsl:if test="document/offset = '70'">8</xsl:if>
		<xsl:if test="document/offset = '80'">9</xsl:if>
		<xsl:if test="document/offset = '90'">10</xsl:if>
   	        </xsl:if>






	    </h1>

	<br/>

        <!-- Показать кнопку для управления сайтом , начало  -->

        <xsl:if test="document/admin/post_manager != ''">
	<div class="footer">Нажмите эту кнопку, если вы  хотите  изменить содержимое сайта
	<A><xsl:attribute name="HREF"><xsl:value-of select="document/admin/post_manager"/>
	</xsl:attribute><img SRC="images/post.jpg" border="0" alt="Post" ></img></A>
        </div>
	</xsl:if>
        <!-- Показать кнопку для управления сайтом , конец  -->


		<xsl:if test="document/empty_page = 'true'" > 
			На этой странице ничего нет.
   	        </xsl:if>



        <!-- Вывод каждого елемента из тега product , начало -->
        <xsl:for-each select="document/list/product">
	<table height="50" >
	    <tbody height="50" >
	        <tr>
	            <td>


	              <TABLE height="20" cellSpacing="0" cellPadding="0" width="300" background="index.files/positionhead.jpg" >
	              <TBODY>
	              <TR>
	                <TD vAlign="center"  Align="center" >
			<FONT color="white" ><B>
			<xsl:if test="rigth/amount != '0'"><b> <xsl:value-of select="rigth/name"/></b></xsl:if>
			<xsl:if test="rigth/amount = '0'">Новости - <xsl:value-of select="rigth/name"/></xsl:if>
  		        </B></FONT>
	               </TD>
	              </TR>
	  	     </TBODY>
	  	   </TABLE>




		<div class="box">
		  <div class="body">
		    <div  valign="center"   align="center"  class="description" >

		    <xsl:if test="rigth/icon != ''"> 
			<table width="295" border="0" cellSpacing="0" cellPadding="5"   height="100"   >
			    <tbody width="295" height="100"   >
<!-- Просто одна картинка -->
<!--

			        <tr>
		        	    <td valign="center"   align="center"   width="100%" >

		        	        <A><xsl:attribute name="HREF"><xsl:value-of select="rigth/policy_url"/></xsl:attribute><IMG height="100" alt="Подробно"  width="295" border="0"><xsl:attribute name="src"><xsl:value-of select="rigth/icon"/></xsl:attribute></IMG></A>
			            </td>
			        </tr>
-->

			        <tr>
				    <xsl:if test="rigth/icon != ''"> 
		        	    <td valign="top"  width="10%" >
		        	        <A><xsl:attribute name="HREF"><xsl:value-of select="rigth/policy_url"/></xsl:attribute><IMG height="80" alt=""  width="80" border="0"><xsl:attribute name="src"><xsl:value-of select="rigth/icon"/></xsl:attribute></IMG></A>
			            </td>
		     		    </xsl:if>

			            <td valign="top" align="left" width="90%"  >

                                   <A><xsl:attribute name="HREF"><xsl:value-of select="rigth/policy_url"/></xsl:attribute>
			  	    <!-- Обрабатываем перевод строки  --> 
   				    <xsl:for-each select="rigth/description/r">
					 <xsl:value-of select="."/> <br/>
			   	    </xsl:for-each>
		                     Подробно...</A>
		                    </td>
			        </tr>


			    </tbody>
			</table>
     		    </xsl:if>

		    <xsl:if test="rigth/icon = ''"> 
			<table width="295" border="0" cellSpacing="0" cellPadding="0"   height="100"   >
			    <tbody width="295" height="100"   >
			        <tr>
		        	    <td valign="center"   align="center"   width="100%" >
			     	   <A><xsl:attribute name="HREF"><xsl:value-of select="rigth/policy_url"/></xsl:attribute>
   				    <xsl:for-each select="rigth/description/r">
					 <xsl:value-of select="."/> <br/>
			   	    </xsl:for-each>
		                     Подробно...</A>
			            </td>

			        </tr>
			    </tbody>
			</table>
     		    </xsl:if>



		    </div>
		  </div>
		</div>


            </td>

	<xsl:if test="left/name != ''"> 
            <td>

              <TABLE height="20" cellSpacing="0" cellPadding="0" width="300" background="index.files/positionhead.jpg" >
              <TBODY>
              <TR>
                <TD vAlign="center"  Align="center" ><FONT color="white" ><B>
		<xsl:if test="left/amount != '0'"><b> <xsl:value-of select="left/name"/></b></xsl:if>
		<xsl:if test="left/amount = '0'">Новости - <xsl:value-of select="left/name"/></xsl:if>

		</B></FONT>
               </TD>
              </TR>
  	     </TBODY>
  	   </TABLE>
		<div class="box" >
		  <div class="body" >
		    <div  valign="center"   align="center"  class="description" >

		    <xsl:if test="left/icon != ''"> 
			<table width="295" border="0" cellSpacing="0" cellPadding="5"   height="100"   >
			    <tbody width="295" height="100"   >

<!--

			        <tr>
		        	    <td valign="center"   align="center"   width="100%" >
		        	        <A><xsl:attribute name="HREF"><xsl:value-of select="left/policy_url"/></xsl:attribute><IMG height="100" alt="Подробно"  width="295" border="0"><xsl:attribute name="src"><xsl:value-of select="left/icon"/></xsl:attribute></IMG></A>
			            </td>
			        </tr>
-->
			        <tr>
				    <xsl:if test="left/icon != ''"> 
		        	    <td  valign="top"  width="10%"  >
		        	        <A><xsl:attribute name="HREF"><xsl:value-of select="left/policy_url"/></xsl:attribute><IMG height="80" alt=""  width="80" border="0"><xsl:attribute name="src"><xsl:value-of select="left/icon"/></xsl:attribute></IMG></A>
			            </td>
		     		    </xsl:if>

			            <td valign="top" align="left" width="90%"  >

				   <A><xsl:attribute name="HREF"><xsl:value-of select="left/policy_url"/></xsl:attribute>
			  	    <!-- Обрабатываем перевод строки  --> 
   				    <xsl:for-each select="left/description/r">
					 <xsl:value-of select="."/><br/>
			   	    </xsl:for-each>
		                     Подробно...</A>

		                    </td>
			        </tr>



			    </tbody>
			</table>
     		    </xsl:if>

		    <xsl:if test="left/icon = ''"> 
			<table width="295" border="0" cellSpacing="0" cellPadding="0"   height="100"   >
			    <tbody width="295" height="100"   >
			        <tr>
		        	    <td valign="center"   align="center"   width="100%" >
			     	   <A><xsl:attribute name="HREF"><xsl:value-of select="left/policy_url"/></xsl:attribute>
   				    <xsl:for-each select="left/description/r">
					 <xsl:value-of select="."/> <br/>
			   	    </xsl:for-each>
		                     Подробно...</A>
			            </td>

			        </tr>
			    </tbody>
			</table>
     		    </xsl:if>

		    </div>
		  </div>
		</div>
            </td>
	</xsl:if>

        </tr>
    </tbody>
</table>


   <!-- Кнопки удаления  , начало -->
	<xsl:if test="/document/admin/post_manager != ''">
	<div class="footer">
		<table>
		    <tbody>
		        <tr>
		         <td>
		                <form name="product_edit"  action="Productlist.jsp">Команды
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="product" ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="rigth/product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Изменить"></INPUT>
		                </form>

		         </td>
			  <td>  
		                <form name="product_del"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="rigth/product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Delete"></INPUT>
		                </form>
		         </td>

		         <td width="150"  >
		 	 </td>
		         <td>

				<xsl:if test="left/icon != ''"> 
		                <form name="product_edit"  action="Productlist.jsp">Команды
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="product" ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="left/product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Изменить"></INPUT>
		                </form>
		        	</xsl:if>

		         </td>
		 	 <td>
				<xsl:if test="left/icon != ''"> 
		                <form name="product_del"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="left/product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Delete"></INPUT>
		                </form>
		        	</xsl:if>
		         </td>

		        </tr>
		    </tbody>
		</table>
            </div>
	</xsl:if>
    <!-- Кнопки удаления  , конец  -->
    </xsl:for-each>
    <!-- Вывод каждого елемента из тега product , конец -->


	                <h1>
			<table >
			    <tbody>
		<TR><TD>

		<xsl:if test="document/search_value != ''"> 
		 <FONT size="2" color="#002a79">Страницы</FONT>
   	        </xsl:if>
		</TD>
		<xsl:if test="document/search_value != ''"> 
                <TD width="350" ></TD>
   	        </xsl:if>
                <TD>



		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="0" ></input><input  type="submit" size="1" value="1" /></form>
   	        </xsl:if>

		</TD><TD>
 		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="10" ></input><input  type="submit" size="1" value="2" /></form>
   	        </xsl:if>
		</TD><TD>
		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="20" ></input><input  type="submit" size="1" value="3" /></form>
   	        </xsl:if>
		</TD><TD>
		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="30" ></input><input  type="submit" size="1" value="4" /></form>
   	        </xsl:if>
		</TD><TD>
		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="40" ></input><input  type="submit" size="1" value="5" /></form>
   	        </xsl:if>
		</TD><TD>
		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="50" ></input><input  type="submit" size="1" value="6" /></form>
   	        </xsl:if>
		</TD><TD>
		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="60" ></input><input  type="submit" size="1" value="7" /></form>
   	        </xsl:if>
		</TD><TD>
 		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="70" ></input><input  type="submit" size="1" value="8" /></form>
   	        </xsl:if>

		</TD><TD>
		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="80" ></input><input  type="submit" size="1" value="9" /></form>
   	        </xsl:if>

<!--

		</TD><TD>
		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="90" ></input><input  type="submit" size="1" value="10" /></form>
   	        </xsl:if>
		</TD><TD>
		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="100" ></input><input  type="submit" size="1" value="11" /></form>
   	        </xsl:if>
		</TD><TD>
		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="110" ></input><input  type="submit" size="1" value="12" /></form>
   	        </xsl:if>
		</TD><TD>
		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="120" ></input><input  type="submit" size="1" value="13" /></form>
   	        </xsl:if>
		</TD><TD>
		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="130" ></input><input  type="submit" size="1" value="14" /></form>
   	        </xsl:if>
		</TD><TD>
		<xsl:if test="document/search_value != ''"> 
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="140" ></input><input  type="submit" size="1" value="15" /></form>
   	        </xsl:if>
-->
		</TD>
		</TR>
		    </tbody>
		</table>
              </h1>

		<xsl:if test="document/search_value = ''"> 
		<table>
  	        <tbody>
		<TR><TD>
		<form name="searchform"  action="Productlist.jsp" ><input name="offset" type="hidden" value="0" ></input><input  type="submit" size="1" value="01" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="10" ></input><input  type="submit" size="1" value="02" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="20" ></input><input  type="submit" size="1" value="03" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="30" ></input><input  type="submit" size="1" value="04" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="40" ></input><input  type="submit" size="1" value="05" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="50" ></input><input  type="submit" size="1" value="06" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="60" ></input><input  type="submit" size="1" value="07" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="70" ></input><input  type="submit" size="1" value="08" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="80" ></input><input  type="submit" size="1" value="09" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="90" ></input><input  type="submit" size="1" value="10" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="100" ></input><input  type="submit" size="1" value="11" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="110" ></input><input  type="submit" size="1" value="12" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="120" ></input><input  type="submit" size="1" value="13" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="130" ></input><input  type="submit" size="1" value="14" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="140" ></input><input  type="submit" size="1" value="15" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="150" ></input><input  type="submit" size="1" value="16" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="160" ></input><input  type="submit" size="1" value="17" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="170" ></input><input  type="submit" size="1" value="18" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="180" ></input><input  type="submit" size="1" value="19" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="190" ></input><input  type="submit" size="1" value="20" /></form>
<!--
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="200" ></input><input  type="submit" size="1" value="21" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="210" ></input><input  type="submit" size="1" value="22" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="220" ></input><input  type="submit" size="1" value="23" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="230" ></input><input  type="submit" size="1" value="24" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="240" ></input><input  type="submit" size="1" value="25" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="250" ></input><input  type="submit" size="1" value="26" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="260" ></input><input  type="submit" size="1" value="27" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="270" ></input><input  type="submit" size="1" value="28" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="280" ></input><input  type="submit" size="1" value="29" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="290" ></input><input  type="submit" size="1" value="30" /></form>

-->

		</TD></TR>
  	        </tbody>
		</table>

   	        </xsl:if>

            </td>

            <td class="right">
	  
              <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
              <TBODY>
              <TR>
                <TD vAlign="rigth" ><FONT color="white" ><B>Разделы </B></FONT>
               </TD>
              </TR>
  	     </TBODY>
  	   </TABLE>



        <xsl:for-each select="document/catalog/catalog-item">
	  <xsl:if test="item != ''">
	 <xsl:if test="code != '-1'">
	 <xsl:if test="code != '-2'">

	<div class="box">
	  <div class="body">
	    <div class="content even">
	     <A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="url"/></xsl:attribute>
	      <U>  <xsl:value-of select="item"/></U> 
	     </A>
	    </div>
	  </div>
	</div>

<!-- <FONT size="2" color="#002a79">  </FONT> -->
	 </xsl:if>
	 </xsl:if>
	 </xsl:if>
        </xsl:for-each>


<xsl:if test="document/empty_page_co2 = 'false'" > 
<div class="box"  >
    <div>


           <TABLE class="text"  height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
              <TBODY>
              <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Дополнительно</B></FONT>
               </TD>
              </TR>
  	     </TBODY>
  	   </TABLE>

           <TABLE height="9" cellSpacing="4" cellPadding="0" width="159"   background="index.files/bg34.gif">
              <TBODY>
              <TR><TD></TD></TR>
              </TBODY>
           </TABLE>                                                    

           <TABLE cellSpacing="0" cellPadding="0" width="159" align="center"  background="index.files/bgleft.gif" border="0" rightmargin="0"  leftmargin="0" topmargin="0">
           <TBODY>
            <!-- News Iten start -->
	    <xsl:for-each select="document/coproductlist2/coproduct2">
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

<br/>
   <!-- Кнопки удаления  , начало -->
	<xsl:if test="/document/admin/post_manager != ''">
		<table>
		    <tbody>
		        <tr>
		         <td>
		                <form name="product_del"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Удалить"></INPUT>
		                </form>
		         </td>
		         <td>

		                <form name="product_edit"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="co2" ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Изменить"></INPUT>
		                </form>
		         </td>
		        </tr>
		    </tbody>
		</table>


	</xsl:if>
   <!-- Кнопки удаления  , конец  -->


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
</xsl:if>

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
    CheckLoadStatus();
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
    Agent.Speak("]]> Privet, Starichek! Rad tebya videt <![CDATA[ ");

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