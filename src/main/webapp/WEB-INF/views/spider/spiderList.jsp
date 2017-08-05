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
		<li class="active">Spiders</li>
	</ol>
	<div class="row margin-bottom-30">
		<div class="col-md-12">
			<ul class="nav nav-pills">
				<li class="active"><a href="#" onclick="getRight()">添加任务</a></li>
			</ul>
		</div>
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
				<h4 class="margin-bottom-15">New Users Table</h4>
				<table class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th>#</th>
							<th>任务名称</th>
							<th>列表页链接</th>
							<th>下一次运行时间</th>
							<th>Email</th>
							<th>Edit</th>
							<th>Status</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<!-- 爬蟲循環開始 -->
						<c:forEach items="${spiders}" var="spider">
							<tr>
								<td>${spider.getTaskId()}</td>
								<td>${spider.getSource()}</td>
								<td>${spider.getUrl()}</td>
								<td>${spider.getNextTime()}</td>
								<td>iflin.cn</td>
								<td><a href="#"
									onclick="doChangePage('${spider.getTaskId()}')"
									class="btn btn-default">Edit</a></td>
								<td>Action</td>
								<td><a href="#" class="btn btn-link">Delete</a></td>
							</tr>
						</c:forEach>
						<!-- 爬蟲循環結束 -->
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