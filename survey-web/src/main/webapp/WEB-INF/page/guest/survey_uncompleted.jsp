<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="/res_jsp/base.jsp" %>

</head>
<body>

<%@include file="/res_jsp/guest_top.jsp" %>

<div id="mainDiv" class="borderDiv">
    [显示我未完成的调查]

    <table class="dataTable">
        <c:if test="${empty page.list }">
            <tr>
                <td>您尚未创建调查！</td>
            </tr>
        </c:if>
        <c:if test="${!empty page.list }">
            <tr>
                <td>ID</td>
                <td>Logo</td>
                <td>SurveyName</td>
                <td colspan="4">Operation</td>
            </tr>
            <c:forEach items="${page.list }" var="survey">
                <tr>
                    <td>${survey.id }</td>
                    <td>
                        <img src="${survey.logoPath }"/>
                    </td>
                    <td>${survey.surveyName }</td>
                    <td>
                        <a href="guest/survey/toDesign/${survey.id }">设计</a>
                    </td>
                    <td>
                        <a href="guest/survey/removeSurvey/${survey.id }/${page.pageNum }">删除</a>
                    </td>
                    <td>
                        <a href="guest/survey/toEdit/${survey.id }/${page.pageNum}">更新</a>
                    </td>
                    <td style="background-color: black;">
                        <a style="color: yellow;font-weight: bolder;" href="guest/survey/deeplyRemove/${survey.id }/${page.pageNum}">深度删除</a>
                    </td>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="7">
                    <c:set var="targetUrl" value="guest/survey/showMyUncompleted" scope="page"/>
                    <%@include file="/res_jsp/navigator.jsp" %>
                </td>
            </tr>

        </c:if>

    </table>

</div>

<%@include file="/res_jsp/guest_bottom.jsp" %>

</body>
</html>