package com.andela.iworkout.repository;

import com.andela.iworkout.model.Day;

import io.realm.Realm;
import io.realm.RealmResults;

public class DayRepositoryImplementation implements DayRepository {
    private Realm realm;

    public DayRepositoryImplementation() {
        this.realm = Realm.getDefaultInstance();
    }

    @Override
    public RealmResults<Day> getAll() {
        return  realm.where(Day.class).findAll();
    }

    @Override
    public Day getByDate(String date) {
        return realm.where(Day.class).equalTo("thedate", date).findFirst();
    }

    @Override
    public void insert(Day day) {
        realm.beginTransaction();
        realm.copyToRealm(day);
        realm.commitTransaction();
    }

    @Override
    public void update(Day day) {
        Day today = getByDate(day.getThedate());
        realm.beginTransaction();
        today.setPushups(today.getPushups() + day.getPushups());
        realm.commitTransaction();
    }

    @Override
    public void insertOrUpdate(Day day) {
        Day tempDay = getByDate(day.getThedate());
        if (tempDay == null) {
            insert(day);
        } else {
            update(day);
        }
    }

    @Override
    public void deleteByDate(String date) {
        Day delete = getByDate(date);
        realm.beginTransaction();
        delete.removeFromRealm();
        realm.commitTransaction();
    }

    @Override
    public void deleteAll() {
        RealmResults<Day> deleteAll = getAll();
        realm.beginTransaction();
        deleteAll.clear();
        realm.commitTransaction();
    }
}
