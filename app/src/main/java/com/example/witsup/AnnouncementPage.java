package com.example.witsup;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class AnnouncementPage extends AppCompatActivity {
    String personNumber;
    String course;

    @SuppressLint("StaticFieldLeak")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //System.out.println(personNumber);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            personNumber = extras.getString("username");
            course = extras.getString("course");
            //System.out.println(personNumber + course);
        }
        System.out.println("Person Number: " + personNumber + "\nCourse: " + course);
        setContentView(R.layout.announcement_page);
        setTitle("Announcements");
        //personNumber = getIntent().getExtras().getString("PersonNumber");

        ContentValues params = new ContentValues();
        params.clear();
        params.put("Course",course);

        AsyncHttpPost asyncHttpPost1;
        asyncHttpPost1 = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/checkLecturer.php", params) {
            @Override
            protected void onPostExecute(String output) {

                try{
                    JSONArray ja = new JSONArray(output);
                    final JSONObject jo = (JSONObject) ja.get(0);
                    if (personNumber.equals(jo.getString("Lecturer")))
                    {
                        //show add announcement button

                        TextView txtTemp = findViewById(R.id.txtAddAnn);
                        txtTemp.setVisibility(View.VISIBLE);
                        txtTemp.setClickable(true);

                        Button btnTemp = findViewById(R.id.btnCreateAnn);
                        btnTemp.setVisibility(View.VISIBLE);
                        btnTemp.setClickable(true);

                        TextView txtTemp2 = findViewById(R.id.lblAnnouncement);
                        txtTemp2.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        asyncHttpPost1.execute();


        params.clear();
        params.put("Course",course);



        AsyncHttpPost asyncHttpPost = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/displayAnnouncements.php", params) {
            @Override
            protected void onPostExecute(String output) {
                processData(output);
            }
        };
        asyncHttpPost.execute();

    }




    public void processData(String output) {
        LinearLayout l = (LinearLayout) findViewById(R.id.containers);
        l.removeAllViews();
        try {
            JSONArray ja = new JSONArray(output);
            for (int i = 0; i < ja.length(); i++) {
                final JSONObject jo = (JSONObject) ja.get(i);
                LinearLayout itemAnn = (LinearLayout) getLayoutInflater().inflate(R.layout.display_announcement, null);
                //System.out.println(jo);
                TextView annName = (TextView) itemAnn.findViewById(R.id.announcement);
                //TextView annDate = (TextView) itemAnn.findViewById(R.id.date);
                annName.setText(jo.getString("Announcement") + "    ");
                //annDate.setText(jo.getString("Date Posted") + "    ");

                l.addView(itemAnn);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAnnouncement(View v) {
        ContentValues params = new ContentValues();
        params.clear();
        EditText t=(EditText)findViewById(R.id.txtAddAnn);
        String announcementTemp= t.getText().toString();
        params.put("CourseCode", course);
        params.put("Announcement", announcementTemp);



        AsyncHttpPost asyncHttpPost2 = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/addAnnouncement.php", params) {


                    @Override
                    protected void onPostExecute(String output) {
                        Toast.makeText(getApplicationContext(), "Announcement added", Toast.LENGTH_SHORT).show();
                    }
                };
        asyncHttpPost2.execute();
        t.setText("");

            }
    public void logout(View v) {
        Intent intent = new Intent(AnnouncementPage.this, LogInPage.class);
        startActivity(intent);
    }


}









