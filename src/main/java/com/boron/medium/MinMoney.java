package com.boron.medium;

import java.util.Arrays;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/14
 * </pre>
 */
public class MinMoney {
}

class MinMoneySolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 最少货币数
     * @param arr int整型一维数组 the array
     * @param aim int整型 the target
     * @return int整型
     */
    public int minMoney (int[] arr, int aim) {
        // write code here
        if (arr == null || aim == 0) {
            return 0;
        }
        Arrays.sort(arr);
        int len = arr.length;
        int[] dp = new int[aim + 1];
        dp[0] = 0;
        for (int i = 1; i <= aim; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; i < len; j++) {
                int need = i - arr[j];
                if (need < 0) {
                    break;
                } else {
                    if (dp[need] == Integer.MAX_VALUE) {
                        continue;
                    } else {
                        min = Math.min(dp[need] + 1, min);
                    }
                }
            }
            dp[i] = min;
        }

        return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
    }
}
