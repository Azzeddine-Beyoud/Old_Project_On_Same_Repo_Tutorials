package com.example.fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NameFragment.OnFragmentClickListener {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv_name);

    }

    @Override
    public void onFragmentInteraction(Name name) {

        Toast.makeText(this,name.getName(),Toast.LENGTH_SHORT).show();
        tv.setText(name.getName());
    }
}


//                NameAdapter adapter =new NameAdapter(names, new onItemClickListener() {
//                    @Override
//                    public void onnItemClick(Name name) {
//                        Bundle bundle = new Bundle();
//                        bundle.putString("name",name.getName());
//                        NameFragment fragment = new NameFragment();
//                        fragment.setArguments(bundle);
//                        FragmentManager fm = getSupportFragmentManager();
//                        FragmentTransaction ft = fm.beginTransaction();
//                        ft.replace(R.id.main_fragment,fragment);
//                        ft.commit();
//




//        Toast.makeText(MainActivity.this,name.getName(),Toast.LENGTH_SHORT).show();