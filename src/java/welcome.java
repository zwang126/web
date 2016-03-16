/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.web1.userBean;
import com.web1.userBeanAction;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zan_Wang
 */
@WebServlet(urlPatterns = {"/welcome"})
public class welcome extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession hs = request.getSession(true);
        String username = (String) hs.getAttribute("username");
        
        if (username == null || username.equals("")) {

            response.sendRedirect("hello?info=error1");
        } else {
            try {
                ArrayList<userBean> al = new ArrayList<userBean>();
                userBeanAction uba = new userBeanAction();
                al = uba.getUserByPage(3, 1);
                out.println("<table border = 1");
                out.println("<tr><th>id</th><th>name</th><th>password</th><th>email</th><th>grade</th></tr>");
                
                for(userBean u : al){
                    out.println("<tr>");
                    out.println("<td>"+u.getUserId()+"</td>");
                    out.println("<td>"+u.getUsername()+"</td>");
                    out.println("<td>"+u.getPassword()+"</td>");
                    out.println("<td>"+u.getEmail()+"</td>");
                    out.println("<td>"+u.getGrade()+"</td>");
                    out.println("</tr>");
                }
                
                out.println("</table>");
                /* TODO output your page here. You may use following sample code. */
                //show welcome message.
                //String username1 = request.getParameter("username");
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet welcome</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>welcome " + username + " to this page</h1>");
                out.println("</body>");
                out.println("</html>");
            }   catch (Exception e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
