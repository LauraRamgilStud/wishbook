package com.wishbook.models;

import java.sql.Blob;

public class Wish {

    private int id;
    private int wishlist_id;
    private String wish_name;
    private String description;
    private String price;
    private String quantity;
    private byte[] wish_pic;
    private String picOut;
    private String url;

    private boolean reserved;

    public Wish() {
    }

    public Wish(int wishlist_id, String wish_name, String description, String price, String quantity, byte[] wish_pic, String url) {
        this.wishlist_id = wishlist_id;
        this.wish_name = wish_name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.wish_pic = wish_pic;
        this.url = url;
    }

    public Wish(int wishlist_id, String wish_name, String description, String price, String quantity, String picOut, String url) {
        this.wishlist_id = wishlist_id;
        this.wish_name = wish_name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.picOut = picOut;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWish_pic(byte[] wish_pic) {
        this.wish_pic = wish_pic;
    }

    public byte[] getWish_pic() {
        return wish_pic;
    }

    public String getPicOut() {
        return picOut;
    }

    public void setPicOut(String picOut) {
        this.picOut = picOut;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
