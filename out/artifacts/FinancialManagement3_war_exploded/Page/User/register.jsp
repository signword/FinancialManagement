<%@ page import="javax.swing.*" %>
<%@ page import="com.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: 16123
  Date: 2021/6/19
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-grid.rtl.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-grid.min.css"/>
    <style>
        .form-signin .form-floating:focus-within {
            z-index: 2;
        }
    </style>
    <script type="text/javascript">
        function re() {
            window.history.go(-1)
        }

        function reg(form) {
            if (form.userId.value === "") {
                alert("账号不能为空");
                return false;
            }
            if (form.instituId.value === "") {
                alert("公司代号不能为空");
                return false;
            }
            if (form.userPwd.value === "") {
                alert("密码不能为空");
                return false;
            }
            if (form.rePwd.value === "") {
                alert("确认密码不能为空");
                return false;
            }
            if (form.userPwd.value !== form.rePwd.value) {
                alert("两次密码输入不一致");
                return false;
            }
            return confirm("确定提交");
        }
    </script>
</head>
<body class="text-center">
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
%>
<div class="container">

        <form class="form-control-sm" action="${pageContext.request.contextPath}/User_RegisterServlet"
              method="post">
            <h3>用户注册</h3>
            <input type="hidden" name="admin" value="0">
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4">
                    <label for="userId" class="col-form-label">*账 号</label>
                    <input type="text" id="userId" name="userId" class="form-control" aria-describedby="userIdInline">
                    <span id="userIdInline" class="form-text">
                请输入由6位数字组成的账号
                </span>
                </div>
                <div class="col-4"></div>
            </div>
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4">
                    <label for="userName" class="col-form-label">名 称</label>
                    <input type="text" id="userName" name="userName" class="form-control"
                           aria-describedby="userNameInline">
                    <span id="userNameInline" class="form-text">
                选填
                </span>
                </div>
                <div class="col-4"></div>
            </div>
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4">
                    <label for="userPwd" class="col-form-label">*密 码</label>
                    <input type="password" id="userPwd" name="userPwd" class="form-control" aria-describedby="userPwdInline">
                    <span id="userPwdInline" class="form-text">
                请输入6到10位由数字或字母组成的密码
                </span> </div>
                <div class="col-4"></div>
            </div>
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4">
                    <label for="rePwd" class="col-form-label">*确认密码</label>
                    <input type="password" id="rePwd" name="rePwd" class="form-control" aria-describedby="rePwdInline">
                    <span id="rePwdInline" class="form-text">
                请再一次确认密码
                </span>
                </div>
                    <div class="col-4"></div>
            </div>

            <div class="row">
                <div class="col-4"></div>
                <div class="col-4">
                    <label for="instituId" class="col-form-label">*公司代号</label>
                    <input type="text" id="instituId" name="instituId" class="form-control"
                           aria-describedby="instituIdInline">
                    <span id="instituIdInline" class="form-text">
                请输入由6位数字组成的公司代号
                </span> </div>
                <div class="col-4"></div>

            </div>

            <div class="row">
                <div class="col-4"></div>
                <div class="col-4">
                    <label for="userSta" class="col-form-label">职 位</label>
                    <input type="text" id="userSta" name="userSta" class="form-control" aria-describedby="userStaInline">
                    <span id="userStaInline" class="form-text">
                选填
                </span>
                </div>

                <div class="col-4"></div>
            </div>
            <button type="submit" onclick="return reg(this);" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                注册
            </button>
            <button type="reset" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                清空
            </button>
        </form>
        <input type="button" value="返回" onclick="re()">


                <%
        session.removeAttribute("message");
    } else {
        response.sendRedirect("index.jsp");
    }
%>
</div>

</body>
</html>

