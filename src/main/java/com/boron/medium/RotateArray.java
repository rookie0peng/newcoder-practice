package com.boron.medium;

/**
 * <pre>
 *  @description:旋转数组
 *  @author: BruceBoron
 *  @date: 2025/12/1
 * </pre>
 */
public class RotateArray {

    private static void test1() {
        int[] solve = new RotateArraySolution().solve(6, 2, new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("over");
    }

    private static void test2() {
        int[] solve = new RotateArraySolution().solve(4, 0, new int[]{1,2,3,4});
        System.out.println("over");
    }

    public static void main(String[] args) {
//        test1();
        test2();
    }

}

class RotateArraySolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 旋转数组
     * @param n int整型 数组长度
     * @param m int整型 右移距离
     * @param a int整型一维数组 给定数组
     * @return int整型一维数组
     */
    public int[] solve (int n, int m, int[] a) {
        // write code here
        if (m % n == 0) {
            return a;
        }
        m = m % n;
        if (n % m == 0) {
            int step = n / m;
            for (int i = 0; i < m; i++) {
                int count = 0;
                int idx = i, nextIdx = 0;
                int val = a[idx], nextVal = -1;
                while (count < step) {
                    nextIdx = (idx + m) % n;
                    nextVal = a[nextIdx];
                    a[nextIdx] = val;
                    val = nextVal;
                    idx = nextIdx;
                    count++;
                }
            }
        } else {
            int count = 0;
            int idx = 0, nextIdx = 0;
            int val = a[idx], nextVal = -1;
            while (count < n) {
                nextIdx = (idx + m) % n;
                nextVal = a[nextIdx];
                a[nextIdx] = val;
                val = nextVal;
                idx = nextIdx;
                count++;
            }
        }


        return a;
    }
}
