package cn.edu.qdu.config;

import cn.edu.qdu.model.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 过滤器，判断是否有权限
 * Created by Adam on 2019/5/9 22:11.
 */
@WebFilter(filterName="authFilter",urlPatterns={"/*"})
public class AuthFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        System.out.println(uri);
        if(uri.contains(".")) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            if("/admin/".equals(uri) || "/admin/login".equals(uri)) {
                chain.doFilter(servletRequest, servletResponse);
            } else {
                HttpSession session = request.getSession();
                Object admin = session.getAttribute("user");
                if(admin == null || ((Admin)admin).getEmail() == null) {
                    request.setAttribute("result","你没有权限访问该页面，登陆后重试!");
                    HttpServletResponse response = (HttpServletResponse) servletResponse;
                    request.getRequestDispatcher("/admin/").forward(servletRequest,servletResponse);
                } else {
                    chain.doFilter(servletRequest, servletResponse);
                }
            }

        }

    }
}
