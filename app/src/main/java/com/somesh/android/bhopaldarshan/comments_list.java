package com.somesh.android.bhopaldarshan;

public class comments_list {


        private String comment, user_id;


        public comments_list() {

        }

        public comments_list(String comment, String user_id ) {
            this.comment = comment;
            this.user_id = user_id;

        }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }


    }
