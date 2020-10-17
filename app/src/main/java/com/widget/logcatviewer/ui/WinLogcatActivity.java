package com.widget.logcatviewer.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.widget.logcatviewer.R;
import com.widget.logviewer.LogcatActivity;

public class WinLogcatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_logcat);
        LogcatActivity.launch(WinLogcatActivity.this);
    }
}
