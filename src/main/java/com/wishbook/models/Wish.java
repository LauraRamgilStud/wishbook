package com.wishbook.models;

import java.sql.Blob;

public class Wish {

    private int id;
    private int wishlist_id;
    private String wish_name;
    private String description;
    private Double price;
    private int quantity;
    private Blob wish_pic;
    private String url;

    public Wish() {
    }

    public Wish(String wish_name, String description, Double price, int quantity, Blob wish_pic, String url) {
        this.wish_name = wish_name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.wish_pic = wish_pic;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(int wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public String getWish_name() {
        return wish_name;
    }

    public void setWish_name(String wish_name) {
        this.wish_name = wish_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Blob getWish_pic() {
        return wish_pic;
    }

    public void setWish_pic(Blob wish_pic) {
        this.wish_pic = wish_pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
