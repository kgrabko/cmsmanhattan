<?xml version='1.0' encoding='windows-1251' ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"  xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
<xsl:output method="html" indent="yes"/>
<xsl:output encoding="UTF-8"/>
<xsl:strip-space elements="*"/>


<xsl:template match="/">
	<xsl:variable name="host" select="string(document/host)"/>	
	<xsl:variable name="user_id" select="number(/document/owner_user_id)"/> 
	<xsl:variable name="role" select="document/role_id"/> 
	
	<xsl:variable name="datefrom" select="document/datefrom"/>
    <xsl:variable name="dateto" select="document/dateto"/>
<HTML>
<HEAD>
<META HTTP-EQUIV="no-cache"/>
 <title><xsl:value-of select="document/title"/></title>
 
     <LINK rel="stylesheet" type="text/css"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/template.css')"/></xsl:attribute></LINK> 
     <LINK rel="stylesheet" type="text/css"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/constant.css')"/></xsl:attribute></LINK> 
	 <SCRIPT type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/caption.js')"/></xsl:attribute></SCRIPT>

	<link type="text/css" rel="stylesheet"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/calendar.css')"/></xsl:attribute></link>	
	<script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/jquery_v2009.js')"/></xsl:attribute></script>
	<script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/datepicker.js')"/></xsl:attribute></script>
	
	 <link rel="stylesheet" type="text/css" media="screen"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/style.css')"/></xsl:attribute></link>

<!--
	 <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/script.responsive.js')"/></xsl:attribute></script>
-->

     <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/script.js')"/></xsl:attribute></script>	
	
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
</HEAD>


<body ><xsl:attribute name="onload"><xsl:value-of select="concat('setDates(',$datefrom,',',$dateto, ');')"/></xsl:attribute>

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
   					 
   					  
   					   <li><A href="Productlist.jsp?action=logoff">Exit</A></li>
   					   
   					   <li><A href="Order.jsp">Cart </A>
	        	 	   </li>
	        	 	   
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
										<font class="user1">
											<xsl:value-of select="document/login"/>
									</font>
								</xsl:if>
											 			
								<xsl:if test="document/role_id = 2"><!--  красный если админ -->
									<font class="user2">
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
	
	

			<div id="mid">
				<div class="mid-left_">
					<div class="mid-right_">
					
					  	<div id="search">
							<div class="module-search">
								<FORM name="searchform"  action="Productlist.jsp" method="POST" >
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
			
			<div id="content" style="opacity: 0.9"   >
					<div class="width">
					<div id="left">
					<DIV class="module" style="margin-right:0px">
			            <DIV class="first">
			            <DIV class="sec">
			            <DIV>
			            <H3>Search orders</H3>           	
		            		<div style="padding:10px 0px 26px 25px"> 
		            		<FORM name="searchcreform"  action="OrderList.jsp"  method="POST">				
						  	   							
						  	   							<TABLE class="poll_s3" cellSpacing="0" cellPadding="1" width="95%" border="0">
													        
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
													                	<INPUT id="datepicker1" size="7" readonly="true"  type="text" style="width:127px; color: black"></INPUT>
													                	
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
													                	<INPUT id="datepicker2" size="7"  readonly="true" type="text" style="width:127px; color: black"></INPUT>
													                </TD>
													              </TR>
													              										           												                                                         												           																		            																		            																            															              																											      
													      </TBODY></TABLE></TD></TR>
													        <TR>
													          <TD>
													            <DIV style="padding-left:20px; padding-top: 10px">
																	<INPUT class="button"  type="submit" size="20" value="Search"  tabindex="30002"   />
													            </DIV>
													            </TD></TR></TBODY>
															</TABLE>
														  </FORM> </div>           
					
			            </DIV>
			            </DIV>
			            </DIV>
			            </DIV>
					
					<!-- О регистрации -->
			           
			            <DIV class="module" style="margin-right:0px">
			            <DIV class="first">
			            <DIV class="sec">
			            <DIV>
			            <H3>Search order</H3>           	
		            		<div style="padding:10px 0px 26px 25px"> 
		            		<FORM name="order" action="Order.jsp" method="POST" >	 						
						  	   							
	   						<TABLE class="poll" cellSpacing="0" cellPadding="1" width="95%" border="0">
					        <THEAD>
					        <TR>
					          <TD style="FONT-WEIGHT: bold">Enter your order number
					        </TD></TR></THEAD>
					        <TBODY>
					        <TR>
					          <TD align="middle">
					            <TABLE class="pollstableborder" cellSpacing="0" cellPadding="0" border="0">
					            <TBODY>            	 
					              <TR>
					                 <TD class="width sectiontableentry2" vAlign="top">
					                 	<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="TEXT" NAME="order_id" style="width:147px"><xsl:attribute name="value"><xsl:value-of select="document/list/order/order_id"/></xsl:attribute></INPUT>													                	
					                </TD>
					              </TR>
					      		</TBODY>
					      		</TABLE>
					      		</TD>
					      	</TR>
					        <TR>
					          	<TD class="find">
					            	<DIV style="padding-left:30px; padding-top: 10px">
										<INPUT class="button" style="padding-top: 4px" type="submit" size="20" value="Search"  tabindex="30002"   />
					            	</DIV>
					           	</TD>
					         </TR>
					         
					         </TBODY>
							</TABLE>
						  </FORM>
						 
						</div>
			            </DIV>
			            </DIV>
			            </DIV>
			            </DIV>
			            
			             <xsl:if test="document/role_id = 2">
			            <DIV class="module" style="margin-right:0px">
			            <DIV class="first">
			            <DIV class="sec">
			            <DIV>
			            <H3>Search order by status</H3>           	
		            		<div style="padding:10px 0px 26px 25px"> 
		            		<FORM name="order" action="OrderList.jsp" method="POST" >	 						
						    <INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="searchquery" value="3"></INPUT>	   							
	   						<TABLE class="poll" cellSpacing="0" cellPadding="1" width="95%" border="0">
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
														<SELECT NAME = "order_paystatus"  style="width: 170px">
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
														<SELECT NAME = "order_status"  style="width: 170px">
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
					            </DIV></TD></TR></TBODY>
							</TABLE>
						  </FORM>
						 
						</div>
			            </DIV>
			            </DIV>
			            </DIV>
			            </DIV>
			            </xsl:if>
			</div>
					<div id="right">
			           
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
						  	</div> 
						  	            
			            </DIV>
			            </DIV>
			            </DIV>
			            </DIV>
			            
			            <DIV class="module" style="margin-right:0px">
			            <DIV class="first">
			            <DIV class="sec">
			            <DIV>
			            <H3>Pay Order</H3>
			          	<P class="text" style="padding:10px 20px 13px;">
			          	 <A href="Policy.jsp?page=pay"> How can I pay my order </A>
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
<div class="comp-cont" style="background-color: #CFCFC8; text-align: left; margin-bottom: 3px" >
		<table class="blog" cellpadding="0" cellspacing="0">
			<tr>
			<td valign="top">
				<div class="article-bg_">
					<div class="article-left_">
						<div class="article-right_">
							<DIV style="padding-left: 20px; ">
								 <br/>
							     <h3 style="padding-left: 20px; font-size: 17px">The list of all your orders</h3>
							      <br/>
								    <DIV >
								    <table border="0" class="orders" style="width:500px">															   
									<colgroup>
										<col width="50px"/>
										<col width="80"/>
										<col width="140"/>
										<col width="70"/>
										<col width="80"/>
									</colgroup>
									<thead>									
					                <TR>
					                	<TD class="order_head">Order</TD>
					                	<TD class="order_head">Sum</TD>
					                	<TD class="order_head">Date</TD>
					                	<TD class="order_head">Status</TD>
					                	<TD class="order_head"></TD>
					                </TR>
					                </thead>					               
					                <tbody>
										<xsl:for-each select="document/list/order">
			                                <TR>
				                                <TD>N<xsl:value-of select="order_id"/></TD>
				                                
				                                <TD><xsl:value-of select="end_amount"/></TD>
				                                
				                                <TD><xsl:value-of select="cdate"/></TD>
				                                
												<TD style="width: 150px;" >[<xsl:value-of select="paystatus_lable"/>]</TD>
																									                                
				                                <TD><FORM name="order" action="Order.jsp" method="POST" ><INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="order_id"><xsl:attribute name="value"><xsl:value-of select="order_id"/></xsl:attribute></INPUT><INPUT TYPE="submit" class="button" name="submit" value="More in detail"></INPUT></FORM></TD>
			                                
			                                </TR>
										</xsl:for-each>
										 <TR>
										 	<TD colspan="5" align="center" class="none_border">
										 		<br/>
										 		<!-- 
										 		<a><xsl:attribute name="HREF"><xsl:value-of select="document/prev"/></xsl:attribute>
										 			<IMG height="15" alt="Back" title="Back" src="" width="15" border="0"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/previous.gif')"/></xsl:attribute></IMG>
										 		</a>
										 		<a><xsl:attribute name="HREF"><xsl:value-of select="document/next"/></xsl:attribute>
										 			<IMG height="15" alt="The following" title="The following" src="" width="15" border="0"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/next.gif')"/></xsl:attribute></IMG>
										 		</a>
										 		 -->
										 	</TD>
										 </TR>
										 <tr>
											<td class="none_border">
												<span class="next">
													<a HREF = "#" onClick="javascript:history.back()"  >
														<strong>	
															Back
														</strong>	
													</a>
												</span>
											</td>											
										</tr>
							    	</tbody>
								</table>
								    </DIV>
								</DIV>
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



</body>



</HTML>
</xsl:template>
</xsl:stylesheet>
