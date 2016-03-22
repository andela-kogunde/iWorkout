package com.andela.iworkout.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andela.iworkout.R;
import com.andela.iworkout.activities.MyApplication;
import com.andela.iworkout.managers.WorkoutManager;

import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivityFragment extends Fragment {
    private WaveLoadingView totalPushUps;
    private WaveLoadingView todaysPushUps;

    private WorkoutManager getWorkoutManager() {
        return MyApplication.getWorkoutManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponent(view);
        updatePushups();
    }

    private void initializeComponent(View view) {
        totalPushUps = (WaveLoadingView) view.findViewById(R.id.totalPushUps);
        todaysPushUps = (WaveLoadingView) view.findViewById(R.id.todaysPushUps);
    }

    public void updatePushups() {
        int total = getWorkoutManager().totalPushUps();
        totalPushUps.setCenterTitle(String.valueOf(total));
        totalPushUps.setAmplitudeRatio(70);
        totalPushUps.setProgressValue(70);

        int today = getWorkoutManager().todaysPushUps();
        todaysPushUps.setCenterTitle(String.valueOf(today));
        todaysPushUps.setAmplitudeRatio(70);
        todaysPushUps.setProgressValue(70);
    }
}
