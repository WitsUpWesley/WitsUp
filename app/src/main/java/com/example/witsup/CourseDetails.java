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

    String personNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  personNumber = getIntent().getExtras().getString("PersonNumber");
        setContentView(R.layout.course_page);

        setTitle("CourseDetails");

        resourcesButton = (Button) findViewById(R.id.btnResources);

        resourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResourcesPage();
            }
        });
    }

    public void openResourcesPage(){

        Intent resourcesIntent = new Intent(this, Resources.class);
        startActivity(resourcesIntent);

    }
}
