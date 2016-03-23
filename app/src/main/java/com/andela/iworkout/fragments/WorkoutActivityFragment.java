package com.andela.iworkout.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andela.iworkout.R;
import com.andela.iworkout.activities.MyApplication;
import com.andela.iworkout.activities.WorkoutActivity;
import com.andela.iworkout.model.Day;
import com.andela.iworkout.managers.WorkoutManager;
import com.andela.iworkout.utilities.DateFormatter;
import com.andela.iworkout.utilities.MsgBox;
import com.andela.iworkout.utilities.OnTimerTickListener;
import com.andela.iworkout.utilities.PushUpListener;
import com.andela.iworkout.utilities.PushUpSoundManager;
import com.andela.iworkout.utilities.PushUpTracker;
import com.andela.iworkout.utilities.Settings;
import com.andela.iworkout.utilities.TimeCounter;

public class WorkoutActivityFragment extends Fragment {
    private static final int SECONDS = 1000;
    private static final int MINUTES = 60000;

    private TimeCounter timeCounter;
    private PushUpTracker pushUpTracker;
    private AlertDialog alertDialog;
    private int counter = 0;
    private int pushUpGoal = 0;
    private int timeTaken = 0;
    private boolean mode;
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

        mode = Settings.getPushUpMode(getContext());

        setTimeKeeper(view);

        if (!Settings.getFirstLaunch(getContext())) {
            Settings.saveFirstLaunch(getContext());
            alertDialog = MsgBox.show(getContext(), "Workout instruction", R.drawable.how_to_use, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            }, false);
            alertDialog.show();
        }
    }

    private void setPushUpManager() {
        View touchScreen = getActivity().findViewById(R.id.touchScreen);
        final TextView pushUpKeeper = (TextView) getActivity().findViewById(R.id.pushUpKeeper);

        pushUpTracker = new PushUpTracker(getContext(), touchScreen);
        pushUpGoal = getPushUpMode();
        if (pushUpGoal <= 0) {
            pushUpTracker.setListener(new PushUpListener() {
                @Override
                public void pushUp() {
                    pushUpKeeper.setText(String.valueOf(counter++));
                    getPushUpSoundManager().playSound();
                }

                @Override
                public void pushDown() {
                }
            });
        } else {
            pushUpKeeper.setText(String.valueOf(pushUpGoal));
            pushUpTracker.setListener(new PushUpListener() {
                @Override
                public void pushUp() {
                    counter++;
                    pushUpKeeper.setText(String.valueOf(pushUpGoal--));

                    if (pushUpGoal < 0) {
                        endPushupSession();
                    }
                    getPushUpSoundManager().playSound();
                }

                @Override
                public void pushDown() {
                }
            });
        }

    }

    private void setTimeKeeper(View view) {
        final TextView timeKeeper = (TextView) view.findViewById(R.id.timeKeeper);

        long timeTill = getTimeMode();
        if (timeTill <= 0) {
            timeCounter.countUp(SECONDS, new OnTimerTickListener() {
                @Override
                public void onTick(long millisecond) {
                    timeTaken++;
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
                    timeTaken++;
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
        timeTaken = 0;
        timeCounter.start();
        setPushUpManager();
    }

    public void stopWorkout() {
        cancelWorkout();

        savePushUp();

        displayDialog();

        getPushUpSoundManager().completed();
    }

    public void muteSound() {
        getPushUpSoundManager().muteSound();
    }

    public void unmuteSound() {
        getPushUpSoundManager().unmuteSound();
    }

    private void displayDialog() {
        alertDialog = MsgBox.show(getContext(), getString(R.string.congratulations), R.drawable.gift, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                getActivity().finish();
            }
        }, false);
        alertDialog.show();
    }

    private void savePushUp() {
        day.setThedate(DateFormatter.getReadableDate(System.currentTimeMillis()));
        day.setPushups(counter - 1);
        day.setTimeTaken(timeTaken);
        getWorkoutManager().savePushUps(day);
    }

    public void cancelWorkout() {
        timeCounter.stop();

        if (pushUpTracker != null) {
            pushUpTracker.disconnectPushUp();
        }
        getPushUpSoundManager().unmuteSound();
    }

    private void endPushupSession() {
        WorkoutActivity workoutActivity = (WorkoutActivity) getActivity();
        workoutActivity.pressButton();
    }

    private String getTime(long milliSeconds) {
        if (milliSeconds >= MINUTES) {
            return DateFormatter.getReadableTime(milliSeconds);
        }

        return DateFormatter.getReadableSeconds(milliSeconds);
    }

    private long getTimeMode() {
        if (!mode) {
            return Settings.getTime(getContext()) * MINUTES;
        }
        return 0;
    }

    private int getPushUpMode() {
        if (mode) {
            return Settings.getPushUps(getContext());
        }
        return 0;
    }

    private WorkoutManager getWorkoutManager() {
        return MyApplication.getWorkoutManager();
    }

    private PushUpSoundManager getPushUpSoundManager() {
        return MyApplication.getPushUpSoundManager(getActivity());
    }
}
