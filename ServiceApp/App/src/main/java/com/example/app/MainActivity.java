package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;
import Controller.DatabaseHelper;
import Modul.ListPerson;
import Utils.Utils;
import View.SignUpLoginSQL;


public class MainActivity extends AppCompatActivity {
   DrawerLayout draw;
   Toolbar tool;
   private DatabaseHelper db;
   public Cursor C;
   BottomNavigationView Navi;
   String name,lastname,phonenumber,emailvalue,cityvalue;
   Fragment SelectedFragment = null;
   Bundle bundle;

    private LocationManager locationManager;
    private LocationListener listener;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        ///////////////////////////////////////////////Load Data
        List<ListPerson> DATAPAINTER = new ArrayList<>();
        List<ListPerson> DATAPLUMBER = new ArrayList<>();
        List<ListPerson> DATAMECHANIC = new ArrayList<>();
        int i;
        db = new DatabaseHelper(this) ;

        Painter.painteritems.clear();
        Plumber.plumberitems.clear();
        Mechanic.mechanicitems.clear();

      //  Fragment mapsFragment =new MapsFragment();



        DATAPAINTER =db.getAllRecordsForOtherTables(Utils.TABLE_PAINTER);
        DATAPLUMBER=db.getAllRecordsForOtherTables(Utils.TABLE_PLUMBER);
        DATAMECHANIC=db.getAllRecordsForOtherTables(Utils.TABLE_MECHANIC);



        for(i=0;i<DATAPAINTER.size();i++){
            Painter.painteritems.add(new ListPerson(DATAPAINTER.get(i).name,DATAPAINTER.get(i).Lastname,DATAPAINTER.get(i).Phonenumber,DATAPAINTER.get(i).city));
        }
        for(i=0;i<DATAPLUMBER.size();i++){
            Plumber.plumberitems.add(new ListPerson(DATAPLUMBER.get(i).name,DATAPLUMBER.get(i).Lastname,DATAPLUMBER.get(i).Phonenumber,DATAPLUMBER.get(i).city));
        }
        for(i=0;i<DATAMECHANIC.size();i++){
            Mechanic.mechanicitems.add(new ListPerson(DATAMECHANIC.get(i).name,DATAMECHANIC.get(i).Lastname,DATAMECHANIC.get(i).Phonenumber,DATAMECHANIC.get(i).city));
        }


////////////////////////////////////////////////////////Receive Data From LogIn

        Intent intent=getIntent();
        name=intent.getStringExtra("NamePassed");
        lastname=intent.getStringExtra("lastNamePassed");
        phonenumber=intent.getStringExtra("PhoneNumberPassed");
        emailvalue=intent.getStringExtra("EmailPassed");
        cityvalue=intent.getStringExtra("wilaya");
/////////////////////////////////////////////////////////////////////////Send Data to Profile


        bundle=new Bundle();
        bundle.putString("name",name);
        bundle.putString("lastname",lastname);
        bundle.putString("phonenumber",phonenumber);
        bundle.putString("Email",emailvalue);
        bundle.putString("wilaya",cityvalue);



////////////////////////////////////////////////////////
        ///first interface  which is Home
        Fragment Frag=new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contentframe,Frag).commit();

        //bottom navigation
      Navi=(BottomNavigationView)findViewById(R.id.bottomnavigation);

        Navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.GoToHome:
                        SelectedFragment = new HomeFragment();
                        break;

                    case R.id.Profile:
                        SelectedFragment = new ProfileFragment();
                        SelectedFragment.setArguments(bundle);
                        break;

                    case R.id.Maps:
                        SelectedFragment = new MapsFragment();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.contentframe,SelectedFragment).commit();
                        break;

                    case R.id.favourite:
                        SelectedFragment = new Favourite();
                        break;

                    case R.id.Search:
                        SelectedFragment = new Search();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.contentframe, SelectedFragment).commit();
                return true;

            }
            });




        //tool bar
        tool=(Toolbar)findViewById(R.id.toolbarita);
        setSupportActionBar(tool);

        //drawer
    draw=(DrawerLayout) findViewById(R.id.Drawer_1);
    ActionBarDrawerToggle tog=new ActionBarDrawerToggle(this,draw,tool,R.string.open,R.string.close);
    draw.addDrawerListener(tog);
    tog.syncState();



        // Elements of the drawer
        NavigationView viewry=(NavigationView)findViewById(R.id.naviview);
      final Intent back=new Intent(this, SignUpLoginSQL.class);


        viewry.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()){

                        case R.id.logout:
                        startActivity(back);
                }
                return true;
            }
        });


        ////////////////////// GPS GPS GPS ////////////////////
//
//        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
//        listener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//
//                Log.d("My Location ",location.toString());
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//
//            }
//        };
//
//        if (Build.VERSION.SDK_INT < 23){
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                    0, 0, listener);
//        }else {
//            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
//                    PackageManager.PERMISSION_GRANTED &&
//                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!=
//                            PackageManager.PERMISSION_GRANTED){
//
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},3);
//
//            }else {
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                        0, 0, listener);
//
//            }
//        }
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync( this);
//
//    }
               /////////////////////////// GPS GPS ////////////////////////
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//            if(ContextCompat.checkSelfPermission(this,
//                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
//
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                    0, 0, listener);
//
//        }
//
   }

                 /////////////////////////// GPS GPS //////////////////////

}




