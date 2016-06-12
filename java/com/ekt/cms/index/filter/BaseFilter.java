package com.ekt.cms.index.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import com.ekt.cms.utils.ThreadLocalUtil;

import java.io.IOException;

/**
 * @author mili
 * @time 20160406
 *   基础过滤器，所有的请求先经过这个过滤器
 */
public class BaseFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        ThreadLocalUtil.setBasePath(basePath);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
