package com.example.app;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ProfileFragment extends Fragment {
Button button;
ImageView imageView;
int idValue;
String namevalue,lastnamevalue,phonenumbervalue,cityvalue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.profile, container, false);


        idValue=getArguments().getInt("id");
        namevalue=getArguments().getString("name");
        lastnamevalue=getArguments().getString("lastname");
        phonenumbervalue=getArguments().getString("phonenumber");
        cityvalue=getArguments().getString("city");
        imageView = view.findViewById(R.id.editt);



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),String.valueOf(idValue),Toast.LENGTH_SHORT).show();
            }
        });

        TextView nom=(TextView)view.findViewById(R.id.nomprofile);
        nom.setText(""+ namevalue);

        TextView prenom=(TextView)view.findViewById(R.id.prenomprofile);
        prenom.setText(""+ lastnamevalue);
//        TextView phonenumber=(TextView)view.findViewById(R.id.phonenumberprofile);
//        phonenumber.setText(""+phonenumbervalue);
//        TextView state=(TextView)view.findViewById(R.id.stateprofile);
//        state.setText(""+cityvalue);
        
        return view;
    }
}




