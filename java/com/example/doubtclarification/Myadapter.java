package com.example.doubtclarification;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;


public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>  {

    private Context context;
    private List<Upload> uploads;





    public Myadapter(Context context, List<Upload> uploads) {
        this.uploads = uploads;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_images, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,  int position) {
        final Upload upload = uploads.get(position);
        Glide.with(context).load(upload.getUrl()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,TextRecognization.class);
                intent.putExtra("image",upload.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);


            imageView = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }
}