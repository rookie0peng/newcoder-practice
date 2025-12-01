package com.boron.hard;

import java.util.*;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/12/1
 * </pre>
 */
public class DesignLRU {

    public static void main(String[] args) {
        Set<Integer> countSet = new LinkedHashSet<>();
        countSet.add(1);
        countSet.add(2);
        countSet.add(3);
        countSet.remove(2);
        countSet.add(2);
        Integer next = countSet.iterator().next();

        System.out.println(countSet);
        System.out.println(next);
    }
}

class DesignLRUSolution1 {
    static class Node {
        private int key;
        private int value;
        private Node pre;
        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Map<Integer, Node> map;
    private final int capacity;
    // -1
    private final Node headSentry;
    // -2
    private final Node tailSentry;

    public DesignLRUSolution1(int capacity) {
        // write code here
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.headSentry = new Node(-1, -1);
        this.tailSentry = new Node(-2, -2);
        this.headSentry.next = this.tailSentry;
        this.tailSentry.pre = this.headSentry;
    }

    public int get(int key) {
        // write code here
        int result = -1;
        if (this.map.containsKey(key)) {
            Node node = this.map.get(key);
            result = node.value;
            moveExistsToHead(node);
        }
        return result;
    }

    public void set(int key, int value) {
        // write code here
//        countSet.add(key)
        if (this.map.containsKey(key)) {
            Node node = this.map.get(key);
            node.value = value;
            moveExistsToHead(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            addNode(node);
            if (map.size() > this.capacity) {
                int removeKey = removeTailNode();
                this.map.remove(removeKey);
            }
        }
    }

    private void addNode(Node node) {

        Node newPre = this.headSentry;
        Node newNext = this.headSentry.next;

        // 插入新节点
        newPre.next = node;
        node.pre = newPre;

        node.next = newNext;
        newNext.pre = node;
    }

    private int removeTailNode() {
        Node removeNode = this.tailSentry.pre;

        if (removeNode == this.headSentry) {
            return -1;
        }

        Node oldPre = removeNode.pre;
        Node oldNext = removeNode.next;

        oldPre.next = oldNext;
        oldNext.pre = oldPre;

        return removeNode.key;
    }

    private void moveExistsToHead(Node node) {
        Node oldPre = node.pre;
        Node oldNext = node.next;

        // 挪出旧节点
        oldPre.next = oldNext;
        oldNext.pre = oldPre;

        Node newPre = this.headSentry;
        Node newNext = this.headSentry.next;

        // 插入新节点
        newPre.next = node;
        node.pre = newPre;

        node.next = newNext;
        newNext.pre = node;
    }
}


class DesignLRUSolution {

    private Map<Integer, Integer> map;
    private Set<Integer> countSet = new LinkedHashSet<>();
    private int capacity;


    public DesignLRUSolution(int capacity) {
        // write code here
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
    }

    public int get(int key) {
        // write code here
        int result = -1;
        if (this.map.containsKey(key)) {
            result = this.map.get(key);
            this.countSet.remove(key);
            this.countSet.add(key);
        }
        return result;
    }

    public void set(int key, int value) {
        // write code here
//        countSet.add(key)
        this.map.put(key, value);
        if (map.size() <= capacity) {
            this.countSet.remove(key);
            this.countSet.add(key);
        } else {
            Integer next = this.countSet.iterator().next();
            this.countSet.remove(next);
            this.map.remove(next);
            this.countSet.add(key);
        }
    }

}

