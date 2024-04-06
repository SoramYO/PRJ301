/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonnx.tbl_Order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sonnx.OrderDetail.OrderDetailDTO;
import sonnx.until.DBHelper;

/**
 *
 * @author Soram
 */
public class tblOrderDAO implements Serializable{

    private List<tblOrderDTO> orderList;

    public List<tblOrderDTO> getOrderList() {
        return orderList;
    }

    public int countOrder() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            //Make connection
            con = DBHelper.getConnection();
            if (con != null) {
                //Create SQL String
                String sql = "SELECT id, date, total "
                        + "FROM tbl_Order";
                //Create statement
                stm = con.prepareStatement(sql);
                //Execute querry
                rs = stm.executeQuery();
                //Process result
                while (rs.next()) {
                    count++;
                }
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
        return count;

    }

    public boolean createNewOrder(tblOrderDTO order) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            //1. make connection
            con = DBHelper.getConnection();
            if (con != null) {
                String id = order.getId();
                Date date = new Date(order.getDate().getTime());
                //2. create sql string
                String sql = "INSERT INTO tbl_Order (id, date, total) VALUES (?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.setDate(2, date);
                stm.setFloat(3, 0);
                int effectRows = stm.executeUpdate();
                // Process result
                if (effectRows > 0) {
                    result = true;
                }
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
        return result;
    }

    public boolean updatOrderById(String orderId, float total)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1. make connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Update tbl_Order "
                        + "Set total = ? "
                        + "Where id = ?";
                //3. create statement object
                stm = con.prepareStatement(sql);
                stm.setFloat(1, total);
                stm.setString(2, orderId);
                //4. execute query
                int affectedRows = stm.executeUpdate();
                if (affectedRows > 0) {
                    result = true;
                }
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
        return result;
    }

    public void getOrderFromDatabase() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT id, date, total "
                        + "FROM tbl_Order";
                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();

                while (rs.next()) {
                    String id = rs.getString("id");
                    Date date = rs.getDate("date");
                    float total = rs.getFloat("total");

                    // Create OrderDetailDTO object
                    tblOrderDTO dto = new tblOrderDTO(id, date, total);
                    if (this.orderList == null) {
                        this.orderList = new ArrayList<>();
                    }
                    this.orderList.add(dto);
                }
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
}
