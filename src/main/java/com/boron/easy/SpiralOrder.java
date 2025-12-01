package com.boron.easy;

import java.util.ArrayList;

/**
 * <pre>
 *  @description: 螺旋读取
 *  @author: BruceBoron
 *  @date: 2025/12/1
 * </pre>
 */
public class SpiralOrder {

    private static void test1() {
        ArrayList<Integer> results = new SpiralOrderSolution().spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        System.out.println("results: " + results);
    }

    private static void test2() {
        ArrayList<Integer> results = new SpiralOrderSolution().spiralOrder(new int[][]{{1, 2, 3}});
        System.out.println("results: " + results);
    }

    public static void main(String[] args) {
//        test1();
        test2();
    }
}

class SpiralOrderSolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param matrix int整型二维数组
     * @return int整型ArrayList
     */
    public ArrayList<Integer> spiralOrder (int[][] matrix) {
        // write code here
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }

        int m = matrix.length, n = matrix[0].length;
        int[][] direction = new int[][] {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        ArrayList<Integer> results = new ArrayList<>();
        int i = 0, j = 0;
        int nextI = 0, nextJ = 0;
        int[] dir = direction[0];
        int dirIdx = 0;
        for (int x = 0; x < m * n; x++) {
            dir = direction[dirIdx];
            results.add(matrix[i][j]);
            matrix[i][j] = 101;
            nextI = i + dir[0];
            nextJ = j + dir[1];
            if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || matrix[nextI][nextJ] == 101) {
                dirIdx = (dirIdx + 1) % direction.length;
                dir = direction[dirIdx];
                nextI = i + dir[0];
                nextJ = j + dir[1];
                if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || matrix[nextI][nextJ] == 101) {
                    break;
                }
            }
            i = nextI;
            j = nextJ;
        }
        return results;
    }
}
