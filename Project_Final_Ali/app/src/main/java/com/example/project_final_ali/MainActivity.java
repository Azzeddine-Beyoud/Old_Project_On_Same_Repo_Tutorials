package com.example.project_final_ali;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.project_final_ali.Controller.AdapterLocal;
import com.example.project_final_ali.Controller.DataBaseHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static AdapterLocal adapterLocal ;
    RecyclerView recyclerViewLocal;
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav= findViewById(R.id.bottom_navigation);
        bottomNav. setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        dataBaseHandler = new DataBaseHandler(this);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.nav_person:
                            selectedFragment = new ProfileFragment();
                            break;

                        case R.id.nav_terminer:
                            selectedFragment = new TerminerFragment();
                            break;

                        case R.id.nav_offline:
                            if(dataBaseHandler.getDataCount()!= 0){
                                selectedFragment = new OffLineFragment();
                            }else {
                                Toast.makeText(MainActivity.this, "the list is vide", Toast.LENGTH_SHORT).show();
                            }
                            break;


                    }

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container,selectedFragment).commit();

                    return true;
                }
            } ;
}