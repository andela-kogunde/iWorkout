package com.andela.iworkout.utilities;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {
    public Settings() {
    }

    public static void saveTime(Context context, long value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("TIMEFORPU", value);
        editor.commit();
    }

    public static long getTime(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int intValue = preferences.getInt("TIMEFORPU", 0);
        return (intValue < 0) ? 0 : intValue;
    }

    public static void savePushUps(Context context, int value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("PUSHUPS", value);
        editor.commit();
    }

    public static int getPushUps(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int intValue = preferences.getInt("PUSHUPS", 0);
        return (intValue < 0) ? 0 : intValue;
    }
}
