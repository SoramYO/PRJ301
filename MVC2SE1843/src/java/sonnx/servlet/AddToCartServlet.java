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
import javax.servlet.http.HttpSession;
import sonnx.cart.CartObject;
import sonnx.product.ProductDAO;
import sonnx.product.ProductDTO;
import sonnx.registration.RegistrationDAO;
import sonnx.until.ApplicationConstants;

/**
 *
 * @author Son
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    //private final String PRODUCT_PAGE  = "DispatchServlet?btAction=showProduct";
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
        String searchvalue = request.getParameter("txtProductname");
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.DISPATCH_CONTROLLER) + "?btAction=Search Product" + "&txtProductname=" + searchvalue;;
        //0.1 get all parameter

        //String url = PRODUCT_PAGE;
        boolean result = false;
        try {
            //1. Customer goes to the cart place 
            HttpSession session = request.getSession();
            //2. Customer take cart
            CartObject cart = (CartObject) session.getAttribute("CART");
            //call DAO
            if (cart == null) {
                cart = new CartObject();
            }//cart has not existed
            //3. Customer drop item into cart
//            String item = request.getParameter("cboBook");
//            cart.addItemToCart(item);
//            session.setAttribute("CART", cart);

            String sku = request.getParameter("checkProduct");
            ProductDAO dao = new ProductDAO();
            ProductDTO items = dao.getProductDetailsById(sku);
            String quantityString = request.getParameter("txtProductQuantity");
            if (!quantityString.trim().isEmpty()) {
                int quantity = Integer.parseInt(quantityString);
                result = cart.addItemToCart(items, quantity);
            }
            session.setAttribute("CART", cart);

            //4. Customer continuely goes shopping
            if (result) {
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.DISPATCH_CONTROLLER)
                        + "?btAction=Search Product"
                        + "&txtProductname=" + searchvalue;
            }
            //URLRewirting để gọi lại trang product
        } catch (NamingException ex) {
            String msg = ex.getMessage();
            log("AddToCartServlet _ Naming " + msg);
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("AddToCartServlet _ SQL " + msg);
        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);

            response.sendRedirect(url);//section van con
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
