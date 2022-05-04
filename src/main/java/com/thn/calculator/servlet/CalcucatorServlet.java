package com.thn.calculator.servlet;

import com.thn.calculator.entity.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calc")
public class CalcucatorServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SignUpServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/calculator.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Calculator calculator = new Calculator();

        double var1 = Double.parseDouble(req.getParameter("var1"));
        double var2 = Double.parseDouble(req.getParameter("var2"));
        double result;
        String operation = req.getParameter("operation");

        switch (operation) {
            case "sum":
                result = calculator.sum(var1, var2);
                req.setAttribute("result", result);
                logger.info("User used an operation of sum");
                break;
            case "minus":
                result = calculator.minus(var1, var2);
                req.setAttribute("result", result);
                logger.info("User used an operation of minus");
                break;
            case "multiply":
                result = calculator.multiply(var1, var2);
                req.setAttribute("result", result);
                logger.info("User used an operation of multiply");
                break;
            case "divide":
                result = calculator.divide(var1, var2);
                req.setAttribute("result", result);
                logger.info("User used an operation of divide");
                break;
        }
        req.getRequestDispatcher("/pages/calculator.jsp").forward(req, resp);
    }
}
