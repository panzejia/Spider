<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
								<div id="echarts" style="width: 100%;">
								<script
								src="${pageContext.request.contextPath}/js/chart/selfChart.js"></script>
							<script src="${pageContext.request.contextPath}/js/chart/Chart.bundle.js"></script>
							<script src="${pageContext.request.contextPath}/js/chart/utils.js"></script>
							<script>
								window.onload = function() {
									var url = "${pageContext.request.contextPath}/analysis/index";
									getIndexChart(url)
								};
							</script>
							<canvas id="canvas"></canvas>
								</div>
							</div>
							<span class="btn btn-success"><a
								href="analysis/articles">More Charts</a></span>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-sm-12 margin-bottom-30">
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