package com.example.projectprm.Model;


import java.io.Serializable;
import java.util.List;

public class ProductCart implements Serializable {
    private String id;
    private String name;
    private List<String> image_url;
    private String price;
    private String stock;
    private String description;
    private String quantity;

    public ProductCart(String id, String name, List<String>img, String price, String stock, String description, String quantity) {
        this.description = description;
        this.stock = stock;
        this.id = id;
        this.name = name;
        this.image_url = img;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImg() {
        return image_url;
    }

    public void setImg(List<String> img) {
        this.image_url = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
