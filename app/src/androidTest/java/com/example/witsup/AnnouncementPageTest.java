package com.example.witsup;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AnnouncementPageTest {

    @Rule
    public ActivityTestRule rule=new ActivityTestRule(AnnouncementPage.class,true,false);

    @Test
    public void shouldRenderView() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.lblAnnouncement)).check(matches(withText("Please enter in your announcement below")));
    }

    @Test
    public void txtAddTest() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.txtAddAnn)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

    }

    @Test
    public void btnCreateTest() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.btnCreateAnn)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

    }

    @Test
    public void lblAnnounceTest() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.lblAnnouncement)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

    }
    @Test
    public void logout() {
        ActivityTestRule rule = new ActivityTestRule(AnnouncementPage.class, true, false);
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LogInPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("username", "a1");
        b.putString("course", "test");
        rule.launchActivity(new Intent().putExtras(b));

        assertNotNull(rule.getActivity().findViewById(R.id.btnLogout2));

        onView(withId(R.id.btnLogout2)).perform(click());

        Activity secondAct = getInstrumentation().waitForMonitorWithTimeout(monitor2, 5000);

        assertNotNull(secondAct);

        onView(withId(R.id.btnLogin)).check(matches(withText("Login")));
        secondAct.finish();


    }


}