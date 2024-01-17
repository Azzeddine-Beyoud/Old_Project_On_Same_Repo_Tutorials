package com.example.project_final_ali.Ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_final_ali.Module.Client;
import com.example.project_final_ali.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProfileCommand extends AppCompatActivity {

    private TextView profileName, profileReference, profileCity, profilePhone, profilePrice;
    private ImageView imagePageEdit;
    private Button editProfile;
    String nom;
    Bundle extras;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private DocumentReference clientRef = db.collection("Client").document();

    private CollectionReference clientRef = db.collection("Client");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_command);

        setTitle("Command");
        profileName = findViewById(R.id.profileName);
        profileReference = findViewById(R.id.profileReference);
        profileCity = findViewById(R.id.profileCity);
        profilePhone = findViewById(R.id.profilePhone);
        imagePageEdit = findViewById(R.id.imagePageEdit);
        editProfile = findViewById(R.id.editProfile);

        extras = getIntent().getExtras();


        getDocument();
        goToEditCommand();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_command, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void goToEditCommand(){

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String extraId = extras.getString("ID");
                Intent intent = new Intent(ProfileCommand.this ,EditCommand.class);
                intent.putExtra("ID",extraId);
                startActivity(intent);

            }
        });
    }


    //onStart give us the value directily because the update localy and it's happening in the database
    @Override
    protected void onStart() {
        String extraId = extras.getString("ID");
        super.onStart();
        clientRef.document(extraId).addSnapshotListener(this ,new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(ProfileCommand.this, "Error while loading!", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", error.toString());
                    return;
                }

                if (documentSnapshot.exists()) {
//                    Client client = documentSnapshot.toObject(Client.class);
//                    client.setDocumentID(documentSnapshot.getId());
//
//                    String id = documentSnapshot.getId();
//                    String name = documentSnapshot.getString("nameClient");
//                    String pic = documentSnapshot.getString("pictureUri");
//                    String reference = String.valueOf(documentSnapshot.get("reference"));
//                    String phone = String.valueOf(documentSnapshot.get("phone"));

                    Client client = documentSnapshot.toObject(Client.class);
                    client.setDocumentID(documentSnapshot.getId());

                    String documentId = client.getDocumentID();
                    String name = client.getNameClient();
                    String pic = client.getPictureUri();
                    String reference = String.valueOf(client.getReference());
                    String phone = client.getPhone();
//                    String city = client.getCity();


                    //profilePrice.setText(pic);
                    profileName.setText(name);
                    profileReference.setText(reference);
                    profilePhone.setText(phone);
//                    profileCity.setText(city);
                    Picasso.get().load(pic)
                            .fit()
                            .centerCrop()
                            .into(imagePageEdit);

//                    profileName.setText(name);

                    //Toast.makeText(ProfileCommand.this, "id: " + pic, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getDocument() {

        String extraId = extras.getString("ID");

        clientRef.document(extraId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()) {
                    Client client = documentSnapshot.toObject(Client.class);
                    String id = documentSnapshot.getId();
                    String name = documentSnapshot.getString("nameClient");
                    String pic = documentSnapshot.getString("pictureUri");
                    String reference = String.valueOf(documentSnapshot.get("reference"));
                    String phone = documentSnapshot.getString("phone");

//                    Client client = documentSnapshot.toObject(Client.class);
//                    String id = documentSnapshot.getId();
//                    String name =client.getNameClient();
//                    String pic = client.getPictureUri();
//                    String reference = String.valueOf(client.getReference());
//                    String phone = String.valueOf(client.getPhone());

                    //profilePrice.setText(pic);
                    profileName.setText(name);
                    profileReference.setText(reference);
                    profilePhone.setText(phone);
                    Picasso.get().load(pic)
                            .fit()
                            .centerCrop()
                            .into(imagePageEdit);

//                    profileName.setText(name);

                    //Toast.makeText(ProfileCommand.this, "id: " + pic, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileCommand.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                }
            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileCommand.this, "field", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
//
//  clientRef.get()
//          .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//@Override
//public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//        List<DocumentSnapshot> list = new ArrayList<>();
//        //String IDS = null;
//        for (int i = 0; i < queryDocumentSnapshots.getDocuments().size(); i++) {
//        Query query = queryDocumentSnapshots.getQuery();
//        String id = ;
//
//        Toast.makeText(ProfileCommand.this, "id: " + id, Toast.LENGTH_SHORT).show();
//        Log.d("tag", String.valueOf(list.size()));
//        }
//        }
//
//        }).addOnFailureListener(new OnFailureListener() {
//@Override
//public void onFailure(@NonNull Exception e) {
//        Toast.makeText(ProfileCommand.this, "faild", Toast.LENGTH_SHORT).show();
//        }
//        });
