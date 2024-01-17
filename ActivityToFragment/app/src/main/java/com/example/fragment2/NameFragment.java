package com.example.fragment2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NameFragment extends Fragment {

private String name ;
public static final String AGR_NAME="name";
private OnFragmentClickListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentClickListener)
            listener= (OnFragmentClickListener) context;
        else
            throw new ClassCastException("your activity not does implements onFragmentClickListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null){
            name = bundle.getString(AGR_NAME);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_name, container, false);


        RecyclerView rv=v.findViewById(R.id.main_rv);

        ArrayList<Name> names = new ArrayList<>();
        names.add(new Name("ahmed"));
        names.add(new Name("mohamed"));
        names.add(new Name("john"));
        names.add(new Name("aymen"));
        names.add(new Name("azzo"));

        NameAdapter adapter =new NameAdapter(names, new onItemClickListener() {
            @Override
            public void onnItemClick(Name name) {

                listener.onFragmentInteraction(name);
            }
        });
        rv.setAdapter( adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;
    }
//

    public interface OnFragmentClickListener{
        void onFragmentInteraction(Name name);
    }
}


//    public static NameFragment newInstance(String name){
//        Bundle bundle = new Bundle();
//        bundle.putString("name",name);
//        NameFragment fragment = new NameFragment();
//        fragment.setArguments(bundle);
//        return fragment;
//    }