package com.example.techmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        setContentView(R.layout.activity_login);
        editTextEmail=(EditText) findViewById(R.id.editTextEmail);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);
        buttonSignin=(Button) findViewById(R.id.buttonSignin);
        textViewSignUp=(TextView) findViewById(R.id.textViewSignUp);
        buttonSignin.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
    }
    private void userlogin(){
        String email=editTextEmail.getText().toString().trim();
        String Password=editTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            //Email is Empty
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            //Email is Empty
            return;
        }
        progressDialog.setMessage("Logging in User....");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful()){
                                    //start profile activity
                                    finish();
                                    startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this,"Account does not Exist",Toast.LENGTH_SHORT).show();

                                }
                            }
                        }

                );
    }
    @Override
    public void onClick(View view) {
        if(view==buttonSignin)
        {
            userlogin();
        }
        if(view==textViewSignUp)
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
