package com.andela.iworkout.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.andela.iworkout.utilities.Launcher;
import com.andela.iworkout.utilities.MsgBox;
import com.andela.iworkout.notifications.PushUpNotificationBuilder;
import com.andela.iworkout.notifications.PushUpNotificationManager;
import com.andela.iworkout.utilities.Settings;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Launcher.launch(this, MainActivity.class);
        finish();
    }
}
