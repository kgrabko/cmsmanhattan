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

<TABLE cellSpacing="0" cellPadding="0" width="100%"  border="0" rightmargin="0" leftmargin="0" topmargin="0" bordercolor="#ECEFF8"  height="100%" >
<TR>

<TD bgcolor="#ECEFF8" style="border: 1px solid #ECEFF8"></TD>
<TD vAlign="top" Align="center" width="1030" style="border: 0px solid #ECEFF8" >


<a class="skipnav" href="#documentContent">Skip to content</a>

<div>

     

<div class="top">
	<TABLE cellSpacing="0" cellPadding="10"  height="120"  width="100%" class="bacgraundTop" border="0" rightmargin="0" leftmargin="0" topmargin="0"   >
	  <TBODY>
	  <TR>
	    <TD vAlign="top" Align="left"  width="20%">
	    </TD>
	    <TD vAlign="center" Align="right"  width="80%">
	           <form name="searchform"  action="Productlist.jsp" >
					<br />
	               <input id="search_value"  name="search_value" type="text"  size="20" alt="Search in goods name"   title="Search in goods name" tabindex="30001" ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input>
	               <input class="searchButton"  type="submit" size="20" value="Search"  tabindex="30002" />
	           </form>
		</TD>
	  </TR>
	  </TBODY>
	</TABLE>
	</div>

	<hr size="" class="netscape4" />

      <div class="tabs">
         <A href="Productlist.jsp?catalog_id=-2" class="plain"><font size="2">Main page</font></A>
      
       <A href="Productlist.jsp?catalog_id=-10" class="plain"><font size="2">The looked most through</font></A>
			
	
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

        
        <hr size="" class="netscape4" />
</div>
<table class="columns">
    <tbody>
        <tr>
            <td class="left">
 	    <div class="box">
  		  <div style="height:20px; TEXT-ALIGN: left"   class="bacgraundBoxTitle">
		         <TABLE height="20" cellSpacing="4" cellPadding="0" width="160px" >
		          <TBODY>
		            <TR>
		               <TD vAlign="center" ><FONT color="white" ><B>Input on a site</B></FONT></TD>
		            </TR>
		     	  </TBODY>
		  	  </TABLE>
	          </div>

        <div class="body">

            <div class="content odd">

                <form action="Authorization.jsp"  method="post">
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
    
                    <input 
                           type="submit" name="submit"
                           value="Enter" tabindex="10003" class="context searchButton"/>
                </form>
            </div>
    
            <div class="content  even"> 
                <a href="">
                   <img><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/linkTransparent.gif')"/></xsl:attribute></img>
                   To send the password by mail
                </a>
            </div>
        </div>
    </div>




<div>

	<div class="box">
    	<div style="height:20px; TEXT-ALIGN: left"   class="bacgraundBoxTitle">
          <TABLE height="20" cellSpacing="4" cellPadding="0" width="160px" >
              <TBODY>
              <TR>
                 <TD vAlign="center" ><FONT color="white" ><B>To send the password</B></FONT></TD>
              </TR>
  	     	  </TBODY>
  	   	  </TABLE>
        </div>    

	  <div class="body">
	    <div class="content even" align="left" >
		<IMG border="0" height="20" name="Nindex_14_03" src="images/user.gif" width="20"/>
		 If you have forgotten the password. You can receive it by e-mail, with which you are registered. 
	    </div>
    
            <div class="content even"> 
                <form action="/sendpassword"  method="post">
                     <INPUT  class="context" title="E-Mail"  SIZE="12" AUTOCOMPLETE="off" TYPE="TEXT" NAME="email" /><br/>
                     <input  SIZE="12"  type="submit" name="submit"  value="Send"  />
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
<br/>
	   <img><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/linkTransparent.gif')"/></xsl:attribute></img> <font size="3" > <b> Registration </b> </font>   <font size="2" > <xsl:value-of select="document/message"/></font>
<br/>
<br/>
<br/>
<center>
<table  cellSpacing="10" cellPadding="0" border="0"  >
    <tbody>
<TR><TD width="100"  >Login: *</TD> <TD> <input size="40"  AUTOCOMPLETE="off" TYPE="TEXT" name="Login"  value="" ><xsl:attribute name="value"><xsl:value-of select="document/login"/></xsl:attribute></input></TD></TR>
<TR><TD>First password: * </TD> <TD> <input size="40" name="Passwd1" type="password"  ></input></TD></TR>
<TR><TD>The second password: * </TD> <TD><input size="40" name="Passwd2" type="password" ></input></TD></TR>
<TR><TD>First name: *  </TD> <TD><input  size="40" AUTOCOMPLETE="off" TYPE="TEXT" name="FName"  value=""   ><xsl:attribute name="value"><xsl:value-of select="document/firstname"/></xsl:attribute></input></TD></TR>
<TR><TD>Last name: * </TD> <TD><input size="40" AUTOCOMPLETE="off" TYPE="TEXT"  name="LName"  value=""   ><xsl:attribute name="value"><xsl:value-of select="document/lastname"/></xsl:attribute></input></TD></TR>
<TR><TD>Company:  </TD> <TD><input size="40" AUTOCOMPLETE="off" TYPE="TEXT"  name="Company" value=""><xsl:attribute name="value"><xsl:value-of select="document/company"/></xsl:attribute></input></TD></TR>


<!--
                       <TR><TD>Country: *</TD><TD ><SELECT NAME = "country_id" id = "country_id"  onChange="doChangeCity('country_id', this.value)"  >
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

                       <TR><TD>City: *</TD><TD><SELECT NAME = "city_id" id = "city_id" >
		 	   <xsl:for-each select="document/city/city-item">
				<OPTION>
				<xsl:attribute name="value">
					<xsl:value-of select="code"/>
				</xsl:attribute>
                                <xsl:value-of select="item"/>
   	          		</OPTION>
			   </xsl:for-each>		
                       </SELECT></TD> </TR>

                    <TR><TD>Currency</TD><TD><input size="40" AUTOCOMPLETE="off" TYPE="hidden"  name="currency_id" value="1" ></input>$</TD> </TR>


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

<input size="40" AUTOCOMPLETE="off" TYPE="hidden"  name="currency_id" value="1" ></input>
<input size="40" AUTOCOMPLETE="off" TYPE="hidden"  name="country_id" value="-1" ></input>
<input size="40" AUTOCOMPLETE="off" TYPE="hidden"  name="city_id" value="-1" ></input>


<TR><TD>E-mail:*  </TD> <TD><input size="40" AUTOCOMPLETE="off" TYPE="TEXT"  name="EMail" value="" ><xsl:attribute name="value"><xsl:value-of select="document/email"/></xsl:attribute></input></TD></TR>
<TR><TD>Phone:  </TD> <TD><input size="40"  AUTOCOMPLETE="off" TYPE="TEXT"  name="Phone" value=""><xsl:attribute name="value"><xsl:value-of select="document/phone"/></xsl:attribute></input></TD></TR>
<TR><TD>Mobile phone:  </TD> <TD><input size="40"  AUTOCOMPLETE="off" TYPE="TEXT"  name="MPhone" value="" ><xsl:attribute name="value"><xsl:value-of select="document/mphone"/></xsl:attribute></input></TD></TR>
<TR><TD>Fax:  </TD> <TD><input size="40"  AUTOCOMPLETE="off" TYPE="TEXT" name="Fax" value="" ><xsl:attribute name="value"><xsl:value-of select="document/fax"/></xsl:attribute></input></TD></TR>
<TR><TD>ICQ:  </TD> <TD><input size="40"  AUTOCOMPLETE="off" TYPE="TEXT"  name="Icq" value=""  ><xsl:attribute name="value"><xsl:value-of select="document/icq"/></xsl:attribute></input></TD></TR>
<TR><TD>Dealer login:  </TD> <TD><input size="40"  AUTOCOMPLETE="off" TYPE="TEXT"  name="Website" value="" ><xsl:attribute name="value"><xsl:value-of select="document/website"/></xsl:attribute></input></TD></TR>
 <input size="40"  AUTOCOMPLETE="off" TYPE="hidden"  name="site_id" value="" ><xsl:attribute name="value"><xsl:value-of select="document/site/site-item/selected"/></xsl:attribute></input>
<TR><TD><br/><img width="95px" alt="Picture with number" src="/gennumberservlet"  /> </TD><TD><br/><input size="40"  AUTOCOMPLETE="off" TYPE="TEXT"  name="gen_number" value=""></input><br/>Enter number from a picture</TD></TR>
<TR><TD></TD> <TD></TD></TR>
<TR><TD></TD> <TD></TD></TR>
    </tbody>
</table>
</center>
<table>
    <tbody>
	<TR><TD width="180"  ></TD>  <TD><input type="submit" name="Submit" value="Apply"></input></TD><TD></TD> <TD><input type="reset" value="Clear"></input></TD><TD></TD> </TR>
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
		back
		</strong>	
	        </a>
	    </span>
	</div>


          </td>

          <td class="right">

         
     <div style="height:20px; TEXT-ALIGN: left"   class="bacgraundBoxTitle">
	       <TABLE height="20" cellSpacing="4" cellPadding="0" width="160px" >
	              <TBODY>
		              <TR>
		               <TD vAlign="center" ><FONT color="white" ><B>About registration</B></FONT></TD>
				       </TR>
	 	     	  </TBODY>
	   	  </TABLE>
	</div>    
	<div class="box">
	  <div class="body">
	    <div class="even" align="left" >
		<IMG border="0" height="20" name="Nindex_14_03" src="images/user.gif" width="20"/>
		 Registration is necessary for authorisation.
                 <br/>
                 <br/> " * " fields obligatory for filling are noted.
	    </div>
	  </div>
	</div>

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