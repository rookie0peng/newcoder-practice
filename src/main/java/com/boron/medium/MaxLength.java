package com.boron.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/29
 * </pre>
 */
public class MaxLength {

    private static void test1() {
        int result = new MaxLengthSolution().maxLength(new int[] {2, 3, 4, 5});
        System.out.println("result: " + result);
    }

    private static void test2() {
        int result = new MaxLengthSolution().maxLength(new int[] {2,2,3,4,3});
        System.out.println("result: " + result);
    }

    private static void test3() {
        int result = new MaxLengthSolution().maxLength(new int[] {9});
        System.out.println("result: " + result);
    }

    private static void test4() {
        int result = new MaxLengthSolution().maxLength(new int[] {1,2,3,1,2,3,2,2});
        System.out.println("result: " + result);
    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }
}

class MaxLengthSolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int maxLength (int[] arr) {
        // write code here
        int left = 0, right = 0;
        Map<Integer, Integer> mark = new HashMap<>();
        boolean unique = true;
        int maxLength = Integer.MIN_VALUE;
        while (left <= right && right < arr.length) {
            while (unique && right < arr.length) {
                int num = arr[right];
                int count = mark.getOrDefault(num, 0);
                mark.put(num, count + 1);
                if (count == 1) {
                    unique = false;
                    right++;
                    break;
                }
                if (right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                }
                right++;
            }
            while (!unique && left < arr.length) {
                int num = arr[left];
                int count = mark.getOrDefault(num, 0);
                mark.put(num, count - 1);
                if (count == 2) {
                    unique = true;
                    left++;
                    break;
                }
                left++;
            }

        }
        return maxLength;
    }
}
