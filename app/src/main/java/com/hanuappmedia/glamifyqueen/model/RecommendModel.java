package com.hanuappmedia.glamifyqueen.model;

import java.io.Serializable;

public class RecommendModel implements Serializable {
    String name, description, image;
    double price, rating;

    public RecommendModel() {
    }

    public RecommendModel(String name, String description, String image, double price, double rating) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.rating = rating;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
