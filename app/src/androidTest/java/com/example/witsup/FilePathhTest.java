package com.example.witsup;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class FilePathhTest {

    @Test
    public void getFilePathhID() {

        String filePathhID="//user//test.pdf";
        String filePathhName="pathOne";
        String filePathhURL="URL//user//test.pdf";

        String expected=filePathhID;
        String output;

         FilePathh testFP = new FilePathh(filePathhID,filePathhName ,filePathhURL ) ;
        output=testFP.getFilePathhID();

         assertEquals(expected, output);


    }

    @Test
    public void getFilePathhName() {
        String filePathhID="//user//test.pdf";
        String filePathhName="pathOne";
        String filePathhURL="URL//user//test.pdf";

        String expected=filePathhName;
        String output;

        FilePathh testFP = new FilePathh(filePathhID,filePathhName ,filePathhURL ) ;
        output=testFP.getFilePathhName();

        assertEquals(expected, output);
    }

    @Test
    public void getFilePathhURL() {
        String filePathhID="//user//test.pdf";
        String filePathhName="pathOne";
        String filePathhURL="URL//user//test.pdf";

        String expected=filePathhURL;
        String output;

        FilePathh testFP = new FilePathh(filePathhID,filePathhName ,filePathhURL ) ;
        output=testFP.getFilePathhURL();

        assertEquals(expected, output);
    }
}