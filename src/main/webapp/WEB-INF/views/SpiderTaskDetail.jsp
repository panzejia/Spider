<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
			<!-- 右边内容Start-->
			<div class="resultrightcenter">
				<div class="detail">
					<div class="title">
						<a>任务名称：</a>
					</div>
					<div class="info">
						<a>${source}</a>
					</div>
					<div class="title">
						<a>列表页链接：</a>
					</div>
					<div class="info">
						<a>${url}</a>
					</div>
					<div class="title">
						<a>列表页区域CSS：</a>
					</div>
					<div class="info">
						<a>${cssSeletor}</a>
					</div>
					<div class="title">
						<a>列表页区域Xpath：</a>
					</div>
					<div class="info">
						<a>${xpath}</a>
					</div>
					<div class="title">
						<a>标题区域CSS：</a>
					</div>
					<div class="info">
						<a>${titleCSS}</a>
					</div>
					<div class="title">
						<a>标题区域Xpath：</a>
					</div>
					<div class="info">
						<a>${titleXpath}</a>
					</div>
					<div class="title">
						<a>发布时间区域CSS：</a>
					</div>
					<div class="info">
						<a>${starttimeCSS}</a>
					</div>
					<div class="title">
						<a>发布时间区域Xpath：</a>
					</div>
					<div class="info">
						<a>${starttimeXpath}</a>
					</div>
					<div class="title">
						<a>正文区域CSS：</a>
					</div>
					<div class="info">
						<a>${contentCSS}</a>
					</div>
					<div class="title">
						<a>正文区域Xpath：</a>
					</div>
					<div class="info">
						<a>${contentXpath}</a>
					</div>
					<a class="button inputbutton" href="javascript:void(0)" onclick="getChangeInfo();">修改信息</a>
				</div>
				<div class="taskdetail">
					<div>
						<a>昨日共爬取X条</a>
						<a>当前爬虫状态：停止</a>
					</div>
					<div>
						<div class="taskinfo">
						<a>爬取时间</a>
						</div>
						<div class="taskinfo">
							<a>爬取链接</a>
						</div>
						<div class="taskinfo">
							<a>文章标题</a>
						</div>
						<div class="taskinfo">
							<a>爬取状态</a>
						</div>
					</div>
					<div class="taskrow">
						<div class="taskinfo">
							<a>2017-7-24</a>
						</div>
						<div class="taskinfo">
							<a>http://www.gdpplgopss.gov.cn/tzgg/201706/t20170609_845214.htm</a>
						</div>
						<div class="taskinfo">
							<a>广东省哲学社会科学“十三五”规划2017年度项目申报通知</a>
						</div>
						<div class="taskinfo">
							<a>成功</a>
						</div>
					</div>
			</div>
			<!--右边内容End-->
</body>
</html>