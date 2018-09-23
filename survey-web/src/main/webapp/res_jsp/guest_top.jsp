<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="logoDiv" class="borderDiv">
	XXX在线调查系统
</div>
<div id="topDiv" class="borderDiv">
	
	<%-- ${sessionScope.loginUser }相当于session.getAttribute("loginUser"); --%>
	<%-- session.getAttribute("loginUser")的前提是session.setAttribute("loginUser",user); --%>
	<c:if test="${empty sessionScope.loginUser }">
		[<a href="guest/user/toLogin">登录</a>]
		[<a href="guest/user/toRegister">注册</a>]
	</c:if>
	<c:if test="${!empty sessionScope.loginUser }">
		<%-- loginUser.userName相当于user.getUserName() --%>
		[欢迎您：${sessionScope.loginUser.userName }]
		[<a href="guest/user/logout">退出登录</a>]
		
		<%-- “创建调查”和“显示我未完成调查”的功能只有企业用户可以访问 --%>
		<%-- loginUser.compay相当于user.isCompany() --%>
		<c:if test="${sessionScope.loginUser.company }">
			[<a href="guest/survey/toAdd">创建调查</a>]
			[<a href="#">我未完成调查</a>]
		</c:if>
		
		<%-- “参与调查”功能无论企业用户还是个人用户都可以访问 --%>
		[<a href="#">参与调查</a>]
		
	</c:if>
	
	[<a href="index.jsp">首页</a>]
	
</div>