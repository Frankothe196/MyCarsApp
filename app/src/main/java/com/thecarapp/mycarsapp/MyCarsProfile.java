package com.thecarapp.mycarsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyCarsProfile extends AppCompatActivity {

    private FirebaseDatabase mFireabaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ProgressBar circular_progress;
    private ListView listcarsview;
    private List<usercars> list_cars;
    private usercars userselected;// this variable should hold the item selected on the list view
    private EditText input_year,input_make,input_model,input_engine,input_reg;

    FirebaseRecyclerOptions<usercars> options;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycarsprofile);

        //adding tooldbar
      Toolbar toolbar = findViewById(R.id.toolbarcarsmenu);
      setSupportActionBar(toolbar);

      circular_progress = findViewById(R.id.progresslistcars);

      listcarsview= findViewById(R.id.list_cars);

      listcarsview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              usercars cars = (usercars) parent.getItemAtPosition(position);
              userselected= cars;
              input_year.setText(cars.getYear());
              input_model.setText(cars.getModel());
              input_make.setText(cars.getMake());
              input_engine.setText(cars.getEngine());
              input_reg.setText(cars.getRegNo());

          }
      });

      mDatabaseReference=FirebaseDatabase.getInstance().getReference("92498cars");
      list_cars= new ArrayList<>();

      //initFirebase();
      addEventFirebaseListener();


    }

    private void addEventFirebaseListener() {
        circular_progress.setVisibility(View.VISIBLE);
        listcarsview.setVisibility(View.VISIBLE);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
            //  Firebase problem connecting firebase to listview
               // usercars cars = postSnapshot.getValue(usercars.class);
              // list_cars.add(cars);
           }
           ListViewAdapter adapter= new ListViewAdapter(MyCarsProfile.this,list_cars);
           listcarsview.setAdapter(adapter);

           circular_progress.setVisibility(View.INVISIBLE);
           listcarsview.setVisibility(View.VISIBLE);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        mFireabaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFireabaseDatabase.getReference();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainaddeditdel,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId()==R.id.menu_add){
            Intent intentadd= new Intent(MyCarsProfile.this,carAddActivity.class);
            startActivity(intentadd);
        }
        else if(item.getItemId()==R.id.menu_edit){
            usercars cardetails = new usercars(UUID.randomUUID().toString(),input_year.getText().toString(),input_make.getText().toString(),input_model.getText().toString(),input_engine.getText().toString(),input_reg.getText().toString());
            updateUser(cardetails);

        }else if (item.getItemId()==R.id.menu_delete){
            deleteCar(userselected);
            clearEditText();

        }

        return true;
    }

    private void deleteCar(usercars userselected) {
        mDatabaseReference.child("92498cars").child(userselected.getUid()).removeValue();
    }

    private void updateUser(usercars cardetails) {
        mDatabaseReference.child("92498cars").child(cardetails.getUid()).child("year").setValue(cardetails.getYear());
        mDatabaseReference.child("92498cars").child(cardetails.getUid()).child("make").setValue(cardetails.getMake());
        mDatabaseReference.child("92498cars").child(cardetails.getUid()).child("model").setValue(cardetails.getModel());
        mDatabaseReference.child("92498cars").child(cardetails.getUid()).child("engine").setValue(cardetails.getEngine());
        mDatabaseReference.child("92498cars").child(cardetails.getUid()).child("regNo").setValue(cardetails.getRegNo());

        clearEditText();

    }

    private void clearEditText() {
        input_reg.setText("");
        input_engine.setText("");
        input_model.setText("");
        input_make.setText("");
        input_year.setText("");
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_add) {
            Intent intentadd= new Intent(MyCarsProfile.this,carAddActivity.class);
            startActivity(intentadd);

        } else if (id == R.id.nav_manage) {

        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}