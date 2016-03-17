package com.andela.iworkout.utilities;

import android.os.CountDownTimer;

public class TimeCounter {
    private static final int COUNT_DOWN = 0;
    private static final int COUNT_UP = 1;

    private CountDownTimer countDownTimer;
    private CountUpTimer countUpTimer;
    private int type;

    public TimeCounter() {
    }

    public TimeCounter countUp(int interval, final OnTimerTickListener listener) {
        countUpTimer = new CountUpTimer(interval) {
            @Override
            public void onTick(long elapsedTime) {
                listener.onTick(elapsedTime);
            }
        };

        type = COUNT_UP;
        return this;
    }

    public TimeCounter countDown(long endTime, int interval, final OnTimerTickListener listener) {
        countDownTimer = new CountDownTimer(endTime, interval) {

            @Override
            public void onTick(long millisUntilFinished) {
                listener.onTick(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                listener.onFinish();
            }
        };

        type = COUNT_DOWN;
        return this;
    }

    public void start() {
        switch (type) {
            case COUNT_UP:
                countUpTimer.start();
                break;
            case COUNT_DOWN:
                countDownTimer.start();
                break;
        }
    }

    public void stop() {
        switch (type) {
            case COUNT_UP:
                countUpTimer.stop();
                break;
            case COUNT_DOWN:
                countDownTimer.cancel();
                break;
        }
    }
}
