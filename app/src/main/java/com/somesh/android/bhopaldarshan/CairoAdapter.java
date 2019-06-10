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

/**
 * Created by nsaxena on 27/2/18.
 */

public class CairoAdapter extends RecyclerView.Adapter<CairoAdapter.MyViewHolder>{
    List<Get_Data_Cairo> mGetDataCairos;
    Context context;

    public CairoAdapter(Context context, List templeList) {
        this.context=context;
        this.mGetDataCairos = templeList;
    }
    @Override
    public int getItemCount() {
        return mGetDataCairos.size();
    }

    @Override
    public CairoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_cairo_adapter,parent, false);
        return new CairoAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CairoAdapter.MyViewHolder holder, final int position) {
        // set the data in items
        Get_Data_Cairo getDataCairo = mGetDataCairos.get(position);
        holder.placeTitle.setText(getDataCairo.getTitle());
        // loading album cover using Glide library
        Glide.with(context)
                .load(getDataCairo.getImageUrl())
                .thumbnail(Glide.with(context).load(R.drawable.giphy))
                .apply(new RequestOptions()
                        .error(R.drawable.broken_image))
                .into(holder.placeImage);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView placeTitle;
        public ImageView placeImage;

        public MyViewHolder(View view) {
            super(view);
            placeTitle=(TextView)view.findViewById(R.id.hometitle);
            placeImage=(ImageView)view.findViewById(R.id.homeimage);
        }
    }

    public Get_Data_Cairo getTemple(int position) {
        return mGetDataCairos.get(position);
    }
}