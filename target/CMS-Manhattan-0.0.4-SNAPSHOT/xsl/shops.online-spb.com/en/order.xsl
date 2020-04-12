<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
<xsl:output method="html" indent="yes"/>
<xsl:output encoding="ISO-8859-1"/>
<xsl:strip-space elements="*"/>


<xsl:template match="/">
<xsl:variable name="host" select="string(document/host)"/>
<xsl:variable name="role" select="document/role_id"/>
<xsl:variable name="page" select="number(document/offset)"/>
<xsl:variable name="user_id" select="number(/document/owner_user_id)"/> 

<html>

<head>
     <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"/>
     <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE"/>
     <meta name="robots content='index,follow'"/>
     <meta name="keywords" content="free internet shop, buy internet shop , develop internet shop, London , New York , Toronto"/>
     <meta name="description" content="Portal with ready internet shop solutions with support and development "/>
     <title>www.siteforyou.net</title>

     <link rel="stylesheet" type="text/css"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/style2.css')"/></xsl:attribute></link> 
     <link rel="stylesheet" type="text/css" ><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/css/jquery.lightbox.css')"/></xsl:attribute></link>


     <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/js/jquery.js')"/></xsl:attribute></script>
     <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/js/jquery.lightbox.js')"/></xsl:attribute></script>
     <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/js/caption.js')"/></xsl:attribute></script>
	
</head>


<body>

<TABLE cellSpacing="0" cellPadding="0" width="100%" height="100%"  border="0" rightmargin="0" leftmargin="0" topmargin="0" bordercolor="#ECEFF8"  >
<TR>


<TD bgcolor="#ECEFF8" style="border: 1px solid #ECEFF8"></TD>
<TD vAlign="top" Align="center" width="1030"  style="border: 0px solid #ECEFF8" >


<a class="skipnav" href="#documentContent">Skip to content</a>

<div>

     

        <div class="top">


<TABLE cellSpacing="0" cellPadding="10" height="120" width="100%" class="bacgraundTop" border="0" rightmargin="0" leftmargin="0" topmargin="0">
  <TBODY>
  <TR>
    <TD vAlign="top" Align="left"  width="20%">
    </TD>
    <TD vAlign="center" Align="right"  width="80%">


              <form name="searchform"  action="Productlist.jsp" >
		<br />
                    <input id="search_value"  name="search_value" type="text"  size="20" alt="Searching video by name"   title="Searching video by name" tabindex="30001" ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input>
		    <input id="search_char"  name="search_char" type="hidden" ></input>
		    <input id="searchquery"  name="searchquery" type="hidden" ></input>
                    <input class="context searchButton"  type="button" size="20" value="Search"  tabindex="30002" onClick="return top.search_word();return true"   />
               </form>

</TD>
  </TR>
  </TBODY>
</TABLE>


        </div>

        <hr size="" class="netscape4" />

        <div class="tabs">
  		<A href="Productlist.jsp?catalog_id=-2" class="plain"><font size="2">Main page</font></A>
        
        <A href="Domain.jsp" class="plain"><font size="2">Registration of domains</font></A> 

	    <A href="webmail/" class="plain"><font size="2">Mail </font></A> 
			
        <xsl:if test="document/login != 'user'">
        	<A href="Productlist.jsp?action=logoff" class="plain"><font size="2">Exit</font></A>        	
        </xsl:if>
        </div>


       <div class="personalBar">
		<!-- 
	    <a href="Productlist.jsp?locale=ru">
                <img alt="Link icon" title="Russian" height="13" width="16" border="0">
                	<xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/flag_ru.gif')"/></xsl:attribute>
                </img>
            </a>
          -->   
	    <a href="Productlist.jsp?locale=en">
                <img alt="Link icon" title="English" height="13" width="16" border="0">
                	<xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/flag_en.gif')"/></xsl:attribute>
                </img>
            </a>
             <a href="webmail/">
                <img alt="Mail for boss" title="Boss Mail" height="16" width="16" border="0">
                	<xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/email.png')"/></xsl:attribute>
                </img>
				login: 

               <xsl:if test="document/login = 'user'">   <!--  показывать если нет логина -->
		 			<xsl:value-of select="document/login"/>
               </xsl:if>

               <xsl:if test="document/login != 'user'">   <!--  показывать если нет логина -->
		 			<font color="red"><xsl:value-of select="document/login"/></font>
               </xsl:if>
            </a>
        </div>


        <div class="pathBar">
            <span>
               <CENTER>  <span> <font size="2" color="red"  >   </font> </span></CENTER>
            </span>

        </div>


        <hr size="" class="netscape4" />

    </div>



<table class="columns">

    <tbody>
        <tr>
            <td class="left">

			<div>
			    <div class="portlet">
			    <h5><strong>Help</strong></h5>
			      <div class="body">
			        <div class="portletContent odd">
					Please enter your information about delivery
			       </div>
			        <div class="portletContent even">
					When you are finished it click button "Checkout".
					You will be forwarded to payment gateway.
			        </div>
			      </div>
			    </div>
			</div>
			
            </td>

            <td class="main">

            <!-- News part -->

	    <h1>Order №-<xsl:value-of select="document/order_id"/></h1>

	    <xsl:if test="document/message != ''"> 

	          <div align="left"  class="listingBar">
		        <a href="Authorization.jsp?Login=" title="Press for registration" >
			<strong> <FONT  color="red">
			<xsl:value-of select="document/message"/> 
			</FONT>	</strong>
			</a>
 	  	  </div>

	    </xsl:if> 




    
	    <div class="description">Your basket<img src="xsl/shops.online-spb.com/images/basket1.gif"/></div>




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
						<!--
 				      <xsl:if test="file_exist != ''"> 
                                         <A><xsl:attribute name="href"><xsl:value-of select="product_url"/></xsl:attribute> download </A>
				      </xsl:if>
 						 -->
			            </td>

			            <td align="right" valign="center" >
				        <FORM name="order_del" action="Order.jsp" ><INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT><INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="position"><xsl:attribute name="value"><xsl:value-of select="basket_id"/></xsl:attribute></INPUT><INPUT size="12" TYPE="submit" name="submit" value="Delete"></INPUT></FORM>
			   	    </td>
			        </tr>
			    </tbody>
			</table>
			</xsl:for-each>

		    </div>

		  </div>
		</div>

	    <div class="description">Total<img src="xsl/shops.online-spb.com/images/basket1.gif"/></div>

<FORM method="post" ACTION="Order.jsp"  id="addressForm" >
		<div class="box">
		  <div class="body">
		    <div >
			<table >
			    <tbody>
	
					<tr>
						<td bgColor="#EFEFEF" width="350" align="left" vAlign="top" >Name</td>
						<td bgColor="#EFEFEF" width="100" align="left" vAlign="top" >Amount</td>
						<td bgColor="#EFEFEF" width="50" align="left" vAlign="top" >Currency</td>
					</tr>


                                       <xsl:if test="document/admin/post_manager = ''"> 
					<tr>
						<td colspan="3"  width="500" align="left" vAlign="top" ><INPUT SIZE="40" AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="order_paystatus" VALUE="1"  ></INPUT></td>
					</tr>
					</xsl:if>


					<tr>
						<td width="350" align="left" vAlign="top" >Sum of the order  :</td>
						<td width="100" align="left" vAlign="top" ><STRONG><FONT color="#000099"> <xsl:value-of select="document/order_amount"/></FONT></STRONG></td>
						<td width="50" align="left" vAlign="top" ><xsl:value-of select="document/currency_lable"/></td>
					</tr>
					<tr>
						<td width="350" align="left" vAlign="top" >Sum for delivery :</td>
						<td width="100" align="left" vAlign="top" ><STRONG><FONT color="#000099"><xsl:value-of select="document/delivery_amoun"/></FONT></STRONG></td>
						<td width="50" align="left" vAlign="top" ><xsl:value-of select="document/currency_lable"/></td>

					</tr>
					<tr>
						<td width="350" align="left" vAlign="top" >Total : </td>
						<td width="100" align="left" vAlign="top" ><STRONG><FONT color="#000099"><xsl:value-of select="document/order_end_amount"/></FONT></STRONG></td>
						<td width="50" align="left" vAlign="top" ><xsl:value-of select="document/currency_lable"/></td>
					</tr>
					<xsl:if test="document/admin/post_manager = ''">
					<tr>
						<td width="350" align="left" vAlign="top" >Condition of payment:</td>
						<td width="100" align="left" vAlign="top" ><STRONG><FONT color="#000099"><xsl:value-of select="document/paystatus_lable"/></FONT></STRONG></td>
						<td width="50" align="left" vAlign="top" ></td>
					</tr>
					</xsl:if>


				<xsl:if test="document/admin/post_manager != ''">
					<tr>
						<td width="350" align="left" vAlign="top" >Change the status of payment : </td>
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

	    <div class="description">Customer address<img src="xsl/shops.online-spb.com/images/basket1.gif"/></div>


		<div class="box">
		  <div class="body">
		    <div >
			<table >
			    <tbody>
					<tr>
						<td width="50">Country</td>
						<td width="150" align="left" vAlign="top" >
						<SELECT NAME = "country_id" onChange="doChangeCity('country_id', this.value)"  id="country_id">
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
						<td width="50">City</td>
						<td width="150" align="left" vAlign="top" ><SELECT NAME = "city_id" id="city_id">
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
						<td width="150">Address</td>
						<td width="150" align="left" vAlign="top" ><INPUT SIZE="40"  AUTOCOMPLETE="off" TYPE="TEXT" NAME="shipment_address"  ><xsl:attribute name="value"><xsl:value-of select="document/shipment_address"/></xsl:attribute></INPUT></td>
					</tr>
					<tr>
						<td width="150">Phone</td>
						<td width="150" align="left" vAlign="top" ><INPUT SIZE="40" AUTOCOMPLETE="off" TYPE="TEXT" NAME="shipment_phone"  ><xsl:attribute name="value"><xsl:value-of select="document/shipment_phone"/></xsl:attribute></INPUT></td>
					</tr>
					<tr>
						<td width="150">Contact person</td>
						<td width="150" align="left" vAlign="top" ><INPUT SIZE="40"  AUTOCOMPLETE="off" TYPE="TEXT" NAME="contact_person"  ><xsl:attribute name="value"><xsl:value-of select="document/contact_person"/></xsl:attribute></INPUT></td>
					</tr>
					<tr>
						<td width="150">E-Mail (to send the invoice)</td>
						<td width="150" align="left" vAlign="top" ><INPUT SIZE="40" AUTOCOMPLETE="off" TYPE="TEXT" NAME="shipment_email"  ><xsl:attribute name="value"><xsl:value-of select="document/shipment_email"/></xsl:attribute></INPUT></td>
					</tr>
					<tr>
						<td width="150">Fax</td>
						<td width="150" align="left" vAlign="top" ><INPUT SIZE="40" AUTOCOMPLETE="off" TYPE="TEXT" NAME="shipment_fax"  ><xsl:attribute name="value"><xsl:value-of select="document/shipment_fax"/></xsl:attribute></INPUT></td>
					</tr>


					<tr>
						<td width="150">Additional information for the manager</td>
	  				        <td width="150" align="left" vAlign="top" >
							<textarea rows="5" cols="40"   NAME="shipment_description"><xsl:value-of select="document/shipment_description"/></textarea>	  				        </td>
					</tr>


					<tr>
						<td width="150"><INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="save"  ></INPUT></td>
						<td width="150" align="left" vAlign="top" >
						<xsl:if test="document/role_id != 0">
<input class="button" type="button" name="Submit" value="Checkout" onClick="isAddressValid()" ></input>
</xsl:if>  
<xsl:if test="document/role_id = 0">
<input class="button" type="button" name="button" value="Checkout" onClick="if( confirm('For order creation it is necessary to be authorised') == true  ) parent.location='Authorization.jsp?Login=';" ></input>
</xsl:if>  

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
		<strong> back </strong>	
	        </a>
	    </span>
	</div>


            </td>

            <td class="right">

 <xsl:choose> 
	     <xsl:when test="/document/role_id != '0'">
	     
	     
	      <div class="box"  >
	       <div style="height:21px;width:159px; TEXT-ALIGN: left" class="bacgraundBoxTitle">
	           <TABLE class="text"   width="100%"   height="20" cellSpacing="4" cellPadding="0" >
	              <TBODY>
	              <TR>
	                <TD vAlign="center" ><FONT color="white" ><B>My office</B></FONT>
	               </TD>
	              </TR>
	  	     </TBODY>
	  	   </TABLE>
			</div>
	                                                               

	           <TABLE cellSpacing="0" cellPadding="0" width="100%" align="center"  class="bacgraundBoxLeft" border="0" rightmargin="0"  leftmargin="0" topmargin="0">
	           <TBODY>
					<TR><TD width="5"></TD><TD><br/></TD></TR>
	              <TR><TD width="5"></TD><TD><A class="menu"  href="Productlist.jsp?action=login_usersite" alt="My portal or the Internet shop"  > My shop</A></TD></TR>

	              <TR><TD width="159" colspan="2"  class="bacgraundLine"  height="10" ></TD></TR>
	     	 
				 <TR><TD width="5"></TD><TD>
				 		<A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="document/shoping_url"/></xsl:attribute>Return to navigator </A>
				 </TD></TR>

		 		 <TR><TD width="159" colspan="2"  class="bacgraundLine"  height="10" ></TD></TR>
			 
				 <TR><TD width="5"></TD><TD>
			 		<A class="menu" ><xsl:attribute name="HREF"><xsl:value-of select="document/to_account_history"/></xsl:attribute>Your balans <xsl:value-of select="document/balans"/> $ .</A>
				 </TD></TR>

				 <TR><TD width="159" colspan="2"  class="bacgraundLine"  height="10" ></TD></TR>
			 
				 <TR><TD width="5"></TD><TD>
				 		<A class="menu" ><xsl:attribute name="HREF"><xsl:value-of select="document/to_order_hist"/></xsl:attribute>Order list</A>
				 </TD></TR>
				 
				 <TR><TD width="159" colspan="2"  class="bacgraundLine"  height="10" ></TD></TR>
			 
				 <TR><TD width="5"></TD><TD>
				    <A class="menu" ><xsl:attribute name="HREF"><xsl:value-of select="document/to_order"/></xsl:attribute>Order</A>
				 </TD></TR>


	              <TR><TD width="155" class="bacgraundLine2" colSpan="2" height="11" ></TD></TR>

	             </TBODY>
	             </TABLE>
	       
	     </div>
	    
	    

	     </xsl:when>
	   </xsl:choose> 


            </td>
        </tr>
    </tbody>
</table>



</TD>
<TD bgcolor="#ECEFF8" style="border: 1px solid #ECEFF8"></TD>
</TR>

<TR>
<TD bgcolor="#ECEFF8" style="border: 1px solid #ECEFF8"></TD>


<TD colspan="1" width="1030" vAlign="bottom" Align="center" cellSpacing="0" cellPadding="10"  >
<div class="footer" style="height: 60px"  >
<font color="black">Internet shop . Copyright 2010 <A HREF="http://www.siteforyou.net"><font color="black">  FDIS Center Business Solutions Inc </font></A>.  All rights reserved
</font>
</div>
</TD>

<TD bgcolor="#ECEFF8" style="border: 1px solid #ECEFF8"></TD>

</TR>



</TABLE>




</body>
</html>
</xsl:template>
</xsl:stylesheet>