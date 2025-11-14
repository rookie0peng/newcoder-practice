package com.boron.hard;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/13
 * </pre>
 */
public class NQueen {
}

class NQueenSolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param n int整型 the n
     * @return int整型
     */
    public int Nqueen (int n) {
        // write code here
        if (n == 1) {
            return 1;
        }
        if (n == 2 || n == 3) {
            return 0;
        }
        AtomicInteger result = new AtomicInteger(0);
        dfs(0, n, result);
        return result.get();
    }

    // 竖线，x = x1
    private Set<Integer> column = new HashSet<>();
    // 斜线，y = x + k
    private Set<Integer> diagonal1 = new HashSet<> ();
    // 斜线，y = -x + k
    private Set<Integer> diagonal2 = new HashSet<> ();

    private void dfs(int i, int n, AtomicInteger result) {
        if (i == n) {
            result.addAndGet(1);
            return;
        }
        for (int j = 0; j < n; j++) {
            if (column.contains(j) || diagonal1.contains(j - i) || diagonal2.contains(j + i)) {
                continue;
            }
            column.add(j);
            diagonal1.add(j - i);
            diagonal2.add(j + i);
            // choosed.add(new int[] {i, j});
            dfs(i + 1, n, result);
            // choosed.remove(choosed.size() - 1);
            column.remove(j);
            diagonal1.remove(j - i);
            diagonal2.remove(j + i);
        }
    }
}
