package com.example.my_map;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback ,
             GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    private LatLng darna = new LatLng(32.791684,4.490987);
    private LatLng elHamis = new LatLng(36.727354,3.244364);
    private LatLng ouedSmar= new LatLng(36.718323,3.161786);
    private LatLng babzawar = new LatLng(36.711512, 3.199178);

    private Marker mDarna;
    private Marker mElHamis;
    private Marker mOuedSmar;
    private Marker mBabZawar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        List<Marker> markerList= new ArrayList<>();

        mDarna = mMap.addMarker(new MarkerOptions().position(darna).title("Ghardai-DARNA")
                     .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        mDarna.setTag(0);
        markerList.add(mDarna);

        mElHamis = mMap.addMarker(new MarkerOptions().position(elHamis).title("Elhamiz_Rissala")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mElHamis.setTag(0);
        markerList.add(mElHamis);

        mOuedSmar = mMap.addMarker(new MarkerOptions().position(ouedSmar).title("OueSmar-ihab")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mOuedSmar.setTag(0);
        markerList.add(mOuedSmar);

        mBabZawar = mMap.addMarker(new MarkerOptions().position(babzawar).title("University-babzawar")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
        mBabZawar.setTag(0);
        markerList.add(mBabZawar);


        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        for (Marker m : markerList){
            LatLng latLng = new LatLng(m.getPosition().latitude,m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,5));
        }

        mMap.setOnMarkerClickListener(this);
        // Add a marker in Sydney and move the camera
       // LatLng Darna = new LatLng(32.791684, 4.490987);
//        mMap.addMarker(new MarkerOptions().position(darna)
//                .title("Rue Fabri MARCELLO, Mourad Raïs"))
//                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(darna,20));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(mourad_Raïs));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this,marker.getTitle(),Toast.LENGTH_SHORT).show();

        return false;
    }
}