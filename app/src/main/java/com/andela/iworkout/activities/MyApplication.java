package com.andela.iworkout.activities;


import android.app.Application;

import com.andela.iworkout.managers.WorkoutManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    private static WorkoutManager workoutManager;

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static WorkoutManager getWorkoutManager() {
        if (workoutManager == null) {
            workoutManager = new WorkoutManager();
        }

        return workoutManager;
    }
}
