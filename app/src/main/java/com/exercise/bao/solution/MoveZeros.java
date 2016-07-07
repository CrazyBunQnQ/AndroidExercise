package com.exercise.bao.solution;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 *
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 *
 * Created by CrazyBun on 2016/7/7.
 */
public class MoveZeros {
    public static void moveZeroes(int[] nums) {
        int numOfZeros = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i + numOfZeros == length) {
                for (int j = 0; j < numOfZeros; j++) {
                    nums[i+j] = 0;
                }
                return;
            }
            while (nums[i + numOfZeros] == 0) {
                numOfZeros = length<2? numOfZeros: numOfZeros+1;
                if (i + numOfZeros >= length - 1) {
                    break;
                }
            }
            nums[i] = i + numOfZeros >= length? nums[i]: nums[i + numOfZeros];
        }
    }
}
