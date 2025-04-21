<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  String pagePath = "Page/loginUser.jsp";
  request.getSession().invalidate();
  if (request.getParameter("path")!=null){
    pagePath = request.getParameter("path");
  }
  response.sendRedirect(pagePath);
%>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
  <p>hello</p>
</body>
</html>