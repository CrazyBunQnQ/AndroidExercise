package com.exercise.bao.solution;

/**
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * For example:
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 * Created by Administrator on 2016/6/30.
 */
public class AddDigits {
    /**
     *
     * @param num
     * @return
     */
    public static int addDigits(int num) {
        int n = 0;

        while (num != 0) {
            n+= (num%10);
            num/=10;
        }

        return n>9?addDigits(n):n;
    }
}
