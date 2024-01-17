package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.Listitem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Listitem> listitems;
    //private RecyclerView.Adapter adapter;

    private MyAdapter adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerviewID);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listitems = new ArrayList<>();


        Listitem item1=new Listitem("azzeddine","java developer","24");
        Listitem item2=new Listitem("sifo","python developer","21");
        Listitem item3=new Listitem("ihab","php developer","20");
        Listitem item4=new Listitem("sofian","c++ developer","24");
        Listitem item5=new Listitem("houssam","kotlin developer","22");

        listitems.add(item1);
        listitems.add(item2);
        listitems.add(item3);
        listitems.add(item4);
        listitems.add(item5);



//        for (int x=0;x<12;x++){
//            Listitem listitem=new Listitem("Azzeddine"+(x+1),"Details","24");
//            listitems.add(listitem);
//        }

          adapter1=new MyAdapter(this,listitems);
          recyclerView.setAdapter(adapter1);



//        adapter=new MyAdapter(this,listitems);
//        recyclerView.setAdapter(adapter);
    }
}