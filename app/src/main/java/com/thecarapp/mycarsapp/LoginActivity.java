package com.thecarapp.mycarsapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextemail,editTextpassword;
    private Button btnSignin;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    ProgressBar progress;

    @Override
    protected void onStart() {
        super.onStart();
        //mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        Button btnlogin=  findViewById(R.id.buttonregister);

        btnSignin = findViewById(R.id.buttonLogin);
        progress = findViewById(R.id.progressLogin);
        editTextemail = findViewById(R.id.email);
        editTextpassword = findViewById(R.id.password);


        mAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Toast.makeText(LoginActivity.this, "User Logged in", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "User signed out ", Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register= new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(register);
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextemail.getText().toString().trim();
                String password = editTextpassword.getText().toString().trim();

                if (email.isEmpty()){
                    editTextemail.setError("Enter the email field");
                    editTextemail.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    editTextpassword.setError("Enter the Password field");
                    editTextpassword.requestFocus();
                    return;
                }

                progress.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                   editTextpassword.setError("Wrong email/password");
                                   editTextpassword.requestFocus();
                                   progress.setVisibility(View.INVISIBLE);
                                }else {
                                    Toast.makeText(LoginActivity.this, "Successfully signed in", Toast.LENGTH_SHORT).show();
                                    progress.setVisibility(View.INVISIBLE);
                                    Intent mainIntent = new Intent(LoginActivity.this, UserProfile.class);
                                    startActivity(mainIntent);

                                }
                            }
                        });
                    }
        });

    }
}
