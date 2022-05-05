package com.thn.calculator.servlet;

import com.thn.calculator.security.JWTManager;
import com.thn.calculator.storage.InMemoryAuthStorage;
import com.thn.calculator.storage.SQLAuthStorage;
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
    private static final Logger logger = LoggerFactory.getLogger(SignUpServlet.class);
    private static final SQLAuthStorage sqlAuthStorage = new SQLAuthStorage();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String token = null;
        String login = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
            if (cookie.getName().equals("login")){
                login = cookie.getValue();
            }
        }
        if (token != null && login != null) {
            if(JWTManager.verifyToken(token,login)){
                req.getSession().setAttribute("id", sqlAuthStorage.getId(login));
                resp.sendRedirect("calc");
            }
        } else req.getRequestDispatcher("/pages/authentication.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        InMemoryAuthStorage inMemoryAuthStorage = new InMemoryAuthStorage();
        if (inMemoryAuthStorage.isAuthenticated(login, password) || sqlAuthStorage.isAuthenticated(login, password)) {
            String token = JWTManager.createToken(login);
            Cookie cookie1 = new Cookie("token", token);
            Cookie cookie2 = new Cookie("login", login);
            cookie1.setMaxAge(1800);
            cookie2.setMaxAge(1800);
            resp.addCookie(cookie1);
            resp.addCookie(cookie2);
            req.getSession().setAttribute("id", sqlAuthStorage.getId(login));
            resp.sendRedirect("calc");
            logger.info("User was successfully logged in by credos");
        } else {
            req.setAttribute("Message", "Something went wrong. Try to reset password");
            req.getRequestDispatcher("/pages/authentication.jsp").forward(req, resp);
        }
    }
}
