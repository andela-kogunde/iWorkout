package com.andela.iworkout.activities;

import android.test.ActivityInstrumentationTestCase2;

import com.andela.iworkout.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {

    }

    public void testOnCreate() throws Exception {
        getActivity();
        onView(withText("iWorkout")).check(matches(isDisplayed()));
        onView(withId(R.id.totalPushUps)).check(matches(isDisplayed()));
        onView(withId(R.id.todaysPushUps)).check(matches(isDisplayed()));
        onView(withId(R.id.caloriesBurned)).check(matches(isDisplayed()));
        onView(withId(R.id.energyGained)).check(matches(isDisplayed()));
    }

    public void testOnCreateOptionsMenu() throws Exception {
        getActivity();
        onView(withText(R.string.action_settings)).check(matches(isDisplayed()));
    }

    public void testOnOptionsItemSelected() throws Exception {
        getActivity();
        onView(withText(R.string.action_settings)).perform(click());
    }

    public void testLaunchWorkout() throws Exception {
        getActivity();
        onView(withId(R.id.workout)).perform(click());
    }

    public void testLaunchAnalytics() throws Exception {

    }
}