package com.example.project_final_ali;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.project_final_ali.Ui.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private Button signoutButt ;
    public SharedPreferences preferences;
    private static final String SHARED_PREF_NAME ="mykey";
    private static final String KEY_EMAIL ="email";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_profile,container,false);

        signoutButt = view.findViewById(R.id.signOut);
        preferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        signoutButt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onClick(View view) {
                signOut();

            }
        });

        return view;

    }

    private void signOut(){

        FirebaseAuth.getInstance().signOut();
        String emailKey = preferences.getString(KEY_EMAIL,null);

        if (emailKey != null){

            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            startActivity(intent);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getActivity().finish();
        }
    }



}
