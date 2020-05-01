<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="authorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<%
  response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
  response.setHeader("Pragma","no-cache"); //HTTP 1.0
  response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
  request.setCharacterEncoding("UTF-8");
%>
<HTML>
  <HEAD>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <TITLE>Image upload</TITLE>
      <script language="JavaScript">
        <!--
        function setClose()
        {
        top.dwindow('SelectFile.jsp'); 
        return true ;
        }
        //-->
	</script>
  </HEAD>
  <BODY>
  <h5><%=authorizationPageBeanId.getLocalization(application).getString("upload_file_from_user")%> </h5>
  
  <%=authorizationPageBeanId.getLocalization(application).getString("upload_file_from_user_description")%>  <br/><br/><br/>
  
  <form  name="uploadform"  action="fileservletupload" method="post" enctype="multipart/form-data">
  <input type="file" name="file"><br>
  <input type="submit" value = "<%= authorizationPageBeanId.getLocalization(application).getString("upload") %>" />
  <input type="button" value = "<%= authorizationPageBeanId.getLocalization(application).getString("close_window") %>" onClick="return setClose()" />
  </form>
</BODY>

</HTML>
