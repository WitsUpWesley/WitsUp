package com.example.witsup;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Resources extends AppCompatActivity {

    Uri pdfUri; // URLs meant for local storage

    Button selectFile, uploadFile;

    TextView notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources2);

        setTitle("Resources");

         selectFile = (Button) findViewById(R.id.select_file);
         uploadFile = (Button) findViewById(R.id.upload_file);

         notification = findViewById(R.id.notification);

        selectFile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(Resources.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                    selectfile();
                }

                else {

                    ActivityCompat.requestPermissions(Resources.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);

                }
            }


        });

        uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pdfUri != null) {
                    uploadfile(Resources.this, pdfUri);
                }

                else {

                    Toast.makeText(Resources.this, "Select a File" , Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private static void uploadfile(final Context c, Uri pdfUri){


        ContentValues cv = new ContentValues();
        //This is where I got stuck I don't know which content values to pass

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/upload.php", cv) {
            @Override
            protected void onPostExecute(String output) {

                if (output.equals("success")){
                    Toast.makeText(c, "upload successful", Toast.LENGTH_SHORT).show();
                }

                else{

                    Toast.makeText(c, "upload failed", Toast.LENGTH_SHORT).show();
                }

            }
        }.execute();



    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 3 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            selectfile();

        }

        else {

            Toast.makeText(Resources.this,"Please provide permission...", Toast.LENGTH_SHORT).show();

        }

    }

    private void selectfile(){

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 86 && resultCode == RESULT_OK && data != null){

            pdfUri = data.getData(); // Return Uri of selected file
            notification.setText("File selected : " + data.getData().getLastPathSegment());

        }

        else {

            Toast.makeText(Resources.this, "No file was selected...", Toast.LENGTH_SHORT).show();

        }

    }
}
