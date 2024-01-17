package com.example.project_final_ali.Ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_final_ali.MainActivity;
import com.example.project_final_ali.Module.Client;
import com.example.project_final_ali.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditCommand extends AppCompatActivity {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;


    private TextView editChangeName, editChangeReference, editChangeCity, editChangePhone, editChangePicture;
    private ImageView imageView;
    private Uri contentUri;
    private String imageFileName;
    private String currentPhotoPath;
    Button cameraBtn;

    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_command);

        editChangeName = findViewById(R.id.change_Name);
        editChangeReference = findViewById(R.id.change_Reference);
        editChangeCity = findViewById(R.id.change_City);
        editChangePhone = findViewById(R.id.change_Phone);
        imageView = findViewById(R.id .imagePageEdit);


        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askpermission();
            }
        });
    }

    public void askpermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERM_CODE);

        } else {
            dispatchTakePictureIntent();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();

            } else {
                Toast.makeText(this, "Camera Permission is Required Use camera", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void uploadImageToFirebase() {

        String fullname = editChangeName.getText().toString().trim();
        String reference = editChangeReference.getText().toString();
        String phone = editChangePhone.getText().toString();
        String city = editChangeCity.getText().toString().trim();


//        if (fullname.trim().isEmpty() || String.valueOf(phone).trim().isEmpty()) {
//            Toast.makeText(this, "please insert a Name and PhoneNumber", Toast.LENGTH_SHORT).show();
//            return;
//        }


        if (contentUri != null){
            final StorageReference image = mStorageRef.child("pictures/" + imageFileName);
            image.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            //hadi log d tbayanli bali ani njib la photo li gardiha fal firebase
                            // uri.to string
//                            Log.d("tag","onSuccess: Uploaded Image Uri is "+ uri.toString());
//                            Log.d("tag","onSuccess: FileUploaded Image Uri is "+ imageFileName);

                            Client client =new Client(fullname,uri.toString(),phone ,
                                    Integer.parseInt(reference),city);

                            CollectionReference notebookRef = FirebaseFirestore.
                                    getInstance().collection("Client");

                           // notebookRef.document().update()
//                            notebookRef.add(client);

                            // Picasso.get().load(uri).into();


                            startActivity(new Intent(EditCommand.this, MainActivity.class));
                            finish();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(EditCommand.this, "upload Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(this, "ContentUri is empty ", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                File f = new File(currentPhotoPath);
                imageView.setImageURI(Uri.fromFile(f));
                Log.d("tag", "Absolute Url of image is " + Uri.fromFile(f));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

                this.contentUri = contentUri;
//                imageFileName = f.getName();


                //hada ma3nah njib la phptp li kayna f storage ta3 telephone machi li kayna
                //fal firebase
                imageFileName = contentUri.toString();
                //et hada howa howa l path dylaha, file:///storage/emulated/0/Android/data/com.example.project_final_ali/files/Pictures/JPEG_20210925_091850_8482192296177081786.jpg

                //uploadImageToFirebase(f.getName(),contentUri);
            }

        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        //  File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileproviders", photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }
}