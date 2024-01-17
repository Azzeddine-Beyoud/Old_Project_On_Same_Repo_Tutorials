package com.example.project_final_ali.Ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_final_ali.Controller.DataBaseHandler;
import com.example.project_final_ali.DataPickerFragment;
import com.example.project_final_ali.MainActivity;
import com.example.project_final_ali.Module.Client;
import com.example.project_final_ali.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommandActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    //private static final int PICK_IMAGE_REQUEST = 1;

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    //public static final int GALLERY_REQUEST_CODE = 105;

    private Button cameraBtn;
    private EditText editTextName, editTextPhone, editTextCity, editTextReference;
    private NumberPicker numberPickerPriority;
    private ImageView imageView;
    private String currentPhotoPath;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private Uri contentUri;
    private String imageFileName;

    private StorageTask mUploadTask;
    private ProgressBar mProgressBar;

    private DataBaseHandler db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add command");

//        numberPickerPriority = findViewById(R.id.number_picker_priority);
        editTextName = findViewById(R.id.edit_Text_Name);
        editTextPhone = findViewById(R.id.edit_Text_Phone);
        editTextCity = findViewById(R.id.edit_Text_City);
        editTextReference = findViewById(R.id.edit_Text_Reference);
        mProgressBar = findViewById(R.id.progress_bar);


//        numberPickerPriority = findViewById(R.id.number_picker_priority);
//        numberPickerPriority.setMinValue(1);
//        numberPickerPriority.setMaxValue(10);

        imageView = findViewById(R.id.captureImage);
        cameraBtn = findViewById(R.id.captureImageButton);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askpermission();
            }
        });

        Button button = (Button) findViewById(R.id.data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dataPicker = new DataPickerFragment();
                dataPicker.show(getSupportFragmentManager(),"data picker");
            }
        });
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,i);
        c.set(Calendar.MONTH ,i1);
        c.set(Calendar.DAY_OF_MONTH,i2);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        TextView textView = findViewById(R.id.viewData);
        textView.setText(currentDateString);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_client_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_client:

                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(CommandActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();

                }else{
                    uploadImageToFirebase(item);
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
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


                //hada ma3nah njib la photo li kayna f storage ta3 telephone machi li kayna
                //fal firebase
                imageFileName = contentUri.toString();
                //et hada howa howa l path dylaha, file:///storage/emulated/0/Android/data/com.example.project_final_ali/files/Pictures/JPEG_20210925_091850_8482192296177081786.jpg

                //uploadImageToFirebase(f.getName(),contentUri);
            }

        }
    }

    /*
check
Internet
Connection
*/
    private boolean isConnected(CommandActivity command) {

        ConnectivityManager cm =
                (ConnectivityManager) command.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wificonn = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobiledataconn = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wificonn != null && wificonn.isConnected()) ||
                (mobiledataconn != null && mobiledataconn.isConnected())) {
            return true;

        } else {
            return false;
        }
    }


    private void showCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(CommandActivity.this);

        builder.setMessage("Please connect to the internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        return;
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void uploadImageToFirebase(MenuItem item) {

        String fullname = editTextName.getText().toString().trim();
        String city = editTextCity.getText().toString().trim();
        String phone = editTextPhone.getText().toString();
        String reference = editTextReference.getText().toString();


        if (!isConnected(this)) {
            showCustomDialog();
            item.setVisible(true);
        }

        if (fullname.isEmpty()) {
            editTextName.setError("Full name is required!");
            editTextName.requestFocus();
            item.setVisible(true);
            return;
        }
        if (reference.isEmpty()) {
            editTextReference.setError("Reference is required!");
            editTextReference.requestFocus();
            item.setVisible(true);
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError("Phone is required!");
            editTextPhone.requestFocus();
            item.setVisible(true);
            return;
        }
        if (city.isEmpty()) {
            editTextCity.setError("Full name is required!");
            editTextCity.requestFocus();
            item.setVisible(true);
            return;
        }

        if (contentUri != null) {


            final StorageReference image = mStorageRef.child("pictures/" + imageFileName);

            mUploadTask = image.putFile(contentUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //mProgressBar.setProgress(0);
                            mProgressBar.setVisibility(View.VISIBLE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    mProgressBar.setProgress(0);

                                }
                            }, 500);

                            image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    //hadi log d tbayanli bali ani njib la photo li gardiha fal firebase
                                    // uri.to string
                                    Log.d("tag", "onSuccess: Uploaded Image Uri is " + uri.toString());
                                    Log.d("tag", "onSuccess: FileUploaded Image Uri is " + imageFileName);

                                    Client client = new Client(fullname, uri.toString(),
                                            phone, Integer.parseInt(reference),city);

//                            String uploadId = mDatabaseRef.push().getKey();
//                            mDatabaseRef.child(uploadId).setValue(client);

                                    CollectionReference notebookRef = FirebaseFirestore.
                                            getInstance().collection("Client");

                                    notebookRef.add(client);
                                    db = new DataBaseHandler(CommandActivity.this);
                                    db.addClient(client);


                                    // Picasso.get().load(uri).into();
                                    startActivity(new Intent(CommandActivity.this, MainActivity.class));
                                    finish();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            item.setVisible(true);

                            Toast.makeText(CommandActivity.this, "upload Failed", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                            double progress = (100.0 * snapshot.getBytesTransferred() /
                                    snapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(this, "Give me a picture ", Toast.LENGTH_SHORT).show();
        }


    }

    private String getFileExtension(Uri uri) {

        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));

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

    //    private void uploadFile() {
//        if (mImageUri != null){
//            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
//                    + "." + getFileExtension(mImageUri) );
//
//            mUploadTask = fileReference.putFile(mImageUri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
////                             mProgressBar.setProgress(0);
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                   // mProgressBar.setProgress(0);
//                                }
//                            },500);
//
//                            Toast.makeText(CommandActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
//
//
////                            Upload upload = new Upload(editTextName.getText().toString().trim(),
////                                    taskSnapshot.getUploadSessionUri().toString());
//
//                            String uploadId = mDatabaseRef.push().getKey();
//                            mDatabaseRef.child(uploadId).setValue(upload);
//
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//
//                            Toast.makeText(CommandActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//
//                            double progress = (100.0 * snapshot.getBytesTransferred() /
//                                    snapshot.getTotalByteCount());
//                           // mProgressBar.setProgress((int)progress);
//                        }
//                    });
//
//
//        }else {
//            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
//        }
//
//    }
}

//    private void openFileChooser() {
//
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, PICK_IMAGE_REQUEST);
//
//    }


//    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        return Uri.parse(path);
//    }


