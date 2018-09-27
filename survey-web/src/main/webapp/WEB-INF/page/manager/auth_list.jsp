<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- @include是JSP指令，是在服务器端执行的 --%>
<%@include file="/res_jsp/base.jsp" %>
<script type="text/javascript">
	
	$(function(){
		$("[name=authName]").change(function(){
			
			var authId = this.id;
			var authName = this.value;
			
			var url = "${pageContext.request.contextPath}/manager/auth/updateAuthName";
			var paramData = {"authId":authId,"authName":authName,"time":new Date()};
			var callBack = function(respData){
				var message = respData.message;
				if(message == "success") {
					alert("操作成功！");
				}else{
					alert("操作失败！");
				}
			};
			var type = "json";
			
			$.post(url,paramData,callBack,type);
			
		});
	});
	
</script>
</head>
<body>
	<%@include file="/res_jsp/manager_top.jsp" %>
	
	<div id="mainDiv" class="borderDiv">
		权限列表
		<form action="manager/auth/batchDelete" method="post">
			<table class="dataTable">
				<c:if test="${empty authList }">
					<tr>
						<td>尚未创建权限！</td>
					</tr>
				</c:if>
				<c:if test="${!empty authList }">
					<tr>
						<td>ID</td>
						<td>权限名称</td>
						<td>删除</td>
						<td>分配</td>
					</tr>
					<c:forEach items="${authList }" var="auth">
						<tr>
							<td>${auth.authId }</td>
							<td>
								<input id="${auth.authId }" type="text" name="authName" value="${auth.authName }" class="longInput"/>
							</td>
							<td>
								<input type="checkbox" name="authIdList" value="${auth.authId }" id="checkbox${auth.authId }"/>
								<label for="checkbox${auth.authId }">点我更轻松</label>
							</td>
							<td>
								<a href="manager/auth/toDispatcherUI/${auth.authId }">分配资源</a>
							</td>
						</tr>
					</c:forEach>
					
					<tr>
						<td colspan="4">
							<input type="submit" value="批量删除"/>
						</td>
					</tr>
				</c:if>
			</table>
		</form>
	</div>
	
	<%@include file="/res_jsp/manager_bottom.jsp" %>

</body>
</html>