package com.example.assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;

import java.util.List;

public class AgeAdaptor extends RecyclerView.Adapter<AgeAdaptor.ViewHolder> {
     private List<String> ageList;
     private Context context;

    public AgeAdaptor(List<String> ageList, Context context) {
        this.ageList = ageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.age_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.number.setText(ageList.get(position));
    }

    @Override
    public int getItemCount() {
        return ageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView number;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number=itemView.findViewById(R.id.number);
        }
    }
}
