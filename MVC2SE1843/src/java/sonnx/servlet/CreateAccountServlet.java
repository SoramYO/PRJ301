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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sonnx.registration.RegistrationDAO;
import sonnx.registration.RegistrationDTO;
import sonnx.registration.RegitrationCreateError;
import sonnx.until.ApplicationConstants;

/**
 *
 * @author Son
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    //private final String CREATE_NEW_ACCOUNT_PAGE = "createAccount.jsp";
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
        String url = siteMaps.getProperty(ApplicationConstants.LoginFeature.CREATE_NEW_ACCOUNT_PAGE);
        //1. get all parameter
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");
        boolean foundErr = false;
        RegitrationCreateError errors = new RegitrationCreateError();
        //String url = CREATE_NEW_ACCOUNT_PAGE;
        try {
            //2 check all user's error
            if(username.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setUsernameLenghtError("Username is must 6 > and < 20");
            }
            if(password.trim().length() < 6 || password.trim().length() > 20) {
                foundErr = true;
                errors.setPasswordLenghtError("Pass is must 6 > and < 30");
            } else if(!confirm.trim().equals(password)) {
                foundErr = true;
                errors.setConfirmNotMatched("Confirm not match password");
            }
            if(fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                foundErr = true;
                errors.setFullNameLenghtError("Full name must be in 6 > and < 50");
            }
            if (foundErr) {
                //catching attribute and tranfer to error page
                request.setAttribute("CREATE_ERRORS", errors);
            } else {
                //3. call DAO
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createAccount(dto);
                if (result) {
                    url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.LOGIN_PAGE);
                }
            }

            //4 process result
        } catch (NamingException ex) {
            log("CreateAccountServlet _ Naming" + ex.getMessage());
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateAccountServlet _ SQL" + ex.getMessage());
            if(msg.contains("duplicate")){
                errors.setUserNameIsExisted(username + "is existed");
                request.setAttribute("CREATE_ERRORS", ex);
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
