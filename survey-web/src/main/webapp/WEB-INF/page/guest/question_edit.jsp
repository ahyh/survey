<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/res_jsp/base.jsp" %>
<script type="text/javascript">
	
	$(function(){
		//1.获取文档刚刚初始化完成时，被选中的题型
		//被选中的单选按钮的value属性值
		var type = $(":radio:checked").val();
		
		//2.如果被选中的题型是简答题，那么就隐藏选项所在行
		if(type == 2) {
			$("#optionsTr").hide();
		}
		
		//3.给单选按钮绑定单击响应函数，根据选中的不同题型切换选项行的显示状态
		$(":radio").click(function(){
			
			var type = this.value;//$(this).val()
			
			if(type == 0 || type == 1) {
				$("#optionsTr").show();
			}
			
			if(type == 2) {
				$("#optionsTr").hide();
			}
			
		});
	});
	
	
</script>
</head>
<body>
	
	<%@include file="/res_jsp/guest_top.jsp" %>
	
	<div id="mainDiv" class="borderDiv">
		[编辑问题]
		
		<form:form action="guest/question/updateQuestion" method="post" modelAttribute="question">
		
			<form:hidden path="questionId"/>
			
			<input type="hidden" name="surveyId" value="${requestScope.surveyId }"/>
			
			<table class="formTable">
				
				<tr>
					<td>问题名称</td>
					<td>
						<form:input path="questionName" cssClass="longInput"/>
					</td>
				</tr>
				
				<tr>
					<td>选择题型</td>
					<td>
						<form:radiobuttons path="questionType" items="${requestScope.radioMap }"/>
					</td>
				</tr>
				
				<tr id="optionsTr">
					<td>选项</td>
					<td>
						<%-- <form:textarea path="questionOptions" cssClass="multiInput"/> --%>
						<textarea name="questionOptions" class="multiInput">${requestScope.question.optionsForEdit }</textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input type="submit" value="更新"/>
					</td>
				</tr>
				
			</table>
			
		</form:form>
		
	</div>
	
	<%@include file="/res_jsp/guest_bottom.jsp" %>

</body>
</html>