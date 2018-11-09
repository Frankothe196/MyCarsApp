package com.thecarapp.mycarsapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseStorage firebaseStorage;
    private ImageView userProfilePic;
    private EditText userFname,userLname, userPassword, userEmail;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;



    ProgressBar progress;
    EditText editTextFname, editTextLname ,editTextEmail, editTextPassword,editTextTestPassword;
    Button btnReg;
    String email, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setupUIViews();
        progress= findViewById(R.id.progressSign);
        progress.setVisibility(View.INVISIBLE);

        editTextFname= findViewById(R.id.signFname);
        editTextLname= findViewById(R.id.signLname);
        editTextEmail = findViewById(R.id.signEmail);
        editTextPassword = findViewById(R.id.signPassword);
        editTextTestPassword = findViewById(R.id.testPassword);
        btnReg = findViewById(R.id.btnSubmit);

        mAuth = FirebaseAuth.getInstance();



        Button btnLogin=findViewById(R.id.btnLoginScreen);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentlogin= new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intentlogin);
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });

    }

    public void RegisterUser(){
        final String fname= editTextFname.getText().toString().trim();
        final String lname= editTextLname.getText().toString().trim();
        final String email= editTextEmail.getText().toString().trim();
        String password=  editTextPassword.getText().toString().trim();
        String testPass = editTextTestPassword.getText().toString().trim();



        if(fname.isEmpty()){
            editTextEmail.setError("First name field empty");
            editTextEmail.requestFocus();
            return;
        }
        if(lname.isEmpty()){
            editTextEmail.setError("Last name field empty");
            editTextEmail.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("Email field empty");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password field empty");
            editTextPassword.requestFocus();
            return;
        }


        if(password.length()<6){
            editTextPassword.setError("password must be longer than 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        if(testPass.isEmpty()){
            editTextTestPassword.setError("Password field empty");
            editTextTestPassword.requestFocus();
            return;
        }

        if(!password.equals(testPass)){
            editTextTestPassword.setError("password do not match");
            editTextTestPassword.requestFocus();
            return;
        }

        progress.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Uprofile user = new Uprofile(email, fname, lname);


                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("message");
                    myRef.setValue("Hello, World!");


                    FirebaseDatabase.getInstance().getReference("users names").child(FirebaseAuth.getInstance()
                            .getCurrentUser().getEmail()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(getApplicationContext(),"User registered successfully",Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.INVISIBLE);

                        }
                    });



                }else {

                    userProfile();
                    Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.INVISIBLE);

                    Intent intLogin= new Intent(SignupActivity.this,LoginActivity.class);
                    startActivity(intLogin);
                }
            }
        });

    }

    private void userProfile() {
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().
                    setDisplayName(editTextFname.getText().toString().trim())
    .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("testing", "updated");
                                }
                            }
                        });
                        }
        }



    private void setupUIViews(){
     userFname = (EditText)findViewById(R.id.signFname);
     userLname = (EditText)findViewById(R.id.signLname);
     userPassword = (EditText)findViewById(R.id.signPassword);
     userEmail = (EditText)findViewById(R.id.signEmail);

 }

/*
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(mAuth.getUid());
        StorageReference imageReference = storageReference.child(mAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignupActivity.this, "Upload failed!", Toast.LENGTH_SHORT).show();
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText(SignupActivity.this, "Upload successful!", Toast.LENGTH_SHORT).show();
            }
        });
        Uprofile userProfile = new Uprofile( email, name);
        myRef.setValue(userProfile);
    }
*/

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //updateUI(currentUser);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    //code below is used to register new users, it is redundant

   /* private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrationActivity.this, "Upload failed!", Toast.LENGTH_SHORT).show();
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText(RegistrationActivity.this, "Upload successful!", Toast.LENGTH_SHORT).show();
            }
        });
        UserProfile userProfile = new UserProfile(age, email, name);
        myRef.setValue(userProfile);
    }
    */

}