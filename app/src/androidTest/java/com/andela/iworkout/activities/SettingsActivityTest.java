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
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class SettingsActivityTest extends ActivityInstrumentationTestCase2<SettingsActivity> {

    public SettingsActivityTest() {
        super(SettingsActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {

    }

    public void testOnCreate() throws Exception {
        getActivity();
        onView(withText("Settings")).check(matches(isDisplayed()));
        onView(withText("Plan Push Up")).check(matches(isDisplayed()));
        onView(withText("Notifications")).check(matches(isDisplayed()));
    }

    public void testOnPlanPushUp() throws Exception {
        getActivity();
        onView(withText("Plan Push Up")).perform(click());

        onView(withText("Plan Push Up")).check(matches(isDisplayed()));
        onView(withText("Push Up mode")).perform(click());
        onView(withText("Push Up mode")).perform(click());

        onView(withText("Push Up goal")).perform(click());
        onView(withInputType(InputType.TYPE_CLASS_NUMBER)).perform(clearText()).perform(typeText("200"));
        onView(withText("OK")).perform(click());

        onView(withText("Push Up goal")).perform(click());
        onView(withInputType(InputType.TYPE_CLASS_NUMBER)).perform(clearText()).perform(typeText("50"));
        onView(withText("OK")).perform(click());

        onView(withText("Push Up time (Minutes)")).perform(click());
        onView(withInputType(InputType.TYPE_CLASS_NUMBER)).perform(clearText()).perform(typeText("97"));
        onView(withText("OK")).perform(click());

        onView(withText("Push Up time (Minutes)")).perform(click());
        onView(withInputType(InputType.TYPE_CLASS_NUMBER)).perform(clearText()).perform(typeText("5"));
        onView(withText("OK")).perform(click());
    }

    public void testNotifications() throws Exception {
        getActivity();
        onView(withText("Notifications")).perform(click());

        onView(withText("Notifications")).check(matches(isDisplayed()));
        onView(withText("Push Up Notifications")).perform(click());
        onView(withText("Push Up Notifications")).perform(click());

        onView(withText("Ringtone")).check(matches(isDisplayed()));
        onView(withText("Ringtone")).perform(click());
        onView(withText("OK")).perform(click());

        onView(withText("Vibrate")).check(matches(isDisplayed()));
        onView(withText("Vibrate")).perform(click());
        onView(withText("Vibrate")).perform(click());

        onView(withText("Reminder")).check(matches(isDisplayed()));
        onView(withText("Reminder")).perform(click());
        onView(withText("Daily")).perform(click());

        onView(withText("Push Up Time")).check(matches(isDisplayed()));
        onView(withText("Push Up Time")).perform(click());
        onView(withText("Set")).perform(click());
    }
}