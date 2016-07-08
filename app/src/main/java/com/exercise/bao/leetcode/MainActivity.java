package com.exercise.bao.leetcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.exercise.bao.solution.IntersectionOfTwoArrays;
import com.exercise.bao.solution.MoveZeros;
import com.exercise.bao.solution.StringReverse;
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
        int[][][] aaa = new int[][][] {{{},{}},
                {{1}, {1}},
                {{0, 1, 0},{0}}};
//                {0, 1, 0, 0, 1, 0, 3, 12},
//                {0, 1, 0, 3, 0, 1, 0, 12},
//                {0, 1, 0, 0, 1, 0, 0, 1, 0, 3, 12},
//                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 5}};
        for (int i = 0; i < aaa.length; i++) {
            IntersectionOfTwoArrays.intersection(aaa[i][0],aaa[i][1]);
        }

        Toast.makeText(this,"结果：aaa = " + aaa.toString(),Toast.LENGTH_LONG).show();
//        StringReverse.stringReverseSlow("")
    }
}
