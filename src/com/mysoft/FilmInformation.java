package com.mysoft;

/**
 * Created by root on 10.12.15.
 */
public class FilmInformation {
    private String title;
    private String description;
    private String img;
    private String country;
    private int year;
    private int id;

    public FilmInformation(String title, String description, String img, int id, int year) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.year = year;
        this.id = id;
    }

    public FilmInformation(String title, String description, String img, String country, int year) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.country = country;
        this.year = year;
    }

    public FilmInformation(String title, String description, String img, int id) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.country = null;
        this.year = 0;
        this.id = id;
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
}

