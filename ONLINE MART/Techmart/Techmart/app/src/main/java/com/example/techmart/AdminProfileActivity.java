package com.example.techmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.techmart.Model.CartItem;
import com.example.techmart.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminProfileActivity extends AppCompatActivity {

    private RecyclerView admin;

    private String CurrentUserID;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference,bought;

    FirebaseRecyclerOptions<CartItem> options;
    FirebaseRecyclerAdapter<CartItem, CartViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        admin=(RecyclerView)findViewById(R.id.admin);
        admin.setHasFixedSize(true);

        firebaseAuth= FirebaseAuth.getInstance();
        CurrentUserID=firebaseAuth.getCurrentUser().getUid();

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(CurrentUserID).child("Cart");
        bought=FirebaseDatabase.getInstance().getReference();

    }
    @Override
    protected void onStart() {
        super.onStart();
        if(adapter!=null)
        {
            adapter.startListening();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.startListening();
        }
    }

}
