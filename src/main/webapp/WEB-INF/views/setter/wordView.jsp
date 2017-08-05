<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/admin/css/admin.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="addWord">
		<p>当前是：${tagChinese}</p>
		<div class="wordArea">
			<input type="text" class="form-control" name="inputWord"
				id="inputWord" placeholder="輸入詞匯">
		</div>
		<div class="wordArea">
			<button type="button" class="btn btn-primary"
				onclick="doSaveWord('${tag}')">添加</button>
		</div>
	</form>
	<div class="row">
		<div class="col-md-12">
			<div class="table-responsive" style="">
				<h4 class="margin-bottom-15">Word Table</h4>
				<table class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th>#</th>
							<th>Word</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<!-- 爬蟲循環開始 -->
						<c:forEach items="${words}" var="word">
							<tr>
								<td>${word.getWordId()}</td>
								<td>${word.getWord()}</td>
								<td><a href="del${tag}?wordId=${word.getWordId()}"
									class="btn btn-link">Delete</a></td>
							</tr>
						</c:forEach>
						<!-- 爬蟲循環結束 -->
					</tbody>
				</table>
			</div>
			<ul class="pagination pull-right">
				<li class="disabled"><a href="#">«</a></li>
				<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
				<li><a href="#">2 <span class="sr-only">(current)</span></a></li>
				<li><a href="#">3 <span class="sr-only">(current)</span></a></li>
				<li><a href="#">4 <span class="sr-only">(current)</span></a></li>
				<li><a href="#">5 <span class="sr-only">(current)</span></a></li>
				<li><a href="#">»</a></li>
			</ul>
		</div>
	</div>
</body>
</html>