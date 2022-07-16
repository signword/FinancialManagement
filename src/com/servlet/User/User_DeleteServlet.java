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

@WebServlet(name = "User_DeleteServlet")
public class User_DeleteServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(10 * 60);
        UserDAO ud = new UserDAO();

        if (!session.isNew() && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            String userId;
            if (user.getAdmin().equals("2")) {
                userId = request.getParameter("userId");
            } else {
                userId = user.getUserId();
            }
            if (userId != null && ud.delete(userId)) {
                session.setAttribute("message", "账号删除成功");
                if (session.getAttribute("user") != null && user.getAdmin().equals("2")) {
                    session.setAttribute("return", "Page/User/manage");
                    session.setAttribute("return", "-1");
                } else {
                    session.removeAttribute("user");
                    session.setAttribute("return", "0");
                }
            } else {
                session.setAttribute("message", "账号删除失败");
                session.setAttribute("return", "-1");
            }
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
