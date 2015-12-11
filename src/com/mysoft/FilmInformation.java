package com.mysoft;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 10.12.15.
 */
public class FilmInformation {
    public String title;
    public String description;
    public String img;
    public String country;
    public int year;

    public List<FilmInformation> filmInformationList = new ArrayList<>();

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

    public List<FilmInformation> getFilmInformationList() {
        return filmInformationList;
    }

    public void setFilmInformationList(List<FilmInformation> filmInformationList) {
        this.filmInformationList = filmInformationList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

