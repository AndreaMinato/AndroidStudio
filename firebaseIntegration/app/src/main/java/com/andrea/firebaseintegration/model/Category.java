package com.andrea.firebaseintegration.model;

/**
 * Created by Andrea on 20/06/17.
 */

public class Category {

    private String categoryId;
    private String categoryName;
    private int count;

    public Category() {
    }

    public Category(String aCategoryId, String aCategoryName, int aCount) {
        categoryId = aCategoryId;
        categoryName = aCategoryName;
        count = aCount;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String aCategoryId) {
        categoryId = aCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String aCategoryName) {
        categoryName = aCategoryName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int aCount) {
        count = aCount;
    }
}
