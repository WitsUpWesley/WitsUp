package com.example.witsup;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    String personNumber;
    String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        personNumber = getIntent().getExtras().getString("PersonNumber");
        setContentView(R.layout.homepage);
        TextView profileIcon = findViewById(R.id.profileIcon); //declares the textview i added to hold the icon.

        setTitle("Home");
        ContentValues params = new ContentValues();
        params.clear();
        params.put("Username", personNumber);


        if (personNumber.charAt(0) == 'a') {
            //show create course button
            System.out.println("Shoudl show all stuff");


            Button btnTemp = findViewById(R.id.btnCreateCourse);
            btnTemp.setVisibility(View.VISIBLE);
            btnTemp.setClickable(true);

         //   LinearLayout.LayoutParams paramss = (LinearLayout.LayoutParams) findViewById(R.id.btnCreateCourse).getLayoutParams();
        //    paramss.height=LinearLayout.LayoutParams.WRAP_CONTENT;
        //    findViewById(R.id.scrollV).setLayoutParams(paramss);



            /*
            findViewById(R.id.lblCourseCode).setVisibility(View.VISIBLE);
            findViewById(R.id.lblCoursePassword).setVisibility(View.VISIBLE);
            findViewById(R.id.lblCourseDescription).setVisibility(View.VISIBLE);

            findViewById(R.id.ETCourseCode).setVisibility(View.VISIBLE);
            findViewById(R.id.ETCoursePassword).setVisibility(View.VISIBLE);
            findViewById(R.id.ETCourseDescription).setVisibility(View.VISIBLE);

            findViewById(R.id.ETCourseCode).setClickable(true);
            findViewById(R.id.ETCoursePassword).setClickable(true);
            findViewById(R.id.ETCourseDescription).setClickable(true);



            LinearLayout.LayoutParams paramss = (LinearLayout.LayoutParams) findViewById(R.id.lblCourseCode).getLayoutParams();
            paramss.height=LinearLayout.LayoutParams.WRAP_CONTENT;

            findViewById(R.id.btnCreateCourse).setLayoutParams(paramss);
            findViewById(R.id.lblCourseCode).setLayoutParams(paramss);
            findViewById(R.id.lblCoursePassword).setLayoutParams(paramss);
            findViewById(R.id.lblCourseDescription).setLayoutParams(paramss);

            findViewById(R.id.ETCourseCode).setLayoutParams(paramss);
            findViewById(R.id.ETCoursePassword).setLayoutParams(paramss);
            findViewById(R.id.ETCourseDescription).setLayoutParams(paramss);
            paramss = (LinearLayout.LayoutParams) findViewById(R.id.scrollV).getLayoutParams();
            paramss.height=LinearLayout.LayoutParams.MATCH_PARENT;
            findViewById(R.id.scrollV).setLayoutParams(paramss);
            */
        }

        AsyncHttpPost asyncHttpPost = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getAll.php", params) {
            @Override
            protected void onPostExecute(String output) {
                processData(output);
            }
        };
        asyncHttpPost.execute();
    }

    public void processData(String output) {
        LinearLayout l = (LinearLayout) findViewById(R.id.container);
        l.removeAllViews();
        try {
            JSONArray ja = new JSONArray(output);
            for (int i = 0; i < ja.length(); i++) {

                System.out.println("DOES THIS RUNNNN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n\n\n");
                final JSONObject jo = (JSONObject) ja.get(i);
                LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.item_course, null);

            /*
                TextView course_id = (TextView) item.findViewById(R.id.course_id);
                course_id.setText(jo.getString("CourseID"));
                final int id = jo.getInt("CourseID");



                removed this from course item above lineale r layour



                <TextView
    android:id="@+id/course_id"
    android:layout_width="10dp"
    android:layout_height="match_parent"
    android:background="#AAAAAA"
    android:text="1"
    android:visibility="invisible" />
                */
                TextView courseName = (TextView) item.findViewById(R.id.course_name);
                courseName.setText(jo.getString("Course Code") + "    ");


               item.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HomePage.this, CourseDetails.class);

                           String courseName = ((TextView) v.findViewById(R.id.course_name)).getText().toString();
                            intent.putExtra("course" , courseName);
                            intent.putExtra("username", personNumber);
                        startActivity(intent);
                    }
                });
                l.addView(item);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> makeIntoArray(String s) {
        ArrayList<String> outputArr = new ArrayList<String>();

        int posSrchStart = 0, posSrchEnd = 0;
        String name = "", price = "";

        while (s.lastIndexOf('}') != posSrchEnd) {
            //gets name
            posSrchStart = s.indexOf(':', posSrchStart) + 1;
            posSrchEnd = s.indexOf(',', posSrchStart);

            name = s.substring(posSrchStart + 1, posSrchEnd - 1);

            outputArr.add(name);
            //gets price

            posSrchStart = s.indexOf(':', posSrchStart) + 1;
            posSrchEnd = s.indexOf('}', posSrchStart - 1);
            price = s.substring(posSrchStart + 1, posSrchEnd - 1);

            outputArr.add(price);
        }
        return outputArr;

    }


    public void logout(View v) {
        Intent intent = new Intent(HomePage.this, LogInPage.class);
        startActivity(intent);
    }

    public void goToCreateCourse(View v) {
        Intent intent = new Intent(HomePage.this, CreateCourse.class);
        intent.putExtra("PersonNumber", personNumber);
        startActivity(intent);
    }
    public void goToEnrolInCourse(View v) {
        Intent intent = new Intent(HomePage.this, Enrolincourse.class);
        intent.putExtra("PersonNumber", personNumber);
        startActivity(intent);
    }
}

