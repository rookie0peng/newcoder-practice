package com.boron.medium;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/14
 * </pre>
 */
public class GetLongestPalindrome {


    private static void test1() {
        int x = new GetLongestPalindromeSolution().getLongestPalindrome("dbadddadbbaabbcbcddbabacbdccdcbaddccdaacdccbcdaadadccbdbabddaaddbddbdaddbaadbabcaabccbacaacdaddbaadbadbbccababaaccbbbcccbbdbbdacbcaacaddccbbdbbbdacdbbdccaaddcdbadabdcbabcabbccdbdabbbaabacabababcbbcdcdcbbdcdcadbcaadaddcb");
        System.out.println("result: " + x);
    }

    private static void test2() {
        int x = new GetLongestPalindromeSolution().getLongestPalindrome("ababa");
        System.out.println("result: " + x);
    }

    public static void main(String[] args) {
        test1();
//        test2();
    }

}

class GetLongestPalindromeSolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param A string字符串
     * @return int整型
     */
    public int getLongestPalindrome (String A) {
        // write code here
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (int i = 0; i < A.length(); i++) {
            sb.append(A.charAt(i));
            sb.append('#');
        }
        String x1 = sb.toString();
        int len = x1.length();
        int[] dp = new int[len];
        dp[0] = 0;
        int maxRight = 0;
        int maxCenter = 0;
        int maxR = 0;
        int maxRCenter = 0;
        for (int i = 1; i < len; i++) {
            int beginR = 0;
            if (maxRight > i) {
                int mirrorPoint = maxCenter - (i - maxCenter);
                beginR = Math.min(dp[mirrorPoint], maxRight - i);
            }
            while (i + beginR < len && i - beginR >= 0 && x1.charAt(i + beginR) == x1.charAt(i - beginR)) {
                beginR++;
            }
            dp[i] = beginR - 1;
            if (i + beginR - 1 > maxRight) {
                maxCenter = i;
                maxRight = i + beginR - 1;
            }
            if (dp[i] > maxR) {
                maxR = dp[i];
                maxRCenter = i;
            }
        }
        return maxR;
    }

    private boolean isPalindrome(int left, int right, String x) {
        while (left <= right) {
            if (x.charAt(left) == x.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
