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
<HTML>
<HEAD>
<META HTTP-EQUIV="no-cache"/>
 <title><xsl:value-of select="document/title"/></title>
 
     <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width" />
    <!-- 
     <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
     <link rel="stylesheet" href="style.ie7.css" media="screen" >
     <xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/style.ie7.css')"/></xsl:attribute></link>
 -->

	 <link rel="stylesheet" type="text/css" media="screen"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/style.css')"/></xsl:attribute></link>
<!--	
 <link rel="stylesheet" type="text/css" media="screen"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/style.responsive.css')"/></xsl:attribute></link>

	 <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/script.responsive.js')"/></xsl:attribute></script>
-->
	<script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/script.js')"/></xsl:attribute></script>


     	 <LINK id="style2" rel="stylesheet" type="text/css"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/template.css')"/></xsl:attribute></LINK> 
	 <LINK rel="stylesheet" type="text/css"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/constant.css')"/></xsl:attribute></LINK>
	 <SCRIPT type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/caption.js')"/></xsl:attribute></SCRIPT>
	 <link rel="stylesheet" type="text/css" media="screen"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/jquery.lightbox.css')"/></xsl:attribute></link>

	 <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/jquery.js')"/></xsl:attribute></script>
	 <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/jquery.lightbox.js')"/></xsl:attribute></script>


	<script type="text/javascript">
 
 	var roleId = <xsl:value-of select="document/role_id" disable-output-escaping="yes" />;
 
	</script>
	 
</HEAD>

<body id="art-main_1" >
<xsl:attribute name="onload"><xsl:value-of	select="concat('setCurrent(',$page,',',$role, '); ')" /></xsl:attribute>

			
	<div id="art-main">
		
		<div class="main">


	<div>
				<IMG alt="Logo"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/logo.gif')"/></xsl:attribute></IMG>
	</div>			

			
			<div id="top">
 				
	  				
					<nav class="art-nav clearfix desktop-nav" >
   					 <ul class="art-hmenu"  >  
   					
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
        
	        	 	  <xsl:if test="/document/login = 'user'">
	        	 	   <li><A href="Authorization.jsp?Login=newuser">Login</A>
	        	 	   </li>
	        	 	   <li><A href="Authorization.jsp?Login=newuser">New user</A>
	        	 	   </li>
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
									<B>Login name</B> 
									 <a href="Authorization.jsp" style="margin-left: 5px; text-decoration: none">				                
									
								 	<xsl:if test="document/login = 'user'">   <!--  показывать если нет логина -->
									<font class="user0">
										<xsl:value-of select="document/login"/>
									</font>
									</xsl:if>
									
									<xsl:if test="document/login != 'user'">   <!--  показывать если есть логин -->
									<xsl:if test="document/role_id = 1"> <!--  оранжевый если юзер -->
										<font class="user0">
											<xsl:value-of select="document/login"/>
									</font>
								</xsl:if>
											 			
								<xsl:if test="document/role_id = 2"><!--  красный если админ -->
									<font class="user0">
									<xsl:value-of select="document/login"/>
									</font>
								</xsl:if>
								</xsl:if>
									
							</a>	
									</xsl:if>
			         				</TD>
			        			</TR>
			        		</TBODY>
			        </TABLE>
			 	</DIV>
			 </DIV>
	
	

			<div id="mid" style="opacity: 0.8" >
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
					          
				                       <a href="Productlist.jsp?catalog_id=-2" class="catalog" alt="To return back  to the top of Categorization" title="To return back  to the top of Categorization">
                                        <U><font size="2" >All Categories</font></U>
                                      </a>&#160; &#187; 
				
						      <xsl:if test="count(document/parent/parent-item) &gt; 0">
                                    
               				
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
					   </div>
					   
					 
					</div>
				</div>
			</div>
			
			
			
			<div id="content" style="opacity: 0.97" >
				<div class="width">
					<div id="left">
					
					<xsl:choose>
											<xsl:when test="/document/role_id != '0'">

											<div class="module">
												<div class="first">
													<div class="sec">
														<h3>My office</h3>
														<div class="box-indent">
														<div class="width">

														<table cellSpacing="0" cellPadding="0" width="100%"
															align="center"  border="0"
															rightmargin="0" leftmargin="0" topmargin="0">
															<tbody>
																<tr>
																	<td width="5"></td>
																	<td>
																		<br />
																	</td>
																</tr>
																<tr>
																	<td width="5"></td>
																	<td>
																		<A  href="Productlist.jsp?action=login_usersite"
																			alt="My portal or the Internet shop"> My shop</A>
																	</td>
																</tr>

																<tr>
																	<td width="159" colspan="2" class="bacgraundLine"
																		height="10"></td>
																</tr>


																<tr>
																	<td width="5"></td>
																	<td>
																		<A >
																			<xsl:attribute name="href"><xsl:value-of
																				select="document/to_account_history" /></xsl:attribute>
																			Your balans
																			<xsl:value-of select="document/balans" />
																			$ .
																		</A>
																	</td>
																</tr>

																<tr>
																	<td width="159" colspan="2" height="10"></td>
																</tr>

																<tr>
																	<td width="5"></td>
																	<td>
																		<A>
																			<xsl:attribute name="href"><xsl:value-of
																				select="document/to_order_hist" /></xsl:attribute>
																			Order list
																		</A>
																	</td>
																</tr>

																<tr>
																	<td width="159" colspan="2" height="10"></td>
																</tr>

																<tr>
																	<td width="5"></td>
																	<td>
																		<A>
																			<xsl:attribute name="href"><xsl:value-of
																				select="document/to_order" /></xsl:attribute>
																			Order
																		</A>
																	</td>
																</tr>


																<tr>
																	<td width="155"  colSpan="2"
																		height="11"></td>
																</tr>

															</tbody>
														</table>

														</div>
													</div>
												</div>
											</div>
											
										</div>



												</xsl:when>
											</xsl:choose>
					
						<DIV style="">
			            <DIV class="module" style="height : expression(parentNode.clientHeight > 300? '347px' : 'auto');max-height: 347px;height: auto;">
						<DIV class="first"  >
						<DIV class="sec" style="height : expression(parentNode.clientHeight > 300? '347px' : 'auto');max-height: 347px;height: auto;">
						 <H3>Sections</H3>
			            <TABLE >
			            
			            <TBODY>
			              <TR>
			                <TD class="over">
			                <DIV class="module_scroll"  >
								<DIV class="first_scroll" >
			          				
										<TABLE cellSpacing="0" cellPadding="0" width="159" >
										   <TBODY>
										   <xsl:for-each select="document/catalog/catalog-item">
											       <xsl:if test="item != ''">
													   <xsl:if test="code != '-1'">
														   <xsl:if test="code != '-2'">
																   <xsl:if test="code != '-3'">
																         <xsl:if test="subcatalog-item/subitem = ''">
																             <TR>
																             <TD width="159" colspan="2"  height="10" >
																             </TD>
																             </TR>						
																             <xsl:if test="code != selected">
																	              <TR>
																	              	<TD  width="25"    height="12" ></TD>
																	             	 <TD  width="149"   height="12" >
																					  <A ><xsl:attribute name="HREF"><xsl:value-of select="url"/></xsl:attribute>
																					  <font size="2" ><xsl:value-of select="item"/></font>
																					  </A>
																				  </TD>
																              </TR>
																	     	</xsl:if>
																		
																		
																		             <xsl:if test="code = selected">
																		              <TR>
																		                <TD  width="25"   height="12" ></TD>
																		                <TD  width="149"   height="12" >
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
										
										 	 <xsl:if test="subcatalog-item/subitem != ''">
										 	 
										       <TR>
										          <TD   colspan="2"  height="10" style="text-align: left; padding-left: 8px; ">
										              <TABLE cellSpacing="0" cellPadding="0" >
											              <TBODY>
											              <TR>
											                <TD   width="25"  height="12" ></TD>
												                <TD height="11" width="149" style="text-align: left; padding-right: 0px ; padding-top: 5px; height: 15px;">
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
							
							</DIV>
							</DIV>
			                </TD>
			               </TR>
			               </TBODY>
			               </TABLE>
			               </DIV>
			               </DIV>
			               </DIV>
			               </DIV>
				
				
				
				
					<div class="module">
					<div class="first">
						<div class="sec">
							<h3>More</h3>
							<div style="padding-right: 10px;padding-left:10px;" class="box-indent">
								<div class="width">
								
									<TABLE class="poll">
						              <TBODY>
						              <TR>
						                <TD class="over">
						                  <UL class="mostread_s3">
						                  <xsl:for-each select="document/coproductlist1/coproduct1">
						                    <LI class="mostread_s3">
						                    <xsl:if test="image != ''" >
									   	        <xsl:if test="big_image_type != 'swf'">
									   	           <xsl:if test="big_image_type != 'flv'">
						                    <A class="mostread_s3"><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><br/>
										       <!-- Обрабатываем перевод строки  -->
										       <img  alt=""  width="147" border="0">
										       <xsl:attribute name="src">
										       <xsl:value-of select="image"/>
										       </xsl:attribute>
										       </img>
								               <br/>
								               <xsl:if test="count(description/r) > 1"> 
												 <xsl:if test="string-length(description/r[1]) > 3">
												 	<IMG style="margin-right: 1px;margin-bottom:10px;margin-top:10px;">
							                        <xsl:attribute name="src">
							                        <xsl:value-of select="concat('xsl/',$host,'/images/list-item-bg.gif')"/>
							                        </xsl:attribute>
							                        </IMG>
					                             </xsl:if>
					                          </xsl:if>
					                          <br/>
								               <U>
								   		       <xsl:for-each select="description/r">
										       <xsl:value-of select="."/> 
										       </xsl:for-each>
								               </U>
										       </A>
								               </xsl:if>
								               </xsl:if>
								          
									    </xsl:if>
								
								            <xsl:if test="image = ''" >
								                <A class="mostread_s3"  ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><br/>
										      <!-- Обрабатываем перевод строки  -->
											 <xsl:if test="count(description/r) > 1"> 
												 <xsl:if test="string-length(description/r[1]) > 3">
												 	<IMG style="margin-right: 1px;margin-bottom:10px;margin-top:10px;">
							                        <xsl:attribute name="src">
							                        <xsl:value-of select="concat('xsl/',$host,'/images/list-item-bg.gif')"/>
							                        </xsl:attribute>
							                        </IMG>
					                             </xsl:if>
					                          </xsl:if>
					                          <br/>
								              <U>
									   		      <xsl:for-each select="description/r">
											      <xsl:value-of select="."/> <br/>
											      </xsl:for-each>
								              </U>
												</A>
										   </xsl:if>	
										 <!-- Кнопки удаления  , начало -->
										<xsl:if test="/document/admin/post_manager != ''">	
										<table><tr><td width="60px" style="padding-bottom: 5px; padding-top: 5px">																
											        <form name="product_del"  action="Productlist.jsp" method="POST">
														<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
														<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
														<INPUT style="padding-bottom: 5px;" class="button" TYPE="submit" name="submit" value="Delete"></INPUT>
											        </form>
											        </td>
											        <td width="5px">
											        </td>
											        <td style="padding-bottom: 5px; padding-top: 5px">
											        <form name="product_edit"  action="Productlist.jsp" method="POST">
														<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
														<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="co1" ></INPUT>
														<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
														<INPUT class="button" TYPE="submit" name="submit" value="Edit" style="padding-bottom: 5px; margin-left: 0px"></INPUT>
											        </form>
										</td></tr></table>	
										</xsl:if>
									   <!-- Кнопки удаления  , конец  -->														
								        </LI>
								        </xsl:for-each>      
						               <LI class="mostread_s3" >
						              	   <A class="mostread_s3"  title="All news"   href="Productlist.jsp?catalog_id=-1">
 												 	<IMG style="margin-right: 5px;margin-bottom:2px;">
							                        <xsl:attribute name="src">
							                        <xsl:value-of select="concat('xsl/',$host,'/images/list-item-bg.gif')"/>
							                        </xsl:attribute>
							                        </IMG>

								                	
								                <u>News Archive</u>
									        </A>
								        </LI> 
								         
						                              
						            </UL></TD></TR></TBODY></TABLE>					
								</div>
							</div>
						</div>
					</div>
					
				</div>
			</div>

				
				
					<div id="right">
					            <xsl:if test="document/login = 'user'">
						</xsl:if>
						
						
						
						<!-- разделы (для админа) -->
			            <xsl:if test="document/role_id != 0"><!--  если админ -->
			            <DIV class="module" style="margin-right:0px">
			            <DIV class="first">
			            <DIV class="sec">
			            <DIV>
			            <H3>My Account</H3>
			          	
						  	<div style="padding:5px 0px 26px"> 
						  	<div class="cab"><a><xsl:attribute name="HREF"><xsl:value-of select="document/to_order_hist"/></xsl:attribute>All orders</a></div>
						  	<div class="cab"><a><xsl:attribute name="HREF"><xsl:value-of select="document/to_order"/></xsl:attribute>The current order</a></div>	
						  	<div class="cab"><a href="Policy.jsp?page=pay">Pay Order</a></div>
						  	<!-- <div class="cab"><a><xsl:attribute name="HREF"><xsl:value-of select="document/to_account_history"/></xsl:attribute>Monetary operations</a></div>				      												             		    								
						  	<div class="cab" style="color: black;"><b>Available funds: </b> <xsl:value-of select="document/balans"/> рублей .</div> -->
							<xsl:if test="document/role_id = 2">
							<div class="cab"><a href="Documentation.jsp">Documentation</a></div>
							<div class="cab"><a href="Domain.jsp">Domain registration</a></div>
							<div class="cab"><a href="EMail.jsp">Mailbox</a></div>
							<div class="cab"><a href="Productlist.jsp?site=2">My purse</a></div>
							</xsl:if>

						  	</div> 
						  	            
			            </DIV>
			            </DIV>
			            </DIV>
			            </DIV>
			            

			            </xsl:if>	
						
			<div class="module_menu">
					<div class="first">
						<div class="sec">
							<h3>News</h3>
								<div class="box-indent">
								<div class="width">
								
								 <xsl:if test="count(document/newslist/news) != 0">
				                  <UL class="menu">
				                  <xsl:for-each select="document/newslist/news">
				                    <LI>  
				                    
				                     <xsl:if test="image != ''" >
									 <A><xsl:attribute name="HREF"><xsl:value-of select="image"/></xsl:attribute>
  							   		     <xsl:attribute name="rel">lightbox</xsl:attribute>
  							   		     <xsl:attribute name="alt"><xsl:value-of select="policy_url"/></xsl:attribute>
  							   		     <xsl:attribute name="id">lightboxdelay</xsl:attribute>
 							   		     <xsl:attribute name="title"><xsl:value-of select="name"/> - Click here for detail  </xsl:attribute>
									 </A>
								     </xsl:if> 
				                                     
									<A><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute>
							   		     <SPAN><xsl:for-each select="description/r">
									      	<xsl:value-of select="."/>
									     </xsl:for-each></SPAN><br/>
									 </A>
						             				
									<!-- Кнопки удаления  , начало -->
									
									<xsl:if test="/document/admin/post_manager != ''">
									
										<table style="width:150px">
										    <tbody>
										        <tr style="padding-bottom: 15px">
										         <td style="padding-bottom: 5px">
										         <form name="product_del" style="width:50px" action="Productlist.jsp" method="POST">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
												<INPUT style="padding-bottom: 5px;" class="button"  TYPE="submit" name="submit" value="Delete"></INPUT>
										         </form>
										         </td>
										         <td width="5px"></td>
										         <td>
								
										        <form name="product_edit" style="width:50px"  action="Productlist.jsp" method="POST">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="news"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
												<INPUT style="padding-bottom: 5px;" class="button" TYPE="submit" name="submit" value="Edit"></INPUT>
										        </form>
										         </td>
										        </tr>
										    </tbody>
										</table>
								
								
									</xsl:if>
								   <!-- Кнопки удаления  , конец  -->
				                    </LI>                    
				                  </xsl:for-each> 
				                  <LI>
				                    <A title="All news" href="Productlist.jsp?catalog_id=-1">
				                    <SPAN>News Archive</SPAN>
				                    </A>
				                    
				                   
				                  </LI> 
				                  </UL>
				                  </xsl:if>
				                 
                  
													
								</div>
							</div>
						</div>
					</div>
					</div>			
						
			<!-- дополнительный модуль -->
            <DIV class="module" style="margin-right:0px">
            <DIV class="first">
            <DIV class="sec">
            <DIV>
            <H3>More</H3>
            <DIV style="padding-right: 10px;padding-left:10px;" class="box-indent">
			<DIV class="width">
			
            <TABLE class="poll">
              <TBODY>
              <TR>
                <TD class="over">
                  <UL class="mostread_s3">
                  <xsl:for-each select="document/coproductlist2/coproduct2">
                    <LI class="mostread_s3">
                    <xsl:if test="image != ''" >
                    
			   	     <xsl:if test="big_image_type != 'swf'">
			   	     <xsl:if test="big_image_type != 'flv'">
                   	 <A class="mostread_s3">
                   	 <xsl:attribute name="HREF">
                   	 <xsl:value-of select="policy_url"/>
                   	 </xsl:attribute>
                   	 <br/>
				       <!-- Обрабатываем перевод строки  -->
				       <img  alt=""  width="147" border="0">
				       <xsl:attribute name="src">
				       <xsl:value-of select="image"/>
				       </xsl:attribute>
				       </img>
		                <br/>
						<xsl:if test="count(description/r) > 1"> 
							 <xsl:if test="string-length(description/r[1]) > 3">
							 	<IMG style="margin-right: 1px;margin-bottom:10px;margin-top:10px;">
		                        <xsl:attribute name="src">
		                        <xsl:value-of select="concat('xsl/',$host,'/images/list-item-bg.gif')"/>
		                        </xsl:attribute>
		                        </IMG>
                             </xsl:if>
                          </xsl:if>
                          	 <br/>
		                 	 <U>
				   		       <xsl:for-each select="description/r">
						       <xsl:value-of select="."/> 
						       </xsl:for-each>
			                 </U>
				        </A>
		              </xsl:if>
		              </xsl:if>
		
			    </xsl:if>
		
		            <xsl:if test="image = ''" >
		                <A class="mostread_s3"  ><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><br/>
				      <!-- Обрабатываем перевод строки  -->
          				 <xsl:if test="count(description/r) > 1"> 
						 <xsl:if test="string-length(description/r[1]) > 3">
						 	<IMG style="margin-right: 1px;margin-bottom:10px;margin-top:10px;">
	                        <xsl:attribute name="src">
	                        <xsl:value-of select="concat('xsl/',$host,'/images/list-item-bg.gif')"/>
	                        </xsl:attribute>
	                        </IMG>
                            </xsl:if>
                         </xsl:if>
                         <br/>
		              <U>
			   		      <xsl:for-each select="description/r">
					      <xsl:value-of select="."/> <br/>
					      </xsl:for-each>
		              </U>
					</A>
			    </xsl:if>	
				 <!-- Кнопки удаления  , начало -->
				<xsl:if test="/document/admin/post_manager != ''">	
				<table><tr><td width="60px" style="padding-bottom: 5px;PADDING-TOP: 5px">																
					        <form name="product_del"  action="Productlist.jsp" method="POST">
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
								<INPUT style="padding-bottom: 5px;" class="button" TYPE="submit" name="submit" value="Delete"></INPUT>
					        </form>
					        </td>
					        <td width="5px">
					        </td>
					        <td style="padding-bottom: 5px;PADDING-TOP: 5px">
					        <form name="product_edit"  action="Productlist.jsp" method="POST">
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="co2" ></INPUT>
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
								<INPUT   class="button" TYPE="submit" name="submit" value="Edit" style="padding-bottom: 5px; margin-left: 0px"></INPUT>
					        </form>
				</td></tr></table>	
				</xsl:if>
			   <!-- Кнопки удаления  , конец  -->														
		        </LI>
		        </xsl:for-each>      
                <LI class="mostread_s3" >
                	
		        	<a title="All news" href="Productlist.jsp?catalog_id=-1">
		        	<IMG style="margin-right: 5px;margin-bottom:2px;">
                       		<xsl:attribute name="src">
                       		<xsl:value-of select="concat('xsl/',$host,'/images/list-item-bg.gif')"/>
                       		</xsl:attribute>
					</IMG>
		        	<u>News Archive</u>
		        	</a>
		        </LI>    
                              
            </UL></TD></TR></TBODY></TABLE></DIV></DIV></DIV></DIV></DIV></DIV>
						
							
							
			
</div>
			
<div id="container">
<div class="comp-cont" >
		<table  >
			<tr>
			<td valign="top">

							
							   
							
							     <TABLE height="30" >
					              <TBODY>
							              <TR>
								            <TD align="center">									
											    <xsl:if test="document/role_id = '2'">											    
														<div style="font-size: 12px">
														Button to edit the site<br/>
															<div class="change">													
															<A ><xsl:attribute name="HREF"><xsl:value-of select="document/admin/post_manager"/>
															</xsl:attribute><DIV >Menu</DIV></A>
															</div>
													    </div>										    
											    </xsl:if>								   
										    </TD>
										  </TR>
						              <TR>
						              <TD vAlign="center"  align="center" class="pagesLink">					              
						              <xsl:if test="((($page div 10) +1) &lt; 21)">
						              <table class="pagesTable" style="width: 80%; background-color:#CFCFCF; opacity: 0.7; ">
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
								
										<form id="formToPage" name="searchform2"  action="Productlist.jsp" method="post"><input name="offset" type="hidden" value="0" ></input></form>
								
	
	
						               </TD>
						              </TR>					              
					  	     </TBODY>
					  	   </TABLE>
							
							 
		
			            <DIV   >
			            
			            <TABLE >
			              <TBODY>
			              <TR>
			                <TD >                
					                  <TABLE >
						                    <TBODY>
								                    <xsl:if test="document/empty_page = 'true'" >
														<TR>
												          <TD style="background-color: #CFCFCF;" vAlign="top"> 
															On this page there is nothing
														  </TD>
														</TR>
										   	        </xsl:if>
								   	        
											   	        <xsl:if test="document/empty_page != 'true'" >
									                    <TR>
									                      <TD > 
										                        
											                           <DIV  >
													                        <xsl:if test="document/empty_page != 'true'" >
																					<table >
																						<xsl:call-template name="prList">
													               							<xsl:with-param name="prCount" select="count(document/product_list/product)"/>
													            						</xsl:call-template>
																					</table>	
																			</xsl:if>	
												                       </DIV>
									                  		</TD>
									                  	</TR>
									                  	</xsl:if>
							                  </TBODY>
					                  </TABLE>
			                  
			                  	</TD>
			                  </TR>
			                  </TBODY>
			                  
			                  </TABLE>
			                  </DIV>
								<span class="article_separator"></span>

				</td>
			</tr>

		<tr > 
		 <td >
		
		  </td>
		</tr>
	</table>

</div>
</div>
				
				
				
				</div>
			</div>
		</div>
	</div>


	<DIV id="footer" style="opacity: 0.8"   >
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

<xsl:template name="prList">
			<xsl:param name="position" select="1"/>
			<xsl:param name="prCount"/>	
			<tr>
			<td>
			
			<table  border="0" cellspacing="0" cellpadding="0"  >
			  <tr>
			    <td  valign="middel"  style="background-color: #CFCFCF; text-align: left; margin-bottom: 3px;opacity: 1.0" >
			    
					<div class="contentheading_1"><a ><xsl:attribute name="HREF"><xsl:value-of select="document/product_list/product[$position]/policy_url"/></xsl:attribute> <xsl:value-of select="document/product_list/product[$position]/name"/></a></div>
					<xsl:if test="document/product_list/product[$position]/amount != 0">
					<div style="background-color: #CFCFCF; text-align: right; margin-bottom: 3px"><font class="price" ><xsl:value-of select="document/product_list/product[$position]/amount"/>  </font> </div>
					</xsl:if>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"  >
				  		<tr>
							<td>
								<xsl:if test="document/product_list/product[$position]/icon != ''"> 						                			
											        	<A>
														<xsl:attribute name="HREF"><xsl:value-of select="document/product_list/product[$position]/policy_url"/></xsl:attribute>
														<img  height="150" border="0" style="float: left; margin: 10px;"  >
									                                   	<xsl:attribute name="alt">
									   				    	  <xsl:for-each select="document/product_list/product[$position]/description/r">
														    <xsl:value-of select="."/> 
												   	    	  </xsl:for-each>
													    	</xsl:attribute>
														<xsl:attribute name="src">
														<xsl:value-of select="document/product_list/product[$position]/icon"/></xsl:attribute>
														</img>
														</A> 				     				   				
					           </xsl:if>
								
							</td>
						</tr>
						<tr>
							<td>
								
								<a style="float: left; margin: 10px;" > <xsl:attribute name="HREF"><xsl:value-of select="document/product_list/product[$position]/policy_url"/></xsl:attribute>
											   				    	  <xsl:for-each select="document/product_list/product[$position]/description/r">
																    <xsl:value-of select="."/> 
														   	    	  </xsl:for-each>
								</a>
							</td>
						</tr>
					</table>
					
				</td>
				<td width="10px">
				</td>
			    <td style="background-color: #CFCFCF; text-align: left; margin-bottom: 3px;opacity: 1.0"    >
			   		<xsl:if test="($position+1) &lt;= ($prCount)">
					<div class="contentheading_1"><a><xsl:attribute name="HREF"><xsl:value-of select="document/product_list/product[$position+1]/policy_url"/></xsl:attribute> <xsl:value-of select="document/product_list/product[$position+1]/name"/></a></div>
					<xsl:if test="document/product_list/product[$position+1]/amount != 0">
					<div style="background-color: #CFCFCF; text-align: right; margin-bottom: 3px"><font class="price" ><xsl:value-of select="document/product_list/product[$position+1]/amount"/>  </font> </div>
					</xsl:if>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  		<tr>
							<td>
							<xsl:if test="document/product_list/product[$position+1]/icon != ''"> 						                			
								<A>
									<xsl:attribute name="HREF"><xsl:value-of select="document/product_list/product[$position+1]/policy_url"/></xsl:attribute>
														<img height="150"  border="0"  style="float: left; margin: 10px;" >
									                        <xsl:attribute name="alt">
									   				    	  <xsl:for-each select="document/product_list/product[$position+1]/description/r">
														   		 <xsl:value-of select="."/> 
												   	    	  </xsl:for-each>
													    	</xsl:attribute>
														<xsl:attribute name="src">
														<xsl:value-of select="document/product_list/product[$position+1]/icon"/></xsl:attribute>
														</img>
								</A> 							     				  				
					        </xsl:if>
							
							</td>
						</tr>
						<tr>
							<td>
							
								<a style="float: left; margin: 10px;" > <xsl:attribute name="HREF"><xsl:value-of select="document/product_list/product[$position+1]/policy_url"/></xsl:attribute>
											   				    	  <xsl:for-each select="document/product_list/product[$position+1]/description/r">
																    <xsl:value-of select="."/> 
														   	    	  </xsl:for-each>
								</a>
							</td>
						</tr>
					</table>
					</xsl:if>
				</td>
			  </tr>
			  <tr>
			  	<td >
					<div>
					<xsl:choose> 

									   <!-- Кнопки удаления  , начало -->
									       <xsl:when test="/document/role_id = '2'">									
									       
										
											<table style="height: 30px;">
											    <tbody>
											        <tr>
											         <td style="padding-top: 7px">
											                <form name="product_edit"  action="Productlist.jsp" method="POST">
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="product" ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="document/product_list/product[$position]/product_id"/></xsl:attribute></INPUT>
													<INPUT class="button" TYPE="submit" name="submit" value="Edit"></INPUT>
											                </form>
											         </td>
												 <td style="padding-left: 10px; padding-top: 7px">  
											                <form name="product_del"  action="Productlist.jsp" method="POST">
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="document/product_list/product[$position]/product_id"/></xsl:attribute></INPUT>
													<INPUT class="button" TYPE="submit" name="submit" value="Delete"></INPUT>
											                </form>
											         </td>
											        </tr>
											    </tbody>
											</table>								            
									      									
									
									       
								    </xsl:when> 
								    <!-- Кнопки удаления  , конец  -->
								
								
								
								
								  <!--  for Edit  User context  Кнопки удаления  , начало -->
								   <xsl:when test="/document/role_id = '1'">
									<xsl:variable name="user_id" select="number(/document/owner_user_id)"/> 
									<xsl:variable name="owner_id" select="number(/document/product_list/product[$position]/user_id)"/>
								       <xsl:if test="$owner_id = $user_id" >								
										<table style="height: 30px;">
										    <tbody>
										        <tr>
										           <td style="padding-top: 7px">
								
										                <form name="product_edit"  action="Productlist.jsp" method="POST">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="userinfo" ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="document/product_list/product[$position]/product_id"/></xsl:attribute></INPUT>
												<INPUT  class="button" TYPE="submit" name="submit" value="Edit"></INPUT>
										                </form>
										           </td>
											   <td style="padding-left: 10px; padding-top: 7px">  
										                <form name="product_del"  action="Productlist.jsp" method="POST">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="document/product_list/product[$position]/product_id"/></xsl:attribute></INPUT>
												<INPUT  class="button" TYPE="submit" name="submit" value="Delete"></INPUT>
										                </form>
										           </td>
										        </tr>
										    </tbody>
										</table>								           
									</xsl:if>
								   </xsl:when>  
								
								   </xsl:choose> 
								
								    <!-- Кнопки удаления  , конец  -->	
					
					</div>
					<xsl:if test="($position) &lt; (($prCount)-1)">
						<div class="line2"></div>
					</xsl:if>
			  	</td>
			  	<td width="10px"></td>
			  	<td >
			  		<xsl:if test="($position+1) &lt;= ($prCount)">
					<div>
					<xsl:choose> 

									   <!-- Кнопки удаления  , начало -->
									       <xsl:when test="/document/role_id = '2'">								
									      
										
											<table style="height: 30px;">
											    <tbody>
											        <tr>
											         <td style="padding-top: 7px">
											                <form name="product_edit"  action="Productlist.jsp" method="POST">
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="product" ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="document/product_list/product[$position+1]/product_id"/></xsl:attribute></INPUT>
													<INPUT class="button" TYPE="submit" name="submit" value="Edit"></INPUT>
											                </form>
											         </td>
												 <td style="padding-left: 10px; padding-top: 7px">  
											                <form name="product_del"  action="Productlist.jsp" method="POST">
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="document/product_list/product[$position+1]/product_id"/></xsl:attribute></INPUT>
													<INPUT class="button" TYPE="submit" name="submit" value="Delete"></INPUT>
											                </form>
											         </td>
											        </tr>
											    </tbody>
											</table>									            
									       	
								    </xsl:when> 
								    <!-- Кнопки удаления  , конец  -->
								
								
								
								
								  <!--  for Edit  User context  Кнопки удаления  , начало -->
								   <xsl:when test="/document/role_id = '1'">
									<xsl:variable name="user_id" select="number(/document/owner_user_id)"/> 
									<xsl:variable name="owner_id" select="number(/document/product_list/product[$position+1]/user_id)"/>
								       <xsl:if test="$owner_id = $user_id" >								
										<table style="height: 30px;">
										    <tbody>
										        <tr>
										           <td style="padding-top: 7px">
								
										                <form name="product_edit"  action="Productlist.jsp" method="POST">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="userinfo" ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="document/product_list/product[$position+1]/product_id"/></xsl:attribute></INPUT>
												<INPUT  class="button" TYPE="submit" name="submit" value="Edit"></INPUT>
										                </form>
										           </td>
											   <td style="padding-left: 10px; padding-top: 7px">  
										                <form name="product_del"  action="Productlist.jsp" method="POST">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="document/product_list/product[$position+1]/product_id"/></xsl:attribute></INPUT>
												<INPUT  class="button" TYPE="submit" name="submit" value="Delete"></INPUT>
										                </form>
										           </td>
										        </tr>
										    </tbody>
										</table>								           
									</xsl:if>
								   </xsl:when>  
								
								   </xsl:choose> 
								
								    <!-- Кнопки удаления  , конец  -->	
					
					</div>	
					<xsl:if test="($position) &lt; (($prCount)-2)">
						<div class="line2"></div>
					</xsl:if>	
					</xsl:if>	  	
			  	</td>			  
			  </tr>
			</table>
			</td>					
			</tr>
			<xsl:if test="$position+2 &lt;= $prCount">
				<xsl:call-template name="prList">
					<xsl:with-param name="position" select="$position+2"/>
					<xsl:with-param name="prCount" select="$prCount"/>
				</xsl:call-template>
			</xsl:if>
</xsl:template>
        


</xsl:stylesheet>