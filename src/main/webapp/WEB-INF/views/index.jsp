<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<title>iFlin爬虫控制终端</title>
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
</head>
<body>
<jsp:include page="/header.jsp" flush="true"/>
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
<jsp:include page="/footer.jsp" flush="true"/>
</body>
</html>