package com.somesh.android.bhopaldarshan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class homeadapter extends RecyclerView.Adapter<homeadapter.MyViewHolder>{
        List<GetData> mhome;
        Context context;

public homeadapter(Context context,List homelist) {
        this.context=context;
        this.mhome = homelist;
        }
@Override
public int getItemCount() {
        return mhome.size();
        }

@Override
public homeadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.activity_homeadapter,parent, false);
        return new homeadapter.MyViewHolder(v);
        }

@Override
public void onBindViewHolder(homeadapter.MyViewHolder holder, final int position) {
        // set the data in items
        GetData home =mhome.get(position);
        holder.ratetext.setText(home.getRate());
  //  holder.comtext.setText(home.getCoun());
        holder.placeTitle.setText(home.getTitle());
        // Picasso.with(context).load(restaurant.getImageUrl()).into(holder.placeImage);
       // Toast.makeText(context, home.getImageUrl(), Toast.LENGTH_LONG).show();
        Glide.with(context)
        .load(home.getImageUrl())
        .thumbnail(Glide.with(context).load(R.drawable.giphy))
        .apply(new RequestOptions()
        .error(R.drawable.broken_image))
        .into(holder.placeImage);

        }
public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView placeTitle,ratetext,comtext;
    public ImageView placeImage;

    public MyViewHolder(View view) {
        super(view);
        placeTitle = (TextView) view.findViewById(R.id.hometitle);
        placeImage = (ImageView) view.findViewById(R.id.homeimage);
        ratetext = (TextView) view.findViewById(R.id.rate);
       // comtext = (TextView) view.findViewById(R.id.comments);



    }
}

    public GetData getRestaurant(int position) {
        return mhome.get(position);
    }
}
