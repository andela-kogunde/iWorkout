package com.andela.iworkout.managers;


import com.andela.iworkout.model.Day;
import com.andela.iworkout.repository.DayRepository;
import com.andela.iworkout.repository.DayRepositoryImplementation;
import com.andela.iworkout.utilities.DateFormatter;

import java.util.List;

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

    public void savePushUps(Day day) {
        if (day != null) {
            dayRepository.insertOrUpdate(day);
        }
    }

    public List<Day> getLastFive() {
        return dayRepository.getFromLast(5);
    }

    public List<Day> getLastSeven() {
        return dayRepository.getFromLast(7);
    }
}
