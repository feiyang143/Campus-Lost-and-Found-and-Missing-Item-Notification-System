<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据统计</title>
<link rel="stylesheet" href="css/index.css" type="text/css"></link>
<style type="text/css">
.dataStatistics_Body {
	width: 1004px;
	padding: 0px;
	margin: 0px auto;
}

.dataStatistics_InfoBody {
	width: 700px;
	min-height: 600px;
	float: left;
	text-align: center;
}

.dataStatistics_Content {
	width: 700px;
	margin-top: 8px;
	border: 6px solid #EAEAEA;
	text-align: center;
}

.dataStatistics_Title {
	font-size: 24px;
	font-weight: bold;
	margin: 20px 0;
	color: #333;
}

.dataStatistics_Counts {
	font-size: 18px;
	margin: 20px 0;
}

.dataStatistics_Chart {
	width: 500px;
	height: 400px;
	margin: 20px auto;
}

.dataStatistics_help {
	width: 270px;
	height: 400px;
	float: left;
	margin-left: 22px;
	margin-top: 8px;
	border: 6px solid #EAEAEA;
}
</style>
<!-- Include Chart.js library -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body class="dataStatistics_Body">
	<div class="lf_top_nav">
		<jsp:include page="./lf_top.jsp"></jsp:include>
		<jsp:include page="./lf_nav.jsp"></jsp:include>
	</div>
	<div class="dataStatistics_InfoBody">
		<div class="dataStatistics_Content">
			<div class="dataStatistics_Title">数据统计</div>
			<div class="dataStatistics_Counts">
				<p>寻物启事数量: <span id="lostCount"><s:property value="#session.lostCount" /></span></p>
				<p>招领启事数量: <span id="foundCount"><s:property value="#session.foundCount" /></span></p>
			</div>
			<div class="dataStatistics_Chart">
				<canvas id="statisticsChart"></canvas>
			</div>
		</div>
	</div>
	<div class="dataStatistics_help">
		<iframe src="index_help.jsp" width="268px;" height="250px;"
			frameborder="0" scrolling="no"></iframe>
	</div>
	<div class="lf_bottom">
		<iframe src="lf_bottom.jsp" width="992px" height="100px"
			frameborder="0" scrolling="no"></iframe>
	</div>
	
	<script type="text/javascript">
		// Get counts from DOM
		var lostCount = parseInt(document.getElementById('lostCount').textContent);
		var foundCount = parseInt(document.getElementById('foundCount').textContent);
		
		// Prepare chart data
		var data = {
			labels: ['寻物启事', '招领启事'],
			datasets: [{
				data: [lostCount, foundCount],
				backgroundColor: [
					'#FFC107', // Yellow for lost
					'#2196F3'  // Blue for found
				],
				borderColor: [
					'#FFC107',
					'#2196F3'
				],
				borderWidth: 1
			}]
		};
		
		// Create chart
		var ctx = document.getElementById('statisticsChart').getContext('2d');
		var statisticsChart = new Chart(ctx, {
			type: 'pie',
			data: data,
			options: {
				responsive: true,
				maintainAspectRatio: false,
				plugins: {
					legend: {
						position: 'bottom',
					},
					title: {
						display: true,
						text: '寻物启事与招领启事数量对比',
						font: {
							size: 16
						}
					}
				}
			}
		});
	</script>
</body>
</html>