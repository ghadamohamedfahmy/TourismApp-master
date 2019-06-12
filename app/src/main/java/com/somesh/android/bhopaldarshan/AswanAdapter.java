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
 * Created by nsaxena on 10/2/18.
 */

class AswanAdapter extends RecyclerView.Adapter<AswanAdapter.MyViewHolder> {

    List<GetData> aswanlist;
    Context context;

    public AswanAdapter(Context context, List aswanlist) {
        this.context=context;
        this.aswanlist = aswanlist;
    }
    @Override
    public int getItemCount() {
        return aswanlist.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aswan_adapter,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        //holder.placeTitle.setText((Integer) aswanlist.get(position));
        GetData getDataAswan = aswanlist.get(position);
        holder.placeTitle.setText(getDataAswan.getTitle());
        holder.ratetext.setText(getDataAswan.getRate());
        // loading album cover using Glide library
        Glide.with(context)
                .load(getDataAswan.getImageUrl())
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

    public GetData getVistingPlace(int position) {
        return aswanlist.get(position);
    }
}
