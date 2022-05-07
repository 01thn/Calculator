package com.thn.calculator.web.servlet;

import com.thn.calculator.storage.InMemoryUserStorage;
import com.thn.calculator.web.info.Messages;
import com.thn.calculator.web.validator.UserValidator;
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
        InMemoryUserStorage inMemoryUserStorage = new InMemoryUserStorage();
//        SQLUserStorage sqlUserStorage = new SQLUserStorage();
        UserValidator userValidator = new UserValidator();

        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (!userValidator.loginAndNameValidate(login, name)
                || userValidator.passwordValidate(password)) {
            req.setAttribute("Message", Messages.INPUT_DATA.getText());
            req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
            return;
        }

        if (!inMemoryUserStorage.find(login)) {
            inMemoryUserStorage.save(login, name, password);
            logger.info("New user has been registered");
            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute("Message", Messages.COLLISION.getText());
            req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
        }
    }
}
