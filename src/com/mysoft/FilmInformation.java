package com.mysoft;

/**
 * Created by root on 10.12.15.
 */
public class FilmInformation {
    private String title;
    private String description;
    private String img;
    private String country;
    private String producer;
    private String codePlayer;
    private int year;
    private int id;

    public FilmInformation(String title, String description, String img, String country, String producer, int year, int id, String codePlayer) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.country = country;
        this.producer = producer;
        this.year = year;
        this.id = id;
        this.codePlayer = codePlayer;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getCodePlayer() {
        return codePlayer;
    }

    public void setCodePlayer(String codePlayer) {
        this.codePlayer = codePlayer;
    }
}

