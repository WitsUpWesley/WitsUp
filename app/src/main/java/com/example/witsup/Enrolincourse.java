package com.example.witsup;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Enrolincourse extends AppCompatActivity  {
        String personNumber;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personNumber = getIntent().getExtras().getString("PersonNumber");
        setContentView(R.layout.homepage);

        setTitle("Home");
        ContentValues params = new ContentValues();
        params.clear();
        params.put("Username", personNumber);
        System.out.println((personNumber));
        if (personNumber.charAt(0) == 'a') {
            //show create course button
            System.out.println("Shoudl show all stuff");


            Button btnTemp = findViewById(R.id.btnCreateCourse);
            btnTemp.setVisibility(View.VISIBLE);
            btnTemp.setClickable(true);


        }

        AsyncHttpPost asyncHttpPost = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getAll.php", params) {
            @Override
            protected void onPostExecute(String output) {
            //    processData(output);
            }
        };
        asyncHttpPost.execute();
    }

    }
