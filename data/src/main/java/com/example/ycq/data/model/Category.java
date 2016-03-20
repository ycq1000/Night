package com.example.ycq.data.model;

/**
 * Created by qiang on 2015-12-3.
 */
public class Category {
    private String name;
    private String logo;

    public Category(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
