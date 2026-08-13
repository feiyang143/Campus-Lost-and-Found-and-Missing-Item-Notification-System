<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link rel="stylesheet" href="css/index.css" type="text/css"></link>
<style type="text/css">
.register_Body {
	width: 1004px;
	padding: 0px;
	margin: auto;
}

.register_Top {
	width: 1004px;
	height: 245px;
	float: left;
}

.register_Content {
	width: 992px;
	float: left;
	margin-top: 10px;
	border: 6px solid #EAEAEA;
	padding: 20px;
}

.register_Title {
	font-family: "微软雅黑";
	font-size: 24px;
	color: #5A7D00;
	margin-bottom: 20px;
	text-align: center;
}

.register_Form {
	width: 500px;
	margin: 0 auto;
}

.register_Form tr {
	height: 40px;
	line-height: 40px;
}

.register_Form th {
	width: 100px;
	text-align: right;
	font-family: "微软雅黑";
	color: #5A7D00;
	font-weight: bolder;
}

.register_Form td {
	width: 400px;
	padding-left: 20px;
}

.register_Input {
	width: 260px;
	padding-left: 10px;
	height: 26px;
	border: 1px solid #CCCCCC;
	font-family: "微软雅黑";
}

.register_Btn {
	width: 80px;
	height: 30px;
	border: 1px solid #CCCCCC;
	background-color: #7FB540;
	font-family: "微软雅黑";
	font-weight: bolder;
	color: white;
	cursor: pointer;
}

.register_Btn:hover {
	background-color: #6A9B30;
}

.register_Link {
	font-family: "微软雅黑";
	color: #5A7D00;
	text-decoration: none;
}

.register_Link:hover {
	color: #FF5603;
	text-decoration: underline;
}
</style>
</head>
<body class="register_Body">
	<div class="register_Top">
		<jsp:include page="./lf_top.jsp"></jsp:include>
		<jsp:include page="./lf_nav.jsp"></jsp:include>
	</div>
	<div class="register_Content">
		<div class="register_Title">用户注册</div>
		<form action="user_Register.action" method="post">
			<table class="register_Form">
				<tr>
					<th>用户名：</th>
					<td><input class="register_Input" type="text" name="username" required /></td>
				</tr>
				<tr>
					<th>密&nbsp;&nbsp;码：</th>
					<td><input class="register_Input" type="password" name="userpassword" required /></td>
				</tr>
				<tr>
					<th>昵&nbsp;&nbsp;称：</th>
					<td><input class="register_Input" type="text" name="usernickname" /></td>
				</tr>
				<tr>
					<th>电&nbsp;&nbsp;话：</th>
					<td><input class="register_Input" type="text" name="userphone" /></td>
				</tr>
				<tr>
					<th>QQ号：</th>
					<td><input class="register_Input" type="text" name="userqq" /></td>
				</tr>
				<tr>
					<th></th>
					<td>
						<input class="register_Btn" type="submit" value="注册" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="register_Btn" type="button" value="返回登录" onclick="window.location.href='index.jsp'" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div class="lf_bottom">
		<iframe src="lf_bottom.jsp" width="992px" height="100px" frameborder="0" scrolling="no"></iframe>
	</div>
</body>
</html>