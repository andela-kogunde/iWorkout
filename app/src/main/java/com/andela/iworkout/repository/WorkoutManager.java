package com.andela.iworkout.repository;


import com.andela.iworkout.model.Day;
import com.andela.iworkout.utilities.DateFormatter;

import io.realm.RealmResults;

public class WorkoutManager {
    private DayRepository dayRepository;

    public WorkoutManager() {
        dayRepository = new DayRepositoryImplementation();
    }

    public int totalPushUps() {
        RealmResults<Day> results = dayRepository.getAll();
        int totalPushups = 0;
        for (Day day : results) {
            totalPushups += day.getPushups();
        }
        return totalPushups;
    }

    public int todaysPushUps() {
        String date = DateFormatter.getReadableDate(System.currentTimeMillis()).trim();
        Day day = dayRepository.getByDate(date);
        return day == null ? 0 : day.getPushups();
    }

    public int energyGained() {
        return 0;
    }

    public int caloriesBurned() {
        return 0;
    }

    public void savePushUps(Day day) {
        if (day != null) {
            dayRepository.insertOrUpdate(day);
        }
    }
}
