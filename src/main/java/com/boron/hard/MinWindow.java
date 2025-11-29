package com.boron.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/29
 * </pre>
 */
public class MinWindow {

    private static void test1() {

        String result = new MinWindowSolution().minWindow("XDOYEZODEYXNZ", "XYZ");
        System.out.println("result: " + result);
    }

    private static void test2() {

        String result = new MinWindowSolution().minWindow("abcAbA", "AA");
        System.out.println("result: " + result);
    }

    private static void test3() {

        String result = new MinWindowSolution().minWindow("a", "a");
        System.out.println("result: " + result);
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }
}

class MinWindowSolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param S string字符串
     * @param T string字符串
     * @return string字符串
     */
    public String minWindow (String S, String T) {
        // write code here
        Map<Character, Integer> baseMark = new HashMap<>();
        int allCount = T.length();
        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            int count = baseMark.getOrDefault(c, 0);
            baseMark.put(c, count + 1);
        }
        Map<Character, Integer> mark = new HashMap<>();
        int left = 0, right = 0;
        int count = 0;
        int minLength = Integer.MAX_VALUE;
        int minLeft = 0, minRight = 0;
        while (left <= right && right < S.length()) {
            while (count < allCount && right < S.length()) {
                char c = S.charAt(right);
                if (!baseMark.containsKey(c)) {
                    right++;
                    continue;
                }
                Integer cCount = mark.getOrDefault(c, 0);
                mark.put(c, cCount + 1);
                if (cCount + 1 <= baseMark.get(c)) {
                    count++;
                }
                right++;
            }
            if (count == allCount) {
                if (right - left < minLength) {
                    minLength = right - left;
                    minLeft = left;
                    minRight = right;
                }
            }
            while (count > allCount - 2 && left < S.length()) {
                if (count == allCount) {
                    if (right - left < minLength) {
                        minLength = right - left;
                        minLeft = left;
                        minRight = right;
                    }
                }
                char c = S.charAt(left);
                if (baseMark.containsKey(c)) {
                    int cCount = mark.getOrDefault(c, 0);
                    mark.put(c, cCount - 1);
                    if (cCount <= baseMark.get(c)) {
                        count--;
                    }
                    if (count == allCount - 2) {
                        mark.put(c, cCount);
                        count++;
                        break;
                    }
                }
                left++;
            }
        }

        if (minLength != Integer.MAX_VALUE) {
            return S.substring(minLeft, minRight);
        }
        return "";
    }
}
