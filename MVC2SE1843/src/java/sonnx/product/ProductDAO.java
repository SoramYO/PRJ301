/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonnx.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import sonnx.OrderDetail.OrderDetailDTO;
import sonnx.until.DBHelper;

/**
 *
 * @author Son
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> producList;

    public List<ProductDTO> getProducList() {
        return producList;
    }

    public void showProductList() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. get connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "SELECT sku, name, description, unitprice, quantity, status "
                        + "FROM Product";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    //5.1 get data from Resultset
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float unitprice = rs.getFloat("unitprice");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    //5.2 set data to DTO properties
                    if (quantity > 0 && status == true) {
                        ProductDTO dto = new ProductDTO(sku, name, description, unitprice, quantity, status);
                        if (this.producList == null) {
                            this.producList = new ArrayList<>();
                        }//end product have NOT exited
                        this.producList.add(dto);
                    }

                }//end product has exited

            }//end connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void searchProduct(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. get connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "SELECT sku, name, description, unitprice, quantity, status "
                        + "FROM Product "
                        + "Where name Like ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    //5.1 get data from Resultset
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float unitprice = rs.getFloat("unitprice");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    //5.2 set data to DTO properties
                    if (quantity > 0 && status == true) {
                        ProductDTO dto = new ProductDTO(sku, name, description, unitprice, quantity, status);
                        if (this.producList == null) {
                            this.producList = new ArrayList<>();
                        }//end product have NOT exited
                        this.producList.add(dto);
                    }

                }//end product has exited

            }//end connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public ProductDTO getProductDetailsById(String id)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO product = null;

        try {
            con = DBHelper.getConnection();
            String sql = "SELECT sku, name, description, unitprice, quantity, status "
                    + "FROM Product "
                    + "WHERE sku = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                String sku = rs.getString("sku");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float unitprice = rs.getFloat("unitprice");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                if (quantity > 0 && status == true) {
                    product = new ProductDTO(sku, name, description, unitprice, quantity, status);
                }//end of product has existed in database
            }//end of connection has existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return product;
    }

    public float getProductUnitPriceFromDatabase(String productId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float unitPrice = (float) 0.0;

        try {
            con = DBHelper.getConnection();
            String query = "SELECT unitprice "
                    + "FROM Product "
                    + "WHERE sku = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, productId);

            // Execute query
            rs = stm.executeQuery();

            // Retrieve unit price from the result set
            if (rs.next()) {
                unitPrice = rs.getFloat("unitprice");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception or log it
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return unitPrice;
    }

}
