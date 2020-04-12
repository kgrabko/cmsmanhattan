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
	 <SCRIPT type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/mootools.js')"/></xsl:attribute></SCRIPT>
	 <SCRIPT type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/caption.js')"/></xsl:attribute></SCRIPT>
	 <SCRIPT type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/caption.js')"/></xsl:attribute></SCRIPT>

	<link type="text/css" rel="stylesheet"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/calendar.css')"/></xsl:attribute></link>	
	<script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/jquery.js')"/></xsl:attribute></script>
	<script type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/datepicker.js')"/></xsl:attribute></script>
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
				<a><xsl:attribute name="href">#</xsl:attribute><IMG alt="Logo"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/logo.gif')"/></xsl:attribute></IMG></a>
			</div>
	<div id="gradient">
		<div class="main">
			<div id="top">

					<div id="topmenu">
						<div class="module-topmenu">
						<ul class="menu-nav">
						
						<li class="item53">
						<a href="Productlist.jsp?catalog_id=-2">
						<span>Главная страница</span>
						</a>
						</li>
						
						<li class="item29">
						<a href="Productlist.jsp?catalog_id=-6">
						<span>Новые поступления</span>
						</a>
						</li>
						
						<LI class="item18">
	  					<A href="Productlist.jsp?catalog_id=-10">
	  					<SPAN>Самые просматриваемые</SPAN>
	  					</A>
	  					</LI>

						<LI class="item17">
	  					<A href="Policy.jsp?page=about">
	  					<SPAN>О компании</SPAN>
	  					</A>
	  					</LI>
	
	  						
						 <xsl:if test="document/login != 'user'">
	        			 <LI ><A href="Productlist.jsp?action=logoff">
	        	 		 <SPAN>Выход</SPAN></A>
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
			        				<xsl:if test="document/login != ''">   <!--  показывать если есть логин -->
									<B>Пользователь </B> 
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
						   		alt="Поиск по имени товара"   
						   		title="Поиск по имени товара">
						   		<xsl:attribute name="value">
						   		<xsl:value-of select="document/search_value"/>
						   		</xsl:attribute>
						   		</INPUT>
						   	  	<INPUT  class="button" type="image" value="Поиск" onClick="return top.search_word();return true"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/searchButton.gif')"/></xsl:attribute></INPUT>
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
                                    
                                      <a href="Productlist.jsp?catalog_id=-2" class="catalog" alt="Вернутся в начало рубрикатора" title="Вернутся в начало рубрикатора">
                                        <U><font size="2" >Все разделы</font></U>
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
			            <H3>Поиск по заказам</H3>           	
		            		<div style="padding:10px 0px 26px 25px"> 
		            		<FORM name="searchcreform"  action="OrderList.jsp"  method="POST">				
						  	   							
						  	   							<TABLE class="poll_s3" cellSpacing="0" cellPadding="1" width="95%" border="0">
													        
													        <TBODY>
													        <TR>
													          <TD align="middle">
													            <TABLE class="pollstableborder_s3" cellSpacing="0" cellPadding="0" border="0">
													            	<TBODY>
													            	
													            	  <xsl:if test="/document/criteria1_label != ''"> 
														              <TR>
														                <TD class="width sectiontableentry2" vAlign="top">
														                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria1_label"/>:</LABEL>
														                </TD>
														              </TR>
														              <TR>
														                <TD class="width sectiontableentry2" vAlign="top">
														                	<SELECT NAME = "creteria1_id" onChange="doChengeCreteria('creteria1_id', this.value)">
								 	   										<xsl:for-each select="document/creteria1/creteria1-item">						
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
														              </xsl:if>
														              
														              <xsl:if test="/document/criteria2_label != ''"> 
														              <TR>
														                <TD class="width sectiontableentry2" vAlign="top">
														                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria2_label"/>:</LABEL>
														                </TD>
														              </TR>
														              <TR>
														                <TD class="width sectiontableentry2" vAlign="top">
														                	<SELECT NAME = "creteria2_id" onChange="doChengeCreteria('creteria2_id', this.value)" >
								 	   											<xsl:for-each select="document/creteria2/creteria2-item">						
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
														              </xsl:if>
														              
														              <xsl:if test="/document/criteria3_label != ''"> 
														              <TR>
														                <TD class="width sectiontableentry2" vAlign="top">
														                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria3_label"/>: </LABEL>
														                </TD>
														              </TR>
														              <TR>
														                <TD class="width sectiontableentry2" vAlign="top">
														                	<SELECT NAME = "creteria3_id" onChange="doChengeCreteria('creteria3_id', this.value)"  >
								 	   										<xsl:for-each select="document/creteria3/creteria3-item">						
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
														              </xsl:if>
														              
														              <xsl:if test="/document/criteria4_label != ''">
														              <TR>
														                <TD class="width sectiontableentry2" vAlign="top">
														                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria4_label"/>: </LABEL>
														                </TD>
														              </TR>
														              <TR>
														                <TD class="width sectiontableentry2" vAlign="top">
														                	<SELECT NAME = "creteria4_id" onChange="doChengeCreteria('creteria4_id', this.value)"  >
								 	   										<xsl:for-each select="document/creteria4/creteria4-item">						
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
														              </xsl:if>
														              
														              <xsl:if test="/document/criteria5_label != ''"> 
														              <TR>
														                <TD class="width sectiontableentry2" vAlign="top">
														                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria5_label"/>: </LABEL>
														                </TD>
														              </TR>
														              <TR>
														                <TD class="width sectiontableentry2" vAlign="top">
														                	<SELECT NAME = "creteria5_id" onChange="doChengeCreteria('creteria5_id', this.value)"  >
								 	   										<xsl:for-each select="document/creteria5/creteria5-item">						
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
														              </xsl:if>
														              
														              <xsl:if test="/document/criteria6_label != ''"> 
														              <TR>
														                <TD class="width sectiontableentry2" vAlign="top">
														                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria6_label"/>: </LABEL>
														                </TD>
														              </TR>
														              <TR>
														                <TD class="width sectiontableentry2" vAlign="top">
														                	<SELECT NAME = "creteria6_id" onChange="doChengeCreteria('creteria6_id', this.value)"  >
								 	   										<xsl:for-each select="document/creteria6/creteria6-item">						
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
														              </xsl:if>
														              
														              <xsl:if test="/document/criteria7_label != ''"> 
														              <TR>
														                 <TD class="width sectiontableentry2" vAlign="top">
														                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria7_label"/>: </LABEL>
														                </TD>
														              </TR>
														              <TR>
														                 <TD class="width sectiontableentry2" vAlign="top">
															                	<SELECT NAME = "creteria7_id" onChange="doChengeCreteria('creteria7_id', this.value)"  >
										   										<xsl:for-each select="document/creteria7/creteria7-item">							
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
														              </xsl:if>
														              
														              <xsl:if test="/document/criteria8_label != ''"> 
														              <TR>
														                <TD class="width sectiontableentry2" vAlign="top">
														                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria8_label"/>: </LABEL>
														                </TD>
														              </TR>
														              <TR>
														                 <TD class="width sectiontableentry2" vAlign="top">
															                	<SELECT NAME = "creteria8_id" onChange="doChengeCreteria('creteria8_id', this.value)"  >
										   										<xsl:for-each select="document/creteria8/creteria8-item">							
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
														              </xsl:if>
														              
														              <xsl:if test="/document/criteria9_label != ''"> 
														              <TR>
														                 <TD class="width sectiontableentry2" vAlign="top">
														                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria9_label"/>: </LABEL>
														                </TD>
														              </TR>
														              <TR>
														                 <TD class="width sectiontableentry2" vAlign="top">
															                	<SELECT NAME = "creteria9_id" onChange="doChengeCreteria('creteria9_id', this.value)"  >
										   										<xsl:for-each select="document/creteria9/creteria9-item">							
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
													              </xsl:if>
													              
													              <xsl:if test="/document/criteria10_label != ''"> 
													              <TR>
													                 <TD class="width sectiontableentry2" vAlign="top">
													                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria10_label"/>: </LABEL>
													                </TD>
													              </TR>
													              <TR>
													                 <TD class="width sectiontableentry2" vAlign="top">
														                	<SELECT NAME = "creteria10_id" onChange="doChengeCreteria('creteria10_id', this.value)"  >
									   										<xsl:for-each select="document/creteria10/creteria10-item">							
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
													              </xsl:if>	
													              
													              <TR>
													                 <TD class="width sectiontableentry2" vAlign="top">
													                	<LABEL for="voteid25">с даты: </LABEL>
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
													                	<LABEL for="voteid25">по дату: </LABEL>
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
																	<INPUT class="button"  type="submit" size="20" value="Искать"  tabindex="30002"   />
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
			            <H3>Поиск заказа</H3>           	
		            		<div style="padding:10px 0px 26px 25px"> 
		            		<FORM name="order" action="Order.jsp" method="POST" >	 						
						  	   							
	   						<TABLE class="poll" cellSpacing="0" cellPadding="1" width="95%" border="0">
					        <THEAD>
					        <TR>
					          <TD style="FONT-WEIGHT: bold">Введите номер заказа
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
									<INPUT class="button"  type="submit" size="20" value="Искать"  tabindex="30002"   />
					            </DIV></TD></TR></TBODY>
							</TABLE>
						  </FORM>
						 
						</div>
			            </DIV>
			            </DIV>
			            </DIV>
			            </DIV>
			</div>
					<div id="right">
			           
			           <DIV class="module" style="margin-right:0px">
			            <DIV class="first">
			            <DIV class="sec">
			            <DIV>
			            <H3>Мой кабинет</H3>
			          	
						  	<div style="padding:5px 0px 26px"> 
						  	<div class="cab"><a><xsl:attribute name="HREF"><xsl:value-of select="document/to_order_hist"/></xsl:attribute>Все заказы</a></div>
						  	<div class="cab"><a><xsl:attribute name="HREF"><xsl:value-of select="document/to_order"/></xsl:attribute>Текущий заказ</a></div>	
						  	<div class="cab"><a href="Policy.jsp?page=pay">Оплатить заказ</a></div>
						  	<!-- <div class="cab"><a><xsl:attribute name="HREF"><xsl:value-of select="document/to_account_history"/></xsl:attribute>Денежные операции</a></div>				      												             		    								
						  	<div class="cab" style="color: black;"><b>Баланc: </b> <xsl:value-of select="document/balans"/> рублей .</div> -->
						  	</div> 
						  	            
			            </DIV>
			            </DIV>
			            </DIV>
			            </DIV>
			            
			            <DIV class="module" style="margin-right:0px">
			            <DIV class="first">
			            <DIV class="sec">
			            <DIV>
			            <H3>Оплатить заказ</H3>
			          	<p class="text" style="padding:10px 20px 13px;"> 
						  	Вы можете оплачивать заказы,
						  	 используя кредитные карты или виртуальные кошельки, такие как Web money , Яндекс деньги , E-Port и другие
						  	
						  	<br/>
						  	<A href="Policy.jsp?page=pay">
						  		<!--  <xsl:attribute name="HREF"><xsl:value-of select="document/to_pay"/></xsl:attribute>-->
								<IMG border="0" height="20" width="140"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/credit-cards.jpg')"/></xsl:attribute></IMG> 
							</A> 
							</p>             
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
							     <h3 style="padding-left: 20px; font-size: 17px">Список всех ваших заказов</h3>
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
					                	<TD class="order_head">Заказ</TD>
					                	<TD class="order_head">Сумма</TD>
					                	<TD class="order_head">Дата</TD>
					                	<TD class="order_head">Статус</TD>
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
																									                                
				                                <TD><FORM name="order" action="Order.jsp" method="POST" ><INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="order_id"><xsl:attribute name="value"><xsl:value-of select="order_id"/></xsl:attribute></INPUT><INPUT TYPE="submit" class="button" name="submit" value="Подробнее"></INPUT></FORM></TD>
			                                
			                                </TR>
										</xsl:for-each>
										 <TR>
										 	<TD colspan="5" align="center" class="none_border">
										 		<br/>
										 		<a><xsl:attribute name="HREF"><xsl:value-of select="document/prev"/></xsl:attribute>
										 			<IMG height="15" alt="назад" title="назад" src="" width="15" border="0"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/previous.gif')"/></xsl:attribute></IMG>
										 		</a>
										 		<a><xsl:attribute name="HREF"><xsl:value-of select="document/next"/></xsl:attribute>
										 			<IMG height="15" alt="следующие" title="следующие" src="" width="15" border="0"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/next.gif')"/></xsl:attribute></IMG>
										 		</a>
										 	</TD>
										 </TR>
										 <tr>
											<td class="none_border">
												<span class="next">
													<a HREF = "#" onClick="javascript:history.back()"  >
														<strong>	
															назад
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
		Internet shop  . Copyright 2010 <A HREF="http://www.siteoneclick.com"> Center Business Solutions ltd </A> . Все права защищены.  
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