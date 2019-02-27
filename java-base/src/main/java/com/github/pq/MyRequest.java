package com.github.pq;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiaoniu 2019/2/10.
 * 我们通过输入流，对HTTP协议进行解析，拿到了HTTP请求头的方法以及URL
 */
@Getter
@Setter
public class MyRequest {

    private String url;

    private String method;

    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        length =  inputStream.read(httpRequestBytes);
        if(length   > 0){
            httpRequest = new String(httpRequestBytes,0,length);
        }

        String httpHead = httpRequest.split("\n")[0];
        url = httpHead.split("\\s")[1];
        method = httpHead.split("\\s")[0];
        System.out.println("url="+url+",method="+method);
    }

}
