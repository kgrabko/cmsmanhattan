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
<jsp:useBean id="SoftPostBeanId" scope="session" class="com.cbsinc.cms.SoftPostBean" />
<jsp:useBean id="AuthorizationPageBeanId" scope="session" class="com.cbsinc.cms.AuthorizationPageBean" />
<jsp:setProperty name="SoftPostBeanId" property="*" />
<title><%=AuthorizationPageBeanId.getLocalization(application).getString("title_select_big_image")%></title>
<script language="JavaScript">
        <!--
        function setData(){
        	parent.postsoftform.bigimagename.value = '<%= SoftPostBeanId.getBigimgname() %>'  ;
        	parent.postsoftform.bigimage_id.value =  '<%= SoftPostBeanId.getBigimage_id() %>'  ;
        	parent.dwindow('SelectBigImage.jsp'); 
        return true ;
        }

		function setEmpty(){
        top.postsoftform.bigimagename.value = ''  ;
        top.postsoftform.bigimage_id.value =  -1  ;
        top.dwindow('SelectImage.jsp'); 
        return true ;
        }

        function setClose(){
        	parent.dwindow('SelectImage.jsp'); 
        return true ;
        }
        
        
        function changeImage(){
		document.forms["selectBigImage"].submit();
        return true ;
		}
		
        //-->
</script>
</HEAD><BODY>
<form method="post" name="selectBigImage"   ACTION="SelectBigImage.jsp"  >
<TABLE>
<TR><TD colspan="3" ><%=AuthorizationPageBeanId.getLocalization(application).getString("title_select_big_image")%></TD></TR>
<TR><TD colspan="3" ><%=SoftPostBeanId.getSelect_big_images()%></TD></TR>
<TR><TD><input type="submit" name="Submit" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("apply") %>"  onclick="return setData()"  ></TD><TD><input type="button" value="<%= AuthorizationPageBeanId.getLocalization(application).getString("select_with_out_pic") %>" onClick="return setEmpty()" ></TD></TR>
</TABLE>
</form>
<img  id="bigimage"  height="160" alt="Current image" src="<%= SoftPostBeanId.getSelect_big_image_url() %>"  >
</body>
</html>
