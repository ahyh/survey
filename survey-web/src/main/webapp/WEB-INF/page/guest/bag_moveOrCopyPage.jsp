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
		[选择目标调查]
		<table class="dataTable">
		
			<tr>
				<td>ID</td>
				<td>调查名称</td>
				<td>操作</td>
			</tr>
			
			<c:forEach items="${page.list }" var="survey">
				
				<tr>
					<td>${survey.id }</td>
					<td>${survey.surveyName }</td>
					<td>
						<c:if test="${requestScope.surveyId != survey.id }">
							<a href="guest/bag/moveToThisSurvey/${requestScope.bagId }/${survey.id}">移动到当前调查</a>
							|
							<a href="guest/bag/copyToThisSurvey/${requestScope.bagId }/${survey.id}">复制到当前调查</a>
						</c:if>
						<c:if test="${requestScope.surveyId == survey.id }">
							当前调查
						</c:if>
					</td>
				</tr>
				
			</c:forEach>
			
			<tr>
				<td colspan="3">
					<c:set var="targetUrl" value="guest/bag/toMoveOrCopyPage/${requestScope.bagId }/${requestScope.surveyId }" scope="page"/>
					<%@include file="/res_jsp/navigator.jsp" %>
				</td>
			</tr>
		
		</table>
	</div>
	
	<%@include file="/res_jsp/guest_bottom.jsp" %>

</body>
</html>