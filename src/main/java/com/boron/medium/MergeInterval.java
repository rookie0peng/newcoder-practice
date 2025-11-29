package com.boron.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/29
 * </pre>
 */
public class MergeInterval {
}

class MergeIntervalSolution {

    static class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param intervals Interval类ArrayList
     * @return Interval类ArrayList
     */
    public ArrayList<Interval> merge (ArrayList<Interval> intervals) {
        // write code here
        // 先排序，后合并
        if (intervals == null || intervals.isEmpty()) {
            return new ArrayList<>();
        }
        List<Interval> sortedIntervals = intervals.stream().sorted(Comparator.comparingInt(x -> x.start)).collect(Collectors.toList());
        ArrayList<Interval> mergedIntervals = new ArrayList<>();
        mergedIntervals.add(sortedIntervals.get(0));
        for (int i = 1; i < sortedIntervals.size(); i++) {
            Interval interval = sortedIntervals.get(i);
            Interval lastInterval = mergedIntervals.get(mergedIntervals.size() - 1);
            if (interval.start <= lastInterval.end) {
                lastInterval.end = Math.max(interval.end, lastInterval.end);
            } else {
                mergedIntervals.add(interval);
            }
        }
        return mergedIntervals;
    }
}
