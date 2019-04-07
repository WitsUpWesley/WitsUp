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
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Courses extends AppCompatActivity {
    String personNumber;
    String creatCourseAllowed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personNumber = getIntent().getExtras().getString("PersonNumber");
        setContentView(R.layout.activity_courses);

        setTitle("Home");
        ContentValues params = new ContentValues();
        params.put("Username", personNumber);
        System.out.println((personNumber.charAt(0)));
        if (personNumber.charAt(0) == 'a') {
            //show create course button
            System.out.println("Shoudl show all stuff");


            Button btnTemp = findViewById(R.id.btnCreateCourse);
            btnTemp.setVisibility(View.VISIBLE);
            btnTemp.setClickable(true);

            findViewById(R.id.lblCourseCode).setVisibility(View.VISIBLE);
            findViewById(R.id.lblCoursePassword).setVisibility(View.VISIBLE);
            findViewById(R.id.lblCourseDescription).setVisibility(View.VISIBLE);

            findViewById(R.id.ETCourseCode).setVisibility(View.VISIBLE);
            findViewById(R.id.ETCoursePassword).setVisibility(View.VISIBLE);
            findViewById(R.id.ETCourseDescription).setVisibility(View.VISIBLE);

            findViewById(R.id.ETCourseCode).setClickable(true);
            findViewById(R.id.ETCoursePassword).setClickable(true);
            findViewById(R.id.ETCourseDescription).setClickable(true);

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
                courseName.setText(jo.getString("Course Code") + "    ");

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
        Intent intent = new Intent(Courses.this, MainActivity.class);
        startActivity(intent);
    }
public void addCourse(String temp){
    ContentValues params = new ContentValues();
    params.clear();
    System.out.println("Temp heree____________________________"+temp);

    if (temp.charAt(0) =='[' && temp.charAt(1)==']') {

        params = new ContentValues();
        params.clear();

        params.put("Username", personNumber);
        String t1,t2,t3;
        t1=((TextView) findViewById(R.id.ETCourseCode)).getText().toString();
        t2=  ((TextView) findViewById(R.id.ETCourseDescription)).getText().toString();
        t3=((TextView) findViewById(R.id.ETCoursePassword)).getText().toString() ;
        System.out.println("Terying to make new course here"+t1+" "+t2+" "+t3);
        if (t1!=null&& t1!=""&& t1.contains(" ")==false && t2!=null&& t2!=""&& t3!=null&& t3!="") {
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
        }else{
            Toast.makeText(getApplicationContext(), "Cannot be blank and course code cannot contain spaces", Toast.LENGTH_SHORT).show();


        }

    } else {
        Toast.makeText(getApplicationContext(), "Course code already exists, choose another course code", Toast.LENGTH_SHORT).show();
        ((TextView) findViewById(R.id.ETCourseCode)).setText("");
    }


}
    public void createCourse(View v) {
        // Intent intent = new Intent(Courses.this, MainActivity.class);
        //     startActivity(intent);


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

