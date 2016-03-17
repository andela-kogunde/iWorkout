package com.andela.iworkout.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.andela.iworkout.R;
import com.andela.iworkout.activities.MyApplication;
import com.andela.iworkout.repository.DayRepository;
import com.andela.iworkout.repository.DayRepositoryImplementation;
import com.andela.iworkout.repository.WorkoutManager;

import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivityFragment extends Fragment {
    WaveLoadingView totalPushUps;
    WaveLoadingView todaysPushUps;
    WaveLoadingView caloriesBurned;
    WaveLoadingView energyGained;

    public MainActivityFragment() {
    }

    private WorkoutManager getWorkoutManager(){
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
        caloriesBurned = (WaveLoadingView) view.findViewById(R.id.caloriesBurned);
        energyGained = (WaveLoadingView) view.findViewById(R.id.energyGained);
    }

    public void updatePushups(){
        totalPushUps.setCenterTitle(String.valueOf(getWorkoutManager().totalPushUps()));
        totalPushUps.setAmplitudeRatio(70);
        totalPushUps.setProgressValue(70);

        todaysPushUps.setCenterTitle(String.valueOf(getWorkoutManager().todaysPushUps()));
        todaysPushUps.setAmplitudeRatio(70);
        todaysPushUps.setProgressValue(70);

        caloriesBurned.setCenterTitle(String.valueOf(getWorkoutManager().caloriesBurned()));
        caloriesBurned.setAmplitudeRatio(10);
        caloriesBurned.setProgressValue(10);

        energyGained.setCenterTitle(String.valueOf(getWorkoutManager().energyGained()));
        energyGained.setAmplitudeRatio(40);
        energyGained.setProgressValue(40);
    }
}
