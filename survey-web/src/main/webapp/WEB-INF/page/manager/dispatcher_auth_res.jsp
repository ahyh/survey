<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- @include是JSP指令，是在服务器端执行的 --%>
<%@include file="/res_jsp/base.jsp" %>
</head>
<body>
	<%@include file="/res_jsp/manager_top.jsp" %>
	
	<div id="mainDiv" class="borderDiv">
		分配资源
		<form action="manager/auth/dispatcher" method="post">
			<input type="hidden" name="authId" value="${authId }"/>
			<table class="dataTable">
				<c:if test="${empty resList }">
					<tr>
						<td>暂时没有资源</td>
					</tr>
				</c:if>
				<c:if test="${!empty resList }">
					<c:forEach items="${resList }" var="res">
						<tr>
							<td>
								<input id="checkbox${res.resId }" type="checkbox" name="resIdList" value="${res.resId }"
									<c:forEach items="${currentIdList }" var="currentId">
										<c:if test="${currentId==res.resId }">checked="checked"</c:if>
									</c:forEach>
								/>
								<label for="checkbox${res.resId }">${res.servletPath }</label>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td><input type="submit" value="OK"/></td>
					</tr>
				</c:if>
			</table>
		</form>
	</div>
	
	<%@include file="/res_jsp/manager_bottom.jsp" %>

</body>
</html>