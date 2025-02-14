<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
<xsl:output method="html" indent="yes"/>
<xsl:output encoding="UTF-8"/>
<xsl:strip-space elements="*"/>

<xsl:template match="/">
<xsl:variable name="host" select="string(document/host)"/>
<xsl:variable name="role" select="document/role_id"/>
<xsl:variable name="page" select="number(document/offset)"/>
<html >


<head>

     <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"/>
     <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE"/>
     <meta name="robots content='index,follow'"/>
     <meta name="keywords" content="�������� ������� ���������, �������� ������� ����������, ���������� �������� �������, ������� �������� �������, ������� ���������� �������� �������, ������� �������� ������� ���������, �������� �������, ��������-�������, ���� �������� �������, ���� �������� ������� ���������, ���� ���������� �������� �������, ������ �������� ��������, �������� ������� � ������, ���������� ����, ���� ���������, ������� ����, ������� ���������� ����, ������� ���� ���������, ��� ������� �������� �������, ��� ������� �������� ������� ���������, ��� ������� ����, ��� ������� ���� ���������, �������� �������� ��������, �������� �������� �������� ���������, ������� �������� ������� ���������, ������ , �����-���������"/>
     <meta name="description" content="������� ���������� ������� �������� �������� ����� ��������. �������. ���������. �����������. �������������. "/>

     <title>CBS Portal</title>
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

<script src="http://vkontakte.ru/js/api/xd_connection.js?2" type="text/javascript"></script>

<script src="http://vkontakte.ru/js/api/openapi.js" type="text/javascript"></script>
<script type="text/javascript">
  VK.init({
    apiId: 2009925
  });
</script>


<SCRIPT language="JavaScript">
<![CDATA[
<!--




	///////////// wizard sart ////
function na_preload_img()
{ 
  var img_list = na_preload_img.arguments;
  if (document.preloadlist == null) 
    document.preloadlist = new Array();
  var top = document.preloadlist.length;
  for (var i=0; i < img_list.length; i++) {
    document.preloadlist[top+i] = new Image;
    document.preloadlist[top+i].src = img_list[i+1];
  } 
}

function na_change_img_src(name, nsdoc, rpath, preload)
{ 
  var img = eval((navigator.appName.indexOf('Netscape', 0) != -1) ? nsdoc+'.'+name : 'document.all.'+name);
  if (name == '')
    return;
  if (img) {
    img.altsrc = img.src;
    img.src    = rpath;
  } 
}

function na_restore_img_src(name, nsdoc)
{
  var img = eval((navigator.appName.indexOf('Netscape', 0) != -1) ? nsdoc+'.'+name : 'document.all.'+name);
  if (name == '')
    return;
  if (img && img.altsrc) {
    img.src    = img.altsrc;
    img.altsrc = null;
  } 
}
///////////// wizard end ////
// -->
]]>
</SCRIPT>

   
</head>


<body><xsl:attribute name="onload"><xsl:value-of select="concat('setCurrent(',$page,',',$role, ');Changehead();na_preload_img(); ')"/></xsl:attribute>
<TABLE cellSpacing="0" cellPadding="0" width="100%"  border="1" rightmargin="0" leftmargin="0" topmargin="0" bordercolor="#ECEFF8" >
<TR>
	<TD bgcolor="#ECEFF8" style="border: 1px solid #ECEFF8"></TD>
	<TD vAlign="top" Align="center" width="1030" style="border: 0px solid #ECEFF8">


	<a class="skipnav" href="#documentContent">Skip to content</a>

	<div>
		<div class="top">
		<TABLE cellSpacing="0" cellPadding="10"  height="120"  width="100%" class="bacgraundTop" border="0" rightmargin="0" leftmargin="0" topmargin="0">
		  <TBODY>
		  <TR>
		    <TD vAlign="top" Align="rigth"  width="30%" >
		       <img height='80'  width='80'  ><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/logo.gif')"/></xsl:attribute></img>
		    </TD>
		    <TD vAlign="center" Align="right"  width="70%">
		    <a  onclick="this.style.behavior='url(#default#homepage)'; this.setHomePage('http://www.siteoneclick.com'); return false;" href="#" ></a> <a href="javascript:window.external.AddFavorite('http://www.siteoneclick.com','www.siteoneclick.com')" ><font color="white" > <b>�������� � ���������</b></font> </a> <br/><font color="white" > <b>+1(516)777-0945</b></font><br/><font color="white" > <b></b></font> 
	          <br/>
			  <form name="searchform"  action="Productlist.jsp" >
	            <input id="search_value"  name="search_value" type="text"  size="20" alt="����� �� ����� ������"   title="����� �� ����� ������" tabindex="30001" ><xsl:attribute name="value"><xsl:value-of select="document/search_value"/></xsl:attribute></input>
			    <input id="search_char"  name="search_char" type="hidden" ></input>
			    <input id="searchquery"  name="searchquery" type="hidden" ></input>
	 	        <INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="offset" VALUE="0"  ></INPUT>
	            <input class="searchButton"  type="button" size="20" value="�����"  tabindex="30002" onClick="return top.search_word();return true"   />
	          </form>
			  <br/>
			</TD>
		  </TR>
		  </TBODY>
		</TABLE>
        </div>


        <hr size="" class="netscape4" />
        <div class="tabs">
		
        	<A href="Productlist.jsp?catalog_id=-2" class="plain"><font size="2">������� ��������</font></A>
        <A href="Domain.jsp" class="plain"><font size="2">����������� �������</font></A>

<!--        <A href="http://companies.siteoneclick.com" class="plain"><font size="2">���� �������</font></A>  -->
	 <A href="webmail/" class="plain"><font size="2">�����</font></A> 		<a href="Policy.jsp?page=about" class="plain"><font size="2"> � ��������</font></a>
		
        <xsl:if test="document/login != 'user'">
        	<A href="Productlist.jsp?action=logoff" class="plain"><font size="2">�����</font></A>        	
        </xsl:if>
          

          


	<!--
           <xsl:if test="/document/role_id = '2'">
           <xsl:if test="/document/site_id != '2'">
		<a href="ProductUserPost.jsp?parent_id=0"  class="plain" ><font size="2"> �������� ������ </font> </a>  
           </xsl:if>
           </xsl:if>
	-->


       <!--    <a href="Productlist.jsp?catalog_id=-1" class="plain" ><font size="2"> ������� </font> </a> -->
       <!--    <a href="Productlist.jsp?catalog_id=-2" class="selected" ><font size="2"> ���������� �������  </font> </a> -->

       <!--  ���������� ���� ��� ������ -->
       <!--
           <xsl:if test="document/login = 'user'">   
           <A href="Authorization.jsp?Login=" class="plain" ><font size="2"> ����������� </font> </A>
	   </xsl:if>
       -->

       <!--

           <xsl:if test="/document/role_id = '1'">
	 	    <xsl:choose> 

                           <xsl:when test="/document/user_site_id = '-1'">
				       <a href="Productlist.jsp?action=create_site" class="plain" ><font size="2"> ���������� ������� - ���� </font> </a>
			   </xsl:when> 

			   <xsl:otherwise>
				<a href="Productlist.jsp?action=login_usersite" class="plain" ><font size="2"> ���������� ������� - ���� </font> </a>
                           </xsl:otherwise> 

                    </xsl:choose> 
           </xsl:if>


           <xsl:if test="/document/site_id != '2'">
		<a href="Productlist.jsp?action=logoff_usersite" class="plain" ><font size="2"> ����� �� ������ ���������� </font> </a>   
           </xsl:if>

           <xsl:if test="/document/site_id = '2'">
           	<xsl:if test="/document/role_id = '2'" >
		<a href="Productlist.jsp?catalog_id=-4" class="plain" ><font size="2">����������� ����������</font> </a>
		<a href="Productlist.jsp?catalog_id=-5" class="plain" ><font size="2">����������� ����������</font> </a>
	        </xsl:if>
           </xsl:if>

	-->


           


         <!--  <a href="about.html" class="plain"> <font size="2">� ��������  </font></a> -->

        </div>


        <div class="personalBar">
	   <a href="Productlist.jsp?locale=ru">
                <img alt="Link icon" title="�������" height="13" width="16" border="0">
                	<xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/flag_ru.gif')"/></xsl:attribute>
                </img>
          </a>
	    <a href="Productlist.jsp?locale=en">
                <img alt="Link icon" title="English" height="13" width="16" border="0">
                	<xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/flag_en.gif')"/></xsl:attribute>
                </img>
          </a>
            <a href="webmail/">
                <img alt="Link icon" title="Link icon" height="16" width="16" border="0">
                	<xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/email.png')"/></xsl:attribute>
                </img>
				�����: 

               <xsl:if test="document/login = 'user'">   <!--  ���������� ���� ��� ������ -->
		 			<xsl:value-of select="document/login"/>
               </xsl:if>

               <xsl:if test="document/login != 'user'">   <!--  ���������� ���� ��� ������ -->
		 			<font color="red"><xsl:value-of select="document/login"/></font>
               </xsl:if>
            </a>
        </div>
         

        <div class="pathBar"   >
            <span>
		<font size="4" color="red" ><div  id="blink" > <xsl:value-of select="document/message"/></div></font>
            </span>
        </div>



        <div class="personalBar">
            <span>            	
      				<xsl:if test="count(document/parent/parent-item) &gt; 0">
	                     <a href="Productlist.jsp?catalog_id=-2" class="catalog" alt="�������� � ������ �����������" title="�������� � ������ �����������">
	                       <U><font size="2" >��� �������</font></U>
	                     </a>&#160; &#187; 
	     								
				        <xsl:for-each select="document/parent/parent-item">										
							 <xsl:if test="code != '-2'">												
								<A ><xsl:attribute name="HREF"><xsl:value-of select="url"/></xsl:attribute>
							        <U><font size="2" > <xsl:value-of select="item"/></font> </U> 
							    </A>&#160; &#187; 
							 </xsl:if>													
				        </xsl:for-each>
      				</xsl:if>      			
            </span>
        </div>

        <hr size="" class="netscape4" />

    </div>



	<table class="columns">

	    <tbody>
	        <tr>
	            <td class="left">
	               <xsl:if test="document/login = 'user'">   <!--  ���������� ���� ��� ������ -->

			       <div class="box" > 

			          <div style="height:20px; TEXT-ALIGN: left"   class="bacgraundBoxTitle">
					  <!-- head for porlet login   --> 
				          <TABLE height="20" cellSpacing="4" cellPadding="0" width="100%" >
				              <TBODY>
				              <TR>
				                <TD vAlign="center" ><FONT color="white" ><B>�����������</B></FONT></TD>
				              </TR>
				  	     	  </TBODY>
				  	   	  </TABLE>
					  <!-- porlet login   --> 
			          </div>
			          	<div class="body">
				            <div class="content odd">
						    <A href="Authorization.jsp?Login=newuser" class="plain" ><font size="3" color='red'  > ����������� </font> </A><br /><br />
			                <form action="Authorization.jsp"  method="post">
			                     <font size="1" > <strong>������������</strong></font> <br />
			                     <INPUT  title="������������" tabindex="10001" SIZE="12" AUTOCOMPLETE="off" TYPE="TEXT" NAME="Login" >
									<xsl:attribute name="value">
										<xsl:value-of select="document/login"/>
									</xsl:attribute>		
			        	     	 </INPUT>
			                     <br />
			                     <font size="1" ><strong>������</strong></font>
					             <br />
					             <INPUT title="������" tabindex="10002"  SIZE="12" AUTOCOMPLETE="off" TYPE="PASSWORD" NAME="Passwd1" ></INPUT>
			                     <br />
			                     <br />
			                     <input class="context searchButton"   type="submit" name="submit"  value="�����" tabindex="10003" />
			                </form>
				 	        </div>
    
				           <div class="content even"> 
				                <a href="Authorization.jsp?Login=newuser">
				                   <img alt="Link icon" title="Link icon" height="11" width="6" border="0">
				                   		<xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/linkTransparent.gif')"/></xsl:attribute>
				                   </img>
				                   ������ ������ ?
				                </a>
				           </div>
			        	</div>
			       </div>
			       <br/>
	  			   </xsl:if>

			  		<xsl:if test="document/empty_page_co1 = 'false'" > 
					  <div class="box"  >
					      <div style="height:21px;width:159px; TEXT-ALIGN: left" class="bacgraundBoxTitle"  >


					           <TABLE class="text"  height="20" cellSpacing="4" cellPadding="0" width="100%"  >
					              <TBODY>
					              <TR>
					                <TD vAlign="center" ><FONT color="white" ><B><!--�������--></B></FONT>
					               </TD>
					              </TR>
					  	     </TBODY>
					  	   </TABLE>

					    </div>
					    <div>
						
			           <TABLE cellSpacing="0" cellPadding="0" width="100%" align="center" class="bacgraundBoxLeft" border="0" rightmargin="0"  leftmargin="0" topmargin="0">
			           <TBODY>
			           <tr><td><br/></td></tr>
			            <!-- News Iten start -->
				    	<xsl:for-each select="document/coproductlist1/coproduct1">
			              <TR>
			                <TD width="12"></TD>
			                <TD width="140">
				                <A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute>
				              
						      	  <!-- ������������ ������� ������  -->

					              <xsl:if test="image != ''">
						      		<img  alt=""  width="140" border="0"><xsl:attribute name="src"><xsl:value-of select="image"/></xsl:attribute></img>
				                  </xsl:if>
			                      <U>
						   		      <xsl:for-each select="description/r">
								      <xsl:value-of select="."/> <br/>
								      </xsl:for-each>
			                      </U>
								</A>
								
								<xsl:if test="/document/admin/post_manager != ''">
			               <TABLE STYLE="padding-top: 5px">
			               <tr>			                
			               	<td>
			               	<!-- ������ ��������  , ������ -->
						        
							                <form name="product_del"  action="Productlist.jsp">
									<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
									<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
									<INPUT TYPE="submit" name="submit" value="�������"></INPUT>
							                </form>
							</td>
							<td>
							                <form name="product_edit"  action="Productlist.jsp">
									<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
									<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="co1" ></INPUT>
									<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
									<INPUT TYPE="submit" name="submit" value="��������"></INPUT>
							                </form>
						    
				                <!-- ������ ��������  , �����  -->
			               	</td>
						   </tr>
						   </TABLE>
						   </xsl:if>		                

							</TD>
			                <TD width="7"></TD>
			               </TR>
			               
			              <TR>
						  	<TD colspan="3" width="155" class="bacgraundLine" colSpan="3"  height="10" ></TD>
						  </TR>
			  	    	</xsl:for-each>
			            <!-- News Iten end -->

			              <TR>
			                <TD colspan="3" align="right" width="155"  colSpan="3" style="padding-right: 15px" ><A class="menu"  title="��� �������" 
			                  href="Productlist.jsp?catalog_id=-6"><FONT  class="text" ><U>����� �����������</U></FONT></A>
							 </TD>
			              </TR>
              
			              <TR>
			                <TD width="155" class="bacgraundLine2" colSpan="3" height="11" ></TD>
			              </TR>

			             </TBODY>
			             </TABLE>
			       </div>
			     </div>
			   <br/>
			  </xsl:if>

					 <div class="box"  >
					      <div style="height:21px;width:159px; TEXT-ALIGN: left"  class="bacgraundBoxTitle" >
					           <TABLE class="text"  height="20" cellSpacing="4" cellPadding="0" width="100%"  >
					              <TBODY>
					              <TR>
					                <TD vAlign="center" ><FONT color="white" ><B>������� ��������</B></FONT></TD>
					              </TR>
					  	     	  </TBODY>
					  	       </TABLE>
					      </div>
					      <div>
				                                                               
					       <TABLE cellSpacing="0" cellPadding="0" width="100%" align="center"  class="bacgraundBoxLeft" border="0" rightmargin="0"  leftmargin="0" topmargin="0">
					           <TBODY>
					           <tr><td><br/></td></tr>
					            <!-- News Iten start -->
						    	<xsl:for-each select="document/newslist/news">
					                <TR>
						                <TD width="12"></TD>
						                <TD width="145">
							                <A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><br/>
									      	<!-- ������������ ������� ������  -->
								              <xsl:if test="image != ''">
									      		<img  alt=""  width="140" border="0"><xsl:attribute name="src"><xsl:value-of select="image"/></xsl:attribute></img>
							                  </xsl:if>

						                      <U>
									   		      <xsl:for-each select="description/r">
											      <xsl:value-of select="."/> <br/>
											      </xsl:for-each>
						                      </U>
											</A>
											<br/>

								        	<!-- ������ ��������  , ������ -->
											<xsl:if test="document/role_id = '2'">
											<table>
											    <tbody>
											        <tr>
											         <td>
											                <form name="product_del"  action="Productlist.jsp">
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
													<INPUT TYPE="submit" name="submit" value="�������"></INPUT>
											                </form>
											         </td>
											         <td>

											                <form name="product_edit"  action="Productlist.jsp">
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="news"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
													<INPUT TYPE="submit" name="submit" value="��������"></INPUT>
											                </form>
											         </td>
											        </tr>
											    </tbody>
											</table>
											</xsl:if>
							           		<!-- ������ ��������  , �����  -->
										</TD>

						                <TD width="5"></TD>
					                </TR>
					              	<TR>
										<TD width="155" class="bacgraundLine" colSpan="3"  height="10" ></TD>
									</TR>
					  	    	</xsl:for-each>
					            <!-- News Iten end -->
					              <TR>
					                <TD align="right" width="155"  colSpan="3"  style="padding-right: 15px"><A class="menu"  title="��� �������" 
					                  href="Productlist.jsp?catalog_id=-6"><FONT  class="text" ><U>����� �����������</U></FONT></A>
									</TD>
					              </TR>
					              <TR>
					                <TD width="155" class="bacgraundLine2" colSpan="3" height="11" ></TD>
					              </TR>

					            </TBODY>
					        </TABLE>
					      </div>
					 </div>
                                   <br/>
				


	            </td>

	            <td valign="center"  align="center"   class="main"    >

	         	<xsl:variable name="user_id" select="number(/document/owner_user_id)"/> 


	        	<!-- �������� ������ ��� ���������� ������ , ������  -->

	        	<xsl:if test="document/role_id = '2'">
					<div class="footer">������� ��� ������, ���� ��  ������  �������� ���������� �����
					<A>
						<xsl:attribute name="HREF"><xsl:value-of select="document/admin/post_manager"/></xsl:attribute>
						<img border="0" alt="Change content" >
							<xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/refresh.png')"/></xsl:attribute>
						</img>
					</A>
				    </div>
	      		</xsl:if>
				
				
				<table id="gallery" style="height:610px;width:593px;"  border="0"  cellSpacing="0" cellPadding="0"  >
			        
			        	<!-- �����  -->
			        <tr>
			        	<td vAlign="center"  align="center" class="pagesLink">
			        	<form id="formToPage" name="searchform2"  action="Productlist.jsp" method="post"><input name="offset" type="hidden" value="0" ></input></form>
			        	<xsl:if test="((($page div 10) +1) &lt; 21)">
					              <table class="pagesTable" style="width: 80%">
					              <tr>
								        <td><a id="next" href="javascript:showPrev()">Prev</a></td>					       
								        <td id="d1"><a id="link1" href="javascript:showStr('link1')">1</a></td>								       
								        <td id="d2"><a id="link2" href="javascript:showStr('link2')">2</a></td>								       
								        <td id="d3"><a id="link3" href="javascript:showStr('link3')">3</a></td>
								        <td id="d4"><a id="link4" href="javascript:showStr('link4')">4</a></td>								       
								        <td id="d5"><a id="link5" href="javascript:showStr('link5')">5</a></td>								       
								        <td id="d6"><a id="link6" href="javascript:showStr('link6')">6</a></td>
								        <td id="d7"><a id="link7" href="javascript:showStr('link7')">7</a></td>								       
								        <td id="d8"><a id="link8" href="javascript:showStr('link8')">8</a></td>								       
								        <td id="d9"><a id="link9" href="javascript:showStr('link9')">9</a></td>
								        <td id="d10"><a id="link10" href="javascript:showStr('link10')">10</a></td>								       
								        <td id="d11"><a id="link11" href="javascript:showStr('link11')">11</a></td>								       
								        <td id="d12"><a id="link12" href="javascript:showStr('link12')">12</a></td>
								        <td id="d13"><a id="link13" href="javascript:showStr('link13')">13</a></td>								       
								        <td id="d14"><a id="link14" href="javascript:showStr('link14')">14</a></td>								       
								        <td id="d15"><a id="link15" href="javascript:showStr('link15')">15</a></td>
								        <td id="d16"><a id="link16" href="javascript:showStr('link16')">16</a></td>								       
								        <td id="d17"><a id="link17" href="javascript:showStr('link17')">17</a></td>								       
								        <td id="d18"><a id="link18" href="javascript:showStr('link18')">18</a></td>
								        <td id="d19"><a id="link19" href="javascript:showStr('link19')">19</a></td>								       
								        <td id="d20"><a id="link20" href="javascript:showStr('link20')">20</a></td>								       				        
								        <td><a id="next" href="javascript:showNext()">Next</a></td>
								  </tr>
								  </table>      
								  </xsl:if>
								  
								  <xsl:if test="((($page div 10) +1) &gt; 99980)">
					              <table class="pagesTable" style="width: 80%">
					              <tr>
								        <td><a id="next" href="javascript:showPrev()">Prev</a></td>								        
								        <td id="d1"><a id="link1" href="javascript:showStr('link1')">99981</a></td>								       
								        <td id="d2"><a id="link2" href="javascript:showStr('link2')">99982</a></td>								        
								        <td id="d3"><a id="link3" href="javascript:showStr('link3')">99983</a></td>	
								        <td id="d4"><a id="link4" href="javascript:showStr('link4')">99984</a></td>								       
								        <td id="d5"><a id="link5" href="javascript:showStr('link5')">99985</a></td>								       
								        <td id="d6"><a id="link6" href="javascript:showStr('link6')">99986</a></td>
								        <td id="d7"><a id="link7" href="javascript:showStr('link7')">99987</a></td>								       
								        <td id="d8"><a id="link8" href="javascript:showStr('link8')">99988</a></td>								       
								        <td id="d9"><a id="link9" href="javascript:showStr('link9')">99989</a></td>
								        <td id="d10"><a id="link10" href="javascript:showStr('link10')">99990</a></td>								       
								        <td id="d11"><a id="link11" href="javascript:showStr('link11')">99991</a></td>								       
								        <td id="d12"><a id="link12" href="javascript:showStr('link12')">99992</a></td>
								        <td id="d13"><a id="link13" href="javascript:showStr('link13')">99993</a></td>								       
								        <td id="d14"><a id="link14" href="javascript:showStr('link14')">99994</a></td>								       
								        <td id="d15"><a id="link15" href="javascript:showStr('link15')">99995</a></td>
								        <td id="d16"><a id="link16" href="javascript:showStr('link16')">99996</a></td>								       
								        <td id="d17"><a id="link17" href="javascript:showStr('link17')">99997</a></td>								       
								        <td id="d18"><a id="link18" href="javascript:showStr('link18')">99998</a></td>
								        <td id="d19"><a id="link19" href="javascript:showStr('link19')">99999</a></td>								       
								        <td id="d20"><a id="link20" href="javascript:showStr('link20')">100000</a></td>							        					        
								        <td><a id="next" href="javascript:showNext()">Next</a></td>
								  </tr>
								  </table>
								  </xsl:if>
								  
								  <xsl:if test="((($page div 10) +1) &lt; 99981)">
								  <xsl:if test="((($page div 10) +1) &gt; 20)">
					              <table class="pagesTable" style="width: 80%">
					              <tr>
								        <td><a id="next" href="javascript:showPrev()">Prev</a></td>								       
								        <td id="d1"><a id="link1" href="javascript:showStr('link1')"><xsl:value-of select="($page div 10)"/></a></td>								      
								        <td id="d2"><a id="link2" href="javascript:showStr('link2')"><xsl:value-of select="(($page div 10) +1)"/></a></td>								        
								        <td id="d3"><a id="link3" href="javascript:showStr('link3')"><xsl:value-of select="(($page div 10) +2)"/></a></td>
								        <td id="d4"><a id="link4" href="javascript:showStr('link4')"><xsl:value-of select="(($page div 10) +3)"/></a></td>								       
								        <td id="d5"><a id="link5" href="javascript:showStr('link5')"><xsl:value-of select="(($page div 10) +4)"/></a></td>								       
								        <td id="d6"><a id="link6" href="javascript:showStr('link6')"><xsl:value-of select="(($page div 10) +5)"/></a></td>
								        <td id="d7"><a id="link7" href="javascript:showStr('link7')"><xsl:value-of select="(($page div 10) +6)"/></a></td>								       
								        <td id="d8"><a id="link8" href="javascript:showStr('link8')"><xsl:value-of select="(($page div 10) +7)"/></a></td>								       
								        <td id="d9"><a id="link9" href="javascript:showStr('link9')"><xsl:value-of select="(($page div 10) +8)"/></a></td>
								        <td id="d10"><a id="link10" href="javascript:showStr('link10')"><xsl:value-of select="(($page div 10) +9)"/></a></td>								       
								        <td id="d11"><a id="link11" href="javascript:showStr('link11')"><xsl:value-of select="(($page div 10) +10)"/></a></td>								       
								        <td id="d12"><a id="link12" href="javascript:showStr('link12')"><xsl:value-of select="(($page div 10) +11)"/></a></td>
								        <td id="d13"><a id="link13" href="javascript:showStr('link13')"><xsl:value-of select="(($page div 10) +12)"/></a></td>								       
								        <td id="d14"><a id="link14" href="javascript:showStr('link14')"><xsl:value-of select="(($page div 10) +13)"/></a></td>								       
								        <td id="d15"><a id="link15" href="javascript:showStr('link15')"><xsl:value-of select="(($page div 10) +14)"/></a></td>
								        <td id="d16"><a id="link16" href="javascript:showStr('link16')"><xsl:value-of select="(($page div 10) +15)"/></a></td>								       
								        <td id="d17"><a id="link17" href="javascript:showStr('link17')"><xsl:value-of select="(($page div 10) +16)"/></a></td>								       
								        <td id="d18"><a id="link18" href="javascript:showStr('link18')"><xsl:value-of select="(($page div 10) +17)"/></a></td>
								        <td id="d19"><a id="link19" href="javascript:showStr('link19')"><xsl:value-of select="(($page div 10) +18)"/></a></td>								       
								        <td id="d20"><a id="link20" href="javascript:showStr('link20')"><xsl:value-of select="(($page div 10) +19)"/></a></td>									        					        
								        <td><a id="next" href="javascript:showNext()">Next</a></td>
								  </tr>
								  </table>      
								  </xsl:if>								        
								  </xsl:if>
			        	</td>
			        </tr>    

					<xsl:if test="document/empty_page = 'true'" > 
			        <tr>
			            <td valign="top"   align="center"  >
						<br/>
						<br/>
							�� ���� �������� ������ ���.
			            </td>
			        </tr>
					</xsl:if>
					
					<tr>
			            <td valign="top"   align="center"  >
						<br/>
						
			            </td>
			        </tr>


			        <tr>
			            <td valign="top"   align="center"  >


				        <!-- ����� ������� �������� �� ���� product , ������ -->
				        <xsl:for-each select="document/list/product">
					<table height="50" cellSpacing="0" cellPadding="0" >
					    <tbody height="50" >
					    	<tr>
					    		<td>
					    		  <DIV style="height:20px;" class="bacgraundProductHead"  >
						              <TABLE  cellSpacing="0" cellPadding="0" width="100%"  ><TBODY>
						              <TR><TD valign="bottom "  align="center" > 
						                <FONT color="white" >
										<B><xsl:if test="rigth/amount != '0'"><b> <xsl:value-of select="rigth/name"/></b></xsl:if>
										<xsl:if test="rigth/amount = '0'"> <xsl:value-of select="rigth/name"/></xsl:if></B>
										</FONT>
						              </TD></TR>
						  	      	  </TBODY></TABLE>
								  	</DIV>
					    		</td>
					    		
					    		<td style="width: 10px; padding-left: 5px"> </td>
					    		
					    		<td>
					    		  <xsl:if test="left/name != ''">
						    		  <DIV style="height:20px; " class="bacgraundProductHead"   >
								         <TABLE  cellSpacing="0" cellPadding="0" width="100%"  >
								              <TR>
												<TD valign="bottom "  align="center" > 
								                <FONT color="white" ><B>
													<xsl:if test="left/amount != '0'"><b> <xsl:value-of select="left/name"/></b></xsl:if>
													<xsl:if test="left/amount = '0'"><xsl:value-of select="left/name"/></xsl:if>
												</B></FONT>
								               </TD>
								              </TR>								     
								  	     </TABLE>
									  </DIV>
								  </xsl:if>
					    		</td>
					    	
					    	</tr>
					        <tr>
					            <td valign="top" class="productBox" >                
								   
									<div >
									  <div class="body">
									    <div  valign="center"   align="center"  class="description" >
									   
										<table width="298" border="0" cellSpacing="0" cellPadding="2" >
										    <tbody width="298"   >
												<!-- ������ ���� �������� -->
												<xsl:if test="rigth/icon != ''"> 
										        <tr>											    
									        	    <td valign="center"   align="center"  width="100%" height="100">										        	    
														<A>
															<xsl:if test="/document/role_id != 2"> 
																<xsl:attribute name="HREF"><xsl:value-of select="rigth/icon"/></xsl:attribute>
																<xsl:attribute name="rel">lightbox</xsl:attribute>
																<xsl:attribute name="alt"><xsl:value-of select="rigth/policy_url"/></xsl:attribute>
															</xsl:if>
															<xsl:if test="/document/role_id = 2"> 
																<xsl:attribute name="HREF"><xsl:value-of select="rigth/policy_url"/></xsl:attribute>
															</xsl:if>
															
															<xsl:attribute name="title"><xsl:value-of select="rigth/name"/> - ������� ���� ������ </xsl:attribute>
															<IMG  alt=""  width="290" border="0">
																<xsl:attribute name="src"><xsl:value-of select="rigth/icon"/></xsl:attribute>
															</IMG>
														</A>														
										            </td>									     		
										        </tr>
										        </xsl:if>
										        
										        <tr>											    
									        	    <td  width="100%" style="padding: 10px">
														<a > <xsl:attribute name="HREF"><xsl:value-of select="rigth/policy_url"/></xsl:attribute>
											   				    	  <xsl:for-each select="rigth/description/r">
																    <xsl:value-of select="."/> 
														   	    	  </xsl:for-each>
														</a>
										            </td>									     		
										        </tr>
										    </tbody>
										</table>						     		

									    
										</div>
									  </div>
									</div>


	            				</td>
								
								<td width="3"><br/></td>
								
								<xsl:if test="left/name != ''">
						        <td class="productBox">
								<div  >
								  <div class="body" >
								    <div     align="center"  class="description" >

									<table width="298" border="0" cellSpacing="0" cellPadding="2">
									    <tbody width="298">
									    	<xsl:if test="left/icon != ''"> 
									        <tr>										    
								        	    <td  align="center"  width="100%" >								        	    	 
													<A>
														<xsl:if test="/document/role_id != 2"> 
																<xsl:attribute name="HREF"><xsl:value-of select="left/icon"/></xsl:attribute>
																<xsl:attribute name="rel">lightbox</xsl:attribute>
																<xsl:attribute name="alt"><xsl:value-of select="left/policy_url"/></xsl:attribute>
															</xsl:if>
															<xsl:if test="/document/role_id = 2"> 
																<xsl:attribute name="HREF"><xsl:value-of select="left/policy_url"/></xsl:attribute>
															</xsl:if>
														
														<xsl:attribute name="title"><xsl:value-of select="left/name"/>- ������� ���� ������</xsl:attribute>
														<IMG  alt=""  width="290" border="0">
															<xsl:attribute name="src"><xsl:value-of select="left/icon"/></xsl:attribute>															 
														</IMG>
													</A>													
									            </td>
									        </tr>
									        </xsl:if>
									        
									        <tr>											    
								        	    <td  width="100%" style="padding: 10px">
													<a > <xsl:attribute name="HREF"><xsl:value-of select="left/policy_url"/></xsl:attribute>
										   				    	  <xsl:for-each select="left/description/r">
															    <xsl:value-of select="."/> 
													   	    	  </xsl:for-each>
													</a>
									            </td>									     		
										    </tr>
									    </tbody>
									</table>						     		

								    
								    </div>
								  </div>
								</div>

						      </td>
								</xsl:if>
								
								<xsl:if test="left/name = ''">
									<td width="298"><br/></td>
								</xsl:if>

	        				</tr>
	        				
	        					        				
	        				<xsl:choose> 

					   <!-- ������ ��������  , ������ -->
					        <xsl:when test="/document/role_id = '2'">
					        		<tr>
			        					<td colspan="3">
			        						<br/>
			        					</td>
			        				</tr>						
							        <tr >
							         <td class="buttons-line">
							        	
								        <form name="product_edit"  action="Productlist.jsp" style="float: left">
											<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
											<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="product" ></INPUT>
											<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="rigth/product_id"/></xsl:attribute></INPUT>
											<INPUT TYPE="submit" name="submit" value="��������" ></INPUT>
							        	</form>
							          
						                <form name="product_del"  action="Productlist.jsp" style="float: left; margin-left: 5px">
											<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
											<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="rigth/product_id"/></xsl:attribute></INPUT>
											<INPUT TYPE="submit" name="submit" value="�������" ></INPUT>
						                </form>						                
							         </td>
		        					
		        					<td width="3px"></td>
		        					
		        					<xsl:if test="left/product_id != ''">
								         <td class="buttons-line">							         	 
									        <form name="product_edit"  action="Productlist.jsp" style="float: left">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="product" ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="left/product_id"/></xsl:attribute></INPUT>
												<INPUT TYPE="submit" name="submit" value="��������" style="float: left"></INPUT>
									        </form>
								        
									        <form name="product_del"  action="Productlist.jsp" style="float: left; margin-left: 5px">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="left/product_id"/></xsl:attribute></INPUT>
												<INPUT TYPE="submit" name="submit" value="�������" style="float: left"></INPUT>
									        </form>								       							    
								         </td>
							         </xsl:if>							         
							        </tr>
					    	</xsl:when> 
					    <!-- ������ ��������  , �����  -->




					   <!--  for Edit  User context  ������ ��������  , ������ -->
					   <xsl:when test="/document/role_id = '1'">
					   				
						<xsl:if test="(rigth/user_id = string(number($user_id))) or (left/user_id = string(number($user_id)))" >
												<tr>
						        					<td colspan="3">
						        						<br/>
						        					</td>
						        				</tr>
						</xsl:if>
						
						
						
								<tr>
					               <xsl:if test="rigth/user_id = string(number($user_id))" >
												
							          <td nowrap="nowrap" class="buttons-line"> 
							                <form name="product_edit"  action="Productlist.jsp" style="float: left">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="edit_biz_info" ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="rigth/product_id"/></xsl:attribute></INPUT>
												<INPUT TYPE="submit" name="submit" value="��������"></INPUT>
							                </form>
							          
							                <form name="product_del"  action="Productlist.jsp" style="float: left; margin-left: 5px">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="rigth/product_id"/></xsl:attribute></INPUT>
												<INPUT TYPE="submit" name="submit" value="�������"></INPUT>
							                </form>
							            	
							            	<!-- 
							                <form name="product_up_position"  action="Productlist.jsp" style="float: left">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="up_position"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="rigth/product_id"/></xsl:attribute></INPUT>
												<INPUT TYPE="submit" name="submit" value="�������"></INPUT>
							                </form>
							         
							                <form name="product_set_color"  action="Productlist.jsp" style="float: left">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="set_color"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="rigth/product_id"/></xsl:attribute></INPUT>
												<INPUT TYPE="submit" name="submit" value="����������"></INPUT>
							                </form>
							                -->
							         </td>

								</xsl:if>
					            <xsl:if test="rigth/user_id != string(number($user_id))" >
					                        <td  width="280"  ></td>
								</xsl:if>			     

							        
								<td width="3px"></td>

					 		    <xsl:if test="left/user_id = string(number($user_id))" >
							         <td nowrap="nowrap" class="buttons-line">
					                    <xsl:if test="left/product_id != ''"> 
							                <form name="product_edit"  action="Productlist.jsp" style="float: left"> 
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="edit_biz_info" ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="left/product_id"/></xsl:attribute></INPUT>
												<INPUT TYPE="submit" name="submit" value="��������"></INPUT>
							                </form>
							        	
							                <form name="product_del"  action="Productlist.jsp" style="float: left; margin-left: 5px">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="left/product_id"/></xsl:attribute></INPUT>
												<INPUT TYPE="submit" name="submit" value="�������"></INPUT>
							                </form>
								         	
								         	<!--  
							                <form name="product_up_position"  action="Productlist.jsp">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="up_position"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="left/product_id"/></xsl:attribute></INPUT>
												<INPUT TYPE="submit" name="submit" value="�������"></INPUT>
								            </form>
								                 
							                <form name="product_set_color"  action="Productlist.jsp">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="set_color"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="left/product_id"/></xsl:attribute></INPUT>
												<INPUT TYPE="submit" name="submit" value="����������"></INPUT>
							                </form>
							                -->
							        	</xsl:if>
							         </td>
								</xsl:if>


						        <xsl:if test="left/user_id != string(number($user_id))" >
						             <td  width="280"  ></td>
								</xsl:if>			     

							</tr>
							    
					   </xsl:when> 

					   </xsl:choose>
					   
					    </tbody>
					</table> 
					<br/>
	    			<!-- ������ ��������  , �����  -->

	    			</xsl:for-each>
	    			<!-- ����� ������� �������� �� ���� product , ����� -->


	           </td>
	     </tr>


	     <tr>
	          <td valign="top"   align="center"  >

	    <!-- ����� ������� �������� �� ���� product , ����� -->
	    <!-- ��� �� ������ ������  -->
	<br/>
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
							<B> ����� - ������� � ������ <xsl:value-of select="document/product/name"/></B>   
						</font>
	              </TD>
	              <TD  width="5%" vAlign="center"  Align="right" >
					<A href="javascript:switchWindoForum()" > 
						<IMG id='forum_div'  title="����������" align="right">
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

	            <!-- News Iten start -->
		    <xsl:for-each select="document/product_blog_list/product_blog">
	                <TR>
	                <TD width="5"></TD>
	                <TD width="*">
			<H1>
			 <font size="1" ><A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><b><xsl:value-of select="parent_title"/></b></A>  / 
			 	<IMG border="0" height="20" name="Nindex_14_03" width="20">
			 		<xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/user.gif')"/></xsl:attribute>
			 	</IMG>   
			 	<xsl:value-of select="author"/>  /���������: <xsl:value-of select="cdate"/>
			 </font> 
			</H1>

			      <b> <xsl:value-of select="name"/></b>
			      <br/>
			      <!-- ������������ ������� ������  -->
	   		      <xsl:for-each select="description/r">
			      <xsl:value-of select="."/> <br/>
			      </xsl:for-each>
		      

			<br/>
			
			<xsl:choose>
			   <!-- ������ �������� , ������ ��� ������  -->
			<xsl:when test="/document/admin/post_manager != ''">
			<table>
			    <tbody>
			        <tr>
			         <td>
			                <form name="product_del"  action="Productlist.jsp">
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
					<INPUT TYPE="submit" name="submit" value="�������"></INPUT>
			                </form>
			         </td>
			         <td>

			                <form name="product_edit"  action="Productlist.jsp">
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="blog" ></INPUT>
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_parent_id"><xsl:attribute name="value"><xsl:value-of select="product_parent_id"/></xsl:attribute></INPUT>
					<INPUT TYPE="submit" name="submit" value="��������"></INPUT>
			                </form>
			         </td>
			        </tr>
			    </tbody>
			</table>
			</xsl:when>
			   <!-- ������ ��������  , �����  ��� ������  -->

			   <!-- ������ �������� , ������ ��� ������  -->

	       <xsl:when test="user_id = string(number($user_id))" >

			<table>
			    <tbody>
			        <tr>
			         <td>
			                <form name="product_del"  action="Productlist.jsp">
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
					<INPUT TYPE="submit" name="submit" value="�������"></INPUT>
			                </form>
			         </td>
			         <td>

			                <form name="product_edit"  action="Productlist.jsp">
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="blog" ></INPUT>
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
					<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_parent_id"><xsl:attribute name="value"><xsl:value-of select="product_parent_id"/></xsl:attribute></INPUT>
					<INPUT TYPE="submit" name="submit" value="��������"></INPUT>
			                </form>
			         </td>
			        </tr>
			    </tbody>
			</table>
			</xsl:when>
			</xsl:choose>
			   <!-- ������ ��������  , �����  ��� ������  -->

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

	<br/>
	    <!-- ��� �� ������ �����  -->




		            </td>
		        </tr>
		</table>

	    <br/>



	             </td>
           
	             <td class="right">
	             	


	     <xsl:choose> 
	     <xsl:when test="/document/role_id = '1'">
	     
	     
	      <div class="box"  >
	       <div style="height:21px;width:159px; TEXT-ALIGN: left" class="bacgraundBoxTitle">
	           <TABLE class="text"   width="100%"   height="20" cellSpacing="4" cellPadding="0" >
	              <TBODY>
	              <TR>
	                <TD vAlign="center" ><FONT color="white" ><B>��� �������</B></FONT>
	               </TD>
	              </TR>
	  	     </TBODY>
	  	   </TABLE>
			</div>
	                                                               

	           <TABLE cellSpacing="0" cellPadding="0" width="100%" align="center"  class="bacgraundBoxLeft" border="0" rightmargin="0"  leftmargin="0" topmargin="0">
	           <TBODY>
					<TR><TD width="5"></TD><TD><br/></TD></TR>
	              <TR><TD width="5"></TD><TD><A class="menu"  href="Productlist.jsp?action=login_usersite" alt="��� ������ ��� �������� �������"  > ��� ������� </A></TD></TR>

	              <TR><TD width="159" colspan="2"  class="bacgraundLine"  height="10" ></TD></TR>

              
	             <TR><TD width="5"></TD><TD><A class="menu"  href="#" alt="��������� ��������"  >� �������� <xsl:value-of select="/document/balans" /> ������ </A></TD></TR>
	 		 
		 		 <TR><TD width="159" colspan="2"  class="bacgraundLine"  height="10" ></TD></TR>
			 
				 <TR><TD width="5"></TD><TD><A class="menu"  href="Policy.jsp?page=pay" alt="��������� �������"  >��������� ������� </A></TD></TR>

		 		 <TR><TD width="159" colspan="2"  class="bacgraundLine"  height="10" ></TD></TR>

<TR><TD width="5"></TD><TD>

<form action="https://www.paypal.com/cgi-bin/webscr" method="post">
<input type="hidden" name="cmd" value="_s-xclick"/>
<input type="hidden" name="hosted_button_id" value="SK9WAV9DY99BY"/>
<input type="submit" value="��������� ������"  border="0" name="submit" alt="��������� ���� ��������� ������ ����� PayPal."/>
</form>

</TD></TR>	              


	              <TR><TD width="155" class="bacgraundLine2" colSpan="2" height="11" ></TD></TR>

	             </TBODY>
	             </TABLE>
	       
	     </div>
	    
	   

	     </xsl:when>
	   </xsl:choose> 
	  <br/>
	   <div>

	             <TABLE height="21" cellSpacing="4" cellPadding="0" width="159" class="bacgraundBoxTitle" >
	             <TBODY>
	             <TR>
	                <TD vAlign="rigth" ><FONT color="white" ><B>�������</B></FONT></TD>
	              </TR>
	  	     </TBODY>
	  	   </TABLE>


	           <div class="scroll" style="overflow-y:auto;overflow-x:hidden; width:100%; border: none;" >

	             <TABLE cellSpacing="0" cellPadding="0" width="159" >
	             <TBODY>


	           <xsl:for-each select="document/catalog/catalog-item">


	           <xsl:if test="item != ''">
		   <xsl:if test="code != '-1'">
		   <xsl:if test="code != '-2'">
		   <xsl:if test="code != '-3'">


		   <xsl:if test="code != '-6'">
		   <xsl:if test="code != '-7'">
		   <xsl:if test="code != '-8'">


	             <xsl:if test="subcatalog-item/subitem = ''">
	              <TR><TD width="159" colspan="2"  class="bacgraundLine"  height="10" ></TD></TR>

	             <xsl:if test="code != selected">
	              <TR>
	                <TD  width="10"  bgcolor="#DFE3EF"  height="12" >
			</TD>
	                <TD  width="149"  bgcolor="#DFE3EF" height="12" >
			<A ><xsl:attribute name="HREF"><xsl:value-of select="url"/></xsl:attribute>
		        <font size="2" ><xsl:value-of select="item"/></font>
		       </A>
			</TD>
	              </TR>
		     </xsl:if>


	             <xsl:if test="code = selected">
	              <TR>
	                <TD  width="10"  bgcolor="#DFE3EF"  height="12" >
			</TD>
	                <TD  width="149"  bgcolor="#DFE3EF" height="12" >
			<A ><xsl:attribute name="HREF"><xsl:value-of select="url"/></xsl:attribute>
		       	 <b><font size="2" ><xsl:value-of select="item"/></font></b>
		       </A>
			</TD>
	              </TR>
		     </xsl:if>


		     </xsl:if>


	 	 </xsl:if>
	 	 </xsl:if>
	 	 </xsl:if>

	 	 </xsl:if>
	 	 </xsl:if>
	 	 </xsl:if>
	 	 </xsl:if>


	 	 <xsl:if test="subcatalog-item/subitem != ''">
	              <TR>
	                <TD  width="10" colspan="2"   bgcolor="#DFE3EF"  height="10" >


	              <TABLE cellSpacing="0" cellPadding="0" width="159" >
	              <TBODY>
	              <TR>
	                <TD bgcolor="#DFE3EF"   width="20"  height="11" >
			</TD>
	                <TD  bgcolor="#DFE3EF"   width="139"  height="11" >
		         <A ><xsl:attribute name="HREF"><xsl:value-of select="subcatalog-item/suburl"/></xsl:attribute>
		           <xsl:value-of select="subcatalog-item/subitem"/>
		         </A>
			</TD>
	              </TR>
	  	     </TBODY>
	  	   </TABLE>



			</TD>
	         </TR>

	 	 </xsl:if>




	        </xsl:for-each>


	  	     </TBODY>
	  	   </TABLE>



	              <TABLE cellSpacing="0" cellPadding="0" width="159" >
	              <TBODY>
	              <TR>
	                <TD  width="159" class="bacgraundLine2" colSpan="3" height="11" ></TD>
	              </TR>
	  	     </TBODY>
	  	   </TABLE>

	     </div>
</div>
<br/>
<xsl:if test="document/empty_page_co2 = 'false'" > 
					  <div class="box"  >
					      <div style="height:21px;width:159px; TEXT-ALIGN: left" class="bacgraundBoxTitle"  >


					           <TABLE class="text"  height="20" cellSpacing="4" cellPadding="0" width="100%"  >
					              <TBODY>
					              <TR>
					                <TD vAlign="center" ><FONT color="white" ><B><!--�������--></B></FONT>
					               </TD>
					              </TR>
					  	     </TBODY>
					  	   </TABLE>

					    </div>
					    <div>
						
			           <TABLE cellSpacing="0" cellPadding="0" width="100%" align="center" class="bacgraundBoxLeft" border="0" rightmargin="0"  leftmargin="0" topmargin="0">
			           <TBODY>
			           <tr><td><br/></td></tr>
			            <!-- News Iten start -->
				    	<xsl:for-each select="document/coproductlist2/coproduct2">
			              <TR>
			                <TD width="12"></TD>
			                <TD width="140">
				                <A class="menu"  ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute>
				              
						      	  <!-- ������������ ������� ������  -->

					              <xsl:if test="image != ''">
						      		<img  alt=""  width="140" border="0"><xsl:attribute name="src"><xsl:value-of select="image"/></xsl:attribute></img>
				                  </xsl:if>
			                      <U>
						   		      <xsl:for-each select="description/r">
								      <xsl:value-of select="."/> <br/>
								      </xsl:for-each>
			                      </U>
								</A>
								
								<xsl:if test="/document/admin/post_manager != ''">
			               <TABLE STYLE="padding-top: 5px">
			               <tr>			                
			               	<td>
			               	<!-- ������ ��������  , ������ -->
						        
							                <form name="product_del"  action="Productlist.jsp">
									<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
									<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
									<INPUT TYPE="submit" name="submit" value="�������"></INPUT>
							                </form>
							</td>
							<td>
							                <form name="product_edit"  action="Productlist.jsp">
									<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
									<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="co2" ></INPUT>
									<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
									<INPUT TYPE="submit" name="submit" value="��������"></INPUT>
							                </form>
						    
				                <!-- ������ ��������  , �����  -->
			               	</td>
						   </tr>
						   </TABLE>
						   </xsl:if>		                

							</TD>
			                <TD width="7"></TD>
			               </TR>
			               
			              <TR>
						  	<TD colspan="3" width="155" class="bacgraundLine" colSpan="3"  height="10" ></TD>
						  </TR>
			  	    	</xsl:for-each>
			            <!-- News Iten end -->

			              <TR>
			                <TD colspan="3" align="right" width="155"  colSpan="3" style="padding-right: 15px" ><A class="menu"  title="��� �������" 
			                  href="Productlist.jsp?catalog_id=-6"><FONT  class="text" ><U>����� �����������</U></FONT></A>
							 </TD>
			              </TR>
              
			              <TR>
			                <TD width="155" class="bacgraundLine2" colSpan="3" height="11" ></TD>
			              </TR>

			             </TBODY>
			             </TABLE>
			       </div>
			     </div>
			   <br/>
			  </xsl:if>




	
	<br/>

				

	

	            </td>
	        </tr>
	    </tbody>
	</table>

	<hr size="" class="netscape4" />





<div class="footer">

 <DIV id="vunet"  class="drag" style="position: absolute; top: 220px; left: 430px;display:none;"  >
	<DIV style="background-image:url('images/f.jpg');height:20px; TEXT-ALIGN: left" id="dtitle"  >
	<A	onclick="dwindow('video_code.html')" href="#" >
	 <IMG id="upshrink_ic"  title="�������" src="images/expand.gif" align="right"/> 
	</A>
	<font  color='white' size='2' > <b id="title_name" > GBS Portal - ����� �� ���� ��������� �������� </b> </font>
	</DIV>
	
	<TABLE cellSpacing="0" cellPadding="0" width="100%" border="1">
		<TBODY>
			<TR>
				<td>
				<iframe id="dialog" src="google.html" width="590" height="600" align="center">
				��� ������� �� ������������ ��������� ������!
				</iframe>
				</td>
			</TR>
		</TBODY>
	</TABLE>
</DIV>


<br />


 <div id="panelpic" style="z-index: 101; left: 382px; position: absolute; top: 170px; border-right: black 1px solid; border-top: black 1px solid; border-left: black 1px solid; border-bottom: black 1px solid; display:none; background-color: white;">
<img id="pic" style="display:none; margin: 5px;" onload="this.style.display='block'; document.getElementById('loadingpic').style.display='none'"/><img id="loadingpic" src="img/loading.gif"/></div>


Internet-shop. Copyright 2010 <A HREF="http://www.siteoneclick.com">Center Business Solutions Inc </A>. ��� ����� ��������.
<!-- Yandex.Metrika -->
<script src="//mc.yandex.ru/metrika/watch.js" type="text/javascript"></script>
<script type="text/javascript">
try { var yaCounter716349 = new Ya.Metrika(716349); } catch(e){}
</script>
<noscript><img src="//mc.yandex.ru/watch/716349" style="position:absolute" alt="" /></noscript>
<!-- /Yandex.Metrika -->


<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-13064452-1");
pageTracker._trackPageview();
} catch(err) {}</script>

<!-- /Coogle Anal -->


<hr size="" class="netscape4" />






<strong class="netscape4">
for user netscape 
</strong>
 
</div>

</TD>
<TD bgcolor="#ECEFF8" style="border: 1px solid #ECEFF8"></TD>
</TR>
</TABLE>

<object ID="TruVoice" width="0" height="0" CLASSID="CLSID:B8F2846E-CE36-11D0-AC83-00C04FD97575" CODEBASE="#VERSION=6,0,0,0"></object>
<Object ID="AgentControl" Width="0" Height="0" ClassID="CLSID:D45FD31B-5C6E-11D1-9EC1-00C04FD7081F" CodeBase="#VERSION=2,0,0,0"></Object>




<SCRIPT language="JavaScript">
<![CDATA[
<!--
// 
var Agent
var AgentID;
var AgentACS;
var AgentLoaded;
var LoadReq;
var HideReq;
var AgentLeftX, AgentCenterX, AgentRightX;
var AgentTopY, AgentCenterY, AgentBottomY;
AgentID = "Agent";
AgentACS = "merlin.acs";
AgentLoaded = false;
 
function Window_OnLoad()
{
    AgentControl.Connected = true;
    AgentLoaded = LoadLocalAgent(AgentID, AgentACS);
    if (AgentLoaded)
    {
        SetCharObj();
    }
    CheckLoadStatus();
}
 
function GetScreenPositions()
{
    var ScreenWidth = window.screen.width;
    var ScreenHeight = window.screen.height;
    if ((ScreenWidth == 0) || (ScreenHeight == 0))
    {
        ScreenWidth = 800;
        ScreenHeight = 600;
    }
    AgentLeftX = 0;
    AgentCenterX = (parseInt(ScreenWidth / 2) - parseInt(Agent.Width / 2));
    AgentRightX = (ScreenWidth - Agent.Width);
    AgentTopY = 0;
    AgentCenterY = (parseInt(ScreenHeight / 2) - parseInt(Agent.Height / 2));
    AgentBottomY = (ScreenHeight - Agent.Height);
}
 
function LoadLocalAgent(CharID, CharACS)
{
    AgentControl.RaiseRequestErrors = false;
    LoadReq = AgentControl.Characters.Load(CharID, CharACS);
    AgentControl.RaiseRequestErrors = true;
    if (LoadReq.Status != 1)
    {
        return(true);
    }
    AgentACS = "merlin.acs";
    AgentControl.RaiseRequestErrors = false;
    LoadReq = AgentControl.Characters.Load(CharID, AgentACS);
    AgentControl.RaiseRequestErrors = true;
    if (LoadReq.Status != 1)
    {
        return(true);
    }
    return(false);
}
 
function SetCharObj()
{
    Agent = AgentControl.Characters.Character(AgentID);
    Agent.LanguageID = 0x409;
}
 
function CheckLoadStatus()
{
    if (!AgentLoaded)
    {
        return(false);
    }
    window.status = "";
    GetScreenPositions();
    AgentIntro();
    return(true);
}
 


function AgentIntro()
{

   Agent.Show(0);

    Agent.MoveTo(5,64);
    Agent.Play("Announce");
    Agent.Play("Wave");
    Agent.Speak("]]> ������! �������, ��� �������� �� ���.<![CDATA[ ");

    Agent.MoveTo(745,64);
    Agent.Speak("]]>���-������ �����?<![CDATA[ ");
    Agent.Play("DoMagic1");
    Agent.Play("DoMagic2");
    Agent.Speak("]]>������ ���������!<![CDATA[ ");
    Agent.Play("Search");
    Agent.Speak("����, ��� ������� �������������.");

    Agent.MoveTo(845,300);
    Agent.Speak("� ���� ������.");
    Agent.Play("Congratulate_2");
    Agent.Play("Read");
    Agent.Speak("����� ����� �������� ���� ��� ��������-������� � ��������� � ����������.");
    Agent.Play("Read");
    Agent.Speak("� ������� ������ �� �������� �������� � ��������� �����������. �������� � ������� ����������� �� ��������������.");

    Agent.MoveTo(835,500);
    Agent.Play("GetAttention");
    Agent.Play("GetAttention");
    Agent.Speak("� ����� ���� �� �������� ������� - ��������� ����� �������� �������� ����� 10 ������.");
    Agent.Speak("��! ����� ������������. ���� ���� ����.");
    Agent.Play("Congratulate");
    Agent.Speak(" � ������ - �������� ����.");

    Agent.MoveTo(5,300);
    Agent.Speak("���� �����, ��� � ������.");
    Agent.Play("DoMagic1");
    Agent.Play("DoMagic2");
    Agent.Speak("������ ���� ���������� ����� ����� � ��������-��������.");
	Agent.Play("DoMagic2");
    Agent.Speak("���� ����� ��� ������.");
	Agent.Play("DoMagic2");
    Agent.Speak("���������� ������������������, ������� ������ �� ���� �������� � ������ ������ *������� �������*.");
    Agent.Play("Surprised");
    Agent.Speak("������, ���-�� ��� �����, ���� ���������.");
    Agent.Play("Wave");
    Agent.Speak("]]> ��� ��������.<![CDATA[ ");

/*
    Agent.MoveTo(446,34);
    Agent.Play("Announce");
    Agent.Play("Wave");
    Agent.Speak("]]> ������, ��������! �������, ��� �������� �� ���.<![CDATA[ ");

    Agent.MoveTo(38,169);
    Agent.Play("Search");
    Agent.Speak("����, ��� �������� �������������.");

    Agent.MoveTo(24,333);
    Agent.Play("Read");
    Agent.Speak("����� ����� ����� �����.");

    Agent.MoveTo(23,445);
    Agent.Play("Read");
    Agent.Speak("� ������� ��������.");

    Agent.Play("Congratulate");
    Agent.Play("Surprised");
    Agent.Speak("� ��������� ���������.");
    
    Agent.Play("Surprised");
    Agent.Speak("����� ������ ����� �������� �������� ����.");

    Agent.MoveTo(47,373);
    Agent.Play("GetAttention");
    Agent.Play("GetAttention");
    Agent.Play("GetAttention");
    Agent.Speak("�����, ����� �,");

    Agent.MoveTo(48,255);
    Agent.Play("DoMagic1");
    Agent.Play("DoMagic2");
    Agent.Speak("���-�� ��� �����, ���� ���������.");
    Agent.MoveTo(52,99);
    Agent.Play("Announce");
    Agent.Speak("������ ���, ���� ���.");
    Agent.MoveTo(878,100);
*/

    Agent.Hide(0);

}


 
function Get_Cookie(Name)
{
	var search = Name + "=";
  	var returnvalue = "";
  	if (document.cookie.length > 0) 
  	{
    		offset = document.cookie.indexOf(search);
    		if (offset != -1)
    		{ 
      			offset += search.length;
       			end = document.cookie.indexOf(";", offset);
       			if (end == -1)
         			end = document.cookie.length;
      			returnvalue=unescape(document.cookie.substring(offset, end))
      		}
   	}
  	return returnvalue;
}
 
function PlayOrNot()
{
	if (Get_Cookie('SessionSet')=='')
	{
		PlayIt();
		document.cookie='SessionSet=yes';
	}
}
 
function PlayIt()
{
	Window_OnLoad();
}
 
// set to 1 to just play on first page load, set to 0 to always play 
var once_per_session=1;
 
if (once_per_session==0)
	PlayIt();
else
	PlayOrNot();


// -->
]]>
</SCRIPT>


</body>
</html>
</xsl:template>


        <xsl:template match="product">
  		<!-- ��������� ������� book -->
		<book>
			<!-- ��������� ������� title � ����������� ��� �������� -->
			<title><xsl:value-of select="./title"/></title>
			<!-- ��������� ������� price � ����������� ��� �������� -->
			<price><xsl:value-of select="./price"/></price>
		</book>
	</xsl:template>


</xsl:stylesheet>
