package com.mysoft;

/**
 * Created by root on 10.12.15.
 */
public class FilmInformation {
    public String title;
    public String description;
    public String img;
    public String country;
    public int year;

    FilmInformation(String title, String description, String img, String country, int year) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.country = country;
        this.year = year;
    }

    FilmInformation() {
        this.title = null;
        this.description = null;
        this.img = null;
        this.country = null;
        this.year = 0;
    }

    public void setData(String title, String description, String img, String country, int year) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.country = country;
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getImg() {
        return img;
    }

    public String getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }
}

