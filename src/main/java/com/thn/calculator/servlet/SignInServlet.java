package com.thn.calculator.servlet;

import com.thn.calculator.storage.InMemoryAuthStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/authentication.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        InMemoryAuthStorage inMemoryAuthStorage = new InMemoryAuthStorage();
        if (inMemoryAuthStorage.isAuthenticated(login, password)) {
            resp.sendRedirect("calc");
        } else {
            req.setAttribute("Message", "You're not registered");
            req.getRequestDispatcher("/pages/authentication.jsp").forward(req, resp);
        }
    }
}
