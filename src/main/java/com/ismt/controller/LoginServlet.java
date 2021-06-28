package com.ismt.controller;

import com.ismt.model.User;
import com.ismt.repo.UserRepo;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/signin", "/signup","/login-user"})
public class LoginServlet extends HttpServlet {

    UserRepo userRepo = new UserRepo();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = null;

        try {
            user = userRepo.checkLogin(username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        if(!(user.equals(null))){
//            request.setAttribute("userInfo", user);
//            response.sendRedirect("/admin");

            HttpSession session =request.getSession();

            session.setAttribute("userInfo",  user.getName() + "  "+ " "+ "role:" + user.getRole());
            response.sendRedirect("/admin");
        }
        else{
            response.sendRedirect("/login");

        }




    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getServletPath().equals("/signin")) {

            RequestDispatcher view = request.getRequestDispatcher("login/signin.jsp");
            view.forward(request, response);

        }


    }

}
