package com.example.witsup;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.*;

import android.support.v4.content.FileProvider;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Resourcesforfb extends AppCompatActivity implements View.OnClickListener {
    private String personNumber, course;

    private StorageReference storageRef;
    private FirebaseStorage storage;
    private FirebaseDatabase database;
    private DatabaseReference myDB;
    private Button btnSelectFile, btnUploadFile;
    private TextView notification;
    private UploadTask uploadTask;
    private Uri filePath;
    public static final int PICK_IMAGE_REQUEST = 234;
    private boolean getUrl = false;
    private List<FilePathh> filePathhList;

    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personNumber = getIntent().getExtras().getString("username");
        System.out.println("person number!!!!!!!!!>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>!!!"+personNumber);
        course = getIntent().getExtras().getString("course");
        setContentView(R.layout.resources);
        FirebaseApp.initializeApp(this);
        storageReference = FirebaseStorage.getInstance().getReference();

        myDB = FirebaseDatabase.getInstance().getReference();

        setContentView(R.layout.resources);

        setTitle("Resources");

        btnSelectFile = (Button) findViewById(R.id.btnSelectFile);
        btnUploadFile = (Button) findViewById(R.id.btnUploadFile);

        notification = findViewById(R.id.lblNotification);
        notification.setText("No file chosen");
        btnSelectFile.setOnClickListener(this);
        btnUploadFile.setOnClickListener(this);


        if (personNumber.charAt(0) == 'a') {

            //show create course button
            System.out.println("Shoudl show all stuff");



            btnSelectFile.setVisibility(View.VISIBLE);
            btnSelectFile.setClickable(true);


            btnUploadFile.setVisibility(View.VISIBLE);
            btnUploadFile.setClickable(true);

            TextView  tmp =(TextView) findViewById(R.id.lblNotification);
            tmp.setVisibility(View.VISIBLE);
            tmp.setClickable(true);

        }





        filePathhList = new ArrayList<>();
        myDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                filePathhList.clear();
                for (DataSnapshot filePathhSnapshot : dataSnapshot.getChildren()) {
                    FilePathh temp = filePathhSnapshot.getValue(FilePathh.class);
                    filePathhList.add(temp);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void showFileChooser() {
        Intent intent = new Intent();

        intent.setType("*/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("course", course);
        intent.putExtra("username", personNumber);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);


    }

    private void uploadFile() {
        if (filePath != null ) {
            final ProgressDialog progressDialog = new ProgressDialog(this);

            progressDialog.setTitle("Uploading");
            progressDialog.show();

            System.out.println(filePath);
            System.out.println(filePath.getPath());
            System.out.println(filePath.getLastPathSegment());

            String fileName = filePath.getLastPathSegment().substring(filePath.getLastPathSegment().lastIndexOf('/'), filePath.getLastPathSegment().length());

            final StorageReference riversRef = storageReference.child("/files").child(fileName);


            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_LONG).show();


                            getUrl = true;

                            riversRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        updateDatabase(task.getResult());
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error getting URL", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getApplicationContext(), "File Could not upload" + exception.getMessage(), Toast.LENGTH_LONG).show();
                            notification.setText("File failed to upload, try again");
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage((int) progress + "% uploaded");
                    notification.setText("No file selected");
                }
            });


        } else {
            notification.setText("No file selected");
            Toast.makeText(getApplicationContext(), "No file selected", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            //thought this was a fix but its not filePath = (Uri) (Uri.parse(data.getData().getPath()));
            filePath = data.getData();
            if (filePath != null) {
                System.out.println("maybe error here1 filepathtostring:" + filePath.toString());
                System.out.println("maybe error here1 filepath:" + filePath.getPath());
                /*


                error here1 filepathtostring:content://com.android.providers.downloads.documents/document/raw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Finvoice1.xlsx
2019-05-05 16:33:11.643 8678-8678/com.example.witsup I/System.out: maybe error here1 filepath:/document/raw:/storage/emulated/0/Download/invoice1.xlsx
2019-05-05 16:33:11.645 8678-8678/com.example.witsup I/System.out: maybe error here2/invoice1.xlsx

error:
maybe error here1 filepathtostring:content://com.android.providers.downloads.documents/document/3535
2019-05-05 16:34:34.187 9158-9158/com.example.witsup I/System.out: maybe error here1 filepath:/document/3535
                 */
                //fails here if the filename contains spaces etc
                //   String tempName =  filePath.getPath();
                //  tempName.replaceAll(" ","");
                //tempName.replaceAll("_","");

                String fileName = filePath.getLastPathSegment().substring(filePath.getLastPathSegment().lastIndexOf('/'), filePath.getLastPathSegment().length());
                System.out.println("maybe error here2" + fileName);

                notification.setText("File selected: " + fileName);
            } else {
                notification.setText("No file selected");

            }
            //to show image can do it here

        }
    }

    @Override
    public void onClick(View v) {
        System.out.println(v);
        if (v == btnSelectFile) {
            showFileChooser();

        } else if (v == btnUploadFile) {

            uploadFile();
        }
    }

    public void goToViewUploads(View v) {
        Intent intent = new Intent(Resourcesforfb.this, testListActivity.class);
        startActivity(intent);
    }

    public void updateDatabase(Uri u) {
        String fileName = filePath.getLastPathSegment().substring(filePath.getLastPathSegment().lastIndexOf('/'), filePath.getLastPathSegment().length());
//        StorageReference temp = storage.getReference("/files").child(fileName);


        final String t = fileName;


        String id = myDB.push().getKey();

        FilePathh filePathh = new FilePathh(id, t, u.toString());


        myDB.child(id).setValue(filePathh);


/*
        temp.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                System.out.println("Hereee____________________________!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("Hereee____________________________!!!!!!!!!!!!!!!!!!!!!!!!!!t=" + t);
                System.out.println("Hereee____________________________!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println(uri);


                // Write a message to the database


                String id = myDB.push().getKey();

                System.out.println("here" + id);


                FilePathh filePathh = new FilePathh(id, t, uri);
                System.out.println(filePathh.getFilePathhID() + "\n " + filePathh.getFilePathhName() + "\n " + filePathh.getFilePathhURL());

                myDB.child(id).setValue(filePathh);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });*/


    }
}