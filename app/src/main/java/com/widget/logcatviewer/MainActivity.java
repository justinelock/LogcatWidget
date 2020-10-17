package com.widget.logcatviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.widget.logcatviewer.ui.FloatingLogActivity;
import com.widget.logcatviewer.ui.LogcatControlActivity;
import com.widget.logviewer.LogcatActivity;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Logcat(View view) {
        Log.e(TAG, "Logcat");
        startActivity(new Intent(this, LogcatActivity.class));
    }

    public void FloatingLogcat(View view) {
        Log.e(TAG, "FloatingLogcat");
        startActivity(new Intent(this, FloatingLogActivity.class));
    }

    public void LogcatControl(View view) {
        Log.e(TAG, "LogcatControl");
        startActivity(new Intent(this, LogcatControlActivity.class));

    }
}
