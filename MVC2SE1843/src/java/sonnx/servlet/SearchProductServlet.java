/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonnx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sonnx.product.ProductDAO;
import sonnx.product.ProductDTO;
import sonnx.until.ApplicationConstants;

/**
 *
 * @author Soram
 */
@WebServlet(name = "SearchProductServlet", urlPatterns = {"/SearchProductServlet"})
public class SearchProductServlet extends HttpServlet {

    //private final String RESULT_PAGE = "product.jsp";
    //private final String PRODUCT_PAGE = "DispatchServlet?btAction=showProduct";
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
        String searchValue = request.getParameter("txtProductname");
        //0.get current context scope & siteMaps
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(ApplicationConstants.ShoppingFeature.SHOPPING_PAGE);
        //String url = RESULT_PAGE;
        try {
            //1. check valid search value
            if (!searchValue.trim().isEmpty()) {
                // 2. Call model
                // 2.1 Call new DAO
                ProductDAO dao = new ProductDAO();
                // 2.2 Call method
                dao.searchProduct(searchValue);
                // 3. Process result
                List<ProductDTO> result = dao.getProducList();
                request.setAttribute("SEARCH_PRODUCT_RESULT", result);
            }//end of search value is valid
            else {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.DISPATCH_CONTROLLER) + "?btAction=showProduct" + "&txtProductname=" + searchValue;;
            }
        } catch (NamingException ex) {
            String msg = ex.getMessage();
            log("SearchProductServlet _ Naming " + msg);
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("SearchProductServlet _ SQL " + msg);
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
