package com.example.techmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle toggle;
    public String currentUserID;
    private DatabaseReference databaseReference;

    private SliderView sliderView;

    public LinearLayout l11,l12,l13,l14,l15,l16,l21,l22,l23,l24,l25,l26,l31,l32,l33,l34,l35,l36,l41,l42,l43,l44,l45,l46,l221,l451,l331,l231,l311,l411;

    private NavigationView navbar;

    private String productid;

    public ImageView i11,i12,i13,i14,i15,i16,i21,i22,i23,i24,i25,i26,i31,i32,i33,i34,i35,i36,i41,i42,i43,i44,i45,i46,i221,i451,i331,i231,i311,i411;
    public TextView t11,t12,t13,t14,t15,t16,t21,t22,t23,t24,t25,t26,t31,t32,t33,t34,t35,t36,t41,t42,t43,t44,t45,t46,t221,t451,t331,t231,t311,t411;
    public TextView n11,n12,n13,n14,n15,n16,n21,n22,n23,n24,n25,n26,n31,n32,n33,n34,n35,n36,n41,n42,n43,n44,n45,n46,n221,n451,n331,n231,n311,n411;

    public TextView n,e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(!isConnected(ProfileActivity.this)) buildDialog(ProfileActivity.this).show();
        else {
        }

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference();

        sliderView = findViewById(R.id.imageSlider);

        final SliderAdapterExample adapter = new SliderAdapterExample(this);
        adapter.setCount(2);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });

        l221=(LinearLayout)findViewById(R.id.l221);
        l451=(LinearLayout)findViewById(R.id.l451);
        l331=(LinearLayout)findViewById(R.id.l331);
        l231=(LinearLayout)findViewById(R.id.l231);
        l311=(LinearLayout)findViewById(R.id.l311);
        l411=(LinearLayout)findViewById(R.id.l411);

        i221=(ImageView)findViewById(R.id.i221);
        t221=(TextView)findViewById(R.id.t221);
        n221=(TextView)findViewById(R.id.n221);

        i451=(ImageView)findViewById(R.id.i451);
        t451=(TextView)findViewById(R.id.t451);
        n451=(TextView)findViewById(R.id.n451);

        i331=(ImageView)findViewById(R.id.i331);
        t331=(TextView)findViewById(R.id.t331);
        n331=(TextView)findViewById(R.id.n331);

        i231=(ImageView)findViewById(R.id.i231);
        t231=(TextView)findViewById(R.id.t231);
        n231=(TextView)findViewById(R.id.n231);

        i311=(ImageView)findViewById(R.id.i311);
        t311=(TextView)findViewById(R.id.t311);
        n311=(TextView)findViewById(R.id.n311);

        i411=(ImageView)findViewById(R.id.i411);
        t411=(TextView)findViewById(R.id.t411);
        n411=(TextView)findViewById(R.id.n411);
        
        l11=(LinearLayout)findViewById(R.id.l11);
        l12=(LinearLayout)findViewById(R.id.l12);
        l13=(LinearLayout)findViewById(R.id.l13);
        l14=(LinearLayout)findViewById(R.id.l14);
        l15=(LinearLayout)findViewById(R.id.l15);
        l16=(LinearLayout)findViewById(R.id.l16);
        l21=(LinearLayout)findViewById(R.id.l21);
        l22=(LinearLayout)findViewById(R.id.l22);
        l23=(LinearLayout)findViewById(R.id.l23);
        l24=(LinearLayout)findViewById(R.id.l24);
        l25=(LinearLayout)findViewById(R.id.l25);
        l26=(LinearLayout)findViewById(R.id.l26);
        l31=(LinearLayout)findViewById(R.id.l31);
        l32=(LinearLayout)findViewById(R.id.l32);
        l33=(LinearLayout)findViewById(R.id.l33);
        l34=(LinearLayout)findViewById(R.id.l34);
        l35=(LinearLayout)findViewById(R.id.l35);
        l36=(LinearLayout)findViewById(R.id.l36);
        l41=(LinearLayout)findViewById(R.id.l41);
        l42=(LinearLayout)findViewById(R.id.l42);
        l43=(LinearLayout)findViewById(R.id.l43);
        l44=(LinearLayout)findViewById(R.id.l44);
        l45=(LinearLayout)findViewById(R.id.l45);
        l46=(LinearLayout)findViewById(R.id.l46);

        t11=(TextView)findViewById(R.id.t11);
        t12=(TextView)findViewById(R.id.t12);
        t13=(TextView)findViewById(R.id.t13);
        t14=(TextView)findViewById(R.id.t14);
        t15=(TextView)findViewById(R.id.t15);
        t16=(TextView)findViewById(R.id.t16);
        t21=(TextView)findViewById(R.id.t21);
        t22=(TextView)findViewById(R.id.t22);
        t23=(TextView)findViewById(R.id.t23);
        t24=(TextView)findViewById(R.id.t24);
        t25=(TextView)findViewById(R.id.t25);
        t26=(TextView)findViewById(R.id.t26);
        t31=(TextView)findViewById(R.id.t31);
        t32=(TextView)findViewById(R.id.t32);
        t33=(TextView)findViewById(R.id.t33);
        t34=(TextView)findViewById(R.id.t34);
        t35=(TextView)findViewById(R.id.t35);
        t36=(TextView)findViewById(R.id.t36);
        t41=(TextView)findViewById(R.id.t41);
        t42=(TextView)findViewById(R.id.t42);
        t43=(TextView)findViewById(R.id.t43);
        t44=(TextView)findViewById(R.id.t44);
        t45=(TextView)findViewById(R.id.t45);
        t46=(TextView)findViewById(R.id.t46);

        n11=(TextView)findViewById(R.id.n11);
        n12=(TextView)findViewById(R.id.n12);
        n13=(TextView)findViewById(R.id.n13);
        n14=(TextView)findViewById(R.id.n14);
        n15=(TextView)findViewById(R.id.n15);
        n16=(TextView)findViewById(R.id.n16);
        n21=(TextView)findViewById(R.id.n21);
        n22=(TextView)findViewById(R.id.n22);
        n23=(TextView)findViewById(R.id.n23);
        n24=(TextView)findViewById(R.id.n24);
        n25=(TextView)findViewById(R.id.n25);
        n26=(TextView)findViewById(R.id.n26);
        n31=(TextView)findViewById(R.id.n31);
        n32=(TextView)findViewById(R.id.n32);
        n33=(TextView)findViewById(R.id.n33);
        n34=(TextView)findViewById(R.id.n34);
        n35=(TextView)findViewById(R.id.n35);
        n36=(TextView)findViewById(R.id.n36);
        n41=(TextView)findViewById(R.id.n41);
        n42=(TextView)findViewById(R.id.n42);
        n43=(TextView)findViewById(R.id.n43);
        n44=(TextView)findViewById(R.id.n44);
        n45=(TextView)findViewById(R.id.n45);
        n46=(TextView)findViewById(R.id.n46);
         
        i11=(ImageView)findViewById(R.id.i11);
        i12=(ImageView)findViewById(R.id.i12);
        i13=(ImageView)findViewById(R.id.i13);
        i14=(ImageView)findViewById(R.id.i14);
        i15=(ImageView)findViewById(R.id.i15);
        i16=(ImageView)findViewById(R.id.i16);
        i21=(ImageView)findViewById(R.id.i21);
        i22=(ImageView)findViewById(R.id.i22);
        i23=(ImageView)findViewById(R.id.i23);
        i24=(ImageView)findViewById(R.id.i24);
        i25=(ImageView)findViewById(R.id.i25);
        i26=(ImageView)findViewById(R.id.i26);
        i31=(ImageView)findViewById(R.id.i31);
        i32=(ImageView)findViewById(R.id.i32);
        i33=(ImageView)findViewById(R.id.i33);
        i34=(ImageView)findViewById(R.id.i34);
        i35=(ImageView)findViewById(R.id.i35);
        i36=(ImageView)findViewById(R.id.i36);
        i41=(ImageView)findViewById(R.id.i41);
        i42=(ImageView)findViewById(R.id.i42);
        i43=(ImageView)findViewById(R.id.i43);
        i44=(ImageView)findViewById(R.id.i44);
        i45=(ImageView)findViewById(R.id.i45);
        i46=(ImageView)findViewById(R.id.i46);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("TechMart");

        if (firebaseAuth.getCurrentUser() == null) {

            startActivity(new Intent(this, LoginActivity.class));
        }

        currentUserID = firebaseAuth.getCurrentUser().getUid();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        l221.setOnClickListener(this);
        l451.setOnClickListener(this);
        l331.setOnClickListener(this);
        l231.setOnClickListener(this);
        l311.setOnClickListener(this);
        l411.setOnClickListener(this);
        
        l11.setOnClickListener(this);
        l12.setOnClickListener(this);
        l13.setOnClickListener(this);
        l14.setOnClickListener(this);
        l15.setOnClickListener(this);
        l16.setOnClickListener(this);
        l21.setOnClickListener(this);
        l22.setOnClickListener(this);
        l23.setOnClickListener(this);
        l24.setOnClickListener(this);
        l25.setOnClickListener(this);
        l26.setOnClickListener(this);
        l31.setOnClickListener(this);
        l32.setOnClickListener(this);
        l33.setOnClickListener(this);
        l34.setOnClickListener(this);
        l35.setOnClickListener(this);
        l36.setOnClickListener(this);
        l41.setOnClickListener(this);
        l42.setOnClickListener(this);
        l43.setOnClickListener(this);
        l44.setOnClickListener(this);
        l45.setOnClickListener(this);
        l46.setOnClickListener(this);

        navbar=(NavigationView)findViewById(R.id.nav_bar);
        View headerView=navbar.getHeaderView(0);
        n=(TextView)headerView.findViewById(R.id.n);
        e=(TextView)headerView.findViewById(R.id.e);

        getEmail();

        navbar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                UserMenuSelector(menuItem);
                return false;
            }
        });

        getinfo();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, CartActivity.class));
            }
        });


    }
    private void getinfo() {

        databaseReference.child("Products").child("22").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i221, new Callback() {
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
        databaseReference.child("Products").child("22").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n221.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("22").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t221.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("45").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i451, new Callback() {
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
        databaseReference.child("Products").child("45").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n451.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("45").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t451.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("33").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i331, new Callback() {
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
        databaseReference.child("Products").child("33").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n331.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("33").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t331.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("23").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i231, new Callback() {
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
        databaseReference.child("Products").child("23").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n231.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("23").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t231.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("31").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i311, new Callback() {
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
        databaseReference.child("Products").child("31").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n311.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("31").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t311.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("41").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i411, new Callback() {
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
        databaseReference.child("Products").child("41").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n411.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("41").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t411.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        
        
        
        databaseReference.child("Products").child("11").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i11, new Callback() {
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
        databaseReference.child("Products").child("11").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n11.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("11").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t11.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        
        databaseReference.child("Products").child("12").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i12, new Callback() {
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
        databaseReference.child("Products").child("12").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n12.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("12").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t12.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        
        databaseReference.child("Products").child("13").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i13, new Callback() {
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
        databaseReference.child("Products").child("13").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n13.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("13").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t13.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("14").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i14, new Callback() {
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
        databaseReference.child("Products").child("14").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n14.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("14").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t14.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        
        databaseReference.child("Products").child("15").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i15, new Callback() {
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
        databaseReference.child("Products").child("15").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n15.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("15").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t15.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("16").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i16, new Callback() {
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
        databaseReference.child("Products").child("16").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n16.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("16").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t16.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        
        
        
        
        databaseReference.child("Products").child("21").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i21, new Callback() {
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
        databaseReference.child("Products").child("21").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n21.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("21").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t21.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("22").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i22, new Callback() {
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
        databaseReference.child("Products").child("22").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n22.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("22").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t22.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("23").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i23, new Callback() {
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
        databaseReference.child("Products").child("23").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n23.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("23").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t23.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("24").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i24, new Callback() {
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
        databaseReference.child("Products").child("24").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n24.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("24").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t24.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("25").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i25, new Callback() {
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
        databaseReference.child("Products").child("25").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n25.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("25").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t25.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        
        databaseReference.child("Products").child("26").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i26, new Callback() {
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
        databaseReference.child("Products").child("26").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n26.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("26").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t26.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        
        
        

        databaseReference.child("Products").child("31").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i31, new Callback() {
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
        databaseReference.child("Products").child("31").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n31.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("31").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t31.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("32").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i32, new Callback() {
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
        databaseReference.child("Products").child("32").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n32.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("32").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t32.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("33").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i33, new Callback() {
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
        databaseReference.child("Products").child("33").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n33.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("33").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t33.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("34").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i34, new Callback() {
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
        databaseReference.child("Products").child("34").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n34.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("34").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t34.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("35").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i35, new Callback() {
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
        databaseReference.child("Products").child("35").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n35.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("35").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t35.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("36").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i36, new Callback() {
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
        databaseReference.child("Products").child("36").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n36.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("36").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t36.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        
        
        
        
        
        databaseReference.child("Products").child("41").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i41, new Callback() {
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
        databaseReference.child("Products").child("41").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n41.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("41").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t41.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("42").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i42, new Callback() {
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
        databaseReference.child("Products").child("42").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n42.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("42").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t42.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("43").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i43, new Callback() {
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
        databaseReference.child("Products").child("43").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n43.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("43").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t43.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("44").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i44, new Callback() {
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
        databaseReference.child("Products").child("44").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n44.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("44").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t44.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("45").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i45, new Callback() {
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
        databaseReference.child("Products").child("45").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n45.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("45").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t45.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Products").child("46").child("images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("icon"))
                {
                    String n = dataSnapshot.child("icon").getValue().toString();
                    Picasso.get().load(n).into(i46, new Callback() {
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
        databaseReference.child("Products").child("46").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Product Name"))
                {
                    String n = dataSnapshot.child("Product Name").getValue().toString();
                    n46.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Products").child("46").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Price"))
                {
                    String n = dataSnapshot.child("Price").getValue().toString();
                    t46.setText("₹"+n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
    private void getEmail(){
        databaseReference.child("Users").child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Email"))
                {
                    String n = dataSnapshot.child("Email").getValue().toString();
                    e.setText(n);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Users").child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Name"))
                {
                    String n1 = dataSnapshot.child("Name").getValue().toString();
                    n.setText(n1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
        else return false;
        } else
        return false;
    }
    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Pleas check your Internet Connection and try again later");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        return builder;
    }

    private void UserMenuSelector(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.navlogout:
                firebaseAuth.signOut();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.profile:
                startActivity(new Intent(this, MyprofileActivity.class));
                break;
            case R.id.cart:
                startActivity(new Intent(this, CartActivity.class));
                break;
            case R.id.order:
                startActivity(new Intent(this, OrderActivity.class));
                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {
            if(item.getItemId()==R.id.navlogout)
            {
                firebaseAuth.signOut();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view==l221)
        {
            productid="22";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l451)
        {
            productid="45";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );

        }
        if(view==l331)
        {
            productid="33";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );

        }
        if(view==l231)
        {
            productid="23";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l311)
        {
            productid="31";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l411)
        {
            productid="41";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l35)
        {
            productid="35";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l32)
        {
            productid="32";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l33)
        {
            productid="33";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l36)
        {
            productid="36";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );

        }
        if(view==l31)
        {
            productid="31";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );

        }
        if(view==l34)
        {
            productid="34";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );

        }
        if(view==l22)
        {
            productid="22";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );

        }
        if(view==l24)
        {
            productid="24";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );

        }
        if(view==l25)
        {
            productid="25";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );

        }
        if(view==l23)
        {
            productid="23";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );

        }
        if(view==l21)
        {
            productid="21";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );

        }
        if(view==l26)
        {
            productid="26";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l44)
        {
            productid="44";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l45)
        {
            productid="45";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l42)
        {
            productid="42";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l46)
        {
            productid="46";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l43)
        {
            productid="43";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l41)
        {
            productid="41";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l16)
        {
            productid="16";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l11)
        {
            productid="11";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l12)
        {
            productid="12";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l13)
        {
            productid="13";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );

        }
        if(view==l15)
        {
            productid="15";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }
        if(view==l14)
        {
            productid="14";
            final String text = productid;
            Intent intent = new Intent(ProfileActivity.this, ProductsDetailActivity.class );
            intent.putExtra( "ID", text );
            startActivity( intent );
        }

    }
    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Sure want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}