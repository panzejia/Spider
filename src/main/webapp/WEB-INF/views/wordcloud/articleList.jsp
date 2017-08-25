<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="index.html">Home</a></li>
		<li class="active"><a href="#" onclick="doWordCloud()">词云</a></li>
	</ol>
	<div class="row" style="margin:0 auto">
		<h4 class="margin-bottom-15">自定义查询词频</h4>
		<form id="WordCloudByUser">
			<textarea autocomplete="off" style="width:100%;height:100px;" name="WordCloudContent" id="WordCloudContent" type="text" placeholder="输入要计算的内容"></textarea>
			<div class="wordCloudButtonArea">
				<a class="btn-download" href="#" onclick="doWordCloudTemp(WordCloudContent,'chinese')">点击计算中文</a>
				<a class="btn-download" href="#" onclick="doWordCloudTemp(WordCloudContent,'english')">点击计算英文</a>
			</div>
		</form>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="btn-group pull-right" id="templatemo_sort_btn">
				<button type="button" class="btn btn-default">Sort by</button>
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					<span class="caret"></span> <span class="sr-only">Toggle
						Dropdown</span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#">任务名称</a></li>
					<li><a href="#">列表页链接</a></li>
					<li><a href="#">下一次运行时间</a></li>
				</ul>
			</div>
			<div class="table-responsive">
				<h4 class="margin-bottom-15">查看词频文章列表</h4>
				<table class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th>#</th>
							<th>文章名称</th>
							<th>文章链接</th>
							<th>词云</th>
						</tr>
					</thead>
					<tbody>
						<!-- 循環開始 -->
						<c:forEach items="${articles}" var="article">
							<tr>
								<td>${article.getArticleId()}</td>
								<td><a href="#"
									onclick="getArticleInfo('${article.getArticleId()}')">${article.getTitle()}</a></td>
								<td>${article.getUrl()}</td>
								<td><a href="#"
									onclick="doWordCloudByArticle('${article.getArticleId()}')"
									class="btn btn-default">查看</a></td>
							</tr>
						</c:forEach>
						<!-- 循環結束 -->
					</tbody>
				</table>
			</div>
			<ul class="pagination pull-right">
				<li class="disabled"><a href="#">«</a></li>
				<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
				<li><a href="#">2 <span class="sr-only">(current)</span></a></li>
				<li><a href="#">3 <span class="sr-only">(current)</span></a></li>
				<li><a href="#">4 <span class="sr-only">(current)</span></a></li>
				<li><a href="#">5 <span class="sr-only">(current)</span></a></li>
				<li><a href="#">»</a></li>
			</ul>
		</div>
	</div>
</body>
</html>