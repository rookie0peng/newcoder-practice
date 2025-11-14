package com.boron.medium;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/14
 * </pre>
 */
public class LIS {

    private static void test1() {
        int lis = new LISSolution().LIS(new int[]{1, 6, 4, 7, 5, 3, 2});
        System.out.printf("result: %s\n", lis);
    }

    private static void test2() {
        int lis = new LISSolution().LIS(new int[]{6,3,1,5,2,3,7});
        System.out.printf("result: %s\n", lis);
    }

    public static void main(String[] args) {
//        test1();
        test2();
    }
}

class LISSolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 给定数组的最长严格上升子序列的长度。
     * @param arr int整型一维数组 给定的数组
     * @return int整型
     */
    public int LIS (int[] arr) {
        // write code here
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        int max = 0;
        for (int i = 1; i <= len; i++) {
            int tmpMax = 0;
            for (int j = 1; j < i; j++) {
                if (arr[i - 1] > arr[j - 1]) {
                    tmpMax = Math.max(dp[j] + 1, tmpMax);
                }
            }
            dp[i] = tmpMax;
            max = Math.max(max, tmpMax);
        }
        return max == 0 ? 0 : max + 1;
    }
}
