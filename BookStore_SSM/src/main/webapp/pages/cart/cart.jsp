<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含base标签、css样式、jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("a.deleteClass").click(function (){
				return confirm("是否确认要删除《" + $(this).parent().parent().find("td:first").text() + "》?");
			})
			$("a.emptyClass").click(function (){
				return confirm("是否确认要清空购物车？");
			})
			$(".updateCount").change(function (){
				var name = $(this).parent().parent().find("td:first").text();
				var count = this.value;
				var bookId = $(this).attr('bookId');
				if(confirm("是否确认修改《" + name + "》的数量为" + count + "？")){
					location.href="cart/updateCount?id=" + bookId + "&count=" + count;
				}else{
					//defaultValue是表单项DOM对象的值，表示默认的value属性值。
					this.value = this.defaultValue;
				}
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%--静态包含登录成功之后的界面--%>
			<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td colspan="2">操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items.values()}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空！快去浏览商品吧！</a></td>
				</tr>
			</c:if>
			<c:forEach items="${sessionScope.cart.items.values()}" var="book">
			<tr>
				<td>${book.name}</td>
				<td>
					<input class="updateCount" type="text" style="width: 80px"
						   bookId="${book.id}" value="${book.count}">
				</td>
				<td>${book.price}</td>
				<td>${book.totalPrice}</td>
				<td><a class="deleteClass" href="cart/deleteItem?id=${book.id}">删除</a></td>
			</tr>
			</c:forEach>

		</table>
		<c:if test="${not empty sessionScope.cart.items.values()}">
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a class="emptyClass" href="cart/clear">清空购物车</a></span>
			<span class="cart_span"><a href="order/createOrder">去结账</a></span>
		</div>
		</c:if>
	
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>