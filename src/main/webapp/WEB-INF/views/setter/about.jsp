<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/admin/menu.js"></script>
</head>
<body>
				<div class="menu"id="menu">
					<ul>
						<li><a href="#guide" class="cur">使用説明</a></li>
						<li><a href="#version"class="cur">版本说明</a></li>
						<li><a href="#info" class="cur">关于US</a></li>
					</ul>
				</div>
				<div class="about">
					<a class="version title" id="guide">使用説明</a>
					<p>1.添加自定义爬虫任务</p>
					<p>2.预览CSS及Xpath是否可用</p>
					<p>3.查看、修改、删除爬虫任务</p>
					<p>4.获取截止时间功能</p>
					<p>5.查看某一个爬虫任务所爬取到的文章</p>
					<p>6.修改爬虫任务运行状态</p>
					<p>7.过滤词汇设置：</p>
					<p>(1)停用词设置--用于过滤不相关标题</p>
					<p>(2)选择词设置--两个以上关键词便认为该文章为申报通知</p>
					<a class="version title" id="version">版本说明</a>
					<p><h1>2017/8/7</h1></p>
					<p>1.增加任务状态控制 </p>
					<p>2.增加获取到截止时间，但不是100%</p>
					<p><h1>2017/8/5</h1></p>
					<p>1.可以自定义停用词、选择词。</p>
					<p>2.对已爬取的文章可进行预览。</p>
					<p><h1>2017/7/26</h1></p>
					<p>1.實現在maven中搭建爬蟲平臺</p>
					<p>2.整合了所有流程，可走完一個流程</p>
					<p>3.使用了Quartz 完成對爬蟲的控制</p>
					<p><h1>2017/7/26</h1></p>
					<p>1.實現在maven中搭建爬蟲平臺</p>
					<p>2.整合了所有流程，可走完一個流程</p>
					<p>3.使用了Quartz 完成對爬蟲的控制</p>
					<p><h1>2017/7/26</h1></p>
					<p>1.實現在maven中搭建爬蟲平臺</p>
					<p>2.整合了所有流程，可走完一個流程</p>
					<p>3.使用了Quartz 完成對爬蟲的控制</p>
					<a class="version title" id="info">关于US</a>
					<p>iflin.cn</p>
					
				</div>

</body>
</html>