<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>

<head>
    <title>Line Chart</title>
    <script src="http://www.chartjs.org/dist/2.7.1/Chart.bundle.js"></script>
    <script src="http://www.chartjs.org/samples/latest/utils.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>
    <script src="${pageContext.request.contextPath}/js/admin/selfChart.js"></script>
    <style>
    canvas{
        -moz-user-select: none;
        -webkit-user-select: none;
        -ms-user-select: none;
    }
    </style>
</head>

<body>
    <div style="width:75%;">
        <canvas id="canvas"></canvas>
    </div>
    <script>
    	var dataList = new Array()
        window.onload = function() {
        	var day = 7;
        	var source = "all";
        	var map ={day : day,source:source}
        	var url = "http://localhost:8080/KTBLControlTerminal/analysis/article"
        	getCrawlArticle(url,map)
        };

    </script>
</body>

</html>
