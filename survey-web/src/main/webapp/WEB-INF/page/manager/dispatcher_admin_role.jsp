<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/res_jsp/base.jsp" %>
</head>
<body>

	<%@include file="/res_jsp/manager_top.jsp" %>
	
	<div id="mainDiv" class="borderDiv">
		[给管理员分配角色]
		<form action="manager/admin/doDispatcher" method="post">
			
			<input type="hidden" name="adminId" value="${requestScope.adminId }"/>
			
			<table class="dataTable">
				
				<c:if test="${empty roleList }">
					<tr>
						<td>暂时没有角色可供分配</td>
					</tr>
				</c:if>
				<c:if test="${!empty roleList }">
					
					<c:forEach items="${roleList }" var="role">
						
						<tr>
							<td>
								<!-- 回显的方式是检查当前遍历得到的roleId是否在roleIdList中 -->
								<input id="checkbox${role.roleId }" 
									   type="checkbox" 
									   name="roleIdList" 
									   value="${role.roleId }"
									   
										<c:forEach items="${roleIdList }" var="currentRoleId">
											<c:if test="${currentRoleId==role.roleId }">checked="checked"</c:if>
										</c:forEach>
									   
									   />
									   
									   
								<label for="checkbox${role.roleId }">${role.roleName }</label>
							</td>
						</tr>
						
					</c:forEach>
					
					<tr>
						<td>
							<input type="submit" value="OK"/>
						</td>
					</tr>
					
				</c:if>
				
			</table>
			
		</form>
	</div>
	
	<%@include file="/res_jsp/manager_bottom.jsp" %>

</body>
</html>