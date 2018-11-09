package com.thecarapp.mycarsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class BookService extends AppCompatActivity {

    private EditText input_fname,input_email,input_tel,input_model,input_reg,input_date,input_time,input_info,input_branch;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_service);


        //thecontrols
        input_fname= findViewById(R.id.servicename);
        input_email=findViewById(R.id.serviceemail);
        input_tel=findViewById(R.id.servicetel);
        input_model=findViewById(R.id.servicemodel);
        input_reg=findViewById(R.id.servicereg);
        input_date=findViewById(R.id.servicedate);
        input_time=findViewById(R.id.servicetime);
        input_info=findViewById(R.id.serviceinfo);
        input_branch=findViewById(R.id.servicebranch);

        TextView viewsave= findViewById(R.id.serviceupload);


        viewsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookService.this,"Your service appointment has been booked",Toast.LENGTH_SHORT).show();
                createBooking();
                clearEditText();
                Intent intentbacktolist= new Intent(BookService.this, UserProfile.class);
                startActivity(intentbacktolist);
            }
        });

        //
        initFirebase();
        //  addEventFirebaseListener;
    }

    private void createBooking() {
        servicebookretrieve cardetails = new servicebookretrieve(UUID.randomUUID().toString(),input_fname.getText().toString(),input_email.getText().toString(),input_tel.getText().toString(),input_model.getText().toString(),input_reg.getText().toString(),input_date.getText().toString(),input_time.getText().toString(),input_branch.getText().toString(),input_info.getText().toString());
        mDatabaseReference.child("92498Booking").child(cardetails.getUid()).setValue(cardetails );


    }

    private void clearEditText() {
       input_info.setText("");
       input_date.setText("");
       input_reg.setText("");
       input_model.setText("");
       input_email.setText("");
       input_fname.setText("");
       input_tel.setText("");
       input_time.setText("");
    }


    private void initFirebase(){
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference();
    }
}
