package com.andela.iworkout.activities;

import android.test.ActivityInstrumentationTestCase2;

import com.andela.iworkout.R;

import junit.framework.TestCase;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class AnalyticsActivityTest extends ActivityInstrumentationTestCase2<AnalyticsActivity> {

    public AnalyticsActivityTest() {
        super(AnalyticsActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testOnCreate() throws Exception {
        getActivity();
        onView(withText("Push Up Analytics")).check(matches(isDisplayed()));
        onView(withId(R.id.chart1)).check(matches(isDisplayed()));
    }

    public void testOnCreateOptionsMenu() throws Exception {
        getActivity();
        onView(withId(R.id.megamenu)).check(matches(isDisplayed()));
    }

    public void testOnOptionsItemSelected() throws Exception {
        getActivity();
        onView(withId(R.id.megamenu)).perform(click());
        onView(withText("Last 5 Days")).perform(click());
        onView(withId(R.id.megamenu)).perform(click());
        onView(withText("Last 7 Days")).perform(click());
    }
}