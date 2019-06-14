package com.somesh.android.bhopaldarshan;

public class ApiModel {
    public String sentimentText;
    public  String  category;
    public String comment;
    @Override
    public String toString() {
        return category;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public String getCategory() {
        return category;
    }

    public String getSentimentText() {
        return sentimentText;
    }
}
