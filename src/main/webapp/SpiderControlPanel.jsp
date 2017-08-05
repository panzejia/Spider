<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>课题部落 -- 测试版--爬虫控制面板</title>
<link rel="stylesheet" type="text/css" href="css/spider/spiderpanel.css" />
<script language="JavaScript" type="text/JavaScript"
	src="js/jquery-1.6.1.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="js/jquery.validate.min.js"></script>
<script type="text/javascript">
	function getRight() {
		var url = "addTask";
		sendRequest(url);
	}
	function getTaskName(taskId) {
		var url = "getDetail?taskId=" + taskId;
		sendRequest(url);
	}
	var XMLHttpReq = false;
	//创建XMLHttpRequest对象       
	function createXMLHttpRequest() {
		if (window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq = new XMLHttpRequest();
		} else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
	}
	//发送请求函数
	function sendRequest(url) {
		createXMLHttpRequest();
		XMLHttpReq.open("GET", url, true);
		XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		XMLHttpReq.send(null); // 发送请求
	}
	// 处理返回信息函数
	function processResponse() {
		if (XMLHttpReq.readyState == 4) { // 判断对象状态
			if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
				var result = XMLHttpReq.responseText;
				document.getElementById("result").innerHTML = result;
			} else { //页面不正常
				window.alert("您所请求的页面有异常。");
			}
		}
	}
	//
	function doPreview(articleUrl, cssSeletor, xpath) {
		createXMLHttpRequest();
		var url = $(articleUrl).val();
		if (cssSeletor == "body") {
			url = "getPageCode?url=" + url + "&cssSeletor=" + cssSeletor
					+ "&xpath=" + xpath;
			window.open(url, '_blank');
			return;
		}
		var css = $(cssSeletor).val();
		var xpath1 = $(xpath).val();
		url = "getPageCode?url=" + url + "&cssSeletor=" + css + "&xpath="
				+ xpath1;
		window.open(url, '_blank');
	}
	function doSave() {
		$.ajax({
			type : "POST",
			dataType : "html",
			url : "doSave",
			data : $('#addForm').serialize(),
			success : function(data) {
				var strresult = data;
				document.getElementById("result").innerHTML = strresult;
			},
			error : function(data) {
				alert("保存失败");
				sendRequest("addTask");
			}
		});
	}
	function doChangeInfo(taskId) {
		$.ajax({
			type : "POST",
			dataType : "html",
			url : "changeInfo",
			data : $('#changeForm').serialize(),
			success : function(data) {
				var strresult = data;
				document.getElementById("result").innerHTML = strresult;
			},
			error : function(data) {
				alert("保存失败");
			}
		});
	}
	function doChangePage(taskId) {
		var url = "getChangePage?taskId=" + taskId;
		sendRequest(url);
	}
</script>

</head>
<body style="width: 970px; margin: 0 auto;">
	<div class="top">
		<ul>
			<li class="topli"><a href="index.html">主页</a></li>
		</ul>
	</div>
	<div class="pic">
		<a href="index.html"><img src="images/logo/logo.png" width="270"
			height="129"></a>
	</div>
	<div class="resultArea">
		<div class="resultleft" id="user">
			<div class="resultlefttop">
				<a class="button inputbutton" href="javascript:void(0)"
					onclick="getRight();">添加任务</a> <a class="button inputbutton"
					href="#">爬虫设置</a>
			</div>
			<ol class="rectangle-list">
				<c:forEach items="${spiders}" var="spider">
					<li><a href="javascript:void(0)"
						onclick="getTaskName('${spider.getTaskId()}')">${spider.getSource()}</a></li>
				</c:forEach>
			</ol>
		</div>
		<div class="resultright" id="result">
			<h1>欢迎使用iflin.cn爬虫控制端</h1>
		</div>
	</div>
</body>
</html>