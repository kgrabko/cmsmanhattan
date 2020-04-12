<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<%
  response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
  response.setHeader("Pragma","no-cache"); //HTTP 1.0
  response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
  request.setCharacterEncoding("UTF-8");
%>
<HTML>
  <HEAD>
    <TITLE><%=AuthorizationPageBeanId.getLocalization(application).getString("upload_music_clip_flv_from_user")%> </TITLE>
    <script language="JavaScript">
        function setClose()
        {
        top.dwindow('SelectImage.jsp'); 
        return true ;
        }
        //-->
	</script>
  </HEAD>
  <BODY>
  <h5><%=AuthorizationPageBeanId.getLocalization(application).getString("upload_music_clip_flv_from_user")%> </h5>
  <br/> <%=AuthorizationPageBeanId.getLocalization(application).getString("upload_music_clip_flv_from_user_description")%> 
  <br/>
  <form  name="uploadform"  action="bigimageservletupload" method="post" enctype="multipart/form-data">
  <input type="file" name="file"><br>
  <input type="submit" value='<%= AuthorizationPageBeanId.getLocalization(application).getString("upload") %>' ><input type="button" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("close_window") %>" onClick="return setClose()" >
  </form>
</BODY>
</HTML>