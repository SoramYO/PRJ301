/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonnx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
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
import sonnx.OrderDetail.OrderDetailDAO;
import sonnx.OrderDetail.OrderDetailDTO;
import sonnx.cart.CartObject;
import sonnx.product.ProductDAO;
import sonnx.product.ProductDTO;
import sonnx.tbl_Order.tblOrderDAO;
import sonnx.tbl_Order.tblOrderDTO;
import sonnx.until.ApplicationConstants;

/**
 *
 * @author Soram
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    //private final String ERROR_PAGE = "error.html";
    //private final String VIEW_ORDER_PAGE = "order.jsp";

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
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    Map<ProductDTO, Integer> items = cart.getItems();
                    if (items != null) {
                        //create order
                        tblOrderDAO orderDAO = new tblOrderDAO();
                        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                        String orderID = "HD" + String.format("%03d", orderDAO.countOrder() + 1);
                        Date date = new Date();
                        tblOrderDTO order = new tblOrderDTO(orderID, date, 0);
                        boolean result = orderDAO.createNewOrder(order);
                        Map<ProductDTO, Integer> checkOutOrder = new HashMap<>();
                        if (result) {
                            float total = 0;
                            //insert each item into OrderDetail
                            for (ProductDTO item : items.keySet()) {
                                float totalDetail = item.getUnitprice() * items.get(item);
                                OrderDetailDTO orderDetail = new OrderDetailDTO(
                                        orderID, item.getSku(), item.getUnitprice(), items.get(item), totalDetail);
                                result = orderDetailDAO.createNewOrderDetail(orderDetail);
                                if (result) {
                                    total = orderDetail.getUnit_price() * orderDetail.getQuantity();
                                    checkOutOrder.put(item, items.get(item));
                                }
                            }
//                            for (Map.Entry<ProductDTO, Integer> entry : items.entrySet()) {
//                                ProductDTO product = entry.getKey();
//                                int quantity = entry.getValue();
//                                float unitPrice = productDAO.getProductUnitPriceFromDatabase(product.getSku());
//                                total = unitPrice * quantity;
//                                OrderDetailDTO orderDetail = new OrderDetailDTO(orderID, product.getSku(), unitPrice, quantity, total);
//                                result = orderDetailDAO.createNewOrderDetail(orderDetail);
//                                if (result) {
//                                    total += orderDetail.getTotal();
//                                    checkOutOrder.put(product, quantity);
//                                }
//                            }
                            //update order's total value
                            orderDAO.updatOrderById(orderID, total);
                            //remove cart
                            session.removeAttribute("CART");
                            session.setAttribute("ORDER_DETAIL", checkOutOrder);
                            session.setAttribute("ORDER", order);
                            url = siteMaps.getProperty(ApplicationConstants.ShoppingFeature.VIEW_ORDER_PAGE);
                        }
                    }
                }
            }
        } catch (NamingException ex) {
            String msg = ex.getMessage();
            log("CheckOutServlet _ Naming " + msg);
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CheckOutServlet _ SQL " + msg);
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
