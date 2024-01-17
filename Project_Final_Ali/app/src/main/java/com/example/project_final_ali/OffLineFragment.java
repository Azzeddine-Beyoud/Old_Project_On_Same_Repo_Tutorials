package com.example.project_final_ali;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_final_ali.Controller.AdapterClients;
import com.example.project_final_ali.Controller.AdapterLocal;
import com.example.project_final_ali.Controller.DataBaseHandler;
import com.example.project_final_ali.Halper.MyButtonClickListener;
import com.example.project_final_ali.Halper.MySwipeHelper;
import com.example.project_final_ali.Module.Client;
import com.example.project_final_ali.Ui.ProfileCommand;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;

public class OffLineFragment extends Fragment {

    public static AdapterLocal adapterLocal ;
    RecyclerView recyclerViewLocal;
    DataBaseHandler dataBaseHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_offline, container, false);


            setUpRecyclerView(view);

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

    @SuppressLint("NotifyDataSetChanged")
    private void setUpRecyclerView(View view) {
//        adapter.notifyDataSetChanged();
        recyclerViewLocal = view.findViewById(R.id.recyclerViewLocal);
        dataBaseHandler = new DataBaseHandler(getContext());
        adapterLocal = new AdapterLocal(getContext(),dataBaseHandler.getALLClients(),dataBaseHandler);
        adapterLocal.notifyDataSetChanged();
        recyclerViewLocal.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerViewLocal.setLayoutManager(new WrapContentLinearLayoutManager(getContext(),
//                LinearLayoutManager.VERTICAL, false));

//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewLocal.setHasFixedSize(true);
        recyclerViewLocal.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLocal.setAdapter(adapterLocal);

//
        MySwipeHelper swipeHelper = new MySwipeHelper(getContext(), recyclerViewLocal, 200) {

            @Override
            public void instanatiateMybutton(RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {
                //RecyclerView.ViewHolder
                buffer.add(new MyButton(getActivity(),
                        "Delete", 30, 0, Color.parseColor("#ff3c30"),
                        new MyButtonClickListener() {
                            @Override
                            public void onClick(int pos) {

                                Toast.makeText(getActivity(), "Delete click", Toast.LENGTH_SHORT).show();
                                //adapterLocal.deleteClient(viewHolder.getAdapterPosition());

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
        }).attachToRecyclerView(recyclerViewLocal);

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

//
//        adapterLocal.setOnItemClickListener(new AdapterClients.OnItemClickListener() {
//            @Override
//            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
//
//                Client client = documentSnapshot.toObject(Client.class);
//                String id = documentSnapshot.getId();
//                String path = documentSnapshot.getReference().getPath();
//
//
//                Toast.makeText(getContext(), "Position " + position + ", ID: " + id, Toast.LENGTH_SHORT).show();
//
//
////                CollectionReference clientIdRef =
////                        FirebaseFirestore.getInstance().collection("Client/"+id);
//
//
//                Intent intent = new Intent(getActivity(), ProfileCommand.class);
//
//                intent.putExtra("ID", id);
//                startActivity(intent);
//            }
//        });


    }
    public static void notifyAdapter(){
        adapterLocal.notifyDataSetChanged();
    }

}
