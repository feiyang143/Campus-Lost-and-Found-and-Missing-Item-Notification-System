<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>赏金排行榜</title>
<link rel="stylesheet" href="css/index.css" type="text/css"></link>
<style type="text/css">
.rewardRanking_Body {
	width: 1004px;
	padding: 0px;
	margin: 0px auto;
}

.rewardRanking_InfoBody {
	width: 700px;
	min-height: 600px;
	float: left;
	text-align: center;
}

.rewardRanking_Table {
	width: 700px;
	margin-top: 8px;
	border: 6px solid #EAEAEA;
	text-align: center;
}

.rewardRanking_Table table {
	width: 100%;
	border-collapse: collapse;
}

.rewardRanking_Table th {
	background-color: #f2f2f2;
	padding: 10px;
	border: 1px solid #ddd;
}

.rewardRanking_Table td {
	padding: 10px;
	border: 1px solid #ddd;
}

.rewardRanking_Table tr:hover {
	background-color: #f5f5f5;
}

.rewardRanking_help {
	width: 270px;
	height: 400px;
	float: left;
	margin-left: 22px;
	margin-top: 8px;
	border: 6px solid #EAEAEA;
}

.rewardRanking_Title {
	font-size: 24px;
	font-weight: bold;
	margin: 20px 0;
	color: #333;
}
</style>
</head>
<body class="rewardRanking_Body">
	<div class="lf_top_nav">
		<jsp:include page="./lf_top.jsp"></jsp:include>
		<jsp:include page="./lf_nav.jsp"></jsp:include>
	</div>
	<div class="rewardRanking_InfoBody">
		<div class="rewardRanking_Table">
			<div class="rewardRanking_Title">赏金排行榜</div>
			<table>
				<tr>
					<th width="10%">排名</th>
					<th width="30%">用户昵称</th>
					<th width="30%">用户名</th>
					<th width="30%">赏金值</th>
				</tr>
				<s:iterator value="#session.topUsers" var="user" status="status">
					<tr>
						<td><s:property value="#status.index + 1" /></td>
						<td><s:property value="#user.usernickname" /></td>
						<td><s:property value="#user.username" /></td>
						<td><s:property value="#user.reward_points" /></td>
					</tr>
				</s:iterator>
				<s:if test="#session.topUsers == null || #session.topUsers.size() == 0">
					<tr>
						<td colspan="4">暂无数据</td>
					</tr>
				</s:if>
			</table>
		</div>
	</div>
	<div class="rewardRanking_help">
		<iframe src="index_help.jsp" width="268px;" height="250px;"
			frameborder="0" scrolling="no"></iframe>
	</div>
	<div class="lf_bottom">
		<iframe src="lf_bottom.jsp" width="992px" height="100px"
			frameborder="0" scrolling="no"></iframe>
	</div>
</body>
</html>