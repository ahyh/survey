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
		[创建调查]
		<form action="guest/survey/saveSurvey" method="post" enctype="multipart/form-data">
			<table class="formTable">
				<c:if test="${!empty requestScope.exception }">
					<tr>
						<td colspan="2">${requestScope.exception.message }</td>
					</tr>
				</c:if>
				<tr>
					<td>调查名称</td>
					<td>
						<input type="text" name="surveyName" class="longInput"/>
					</td>
				</tr>
				<tr>
					<td>上传图片</td>
					<td>
						<!-- ※注意：name不要写logoPath※ -->
						<input type="file" name="logoFile"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="保存"/>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	
	<%@include file="/res_jsp/guest_bottom.jsp" %>

</body>
</html>