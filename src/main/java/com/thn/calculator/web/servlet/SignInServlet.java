package com.thn.calculator.web.servlet;

import com.thn.calculator.security.JWTManager;
import com.thn.calculator.service.CookieService;
import com.thn.calculator.storage.SQLUserStorage;
import com.thn.calculator.web.info.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SignInServlet.class);
    private static final SQLUserStorage sqlUserStorage = new SQLUserStorage();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        JWTManager jwtManager = new JWTManager();
        CookieService cookieService = new CookieService();
        String token = cookieService.getToken(cookies);
        String login = cookieService.getLogin(cookies);
        if (token != null && login != null) {
            if (jwtManager.verifyToken(token, login)) {
                req.getSession().setAttribute("id", sqlUserStorage.getId(login));
                resp.sendRedirect("calc");
            }
        } else req.getRequestDispatcher("/pages/authentication.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JWTManager jwtManager = new JWTManager();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (sqlUserStorage.isAuthenticated(login, password)) {
            String token = jwtManager.createToken(login);
            Cookie cookie1 = new Cookie("token", token);
            Cookie cookie2 = new Cookie("login", login);
            cookie1.setMaxAge(1800);
            cookie2.setMaxAge(1800);
            resp.addCookie(cookie1);
            resp.addCookie(cookie2);
            req.getSession().setAttribute("id", sqlUserStorage.getId(login));
            resp.sendRedirect("calc");
            logger.info("User was successfully logged in by credos");
        } else {
            req.setAttribute("Message", Messages.WRONG_PASS.getText());
            req.getRequestDispatcher("/pages/authentication.jsp").forward(req, resp);
        }
    }
}
