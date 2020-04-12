<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="SoftPostBeanId" scope="session" class="com.cbsinc.cms.SoftPostBean" />
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<%
  response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
  response.setHeader("Pragma","no-cache"); //HTTP 1.0
  response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
  request.setCharacterEncoding("UTF-8");
%>


if( request.getParameter("creteria1_id") !=null ) SoftPostBeanId.setCreteria1_id( request.getParameter("creteria1_id"));
if( request.getParameter("creteria2_id") !=null ) SoftPostBeanId.setCreteria2_id( request.getParameter("creteria2_id"));
if( request.getParameter("creteria3_id") !=null ) SoftPostBeanId.setCreteria3_id( request.getParameter("creteria3_id"));
if( request.getParameter("creteria4_id") !=null ) SoftPostBeanId.setCreteria4_id( request.getParameter("creteria4_id"));
if( request.getParameter("creteria5_id") !=null ) SoftPostBeanId.setCreteria5_id( request.getParameter("creteria5_id"));
if( request.getParameter("creteria6_id") !=null ) SoftPostBeanId.setCreteria6_id( request.getParameter("creteria6_id"));
if( request.getParameter("creteria7_id") !=null ) SoftPostBeanId.setCreteria7_id( request.getParameter("creteria7_id"));
if( request.getParameter("creteria8_id") !=null ) SoftPostBeanId.setCreteria8_id( request.getParameter("creteria8_id"));
if( request.getParameter("creteria9_id") !=null ) SoftPostBeanId.setCreteria9_id( request.getParameter("creteria9_id"));
if( request.getParameter("creteria10_id") !=null ) SoftPostBeanId.setCreteria10_id( request.getParameter("creteria10_id"));


String   softname  = request.getParameter("softname");
if ( softname != null ) {  SoftPostBeanId.setStrSoftName( softname ); }


String catalog_id  = request.getParameter("catalog_id") ;
if(catalog_id  != null){ AuthorizationPageBeanId.setCatalog_id( catalog_id) ; }


String softcost  = request.getParameter("softcost") ;
if(softcost  != null){ SoftPostBeanId.setStrSoftCost( softcost) ; }

String currency_id  = request.getParameter("currency_id") ;
if(currency_id  != null){ SoftPostBeanId.setStrCurrency( currency_id) ; }

String   description  = request.getParameter("description");
if ( description != null ) {  SoftPostBeanId.setStrSoftDescription( description ); }


String   fulldescription  = request.getParameter("fulldescription");
if ( fulldescription != null ) {  SoftPostBeanId.setProduct_fulldescription( fulldescription ); }


String   imagename  = request.getParameter("imagename");
if ( imagename != null ) {  SoftPostBeanId.setImgname( imagename ); }


String   image_id  = request.getParameter("image_id");
if ( image_id != null ) {  SoftPostBeanId.setImage_id( image_id ); }

if ( request.getParameter("portlettype_id") != null ) {  SoftPostBeanId.setPortlettype_id( request.getParameter("portlettype_id")); }

String   filename  = request.getParameter("filename");
if ( filename != null ) {  SoftPostBeanId.setSample( filename ); }
else{ SoftPostBeanId.setSample(""); }
filename = null ;



String   bigimagename  = request.getParameter("bigimagename");
if ( bigimagename != null ) {  SoftPostBeanId.setBigimgname( bigimagename ); }


String   bigimage_id  = request.getParameter("bigimage_id");
if ( bigimage_id != null ) {  SoftPostBeanId.setBigimage_id( bigimage_id ); }


if( request.getParameter("salelogic_id") !=null ) SoftPostBeanId.setProgname_id( request.getParameter("salelogic_id"));

if( AuthorizationPageBeanId.getIntUserID() == 0 ){
AuthorizationPageBeanId.setStrMessage("User not login or loguot by timeout , make login in site now") ;
response.sendRedirect("Authorization.jsp" );
}
else SoftPostBeanId.setUser_id("" + AuthorizationPageBeanId.getIntUserID()) ;

if( AuthorizationPageBeanId.getIntLevelUp() != 2 ){
AuthorizationPageBeanId.setStrMessage("You don't have access to add position , send mail to grabko@mail.ru for access") ;
response.sendRedirect("Authorization.jsp" );
}

if(request.getMethod().toUpperCase().compareTo("POST") == 0)
{
/////SoftPostBeanId.setSite_id(AuthorizationPageBeanId.getSite_id());
/////if(SoftPostBeanId.getSoft_id().compareTo("-1")==0) SoftPostBeanId.saveDescSoft();
/////else SoftPostBeanId.updateDescSoft();
/////String strSoft_id = SoftPostBeanId.getCountActiveRow() ;
/////if(strSoft_id.length() > 1) strSoft_id = strSoft_id.substring(0,strSoft_id.length() - 1) + "0" ;
/////else strSoft_id = "0" ;
response.sendRedirect("Productlist.jsp?offset=" + 0 + "&catalog_id=" + AuthorizationPageBeanId.getCatalog_id()  );
}
%>


<html>
<head>
     <title>GBS Portal</title>
     <style type="text/css" media="screen"> @import url(style2.css);</style>

<script language="JavaScript">
<!--
function select_file(){
self.name  = 'UploadForm' ;
var url = 'SelectFile.jsp' ;
window.open(url ,'New','width=400,height=210,scrollbars=yes,screenX=100,screenY=100');
return false ;
//return true ;
}

function new_file(){
self.name  = 'UploadForm' ;
var url = 'File1.html' ;
window.open(url ,'New','width=400,height=210,scrollbars=yes,screenX=100,screenY=100');
return false ;
//return true ;
}

function select_Image(){
self.name  = 'UploadForm' ;
var url = 'SelectImage.jsp' ;
window.open(url ,'New','width=400,height=210,scrollbars=yes,screenX=100,screenY=100');
return false ;
}

function select_BigImage(){
self.name  = 'UploadForm' ;
var url = 'SelectBigImage.jsp' ;
window.open(url ,'New','width=400,height=210,scrollbars=yes,screenX=100,screenY=100');
return false ;
}


function new_Image(){
self.name  = 'UploadForm' ;
var url = 'Image.html' ;
window.open(url ,'New','width=400,height=210,scrollbars=yes,screenX=100,screenY=100');
return false ;
}


function new_BigImage(){
self.name  = 'UploadForm' ;
var url = 'big_images.html' ;
window.open(url ,'New','width=400,height=210,scrollbars=yes,screenX=100,screenY=100');
return false ;
}


function isNumber(tmp){
var IntField = '0123456789.' ;
for(var i=0 ; i< tmp.length ; i++ ){
if( IntField.indexOf(tmp.charAt(i)) == -1 ) return true ;
}
return false ;
}

function isEmpty(tmp){
if( tmp.length == 0 ) return true ;
return false ;
}

function isDate(tmp){
var IntField = '0123456789' ;
//1957-06-13
if( IntField.indexOf(tmp.charAt(0)) != -1 )
if( IntField.indexOf(tmp.charAt(1)) != -1 )
if( IntField.indexOf(tmp.charAt(2)) != -1 )
if( IntField.indexOf(tmp.charAt(3)) != -1 )
if( IntField.indexOf(tmp.charAt(5)) != -1 )
if( IntField.indexOf(tmp.charAt(6)) != -1 )
if( IntField.indexOf(tmp.charAt(8)) != -1 )
if( IntField.indexOf(tmp.charAt(9)) != -1 )
if( tmp.indexOf('-') == 4 )
if( tmp.indexOf('-',5) == 7 ) return false ;
return true ;
}

function isURL(tmp){
if( tmp.indexOf('http://') != -1 ) return false ;
if( tmp.indexOf('ftp://') != -1 ) return false ;
if( tmp.indexOf('mailto:') != -1 ) return false ;
return true ;
}


function IsFormOk(){
if(isEmpty( document.postsoftform.softname.value)){ alert('Field name did not fill'); return false; }
//if(isNumber(document.postsoftform.image_id.value) ){ alert('This is not number'); return false; }
if(isEmpty( document.postsoftform.imagename.value)){ alert('Field image did not fill'); return false; }
if(isEmpty( document.postsoftform.description.value)){ alert('Field description did not fill'); return false; }
return true ;
}

function checkNumber(tmp){
if(isNumber(tmp) ){ alert('This is not number'); return false; }
return true ;
}

function checkEmpty(tmp){
if(isEmpty(tmp) ){ alert('Filed is empty'); return false; }
return true ;
}

//-->
</script>


</head>
<body>

<a class="skipnav" href="#documentContent">Skip to content</a>

<div>



        <div class="top">

        </div>

        <hr size="" class="netscape4" />


        <div class="pathBar">
            <span>
                <span> <%=AuthorizationPageBeanId.getLocalization(application).getString("control_of_site")%> </span>
            </span>

        </div>

        <hr size="" class="netscape4" />

    </div>



<table class="columns">

    <tbody>
        <tr>
            <td class="left">
            </td>

            <td class="main">

            <!-- News part -->

	    <h1><%=AuthorizationPageBeanId.getLocalization(application).getString("title_common_of_form")%></h1>
<br/>


		<div class="box">
		  <div class="body">
		    <div >


                     <form method="post" name="postsoftform"  ACTION="ProductPost.jsp" onSubmit="return  IsFormOk()"  >
                     <TABLE>
                     <TR><TD><%=AuthorizationPageBeanId.getLocalization(application).getString("title_form")%>:* </TD><TD> <input  name="softname" size="20"  value="<%= SoftPostBeanId.getStrSoftName() %>" onBlur="checkEmpty(this.value)" >
                     <%=AuthorizationPageBeanId.getLocalization(application).getString("selected_section")%>:*<%=SoftPostBeanId.getComboBoxAutoSubmit("catalog_id", AuthorizationPageBeanId.getCatalog_id() ,"select catalog_id , lable   from catalog   where  active = true and site_id = " + AuthorizationPageBeanId.getSite_id() + " and parent_id = " + AuthorizationPageBeanId.getCatalogParent_id() )%>
                     <TR><TD><%=AuthorizationPageBeanId.getLocalization(application).getString("title_form_on_foreign_lang")%>:* </TD> <TD> <input  name="softname2" size="40"  value="<%= SoftPostBeanId.getStrSoftName2() %>"  >  </TD></TR>
                     <TR><TD><%=AuthorizationPageBeanId.getLocalization(application).getString("upload_small_image")%>:* </TD> <TD><input  name="imagename"  disabled="disabled" size="20" value="<%= SoftPostBeanId.getImgname() %>" ><input type="button" name="newimage" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("new_small_image") %>"   onclick="dwindow('NewSmallImage.jsp'); return false;"  ><input type="button" name="selectimage" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("select_small_image") %>" onclick="dwindow('SelectImage.jsp'); return false;" ><input type="hidden"  name="image_id" size="20" value="<%= SoftPostBeanId.getImage_id() %>" ></TD></TR>
                     <TR><TD><%=AuthorizationPageBeanId.getLocalization(application).getString("cost")%>:* </TD> <TD><input  name="softcost" size="20" value="<%= SoftPostBeanId.getStrSoftCost() %>" onBlur="checkNumber(this.value)"  ><%=SoftPostBeanId.getComboBox("currency_id", "3" ,"SELECT currency_id , currency_lable FROM currency  WHERE active = true")%></TD></TR>
                     <TR><TD><%=AuthorizationPageBeanId.getLocalization(application).getString("short_info")%> :* </TD> <TD> <textarea name="description" rows="10" cols="70"  ><%=SoftPostBeanId.getStrSoftDescription()%></textarea></TD></TR>
                     <TR><TD><%=AuthorizationPageBeanId.getLocalization(application).getString("upload_big_image")%>:* </TD> <TD><input  name="bigimagename" disabled="disabled" size="20" value="<%= SoftPostBeanId.getBigimgname() %>" ><input type="button" name="newbig_image" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("new_big_image") %>"  onclick="dwindow('NewBigImage.jsp'); return false;" ><input type="button" name="selectbig_image" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("select_big_image") %>" image" onclick="dwindow('SelectBigImage.jsp'); return false;" ><input type="hidden"  name="bigimage_id" size="20" value="<%= SoftPostBeanId.getBigimage_id() %>" ></TD></TR>
                     <TR><TD><%=AuthorizationPageBeanId.getLocalization(application).getString("full_information")%> :* </TD> <TD> <textarea name="fulldescription" rows="10" cols="70"  ><%=SoftPostBeanId.getProduct_fulldescription()%></textarea></TD></TR>
                     <TR><TD><%=AuthorizationPageBeanId.getLocalization(application).getString("attach_file")%>:* </TD> <TD><input  name="filename" disabled="disabled"  size="20" value="<%= SoftPostBeanId.getFilename() %>" ><input type="button" name="newfile" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("new_file") %>" onclick="dwindow('NewFile.jsp')" ><input type="button" name="selectfile" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("select_file") %>" onclick="dwindow('SelectFile.jsp')" ><input type="hidden"  name="file_id" size="20" value="<%= SoftPostBeanId.getFile_id() %>" ></TD></TR>
                     <TR><TD></TD> <TD><input type="submit" name="Submit" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("save") %>"> <input type="reset" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("clear") %>"></TD></TR>
                     </TABLE>
                     <input type="hidden"  name="portlettype_id"  value="0"/>
                     </form>

		     </div>
		  </div>
		</div>


        <!-- Navigation -->
        <div class="listingBar">
	    <span class="next">
                <a HREF = "#" onClick="javascript:history.back()"  >
		<strong>
		<%=AuthorizationPageBeanId.getLocalization(application).getString("back")%>
		</strong>
	        </a>
	    </span>
	</div>


            </td>

            <td class="right">




            </td>
        </tr>
    </tbody>
</table>


<hr size="" class="netscape4" />

<div class="footer">


<br />

 <%=AuthorizationPageBeanId.getLocalization(application).getString("all_rights_reserved")%>

<hr size="" class="netscape4" />

<strong class="netscape4">
for user netscape
</strong>

</div>

</body>
</html>