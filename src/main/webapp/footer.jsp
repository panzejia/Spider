<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<!-- Modal -->
		<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Are you sure you
							want to sign out?</h4>
					</div>
					<div class="modal-footer">
						<a href="sign-in.html" class="btn btn-primary">Yes</a>
						<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					</div>
				</div>
			</div>
		</div>
		<footer class="templatemo-footer" style="background-color: #4eb4ff; border-color: #4eb4ff; color: #fff;">
			<div class="templatemo-copyright">
				<p>
					Copyright &copy; 2017 <a href="http://www.iflin.cn/"
						title="panzejia" target="_blank">沉記本</a>
				</p>
			</div>
		</footer>
	</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/templatemo_script.js"></script>
<script src="${pageContext.request.contextPath}/js/admin/admin.js"></script>
</body>
</html>

