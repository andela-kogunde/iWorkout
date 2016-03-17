package com.andela.iworkout.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Day extends RealmObject {
    private int pushups;
    private int timeTaken;

    @PrimaryKey
    private String thedate;

    public int getPushups() {
        return pushups;
    }

    public void setPushups(int pushups) {
        this.pushups = pushups;
    }

    public String getThedate() {
        return thedate;
    }

    public void setThedate(String thedate) {
        this.thedate = thedate;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(int timeTaken) {
        this.timeTaken = timeTaken;
    }
}
