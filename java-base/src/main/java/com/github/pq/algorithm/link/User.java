package com.github.pq.algorithm.link;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private transient String pwd;
    private transient String cardNo;
    private transient String phone;

    public User() {
    }

    public User(String username, String pwd, String cardNo, String phone) {
        this.username = username;
        this.pwd = pwd;
        this.cardNo = cardNo;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
