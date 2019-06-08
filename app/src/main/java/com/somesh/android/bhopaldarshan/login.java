package com.somesh.android.bhopaldarshan;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class login extends AppCompatActivity implements home_listener.OnReclyclerClickListener {
    private Spinner spinner;
    private ImageButton button;
    private ProgressBar mProgressCircle;
    NetworkInfo netInfo;
    ListView simpleList;
    RecyclerView myRecyclerView;
    homeadapter myAdapter;
    List mhome = new ArrayList<Restaurant>();
    private DatabaseReference mDatabase;
    ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();
    private static final String TAG = "login";

    @RequiresApi (api = Build.VERSION_CODES.M)
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
        mDatabase.child("Alex").addChildEventListener(new ChildEventListener() {

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
        setContentView(R.layout.activity_login);
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo = conMgr.getActiveNetworkInfo();
        mProgressCircle = findViewById(R.id.progress_circle);
        button = findViewById(R.id.user);
        spinner = findViewById(R.id.spinner1);
        myRecyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        myRecyclerView.addOnItemTouchListener(new home_listener(this,myRecyclerView,this));
        simpleList=findViewById(R.id.simpleListView);

        // button = findViewById(R.id.selectcategory);
        List<String> categories = new ArrayList<>();
        categories.add(0, "Choose Category");
        categories.add("cairo");
        categories.add("Aswan");
        categories.add("Alex");
        categories.add("luxor");
        categories.add("Other");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        //Dropdown layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Choose Category")) {
                    //do nothing
                }
                //tetkarr lkol category
                else if (parent.getItemAtPosition(position).equals("cairo")) {
                    //on selecting a spinner item
                    String item = parent.getItemAtPosition(position).toString();

                    //show selected spinner item
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                    //OpenActivity_arts();
                    //anything else you want to do on item selection do here
                } else if (parent.getItemAtPosition(position).equals("luxor")) {
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                   // openActivity_carpenter();
                } else if (parent.getItemAtPosition(position).equals("Aswan")) {
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                  //  openActivity_real();
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // TODO Auto-generated method stub
            }
        });

    }
    public void loadData(DataSnapshot dataSnapshot) {
        Restaurant home=dataSnapshot.getValue(Restaurant.class);
        mhome.add(home);
        myAdapter = new homeadapter(login.this, mhome);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.setAdapter(myAdapter);
        mProgressCircle.setVisibility(View.INVISIBLE);
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
    public void buttonFunction(View y)
    {
        if (netInfo == null){
            Toast.makeText(this,"There is no Internet Connectivity !",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(getApplicationContext(), Restaurants.class);
            startActivity(intent);
        }
    }
  /*  public void OpenActivity_arts(){
        //// Intent intent= new Intent(this,activity_.class);
        Intent intent= new Intent (this,cairo.class);
        startActivity( intent);

    }
    public void openActivity_carpenter(){

        Intent intent= new Intent (this,Aswan.class);
        startActivity( intent);

    }
    public void openActivity_real(){
        //// Intent intent= new Intent(this,activity_.class);
        Intent intent= new Intent (this,Alex.class);
        startActivity( intent);

    }
    public void open_addd_services(){
        Intent intent= new Intent(this,luxor.class);
        startActivity( intent);
    }
    public void req(){
        Intent intent= new Intent(this, others.class);
        startActivity( intent);
    }*/
}