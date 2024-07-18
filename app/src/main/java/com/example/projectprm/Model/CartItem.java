package com.example.projectprm.Model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String id;
    private String user_id;
    private String product_id;
    private String quantity;
    private String created_at;
    private String updated_at;
    private ProductCart product;

    // Getter and Setter for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter for user_id
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    // Getter and Setter for product_id
    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    // Getter and Setter for quantity
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    // Getter and Setter for created_at
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    // Getter and Setter for updated_at
    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    // Getter and Setter for product
    public ProductCart getProduct() {
        return product;
    }

    public void setProduct(ProductCart product) {
        this.product = product;
    }
}
