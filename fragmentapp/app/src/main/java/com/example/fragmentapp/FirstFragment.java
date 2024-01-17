package com.example.fragmentapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Toast.makeText(context,"on attach",Toast.LENGTH_SHORT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Toast.makeText(getActivity(),"on Create",Toast.LENGTH_SHORT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toast.makeText(getActivity(),"on create View",Toast.LENGTH_SHORT);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toast.makeText(getActivity(),"on Activity Created",Toast.LENGTH_SHORT);
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(),"on Start",Toast.LENGTH_SHORT);
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(),"on Resume",Toast.LENGTH_SHORT);
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getActivity(),"on Pause",Toast.LENGTH_SHORT);
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(),"on Stop",Toast.LENGTH_SHORT);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(),"on Destroy View",Toast.LENGTH_SHORT);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(getActivity(),"on View Created",Toast.LENGTH_SHORT);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(),"on Destroy",Toast.LENGTH_SHORT);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(getActivity(),"on Detach",Toast.LENGTH_SHORT);
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;






    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }




}