package com.boron.hard;

/**
 * <pre>
 *  @description: 贪心法分糖果
 *  @author: BruceBoron
 *  @date: 2025/12/1
 * </pre>
 */
public class Candy {

    private static void test1() {
        int result = new CandySolution().candy(new int[] {1,1,2});
        System.out.println("result: " + result);
    }

    private static void test2() {
        int result = new CandySolution().candy(new int[] {1, 1, 1});
        System.out.println("result: " + result);
    }

    private static void test3() {
        int result = new CandySolution().candy(new int[] {1,2,3,3,3,2,1,1});
        System.out.println("result: " + result);
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }
}

class CandySolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * pick candy
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int candy (int[] arr) {
        // write code here
        this.arr = arr;
        this.mark = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            // 不重复标记
            if (mark[i] != 0) {
                continue;
            }
            if (i > 0 && i < arr.length - 1) {
                if (arr[i] <= arr[i + 1] && arr[i] <= arr[i - 1]) {
                    markAround(i);
                }
            } else if ((i == 0 && arr[i] <= arr[i + 1]) || (i == arr.length - 1 && arr[i] <= arr[i - 1])) {
                markAround(i);
            }
        }
        int res = 0;
        for (int x : mark) {
            res += x;
        }
        return res;
    }

    private int[] arr;
    private int[] mark;

    private void markAround(int idx) {
        mark[idx] = 1;
        // to right
        int idxRight = idx + 1;
        while (idxRight < arr.length) {
            if (arr[idxRight] > arr[idxRight - 1]) {
                if (mark[idxRight] == 0) {
                    mark[idxRight] = mark[idxRight - 1] + 1;
                } else {
                    mark[idxRight] = Math.max(mark[idxRight], mark[idxRight - 1] + 1);
                }

            } else {
                break;
            }
            idxRight++;
        }
        // to left
        int idxLeft = idx - 1;
        while (idxLeft >= 0) {
            if (arr[idxLeft] > arr[idxLeft + 1]) {
                if (mark[idxLeft] == 0) {
                    mark[idxLeft] = mark[idxLeft + 1] + 1;
                } else {
                    mark[idxLeft] = Math.max(mark[idxLeft], mark[idxLeft + 1] + 1);
                }
            } else {
                break;
            }
            idxLeft--;
        }
    }
}
