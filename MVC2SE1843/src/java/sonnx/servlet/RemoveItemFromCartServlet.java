/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonnx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonnx.cart.CartObject;
import sonnx.product.ProductDAO;
import sonnx.product.ProductDTO;
import sonnx.until.ApplicationConstants;

/**
 *
 * @author Son
 */
@WebServlet(name = "RemoveItemFromCartServlet", urlPatterns = {"/RemoveItemFromCartServlet"})
public class RemoveItemFromCartServlet extends HttpServlet {

    //private final String ERROR_PAGE = "error.html";
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
        String url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.ERROR_PAGE);
        //String url = ERROR_PAGE;
        try {
            //1.Custom goes to his/her cart
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. Cus take his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cus take items
                    String[] skus = request.getParameterValues("chkItem");
                    if (skus != null) {
                        for (String sku : skus) {
                            ProductDAO dao = new ProductDAO();
                            ProductDTO item = dao.getProductDetailsById(sku);
                            cart.removeItemFromCart(item);
                        }
                        session.setAttribute("CART", cart);
                        url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.DISPATCH_CONTROLLER)
                                + "?btAction=View%20your%20cart";
                    }//end of items has existed
                    //Map<String, Integer> items = cart.getItems();
//                    if (items != null) {
//                        //4.Cus remove items from cart  
//                        String[] selectedItems = request.getParameterValues("chkItem");
//                        if (selectedItems != null) {
//                            for (String item : selectedItems) {
//                                cart.removeItemFromCart(item);
//                            }
//                            session.setAttribute("CART", cart);
//                        }//selected itrem has exitetd
//                    }//item have exitsted
                }//cart has exist
            }//session has existed
        } catch (NamingException ex) {
            String msg = ex.getMessage();
            log("RemoveItemsFromCartServlet _ Naming " + msg);
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("RemoveItemsFromCartServlet _ SQL " + msg);
        } finally {
            response.sendRedirect(url);
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
