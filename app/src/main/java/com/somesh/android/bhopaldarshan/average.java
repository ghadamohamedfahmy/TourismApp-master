package com.somesh.android.bhopaldarshan;

import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class average {
    private  FirebaseDatabase mmDatabase,hDatabase;
    private DatabaseReference mDatabase,homeDatabase;
    private DatabaseReference Database;
    private static final String TAG = "Cairo";
    ProgressBar progressBar;
      String avg;
    double xx;
   private  double sum,count,add;
     private String x,y,z;

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }
    public void setZ(String x) {
        this.z = z;
    }
    public void setAdd(double add) {
        this.add = add;
    }

    public double getSum() {
        return sum;
    }

    public double getCount() {
        return count;
    }

    public double getAdd() {
        return add;
    }

/* public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }*/

    void rate() {
        mmDatabase = FirebaseDatabase.getInstance();
        mDatabase = mmDatabase.getReference();
        Database = mmDatabase.getReference();
        mDatabase.child(x).child(y).child("counter").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                count = Double.parseDouble(snapshot.getValue().toString());
                count++;

                mDatabase = FirebaseDatabase.getInstance().getReference("/" + x + "/" + y + "/counter");
                //Database=mmDatabase.getReference("/Cairo/El-azhar-park/sum");
                mDatabase.setValue(String.valueOf(count));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        Database.child(x).child(y).child("sum").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                sum = Double.parseDouble(snapshot.getValue().toString());
                sum = sum + add;
                xx= Math.round((sum / count)*1000.0)/1000.0;
                avg = String.valueOf(xx);
                mDatabase = FirebaseDatabase.getInstance().getReference("/" + x + "/" + y + "/sum");
                //Database=mmDatabase.getReference("/Cairo/El-azhar-park/counter");
                mDatabase.setValue(String.valueOf(avg));
                mDatabase = FirebaseDatabase.getInstance().getReference("/" + x + "/" + y + "/rate");
                //Database=mmDatabase.getReference("/Cairo/El-azhar-park/counter");


                mDatabase.setValue(String.valueOf(avg));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }}