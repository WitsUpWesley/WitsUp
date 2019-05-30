package com.example.witsup;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class ResourcesforfbTest {
    @Rule
    public ActivityTestRule rule = new ActivityTestRule(Resourcesforfb.class, true, false);

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(testListActivity.class.getName(), null, false);




    @Test
    public void onCreate() {
        //assertNotNull(rule.getActivity().findViewById(R.layout.resources));
    }

    @Test
    public void onActivityResult() {
    }

    @Ignore("Called by view")
    @Test
    public void onClick() {
    }

    @Test
    public void goToViewUploads() {
        Bundle b = new Bundle();
        b.putString("username", "a1");
        b.putString("course", "test");

        rule.launchActivity(new Intent().putExtras(b));

        assertNotNull(rule.getActivity().findViewById(R.id.btnViewUploads));

        onView(withId(R.id.btnViewUploads)).perform(click());

        Activity secondAct = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull(secondAct);
        secondAct.finish();


    }

    @Test
    public void updateDatabase() {
    }




    @Test
    public void logout() {
        ActivityTestRule rule = new ActivityTestRule(Resourcesforfb.class, true, false);
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LogInPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("username", "a1");
        b.putString("course", "test");
        rule.launchActivity(new Intent().putExtras(b));

        assertNotNull(rule.getActivity().findViewById(R.id.btnLogout));

        onView(withId(R.id.btnLogout)).perform(click());

        Activity secondAct = getInstrumentation().waitForMonitorWithTimeout(monitor2, 5000);

        assertNotNull(secondAct);

        onView(withId(R.id.btnLogin)).check(matches(withText("Login")));
        secondAct.finish();


    }
}