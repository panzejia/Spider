<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/header.jsp" flush="true" />
<script src="${pageContext.request.contextPath}/js/chart/selfChart.js"></script>
<script
	src="${pageContext.request.contextPath}/js/chart/Chart.bundle.js"></script>
<script src="${pageContext.request.contextPath}/js/chart/utils.js"></script>
<div class="templatemo-content-wrapper">
	<div class="templatemo-content" id="content">
		<ol class="breadcrumb">
			<li><a href="index.html">SpiderPanel</a></li>
			<li class="active">Overview</li>
		</ol>
		<h1>文章分析</h1>
		<div class="templatemo-panels">
			<div class="row">
				<script>
					var dataList = new Array()
					window.onload = function() {
						var map = {
							day : 7,
							source : "all"
						}
						var url = "${pageContext.request.contextPath}/analysis/article"
						getCrawlArticle(url, map, 'canvas');
						var map2 = {
							day : 30,
							source : "all"
						};
						getCrawlArticle(url, map2, 'canvas2');
					};
				</script>
				<div class="col-md-6 col-sm-6 margin-bottom-30">
					<div class="panel panel-success">
						<div class="panel-heading">最近7天趋势</div>
						<div style="width: 100%;">

							<canvas id="canvas"></canvas>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6 margin-bottom-30">
					<div class="panel panel-success">
						<div class="panel-heading">最近30天趋势</div>
						<div style="width: 100%;">
							<canvas id="canvas2"></canvas>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/footer.jsp" flush="true" />
</body>
</html>
<script>
	$("#articlemanager").removeClass("active");
	$("#analysis").addClass("active");
	$("#spiderList").removeClass("active");
	$("#setter").removeClass("active");
	$("#home").removeClass("active");
	$("#wordCloud").removeClass("active");
	$("#user").removeClass("active");
</script>