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

public class Courses  extends AppCompatActivity {
    String personNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personNumber=getIntent().getExtras().getString("PersonNumber");
        setContentView(R.layout.activity_courses);

        setTitle("Home");
        ContentValues params = new ContentValues();
        params.put("Username",personNumber);

        AsyncHttpPost asyncHttpPost = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getAll.php",params) {
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
                final JSONObject jo = (JSONObject) ja.get(i);
                LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.course_item, null);

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
                courseName.setText(jo.getString("Course Code")+"    ");

/*
               item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Courses.this,coursePage.class);
                        try {
                            String temp =jo.getString("course");
                            System.out.println("Sending course here "+temp);
                            intent.putExtra("course",temp);
                            intent.putExtra("username", personNumber);

                        } catch (JSONException e) {
                            System.out.println("Cannot put product id into inent for product infro page");
                        }
                        startActivity(intent);
                    }
                });
*/






                //if lecturer is clicked then show lecturer contact details
    //            TextView lecturer = (TextView) item.findViewById(R.id.course_lecturer);
    //            lecturer.setText(jo.getString("lecturer")+"    ");
                /*

                place in course item
                 <TextView
        android:id="@+id/course_lecturer"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:gravity="right"
        android:text="R: " />
                 */



                l.addView(item);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public ArrayList <String> makeIntoArray(String s) {
        ArrayList <String> outputArr =new ArrayList<String>();

        int posSrchStart = 0, posSrchEnd = 0;
        String name = "", price = "";

        while(s.lastIndexOf('}')!=posSrchEnd){
            //gets name
            posSrchStart = s.indexOf(':', posSrchStart) + 1;
            posSrchEnd = s.indexOf(',', posSrchStart);

            name = s.substring(posSrchStart+1, posSrchEnd-1);

            outputArr.add(name);
            //gets price

            posSrchStart = s.indexOf(':', posSrchStart) + 1;
            posSrchEnd = s.indexOf('}', posSrchStart-1);
            price = s.substring(posSrchStart+1, posSrchEnd-1);

            outputArr.add(price);
        }

        return outputArr;

    }



    public void logout(View v){
        Intent intent = new Intent(Courses.this, MainActivity.class);
        startActivity(intent);
    }



}
