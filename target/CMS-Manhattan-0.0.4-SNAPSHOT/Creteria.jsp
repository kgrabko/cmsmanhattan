<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="creteria_listBean" scope="session" class="com.cbsinc.cms.Creteria_listBean" />
<jsp:useBean id="SoftPostBeanId" scope="session" class="com.cbsinc.cms.SoftPostBean" />
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:useBean id="authorizationPageFaced" scope="application" class="com.cbsinc.cms.faceds.AuthorizationPageFaced" />
 <%
   request.setCharacterEncoding("UTF-8");
   response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
   %>
<html>
<head>
     <title>GBS Portal</title>
     <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <style type="text/css" media="screen"> @import url(style2.css);</style>
     <script language="JavaScript">
	<!--     
	
 
	function setTitle() 
	{
     //top.document.getElementById("title_name").value = 'Изменение значения в критерии' ; 
          parent.document.getElementById("title_name").value = '<%= AuthorizationPageBeanId.getLocalization(application).getString("change_means_of_creteria") %>' ; 
          
	}

	window.onunload=myUnloadHandler;

	function myUnloadHandler()
	{
	parent.doRefreshCreteria() ;
    //alert('reload' + parent.document.getElementById("search_cre").title);
	}


	
	 //-->
	</script>
 
</head>

<%
//creteria_listBean.setSite_id(AuthorizationPageBeanId.getSite_id());
//if( request.getParameter("parent_id") != null) creteria_listBean.setParent_id(request.getParameter("parent_id"));

if( request.getParameter("table_name") != null) creteria_listBean.setTable_name(request.getParameter("table_name"));

if( request.getParameter("table_name") != null  )
{
if( request.getParameter("table_name").compareTo("creteria1") == 0 ) creteria_listBean.setLink_id(0);
else if( request.getParameter("table_name").compareTo("creteria2") == 0 ) creteria_listBean.setLink_id(Integer.parseInt(SoftPostBeanId.getCreteria1_id()));
else if( request.getParameter("table_name").compareTo("creteria3") == 0 ) creteria_listBean.setLink_id(Integer.parseInt(SoftPostBeanId.getCreteria2_id()));
else if( request.getParameter("table_name").compareTo("creteria4") == 0 ) creteria_listBean.setLink_id(Integer.parseInt(SoftPostBeanId.getCreteria3_id()));
else if( request.getParameter("table_name").compareTo("creteria5") == 0 ) creteria_listBean.setLink_id(Integer.parseInt(SoftPostBeanId.getCreteria4_id()));
else if( request.getParameter("table_name").compareTo("creteria6") == 0 ) creteria_listBean.setLink_id(Integer.parseInt(SoftPostBeanId.getCreteria5_id()));
else if( request.getParameter("table_name").compareTo("creteria7") == 0 ) creteria_listBean.setLink_id(Integer.parseInt(SoftPostBeanId.getCreteria6_id()));
else if( request.getParameter("table_name").compareTo("creteria8") == 0 ) creteria_listBean.setLink_id(Integer.parseInt(SoftPostBeanId.getCreteria7_id()));
else if( request.getParameter("table_name").compareTo("creteria9") == 0 ) creteria_listBean.setLink_id(Integer.parseInt(SoftPostBeanId.getCreteria8_id()));
else if( request.getParameter("table_name").compareTo("creteria10") == 0 ) creteria_listBean.setLink_id(Integer.parseInt(SoftPostBeanId.getCreteria9_id()));
}

if( request.getParameter("creteria_value") != null) creteria_listBean.setTitle(request.getParameter("creteria_value"), creteria_listBean.getPartCriteria(AuthorizationPageBeanId.getSite_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true")));


if( request.getParameter("row") != null)
{
int index =  creteria_listBean.stringToInt(request.getParameter("row")) ;
creteria_listBean.setIndx_select(index);
}
if( request.getParameter("del") != null)
{
int index =  creteria_listBean.stringToInt(request.getParameter("del")) ;
//int g =  creteria_listBean.rows.length ;
String creteria_id = creteria_listBean.rows[index][0] ;
  if(!creteria_id.equals("0"))
   { 
   if(creteria_id != null)creteria_listBean.delete(creteria_id) ;
   request.setAttribute("del",null);
   }
}
if( request.getParameter("offset") != null){
creteria_listBean.setOffset(  creteria_listBean.stringToInt(request.getParameter("offset")));
}
creteria_listBean.initPage(creteria_listBean.getPartCriteria(AuthorizationPageBeanId.getSite_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true")));
%>
<body onload="setTitle()"  >

	   <h1><%=AuthorizationPageBeanId.getLocalization(application).getString("change_name_of_creteria")%> </h1>
	   <div class="box">
		  <div class="body">
		     <div >
		        <form method="post"  name="creteria"  action="Creteria.jsp">
				<INPUT SIZE="40"  AUTOCOMPLETE="off" TYPE="TEXT" NAME="creteria_value" VALUE="<%= creteria_listBean.getTitle() %>"  ></INPUT>
				<INPUT TYPE="submit" name="submit" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("edit") %>"></INPUT>
		        </form>
		       	        
		       
		     </div>
		  </div>
		</div>
		<h1><%=AuthorizationPageBeanId.getLocalization(application).getString("add_keyword")%> </h1>
		<div class="box">
		  <div class="body">
		     <div>
                   <%=creteria_listBean.getTable(request.getLocale())%>
		     </div>
		  </div>
		</div>
		<div class="listingBar">
   	   	 	<a HREF = "Creteria.jsp?creteria_value="  ><%=AuthorizationPageBeanId.getLocalization(application).getString("hide")%></a>
		</div>
		
</body>
</html>