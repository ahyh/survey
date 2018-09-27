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
		$("[name=roleName]").change(function(){
			
			var roleId = this.id;
			var roleName = this.value;
			
			var url = "${pageContext.request.contextPath}/manager/role/updateRoleName";
			var paramData = {"roleId":roleId,"roleName":roleName,"time":new Date()};
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
		角色列表
		<form action="manager/role/batchDelete" method="post">
			<table class="dataTable">
				<c:if test="${empty roleList }">
					<tr>
						<td>尚未创建角色！</td>
					</tr>
				</c:if>
				<c:if test="${!empty roleList }">
					<tr>
						<td>ID</td>
						<td>角色名称</td>
						<td>删除</td>
						<td>分配</td>
					</tr>
					<c:forEach items="${roleList }" var="role">
						<tr>
							<td>${role.roleId }</td>
							<td>
								<input id="${role.roleId }" type="text" name="roleName" value="${role.roleName }" class="longInput"/>
							</td>
							<td>
								<input type="checkbox" name="roleIdList" value="${role.roleId }" id="checkbox${role.roleId }"/>
								<label for="checkbox${role.roleId }">点我更轻松</label>
							</td>
							<td>
								<a href="manager/role/toDispatcherUI/${role.roleId }">分配权限</a>
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