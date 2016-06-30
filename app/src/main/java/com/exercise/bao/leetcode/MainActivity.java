package com.exercise.bao.leetcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.exercise.bao.util.TimeUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimeUtil.setCurrentTime();
        TestCode();
        Toast.makeText(this,"耗时" + TimeUtil.runTime() + "毫秒",Toast.LENGTH_LONG).show();
    }

    private void TestCode() {
//        for (int i = 0; i<99999;i++) {
//            SumOfTwoIntegers.getSum2(50,60);
//        }

//        StringReverse.stringReverseSlow("")
    }
}
