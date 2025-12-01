package com.boron.medium;

/**
 * <pre>
 *  @description: 旋转矩阵
 *  @author: BruceBoron
 *  @date: 2025/12/1
 * </pre>
 */
public class RotateMatrix {

    private static void test1() {
        int[][] ints = new RotateMatrixSolution().rotateMatrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 3);
        System.out.println("over");
    }

    private static void test2() {
        int[][] ints = new RotateMatrixSolution().rotateMatrix(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}, 4);
        System.out.println("over");
    }

    public static void main(String[] args) {
        test1();
//        test2();
    }
}

class RotateMatrixSolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param mat int整型二维数组
     * @param n int整型
     * @return int整型二维数组
     */
    public int[][] rotateMatrix (int[][] mat, int n) {
        // write code here

        for (int i = 0; i < n / 2; i++) {
            int len = mat[i].length - i * 2;
            int maxIdx = i + len - 1;
            for (int j = 0; j < len - 1; j++) {
                int cache = mat[i][j + i];
                mat[i][j + i] = mat[maxIdx - j][i];
                mat[maxIdx - j][i] = mat[maxIdx][maxIdx - j];
                mat[maxIdx][maxIdx - j] = mat[j + i][maxIdx];
                mat[j + i][maxIdx] = cache;
            }
        }
        return mat;
    }
}
