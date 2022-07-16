package com.servlet.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "User_ExitServlet")
public class User_ExitServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(10*60);

        if(!session.isNew() && session.getAttribute("user") != null){
            session.removeAttribute("user");
            System.out.println("退出成功");
            session.setAttribute("message", "退出成功");
            session.setAttribute("return","0");
        } else {
            System.out.println("退出失败");
            session.setAttribute("message", "退出失败");
            session.setAttribute("return","-1");
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
