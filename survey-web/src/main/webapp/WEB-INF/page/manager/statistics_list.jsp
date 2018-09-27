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
		[调查统计]
		
		<table class="dataTable">
			
			<c:if test="${empty page.list }">
				<tr>
					<td>暂时没有可以统计的调查</td>
				</tr>
			</c:if>
			
			<c:if test="${!empty page.list }">
			
				<tr>
					<td>ID</td>
					<td>调查名称</td>
					<td colspan="2">操作</td>
				</tr>
				
				<c:forEach items="${page.list }" var="survey">
					
					<tr>
						<td>${survey.id }</td>
						<td>${survey.surveyName }</td>
						<td>
							<a href="manage/statistics/showSummary/${survey.id }">查看调查大纲</a>
						</td>
						<td>
							<a href="manage/statistics/exportExcel/${survey.id }">导出Excel文件</a>
						</td>
					</tr>
					
				</c:forEach>
				
				<tr>
					<td colspan="4">
						<c:set var="targetUrl" value="manage/statistics/showAllAvailable"/>
						<%@include file="/res_jsp/navigator.jsp" %>
					</td>
				</tr>
				
			</c:if>
			
		</table>
		
	</div>
	
	<%@include file="/res_jsp/manager_bottom.jsp" %>

</body>
</html>