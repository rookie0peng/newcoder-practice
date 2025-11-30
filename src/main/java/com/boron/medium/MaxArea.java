package com.boron.medium;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/29
 * </pre>
 */
public class MaxArea {

    private static void test1() {
        int result = new MaxAreaSolution().maxArea(new int[] {1,7,3,2,4,5,8,2,7});
        System.out.println("result: " + result);
    }

    private static void test2() {
        int result = new MaxAreaSolution().maxArea(new int[] {2,2});
        System.out.println("result: " + result);
    }

    private static void test3() {
        int result = new MaxAreaSolution().maxArea(new int[] {5,4,3,2,1,5});
        System.out.println("result: " + result);
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }
}

class MaxAreaSolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param height int整型一维数组
     * @return int整型
     */
    public int maxArea (int[] height) {
        // write code here
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int h = Math.min(height[right], height[left]);
            maxArea = Math.max(maxArea, h * (right - left));
            if (height[right] > height[left]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
