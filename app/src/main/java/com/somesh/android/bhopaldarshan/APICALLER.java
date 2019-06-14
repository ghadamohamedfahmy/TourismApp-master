package com.somesh.android.bhopaldarshan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICALLER {
  //  private TextView textViewResult;
   // private  String comment="i love it";
    String content ;
    String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getContent() {
        return content;
    }

    // @Override
    public void   Machine() {
        // super.onCreate(savedInstanceState);
        //setContentView(R.layout.api);


        //textViewResult = findViewById(R.id.text_view_result);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://testapicore.conveyor.cloud/api/values/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonAPI.JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(jsonAPI.JsonPlaceHolderApi.class);

        Call<List<ApiModel>> call = jsonPlaceHolderApi.getPosts(comment);

        call.enqueue(new Callback<List<ApiModel>>() {

            @Override
            public void onResponse(Call<List<ApiModel>> call, Response<List<ApiModel>> response) {

                if (!response.isSuccessful()) {

                    //textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<ApiModel> posts = response.body();

                for (ApiModel post : posts) {

                    //content = post.getCategory();
                    content += "ID: " + post.getCategory() + "\n";
                    return;
                    //textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<ApiModel>> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                // Toast.makeText(com.somesh.android.bhopaldarshan.commentLuxor.this, "Comment added", Toast.LENGTH_SHORT).show();
            }
        });

    }

}