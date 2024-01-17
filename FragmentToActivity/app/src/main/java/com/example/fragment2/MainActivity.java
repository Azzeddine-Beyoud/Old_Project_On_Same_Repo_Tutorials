package com.example.fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv=findViewById(R.id.main_rv);

        ArrayList<Name> names = new ArrayList<>();
        names.add(new Name("ahmed"));
        names.add(new Name("mohamed"));
        names.add(new Name("john"));
        names.add(new Name("aymen"));
        names.add(new Name("azzo"));

        NameAdapter adapter =new NameAdapter(names, new onItemClickListener() {
            @Override
            public void onnItemClick(Name name) {

                NameFragment fragment = NameFragment.newInstance(name.getName());

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_fragment,fragment);
                ft.commit();

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
            }
        });
        rv.setAdapter( adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }
}