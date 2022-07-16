package com.servlet.Bill;

import com.dao.BillDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "Bill_DeleteServlet")
public class Bill_DeleteServlet extends HttpServlet {
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

        if (!session.isNew() && session.getAttribute("user") != null) {
            BillDAO bd = new BillDAO();
            String userId = request.getParameter("userId");
            String time = request.getParameter("billTime");
            Timestamp billTime = Timestamp.valueOf(time);

            if (userId != null && bd.delete(billTime, userId)){
                session.setAttribute("message", "记录删除成功");
            }else {
                session.setAttribute("message", "记录删除失败");
            }
            session.setAttribute("return", "-1");
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
