package com.example.techmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techmart.Model.CartItem;
import com.example.techmart.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CartActivity extends AppCompatActivity  {
    RecyclerView recyclerView;
    DatabaseReference databaseReference,bought;

    private String CurrentUserID;
    private FirebaseAuth firebaseAuth;

    FirebaseRecyclerOptions<CartItem> options;
    FirebaseRecyclerAdapter<CartItem, CartViewHolder> adapter;

    private TextView totalPrice;
    private Button next;

    private int n3;
    private int n5;
    private int q;
    private int t=0;
    private int quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cart");

        recyclerView=(RecyclerView)findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);

        firebaseAuth= FirebaseAuth.getInstance();
        CurrentUserID=firebaseAuth.getCurrentUser().getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(CurrentUserID).child("Cart");
        bought=FirebaseDatabase.getInstance().getReference();

        next=(Button)findViewById(R.id.order);

        options=new FirebaseRecyclerOptions.Builder<CartItem>()
                .setQuery(databaseReference,CartItem.class).build();
        adapter=new FirebaseRecyclerAdapter<CartItem, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull final CartItem model) {
                Picasso.get().load(model.getImagelink()).into(holder.i1, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });
                holder.n1.setText(model.proname);
                holder.q1.setText("Quantitiy = "+model.quantity);
                holder.p1.setText("Price = ₹"+model.price);

                bought.child("Products").child(model.getProdid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("No of items Left"))
                        {
                            String n = dataSnapshot.child("No of items Left").getValue().toString();
                            int m = Integer.valueOf(n);
                            String n1 = Integer.toString(m);
                            n3=m;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                n5=Integer.valueOf(model.price);
                q=Integer.valueOf(model.quantity);
                quantity=quantity+q;
                int onet=n5*q;
                t=t+onet;
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialog();
                        String Total=Integer.toString(t);
                        Toast.makeText(CartActivity.this,"Order Total is "+t,Toast.LENGTH_LONG).show();
                    }
                });

                holder.im2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        n5=Integer.valueOf(model.price);
                        q=Integer.valueOf(model.quantity);
                        int onet=n5*q;
                        t=t-onet;
                        n3=n3+q;
                        quantity=quantity+q;
                        String Total=Integer.toString(t);
                        totalPrice.setText("Total Price = ₹"+Total);
                        bought.child("Products").child(model.getProdid()).child("No of items Left").setValue(n3);
                        databaseReference.child(model.getProdid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(CartActivity.this,"Item Removed Sucessfully",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence choices[]=new CharSequence[]
                                {
                                        "Edit",
                                        "Remove From Cart"
                                };
                        AlertDialog.Builder builder= new AlertDialog.Builder(CartActivity.this);
                        builder.setTitle("Options");

                        builder.setItems(choices, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which==0) {
                                       String productid=model.getProdid();
                                        final String text = productid;
                                        Intent intent = new Intent(CartActivity.this, ProductsDetailActivity.class );
                                        intent.putExtra( "ID", text );
                                        startActivity( intent );
                                }
                                if(which==1)
                                {
                                    n5=Integer.valueOf(model.price);
                                    q=Integer.valueOf(model.quantity);
                                    int onet=n5*q;
                                    t=t-onet;
                                    n3=n3+q;
                                    quantity=quantity+q;
                                    String Total=Integer.toString(t);
                                    totalPrice.setText("Total Price = ₹"+Total);
                                    bought.child("Products").child(model.getProdid()).child("No of items Left").setValue(n3);
                                    databaseReference.child(model.getProdid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(CartActivity.this,"Item Removed Sucessfully",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }
                            }
                        });
                        builder.show();
                    }
                });



            }
            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout,parent,false);

                return new CartViewHolder(view);
            }
        };

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

        totalPrice=(TextView)findViewById(R.id.totalprice);
        totalPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Total=Integer.toString(t);
                totalPrice.setText("Total Price = ₹"+Total);

            }
        });
    }

    public void openDialog() {

        final AlertDialog.Builder alert = new AlertDialog.Builder(CartActivity.this);
        View mview = getLayoutInflater().inflate(R.layout.layout_dialog,null);
        final EditText Name=mview.findViewById(R.id.name);
        final EditText Phone=mview.findViewById(R.id.phone);
        final EditText Flat=mview.findViewById(R.id.flat);
        final EditText Add1=mview.findViewById(R.id.address);
        final EditText City=mview.findViewById(R.id.city);
        final EditText State=mview.findViewById(R.id.state);
        final EditText Pincode=mview.findViewById(R.id.pincode);

        final String name=Name.getText().toString();
        final String phone=Phone.getText().toString();
        final String flat=Flat.getText().toString();
        final String add1=Add1.getText().toString();
        final String city=City.getText().toString();
        final String state=State.getText().toString();
        final String pincode=Pincode.getText().toString();



        final AlertDialog alertDialog = alert.create();

        alert.setView(mview)
                .setTitle("Add Address")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SimpleDateFormat sdf = new SimpleDateFormat("YYMMddHHmm", Locale.getDefault());
                        String currentDate = sdf.format(new Date());

                        SimpleDateFormat sdf1 = new SimpleDateFormat("HH-mm", Locale.getDefault());
                        String time=sdf1.format(new Date());

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                        String date=simpleDateFormat.format(new Date());

                            String tot=Integer.toString(t);
                            String itemsb=Integer.toString(quantity);

                            bought.child("Users").child(CurrentUserID).child("Orders").child(currentDate).child("Address").child("name").setValue(name);

                            bought.child("Users").child(CurrentUserID).child("Orders").child(currentDate).child("Address").child("phone").setValue(phone);

                            bought.child("Users").child(CurrentUserID).child("Orders").child(currentDate).child("Address").child("flat").setValue(flat);

                            bought.child("Users").child(CurrentUserID).child("Orders").child(currentDate).child("Address").child("address").setValue(add1);

                            bought.child("Users").child(CurrentUserID).child("Orders").child(currentDate).child("Address").child("city").setValue(city);

                            bought.child("Users").child(CurrentUserID).child("Orders").child(currentDate).child("Address").child("state").setValue(state);

                            bought.child("Users").child(CurrentUserID).child("Orders").child(currentDate).child("Address").child("pincode").setValue(pincode);

                            bought.child("Users").child(CurrentUserID).child("Orders").child(currentDate).child("date").setValue(date);

                            bought.child("Users").child(CurrentUserID).child("Orders").child(currentDate).child("ID").setValue(currentDate);

                            bought.child("Users").child(CurrentUserID).child("Orders").child(currentDate).child("time").setValue(time);

                            bought.child("Users").child(CurrentUserID).child("Orders").child(currentDate).child("ordertotal").setValue(tot);

                            bought.child("Users").child(CurrentUserID).child("Orders").child(currentDate).child("itemsbought").setValue(itemsb);

                            bought.child("Users").child(CurrentUserID).child("Cart").removeValue();


                            Toast.makeText(CartActivity.this, "Order recieved Sucessfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(CartActivity.this, ProfileActivity.class));

                    }
                });
        alert.show();
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
