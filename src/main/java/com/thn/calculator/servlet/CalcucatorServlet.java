package com.thn.calculator.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calc")
public class CalcucatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/calculator.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double var1 = Double.parseDouble(req.getParameter("var1"));
        double var2 = Double.parseDouble(req.getParameter("var2"));
        String operation = req.getParameter("operation");
        switch (operation) {
            case "sum":
                req.setAttribute("result", var1 + var2);
                break;
        }
        req.getRequestDispatcher("/pages/calculator.jsp").forward(req, resp);
    }
}
