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

@WebServlet(name = "User_UpdateServlet")
public class User_UpdateServlet extends HttpServlet {
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

        if (!session.isNew() && session.getAttribute("user") != null) {
            UserDAO ud = new UserDAO();
            User user = new User();
            User now_user = (User) session.getAttribute("user");

            String userId;
            String userName;
            String userPwd;
            String userSta;
            String instituId;
            String admin;
            String op = request.getParameter("op");
            if (op.equals("1")){
                String ori_userPwd = request.getParameter("ori_userPwd");
                userId = now_user.getUserId();

                if (!ori_userPwd.equals(ud.search(userId).getUserPwd())){
                    System.out.println("原密码不正确");
                    request.setAttribute("message", "原密码不正确！");
                    session.setAttribute("return","-1");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }

                userName = now_user.getUserName();
                userPwd = request.getParameter("userPwd");
                userSta = now_user.getUserSta();
                instituId = now_user.getInstituId();
                admin = now_user.getAdmin();

            } else {
                userId = request.getParameter("userId");
                userName = request.getParameter("userName");
                userPwd = now_user.getUserPwd();
                userSta = request.getParameter("userSta");
                instituId = request.getParameter("instituId");
                admin= request.getParameter("admin");

            }

            user.setUserId(userId);
            user.setUserName(userName);
            user.setUserPwd(userPwd);
            user.setUserSta(userSta);
            user.setInstituId(instituId);
            user.setAdmin(admin);

            if (ud.update(user)){
                System.out.println("更新成功");
                session.setAttribute("message", "信息修改成功！");
                if (user.getUserId().equals(((User) session.getAttribute("user")).getUserId())) {
                    session.setAttribute("user", user);
                }

            } else {
                System.out.println("更新失败");
                request.setAttribute("message", "信息修改失败！");
            }
            session.setAttribute("return","-1");
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
