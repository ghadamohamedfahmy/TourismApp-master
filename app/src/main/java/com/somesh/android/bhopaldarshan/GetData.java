package com.somesh.android.bhopaldarshan;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nsaxena on 28/2/18.
 */

public class GetData implements Serializable {
    private static final long serialVersionUID = 1L;

    String title;
    String about;
    String address;
    String latitude;
    String longitude;
    String imageUrl;
    String rate;
    String websities;
    String comments;
    String Name;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {  Name = name; }

    public String getName() { return Name;}

    public void setAbout(String about) {   this.about = about;}

   public void setRate(String rate) {  this.rate = rate;  }

    public String getRate() { return rate; }

    public void setWebsities(String websities) { this.websities = websities; }

    public String getWebsities() { return websities; }

    public void setComments(String comments) { this.comments = comments; }

    public String getComments() {return comments;}

    public String getAbout() { return about; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}