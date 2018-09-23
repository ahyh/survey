<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/res_jsp/base.jsp" %>
<script type="text/javascript">
	
	$(function(){
		
		//1.在文档加载完成时，将选项所在的行隐藏
		$("#optionsTr").hide();
		
		//2.获取题型的单选按钮并绑定单击响应函数
		$(":radio").click(function(){
			
			//3.获取当前单选按钮的value属性值
			var type = this.value;//$(this).val()
			
			//4.判断type是否是0或1
			if(type == 0 || type == 1) {
				//单选题或多选题显示
				$("#optionsTr").show();
			}
			
			if(type == 2) {
				//简答题隐藏
				$("#optionsTr").hide();
			}
			
		});
		
		//3.过滤选项文本框中输入的数据
		$("textarea").change(function(){
			
			//获取当前输入的数据
			var options = this.value;
			
			//去除前后空格
			this.value = $.trim(options);
			
		});
		
	});
	
</script>
</head>
<body>
	
	<%@include file="/res_jsp/guest_top.jsp" %>
	
	<div id="mainDiv" class="borderDiv">
		[创建问题]
		
		<form action="guest/question/saveQuestion" method="post">
		
			<input type="hidden" name="bagId" value="${requestScope.bagId }"/>
			<input type="hidden" name="surveyId" value="${requestScope.surveyId }"/>
		
			<table class="formTable">
				<tr>
					<td>问题名称</td>
					<td>
						<input type="text" name="questionName" class="longInput"/>
					</td>
				</tr>
				<tr>
					<td>选择题型</td>
					<td>
						<input id="type01" type="radio" name="questionType" value="0"/>
						<label for="type01">单选题</label>
						
						<input id="type02" type="radio" name="questionType" value="1"/>
						<label for="type02">多选题</label>
						
						<input id="type03" type="radio" name="questionType" value="2" checked="checked"/>
						<label for="type03">简答题</label>
						
					</td>
				</tr>
				<tr id="optionsTr">
					<td>选项</td>
					<td>
						<!-- 不使用单行文本框是因为单行文本框中输入数据的过程中“回车”会提交表单 -->
						<textarea name="questionOptions" class="multiInput"></textarea>
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