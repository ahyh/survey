<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib uri="http://www.atguigu.com/myTag" prefix="atguigu" %>--%>
<div id="logoDiv" class="borderDiv">XXX在线调查系统后台</div>
<div id="topDiv" class="borderDiv">
	
	<c:if test="${sessionScope.loginAdmin == null }">
		[<a href="manage/admin/toLogin">登录</a>]
	</c:if>
	<c:if test="${sessionScope.loginAdmin != null }">
		
		[欢迎您，${sessionScope.loginAdmin.adminName }]
		[<a href="manage/admin/logout">退出登录</a>]
		
		<%--<atguigu:auth servletPath="manager/statistics/showAllAvailable">--%>
			[<a href="manage/statistics/showAllAvailable">统计调查</a>]
		<%--</atguigu:auth>--%>
		<%----%>
		<%--<atguigu:auth servletPath="manager/res/showList">--%>
			[<a href="manage/res/showList">资源列表</a>]
		<%--</atguigu:auth>--%>
		<%----%>
		<%--<atguigu:auth servletPath="manager/auth/toAddUI">--%>
			<%--[<a href="manager/auth/toAddUI">创建权限</a>]--%>
		<%--</atguigu:auth>--%>
		<%----%>
		<%--<atguigu:auth servletPath="manager/auth/showList">--%>
			<%--[<a href="manager/auth/showList">权限列表</a>]--%>
		<%--</atguigu:auth>--%>
		<%----%>
		<%--<atguigu:auth servletPath="manager/role/toAddUI">--%>
			<%--[<a href="manager/role/toAddUI">创建角色</a>]--%>
		<%--</atguigu:auth>--%>
		<%----%>
		<%--<atguigu:auth servletPath="manager/role/showList">--%>
			<%--[<a href="manager/role/showList">角色列表</a>]--%>
		<%--</atguigu:auth>--%>
		<%----%>
		<%--<atguigu:auth servletPath="manager/admin/toAddUI">--%>
			<%--[<a href="manager/admin/toAddUI">创建账号</a>]--%>
		<%--</atguigu:auth>--%>
		<%----%>
		<%--<atguigu:auth servletPath="manager/admin/showList">--%>
			<%--[<a href="manager/admin/showList">账号列表</a>]--%>
		<%--</atguigu:auth>--%>
		
		[<a href="manage/log/showList">日志列表</a>]
	
	</c:if>
	
	[<a href="manage/admin/toMain">首页</a>]
	
</div>