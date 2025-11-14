package com.boron.easy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/13
 * </pre>
 */
public class MinCostClimbingStairs {

    private static void test1() {
        MinCostClimbingStairsSolution solution = new MinCostClimbingStairsSolution();
        int i = solution.minCostClimbingStairs(new int[]{2, 5, 20});
        System.out.println("result = " + i);
    }

    public static void main(String[] args) {
        test1();
    }


}

class MinCostClimbingStairsSolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param cost int整型一维数组
     * @return int整型
     */
    public int minCostClimbingStairs (int[] cost) {
        // write code here
        int[] dp = new int[cost.length + 1];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[cost.length];
    }

    private int len;
    private int[] cost;

    private void dfs(int i, int hasCosted, AtomicInteger result) {
        if (i >= len) {
            if (hasCosted <= result.get()) {
                result.set(hasCosted);
            }
            return;
        }
        int nextCosted = hasCosted + cost[i];
        dfs(i + 1, nextCosted, result);
        dfs(i + 2, nextCosted, result);
    }
}
