package com.somesh.android.bhopaldarshan;

public class ApiModel {
    public String sentimentText;
    public  String  category;

    @Override
    public String toString() {
        return category;
    }

    public String getCategory() {
        return category;
    }

    public String getSentimentText() {
        return sentimentText;
    }
}
