package com.boron.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/14
 * </pre>
 */
public class RestoreIpAddresses {

    private static void test1() {
        ArrayList<String> strings = new RestoreIpAddressesSolution().restoreIpAddresses("25525522135");
        System.out.println("strings: " + strings);
    }

    private static void test2() {
        ArrayList<String> strings = new RestoreIpAddressesSolution().restoreIpAddresses("1111");
        System.out.println("strings: " + strings);
    }

    private static void test3() {
        ArrayList<String> strings = new RestoreIpAddressesSolution().restoreIpAddresses("0000");
        System.out.println("strings: " + strings);
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}

class RestoreIpAddressesSolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param s string字符串
     * @return string字符串ArrayList
     */
    public ArrayList<String> restoreIpAddresses (String s) {
        // write code here
        this.s = s;
        List<Integer> cutPoints = new ArrayList<> ();
        ArrayList<String> results = new ArrayList<> ();
        dfs(4, 0, cutPoints, results);
        return results;
    }

    private String s;

    private void dfs(int leftNum, int lastPoint, List<Integer> cutPoints, List<String> results) {
        if (leftNum == 0) {
            if (lastPoint == s.length()) {
                StringBuilder sb = new StringBuilder();
                int prePoint = 0;
                for (int point : cutPoints) {
                    sb.append(s, prePoint, point);
                    prePoint = point;
                    sb.append('.');
                }
                sb.deleteCharAt(sb.length() - 1);
                results.add(sb.toString());

            }
            return;
        }
        if (s.length() - lastPoint < leftNum) {
            return;
        }
//        int lastPoint = cutPoints.isEmpty() ? 0 : cutPoints.get(cutPoints.size() - 1);
//        if (s.charAt(lastPoint) == '0') {
//            return;
//        }
        for (int i = lastPoint + 1; i <= s.length(); i++) {
            if (s.charAt(lastPoint) == '0' && i > lastPoint + 1) {
                return;
            }
            if (Integer.parseInt(s.substring(lastPoint, i)) > 255) {
                return;
            }
            cutPoints.add(i);
            dfs(leftNum - 1, i, cutPoints, results);
            cutPoints.remove(cutPoints.size() - 1);
        }
    }
}
