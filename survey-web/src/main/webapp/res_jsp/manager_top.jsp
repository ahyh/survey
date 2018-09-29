<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.yanhuan.com/myTag" prefix="yanhuan" %>
<div id="logoDiv" class="borderDiv">XXX在线调查系统后台</div>
<div id="topDiv" class="borderDiv">
	
	<c:if test="${sessionScope.loginAdmin == null }">
		[<a href="manage/admin/toLogin">登录</a>]
	</c:if>
	<c:if test="${sessionScope.loginAdmin != null }">
		
		[欢迎您，${sessionScope.loginAdmin.adminName }]
		[<a href="manage/admin/logout">退出登录</a>]
		
		<yanhuan:auth servletPath="manage/statistics/showAllAvailable">
			[<a href="manage/statistics/showAllAvailable">统计调查</a>]
		</yanhuan:auth>

		<yanhuan:auth servletPath="manage/res/showList">
			[<a href="manage/res/showList">资源列表</a>]
		</yanhuan:auth>

		<yanhuan:auth servletPath="manage/auth/toAdd">
			[<a href="manage/auth/toAdd">创建权限</a>]
		</yanhuan:auth>

		<yanhuan:auth servletPath="manage/auth/showList">
			[<a href="manage/auth/showList">权限列表</a>]
		</yanhuan:auth>

		<yanhuan:auth servletPath="manage/role/toAdd">
			[<a href="manage/role/toAdd">创建角色</a>]
		</yanhuan:auth>

		<yanhuan:auth servletPath="manage/role/showList">
			[<a href="manage/role/showList">角色列表</a>]
		</yanhuan:auth>

		<yanhuan:auth servletPath="manage/admin/toAdd">
			[<a href="manage/admin/toAdd">创建账号</a>]
		</yanhuan:auth>

		<yanhuan:auth servletPath="manage/admin/showList">
			[<a href="manage/admin/showList">账号列表</a>]
		</yanhuan:auth>
		
		[<a href="manage/log/showList">日志列表</a>]
	
	</c:if>
	
	[<a href="manage/admin/toMain">首页</a>]
	
</div>