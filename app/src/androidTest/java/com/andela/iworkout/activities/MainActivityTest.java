package com.andela.iworkout.activities;

import android.test.ActivityInstrumentationTestCase2;
import android.text.InputType;

import com.andela.iworkout.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
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
    }

    public void testOnCreateOptionsMenu() throws Exception {
        getActivity();
        onView(withId(R.id.action_settings)).check(matches(isDisplayed()));
    }

    public void testOnOptionsItemSelected() throws Exception {
        getActivity();
        onView(withId(R.id.action_settings)).perform(click());
    }

    public void testLaunchWorkout() throws Exception {
        getActivity();
        onView(withId(R.id.workout)).perform(click());
    }

    public void testLaunchAnalytics() throws Exception {

    }

    public void testApplicationFlow() throws Exception {
        getActivity();
        onView(withText("iWorkout")).check(matches(isDisplayed()));
        onView(withId(R.id.totalPushUps)).check(matches(isDisplayed()));
        onView(withId(R.id.todaysPushUps)).check(matches(isDisplayed()));

        onView(withId(R.id.action_settings)).perform(click());
        onView(withText("Settings")).check(matches(isDisplayed()));
        onView(withText("Plan Push Up")).check(matches(isDisplayed()));
        onView(withText("Notifications")).check(matches(isDisplayed()));

        onView(withText("Plan Push Up")).perform(click());
        onView(withText("Plan Push Up")).check(matches(isDisplayed()));
        onView(withText("Push Up mode")).perform(click());
        onView(withText("Push Up mode")).perform(click());

        onView(withText("Push Up goal")).perform(click());
        onView(withInputType(InputType.TYPE_CLASS_NUMBER)).perform(clearText()).perform(typeText("200"));
        onView(withText("OK")).perform(click());

        onView(withContentDescription("Navigate up")).perform(click());
        onView(withContentDescription("Navigate up")).perform(click());

        onView(withId(R.id.workout)).perform(click());
        onView(withText("Workout")).check(matches(isDisplayed()));
        onView(withId(R.id.pushUpKeeper)).check(matches(isDisplayed()));
        onView(withId(R.id.timeKeeper)).check(matches(isDisplayed()));
        onView(withId(R.id.touchScreen)).check(matches(isDisplayed()));
        onView(withId(R.id.start)).check(matches(isDisplayed()));

        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withText("190")).check(matches(isDisplayed()));
        onView(withId(R.id.start)).perform(click());
        onView(withText("Congratulations")).check(matches(isDisplayed()));
        onView(withId(R.id.gotit)).perform(click());

        onView(withText("iWorkout")).check(matches(isDisplayed()));
        onView(withId(R.id.totalPushUps)).check(matches(isDisplayed()));
        onView(withId(R.id.todaysPushUps)).check(matches(isDisplayed()));

        onView(withId(R.id.action_settings)).perform(click());
        onView(withText("Settings")).check(matches(isDisplayed()));
        onView(withText("Plan Push Up")).check(matches(isDisplayed()));
        onView(withText("Notifications")).check(matches(isDisplayed()));

        onView(withText("Plan Push Up")).perform(click());
        onView(withText("Plan Push Up")).check(matches(isDisplayed()));
        onView(withText("Push Up mode")).perform(click());

        onView(withText("Push Up time (Minutes)")).perform(click());
        onView(withInputType(InputType.TYPE_CLASS_NUMBER)).perform(clearText()).perform(typeText("10"));
        onView(withText("OK")).perform(click());

        onView(withContentDescription("Navigate up")).perform(click());
        onView(withContentDescription("Navigate up")).perform(click());

        onView(withId(R.id.workout)).perform(click());
        onView(withText("Workout")).check(matches(isDisplayed()));
        onView(withId(R.id.pushUpKeeper)).check(matches(isDisplayed()));
        onView(withId(R.id.timeKeeper)).check(matches(isDisplayed()));
        onView(withId(R.id.touchScreen)).check(matches(isDisplayed()));
        onView(withId(R.id.start)).check(matches(isDisplayed()));

        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withText("10")).check(matches(isDisplayed()));
        onView(withId(R.id.start)).perform(click());
        onView(withText("Congratulations")).check(matches(isDisplayed()));
        onView(withId(R.id.gotit)).perform(click());

        onView(withText("iWorkout")).check(matches(isDisplayed()));
        onView(withId(R.id.totalPushUps)).check(matches(isDisplayed()));
        onView(withId(R.id.todaysPushUps)).check(matches(isDisplayed()));

        onView(withId(R.id.action_settings)).perform(click());
        onView(withText("Settings")).check(matches(isDisplayed()));
        onView(withText("Plan Push Up")).check(matches(isDisplayed()));
        onView(withText("Notifications")).check(matches(isDisplayed()));

        onView(withText("Plan Push Up")).perform(click());
        onView(withText("Plan Push Up")).check(matches(isDisplayed()));
        onView(withText("Push Up mode")).perform(click());

        onView(withText("Push Up goal")).perform(click());
        onView(withInputType(InputType.TYPE_CLASS_NUMBER)).perform(clearText()).perform(typeText("50"));
        onView(withText("OK")).perform(click());

        onView(withContentDescription("Navigate up")).perform(click());
        onView(withContentDescription("Navigate up")).perform(click());
    }
}