package com.example.witsup;


import android.content.Intent;
import android.support.test.espresso.action.ScrollToAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)

public class QandATest {

    @Rule
    public ActivityTestRule rule=new ActivityTestRule(QandAPage.class,true,false);

    @Test
    public void btns() throws Exception {
       rule.launchActivity(new Intent());
       onView(withId(R.id.btnPost)).check(matches(withText("POST")));
       onView(withId(R.id.btnClear)).check(matches(withText("CLEAR")));
    }

    @Test
    public void text(){
        rule.launchActivity(new Intent());
        onView(withId(R.id.question)).check(matches(withText("QUESTION")));

    }
    @Test
    public void canItPostAQuestion(){
        rule.launchActivity(new Intent());
        onView(withId(R.id.questionInput)).perform(typeText("what is a surname"));
        onView(withId(R.id.btnPost)).perform(click());

    }

    @Test
    public void postQuestion() {
        rule.launchActivity(new Intent());
        onView(withId(R.id.answerBInput)).perform(typeText("name"));
        onView(withId(R.id.checkBoxB)).perform(click());
    }

    @Test
    public void answerC() {
        rule.launchActivity(new Intent());
        onView(withId(R.id.answerCInput)).perform(typeText("name"));
        onView(withId(R.id.checkBoxC)).perform(click());

    }

    @Test
    public void can_The_clear_Questions_and_answers(){
        rule.launchActivity(new Intent());

        onView(withId(R.id.questionInput)).perform(typeText("whats your name"),closeSoftKeyboard());
        onView(withId(R.id.answerAInput)).perform(typeText("name"),closeSoftKeyboard());
        onView(withId(R.id.answerBInput)).perform(typeText("no name"),closeSoftKeyboard());
        onView(withId(R.id.answerCInput)).perform(typeText("A and B"),closeSoftKeyboard());
        onView(withId(R.id.btnClear)).perform(click());

        onView(withId(R.id.questionInput)).check(matches(withText("")));
        onView(withId(R.id.answerAInput)).check(matches(withText("")));
        onView(withId(R.id.answerBInput)).check(matches(withText("")));
        onView(withId(R.id.answerCInput)).check(matches(withText("")));
    }




}
