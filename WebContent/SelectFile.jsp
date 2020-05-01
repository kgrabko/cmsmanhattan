<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%
  response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
  response.setHeader("Pragma","no-cache"); //HTTP 1.0
  response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
  request.setCharacterEncoding("UTF-8");
%>
<html>
<HEAD>
<jsp:useBean id="authorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:useBean id="publisherBeanId" scope="session" class="com.cbsinc.cms.PublisherBean" />
<jsp:setProperty name="publisherBeanId" property="*" />

<title><%=authorizationPageBeanId.getLocalization(application).getString("title_select_file")%></title>
<script language="JavaScript">
        <!--
        function setData(){
        	parent.postsoftform.filename.value = '<%= publisherBeanId.getFilename() %>'  ;
        	parent.postsoftform.file_id.value =  '<%= publisherBeanId.getFile_id() %>'  ;
        	parent.dwindow('SelectFile.jsp'); 
        return true ;
        }

		function setEmpty(){
        top.postsoftform.filename.value = ''  ;
        top.postsoftform.file_id.value =  -1  ;
        top.dwindow('SelectFile.jsp'); 
        return true ;
        }

         function changeImage(){
		document.forms["selectfile1"].submit();
        return true ;
		}
        
        function setClose()
        {
        	parent.dwindow('SelectFile.jsp'); 
        return true ;
        }

        //-->
</script>
</HEAD><BODY  >
<form method="post" name="selectfile1"   ACTION="SelectFile.jsp"  >
<TABLE>
<TR><TD colspan="3" ><%=authorizationPageBeanId.getLocalization(application).getString("title_select_file")%></TD></TR> 
<TR><TD colspan="3" ><%=publisherBeanId.getSelect_files()%></TD></TR>
<TR> <TD><input type="submit" name="Submit" value="<%= authorizationPageBeanId.getLocalization(application).getString("apply") %>"  onclick="return setData()" ></TD><TD><input type="button" value="<%= authorizationPageBeanId.getLocalization(application).getString("select_with_out_file") %>" onClick="return setEmpty()" ></TD></TR>
</TABLE>
</form>
</body>
</html>
