<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/header.jsp" flush="true" />


<div class="templatemo-content-wrapper">
	<div class="templatemo-content" id="content">
		<ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
			<li class="active">文章查询</li>
		</ol>
		<div class="row">
			<div class="col-md-12">
				<div id="region_detail" class="well well-sm"
					style="margin-bottom: 0;">
					<p style="font-size: 14px;">资助范围</p>
					<ul class="list-inline text-muted" style="margin-bottom: 0">
						<li><a href="inquire?range=全国&sort=StopTime">全国</a> 431</li>
						<li><a href="inquire?range=江苏&sort=StopTime">江苏</a> 203</li>
						<li><a href="inquire?range=广东&sort=StopTime">广东</a> 186</li>
						<li><a href="inquire?range=河北&sort=StopTime">河北</a> 88</li>
						<li><a href="inquire?range=浙江&sort=StopTime">浙江</a> 88</li>
						<li><a href="inquire?range=河南&sort=StopTime">河南</a> 86</li>
						<li><a href="inquire?range=北京&sort=StopTime">北京</a> 85</li>
						<li><a href="inquire?range=上海&sort=StopTime">上海</a> 71</li>
						<li><a href="inquire?range=安徽&sort=StopTime">安徽</a> 66</li>
						<li><a href="inquire?range=山东&sort=StopTime">山东</a> 60</li>
						<li><a href="inquire?range=四川&sort=StopTime">四川</a> 57</li>
						<li><a href="inquire?range=福建&sort=StopTime">福建</a> 57</li>
						<li><a href="inquire?range=山西&sort=StopTime">山西</a> 50</li>
						<li><a href="inquire?range=湖南&sort=StopTime">湖南</a> 44</li>
						<li><a href="inquire?range=天津&sort=StopTime">天津</a> 42</li>
						<li><a href="inquire?range=湖北&sort=StopTime">湖北</a> 42</li>
						<li><a href="inquire?range=陕西&sort=StopTime">陕西</a> 39</li>
						<li><a href="inquire?range=辽宁&sort=StopTime">辽宁</a> 33</li>
						<li><a href="inquire?range=重庆&sort=StopTime">重庆</a> 33</li>
						<li><a href="inquire?range=广西&sort=StopTime">广西</a> 32</li>
						<li><a href="inquire?range=贵州&sort=StopTime">贵州</a> 32</li>
						<li><a href="inquire?range=江西&sort=StopTime">江西</a> 30</li>
						<li><a href="inquire?range=新疆&sort=StopTime">新疆</a> 29</li>
						<li><a href="inquire?range=海南&sort=StopTime">海南</a> 29</li>
						<li><a href="inquire?range=云南&sort=StopTime">云南</a> 28</li>
						<li><a href="inquire?range=黑龙江&sort=StopTime">黑龙江</a> 27</li>
						<li><a href="inquire?range=甘肃&sort=StopTime">甘肃</a> 24</li>
						<li><a href="inquire?range=宁夏&sort=StopTime">宁夏</a> 21</li>
						<li><a href="inquire?range=内蒙古&sort=StopTime">内蒙古</a> 19</li>
						<li><a href="inquire?range=青海&sort=StopTime">青海</a> 18</li>
						<li><a href="inquire?range=吉林&sort=StopTime">吉林</a> 12</li>
					</ul>
					<p style="font-size: 14px;">排序方法</p>
					<ul class="list-inline text-muted" style="margin-bottom: 0">
						<li><a href="inquire?range=全国&sort=StopTime">截止时间</a></li>
						<li><a href="inquire?range=全国&sort=ArticleId">爬取时间</a></li>
					</ul>
				</div>
				<div class="table-responsive">
					<h4 class="margin-bottom-15"></h4>
					<table class="table table-striped table-hover table-bordered">
						<thead>
							<tr>
								<th>#</th>
								<th>标题</th>
								<th>链接</th>
								<th>来源</th>
								<th>地区</th>
								<th>发布时间</th>
								<th>截止时间</th>
								<th>爬取时间</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<!-- 爬蟲循環開始 -->
							<c:forEach items="${articles}" var="article">
								<tr>
									<td>${article.getArticleId()}</td>
									<td>${article.getTitle()}</td>
									<td><a
										href="https://www.iktbl.com/view?articleid=${article.getArticleId()}"
										target="_blank">站内链接 </a><a href="${article.getUrl()}"
										target="_blank">来源链接</a></td>
									<td>${article.getSource()}</td>
									<td>${article.getArea()}</td>
									<td>${article.getStarttime()}</td>
									<td>${article.getStoptime()}</td>
									<td>${article.getCrawlTime()}</td>
									<td><a href="JavaScript:" onclick="doDelArticle(${article.getArticleId()})">Delete</a></td>
								</tr>
							</c:forEach>
							<!-- 爬蟲循環結束 -->
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/footer.jsp" flush="true" />
<script>
	$("#articlemanager").addClass("active");
	$("#analysis").removeClass("active");
	$("#spiderList").removeClass("active");
	$("#setter").removeClass("active");
	$("#home").removeClass("active");
	$("#wordCloud").removeClass("active");
	$("#user").removeClass("active");
</script>