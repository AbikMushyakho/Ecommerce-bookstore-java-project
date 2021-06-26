package com.ismt.model;

public class Category {
    int id;
    String cat_name;
    String cat_description;

    public Category() {
    }

    public Category(int id, String cat_name, String cat_description) {
        this.id = id;
        this.cat_name = cat_name;
        this.cat_description = cat_description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_description() {
        return cat_description;
    }

    public void setCat_description(String cat_description) {
        this.cat_description = cat_description;
    }
}
