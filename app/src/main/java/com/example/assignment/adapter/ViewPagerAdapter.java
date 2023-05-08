package com.example.assignment.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignment.R;
import com.example.assignment.interFace.OnClickViewPagerButtons;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>  {
    private List<String> imageList;
    private Context context;
    private OnClickViewPagerButtons onClickViewPagerButtons;

    public ViewPagerAdapter(List<String> imageList, Context context, OnClickViewPagerButtons onClickViewPagerButtons) {
        this.imageList = imageList;
        this.context = context;
        this.onClickViewPagerButtons = onClickViewPagerButtons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(imageList.get(position)).centerCrop().into(holder.image);
        holder.image.setOnClickListener(v -> {
            onClickViewPagerButtons.onClickViePageAndBtcButton(imageList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
        }
    }
}
