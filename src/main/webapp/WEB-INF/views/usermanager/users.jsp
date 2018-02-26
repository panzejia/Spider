<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<jsp:include page="/header.jsp" flush="true" />

<div class="templatemo-content-wrapper">
	<div class="templatemo-content" id="content">
		<ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
			<li class="active">用户列表</li>
		</ol>
		<div class="row">
			<div class="col-md-12">
				<div class="table-responsive">
					<table class="table table-striped table-hover table-bordered">
						<thead>
							<tr>
								<th>#</th>
								<th>邮箱</th>
								<th>姓名</th>
								<th>工作单位</th>
								<th>激活状态</th>
								<th>手机</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<!-- 爬蟲循環開始 -->
							<c:forEach items="${users}" var="user">
								<tr>
									<td>${user.getId()}</td>
									<td><a href="${pageContext.request.contextPath}/user/users?id=${user.getId()}">${user.getMail()}</a></td>
									<td>${user.getName()}</td>
									<td>${user.getWorkspace()}</td>
									<td><p >${user.getStatus()}</p></td>
									<td><p>${user.getPhone()}</p></td>
									<td><a href=""
										class="btn btn-link">Delete</a></td>
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
$("#articlemanager").removeClass("active");
$("#analysis").removeClass("active");
$("#spiderList").removeClass("active");
$("#setter").removeClass("active");
$("#home").removeClass("active");
$("#wordCloud").removeClass("active");
$("#user").addClass("active");
</script>