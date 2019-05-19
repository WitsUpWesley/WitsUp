package com.example.witsup;

import android.content.ContentValues;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewAnswers extends AppCompatActivity implements View.OnClickListener{

    String q = null;
    String course = null;
    String[] myArray = {"AnswerA","AnswerB","AnswerC","AnswerD"};
    RadioGroup rg;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;

    private ColorStateList textColorDefaultRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_answers);

        Bundle extras = getIntent().getExtras();

        course = extras.getString("course");
        q = extras.getString("q");
        setTitle("Answers");

        TextView qshown = findViewById(R.id.questionShown);
        qshown.setText(q);

        rg = findViewById(R.id.radio_group);
        rb1  = findViewById(R.id.A);
        rb2 = findViewById(R.id.B);
        rb3 = findViewById(R.id.C);
        rb4 = findViewById(R.id.D);

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);

        ContentValues cv = new ContentValues();
        cv.put("course", course);
        cv.put("question", q);
        displayAnswers(cv);

    }

    public void displayAnswers(ContentValues cv) {


        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getAnswer.php", cv) {
            @Override
            protected void onPostExecute(String output) {

                try {
                    JSONArray answers = new JSONArray(output);
                    for (int i = 0; i < answers.length(); i++) {

                        JSONObject answer = answers.getJSONObject(i);

                        rb1.setText("A: " + answer.getString(myArray[0]));
                        rb2.setText("B: " + answer.getString(myArray[1]));
                        rb3.setText("C: " + answer.getString(myArray[2]));
                        rb4.setText("D: " + answer.getString(myArray[3]));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.execute();


    }


    @Override
    public void onClick(View v) {

        Bundle extras = getIntent().getExtras();

        course = extras.getString("course");
        q = extras.getString("q");

        ContentValues cv = new ContentValues();
        cv.put("course", course);
        cv.put("question", q);

        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/correctAnswer.php", cv) {
            @Override
            protected void onPostExecute(String output) {

                try {
                    JSONArray correctAnswer = new JSONArray(output);
                    String Canswer = correctAnswer.getJSONObject(0).getString("CorrectAnswer");

                    System.out.println(Canswer);

                    if(Canswer.equals("AnswerA")) {
                        rb1.setTextColor(Color.GREEN);
                    }

                    else if(Canswer.equals("AnswerB")) {
                        rb2.setTextColor(Color.GREEN);
                    }

                    if(Canswer.equals("AnswerC")) {
                        rb3.setTextColor(Color.GREEN);
                    }

                    if(Canswer.equals("AnswerD")) {
                        rb4.setTextColor(Color.GREEN);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute();

        }

    }


