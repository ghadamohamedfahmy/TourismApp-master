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

public class Cairo_Details extends AppCompatActivity {

    TextView address,about,rate,websites,comments;

    ImageView CairoImage;
Button button ;
    Button mapButton;
    ImageButton mvideoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cairo_details);

        address=findViewById(R.id.drop1);
        about=findViewById(R.id.drop3);
        rate=findViewById(R.id.drop2);
        comments=findViewById(R.id.drop5);
        websites=findViewById(R.id.drop4);

        button=findViewById(R.id.addcomment);
        CairoImage=findViewById(R.id.imageView);

        Intent intent = getIntent();
        final GetData getDataCairo = (GetData)intent.getSerializableExtra("CAIRO_TRANSFER");

        Glide.with(getApplicationContext())
                .load(getDataCairo.getImageUrl())
                .thumbnail(Glide.with(getApplicationContext()).load(R.drawable.giphy))
                .apply(new RequestOptions()
                        .error(R.drawable.broken_image))
                .into(CairoImage);
        address.setText(getDataCairo.getAddress());
        about.setText(getDataCairo.getAbout());
        rate.setText(getDataCairo.getRate());
      //  comments.setText(getDataCairo.getComments());
        websites.setText(getDataCairo.getWebsities());

        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cairo_Details.this,Comments.class);
            intent.putExtra("CAIRO_COMMENT",getDataCairo.getAbout());
                intent.putExtra("city","Cairo");
            startActivity(intent);
            }
        }));
        mvideoButton=findViewById(R.id.videoButton);
        mvideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),showvideo.class);


                startActivity(intent);            }
        });
        mapButton=(Button) findViewById(R.id.mapButton);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("LAT",Double.valueOf(getDataCairo.getLatitude()));
                intent.putExtra("LNG",Double.valueOf(getDataCairo.getLongitude()));
                startActivity(intent);            }
        });
    }
}
