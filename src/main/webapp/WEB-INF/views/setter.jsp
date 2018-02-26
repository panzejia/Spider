<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/header.jsp" flush="true" />

<div class="templatemo-content-wrapper">
	<div class="templatemo-content" id="content">
		<ol class="breadcrumb">
			<li><a href="index.html">SpiderPanel</a></li>
			<li class="active">Setter</li>
		</ol>
		<h1>设置</h1>
		<a class="selectArea selectArea_select" onclick="getWordSetterPage()"><i
			class="fa fa-book" style="font-size: 22px">词汇设置</i></a> <a
			class="selectArea selectArea_select" href="#"
			onclick="getAboutPage()"><i class="fa fa-heartbeat"
			style="font-size: 22px">关于</i></a> <a
			class="selectArea selectArea_select" href="about.htm"><i
			class="fa fa-heartbeat" style="font-size: 22px">使用说明</i></a>
	</div>
</div>
<jsp:include page="/footer.jsp" flush="true" />
<script>
$("#articlemanager").removeClass("active");
$("#analysis").removeClass("active");
$("#spiderList").removeClass("active");
$("#setter").addClass("active");
$("#home").removeClass("active");
$("#wordCloud").removeClass("active");
$("#user").removeClass("active");
</script>