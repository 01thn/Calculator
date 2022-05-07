package com.thn.calculator.web.filter;

import com.thn.calculator.security.JWTManager;
import com.thn.calculator.service.CookieService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter("/calc")
public class AuthFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        CookieService cookieService = new CookieService();
        JWTManager jwtManager = new JWTManager();
        Cookie[] cookies = req.getCookies();
        String token = cookieService.getLogin(cookies);
        String login = cookieService.getLogin(cookies);
        if (jwtManager.verifyToken(token, login)) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect("sign-in");
        }
    }
}
