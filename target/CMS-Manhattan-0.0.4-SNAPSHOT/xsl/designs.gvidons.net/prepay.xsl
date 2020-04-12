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
	<!--      <img src="xsl/designs.gvidons.net/img/logotip.gif" border="0" height="70" width="90"   /> -->
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


        </div>

        <hr size="" class="netscape4" />

        <div class="tabs">
        

           <a href="Productlist.jsp?catalog_id=-1" class="plain" ><font size="2"> ������� </font> </a>
          <a href="Productlist.jsp?catalog_id=-2" class="plain" ><font size="2"> ������� �������� </font> </a>
           <A href="Authorization.jsp?Login=newuser" class="plain" > <font size="2">����������� ������� </font></A>
           <a href="about.html" class="plain"><font size="2"> � �������� </font></a>


        </div>

        <div class="personalBar">

            <a href="Authorization.jsp">
                <img src="xsl/designs.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
		Login is <xsl:value-of select="document/login"/>@<xsl:value-of select="document/host"/>
            </a>

            
        </div>

        <div class="pathBar">
            <span>
                <span> ���� ���� ������� � ��������: ����: +7 (812) 463-4411 , ���: +7 (812) 463-5983 -  ���������� ����������� ����������� ��� �����  </span>
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
                <TD vAlign="center" ><FONT color="white" ><B>�����������</B></FONT>
               </TD>
              </TR>
  	     </TBODY>
  	   </TABLE>
	  <!-- porlet login   --> 


    <div class="box">
        <div class="body">
            <div class="odd">
                <form action="Authorization.jsp"   method="post">
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
    
            <div class="even"> 
                <a href="">
                   <img src="xsl/designs.gvidons.net/img/user.gif" alt="Link icon" title="Link icon" height="15" width="10" border="0" />
                   ������� ������ �� �����
                </a>
            </div>
        </div>
    </div>


<div>
    <div class="portlet">
    <h5><strong>������</strong></h5>
      <div class="body">
        <div class="portletContent odd">
<strong>1.</strong> �������� ��������� ������� � ������ ������
        </div>
        <div class="portletContent even">
<strong>2.</strong> ������� ������ "�����"
        </div>
      
      </div>
    </div>
</div>






            </td>

            <td class="main">

            <!-- News part -->

	    <h1>�������� ������ ������</h1>
<br/>


                <FORM  method="post" name="prepayform"  ACTION="Pay.jsp"   >
		<div class="box">
		  <div class="body">
		    <div >
			<table >
			    <tbody>

                       <TR> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> </TR>
                       <TR> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> </TR>
                       <TR> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> </TR>
                       <TR> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> </TR>

                       <TR><TD colspan="5" ></TD><TD></TD></TR>
                       <TR><TD>�����</TD><TD  width="50" colspan="4" ></TD><TD><INPUT SIZE="15"  AUTOCOMPLETE="off" TYPE="TEXT" NAME="Amount" VALUE="0"  ><xsl:attribute name="VALUE"><xsl:value-of select="document/order_end_amount"/></xsl:attribute></INPUT></TD></TR>
                       <TR><TD >������</TD><TD width="50" colspan="4" ></TD><TD>
                            <SELECT  NAME = "currency_id">
        	     	      <xsl:for-each select="document/currency/currency-item">
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
                       <TR><TD>��������� �������</TD><TD width="50"  colspan="4" ></TD><TD>
                             <SELECT SIZE="10" NAME = "paysystem_id">
      			     <xsl:for-each select="document/paysystem/paysystem-item">
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


                       <TR> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> </TR>
                       <TR> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> </TR>
                       <TR> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> </TR>
                       <TR> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> <TD></TD> </TR>

                       <TR><TD align="right" colspan="5"></TD> <TD ><input type="submit" name="Submit" value="�����"></input></TD> </TR>                       
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
	    <div class="even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/shoping_url"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/designs.gvidons.net/img/user.gif" width="20"></IMG>��������� � ������� ������� ��� ����������� �������</A>
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
	    <div class="even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_account_history"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/designs.gvidons.net/img/user.gif" width="20"></IMG>���� �������� �������� .<br/>������� ����� �������� ������� � �������� <br/> <xsl:value-of select="document/balans"/> ������ .</A>
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
	    <div class="even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_order_hist"/></xsl:attribute><IMG border="0" name="Nindex_14_03" src="xsl/designs.gvidons.net/img/user.gif" height="20"   width="20"></IMG>������</A>
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
	    <div class="even" align="left" >
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_order"/></xsl:attribute><IMG border="0"  name="Nindex_14_03" src="xsl/designs.gvidons.net/img/user.gif" height="20"  width="20"></IMG>�����</A>
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
	    <div class="even" align="left" ><br/><IMG border="0" height="20" name="Nindex_14_03" src="xsl/designs.gvidons.net/img/user.gif" width="20"></IMG> �� ������ ��������� �������� �������� � ������� ��� ������ ������, ��������� ��������� ����� ��� ����������� ��������, ����� ��� Web money , ������ ������ , E-Port � ������  <br/><br/>
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/to_pay"/></xsl:attribute><IMG border="0" height="20" name="Nindex_14_03" src="xsl/designs.gvidons.net/img/credit-cards.jpg" width="130"></IMG></A>
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

Internet shop. Copyright 2005 <A HREF="http://www.grabko.com">Grabko Business Solution</A> . ��� ����� ��������.

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