<%--
  Created by IntelliJ IDEA.
  User: Levy
  Date: 2022/1/13
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/";
        pageContext.setAttribute("basePath",basePath);
    %>
    <base href=<%=basePath%>>
    <link type="text/css" rel="stylesheet" href="static/css/style.css" >
    <script type="text/javascript" src="static/jquery-1.7.2.js"></script>
</body>
</html>
