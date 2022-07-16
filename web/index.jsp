<%@ page import="com.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: 16123
  Date: 2021/6/17
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<%
    String TipMessage = (String) session.getAttribute("message");
    String PathReturn = (String) session.getAttribute("return");
    User user = (User) session.getAttribute("user");
%>
<script type="text/javascript">
    <%
        if (TipMessage != null){
    %>
    alert("<%=TipMessage%>");
    <%
        session.removeAttribute("message");
        }
        if (PathReturn == null || PathReturn.equals("0") || user == null){
    %>
    window.location = 'Page/User/login.jsp';
    <%
        }
        if (PathReturn != null){
            if (PathReturn.equals("-1")){
    %>
    window.history.go(-1)
    <%
            }else if (PathReturn.equals("1")){
    %>
    window.location = 'Bill_ManageServlet';
    <%
            }else if (!PathReturn.equals("0")){
    %>
    window.location = '<%=PathReturn%>';
    <%
            }
             session.removeAttribute("return");
        }
    %>
</script>

</body>
</html>
