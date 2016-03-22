package com.andela.iworkout.notifications;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import com.andela.iworkout.R;
import com.andela.iworkout.activities.MainActivity;
import com.andela.iworkout.utilities.Settings;

public class PushUpNotificationBuilder {

    public static Notification getNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("iWorkout");
        builder.setContentText("It's time for push up");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(getPendingIntent(context));
        builder.setAutoCancel(true);

        setVibration(context, builder);
        setSound(context, builder);

        return builder.build();
    }

    private static void setSound(Context context, NotificationCompat.Builder builder) {
        Uri ringtone = Settings.getRingtone(context);
        if (ringtone != null) {
            builder.setSound(ringtone);
        }
    }

    private static void setVibration(Context context, NotificationCompat.Builder builder) {
        if (Settings.getVibrate(context)) {
            builder.setVibrate(new long[]{500, 1000, 500, 1000});
        }
    }

    private static PendingIntent getPendingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
