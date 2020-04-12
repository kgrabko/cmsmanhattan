<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
<xsl:output method="html" indent="yes"/>
<xsl:output encoding="UTF-8"/>
<xsl:strip-space elements="*"/>


<xsl:template match="/">
	<xsl:variable name="host" select="string(document/host)"/>	
	<xsl:variable name="user_id" select="number(/document/owner_user_id)"/> 
	<xsl:variable name="role" select="document/role_id"/> 
<HTML>
<HEAD>
<META HTTP-EQUIV="no-cache"/>
 <title><xsl:value-of select="document/title"/></title>
 
     <LINK rel="stylesheet" type="text/css"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/template.css')"/></xsl:attribute></LINK> 
     <LINK rel="stylesheet" type="text/css"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/constant.css')"/></xsl:attribute></LINK> 
     <link rel="stylesheet" type="text/css" media="screen"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/style.css')"/></xsl:attribute></link>
     
	 <SCRIPT type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/caption.js')"/></xsl:attribute></SCRIPT>

<!--
	 <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/script.responsive.js')"/></xsl:attribute></script>
-->
     <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/script.js')"/></xsl:attribute></script>

</HEAD>


<body>

	<div id="art-main">
	
	<div class="main">

	<div>
		<IMG alt="Logo"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/logo.gif')"/></xsl:attribute></IMG>
	</div>


			<div id="top">

<nav class="art-nav clearfix desktop-nav ">
   					 <ul class="art-hmenu">  
   					
   					 <li><a href="index.jsp">Home</a></li>
   					 
   					  <xsl:for-each select="document/menu/menu-item">
											       <xsl:if test="item != ''">
													   <xsl:if test="code != '-1'">
														   <xsl:if test="code != '-2'">
																   <xsl:if test="code != '-3'">
																         
																	             <li>
																					  <a class="active" >
																					  <xsl:attribute name="HREF"><xsl:value-of select="url"/></xsl:attribute>
																					  <xsl:value-of select="item"/>
																					  </a>
																					  
													    							<ul class="active">
																					  <xsl:for-each select="submenu-item">
																					  <li><a ><xsl:attribute name="HREF"><xsl:value-of select="suburl"/></xsl:attribute>
											           									<xsl:value-of select="subitem"/>
													     							 </a></li>
													     							 
																					 </xsl:for-each>
																					</ul>
																				  </li>
															 </xsl:if>
												 		 </xsl:if>
											 		 </xsl:if>
										 		 </xsl:if>
						</xsl:for-each>
   					 
   					  
   					   
   					   <li><A href="Order.jsp">Cart </A>
	        	 	   </li>
	        	 	   
	        	 	  <xsl:if test="/document/login != 'user'">
        				<li><A href="Productlist.jsp?action=logoff">Logout</A></li>        	
        			  </xsl:if>
	        	 	   
	        	 	   
   					  </ul> 
   					 </nav>
					
			</div>


			    <DIV class="indent" >
			    	<DIV class="moduletable">
			     		<TABLE class="who_is_online" style="WIDTH: auto" align="right">
			       			<TBODY>
			        			<TR>
			        				
			          				<TD>
			        				<xsl:if test="document/login != ''">   <!--  показывать если есть логин -->
									<b>Login name </b> 
									<a href="Authorization.jsp" class="user0">
									<xsl:value-of select="document/login"/>
									</a>
									</xsl:if>
			         				</TD>
			        			</TR>
			        		</TBODY>
			        </TABLE>
			 	</DIV>
			 </DIV>
	
	

			<div id="mid">
				<div class="mid-left_">
					<div class="mid-right_">
					
					  	<div id="search">
							<div class="module-search">
								<FORM name="searchform"  action="Productlist.jsp"  method="POST">
								<div class="search">
								<INPUT class="inputbox" 
								id="search_value"  
						   		name="search_value" 
						   		type="text"  
						   		size="20" 
						   		alt="It is search by name goods"   
						   		title="It is search by name goods">
						   		<xsl:attribute name="value">
						   		<xsl:value-of select="document/search_value"/>
						   		</xsl:attribute>
						   		</INPUT>
						   	  	<INPUT  class="button" type="image" value="Search" onClick="return top.search_word();return true"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/searchButton.gif')"/></xsl:attribute></INPUT>
						      	</div>
						     	<INPUT id="search_char"  name="search_char" type="hidden" ></INPUT>
							  	<INPUT id="searchquery"  name="searchquery" type="hidden" ></INPUT>
							  	<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="offset" VALUE="0"  ></INPUT> 
						     	</FORM>					
					        </div>
						</div>
			
						
					   <div id="breadcrumb">
						   <div class="space">
					       <span class="breadcrumbs pathway">
					          <xsl:if test="document/role_id != 0">
						      <xsl:if test="count(document/parent/parent-item) != 1">
                                    
                                      <a href="Productlist.jsp?catalog_id=-2" class="catalog" alt="To return back  to the top of Categorization" title="To return back  to the top of Categorization">
                                        <U><font size="2" >All Categories</font></U>
                                      </a>&#160; &#187; 
								
								        <xsl:for-each select="document/parent/parent-item">										
											 <xsl:if test="code != '-2'">												
												<A ><xsl:attribute name="HREF"><xsl:value-of select="url"/></xsl:attribute>
											        <U><font size="2" > <xsl:value-of select="item"/></font> </U> 
											    </A>&#160; &#187; 
											 </xsl:if>													
								        </xsl:for-each>
								        
						      </xsl:if>
						      </xsl:if>
						    
					       </span>
					       </div>
					   </div>
					   
					 
					</div>
				</div>
			</div>
			
			<div id="content" style="opacity: 0.9" >
					<div class="width">
					<div id="left">
					<div class="module_menu">
					<div class="first">
					<div class="sec">
							<h3>Forgot your password?</h3>
								<div class="box-indent">
								<div class="width">
								<P  style="font-size: 12px; margin-bottom: 5px;padding-top:5px">
								  To get password , Please  input your email in field for it.
						        </P>												        
							     <form action="/sendpassword"  method="post">													      	
					                     <INPUT  class="inputbox" title="E-Mail"  AUTOCOMPLETE="off" TYPE="TEXT" NAME="email" /><br/>
					                     <div style="padding-top: 5px; padding-left: 35px">
					                     <input  class="button" type="submit" name="submit"  value="Send"/>
					                     </div>
					             </form>
								</div>
					</div>
					</div>
					</div>
					</div>
					
					<!-- О регистрации -->
			           
			            <DIV class="module" style="margin-right:0px">
			            <DIV class="first">
			            <DIV class="sec">
			            <DIV>
			            <H3>About registration</H3>
			            	<P class="text" style="padding:10px 20px 13px;">Registration is necessary for registration of the order and payment through the Internet.</P>
		            		
		            		<P class="text" style="padding:10px 20px 16px;">" * "required fields. </P>               
					
			            </DIV>
			            </DIV>
			            </DIV>
			            </DIV>
			</div>
					<div id="right">
					
					
								<div class="module" style="margin-right:0px">
								<div class="first">
								<div class="sec">

								<h3>Authorization</h3>
								<div class="box-indent">
								<div class="width">
										
										<FORM action="Authorization.jsp"  method="post" id="form-login" class="form-login">
						                  <FIELDSET class="input">
						                  <P id="form-login-username">
						                  <LABEL for="modlgn_username">Login</LABEL>
						                  <BR/>
						                  
						                  <INPUT id="modlgn_username" title=" User" class="inputbox" tabindex="10001"  AUTOCOMPLETE="off" TYPE="TEXT" NAME="Login" >
														<xsl:attribute name="value">
															<xsl:value-of select="document/login"/>
														</xsl:attribute>		
										  </INPUT> 
										  </P>
						                  
						                  <P id="form-login-password">
						                  <LABEL for="modlgn_passwd">Password</LABEL><BR/>
						                  <INPUT class="inputbox" title="Password" id="modlgn_passwd" tabindex="10002" AUTOCOMPLETE="off" TYPE="PASSWORD" NAME="Passwd1" ></INPUT> 
						                  </P>
						                  
						                  <div style="padding-left: 35px; padding-bottom: 5px;border: none; width: 100px">
						                  	<br/> 
						                  </div>
						                  
						                  <div style="padding-left: 35px; padding-bottom: 5px;border: none; width: 100px">
						                  	<INPUT class="button" type="submit" value="Enter" name="Submit"/> 
						                  </div>
						                  
						                   <div style="padding-left: 35px; padding-bottom: 5px;border: none; width: 100px">
						                  	<br/> 
						                  </div>
						                  
						                  </FIELDSET> 
						                 </FORM>                  
						                  
						                  <UL class="log_list">
						                  <LI>
						                  <A href="Authorization.jsp?Login=newuser" style="COLOR: #072f3a">Forgot your password?
						                  </A> 
						                  </LI> 
						                  <LI>
						                  <A href="Authorization.jsp?Login=" style="COLOR: #072f3a">Registration
						                  </A>
						                  </LI>
						                  </UL>
											
								</div>
								</div>

						</div>
						</div>
						</div>
						
			           
			            <DIV class="module" style="margin-right:0px">
			            <DIV class="first">
			            <DIV class="sec">
			            <DIV>
			            <H3>Pay Order</H3>
			          	<P class="text" style="padding:10px 20px 13px;">
			          	 To pay order payment you must be already logged on this site
			          	</P>
		                <div style="padding:0px 20px 17px;">
		                <A>
		                
		                <xsl:attribute name="HREF">
		                <xsl:value-of select="document/to_pay"/>
		                </xsl:attribute>
		                
						<IMG border="0" height="20" width="140">
						<xsl:attribute name="src">
						<xsl:value-of select="concat('xsl/',$host,'/images/credit-cards.jpg')"/>
						</xsl:attribute>
						</IMG> 
						</A>               
				      	</div>
			            </DIV>
			            </DIV>
			            </DIV>
			            </DIV>
			            
						
						
		
						
							
							
			
</div>
			
<div id="container">
<div class="comp-cont">
		<table class="blog" cellpadding="0" cellspacing="0">
			<tr>
			<td valign="top">
				<div class="article-bg_">
					<div class="article-left_">
						<div class="article-right_">
							<div class="width">
							
                            
								<DIV  class="registration"  style="background-color: #CFCFC8; text-align: left; margin-bottom: 3px">							
										 
			        				<xsl:if test="document/message != ''">
			        				<p align="center"> 			        				 >
			        				<font color="red" >
										<xsl:value-of select="document/message"/>
									</font>
									</p>
									<br/>
									<br/>
									</xsl:if>
									
									<br/>
							<p align="center" ><h3 style="padding-left: 40px; padding-bottom: 15px;color: #4C4B49; font-size: 20px">Registration</h3></p>
										 <br/>
										  <form method="post" ACTION="RegPage.jsp">
												    <TABLE class="registration" cellSpacing="0" cellPadding="0" border="0">															    													
														<TR><TD style="width: 150px; height: 30px;"  >Login:*</TD> <TD align="left"> <input  style="width: 330px; height: 25px;" size="60"  AUTOCOMPLETE="off" TYPE="TEXT" name="Login"><xsl:attribute name="value"><xsl:value-of select="document/login"/></xsl:attribute></input></TD></TR>

														<TR><TD style="width: 150px; height: 30px;" >Password:* </TD> <TD> <input style="width: 330px; height: 25px;"  size="60" name="Passwd1" type="password" id="password" ></input></TD></TR>
														<TR><TD style="width: 150px; height: 30px;">Re-enter password*   :  </TD> <TD><input  style="width: 330px; height: 25px;"  size="60" name="Passwd2" type="password" id="password"></input></TD></TR>
														<TR><TD style="width: 150px; height: 30px;">First name:*  </TD> <TD><input style="width: 330px; height: 25px;"  size="60" AUTOCOMPLETE="off" TYPE="TEXT" name="FName"><xsl:attribute name="value"><xsl:value-of select="document/firstname"/></xsl:attribute></input></TD></TR>
														<TR><TD style="width: 150px; height: 30px;">Last name:* </TD> <TD><input style="width: 330px; height: 25px;"  size="60" AUTOCOMPLETE="off" TYPE="TEXT"  name="LName"  value=""   ><xsl:attribute name="value"><xsl:value-of select="document/lastname"/></xsl:attribute></input></TD></TR>
														<TR><TD style="width: 150px; height: 30px;">The company:* </TD> <TD><input style="width: 330px; height: 25px;" size="60" AUTOCOMPLETE="off" TYPE="TEXT"  name="Company" value=""><xsl:attribute name="value"><xsl:value-of select="document/company"/></xsl:attribute></input></TD></TR>
				                       					<TR><TD style="width: 150px; height: 30px;">Country:*</TD>
				                       						<TD >
				                       						<SELECT style="width: 330px; height: 24px;"  NAME = "country_id" onChange="doChangeCity('country_id', this.value)"  >
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
											               </SELECT>
											               </TD>
											            </TR>
											           
											            <TR><TD width="150px">City:*</TD>
											            	<TD>
												                <SELECT style="width: 330px; height: 24px;"  NAME = "city_id">
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
													   		   </SELECT>
												   		   </TD> 
												   	   </TR>

                    								   <!--  <TR><TD width="150px">Currency</TD><TD><input  style="width: 330px; height: 19px;"  size="60" AUTOCOMPLETE="off" TYPE="hidden"  name="currency_id" value="1" ></input>dollars</TD> </TR>  -->							
														<TR><TD style="width: 150px; height: 30px;">E-Mail:*  </TD> <TD><input style="width: 330px; height: 25px;"  size="60" AUTOCOMPLETE="off" TYPE="TEXT"  name="EMail" value="" ><xsl:attribute name="value"><xsl:value-of select="document/email"/></xsl:attribute></input></TD></TR>
														<TR><TD style="width: 150px; height: 30px;">Phone:</TD> <TD><input style="width: 330px; height: 25px;"  size="60"  AUTOCOMPLETE="off" TYPE="TEXT"  name="Phone" value=""><xsl:attribute name="value"><xsl:value-of select="document/phone"/></xsl:attribute></input></TD></TR>
														<TR><TD style="width: 150px; height: 30px;">Cell:  </TD> <TD><input style="width: 330px; height: 25px;"  size="60"  AUTOCOMPLETE="off" TYPE="TEXT"  name="MPhone" value="" ><xsl:attribute name="value"><xsl:value-of select="document/mphone"/></xsl:attribute></input></TD></TR>
														<TR><TD style="width: 150px; height: 30px;">Fax:  </TD> <TD><input style="width: 330px; height: 25px;"  size="60"  AUTOCOMPLETE="off" TYPE="TEXT" name="Fax" value="" ><xsl:attribute name="value"><xsl:value-of select="document/fax"/></xsl:attribute></input></TD></TR>
														<TR><TD style="width: 150px; height: 30px;">Skype:  </TD> <TD><input style="width: 330px; height: 25px;"  size="60"  AUTOCOMPLETE="off" TYPE="TEXT"  name="Skype" value=""  ><xsl:attribute name="value"><xsl:value-of select="document/icq"/></xsl:attribute></input></TD></TR>
														<TR><TD style="width: 150px; height: 30px;">Broker ID:  </TD> <TD><input style="width: 330px; height: 25px;"  size="60"  AUTOCOMPLETE="off" TYPE="TEXT"  name="Broker ID" value="" ><xsl:attribute name="value"><xsl:value-of select="document/website"/></xsl:attribute></input>
															<input size="60" style="width: 330px; height: 25px;"  AUTOCOMPLETE="off" TYPE="hidden"  name="site_id" value="" ><xsl:attribute name="value"><xsl:value-of select="document/site/site-item/selected"/></xsl:attribute></input>
														</TD></TR>
														<TR><TD width="150px"><img width="100px" alt="The picture with the generation number" src="/gennumberservlet"  /> </TD> <TD><input style="width: 50px; height: 20px;"  size="60"  AUTOCOMPLETE="off" TYPE="TEXT"  name="gen_number" value=""></input><br/>Enter the number in the image here</TD></TR>																
														<TR><TD width="150px"></TD> <TD></TD></TR>
														<TR  align="right">
															<TD colspan="2" width="250px" align="center" class="regbut" style="padding-left: 150px; padding-bottom: 25px">	
															
																<DIV class="regbut" style="padding-top:10px">
																	<table width="150px"><tr>
																	<td width="100px">
																		<input class="button" type="submit" name="Submit" value="OK"></input>	
																	</td>
																	<td>
																	<input  class="button" type="reset" value="Clean"></input>
																	</td></tr></table>
																</DIV>																
															</TD>															
															
														</TR>
																
														</TABLE>		
														</form>						
									 	                        
	                        		</DIV>
							
							
							
							
							
							
							
							
							
							
							
							
							     
							<span class="article_separator"></span>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>

		
	</table>

</div>
</div>
				
				
				
				</div>
			</div>
		</div>
	</div>



	<DIV id="footer" style="opacity: 0.9" >
		<DIV class="main">
		<div class="space">
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
<br/>
<font color="black">CMS Manhattan  . Copyright 2014 
		<A HREF="http://www.siteforyou.net"><font color="black">   Center Business Solutions Inc </font></A>.  All rights reserved
</font>
<br/>		
		</div>
		</DIV>
	</DIV>



<!-- Yandex.Metrika counter -->
<script type="text/javascript">
(function (d, w, c) {
    (w[c] = w[c] || []).push(function() {
        try {
            w.yaCounter7171372 = new Ya.Metrika({id:7171372, enableAll: true});
        } catch(e) { }
    });
    
    var n = d.getElementsByTagName("script")[0],
        s = d.createElement("script"),
        f = function () { n.parentNode.insertBefore(s, n); };
    s.type = "text/javascript";
    s.async = true;
    s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js";

    if (w.opera == "[object Opera]") {
        d.addEventListener("DOMContentLoaded", f);
    } else { f(); }
})(document, window, "yandex_metrika_callbacks");
</script>
<noscript><div><img src="//mc.yandex.ru/watch/7171372" style="position:absolute; left:-9999px;" alt="" /></div></noscript>
<!-- /Yandex.Metrika counter -->


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


</body>









</HTML>
</xsl:template>
</xsl:stylesheet>