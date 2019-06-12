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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comments extends AppCompatActivity {

    private Toolbar commentToolbar;
  // private RecyclerView recyclerView;
  //  private comment_adapter comment_adapter;
  //  private List<comments_list> mcomment;
    private EditText comment_field;
    private ImageButton comment_post_btn;
    private TextView commentview;
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
    private String blog_post_id;

    public Comments () {

    }

    public void setBlog_post_id(String blog_post_id) {
        this.blog_post_id = blog_post_id;
    }

    public String getBlog_post_id() {
        return blog_post_id;
    }
    /*final String City;
    public Comments(final String blog_post_id,final String City){
      this.blog_post_id=blog_post_id;
this.City=City;
    }*/

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
       // recyclerView =(RecyclerView)findViewById(R.id.recycler_view);
       // recyclerView.setHasFixedSize(true);
      //  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
       // recyclerView.setLayoutManager(linearLayoutManager);


        firebaseAuth = FirebaseAuth.getInstance();


       firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Intent intent=getIntent();
     //   intent
       //blog_post_id = getIntent().getStringExtra("CAIRO_TRANSFER");
    //  final String blog_post_id=(String) intent.getSerializableExtra("City_COMMENT");
      final String City=(String) intent.getSerializableExtra("city");
        //blog_post_id = getIntent().getSerializableExtra("Luxor_TRANSLATION");
        comment_field = findViewById(R.id.comment);
        comment_post_btn = findViewById(R.id.addcomment);
       // recyclerView = findViewById(R.id.comment_list);

        //RecyclerView Firebase List
       // commentsList = new ArrayList<>();
       // commentsRecyclerAdapter = new CommentsRecyclerAdapter(commentsList);
        //recyclerView.setHasFixedSize(true);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //comment_list.setAdapter(commentsRecyclerAdapter);
       // mcomment =new ArrayList<>();
       // comment_adapter = new comment_adapter(this,mcomment);
       // recyclerView.setAdapter(comment_adapter);
///****///
        comment_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference Reference = database.getReference(City).child(blog_post_id).child("Comments");

                String comment_message = comment_field.getText().toString();

                Map<String, Object> commentsMap = new HashMap<>();
                commentsMap.put("Comment", comment_message);
                commentsMap.put("user_id", firebaseUser.getUid());

                Reference.push().setValue(commentsMap);
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
        ///***///


        //readcomment();

                /////*****////







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
