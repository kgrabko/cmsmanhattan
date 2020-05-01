<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="creteriaListBeanId" scope="session" class="com.cbsinc.cms.CreteriaListBean" />
<jsp:useBean id="publisherBeanId" scope="session" class="com.cbsinc.cms.PublisherBean" />
<jsp:useBean id="authorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
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
          parent.document.getElementById("title_name").value = '<%= authorizationPageBeanId.getLocalization(application).getString("change_means_of_creteria") %>' ; 
          
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
//creteriaListBeanId.setSite_id(authorizationPageBeanId.getSite_id());
//if( request.getParameter("parent_id") != null) creteriaListBeanId.setParent_id(request.getParameter("parent_id"));

if( request.getParameter("table_name") != null) creteriaListBeanId.setTable_name(request.getParameter("table_name"));

if( request.getParameter("table_name") != null  )
{
if( request.getParameter("table_name").compareTo("creteria1") == 0 ) creteriaListBeanId.setLink_id(0);
else if( request.getParameter("table_name").compareTo("creteria2") == 0 ) creteriaListBeanId.setLink_id(Integer.parseInt(publisherBeanId.getCreteria1_id()));
else if( request.getParameter("table_name").compareTo("creteria3") == 0 ) creteriaListBeanId.setLink_id(Integer.parseInt(publisherBeanId.getCreteria2_id()));
else if( request.getParameter("table_name").compareTo("creteria4") == 0 ) creteriaListBeanId.setLink_id(Integer.parseInt(publisherBeanId.getCreteria3_id()));
else if( request.getParameter("table_name").compareTo("creteria5") == 0 ) creteriaListBeanId.setLink_id(Integer.parseInt(publisherBeanId.getCreteria4_id()));
else if( request.getParameter("table_name").compareTo("creteria6") == 0 ) creteriaListBeanId.setLink_id(Integer.parseInt(publisherBeanId.getCreteria5_id()));
else if( request.getParameter("table_name").compareTo("creteria7") == 0 ) creteriaListBeanId.setLink_id(Integer.parseInt(publisherBeanId.getCreteria6_id()));
else if( request.getParameter("table_name").compareTo("creteria8") == 0 ) creteriaListBeanId.setLink_id(Integer.parseInt(publisherBeanId.getCreteria7_id()));
else if( request.getParameter("table_name").compareTo("creteria9") == 0 ) creteriaListBeanId.setLink_id(Integer.parseInt(publisherBeanId.getCreteria8_id()));
else if( request.getParameter("table_name").compareTo("creteria10") == 0 ) creteriaListBeanId.setLink_id(Integer.parseInt(publisherBeanId.getCreteria9_id()));
}

if( request.getParameter("creteria_value") != null) creteriaListBeanId.setTitle(request.getParameter("creteria_value"), creteriaListBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true")));


if( request.getParameter("row") != null)
{
int index =  creteriaListBeanId.stringToInt(request.getParameter("row")) ;
creteriaListBeanId.setIndx_select(index);
}
if( request.getParameter("del") != null)
{
int index =  creteriaListBeanId.stringToInt(request.getParameter("del")) ;
//int g =  creteriaListBeanId.rows.length ;
String creteria_id = creteriaListBeanId.rows[index][0] ;
  if(!creteria_id.equals("0"))
   { 
   if(creteria_id != null)creteriaListBeanId.delete(creteria_id) ;
   request.setAttribute("del",null);
   }
}
if( request.getParameter("offset") != null){
creteriaListBeanId.setOffset(  creteriaListBeanId.stringToInt(request.getParameter("offset")));
}
creteriaListBeanId.initPage(creteriaListBeanId.getPartCriteria(authorizationPageBeanId.getSite_id(), authorizationPageFaced.getResources_cms_settings().getString("is_criteria_by_catalog").equals("true")));
%>
<body onload="setTitle()"  >

	   <h1><%=authorizationPageBeanId.getLocalization(application).getString("change_name_of_creteria")%> </h1>
	   <div class="box">
		  <div class="body">
		     <div >
		        <form method="post"  name="creteria"  action="Creteria.jsp">
				<INPUT SIZE="40"  AUTOCOMPLETE="off" TYPE="TEXT" NAME="creteria_value" VALUE="<%= creteriaListBeanId.getTitle() %>"  ></INPUT>
				<INPUT TYPE="submit" name="submit" value="<%= authorizationPageBeanId.getLocalization(application).getString("edit") %>"></INPUT>
		        </form>
		       	        
		       
		     </div>
		  </div>
		</div>
		<h1><%=authorizationPageBeanId.getLocalization(application).getString("add_keyword")%> </h1>
		<div class="box">
		  <div class="body">
		     <div>
                   <%=creteriaListBeanId.getTable(request.getLocale())%>
		     </div>
		  </div>
		</div>
		<div class="listingBar">
   	   	 	<a HREF = "Creteria.jsp?creteria_value="  ><%=authorizationPageBeanId.getLocalization(application).getString("hide")%></a>
		</div>
		
</body>
</html>