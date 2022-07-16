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
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@WebServlet(name = "Bill_AddServlet")
public class Bill_AddServlet extends HttpServlet {
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

        BillDAO bd = new BillDAO();
        Bill bill = new Bill();
        User user = (User) session.getAttribute("user");

        if (!session.isNew() && user != null){
            String userId = user.getUserId();
            Timestamp billTime = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            billTime = Timestamp.valueOf(df.format(billTime));
            String dealInsuId = request.getParameter("dealInsuId");
            Object number = request.getParameter("in_out");
            BigDecimal in_out = bd.getBigDecimal(number);
            String remark = request.getParameter("remark");

            bill.setBillTime(billTime);
            bill.setUserId(userId);
            bill.setDealInsuId(dealInsuId);
            bill.setIn_out(in_out);
            bill.setRemark(remark);

            try {
                if (bd.add(bill)){
                    System.out.println("添加成功");
                    session.setAttribute("message", "添加成功！");
                    session.setAttribute("bill", bill);
                }else {
                    System.out.println("添加错误");
                    session.setAttribute("message", "添加失败！");
                }
                session.setAttribute("return","-1");
            } catch (SQLException e) {
                System.out.println("添加错误");
                session.setAttribute("message", "添加失败！");
                session.setAttribute("return","-1");
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
