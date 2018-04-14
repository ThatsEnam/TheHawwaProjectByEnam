package com.movietopper.movietopper.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Enam on 4/1/2018.
 */

public class Category {
    @SerializedName("category_id")
    String idCategory;

    @SerializedName("category_name")
    String nameCategory;

    @SerializedName("category_url")
    String urlCategory;

    @SerializedName("category_movies_count")
    String numberOfMovie;

    public Category() {

    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getUrlCategory() {
        return urlCategory;
    }

    public void setUrlCategory(String urlCategory) {
        this.urlCategory = urlCategory;
    }

    public String getNumberOfMovie() {
        return numberOfMovie;
    }

    public void setNumberOfMovie(String numberOfMovie) {
        this.numberOfMovie = numberOfMovie;
    }
}
