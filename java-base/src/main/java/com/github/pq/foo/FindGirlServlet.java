package com.github.pq.foo;

import com.github.pq.MyRequest;
import com.github.pq.MyResponse;
import com.github.pq.MyServlet;

import java.io.IOException;

/**
 * @author xiaoniu 2019/2/10.
 */
public class FindGirlServlet extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get girl....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post girl....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
