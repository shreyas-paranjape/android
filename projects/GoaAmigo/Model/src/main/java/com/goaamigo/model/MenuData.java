package com.goaamigo.model;

import java.io.Serializable;

public class MenuData implements Serializable {

    String title;
    String description;
    int imageId;

    public MenuData() {
    }

    public MenuData(String title) {
        this.title = title;
    }

    public MenuData(String title, int imageId, String description) {
        this.title = title;
        this.imageId = imageId;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuData menuData = (MenuData) o;

        return !(title != null ? !title.equals(menuData.title) : menuData.title != null);

    }

    @Override
    public String toString() {
        return "MenuData{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageId=" + imageId +
                '}';
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
