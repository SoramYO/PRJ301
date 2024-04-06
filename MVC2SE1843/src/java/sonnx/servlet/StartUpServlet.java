/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonnx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonnx.registration.RegistrationDAO;
import sonnx.registration.RegistrationDTO;
import sonnx.until.ApplicationConstants;

/**
 *
 * @author Son
 */
@WebServlet(name = "StartUpServlet", urlPatterns = {"/StartUpServlet"})
public class StartUpServlet extends HttpServlet {

    //private final String SEARCH_PAGE = "search.jsp";
    //private final String LOGIN_PAGE = "login.html";

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
        //0.get current context scope & siteMaps
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.LOGIN_PAGE);
        //String url = LOGIN_PAGE;
        try {
//            //1. check exist cookies
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
//                //2. get Name and Value(username and password)
//                Cookie lastCookie = cookies[cookies.length - 1];
//                String username = lastCookie.getName();
//                String password = lastCookie.getValue();
//                //3. check authenticate of username and password (call DAO)
//                RegistrationDAO dao = new RegistrationDAO();
//                RegistrationDTO result = dao.checkLogin(username, password);
////                boolean result = dao.checkLogin(username, password);
//                //4. process result
//                if (result!= null) {
//                    url =  SEARCH_PAGE;
//                    //sending cookie to client
//                    Cookie cookie = new Cookie(username, password);
//                    cookie.setMaxAge(3 * 60);
//                    response.addCookie(cookie);
//                }
//
//            }//end cookies have existed
            HttpSession session = request.getSession();
            if (session != null) {
                RegistrationDTO user = (RegistrationDTO) session.getAttribute("USER");
                if (user != null) {
                    String username = user.getUsername();
                    String password = user.getPassword();
                    RegistrationDAO dao = new RegistrationDAO();
                    RegistrationDTO result = dao.checkLogin(username, password);
                    if (result != null) {
                        url = siteMaps.getProperty(ApplicationConstants.LoginFeature.SEARCH_PAGE);
                    }
                }
            }
        } catch (NamingException ex) {
            String msg = ex.getMessage();
            log("StartUpServlet _ Naming " + msg);
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("StartUpServlet _ SQL " + msg);
        } finally {
            response.sendRedirect(url);// can use other method
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
