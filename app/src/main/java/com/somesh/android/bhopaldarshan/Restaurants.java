package com.somesh.android.bhopaldarshan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Restaurants extends AppCompatActivity implements RestaurantsListener.OnReclyclerClickListener{
    ListView simpleList;
    RecyclerView myRecyclerView;
    RestaurantsAdapter myAdapter;
    List mRestaurants = new ArrayList<Restaurant>();
    private DatabaseReference mDatabase;
    private static final String TAG = "Restaurants";
    ProgressBar progressBar;
    ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Cairo").addChildEventListener(new ChildEventListener() {

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
        setContentView(R.layout.restaurants);
        progressBar=findViewById(R.id.progress);
        myRecyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        myRecyclerView.addOnItemTouchListener(new RestaurantsListener(this,myRecyclerView,this));
        simpleList=findViewById(R.id.simpleListView);

    }

    public void loadData(DataSnapshot dataSnapshot) {
        // Map<String, String> data = (HashMap<String, String>) dataSnapshot.getValue();
        //Iterator myVeryOwnIterator = data.keySet().iterator();
        //while (myVeryOwnIterator.hasNext()) {
        // String key = String.valueOf(myVeryOwnIterator.next());
        // String value = String.valueOf(data.get(key));
        //adaaapter as = new adaaapter( data);
        //simpleList.setAdapter(as);

        //progressBar.setVisibility(View.INVISIBLE);

        //  Toast.makeText(getApplicationContext(), "Key: "+key+" Value: "+value, Toast.LENGTH_LONG).show();
        //}
         Restaurant restaurant=dataSnapshot.getValue(Restaurant.class);
        mRestaurants.add(restaurant);
        myAdapter = new RestaurantsAdapter(Restaurants.this, mRestaurants);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        myRecyclerView.setLayoutManager(gridLayoutManager);
        myRecyclerView.setAdapter(myAdapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClick(View view, int postition) {
        Intent intent = new Intent(this,RestaurantDetails.class);
        intent.putExtra("RESTAURANT_TRANSFER", myAdapter.getRestaurant(postition));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int postition) {

    }

}
