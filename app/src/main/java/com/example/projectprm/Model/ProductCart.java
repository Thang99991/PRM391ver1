package com.example.projectprm.Model;


import java.util.List;

public class ProductCart {
    private String id;
    private String name;
    private List<String> image_url;
    private String price;

    public ProductCart(String id, String name, List<String> img, String price) {
        this.id = id;
        this.name = name;
        this.image_url = img;
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
}
