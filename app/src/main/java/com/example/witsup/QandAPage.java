package com.example.witsup;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        course = getIntent().getExtras().getString("course");

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
