package com.thn.calculator.filter;

import com.thn.calculator.security.JWTManager;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;
        Cookie[] cookies = httpReq.getCookies();
        String token = null;
        String login = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) token = cookie.getValue();
            if (cookie.getName().equals("login")) login = cookie.getValue();
        }
        if ((token==null && login==null) || !JWTManager.verifyToken(token,login)){
            httpResp.sendRedirect("sign-in");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
