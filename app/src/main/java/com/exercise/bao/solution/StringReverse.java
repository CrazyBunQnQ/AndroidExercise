package com.exercise.bao.solution;

/**
 * Created by Administrator on 2016/6/30.
 * 用来反转字符串
 */
public class StringReverse {
    /**
     * 反转字符串
     * @param s
     * @param speed 默认为最快速，0：最慢，1较快
     * @return
     */
    public String stringReverse(String s, int speed) {
        switch (speed) {
            case 0:
                return stringReverseSlow(s);
            case 1:
                return stringReverseFaster(s);
            default:
                return stringReverseFastest(s);
        }
    }

    /**
     * 反转字符串，速度极慢
     * @param s
     * @return
     */
    public String stringReverseSlow(String s) {
        String newS = "";
        for (int i = 0; i < s.length(); i++) {
            newS += s.charAt(s.length() - 1 - i);
        }
        return newS;
    }

    /**
     * 反转字符串，系统内部方法，很快
     * @param s
     * @return
     */
    public String stringReverseFaster(String s) {
        return new StringBuffer(s).reverse().toString();
    }

    /**
     * 反转字符串，速度比上面的方法快一倍，时间复杂度折半
     * @param s
     * @return
     */
    public String stringReverseFastest(String s) {
        char[] word = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i<j) {
            char temp = word[i];
            word[i] = word[j];
            word[j] = temp;
            i++;
            j--;
        }
        return new String(word);
    }
}
