package com.example.witsup;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Output;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import static android.graphics.Color.WHITE;

public class QandAPage extends AppCompatActivity {

    String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qand_apage);

        setTitle("Q and A");

        final EditText

                question = findViewById(R.id.questionInput),
                answerA = findViewById(R.id.answerAInput),
                answerB = findViewById(R.id.answerBInput),
                answerC = findViewById(R.id.answerCInput),
                answerD = findViewById(R.id.answerDInput),
                correctAnswer = findViewById(R.id.correctAnswerInput);


        final Button post = findViewById(R.id.btnPost);

        final Button clear = findViewById(R.id.btnClear);

        final CheckBox

                A = findViewById(R.id.checkBoxA),
                B = findViewById(R.id.checkBoxB),
                C = findViewById(R.id.checkBoxC),
                D = findViewById(R.id.checkBoxD);
        Intent intent=getIntent();
        course= intent.getStringExtra("course");
        //course = getIntent().getExtras().getString("course");

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (question.length()==0 || answerA.length()==0 || answerB.length()==0 || answerC.length()==0 || answerD.length()==0 || correctAnswer.length()==0 ) {

                        Toast.makeText(getApplicationContext(), "Missing Entry", Toast.LENGTH_SHORT).show();

                }


                else {

                    String q = question.getText().toString().trim();
                    String a = answerA.getText().toString().trim();
                    String b = answerB.getText().toString().trim();
                    String c = answerC.getText().toString().trim();
                    String d = answerD.getText().toString().trim();
                    String ca = correctAnswer.getText().toString().trim();

                    ContentValues cv = new ContentValues();
                    cv.put("course", course);
                    cv.put("question", q);
                    cv.put("answerA", a);
                    cv.put("answerB", b);
                    cv.put("answerC", c);
                    cv.put("answerD", d);
                    cv.put("correctAnswer", ca);

                    postQandA(QandAPage.this, cv);

                    question.getText().clear();
                    answerA.getText().clear();
                    answerB.getText().clear();
                    answerC.getText().clear();
                    answerD.getText().clear();
                    correctAnswer.getText().clear();
                    A.setChecked(false);
                    B.setChecked(false);
                    C.setChecked(false);
                    D.setChecked(false);

                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.getText().clear();
                answerA.getText().clear();
                answerB.getText().clear();
                answerC.getText().clear();
                answerD.getText().clear();
                correctAnswer.getText().clear();
                A.setChecked(false);
                B.setChecked(false);
                C.setChecked(false);
                D.setChecked(false);
            }
        });

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAnswer.setText("AnswerA");
                correctAnswer.setTextColor(WHITE);
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAnswer.setText("AnswerB");
                correctAnswer.setTextColor(WHITE);
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAnswer.setText("AnswerC");
                correctAnswer.setTextColor(WHITE);
            }
        });

        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAnswer.setText("AnswerD");
                correctAnswer.setTextColor(WHITE);
            }
        });



    }

    private static void postQandA(final Context c, ContentValues cv){
        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/qanda.php", cv) {
            @Override
            protected void onPostExecute(String output) {
                if(output.equals("1")){

                    Toast.makeText(c, "Successfully Posted", Toast.LENGTH_SHORT).show();

                }
                else{

                    Toast.makeText(c, "Post Failed", Toast.LENGTH_SHORT).show();

                }

            }
        }.execute();

    }


}
