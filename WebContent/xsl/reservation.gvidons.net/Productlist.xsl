<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
<xsl:output method="html" indent="yes"/>
<xsl:output encoding="UTF-8"/>
<xsl:strip-space elements="*"/>

<xsl:template match="/">

<html >

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
<TD bgcolor="#ECEFF8" ></TD>
<TD vAlign="top" Align="center" width="1030">


<a class="skipnav" href="#documentContent">Skip to content</a>

<div>

        <div class="top">


<TABLE cellSpacing="0" cellPadding="10"  height="120"  width="100%" background="index.files/bg.jpg" border="0" rightmargin="0" leftmargin="0" topmargin="0">
  <TBODY>
  <TR>
    <TD vAlign="top" Align="left"  width="20%">
<!--      <img src="xsl/reservation.gvidons.net/img/logotip.gif" border="0" height="70" width="90"   /> -->
       <a  onclick="this.style.behavior='url(#default#homepage)'; this.setHomePage('http://10.6.0.106'); return false;" href="#" ><font color="white" ><b>Сделать стартовой</b></font></a> <br/>   <a href="javascript:window.external.AddFavorite('http://10.6.0.106','Media@Shop')" ><font color="white" > <b>Добавить в избранное</b></font> </a> <br/>
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
           <xsl:if test="/document/role_id = '1'">
	 	    <xsl:choose> 

                           <xsl:when test="/document/user_site_id = '-1'">
				       <a href="Productlist.jsp?action=create_site" class="plain" ><font size="2"> Подать обьявление </font> </a>
			   </xsl:when> 

			   <xsl:otherwise>
				<a href="Productlist.jsp?action=login_usersite" class="plain" ><font size="2"> Подать обьявление </font> </a>
                           </xsl:otherwise> 

                    </xsl:choose> 
           </xsl:if>

           <xsl:if test="/document/site_id != '2'">
		<a href="Productlist.jsp?action=logoff_usersite" class="plain" ><font size="2"> Выход из режима добавлений </font> </a>   
           </xsl:if>


           <xsl:if test="document/login != 'user'">   <!--  показывать если нет логина -->
 	          <a href="Productlist.jsp?action=logoff" class="plain" ><font size="2"> Выход </font> </a>
           </xsl:if>


           <a href="about.html" class="plain"> <font size="2">О компании  </font></a>
        </div>

        <div class="personalBar">
            
            <a href="Authorization.jsp">
                <img src="xsl/reservation.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
		Login is 

               <xsl:if test="document/login = 'user'">   <!--  показывать если нет логина -->
		 <xsl:value-of select="document/login"/>@<xsl:value-of select="document/host"/>
               </xsl:if>

               <xsl:if test="document/login != 'user'">   <!--  показывать если нет логина -->
		 <font color="red"><xsl:value-of select="document/login"/>@<xsl:value-of select="document/host"/></font>
               </xsl:if>

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




        <div class="pathBar">
            <span>
              <CENTER>
                <font size="2"  color="red"  >
                 Программа онлайн бронрования отелей - (для разрешения дисплея 1024x768)
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


  <xsl:if test="document/login = 'user'">   <!--  показывать если нет логина -->

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
                   <img src="xsl/reservation.gvidons.net/img/linkTransparent.gif" alt="Link icon" title="Link icon" height="11" width="6" border="0" />
                   Выслать пароль по электронной почте
                </a>
            </div>
        </div>
    </div>

</xsl:if>

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

  	   <INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="searchquery" VALUE="4"  ></INPUT>

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

 	    <table width="150"  >
	     <tr>
             <td> Страна: </td>
	     </tr>
	     <tr>
	     <td align="right" ><SELECT NAME = "creteria1_id" onChange="javascript:this.form.submit()" >
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


 	    <table width="150"  >
	     <tr>
             <td> Город: </td>
	     </tr>
	     <tr>

	     <td align="right" ><SELECT NAME = "creteria2_id" onChange="javascript:this.form.submit()" >
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

 	    <table width="150"  >
	     <tr>
             <td> Отели: </td>
	     </tr>
	     <tr>
             <td align="right" > <SELECT NAME = "creteria3_id" onChange="javascript:this.form.submit()"  >
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

 	    <table width="150"  >
	     <tr>
             <td> Звезды: </td>
	     </tr>
	     <tr>
             <td align="right" > <SELECT NAME = "creteria4_id" onChange="javascript:this.form.submit()"  >
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

            <!-- Creteria Iten start -->
                <TR>
                <TD width="5"></TD>
                <TD width="145">

 	    <table width="150"  ><tr><td> От аэропорта, в км: </td></tr>
	    <tr>
             <td align="right" >  <SELECT NAME = "creteria5_id" onChange="javascript:this.form.submit()"  >
		 	   <xsl:for-each select="document/creteria5/creteria5-item">


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

 	    <table width="150"  ><tr><td> Расположение: </td></tr>
	    <tr>
             <td align="right" >  <SELECT NAME = "creteria6_id"  onChange="javascript:this.form.submit()"  >
		 	   <xsl:for-each select="document/creteria6/creteria6-item">


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

 	    <table width="150"  >
	     <tr><td> Тип номера: </td></tr>
	     <tr>
             <td align="right" > <SELECT NAME = "catalog_id" onChange="javascript:this.form.submit()"  >

		   <OPTION value="0" >Нет</OPTION>

	 	   <xsl:for-each select="document/catalog/catalog-item">
			       <xsl:if test="code != '-1'">
			       <xsl:if test="code != '-2'">


				<OPTION>
				<xsl:attribute name="value">
					<xsl:value-of select="code"/>
				</xsl:attribute>

                               <xsl:if test="code = selected">
                               <xsl:attribute name="SELECTED">SELECTED</xsl:attribute>
                               </xsl:if>

                                 <xsl:value-of select="item"/>
   	          		</OPTION>

				</xsl:if>
				</xsl:if>
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
             <td > Дата: вьезда </td>
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
             <td width="50"  ></td>
             <td align="right" >  <SELECT NAME = "dayfrom_id"  >
		 	   <xsl:for-each select="document/dayfrom/dayfrom-item">


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
			<SELECT NAME = "mountfrom_id"  >
		 	   <xsl:for-each select="document/mountfrom/mountfrom-item">


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
			<SELECT NAME = "yearfrom_id"  >
		 	   <xsl:for-each select="document/yearfrom/yearfrom-item">


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
             <td > Дата: выезда </td>
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
             <td width="50"  ></td>
             <td align="right" >  <SELECT NAME = "dayto_id"  >
		 	   <xsl:for-each select="document/dayto/dayto-item">


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
			<SELECT NAME = "mountto_id"  >
		 	   <xsl:for-each select="document/mountto/mountto-item">


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
			<SELECT NAME = "yearto_id"  >
		 	   <xsl:for-each select="document/yearto/yearto-item">


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

         <xsl:variable name="user_id" select="number(/document/owner_user_id)"/> 

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
		<xsl:if test="document/offset = '80'">8</xsl:if>
		<xsl:if test="document/offset = '90'">10</xsl:if>
   	        </xsl:if>

		<xsl:if test="document/search_query != '0'">   <!-- показывать если поиск -->
                 Результаты поиска  страница №
 		<xsl:if test="document/offset = '0'">1</xsl:if>
		<xsl:if test="document/offset = '10'">2</xsl:if>
		<xsl:if test="document/offset = '20'">3</xsl:if>
		<xsl:if test="document/offset = '30'">4</xsl:if>
		<xsl:if test="document/offset = '40'">5</xsl:if>
		<xsl:if test="document/offset = '50'">6</xsl:if>
		<xsl:if test="document/offset = '60'">7</xsl:if>
		<xsl:if test="document/offset = '70'">8</xsl:if>
		<xsl:if test="document/offset = '80'">8</xsl:if>
		<xsl:if test="document/offset = '90'">10</xsl:if>


   	        </xsl:if>

		, найденных номеров  <!-- <xsl:value-of select="document/quantity_products"/> -->

	    </h1>

	<br/>

        <!-- Показать кнопку для управления сайтом , начало  -->

        <xsl:if test="document/role_id = '2'">
	<div class="footer">Нажмите эту кнопку, если вы  хотите  изменить содержимое сайта
	<A><xsl:attribute name="HREF"><xsl:value-of select="document/admin/post_manager"/>
	</xsl:attribute><img SRC="images/post.jpg" border="0" alt="Post" ></img></A>
        </div>
	</xsl:if>
        <!-- Показать кнопку для управления сайтом , конец  -->

                admin: <xsl:value-of select="/document/admin/post_manager"/>
		<xsl:if test="document/empty_page = 'true'" > 
			На этой странице ничего нет.
   	        </xsl:if>

	<table height="650" >
	        <tr>
	            <td valign="top"   align="center"  >



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
			<FONT color="white" ><B> userid: <xsl:value-of select="rigth/user_id"/> -
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
			<table width="295" border="1" cellSpacing="0" cellPadding="0"   height="100"   >
			    <tbody width="295" height="295"   >
			        <tr>
		        	    <td valign="center"   align="center"   width="100%" >
		        	        <A><xsl:attribute name="HREF"><xsl:value-of select="rigth/policy_url"/></xsl:attribute><IMG height="272" alt="Подробно"  width="295" border="0"><xsl:attribute name="src"><xsl:value-of select="rigth/icon"/></xsl:attribute></IMG></A>
					<br/> <font color="black" size="3"  ><strong> Цена : </strong> </font>
					<font color="red" size="3"  ><strong><xsl:value-of select="rigth/amount"/> рублей. </strong></font>
					<font color="red" size="3"  >   </font>
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
                <TD vAlign="center"  Align="center" ><FONT color="white" ><B> userid: <xsl:value-of select="left/user_id"/> -
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
			<table width="295" border="1" cellSpacing="0" cellPadding="0"   height="100"   >
			    <tbody width="295" height="295"   >
			        <tr>
		        	    <td valign="center"   align="center"   width="100%" >
		        	        <A><xsl:attribute name="HREF"><xsl:value-of select="left/policy_url"/></xsl:attribute><IMG height="272" alt="Подробно"  width="295" border="0"><xsl:attribute name="src"><xsl:value-of select="left/icon"/></xsl:attribute></IMG></A>

					<br/> <font color="black" size="3"  ><strong> Цена : </strong> </font>
					<font color="red" size="3"  ><strong><xsl:value-of select="left/amount"/> рублей. </strong></font>
					<font color="red" size="3"  >   </font>

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



  <xsl:choose> 

   <!-- Кнопки удаления  , начало -->
        <xsl:when test="/document/role_id = '2'">
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

			  <td>  
		                <form name="product_calendar"  action="calendar_list.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="rigth/product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Calendar"></INPUT>
		                </form>
		         </td>


		         <td width="10"  >
		 	 </td>
		         <td>

                                <xsl:if test="left/product_id != ''"> 
		                <form name="product_edit"  action="Productlist.jsp">Команды
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="product" ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="left/product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Изменить"></INPUT>
		                </form>
		        	</xsl:if>


		         </td>
		 	 <td>

                                <xsl:if test="left/product_id != ''"> 
		                <form name="product_del"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="left/product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Delete"></INPUT>
		                </form>
		        	</xsl:if>


		         </td>

			  <td>  

                                <xsl:if test="left/product_id != ''"> 
		                <form name="product_calendar"  action="calendar_list.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="left/product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Calendar"></INPUT>
		                </form>
		        	</xsl:if>
		         </td>


		        </tr>
		    </tbody>
		</table>
            </div>

    </xsl:when> 
    <!-- Кнопки удаления  , конец  -->




   <!--  for Edit  User context  Кнопки удаления  , начало -->
   <xsl:when test="/document/role_id = '1'">
	<div class="footer">
		<table>
		    <tbody>
		        <tr>

                           <xsl:if test="rigth/user_id = string(number($user_id))" >
			     
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

			  <td>  
		                <form name="product_calendar"  action="calendar_list.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="rigth/product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Calendar"></INPUT>
		                </form>
		         </td>

			</xsl:if>
                        <xsl:if test="rigth/user_id != string(number($user_id))" >
                        <td colspan =  "3" width="280"  ></td>
			</xsl:if>			     

		         <td width="10"  >
		 	 </td>


 		         <xsl:if test="left/user_id = string(number($user_id))" >

		         <td>
                                <xsl:if test="left/product_id != ''"> 
		                <form name="product_edit"  action="Productlist.jsp">Команды
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="product" ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="left/product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Изменить"></INPUT>
		                </form>
		        	</xsl:if>


		         </td>
		 	 <td>
                                <xsl:if test="left/product_id != ''"> 
		                <form name="product_del"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="left/product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Delete"></INPUT>
		                </form>
		        	</xsl:if>
		         </td>

			  <td>  
                                <xsl:if test="left/product_id != ''"> 
		                <form name="product_calendar"  action="calendar_list.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="left/product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Calendar"></INPUT>
		                </form>
		        	</xsl:if>
		         </td>

                          </xsl:if>


	                         <xsl:if test="left/user_id != string(number($user_id))" >
	                  <td colspan =  "3" width="280"   >
			  </td>
				</xsl:if>			     

		        </tr>
		    </tbody>
		</table>
            </div>
   </xsl:when> 

   </xsl:choose> 

    <!-- Кнопки удаления  , конец  -->




    </xsl:for-each>
    <!-- Вывод каждого елемента из тега product , конец -->


	            </td>
	        </tr>
	</table>


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

		<form name="searchform"  action="Productlist.jsp" ><input name="offset" type="hidden" value="0" ></input><input  type="submit" size="1" value="1" /></form>

		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input name="offset" type="hidden" value="10" ></input><input  type="submit" size="1" value="2" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="20" ></input><input  type="submit" size="1" value="3" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="30" ></input><input  type="submit" size="1" value="4" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="40" ></input><input  type="submit" size="1" value="5" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="50" ></input><input  type="submit" size="1" value="6" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="60" ></input><input  type="submit" size="1" value="7" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="70" ></input><input  type="submit" size="1" value="8" /></form>
		</TD><TD>
		<form name="searchform"  action="Productlist.jsp" ><input  name="search_char" type="hidden"  ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input><input name="offset" type="hidden" value="80" ></input><input  type="submit" size="1" value="9" /></form>

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
<!--		

		<xsl:if test="document/search_value = ''"> 
		<TD width="480" align="right">
		<FONT size="2" color="#002a79">Страницу</FONT></TD><TD><a><xsl:attribute name="HREF"><xsl:value-of select="document/prev"/></xsl:attribute><IMG height="15" alt="" src="xsl/reservation.gvidons.net/2uparrow.jpg" width="15" border="0"><a><xsl:attribute name="HREF"><xsl:value-of select="document/next"/></xsl:attribute><IMG height="15" alt="" src="xsl/reservation.gvidons.net/2downarrow.jpg" width="15" border="0"></IMG></a></IMG></a>
		</TD>
   	        </xsl:if>
-->

		</TR>
			    </tbody>
			</table>
                        </h1>




            </td>

            <td class="right">

              <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
              <TBODY>
              <TR>
                <TD vAlign="rigth" ><FONT color="white" ><B>Добавить в заказ  </B></FONT>
               </TD>
              </TR>
  	     </TBODY>
  	   </TABLE>

	<div class="box">
	  <div class="body">
	    <div class="content even">
	     добавить количество найденных номеров
	    <FORM  name="order" action="Order.jsp">
	    <INPUT AUTOCOMPLETE="off" size="15"  TYPE="text" NAME="count_positions"  ><xsl:attribute name="value"><xsl:value-of select="document/quantity_products"/></xsl:attribute></INPUT>
            <INPUT AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="addgroup" ></INPUT>
	    <br/>
            <INPUT AUTOCOMPLETE="off" TYPE="Submit" NAME="Submit" VALUE="Добавить" ></INPUT>
            </FORM>
	    </div>
	  </div>
	</div>




	  
              <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
              <TBODY>
              <TR>
                <TD vAlign="rigth" ><FONT color="white" ><B>Разделы </B></FONT>
               </TD>
              </TR>
  	     </TBODY>
  	   </TABLE>


           <xsl:if test="/document/role_id = '2'">

	<div class="box">
	  <div class="body">
	    <div class="content even">
	     <A class="menu" href="Productlist.jsp?catalog_id=-3"  >
	      <U>Входящие</U> 
	     </A>
	    </div>
	  </div>
	</div>

           </xsl:if>



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


</body>
</html>
</xsl:template>
</xsl:stylesheet>