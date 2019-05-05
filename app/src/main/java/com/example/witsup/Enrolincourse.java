package com.example.witsup;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Enrolincourse extends AppCompatActivity {
    String personNumber;
    String selectedCourse="";
    String password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personNumber = getIntent().getExtras().getString("PersonNumber");
        setContentView(R.layout.homepage);

        setTitle("Enroll In Course");
        ContentValues params = new ContentValues();
        params.clear();
        params.put("Username", personNumber);
        System.out.println((personNumber));
        Button btnTemp1 = findViewById(R.id.btnEnrolInsCourse);
        btnTemp1.setVisibility(View.INVISIBLE);
        btnTemp1.setClickable(false);
        /*if (personNumber.charAt(0) == 'a') {
            //show create course button
            System.out.println("Shoudl show all stuff");
            Button btnTemp = findViewById(R.id.btnCreateCourse);
            btnTemp.setVisibility(View.VISIBLE);
            btnTemp.setClickable(true);


        }*/

        AsyncHttpPost asyncHttpPost = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/notEnrolled.php", params) {
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
                LinearLayout item = (LinearLayout) getLayoutInflater().inflate(R.layout.item_course, null);


                TextView courseName = (TextView) item.findViewById(R.id.course_name);
                courseName.setText(jo.getString("Course Code") + "    ");


                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //so if clicked call method to set selected course name
                        try {
                            String temp ="" ;
                            System.out.println("1\n\n\n");


                            temp= jo.getString("Course Code");
                            System.out.println("2\n\n\n");

                            selectCourse(temp);

                            System.out.println("Sending course here " + temp);
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Enrolincourse.this);
                                builder.setTitle("Enroll into course "+selectedCourse);

// Set up the input
                                final EditText input = new EditText(Enrolincourse.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                                input.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                builder.setView(input);

// Set up the buttons
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        password= input.getText().toString();
                                        enrolIntoCourse();
                                    }
                                });
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                                builder.show();
                            }




                        } catch (JSONException e) {
                            System.out.println("Cannot put product id into inent for product infro page");
                        }

                    }
                });
                l.addView(item);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void selectCourse(String s){
         selectedCourse=s;
        System.out.println("a\n\n\n");
        Toast.makeText(getApplicationContext(), "Selected course: "+selectedCourse, Toast.LENGTH_SHORT).show();
        System.out.println("________________________________________________________________course seklected is: "+s);
    }
    public void logout(View v) {
        Intent intent = new Intent(Enrolincourse.this, LogInPage.class);
        startActivity(intent);
    }
    public void enrolIntoCourse(){
        if (  selectedCourse.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Cannot be blank and course code cannot contain spaces", Toast.LENGTH_SHORT).show();
        }else{
            ContentValues params = new ContentValues();
            params.clear();


            params.put("Username", personNumber);
            params.put("CoursePass", password);


            params.put("CourseCode", selectedCourse);


            System.out.println((personNumber));

            AsyncHttpPost asyncHttpPost = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/addStudentCourse.php", params) {
                @Override
                protected void onPostExecute(String output) {
                   // processData(output);
                    System.out.println("asycn happned result from addStudentCourse: ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+output);
                    Toast.makeText(getApplicationContext(), "Course added succesfully", Toast.LENGTH_SHORT).show();
                  //  Intent intent = new Intent(Enrolincourse.this, Enrolincourse.class);
                  //  startActivity(intent);


                    
                }
            };
            asyncHttpPost.execute();


        }



    }

}
