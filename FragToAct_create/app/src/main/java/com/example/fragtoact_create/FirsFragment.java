package com.example.fragtoact_create;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class FirsFragment extends Fragment {

    private onFragmentClickListener listener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof onFragmentClickListener)
        listener = (onFragmentClickListener) context;
        else
            throw new ClassCastException("your activity does not implement " +
                                          "onFragmentClickListener");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    public FirsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_firs, container, false);

        RecyclerView rv = v.findViewById(R.id.main_rv);

        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("ahmed"));
        people.add(new Person("azzo"));
        people.add(new Person("riyad"));
        people.add(new Person("anis"));
        people.add(new Person("rabia"));

        AdapterPerson adapterPerson = new AdapterPerson(people, new onItemClickListener() {
            @Override
            public void onnItemClick(Person person) {

                listener.onFragmentInteraction(person);
            }
        });

        rv.setAdapter(adapterPerson);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    public interface onFragmentClickListener{

        void onFragmentInteraction(Person person);
    }
}
















//    // TODO: Rename and change types and number of parameters
//    public static FirsFragment newInstance(String param1, String param2) {
//        FirsFragment fragment = new FirsFragment();
//        Bundle args = new Bundle();
//
//        fragment.setArguments(args);
//        return fragment;
//    }

//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;