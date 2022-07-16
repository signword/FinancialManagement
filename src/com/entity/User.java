package com.entity;

public class User {
    private String userId;
    private String userName;
    private String userPwd;
    private String userSta;
    private String instituId;
    private String admin;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserSta() {
        return userSta;
    }

    public void setUserSta(String userSta) {
        this.userSta = userSta;
    }

    public String getInstituId() {
        return instituId;
    }

    public void setInstituId(String instituId) {
        this.instituId = instituId;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
