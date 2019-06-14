package com.somesh.android.bhopaldarshan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class others_adapter extends RecyclerView.Adapter<others_adapter.MyViewHolder>{
    List<GetData> mGetData;
    Context context;

    public others_adapter(Context context, List restaurantList) {
        this.context=context;
        this.mGetData = restaurantList;
    }
    @Override
    public int getItemCount() {
        return mGetData.size();
    }

    @Override
    public others_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alex_adapter,parent, false);
        return new others_adapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(others_adapter.MyViewHolder holder, final int position) {
        // set the data in items
        GetData getData = mGetData.get(position);
        holder.placeTitle.setText(getData.getTitle());
        holder.ratetext.setText(""+(Math.round((Double.parseDouble(getData.getRate()))*1000.0)/1000.0));
        // Picasso.with(context).load(getData.getImageUrl()).into(holder.placeImage);
       // Toast.makeText(context, getData.getImageUrl(), Toast.LENGTH_LONG).show();
        Glide.with(context)
                .load(getData.getImageUrl())
                .thumbnail(Glide.with(context).load(R.drawable.giphy))
                .apply(new RequestOptions()
                        .error(R.drawable.broken_image))
                .into(holder.placeImage);

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView placeTitle,ratetext;
        public ImageView placeImage;

        public MyViewHolder(View view) {
            super(view);
            placeTitle=(TextView)view.findViewById(R.id.hometitle);
            placeImage=(ImageView)view.findViewById(R.id.homeimage);

            ratetext=(TextView)view.findViewById(R.id.rate);
        }
    }

    public GetData getRestaurant(int position) {
        return mGetData.get(position);
    }
}
