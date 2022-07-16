<%@ page import="com.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dao.UserDAO" %>
<%--
  Created by IntelliJ IDEA.
  User: 16123
  Date: 2021/6/23
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>用户管理</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-grid.rtl.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-grid.min.css"/>
    <script>
        function update() {
            return confirm("确定提交");
        }

        function re() {
            window.history.go(-1)
        }

        function load() {
            return true
        }

        window.onload = load;
    </script>
</head>
<body class="text-center">
<form align="center" action="${pageContext.request.contextPath}/User_SearchServlet" method="post">
    <tr><input align="right" name="userId"></tr>
    <tr><input type="submit" value="搜索账号"></tr>
</form>
<%
    String op = "2";
    User sea_user = (User) request.getAttribute("returnUser");
    if (sea_user != null){
%>
<table align="center" border="0" cellpadding="0" cellspacing="0">
    <capiton align="center"><h5>搜索结果</h5></capiton>
    <tr>
        <th>账号</th>
        <th>昵称</th>
        <th>公司</th>
        <th>职位</th>
        <th>权限</th>
    </tr>
    <form action="${pageContext.request.contextPath}/User_UpdateServlet?op=<%=op%>" method="post">
        <tr>
            <td align="center"><%=sea_user.getUserId()%></td>
            <input type="hidden" value="<%=sea_user.getUserId()%>" name="userId">
            <td align="center"><label>
                <input type="text" value="<%=sea_user.getUserName()%>" name="userName">
            </label></td>
            <td align="center"><label>
                <input type="text" value="<%=sea_user.getInstituId()%>" name="instituId">
            </label></td>
            <td align="center"><label>
                <input type="text" value="<%=sea_user.getUserSta()%>" name="userSta">
            </label></td>
            <td align="center"><label>
                <input type="text" value="<%=sea_user.getAdmin()%>" name="admin">
            </label></td>
            <td align="center"><a href="User_DeleteServlet?userId=<%=sea_user.getUserId()%>" onclick="return update()">删除</a>
                <input type="submit" value="更新" onclick="return update()">
            </td>
        </tr>
    </form>
</table>
<%
    }
    UserDAO ud = new UserDAO();
    List<User> userALL = ud.all();
    System.out.println("userALL:" + userALL);
    User now_user = (User) session.getAttribute("user");
    if (userALL != null){
%>
<table align="center" border="0" cellpadding="0" cellspacing="0">
    <capiton align="center"><h3>用户总表</h3></capiton>
    <tr>
        <th>账号</th>
        <th>昵称</th>
        <th>公司</th>
        <th>职位</th>
        <th>权限</th>
    </tr>

    <%

        int i = 0;
        for (; i < userALL.size(); i++) {
            User user = userALL.get(i);
    %>

        <form action="${pageContext.request.contextPath}/User_UpdateServlet?op=<%=op%>" method="post">
            <tr>
                <td align="center"><%=user.getUserId()%></td>
                <input type="hidden" value="<%=user.getUserId()%>" name="userId">
                <td align="center"><label>
                    <input type="text" value="<%=user.getUserName()%>" name="userName">
                </label></td>
                <td align="center"><label>
                    <input type="text" value="<%=user.getInstituId()%>" name="instituId">
                </label></td>
                <td align="center"><label>
                    <input type="text" value="<%=user.getUserSta()%>" name="userSta">
                </label></td>
                <td align="center"><label>
                    <input type="text" value="<%=user.getAdmin()%>" name="admin">
                </label></td>
                <td align="center"><a href="User_DeleteServlet?userId=<%=user.getUserId()%>" onclick="return update()">删除</a>
                    <input type="submit" value="更新" onclick="return update()">
                </td>
            </tr>
        </form>
    <%
            }
        }
    %>
</table>

<div align="center">
    <input type="button" align="center" value="返回" onclick="re()">
</div>
</body>
</html>
