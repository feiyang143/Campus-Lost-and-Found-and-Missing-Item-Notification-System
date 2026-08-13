<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>联系我们</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="./JS/jquery-1.11.0.js"></script>
<style type="text/css">
.background {
	width: 1004px;
	overflow: auto;
	margin: 0 auto;
}


.tBody {
	width: 992px;
	margin-top: 10px;
	min-height: 400px;
	float: left;
	font-size: 14px;
	font-family: 微软雅黑;
	border: 6px solid #EAEAEA;
}

.lf_bottom {
	width: 992px;
	height: 120px;
	float: left;
	margin-top: 10px;
	border: 6px solid #EAEAEA;
}
  #container
        {
            width:540px;
            height: 400px;
           
        
        }
        a img
	{ 
				border:none;
	}
</style>
<script type="text/javascript">
	</script>
</head>

<body onload="loand()">
	<div class="background">
		<div class="lf_top_nav">
			<jsp:include page="./lf_top.jsp"></jsp:include>
			<jsp:include page="./lf_nav.jsp"></jsp:include>
		</div>
		<div class="tBody" style="float: left;">
			<div
				style="float: left;margin-top: 5px;width: 820px;margin-left:100px;word-wrap: break-word; word-break: normal;escape:false;text-align: left;">

				<p style="text-align: center;font-size: 24px;">
					<span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:24px;font-family:'宋体'">联系我们</span>
				</p>

				<p
					style=";text-autospace:ideograph-other;text-align:left;line-height:22px">
					<span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:14px;font-family:'宋体'">电话：</span><span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:14px;font-family:'宋体'">暂无&nbsp;&nbsp;</span><span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:14px;font-family:'宋体'">&nbsp;</span>
				</p>
				<p
					style=";text-autospace:ideograph-other;text-align:left;line-height:22px">
					<span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:14px;font-family:'宋体'">Email:</span><span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:14px;font-family:'宋体'">暂无</span>
				</p>
				<p
					style=";text-autospace:ideograph-other;text-align:left;line-height:22px">
					<span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:14px;font-family:'宋体'"></span>
				</p>
				<p
					style=";text-autospace:ideograph-other;text-align:left;line-height:22px">
					<span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:14px;font-family:'宋体'"></span>
				</p>
			<div class="di_tu">
   			 </div>
			</div>
		</div>
		<div class="lf_bottom">
			<iframe src="lf_bottom.jsp" width="992px" height="100px"
				frameborder="0" scrolling="no"></iframe>
		</div>
	</div>
</body>
</html>
