package com.somesh.android.bhopaldarshan;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class RestaurantDetails extends AppCompatActivity {

    private static final String TAG = "RestaurantDetails";

    TextView address,cuisines,openingHours,highlights,cost;

    ImageView restaurantImage;

    Button mapButton;

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
        final Restaurant restaurant = (Restaurant)intent.getSerializableExtra("RESTAURANT_TRANSFER");

       // Glide.with(getApplicationContext())
           //     .load(restaurant.getImageUrl())
             //   .thumbnail(Glide.with(getApplicationContext()).load(R.drawable.giphy))
               // .apply(new RequestOptions()
                 //       .error(R.drawable.broken_image))
              //  .into(restaurantImage);
       // Toast.makeText(getApplicationContext(),restaurant.getAddress(),Toast.LENGTH_SHORT).show();
       // address.setText( String.valueOf(restaurant.getAddress()));
        //cuisines.setText(String.valueOf(restaurant.getCuisines()));
        //openingHours.setText(String.valueOf(restaurant.getOpeningHours()));
        //highlights.setText(String.valueOf(restaurant.getHighlights()));
        //cost.setText(String.valueOf(restaurant.getCost()));

        /*if(restaurant.getMenuImages()!=null)
        {
            for(String menuImage:restaurant.getMenuImages())
            {
                ImageView currentImage = new ImageView(this);
                linearLayout.addView(currentImage);
                Glide.with(getApplicationContext())
                    .load(menuImage)
                    .thumbnail(Glide.with(getApplicationContext()).load(R.drawable.giphy))
                    .apply(new RequestOptions()
                            .error(R.drawable.broken_image))
                    .into(currentImage);
            }
        }else {
            TextView tv=new TextView(this);
            tv.setTextSize(24);
            tv.setTextColor(Color.WHITE);
            tv.setText("Sorry no Menu right now !");
            linearLayout.addView(tv);
        }

*/
        mapButton=findViewById(R.id.mapButton);

        //mapButton.setOnClickListener(new View.OnClickListener() {
           // @Override
         //   public void onClick(View view) {

                //Intent intent= new Intent(getApplicationContext(),MapsActivity.class);
               // intent.putExtra("LAT",Double.valueOf(restaurant.getLatitude()));
                //intent.putExtra("LNG",Double.valueOf(restaurant.getLongitude()));
                //startActivity(intent);            }
       // });
    }

}
