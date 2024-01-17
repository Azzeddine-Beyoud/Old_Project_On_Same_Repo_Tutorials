package com.example.fragmenttofragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.fragmenttofragment.NameFragment.AGR_NAME;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link textFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class textFragment extends Fragment {
    private String name ;
    public static final String AGR_NAME="name";


        public static textFragment newInstance(String name){
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        textFragment fragment = new textFragment();
        fragment.setArguments(bundle);
        return fragment;
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
        View v = inflater.inflate(R.layout.fragment_text, container, false);

        TextView tv = v.findViewById(R.id.tv_name);
        tv.setText(name);
        return v;
    }
}



//    // TODO: Rename and change types and number of parameters
//    public static textFragment newInstance(String param1, String param2) {
//        textFragment fragment = new textFragment();
//        Bundle args = new Bundle();
//
//        fragment.setArguments(args);
//        return fragment;
//    }