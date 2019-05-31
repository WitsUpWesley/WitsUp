package com.example.witsup;


import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class viewTest {


    @Test
    public void questionView() {
        ActivityTestRule rule = new ActivityTestRule(ViewAnswers.class, true, false);
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LogInPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("username", "a1");
        b.putString("course", "coms3006");
        rule.launchActivity(new Intent().putExtras(b));
        assertNotNull(rule.getActivity().findViewById(R.id.questionShown));
    }

    @Test
    public void radioA() {
        ActivityTestRule rule = new ActivityTestRule(ViewAnswers.class, true, false);
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LogInPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("username", "a1");
        b.putString("course", "coms3006");
        rule.launchActivity(new Intent().putExtras(b));
        assertNotNull(rule.getActivity().findViewById(R.id.A));
    }

    @Test
    public void radioB() {
        ActivityTestRule rule = new ActivityTestRule(ViewAnswers.class, true, false);
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LogInPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("username", "a1");
        b.putString("course", "coms3006");
        rule.launchActivity(new Intent().putExtras(b));
        assertNotNull(rule.getActivity().findViewById(R.id.B));
    }

    @Test
    public void radioC() {
        ActivityTestRule rule = new ActivityTestRule(ViewAnswers.class, true, false);
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LogInPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("username", "a1");
        b.putString("course", "coms3006");
        rule.launchActivity(new Intent().putExtras(b));
        assertNotNull(rule.getActivity().findViewById(R.id.C));
    }
    @Test
    public void radioD() {
        ActivityTestRule rule = new ActivityTestRule(ViewAnswers.class, true, false);
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LogInPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("username", "a1");
        b.putString("course", "coms3006");
        rule.launchActivity(new Intent().putExtras(b));
        assertNotNull(rule.getActivity().findViewById(R.id.D));
    }



    @Test
    public void questionView2() {
        ActivityTestRule rule = new ActivityTestRule(ViewAnswers.class, true, false);
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LogInPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("username", "a1");
        b.putString("course", "t1");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.questionShown)).check(matches(withText("")));
    }

    @Test
    public void radioA2() {
        ActivityTestRule rule = new ActivityTestRule(ViewAnswers.class, true, false);
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LogInPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("username", "a1");
        b.putString("course", "t1");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.A)).check(matches(withText("AnswerA")));
    }

    @Test
    public void radioB2() {
        ActivityTestRule rule = new ActivityTestRule(ViewAnswers.class, true, false);
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LogInPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("username", "a1");
        b.putString("course", "t1");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.B)).check(matches(withText("AnswerB")));
    }

    @Test
    public void radioC2() {
        ActivityTestRule rule = new ActivityTestRule(ViewAnswers.class, true, false);
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LogInPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("username", "a1");
        b.putString("course", "t1");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.C)).check(matches(withText("AnswerC")));
    }
    @Test
    public void radioD2() {
        ActivityTestRule rule = new ActivityTestRule(ViewAnswers.class, true, false);
        Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(LogInPage.class.getName(), null, false);
        Bundle b = new Bundle();
        b.putString("username", "a1");
        b.putString("course", "t1");
        rule.launchActivity(new Intent().putExtras(b));
        onView(withId(R.id.D)).check(matches(withText("AnswerD")));

    }
}
