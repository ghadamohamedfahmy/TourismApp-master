package com.somesh.android.bhopaldarshan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Luxor extends AppCompatActivity implements Luxor_Listener.OnReclyclerClickListener{
    ListView simpleList;
    RecyclerView myRecyclerView;
    Luxor_adapter myAdapter;

    List mRestaurants = new ArrayList<GetData>();
    private DatabaseReference mDatabase;
    private static final String TAG = "Luxor";
    ProgressBar progressBar;
    ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Luxor").orderByChild("rate").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                loadData(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                loadData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luxor);
        progressBar=findViewById(R.id.progress);

        myRecyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        myRecyclerView.addOnItemTouchListener(new Luxor_Listener(this,myRecyclerView,this));
       // simpleList=findViewById(R.id.simpleListView);



    }

    public void loadData(DataSnapshot dataSnapshot) {



        //  Toast.makeText(getApplicationContext(), "Key: "+key+" Value: "+value, Toast.LENGTH_LONG).show();
        //}
        GetData getData =dataSnapshot.getValue(GetData.class);
        mRestaurants.add(getData);
        myAdapter = new Luxor_adapter(Luxor.this, mRestaurants);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        myRecyclerView.setAdapter(myAdapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClick(View view, int postition) {
        Intent intent = new Intent(this, Luxor_Details.class);
        intent.putExtra("Luxor_TRANSLATION", myAdapter.getRestaurant(postition));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int postition) {

    }


}

