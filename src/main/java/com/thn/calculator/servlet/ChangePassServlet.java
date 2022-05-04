package com.thn.calculator.servlet;

import com.thn.calculator.service.UserValidateService;
import com.thn.calculator.storage.SQLChangeStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/change")
public class ChangePassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/change.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SQLChangeStorage sqlChangeStorage = new SQLChangeStorage();
        String login = req.getParameter("login");
        String newPass = req.getParameter("password");
        if(sqlChangeStorage.find(login)){
            if(UserValidateService.passwordValidate(newPass)){
                req.setAttribute("Message", "Password must consist at least 6 char and no special chars");
                req.getRequestDispatcher("/pages/change.jsp").forward(req,resp);
            }
            sqlChangeStorage.save(login, newPass);
            resp.sendRedirect("sign-in");
        } else {
            req.setAttribute("Message", "Looks like user does not exist. <a href=\"sign-up\">Create an account</a>");
            req.getRequestDispatcher("/pages/change.jsp").forward(req,resp);
        }
    }
}
