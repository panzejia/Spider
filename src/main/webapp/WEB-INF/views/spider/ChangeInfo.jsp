<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/spider/spiderpanel.css" />
<title>Insert title here</title>
</head>
<body>
	<!-- 右边内容Start-->
	<form id="changeForm"  class="resultrightcenter">
		<div class="textarea" style="display:none">
			<div
				style="width: 30%; float: left; text-align: center; height: 48px; line-height: 48px">
				<a>任务ID：</a>
			</div>
			<div style="width: 70%; float: left">
				<input  autocomplete="off" class="textareastyle" name="taskId"
					id="taskId" type="text" value="${taskId}"></input >
			</div>
		</div>
		<div class="textarea">
			<div
				style="width: 30%; float: left; text-align: center; height: 48px; line-height: 48px">
				<a>任务名称或来源名称：</a>
			</div>
			<div style="width: 70%; float: left">
				<textarea autocomplete="off" class="textareastyle" name="source"
					id="source" type="text" placeholder="${source}"></textarea>
			</div>
		</div>
		<div class="textarea">
			<div
				style="width: 30%; float: left; text-align: center; height: 48px; line-height: 48px">
				<a>列表页链接：</a>
			</div>
			<div style="width: 70%; float: left">
				<textarea autocomplete="off" class="textareastyle" name="liUrl"
					id="liUrl" type="text" placeholder="${url}"></textarea>
			</div>
		</div>
		<div class="textarea">
			<div
				style="width: 30%; float: left; text-align: center; height: 48px; line-height: 48px">
				<a>列表页区域CSS：</a>
			</div>
			<div style="width: 70%; float: left">
				<textarea autocomplete="off" class="textareastyle" name="cssSeletor"
					id="cssSeletor" type="text" placeholder="${cssSeletor}"></textarea>
			</div>
			<div class="textarea">
				<div
					style="width: 30%; float: left; text-align: center; height: 48px; line-height: 48px">
					<a>列表页区域Xpath：</a>
				</div>
				<div style="width: 70%; float: left">
					<textarea autocomplete="off" class="textareastyle" name="xpath"
						id="xpath" type="text" placeholder="${xpath}"></textarea>
				</div>
			</div>
			<div class="textarea">
				<input class="button inputbutton" type="submit"
					onclick="doPreview(liUrl,'body','0');" value="预览整个页面"> <input
					class="button inputbutton" type="submit"
					onclick="doPreview(liUrl,cssSeletor,xpath);" value="预览列表区域">
			</div>
			<div class="textarea">
				<div
					style="width: 30%; float: left; text-align: center; height: 48px; line-height: 48px">
					<a>正文测试链接：</a>
				</div>
				<div style="width: 70%; float: left">
					<textarea autocomplete="off" class="textareastyle"
						name="articleUrl" id="articleUrl" type="text"
						placeholder="用来测试所填的选择器"></textarea>
				</div>
			</div>
			<div class="textarea">
				<div
					style="width: 30%; float: left; text-align: center; height: 48px; line-height: 48px">
					<a>标题区域CSS：</a>
				</div>
				<div style="width: 70%; float: left">
					<textarea autocomplete="off" class="textareastyle" name="titleCSS"
						id="titleCSS" type="text" placeholder="${titleCSS}"></textarea>
				</div>
			</div>
			<div class="textarea">
				<div
					style="width: 30%; float: left; text-align: center; height: 48px; line-height: 48px">
					<a>标题区域Xpath：</a>
				</div>
				<div style="width: 70%; float: left">
					<textarea autocomplete="off" class="textareastyle"
						name="titleXpath" id="titleXpath" type="text"
						placeholder="${titleXpath}"></textarea>
				</div>
			</div>
			<div class="textarea">
				<div
					style="width: 30%; float: left; text-align: center; height: 48px; line-height: 48px">
					<a>发布时间区域CSS：</a>
				</div>
				<div style="width: 70%; float: left">
					<textarea autocomplete="off" class="textareastyle"
						name="starttimeCSS" id="starttimeCSS" type="text"
						placeholder="${starttimeCSS}<"></textarea>
				</div>
			</div>
			<div class="textarea">
				<div
					style="width: 30%; float: left; text-align: center; height: 48px; line-height: 48px">
					<a>发布时间区域Xpath：</a>
				</div>
				<div style="width: 70%; float: left">
					<textarea autocomplete="off" class="textareastyle"
						name="starttimeXpath" id="starttimeXpath" type="text"
						placeholder="${starttimeXpath}"></textarea>
				</div>
			</div>
			<div class="textarea">
				<div
					style="width: 30%; float: left; text-align: center; height: 48px; line-height: 48px">
					<a>正文区域CSS：</a>
				</div>
				<div style="width: 70%; float: left">
					<textarea autocomplete="off" class="textareastyle"
						name="contentCSS" id="contentCSS" type="text"
						placeholder="${contentCSS}"></textarea>
				</div>
			</div>
			<div class="textarea">
				<div
					style="width: 30%; float: left; text-align: center; height: 48px; line-height: 48px">
					<a>正文区域Xpath：</a>
				</div>
				<div style="width: 70%; float: left">
					<textarea autocomplete="off" class="textareastyle"
						name="contentXpath" id="contentXpath" type="text"
						placeholder="${contentXpath}"></textarea>
				</div>
			</div>
			<div class="textarea">
				<input class="button inputbutton" type="submit"
					onclick="doPreview(articleUrl,titleCSS,titleXpath);" value="预览标题" />
				<input class="button inputbutton" type="submit"
					onclick="doPreview(articleUrl,starttimeCSS,starttimeXpath);"
					value="预览发布时间" /> <input class="button inputbutton" type="submit"
					onclick="doPreview(articleUrl,contentCSS,contentXpath);"
					value="预览正文" />
			</div>
			<div class="textarea">
				<input type="button" class="button inputbutton" value="提交"
					onclick="doChangeInfo();"> 
			</div>
		</div>
	</form>
	<!-- 右边内容End-->
</body>
</html>