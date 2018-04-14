package com.movietopper.movietopper.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Enam on 3/30/2018.
 */

public class Movie implements Parcelable {
    @SerializedName("post_title")
    private String title;

    @SerializedName("post_id")
    private String id;

    @SerializedName("post_url")
    private String movieUrl;

    @SerializedName("thumb_url")
    private String thumnailUrl;

    @SerializedName("release_date")
    private String releaseDate;

    public Movie(String title, String id, String movieUrl, String thumnailUrl, String releaseDate) {
        this.title = title;
        this.id = id;
        this.movieUrl = movieUrl;
        this.thumnailUrl = thumnailUrl;
        this.releaseDate = releaseDate;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        id = in.readString();
        movieUrl = in.readString();
        thumnailUrl = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    public String getThumnailUrl() {
        return thumnailUrl;
    }

    public void setThumnailUrl(String thumnailUrl) {
        this.thumnailUrl = thumnailUrl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.getTitle());
        parcel.writeString(this.getId());
        parcel.writeString(this.getMovieUrl());
        parcel.writeString(this.getThumnailUrl());
        parcel.writeString(this.getReleaseDate());


    }
}
