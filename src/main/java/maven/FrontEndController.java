package maven;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = {"/ecommerce", "/checkout","/eg"})
public class FrontEndController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();
        System.out.println(path);
        String logoName = "ISMT ECOMMERCE APPS";
        if (request.getServletPath().equals("/ecommerce")) {
            HttpSession session =request.getSession();
            session.setAttribute("companyName", logoName);
            response.sendRedirect("home.jsp");
        }
        else if(request.getServletPath().equals("/eg")){

            HttpSession session =request.getSession();
            session.setAttribute("companyName", logoName);
            response.sendRedirect("eg.jsp");
//            RequestDispatcher eg =request.getRequestDispatcher("/eg.jsp");
//            eg.forward(request,response);
        }
    }
}
