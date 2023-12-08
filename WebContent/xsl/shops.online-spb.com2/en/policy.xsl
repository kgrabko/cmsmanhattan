<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
<xsl:output method="html" indent="yes"/>
<xsl:output encoding="UTF-8"/>
<xsl:strip-space elements="*"/>


<xsl:template match="/">
	<xsl:variable name="host" select="string(document/host)"/>
    <xsl:variable name="page" select="number(document/offset)"/>
    <xsl:variable name="user_id" select="number(/document/owner_user_id)"/>
    <xsl:variable name="role" select="document/role_id"/>
<html>

<head>
     <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"/>
     <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE"/>

     <title>www.siteforyou.net</title>
     
     <LINK id="style2" rel="stylesheet" type="text/css"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/style2.css')"/></xsl:attribute></LINK> 
     <SCRIPT type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/common.js')"/></xsl:attribute></SCRIPT>
     
     <link rel="stylesheet" type="text/css" media="screen"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/css/jquery.lightbox.css')"/></xsl:attribute></link>
     
     <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/js/jquery.js')"/></xsl:attribute></script>
	 <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/js/jquery.lightbox.js')"/></xsl:attribute></script>
	
	
	
	<script type="text/javascript">
	$(function() {
		$('a[@rel*=lightbox]').lightBox(); // Select all links that contains lightbox in the attribute rel
	});
	</script>

<script type="text/javascript">

function open_phone() 
{
 window.open('phone.html','','width=330,height=610')
}

</script>

</head>

<body>

<TABLE cellSpacing="0" cellPadding="0" width="100%"  border="0" rightmargin="0" leftmargin="0" topmargin="0" bordercolor="#ECEFF8" >
<TR>
<TD bgcolor="#ECEFF8" >.</TD>
<TD vAlign="top" Align="center" width="1023" >


<a class="skipnav" href="#documentContent">Skip to content</a>

<div>

     

    <div class="top">

    <TABLE cellSpacing="0" cellPadding="10"  height="120"  width="100%" class="bacgraundTop" border="0" rightmargin="0" leftmargin="0" topmargin="0">
	  <TBODY>
	  <TR>
	    <TD vAlign="top" Align="left"  width="20%">
			<img height='80'  width='80'  ><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/logo.gif')"/></xsl:attribute></img>
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

<!--

            <div class="searchBox">
                <form name="searchform"
                      action="">

                    <input id="searchGadget"
                           name="SearchableText" type="text"
                           size="20" alt="Search"
                           title="Search" tabindex="30001" />

                    <input class="context searchButton"
                           type="submit" size="20" value="Search"
                           tabindex="30002" />
                </form>
            </div>
<br />
<br />

	<div class="box">
	  <div class="body">
	    <div class="content even"> <img src="xsl/www.siteoneclick.com/img/logo5.jpg" border="0" ><font size="3"><b> Internet shop <xsl:value-of select="document/site_name"/></b></font></img></div>
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



       
<!--

        <xsl:if test="document/message != ''">   
        <div class="pathBar">
            <span>
              <CENTER>
                <font size="2"  color="red"  > 
		   <xsl:value-of select="document/message"/> 
	        </font>
	       </CENTER>
            </span>
        </div>
	</xsl:if>

-->
			<!-- show message -->

			<xsl:if test="document/message != ''">

			 <!--
			<SCRIPT LANGUAGE="javascript">
			if (confirm('<xsl:value-of select="document/message"/>. Wish to be registered?'))  parent.location='Authorization.jsp?Login=';
			</SCRIPT>
			-->
			</xsl:if>


        <hr size="" class="netscape4" />

    </div>



<table class="columns">

    <tbody>
        <tr>
            <td class="left">

 	               <xsl:if test="document/login = 'user'">   <!--  показывать если нет логина -->

			       <div class="box" > 

			          <div style="height:20px; TEXT-ALIGN: left"   class="bacgraundBoxTitle">
					  <!-- head for porlet login   --> 
				          <TABLE height="20" cellSpacing="4" cellPadding="0" width="100%" >
				              <TBODY>
				              <TR>
				                <TD vAlign="center" ><FONT color="white" ><B>Authorization</B></FONT></TD>
				              </TR>
				  	     	  </TBODY>
				  	   	  </TABLE>
					  <!-- porlet login   --> 
			          </div>
			          	<div class="body">
				            <div class="content odd">
						    <A href="Authorization.jsp?Login=newuser" class="plain" ><font size="3" color="red"  > Registration </font> </A><br /><br />
			                <form action="Authorization.jsp"  method="post">
			                     <font size="1" > <strong>User</strong></font> <br />
			                     <INPUT  title="User" tabindex="10001" SIZE="12" AUTOCOMPLETE="off" TYPE="TEXT" NAME="Login" >
									<xsl:attribute name="value">
										<xsl:value-of select="document/login"/>
									</xsl:attribute>		
			        	     	 </INPUT>
			                     <br />
			                     <font size="1" ><strong>Password</strong></font>
					             <br />
					             <INPUT title="Password" tabindex="10002"  SIZE="12" AUTOCOMPLETE="off" TYPE="PASSWORD" NAME="Passwd1" ></INPUT>
			                     <br />
			                     <br />
			                     <input class="context searchButton"   type="submit" name="submit"  value="Enter" tabindex="10003" />
			                </form>
				 	        </div>
    
				           <div class="content even"> 
				                <a href="Authorization.jsp?Login=newuser">
				                   <img alt="Link icon" title="Link icon" height="11" width="6" border="0">
				                   		<xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/linkTransparent.gif')"/></xsl:attribute>
				                   </img>
				                   Have forgotten the password?
				                </a>
				           </div>
			        	</div>
			       </div>
			       <br/>
	  			   </xsl:if>


<div>
    <div class="portlet">

           <TABLE class="bacgraundBoxTitle"  height="20" cellSpacing="4" cellPadding="0" width="159"  >
              <TBODY>
              <TR>
                <TD vAlign="center" ><FONT color="white" ><B>All steps</B></FONT>
               </TD>
              </TR>
  	     </TBODY>
  	   </TABLE>

                                                              

           <TABLE cellSpacing="0" cellPadding="0" width="159" align="center"  class="bacgraundBoxLeft" border="0" rightmargin="0"  leftmargin="0" topmargin="0">
           <TBODY>
            <!-- News Iten start -->
	    <xsl:for-each select="document/newslist/news">
                <TR>
                <TD width="5"></TD>
                <TD width="145">


            <xsl:if test="image != ''" >

   	        <xsl:if test="big_image_type != 'swf'">
   	           <xsl:if test="big_image_type != 'flv'">
                      <A class="menu"  >
                      	<xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><br/>
		       <!-- Transfer of a line is processed -->
		       <img  alt=""  width="147" border="0"><xsl:attribute name="src"><xsl:value-of select="image"/></xsl:attribute></img>
                       <U>
   		       <xsl:for-each select="description/r">
		       <xsl:value-of select="."/> <br/>
		       </xsl:for-each>
                       </U>
		      </A>
                   </xsl:if>
                </xsl:if>


   	        <xsl:if test="big_image_type = 'flv'">

		   <embed src="images/player.swf" 
			width="147" 
			searchbar="false"
			allowscriptaccess="always" 
			allowfullscreen="true" >
			<xsl:attribute name="flashvars">searchbar=false<![CDATA[&]]>file=http://<xsl:value-of select="document/host"/>/<xsl:value-of select="image"/><![CDATA[&]]>bufferlength=200</xsl:attribute>
		   </embed>

                    <A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><br/>
                      <U>
   		      <xsl:for-each select="description/r">
		      <xsl:value-of select="."/> <br/>
		      </xsl:for-each>
                      </U>
		    </A>
               </xsl:if>
	    </xsl:if>

            <xsl:if test="image = ''" >
                <A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><br/>
		      <!-- Transfer of a line is processed  -->
                      <U>
   		      <xsl:for-each select="description/r">
		      <xsl:value-of select="."/> <br/>
		      </xsl:for-each>
                      </U>
		</A>
	    </xsl:if>

<br/>
   <!-- Removal buttons  , beginning -->
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
   <!-- Removal buttons  , end  -->
		</TD>
                <TD width="5">
                </TD>
                </TR>
              <TR><TD width="155"  class="bacgraundLine" colSpan="3"  height="10" ></TD></TR>
  	    </xsl:for-each>
            <!-- News Iten end -->
            <!-- 
              <TR>
                <TD align="right" width="155"  colSpan="3" style="padding-right: 15px"><A class="menu"  title="All news" 
                  href="Productlist.jsp?catalog_id=-6"><FONT  class="text" ><U>Новые поступления</U></FONT></A></TD>
              </TR>
               -->
              <TR>
                <TD width="155"  class="bacgraundLine2" colSpan="3" height="11" ></TD>
              </TR>
             </TBODY>
             </TABLE>





    </div>
</div>

<br/>


       <xsl:if test="document/empty_page_ext2 = 'false'" > 
       <div class="box">
	   <div>
           <TABLE class="bacgraundBoxTitle"  height="20" cellSpacing="4" cellPadding="0" width="159" >
              <TBODY>
              <TR>
                <TD vAlign="center" ><FONT color="white" ><B>More</B></FONT>
               </TD>
              </TR>
  	     </TBODY>
  	   </TABLE>
                                         

           <TABLE cellSpacing="0" cellPadding="0" width="159" id="gallery_left" align="center"  class="bacgraundBoxLeft" border="0" rightmargin="0"  leftmargin="0" topmargin="0">
           <TBODY>
           <TR>
                <TD width="5"><br/></TD>
           </TR>
            <!-- News Iten start -->
	    <xsl:for-each select="document/extpolicy_productlist2/extpolicy_product2">
                <TR>
                <TD width="5"></TD>
                <TD width="145">


            <xsl:if test="image != ''" >

   	        <xsl:if test="big_image_type != 'swf'">
   	           <xsl:if test="big_image_type != 'flv'">
                      <A class="menu"  rel="lightbox">
                      <xsl:attribute name="HREF"><xsl:value-of select="image"/></xsl:attribute>
                      <xsl:if test="/document/role_id = '1'">
						<xsl:if test="file_exist = 'true'">
							<xsl:attribute name="title">Create shop</xsl:attribute>		       			
							<xsl:attribute name="alt">Productlist.jsp?create_site_by_id=<xsl:value-of select="product_id"/></xsl:attribute>
					   </xsl:if>
					 </xsl:if>                      	
						<br/>
		       			<img  alt=""  width="147" border="0">
		       				<xsl:attribute name="src"><xsl:value-of select="image"/></xsl:attribute>
		       			</img>
                      </A>
               <A class="menu"><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute>
                       <U>
   		       <xsl:for-each select="description/r">
		       <xsl:value-of select="."/> <br/>
		       </xsl:for-each>
                       </U>
		      </A>
                   </xsl:if>
                </xsl:if>


   	        <xsl:if test="big_image_type = 'flv'">

		   <embed src="images/player.swf" 
			width="147" 
			searchbar="false"
			allowscriptaccess="always" 
			allowfullscreen="true" >
			<xsl:attribute name="flashvars">searchbar=false<![CDATA[&]]>file=http://<xsl:value-of select="document/host"/>/<xsl:value-of select="image"/><![CDATA[&]]>bufferlength=200</xsl:attribute>
		   </embed>

                    <A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><br/>
                      <U>
   		      <xsl:for-each select="description/r">
		      <xsl:value-of select="."/> <br/>
		      </xsl:for-each>
                      </U>
		    </A>
               </xsl:if>
	    </xsl:if>

            <xsl:if test="image = ''" >
                <A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><br/>
		      <!-- Обрабатываем перевод строки  -->
                      <U>
   		      <xsl:for-each select="description/r">
		      <xsl:value-of select="."/> <br/>
		      </xsl:for-each>
                      </U>
		</A>
	    </xsl:if>
   <br/>
   <!-- Кнопки удаления  , начало -->
       <xsl:if test="/document/role_id = '2'">
	    
		<table>
		    <tbody>
		        <tr>
		         <td>
		                <form name="product_del"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="delete"></INPUT>
		                </form>
		         </td>
		         <td>

		                <form name="product_edit"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="ext2" ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="delete"></INPUT>
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
              <TR><TD width="155"  class="bacgraundLine" colSpan="3"  height="10" ></TD></TR>
  	    </xsl:for-each>
            <!-- News Iten end -->
              <TR>
                <TD align="right" width="155"  colSpan="3" style="padding-right: 15px"><A class="menu"  title="All news" 
                  href="Productlist.jsp?catalog_id=-1"><FONT  class="text" ><U>Archive of news</U></FONT></A></TD>
              </TR>
              
              <TR>
                <TD width="155"  class="bacgraundLine2" colSpan="3" height="11" ></TD>
              </TR>
             </TBODY>
             </TABLE>
       </div>
     </div>
</xsl:if>









            </td>

            <td class="main"  height="650"  valign="top"   align="center"   >

        <!-- To show the button for management of a site , beginning  -->

        <xsl:if test="document/admin/post_manager != ''">
	<div class="footer">Press this button if you wish to change site contents
	<A><xsl:attribute name="HREF"><xsl:value-of select="document/admin/post_manager"/>
	</xsl:attribute><img><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/refresh.gif')"/></xsl:attribute></img></A>
        </div>
	</xsl:if>
        <!-- To show the button for management of a site , end  -->


        <!-- News part -->




        <div class="listingBar">
	    <span class="next">
		<A><xsl:attribute name="HREF"><xsl:value-of select="document/product/back_url"/></xsl:attribute><strong>To return to the beginning  </strong></A>
	    </span>
	</div>


    
            <!-- News -->

            <!-- News Iten start -->
            <div class="box">

                <div class="body">
                    <div class="content">
   

                        <h2><xsl:value-of select="document/product/name"/></h2>  
                        <p>
  <FORM  name="order" action="Order.jsp">
  <INPUT AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="position"  ><xsl:attribute name="value"><xsl:value-of select="document/product/product_id"/></xsl:attribute></INPUT>
  <INPUT AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="add" ></INPUT>


<table border="0" width="95%" style="padding-left: 15px">
    <tbody>
   	   <xsl:if test="document/product/image != ''">
										        
												<div style="width: 100%; text-align: center; margin-bottom: 10px; margin-top: 10px">
										   	    <xsl:if test="document/product/image_type != 'swf'">
										   	      <xsl:if test="document/product/image_type != 'flv'">
												<IMG alt=""  width="400" border="0"><xsl:attribute name="src"><xsl:value-of select="document/product/image"/></xsl:attribute></IMG> 
										              </xsl:if>
										            </xsl:if>
										
										   	    <xsl:if test="document/product/image_type = 'flv'">
												  <embed src="http://www.jeroenwijering.com/embed/player.swf" 
													width="600" 
													height="600"  
													searchbar="false"
													allowscriptaccess="always" 
													allowfullscreen="true" >
													<xsl:attribute name="flashvars">searchbar=false<![CDATA[&]]>file=http://<xsl:value-of select="document/domain"/>/<xsl:value-of select="document/product/image"/><![CDATA[&]]>bufferlength=200</xsl:attribute>
												   </embed>
										            </xsl:if>
										
										   	    <xsl:if test="document/product/image_type = 'swf'">
												<OBJECT id="Tech Presentation1" >
												<embed 
												quality="high" 
												bgcolor="#ffffff" 
												width="400" 
												
												name="Tech Presentation1" 
												align="middle" 
												allowScriptAccess="sameDomain" 
												type="application/x-shockwave-flash" 
												pluginspage="http://www.macromedia.com/go/getflashplayer" ><xsl:attribute name="src"><xsl:value-of select="document/product/image"/></xsl:attribute></embed>
												</OBJECT>
										             </xsl:if>
										
										   	   </div> 
										  	</xsl:if>

        <tr>
   	    <xsl:if test="document/product/image = ''">
            <td width="650" >
  	    <!-- Transfer of a line is processed  --> 
		  <xsl:for-each select="document/product/description/r">
			<xsl:value-of select="."/> <br/>
	  	  </xsl:for-each>

            </td>
	    </xsl:if>

   	    <xsl:if test="document/product/image != ''">
            <td width="520" >
  	    <!-- Transfer of a line is processed  --> 
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


<!-- Voting , beginning  --> 


        <tr >
            <td colspan="2"  align="left"  valign="bottom" >
<xsl:if test="document/show_rating1 = 'true'">	
<div class="star" style="vertical-align: top"><br/><table><tr><td style="padding-top: 2px">Vote :</td><td>
  <A href="Policy.jsp?rate=1"  alt="Estimation 1"  ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue1.jpg')"/></xsl:attribute></IMG></A>
  <A href="Policy.jsp?rate=2"  alt="Estimation 2" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue2.jpg')"/></xsl:attribute></IMG></A>
  <A href="Policy.jsp?rate=3"  alt="Estimation 3" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue3.jpg')"/></xsl:attribute></IMG></A>
  <A href="Policy.jsp?rate=4"  alt="Estimation 4" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue4.jpg')"/></xsl:attribute></IMG></A>
  <A href="Policy.jsp?rate=5"  alt="Estimation 5" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue5.jpg')"/></xsl:attribute></IMG></A>
  <A href="Policy.jsp?rate=6"  alt="Estimation 6" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue6.jpg')"/></xsl:attribute></IMG></A>
  <A href="Policy.jsp?rate=7"  alt="Estimation 7" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue7.jpg')"/></xsl:attribute></IMG></A>
  <A href="Policy.jsp?rate=8"  alt="Estimation 8" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue8.jpg')"/></xsl:attribute></IMG></A>
  <A href="Policy.jsp?rate=9"  alt="Estimation 9" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue9.jpg')"/></xsl:attribute></IMG></A>
  <A href="Policy.jsp?rate=10"  alt="Estimation 10" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue10.jpg')"/></xsl:attribute></IMG></A>
       </td></tr></table></div><div><table><tr><td style="padding-top: 2px">Average estimation :</td>

<td class="star">
	 <xsl:choose>
          <xsl:when test="document/rating1/show_star_1 != 'no'">
  <A href="Policy.jsp?rate=1"  alt="Estimation 1"  ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue_fill.jpg')"/></xsl:attribute></IMG></A>
          </xsl:when>
          <xsl:otherwise>
  <A href="Policy.jsp?rate=1"  alt="Estimation 1"  ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue.jpg')"/></xsl:attribute></IMG></A>
          </xsl:otherwise>
        </xsl:choose>

 <xsl:choose>
          <xsl:when test="document/rating1/show_star_2 != 'no'">
  <A href="Policy.jsp?rate=2"  alt="Estimation 2" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue_fill.jpg')"/></xsl:attribute></IMG></A>
          </xsl:when>
          <xsl:otherwise>
  <A href="Policy.jsp?rate=2"  alt="Estimation 2" > <IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue.jpg')"/></xsl:attribute></IMG></A>
          </xsl:otherwise>
        </xsl:choose>


 <xsl:choose>
          <xsl:when test="document/rating1/show_star_3 != 'no'">
  <A href="Policy.jsp?rate=3"  alt="Estimation 3" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue_fill.jpg')"/></xsl:attribute></IMG></A>
          </xsl:when>
          <xsl:otherwise>
  <A href="Policy.jsp?rate=3"  alt="Estimation 3" > <IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue.jpg')"/></xsl:attribute></IMG></A>
          </xsl:otherwise>
        </xsl:choose>


 <xsl:choose>
          <xsl:when test="document/rating1/show_star_4 != 'no'">
  <A href="Policy.jsp?rate=4"  alt="Estimation 4" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue_fill.jpg')"/></xsl:attribute></IMG></A>
          </xsl:when>
          <xsl:otherwise>
  <A href="Policy.jsp?rate=4"  alt="Estimation 4" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue.jpg')"/></xsl:attribute></IMG></A>
          </xsl:otherwise>
        </xsl:choose>



 <xsl:choose>
          <xsl:when test="document/rating1/show_star_5 != 'no'">
  <A href="Policy.jsp?rate=5"  alt="Estimation 5" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue_fill.jpg')"/></xsl:attribute></IMG></A>
          </xsl:when>
          <xsl:otherwise>
  <A href="Policy.jsp?rate=5"  alt="Estimation 5" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue.jpg')"/></xsl:attribute></IMG></A>
          </xsl:otherwise>
        </xsl:choose>


 <xsl:choose>
          <xsl:when test="document/rating1/show_star_6 != 'no'">
  <A href="Policy.jsp?rate=6"  alt="Estimation 6" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue_fill.jpg')"/></xsl:attribute></IMG></A>
          </xsl:when>
          <xsl:otherwise>
  <A href="Policy.jsp?rate=6"  alt="Estimation 6" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue.jpg')"/></xsl:attribute></IMG></A>
          </xsl:otherwise>
        </xsl:choose>



 <xsl:choose>
          <xsl:when test="document/rating1/show_star_7 != 'no'">
  <A href="Policy.jsp?rate=7"  alt="Estimation 7" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue_fill.jpg')"/></xsl:attribute></IMG></A>
          </xsl:when>
          <xsl:otherwise>
  <A href="Policy.jsp?rate=7"  alt="Estimation 7" > <IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue.jpg')"/></xsl:attribute></IMG></A>
          </xsl:otherwise>
        </xsl:choose>



 <xsl:choose>
          <xsl:when test="document/rating1/show_star_8 != 'no'">
  <A href="Policy.jsp?rate=8"  alt="Estimation 8" ><IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue_fill.jpg')"/></xsl:attribute></IMG></A>
          </xsl:when>
          <xsl:otherwise>
  <A href="Policy.jsp?rate=8"  alt="Estimation 8" > <IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue.jpg')"/></xsl:attribute></IMG></A>
          </xsl:otherwise>
        </xsl:choose>



 <xsl:choose>
          <xsl:when test="document/rating1/show_star_9 != 'no'">
  <A href="Policy.jsp?rate=9"  alt="Estimation 9" > <IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue_fill.jpg')"/></xsl:attribute></IMG></A>
          </xsl:when>
          <xsl:otherwise>
  <A href="Policy.jsp?rate=9"  alt="Estimation 9" > <IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue.jpg')"/></xsl:attribute></IMG></A>
          </xsl:otherwise>
        </xsl:choose>



 <xsl:choose>
          <xsl:when test="document/rating1/show_star_10 != 'no'">
  <A href="Policy.jsp?rate=10"  alt="Estimation 10" > <IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue_fill.jpg')"/></xsl:attribute></IMG></A>
          </xsl:when>
          <xsl:otherwise>
  <A href="Policy.jsp?rate=10"  alt="Estimation 10" > <IMG><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/star_blue.jpg')"/></xsl:attribute></IMG></A>
          </xsl:otherwise>
        </xsl:choose>


</td></tr></table>
        </div></xsl:if>	



   	    </td>
        </tr>


<!-- Voting , end  --> 


        <tr >
            <td colspan="2"  align="right"  valign="bottom" ><br/>
   	    </td>
        </tr>


        <tr >
            <td colspan="2"  align="right"  valign="bottom" >Viewing: <xsl:value-of select="document/product/statistic"/>  <br/>
		Publish: <xsl:value-of select="document/product/cdate"/>
   	    </td>
        </tr>

        <tr >
            <td colspan="2"  align="right"  valign="bottom" ><br/>
   	    </td>
        </tr>
        <!-- 
        <tr >
  	    <xsl:if test="document/product/amount != '0'"> 
            <td colspan="1"  align="right"  valign="bottom" >
   	        </td>
            <td colspan="1"  align="right"  valign="bottom" >
                   <INPUT AUTOCOMPLETE="off" TYPE="Submit" NAME="Submit" VALUE="To put in a basket" ></INPUT>
    	    </td>
           </xsl:if>
        </tr>
   		 -->

		<xsl:if test="/document/role_id = '1'">
		<xsl:if test="/document/product/file_exist = 'true'">
		<tr >
            <td colspan="2"  align="right"  valign="bottom" >
            <img alt="Link icon" title="Link icon" height="11" width="6" border="0" style="margin-right: 5px">
           		<xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/linkTransparent.gif')"/></xsl:attribute>
            </img>
			<a><xsl:attribute name="href">Productlist.jsp?create_site_by_id=<xsl:value-of select="document/product/product_id"/></xsl:attribute>To create shop</a><br/>
   	    	</td>
        </tr>
	   </xsl:if>
	   </xsl:if>

	
	<xsl:if test="/document/role_id = 2">
	<xsl:if test="/document/product/file_exist = 'true'">
	  <tr >
  	    <xsl:if test="document/internet != 'true'"> 
           		<td colspan="2"  align="right"  valign="bottom" >
					<A><xsl:attribute name="href"><xsl:value-of select="/document/product/product_url"/></xsl:attribute> To download design</A>
   	    		</td>
         </xsl:if>
        </tr>
	</xsl:if>
   </xsl:if>

    </tbody>
</table>

  </FORM>

			
			</p>



	<xsl:if test="count(//document/extpolicy_file_list/extpolicy_file) != '0'" > 


           <TABLE height="20" cellSpacing="0" cellPadding="0" width="601" class="forum" >
	              <TBODY>
	              <TR>
	                <TD vAlign="center"  Align="center" >
			 <FONT color="white" ><B> Office documents of the company - <xsl:value-of select="document/product/name"/>
  		        </B></FONT>
	               </TD>
	              </TR>
	  	     </TBODY>
	   </TABLE>

           <TABLE style="background: transparent;border-collapse: collapse;border: 1px solid #8CACBB;"  cellSpacing="0" cellPadding="0" width="600" align="center"  border="0" rightmargin="0"  leftmargin="0" topmargin="0">
           <TBODY>
            <!-- News Iten start -->
	    <xsl:for-each select="document/extpolicy_file_list/extpolicy_file">
                <TR>


                <TD width="5"></TD>
                <TD width="*">

		  <xsl:value-of select="position()"/>. 

 	          <A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="product_url"/></xsl:attribute>
                      <U>
		         <xsl:value-of select="name"/>   - to download
.  
                      </U>
		  </A>

		</TD>                

		<TD width="2"></TD>
                </TR>

                <TR>
		  <TD width="555"  align="center"   style="background: #DFE3EF;"   colSpan="5"  height="10" >



   <!-- Removal buttons , beginning -->
  <xsl:choose> 
       <xsl:when test="/document/role_id = '2'">
		     <table>
		     <tbody>
		        <tr>
		         <td>
		                <form name="product_del"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Delete"></INPUT>
		                </form>
		         </td>
		         <td>

		                <form name="product_edit"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="ext_files" ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Change"></INPUT>
		                </form>
		         </td>
		        </tr>
		      </tbody>
		     </table>
    </xsl:when> 
    <!-- Removal buttons , end -->
    <!--  for Edit  User context  Removal buttons  , beginning -->
   <xsl:when test="/document/role_id = '1'">

                <xsl:if test="user_id = string(number($user_id))" >

		<table>
		    <tbody>
		        <tr>
		         <td>
		                <form name="product_del"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Delete"></INPUT>
		                </form>
		         </td>
		         <td>

		                <form name="product_edit"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="ext_ofice_files_user" ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Change"></INPUT>
		                </form>
		         </td>
		        </tr>
		    </tbody>
		</table>
		</xsl:if>
   </xsl:when> 
  </xsl:choose> 
<!-- Removal buttons  , the end for the author  -->


	 </TD>
	</TR> 
    </xsl:for-each>

    </TBODY>
    </TABLE>
</xsl:if>

<br/>
										<xsl:if test="document/product/amount != '0'"> 
										<xsl:if test="document/product/amount != '0.0'"> 
										 <br/>
								       <FORM  name="order" action="Order.jsp" method="POST">
			                        	<table><tr>
			                        		<td width="90%" align="right"  >
			            						    <INPUT AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="position"  ><xsl:attribute name="value"><xsl:value-of select="document/product/product_id"/></xsl:attribute></INPUT>
												    <INPUT AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="add" ></INPUT>
												    <div class="cena2" style="padding:2px;margin:2px;" >Quantity</div>
						            		</td>
			                        		<td width="5%"  >
			       								    <INPUT AUTOCOMPLETE="off" type="number"  size="6" min="1" max="90000"  NAME="quantity" VALUE="1" ></INPUT>
			                        		</td>
			                        		<td width="5%"  >
 											            <INPUT class="button" AUTOCOMPLETE="off" TYPE="Submit" NAME="Submit" alt="Add to cart"  VALUE="Add to cart" ></INPUT>   
						            		</td>
			                        	</tr></table>
			                        	 </FORM> 
			                        	 <br/>
			                        	</xsl:if>
										</xsl:if>		
                        
                    </div>

                </div>
                
            </div>

            <!-- News Iten end -->


        <!-- Navigation -->
        <div class="listingBar">
	    <span class="next">
                <a HREF = "javascript:history.back()">
		<strong>	
		back
		</strong>	
	        </a>
	    </span>
	</div>

	<br/>

<div>
<xsl:if test="document/show_blog = 'true'" > 
<div class="box">
	  <div class="body">
	    <div>
	       <DIV class="forum">

	              <TABLE  cellSpacing="0" cellPadding="0"  width="100%"  >
	              <TBODY>
	              <TR>
	              <TD  width="5%"  height=""  vAlign="center"  Align="left" >
	              </TD>
	              <TD  width="90%" vAlign="center"  Align="center" >
						<font color='white' size='2'   > 
							<B> Forum - questions and answers<xsl:value-of select="document/product/name"/></B>   
						</font>
	              </TD>
	              <TD  width="5%" vAlign="center"  Align="right" >
					<A href="javascript:switchWindoForum()" > 
						<IMG id='forum_div'  title="Expand" align="right">
							<xsl:attribute name="src">
								<xsl:value-of select="concat('xsl/',$host,'/images/expand.gif')"/>
							</xsl:attribute>
						</IMG> 
					</A>
	              </TD>
	              </TR>
	              </TBODY>
	              </TABLE>

		     </DIV>
		<br/>
	      <div id='windowforum'  >


	           <TABLE  cellSpacing="0" cellPadding="0" width="590" align="center"  border="0" rightmargin="0"  leftmargin="0" topmargin="0" >
	           <TBODY>
	           <TR><TD  width="*"  colSpan="3"  height="8" ><A href="BlogExtProductPost.jsp" ><img SRC="images/file.png" border="0" alt="Add the message" ></img>Add the message </A></TD></TR>

	            <!-- News Iten start -->
		    <xsl:for-each select="document/product_blog_list/product_blog">
	                <TR>
	                <TD width="5"></TD>
	                <TD width="*">
						<H1>
						 <font size="1" >						 					 	
						 	<b><xsl:value-of select="name"/></b> / 
						 	<IMG border="0" height="20" name="Nindex_14_03" width="20">
						 		<xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/user.gif')"/></xsl:attribute>
						 	</IMG>   
						 	<xsl:value-of select="author"/>  /Added: <xsl:value-of select="cdate"/>
						 </font> 
						</H1>
			      
			      
			      <!-- Transfer of a line is processed  -->
	   		      <xsl:for-each select="description/r">
			      <xsl:value-of select="."/> <br/>
			      </xsl:for-each>
		      

			<br/>
			
			<xsl:choose>
			   <!-- Removal buttons , beginning for the administrator-->
			<xsl:when test="/document/admin/post_manager != ''">
			<table>
			    <tbody>
			        <tr>
			         <td>
			                <form name="product_del"  action="Productlist.jsp">
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
								<INPUT TYPE="submit" name="submit" value="Delete"></INPUT>
			                </form>
			         </td>
			         <td>

			                <form name="product_edit"  action="Productlist.jsp">
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="blog" ></INPUT>
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_parent_id"><xsl:attribute name="value"><xsl:value-of select="/document/product/product_id"/></xsl:attribute></INPUT>
								<INPUT TYPE="submit" name="submit" value="Change"></INPUT>
			                </form>
			         </td>
			        </tr>
			    </tbody>
			</table>
			</xsl:when>
			   <!-- Removal buttons  , end for the administrator-->

			   <!-- Removal buttons , beginning for the author  -->

	       <xsl:when test="user_id = string(number($user_id))" >

			<table>
			    <tbody>
			        <tr>
			         <td>
			                <form name="product_del"  action="Productlist.jsp">
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
					<INPUT TYPE="submit" name="submit" value="Delete"></INPUT>
			                </form>
			         </td>
			         <td>

			                <form name="product_edit"  action="Productlist.jsp">
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="blog" ></INPUT>
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_parent_id"><xsl:attribute name="value"><xsl:value-of select="/document/product/product_id"/></xsl:attribute></INPUT>
					<INPUT TYPE="submit" name="submit" value="Change"></INPUT>
			                </form>
			         </td>
			        </tr>
			    </tbody>
			</table>
			</xsl:when>
			</xsl:choose>
			   <!-- Removal buttons  , beginning for the author-->

			</TD>
	                <TD width="5">
	                </TD>
	                </TR>
	              <TR><TD width="*"  colSpan="3"  height="10" ></TD></TR>
	  	    </xsl:for-each>
	    </TBODY>
	</TABLE>

	   </div>
	 </div>
	 </div>
	</div>
</xsl:if>

          

           
</div>
<br/>



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


<!--
       <h1> <xsl:value-of select="document/product/creator_info_user_id"/></h1><br/>
       <h1> <xsl:value-of select="document/owner_user_id"/></h1>
-->

         
	
		    
	    

		

	<xsl:if test="document/empty_page_ext1 = 'false'" > 


<div class="box"  >
    <div>

           <TABLE class="bacgraundBoxTitle"  height="20" cellSpacing="4" cellPadding="0" width="159">
              <TBODY>
              <TR>
                <TD vAlign="center" ><FONT color="white" ><B>Additional</B></FONT>
               </TD>
              </TR>
  	     </TBODY>
  	   </TABLE>
                                            

           <TABLE cellSpacing="0" cellPadding="0" id="gallery_right" width="159" align="center"  class="bacgraundBoxLeft" border="0" rightmargin="0"  leftmargin="0" topmargin="0">
           <TBODY>
           <TR>
                <TD width="5"></TD>
            </TR>
            <!-- News Iten start -->
	    <xsl:for-each select="document/extpolicy_productlist1/extpolicy_product1">
                <TR>
                <TD width="5"></TD>
                <TD width="145">

            <xsl:if test="image != ''" >

   	        <xsl:if test="big_image_type != 'swf'">
   	           <xsl:if test="big_image_type != 'flv'">
   	           
   	           
                <A class="menu"  rel="lightbox">
                	<xsl:attribute name="HREF"><xsl:value-of select="image"/></xsl:attribute>
                	<xsl:attribute name="HREF"><xsl:value-of select="image"/></xsl:attribute>
                      <xsl:if test="/document/role_id = '1'">
						<xsl:if test="file_exist = 'true'">
							<xsl:attribute name="title">create shop</xsl:attribute>		       			
							<xsl:attribute name="alt">Productlist.jsp?create_site_by_id=<xsl:value-of select="product_id"/></xsl:attribute>
					   </xsl:if>
					 </xsl:if> 
		       <!-- Transfer of a line is processed  --><br/>
		       <img  alt=""  width="147" border="0"><xsl:attribute name="src"><xsl:value-of select="image"/></xsl:attribute></img>
		       </A>
               <A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute> 
                
                       <U>
   		       <xsl:for-each select="description/r">
		       <xsl:value-of select="."/> <br/>
		       </xsl:for-each>
                       </U>
		      </A>
                   </xsl:if>
                </xsl:if>


   	        <xsl:if test="big_image_type = 'flv'">

		   <embed src="http://www.jeroenwijering.com/embed/player.swf" 
			width="147" 
			searchbar="false"
			allowscriptaccess="always" 
			allowfullscreen="true" >
			<xsl:attribute name="flashvars">searchbar=false<![CDATA[&]]>file=http://<xsl:value-of select="document/host"/>/<xsl:value-of select="image"/><![CDATA[&]]>bufferlength=200</xsl:attribute>
		   </embed>


                    <A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><br/>
                      <U>
   		      <xsl:for-each select="description/r">
		      <xsl:value-of select="."/> <br/>
		      </xsl:for-each>
                      </U>
		    </A>
               </xsl:if>
	    </xsl:if>

            <xsl:if test="image = ''" >
                <A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><br/>
		      <!-- Transfer of a line is processed  -->
                      <U>
   		      <xsl:for-each select="description/r">
		      <xsl:value-of select="."/> <br/>
		      </xsl:for-each>
                      </U>
		</A>
	    </xsl:if>

   <br/>


   <!-- Removal buttons  , beginning -->
  
       <xsl:if test="/document/role_id = '2'">
	    
		<table>
		    <tbody>
		        <tr>
		         <td>
		                <form name="product_del"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="delete"></INPUT>
		                </form>
		         </td>
		         <td>

		                <form name="product_edit"  action="Productlist.jsp">
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="ext1" ></INPUT>
				<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
				<INPUT TYPE="submit" name="submit" value="Change"></INPUT>
		                </form>
		         </td>
		        </tr>
		    </tbody>
		</table>
            
    </xsl:if> 
    <!-- Removal buttons  , end  -->

   


		</TD>
                <TD width="5">
                </TD>
                </TR>
              <TR><TD width="155"  class="bacgraundLine" colSpan="3"  height="10" ></TD></TR>
  	    </xsl:for-each>
            <!-- News Iten end -->
              <TR>
                <TD align="right" width="155"  colSpan="3"  style="padding-right: 15px"><A class="menu"  title="All news" 
                  href="Productlist.jsp?catalog_id=-1"><FONT  class="text" ><U>Archive of news</U></FONT></A></TD>
              </TR>
              
              <TR>
			                <TD width="155" class="bacgraundLine2" colSpan="3" height="11" ></TD>
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

<p align="center">
<table width="100%"   > 
	<tbody>
	 <tr><td width="20%"></td>
		<xsl:for-each select="document/bottomlist/bottom">
					               <td>
							                <A ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><br/>
						                      <U>
									   		      <xsl:value-of select="name"/>
						                      </U>
											</A>
											

								        	<!-- Кнопки удаления  , начало -->
											<xsl:if test="/document/admin/post_manager != ''">
											<br/>
											<table>
											    <tbody>
											        <tr>
											         <td>
											               <form name="product_edit"  action="Productlist.jsp">
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="bottom"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
													<INPUT TYPE="submit" name="submit" value="Change"></INPUT>
											                </form>  
											         </td>
											         <td>
															<form name="product_del"  action="Productlist.jsp">
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
													<INPUT TYPE="submit" name="submit" value="Delete"></INPUT>
											                </form>
											               
											         </td>
											        </tr>
											    </tbody>
											</table>
											</xsl:if>
							           		<!-- Кнопки удаления  , конец  -->
										</td>
					  	    	</xsl:for-each>
	 <td width="20%"></td></tr>
    </tbody>
</table>
</p>
<br />


<font color="black">Internet shop . Copyright 2010 
		<A HREF="http://www.siteforyou.net"><font color="black">  FDIS Center Business Solutions Inc </font></A>.  All rights reserved
</font>
<hr size="" class="netscape4" />



<strong class="netscape4">
for user netscape 
</strong>
 
</div>

</TD>
<TD bgcolor="#ECEFF8" >.</TD>

</TR>
</TABLE>


</body>
</html>
</xsl:template>
</xsl:stylesheet>
