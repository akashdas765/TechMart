package com.example.techmart.ViewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techmart.R;

public class CartViewHolder extends RecyclerView.ViewHolder {

   public TextView n1,q1,p1;
   public ImageView i1;
   public ImageButton im2;
    public TextView d1,q2,p2,t1,addname;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        n1=(TextView)itemView.findViewById(R.id.productn);
        q1=(TextView)itemView.findViewById(R.id.productq);
        p1=(TextView)itemView.findViewById(R.id.productp);
        i1=(ImageView)itemView.findViewById(R.id.image);
        im2=(ImageButton)itemView.findViewById(R.id.delete);

        d1=(TextView)itemView.findViewById(R.id.date);
        q2=(TextView)itemView.findViewById(R.id.items);
        p2=(TextView)itemView.findViewById(R.id.totalp);
        t1=(TextView)itemView.findViewById(R.id.time);
        addname=(TextView)itemView.findViewById(R.id.addname);


    }
}
