package com.example.admin.xmltest.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HTML5 on 18/11/2017.
 */

public class Truyen implements Serializable{
    String name;
    String description;
    String category;
    List<Chuong> chuongs;
    String thumbnail;

    public Truyen(){

    }

    public Truyen(String name, String description, String category, List<Chuong> chuongs, String thumbnail) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.chuongs = chuongs;
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Chuong> getChuongs() {
        return chuongs;
    }

    public void setChuongs(List<Chuong> chuongs) {
        this.chuongs = chuongs;
    }
}
