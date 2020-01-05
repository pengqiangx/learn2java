package com.github.pq.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author xiaoniu 2019/7/9.
 */
public class DemoConnection {

   public void test1() throws SQLException {
       // 1.通过DriverManger注册驱动，注意此时Driver是在com.mysql.jdbc包中
       DriverManager.registerDriver(new Driver());
       /**
        * 2.通过DriverManager获取连接对象
        *
        * jdbc:mysql://：这是固定的写法，表示使用jdbc连接mysql数据库
        * localhost：ip地址，本地可以写成localhost。
        * 3306：mysql的端口号。
        * xia：数据库的名字。
        * 第一个root：mysql的用户名
        * 第二个root：mysql的密码。
        */
       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xia", "root", "123456");


   }
}
