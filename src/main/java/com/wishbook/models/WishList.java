package com.wishbook.models;

import java.sql.Blob;

public class WishList {

    private int id;
    private int user_id;
    private String list_name;
    private String occasion;
    private byte[] cover_pic;
    private String picOut;

    public WishList() {
    }

    public WishList(int user_id, String list_name, String occasion, byte[] cover_pic) {
        this.user_id = user_id;
        this.list_name = list_name;
        this.occasion = occasion;
        this.cover_pic = cover_pic;
    }

    public WishList(int user_id, String list_name, String occasion, String picOut) {
        this.user_id = user_id;
        this.list_name = list_name;
        this.occasion = occasion;
        this.picOut = picOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public byte[] getCover_pic() {
        return cover_pic;
    }

    public void setCover_pic(byte[] cover_pic) {
        this.cover_pic = cover_pic;
    }

    public String getPicOut() {
        return picOut;
    }

    public void setPicOut(String picOut) {
        this.picOut = picOut;
    }
}
