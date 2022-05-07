package com.thn.calculator.service;

import javax.servlet.http.Cookie;

public class CookieService {

    public CookieService() {
    }

    public String getToken(Cookie[] cookies) {
        String token = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }
        return token;
    }

    public String getLogin(Cookie[] cookies) {
        String login = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login")) {
                login = cookie.getValue();
            }
        }
        return login;
    }
}
