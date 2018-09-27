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
		[简答题答案列表]
		<table class="dataTable">
		
			<c:if test="${empty textResult }">
				<tr>
					<td>暂时没有答案数据</td>
				</tr>
			</c:if>
			<c:if test="${!empty textResult }">
				<c:forEach items="${textResult }" var="text">
					<tr>
						<td>${text }</td>
					</tr>
				</c:forEach>
			</c:if>
		
		</table>
	</div>
	
	<%@include file="/res_jsp/manager_bottom.jsp" %>

</body>
</html>