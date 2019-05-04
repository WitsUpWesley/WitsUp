package com.example.witsup;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CourseDetails extends AppCompatActivity   {

    private Button resourcesButton;
    private Button announcementButton;

     String personNumber;
     String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            personNumber = extras.getString("username");
            course = extras.getString("course");
            System.out.println(personNumber + course);
        }
        //personNumber = getIntent().getExtras().getString("PersonNumber");
        //course = getIntent().getExtras().getString("course");
        setContentView(R.layout.course_page);

        setTitle(course +  " Course Details");


        resourcesButton = (Button) findViewById(R.id.btnResources);
        resourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResourcesPage();
            }
        });

        announcementButton = (Button) findViewById(R.id.btnAnnouncements);
        announcementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnnouncementsPage();
            }
        });



    }

    public void openResourcesPage(){

        Intent resourcesIntent = new Intent(this, Resources.class);
        startActivity(resourcesIntent);

    }

    public void openAnnouncementsPage(){
        Intent announcementIntent = new Intent(this, AnnouncementPage.class);
        announcementIntent.putExtra("course",course);
        announcementIntent.putExtra("username", personNumber);
        startActivity(announcementIntent);
    }
}
