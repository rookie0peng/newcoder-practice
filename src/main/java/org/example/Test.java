package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/10/31
 * </pre>
 */
public class Test {

    public static void main(String[] args) {
        List<Integer> results = new ArrayList<>();
//        dfs(root, results);
        int[] array = results.stream().mapToInt(Integer::intValue).toArray();
    }
}
