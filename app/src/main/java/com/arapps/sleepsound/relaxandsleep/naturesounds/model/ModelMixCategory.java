package com.arapps.sleepsound.relaxandsleep.naturesounds.model;

public class ModelMixCategory {
    int categoryId;
    boolean isOk = false;
    String categoryName;

    public ModelMixCategory(int i, String str) {
        this.categoryId = i;
        this.categoryName = str;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int i) {
        this.categoryId = i;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String str) {
        this.categoryName = str;
    }

    public boolean isOk() {
        return this.isOk;
    }

    public void setOk(boolean z) {
        this.isOk = z;
    }
}
