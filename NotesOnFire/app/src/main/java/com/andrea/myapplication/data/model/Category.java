package com.andrea.myapplication.data.model;


import java.util.UUID;

public class Category {

    private String categoryId;
    private String categoryName;
    private int count;

    public Category() {
    }

    public Category(String aCategoryName) {
        categoryId = UUID.randomUUID().toString();
        categoryName = aCategoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
