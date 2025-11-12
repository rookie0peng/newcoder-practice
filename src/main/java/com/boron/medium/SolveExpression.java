package com.boron.medium;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/11
 * </pre>
 */
public class SolveExpression {

    private static void test1() {
        int solve = new SolveExpressionSolution().solve("(2*(3-4))*5");
        System.out.println("result: " + solve);
    }

    // 3+2*3*4-1
    private static void test2() {
        int solve = new SolveExpressionSolution().solve("3+2*3*4-1");
        System.out.println("result: " + solve);
    }

    // 1-2-3
    private static void test3() {
        int solve = new SolveExpressionSolution().solve("1-2-3");
        System.out.println("result: " + solve);
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        ArrayList<Integer> collect = Stream.of(1, 2, 3).sorted().collect(Collectors.toCollection(ArrayList::new));
        int[] ints = {1, 2, 3};
        List<Integer> lists = Arrays.stream(ints).boxed().collect(Collectors.toList());
        String x = "123";
        char[] sArr = x.toCharArray();
//        List<Character> collect2 = x.chars().mapToObj(c -> (char) c).sorted().collect(Collectors.toList());
//        List<char[]> collect1 = x.toCharArray().;
//        Stream.of()
    }
}

class SolveExpressionSolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 返回表达式的值
     * @param s string字符串 待计算的表达式
     * @return int整型
     */
    public int solve (String s) {
        // write code here
        Stack<Integer> valQueue = new Stack<>();
        Stack<Character> opQueue = new Stack<> ();
        // queue.add(123);
        // +: 1, -: -1, *: 0
        int len = s.length();
        int value = 0;
        boolean hasVal = false;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                hasVal = true;
                value = value * 10 + c - '0';
            } else {
                if (hasVal) {
                    valQueue.add(value);
                    hasVal = false;
                    value = 0;
                }
                if (!opQueue.isEmpty() && opQueue.peek() == '*') {
                    if (valQueue.size() >= 2) {
                        int nun2 = valQueue.pop();
                        int nun1 = valQueue.pop();
                        char op = opQueue.pop();
                        int tmpRes = cal(nun1, nun2, op);
                        valQueue.add(tmpRes);
                    }
                }
                if (c == '(') {
                    opQueue.add('(');
                } else if (c == ')') {
                    int lastVal = valQueue.pop();
                    while(!opQueue.isEmpty() && opQueue.peek() != '(') {
                        char op = opQueue.pop();
                        int val = valQueue.pop();
                        if (!opQueue.isEmpty() && opQueue.peek() == '-') {
                            val = -val;
                            opQueue.pop();
                            opQueue.add('+');
                        }
                        lastVal = cal(val, lastVal, op);
                    }
                    opQueue.pop();
                    valQueue.add(lastVal);
                } else {
                    opQueue.add(c);
                }
            }
        }
        if (hasVal) {
            valQueue.add(value);
        }
        while (!opQueue.isEmpty()) {
            int nun2 = valQueue.pop();
            int nun1 = valQueue.pop();
            char op = opQueue.pop();
            if (!opQueue.isEmpty() && opQueue.peek() == '-') {
                nun1 = -nun1;
                opQueue.pop();
                opQueue.add('+');
            }
            int tmpRes = cal(nun1, nun2, op);
            valQueue.add(tmpRes);
        }
        return valQueue.pop();
    }


    private int cal(int val1, int val2, char op) {
        if (op == '*') {
            return val1 * val2;
        } else if (op == '+') {
            return val1 + val2;
        } else {
            return val1 - val2;
        }
    }
}
