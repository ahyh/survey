<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="/res_jsp/base.jsp" %>
    <script type="text/javascript">

        $(function () {

            $(":button").click(function () {
                var thisBtn = this;
                //最终目的：将当前资源的id发送到服务器端，让服务器端根据这个id值更新资源的publicStatus属性
                //1.获取当前资源的id
                var resId = this.id;
                //2.准备$.post()函数需要的参数值
                var url = "${pageContext.request.contextPath}/manage/res/toggleStatus";
                var paramData = {"resId": resId};
                //respData是服务器端返回的响应数据
                var callBack = function (respData) {
                    //{"newStatus":"true","message":"success"}
                    var code = respData.resultCode;
                    if (code == 1) {
                        alert("操作成功！");
                        var newStatus = respData.resultValue;
                        if (newStatus == 0) {
                            thisBtn.value = "公共资源";
                        }
                        if (newStatus == 1) {
                            thisBtn.value = "受保护资源";
                        }
                    }
                    if (code != 1) {
                        alert("操作失败！");
                    }
                };
                //接收到服务器端数据后的解析方式
                var type = "json";
                //3.发送Ajax请求
                $.post(url, paramData, callBack, type);
            });

        });

    </script>
</head>
<body>

<%@include file="/res_jsp/manager_top.jsp" %>

<div id="mainDiv" class="borderDiv">
    [资源列表]
    <form action="manage/res/batchDelete" method="post">
        <table class="dataTable">
            <c:if test="${empty resList }">
                <tr>
                    <td>尚未捕获到资源</td>
                </tr>
            </c:if>
            <c:if test="${!empty resList }">
                <tr>
                    <td>ID</td>
                    <td>ServletPath</td>
                    <td>资源状态</td>
                    <td>删除</td>
                </tr>

                <c:forEach items="${resList }" var="res">
                    <tr>
                        <td>${res.id }</td>
                        <td>${res.servletPath }</td>
                        <td>
                            <!-- <button>在表单中有提交表单的效果</button> -->
                            <c:if test="${res.publicStatus == 0}">
                                <input id="${res.id }" type="button" value="公共资源"/>
                            </c:if>
                            <c:if test="${res.publicStatus == 1}">
                                <input id="${res.id }" type="button" value="受保护资源"/>
                            </c:if>
                        </td>
                        <td>
                            <input id="checkbox${res.id }" type="checkbox" name="resIdList" value="${res.id }"/>
                            <label for="checkbox${res.id }">点我更轻松</label>
                        </td>
                    </tr>
                </c:forEach>

                <tr>
                    <td colspan="4">
                        <input type="submit" value="批量删除"/>
                    </td>
                </tr>

            </c:if>
        </table>
    </form>
</div>

<%@include file="/res_jsp/manager_bottom.jsp" %>

</body>
</html>