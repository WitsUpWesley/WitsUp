package com.example.witsup;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ResourcesforfbTest {
    @Rule
    public ActivityTestRule rule=new ActivityTestRule(Resourcesforfb.class,true,false);
    @Test
    public void onCreate() {
        assertNotNull(rule.getActivity().findViewById(R.layout.resources));
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
    }

    @Test
    public void updateDatabase() {
    }

    @Test
    public void logout() {
    }
}