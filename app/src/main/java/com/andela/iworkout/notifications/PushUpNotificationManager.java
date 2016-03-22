package com.andela.iworkout.notifications;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.andela.iworkout.utilities.Settings;
import com.andela.iworkout.preferences.TimePreference;

import java.util.Calendar;

public class PushUpNotificationManager {
    private static final int DAILY = 300;
    private static final int EVERY_TWO_DAYS = 200;
    private static final int EVERY_SATURDAYS = 100;

    private Context context;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    public PushUpNotificationManager(Context context) {
        this.context = context;
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public void setPushUpNotifications(Notification notification) {
        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);

        pendingIntent = PendingIntent.getBroadcast(
                context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        String time = Settings.getAlarmTime(context);
        if (!time.isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, TimePreference.getHour(time));
            calendar.set(Calendar.MINUTE, TimePreference.getMinute(time));
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            schedulePushUp(calendar);
        }
    }

    public void cancelPushUpNotifications() {
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

    private void schedulePushUp(Calendar calendar) {
        int reminder = Settings.getReminder(context);
        switch (reminder) {
            case DAILY:
                daily(calendar);
                break;
            case EVERY_TWO_DAYS:
                everyTwoDays(calendar);
                break;
            case EVERY_SATURDAYS:
                everySaturdays(calendar);
                break;
        }
    }

    private void daily(Calendar calendar) {
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent);
    }

    private void everyTwoDays(Calendar calendar) {
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                (AlarmManager.INTERVAL_DAY * 2),
                pendingIntent);
    }

    private void everySaturdays(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                (AlarmManager.INTERVAL_DAY * 7),
                pendingIntent);
    }
}
