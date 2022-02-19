<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <%--静态包含base标签、css样式、jQuery文件--%>
    <%@ include file="/pages/common/head.jsp"%>
</head>
<body>
<h1 align="center">您没有登录后台的权限，请联系管理员~</h1>
<div>
    <a href="index.jsp">返回首页</a>
</div>
</body>
</html>