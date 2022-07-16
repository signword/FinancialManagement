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

@WebServlet(name = "Bill_ManageServlet")
public class Bill_ManageServlet extends HttpServlet {
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
        BillDAO bd = new BillDAO();
        List<Bill> bills = bd.all(null, user.getInstituId(), user.getAdmin());
        if (!session.isNew()){
            if (bills != null){
                System.out.println("获取成功");
                request.setAttribute("bills", bills);
                request.getRequestDispatcher("Page/Bill/manage.jsp").forward(request, response);
            }else {
                System.out.println("获取失败");
                session.setAttribute("message","无账单或查看失败");
                session.setAttribute("return","-1");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }else request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
