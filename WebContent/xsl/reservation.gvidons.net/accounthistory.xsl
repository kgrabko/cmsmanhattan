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
                    <input id="search_value"  name="search_value" type="text"  size="20" alt="����� �� ����� ������"   title="����� �� ����� ������" tabindex="30001" ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input>
                    <input class="context searchButton"  type="submit" size="20" value="�����"  tabindex="30002" />
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
                           type="submit" size="20" value="�����"
                           tabindex="30002" />
                </form>
            </div>
<br />
<br />

	<div class="box">
	  <div class="body">
	    <div class="content even"> <img src="xsl/reservation.gvidons.net/img/logo5.jpg" border="0" ><font size="3"><b> �������� ������� <xsl:value-of select="document/site_name"/></b></font></img></div>
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
           <a href="Productlist.jsp?catalog_id=-1" class="plain" ><font size="2"> ������� </font> </a>
           <a href="Productlist.jsp?catalog_id=-2" class="plain" ><font size="2"> ������� ������� </font> </a>
           <A href="Authorization.jsp?Login=newuser" class="plain" > <font size="2">����������� ������� </font></A>
           <a href="about.html" class="plain"> <font size="2">� ��������  </font></a>
        </div>

        <div class="personalBar">

            <a href="Authorization.jsp">
                <img src="xsl/reservation.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
		Login is <xsl:value-of select="document/login"/>@<xsl:value-of select="document/host"/>
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



  <xsl:if test="document/login = 'user'">   <!--  ���������� ���� ����� -->

	  <!-- head for porlet login   --> 
          <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
              <TBODY>
              <TR>
                <TD vAlign="center" ><FONT color="white" ><B>�����������</B></FONT>
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
    
                    
    
                    <strong>������������</strong> <br />



                     <INPUT  title="������������" tabindex="10001" SIZE="12" AUTOCOMPLETE="off" TYPE="TEXT" NAME="Login" >
			<xsl:attribute name="value">
				<xsl:value-of select="document/login"/>
			</xsl:attribute>		
        	     </INPUT>
    
                    <br />
    
                    <strong>������</strong>
		    <br />
		    <INPUT title="������" tabindex="10002"  SIZE="12" AUTOCOMPLETE="off" TYPE="PASSWORD" NAME="Passwd1" ></INPUT>
                    <br />
                    <br />
    
                    <input class="context searchButton"
                           type="submit" name="submit"
                           value="Log in" tabindex="10003" />
                </form>
            </div>
    
            <div class="content even"> 
                <a href="">
                   <img src="xsl/reservation.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
                   ������� ������ �� �����
                </a>
            </div>
        </div>
    </div>

</xsl:if>


<div>
    <div class="portlet">
    <h5><strong>�������</strong></h5>
      <div class="body">
        <div class="portletContent odd">
...
        </div>
        <div class="portletContent even">
...
        </div>
      </div>
    </div>
</div>






            </td>

            <td class="main">

            <!-- News part -->

	    <h1>������ ����� �������� ��������</h1>

	    <div class="description"><img src="xsl/reservation.gvidons.net/img/basket1.gif"/>������� ����� ����� <FORM name="order" action="Order.jsp" ><INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="TEXT" NAME="account_history_id"></INPUT><INPUT TYPE="submit" name="submit" value="�����"></INPUT></FORM></div>



		<div class="box">
		  <div class="body">
		    <div >




			<table >
			    <tbody>
<!--
                       <TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD ALIGN="RIGHT"><a><xsl:attribute name="HREF"><xsl:value-of select="document/prev"/></xsl:attribute><IMG height="23" alt="" src="xsl/reservation.gvidons.net/2uparrow.jpg" width="25" border="0"></IMG></a></TD></TR>
                       <TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD ALIGN="RIGHT" ><a><xsl:attribute name="HREF"><xsl:value-of select="document/next"/></xsl:attribute><IMG height="23" alt="" src="xsl/reservation.gvidons.net/2downarrow.jpg" width="25" border="0"></IMG></a></TD></TR> 

-->


                       <TR><TD width="50" >����</TD><TD width="30" align="right"  >�����</TD><TD width="50"></TD><TD width="200" >����</TD><TD width="250" >������ ����������</TD><TD></TD></TR>

			<xsl:for-each select="document/list/payment">
                                <TR>
                                <TD>N<xsl:value-of select="amount_id"/></TD>
                                <TD align="right" ><xsl:value-of select="add_amount"/></TD>
				<TD align="left"  ><xsl:value-of select="currency_add_lable"/></TD>
                                <TD><xsl:value-of select="sysdate"/></TD>

                                <!--  <TD><xsl:if test="complete = 't'"> <STRONG><FONT color="#000099">�����������</FONT></STRONG></xsl:if><xsl:if test="complete = 'f'"> <STRONG><FONT color="#000099">������� ����������</FONT></STRONG></xsl:if></TD>  -->
                                <TD>
				<xsl:if test="rezult_cd = '3'"> <STRONG><FONT color="#000099">������ �� ������</FONT></STRONG></xsl:if>
				<xsl:if test="rezult_cd = '2'"> <STRONG><FONT color="#000099">������ ������</FONT></STRONG></xsl:if>
				<xsl:if test="rezult_cd = '1'"> <STRONG><FONT color="#000099">������� ����� �� �����</FONT></STRONG></xsl:if>
				<xsl:if test="rezult_cd = ''"> 
				<xsl:if test="complete = 't'"> <STRONG><FONT color="#000099">�����������</FONT></STRONG></xsl:if><xsl:if test="complete = 'f'"> <STRONG><FONT color="#000099">������� ����������</FONT></STRONG></xsl:if>
				</xsl:if>
				</TD>

                                <TD align="right"  ><FORM name="order" action="AccountHistoryDetal.jsp" ><INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="amount_id"><xsl:attribute name="value"><xsl:value-of select="amount_id"/></xsl:attribute></INPUT><INPUT TYPE="submit" name="submit" value="���������"></INPUT></FORM></TD>
                                </TR>
			</xsl:for-each>

			    </tbody>
			</table>

		    </div>
		  </div>

	                <h1>
			<table >
			    <tbody>
                             <TR><TD width="50" ></TD><TD width="30"  ></TD><TD width="50"></TD><TD width="200" ></TD><TD width="250" ></TD><TD><a><xsl:attribute name="HREF"><xsl:value-of select="document/prev"/></xsl:attribute><IMG height="15" alt="" src="xsl/reservation.gvidons.net/2uparrow.jpg" width="15" border="0"><a><xsl:attribute name="HREF"><xsl:value-of select="document/next"/></xsl:attribute><IMG height="15" alt="" src="xsl/reservation.gvidons.net/2downarrow.jpg" width="15" border="0"></IMG></a></IMG></a></TD></TR>
			    </tbody>
			</table>
                        </h1>

		</div>


        <!-- Navigation -->
        <div class="listingBar">
	    <span class="next">
                <a HREF = "#" onClick="javascript:history.back()"  >
		<strong>	
		�����
		</strong>	
	        </a>
	    </span>
	</div>


            </td>

            <td class="right">



        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>������</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/shoping_url"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/reservation.gvidons.net/img/user.gif" width="20"></IMG>��������� � ������� ������� ��� ����������� �������</A>
	    </div>
	  </div>
	</div>


        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>��� ������</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_account_history"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/reservation.gvidons.net/img/user.gif" width="20"></IMG>���� �������� �������� .<br/>������� ����� �������� ������� � �������� <br/> <xsl:value-of select="document/balans"/> ������ .</A>
	    </div>
	  </div>
	</div>


        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>��� ���� ������</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_order_hist"/></xsl:attribute><IMG border="0" name="Nindex_14_03" src="xsl/reservation.gvidons.net/img/user.gif" height="20"   width="20"></IMG>������</A>
	    </div>
	  </div>
	</div>


        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>������� �����</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_order"/></xsl:attribute><IMG border="0"  name="Nindex_14_03" src="xsl/reservation.gvidons.net/img/user.gif" height="20"  width="20"></IMG>�����</A>
	    </div>
	  </div>
	</div>


        <TABLE height="20" cellSpacing="4" cellPadding="0" width="159" background="index.files/bg11.gif" >
           <TBODY>
            <TR>
                <TD vAlign="center" ><FONT color="white" ><B>�������� �����</B></FONT>
            </TD>
            </TR>
           </TBODY>
        </TABLE>
	<div class="box">
	  <div class="body">
	    <div class="content even" align="left" ><br/><IMG border="0" height="20" name="Nindex_14_03" src="xsl/reservation.gvidons.net/img/user.gif" width="20"></IMG> �� ������ ��������� �������� �������� � ������� ��� ������ ������, ��������� ��������� ����� ��� ����������� ��������, ����� ��� Web money , ������ ������ , E-Port � ������  <br/><br/>
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

Product Media@Shop  - Internet shop. Copyright 2005 <A HREF="http://www.grabko.com">Grabko Business Solution</A> . ��� ����� ��������.

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