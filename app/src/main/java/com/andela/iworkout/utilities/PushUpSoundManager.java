package com.andela.iworkout.utilities;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import com.andela.iworkout.R;

public class PushUpSoundManager {
    private int counter;
    private int popSound;
    private int tenSound;
    private int tadaSound;
    private float currentVolume;

    private boolean mute = false;
    private boolean loaded = false;

    private SoundPool sounds;

    public PushUpSoundManager(final Activity activity) {

        AudioManager audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVolume = actualVolume / maxVolume;
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            createSoundPool();
        } else {
            createDepricatedSoundPool();
        }

        sounds.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        popSound = sounds.load(activity, R.raw.pop, 1);
        tenSound = sounds.load(activity, R.raw.ten, 1);
        tadaSound = sounds.load(activity, R.raw.tada, 1);
        counter = 0;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void createSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        sounds = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    @SuppressWarnings("deprecation")
    protected void createDepricatedSoundPool() {
        sounds = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
    }

    public void muteSound() {
        mute = true;
    }

    public void unmuteSound() {
        mute = false;
    }

    public void completed() {
        playSound(tadaSound);
    }

    public void playSound() {
        if (counter != 0) {
            if (counter % 10 == 0) {
                playSound(tenSound);
                counter = 0;
            } else {
                playSound(popSound);
            }
        }
        counter++;
    }

    private void playSound(int song) {
        if (!mute && loaded) {
            sounds.play(song, currentVolume, currentVolume, 1, 0, 1f);
        }
    }
}
