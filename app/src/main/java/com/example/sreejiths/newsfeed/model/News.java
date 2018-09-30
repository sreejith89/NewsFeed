package com.example.sreejiths.newsfeed.model;

public class News {

    public String title;
    public String description;
    public String imageHref;

    public News () { }

    public News (String title, String description, String imageHref) {
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}
