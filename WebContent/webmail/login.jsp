<%@ page import = "java.io.*,java.util.*" %>

<html>
   <head>
      <title>Web mail service</title>
   </head>
   
   <body>
      <center>
         <h1>Web mail service</h1>
      </center>
      Use your site login and password to login in email
      <%
         // New location to be redirected
         String site = new String("/webmail/");
         response.setStatus(response.SC_MOVED_TEMPORARILY);
         response.setHeader("Location", site); 
      %>
   </body>
</html>