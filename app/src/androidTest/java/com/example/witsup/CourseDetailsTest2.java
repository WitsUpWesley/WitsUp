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
public class CourseDetailsTest2 {

    @Rule
    public ActivityTestRule rule=new ActivityTestRule(CourseDetails.class,true,false);

    @Test
    public void btnAnnTest() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.btnAnnouncements)).check(matches(withText("Announcements")));
        onView(withId(R.id.btnAnnouncements)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void btnResTest() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.btnResources)).check(matches(withText("Resources")));
        onView(withId(R.id.btnResources)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void btnChatTest() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.btnChat)).check(matches(withText("Chat")));
        onView(withId(R.id.btnChat)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void btnQTest() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.btnQandA)).check(matches(withText("Q and A")));
        onView(withId(R.id.btnQandA)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void courseTest() throws Exception {
        rule.launchActivity(new Intent());
        onView(withId(R.id.labelCourse)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
}