package com.somesh.android.bhopaldarshan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class homeDetails extends AppCompatActivity {


    TextView address,about,rate,websites,comments;

    ImageView LuxorImage;

    Button mapButton;
    Button button ;
    ImageButton mvideoButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luxor__details);
        button=findViewById(R.id.addcomment);
        address=findViewById(R.id.drop1);
        about=findViewById(R.id.drop3);
        rate=findViewById(R.id.drop2);
        //comments=findViewById(R.id.drop5);
        websites=findViewById(R.id.drop4);

        LuxorImage=findViewById(R.id.imageView);

        Intent intent = getIntent();
        final GetData gethomeData = (GetData)intent.getSerializableExtra("HOME_TRANSFER");

        Glide.with(getApplicationContext())
                .load(gethomeData.getImageUrl())
                .thumbnail(Glide.with(getApplicationContext()).load(R.drawable.giphy))
                .apply(new RequestOptions()
                        .error(R.drawable.broken_image))
                .into(LuxorImage);
        address.setText(gethomeData.getAddress());
        about.setText(gethomeData.getAbout());
        rate.setText(gethomeData.getRate());
        // comments.setText(getDataLuxor.getComments());
        websites.setText(gethomeData.getWebsities());

        mapButton=(Button) findViewById(R.id.mapButton);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("LAT",Double.valueOf(gethomeData.getLatitude()));
                intent.putExtra("LNG",Double.valueOf(gethomeData.getLongitude()));
                startActivity(intent);            }
        });
        mvideoButton=findViewById(R.id.videoButton);
        mvideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),showvideo.class);


                startActivity(intent);            }
        });
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeDetails.this,Comments.class);
                intent.putExtra("home_COMMENT",gethomeData.getAbout());
                intent.putExtra("city","home");
                startActivity(intent);
            }
        }));
    }
}
