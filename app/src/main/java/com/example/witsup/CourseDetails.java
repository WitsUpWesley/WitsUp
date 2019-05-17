package com.example.witsup;
import android.content.ContentValues;
import android.content.Context;
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
    private Button QandAButton;
    private TextView lblCourse;

    private String personNumber, course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          personNumber = getIntent().getExtras().getString("username");
          course = getIntent().getExtras().getString("course");
        setContentView(R.layout.course_page);

        setTitle("Course Details");
        TextView tmp =(TextView) findViewById(R.id.labelCourse);
        tmp.setText(course);


        lblCourse=(TextView) findViewById(R.id.labelCourse);
        lblCourse.setText("Course: "+course);
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

        QandAButton = (Button) findViewById(R.id.btnQandA);

        QandAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openViewQuestionsPage();
            }
        });



    }
    public void logout(View v) {
        Intent intent = new Intent(CourseDetails.this, LogInPage.class);
        startActivity(intent);
    }

    public void openResourcesPage(){
        Intent intent = new Intent(this, Resourcesforfb.class);
        intent.putExtra("course", course);
        intent.putExtra("username", personNumber);
        startActivity(intent);

    }

    public void openAnnouncementsPage(){
        Intent announcementIntent = new Intent(this, AnnouncementPage.class);
        announcementIntent.putExtra("course",course);
        announcementIntent.putExtra("username", personNumber);
        startActivity(announcementIntent);
    }


    private void openViewQuestionsPage(){

        Intent viewQuestionsIntent = new Intent(this, ViewQuestions.class);
        viewQuestionsIntent.putExtra("course",course);
        viewQuestionsIntent.putExtra("username", personNumber);
        startActivity(viewQuestionsIntent);

    }
}
