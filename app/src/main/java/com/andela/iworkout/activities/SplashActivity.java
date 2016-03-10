package com.andela.iworkout.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.andela.iworkout.utilities.Launcher;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Launcher.launch(this, MainActivity.class);
        finish();
    }
}
