/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonnx.OrderDetail;

import java.io.Serializable;

/**
 *
 * @author Soram
 */
public class OrderDetailDTO implements Serializable{

    private String order_id;
    private String product_id;
    private float unit_price;
    private int quantity;
    private float total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String order_id, String product_id, float unit_price, int quantity, float total) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.unit_price = unit_price;
        this.quantity = quantity;
        this.total = total;
    }

    /**
     * @return the order_id
     */
    public String getOrder_id() {
        return order_id;
    }

    /**
     * @param order_id the order_id to set
     */
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    /**
     * @return the product_id
     */
    public String getProduct_id() {
        return product_id;
    }

    /**
     * @param product_id the product_id to set
     */
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    /**
     * @return the unit_price
     */
    public float getUnit_price() {
        return unit_price;
    }

    /**
     * @param unit_price the unit_price to set
     */
    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

}
