package com.somesh.android.bhopaldarshan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Cairo_Details extends AppCompatActivity {


    TextView address,about,rate,websites,comments;
    average obj=new average();
    ImageView CairoImage;
    average home=new average();
Button button,ratebutton ;
    private  FirebaseDatabase mmDatabase;
    private DatabaseReference mDatabase;
    Button mapButton;
    TextView getcounteeer;
    ImageButton mvideoButton;
 private    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cairo_details);
        ratebutton = findViewById(R.id.addrate);
        getcounteeer = findViewById(R.id.textView3);
        address = findViewById(R.id.drop1);
        about = findViewById(R.id.drop3);
        rate = findViewById(R.id.drop2);
        comments = findViewById(R.id.drop5);
        websites = findViewById(R.id.drop4);

        button = findViewById(R.id.addcomment);
        CairoImage = findViewById(R.id.imageView);

        Intent intent = getIntent();
        final GetData getDataCairo = (GetData) intent.getSerializableExtra("CAIRO_TRANSFER");

        Glide.with(getApplicationContext())
                .load(getDataCairo.getImageUrl())
                .thumbnail(Glide.with(getApplicationContext()).load(R.drawable.giphy))
                .apply(new RequestOptions()
                        .error(R.drawable.broken_image))
                .into(CairoImage);
        address.setText(getDataCairo.getAddress());
        about.setText(getDataCairo.getAbout());

        //  comments.setText(getDataCairo.getComments());
        websites.setText(getDataCairo.getWebsities());

        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cairo_Details.this, Comments.class);
                intent.putExtra("CAIRO_COMMENT", getDataCairo.getName());
                intent.putExtra("city", "Cairo");
                startActivity(intent);
            }
        }));
        mvideoButton = findViewById(R.id.videoButton);
        mvideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), showvideo.class);


                startActivity(intent);
            }
        });
        mapButton = (Button) findViewById(R.id.mapButton);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("LAT", Double.valueOf(getDataCairo.getLatitude()));
                intent.putExtra("LNG", Double.valueOf(getDataCairo.getLongitude()));
                startActivity(intent);
            }
        });
        rate.setText(getDataCairo.getRate());
        ratebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mmDatabase = FirebaseDatabase.getInstance();
                mDatabase=mmDatabase.getReference();
                obj.setX("Cairo");
                obj.setY(getDataCairo.getName());
                obj.setAdd(Double.parseDouble(getDataCairo.getRate()));
                obj.rate();
                home.setX("home");
                home.setY(getDataCairo.getName());
                home.setAdd(Double.parseDouble(getDataCairo.getRate()));
                home.rate();
                     counter++;
             if(counter<=10&&counter>0) {
          getcounteeer.setText(counter + "");
        }else{ getcounteeer.setText(" that's enough for rate 10");}
            }
        });
    }}