package com.thecarapp.mycarsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class UserProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView ProfileEmail,ProfileName;


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseAuth mAuth;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //clicking on view action
        TextView cars = findViewById(R.id.mycarsview);
        TextView fuel = findViewById(R.id.fuelconsumptionview);
        TextView warehouse = findViewById(R.id.warehouseview);
        TextView service = findViewById(R.id.serviceview);
        TextView spares = findViewById(R.id.sparepartsview);




        ProfileName=findViewById(R.id.navprofilename);
        ProfileEmail=findViewById(R.id.navprofileemail);

        //setting user email and username to profile
        mAuth =FirebaseAuth.getInstance();


        if(mAuth.getCurrentUser()==null) {
            //user not logged in
            startActivity(new Intent(UserProfile.this, LoginActivity.class));
        }else if(mAuth.getCurrentUser()!=null) {
            Toast.makeText(this,"youre logged in", Toast.LENGTH_SHORT);
            loadUserInfo();

        }

        FirebaseUser user = mAuth.getCurrentUser();



        cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, MyCarsProfile.class);
                startActivity(intent);
            }


        });
        spares.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(UserProfile.this, SpareParts.class);
            startActivity(intent);

        }

    });

        warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, WarehouseMapsActivity.class);
                startActivity(intent);

            }

        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, BookService.class);
                startActivity(intent);

            }

        });
        fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, FuelConsumption.class);
                startActivity(intent);
            }
        });

    }//end of on create


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile, menu);
        return true;
    }

   /*code below used for menu item listener
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


    }
*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


public void loadUserInfo(){
        FirebaseUser user = mAuth.getCurrentUser();
       //String displayname= user.getDisplayName();

        //ProfileName.setText(displayname);
      //  String displayemail= user.getEmail();
        //ProfileEmail.setText(displayemail);


        }

public void onStart(){
    super.onStart();
    if(mAuth.getCurrentUser()==null) {
        startActivity(new Intent(this,LoginActivity.class) );

    }
    }

}
