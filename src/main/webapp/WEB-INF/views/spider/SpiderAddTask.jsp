<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/admin/css/spider.css" />

<title>Insert title here</title>
</head>
<body>
			<!-- 右边内容Start-->
			<form id="addForm" class="resultrightcenter">
				<p>(CSS选择器与Xpath选择器二选一)请不要使用含有#的选择器语句</p>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>任务名称或来源名称：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="textareastyle" name="source" id="source"  type="text"placeholder="任务名称或来源名称（如广东省教育厅）" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>列表页链接：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="textareastyle" name="liUrl" id="liUrl"  type="text"placeholder="文章列表链接" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>列表页区域CSS：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="textareastyle" name="cssSeletor" id="cssSeletor" type="text"placeholder="" ></textarea></div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>列表页区域Xpath：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="textareastyle" name="xpath" id="xpath" type="text"placeholder="" ></textarea></div>
				</div>
				<div class="textarea">
					<button class="button inputbutton" type="button" onclick="doPreview(liUrl,'body','0');">预览整个页面</button>
					<button class="button inputbutton" type="button" onclick="doPreview(liUrl,cssSeletor,xpath);">预览列表区域</button>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>正文测试链接：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="textareastyle" name="articleUrl" id="articleUrl" type="text"placeholder="用来测试所填的选择器" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>标题区域CSS：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="textareastyle" name="titleCSS" id="titleCSS" type="text"placeholder="" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>标题区域Xpath：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="textareastyle" name="titleXpath" id="titleXpath" type="text"placeholder="" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>发布时间区域CSS：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="textareastyle" name="starttimeCSS" id="starttimeCSS" type="text"placeholder="" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>发布时间区域Xpath：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="textareastyle" name="starttimeXpath" id="starttimeXpath" type="text"placeholder="" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>正文区域CSS：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="textareastyle" name="contentCSS" id="contentCSS" type="text"placeholder="" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>正文区域Xpath：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="textareastyle" name="contentXpath" id="contentXpath" type="text"placeholder="" ></textarea></div>
				</div>
				<div class="textarea">
					<button class="button inputbutton" type="button" onclick="doPreview(articleUrl,titleCSS,titleXpath);"  >预览标题</button>
					<button class="button inputbutton" type="button" onclick="doPreview(articleUrl,starttimeCSS,starttimeXpath);">预览发布时间</button>
					<button class="button inputbutton" type="button" onclick="doPreview(articleUrl,contentCSS,contentXpath);" >预览正文</button>
				</div>
				<div class="textarea">
					<button type="button" class="button inputbutton" onclick="doSave();">提交</button>
				</div>
			</div>
		</form>
		<!-- 右边内容End-->
</body>
</html>