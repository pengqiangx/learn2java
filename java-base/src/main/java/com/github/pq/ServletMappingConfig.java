package com.github.pq;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoniu 2019/2/10.
 * 你应该有些感觉了吧？
 *
 * 我们在servlet开发中，会在web.xml中通过<servlet></servlet>和<servlet-mapping></servlet-mapping>来进行指定哪个URL交给哪个servlet进行处理
 */
public class ServletMappingConfig {
    public static List<ServletMapping> servletMappingList = new ArrayList();

    static {
        servletMappingList.add(new ServletMapping("findGirl","/girl","com.github.pq.foo.FindGirlServlet"));
        servletMappingList.add(new ServletMapping("helloWorld","/world","com.github.pq.foo.HelloWorldServlet"));
    }
}
