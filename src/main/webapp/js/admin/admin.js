var randomScalingFactor = function() {
	return Math.round(Math.random() * 100)
};
var lineChartData = {
	labels : [ "January", "February", "March", "April", "May", "June", "July" ],
	datasets : [
			{
				label : "My First dataset",
				fillColor : "rgba(220,220,220,0.2)",
				strokeColor : "rgba(220,220,220,1)",
				pointColor : "rgba(220,220,220,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(220,220,220,1)",
				data : [ randomScalingFactor(), randomScalingFactor(),
						randomScalingFactor(), randomScalingFactor(),
						randomScalingFactor(), randomScalingFactor(),
						randomScalingFactor() ]
			},
			{
				label : "My Second dataset",
				fillColor : "rgba(151,187,205,0.2)",
				strokeColor : "rgba(151,187,205,1)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(151,187,205,1)",
				data : [ randomScalingFactor(), randomScalingFactor(),
						randomScalingFactor(), randomScalingFactor(),
						randomScalingFactor(), randomScalingFactor(),
						randomScalingFactor() ]
			} ]

}

window.onload = function() {
	var ctx_line = document.getElementById("templatemo-line-chart").getContext(
			"2d");
	window.myLine = new Chart(ctx_line).Line(lineChartData, {
		responsive : true
	});
};

$('#myTab a').click(function(e) {
	e.preventDefault();
	$(this).tab('show');
});

$('#loading-example-btn').click(function() {
	var btn = $(this);
	btn.button('loading');
	// $.ajax(...).always(function () {
	// btn.button('reset');
	// });
});

/** 自定义内容从此开始 */
var XMLHttpReq = false;
// 创建XMLHttpRequest对象
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) { // Mozilla 浏览器
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
// 发送请求函数
function sendRequest(url) {
	createXMLHttpRequest();
	XMLHttpReq.open("GET", url, true);
	XMLHttpReq.onreadystatechange = processResponse;// 指定响应函数
	XMLHttpReq.send(null); // 发送请求
}
// 处理返回信息函数
function processResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var result = XMLHttpReq.responseText;
			document.getElementById("content").innerHTML = result;
		} else { // 页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
// 发送请求函数
function sendSetterRequest(url) {
	createXMLHttpRequest();
	XMLHttpReq.open("GET", url, true);
	XMLHttpReq.onreadystatechange = processSetterResponse;// 指定响应函数
	XMLHttpReq.send(null); // 发送请求
}
// 处理返回信息函数
function processSetterResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var result = XMLHttpReq.responseText;
			document.getElementById("setterArea").innerHTML = result;
		} else { // 页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
// 修改任务状态
function changeTaskStatus(taskId, status) {
	var url = "changeTaskStatus?taskId=" + taskId + "&status=" + status;
	sendRequest(url);
}

// 获取设置界面
function getSetterPage() {
	var url = "getSetter";
	$("#setter").addClass("active")
	$("#spiderList").removeClass("active")
	$("#home").removeClass("active")
	sendRequest(url);
}
// 获取词汇设置界面
function getWordSetterPage() {
	var url = "getWordSetterPage";
	sendRequest(url);
}
// 获取停用词界面
function getStopWordView() {
	var url = "getStopWordView";
	sendSetterRequest(url);
}
// 获取选择词词界面
function getSelectWordView() {
	var url = "getSelectWordView";
	sendSetterRequest(url);
}
// 保存词汇
function doSaveWord(tag) {
	var newurl = "save" + tag;
	$.ajax({
		type : "POST",
		dataType : "html",
		url : newurl,
		data : $('#addWord').serialize(),
		success : function(data) {
			var strresult = data;
			alert(strresult);
		},
		error : function(data) {
			alert("保存失败");
		}
	});
}
// 获取爬虫列表
function getSpiderList() {
	var url = "getSpiderList";
	$("#spiderList").addClass("active")
	$("#setter").removeClass("active")
	$("#home").removeClass("active")
	sendRequest(url);
}
// 获取添加爬虫页面
function getRight() {
	var url = "addTask";
	sendRequest(url);
}

// 获取某个爬虫信息
function getTaskName(taskId) {
	var url = "getDetail?taskId=" + taskId;
	sendRequest(url);
}
// 通过来源名称获取爬虫已爬文章
function getSpiderArticle(source) {
	var url = "getSpiderArticle?source=" + source;
	sendRequest(url);
}
// 获取爬虫已爬文章内容
function getArticleInfo(articleId) {
	var url = "getArticleInfo?articleId=" + articleId;
	sendRequest(url);
}
// 获取预览页面
function doPreview(articleUrl,cssSeletor,xpath){ 
	createXMLHttpRequest();
	var url =$(articleUrl).val();
	if(cssSeletor=="body"){
		url = "getPageCode?url="+url+"&cssSeletor="+cssSeletor+"&xpath="+xpath;
		window.open(url, '_blank');
		return;
	}
	var css =$(cssSeletor).val();
	var xpath1 =$(xpath).val();
	url = "getPageCode?url="+url+"&cssSeletor="+css+"&xpath="+xpath1;
	window.open(url, '_blank');
}

// 保存爬虫信息
function doSave() {
	$.ajax({
		type : "POST",
		dataType : "html",
		url : "doSave",
		data : $('#addForm').serialize(),
		success : function(data) {
			var strresult = data;
			document.getElementById("content").innerHTML = strresult;
		},
		error : function(data) {
			alert("保存失败");
			sendRequest("addTask");
		}
	});
}
// 保存修改爬虫信息页面
function doChangeInfo(taskId) {
	$.ajax({
		type : "POST",
		dataType : "html",
		url : "changeInfo",
		data : $('#changeForm').serialize(),
		success : function(data) {
			var strresult = data;
			document.getElementById("content").innerHTML = strresult;
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