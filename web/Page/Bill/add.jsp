<%@ page import="com.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: 16123
  Date: 2021/6/23
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加账单记录</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-grid.rtl.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-grid.min.css"/>

    <script type="text/javascript">
        function firm(form) {
            if (form.dealInsuId.value === "") {
                alert("交易对象账号不能为空");
                return false;
            }
            if (form.in_out.value === "") {
                alert("收支不能为空");
                return false;
            }

            return confirm("确定提交");
        }

        function re() {
            window.history.go(-1)
        }
    </script>
</head>
<body class="text-center">
<%
    User user = (User) session.getAttribute("user");
    if (user != null) {
%>
<div align="center">

    <form action="${pageContext.request.contextPath}/Bill_AddServlet" method="post"
          onsubmit="return firm(this);">
        <table align="center" width="450" border="0">
            <input type="hidden" name="admin" value="0">
            <tr>
                <td align="right">账 号：<%=user.getUserId()%>"</td>
            </tr>
            <tr>
                <td align="right">交易对象账号：</td>
                <td>
                    <input type="text" name="dealInsuId" style="height: 25px;width: 200px;font-size: 10px;"
                           placeholder="请输入6位交易对象的的账号">
                </td>
            </tr>
            <tr>
                <td align="right" style="color: crimson">收支（元）：</td>
                <td>
                    <input type="number" name="in_out" style="height: 25px;width: 200px;font-size: 10px;"
                           placeholder="请输入20位以内的数字"
                           oninput="if(value.length>20)value=value.slice(0,20);">
                </td>
            </tr>
            <tr>
                <td align="right" style="color: crimson">备注：</td>
                <td>
                    <input type="text" name="remark" style="height: 25px;width: 200px;font-size: 10px;"
                           placeholder="选填">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="添 加">
                    <input type="reset" value="重 置">
                </td>
            </tr>
        </table>
    </form>
    <input type="button" align="center" value="返回" onclick="re()">
</div>


<%
        session.removeAttribute("message");
    } else {
        response.sendRedirect("index.jsp");
    }
%>
</body>
</html>
