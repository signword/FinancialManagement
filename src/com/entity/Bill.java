package com.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class Bill {
    Timestamp billTime;
    String userId;
    String dealInsuId;
    BigDecimal in_out;
    String remark;

    public Timestamp getBillTime() {
        return billTime;
    }

    public void setBillTime(Timestamp billTime) {
        this.billTime = billTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDealInsuId() {
        return dealInsuId;
    }

    public void setDealInsuId(String dealInsuId) {
        this.dealInsuId = dealInsuId;
    }

    public BigDecimal getIn_out() {
        return in_out;
    }

    public void setIn_out(BigDecimal in_out) {
        this.in_out = in_out;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
