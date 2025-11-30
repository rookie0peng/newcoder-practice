package com.boron.hard;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/30
 * </pre>
 */
public class MaxWater {

    private static void test1() {
        long result = new MaxWaterSolution().maxWater(new int[] {3,1,2,5,2,4});
        System.out.println("result: " + result);
    }

    private static void test2() {
        long result = new MaxWaterSolution().maxWater(new int[] {4,5,1,3,2});
        System.out.println("result: " + result);
    }

    public static void main(String[] args) {
        test1();
//        test2();
    }

}

class MaxWaterSolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * max water
     * @param arr int整型一维数组 the array
     * @return long长整型
     */
    public long maxWater (int[] arr) {
        // write code here
        int len = arr.length;
        // 记录区间的最大值的下标
//        int[][] dp = new int[len][len];
        int[] leftDp = new int[len];
        int[] rightDp = new int[len];
        int maxHeightIdx = 0;
        leftDp[0] = 0;
        rightDp[len - 1] = len - 1;
        for (int i = 1; i < len; i++) {
            if (arr[i] > arr[leftDp[i - 1]]) {
                leftDp[i] = i;
            } else {
                leftDp[i] = leftDp[i - 1];
            }
            if (arr[len - 1 - i] > arr[rightDp[len - 1 - i + 1]]) {
                rightDp[len - 1 - i] = len - 1 - i;
            } else {
                rightDp[len - 1 - i] = rightDp[len - 1 - i + 1];
            }
            if (arr[i] > arr[maxHeightIdx]) {
                maxHeightIdx = i;
            }
        }

        int idx = maxHeightIdx;
//        while (idx < len) {
//            int maxIdx =
//        }
        long water = 0;
        // max -> right
        while (idx < len - 1) {
            int nextIdx = rightDp[idx + 1];
            if (nextIdx == idx) {
                break;
            }
            if (nextIdx >= idx + 2) {
                long height = Math.min(arr[idx], arr[nextIdx]);
                long width = nextIdx - idx - 1;
                long reduce = 0;
                for (int i = idx + 1; i < nextIdx; i++) {
                    reduce += arr[i];
                }
                water = water + height * width - reduce;
            }
            idx = nextIdx;
        }
        // left <- max
        idx = maxHeightIdx;
        while (idx > 0) {
            int nextIdx = leftDp[idx - 1];
            if (nextIdx == idx) {
                break;
            }
            if (idx >= nextIdx + 2) {
                long height = Math.min(arr[idx], arr[nextIdx]);
                long width = idx - nextIdx - 1;
                long reduce = 0;
                for (int i = idx - 1; i > nextIdx; i--) {
                    reduce += arr[i];
                }
                water = water + height * width - reduce;
            }
            idx = nextIdx;
        }

        return water;
    }
}
