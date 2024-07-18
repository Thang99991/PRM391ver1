package com.example.projectprm.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String order_id;
    private  String product_id;
    private String product_name;
    private String quantity;
    private String price_per;
    private String name;

    public Product(String id, String order_id, String product_id, String product_name, String quantity, String price_per, String name) {
        this.id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.price_per = price_per;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice_per() {
        return price_per;
    }

    public void setPrice_per(String price_per) {
        this.price_per = price_per;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
