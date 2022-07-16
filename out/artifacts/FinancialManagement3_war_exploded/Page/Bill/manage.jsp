<%@ page import="com.entity.User" %>
<%@ page import="com.entity.Bill" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.dao.BillDAO" %>
<%--
  Created by IntelliJ IDEA.
  User: 16123
  Date: 2021/6/24
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path; %>
<html>
<head>
    <title>管理账单记录</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-grid.rtl.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-grid.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.bundle.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.esm.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script>
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
            return confirm("确定退出");
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
<div class="container">
    <%
        User user = (User) session.getAttribute("user");
        String sea_userId = (String) session.getAttribute("sea_userId");
        if (user != null) {
            List<Bill> bills = (List<Bill>) request.getAttribute("bills");
            BigDecimal sum = new BigDecimal(0);
            BigDecimal a = new BigDecimal(-1);
            String op1 = "1";
            String op2 = "2";
            String op = "3";
    %>
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <div class="align-content-center">当前账号：<%=user.getUserId()%></div>
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page"
                                   href="<%=basePath%>/Page/User/update.jsp?op=<%=op2%>">修改信息</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page"
                                   href="<%=basePath%>/Page/Bill/add.jsp">添加账单记录</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page"
                                   href="<%=basePath%>/User_ExitServlet" onclick="return firm()">用户退出</a>
                            </li>
                            <%
                                if (user.getAdmin().equals("2")) {
                            %>
                            <li class="nav-item">
                                <a class="nav-link" href="<%=basePath%>/Page/User/manage.jsp"
                                   onclick="return update();">管理用户</a>
                            </li>
                            <%
                                }
                            %>
                        </ul>

                        <form class="d-flex" action="${pageContext.request.contextPath}/Bill_SearchServlet?op=<%=op%>"
                              method="post">
                            <input class="form-control me-auto" type="search" name="userId" placeholder="Search"
                                   aria-label="Search">
                            <button class="btn btn-primary" type="submit">搜索账号</button>
                        </form>
                    </div>
                </div>
            </nav>
        </div>
        <div class="col-md-2"></div>

    </div>
    <div class="row">
        <div class="col-md-2"></div>
        <%
            if (bills != null && bills.size() > 0) {
        %>
        <div class="col-md-8 align-content-center">
            <table class="table table-hover">
                <capiton align="center"><h5>账单记录总表</h5></capiton>
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">账单发起人账号</th>
                    <th scope="col">账单建立时间</th>
                    <th scope="col">交易对象账号</th>
                    <th scope="col">收支金额（元）</th>
                    <th scope="col">备注</th>
                    <th scope="col">操作</th>
                </tr>
                </thead>
                <%
                    int i = 0;
                    for (; i < bills.size(); i++) {
                        Bill bill = bills.get(i);
                %>
                <tbody>
                <tr>
                    <th scope="row"><%=i + 1%></th>
                    <td><%=bill.getUserId()%></td>
                    <td><%=bill.getBillTime()%></td>
                    <td><%=bill.getDealInsuId()%></td>
                    <td><%=bill.getIn_out()%></td>
                    <td><%=bill.getRemark()%></td>
                    <%
                        System.out.println(sea_userId);
                        if (sea_userId != null) {
                            if (sea_userId.equals(bill.getUserId())) {
                                sum = sum.add(bill.getIn_out());
                            } else {
                                sum = sum.add(bill.getIn_out().multiply(a));
                            }
                        }
                    %>
                    <td>
                        <%
                            if (user.getUserId().equals(bill.getUserId())) {
                        %>
                        <a href="Bill_DeleteServlet?userId=<%=bill.getUserId()%>&billTime=<%=bill.getBillTime()%>"
                           onclick="return update();">删除</a>
                        <%
                            }//for
                        %>
                    </td>
                </tr>
                </tbody>
                <%
                    }
            }//if
                %>
            </table>
            <%
                if (sea_userId != null) {
                    System.out.println(sum);
                    BillDAO bd = new BillDAO();
                    String amount = bd.bigDecimalToString(sum);
                    System.out.println(amount);
            %>
            <div align="center">
                账号<%=sea_userId%>总收支：<%=sum%>(元)
                <div align="center"><input type="button" value="返回" onclick="re()"></div>
            </div>
            <%
                }
            %>

        </div>
        <div class="col-md-2"></div>
    </div>
    <%
        } else {
            session.setAttribute("message", "无结果");
            response.sendRedirect("index.jsp");
        }
    %>
</div>
</body>
</html>
