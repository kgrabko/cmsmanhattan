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
	<!--      <img src="xsl/holodilnik.gvidons.net/img/logotip.gif" border="0" height="70" width="90"   /> -->
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
           <a href="http://www.grabko.com"  class="plain"> <font size="2"> Аренда интернет магазинов </font></a>
           <a href="pogoda.html" class="plain"> <font size="2">О погоде </font></a>
           <a href="about.html" class="plain"> <font size="2">О компании  </font></a>
        </div>

        <div class="personalBar">
            
            <a href="Authorization.jsp">
                <img src="xsl/holodilnik.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
		Login is <xsl:value-of select="document/login"/>@<xsl:value-of select="document/host"/>
            </a>
            
        </div>

        <div class="pathBar">
            <span>
              <CENTER>  <span> </span></CENTER>
            </span>

        </div>

        <hr size="" class="netscape4" />

    </div>



<table class="columns">

    <tbody>
        <tr>
            <td class="left">

<xsl:if test="document/empty_page_ext2 = 'false'" > 
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
	    <xsl:for-each select="document/extpolicy_productlist2/extpolicy_product2">
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
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="ext2" ></INPUT>
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
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="news" ></INPUT>
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

        <!-- Показать кнопку для управления сайтом , начало  -->

        <xsl:if test="document/admin/post_manager != ''">
	<div class="footer">Нажмите эту кнопку, если вы  хотите  изменить содержимое сайта
	<A><xsl:attribute name="HREF"><xsl:value-of select="document/admin/post_manager"/>
	</xsl:attribute><img SRC="images/post.jpg" border="0" alt="Post" ></img></A>
        </div>
	</xsl:if>
        <!-- Показать кнопку для управления сайтом , конец  -->


            <!-- News part -->

	    <h1>Подробности</h1>


	<table height="550" >
	        <tr>
	            <td valign="top"   align="center"  >



            <!-- News Iten start -->
            <div class="box">

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
		<IMG height="470" alt=""  width="250" border="0"><xsl:attribute name="src"><xsl:value-of select="document/product/image"/></xsl:attribute></IMG> 
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
	        </tr>
	</table>

<!-- end strong size -->

            </td>

            <td class="right">

<xsl:if test="document/empty_page_ext1 = 'true'" > 

        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Вернуться</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/shoping_url"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/holodilnik.gvidons.net/img/user.gif" width="20"></IMG>Вернуться на главную страницу </A>
	    </div>
	  </div>
	</div>
<!--

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

-->
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

<!--
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
-->

</xsl:if>



<xsl:if test="document/empty_page_ext1 = 'false'" > 

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
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/shoping_url"/></xsl:attribute><IMG border="0" height="14" name="Nindex_14_03" src="xsl/holodilnik.gvidons.net/img/user.gif" width="15"></IMG>Продолжить покупки</A>
	    </div>
	  </div>
	</div>



<div class="box"  >
    <div>


           <TABLE class="text"  height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
              <TBODY>
              <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Отели</B></FONT>
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
	    <xsl:for-each select="document/extpolicy_productlist1/extpolicy_product1">
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
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="ext1" ></INPUT>
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


</body>
</html>
</xsl:template>
</xsl:stylesheet>