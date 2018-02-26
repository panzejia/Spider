<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/header.jsp" flush="true" />

<div class="templatemo-content-wrapper">
	<div class="templatemo-content" id="content">
		<ol class="breadcrumb">
			<li><a href="index">Home</a></li>
			<li class="active"><a href="${pageContext.request.contextPath}/usermanager">用户列表</a></li>
			<li class="active">用户</li>
		</ol>
		<div class="col-md-8">
			<ul class="nav nav-tabs" style="margin-bottom: 15px;">
				<li class="active"><a href="#UserInfo" data-toggle="tab">用户资料</a></li>
				<li class=""><a href="#UserLoginInfo" data-toggle="tab">登录信息</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="UserInfo">
					<div style="text-align: center">${requestScope.status}</div>
					<div id="basic" class="col-md-12">
						<h4 style="color: #428bca;">基本资料</h4>
					</div>
					<form id="userextrainfo">
						<div class="col-md-12" style="margin-top: 30px;">
							<div class="form-horizontal">
								<div class="form-group">
									<label class="col-md-2 col-md-offset-2 control-label"
										for="Employer">姓名</label>
									<div class="col-md-6">
										<div class="input-medium">
											<div class="input-group">
												<input class="form-control input-medium" id="Email"
													name="Email" data-bind="value:Email" type="text"
													placeholder="${user.getName()}"> <span
													class="input-group-addon"> <i
													class="icon-envelope-alt"></i>

												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail5"
										class="col-md-2 col-md-offset-2 control-label">用户性别</label>
									<div class="col-md-4">
										<label class="radio-inline"> <input type="radio"
											id="SexMale" name="Sex" value="male" data-bind="checked:Sex">
											先生
										</label> <label class="radio-inline"> <input type="radio"
											id="SexFemale" name="Sex" value="female"
											data-bind="checked:Sex"> 女士
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 col-md-offset-2 control-label"
										for="Employer">职称</label>
									<div class="col-md-6">
										<div class="input-medium">
											<div class="input-group">
												<input class="form-control input-medium" id="Employer"
													name="Employer" data-bind="value:Employer" type="text"
													placeholder="职称"> <span class="input-group-addon">
													<i class="icon-home"></i>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 col-md-offset-2 control-label"
										for="Employer">工作地点</label>
									<div class="col-md-6">
										<div class="input-medium">
											<div class="input-group">
												<input class="form-control input-medium" id="Employer"
													name="Employer" data-bind="value:Employer" type="text"
													placeholder="工作地点"> <span class="input-group-addon">
													<i class="icon-home"></i>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 col-md-offset-2 control-label"
										for="Employer">现工作单位</label>
									<div class="col-md-6">
										<div class="input-medium">
											<div class="input-group">
												<input class="form-control input-medium" id="Employer"
													name="Employer" data-bind="value:Employer" type="text"
													placeholder="${user.getWorkspace()}"> <span
													class="input-group-addon"> <i class="icon-home"></i>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 col-md-offset-2 control-label"
										for="Employer">曾工作单位</label>
									<div class="col-md-6">
										<div class="input-medium">
											<div class="input-group">
												<input class="form-control input-medium" id="Employer"
													name="Employer" data-bind="value:Employer" type="text"
													placeholder="工作单位"> <span class="input-group-addon">
													<i class="icon-home"></i>
												</span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<h4 style="color: #428bca;">联系信息</h4>
							<hr>
							<div class="form-horizontal">
								<div class="form-group">
									<label class="col-md-2 col-md-offset-2 control-label"
										for="Email">登录邮箱</label>
									<div class="col-md-6">
										<div class="input-medium">
											<div class="input-group">
												<input class="form-control input-medium" id="Email"
													name="Email" data-bind="value:Email" type="text"
													placeholder="${user.getMail()}"> <span
													class="input-group-addon"> <i
													class="icon-envelope-alt"></i>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 col-md-offset-2 control-label"
										for="Phone">联系电话</label>
									<div class="col-md-4">
										<div class="input-medium">
											<div class="input-group">
												<input class="form-control input-medium" id="Phone"
													name="Phone" data-bind="value:Phone" type="text"
													placeholder="${user.getPhone()}"> <span
													class="input-group-addon"> <i class="icon-phone"></i>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 col-md-offset-2 control-label"
										for="Email">研究成果</label>
									<div class="col-md-6">
										<div class="input-medium">
											<div class="input-group">
												<textarea class="form-control input-medium" id="Email"
													name="Email" data-bind="value:Email" type="text"
													placeholder="${user.getInfo()}"></textarea>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="well" style="text-align: center">
								<button id="userinfosubmit" type="button"
									class="btn btn-primary btn-lg" onclick="UpdateUserInfo();">保存</button>
							</div>
						</div>
					</form>
				</div>
				<div class="tab-pane" id="UserLoginInfo">
					<form id="changepasswordform" class="form-horizontal" role="form">
						<div class="form-group">
							<label for="password_current" class="col-sm-2 control-label">当前密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control"
									id="password_current" name="password_current"
									placeholder="当前密码">
							</div>
						</div>
						<div class="form-group">
							<label for="password_new" class="col-sm-2 control-label">输入新密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="password_new"
									name="password_new" placeholder="输入新密码">
							</div>
						</div>
						<div class="form-group">
							<label for="password_confirm" class="col-sm-2 control-label">再输一遍</label>
							<div class="col-sm-10">
								<input type="password" class="form-control"
									id="password_confirm" name="password_confirm"
									placeholder="再输一遍新密码">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10" id="divchangepassword">
								<a id="btnchangepassword" href="#"
									class="btn btn-primary btn-lg" onclick="ChangePassword()">确认修改</a>
							</div>
						</div>
					</form>
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