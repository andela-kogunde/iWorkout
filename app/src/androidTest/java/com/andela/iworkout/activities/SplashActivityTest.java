package com.andela.iworkout.activities;

import android.test.ActivityInstrumentationTestCase2;

public class SplashActivityTest extends ActivityInstrumentationTestCase2<SplashActivity> {

    public SplashActivityTest() {
        super(SplashActivity.class);
    }

    public void testShouldBeAbleToLaunchSplashActivity() {
        getActivity();
    }
}