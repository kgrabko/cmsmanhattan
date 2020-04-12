<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:useBean id="creteria_listBean" scope="session" class="com.cbsinc.cms.Creteria_listBean" />
<jsp:useBean id="creteria_addBean" scope="session" class="com.cbsinc.cms.Creteria_addBean" />
<%
request.setCharacterEncoding("UTF-8");

if( AuthorizationPageBeanId.getIntLevelUp() != 2 )
{

	AuthorizationPageBeanId.setStrMessage(AuthorizationPageBeanId.getLocalization(application).getString("post_message_notaccess_admin"));
	response.sendRedirect("Authorization.jsp" );
}

creteria_addBean.setTable_name(creteria_listBean.getTable_name());
if( request.getParameter("link_id") != null) creteria_addBean.setLink_id(Integer.parseInt(request.getParameter("link_id")));

if( request.getParameter("name") != null)
{
	creteria_addBean.setName(  request.getParameter("name"));
	creteria_addBean.setLabel(creteria_listBean.getTitle());
}

if(request.getMethod().toUpperCase().compareTo("POST") == 0)
{
	creteria_addBean.addCatalog(AuthorizationPageBeanId.getSite_id());
	response.sendRedirect("Creteria.jsp");
}
%>
<html>
<head>
     <title>GBS Portal</title>
     <style type="text/css" media="screen"> @import url(style2.css);</style>
     <script language="JavaScript">
	<!--  
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

<body>
    <h1><%=AuthorizationPageBeanId.getLocalization(application).getString("add_means_of_creteria")%> </h1>
	<br/>
		<div class="box">
		  <div class="body">
		    <div >
                     <form method="post"   name="catalog_add"  ACTION="Creteria_add.jsp" >
                     <TABLE>
                     <TR><TD><%=AuthorizationPageBeanId.getLocalization(application).getString("creteriy")%>:* </TD><TD> <input  name="name" size="30"   onBlur="checkEmpty(this.value)" >
                     <TR><TD></TD> <TD><input type="submit" name="Submit" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("save") %>"> <input type="reset" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("clear") %>"></TD></TR>
                     </TABLE>
                     </form>
		     </div>
		  </div>
		</div>
		<div class="listingBar">
   	   	 <span class="next"> 
   	   	 	<a HREF = "#" onClick="javascript:history.back()"  >
			<strong>
			<%=AuthorizationPageBeanId.getLocalization(application).getString("back")%>
			</strong>
	        </a>
		</div>
</body>
</html>