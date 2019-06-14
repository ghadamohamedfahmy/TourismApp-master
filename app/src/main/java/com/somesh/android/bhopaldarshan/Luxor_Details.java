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

public class Luxor_Details extends AppCompatActivity {
    TextView address,about,rate,websites,comments;
    average obj=new average();
    ImageView LuxorImage;
    average home=new average();
    Button mapButton;
    Button button ,ratebutton ;
    TextView getcounteeer;
    ImageButton mvideoButton;
  private   int counter=0;
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
        getcounteeer = findViewById(R.id.textView3);
        ratebutton = findViewById(R.id.addrate);
        LuxorImage=findViewById(R.id.imageView);

        Intent intent = getIntent();
        final GetData getDataLuxor = (GetData)intent.getSerializableExtra("Luxor_TRANSLATION");

        Glide.with(getApplicationContext())
                .load(getDataLuxor.getImageUrl())
                .thumbnail(Glide.with(getApplicationContext()).load(R.drawable.giphy))
                .apply(new RequestOptions()
                        .error(R.drawable.broken_image))
                .into(LuxorImage);
        address.setText(getDataLuxor.getAddress());
        about.setText(getDataLuxor.getAbout());
        rate.setText(""+(Math.round((Double.parseDouble(getDataLuxor.getRate()))*1000.0)/1000.0));
       // comments.setText(getDataLuxor.getComments());
        websites.setText(getDataLuxor.getWebsities());

        mapButton=(Button) findViewById(R.id.mapButton);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("LAT",Double.valueOf(getDataLuxor.getLatitude()));
                intent.putExtra("LNG",Double.valueOf(getDataLuxor.getLongitude()));
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
                Intent intent = new Intent(Luxor_Details.this,commentLuxor.class);
                intent.putExtra("LUXOR_COMMENT",getDataLuxor.getName());
                intent.putExtra("city","Luxor");

                startActivity(intent);
            }
        }));
        ratebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                obj.setX("Luxor");
                obj.setY(getDataLuxor.getName());
                obj.setAdd(Double.parseDouble(getDataLuxor.getRate()));
                obj.rate();
                home.setX("home");
                home.setY(getDataLuxor.getName());
                home.setAdd(Double.parseDouble(getDataLuxor.getRate()));
                home.rate();
                counter++;
                if(counter<=10&&counter>0) {
                    getcounteeer.setText(counter + "");
                }else{ getcounteeer.setText(" that's enough for rate 10");}
            }
        });
    }
}
