package com.andela.iworkout.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.andela.iworkout.R;
import com.andela.iworkout.fragments.WorkoutActivityFragment;

public class WorkoutActivity extends AppCompatActivity {
    private boolean toggle = false;
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

    public void startIsPressed() {
        getWorkout().startWorkout();
    }

    public void completedIsPressed() {
        getWorkout().stopWorkout();
    }

    @Override
    public void onBackPressed() {
        getWorkout().cancelWorkout();
        super.onBackPressed();
    }

    private WorkoutActivityFragment getWorkout() {
        return (WorkoutActivityFragment) getSupportFragmentManager()
                .getFragments().get(0);
    }
}
