package com.thn.calculator.web.servlet;

import com.thn.calculator.storage.SQLUserStorage;
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

@WebServlet("/change")
public class ChangePassServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ChangePassServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/change.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SQLUserStorage sqlUserStorage = new SQLUserStorage();
        UserValidator userValidator = new UserValidator();
        String login = req.getParameter("login");
        String newPass = req.getParameter("password");
        if (sqlUserStorage.find(login)) {
            if (userValidator.passwordValidate(newPass)) {
                req.setAttribute("Message", Messages.PASS_REQS.getText());
                req.getRequestDispatcher("/pages/change.jsp").forward(req, resp);
                logger.error("User " + login + " inputted incorrect type of password");
            }
            sqlUserStorage.changePass(login, newPass);
            resp.sendRedirect("sign-in");
            logger.info("User " + login + " changed his password");
        } else {
            req.setAttribute("Message", Messages.USER_NOT_FOUND.getText() +
                    "<a href=\"sign-up\">" + Messages.CREATE_ACC.getText() + "</a>");
            req.getRequestDispatcher("/pages/change.jsp").forward(req, resp);
            logger.error("User tried to touch user which doesn't exist");
        }
    }
}
