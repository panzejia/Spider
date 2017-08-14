<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<title>iFlin爬虫控制端</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="css/admin/css/templatemo_main.css">
<link rel="stylesheet" href="css/admin/css/admin.css">
<script src="js/admin/jquery.min.js"></script>
<script src="js/admin/bootstrap.min.js"></script>
<script src="js/admin/Chart.min.js"></script>
<script src="js/admin/templatemo_script.js"></script>
<script src="js/admin/admin.js"></script>
<script src="js/wordcloud/d3.js"></script>
<script src="js/wordcloud/d3.layout.cloud.js"></script>
<script src="js/wordcloud/wordCloud.js"></script>
<script src="js/echarts.js"></script>
<script type="text/javascript">
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('echarts'));

	// 指定图表的配置项和数据
	var option = {
		color : [ '#3398DB' ],
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		xAxis : [ {
			type : 'category',
			data : [ '广东社科规划网', '中国高校社会科学信息网', ],
			axisTick : {
				alignWithLabel : true
			}
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			type : 'bar',
			barWidth : '60%',
			data : [ 10, 52 ]
		} ]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
</script>
</head>
<body>
	<div class="navbar navbar-inverse" role="navigation">
		<div class="navbar-header">
			<div class="logo">
				<h1>iFlin爬虫控制端</h1>
				<h1>当前登录的用户为：${currentUser}</h1>
			</div>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
	</div>
	<div class="template-page-wrapper">
		<div class="navbar-collapse collapse templatemo-sidebar">
			<ul class="templatemo-sidebar-menu">
				<li id="home" class="active"><a href="index.html"><i
						class="fa fa-home"></i>Home</a></li>
				<li id="spiderList"><a href="javascript:;"
					onclick="getSpiderList()"><i class="fa fa-database"></i>爬虫</a></li>
				<li id=""><a href="javascript:;"><i class="fa fa-cubes"></i>数据分析</a></li>
				<li id="wordCloud"><a href="javascript:;" onclick="doWordCloud()"><i class="fa fa-users"></i><span
						class="badge pull-right">NEW</span>词云</a></li>
				<li id="setter"><a href="javascript:;"
					onclick="getSetterPage()"><i class="fa fa-cog"></i>设置</a></li>
				<li><a href="javascript:;" data-toggle="modal"
					data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
			</ul>
		</div>
		<!--/.navbar-collapse -->

		<div class="templatemo-content-wrapper">
			<div class="templatemo-content" id="content">
				<ol class="breadcrumb">
					<li><a href="index.html">SpiderPanel</a></li>
					<li class="active">Overview</li>
				</ol>
				<h1>Home</h1>
				<div class="templatemo-panels">
					<div class="row">
						<div class="col-md-6 col-sm-6 margin-bottom-30">
							<div class="panel panel-success">
								<div class="panel-heading">Data Visualization</div>
								<div id="echarts" style="width: 100%; height: 400px;"></div>
							</div>
							<span class="btn btn-success"><a
								href="data-visualization.html">More Charts</a></span>
						</div>
						<div class="col-md-6 col-sm-6 margin-bottom-30">
							<div class="panel panel-primary">
								<div class="panel-heading">New Spider Table</div>
								<div class="panel-body">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>#</th>
												<th>任务名称</th>
												<th>列表页</th>
												<th>下次运行时间</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${spiders}" var="spider">
												<tr>
													<td>${spider.getTaskId()}</td>
													<td>${spider.getSource()}</td>
													<td>${spider.getUrl()}</td>
													<td>${spider.getNextTime()}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<span class="btn btn-primary"><a href="tables.html">See
									Tables</a></span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Are you sure you
							want to sign out?</h4>
					</div>
					<div class="modal-footer">
						<a href="sign-in.html" class="btn btn-primary">Yes</a>
						<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					</div>
				</div>
			</div>
		</div>
		<footer class="templatemo-footer">
			<div class="templatemo-copyright">
				<p>
					Copyright &copy; 2017 <a href="http://www.iflin.cn/"
						title="panzejia" target="_blank">沉記本</a>
				</p>
			</div>
		</footer>
	</div>


</body>
</html>