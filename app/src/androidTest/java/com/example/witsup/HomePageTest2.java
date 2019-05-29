package com.example.witsup;

import android.content.Intent;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class HomePageTest2 {

    @Rule
    public ActivityTestRule<HomePage> rule=new ActivityTestRule(HomePage.class,true,false);

    @Test
    public void enrolBtn() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.btnEnrolInsCourse)).check(matches(withText("Enrol In Course")));
    }

    @Test
    public void createBtn() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.btnCreateCourse)).check(matches(withText("Create Course")));
    }

    @Test
    public void createBtnVisi() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.btnCreateCourse)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }
}