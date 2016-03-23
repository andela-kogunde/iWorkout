package com.andela.iworkout.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.andela.iworkout.R;
import com.andela.iworkout.fragments.WorkoutActivityFragment;
import com.andela.iworkout.utilities.MsgBox;

public class WorkoutActivity extends AppCompatActivity {
    private boolean toggle = false;
    private boolean muteToggle = false;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        manageToolbar();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void manageToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_workout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mute:
                muteSound(item);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void muteSound(MenuItem item) {
        if (muteToggle) {
            item.setIcon(R.mipmap.unmute);
            getWorkout().unmuteSound();
        } else {
            item.setIcon(R.mipmap.mute);
            getWorkout().muteSound();
        }
        muteToggle = !muteToggle;
    }

    public void startPressed(View view) {
        button = (Button) view;
        pressButton();
    }

    public void pressButton() {
        if (toggle) {
            button.setText(getString(R.string.start));
            completedIsPressed();
        } else {
            button.setText(getString(R.string.completed));
            startIsPressed();
        }
        toggle = !toggle;
    }

    private void startIsPressed() {
        getWorkout().startWorkout();
    }

    private void completedIsPressed() {
        getWorkout().stopWorkout();
    }

    @Override
    public void onBackPressed() {
        getWorkout().cancelWorkout();
        finish();
    }

    private WorkoutActivityFragment getWorkout() {
        return (WorkoutActivityFragment) getSupportFragmentManager()
                .getFragments().get(0);
    }
}
