/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonnx.product;

import java.io.Serializable;

/**
 *
 * @author Son
 */
public class ProductDTO implements Serializable {

    private String sku;
    private String name;
    private String description;
    private float unitprice;
    private int quantity;
    private boolean status;

    public ProductDTO() {
    }

    public ProductDTO(String sku, String name, String description, float unitprice, int quantity, boolean status) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.unitprice = unitprice;
        this.quantity = quantity;
        this.status = status;
    }

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the unitprice
     */
    public float getUnitprice() {
        return unitprice;
    }

    /**
     * @param unitprice the unitprice to set
     */
    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
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
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

 
    
}
