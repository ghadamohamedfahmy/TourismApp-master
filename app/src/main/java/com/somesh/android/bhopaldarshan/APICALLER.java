package com.somesh.android.bhopaldarshan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICALLER extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api);

        textViewResult = findViewById(R.id.text_view_result);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:4446")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonAPI.JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(jsonAPI.JsonPlaceHolderApi.class);

        Call<List<ApiModel>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<ApiModel>>() {
            @Override
            public void onResponse(Call<List<ApiModel>> call, Response<List<ApiModel>> response) {

                if (!response.isSuccessful()) {

                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<ApiModel> posts = response.body();

                for (ApiModel post : posts) {
                    String content = "";
                    content += "ID: " + post.getCategory() + "\n";


                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<ApiModel>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}