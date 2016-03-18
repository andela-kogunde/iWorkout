package com.andela.iworkout.utilities;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {
    public Settings() {
    }

    public static boolean getPushUpMode(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("pushup_mode", false);
    }

    public static long getTime(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        long intValue = Long.valueOf(preferences.getString("set_time_of_pushup", ""));
        return (intValue < 0) ? 0 : intValue;
    }

    public static int getPushUps(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int intValue = Integer.valueOf(preferences.getString("set_number_of_pushup", ""));
        return (intValue < 0) ? 0 : intValue;
    }
}
