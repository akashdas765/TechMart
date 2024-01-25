package com.example.techmart.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techmart.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    public TextView d1,q2,p2;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        d1=(TextView)itemView.findViewById(R.id.date);
        q2=(TextView)itemView.findViewById(R.id.items);
        p2=(TextView)itemView.findViewById(R.id.totalp);
    }
}
