package com.somesh.android.bhopaldarshan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Alex_Details extends AppCompatActivity {

    private static final String TAG = "Alex_Details";

    TextView address,about,rate,websites,comments;
    average obj=new average();
    average home=new average();
    ImageView AlexImage;
    Button button1 ;
    Button mapButton,ratebutton ;
    TextView getcounteeer;
    ImageButton mvideoButton;
   private int counter=0;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alex_details);
        button1=findViewById(R.id.addcomment);
        address=findViewById(R.id.drop1);
        about=findViewById(R.id.drop3);
        rate=findViewById(R.id.drop2);
        comments=findViewById(R.id.drop5);
        websites=findViewById(R.id.drop4);
        getcounteeer = findViewById(R.id.textView3);
        ratebutton = findViewById(R.id.addrate);
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
        rate.setText(""+(Math.round((Double.parseDouble(getData.getRate()))*1000.0)/1000.0));
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
        button1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Alex_Details.this,commentAlex.class);
                intent.putExtra("ALEX_COMMENT",getData.getName());
                intent.putExtra("city","Alex");

                startActivity(intent);
            }
        }));
        ratebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                obj.setX("Alex");
                obj.setY(getData.getName());
                obj.setAdd(Double.parseDouble(getData.getRate()));
                obj.rate();
                home.setX("home");
                home.setY(getData.getName());
                home.setAdd(Double.parseDouble(getData.getRate()));
                home.rate();
                counter++;
                if(counter<=10&&counter>0) {
                    getcounteeer.setText(counter + "");
                }else{ getcounteeer.setText(" that's enough for rate 10");}
            }
        });
    }

}

