package com.somesh.android.bhopaldarshan;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private EditText loginEmailText;
    private EditText loginPassText;
    private Button loginBtn;
    private Button loginRegBtn;

    private FirebaseAuth mAuth;
FirebaseDatabase database;
DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginEmailText = findViewById(R.id.usrusr);
        loginPassText = findViewById(R.id.pswrdd);
        loginBtn = findViewById(R.id.login);
        loginRegBtn = findViewById(R.id.button2);
        database = FirebaseDatabase.getInstance();
       // users = database.getReference("users");
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

   loginRegBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent regIntent = new Intent(MainActivity.this, Register.class);
            startActivity(regIntent);

        }
    });


        loginBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String loginEmail = loginEmailText.getText().toString().trim();
            String loginPass = loginPassText.getText().toString().trim();

            if(!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)){
               // loginProgress.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(loginEmail, loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            sendToMain();

                        } else {

                            String errorMessage = task.getException().getMessage();
                            Toast.makeText(MainActivity.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();


                        }

                      //  loginProgress.setVisibility(View.INVISIBLE);

                    }
                });

            }


        }
    });


}

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){

            sendToMain();

        }


    }

    private void sendToMain() {

        Intent mainIntent = new Intent(MainActivity.this, login.class);
        startActivity(mainIntent);
        finish();

    }
}
