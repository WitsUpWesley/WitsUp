package com.example.witsup;

import android.content.ContentValues;
import android.net.Uri;


import java.net.*;
import java.io.*;
import android.os.*;

//import android.os.AsyncTask;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;

//import java.net.HttpURLConnection;
//import java.net.URL;

import java.sql.SQLOutput;

/**
 * Created by 1657041 on 2018/04/26.
 */

public abstract class AsyncHttpPost extends AsyncTask<String, String, String> {
    String address;
    ContentValues parameters;
    public AsyncHttpPost(String address, ContentValues parameters) {
        this.address = address;
        this.parameters = parameters;
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            System.out.println("Async http-------------------------------------"+parameters);
            URL url = new URL(address);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            connection.setDoInput(true);

            if (parameters.size() > 0) {
                Uri.Builder builder = new Uri.Builder();

                for (String s : parameters.keySet()) {

                    builder.appendQueryParameter(s,parameters.getAsString(s));
                }

                String query = builder.build().getEncodedQuery();

                OutputStream os;

                os= connection.getOutputStream();
                System.out.println("output stream "+os);



                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                System.out.println("writing query"+query);
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                System.out.println("closed os");
            }
            //BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            System.out.println("trying input stream");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println("br"+br.toString());
            String response = br.readLine();

            br.close();
            System.out.println("response"+response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }
    }
    @Override
    protected abstract void onPostExecute(String output);
}
