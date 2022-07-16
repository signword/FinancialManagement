<%@ page import="com.entity.User" %>
<%@ page import="com.entity.Bill" %><%--
  Created by IntelliJ IDEA.
  User: 16123
  Date: 2021/6/24
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改账单记录</title>
    <script>
        function load(){
            return true
        }
        function re(){
            window.history.back();
        }
        window.onload = load;
        // window.onload = check(regForm);
    </script>
</head>
<body>
<%
    // 获取登录的用户信息
    User user = (User) session.getAttribute("user");
    Bill bill = (Bill) request.getAttribute("bill");
//    System.out.println(bill);
// 判断用户是否登录
    if (user != null) {

%>

<div align="center">
    <div class="div1">
        <div class="div3">
            <form action="${pageContext.request.contextPath}/User_UpdateServlet" method="post"
                  onsubmit="return update(this);">
                <input type="hidden" name="admin" value="0">
                <table align="center" width="450" border="0">
                    <tr>
                        <td align="right">账单建立时间：<%=request.getAttribute("billTime")%></td>
                        <td>
                            <input type="hidden" name="billTime" value="<%=request.getAttribute("billTime")%>">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">账单发起人账号：<%=request.getAttribute("billId")%></td>
                        <td>
                            <input type="hidden" name="billId" value="<%=request.getAttribute("billId")%>">
                        </td>
                    </tr>

                    <tr>
                        <td align="right">交易对象账号：</td>
                        <td>
                            <input type="text" name="dealInsuId" value="<%=request.getAttribute("dealInsuId")%>">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">收支金额：</td>
                        <td>
                            <input type="number" name="in_out" value="<%=request.getAttribute("in_out")%>">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">备注：</td>
                        <td>
                            <input type="text" name="remark" value="<%=request.getAttribute("remark")%>">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="修 改">
                            <input type="reset" value="重 置">
                        </td>
                    </tr>
                </table>
            </form>

        </div>
    </div>
</div>
</div>
<td><a onclick="return re()">返回</a></td>
<%
    } else {
        out.println("<br>对不起，您还没有登录！");
        response.sendRedirect("index.jsp");
    }
%>
</body>
</html>
