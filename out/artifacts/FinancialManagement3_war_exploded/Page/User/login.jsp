<%@ page import="com.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: 16123
  Date: 2021/6/16
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path; %>
<html>
<head>
    <title>用户登录</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-grid.rtl.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-grid.min.css"/>
    <style>
        body {
            height: 100%;
        }

        body {
            display: flex;
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }

        .form-signin .form-floating:focus-within {
            z-index: 2;
        }

        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.bundle.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.esm.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script type="text/javascript">

        function load() {
            return true

        }

        window.onload = load;
        function login(form) {
            if (form.userId.value === "") {
                alert("用户不能为空");
                return false;
            }
            if (form.userPwd.value === "") {
                alert("密码不能为空");
                return false;
            }
        }
    </script>

</head>
<body class="text-center">

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
%>
<main class="form-signin">
    <h2>简单财务系统</h2>
    <form class="form-control-sm" action="${pageContext.request.contextPath}/User_LoginServlet" method="post"
          name="loginForm" onsubmit="return login(this);">
        <h3>用户登录</h3>
        <div class="form-floating">
            <input type="text" class="form-control" id="floatingInput" name="userId" placeholder="name@example.com">
            <label for="floatingInput">账 号</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" name="userPwd" placeholder="Password">
            <label for="floatingPassword">密 码</label>
        </div>
        <button class="btn btn-primary" type="submit">登 录</button>
    </form>
    <button type="button" class="btn btn-link" onclick="window.location.href = 'register.jsp'">没有账号？点击这里注册</button>
</main>
<%
    } else {
        response.sendRedirect("index.jsp");
    }
%>
</body>
</html>
