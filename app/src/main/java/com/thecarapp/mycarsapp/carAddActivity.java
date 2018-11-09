package com.thecarapp.mycarsapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;


public class carAddActivity extends AppCompatActivity {

    private EditText input_year,input_make,input_model,input_engine,input_reg;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add);

        //thecontrols
        input_year= findViewById(R.id.caryear);
        input_make=findViewById(R.id.carmake);
        input_model=findViewById(R.id.carModel);
        input_engine=findViewById(R.id.carengine);
        input_reg=findViewById(R.id.carreg);

        TextView viewsave= findViewById(R.id.viewbtnsave);

        viewsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(carAddActivity.this,"saved",Toast.LENGTH_SHORT).show();
                createUser();
                clearEditText();
                Intent intentbacktolist= new Intent(carAddActivity.this, MyCarsProfile.class);
                startActivity(intentbacktolist);
            }
        });

        //
        initFirebase();
      //  addEventFirebaseListener;
    }

    private void createUser() {
        usercars cardetails = new usercars(UUID.randomUUID().toString(),input_year.getText().toString(),input_make.getText().toString(),input_model.getText().toString(),input_engine.getText().toString(),input_reg.getText().toString());
        mDatabaseReference.child("92498cars").child(cardetails.getUid()).setValue(cardetails);


    }

    private void clearEditText() {
            input_reg.setText("");
            input_engine.setText("");
            input_model.setText("");
            input_make.setText("");
            input_year.setText("");
    }


    private void initFirebase(){
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference();
    }
}

