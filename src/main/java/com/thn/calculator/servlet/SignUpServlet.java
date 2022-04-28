package com.thn.calculator.servlet;

import com.thn.calculator.storage.InMemoryRegisterStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InMemoryRegisterStorage inMemoryRegisterStorage = new InMemoryRegisterStorage();

        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (!inMemoryRegisterStorage.isValid(login, name, password)) {
            req.setAttribute("Message", "Check your input data. " +
                    "Login and name gotta consist at least 3 char. Password at least 6 char and no special chars");
            req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
            return;
        }

        if (!inMemoryRegisterStorage.find(login)) {
            inMemoryRegisterStorage.save(login, name, password);
            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute("Message", "User with such login exists");
            req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
        }
    }
}
