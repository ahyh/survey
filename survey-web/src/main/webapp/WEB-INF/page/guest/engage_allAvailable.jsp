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
		[所有可以参与的调查]
		<table class="dataTable">
			<c:if test="${empty page.list }">
				<tr>
					<td>暂时还没有可以参与的调查</td>
				</tr>
			</c:if>
			<c:if test="${!empty page.list }">
				
				<tr>
					<td>Logo</td>
					<td>调查名称</td>
					<td>参与</td>
				</tr>
				
				<c:forEach items="${page.list }" var="survey">
					
					<tr>
						<td><img src="${survey.logoPath }"/></td>
						<td>${survey.surveyName }</td>
						<td>
							<a href="guest/engage/entry/${survey.id }">参与调查</a>
						</td>
					</tr>
					
				</c:forEach>
				
				<tr>
					<td colspan="3">
						<c:set var="targetUrl" value="guest/engage/showAllAvailable"/>
						<%@include file="/res_jsp/navigator.jsp" %>
					</td>
				</tr>
			
			</c:if>
		</table>
	</div>
	
	<%@include file="/res_jsp/guest_bottom.jsp" %>

</body>
</html>