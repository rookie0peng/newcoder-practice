package com.boron.hard;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/28
 * </pre>
 */
public class EditDistance {

    private static void test1() {
        int result = new EditDistanceSolution().editDistance("nowcoder", "new");
        System.out.println("res: " + result);
    }

    private static void test2() {
        int result = new EditDistanceSolution().editDistance("intention", "execution");
        System.out.println("res: " + result);
    }

    private static void test3() {
        int result = new EditDistanceSolution().editDistance("now", "nowcoder");
        System.out.println("res: " + result);
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

}

class EditDistanceSolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param str1 string字符串
     * @param str2 string字符串
     * @return int整型
     */
    public int editDistance (String str1, String str2) {
        // write code here
        if (str1.length() > str2.length()) {
            return editDistance(str2, str1);
        }
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m][n];
        dp[0][0] = str1.charAt(0) == str2.charAt(0) ? 0 : 1;
        for (int i = 1; i < n; i++) {
            if (str1.charAt(0) == str2.charAt(i) && dp[0][i - 1] == i) {
                dp[0][i] = dp[0][i - 1];
            } else {
                dp[0][i] = dp[0][i - 1] + 1;
            }
        }
        for (int i = 1; i < m; i++) {
            if (str1.charAt(i) == str2.charAt(0) && dp[i - 1][0] == i) {
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int min1 = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    dp[i][j] = Math.min(min1, dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
