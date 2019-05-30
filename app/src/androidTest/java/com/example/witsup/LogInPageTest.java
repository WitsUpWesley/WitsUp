package com.example.witsup;

import android.content.Intent;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class LogInPageTest {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(LogInPage.class,true,false);



    @Test
    public void onCreate() {
    }

    @Test
    public void login() {

    }

    @Ignore("Not a feature yet")
    @Test
    public void createAccount() {

    }

    @Test
    public void checkIfUserExists() {
/*
        rule.launchActivity(new Intent());


        onView(withId(R.id.txtConfirmPassword)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.btnLogin)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
        */
    }

    @Test
    public void outputToLabel() {
    }

    @Test
    public void createNewUser() {
    }
}