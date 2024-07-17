package com.example.projectprm.Model;


public class ProductCart {
    private String id;
    private String name;
    private String img_url;
    private String price;
    private String stock;
    private String description;

    public ProductCart(String id, String name, String img, String price, String stock, String description) {
        this.description = description;
        this.stock = stock;
        this.id = id;
        this.name = name;
        this.img_url = img;
        this.price = price;
    }



    // Getters and setters
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

    public String getImg() {
        return img_url;
    }

    public void setImg(String img) {
        this.img_url = img;
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
