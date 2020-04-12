<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<html>
<head>
     <title>GBS Portal Forum</title>
     <style type="text/css" media="screen"> @import url(style2.css);</style>
	 <style type="text/css">
	 .drag{
      position:relative;
      cursor:hand
      top: 120px;
      left: 130px;
	 }
	 #vunet{
      background-color:#F4F4F4;
      cursor:move;
      width:400px;
      filter:alpha(opacity=85);
      -moz-opacity:.85;
      opacity:.85;
	 }
	 </style>
	 
     
<script language="JavaScript">
<!--


// start select row in table
var select_id ;

function selected( color , id ) 
{
if(document.getElementById(select_id) != null ) document.getElementById(select_id).style.background = 'white' ;
select_id = id ; 
document.getElementById(id).style.background = color ;

var combobox = document.getElementById("catalog_id") ;

 for (loop = 0; loop < combobox.options.length; loop++) 
 {
 if(combobox.options[loop].value == select_id) 
  {
  combobox.options[loop].style.background = '#FFEFFF' ;
  combobox.options[loop].selected = true;
  }
  else combobox.options[loop].style.background = 'white' ;
 }

}


function setColor( color , id ) 
{
if( select_id != id ) document.getElementById(id).style.background = color ;
else  document.getElementById(id).style.background = '#FFEFFF' ;
}
// end select row in table

var windowsearch_hide = true ;

function switchWindoSearch()
{
				windowsearch_hide = !windowsearch_hide;
				document.getElementById("search_cre").src =  (windowsearch_hide ? "images/expand.gif" : "images/collapse.gif");
				document.getElementById("search_cre").title =  (windowsearch_hide ? "Развернуть" : "Свернуть");
				document.getElementById("windowsearch").style.display = windowsearch_hide ? "none" : "";
}



// start dialog widows
var current_header_ic = true;	

	function dwindow(url)
	{
	    document.onmousemove = null ;
	    current_header_ic = !current_header_ic;
	    document.getElementById("dialog").src = url ;
	    document.getElementById("vunet").style.display = current_header_ic ? "none" : "";

	}

function setTitle(message) 
{
    var mdiv = document.getElementById("dtitle");
    mdiv.innerHTML =  message ;
}

var ie = document.all;
var ns6 = document.getElementById && !document.all;
var dragapproved=false;
var z, x, y;
function move(e) {
  if (dragapproved) {
    z.style.left=ns6? temp1+e.clientX-x: temp1+event.clientX-x;
    z.style.top=ns6? temp2+e.clientY-y : temp2+event.clientY-y;
    return false;
  }
}
function drags(e) {
  if (!ie&&!ns6)
  return;
  var firedobj = ns6? e.target : event.srcElement;
  var topelement = ns6? "HTML" : "BODY";
  while (firedobj.tagName != topelement&&firedobj.className != "drag") {
    firedobj = ns6? firedobj.parentNode : firedobj.parentElement;
  }
  if (firedobj.className == "drag") {
    dragapproved = true;
    z = firedobj;
    temp1 = parseInt(z.style.left+0);
    temp2 = parseInt(z.style.top+0);
    x = ns6? e.clientX: event.clientX;
    y = ns6? e.clientY: event.clientY;
    document.onmousemove=move;
    return false;
  }
}
document.onmousedown=drags;
document.onmouseup=new Function("dragapproved=false");

// end dialog widows

function do_save() 
{ 
document.postsoftform.save.value = 'true' ;
document.postsoftform.submit();
return true ;
}

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
window.open(url ,'New','width=400,height=410,scrollbars=yes,screenX=100,screenY=100');
return false ;
}

function select_BigImage(){
self.name  = 'UploadForm' ;
var url = 'SelectBigImage.jsp' ;
window.open(url ,'New','width=400,height=410,scrollbars=yes,screenX=100,screenY=100');
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
//if(isEmpty( document.postsoftform.imagename.value)){ alert('Field image did not fill'); return false; }
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

//  Ajax - chenge criteria
function initRequest() 
{
       if (window.XMLHttpRequest) 
       {
           return new XMLHttpRequest();
       }
       else if (window.ActiveXObject) 
       {
           return new ActiveXObject("Microsoft.XMLHTTP");
       }
}


function doChangeCreteria( creteria_id , creteria_value ) 
{

           
           var url = 'postcreteria?' + creteria_id  + '=' + creteria_value  ; 

           var req = initRequest();
           req.onreadystatechange = function() 
	   {
               if (req.readyState == 4) 
	       {
                   if (req.status == 200) 
		   {

		     parseMessages(req.responseXML);
                   }
               }
           };

           req.open("GET", url, true);
           req.send(null);


}



function parseMessages(responseXML) 
{
       var doc = responseXML.getElementsByTagName("document")[0];
	// createri 1
       var creteria1 = doc.getElementsByTagName("creteria1")[0];
       if(document.all["creteria1_id"] != null  && creteria1 != null )
       {

         var combobox = document.all["creteria1_id"].ownerDocument; 
         if (!combobox) combobox = document.all["creteria1_id"].document;
         document.all["creteria1_id"].options.length = 0 ;


         for (loop = 0; loop < creteria1.childNodes.length; loop++) 
		 {
           var creteria1_item = creteria1.childNodes[loop];
           var selected = creteria1_item.getElementsByTagName("selected")[0];
           var code = creteria1_item.getElementsByTagName("code")[0];
           var item  = creteria1_item.getElementsByTagName("item")[0];
		   var opt = combobox.createElement('OPTION');
	 	   opt.value = code.childNodes[0].nodeValue;
	 	   opt.text = item.childNodes[0].nodeValue;
		   if(code.childNodes[0].nodeValue == selected.childNodes[0].nodeValue ) opt.selected = true;
		   document.all["creteria1_id"].options.add(opt, loop);
         }
	}	


	// createri 2
       var creteria2 = doc.getElementsByTagName("creteria2")[0];
       if(document.all["creteria2_id"] != null && creteria2 != null )
       {

          var combobox2 = document.all["creteria2_id"].ownerDocument; 
          if (!combobox2) combobox2 = document.all["creteria2_id"].document;
          document.all["creteria2_id"].options.length = 0 ;

          for (loop = 0; loop < creteria2.childNodes.length; loop++) 
		  {
           var creteria2_item = creteria2.childNodes[loop];
           var selected = creteria2_item.getElementsByTagName("selected")[0];
           var code = creteria2_item.getElementsByTagName("code")[0];
           var item  = creteria2_item.getElementsByTagName("item")[0];

		   var opt = combobox2.createElement('OPTION');
	 	   opt.value = code.childNodes[0].nodeValue;
	 	   opt.text = item.childNodes[0].nodeValue;
		   if(code.childNodes[0].nodeValue == selected.childNodes[0].nodeValue )  opt.selected = true;
		   document.all["creteria2_id"].options.add(opt, loop);
          }
	}	



	// createri 3
       var creteria3 = doc.getElementsByTagName("creteria3")[0];
       if(document.all["creteria3_id"] != null && creteria3 != null )
       {

          var combobox2 = document.all["creteria3_id"].ownerDocument; 
          if (!combobox2) combobox2 = document.all["creteria3_id"].document;
          document.all["creteria3_id"].options.length = 0 ;


          for (loop = 0; loop < creteria3.childNodes.length; loop++) 
		  {
           var creteria3_item = creteria3.childNodes[loop];
           var selected = creteria3_item.getElementsByTagName("selected")[0];
           var code = creteria3_item.getElementsByTagName("code")[0];
           var item  = creteria3_item.getElementsByTagName("item")[0];
		   var opt = combobox2.createElement('OPTION');
	 	   opt.value = code.childNodes[0].nodeValue;
	 	   opt.text = item.childNodes[0].nodeValue;
		   if(code.childNodes[0].nodeValue == selected.childNodes[0].nodeValue )  opt.selected = true;
		   document.all["creteria3_id"].options.add(opt, loop);
          }
	}	



	// createri 4
       var creteria4 = doc.getElementsByTagName("creteria4")[0];
       if(document.all["creteria4_id"] != null && creteria4 != null )
       {

          var combobox2 = document.all["creteria4_id"].ownerDocument; 
          if (!combobox2) combobox2 = document.all["creteria4_id"].document;
          document.all["creteria4_id"].options.length = 0 ;


          for (loop = 0; loop < creteria4.childNodes.length; loop++) 
		  {
           var creteria4_item = creteria4.childNodes[loop];
           var selected = creteria4_item.getElementsByTagName("selected")[0];
           var code = creteria4_item.getElementsByTagName("code")[0];
           var item  = creteria4_item.getElementsByTagName("item")[0];
		   var opt = combobox2.createElement('OPTION');
	 	   opt.value = code.childNodes[0].nodeValue;
	 	   opt.text = item.childNodes[0].nodeValue;
		   if(code.childNodes[0].nodeValue == selected.childNodes[0].nodeValue )  opt.selected = true;
		   document.all["creteria4_id"].options.add(opt, loop);
          }
	}	



	// createri 5
       var creteria5 = doc.getElementsByTagName("creteria5")[0];
       if(document.all["creteria5_id"] != null && creteria5 != null )
       {

          var combobox2 = document.all["creteria5_id"].ownerDocument; 
          if (!combobox2) combobox2 = document.all["creteria5_id"].document;
          document.all["creteria5_id"].options.length = 0 ;


          for (loop = 0; loop < creteria5.childNodes.length; loop++) 
		  {
           var creteria5_item = creteria5.childNodes[loop];
           var selected = creteria5_item.getElementsByTagName("selected")[0];
           var code = creteria5_item.getElementsByTagName("code")[0];
           var item  = creteria5_item.getElementsByTagName("item")[0];
		   var opt = combobox2.createElement('OPTION');
	 	   opt.value = code.childNodes[0].nodeValue;
	 	   opt.text = item.childNodes[0].nodeValue;
		   if(code.childNodes[0].nodeValue == selected.childNodes[0].nodeValue )  opt.selected = true;
		   document.all["creteria5_id"].options.add(opt, loop);

          }
	}	



	// createri 6
       var creteria6 = doc.getElementsByTagName("creteria6")[0];
       if(document.all["creteria6_id"] != null && creteria6 != null )
       {

          var combobox2 = document.all["creteria6_id"].ownerDocument; 
          if (!combobox2) combobox2 = document.all["creteria6_id"].document;
          document.all["creteria6_id"].options.length = 0 ;


          for (loop = 0; loop < creteria6.childNodes.length; loop++) 
		  {
           var creteria6_item = creteria6.childNodes[loop];
           var selected = creteria6_item.getElementsByTagName("selected")[0];
           var code = creteria6_item.getElementsByTagName("code")[0];
           var item  = creteria6_item.getElementsByTagName("item")[0];
		   var opt = combobox2.createElement('OPTION');
	 	   opt.value = code.childNodes[0].nodeValue;
	 	   opt.text = item.childNodes[0].nodeValue;
		   if(code.childNodes[0].nodeValue == selected.childNodes[0].nodeValue )  opt.selected = true;
		   document.all["creteria6_id"].options.add(opt, loop);
          }
	}	



	// createri 7
       var creteria7 = doc.getElementsByTagName("creteria7")[0];
       if(document.all["creteria7_id"] != null && creteria7 != null )
       {

          var combobox2 = document.all["creteria7_id"].ownerDocument; 
          if (!combobox2) combobox2 = document.all["creteria7_id"].document;
          document.all["creteria7_id"].options.length = 0 ;


          for (loop = 0; loop < creteria7.childNodes.length; loop++) 
		  {
           var creteria7_item = creteria7.childNodes[loop];
           var selected = creteria7_item.getElementsByTagName("selected")[0];
           var code = creteria7_item.getElementsByTagName("code")[0];
           var item  = creteria7_item.getElementsByTagName("item")[0];
		   var opt = combobox2.createElement('OPTION');
	 	   opt.value = code.childNodes[0].nodeValue;
	 	   opt.text = item.childNodes[0].nodeValue;
		   if(code.childNodes[0].nodeValue == selected.childNodes[0].nodeValue )  opt.selected = true;
		   document.all["creteria7_id"].options.add(opt, loop);
          }
	}	


	// createri 8
       var creteria8 = doc.getElementsByTagName("creteria8")[0];
       if(document.all["creteria8_id"] != null && creteria8 != null )
       {

          var combobox2 = document.all["creteria8_id"].ownerDocument; 
          if (!combobox2) combobox2 = document.all["creteria8_id"].document;
          document.all["creteria8_id"].options.length = 0 ;


          for (loop = 0; loop < creteria8.childNodes.length; loop++) 
		  {
           var creteria8_item = creteria8.childNodes[loop];
           var selected = creteria8_item.getElementsByTagName("selected")[0];
           var code = creteria8_item.getElementsByTagName("code")[0];
           var item  = creteria8_item.getElementsByTagName("item")[0];

		   var opt = combobox2.createElement('OPTION');
 		   opt.value = code.childNodes[0].nodeValue;
 	   	   opt.text = item.childNodes[0].nodeValue;
		   if(code.childNodes[0].nodeValue == selected.childNodes[0].nodeValue )  opt.selected = true;
		   document.all["creteria8_id"].options.add(opt, loop);

          }
	}	


	// createri 9
       var creteria9 = doc.getElementsByTagName("creteria9")[0];
       if(document.all["creteria9_id"] != null && creteria9 != null )
       {

          var combobox2 = document.all["creteria9_id"].ownerDocument; 
          if (!combobox2) combobox2 = document.all["creteria9_id"].document;
          document.all["creteria9_id"].options.length = 0 ;


          for (loop = 0; loop < creteria9.childNodes.length; loop++) 
	  {
           var creteria9_item = creteria9.childNodes[loop];
           var selected = creteria9_item.getElementsByTagName("selected")[0];
           var code = creteria9_item.getElementsByTagName("code")[0];
           var item  = creteria9_item.getElementsByTagName("item")[0];

		   var opt = combobox2.createElement('OPTION');
 		   opt.value = code.childNodes[0].nodeValue;
 	   	   opt.text = item.childNodes[0].nodeValue;
		   if(code.childNodes[0].nodeValue == selected.childNodes[0].nodeValue )  opt.selected = true;

		   document.all["creteria9_id"].options.add(opt, loop);

          }
	}	



	// createri 10
       var creteria10 = doc.getElementsByTagName("creteria10")[0];
       if(document.all["creteria10_id"] != null && creteria10 != null )
       {

          var combobox2 = document.all["creteria10_id"].ownerDocument; 
          if (!combobox2) combobox2 = document.all["creteria10_id"].document;
          document.all["creteria10_id"].options.length = 0 ;

          for (loop = 0; loop < creteria10.childNodes.length; loop++) 
	 	   {
           var creteria10_item = creteria10.childNodes[loop];
           var selected = creteria10_item.getElementsByTagName("selected")[0];
           var code = creteria10_item.getElementsByTagName("code")[0];
           var item  = creteria10_item.getElementsByTagName("item")[0];

	       var opt = combobox2.createElement('OPTION');
           opt.value = code.childNodes[0].nodeValue;
   	       opt.text = item.childNodes[0].nodeValue;
	       if(code.childNodes[0].nodeValue == selected.childNodes[0].nodeValue )  opt.selected = true;
		   document.all["creteria10_id"].options.add(opt, loop);

          }
	}	

}
//-->
</script>

</head>
<jsp:useBean id="SoftPostBeanId" scope="session" class="com.cbsinc.cms.SoftPostBean" />
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:useBean id="authorizationPageFaced" scope="application" class="com.cbsinc.cms.faceds.AuthorizationPageFaced" />
<jsp:useBean id="policyBeanId" scope="request" class="com.cbsinc.cms.PolicyBean" />
<%
request.setCharacterEncoding("UTF-8");
%>

<body>
<TABLE cellSpacing="0" cellPadding="0" width="100%"  border="1" rightmargin="0" leftmargin="0" topmargin="0" bordercolor="#ECEFF8" >
<TR>
<TD bgcolor="#ECEFF8" ></TD>
<TD vAlign="top" Align="center" width="1010">
<a class="skipnav" href="#documentContent">Skip to content</a>

<div>



        <div class="top">

        </div>

        <hr size="" class="netscape4" />


        <div class="pathBar">
            <span>
                <span> Управление сайтом </span>
            </span>

        </div>

        <hr size="" class="netscape4" />

    </div>



<table class="columns">

    <tbody>
        <tr>
            <td class="left">
 

<div>
    <div class="portlet">
    <h5><strong>Помощ</strong></h5>
      <div class="body">
        <div class="portletContent odd">
         Это форма для добавления вашего сообщения на форум .
        </div>
        <div class="portletContent even">
	Здесь можно добавлять свои ,
	сообщения на саит в ту область 
	в которой вы нажали кнопку добавить .
        </div>
      </div>
    </div>
</div>






            </td>

            <td class="main">

            <!-- News part -->

	    <h1>Написать сообщение на форум</h1>
<br/>


		<div class="box">
		  <div class="body">
		    <div >


   <form method="post" name="postsoftform"  ACTION="BlogExtProductPost.jsp" onSubmit="return  IsFormOk()"  >
                 
    <br/>
	<br/>
		<div class="box">
		  <div class="body">
		    <div >
		    <DIV style="background-image:url('images/f.jpg');height:20px; TEXT-ALIGN: left"  >
				<A	onclick="switchWindoSearch()" href="#" > <IMG id='search_cre'  title="Развернуть" src="images/expand.gif" align="right"/> </A>
				<font color='white' size='2' >&nbsp; Устаноска критериев для поиска этой информации на сайте </font>
			</DIV>
			<br/>
			<div id='windowsearch' style="display:none;" >
					<TABLE border="0"  width="100%" cellpadding="0" cellspacing="0"   >
                     <TR>
					 <TD width="20" >&nbsp;</TD>
                     <TD width="20" >&nbsp;1.</TD>
                     <TD width="180" ><%= SoftPostBeanId.getOneLabel("select  label   from creteria1   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true"))  ) %> :* </TD> 
                     <TD align="left" ><%=SoftPostBeanId.getComboBoxAutoSubmitLocale("creteria1_id", SoftPostBeanId.getCreteria1_id() , AuthorizationPageBeanId.getLocalization(application).getString("notselected") , "select creteria1_id , name   from creteria1   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true"))  )%></TD>
					 <TD width="20" >&nbsp;</TD>                     
                     </TR>

                     <TR>
					 <TD width="20" >&nbsp;</TD>
                     <TD width="20" >&nbsp;2.</TD>
                     <TD width="180" ><%=SoftPostBeanId.getOneLabel("select label   from creteria2   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true"))  )%> :* </TD> 
                     <TD align="left" ><%=SoftPostBeanId.getComboBoxAutoSubmitLocale("creteria2_id", SoftPostBeanId.getCreteria2_id() , AuthorizationPageBeanId.getLocalization(application).getString("notselected") ,"select creteria2_id , name   from creteria2   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria1_id()+ " ) "   )%></TD>
					 <TD width="20" >&nbsp;</TD>                     
                     </TR>
                     
                     <TR>
					 <TD width="20" >&nbsp;</TD>
                     <TD width="20" >&nbsp;3.</TD>
                     <TD width="180" ><%=SoftPostBeanId.getOneLabel("select  label   from creteria3   where  active = true "  + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true"))  )%> :* </TD> 
                     <TD align="left"  ><%=SoftPostBeanId.getComboBoxAutoSubmitLocale("creteria3_id", SoftPostBeanId.getCreteria3_id() , AuthorizationPageBeanId.getLocalization(application).getString("notselected") ,"select creteria3_id , name   from creteria3   where  active = true "  + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria2_id()+ " ) " )%></TD>
					 <TD width="20" >&nbsp;</TD>                     
                     </TR>

                     <TR>
					 <TD width="20" >&nbsp;</TD>
                     <TD width="20" >&nbsp;4.</TD>
                     <TD width="180" ><%=SoftPostBeanId.getOneLabel("select  label   from creteria4   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true"))  )%> :* </TD> 
                     <TD align="left"  ><%=SoftPostBeanId.getComboBoxAutoSubmitLocale("creteria4_id", SoftPostBeanId.getCreteria4_id() , AuthorizationPageBeanId.getLocalization(application).getString("notselected") ,"select creteria4_id , name   from creteria4   where  active = true "  + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria3_id()+ " ) " )%></TD>
					 <TD width="20" >&nbsp;</TD>                    
                     </TR>

                     <TR>
					 <TD width="20" >&nbsp;</TD>
                     <TD width="20" >&nbsp;5.</TD>
                     <TD width="180" ><%=SoftPostBeanId.getOneLabel("select  label   from creteria5   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true"))  )%> :* </TD> 
                     <TD align="left" ><%=SoftPostBeanId.getComboBoxAutoSubmitLocale("creteria5_id", SoftPostBeanId.getCreteria5_id() , AuthorizationPageBeanId.getLocalization(application).getString("notselected") ,"select creteria5_id , name   from creteria5   where  active = true "  + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria4_id()+ " ) " )%></TD>
					 <TD width="20" >&nbsp;</TD>                    
                     </TR>

                     <TR>
					 <TD width="20" >&nbsp;</TD>
                     <TD width="20" >&nbsp;6.</TD>
                     <TD width="180" ><%=SoftPostBeanId.getOneLabel("select  label   from creteria6   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true"))  )%> :* </TD> 
                     <TD align="left" ><%=SoftPostBeanId.getComboBoxAutoSubmitLocale("creteria6_id", SoftPostBeanId.getCreteria6_id() , AuthorizationPageBeanId.getLocalization(application).getString("notselected") ,"select creteria6_id , name   from creteria6   where  active = true "  + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria5_id()+ " ) " )%></TD>
   					 <TD width="20" >&nbsp;</TD>
                     </TR>


                     <TR>
					 <TD width="20" >&nbsp;</TD>
                     <TD width="20" >&nbsp;7.</TD>
                     <TD width="180" ><%=SoftPostBeanId.getOneLabel("select  label   from creteria7   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true"))  )%> :* </TD>
                     <TD align="left" ><%=SoftPostBeanId.getComboBoxAutoSubmitLocale("creteria7_id", SoftPostBeanId.getCreteria7_id() , AuthorizationPageBeanId.getLocalization(application).getString("notselected")  ,"select creteria7_id , name   from creteria7   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria6_id()+ " ) " )%></TD>
 					 <TD width="20" >&nbsp;</TD>
                   
                     </TR>

                     <TR>
					 <TD width="20" >&nbsp;</TD>
                     <TD width="20" >&nbsp;8.</TD>
                     <TD width="180" ><%=SoftPostBeanId.getOneLabel("select  label   from creteria8   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true"))  )%> :* </TD> 
                     <TD align="left" ><%=SoftPostBeanId.getComboBoxAutoSubmitLocale("creteria8_id", SoftPostBeanId.getCreteria8_id() , AuthorizationPageBeanId.getLocalization(application).getString("notselected") ,"select creteria8_id , name   from creteria8   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria7_id()+ " ) " )%></TD>
   					 <TD width="20" >&nbsp;</TD>
                     </TR>


                     <TR>
					 <TD width="20" >&nbsp;</TD>
                     <TD width="20" >&nbsp;9.</TD>
                     <TD width="180" ><%=SoftPostBeanId.getOneLabel("select  label   from creteria9   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true"))  )%> :* </TD> 
                     <TD align="left" ><%=SoftPostBeanId.getComboBoxAutoSubmitLocale("creteria9_id", SoftPostBeanId.getCreteria9_id() , AuthorizationPageBeanId.getLocalization(application).getString("notselected")  ,"select creteria9_id , name   from creteria9   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria8_id()+ " ) " )%></TD>
   					 <TD width="20" >&nbsp;</TD>
                     </TR>                                          
                     
                     <TR>
					 <TD width="20" >&nbsp;</TD>
                     <TD width="20" >&nbsp;10.</TD>
                     <TD width="180" ><%=SoftPostBeanId.getOneLabel("select  label   from creteria10   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true"))  )%> :* </TD> 
                     <TD align="left"  ><%=SoftPostBeanId.getComboBoxAutoSubmitLocale("creteria10_id", SoftPostBeanId.getCreteria10_id() , AuthorizationPageBeanId.getLocalization(application).getString("notselected") ,"select creteria10_id , name   from creteria10   where  active = true " + SoftPostBeanId.getPartCriteria(AuthorizationPageBeanId.getCatalog_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true")) + " and ( link_id = 0 or link_id = " + SoftPostBeanId.getCreteria9_id()+ " ) " )%></TD>
					 <TD width="20" >&nbsp;</TD>                     
                     </TR>                                          
                     
                     <TR>
					 <TD width="20" >&nbsp;</TD>
                     <TD width="20" >&nbsp;</TD>
                     <TD width="180" ></TD> 
                     <TD></TD>
                     <TD width="90" ></TD>
					 <TD width="20" >&nbsp;</TD>                     
                     </TR>  
					</TABLE>
			   </div>
		     </div>
		  </div>
		</div>
		<br/>
		<br/>
            	<div class="box">
	  	 	 		<div class="body">
			    	<div >
			    	<DIV style="background-image:url('images/f.jpg');height:20px; TEXT-ALIGN: left"  >
						<font color='white' size='2' > &nbsp;&raquo; Форма для заведения или изменения этой информации на сайте </font>
					</DIV>  
					 <div>          
                     <TABLE width="500" >
                     <TR><TD>Заголовок:* </TD><TD> <input  name="softname" size="40"  value="<%= SoftPostBeanId.getStrSoftName() %>" onBlur="checkEmpty(this.value)" >
                     <input type="hidden"  name="catalog_id"  value="<%= AuthorizationPageBeanId.getCatalog_id() %>"/></TR>
                     <TR><TD>Закачать картинку:* </TD> <TD><input  name="imagename"  disabled="disabled" size="20" value="<%= SoftPostBeanId.getImgname() %>" ><input type="button" name="newimage" value="New Image"   onclick="dwindow('Image.html'); return false;"  ><input type="button" name="selectimage" value="select image" onclick="dwindow('SelectImage.jsp'); return false;" ><input type="hidden"  name="image_id" size="20" value="<%= SoftPostBeanId.getImage_id() %>" ></TD></TR>
                     <TR><TD>Краткая информация :* </TD> <TD> <textarea name="description" rows="10" cols="140"  ><%= SoftPostBeanId.getStrSoftDescription() %></textarea></TD></TR>
                     <TR><TD></TD> <TD><input type="submit" name="Submit" value="Сохранить"> <input type="reset" value="Сброс"></TD></TR>
                     </TABLE>
                     </div>
                     <div>
                     <TABLE>
                     <TR><TD colspan="2" >Введите код из картинки  перед тем как нажать кнопку сохранить </TD></TR>
                     <TR><TD><img alt="Картинка с генерированым номером" src="/gennumberservlet"  /></TD> <TD><input type="text" name="gen_number"></TD></TR>
                     <TR><TD colspan="2" ></TD></TR>
                     </TABLE>
	 		         </div>
					 <input type="hidden"  name="currency_id" size="20" value="3" >
					 <input type="hidden"  name="softcost" size="20" value="0" >
					 <input type="hidden"  name="bigimage_id" size="20" value="-1" >
					 <input type="hidden"  name="type_id" size="20" value="0" >
					 <input type="hidden"  name="file_id" size="20" value="-1" >
                     <input type="hidden"  name="portlettype_id"  value="3"/>
                     <input type="hidden"  name="parent_id"  value="<%= policyBeanId.getProduct_id() %>"/>
				     </div>
				  	</div>
					</div>

		      </form>
		     </div>
		  </div>
		</div>
	
        <!-- Navigation -->
        <div class="listingBar">
	    <span class="next">
                <a HREF = "#" onClick="javascript:history.back()"  >
				<strong>
				назад
				</strong>
		        </a>
	    </span>
		</div>
		<TABLE border="0" height="200" >
		<TR><TD></TD></TR>
        </TABLE>
    
            </td>

            <td class="right">




            </td>
        </tr>
    </tbody>
</table>


<hr size="" class="netscape4" />

<div class="footer">


<br />

Product Media@Shop  - Internet shop. Copyright 2005 <A HREF="http://www.grabko.com">Grabko Business Solution</A> . Все права защищены.

<hr size="" class="netscape4" />

<strong class="netscape4">
for user netscape
</strong>

</div>

<DIV id="vunet"  class="drag" style="position: absolute; top: 120px; left: 130px;display:none;"  >
	<DIV style="background-image:url('images/f.jpg');height:20px; TEXT-ALIGN: left" id="dtitle"  >
	<A	onclick="dwindow('SelectImage.jsp')" href="#" >
	 <IMG id=upshrink_ic  title="Закрыть" src="images/expand.gif" align="right"/> 
	</A>
	<font  color='white' size='2' > <b id="title_name" >&nbsp;&raquo;&nbsp; GBS Portal</b> </font>
	</DIV>
	
	<TABLE cellSpacing="0" cellPadding="0" width="100%" border="1">
		<TBODY>
			<TR>
				<td>
				<iframe id="dialog" src="SelectImage.jsp" width="390" height="400" align="center">
				Ваш браузер не поддерживает плавающие фреймы!
				</iframe>
				</td>
			</TR>
		</TBODY>
	</TABLE>
</DIV>
</body>
</html>