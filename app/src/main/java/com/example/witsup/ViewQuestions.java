package com.example.witsup;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
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

import static android.view.View.VISIBLE;

public class ViewQuestions extends AppCompatActivity {

    String course = null;
    String personNumber = null;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Questions");
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        setContentView(R.layout.view_questions);

        personNumber = extras.getString("username");
        course = extras.getString("course");

        final Button btnaddQuestion = findViewById(R.id.addQuestion);

        ContentValues c = new ContentValues();
        c.put("Course",course);

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/checkLecturer.php", c) {
            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray lecturer = new JSONArray(output){};

                    JSONObject lec = lecturer.getJSONObject(0);

                    System.out.println(lec);

                    if (personNumber.equals(lec.getString("Lecturer"))){

                        btnaddQuestion.setVisibility(View.VISIBLE);
                        btnaddQuestion.setClickable(true);
                    }

                    else {

                        btnaddQuestion.setVisibility(View.GONE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute();

        LinearLayout holder = findViewById(R.id.questionHolder);

        ContentValues cv = new ContentValues();
        cv.put("course", course);

        displayQuestions(holder, cv);

        btnaddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openQandApostpage();

            }
        });


    }

    public void displayQuestions(final LinearLayout holder, ContentValues cv) {


        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getQuestions.php", cv) {
            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray questions = new JSONArray(output);
                    for (int i = 0; i < questions.length(); i++) {
                        JSONObject question = questions.getJSONObject(i);

                        View v = View.inflate(holder.getContext(), R.layout.question_item, null);

                        ((TextView) v.findViewById(R.id.displayedQuestion)).setText(question.getString("Question"));

                        final String q =((TextView) v.findViewById(R.id.displayedQuestion)).getText().toString();

                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                    openViewAnswersPage(q);

                            }
                        });

                        holder.addView(v);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.execute();


    }

    private void openViewAnswersPage(String q){

        Intent viewAnswersIntent = new Intent(this, ViewAnswers.class);
        viewAnswersIntent.putExtra("q", q);
        viewAnswersIntent.putExtra("course", course);
        startActivity(viewAnswersIntent);

    }

    private void openQandApostpage(){
        Intent QandAIntent = new Intent(this, QandAPage.class);
        QandAIntent.putExtra("course", course);
        startActivity(QandAIntent);


    }

}
