package com.andela.iworkout.utilities;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
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
        long intValue = Long.valueOf(preferences.getString("set_time_of_pushup", "-1"));
        return (intValue < 0) ? 0 : intValue;
    }

    public static int getPushUps(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int intValue = Integer.valueOf(preferences.getString("set_number_of_pushup", "-1"));
        return (intValue < 0) ? 0 : intValue;
    }

    public static boolean getNotificationMode(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("notifications_pushup", false);
    }

    public static Uri getRingtone(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String value = preferences.getString("notifications_pushup_ringtone", "");
        return value.isEmpty() ? null : Uri.parse(value);
    }

    public static boolean getVibrate(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("notifications_pushup_vibrate", false);
    }

    public static int getReminder(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String value = preferences.getString("pushup_reminder", "");
        return value.isEmpty() ? -1 : Integer.valueOf(value);
    }

    public static String getAlarmTime(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("alarm_time", "");
    }
}
