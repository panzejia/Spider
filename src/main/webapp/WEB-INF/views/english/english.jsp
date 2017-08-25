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
				<a class="btn-download" href="#" onclick="getEnglishWords(WordCloudContent,'kaoyan')">点击计算考研</a>
			</div>
		</form>
	</div>
</body>
</html>