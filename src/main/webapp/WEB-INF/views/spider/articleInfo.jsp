<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/result.css" />
<title>Insert title here</title>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="index.html">Home</a></li>
		<li><a href="#" onclick="getSpiderList()">Spiders</a></li>
		<li class="active">Article</li>
	</ol>
	<div class="resultArea" style=" text-align:center;">
		<div style="color:#000000;font-size:18px;" >
			${article.getTitle()}
		</div>
		<div style="color:#505050;font-size:15px;" >
			发布时间：${article.getStarttime()}
		</div>
		<div style="color:#505050;font-size:15px;" >
			截止时间：${article.getStoptime()}
		</div>
		<div class="article" style="font-family: 微软雅黑;font-size: 16px; text-align:left;">
			${article.getContent()}
		</div>
	</div>
</body>
</html>