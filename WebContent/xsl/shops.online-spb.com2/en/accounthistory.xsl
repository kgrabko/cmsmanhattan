<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
<xsl:output method="html" indent="yes"/>
<xsl:output encoding="UTF-8"/>
<xsl:strip-space elements="*"/>

<xsl:template match="/">
<xsl:variable name="host" select="string(document/host)"/>
<xsl:variable name="role" select="document/role_id"/>
<xsl:variable name="page" select="number(document/offset)"/>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"  lang="en">

<head>
       <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"/>
     <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE"/>
     <meta name="robots content='index,follow'"/>
     <meta name="keywords" content="free internet shop, buy internet shop , develop internet shop, London , New York , Toronto"/>
     <meta name="description" content="Portal with ready internet shop solutions with support and development "/>
     <title>www.siteforyou.net</title>
     <LINK id="style2" rel="stylesheet" type="text/css"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/style2.css')"/></xsl:attribute></LINK> 
     <SCRIPT type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/js/common.js')"/></xsl:attribute></SCRIPT>
     <SCRIPT type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/js/caption.js')"/></xsl:attribute></SCRIPT>
     
     <link rel="stylesheet" type="text/css" media="screen"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/css/jquery.lightbox.css')"/></xsl:attribute></link>
     
     <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/js/jquery.js')"/></xsl:attribute></script>
	 <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/js/jquery.lightbox.js')"/></xsl:attribute></script>
	
</head>




<body>

<TABLE cellSpacing="0" cellPadding="0" width="100%" height="100%"  border="1" rightmargin="0" leftmargin="0" topmargin="0" bordercolor="#ECEFF8" >
<TR>
<TD bgcolor="#ECEFF8" ></TD>
<TD vAlign="top" Align="center" width="1030" style="border: 0px solid #ECEFF8" >


<a class="skipnav" href="#documentContent">Skip to content</a>

<div>

     

        <div class="top">

<TABLE cellSpacing="0" cellPadding="10" height="120" width="100%" class="bacgraundTop" border="0" rightmargin="0" leftmargin="0" topmargin="0">
  <TBODY>
  <TR>
    <TD vAlign="top" Align="left"  width="20%">
<!--      <img src="xsl/reservation.gvidons.net/img/logotip.gif" border="0" height="70" width="90"   /> -->
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
			
        <xsl:if test="/document/login != 'user'">
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
                <center>  <span> . </span> </center>
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
        
            <div class="content odd">
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
    
            <div class="content even"> 
                <a href="">
                   <img src="images/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
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
		Please enter number of your order to find to payment.
        </div>
        <div class="portletContent even">
		When you get grid structure of payments to scroll it.
		Use arrows to scroll it.
        </div>
      </div>
    </div>
</div>






            </td>

            <td class="main">

            <!-- News part -->

	    <h1>The list of your monetary operations</h1>

	    <div class="description"><img src="xsl/shops.online-spb.com/images/basket1.gif"/>Enter an account number<FORM name="order" action="Order.jsp" ><INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="TEXT" NAME="account_history_id"></INPUT><INPUT TYPE="submit" name="submit" value="Search"></INPUT></FORM></div>



		<div class="box">
		  <div class="body">
		    <div >




			<table >
			    <tbody>
<!--
                       <TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD ALIGN="RIGHT"><a><xsl:attribute name="HREF"><xsl:value-of select="document/prev"/></xsl:attribute><IMG height="23" alt="" src="xsl/reservation.gvidons.net/xsl/shops.online-spb.com/images/images/2uparrow.jpg.jpg" width="25" border="0"></IMG></a></TD></TR>
                       <TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD ALIGN="RIGHT" ><a><xsl:attribute name="HREF"><xsl:value-of select="document/next"/></xsl:attribute><IMG height="23" alt="" src="xsl/reservation.gvidons.net/xsl/shops.online-spb.com/images/images/2downarrow.jpg" width="25" border="0"></IMG></a></TD></TR> 

-->


                       <TR><TD width="50" >Account</TD><TD width="30" align="right"  >Sum</TD><TD width="50"></TD><TD width="200" >Date</TD><TD width="250" >The performance status</TD><TD></TD></TR>

			<xsl:for-each select="document/list/payment">
                                <TR>
                                <TD>N<xsl:value-of select="amount_id"/></TD>
                                <TD align="right" ><xsl:value-of select="add_amount"/></TD>
				<TD align="left"  ><xsl:value-of select="currency_add_lable"/></TD>
                                <TD><xsl:value-of select="sysdate"/></TD>

                                <!--  <TD><xsl:if test="complete = 't'"> <STRONG><FONT color="#000099">Executed</FONT></STRONG></xsl:if><xsl:if test="complete = 'f'"> <STRONG><FONT color="#000099">Expects performance</FONT></STRONG></xsl:if></TD>  -->
                                <TD>
				<xsl:if test="rezult_cd = '2'"> <STRONG><FONT color="#000099">Payment is not approved </FONT></STRONG></xsl:if>
				<xsl:if test="rezult_cd = '1'"> <STRONG><FONT color="#000099">Payment was approved </FONT></STRONG></xsl:if>
				<xsl:if test="rezult_cd = '3'"> <STRONG><FONT color="#000099">Payment is not approved and error</FONT></STRONG></xsl:if>
				<xsl:if test="rezult_cd = ''"> 
				<xsl:if test="complete = 't'"> <STRONG><FONT color="#000099">Executed</FONT></STRONG></xsl:if><xsl:if test="complete = 'f'"> <STRONG><FONT color="#000099">Expects performance</FONT></STRONG></xsl:if>
				</xsl:if>
				</TD>

                                <TD align="right"  ><FORM name="order" action="AccountHistoryDetal.jsp" ><INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="amount_id"><xsl:attribute name="value"><xsl:value-of select="amount_id"/></xsl:attribute></INPUT><INPUT TYPE="submit" name="submit" value="Detailed"></INPUT></FORM></TD>
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

<hr size="" class="netscape4" />

</TD>
<TD bgcolor="#ECEFF8" ></TD>
</TR>

<TR>
<TD bgcolor="#ECEFF8" ></TD>
<TD vAlign="bottom" Align="center"  style="border: 0px solid #ECEFF8" >

<div class="footer">
<font color="black">Internet shop . Copyright 2010 
		<A HREF="http://www.siteforyou.net"><font color="black">  FDIS Center Business Solutions Inc </font></A>.  All rights reserved
</font>
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