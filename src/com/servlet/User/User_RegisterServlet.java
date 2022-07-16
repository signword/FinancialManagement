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
import java.sql.SQLException;

@WebServlet(name = "User_RegisterServlet")
public class User_RegisterServlet extends HttpServlet {
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

        String id = request.getParameter("userId");
        String userId = String.format("%6s", id).replace(" ", "0");
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");
        String userSta = request.getParameter("userSta");
        String id2 = request.getParameter("instituId");
        String instituId = String.format("%6s", id2).replace(" ", "0");
        String admin = request.getParameter("admin");

        UserDAO ud = new UserDAO();
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserPwd(userPwd);
        user.setUserSta(userSta);
        user.setInstituId(instituId);
        user.setAdmin(admin);

        if (ud.judge(id,6) || ud.judge(id2,6)){
            session.setAttribute("message", "请输入长度为6位的账号或公司代号");
            session.setAttribute("return","-1");
        }else {
            if (!session.isNew() && session.getAttribute("user") != null){
                System.out.println("session已存在");
            } else {
                System.out.println("session创建成功");
                session.setAttribute("user", user);
                if (ud.search(user.getUserId()) == null) {

                    try {
                        if (ud.register(user)) {
                            System.out.println("注册成功");
                            session.setAttribute("message", "注册成功！");
                            session.setAttribute("return","1");
                        } else {
                            System.out.println("注册错误");
                            session.removeAttribute("user");
                            session.setAttribute("message", "注册失败！");
                            session.setAttribute("return","-1");
                        }
                    } catch (SQLException e) {
                        System.out.println("插入错误");
                        session.removeAttribute("user");
                        session.setAttribute("message", "注册失败！");
                        session.setAttribute("return","-1");
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("账号已存在");
                    session.removeAttribute("user");
                    session.setAttribute("message", "此账号已存在，请重新输入！");
                    session.setAttribute("return","-1");
                }

            }
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
