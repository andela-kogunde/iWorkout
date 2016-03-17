package com.andela.iworkout.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andela.iworkout.R;
import com.andela.iworkout.activities.MyApplication;
import com.andela.iworkout.activities.WorkoutActivity;
import com.andela.iworkout.model.Day;
import com.andela.iworkout.repository.WorkoutManager;
import com.andela.iworkout.utilities.DateFormatter;
import com.andela.iworkout.utilities.MsgBox;
import com.andela.iworkout.utilities.OnTimerTickListener;
import com.andela.iworkout.utilities.PushUpListener;
import com.andela.iworkout.utilities.PushUpManager;
import com.andela.iworkout.utilities.Settings;
import com.andela.iworkout.utilities.TimeCounter;

public class WorkoutActivityFragment extends Fragment {
    private static final int SECONDS = 1000;
    private static final int MINUTES = 60000;

    private TimeCounter timeCounter;
    private PushUpManager pushUpManager;
    private int counter = 0;
    private int pushUpGoal = 0;
    private Day day;

    public WorkoutActivityFragment() {
        timeCounter = new TimeCounter();
        day = new Day();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTimeKeeper(view);
    }

    private void setPushUpManager() {
        View touchScreen = getActivity().findViewById(R.id.touchScreen);
        final TextView pushUpKeeper = (TextView) getActivity().findViewById(R.id.pushUpKeeper);

        pushUpManager = new PushUpManager(getContext(), touchScreen);

        pushUpGoal = 50;
        //pushUpGoal = Settings.getPushUps(getContext());
        if (pushUpGoal <= 0) { //PUSHUP COUNT UP TILL TIME ELAPSE
            pushUpManager.setListener(new PushUpListener() {
                @Override
                public void pushUp() {
                    pushUpKeeper.setText(String.valueOf(counter++));
                }

                @Override
                public void pushDown() {
                }
            });
        } else {
            pushUpKeeper.setText(String.valueOf(pushUpGoal));
            pushUpManager.setListener(new PushUpListener() {
                @Override
                public void pushUp() {
                    counter++;
                    pushUpKeeper.setText(String.valueOf(pushUpGoal--));

                    if (pushUpGoal < 0) {
                        endPushupSession();
                    }
                }

                @Override
                public void pushDown() {
                }
            });
        }

    }

    private void setTimeKeeper(View view) {
        final TextView timeKeeper = (TextView) view.findViewById(R.id.timeKeeper);

        long timeTill = Settings.getTime(getContext());
        //long timeTill = 100000;
        if (timeTill <= 0) { //TIME COUNT UP TO MEET PUSHUP GOAL
            timeCounter.countUp(SECONDS, new OnTimerTickListener() {
                @Override
                public void onTick(long millisecond) {
                    timeKeeper.setText(getTime(millisecond));
                }

                @Override
                public void onFinish() {
                }
            });
        } else {
            timeKeeper.setText(getTime(timeTill));
            timeCounter.countDown(timeTill, SECONDS, new OnTimerTickListener() {
                @Override
                public void onTick(long millisecond) {
                    timeKeeper.setText(getTime(millisecond));
                }

                @Override
                public void onFinish() {
                    timeKeeper.setText(String.valueOf(0));
                    endPushupSession();
                }
            });
        }
    }

    public void startWorkout() {
        counter = 0;
        timeCounter.start();
        setPushUpManager();
    }

    public void stopWorkout() {
        cancelWorkout();

        day.setThedate(DateFormatter.getReadableDate(System.currentTimeMillis()));
        day.setPushups(counter - 1);
        getWorkoutManager().savePushUps(day);
    }

    public void cancelWorkout() {
        timeCounter.stop();
        if (pushUpManager != null) {
            pushUpManager.disconnectPushUp();
        }
    }

    private void endPushupSession() {
        //PUSHUP PREVIEW.
        //PLAY SOUND

        WorkoutActivity workoutActivity = (WorkoutActivity) getActivity();
        workoutActivity.pressButton();
    }

    private String getTime(long milliSeconds) {
        if (milliSeconds >= MINUTES) {
            return DateFormatter.getReadableTime(milliSeconds);
        }

        return DateFormatter.getReadableSeconds(milliSeconds);
    }

    private WorkoutManager getWorkoutManager() {
        return MyApplication.getWorkoutManager();
    }

}
