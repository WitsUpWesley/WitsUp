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
public class LogInPageTest2 {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(LogInPage.class,true,false);

    @Test
    public void txtUser() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.txtUsername)).check(matches(withText("")));
    }

    @Test
    public void txtPass() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.txtPassword)).check(matches(withText("")));
    }

    @Test
    public void loginPassword() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.lblPassword)).check(matches(withText("Password")));
    }

    @Test
    public void loginUsername() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.lblUsername)).check(matches(withText("Username")));
    }

    @Test
    public void btnLogin() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.btnLogin)).check(matches(withText("Login")));
    }

    @Test
    public void testHidden() throws Exception{
        rule.launchActivity(new Intent());
        onView(withId(R.id.btnCreateNewuser1)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }
}