package com.gy.wanandroidmix.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.gy.wanandroidmix.R;
import com.gy.wanandroidmix.ui.base.MyBaseActivity;

public class TestActivity extends MyBaseActivity {

    public static final String KEY = "KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            int intExtra = intent.getIntExtra("ss", 0);
            Log.e(TAG, "onCreate: " + intExtra );
        }
        setContentView(R.layout.activity_test2);
    }
}
