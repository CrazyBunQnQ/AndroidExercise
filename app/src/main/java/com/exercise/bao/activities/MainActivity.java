package com.exercise.bao.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.exercise.bao.utils.*;

public class MainActivity extends Activity {

    private static final int OFFSET = 500;
    private Context context;

    public MainActivity() {
        this.context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(OFFSET);
                    HomeActivity.lanuch(context);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        TimeUtil.setCurrentTime();
//        TestCode();
//        Toast.makeText(this,"耗时" + TimeUtil.runTime() + "毫秒",Toast.LENGTH_LONG).show();
    }

    private void TestCode() {

    }
}
