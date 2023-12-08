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
	
	 <link rel="stylesheet" type="text/css" media="screen"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/menu.css')"/></xsl:attribute></link>
	 <script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/menu.js')"/></xsl:attribute></script>
	
	
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


<body id="body"><xsl:attribute name="onload"><xsl:value-of select="concat('setDates(',$datefrom,',',$dateto, ');')"/></xsl:attribute>
	<div class="main" style="background-color: #E7E7DF">
				<IMG alt="Logo"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/logo.gif')"/></xsl:attribute></IMG>
			</div>
	<div id="gradient">
		<div class="main">
			<div id="top">

					<div id="topmenu">
						<div class="module-topmenu">
						
						<!--  <ul class="menu-nav">  -->
						<ul id="sddm" >
						<li class="item53">
						<a href="Productlist.jsp?catalog_id=-2">
						<span>Home page</span>
						</a>
						</li>
						<!-- 
						<li class="item29">
						<a href="Productlist.jsp?catalog_id=-6">
						<span>New Arrivals</span>
						</a>
						</li>
						
						<LI class="item18">
	  					<A href="Productlist.jsp?catalog_id=-10">
	  					<SPAN>Popular</SPAN>
	  					</A>
	  					</LI>
	  					 -->
	  						  				 <xsl:for-each select="document/menu/menu-item">
	  				   <xsl:variable name="rowNum" select="position()" /> 
											       <xsl:if test="item != ''">
													   <xsl:if test="code != '-1'">
														   <xsl:if test="code != '-2'">
																   <xsl:if test="code != '-3'">
																         
																	             <LI class="item17">
																					  <A    onmouseout="mclosetime()" >
																					  <xsl:attribute name="HREF"><xsl:value-of select="url"/></xsl:attribute>
																					  <xsl:attribute name="onmouseover">mopen('m<xsl:value-of select="$rowNum"/>')</xsl:attribute>
																					  <SPAN><xsl:value-of select="item"/></SPAN>
																					  </A>
																					  
													    							<div  onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
													    								 <xsl:attribute name="id"><xsl:value-of select="concat('m',$rowNum)"/></xsl:attribute>	
																					  
																					  <xsl:for-each select="submenu-item">
																					  <A ><xsl:attribute name="HREF"><xsl:value-of select="suburl"/></xsl:attribute>
											           									<xsl:value-of select="subitem"/>
													     							 </A>
													     							 
																					 </xsl:for-each>
																					</div>
																				  </LI>
															 </xsl:if>
												 		 </xsl:if>
											 		 </xsl:if>
										 		 </xsl:if>
						</xsl:for-each>
	  					
						
						<LI class="item18">
	  					<A href="Authorization.jsp?Login=">
	  					<SPAN>Registration</SPAN>
	  					</A>
	  					</LI>
	  				
		
						 <xsl:if test="document/role_id != 0">
	        			 <LI ><A href="Productlist.jsp?action=logoff">
	        	 		 <SPAN>Exit</SPAN></A>
	        	 		 </LI>
	      				 </xsl:if>
	      				 
	      				

						</ul>
						</div>
				</div>
			</div>




			    <DIV class="indent" >
			    	<DIV class="moduletable">
			     		<TABLE class="who_is_online" style="WIDTH: auto" align="right">
			       			<TBODY>
			        			<TR>
			          				<TD>
			        				<xsl:if test="document/login != ''">   <!--  ���������� ���� ���� ����� -->
									<B>User</B> 
									 <a href="Authorization.jsp" style="margin-left: 5px; text-decoration: none">				                
									
								 	<xsl:if test="document/login = 'user'">   <!--  ���������� ���� ��� ������ -->
									<font class="user0">
										<xsl:value-of select="document/login"/>
									</font>
									</xsl:if>
									
									<xsl:if test="document/login != 'user'">   <!--  ���������� ���� ���� ����� -->
									<xsl:if test="document/role_id = 1"> <!--  ��������� ���� ���� -->
										<font class="user1">
											<xsl:value-of select="document/login"/>
									</font>
								</xsl:if>
											 			
								<xsl:if test="document/role_id = 2"><!--  ������� ���� ����� -->
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
				<div class="mid-left">
					<div class="mid-right">
					
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
			
			<div id="content">
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
					
					<!-- � ����������� -->
			           
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
						  	<div class="cab" style="color: black;"><b>Available funds: </b> <xsl:value-of select="document/balans"/> ������ .</div> -->
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
<div class="comp-cont">
		<table class="blog" cellpadding="0" cellspacing="0">
			<tr>
			<td valign="top">
				<div class="article-bg">
					<div class="article-left">
						<div class="article-right">
							<DIV style="padding-left: 20px; ">
							     <h3 style="padding-left: 20px; font-size: 17px">The list of all your orders</h3>
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
				                                
				                                <TD><xsl:value-of select="end_amount"/>/<xsl:value-of select="currency_lable"/></TD>
				                                
				                                <TD><xsl:value-of select="cdate"/></TD>
				                                
												<TD>[<xsl:value-of select="paystatus_lable"/>]</TD>
																									                                
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


	<DIV id="footer">
		<DIV class="main">
		<div class="space">
		Internet shop . Copyright 2010 
		 <A HREF="http://www.siteoneclick.com"> Center Business Solutions Ltd </A>.  All rights reserved 
		</div>
		</DIV>
	</DIV>

</body>









</HTML>
</xsl:template>
</xsl:stylesheet><!-- Stylus Studio meta-information - (c) 2004-2006. Progress Software Corporation. All rights reserved.
<metaInformation>
<scenarios/><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/><MapperBlockPosition></MapperBlockPosition><TemplateContext></TemplateContext><MapperFilter side="source"></MapperFilter></MapperMetaTag>
</metaInformation>
-->