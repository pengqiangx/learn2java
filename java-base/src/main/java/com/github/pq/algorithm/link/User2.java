package com.github.pq.algorithm.link;

import java.io.Serializable;

public class User2 implements Serializable {

    private String username;
    private static String pwd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getPwd() {
        return pwd;
    }

    public static void setPwd(String pwd) {
        User2.pwd = pwd;
    }

}
