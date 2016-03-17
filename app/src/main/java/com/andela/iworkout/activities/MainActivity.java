package com.andela.iworkout.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.andela.iworkout.R;
import com.andela.iworkout.utilities.Launcher;
import com.andela.iworkout.utilities.MsgBox;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manageToolbar();
    }

    private void manageToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                MsgBox.show(MainActivity.this, "Settings Clicked");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchWorkout(View view) {
        Launcher.launch(this, WorkoutActivity.class);
    }

    public void launchAnalytics(View view) {
        MsgBox.show(this, "Launch Analytics Screen");
    }
}
