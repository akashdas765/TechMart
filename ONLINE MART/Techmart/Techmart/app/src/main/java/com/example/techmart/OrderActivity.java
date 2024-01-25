package com.example.techmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.techmart.Model.CartItem;
import com.example.techmart.Model.OrderItem;
import com.example.techmart.ViewHolder.CartViewHolder;
import com.example.techmart.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference,databaseReference1,bought;

    private String CurrentUserID;
    private FirebaseAuth firebaseAuth;

    private String IDs;
    FirebaseRecyclerOptions<CartItem> options;
    FirebaseRecyclerAdapter<CartItem, CartViewHolder> adapter;
    FirebaseRecyclerOptions<CartItem> options1;
    FirebaseRecyclerAdapter<CartItem, CartViewHolder> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Orders");


        recyclerView = (RecyclerView) findViewById(R.id.order_list);
        recyclerView.setHasFixedSize(true);
        firebaseAuth = FirebaseAuth.getInstance();
        CurrentUserID = firebaseAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(CurrentUserID).child("Orders");
        bought = FirebaseDatabase.getInstance().getReference();
        options = new FirebaseRecyclerOptions.Builder<CartItem>()
                .setQuery(databaseReference, CartItem.class).build();
        adapter = new FirebaseRecyclerAdapter<CartItem, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull CartItem model) {
                holder.d1.setText(model.date);
                holder.q2.setText("Quantitiy = " + model.itemsbought);
                holder.p2.setText("Total Price = ₹" + model.ordertotal);
                holder.t1.setText(model.time);
               // IDs=model.ID;
                holder.addname.setText(model.name);

            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items_layout, parent, false);

                return new CartViewHolder(view);
            }
        };


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

        /*databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Users").child(CurrentUserID).child("Orders").child(IDs).child("Address");
        options1 = new FirebaseRecyclerOptions.Builder<CartItem>()
                .setQuery(databaseReference1, CartItem.class).build();
        adapter1 = new FirebaseRecyclerAdapter<CartItem, CartViewHolder>(options1) {

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items_layout, parent, false);

                return new CartViewHolder(view1);
            }

            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull CartItem model) {
                holder.addname.setText(model.name);
            }
        };*/
    }
}
