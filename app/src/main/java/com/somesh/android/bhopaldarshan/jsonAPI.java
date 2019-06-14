package com.somesh.android.bhopaldarshan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface jsonAPI {


    public interface JsonPlaceHolderApi {

        @GET("api/values/perfect")
        Call<List<ApiModel>> getPosts();
    }
}
