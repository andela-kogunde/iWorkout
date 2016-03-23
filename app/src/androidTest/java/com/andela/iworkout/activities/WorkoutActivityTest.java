package com.andela.iworkout.activities;

import android.test.ActivityInstrumentationTestCase2;

import com.andela.iworkout.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class WorkoutActivityTest  extends ActivityInstrumentationTestCase2<WorkoutActivity> {

    public WorkoutActivityTest() {
        super(WorkoutActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testOnCreate() throws Exception {
        getActivity();
        onView(withText("Workout")).check(matches(isDisplayed()));
        onView(withId(R.id.pushUpKeeper)).check(matches(isDisplayed()));
        onView(withId(R.id.timeKeeper)).check(matches(isDisplayed()));
        onView(withId(R.id.touchScreen)).check(matches(isDisplayed()));
        onView(withId(R.id.start)).check(matches(isDisplayed()));
    }

    public void testStartPressed() throws Exception {
        getActivity();
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withId(R.id.touchScreen)).perform(click());
        onView(withText("45")).check(matches(isDisplayed()));
        onView(withId(R.id.start)).perform(click());
        onView(withText("Congratulations")).check(matches(isDisplayed()));
        onView(withId(R.id.gotit)).perform(click());
    }

    public void testPressButton() throws Exception {
        getActivity();
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
        onView(withText("30")).check(matches(isDisplayed()));
        onView(withId(R.id.start)).perform(click());
        onView(withText("Congratulations")).check(matches(isDisplayed()));
        onView(withId(R.id.gotit)).perform(click());
    }
}