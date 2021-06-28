package com.ismt.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = {"/ecommerce", "/checkout","/eg","/login"})
public class FrontEndController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();
        System.out.println(path);
        String logoName = "ISMT ECOMMERCE APPS";
        if (request.getServletPath().equals("/ecommerce")) {
            HttpSession session =request.getSession();
            session.setAttribute("companyName", logoName);
            response.sendRedirect("index.jsp");
        }
        else if(request.getServletPath().equals("/eg")){

//            HttpSession session =request.getSession();
//            session.setAttribute("companyName", logoName);
//            response.sendRedirect("eg.jsp");
            request.setAttribute("company", "ISMT ECOMMERCE APPS");
            RequestDispatcher eg =request.getRequestDispatcher("eg.jsp");
            eg.forward(request,response);
        }
        else if(request.getServletPath().equals("/login")){
            RequestDispatcher eg =request.getRequestDispatcher("login.jsp");
            eg.forward(request,response);
        }
    }
}
