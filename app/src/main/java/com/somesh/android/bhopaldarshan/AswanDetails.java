package com.somesh.android.bhopaldarshan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class AswanDetails extends AppCompatActivity {
    private static final String TAG = "AswanDetails";

    TextView address,about,rate,websites,comments;

    ImageView AswanImage;
    Button button ;
    Button mapButton;
    ImageButton mvideoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aswan_details);
        // loading album cover using Glide library
        button=findViewById(R.id.addcomment);
        address=findViewById(R.id.drop1);
        about=findViewById(R.id.drop3);
        rate=findViewById(R.id.drop2);
        comments=findViewById(R.id.drop5);
        websites=findViewById(R.id.drop4);
        AswanImage =findViewById(R.id.imageView);
        Intent intent = getIntent();
        final GetData getDataAswan = (GetData)intent.getSerializableExtra("ASWAN_TRANSLATION");
        Glide.with(getApplicationContext())
                .load(getDataAswan.getImageUrl())
                .thumbnail(Glide.with(getApplicationContext()).load(R.drawable.giphy))
                .apply(new RequestOptions()
                        .error(R.drawable.broken_image))
                .into(AswanImage);
        address.setText(getDataAswan.getAddress());
        about.setText(getDataAswan.getAbout());
        rate.setText(getDataAswan.getRate());
        comments.setText(getDataAswan.getComments());
        websites.setText(getDataAswan.getWebsities());
        mapButton=(Button) findViewById(R.id.mapButton);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("LAT",Double.valueOf(getDataAswan.getLatitude()));
                intent.putExtra("LNG",Double.valueOf(getDataAswan.getLongitude()));
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
                Intent intent = new Intent(AswanDetails.this,Comments.class);

                intent.putExtra("City_COMMENT",getDataAswan.getName());
                intent.putExtra("city","Aswan");
                startActivity(intent);
            }
        }));

    }
}
