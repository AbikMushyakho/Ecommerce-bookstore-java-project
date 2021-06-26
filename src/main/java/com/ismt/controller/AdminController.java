package com.ismt.controller;

import com.ismt.model.Category;
import com.ismt.model.Order;
import com.ismt.model.Product;
import com.ismt.repo.CategoryRepo;
import com.ismt.repo.OrderRepo;
import com.ismt.repo.ProductRepo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin","/order","/category","/product"})
public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    OrderRepo orderRepo = new OrderRepo();
    CategoryRepo categoryRepo =new CategoryRepo();
    ProductRepo productRepo =new ProductRepo();
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String newPath = request.getServletPath();
        System.out.println(newPath);
        if (request.getServletPath().equals("/admin" ) || request.getServletPath().equals("/category")){

//            HttpSession session = request.getSession();
//             session.setAttribute("titleName", "CATEGORY");
//            response.sendRedirect("category.jsp");

            List<Category> categories = categoryRepo.listCategories();
            request.setAttribute("categoryList", categories );
            request.setAttribute("titleName", "Category");
            RequestDispatcher eg =request.getRequestDispatcher("category.jsp");
            eg.forward(request,response);
        }
        else if (request.getServletPath().equals("/order")){

//            HttpSession session = request.getSession();
//             session.setAttribute("titleName", "Order");
//            response.sendRedirect("order.jsp");
            List<Order> orders = orderRepo.listOrder();
            request.setAttribute("titleName", "Order");
            request.setAttribute("orderList",orders);
            RequestDispatcher eg =request.getRequestDispatcher("order.jsp");
            eg.forward(request,response);
        }
        else if (request.getServletPath().equals("/product")){

//            HttpSession session = request.getSession();
//             session.setAttribute("titleName", "Order");
//            response.sendRedirect("order.jsp");
            List<Product> showProducts = productRepo.listProduct();
            request.setAttribute("shownProduct",showProducts);
            request.setAttribute("titleName", "Product");
            RequestDispatcher eg =request.getRequestDispatcher("product.jsp");
            eg.forward(request,response);
        }
    }

}
