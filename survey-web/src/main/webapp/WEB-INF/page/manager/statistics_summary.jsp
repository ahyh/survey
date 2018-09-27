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
		[调查大纲：${survey.surveyName }]
		
		
		<table class="dataTable">
			
			<c:if test="${empty survey.bagSet }">
				<tr>
					<td>当前调查不正常！未检测到包裹数据！</td>
				</tr>
			</c:if>
			<c:if test="${!empty survey.bagSet }">
				
				<tr>
					<td colspan="2">包裹信息</td>
				</tr>
				<c:forEach items="${survey.bagSet }" var="bag">
					<tr>
						<td>${bag.bagName }</td>
						<td>查看问题统计结果</td>
					</tr>
					
					<c:if test="${empty bag.questionSet }">
						<tr>
							<td>当前包裹不正常！</td>
						</tr>
					</c:if>
					<c:if test="${!empty bag.questionSet }">
						
						<tr>
							<td><!-- 空单元格，让问题数据向里面缩进一级 --></td>
							<td>
								<table class="dataTable">
									
									<c:forEach items="${bag.questionSet }" var="question">
										<tr>
											<td>${question.questionName }</td>
											<td>
												<c:if test="${question.questionType==0 || question.questionType==1 }">
													<a target="_blank" href="manager/statistics/showChart/${question.questionId }">以图表形式查看统计结果</a>
												</c:if>
												<c:if test="${question.questionType==2 }">
													<a href="manager/statistics/showTextResult/${question.questionId }">以列表形式查看统计结果</a>
												</c:if>
											</td>
										</tr>
									</c:forEach>
									
								</table>
							</td>
						</tr>
						
					</c:if>
					
				</c:forEach>
				
			</c:if>
			
		</table>
		
	</div>
	
	<%@include file="/res_jsp/manager_bottom.jsp" %>

</body>
</html>