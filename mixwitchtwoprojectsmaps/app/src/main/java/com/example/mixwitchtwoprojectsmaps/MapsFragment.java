package com.example.mixwitchtwoprojectsmaps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.content.Context.LOCATION_SERVICE;


public class MapsFragment extends Fragment  {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener listener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_maps, container, false);



        SupportMapFragment supportMapFragment = (SupportMapFragment)getChildFragmentManager().
                findFragmentById(R.id.google_map);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                mMap = googleMap;

                locationManager = (LocationManager)getActivity().getSystemService(LOCATION_SERVICE);
                listener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Log.d("location",location.toString()) ;
                        mMap.clear();
                        LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Azzo Batna location "));
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
                if (Build.VERSION.SDK_INT<23){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
                }else {
                    if (ContextCompat.checkSelfPermission(getContext().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED){

                        ActivityCompat.requestPermissions(getActivity() ,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                    }else {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
                    }
                }

//                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                    @Override
//                    public void onMapClick(LatLng latLng) {
//                        MarkerOptions markerOptions = new MarkerOptions();
//
//                        markerOptions.position(latLng);
//                        markerOptions.title(latLng.latitude + ":" +latLng.longitude);
//                        googleMap.clear();
//                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
//                        googleMap.addMarker(markerOptions);
//
//                    }
//
//                });

            }
        });



        return view;

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if (ContextCompat.checkSelfPermission(getContext().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
            }
        }
    }

}

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
