package com.example.witsup;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;

import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class CourseDetailsTest {

    @Test
    public void onCreate() {
    }

    @Test
    public void logout() {
        ActivityTestRule rule = new ActivityTestRule(CourseDetails.class, true, false);
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


    @Test
    public void openResourcesPage() {


            ActivityTestRule rule = new ActivityTestRule(CourseDetails.class, true, false);
            Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(Resourcesforfb.class.getName(), null, false);
            Bundle b = new Bundle();
            b.putString("username", "a1");
            b.putString("course", "test");
            rule.launchActivity(new Intent().putExtras(b));

            assertNotNull(rule.getActivity().findViewById(R.id.btnResources));

            onView(withId(R.id.btnResources)).perform(click());

            Activity secondAct = getInstrumentation().waitForMonitorWithTimeout(monitor2, 5000);

            assertNotNull(secondAct);

            onView(withId(R.id.btnViewUploads)).check(matches(withText("View uploads")));
            secondAct.finish();

    }

    @Test
    public void openAnnouncementsPage() {


        ActivityTestRule rule = new ActivityTestRule(CourseDetails.class, true, false);

        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(AnnouncementPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("username", "a1");
        b.putString("course", "test");
        rule.launchActivity(new Intent().putExtras(b));

        assertNotNull(rule.getActivity().findViewById(R.id.btnAnnouncements));

        onView(withId(R.id.btnAnnouncements)).perform(click());

        Activity secondAct = getInstrumentation().waitForMonitorWithTimeout(monitor2, 5000);

        assertNotNull(secondAct);

    //    onView(withId(R.id.btnCreateAnn)).check(matches(withText("Create Announcement")));
        secondAct.finish();
    }
}