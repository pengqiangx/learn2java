package com.github.pq;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaoniu 2019/2/10.
 */
@Getter
@Setter
public class ServletMapping {

    private String servletName;

    private String url;

    private String clazz;

    public ServletMapping(String servletName, String url, String clazz) {
        this.servletName = servletName;
        this.url = url;
        this.clazz = clazz;
    }
}
