package com.exercise.bao.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.exercise.bao.utils.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimeUtil.setCurrentTime();
        TestCode();
        Toast.makeText(this,"耗时" + TimeUtil.runTime() + "毫秒",Toast.LENGTH_LONG).show();
    }

    private void TestCode() {
        int[][][] aaa = new int[][][] {{{},{}},
                {{1}, {1}},
                {{0, 1, 0},{0}}};
//                {0, 1, 0, 0, 1, 0, 3, 12},
//                {0, 1, 0, 3, 0, 1, 0, 12},
//                {0, 1, 0, 0, 1, 0, 0, 1, 0, 3, 12},
//                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 5}};
//        for (int i = 0; i < aaa.length; i++) {
        int[] a = new int[] {4,5,7,8,9,12,19,21,22,23,35,40,41,51,60};
        int[] b = new int[] {2,5,7,9,21,23,24,31,35,41, 51};
        int[] c = ArrayUtil.merge(a, b);
//        }

        Toast.makeText(this,"结果：aaa = " + c.toString(),Toast.LENGTH_LONG).show();
//        StringReverse.stringReverseSlow("")
    }
}
