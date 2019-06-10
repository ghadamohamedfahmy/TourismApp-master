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

    TextView address,cuisines,openingHours,highlights,cost;

    ImageView restaurantImage;

    Button mapButton;
    ImageButton mvideoButton;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        address=findViewById(R.id.drop1);
        cuisines=findViewById(R.id.drop2);
        openingHours=findViewById(R.id.drop3);
        highlights=findViewById(R.id.drop4);
        cost=findViewById(R.id.drop5);

        restaurantImage=findViewById(R.id.imageView);
        linearLayout=findViewById(R.id.linearlayout);
        Intent intent = getIntent();
        final GetData getData = (GetData)intent.getSerializableExtra("RESTAURANT_TRANSFER");
        Picasso.with(getApplicationContext())
                .load(getData.getImageUrl())

                .into(restaurantImage);
       /*Glide.with(getApplicationContext())
                .load(getData.getImageUrl())
                .thumbnail(Glide.with(getApplicationContext()).load(R.drawable.giphy))
                .apply(new RequestOptions()
                        .error(R.drawable.broken_image))
               .into(restaurantImage);*/
       // Toast.makeText(getApplicationContext(),getData.getAddress(),Toast.LENGTH_SHORT).show();
       address.setText( String.valueOf(getData.getAddress()));
        cuisines.setText(String.valueOf(getData.getCuisines()));
        openingHours.setText(String.valueOf(getData.getOpeningHours()));
        highlights.setText(String.valueOf(getData.getHighlights()));
        cost.setText(String.valueOf(getData.getCost()));

        if(getData.getMenuImages()!=null)
        {
            for(String menuImage: getData.getMenuImages())
            {
                ImageView currentImage = new ImageView(this);
                linearLayout.addView(currentImage);
                Picasso.with(getApplicationContext())
                        .load(menuImage)

                        .into(currentImage);
               /* Glide.with(getApplicationContext())
                    .load(menuImage)
                    .thumbnail(Glide.with(getApplicationContext()).load(R.drawable.giphy))
                    .apply(new RequestOptions()
                            .error(R.drawable.broken_image))
                    .into(currentImage);*/
            }
        }else {
            TextView tv=new TextView(this);
            tv.setTextSize(24);
            tv.setTextColor(Color.WHITE);
            tv.setText("Sorry no Menu right now !");
            linearLayout.addView(tv);
        }


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
    }

}

