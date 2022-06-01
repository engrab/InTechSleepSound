package com.arapps.sleepsound.relaxandsleep.naturesounds.Model;

public class MixCategoryModel {
    int id;
    boolean isChecked = false;
    String name;

    public MixCategoryModel(int i, String str) {
        this.id = i;
        this.name = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }
}
