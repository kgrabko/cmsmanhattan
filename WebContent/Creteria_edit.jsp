<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="creteriaEditBeanId" scope="session" class="com.cbsinc.cms.CreteriaEditBean" />
<jsp:useBean id="creteriaListBeanId" scope="session" class="com.cbsinc.cms.CreteriaListBean" />
<jsp:useBean id="authorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<%
request.setCharacterEncoding("UTF-8");

if( authorizationPageBeanId.getIntLevelUp() != 2 )
{

	authorizationPageBeanId.setStrMessage(authorizationPageBeanId.getLocalization(application).getString("post_message_notaccess_admin"));
	response.sendRedirect("Authorization.jsp" );
}

if( request.getParameter("row") != null)
{
int index  =  creteriaListBeanId.stringToInt(request.getParameter("row")) ;
creteriaEditBeanId.setIndx_select(index);
creteriaEditBeanId.setTable_name(creteriaListBeanId.getTable_name());
}
if( request.getParameter("name") != null)
{
	creteriaEditBeanId.setName(  request.getParameter("name"));
}

if( request.getParameter("creteria_id") != null)
{
	creteriaEditBeanId.setCreteria_id(  request.getParameter("creteria_id"));
}

if(request.getMethod().toUpperCase().compareTo("POST") == 0)
{
	creteriaEditBeanId.editCatalog();
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
     top.document.getElementById("title_name").value = '<%= authorizationPageBeanId.getLocalization(application).getString("change_means_of_creteria") %>' ; 
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
	    <h1><%=authorizationPageBeanId.getLocalization(application).getString("change_means_of_creteria")%></h1>
		<br/>
		<div class="box">
		  <div class="body">
		    <div >
                     <form method="post"   name="creteria_edit"  ACTION="Creteria_edit.jsp" >
                     <TABLE>
                     <TR><TD></TD><TD><input type="hidden" name="creteria_id"  value = "<%= creteriaListBeanId.rows[creteriaEditBeanId.getIndx_select()][0] %>"  />
                     <TR><TD><%=authorizationPageBeanId.getLocalization(application).getString("name")%>:* </TD><TD> <input name="name" size="30" value = "<%= creteriaListBeanId.rows[creteriaEditBeanId.getIndx_select()][1] %>"  onBlur="checkEmpty(this.value)" />
                     <TR><TD></TD> <TD><input type="submit" name="Submit" value="<%= authorizationPageBeanId.getLocalization(application).getString("save") %>"> <input type="reset" value="<%= authorizationPageBeanId.getLocalization(application).getString("undo") %>"></TD></TR>
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
			<%=authorizationPageBeanId.getLocalization(application).getString("back")%>
			</strong>
	        </a>
		</div>
</body>
</html>