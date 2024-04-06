/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonnx.servlet;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sonnx.until.ApplicationConstants;

/**
 *
 * @author Son
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

//    private final String LOGIN_PAGE = "login.html";
//    private final String LOGIN_PAGE = "";
//    private final String LOGIN_CONTROLLER = "LoginServlet";
//    private final String LOGIN_CONTROLLER = "loginController";
//    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastNameServlet";
//    private final String SEARCH_LASTNAME_CONTROLLER = "searchController";
//    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
//    private final String STARTUP_CONTROLLER = "StartUpServlet";
//    private final String UPDATE_CONTROLLER = "UpdateAccountServlet";
//    private final String LOGOUT_CONTROLLER = "LogoutAccountServlet";
//    private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
//    private final String VIEW_CART_PAGE = "viewCart.jsp";
//    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemFromCartServlet";
//    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet";
//
//    private final String SHOW_PRODUCT_CONTROLLER = "ShowProductServlet";
//    private final String SEARCH_PRODUCT_CONTROLLER = "SearchProductServlet";
//    private final String CHECK_OUT_CONTROLLER = "CheckOutServlet";
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
        //0. get context scope & get siteMaps
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //string url = loginpage
        String url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.LOGIN_PAGE);
//        String url = siteMaps.getProperty(LOGIN_PAGE);
        //1. Which button did user click?
        String button = request.getParameter("btAction");
        //String url = LOGIN_PAGE;

        try {
            if (button == null) {
                //first time
                //do notthing --> transfer to LOGIN PAGE
                //check cookies to determine which page is tranfer
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.START_UP_CONTROLLER);
            } else if (button.equals("Login")) {//user clicked login
                //url = LOGIN_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
            } else if (button.equals("Search")) {//user clicked search at search.html
//                url = SEARCH_LASTNAME_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.DispatchFeature.SEARCH_LASTNAME_CONTROLLER);
            } else if (button.equals("delete")) {//user click delete
                //url = DELETE_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.SearchFeature.DELETE_CONTROLLER);
            } else if (button.equals("Update")) {//user click update
                //url = UPDATE_ACCOUNT_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.SearchFeature.UPDATE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Add product to Your cart")) {//user click add product to cart
                //url = ADD_TO_CART_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.ShoppingFeature.SHOPPING_CONTROLLER);
            } else if (button.equals("View your cart")) {//user click view to cart
                //url = VIEW_CART_PAGE;
                url = siteMaps.getProperty(ApplicationConstants.ShoppingFeature.VIEW_CART_PAGE);
            } else if (button.equals("Remove Selected Items")) {//user click view to cart
                //url = REMOVE_ITEMS_FROM_CART_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.ShoppingFeature.REMOVE_ITEMS_FROM_CART_CONTROLLER);
            } else if (button.equals("Search Product")) {//user click view to cart
                //url = SEARCH_PRODUCT_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.ShoppingFeature.SEARCH_PRODUCT_CONTROLLER);
            } else if (button.equals("showProduct")) {
                //url = SHOW_PRODUCT_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.ShoppingFeature.SHOW_PRODUCT_CONTROLLER);
            } else if (button.equals("Check out")) {
                //url = CHECK_OUT_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.ShoppingFeature.CHECK_OUT_CONTROLLER);
            } else if (button.equals("Create New Account")) {//user click view to cart
                //url = CREATE_ACCOUNT_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.LoginFeature.CREATE_NEW_ACCOUNT_CONTROLLER);
            } else if (button.equals("Logout")) {//user click update
                //url = LOGOUT_CONTROLLER;
                url = siteMaps.getProperty(ApplicationConstants.LoginFeature.LOG_OUT_CONTROLLER);
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
