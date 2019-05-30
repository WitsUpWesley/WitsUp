package com.example.witsup;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;
import android.widget.LinearLayout;

import org.junit.Ignore;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class HomePageTest {

    @Test
    public void onCreate() {
    }


    @Ignore("Tested in app that populates all")
    @Test
    public void processData() {

    }



    @Test
    public void logout() {
        ActivityTestRule rule = new ActivityTestRule(HomePage.class, true, false);
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LogInPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("PersonNumber", "a1");
        b.putString("course", "test");
        rule.launchActivity(new Intent().putExtras(b));

        assertNotNull(rule.getActivity().findViewById(R.id.btnLogout));

        onView(withId(R.id.btnLogout)).perform(click());

        Activity secondAct = getInstrumentation().waitForMonitorWithTimeout(monitor2, 5000);

        assertNotNull(secondAct);

        onView(withId(R.id.btnLogin)).check(matches(withText("Login")));
        secondAct.finish();


    }


    @Test
    public void goToCreateCourse() {



            ActivityTestRule rule = new ActivityTestRule(HomePage.class, true, false);

            Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(CreateCourse.class.getName(), null, false);
            Bundle b = new Bundle();
            b.putString("PersonNumber", "a1");
            b.putString("course", "test");
            rule.launchActivity(new Intent().putExtras(b));

            assertNotNull(rule.getActivity().findViewById(R.id.btnCreateCourse));

            onView(withId(R.id.btnCreateCourse)).perform(click());

            Activity secondAct = getInstrumentation().waitForMonitorWithTimeout(monitor2, 5000);

            assertNotNull(secondAct);

            secondAct.finish();






    }

    @Test
    public void goToEnrolInCourse() {
        ActivityTestRule rule = new ActivityTestRule(HomePage.class, true, false);

        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(Enrolincourse.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("PersonNumber", "a1");
        b.putString("course", "test");
        rule.launchActivity(new Intent().putExtras(b));

        assertNotNull(rule.getActivity().findViewById(R.id.btnEnrolInsCourse));

        onView(withId(R.id.btnEnrolInsCourse)).perform(click());

        Activity secondAct = getInstrumentation().waitForMonitorWithTimeout(monitor2, 5000);

        assertNotNull(secondAct);

        secondAct.finish();
    }
}