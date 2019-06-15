package com.somesh.android.bhopaldarshan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface jsonAPI {

    ApiModel ss=new ApiModel();


    public interface JsonPlaceHolderApi {


        @GET
        Call<List<ApiModel>> getPosts(@Url String url) ;
    }
}
