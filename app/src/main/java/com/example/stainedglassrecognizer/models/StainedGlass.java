package com.example.stainedglassrecognizer.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StainedGlass implements Serializable {
    private String filename;

    private String image;

    private String artist;
    @SerializedName("year_birth")
    private String yearBirth;
    @SerializedName("year_passing")
    private String yearPassing;
    @SerializedName("artist_reference")
    private String artistRef;
    @SerializedName("glass_date")
    private String glassDate;
    @SerializedName("date_reference")
    private String dateRef;
    private String iconography;
    @SerializedName("church_name")
    private String churchName;
    private String url;


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(String yearBirth) {
        this.yearBirth = yearBirth;
    }

    public String getYearPassing() {
        return yearPassing;
    }

    public void setYearPassing(String yearPassing) {
        this.yearPassing = yearPassing;
    }

    public String getArtistRef() {
        return artistRef;
    }

    public void setArtistRef(String artistRef) {
        this.artistRef = artistRef;
    }

    public String getGlassDate() {
        return glassDate;
    }

    public void setGlassDate(String glassDate) {
        this.glassDate = glassDate;
    }

    public String getDateRef() {
        return dateRef;
    }

    public void setDateRef(String dateRef) {
        this.dateRef = dateRef;
    }

    public String getIconography() {
        return iconography;
    }

    public void setIconography(String iconography) {
        this.iconography = iconography;
    }

    public String getChurchName() {
        return churchName;
    }

    public void setChurchName(String churchName) {
        this.churchName = churchName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
