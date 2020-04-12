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
 
     <LINK id="style2" rel="stylesheet" type="text/css"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/template.css')"/></xsl:attribute></LINK> 
	 <LINK rel="stylesheet" type="text/css"><xsl:attribute name="href"><xsl:value-of select="concat('xsl/',$host,'/constant.css')"/></xsl:attribute></LINK>
	 <SCRIPT type="text/javascript"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/caption.js')"/></xsl:attribute></SCRIPT>
	 
</HEAD>

<body id="body">

<xsl:attribute name="onload">
<xsl:value-of select="concat('setCurrent(',$page,',',$role, ');')"/>
</xsl:attribute>
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
						<span>������� ��������</span>
						</a>
						</li>
						
						<li class="item29">
						<a href="Productlist.jsp?catalog_id=-6">
						<span>����� �����������</span>
						</a>
						</li>
						
						<LI class="item18">
	  					<A href="Productlist.jsp?catalog_id=-10">
	  					<SPAN>����� ���������������</SPAN>
	  					</A>
	  					</LI>
	  					
						<LI class="item17">
	  					<A href="Policy.jsp?page=about">
	  					<SPAN>� ��������</SPAN>
	  					</A>
	  					</LI>
	  				
		
						 <xsl:if test="document/login != 'user'">
	        			 <LI ><A href="Productlist.jsp?action=logoff">
	        	 		 <SPAN>�����</SPAN></A>
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
									<B>������������ </B> 
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
								<FORM name="searchform"  action="Productlist.jsp"  method="POST">
								<div class="search">
								<INPUT class="inputbox" 
								id="search_value"  
						   		name="search_value" 
						   		type="text"  
						   		size="20" 
						   		alt="����� �� ����� ������"   
						   		title="����� �� ����� ������">
						   		<xsl:attribute name="value">
						   		<xsl:value-of select="document/search_value"/>
						   		</xsl:attribute>
						   		</INPUT>
						   	  	<INPUT  class="button" type="image" value="�����" onClick="return top.search_word();return true"><xsl:attribute name="src"><xsl:value-of select="concat('xsl/',$host,'/images/searchButton.gif')"/></xsl:attribute></INPUT>
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
					<div class="module_menu">
					<div class="first">
						<div class="sec">
							<h3>�������</h3>
								<div class="box-indent">
								<div class="width">
								
								 <xsl:if test="count(document/newslist/news) != 0">
				                  <UL class="menu">
				                  <xsl:for-each select="document/newslist/news">
				                    <LI>                   
									<A><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute>
						        	
								      <!-- ������������ ������� ������  -->
						             
							   		     <SPAN><xsl:for-each select="description/r">
									      	<xsl:value-of select="."/><BR/>
									     </xsl:for-each></SPAN></A>
						             				
									<!-- ������ ��������  , ������ -->
									
									<xsl:if test="/document/admin/post_manager != ''">
									
										<table style="width:150px">
										    <tbody>
										        <tr style="padding-bottom: 15px">
										         <td style="padding-bottom: 5px">
										         <form name="product_del" style="width:50px" action="Productlist.jsp" method="POST">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
												<INPUT class="button"  TYPE="submit" name="submit" value="�������"></INPUT>
										         </form>
										         </td>
										         <td width="5px"></td>
										         <td>
								
										        <form name="product_edit" style="width:50px"  action="Productlist.jsp" method="POST">
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="news"  ></INPUT>
												<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
												<INPUT class="button" TYPE="submit" name="submit" value="��������"></INPUT>
										        </form>
										         </td>
										        </tr>
										    </tbody>
										</table>
								
								
									</xsl:if>
								   <!-- ������ ��������  , �����  -->
				                    </LI>                    
				                  </xsl:for-each> 
				                  <LI>
				                    <A title="��� �������" href="Productlist.jsp?catalog_id=-1">
				                    <SPAN>����� ��������</SPAN>
				                    </A>
				                    
				                   
				                  </LI> 
				                  </UL>
				                  </xsl:if>
				                 
                  
													
								</div>
							</div>
						</div>
					</div>
					</div>
					
					<div class="module">
					<div class="first">
						<div class="sec">
							<h3>����������� �����</h3>
							<div class="box-indent">
								<div class="width">
									<TABLE>
					              <TBODY>
					              <TR>
					                <TD class="over">
					                  <FORM name="searchcreform"  action="Productlist.jsp" method="POST" >	  						
										<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="searchquery" VALUE="6"  ></INPUT>
					 					<TABLE class="poll" cellSpacing="0" cellPadding="1" width="95%" border="0">
								        
								        <TBODY>
								        <TR>
								          <TD align="middle">
								            <TABLE class="pollstableborder_s4" cellSpacing="0" cellPadding="0" border="0">
								            	<TBODY>			            	
								            	  <xsl:if test="/document/criteria1_label != ''"> 
									              <TR>
									                <TD class="sectiontableentry2_s4" vAlign="top">
									                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria1_label"/>:</LABEL>
									                </TD>
									              </TR>
									              <TR>
									                <TD class="sectiontableentry2_s4" vAlign="top">
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
									                <TD class="sectiontableentry2_s4" vAlign="top">
									                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria2_label"/>:</LABEL>
									                </TD>
									              </TR>
									              <TR>
									                <TD class="sectiontableentry2_s4" vAlign="top">
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
									                <TD class="sectiontableentry2_s4" vAlign="top">
									                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria3_label"/>: </LABEL>
									                </TD>
									              </TR>
									              <TR>
									                <TD class="sectiontableentry2_s4" vAlign="top">
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
									                <TD class="sectiontableentry2_s4" vAlign="top">
									                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria4_label"/>: </LABEL>
									                </TD>
									              </TR>
									              <TR>
									                <TD class="sectiontableentry2_s4" vAlign="top">
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
									                <TD class="sectiontableentry2_s4" vAlign="top">
									                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria5_label"/>: </LABEL>
									                </TD>
									              </TR>
									              <TR>
									                <TD class="sectiontableentry2_s4" vAlign="top">
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
									                <TD class="sectiontableentry2_s4" vAlign="top">
									                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria6_label"/>: </LABEL>
									                </TD>
									              </TR>
									              <TR>
									                <TD class="sectiontableentry2_s4" vAlign="top">
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
									                <TD class="sectiontableentry2_s4" vAlign="top">
									                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria7_label"/>: </LABEL>
									                </TD>
									              </TR>
									              <TR>
									                <TD class="sectiontableentry2_s4" vAlign="top">
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
									                <TD class="sectiontableentry2_s4" vAlign="top">
									                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria8_label"/>: </LABEL>
									                </TD>
									              </TR>
									              <TR>
									                <TD class="sectiontableentry2_s4" vAlign="top">
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
									                <TD class="sectiontableentry2_s4" vAlign="top">
									                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria9_label"/>: </LABEL>
									                </TD>
									              </TR>
									              <TR>
									                <TD class="sectiontableentry2_s4" vAlign="top">
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
									                <TD class="sectiontableentry2_s4" vAlign="top">
									                	<LABEL for="voteid25"><xsl:value-of select="/document/criteria10_label"/>: </LABEL>
									                </TD>
									              </TR>
									              <TR>
									                <TD class="sectiontableentry2_s4" vAlign="top">
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
														<TD class="sectiontableentry2_s4" vAlign="top">
								     					 	<LABEL for="voteid25">���� �: </LABEL>
														</TD>
													</TR>										              
									              <TR>
														<TD class="sectiontableentry2_s4" vAlign="top">
								     					 	<INPUT name="fromcost" size="7"  type="text" ><xsl:attribute name="value"><xsl:value-of select="/document/fromcost"/></xsl:attribute></INPUT>
														</TD>
													</TR>													
							 						 <TR>
														<TD class="sectiontableentry2_s4" vAlign="top">
							         						<LABEL for="voteid25">���� ��: </LABEL>
														</TD>
													  </TR>	
                                        			<TR>
														<TD class="sectiontableentry2_s4" vAlign="top">
							         						<INPUT name="tocost" size="7"  type="text" ><xsl:attribute name="value"><xsl:value-of select="/document/tocost"/></xsl:attribute></INPUT>
														</TD>
													  </TR>														  								           												                                                         												           																		            																		            																            															              																											      
								      </TBODY></TABLE></TD></TR>
								        <TR>
								          <TD style="text-align: center;padding-bottom: 0px">
								            
												<INPUT class="button"  type="submit" size="20" value="������"  tabindex="30002"   />
								           
								           </TD></TR></TBODY>
										</TABLE>
									  </FORM></TD></TR></TBODY></TABLE>
														
											
									
														
								</div>
							</div>
						</div>
					</div>
				</div>
				
				
				
				
					<div class="module">
					<div class="first">
						<div class="sec">
							<h3>�������������</h3>
							<div class="box-indent">
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
										       <!-- ������������ ������� ������  -->
										       <img  alt=""  width="147" border="0">
										       <xsl:attribute name="src">
										       <xsl:value-of select="image"/>
										       </xsl:attribute>
										       </img>
								               <br/>
								               <xsl:if test="count(description/r) > 1"> 
												 <xsl:if test="string-length(description/r[1]) > 3">
												 	<IMG style="margin-right: 5px;margin-bottom:2px;">
							                        <xsl:attribute name="src">
							                        <xsl:value-of select="concat('xsl/',$host,'/images/list-item-bg.gif')"/>
							                        </xsl:attribute>
							                        </IMG>
					                             </xsl:if>
					                          </xsl:if>
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
										      <!-- ������������ ������� ������  -->
											 <xsl:if test="count(description/r) > 1"> 
												 <xsl:if test="string-length(description/r[1]) > 3">
												 	<IMG style="margin-right: 5px;margin-bottom:2px;">
							                        <xsl:attribute name="src">
							                        <xsl:value-of select="concat('xsl/',$host,'/images/list-item-bg.gif')"/>
							                        </xsl:attribute>
							                        </IMG>
					                             </xsl:if>
					                          </xsl:if>
								              <U>
									   		      <xsl:for-each select="description/r">
											      <xsl:value-of select="."/> <br/>
											      </xsl:for-each>
								              </U>
												</A>
										   </xsl:if>	
										 <!-- ������ ��������  , ������ -->
										<xsl:if test="/document/admin/post_manager != ''">	
										<table><tr><td width="60px" style="padding-bottom: 5px; padding-top: 5px">																
											        <form name="product_del"  action="Productlist.jsp" method="POST">
														<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
														<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
														<INPUT class="button" TYPE="submit" name="submit" value="�������"></INPUT>
											        </form>
											        </td>
											        <td width="5px">
											        </td>
											        <td style="padding-bottom: 5px; padding-top: 5px">
											        <form name="product_edit"  action="Productlist.jsp" method="POST">
														<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
														<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="co1" ></INPUT>
														<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
														<INPUT  class="button" TYPE="submit" name="submit" value="��������" style="margin-left: 0px"></INPUT>
											        </form>
										</td></tr></table>	
										</xsl:if>
									   <!-- ������ ��������  , �����  -->														
								        </LI>
								        </xsl:for-each>      
						               <LI class="mostread_s3" >
						              	   <A class="mostread_s3"  title="��� �������"   href="Productlist.jsp?catalog_id=-1">
 												 	<IMG style="margin-right: 5px;margin-bottom:2px;">
							                        <xsl:attribute name="src">
							                        <xsl:value-of select="concat('xsl/',$host,'/images/list-item-bg.gif')"/>
							                        </xsl:attribute>
							                        </IMG>

								                	
								                <u>����� ��������</u>
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
								<div class="module_s2">
								<div class="first">
								<div class="sec">

								<h3>�����������</h3>
								<div class="box-indent">
								<div class="width">
										
										<FORM action="Authorization.jsp"  id="form-login" class="form-login" method="POST">
						                  <FIELDSET class="input">
						                  <P id="form-login-username">
						                  <LABEL for="modlgn_username">�����</LABEL>
						                  <BR/>
						                  
						                  <INPUT id="modlgn_username" title="������������" class="inputbox" tabindex="10001"  AUTOCOMPLETE="off" TYPE="TEXT" NAME="Login" >
														<xsl:attribute name="value">
															<xsl:value-of select="document/login"/>
														</xsl:attribute>		
										  </INPUT> 
										  </P>
						                  
						                  <P id="form-login-password">
						                  <LABEL for="modlgn_passwd">������</LABEL><BR/>
						                  <INPUT class="inputbox" title="������" id="modlgn_passwd" tabindex="10002" AUTOCOMPLETE="off" TYPE="PASSWORD" NAME="Passwd1" ></INPUT> 
						                  </P>
						                  
						                  <div style="padding-left: 35px; padding-bottom: 5px;border: none; width: 100px">
						                  	<INPUT class="button" type="submit" value="�����" name="Submit"/> 
						                  </div>
						                  
						                  </FIELDSET> 
						                 </FORM>                  
						                  
						                  <UL class="log_list">
						                  <LI>
						                  <A href="Authorization.jsp?Login=newuser" style="COLOR: #072f3a">������ ������ ?
						                  </A> 
						                  </LI> 
						                  <LI>
						                  <A href="Authorization.jsp?Login=" style="COLOR: #072f3a">�����������
						                  </A>
						                  </LI>
						                  </UL>
											
								</div>
								</div>

						</div>
						</div>
						</div>
						</xsl:if>
						
						<!-- ������� (��� ������) -->
			            <xsl:if test="document/role_id != 0"><!--  ���� ����� -->
			            <DIV class="module" style="margin-right:0px">
			            <DIV class="first">
			            <DIV class="sec">
			            <DIV>
			            <H3>��� �������</H3>
			          	
						  	<div style="padding:5px 0px 26px"> 
						  	<div class="cab"><a><xsl:attribute name="HREF"><xsl:value-of select="document/to_order_hist"/></xsl:attribute>��� ������</a></div>
						  	<div class="cab"><a><xsl:attribute name="HREF"><xsl:value-of select="document/to_order"/></xsl:attribute>������� �����</a></div>	
						  	<div class="cab"><a href="Policy.jsp?page=pay">�������� �����</a></div>
						  	<!-- <div class="cab"><a><xsl:attribute name="HREF"><xsl:value-of select="document/to_account_history"/></xsl:attribute>�������� ��������</a></div>				      												             		    								
						  	<div class="cab" style="color: black;"><b>�����c: </b> <xsl:value-of select="document/balans"/> ������ .</div> -->
							<xsl:if test="document/role_id = 2">
							<div class="cab"><a href="Productlist.jsp?site=2">��� ���������������</a></div>
							<div class="cab"><a href="Support.jsp">������������</a></div>
							<div class="cab"><a href="Seo.jsp">����������� �����</a></div>
							<div class="cab"><a href="Documentation.jsp">������������</a></div>
							<div class="cab"><a href="Domain.jsp">��������� �����</a></div>
							<div class="cab"><a href="EMail.jsp">�������� ����</a></div>
							<div class="cab"><a href="Design.jsp">����� �������</a></div>
							<div class="cab"><a href="Productlist.jsp?site=2">www.siteoneclick.com</a></div>
							</xsl:if>

						  	</div> 
						  	            
			            </DIV>
			            </DIV>
			            </DIV>
			            </DIV>
			            
			            <DIV style="">
			            <DIV class="module" style="height : expression(parentNode.clientHeight > 300? '347px' : 'auto');max-height: 347px;height: auto;">
						<DIV class="first"  >
						<DIV class="sec" style="height : expression(parentNode.clientHeight > 300? '347px' : 'auto');max-height: 347px;height: auto;">
						 <H3>������� </H3>
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
										          <TD   colspan="2"  height="10" style="text-align: left; padding-left: 5px">
										              <TABLE cellSpacing="0" cellPadding="0" >
											              <TBODY>
											              <TR>
											                <TD   width="25"  height="11" ></TD>
												                <TD height="11" style="text-align: left; padding-right: 10px">
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
			            </xsl:if>	
						
			<!-- �������������� ������ -->
            <DIV class="module" style="margin-right:0px">
            <DIV class="first">
            <DIV class="sec">
            <DIV>
            <H3>�������������</H3>
            <DIV class="box-indent">
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
				       <!-- ������������ ������� ������  -->
				       <img  alt=""  width="147" border="0">
				       <xsl:attribute name="src">
				       <xsl:value-of select="image"/>
				       </xsl:attribute>
				       </img>
		                <br/>
						<xsl:if test="count(description/r) > 1"> 
							 <xsl:if test="string-length(description/r[1]) > 3">
							 	<IMG style="margin-right: 5px;margin-bottom:2px;">
		                        <xsl:attribute name="src">
		                        <xsl:value-of select="concat('xsl/',$host,'/images/list-item-bg.gif')"/>
		                        </xsl:attribute>
		                        </IMG>
                             </xsl:if>
                          </xsl:if>
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
				      <!-- ������������ ������� ������  -->
          				 <xsl:if test="count(description/r) > 1"> 
						 <xsl:if test="string-length(description/r[1]) > 3">
						 	<IMG style="margin-right: 5px;margin-bottom:2px;">
	                        <xsl:attribute name="src">
	                        <xsl:value-of select="concat('xsl/',$host,'/images/list-item-bg.gif')"/>
	                        </xsl:attribute>
	                        </IMG>
                            </xsl:if>
                         </xsl:if>
		              <U>
			   		      <xsl:for-each select="description/r">
					      <xsl:value-of select="."/> <br/>
					      </xsl:for-each>
		              </U>
					</A>
			    </xsl:if>	
				 <!-- ������ ��������  , ������ -->
				<xsl:if test="/document/admin/post_manager != ''">	
				<table><tr><td width="60px" style="padding-bottom: 5px;PADDING-TOP: 5px">																
					        <form name="product_del"  action="Productlist.jsp" method="POST">
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
								<INPUT class="button" TYPE="submit" name="submit" value="�������"></INPUT>
					        </form>
					        </td>
					        <td width="5px">
					        </td>
					        <td style="padding-bottom: 5px;PADDING-TOP: 5px">
					        <form name="product_edit"  action="Productlist.jsp" method="POST">
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="co2" ></INPUT>
								<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
								<INPUT  class="button" TYPE="submit" name="submit" value="��������" style="margin-left: 0px"></INPUT>
					        </form>
				</td></tr></table>	
				</xsl:if>
			   <!-- ������ ��������  , �����  -->														
		        </LI>
		        </xsl:for-each>      
                <LI class="mostread_s3" >
                	
		        	<a title="��� �������" href="Productlist.jsp?catalog_id=-1">
		        	<IMG style="margin-right: 5px;margin-bottom:2px;">
                       		<xsl:attribute name="src">
                       		<xsl:value-of select="concat('xsl/',$host,'/images/list-item-bg.gif')"/>
                       		</xsl:attribute>
					</IMG>
		        	<u>����� ��������</u>
		        	</a>
		        </LI>    
                              
            </UL></TD></TR></TBODY></TABLE></DIV></DIV></DIV></DIV></DIV></DIV>
						
							
							
			
</div>
			
<div id="container">
<div class="comp-cont">
		<table class="blog" cellpadding="0" cellspacing="0">
			<tr>
			<td valign="top">
				<div class="article-bg">
					<div class="article-left">
						<div class="article-right">
							<div class="width">
							
							     <TABLE height="20" cellSpacing="0" cellPadding="0"  >
					              <TBODY>
							              <TR>
								            <TD align="center">									
											    <!-- �������� ������ ��� ���������� ������ , ������  -->				
											    <xsl:if test="document/role_id = '2'">											    
														<div style="font-size: 12px">
														������� ��� ������, ���� ��  ������ �������� ��� �������� ���������� �����<br/>
															<div class="change">													
															<A ><xsl:attribute name="HREF"><xsl:value-of select="document/admin/post_manager"/>
															</xsl:attribute><DIV >����</DIV></A>
															</div>
													    </div>										    
											    </xsl:if>								   
										    </TD>
										  </TR>
						              <TR>
						              <TD vAlign="center"  align="center" class="pagesLink">					              
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
								
										<form id="formToPage" name="searchform2"  action="Productlist.jsp" method="post"><input name="offset" type="hidden" value="0" ></input></form>
								
	
	
						               </TD>
						              </TR>					              
					  	     </TBODY>
					  	   </TABLE>
							
							
		
			            <DIV  id="mainContent" class="mainContent" style="width: 494px">
			            
			            <TABLE class="blog" cellSpacing="0" cellPadding="0"  style="width: 494px">
			              <TBODY>
			              <TR>
			                <TD vAlign="top">                
					                  <TABLE cellSpacing="0" cellPadding="0" width="100%">
						                    <TBODY>
								                    <xsl:if test="document/empty_page = 'true'" >
														<TR>
												          <TD vAlign="top"> 
															�� ���� �������� ������ ���.
														  </TD>
														</TR>
										   	        </xsl:if>
								   	        
											   	        <xsl:if test="document/empty_page != 'true'" >
									                    <TR>
									                      <TD class="article_column" vAlign="top" width="100%"> 
										                        
											                           <DIV class="products">
													                        <xsl:if test="document/empty_page != 'true'" >
																					<table width="494px">
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
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>

		<tr > 
		 <td >
		  <DIV class="module_forum" style="margin-right:0px;">
		  <DIV class="first_forum">
		  <DIV class="sec_forum">
		  <H3>����� - ���������� <xsl:value-of select="document/product/name"/></H3>
		  <DIV>
		  <DIV class="forum" style="width:512px" >
		  <DIV>
		  <DIV>
		  
		 	<TABLE>
				<TBODY>
					<TR>
						<TD class="over" id="forum">
						<UL>
						<xsl:for-each select="document/product_blog_list/product_blog">
						<LI  width="100%" style="padding-bottom:20px">
						
						<table width="100%">
							<tr>
								<td colspan="2" style="padding-bottom:5px">
								<A class="menu"><xsl:attribute name="HREF"><xsl:value-of select="policy_url"/></xsl:attribute><b><xsl:value-of select="parent_title"/></b></A> 												        	
								</td>			        	
							</tr>
							<tr>
								<td>			        		
								<IMG border="0" height="20" width="20" style="margin-right: 5px">
								<xsl:attribute name="src">
								<xsl:value-of select="concat('xsl/',$host,'/images/user1.png')"/>
								</xsl:attribute>
								</IMG> 
								<em><xsl:value-of select="author"/></em>											        	
								</td>
								
								<td style="text-align: right; color: #BB1F1F; padding-right: 20px; vertical-align: bottom; font-size: 10px;">
								���������: <xsl:value-of select="cdate"/>
								</td>
							</tr>
							<tr>
								<td colspan="2" style="FONT: 11px Arial; COLOR: #474646">			        	
								<b><xsl:value-of select="name"/></b>
								<br/>						    
								<xsl:for-each select="description/r">
								<xsl:value-of select="."/> 
								</xsl:for-each>					    
								</td>													        
							</tr>													        
						</table>
															     												
															
													
										   <!-- ������ �������� , ������ ��� ������  -->
										<xsl:choose>
										<xsl:when test="/document/role_id = 2">
											<table style="width:200px; padding-left: 20px">
											    <tbody>
											        <tr>
												        <td style="padding-left: 30px; padding-right: 16px;padding-top: 10px;">														         
													                <form name="product_del"  action="Productlist.jsp" method="POST">
															<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
															<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
															<INPUT class="button" TYPE="submit" name="submit" value="�������"></INPUT>
													                </form>
												         </td>
											             <td style="padding-top: 10px;">
														    <form name="product_edit"  action="Productlist.jsp" method="POST">
																<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
																<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="blog" ></INPUT>
																<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
																<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_parent_id"><xsl:attribute name="value"><xsl:value-of select="product_parent_id"/></xsl:attribute></INPUT>
																<INPUT class="button" TYPE="submit" name="submit" value="��������"></INPUT>
														    </form>
												         </td>
											        </tr>
											    </tbody>
											</table>
										</xsl:when>
									   <!-- ������ ��������  , �����  ��� ������  -->
							
									   <!-- ������ �������� , ������ ��� ������  -->
							
							         <xsl:otherwise>
										<xsl:if  test="user_id = string(number($user_id))">
										<xsl:if test="/document/role_id = '1'">
										<table style="width:200px; padding-left: 20px">
									   		<tbody>
									        	<tr>
									        	<td style="padding-left: 30px; padding-right: 16px;padding-top: 10px;">
									            <form name="product_del"  action="Productlist.jsp" method="POST">
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
													<INPUT class="button" TYPE="submit" name="submit" value="�������"></INPUT>
											    </form>
									        	</td>
										         <td style="padding-top: 10px;">
										         <form name="product_edit"  action="Productlist.jsp" method="POST">
														<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
														<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="blog" ></INPUT>
														<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="product_id"/></xsl:attribute></INPUT>
														<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_parent_id"><xsl:attribute name="value"><xsl:value-of select="product_parent_id"/></xsl:attribute></INPUT>
														<INPUT class="button" TYPE="submit" name="submit" value="��������"></INPUT>
												</form>
										        </td>
									        </tr>
									        </tbody>
									     </table>
									     </xsl:if>
									     </xsl:if>
									     </xsl:otherwise>
									     </xsl:choose>
									     <SPAN class="article_separator"></SPAN> 
							</LI>
							</xsl:for-each>
							</UL>
							</TD>
							</TR>
							</TBODY>
							</TABLE>
										            </DIV>
										            </DIV>
										            </DIV>   
	     	          								</DIV>
			                                        </DIV>
		                                            </DIV>
		                                            </DIV>
		
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
		Internet shop  . Copyright 2010 <A HREF="http://www.siteoneclick.com"> Center Business Solutions ltd </A> . ��� ����� ��������.  
		</div>
		</DIV>
	</DIV>

</body>






</HTML>
</xsl:template>

<xsl:template name="prList">
			<xsl:param name="position" select="1"/>
			<xsl:param name="prCount"/>	
			<tr>
			<td>
			
			<table  border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td width="235px">
					<div class="contentheading"><a ><xsl:attribute name="HREF"><xsl:value-of select="document/product_list/product[$position]/policy_url"/></xsl:attribute> <xsl:value-of select="document/product_list/product[$position]/name"/></a></div>
					<xsl:if test="document/product_list/product[$position]/amount != 0">
					<div style="background-color: #CFCFC8; text-align: right; margin-bottom: 3px"><font class="price" ><xsl:value-of select="document/product_list/product[$position]/amount"/>  </font> </div>
					</xsl:if>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  		<tr>
							<td>
								<xsl:if test="document/product_list/product[$position]/icon != ''"> 						                			
											        	<A>
														<xsl:attribute name="HREF"><xsl:value-of select="document/product_list/product[$position]/policy_url"/></xsl:attribute>
														<img  height="80" border="0" style="float: left; margin: 5 15px 5px 5;" >
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
								<a > <xsl:attribute name="HREF"><xsl:value-of select="document/product_list/product[$position]/policy_url"/></xsl:attribute>
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
			    <td width="245px">
			   		<xsl:if test="($position+1) &lt;= ($prCount)">
					<div class="contentheading"><a><xsl:attribute name="HREF"><xsl:value-of select="document/product_list/product[$position+1]/policy_url"/></xsl:attribute> <xsl:value-of select="document/product_list/product[$position+1]/name"/></a></div>
					<xsl:if test="document/product_list/product[$position+1]/amount != 0">
					<div style="background-color: #CFCFC8; text-align: right; margin-bottom: 3px"><font class="price" ><xsl:value-of select="document/product_list/product[$position+1]/amount"/>  </font> </div>
					</xsl:if>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  		<tr>
							<td>
							<xsl:if test="document/product_list/product[$position+1]/icon != ''"> 						                			
								<A>
									<xsl:attribute name="HREF"><xsl:value-of select="document/product_list/product[$position+1]/policy_url"/></xsl:attribute>
														<img height="80"  border="0" style="float: left; margin: 5 15px 5px 5;"  >
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
								<a > <xsl:attribute name="HREF"><xsl:value-of select="document/product_list/product[$position+1]/policy_url"/></xsl:attribute>
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
			  	<td width="235px">
					<div>
					<xsl:choose> 

									   <!-- ������ ��������  , ������ -->
									       <xsl:when test="/document/role_id = '2'">									
									       
										
											<table style="height: 30px;">
											    <tbody>
											        <tr>
											         <td style="padding-top: 7px">
											                <form name="product_edit"  action="Productlist.jsp" method="POST">
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="product" ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="document/product_list/product[$position]/product_id"/></xsl:attribute></INPUT>
													<INPUT class="button" TYPE="submit" name="submit" value="��������"></INPUT>
											                </form>
											         </td>
												 <td style="padding-left: 10px; padding-top: 7px">  
											                <form name="product_del"  action="Productlist.jsp" method="POST">
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="document/product_list/product[$position]/product_id"/></xsl:attribute></INPUT>
													<INPUT class="button" TYPE="submit" name="submit" value="�������"></INPUT>
											                </form>
											         </td>
											        </tr>
											    </tbody>
											</table>								            
									      									
									
									       
								    </xsl:when> 
								    <!-- ������ ��������  , �����  -->
								
								
								
								
								  <!--  for Edit  User context  ������ ��������  , ������ -->
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
												<INPUT  class="button" TYPE="submit" name="submit" value="��������"></INPUT>
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
								
								    <!-- ������ ��������  , �����  -->	
					
					</div>
					<xsl:if test="($position) &lt; (($prCount)-1)">
						<div class="line2"></div>
					</xsl:if>
			  	</td>
			  	<td width="10px"></td>
			  	<td width="245px">
			  		<xsl:if test="($position+1) &lt;= ($prCount)">
					<div>
					<xsl:choose> 

									   <!-- ������ ��������  , ������ -->
									       <xsl:when test="/document/role_id = '2'">								
									      
										
											<table style="height: 30px;">
											    <tbody>
											        <tr>
											         <td style="padding-top: 7px">
											                <form name="product_edit"  action="Productlist.jsp" method="POST">
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="edit"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="element" VALUE="product" ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="document/product_list/product[$position+1]/product_id"/></xsl:attribute></INPUT>
													<INPUT class="button" TYPE="submit" name="submit" value="��������"></INPUT>
											                </form>
											         </td>
												 <td style="padding-left: 10px; padding-top: 7px">  
											                <form name="product_del"  action="Productlist.jsp" method="POST">
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="action" VALUE="del"  ></INPUT>
													<INPUT SIZE="0"  AUTOCOMPLETE="off" TYPE="HIDDEN" NAME="product_id"><xsl:attribute name="value"><xsl:value-of select="document/product_list/product[$position+1]/product_id"/></xsl:attribute></INPUT>
													<INPUT class="button" TYPE="submit" name="submit" value="�������"></INPUT>
											                </form>
											         </td>
											        </tr>
											    </tbody>
											</table>									            
									       	
								    </xsl:when> 
								    <!-- ������ ��������  , �����  -->
								
								
								
								
								  <!--  for Edit  User context  ������ ��������  , ������ -->
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
												<INPUT  class="button" TYPE="submit" name="submit" value="��������"></INPUT>
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
								
								    <!-- ������ ��������  , �����  -->	
					
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
        


</xsl:stylesheet><!-- Stylus Studio meta-information - (c) 2004-2006. Progress Software Corporation. All rights reserved.
<metaInformation>
<scenarios/><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/><MapperBlockPosition></MapperBlockPosition><TemplateContext></TemplateContext><MapperFilter side="source"></MapperFilter></MapperMetaTag>
</metaInformation>
-->