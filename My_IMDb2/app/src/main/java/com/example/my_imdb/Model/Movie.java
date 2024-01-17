package com.example.my_imdb.Model;

import android.widget.ImageView;

public class Movie {
    private String title,genre,year ,info;
    private int image;

//    public Movie() {
//    }

    public Movie(String title, String genre, String year, int image,String info) {

        this.image=image;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.info=info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
