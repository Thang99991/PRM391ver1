package com.example.projectprm.Model;

public class Cart {
    private String user_id;
    private String product_id;
    private int quantity;


    public Cart(String user_id, String product_id, int quantity) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity = quantity;

    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public String getProductId() {
        return product_id;
    }

    public void setProductId(String product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}