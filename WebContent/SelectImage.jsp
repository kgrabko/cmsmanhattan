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
<title><%=authorizationPageBeanId.getLocalization(application).getString("title_select_small_image")%></title>
<script language="JavaScript">
        <!--
        function setData(){
        parent.postsoftform.imagename.value = '<%= publisherBeanId.getImgname() %>'  ;
        parent.postsoftform.image_id.value =  '<%= publisherBeanId.getImage_id() %>'  ;
        parent.dwindow('SelectImage.jsp'); 
        return true ;
        }

		function setEmpty(){
        top.postsoftform.imagename.value = ''  ;
        top.postsoftform.image_id.value =  -1  ;
        top.dwindow('SelectImage.jsp'); 
        return true ;
        }

        function setClose(){
        parent.dwindow('SelectImage.jsp'); 
        return true ;
        }

        function changeImage(){
		document.forms["selectImage1"].submit();
        return true ;
        }



        //-->
</script>
</HEAD><BODY>
<form method="post" name="selectImage1"   ACTION="SelectImage.jsp"  >
<TABLE>
<TR><TD colspan="3" ><%=authorizationPageBeanId.getLocalization(application).getString("title_select_small_image")%></TD></TR>
<TR><TD colspan="3" ><%=publisherBeanId.getSelect_small_images()%></TD></TR>
<TR><TD><input type="submit" name="Submit" value="<%= authorizationPageBeanId.getLocalization(application).getString("apply") %>"  onclick="return setData()"  ></TD><TD><input type="button" value="<%= authorizationPageBeanId.getLocalization(application).getString("select_with_out_pic") %>" onClick="return setEmpty()" ></TD></TR>
</TABLE>
</form>
 
 <img  id="smalimage"   height="260" alt="Current image"  src="<%= publisherBeanId.getSelect_small_image_url() %>"  >

</body>
</html>
