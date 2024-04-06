/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonnx.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import sonnx.product.ProductDTO;

/**
 *
 * @author Son
 */
public class CartObject implements Serializable {

    private Map<ProductDTO, Integer> items;

    public Map<ProductDTO, Integer> getItems() {
        return items;
    }

//    private Map<String, Integer> items;
//
//    public Map<String, Integer> getItems() {
//        return items;
//    }
    public boolean addItemToCart(ProductDTO item, int inputQuantity) {

//        boolean result = false;
//        //1. check exitsed id
//        if (id == null) {
//            return result;
//        }
//        //id is valid
//        if (id.trim().isEmpty()) {
//            return result;
//        }
        //2. check exitsed items
//        if (this.items == null) {
//            this.items = new HashMap<>();
//        }
//        //3. check exitsed item --> exited --> increase quantity, otherwise, drop item
//        int quantity = 1;
//        if (this.items.containsKey(id)) {
//            quantity = this.items.get(id) + 1;
//        }//item has exited in item
//        //4. update item
//        this.items.put(id, quantity);
//        result = true;
//        return result;
        if (item == null) {
            return false;
        }
        //1. Check existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //2. Chech existed item
        int quantity = inputQuantity;
        ProductDTO existedItem = getItemBySku(item.getSku());
        if (existedItem != null) {
            quantity = this.items.get(existedItem) + inputQuantity;
            item = existedItem;
        }
        //3. Update items
        this.items.put(item, quantity);
        return true;
    }

    public boolean removeItemFromCart(ProductDTO item) {
//        boolean result = false;
//
//        //1. check existed items
//        if (this.items != null) {
//            //2. check existed item -> existed -> remove
//            if (this.items.containsKey(id)) {
//                this.items.remove(id);
//                if (this.items.isEmpty()) {
//                    this.items = null;//rong thi set no bang null
//                }
//                result = true;
//            }
//        }//end items have exited
//
//        return result;
//1. Check existed items
        if (this.items == null) {
            return false;
        }
        //2. Check existed item
        ProductDTO removeItem = getItemBySku(item.getSku());
        if (removeItem != null) {
            //3. Remove item
            this.items.remove(removeItem);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
        return true;
    }

    public ProductDTO getItemBySku(String sku) {
        //1. Check existed items
        if (this.items == null) {
            return null;
        }
        if (this.items.isEmpty()) {
            return null;
        }
        //2. Check existed item
        ProductDTO result = null;
        for (Map.Entry<ProductDTO, Integer> entry : items.entrySet()) {
            if (entry.getKey().getSku().equals(sku)) {
                result = entry.getKey();
                break;
            }
        }
        return result;
    }
}
