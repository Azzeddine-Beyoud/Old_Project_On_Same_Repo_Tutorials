 package com.example.mapswithlocation;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LocationManager locationManager;
    private LocationListener listener;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE) ;
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

           //     Log.d("location",location.toString());
                mMap.clear();
                LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(sydney).title("azzzzoooooo"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (Build.VERSION.SDK_INT < 23){

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
        }else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}
                ,1);
            }else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED){

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
            }else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
            }

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE) ;
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
              //  String myFullAdress = "";
                Log.d("location",location.toString());

                mMap.clear();
                LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(sydney).title("azzzzoooooo"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


                //                mMap.addMarker(new MarkerOptions().position(sydney).title(myFullAdress));
//
//
//                Log.d("location",location.toString());
//
//                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
//                try {
//                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude()
//                     ,1);
//
//
//                    if (addressList != null && addressList.size()>0){
//                        Log.d("address",addressList.get(0).toString());
//
//                        if (addressList.get(0).getCountryName() !=null ){
//                            myFullAdress +=addressList.get(0).getCountryName() +"";
//                        }
//
//                        if (addressList.get(0).getAddressLine(0)!=null ){
//                            myFullAdress +=addressList.get(0).getAddressLine(0) +"";
//                        }
//
//                        if (addressList.get(0).getSubAdminArea() !=null ){
//                            myFullAdress +=addressList.get(0).getSubAdminArea() +"";
//                        }
//                        Toast.makeText(MapsActivity.this,myFullAdress,Toast.LENGTH_SHORT).show();
//
//                    }else {
//                       Log.d("address", "Address not found");
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        // Add a marker in Sydney and move the camera

    }
}