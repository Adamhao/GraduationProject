package cn.edu.qdu.config;

import cn.edu.qdu.model.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 过滤器，判断是否有权限
 * Created by Adam on 2019/5/9 22:11.
 */
@WebFilter(filterName = "authFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        String ip = getRequesterIp(request);
        Object temp = request.getSession().getAttribute("user");
        logger.info("ip:" + ip +",请求路径:" + uri +",登录状态:" + (temp == null ? "未登录" : "已登录,用户名:" + (((Admin)temp).getUsername())));
        if (uri.contains(".")) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            if ("/admin/".equals(uri) || "/admin/login".equals(uri)) {
                chain.doFilter(servletRequest, servletResponse);
            } else {
                HttpSession session = request.getSession();
                Object admin = session.getAttribute("user");
                if (admin == null || ((Admin) admin).getEmail() == null) {
                    request.setAttribute("result", "你没有权限访问该页面，登陆后重试!");
                    HttpServletResponse response = (HttpServletResponse) servletResponse;
                    request.getRequestDispatcher("/admin/").forward(servletRequest, servletResponse);
                } else {
                    chain.doFilter(servletRequest, servletResponse);
                }
            }

        }
    }

    private String getRequesterIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
