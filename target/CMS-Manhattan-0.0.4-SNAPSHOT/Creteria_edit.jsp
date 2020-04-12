<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="creteria_editBean" scope="session" class="com.cbsinc.cms.Creteria_editBean" />
<jsp:useBean id="creteria_listBean" scope="session" class="com.cbsinc.cms.Creteria_listBean" />
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<%
request.setCharacterEncoding("UTF-8");

if( AuthorizationPageBeanId.getIntLevelUp() != 2 )
{

	AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(application).getString("post_message_notaccess_admin"));
	response.sendRedirect("Authorization.jsp" );
}

if( request.getParameter("row") != null)
{
int index  =  creteria_listBean.stringToInt(request.getParameter("row")) ;
creteria_editBean.setIndx_select(index);
creteria_editBean.setTable_name(creteria_listBean.getTable_name());
}
if( request.getParameter("name") != null)
{
	creteria_editBean.setName(  request.getParameter("name"));
}

if( request.getParameter("creteria_id") != null)
{
	creteria_editBean.setCreteria_id(  request.getParameter("creteria_id"));
}

if(request.getMethod().toUpperCase().compareTo("POST") == 0)
{
	creteria_editBean.editCatalog();
	response.sendRedirect("Creteria.jsp");
}
%>
<html>
<head>
    <title>GBS Portal</title>
    <style type="text/css" media="screen"> @import url(style2.css);</style>
	
	<script language="JavaScript">
	<!--     
	function setTitle() 
	{
     top.document.getElementById("title_name").value = '<%= AuthorizationPageBeanId.getLocalization(application).getString("change_means_of_creteria") %>' ; 
	}
	
	function checkEmpty(tmp){
	if(isEmpty(tmp) ){ alert('Filed ' + tmp + ' is empty'); return false; }
	return true ;
	}
	
	function isEmpty(tmp)
	{
	if( tmp.length == 0 ) return true ;
	return false ;
	}
	
	 //-->
	</script>    
</head>


<body onload="setTitle()" >
	    <h1><%=AuthorizationPageBeanId.getLocalization(application).getString("change_means_of_creteria")%></h1>
		<br/>
		<div class="box">
		  <div class="body">
		    <div >
                     <form method="post"   name="creteria_edit"  ACTION="Creteria_edit.jsp" >
                     <TABLE>
                     <TR><TD></TD><TD><input type="hidden" name="creteria_id"  value = "<%= creteria_listBean.rows[creteria_editBean.getIndx_select()][0] %>"  />
                     <TR><TD><%=AuthorizationPageBeanId.getLocalization(application).getString("name")%>:* </TD><TD> <input name="name" size="30" value = "<%= creteria_listBean.rows[creteria_editBean.getIndx_select()][1] %>"  onBlur="checkEmpty(this.value)" />
                     <TR><TD></TD> <TD><input type="submit" name="Submit" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("save") %>"> <input type="reset" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("undo") %>"></TD></TR>
                     </TABLE>
                     </form>
		     </div>
		  </div>
		</div>
	    <br/>
		<div class="listingBar">
   	   	 <span class="next"> 
   	   	 	<a HREF = "Creteria.jsp"  >
			<strong>
			<%=AuthorizationPageBeanId.getLocalization(application).getString("back")%>
			</strong>
	        </a>
		</div>
</body>
</html>