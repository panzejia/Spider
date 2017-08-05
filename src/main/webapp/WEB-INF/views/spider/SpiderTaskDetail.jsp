<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 右边内容Start-->
	<div class="resultrightcenter">
		<div class="detail">
			<div class="title">
				<a>任务名称：</a>
			</div>
			<div class="info">
				<a>${source}</a>
			</div>
			<div class="title">
				<a>列表页链接：</a>
			</div>
			<div class="info">
				<a>${url}</a>
			</div>
			<div class="title">
				<a>列表页区域CSS：</a>
			</div>
			<div class="info">
				<a>${cssSeletor}</a>
			</div>
			<div class="title">
				<a>列表页区域Xpath：</a>
			</div>
			<div class="info">
				<a>${xpath}</a>
			</div>
			<div class="title">
				<a>标题区域CSS：</a>
			</div>
			<div class="info">
				<a>${titleCSS}</a>
			</div>
			<div class="title">
				<a>标题区域Xpath：</a>
			</div>
			<div class="info">
				<a>${titleXpath}</a>
			</div>
			<div class="title">
				<a>发布时间区域CSS：</a>
			</div>
			<div class="info">
				<a>${starttimeCSS}</a>
			</div>
			<div class="title">
				<a>发布时间区域Xpath：</a>
			</div>
			<div class="info">
				<a>${starttimeXpath}</a>
			</div>
			<div class="title">
				<a>正文区域CSS：</a>
			</div>
			<div class="info">
				<a>${contentCSS}</a>
			</div>
			<div class="title">
				<a>正文区域Xpath：</a>
			</div>
			<div class="info">
				<a>${contentXpath}</a>
			</div>
			<a class="button inputbutton" href="javascript:void(0)"
				onclick="doChangePage('${taskId}')">修改信息</a> <a
				class="button inputbutton" href="delTask?taskId=${taskId}">刪除任務</a>
		</div>

		<div class="taskdetail">
			<div>
				<a>昨日共爬取X条</a> <a>下次运行时间：${nextTime}</a>
			</div>
		</div>
	</div>
	<!--右边内容End-->
</body>
</html>