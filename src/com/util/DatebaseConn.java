package com.util;

import com.Reference;
import com.entity.User;

import javax.servlet.http.HttpSession;
import java.sql.*;


public class DatebaseConn {
    static String url = "jdbc:mysql://localhost:3306/fm?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false";
    static String username = "root";
    static String password = "123456";
    static Connection conn = null;
    static ResultSet rs = null;
    static PreparedStatement ps = null;

    public static void init(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
//            System.out.println(conn.isClosed());
        } catch(Exception e){
            System.out.println("SQL驱动程序初始化失败");
            e.printStackTrace();
        }
    }

    public static int add_upd_del(String sql){
        int i = 0;
        try{
            ps = conn.prepareStatement(sql);
            i = ps.executeUpdate();
        } catch(SQLException e){
//            System.out.println(ps);
            System.out.println("sql数据库增删改异常");
            e.printStackTrace();
        }
        return i;
    }
    public static ResultSet select(String sql) throws SQLException {
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
        } catch(SQLException e){
            System.out.println("sql数据库查询异常");
//            if(rs == null) System.out.println("rs为空");
            e.printStackTrace();
        }
        return rs;
    }
    public static void closeConn(){
        try{
            conn.close();
        } catch(SQLException e){
            System.out.println("sql数据库关闭异常");
            e.printStackTrace();
        }
    }

}
