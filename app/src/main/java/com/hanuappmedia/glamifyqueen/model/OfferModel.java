package com.hanuappmedia.glamifyqueen.model;

public class OfferModel {
    String name, description, image, time;
    double views;

    public OfferModel() {
    }

    public OfferModel(String name, String description, String image, String time, double views) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.views = views;
        this.time = time;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getViews() {
        return views;
    }

    public void setViews(double views) {
        this.views = views;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
