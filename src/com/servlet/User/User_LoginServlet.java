package com.servlet.User;

import com.dao.UserDAO;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "User_LoginServlet")
public class User_LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(10*60);

        String userId = request.getParameter("userId");
        String userPwd = request.getParameter("userPwd");
        UserDAO ud = new UserDAO();
        User user = ud.search(userId);

        if (session.isNew() ||  session.getAttribute("user") == null) {
            System.out.println("session创建成功");
            int i = ud.login(userId,userPwd);
            if(i == 0){
                System.out.println("登录失败");
                session.setAttribute("message","登录失败");
                session.setAttribute("return","-1");
            }
            if(i == 1){
                System.out.println("账号或密码不正确");
                session.setAttribute("message","账号或密码不正确");
                session.setAttribute("return","-1");
            }
            if(i == 2){
                System.out.println("登录成功");
                session.setAttribute("user", user);
                System.out.println(user);
                session.setAttribute("message","登录成功");
                session.setAttribute("return","1");
            }
        }else {
            System.out.println("session已存在");
            session.setAttribute("return","1");
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
