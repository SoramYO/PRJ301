/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonnx.OrderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sonnx.tbl_Order.tblOrderDTO;
import sonnx.until.DBHelper;

/**
 *
 * @author Soram
 */
public class OrderDetailDAO implements Serializable{

    private List<OrderDetailDTO> orderDetails;

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void getOrderDetailsFromDatabase() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.getConnection();
            String sql = "SELECT unit_price, quantity, total "
                    + "FROM OrderDetail ";
            stm = con.prepareStatement(sql);

            rs = stm.executeQuery();

            while (rs.next()) {
                String order_id = rs.getString("order_id");
                String product_id = rs.getString("product_id");
                float unitprice = rs.getFloat("unit_price");
                int quantity = rs.getInt("quantity");
                float total = rs.getFloat("total");

                // Create OrderDetailDTO object
                OrderDetailDTO dto = new OrderDetailDTO(order_id, product_id, unitprice, quantity, total);
                if (this.orderDetails == null) {
                    this.orderDetails = new ArrayList<>();
                }
                this.orderDetails.add(dto);
            }
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

    public boolean createNewOrderDetail(OrderDetailDTO orderDetail) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "INSERT INTO OrderDetail (order_id, product_id, unit_price, quantity, total) VALUES (?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderDetail.getOrder_id());
                stm.setString(2, orderDetail.getProduct_id());
                stm.setFloat(3, orderDetail.getUnit_price());
                stm.setInt(4, orderDetail.getQuantity());
                stm.setFloat(5, orderDetail.getTotal());

                // Execute the query
                int effectRows = stm.executeUpdate();

                // Process result
                if (effectRows > 0) {
                    result = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception or log it
        } finally {
            // Close resources
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

}
