package com.andela.iworkout.utilities;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

public class PushUpTracker {
    private static final int PUSH_DOWN = 0;
    private static final int PUSH_UP = 1;

    private SensorManager sensorManager;
    private Sensor sensor;
    private PushUpListener listener;
    private View view;

    public PushUpTracker(Context context) {
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    public PushUpTracker(Context context, View view) {
        this(context);
        this.view = view;
        this.view.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.pushUp();
        }
    };

    private SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            int value = (int) event.values[0];
            switch (value) {
                case PUSH_DOWN:
                    listener.pushDown();
                    break;
                case PUSH_UP:
                    listener.pushUp();
                    break;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public void disconnectPushUp() {
        sensorManager.unregisterListener(sensorListener, sensor);
        this.view.setOnClickListener(null);
    }

    public void setListener(PushUpListener listener) {
        this.listener = listener;
        sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
