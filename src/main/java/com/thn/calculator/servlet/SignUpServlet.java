package com.thn.calculator.servlet;

import com.thn.calculator.service.UserValidateService;
import com.thn.calculator.storage.InMemoryRegisterStorage;
import com.thn.calculator.storage.SQLRegisterStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(SignUpServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InMemoryRegisterStorage inMemoryRegisterStorage = new InMemoryRegisterStorage();
        SQLRegisterStorage sqlRegisterStorage = new SQLRegisterStorage();

        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (!UserValidateService.loginAndNameValidate(login, name)
                || UserValidateService.passwordValidate(password)) {
            req.setAttribute("Message", "Check your input data. " +
                    "Login and name gotta consist at least 3 char. Password at least 6 char and no special chars");
            req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
            return;
        }

        if (!inMemoryRegisterStorage.find(login) || !sqlRegisterStorage.find(login)) {
            inMemoryRegisterStorage.save(login, name, password);
            sqlRegisterStorage.save(login, name, password);
            logger.info("New user has been registered");
            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute("Message", "User with such login exists");
            req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
        }
    }
}
