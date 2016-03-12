package com.andela.iworkout.repository;


import com.andela.iworkout.model.Day;

import io.realm.RealmResults;

public interface DayRepository {
    RealmResults<Day> getAll();
    Day getByDate(String date);
    void insert(Day day);
    void update(Day day);
    void insertOrUpdate(Day day);
    void deleteByDate(String date);
    void deleteAll();
}
