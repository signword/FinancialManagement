package com.dao;

import com.entity.Bill;
import com.entity.User;
import com.util.DatebaseConn;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    public boolean add(Bill bill) throws SQLException {
        boolean flag = false;
        String sql = "insert into bill " + "values('" + bill.getBillTime() + "', '" +
                bill.getUserId() + "', '" + bill.getDealInsuId() + "', " + bill.getIn_out() +
                ", '" + bill.getRemark() + "')";
        System.out.println(bill.getBillTime());
        DatebaseConn.init();
        int i = DatebaseConn.add_upd_del(sql);
        if(i > 0) flag = true;
        System.out.println(sql);
        DatebaseConn.closeConn();
        return flag;
    }
    public List<Bill> all(String userId, String instituId,String admin) {
        List<Bill> list = new ArrayList<>();
        String sql;
        String admin_sql;
        if (userId == null){
            admin_sql = "select * from bill";
            sql = "select * from bill where userId in(select userId from user where instituId='" + instituId + "')";
        } else {
            admin_sql = "select * from bill where userId='" + userId + "' or dealInsuId='" + userId + "'";
            sql = "select * from bill where userId in(select userId from user where instituId='" + instituId + "')" +
            " and (userId='" + userId + "' or dealInsuId='" + userId + "')";
        }
        try {
            DatebaseConn.init();
            ResultSet rs;
            if (admin.equals("0")) rs = DatebaseConn.select(sql);
            else rs = DatebaseConn.select(admin_sql);

            while(rs.next()){
                Bill bill = new Bill();
                bill.setBillTime(rs.getTimestamp("billTime"));
                bill.setUserId(rs.getString("userId"));
                bill.setDealInsuId(rs.getString("dealInsuId"));
                bill.setIn_out(rs.getBigDecimal("in_out"));
                bill.setRemark(rs.getString("remark"));
                System.out.println(bill.getBillTime());
                if (bill.getUserId() == null && bill.getBillTime() != null) continue;
                list.add(bill);
            }
            System.out.println(list);
            DatebaseConn.closeConn();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Bill search(Timestamp billTime, String userId) {
        Bill bill = new Bill();
        String sql2 = "select * from bill where billTime='" + billTime + "' and userId='" + userId + "'";
        try {
            DatebaseConn.init();
            ResultSet rs = DatebaseConn.select(sql2);
            if (rs.next()){
                bill.setBillTime(rs.getTimestamp("billTime"));
                bill.setUserId(rs.getString("userId"));
                bill.setDealInsuId(rs.getString("dealInsuId"));
                bill.setIn_out(rs.getBigDecimal("in_out"));
                bill.setRemark(rs.getString("remark"));
            }
            DatebaseConn.closeConn();
            if (bill.getUserId() == null && bill.getBillTime() != null) return bill;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean update(Bill bill){
        boolean flag = false;
        String sql = "update bill set dealInsuId='" + bill.getDealInsuId() + "', in_out=" + bill.getIn_out() +
                ", remark='" + bill.getRemark() + "' where billTime='" + bill.getBillTime() +
                "' and userId='" + bill.getUserId() + "'";
        DatebaseConn.init();
        int i = DatebaseConn.add_upd_del(sql);
        System.out.println(sql);
        if(i>0) flag = true;
        return flag;
    }
    public boolean delete(Timestamp billTime, String userId){
        boolean flag = false;
        String sql = "delete from bill where billTime='" + billTime + "' and userId='" + userId + "'";
        DatebaseConn.init();
        System.out.println(sql);
        int i = DatebaseConn.add_upd_del(sql);
        if(i>0) flag = true;
        DatebaseConn.closeConn();
        return flag;
    }
    public BigDecimal getBigDecimal(Object value) {
        BigDecimal ret = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret = BigDecimal.valueOf(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return ret;
    }
    public String bigDecimalToString(BigDecimal bigDecimalValue){
        NumberFormat cf = NumberFormat.getInstance();
        double taxD = bigDecimalValue.doubleValue();
        String strValue = cf.format(taxD).replace(",", "");
        return strValue;
    }
//    public Timestamp getTimestamp(Object time) throws ParseException {
//        if (time == null) return null;
//        else {
//            Format f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//            Date d = (Date) f.parseObject((String) time);
//            Timestamp ts = null;
//            if (d != null) ts = new Timestamp(d.getTime());
//            System.out.println(d);
//            return ts;
//        }
//    }
//    public boolean judge(){
//        boolean flag = false;
//
//    }
}
