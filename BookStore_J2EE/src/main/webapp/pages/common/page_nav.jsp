<%--
  Created by IntelliJ IDEA.
  User: Levy
  Date: 2022/1/15
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div id="page_nav">
        <c:if test="${requestScope.page.pageNo > 1}">
            <a href="${requestScope.page.url}&pageNo=1">首页</a>
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
        </c:if>
        <%-- 页码输出的开始 --%>
        <c:choose>
            <%-- 页码输出的第一种情况 --%>
            <c:when test="${requestScope.page.pageTotal  <= 5}">
                <c:set var="begin" value="1" />
                <c:set var="end" value="${requestScope.page.pageTotal}" />
            </c:when>
            <%-- 页码输出的第二种情况 --%>
            <c:when test="${requestScope.page.pageTotal > 5}">
                <c:choose>
                    <c:when test="${requestScope.page.pageNo <= 3}">
                        <c:set var="begin" value="1" />
                        <c:set var="end" value="5" />
                    </c:when>
                    <c:when test="${requestScope.page.pageNo >= requestScope.page.pageTotal - 2}">
                        <c:set var="begin" value="${requestScope.page.pageTotal - 4}" />
                        <c:set var="end" value="${requestScope.page.pageTotal}" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="begin" value="${requestScope.page.pageNo - 2}" />
                        <c:set var="end" value="${requestScope.page.pageNo + 2}" />
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
        <c:forEach begin="${begin}" end="${end}" var="i">
            <c:if test="${i == requestScope.page.pageNo}">
                【${i}】
            </c:if>
            <c:if test="${i != requestScope.page.pageNo}">
                <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
            </c:if>
        </c:forEach>

        <%-- 页码输出的结束 --%>
        <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
        </c:if>
        共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
        到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
        <input type="button" value="确定" id="searchPageBtn">
        <script type="text/javascript">
            $(function (){
                $("#searchPageBtn").click(function (){
                    var pageNo = $("#pn_input").val();
                    if(pageNo < 1 || pageNo > ${requestScope.page.pageTotal}){
                        alert("输入页码非法！");
                    }else{
                        location.href = "${pageScope.basePath}/${requestScope.page.url}&pageNo=" + pageNo;
                    }
                })
            })
        </script>
    </div>
</body>
</html>
