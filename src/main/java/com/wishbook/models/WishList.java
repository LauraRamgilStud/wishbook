package com.wishbook.models;

import java.sql.Blob;

public class WishList {

    private int id;
    private int user_id;
    private String list_name;
    private String occasion;
    private Blob cover_pic;

    public WishList() {
    }

    public WishList(int user_id, String list_name, String occasion, Blob cover_pic) {
        this.user_id = user_id;
        this.list_name = list_name;
        this.occasion = occasion;
        this.cover_pic = cover_pic;
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

    public Blob getCover_pic() {
        return cover_pic;
    }

    public void setCover_pic(Blob cover_pic) {
        this.cover_pic = cover_pic;
    }

}
