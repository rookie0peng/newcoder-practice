package com.boron.easy;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/14
 * </pre>
 */
public class FindGreatestSumOfSubArray {


    private static void test1() {
        int i = new FindGreatestSumOfSubArraySolution().FindGreatestSumOfSubArray(new int[]{1, -2, 3, 10, -4, 7, 2, -5});
        System.out.println("result: " + i);
    }

    public static void main(String[] args) {
        test1();
    }
}

class FindGreatestSumOfSubArraySolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param array int整型一维数组
     * @return int整型
     */
    public int FindGreatestSumOfSubArray (int[] array) {
        // write code here
        int[] dp = new int[array.length + 1];
        dp[0] = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < dp.length; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = array[i - 1];
            } else {
                dp[i] = dp[i - 1] + array[i - 1];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
