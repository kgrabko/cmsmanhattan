<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
<xsl:output method="html" indent="yes"/>
<xsl:output encoding="UTF-8"/>
<xsl:strip-space elements="*"/>


<xsl:template match="/">
<xsl:variable name="host" select="string(document/host)"/>
<xsl:variable name="role" select="document/role_id"/>
<xsl:variable name="page" select="number(document/offset)"/>

<html>

<head>
       <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"/>
     <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE"/>
     <meta name="robots content='index,follow'"/>
     <meta name="keywords" content="free internet shop, buy internet shop , develop internet shop, London , New York , Toronto"/>
     <meta name="description" content="Portal with ready internet shop solutions with support and development "/>
     <title>www.siteforyou.net</title>
     <link rel="stylesheet" type="text/css" ><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/style2.css')"/></xsl:attribute></link> 
     <link rel="stylesheet" type="text/css" ><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/calendar.css')"/></xsl:attribute></link>	
   
     <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/js/jquery_v2009.js')"/></xsl:attribute></script>
     <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/js/datepicker.js')"/></xsl:attribute></script>
     <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/js/caption.js')"/></xsl:attribute></script>
     
	<script type="text/javascript">
	$(function() {
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '<xsl:value-of select="concat('xsl/',$host,'/images/calendar.png')"/>', buttonImageOnly: true,
		 	onSelect: function(){ 
		 		var inp_date = $("#datepicker1").datepicker("getDate");			 		
		 		document.getElementById('datefrom').value = inp_date.valueOf();	
		 				 		
		 	}
		});
	});
	
	$(function() {
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '<xsl:value-of select="concat('xsl/',$host,'/images/calendar.png')"/>', buttonImageOnly: true,
			onSelect: function(){ 
		 		var inp_date = $("#datepicker2").datepicker("getDate");
		 		document.getElementById('dateto').value = inp_date.valueOf();			 		
		 	}
		});
	});
	</script>
</head>


<body>

<TABLE cellSpacing="0" cellPadding="0" width="100%" height="100%"  border="0" rightmargin="0" leftmargin="0" topmargin="0" bordercolor="#ECEFF8"  >
<TR>
<TD bgcolor="#ECEFF8" style="border: 1px solid #ECEFF8"></TD>

<TD vAlign="top" Align="center" width="1030" style="border: 0px solid #ECEFF8" >


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
                    <input id="search_value"  name="search_value" type="text"  size="20" alt="Search in goods name"   title="Search in goods name" tabindex="30001" ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input>
                    <input class="context searchButton"  type="submit" size="20" value="Search"  tabindex="30002" />
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
               <center> <span> .  </span> </center>
            </span>

        </div>

        <hr size="" class="netscape4" />

    </div>



<table class="columns">

    <tbody>
        <tr>
            <td class="left">

  <xsl:if test="document/login = 'user'">   <!--  To show, if search -->

	  <!-- head for porlet login   --> 
          <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
              <TBODY>
              <TR>
                <TD vAlign="center" ><FONT color="white" ><B>For buyers</B></FONT>
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
    
                    
    
                    <strong>User</strong> <br />



                     <INPUT  title="User" tabindex="10001" SIZE="12" AUTOCOMPLETE="off" TYPE="TEXT" NAME="Login" >
			<xsl:attribute name="value">
				<xsl:value-of select="document/login"/>
			</xsl:attribute>		
        	     </INPUT>
    
                    <br />
    
                    <strong>Password</strong>
		    <br />
		    <INPUT title="Password" tabindex="10002"  SIZE="12" AUTOCOMPLETE="off" TYPE="PASSWORD" NAME="Passwd1" ></INPUT>
                    <br />
                    <br />
    
                    <input class="context searchButton"
                           type="submit" name="submit"
                           value="Log in" tabindex="10003" />
                </form>
            </div>
    
            <div class="even"> 
                <a href="">
			<img src="images/linkTransparent.gif" alt="Link icon" title="Link icon" height="11" width="6" border="0" />
                   To send the password by mail

                </a>
            </div>
        </div>
    </div>


</xsl:if>


<div>
    <div class="portlet">
    <h5><strong>Help</strong></h5>
      <div class="body">
        <div class="portletContent odd">
		Please enter number of your order to find your order.
       </div>
        <div class="portletContent even">
		When you get grid structure of orders to scroll it.
		Use arrows to scroll it.
        </div>
      </div>
    </div>
</div>

 <br />
 <br />


            </td>

            <td class="main">

            <!-- News part -->

	    <h1>The list of all orders</h1>

    
	    <div class="description"><img src="xsl/shops.online-spb.com/images/basket1.gif"/>Enter order number <FORM name="order" action="Order.jsp" ><INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="TEXT" NAME="order_id"><xsl:attribute name="value"><xsl:value-of select="document/list/order/order_id"/></xsl:attribute></INPUT><INPUT class="context searchButton"  TYPE="submit" name="submit" value="Find"></INPUT></FORM></div>


		<div class="box">
		  <div class="body">
		    <div >

			<table border="0" >
			    <tbody>

	                       <TR><TD>Order</TD><TD width="30" ></TD> <TD>Sum</TD><TD width="30" ></TD> <TD>Date</TD><TD width="30" ></TD> <TD>Status</TD><TD width="80" ></TD> <TD></TD></TR>
					<xsl:for-each select="document/list/order">
	                                <TR>
	                                <TD>N<xsl:value-of select="order_id"/></TD>
	                                <TD width="30" ></TD>
	                                <TD><xsl:value-of select="end_amount"/>/<xsl:value-of select="currency_lable"/></TD>
	                                <TD width="30" ></TD>
	                                <TD><xsl:value-of select="cdate"/></TD>
	                                <TD width="30" ></TD>
					<TD>[<xsl:value-of select="paystatus_lable"/>]</TD>
	                                <TD width="80" ></TD>
	                                <TD align="right" width="30%"   ><FORM name="order" action="Order.jsp" ><INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="order_id"><xsl:attribute name="value"><xsl:value-of select="order_id"/></xsl:attribute></INPUT><INPUT TYPE="submit" name="submit" value="Detailed"></INPUT></FORM></TD>
	                                </TR>
				</xsl:for-each>
			    </tbody>
			</table>

		    </div>

		  </div>

	                <h1>
			<table >
			    <tbody>
                             <TR><TD width="50" ></TD><TD width="30"  ></TD><TD width="50"></TD><TD width="200" ></TD><TD width="250" ></TD><TD>
                             <!--  
                             <a><xsl:attribute name="HREF"><xsl:value-of select="document/prev"/></xsl:attribute><IMG height="15" alt="" src="xsl/shops.online-spb.com/images/2uparrow.jpg" width="15" border="0">
                             <a><xsl:attribute name="HREF"><xsl:value-of select="document/next"/></xsl:attribute><IMG height="15" alt="" src="xsl/shops.online-spb.com/images/2downarrow.jpg" width="15" border="0"></IMG></a></IMG></a>
                             -->
                             </TD></TR>
			    </tbody>
			</table>
                        </h1>




		</div>


        <!-- Navigation -->
        <div class="listingBar">
	    <span class="next">
                <a HREF = "#" onClick="javascript:history.back()"  >
		<strong>	
		Back
		</strong>	
	        </a>
	    </span>
	</div>


            </td>

            <td class="right">


<div class="box"  >
  
     <div style="height:21px;width:159px; TEXT-ALIGN: left" class="bacgraundBoxTitle">
	           <TABLE class="text"   width="100%"   height="20" cellSpacing="4" cellPadding="0" >
	              <TBODY>
	              <TR>
	                <TD vAlign="center" ><FONT color="white" ><B>Search orders</B></FONT>
	               </TD>
	              </TR>
	  	     </TBODY>
	  	   </TABLE>
	  </div>
			
      <div class="body">
 		        <FORM name="searchcreform"  action="OrderList.jsp"  method="POST">				
						  	   							
									           <TABLE cellSpacing="0" cellPadding="0" width="100%" align="center"  class="bacgraundBoxLeft" border="0" rightmargin="0"  leftmargin="0" topmargin="0">
												        
													        <TBODY>
													        <TR>
													          <TD align="middle">
													            <TABLE class="pollstableborder_s3" cellSpacing="0" cellPadding="0" border="0">
													            <TBODY>
													              <TR>
													                 <TD class="width sectiontableentry2" vAlign="top">
													                	<LABEL for="voteid25">from date: </LABEL>
													                </TD>
													              </TR>
													              <INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="searchquery" value="2"></INPUT>
													              <TR>
													                 <TD class="width sectiontableentry2" vAlign="top">
													                 	<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="datefrom" id="datefrom"></INPUT>
													                	<INPUT id="datepicker1" size="5" readonly="true"  type="text" style="width:127px; color: black"></INPUT>
													                	
													                </TD>
													              </TR>
													              
													              <TR>
													                 <TD class="width sectiontableentry2" vAlign="top">
													                	<LABEL for="voteid25">before date: </LABEL>
													                </TD>
													              </TR>
													              <TR>
													                 <TD class="width sectiontableentry2" vAlign="top">
													                 	<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" id="dateto" NAME="dateto"></INPUT>
													                	<INPUT id="datepicker2" size="5"  readonly="true" type="text" style="width:127px; color: black"></INPUT>
													                </TD>
													              </TR>
													              										           												                                                         												           																		            																		            																            															              																											      
													      </TBODY>
													      </TABLE>
													      </TD>
													      </TR>
													       	 <TR>
													          	<TD>
													            <DIV style="padding-left:20px; padding-top: 10px">
																	<INPUT class="button"  type="submit" size="20" value="Search"  tabindex="30002"   />
													            </DIV>
													            </TD>
													            </TR>
													            <TR>
													          	<TD>
													            <DIV style="padding-left:20px; padding-top: 10px">
																	
													            </DIV>
													            </TD>
													            </TR>
													         </TBODY>
														</TABLE>
					</FORM>           
					
	       </div>
    </div>

 <br />
 <br />

<div class="box"  >
     <div style="height:21px;width:159px; TEXT-ALIGN: left" class="bacgraundBoxTitle">
	           <TABLE class="text"   width="100%"   height="20" cellSpacing="4" cellPadding="0" >
	              <TBODY>
	              <TR>
	                <TD vAlign="center" ><FONT color="white" ><B>Search order by status</B></FONT>
	               </TD>
	              </TR>
	  	     </TBODY>
	  	   </TABLE>
	  </div>
      <div class="body">
        <FORM name="order" action="OrderList.jsp" method="POST" >	 						
						    <INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="searchquery" value="3"></INPUT>	   							
			 	           <TABLE cellSpacing="0" cellPadding="0" width="100%" align="center"  class="bacgraundBoxLeft" border="0" rightmargin="0"  leftmargin="0" topmargin="0">
					        <TBODY>
					        <TR>
					          <TD align="middle">
					            <TABLE class="pollstableborder" cellSpacing="0" cellPadding="0" border="0">
					            	<TBODY>            	 
					             					<TR>
					          						<TD style="FONT-WEIGHT: bold">the payment status</TD>
					        						</TR>
													<tr>
														<td  align="left" vAlign="top" >
														<SELECT NAME = "order_paystatus"  style="width: 130px">
												  	  	 <xsl:for-each select="document/paystatus/paystatus-item">
														 <OPTION>
															<xsl:attribute name="value"><xsl:value-of select="code"/></xsl:attribute>
							                           		 			<xsl:if test="code = selected"><xsl:attribute name="SELECTED">SELECTED</xsl:attribute></xsl:if>
                             												<xsl:value-of select="item"/>
									   	          			 </OPTION>
													  	 </xsl:for-each>		
						                       			</SELECT></td>
													</tr>
													<TR>
					          						<TD style="FONT-WEIGHT: bold">the order status</TD>
					        						</TR>	
													<tr>
														<td  align="left" vAlign="top" >
														<SELECT NAME = "order_status"  style="width: 130px">
												  	  	 <xsl:for-each select="document/deliverystatus/deliverystatus-item">
														 <OPTION>
															<xsl:attribute name="value"><xsl:value-of select="code"/></xsl:attribute>
							                           		 			<xsl:if test="code = selected"><xsl:attribute name="SELECTED">SELECTED</xsl:attribute></xsl:if>
                             												<xsl:value-of select="item"/>
									   	          			 </OPTION>
													  	 </xsl:for-each>		
						                       			</SELECT></td>
													</tr>
					              										           												                                                         												           																		            																		            																            															              																											      
					      </TBODY></TABLE></TD></TR>
					        <TR>
					          <TD class="find">
					            <DIV style="padding-left:30px; padding-top: 10px">
									<INPUT class="button"  type="submit" size="20" value="Search"  tabindex="30002"   />
					            </DIV></TD></TR>
					            <TR>
													          	<TD>
													            <DIV style="padding-left:20px; padding-top: 10px">
																	
													            </DIV>
													            </TD>
													            </TR>
					            </TBODY>
							</TABLE>
						  </FORM>
      </div>
    </div>

    <br />

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