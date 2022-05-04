package com.thn.calculator.servlet;

import com.thn.calculator.entity.Calculator;
import com.thn.calculator.storage.SQLOperationStorage;
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
    private static final Logger logger = LoggerFactory.getLogger(CalcucatorServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("Hello", "Hello, "+req.getSession().getAttribute("login"));
        req.getRequestDispatcher("/pages/calculator.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Calculator calculator = new Calculator();
        SQLOperationStorage sqlOperationStorage = new SQLOperationStorage();

        long id = (long) req.getSession().getAttribute("id");
        double var1 = Double.parseDouble(req.getParameter("var1"));
        double var2 = Double.parseDouble(req.getParameter("var2"));
        double result;
        String operation = req.getParameter("operation");

        switch (operation) {
            case "sum":
                result = calculator.sum(var1, var2);
                req.setAttribute("result", result);
                sqlOperationStorage.save(id,var1,var2,"sum",result);
                logger.info("User used an operation of sum");
                break;
            case "minus":
                result = calculator.minus(var1, var2);
                req.setAttribute("result", result);
                sqlOperationStorage.save(id,var1,var2,"minus",result);
                logger.info("User used an operation of minus");
                break;
            case "multiply":
                result = calculator.multiply(var1, var2);
                req.setAttribute("result", result);
                sqlOperationStorage.save(id,var1,var2,"multiply",result);
                logger.info("User used an operation of multiply");
                break;
            case "divide":
                result = calculator.divide(var1, var2);
                req.setAttribute("result", result);
                sqlOperationStorage.save(id,var1,var2,"divide",result);
                logger.info("User used an operation of divide");
                break;
        }
        req.getRequestDispatcher("/pages/calculator.jsp").forward(req, resp);
    }
}
