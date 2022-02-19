<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--静态包含base标签、css样式、jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("#username").blur(function (){
				var username = this.value;

				$.getJSON("userServlet", "action=ajaxExistUsername&username=" + username, function (data) {
					// console.log(userStatus);
					if(data.isExist){
						$(".errorMsg").text("用户名可用");
					}else{
						$(".errorMsg").text("用户名已存在");
					}
				})
			})
			$("#sub_btn").click(function (){
				var patt=new RegExp(/^\w{5,12}$/);
				var emailpatt = new RegExp(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/)
				var $usernameText = $("#username").val();
				var $passwordText = $("#password").val();
				var $passwordConfirm = $("#repwd").val();
				var $code = $("#code").val();
				var $email = $("#email").val();
				if(!patt.test($usernameText)){
					$(".errorMsg").text("用户名不合法！");
					return false;
				}
				if(!patt.test($passwordText)){
					$(".errorMsg").text("密码不合法！");
					return false;
				}
				if($passwordText !=$passwordConfirm){
					$(".errorMsg").text("两次输入密码不一致！");
					return false;
				}
				if(!emailpatt.test($email)){
					$(".errorMsg").text("邮箱不合法！");
					return false;
				}
				if($.trim($code) == ""){
					$(".errorMsg").text("验证码不合法！");
					return false;
				}
				$(".errorMsg").empty();
			})
			$("#code_img").click(function (){
				this.src = "${basePath}kaptcha.jpg?d=" + new Date();
			});

		})
	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
<%--										   value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>" />--%>
										   value="${requestScope.username}" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
<%--										   value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>" />--%>
										   value="${requestScope.email}" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code" id="code"/>
									<img id="code_img" src="kaptcha.jpg" style="width:100px;height:38px;float: right; margin-right: 30px">
									<br />
									<br />
									<input type="hidden" name="action" value="regist">
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/foot.jsp"%>
</body>
</html>