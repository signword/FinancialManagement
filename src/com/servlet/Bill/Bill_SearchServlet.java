package com.servlet.Bill;

import com.dao.BillDAO;
import com.entity.Bill;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Bill_SearchServlet")
public class Bill_SearchServlet extends HttpServlet {
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

        User user = (User) session.getAttribute("user");

        if (!session.isNew() && user != null){
            String userId = request.getParameter("userId");
            String op = request.getParameter("op");
            BillDAO bd = new BillDAO();
            List<Bill> bills = bd.all(userId, user.getInstituId(), user.getAdmin());
            if (bills != null){
                if (bills.size() > 0){
                    System.out.println("获取成功");
                    request.setAttribute("bills", bills);
                    if (op.equals("3")) session.setAttribute("sea_userId",userId);
                    request.getRequestDispatcher("Page/Bill/manage.jsp").forward(request, response);
                } else {
                    System.out.println("获取失败");
                    session.setAttribute("message","无账单记录");
                    session.setAttribute("return","-1");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }

            } else {
                System.out.println("获取失败");
                session.setAttribute("message","搜索失败");
                session.setAttribute("return","-1");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }
}
