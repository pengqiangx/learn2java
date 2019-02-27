package com.github.pq.foo;

import com.github.pq.MyRequest;
import com.github.pq.MyResponse;
import com.github.pq.MyServlet;

import java.io.IOException;

/**
 * @author xiaoniu 2019/2/10.
 */
public class HelloWorldServlet extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get world....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post world....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
