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

public class Other_Details extends AppCompatActivity {

    TextView address,about,rate,websites,comments;
    average obj=new average();
    average home=new average();
    ImageView OtherImage;

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
        comments=findViewById(R.id.drop5);
        websites=findViewById(R.id.drop4);
        getcounteeer = findViewById(R.id.textView3);
        ratebutton = findViewById(R.id.addrate);

        ImageButton mvideoButton;
        OtherImage=findViewById(R.id.imageView);

        Intent intent = getIntent();
        final GetData getDataOther = (GetData)intent.getSerializableExtra("OTHER_TRANSFER");

        Glide.with(getApplicationContext())
                .load(getDataOther.getImageUrl())
                .thumbnail(Glide.with(getApplicationContext()).load(R.drawable.giphy))
                .apply(new RequestOptions()
                        .error(R.drawable.broken_image))
                .into(OtherImage);
        address.setText(getDataOther.getAddress());
        about.setText(getDataOther.getAbout());
        rate.setText(getDataOther.getRate());
        comments.setText(getDataOther.getComments());
        websites.setText(getDataOther.getWebsities());

        mapButton=(Button) findViewById(R.id.mapButton);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("LAT",Double.valueOf(getDataOther.getLatitude()));
                intent.putExtra("LNG",Double.valueOf(getDataOther.getLongitude()));
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
                Intent intent = new Intent(Other_Details.this,commentOther.class);
                intent.putExtra("OTHERCOMMENT",getDataOther.getName());
                intent.putExtra("city","Others");
                startActivity(intent);
            }
        }));
        ratebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                obj.setX("Others");
                obj.setY(getDataOther.getName());
                obj.setAdd(Double.parseDouble(getDataOther.getRate()));
                obj.rate();

                home.setX("home");
                home.setY(getDataOther.getName());
                home.setAdd(Double.parseDouble(getDataOther.getRate()));
                home.rate();                counter++;
                if(counter<=10&&counter>0) {
                    getcounteeer.setText(counter + "");
                }else{ getcounteeer.setText(" that's enough for rate 10");}
            }
        });
    }
}
