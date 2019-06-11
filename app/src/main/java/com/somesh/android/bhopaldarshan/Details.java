package com.somesh.android.bhopaldarshan;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    private static final String TAG = "Details";

    TextView address,about,rate,websites,comments;

    ImageView AlexImage;
    Button button ;
    Button mapButton;
    ImageButton mvideoButton;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alex_details);
        button=findViewById(R.id.addcomment);
        address=findViewById(R.id.drop1);
        about=findViewById(R.id.drop3);
        rate=findViewById(R.id.drop2);
        comments=findViewById(R.id.drop5);
        websites=findViewById(R.id.drop4);

        AlexImage =findViewById(R.id.imageView);
        linearLayout=findViewById(R.id.linearlayout);
        Intent intent = getIntent();
        final GetData getData = (GetData)intent.getSerializableExtra("ALEX_TRANSFER");
        Picasso.with(getApplicationContext())
                .load(getData.getImageUrl())

                .into(AlexImage);
       /*Glide.with(getApplicationContext())
                .load(getData.getImageUrl())
                .thumbnail(Glide.with(getApplicationContext()).load(R.drawable.giphy))
                .apply(new RequestOptions()
                        .error(R.drawable.broken_image))
               .into(AlexImage);*/
       // Toast.makeText(getApplicationContext(),getData.getAddress(),Toast.LENGTH_SHORT).show();
        address.setText(getData.getAddress());
        about.setText(getData.getAbout());
        rate.setText(getData.getRate());
        comments.setText(getData.getComments());
        websites.setText(getData.getWebsities());
        mapButton=findViewById(R.id.mapButton);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),MapsActivity.class);

                intent.putExtra("LAT",Double.valueOf(getData.getLatitude()));
                intent.putExtra("LNG",Double.valueOf(getData.getLongitude()));
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
                Intent intent = new Intent(Details.this,Comments.class);
                intent.putExtra("City_COMMENT",getData.getAbout());
                intent.putExtra("city","Alex");
                startActivity(intent);
            }
        }));
    }

}

