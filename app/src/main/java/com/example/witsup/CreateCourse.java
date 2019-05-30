package com.example.witsup;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class CreateCourse extends AppCompatActivity {

    String personNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personNumber = getIntent().getExtras().getString("PersonNumber");
        setContentView(R.layout.create_course);

        setTitle("Home");
        ContentValues params = new ContentValues();
        params.put("Username", personNumber);
        System.out.println((personNumber.charAt(0)));
        if (personNumber.charAt(0) == 'a') {
            //show create course button
            System.out.println("Should show all stuff");


            Button btnTemp = findViewById(R.id.btnCreateCourse);
            btnTemp.setVisibility(View.VISIBLE);
            btnTemp.setClickable(true);

        }
    }
    public void addCourse(String temp) {
        ContentValues params = new ContentValues();
        params.clear();
        System.out.println("Temp here____________________________" + temp);

        if (temp.charAt(0) == '[' && temp.charAt(1) == ']') {

            params = new ContentValues();
            params.clear();

            params.put("Username", personNumber);
            String t1, t2, t3;
            t1 = ((TextView) findViewById(R.id.ETCourseCode)).getText().toString().toUpperCase();
            t2 = ((TextView) findViewById(R.id.ETCourseDescription)).getText().toString();
            t3 = ((TextView) findViewById(R.id.ETCoursePassword)).getText().toString();
            System.out.println("Terying to make new course here" + t1 + " " + t2 + " " + t3);
            if (t1.isEmpty()|| t1.contains(" ") || t2.isEmpty()||t3.isEmpty()|| t3.contains(" ")) {
                Toast.makeText(getApplicationContext(), "Cannot be blank and course code cannot contain spaces", Toast.LENGTH_SHORT).show();
            }
            else {
                params.put("CourseCode", t1);
                params.put("CourseDisc", t2);
                params.put("CoursePass", t3);



                AsyncHttpPost asyncHttpPost2 = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/addCourse.php", params) {


                    @Override
                    protected void onPostExecute(String output) {
                        System.out.println("from addcoursephp" + output);
                        Toast.makeText(getApplicationContext(), "Course added", Toast.LENGTH_SHORT).show();
                        ((TextView) findViewById(R.id.ETCourseCode)).setText("");
                        ((TextView) findViewById(R.id.ETCourseDescription)).setText("");
                        ((TextView) findViewById(R.id.ETCoursePassword)).setText("");
                    }
                };
                asyncHttpPost2.execute();

            }

        } else {
            Toast.makeText(getApplicationContext(), "Course code already exists, choose another course code", Toast.LENGTH_SHORT).show();
            ((TextView) findViewById(R.id.ETCourseCode)).setText("");
        }
    }
    public void createCourse(View v) {
        // Intent intent = new Intent(HomePage.this, LogInPage.class);
        //     startActivity(intent);
        if (((TextView) findViewById(R.id.ETCourseCode)).getText().toString() != "") {

            System.out.println("Create course");


            ContentValues params = new ContentValues();
            params.clear();

            params.put("CourseCode", ((TextView) findViewById(R.id.ETCourseCode)).getText().toString());


            AsyncHttpPost asyncHttpPost = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/checkCourse.php", params) {

                @Override
                protected void onPostExecute(String output) {


                    System.out.println("from checkcoursephp" + output);
                    addCourse(output);

                }
            };
            asyncHttpPost.execute();
        }

    }
    public void logout(View v) {
        Intent intent = new Intent(CreateCourse.this, LogInPage.class);
        startActivity(intent);
    }


}
