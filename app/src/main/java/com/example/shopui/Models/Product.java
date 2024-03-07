package com.example.shopui.Models;

import java.io.Serializable;

public class Product implements Serializable {
    private String title;
    private int picUrl;
    private int review;
    private double score;
    private int numberInChart;
    private double price;
    private String description;

    public String getDescription() {
        return description;
    }

    public Product(){

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getNumberInChart() {
        return numberInChart;
    }

    public void setNumberInChart(int numberInChart) {
        this.numberInChart = numberInChart;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(int picUrl) {
        this.picUrl = picUrl;
    }

    public Product(String title, int picUrl, int review, double score, double price, String description) {
        this.title = title;
        this.picUrl = picUrl;
        this.review = review;
        this.score = score;
        this.price = price;
        this.description = description;
    }
}
