<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
<xsl:output method="html" indent="yes"/>
<xsl:output encoding="UTF-8"/>
<xsl:strip-space elements="*"/>


<xsl:template match="/">
	<xsl:variable name="host" select="string(document/host)"/>

<html>

<head>
     <title>GBS Portal</title>
     
     <LINK id="style2" rel="stylesheet" type="text/css"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/style2.css')"/></xsl:attribute></LINK> 
     <SCRIPT type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/common.js')"/></xsl:attribute></SCRIPT>
</head>

<body>

<TABLE cellSpacing="0" cellPadding="0" width="100%"  border="1" rightmargin="0" leftmargin="0" topmargin="0" bordercolor="#ECEFF8" >
<TR>
<TD bgcolor="#ECEFF8" ></TD>
<TD vAlign="top" Align="center" width="1030">


<a class="skipnav" href="#documentContent">Skip to content</a>

<div>

     

<div class="top">
	<TABLE cellSpacing="0" cellPadding="10"  height="120"  width="100%" class="bacgraundTop" border="0" rightmargin="0" leftmargin="0" topmargin="0">
	  <TBODY>
	  <TR>
	    <TD vAlign="top" Align="left"  width="20%">
	    </TD>
	    <TD vAlign="center" Align="right"  width="80%">
	           <form name="searchform"  action="Productlist.jsp" >
					<br />
	               <input id="search_value"  name="search_value" type="text"  size="20" alt="Поиск по имени товара"   title="Поиск по имени товара" tabindex="30001" ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input>
	               <input class="searchButton"  type="submit" size="20" value="Поиск"  tabindex="30002" />
	           </form>
		</TD>
	  </TR>
	  </TBODY>
	</TABLE>
	</div>

	<hr size="" class="netscape4" />

      <div class="tabs">
         <A href="Productlist.jsp?catalog_id=-2" class="plain"><font size="2">Главная страница</font></A>
      
       <A href="Productlist.jsp?catalog_id=-10" class="plain"><font size="2">Самые просматриваемые</font></A>
			<a href="Policy.jsp?page=about" class="plain"><font size="2"> О компании</font></a>
	
       <xsl:if test="document/login != 'user'">
       	<A href="Productlist.jsp?action=logoff" class="plain"><font size="2">Выход</font></A>        	
       </xsl:if>
      </div>
        
        
        <div class="personalBar">
            <a href="webmail/">
                <img src="images/email.png" alt="Boss Mail" title="Boss Mail" height="15" width="10" border="0" />
					логин: <xsl:value-of select="document/login"/>
            </a>
        </div>
        
        <hr size="" class="netscape4" />
</div>
<table class="columns">
    <tbody>
        <tr>
            <td class="left">


 

	  <!-- head for porlet login   --> 
          <TABLE style="height: 20px" cellSpacing="4" cellPadding="0" width="159" class="bacgraundBoxTitle" >
              <TBODY>
              <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Вход на сайт</B></FONT>
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
                     <INPUT  title="Пользователь" tabindex="10001" SIZE="16" AUTOCOMPLETE="off" TYPE="TEXT" NAME="Login" >
			<xsl:attribute name="value">
				<xsl:value-of select="document/login"/>
			</xsl:attribute>		
        	     </INPUT>
    
                    <br />
    
                    <strong>Пароль</strong>
		    <br />
		    <INPUT title="Пароль" tabindex="10002"  SIZE="16" AUTOCOMPLETE="off" TYPE="PASSWORD" NAME="Passwd1" ></INPUT>
                    <br />
                    <br />
    
                    <input 
                           type="submit" name="submit"
                           value="Войти" tabindex="10003" class="context searchButton"/>
                </form>
            </div>
    
            <div class="even"> 
                <a href="">
                   <img><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/linkTransparent.gif')"/></xsl:attribute></img>
                   Выслать пароль по почте
                </a>
            </div>
        </div>
    </div>




<div>
         <TABLE style="height: 20px" cellSpacing="4" cellPadding="0" width="159" class="bacgraundBoxTitle" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Прислать пароль</B></FONT>
            </TD>
            </TR>
           </TBODY>
         </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="even" align="left" >
		<IMG border="0" height="20" name="Nindex_14_03" src="images/user.gif" width="20"/>
		 Если вы забыли пароль, то можете получить его по электронной почте, указав свой email, с которым вы зарегистрированны. 
	    </div>
    
            <div class="even"> 
                <form action="/sendpassword"  method="post">
                     <INPUT  class="context" title="E-Mail"  SIZE="16" AUTOCOMPLETE="off" TYPE="TEXT" NAME="email" /><br/>
                     <input  SIZE="12"  type="submit" name="submit"  value="Послать"  />
               </form>
            </div>

	  </div>
	</div>

</div>






            </td>

            <td class="main">

            <!-- News part -->

<br/>
    
            <!-- News -->

            <!-- News Iten start -->
            <div class="box">

                <div class="body">
                    <div class="content">
        
                        <p>

<!-- <p><center> <b> <xsl:value-of select="document/message"/></b></center> </p> -->

<form method="post" ACTION="RegPage.jsp"  >
	   <h1> <font size="3" > <b> Регистрация </b> </font>   <font size="2" > <xsl:value-of select="document/message"/></font> </h1>
<br/>
<center>
<table  cellSpacing="10" cellPadding="0" border="0"  >
    <tbody>
<TR><TD width="100"  >Логин: *</TD> <TD> <input size="40"  AUTOCOMPLETE="off" TYPE="TEXT" name="Login"  value="" ><xsl:attribute name="value"><xsl:value-of select="document/login"/></xsl:attribute></input></TD></TR>
<TR><TD>Пароль: * </TD> <TD> <input size="40" name="Passwd1" type="password"  ></input></TD></TR>
<TR><TD>Повт. пароль: * </TD> <TD><input size="40" name="Passwd2" type="password" ></input></TD></TR>
<TR><TD>Имя: *  </TD> <TD><input  size="40" AUTOCOMPLETE="off" TYPE="TEXT" name="FName"  value=""   ><xsl:attribute name="value"><xsl:value-of select="document/firstname"/></xsl:attribute></input></TD></TR>
<TR><TD>Фамилия: * </TD> <TD><input size="40" AUTOCOMPLETE="off" TYPE="TEXT"  name="LName"  value=""   ><xsl:attribute name="value"><xsl:value-of select="document/lastname"/></xsl:attribute></input></TD></TR>
<TR><TD>Компания:  </TD> <TD><input size="40" AUTOCOMPLETE="off" TYPE="TEXT"  name="Company" value=""><xsl:attribute name="value"><xsl:value-of select="document/company"/></xsl:attribute></input></TD></TR>

                       <TR><TD>Страна: *</TD><TD ><SELECT NAME = "country_id" id = "country_id"  onChange="doChangeCity('country_id', this.value)"  >
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
                       </SELECT></TD></TR>

                       <TR><TD>Город: *</TD><TD><SELECT NAME = "city_id" id = "city_id" >
		 	   <xsl:for-each select="document/city/city-item">

				<OPTION>
				<xsl:attribute name="value">
					<xsl:value-of select="code"/>
				</xsl:attribute>
                                <xsl:value-of select="item"/>
   	          		</OPTION>
			   </xsl:for-each>		
                       </SELECT></TD> </TR>

                    <TR><TD>Валюта</TD><TD><input size="40" AUTOCOMPLETE="off" TYPE="hidden"  name="currency_id" value="3" ></input>рубли</TD> </TR>

<!--
                       <TR><TD>Currency</TD><TD><SELECT NAME = "currency_id">
		 	   <xsl:for-each select="document/currency/currency-item">
				<OPTION>
				<xsl:attribute name="value">
					<xsl:value-of select="code"/>
				</xsl:attribute>
                                <xsl:value-of select="item"/>
   	          		</OPTION>
			   </xsl:for-each>		
                       </SELECT></TD> </TR>
-->

<TR><TD>Эл.почта  :*  </TD> <TD><input size="40" AUTOCOMPLETE="off" TYPE="TEXT"  name="EMail" value="" ><xsl:attribute name="value"><xsl:value-of select="document/email"/></xsl:attribute></input></TD></TR>
<TR><TD>Телефон :  </TD> <TD><input size="40"  AUTOCOMPLETE="off" TYPE="TEXT"  name="Phone" value=""><xsl:attribute name="value"><xsl:value-of select="document/phone"/></xsl:attribute></input></TD></TR>
<TR><TD>Сотовый :  </TD> <TD><input size="40"  AUTOCOMPLETE="off" TYPE="TEXT"  name="MPhone" value="" ><xsl:attribute name="value"><xsl:value-of select="document/mphone"/></xsl:attribute></input></TD></TR>
<TR><TD>Факс    :  </TD> <TD><input size="40"  AUTOCOMPLETE="off" TYPE="TEXT" name="Fax" value="" ><xsl:attribute name="value"><xsl:value-of select="document/fax"/></xsl:attribute></input></TD></TR>
<TR><TD>Дилер логин  :  </TD> <TD><input size="40"  AUTOCOMPLETE="off" TYPE="TEXT"  name="Icq" value=""  ><xsl:attribute name="value"><xsl:value-of select="document/icq"/></xsl:attribute></input></TD></TR>
<TR><TD>Ваш саит (URL) :  </TD> <TD><input size="40"  AUTOCOMPLETE="off" TYPE="TEXT"  name="Website" value="" ><xsl:attribute name="value"><xsl:value-of select="document/website"/></xsl:attribute></input></TD></TR>
 <input size="40"  AUTOCOMPLETE="off" TYPE="hidden"  name="site_id" value="" ><xsl:attribute name="value"><xsl:value-of select="document/site/site-item/selected"/></xsl:attribute></input>
<TR><TD><br/><img width="95px" alt="Картинка с генерированым номером" src="/gennumberservlet"  /> </TD><TD><br/><input size="40"  AUTOCOMPLETE="off" TYPE="TEXT"  name="gen_number" value=""></input><br/>Введите номер с картинки здесь</TD></TR>
<TR><TD></TD> <TD></TD></TR>
<TR><TD></TD> <TD></TD></TR>
    </tbody>
</table>
</center>
<table>
    <tbody>
	<TR><TD width="180"  ></TD>  <TD><input type="submit" name="Submit" value="Применить"></input></TD><TD></TD> <TD><input type="reset" value="Сброс"></input></TD><TD></TD> </TR>
    </tbody>
</table>

</form>

			
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


         <TABLE style="height: 20px" cellSpacing="4" cellPadding="0" width="159" class="bacgraundBoxTitle" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>О регистрации</B></FONT>
            </TD>
            </TR>
           </TBODY>
         </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="even" align="left" >
		<IMG border="0" height="20" name="Nindex_14_03" src="images/user.gif" width="20"/>
		 Регистрация нужна для добавления ресурса.
                 <br/>
                 <br/> " * " отмечены поля обязательные для заполнения.
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

Internet-shop. Copyright 2010 <A HREF="http://www.siteoneclick.com">Center Business Solutions Inc</A>. Все права защищены.
<!-- Yandex.Metrika -->
<script src="//mc.yandex.ru/metrika/watch.js" type="text/javascript"></script>
<script type="text/javascript">
try { var yaCounter716349 = new Ya.Metrika(716349); } catch(e){}
</script>
<noscript><img src="//mc.yandex.ru/watch/716349" style="position:absolute" alt="" /></noscript>
<!-- /Yandex.Metrika -->

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