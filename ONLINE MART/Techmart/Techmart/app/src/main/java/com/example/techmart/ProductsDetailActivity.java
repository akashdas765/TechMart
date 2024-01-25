package com.example.techmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ProductsDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonbuy;
    private TextView textViewitems;
    private TextView textViewprice;

    private TextView i1;
    private TextView i2;
    private TextView i3;
    private TextView i4;

    private String CurrentUserID;

    private TextView line1,line2,line3,line4;

    private ElegantNumberButton Quantity;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    private int n5;
    private int n3;
    private int n6;
    private int n4;
    private int q;

    private String id,name;
    private String price,image;
    private ImageView im1;
    private ImageView im2;
    private ImageView im3;
    private ImageView im4;

    private String proname;


    private String productid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Loading....");

        textViewitems=(TextView)findViewById(R.id.textViewitems);
        textViewprice=(TextView) findViewById(R.id.textViewprice);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            String myParam = extras.getString("ID");
            productid=myParam;
        }
        else
        {

        }

        im1=(ImageView)findViewById(R.id.im1);
        im2=(ImageView)findViewById(R.id.im2);
        im3=(ImageView)findViewById(R.id.im3);
        im4=(ImageView)findViewById(R.id.im4);

        line1=(TextView)findViewById(R.id.line1);
        line2=(TextView)findViewById(R.id.line2);
        line3=(TextView)findViewById(R.id.line3);
        line4=(TextView)findViewById(R.id.line4);

        buttonbuy=(Button)findViewById(R.id.buttonbuy);
        buttonbuy.setOnClickListener(this);

        Quantity=(ElegantNumberButton)findViewById(R.id.quantitybtn);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();
        CurrentUserID=firebaseAuth.getCurrentUser().getUid();

        getdata();

        getimages();

        getname();

        getdisp();

        databaseReference.child("Users").child(CurrentUserID).child("Cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(id))
                {
                    String n = dataSnapshot.child(id).child("quantity").getValue().toString();
                    Quantity.setNumber(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Quantity.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                String count =Quantity.getNumber();
                n5=Integer.valueOf(count);
            }
        });
        q=q+n5;

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductsDetailActivity.this, CartActivity.class));
            }
        });
    }


    private void getdata() {
        databaseReference.child("Products").child(productid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("No of items Left"))
                {
                    String n = dataSnapshot.child("No of items Left").getValue().toString();
                    int m = Integer.valueOf(n);
                    String n1 = Integer.toString(m);
                    n3=m;
                    textViewitems.setText(n1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child(productid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("No of items Sold"))
                {
                    String n = dataSnapshot.child("No of items Sold").getValue().toString();
                    int m = Integer.valueOf(n);
                    n6=m;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child(productid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Price")) {
                    String n2 = dataSnapshot.child("Price").getValue().toString();
                    int m1 = Integer.valueOf(n2);
                    price=n2;
                    String n4 = Integer.toString(m1);
                    textViewprice.setText("₹"+n4);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child(productid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Product Name")) {
                    String n2 = dataSnapshot.child("Product Name").getValue().toString();
                    name=n2;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child(productid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("ProductID"))
                {
                    String n = dataSnapshot.child("ProductID").getValue().toString();
                    id=n;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child(productid).child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    image=n;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void putdata() {
        databaseReference.child("Products").child(productid).child("No of items Left").setValue(n3);
        databaseReference.child("Products").child(productid).child("No of items Sold").setValue(n6);
        databaseReference.child("Users").child(CurrentUserID).child("Cart").child(id).child("price").setValue(price);
        databaseReference.child("Users").child(CurrentUserID).child("Cart").child(id).child("proname").setValue(name);
        databaseReference.child("Users").child(CurrentUserID).child("Cart").child(id).child("imagelink").setValue(image);
        databaseReference.child("Users").child(CurrentUserID).child("Cart").child(id).child("prodid").setValue(id);
    }

    private void getimages(){
        databaseReference.child("Products").child(productid).child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("image1"))
                {
                    String n = dataSnapshot.child("image1").getValue().toString();
                    Picasso.get().load(n).into(im1, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child(productid).child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("image2"))
                {
                    String n = dataSnapshot.child("image2").getValue().toString();
                    Picasso.get().load(n).into(im2, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child(productid).child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("image3"))
                {
                    String n = dataSnapshot.child("image3").getValue().toString();
                    Picasso.get().load(n).into(im3, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child(productid).child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("image4"))
                {
                    String n = dataSnapshot.child("image4").getValue().toString();
                    Picasso.get().load(n).into(im4, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void getname() {
        databaseReference.child("Products").child(productid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Product Name")) {
                    String n2 = dataSnapshot.child("Product Name").getValue().toString();
                    proname=n2;
                    ActionBar actionBar = getSupportActionBar();
                    actionBar.setTitle(proname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getdisp()
    {
        databaseReference.child("Products").child(productid).child("Description").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("line1")) {
                    String n2 = dataSnapshot.child("line1").getValue().toString();
                    line1.setText(n2);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child(productid).child("Description").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("line2")) {
                    String n2 = dataSnapshot.child("line2").getValue().toString();
                    line2.setText(n2);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child(productid).child("Description").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("line3")) {
                    String n2 = dataSnapshot.child("line3").getValue().toString();
                    line3.setText(n2);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child(productid).child("Description").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("line4")) {
                    String n2 = dataSnapshot.child("line4").getValue().toString();
                    line4.setText(n2);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        if(v==buttonbuy) {
            if(n3>0&&n3>=n5) {
                if(n5!=0) {
                    n3 = n3 - n5;
                    n6 = n6 + n5;
                    n4 = n4 + n5;
                    String quan = Integer.toString(n4);
                    databaseReference.child("Users").child(CurrentUserID).child("Cart").child(id).child("quantity").setValue(quan);
                    putdata();
                }
                else
                {
                    Toast.makeText(ProductsDetailActivity.this,"Please Select the Quantity",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(ProductsDetailActivity.this,"No more Products Left",Toast.LENGTH_SHORT).show();
            }

        }

    }

}
