package com.andela.iworkout.repository;


import com.andela.iworkout.model.Day;

import java.util.List;

import io.realm.RealmResults;

public interface DayRepository {
    RealmResults<Day> getAll();

    List<Day> getFromLast(int value);

    Day getByDate(String date);

    void insert(Day day);

    void update(Day day);

    void insertOrUpdate(Day day);

    void deleteByDate(String date);

    void deleteAll();

    void close();
}
