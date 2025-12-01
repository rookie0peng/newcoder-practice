package com.boron.hard;

import java.util.*;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/12/2
 * </pre>
 */
public class DesignLFU {


    private static void test1() {
        int[] results = new DesignLFUSolution().LFU(new int[][]{{1,1,1}, {1,2,2}, {1,3,2}, {1,2,4}, {1,3,5}, {2,2}, {1,4,4}, {2,1}}, 3);
        System.out.println("over");
    }

    private static void test2() {
        int[] results = new DesignLFUSolution().LFU(new int[][]{{1,1,1}, {1,2,2}, {1,1,2}, {1,2,4}, {1,3,5}, {1,3,6}, {1,4,6}, {2,2}, {2,1}}, 3);
        System.out.println("over");
    }

    public static void main(String[] args) {
        test1();
//        test2();
    }

}

class DesignLFUSolution {

    private Map<Integer, Node> map;

    private Map<Integer, LinkedHashSet<Node>> frequencyMap;

    private int minFreq = Integer.MAX_VALUE;

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * lfu design
     * @param operators int整型二维数组 ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LFU (int[][] operators, int k) {
        // write code here
        map = new HashMap<>(k);
        frequencyMap = new HashMap<>();
        List<Integer> results = new ArrayList<>();
        for (int[] operator : operators) {
            if (operator[0] == 1) {
                if (map.containsKey(operator[1])) {
                    Node node = map.get(operator[1]);
                    node.value = operator[2];
                    removeFreq(node);
                    node.count++;
                    addFreq(node);
                } else {
                    if (map.size() == k) {
                        LinkedHashSet<Node> nodes = frequencyMap.get(minFreq);
                        Node removeNode = nodes.iterator().next();
                        removeFreq(removeNode);
                    }
                    Node node = new Node(operator[1], operator[2]);
                    map.put(operator[1], node);
                    addFreq(node);
                }
            } else {
                if (map.containsKey(operator[1])) {
                    Node node = map.get(operator[1]);
                    removeFreq(node);
                    node.count++;
                    addFreq(node);

                    results.add(node.value);
                } else {
                    results.add(-1);
                }
            }
        }
        return results.stream().mapToInt(Integer::intValue).toArray();

    }

    static class Node {
        int key;
        int value;
        int count = 1;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return key == node.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }



    private void removeFreq(Node node) {
        LinkedHashSet<Node> nodes = frequencyMap.get(node.count);
        nodes.remove(node);
        map.remove(node.key);
        if (nodes.isEmpty()) {
            frequencyMap.remove(node.count);
            if (node.count == minFreq) {
                minFreq = Integer.MAX_VALUE;
            }
        }

    }

    private void addFreq(Node node) {
        if (!map.containsKey(node.key)) {
            map.put(node.key, node);
        }
        LinkedHashSet<Node> nodes1 = frequencyMap.computeIfAbsent(node.count, x -> new LinkedHashSet<>());
        nodes1.add(node);
        if (node.count < minFreq) {
            minFreq = node.count;
        }
    }
}
