<%@page import="com.yh.survey.domain.utils.BagUtils" %>
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
    [参与调查]
    <table class="dataTable">
        <tr>
            <td colspan="2">调查信息</td>
        </tr>
        <tr>
            <td>Logo</td>
            <td>名称</td>
        </tr>
        <tr>
            <td><img src="${sessionScope.currentSurvey.logoPath }"/></td>
            <td>${sessionScope.currentSurvey.surveyName }</td>
        </tr>
    </table>

    <br/>
    <br/>

    <form action="guest/engage/engage" method="post">

        <input type="hidden" name="currentIndex" value="${requestScope.currentIndex }"/>
        <input type="hidden" name="bagId" value="${requestScope.currentBag.id }"/>

        <table class="dataTable">

            <tr>
                <td>当前包裹：${requestScope.currentBag.bagName }</td>
            </tr>
            <tr>
                <td>
                    <c:forEach items="${requestScope.currentBag.questionSet }" var="question">
                        ${question.questionName }

                        <c:if test="${question.questionType==0 }">

                            <%-- ${myStatus.index}可以获取到遍历过程中集合元素的索引值 --%>
                            <c:forEach items="${question.optionsArray }" var="option" varStatus="myStatus">
                                <input type="radio"
                                       name="q${question.id }"
                                       value="${myStatus.index}"
                                       id="id${question.id }${myStatus.index}"
                                        <%=BagUtils.checkedRedisplay(pageContext) %>
                                />

                                <label for="id${question.id }${myStatus.index}">${option }</label>
                            </c:forEach>

                        </c:if>

                        <c:if test="${question.questionType==1 }">

                            <c:forEach items="${question.optionsArray }" var="option" varStatus="myStatus">
                                <input type="checkbox"
                                       name="q${question.id }"
                                       value="${myStatus.index}"
                                       id="id${question.id }${myStatus.index}"
                                        <%=BagUtils.checkedRedisplay(pageContext) %>
                                />
                                <label for="id${question.id }${myStatus.index}">${option }</label>
                            </c:forEach>

                        </c:if>

                        <c:if test="${question.questionType==2 }">

                            <input type="text"
                                   name="q${question.id }"
                                   class="longInput"
                                   value="<%=BagUtils.valueRedisplay(pageContext) %>"/>

                        </c:if>

                        <br/>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>

                    <c:if test="${requestScope.currentIndex > 0 }">
                        <input type="submit" name="submit_prev" value="返回上一个包裹"/>
                    </c:if>

                    <c:if test="${requestScope.currentIndex < sessionScope.lastIndex }">
                        <input type="submit" name="submit_next" value="进入下一个包裹"/>
                    </c:if>

                    <input type="submit" name="submit_quit" value="放弃"/>

                    <c:if test="${requestScope.currentIndex == sessionScope.lastIndex }">
                        <input type="submit" name="submit_done" value="完成"/>
                    </c:if>

                </td>
            </tr>

        </table>
    </form>

</div>

<%@include file="/res_jsp/guest_bottom.jsp" %>

</body>
</html>