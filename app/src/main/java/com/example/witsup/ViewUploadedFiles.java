package com.example.witsup;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ViewUploadedFiles extends AppCompatActivity {
    String personNumber;
    private List<String> keys = new ArrayList<>();
    private List<FilePathh> filePathh = new ArrayList<>();
    private DatabaseReference myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        personNumber = getIntent().getExtras().getString("PersonNumber");
        setContentView(R.layout.view_uploaded_files);

        // TextView profileIcon = findViewById(R.id.profileIcon); //declares the textview i added to hold the icon.

        setTitle("Uploaded resources");

        FirebaseApp.initializeApp(this);
        myDb = FirebaseDatabase.getInstance().getReference();


        myDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
              

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

/*

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("filePathh/FilePathhDocument");
          String id=   myRef.push().getKey();

          FilePathh filePathh = new FilePathh(id, "test.pdf", );
          myRef.child(id).setValue(filePathh);
*/


    }

    public interface DataStatus {
        void DataIsLoaded(List<FilePathh> filePathh, List<String> keys);

        void DataIsInserted();

        void DataIsUpdated();

        void DataIsDeleted();
    }

    public void getFileNames(final DataStatus dataStatus) {
        myDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("num childer" + dataSnapshot.getChildrenCount());
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    System.out.println("printing childen key: " + keyNode.getKey());
                    keys.add(keyNode.getKey());
                    FilePathh fileP = keyNode.getValue(FilePathh.class);
                    filePathh.add(fileP);
                    ///here we must mkae the list view and assign filename and url to each field

                }
                dataStatus.DataIsLoaded(filePathh, keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}




