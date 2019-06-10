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

public class VisitingPlaceDetails extends AppCompatActivity {
    private static final String TAG = "VisitingPlaceDetails";

    TextView address,goodFor,openingHours,entryFee,website,visitDuration,about;

    ImageView visitingPlaceImage;

    Button mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiting_place_detail);
        // loading album cover using Glide library

        address=findViewById(R.id.drop1);
        goodFor=findViewById(R.id.drop2);
        openingHours=findViewById(R.id.drop3);
        entryFee=findViewById(R.id.drop4);
        website=findViewById(R.id.drop5);
        visitDuration=findViewById(R.id.drop6);
        about=findViewById(R.id.drop7);
        visitingPlaceImage=findViewById(R.id.imageView);
        Intent intent = getIntent();
        final Get_Data_Aswan getDataAswan = (Get_Data_Aswan)intent.getSerializableExtra("VISITING_PLACE_TRANSFER");
        Glide.with(getApplicationContext())
                .load(getDataAswan.getImageUrl())
                .thumbnail(Glide.with(getApplicationContext()).load(R.drawable.giphy))
                .apply(new RequestOptions()
                        .error(R.drawable.broken_image))
                .into(visitingPlaceImage);
        address.setText(getDataAswan.getAddress());
        goodFor.setText(getDataAswan.getGoodFor());
        openingHours.setText(getDataAswan.getOpeningHours());
        entryFee.setText(getDataAswan.getEntryFee());
        website.setText(getDataAswan.getWebsites());
        visitDuration.setText(getDataAswan.getVisitDuration());
        about.setText(getDataAswan.getAbout());
        mapButton=(Button) findViewById(R.id.mapButton);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("LAT",Double.valueOf(getDataAswan.getLatitude()));
                intent.putExtra("LNG",Double.valueOf(getDataAswan.getLongitude()));
                startActivity(intent);            }
        });

    }
}
