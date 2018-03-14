package com.chaimao.designer.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: cmxu
 * @Description: 跨域过滤器
 * @Date： create in 21:23 2018/3/12
 * @Modified By:
 */
@Component
public class SimpleCORSFilter implements Filter {

    @Override
    public void destroy() {
//TODO Auto-generated method stub  

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException

    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
//response.setHeader("Access-Control-Allow-Origin","*");  
        response.setHeader("Access-Control-Allow-Methods", "POST,GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Cache-Control,Pragma,Content-Type,Token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
//TODO Auto-generated method stub  

    }

}
