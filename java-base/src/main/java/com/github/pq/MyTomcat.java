package com.github.pq;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoniu 2019/2/11.

 *
 *Tomcat是非常流行的Web Server，它还是一个满足Servlet规范的容器。那么想一想，Tomcat和我们的Web应用是什么关系？
 * 从感性上来说，我们一般需要把Web应用打成WAR包部署到Tomcat中，在我们的Web应用中，
 * 我们要指明URL被哪个类的哪个方法所处理（不论是原始的Servlet开发，还是现在流行的Spring MVC都必须指明）。
 * 由于我们的Web应用是运行在Tomcat中，那么显然，请求必定是先到达Tomcat的。Tomcat对于请求实际上会进行下面的处理：
 * 第一：提供Socket服务
 * Tomcat的启动，必然是Socket服务，只不过它支持HTTP协议而已！
 * 这里其实可以扩展思考下，Tomcat既然是基于Socket，那么是基于BIO or NIO or AIO呢？
 *
 * 第二：进行请求的分发
 * 要知道一个Tomcat可以为多个Web应用提供服务，那么很显然，Tomcat可以把URL下发到不同的Web应用。
 *
 * 第三：需要把请求和响应封装成request/response
 * 我们在Web应用这一层，可从来没有封装过request/response的，我们都是直接使用的，这就是因为Tomcat已经为你做好了！

 */
public class MyTomcat {

    private int port = 8080;

    private Map<String,String> urlServletMap = new HashMap<String,String>();

    public MyTomcat(int port) {
        this.port = port;
    }

    public void start() {
        //初始化，url与对应处理的servlet的关系
        initServletMapping();
        ServerSocket serverSocket = null;
        try {
            serverSocket= new ServerSocket(port);
            while(true){
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                MyRequest myRequest = new MyRequest(inputStream);
                MyResponse myResponse = new MyResponse(outputStream);
                //请求分发
                dispatch(myRequest,myResponse);

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void dispatch(MyRequest myRequest, MyResponse myResponse) {
        String clazz = urlServletMap.get(myRequest.getUrl());
        //* 你能够看到Tomcat的处理流程：把URL对应处理的Servlet关系形成，解析HTTP协议，封装请求/响应对象，利用反射实例化具体的Servlet进行处理即可。
        //反射
        Class<MyServlet> myServletClass = null;
        try {
            System.out.println("clazz="+clazz);
            if(clazz == null || clazz.length() < 1){
                return;
            }
            myServletClass = (Class<MyServlet>) Class.forName(clazz);
            MyServlet myServlet = null;
            myServlet = myServletClass.newInstance();

            myServlet.service(myRequest,myResponse);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void initServletMapping() {
        for(ServletMapping servletMapping: ServletMappingConfig.servletMappingList){
            urlServletMap.put(servletMapping.getUrl(),servletMapping.getClazz());
        }
    }

    public static void main(String[] args) {
        new MyTomcat(8080).start();
    }
}
