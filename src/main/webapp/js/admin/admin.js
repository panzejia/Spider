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
function getSpiderList() {
	var url = "getSpiderList";
	$("#spiderList").addClass("active")
	$("#home").removeClass("active")
	sendRequest(url);
}
function getRight() {
	var url = "addTask";
	sendRequest(url);
}
function getTaskName(taskId) {
	var url = "getDetail?taskId=" + taskId;
	sendRequest(url);
}
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
	url = "getPageCode?url=" + url + "&cssSeletor=" + css + "&xpath=" + xpath1;
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
			document.getElementById("content").innerHTML = strresult;
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