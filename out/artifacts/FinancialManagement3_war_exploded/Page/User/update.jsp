<%@ page import="com.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: 16123
  Date: 2021/6/20
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path; %>
<html>
<head>
    <title>修改用户</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-grid.rtl.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-grid.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.bundle.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.esm.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script type="text/javascript">
        function update(form) {
            if (form.ori_userPwd.value === "") {
                alert("原密码不能为空");
                return false;
            }
            if (form.userPwd.value === "") {
                alert("新密码不能为空");
                return false;
            }
            if (form.rePwd.value === "") {
                alert("确认新密码不能为空");
                return false;
            }
            if (form.userPwd.value !== form.rePwd.value) {
                alert("两次密码输入不一致");
                return false;
            }
            return confirm("确定提交");
        }
        function firm(){
            return confirm("确定要删除账号吗");
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
<%
    User user = (User) session.getAttribute("user");
    String op = request.getParameter("op");
    String op1 = "1";
    if (user != null) {
        if (op.equals("2")) {

%>
<div align="center">
    <div class="div1">
        <div class="div3">
            <form action="${pageContext.request.contextPath}/User_UpdateServlet?op=<%=op%>" method="post"
                  onsubmit="return update(this);">
                <input type="hidden" name="admin" value="0">
                <table align="center" width="450" border="0">
                    <tr>
                        <td align="right">账 号：<%=user.getUserId()%>"</td>
                        <td>
                            <input type=hidden name="userId" value="<%=user.getUserId()%>">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">用户名：</td>
                        <td>
                            <input type="text" name="userName" value="<%=user.getUserName()%>">
                        </td>
                    </tr>

                    <tr>
                        <td align="right">公司号：</td>
                        <td>
                            <input type="text" name="instituId" value="<%=user.getInstituId()%>">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">职 位：</td>
                        <td>
                            <input type="text" name="userSta" value="<%=user.getUserSta()%>">
                        </td>
                    </tr>
                    <input type="hidden" name="admin" value="<%=user.getAdmin()%>">
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="修 改">
                            <input type="reset" value="重 置">
                        </td>
                    </tr>
                </table>
            </form>
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                修改密码
            </button>

            <!-- Modal -->
            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/User_UpdateServlet?op=<%=op1%>" method="post"
                                  onsubmit="return update(this);">
                                <table align="center" width="300" border="0" class="tb1">
                                    <tr>
                                        <td align="right">原密码：</td>
                                        <td>
                                            <input type="password" name="ori_userPwd">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">新密码：</td>
                                        <td>
                                            <input type="password" name="userPwd">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">确认新密码：</td>
                                        <td>
                                            <input type="password" name="rePwd">
                                        </td>
                                    </tr>
                                </table>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">修 改</button>
                                <button type="reset" class="btn btn-primary">清 空</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <a href="<%=basePath%>/User_DeleteServlet?userId=<%=user.getUserId()%>" onclick="return firm();">
                删除账号</a>
        </div>
        <div align="center"><input type="button" value="返回" onclick="re()"></div>
    </div>
</div>

<%
        }
    } else {
        out.println("<br>对不起，您还没有登录！");
        response.sendRedirect("index.jsp");
    }
%>
</body>
</html>
