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

@WebServlet(name = "User_SearchServlet")
public class User_SearchServlet extends HttpServlet {
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

        String userId = request.getParameter("userId");
        UserDAO ud = new UserDAO();
        User user = ud.search(userId);

        if (user != null){
            System.out.println("搜索成功");
            request.setAttribute("returnUser", user);
            request.getRequestDispatcher("Page/User/manage.jsp").forward(request, response);
        } else {
            System.out.println("搜索失败");
            session.setAttribute("message","无查询结果");
            session.setAttribute("return","-1");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
