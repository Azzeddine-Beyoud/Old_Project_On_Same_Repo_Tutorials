package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static android.content.Context.LOCATION_SERVICE;

public class MapsFragment extends Fragment {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener listener;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        SupportMapFragment mapFragment =
//                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
//        if (mapFragment != null) {
//            mapFragment.getMapAsync(callback);
//        }
//
//
//        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
//        listener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//
//                Log.d("locatoin",location.toString()) ;
////                mMap.clear();
//                LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
////                mMap.addMarker(new MarkerOptions().position(sydney).title("myFullAdress"));
////                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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
//        if (Build.VERSION.SDK_INT<23){
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
//        }else {
//            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
//                    PackageManager.PERMISSION_GRANTED){
//
//                ActivityCompat.requestPermissions(getActivity() ,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
//            }else {
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
//            }
//        }
//    }

    //// /////////////////////////////////////////////////////////////


    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;
            locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
            listener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    Log.d("location",location.toString()) ;

                    String myFullAdress = "";

                                Geocoder geocoder = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());
                                try {
                                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude()
                                            ,1);


                                    if (addressList != null && addressList.size()>0){
                                        Log.d("address",addressList.get(0).toString());

                                        if (addressList.get(0).getCountryName() !=null ){
                                            myFullAdress +=addressList.get(0).getCountryName() +"";
                                        }

                                        if (addressList.get(0).getAddressLine(0)!=null ){
                                            myFullAdress +=addressList.get(0).getAddressLine(0) +"";
                                        }

                                        if (addressList.get(0).getSubAdminArea() !=null ){
                                            myFullAdress +=addressList.get(0).getSubAdminArea() +"";
                                        }
//                                        Toast.makeText(getActivity(),myFullAdress,Toast.LENGTH_SHORT).show();

                                    }else {
                                        Log.d("address", "Address not found");
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
//
                    mMap.clear();
                    LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(sydney).title(myFullAdress));
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
        }
    };


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }


        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.d("locatoin",location.toString()) ;
                String myFullAdress = "";

                Geocoder geocoder = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude()
                            ,1);


                    if (addressList != null && addressList.size()>0){
                        Log.d("address",addressList.get(0).toString());

                        if (addressList.get(0).getCountryName() !=null ){
                            myFullAdress +=addressList.get(0).getCountryName() +"";
                        }

                        if (addressList.get(0).getAddressLine(0)!=null ){
                            myFullAdress +=addressList.get(0).getAddressLine(0) +"";
                        }

                        if (addressList.get(0).getSubAdminArea() !=null ){
                            myFullAdress +=addressList.get(0).getSubAdminArea() +"";
                        }
                        Toast.makeText(getActivity(),myFullAdress,Toast.LENGTH_SHORT).show();

                    }else {
                        Log.d("address", "Address not found");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mMap.clear();
                LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(sydney).title("myFullAdress"));
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
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(getActivity() ,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        return view;

    }
}

