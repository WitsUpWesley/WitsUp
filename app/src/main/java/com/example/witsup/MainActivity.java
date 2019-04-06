package com.example.witsup;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");
        TextView textView =  textView=(TextView) findViewById(R.id.btnCreateNewuser1);
        textView.setVisibility(View.INVISIBLE);
        textView.setEnabled(false);


    }
    public void login(View v){
        EditText t;
        final TextView uName = findViewById(R.id.txtUsername);

        final TextView LABEL = findViewById(R.id.lblOutput);

        String username, password;

        t=(EditText)findViewById(R.id.txtUsername);
        username= t.getText().toString();
        t=(EditText)findViewById(R.id.txtPassword);
        password= t.getText().toString();

        ContentValues params = new ContentValues();

        params.clear();
        params.put( "Username", username);
        params.put( "Password", password);


     //   AsyncHttpPost asyncHttpPost = new AsyncHttpPost(" http%3A%2F%2Flamp.ms.wits.ac.za%2F%7Es1355485%2FloginTest.php", params) {

AsyncHttpPost asyncHttpPost = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/loginTest.php", params) {
   //   AsyncHttpPost asyncHttpPost = new AsyncHttpPost("http://s1355485@lamp.ms.wits.ac.za/loginTest.php", params) {

            @Override
            protected void onPostExecute(String output) {
                String checker="";
                int start, end;

                if(output.equals("[]")){
                    System.out.println("User doesn't exist");
                    LABEL.setText("Invalid Credentials"+output);

                }else {//logged in
//
                 //   start = output.indexOf(":")+2;
                 //   end= output.indexOf(34,start);
//
                 //   checker= output.substring(start,end);

                    /*
                    System.out.println("username:"+checker);


                    if (!LABEL.getText().toString().equals(checker)) {
                        System.out.println("wrong password");
                        LABEL.setText("WRONG PASSS" + output);
                    } else {
                        System.out.println("logged in");
                        LABEL.setText("R" + output);
                    }
                    */
                    System.out.println("Logged in"+output);
                    LABEL.setText("Logged in");

                    //temp star
                   // Intent intent = new Intent(MainActivity.this, homepageOnClicks.class);
                  //  intent.putExtra("username", checker);
                 //   startActivity(intent); ***



                }
            }
        };
        asyncHttpPost.execute();

    }
    public void createAccount(View v){
        EditText t;
        String username, password;

        t=(EditText)findViewById(R.id.txtUsername);
        username= t.getText().toString();
        t=(EditText)findViewById(R.id.txtPassword);
        password= t.getText().toString();

        TextView textView = (TextView) findViewById(R.id.lblConfirmPassword);
        textView.setVisibility(View.VISIBLE);
        textView=(TextView) findViewById(R.id.txtConfirmPassword);
        textView.setVisibility(View.VISIBLE);
        textView=(TextView) findViewById(R.id.btnLogin);
        textView.setVisibility(View.INVISIBLE);
        textView.setEnabled(false);


        textView=(TextView) findViewById(R.id.btnCreateUser);
        textView.setVisibility(View.INVISIBLE);
        textView.setEnabled(false);
        textView=(TextView) findViewById(R.id.btnCreateNewuser1);
        textView.setVisibility(View.VISIBLE);
        textView.setEnabled(true);



    }
    public void checkIfUserExists(View v){
        System.out.println("Running check if user exists");
        EditText t;
        String username, password1,password2;
        TextView textView ;
        t=(EditText)findViewById(R.id.txtUsername);
        username= t.getText().toString();
        t=(EditText)findViewById(R.id.txtPassword);
        password1= t.getText().toString();
        t=(EditText)findViewById(R.id.txtConfirmPassword);
        password2= t.getText().toString();



        if(username.equals("")||username.contains(" ")){
            textView = (TextView) findViewById(R.id.lblOutput);
            textView.setText("Username cannot be blank/contain spaces");
            textView.setVisibility(View.VISIBLE);
            System.out.println("Username Is Incorrect");
        }
        else {

            if (password1.equals(password2)) {
                ContentValues params = new ContentValues();

                params.put("Username",username);
                AsyncHttpPost asyncHttpPost = new AsyncHttpPost(
                        "http://lamp.ms.wits.ac.za/~s1355485/checkUser.php",params) {
                    @Override
                    protected void onPostExecute(String output) {
                        if(output.equals("[]")){
                            //user doesnt exist so can create new user
                            createNewUser();
                            System.out.println("Create new user called 1");
                        }else{
                            //user exists choose different username
                            outputToLabel("Username already exists");
                        }


                    }
                };
                asyncHttpPost.execute();

                textView = (TextView) findViewById(R.id.lblOutput);
                textView.setText("Creating a New User");


            } else {

                outputToLabel("Passwords do not match");

                System.out.println("Passwords don't match");
            }

        }


    }

    public void outputToLabel(String output){
        TextView textView = (TextView) findViewById(R.id.lblOutput);
        textView.setVisibility(View.VISIBLE);
        textView.setText(output);
    }



    public void createNewUser(){
        TextView t , textView;
        t=(EditText)findViewById(R.id.txtUsername);
        final String username= t.getText().toString();
        t=(EditText)findViewById(R.id.txtPassword);
        final String  password= t.getText().toString();
        System.out.println("in create new user");

        ContentValues params = new ContentValues();
        params.put("Username",username);
        params.put("Password", password);


        AsyncHttpPost asyncHttpPost = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/createUser.php",params) {
            @Override
            protected void onPostExecute(String output) {
                System.out.println(output);

            }
        };
        asyncHttpPost.execute();
        textView = (TextView) findViewById(R.id.btnCreateNewuser1);
        textView.setVisibility(View.INVISIBLE);
        textView.setEnabled(false);

        textView = (TextView) findViewById(R.id.txtConfirmPassword);
        textView.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.lblConfirmPassword);
        textView.setVisibility(View.INVISIBLE);


        textView = (TextView) findViewById(R.id.btnLogin);
        textView.setVisibility(View.VISIBLE);
        textView.setEnabled(true);
    }
}
