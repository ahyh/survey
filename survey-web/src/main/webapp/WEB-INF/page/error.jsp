<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="/res_jsp/base.jsp" %>
    <script type="text/javascript">

        $(function () {
            $("#goBackBtn").click(function () {
                //模拟浏览器的后退操作
                window.history.back();
            });
        });

    </script>
</head>
<body>

<div id="mainDiv" class="borderDiv">
    [出错啦！]
    <br/>
    <c:if test="${!empty exception }"><h2 style="color: red">${exception.message }</h2></c:if>
    <br/>
    <button id="goBackBtn">返回继续操作</button>
</div>

</body>
</html>