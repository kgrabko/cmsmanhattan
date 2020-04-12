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
	    <div class="content even"> <img src="xsl/reservation.gvidons.net/img/logo5.jpg" border="0" ><font size="3"><b> Интернет магазин <xsl:value-of select="document/site_name"/></b></font></img></div>
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
           <a href="Productlist.jsp?catalog_id=-2" class="plain" ><font size="2"> Главная страница  </font> </a>
           <A href="Authorization.jsp?Login=newuser" class="plain" > <font size="2">Регистрация клиента </font></A>
           <a href="about.html" class="plain"><font size="2">  О компании  </font> </a>
        </div>

        <div class="personalBar">

            <a href="Authorization.jsp">
                <img src="xsl/reservation.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
		Login is <xsl:value-of select="document/login"/>@<xsl:value-of select="document/host"/>
            </a>
            
        </div>

        <div class="pathBar">
            <span>
             <!--   <span> <xsl:value-of select="document/contact_person"/>  </span> -->
               <CENTER>  <span> <font size="2" color="red"  >  ВНИМАНИЕ  !!! Система бронирования номеров в отелей ,  Это альфа версия для демонстрации движка </font> </span></CENTER>
            </span>

        </div>

        <div class="pathBar">
            <span>
             <!--   <span> <xsl:value-of select="document/contact_person"/>  </span> -->
               <CENTER>  <span> <font size="2" color="red"  >  ВНИМАНИЕ  !!! ориентировочная цена программы  (онлайн бронирования)  2 000 000 рублей  с доводкой под заказчика . </font> </span></CENTER>
            </span>

        </div>

        <hr size="" class="netscape4" />

    </div>



<table class="columns">

    <tbody>
        <tr>
            <td class="left">


  <xsl:if test="document/login = 'user'">   <!--  показывать если поиск -->

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
			<img src="xsl/reservation.gvidons.net/img/linkTransparent.gif" alt="Link icon" title="Link icon" height="11" width="6" border="0" />
                   Выслать пароль по электронной почте
                </a>
            </div>
        </div>
    </div>

</xsl:if>

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
<strong>1.</strong>  Без регистрации Вы не сможете оплатить заказ  (это нужно для проверки владельца пластиковой карты в целях защиты от мошенничества).
        </div>

        <div class="even">
<strong>2.</strong>После регистрации Вы можете просмотреть все Ваши заказы и отчеты от банка по заказам.
        </div>
        <div class="even">
<strong>3.</strong> Вы можете изменть данные в рамке "доставка", сформированные автоматически из формы регистрации. 
        </div>
        <div class="even">
<strong>4.</strong>   Чтобы оплатить заказ нажмите кнопку "оплатить заказ".<br/>  Либо нажмите -   <br/>"создать свою <br/> учетную запись",
и после регистрации вернитесь к сохраненному заказу.
        </div>

      </div>
  </div>
</div>






            </td>

            <td class="main">

            <!-- News part -->

	    <h1>Заказ №-<xsl:value-of select="document/order_id"/></h1>

Страница
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


	    <xsl:if test="document/message != ''"> 

	          <div align="left"  class="listingBar">
		        <a href="Authorization.jsp?Login=" title="Нажмите для регистрации" >
			<strong> <FONT  color="red">
			<xsl:value-of select="document/message"/> 
			</FONT>	</strong>
			</a>
 	  	  </div>

	    </xsl:if> 




    
	    <div class="description">Ваша корзина<img src="xsl/reservation.gvidons.net/img/basket1.gif"/></div>

	   <xsl:if test="document/quantity_product = '0'" > 
		Корзина пуста
   	   </xsl:if>

 	   <xsl:if test="document/quantity_product != '0'" > 

		<xsl:if test="document/empty_page = 'true'" > 
			На этой странице ничего нет.
   	        </xsl:if>

		<xsl:if test="document/empty_page != 'true'" > 

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

             </xsl:if>   <!-- наличие товаров в корзине на этой странице   -->

	                <h1>  <!-- прокрутка страниц  -->
			<table >
			    <tbody>
                             <TR><TD width="0" ></TD><TD width="30"  ></TD><TD width="50"></TD><TD width="0" ></TD><TD width="500" > <font size="2" > Количество номеров заказе  <xsl:value-of select="document/quantity_product"/>  пролистать по 10 позиций в заказе </font> </TD><TD>  <a><xsl:attribute name="HREF"><xsl:value-of select="document/prev"/></xsl:attribute><IMG height="15" alt="назад" title="назад" src="xsl/reservation.gvidons.net/2uparrow.jpg" width="15" border="0"><a><xsl:attribute name="HREF"><xsl:value-of select="document/next"/></xsl:attribute><IMG height="15" alt="следующие" title="следующие" src="xsl/reservation.gvidons.net/2downarrow.jpg" width="15" border="0"></IMG></a></IMG></a></TD></TR>
			    </tbody>
			</table>
                        </h1>
          </xsl:if>  <!-- наличие товаров в корзине  -->




	    <div class="description">Дополнительные расходы<img src="xsl/reservation.gvidons.net/img/basket1.gif"/></div>

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


<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="status"  />

</FORM>

<FORM name="order" action="Order.jsp" >


	<INPUT SIZE="40" AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="order_paystatus" ><xsl:attribute name="value"><xsl:value-of select="document/order_paystatus"/></xsl:attribute></INPUT>

	    <div class="description">Адрес доставки<img src="xsl/reservation.gvidons.net/img/basket1.gif"/></div>


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
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/shoping_url"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/reservation.gvidons.net/img/user.gif" width="20"></IMG>Вернуться в каталог товаров для продолжения покупок</A>
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
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_account_history"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/reservation.gvidons.net/img/user.gif" width="20"></IMG>Ваши денежные операции .<br/>Наличие ваших денежных средств в магазине <br/> <xsl:value-of select="document/balans"/> рублей .</A>
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
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_order_hist"/></xsl:attribute><IMG border="0" name="Nindex_14_03" src="xsl/reservation.gvidons.net/img/user.gif" height="20"   width="20"></IMG>Заказы</A>
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
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_order"/></xsl:attribute><IMG border="0"  name="Nindex_14_03" src="xsl/reservation.gvidons.net/img/user.gif" height="20"  width="20"></IMG>Заказ</A>
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
	    <div class="content even" align="left" ><br/><IMG border="0" height="20" name="Nindex_14_03" src="xsl/reservation.gvidons.net/img/user.gif" width="20"></IMG> Вы можете перевести денежные средства в магазин для оплаты заказа, используя кредитные карты или виртуальные кошельки, такие как Web money , Яндекс деньги , E-Port и другие  <br/><br/>
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_pay"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/reservation.gvidons.net/img/credit-cards.jpg" width="130"></IMG></A>
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


</body>
</html>
</xsl:template>
</xsl:stylesheet>