package com.example.locationsms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView showGPS ,mySubAdminArea ,myAddressLine;
    private EditText phoneNumber;
    private Button buttonSend;

    private LocationManager locationManager;
    private LocationListener listener;
    String[] appPermissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS};
    private static final int PERMISSION_REQUEST_CODE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showGPS=findViewById(R.id.showGPStext);
        mySubAdminArea=findViewById(R.id.mySubAdminArea);
        myAddressLine=findViewById(R.id.myAddressLine);

        phoneNumber = findViewById(R.id.editText);
        buttonSend = findViewById(R.id.sendbutton);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (checkAndPermissionRequest())
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                String fullAddress="";

                showGPS.setText(location.getLatitude()+" - " + location.getLongitude());
                Log.d("location", location.toString());

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),
                            location.getLongitude(),1);

                    if (addressList!=null && addressList.size()>0){
                        if (addressList.get(0).getSubAdminArea()!=null){
                            fullAddress += addressList.get(0).getSubAdminArea() +"";
                            mySubAdminArea.setText(addressList.get(0).getSubAdminArea() +"");
                        }
                        if (addressList.get(0).getAddressLine(0)!=null){
                            myAddressLine.setText(addressList.get(0).getAddressLine(0) +"");
                            fullAddress += addressList.get(0).getAddressLine(0) +"";


                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, listener);
    }

    private boolean checkAndPermissionRequest() {
        List<String> listPermissionNeeded = new ArrayList<>();
        for (String perm :appPermissions){
            if (ContextCompat.checkSelfPermission(this,perm)!= PackageManager.PERMISSION_GRANTED){
                listPermissionNeeded.add(perm);
            }
        }

        if (!listPermissionNeeded.isEmpty()){
            ActivityCompat.requestPermissions(this,listPermissionNeeded.
                    toArray(new String[listPermissionNeeded.size()]),PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }
}