/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonnx.until;

/**
 *
 * @author Son
 */
public class ApplicationConstants {

    public class DispatchFeature {

        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchController";
        public static final String START_UP_CONTROLLER = "startUpController";
        public static final String DISPATCH_CONTROLLER = "dispatchController";
        public static final String ERROR_PAGE = "errorPage";
        public static final String SEARCH_STATIC_PAGE = "searchStaticPage";

    }

    public class LoginFeature {
        public static final String CREATE_NEW_ACCOUNT_PAGE = "createNewAccountPage";
        public static final String CREATE_NEW_ACCOUNT_CONTROLLER = "createNewAccountController";
        public static final String LOG_OUT_CONTROLLER = "logOutController";
        public static final String INVALID_PAGE = "invalidPage";
        public static final String SEARCH_PAGE = "homePage";
    }
    public class SearchFeature {
        public static final String DELETE_CONTROLLER = "deleteAccountController";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchController";
    }
    
    public class ShoppingFeature {
        public static final String SHOW_PRODUCT_CONTROLLER = "showProductController";
        public static final String SEARCH_PRODUCT_CONTROLLER = "searchProductController";
        public static final String SHOPPING_CONTROLLER = "shoppingController";
        public static final String VIEW_CART_PAGE = "viewCartPage";
        public static final String REMOVE_ITEMS_FROM_CART_CONTROLLER = "removeItemFromCartController";
        public static final String CHECK_OUT_CONTROLLER = "checkOutController";
        public static final String SHOPPING_PAGE = "shoppingPage";
        public static final String VIEW_ORDER_PAGE = "viewOrderPage";
    }
}
