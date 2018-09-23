<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/res_jsp/base.jsp" %>
</head>
<body>
	
	<%@include file="/res_jsp/guest_top.jsp" %>
	<div id="mainDiv" class="borderDiv">
		[用户注册]
		<form action="guest/user/register" method="post">
			<table class="formTable">
				<c:if test="${!empty requestScope.exception }">
					<tr>
						<td colspan="2">${requestScope.exception.message }</td>
					</tr>
				</c:if>
				<tr>
					<td>用户名</td>
					<td>
						<input type="text" name="userName" class="longInput"/>
					</td>
				</tr>
				<tr>
					<td>密码</td>
					<td>
						<input type="password" name="userPwd" class="longInput"/>
					</td>
				</tr>
				<tr>
					<td>确认密码</td>
					<td>
						<input type="password" name="confirmPwd" class="longInput"/>
					</td>
				</tr>
				<tr>
					<td>选择用户类别</td>
					<td>
						<input id="companyTrue" type="radio" name="company" value="true" checked="checked"/>
						<label for="companyTrue">企业用户</label>
						
						<input id="companyFalse" type="radio" name="company" value="false"/>
						<label for="companyFalse">个人用户</label>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="注册"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<%@include file="/res_jsp/guest_bottom.jsp" %>

</body>
</html>