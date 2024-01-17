package com.example.project_final_ali;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_final_ali.Controller.AdapterClients;

import com.example.project_final_ali.Halper.MyButtonClickListener;
import com.example.project_final_ali.Halper.MySwipeHelper;
import com.example.project_final_ali.Module.Client;
import com.example.project_final_ali.Ui.CommandActivity;
import com.example.project_final_ali.Ui.ProfileCommand;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class HomeFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private AdapterClients adapter;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_home, container, false);

        setUpRecyclerView(view);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.addClient);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CommandActivity.class));
            }
        });

        return view;
    }


    public static class WrapContentLinearLayoutManager extends LinearLayoutManager {
        public WrapContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        //... constructor
        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
                Log.e("TAG", "meet a IOOBE in RecyclerView");
            }
        }
    }


    private void setUpRecyclerView(View view) {

//        Query query = notebookRef.orderBy("priority",Query.Direction.DESCENDING);
        CollectionReference notebookRef = FirebaseFirestore.getInstance().collection("Client");
        Query query = notebookRef;

        FirestoreRecyclerOptions<Client> option = new FirestoreRecyclerOptions
                .Builder<Client>()
                .setQuery(query, Client.class)
                .build();

        adapter = new AdapterClients(option);

//        adapter.notifyDataSetChanged();

        recyclerView = view.findViewById(R.id.recyclerViewID);

        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

//
        MySwipeHelper swipeHelper = new MySwipeHelper(getContext(), recyclerView, 200) {

            @Override
            public void instanatiateMybutton(RecyclerView.ViewHolder viewHolder, List<MySwipeHelper.MyButton> buffer) {
//RecyclerView.ViewHolder
                buffer.add(new MyButton(getActivity(),
                        "Delete", 30, 0, Color.parseColor("#ff3c30"),
                        new MyButtonClickListener() {
                            @Override
                            public void onClick(int pos) {

                                Toast.makeText(getActivity(), "Delete click", Toast.LENGTH_SHORT).show();
                                adapter.deleteClient(viewHolder.getAdapterPosition());

                            }
                        }));

                buffer.add(new MyButton(getActivity(),
                        "UpDate", 30, R.drawable.ic_edit_white_24,
                        Color.parseColor("#FF9502"),
                        new MyButtonClickListener() {
                            @Override
                            public void onClick(int pos) {


                                Toast.makeText(getActivity(),String.valueOf( viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getActivity(), "Update click", Toast.LENGTH_SHORT).show();
                            }
                        }));
            }
        };

        /////////////////////////////////////////////////////////////


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                switch (direction) {
                    case ItemTouchHelper.RIGHT:


                        break;
//                    case ItemTouchHelper.LEFT:
//
//                        break;
                }
                Toast.makeText(getContext(), "Phone", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

//
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
//                ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//                Toast.makeText(getContext(), "Phone", Toast.LENGTH_SHORT).show();
//            }
//        }).attachToRecyclerView(recyclerView);
        ///////////////////////////////////////////////////


        adapter.setOnItemClickListener(new AdapterClients.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {

                Client client = documentSnapshot.toObject(Client.class);
                String id = documentSnapshot.getId();
                String path = documentSnapshot.getReference().getPath();


                Toast.makeText(getContext(), "Position " + position + ", ID: " + id, Toast.LENGTH_SHORT).show();


//                CollectionReference clientIdRef =
//                        FirebaseFirestore.getInstance().collection("Client/"+id);


                Intent intent = new Intent(getActivity(), ProfileCommand.class);

                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });


//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}
