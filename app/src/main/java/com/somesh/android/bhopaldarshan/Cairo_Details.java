package com.somesh.android.bhopaldarshan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Cairo_Details extends AppCompatActivity {

    TextView address,openingHours,about;

    ImageView templeImage;

    Button mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cairo_details);

        address=findViewById(R.id.drop1);
        openingHours=findViewById(R.id.drop2);
        about=findViewById(R.id.drop3);

        templeImage=findViewById(R.id.imageView);

        Intent intent = getIntent();
        final Get_Data_Cairo getDataCairo = (Get_Data_Cairo)intent.getSerializableExtra("TEMPLE_TRANSFER");

        Glide.with(getApplicationContext())
                .load(getDataCairo.getImageUrl())
                .thumbnail(Glide.with(getApplicationContext()).load(R.drawable.giphy))
                .apply(new RequestOptions()
                        .error(R.drawable.broken_image))
                .into(templeImage);
        address.setText(getDataCairo.getAddress());
        openingHours.setText(getDataCairo.getOpeningHours());
        about.setText(getDataCairo.getAbout());

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
