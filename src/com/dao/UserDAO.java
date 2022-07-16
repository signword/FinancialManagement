package com.dao;


import com.entity.User;
import com.util.DatebaseConn;

import javax.print.attribute.Size2DSyntax;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class UserDAO {
    public User search(String userId) {
        User user = new User();
        String sql2 = "select * from user where userId='" + userId + "'";
        try {
            DatebaseConn.init();
            ResultSet rs = DatebaseConn.select(sql2);
            if (rs.next()){
                user.setUserId(rs.getString("userId"));
                user.setUserName(rs.getString("userName"));
                user.setUserPwd(rs.getString("userPwd"));
                user.setUserSta(rs.getString("userSta"));
                user.setInstituId(rs.getString("instituId"));
                user.setAdmin(rs.getString("admin"));
            }
            DatebaseConn.closeConn();
//            System.out.println("2:" + user.getUserId());
            if(user.getUserId() != null) return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<User> all() {
        List<User> list = new ArrayList<>();
        String sql = "select * from user";
        try {
            DatebaseConn.init();
            ResultSet rs = DatebaseConn.select(sql);
            while(rs.next()){
                User user = new User();
                user.setUserId(rs.getString("userId"));
                user.setUserName(rs.getString("userName"));
                user.setUserPwd(rs.getString("userPwd"));
                user.setUserSta(rs.getString("userSta"));
                user.setInstituId(rs.getString("instituId"));
                user.setAdmin(rs.getString("admin"));
//                System.out.println(user.getUserId());
                if (user.getUserId() == null) continue;
                list.add(user);
            }
//            System.out.println("list");
            DatebaseConn.closeConn();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean register(User user) throws SQLException {
        boolean flag = false;
        String sql = "insert into user " + "values('"+ user.getUserId() + "', '" + user.getUserName() + "', '" +
                user.getUserPwd() + "', '" + user.getUserSta() + "', '" + user.getInstituId() +
                "', '" + user.getAdmin() + "')";
        DatebaseConn.init();
//        System.out.println("1:" + user.getUserId());
        int i = DatebaseConn.add_upd_del(sql);
        if(i > 0) flag = true;
        DatebaseConn.closeConn();
        return flag;
    }
    public int login(String userId, String userPwd){
        int flag = 0;
        String sql = "select * from user where userId='" +
                userId + "' and userPwd='" + userPwd + "'";
        try {
            DatebaseConn.init();
            ResultSet rs = DatebaseConn.select(sql);
            if (rs.next()){
                    flag = 2;
            } else flag = 1;
//            if(f == 0) System.out.println("sql数据库无数据记录");
            DatebaseConn.closeConn();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean update(User user){
        boolean flag = false;
        String sql = "update user set userName='" + user.getUserName() + "', userPwd='" + user.getUserPwd() +
                "', userSta='" + user.getUserSta() + "', instituId='" + user.getUserId() + "', admin='" +
                user.getAdmin() + "' where userId='" + user.getUserId() + "'";
        DatebaseConn.init();
        int i = DatebaseConn.add_upd_del(sql);
        if(i>0) flag = true;
        return flag;
    }

    public boolean delete(String userId){
        boolean flag = false;
        String sql = "delete from user where userId='" + userId + "'";
        DatebaseConn.init();
        System.out.println(sql);
        int i = DatebaseConn.add_upd_del(sql);
        if(i>0) flag = true;
        DatebaseConn.closeConn();
        return flag;
    }

    public boolean judge(String in, int l){
        boolean flag = false;
        if(in == null) flag = true;
        else {
            if (in.length() != l) flag = true;
            else {
//            System.out.println(in.length());
                int i = 0;
                for (; i < l; i++){
                    if (in.charAt(i) < '0' || in.charAt(i) > '9'){
//                    System.out.println(in.charAt(i));
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }
}
