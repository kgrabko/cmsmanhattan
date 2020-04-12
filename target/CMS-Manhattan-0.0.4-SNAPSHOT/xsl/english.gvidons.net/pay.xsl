<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
<xsl:output method="xml" indent="yes"/>
<xsl:output encoding="ISO-8859-1"/>
<xsl:strip-space elements="*"/>


<xsl:template match="/">
<!--
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
-->

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"
      lang="en">

<head>
     <META http-equiv="Page-Enter" content="BlendTrans(Duration=0.5)" />
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
     <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1"/>

     <title>Ishop  - Grabko Konstantin </title>
     <style type="text/css" media="screen"> @import url(style2.css);</style>

<SCRIPT language="JavaScript">
<![CDATA[
<!--

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

<body>

<TABLE cellSpacing="0" cellPadding="0" width="100%"  border="1" rightmargin="0" leftmargin="0" topmargin="0" bordercolor="#ECEFF8" >
<TR>
<TD bgcolor="#ECEFF8"  ></TD>
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
           <a href="Productlist.jsp?catalog_id=-1" class="plain" ><font size="2"> News </font> </a>
           <a href="Productlist.jsp?catalog_id=-2" class="plain" ><font size="2"> Home page </font> </a>
           <A href="Authorization.jsp?Login=newuser" class="plain" > <font size="2">My Account</font></A>
           <a href="about.html" class="plain"> <font size="2">Contact Us</font></a>
        </div>

        <div class="personalBar">
            <a href="webmail/index.jsp">
            <img src="xsl/english.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
	     Login is <xsl:value-of select="document/login"/>@online-spb.com
	     </a>
        </div>

        <div class="pathBar">
            <span>
                <span>. </span>
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
                <TD vAlign="center" ><FONT color="white" ><B>Returning Customer</B></FONT>
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
    
                    
    
                    <strong>Login</strong> <br />



                     <INPUT  title="Customer login" tabindex="10001" SIZE="12" AUTOCOMPLETE="off" TYPE="TEXT" NAME="Login" >
			<xsl:attribute name="value">
				<xsl:value-of select="document/login"/>
			</xsl:attribute>		
        	     </INPUT>
    
                    <br />
    
                    <strong>Password</strong>
		    <br />
		    <INPUT title="Customer password " tabindex="10002"  SIZE="12" AUTOCOMPLETE="off" TYPE="PASSWORD" NAME="Passwd1" ></INPUT>
                    <br />
                    <br />
    
                    <input class="context searchButton"
                           type="submit" name="submit"
                           value="Log in" tabindex="10003" />
                </form>
            </div>
    
            <div class="content even"> 
                <a href="">
                   <img src="xsl/english.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
                   Password forgotten? Click here.
                </a>
            </div>
        </div>
    </div>



<div>
    <div class="portlet">
    <h5><strong>Help</strong></h5>
      <div class="body">
        <div class="portletContent odd">
<strong>1.</strong>By pressing the button "To Bank ", you get in a payment sluice of bank.  
        </div>
        <div class="portletContent even">
<strong>2.</strong>
After carrying out of payment in a bank sluice, 
You should press the button in bank " Will return to the Internet shop " and 
To expect the answer from bank in the expanded monitoring answers of bank (the form the Account) 
In our shop. At the positive answer from bank your order will receive the status 
It is paid.

       </div>
      </div>
    </div>
</div>






            </td>

            <td class="main">

            <!-- News part -->

	    <h1>Send data to bank</h1>
<br/>


<!--  <FORM ACTION="https://secure.assist.ru/shops/cardpayment.cfm" METHOD="POST">  -->
		<FORM ACTION="https://pgate.grabko.com/authorizationPay.do" METHOD="POST">  
		<div class="box">
		  <div class="body">
		    <div >
			<table >
			    <tbody>
			<!-- <xsl:value-of select="document/currency_paysys_id"/> -->
			<TR><TD align="CENTER"  colspan="5" >Total payment   - <xsl:value-of select="document/amount"/></TD><TD></TD><TD width="400" align="LEFT">
			<INPUT TYPE="HIDDEN" NAME="Shop_IDP" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/shop_paysys_id"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Order_IDP" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/account_history_id"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" SIZE="10" NAME="Subtotal_P" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/amount"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Delay" VALUE="0"></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Language" VALUE="0"></INPUT>
			<INPUT TYPE="HIDDEN" NAME="URL_RETURN_OK" VALUE="http://www.gvidons.net/Order.jsp"></INPUT>
			<INPUT TYPE="HIDDEN" NAME="URL_RETURN_NO" VALUE="http://www.gvidons.net/AccountHistory.jsp"></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Currency" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/currency_paysys_id"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Comment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/decsription"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="LastName" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/lastname"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="FirstName" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/firstname"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="MiddleName" VALUE=""></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Email" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/email"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Address" VALUE=""></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Phone" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/mphone"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Country" VALUE=""></INPUT>
			<INPUT TYPE="HIDDEN" NAME="State" VALUE=""></INPUT>
			<INPUT TYPE="HIDDEN" NAME="City" VALUE=""></INPUT>
			<INPUT TYPE="HIDDEN" NAME="Zip" VALUE=""></INPUT>
			<INPUT TYPE="HIDDEN" NAME="ChoosenCardType" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/type_creditcard"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="IsFrame" VALUE="1"></INPUT>
			<INPUT TYPE="HIDDEN" NAME="f_Email" VALUE="1"></INPUT>
			<INPUT TYPE="HIDDEN" NAME="CardPayment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/card_payment"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="WalletPayment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/wallet_payment"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="WebMoneyPayment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/webmoney_payment"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="RapidaPayment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/papida_payment"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="PayCashPayment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/paycash_payment"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="EPortPayment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/eport_payment"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="KreditPilotPayment" VALUE=""><xsl:attribute name="VALUE"><xsl:value-of select="document/kredit_pilotpayment"/></xsl:attribute></INPUT>
			<INPUT TYPE="HIDDEN" NAME="ListParameters" VALUE=""></INPUT>
     		      <!--	<INPUT TYPE="HIDDEN" NAME="DemoResult" VALUE="AS000" ID="Hidden1"></INPUT> -->
			
			</TD><TD  align="rigth" ><input type="submit" name="Submit" value="To Bank"></input></TD></TR>


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
		<strong>	
		back
		</strong>	
	        </a>
	    </span>
	</div>


            </td>

            <td class="right">

        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Do shoping</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/shoping_url"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/english.gvidons.net/img/user.gif" width="20"></IMG>Return to video catalog for shoping </A>
	    </div>
	  </div>
	</div>


        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Amount on account</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_account_history"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/english.gvidons.net/img/user.gif" width="20"></IMG>Your transuctions .<br/>Current status account in internet shop <br/> Total amount : <xsl:value-of select="document/balans"/> rubley .</A>
	    </div>
	  </div>
	</div>


        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Review your orders</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_order_hist"/></xsl:attribute><IMG border="0" name="Nindex_14_03" src="xsl/english.gvidons.net/img/user.gif" height="20"   width="20"></IMG>Order list</A>
	    </div>
	  </div>
	</div>


        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Last order</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_order"/></xsl:attribute><IMG border="0"  name="Nindex_14_03" src="xsl/english.gvidons.net/img/user.gif" height="20"  width="20"></IMG>Order</A>
	    </div>
	  </div>
	</div>


        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Transfer money to shop </B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" ><br/><IMG border="0" height="20" name="Nindex_14_03" src="xsl/english.gvidons.net/img/user.gif" width="20"></IMG> You can transfer money in internet shop from Yandex  Money Account . This is very security technology . <br/> Step 1 <br/> Create account in Yandex Money <br/> Step 2 <br/> Transfer money to your Yandex Money Account <br/>Step 3 <br/> Next , Do submit from your order (on us site) etc. <br/>
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_pay"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/english.gvidons.net/img/credit-cards.jpg" width="130"></IMG></A>
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

Product Media@Shop  - Internet shop. Copyright 2005 <A HREF="http://www.grabko.com">Grabko Business Solution Inc. </A> . All Rights Reserved.

<hr size="" class="netscape4" />

<strong class="netscape4">
for user netscape 
</strong>
 
</div>
</TD>
<TD  bgcolor="#ECEFF8" ></TD>
</TR>
</TABLE>


</body>
</html>
</xsl:template>
</xsl:stylesheet>