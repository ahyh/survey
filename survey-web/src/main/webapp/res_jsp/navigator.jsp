<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	
	$(function(){
		$("#goPageNo").change(function(){
			//1.获取当前文本框中输入的pageNo
			var pageNo = $.trim(this.value);
			if(pageNo == "" || isNaN(pageNo)) {
				//如果用户输入的值无效，则恢复为默认值
				this.value = this.defaultValue;
				return ;
			}
			//3.执行页面跳转
			var url = "${pageContext.request.contextPath}/${targetUrl}?pageNoStr="+pageNo;
			window.location.href = url;
		});
	});
	
</script>
<%-- page.hasPrev相当于page.isHasPrev() --%>
<c:if test="${page.hasPreviousPage }">
	<a href="${targetUrl}?pageNoStr=1">首页</a>
	<a href="${targetUrl}?pageNoStr=${page.prePage }">上一页</a>
</c:if>

★您现在在第${page.pageNum }页
共${page.pages }页
共${page.total }条记录★

跳转到第<input type="text" class="shortInput" id="goPageNo"/>页

<c:if test="${page.hasNextPage }">
	<a href="${targetUrl}?pageNoStr=${page.nextPage }">下一页</a>
	<a href="${targetUrl}?pageNoStr=${page.lastPage }">末页</a>
</c:if>