package com.dawncoody.reggie.filter;

import com.alibaba.fastjson2.JSON;
import com.dawncoody.reggie.common.BaseContext;
import com.dawncoody.reggie.common.R;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

/**
 * @description: 检查用户是否已经完成登录
 * @author: dawncoody
 * @create: 2023-03-23 11:01
 */
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    // 路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 1.获取本次请求的URI
        String requestURI = httpServletRequest.getRequestURI();
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login",
                // "/doc.html",
                // "/webjars/**",
                // "/swagger-resources",
                // "/v2/api-docs"
        };
        // 2.判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        // 3.如果不需要处理，则直接放行
        if (check) {
            chain.doFilter(request, response);
            return;
        }
        // 4-1.判断登录状态，如果已登录，则直接放行
        if (httpServletRequest.getSession().getAttribute("employee") != null) {
            Long empId = (Long) httpServletRequest.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            chain.doFilter(request, response);
            return;
        }

        // 4-2.判断登录状态，如果已登录，则直接放行
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            Long userId = (Long) httpServletRequest.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            chain.doFilter(request, response);
            return;
        }
        // 5.如果未登录则返回未登录结果，通过输出流的方式想客户端页面响应数据
        httpServletResponse.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
