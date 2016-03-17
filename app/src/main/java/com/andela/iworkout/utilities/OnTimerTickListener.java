package com.andela.iworkout.utilities;


public interface OnTimerTickListener {
    void onTick(long millisecond);

    void onFinish();
}
