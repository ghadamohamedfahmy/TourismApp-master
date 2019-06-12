package com.somesh.android.bhopaldarshan;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class comment_adapter extends  RecyclerView.Adapter <com.somesh.android.bhopaldarshan.comment_adapter
        .MyViewHolder>
 {
     private FirebaseUser firebaseUser;
     private FirebaseAuth firebaseAuth;

    /* List<comments_list> mhome;
        Context context;

        public comment_adapter(Context context,List commentlist) {
            this.context=context;
            this.mhome = commentlist;
        }*/
        @Override
        public int getItemCount() {
            return 0;
        }

        @Override
        public com.somesh.android.bhopaldarshan.comment_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.commentadapter,parent, false);
            return new com.somesh.android.bhopaldarshan.comment_adapter.MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(com.somesh.android.bhopaldarshan.comment_adapter.MyViewHolder holder, final int position) {
            // set the data in items

            firebaseUser = firebaseAuth.getInstance().getCurrentUser();
           // comments_list comments_listtt=mhome.get(position);
           // comments_list home =mhome.get(position);
           // holder.placeTitle.setText(home.getComment());

            // Toast.makeText(context, home.getImageUrl(), Toast.LENGTH_LONG).show();

        }
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView placeTitle;
            public TextView username1;
            public ImageView placeImage;

            public MyViewHolder(View view) {
                super(view);
                placeTitle=(TextView)view.findViewById(R.id.comment_message);
                username1 = itemView.findViewById(R.id.comment_username);

            }
        }

     private void getUserInfo(ImageView imageView, final TextView username , String publisher){
         DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Cairo");
         reference.child("Comments").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 comments_list user = dataSnapshot.getValue(comments_list.class);
                 username.setText(user.getUser_id());

             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });
     }
}
/*
    private Context mcontext;
private List<comments_list> mcomment;
private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    public comment_adapter(Context mcontext, List<comments_list> mcomment) {
        this.mcontext = mcontext;
        this.mcomment = mcomment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext)
                .inflate(R.layout.commentadapter,parent, false);
        return new comment_adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        comments_list comments_listtt=mcomment.get(position);
        holder.commentlista.setText(comments_listtt.getMessage());
        getUserInfo(holder.image_prof,holder.username1, comments_listtt.getUser_id());
        holder.commentlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mcomment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView image_prof;
        public TextView username1,commentlista;
        public ViewHolder(View itemView) {
            super(itemView);
            username1 = itemView.findViewById(R.id.comment_username);
            commentlista = itemView.findViewById(R.id.comment_message);


        }
    }

    private void getUserInfo(ImageView imageView, final TextView username , String publisher){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Cairo");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              comments_list user = dataSnapshot.getValue(comments_list.class);
                username.setText(user.getUser_id());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
*/