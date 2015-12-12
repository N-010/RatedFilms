package com.mysoft;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 10.12.15.
 */
public class FilmInformation {
    private String title;//TODO: make fields private.
    private String description;
    private String img;
    private String country;
    private int year;

    private List<FilmInformation> filmInformationList = new ArrayList<>();

    FilmInformation(String title, String description, String img, String country, int year) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.country = country;
        this.year = year;
    }

    FilmInformation(String title, String description, String img) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.country = null;
        this.year = 0;
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

