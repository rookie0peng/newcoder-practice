package com.boron.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <pre>
 *  @description: 主持人调度
 *  @author: BruceBoron
 *  @date: 2025/12/1
 * </pre>
 */
public class MinmumNumberOfHost {
}

class MinmumNumberOfHostSolution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 计算成功举办活动需要多少名主持人
     * @param n int整型 有n个活动
     * @param startEnd int整型二维数组 startEnd[i][0]用于表示第i个活动的开始时间，startEnd[i][1]表示第i个活动的结束时间
     * @return int整型
     */
    public int minmumNumberOfHost (int n, int[][] startEnd) {
        // write code here
        List<int[]> sortedActivities = Arrays.stream(startEnd).sorted(Comparator.comparingInt(o -> o[0])).collect(Collectors.toList());
        Queue<Integer> queue = new PriorityQueue<>(Integer::compare);
        int max = 0;
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && queue.element() <= sortedActivities.get(i)[0]) {
                queue.poll();
            }
            queue.add(sortedActivities.get(i)[1]);
            max = Math.max(max, queue.size());
        }
        return max;
    }
}
