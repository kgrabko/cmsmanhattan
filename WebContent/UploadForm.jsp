<%@ page errorPage="error.jsp" %>
<html>
<head>
<title>
UploadForm
</title>
<%
  response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
  response.setHeader("Pragma","no-cache"); //HTTP 1.0
  response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

</head>
<jsp:useBean id="publisherBeanId" scope="session" class="com.cbsinc.cms.PublisherBean" />
<jsp:setProperty name="publisherBeanId" property="*" />
<body>

<%
String   filename  = request.getParameter("file_id");
if ( filename != null ) {  publisherBeanId.setSample( filename ); }
else{ publisherBeanId.setSample(""); }
filename = null ;
%>

<h1>
Upload File
</h1>
  <form  name="uploadform"  action="/uploadservlet" method="post" enctype="multipart/form-data">
  <input type="file" name="file"><br>
  <input type="submit" value="Select file" >
  </form>
</body>
</html>
