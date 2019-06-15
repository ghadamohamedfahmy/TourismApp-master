package com.somesh.android.bhopaldarshan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Comments extends AppCompatActivity {

    private Toolbar commentToolbar;
int count=0;

    private EditText comment_field;
    private ImageButton comment_post_btn;
    private TextView commentview;
   // APICALLER objcommentcity= new APICALLER();
  //  APICALLER objcommenthome= new APICALLER();
   String content1="" ;
    String comment1;
    average objratehome =new average();
    average objrate =new average();
    String ratemachine;

/***/
private ListView mlist;
    List mhome = new ArrayList<String>();
    private ArrayList<String> muser= new ArrayList<>();
//***//

    //  private CommentsRecyclerAda commentsRecyclerAdapter;
  //  private List<Comments> commentsList;
   // List mcomment = new ArrayList<comments_list>();
    FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    Intent intent=getIntent();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
/**/
        mlist = findViewById(R.id.lista);
        final ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,mhome);
        //**//
        commentToolbar = findViewById(R.id.comment_toolbar);
        setSupportActionBar(commentToolbar);
        getSupportActionBar().setTitle("Comments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        commentToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();


       firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Intent intent=getIntent();
     //   intent
       //blog_post_id = getIntent().getStringExtra("CAIRO_TRANSFER");
     final String blog_post_id=(String) intent.getSerializableExtra("CAIRO_COMMENT");
      final String City=(String) intent.getSerializableExtra("city");
        //blog_post_id = getIntent().getSerializableExtra("Luxor_TRANSLATION");
        comment_field = findViewById(R.id.comment);
        comment_post_btn = findViewById(R.id.addcomment);



        comment_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference Reference = database.getReference(City).child(blog_post_id).child("Comments");
                DatabaseReference hooome = database.getReference("home").child(blog_post_id).child("Comments");
                String comment_message = comment_field.getText().toString();
//GetData x=new GetData();

                Map<String, Object> commentsMap = new HashMap<>();
                commentsMap.put("Comment", comment_message);
               // commentsMap.put("user_id", firebaseUser.getUid());
                count++;
                //objcommentcity.setComment(comment_message);
             //objcommentcity.Machine();


                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://testapicore.conveyor.cloud/api/values/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                jsonAPI.JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(jsonAPI.JsonPlaceHolderApi.class);

                Call<List<ApiModel>> call = jsonPlaceHolderApi.getPosts(comment_message);

                call.enqueue(new Callback<List<ApiModel>>() {

                    @Override
                    public void onResponse(Call<List<ApiModel>> call, Response<List<ApiModel>> response) {

                        if (!response.isSuccessful()) {
                            comment1+=response.code();
                            comment_field.setText("Code: " + response.code());
                            return;
                        }

                        List<ApiModel> posts = response.body();

                        for (ApiModel post : posts) {

                            //content = post.getCategory();
                            content1 +=post.getCategory();
                            //Reference.push().setValue( post.getCategory());
                            int x= Integer.parseInt(content1);
                            Double y= new Double(x);
                           // comment_field.append(y);
                            objrate.setX("Cairo");
                            objrate.setY(blog_post_id);
                            objrate.setAdd(y);
                            objrate.rate();
                            objratehome.setX("home");
                            objratehome.setY(blog_post_id);
                            objratehome.setAdd(y);
                            objratehome.rate();
                            return ;
                            //textViewResult.append(content);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ApiModel>> call, Throwable t) {
                        comment_field.setText(t.getMessage());
                        // Toast.makeText(com.somesh.android.bhopaldarshan.commentLuxor.this, "Comment added", Toast.LENGTH_SHORT).show();
                    }
                });



                // objrate.setX("Cairo");
                // objrate.setY(blog_post_id);
                // objrate.setAdd(Double.parseDouble(ratemachine));
              //  x.setCoun(count);
               // commentsMap.put("xxx", content1);
               Reference.push().setValue(commentsMap);
                hooome.push().setValue(commentsMap);
               comment_field.setText("");
                Toast.makeText(Comments.this, "Comment added", Toast.LENGTH_SHORT).show();
                /////*****////

            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        mlist.setAdapter(arrayAdapter);
        DatabaseReference read = database.getReference();
        read.child(City).child(blog_post_id).child("Comments").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue().toString();
                mhome.add(value);
//mlist.getAdapter().getCount();
                arrayAdapter.notifyDataSetChanged();
               // comments_list restaurant = dataSnapshot.getValue(comments_list.class);
               /* for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                   // comments_list comm = snapshot.getValue(comments_list.class);
                    // mcomment.add(comm);
                    // mhome.add(restaurant).

                 mhome.add(value);
                    arrayAdapter.notifyDataSetChanged();

                }*/
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
   /* private void  readcomment(){
        Intent intent=getIntent();
        final String blog_post_id=(String) intent.getSerializableExtra("CAIRO_COMMENT");
        final String City=(String) intent.getSerializableExtra("city");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference read = database.getReference(City).child(blog_post_id).child("Comments");
        read.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // mcomment.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    comments_list comm =snapshot.getValue(comments_list.class);
                   // mcomment.add(comm);
                }
                comment_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }*/
}
