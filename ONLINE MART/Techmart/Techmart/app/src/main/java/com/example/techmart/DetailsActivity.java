package com.example.techmart;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private RadioButton radiobutton;
    private Button buttonRegister;
    private EditText editTextPassword;
    private EditText editName;
    private EditText phoneNo;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private String CurrentUserID;
    private FirebaseUser user;

    private String Fname;
    private String Phoneno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        radioGroup=(RadioGroup)findViewById(R.id.gender);
        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();

        databaseReference= FirebaseDatabase.getInstance().getReference();
        CurrentUserID=firebaseAuth.getCurrentUser().getUid();

        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editName = (EditText)findViewById(R.id.editTextName);
        phoneNo = (EditText) findViewById(R.id.editTextnumber);
        radioGroup.setOnCheckedChangeListener(this);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==buttonRegister)
                {
                    registerUser();
                }
            }
        });
    }
    private void registerUser() {
        Fname = editName.getText().toString().trim();
        String fn = Fname;
        Phoneno = phoneNo.getText().toString().trim();
        String p = Phoneno;
        if (TextUtils.isEmpty(fn)) {
            Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();
            //Email is Empty
            return;
        }
        if (TextUtils.isEmpty(p)) {
            Toast.makeText(this, "Please Enter Phone No.", Toast.LENGTH_SHORT).show();
            //Email is Empty
            return;
        }
        progressDialog.setMessage("Register User....");
        progressDialog.show();
        databaseReference.child("Users").child(CurrentUserID).child("Name").setValue(Fname);
        databaseReference.child("Users").child(CurrentUserID).child("Phone No").setValue(Phoneno);
        databaseReference.child("Users").child(CurrentUserID).child("Email").setValue(user.getEmail());

        startActivity(new Intent(this,ProfileActivity.class));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch(checkedId){
            case R.id.male:
                databaseReference.child("Users").child(CurrentUserID).child("Gender").setValue("Male");
            break;
            case R.id.female:
                databaseReference.child("Users").child(CurrentUserID).child("Gender").setValue("Female");
                break;

        }
    }
}
