package com.andela.iworkout.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.andela.iworkout.R;
import com.andela.iworkout.fragments.MainActivityFragment;
import com.andela.iworkout.notifications.PushUpNotificationBuilder;
import com.andela.iworkout.notifications.PushUpNotificationManager;
import com.andela.iworkout.utilities.Launcher;
import com.andela.iworkout.utilities.Settings;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manageToolbar();

        managePushUpNotifications(this);
    }

    private void manageToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void managePushUpNotifications(Context context) {
        PushUpNotificationManager notificationManager = new PushUpNotificationManager(context);
        notificationManager.cancelPushUpNotifications();

        if (Settings.getNotificationMode(context)) {
            notificationManager.setPushUpNotifications(
                    PushUpNotificationBuilder.getNotification(context));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Launcher.launch(MainActivity.this, SettingsActivity.class);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchWorkout(View view) {
        Launcher.launchForResult(this, WorkoutActivity.class, 1);
    }

    public void launchAnalytics(View view) {
        Launcher.launchForResult(this, AnalyticsActivity.class, 1);
    }

    private MainActivityFragment getDashboard() {
        return (MainActivityFragment) getSupportFragmentManager()
                .getFragments().get(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getDashboard().updatePushups();
    }
}
