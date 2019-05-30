package com.example.witsup;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreateCourseTest {

    @Test
    public void countCourses() {
        CreateCourse cr=new CreateCourse();
        int input=5;
        int expected=15;
        int output;
        output=cr.countCourses(input);
        assertEquals(expected,output);
    }
}