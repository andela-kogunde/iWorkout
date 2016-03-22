package com.andela.iworkout.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.andela.iworkout.R;
import com.andela.iworkout.managers.WorkoutManager;
import com.andela.iworkout.managers.AnalyticsManager;
import com.github.mikephil.charting.charts.BarChart;

public class AnalyticsActivity extends AppCompatActivity {
    private AnalyticsManager analyticsManager;

    private WorkoutManager getWorkoutManager() {
        return MyApplication.getWorkoutManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        manageToolbar();

        BarChart mChart = (BarChart) findViewById(R.id.chart1);

        analyticsManager = new AnalyticsManager(mChart);
        analyticsManager.setData(getWorkoutManager().getLastFive());
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
        getMenuInflater().inflate(R.menu.menu_analytics, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fivedays:
                analyticsManager.displayInstantData(getWorkoutManager().getLastFive());
                return true;
            case R.id.sevendays:
                analyticsManager.displayInstantData(getWorkoutManager().getLastSeven());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
