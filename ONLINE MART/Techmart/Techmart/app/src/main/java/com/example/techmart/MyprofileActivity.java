package com.example.techmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyprofileActivity extends AppCompatActivity {

    private TextView name;
    private TextView email;
    private RadioGroup gender;
    private TextView phone;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    private DatabaseReference databaseReference;
    private String CurrentUserID;

    private Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        name=(TextView)findViewById(R.id.name);
        gender=(RadioGroup)findViewById(R.id.gender);

        email=(TextView)findViewById(R.id.email);
        phone=(TextView)findViewById(R.id.phone);


        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();


        databaseReference= FirebaseDatabase.getInstance().getReference();
        CurrentUserID=firebaseAuth.getCurrentUser().getUid();

        Save=(Button)findViewById(R.id.save);

        values();

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getvalues();

            }
        });

    }
private void values() {
    databaseReference.child("Users").child(CurrentUserID).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
       if(dataSnapshot.hasChild("Phone No"))
        {
            String S=dataSnapshot.child("Phone No").getValue().toString();
            phone.setText(S);
        }
        if(dataSnapshot.hasChild("Name"))
        {
            String N=dataSnapshot.child("Name").getValue().toString();
            name.setText(N);
        }
        if(dataSnapshot.hasChild("Email"))
        {
            String N=dataSnapshot.child("Email").getValue().toString();
            email.setText(N);
        }
        if(dataSnapshot.hasChild("Gender"))
        {
            String N=dataSnapshot.child("Gender").getValue().toString();
            if(N.equals("Male"))
            {
                gender.check(R.id.male);
            }
            if(N.equals("Female"))
            {
                gender.check(R.id.female);
            }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
}
private void getvalues(){
        String S1=phone.getText().toString();
        databaseReference.child("Users").child(CurrentUserID).child("Phone No").setValue(S1);
        String S2=name.getText().toString();
        databaseReference.child("Users").child(CurrentUserID).child("Name").setValue(S2);
        }
}
