<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<title>iFlin后台管理系统</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/css/templatemo_main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/css/admin.css">
</head>
<body>
	<div class="navbar navbar-inverse" role="navigation" style="background-color: #4eb4ff; border-color: #4eb4ff; color: #fff;">
		<div class="navbar-header">
			<div class="logo">
				<h1>iFlin后台管理系统</h1>
				<p>当前登录的用户为：${currentUser}</p>
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
				<li id="home" class="active"><a href="${pageContext.request.contextPath}/index"><i
						class="fa fa-home"></i>Home</a></li>
				<li id="spiderList"><a href="${pageContext.request.contextPath}/spider"><i
						class="fa fa-database"></i>爬虫管理</a></li>
				<li class="sub" id="articlemanager"><a href="javascript:;"><i
						class="fa fa-cubes"></i>文章管理
						<div class="pull-right">
							<span class="caret"></span>
						</div></a>
					<ul class="templatemo-submenu">
						<li><a href="${pageContext.request.contextPath}/article/inquire?range=全国&sort=StopTime">文章查询</a></li>
						<li><a href="#">文章管理</a></li>
					</ul></li>
				<li id="user"><a href="${pageContext.request.contextPath}/usermanager"><i class="fa fa-cubes"></i>用户管理</a></li>
				<li class="sub" id="analysis"><a href="javascript:;"><i
						class="fa fa-cubes"></i>数据分析
						<div class="pull-right">
							<span class="caret"></span>
						</div></a>
					<ul class="templatemo-submenu">
						<li><a href="${pageContext.request.contextPath}/analysis/articles">文章分析</a></li>
						<li><a href="#">爬虫分析</a></li>
						<li><a href="#">系统分析</a></li>
					</ul></li>
				<li id="setter"><a href="${pageContext.request.contextPath}/setter"><i class="fa fa-cog"></i>设置</a></li>
				<li><a href="javascript:;" data-toggle="modal"
					data-target="#confirmModal"><i class="fa fa-sign-out"></i>退出</a></li>
			</ul>
		</div>
	</div>
	<!--/.navbar-collapse -->