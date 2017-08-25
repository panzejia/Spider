<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<title>iFlin爬虫控制终端</title>
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
				<li id="english"><a href="javascript:;" onclick="getEnglish()"><i class="fa fa-users"></i><span
						class="badge pull-right">NEW</span>英语词频</a></li>
				<li id="wordCloud"><a href="javascript:;" onclick="doWordCloud()"><i class="fa fa-users"></i><span
						class="badge pull-right">NEW</span>词云</a></li>
				<li id="setter"><a href="javascript:;"
					onclick="getSetterPage()"><i class="fa fa-cog"></i>设置</a></li>
				<li><a href="javascript:;" data-toggle="modal"
					data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
			</ul>
		</div>
		<!--/.navbar-collapse -->
</body>
</html>