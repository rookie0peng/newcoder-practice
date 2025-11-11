package com.boron.hard;

import java.util.*;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/10
 * </pre>
 */
public class SerializeBinaryTree {

    private static void test1() {
        SerializeBinaryTreeSolution.TreeNode node1 = new SerializeBinaryTreeSolution.TreeNode(1);
        SerializeBinaryTreeSolution.TreeNode node2 = new SerializeBinaryTreeSolution.TreeNode(2);
        SerializeBinaryTreeSolution.TreeNode node3 = new SerializeBinaryTreeSolution.TreeNode(3);
        SerializeBinaryTreeSolution.TreeNode node6 = new SerializeBinaryTreeSolution.TreeNode(6);
        SerializeBinaryTreeSolution.TreeNode node7 = new SerializeBinaryTreeSolution.TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node6;
        node3.right = node7;

        String serialize = new SerializeBinaryTreeSolution().Serialize(node1);
        SerializeBinaryTreeSolution.TreeNode deserialize = new SerializeBinaryTreeSolution().Deserialize(serialize);
        System.out.println("over");
    }

    private static void test2() {
        SerializeBinaryTreeSolution.TreeNode node1 = new SerializeBinaryTreeSolution.TreeNode(8);
        SerializeBinaryTreeSolution.TreeNode node2 = new SerializeBinaryTreeSolution.TreeNode(6);
        SerializeBinaryTreeSolution.TreeNode node3 = new SerializeBinaryTreeSolution.TreeNode(10);
        SerializeBinaryTreeSolution.TreeNode node4 = new SerializeBinaryTreeSolution.TreeNode(5);
        SerializeBinaryTreeSolution.TreeNode node5 = new SerializeBinaryTreeSolution.TreeNode(7);
        SerializeBinaryTreeSolution.TreeNode node6 = new SerializeBinaryTreeSolution.TreeNode(9);
        SerializeBinaryTreeSolution.TreeNode node7 = new SerializeBinaryTreeSolution.TreeNode(11);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        String serialize = new SerializeBinaryTreeSolution().Serialize(node1);
        SerializeBinaryTreeSolution.TreeNode deserialize = new SerializeBinaryTreeSolution().Deserialize(serialize);
        System.out.println("over");
    }

    public static void main(String[] args) {
//        test1();
        test2();

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
    }

}

class SerializeBinaryTreeSolution {

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    String Serialize(TreeNode root) {
        if (root == null) {
            return "{}";
        }
        Deque<TreeNode> queue = new LinkedList<>();
        List<TreeNode> nextNodes = new ArrayList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append('#');
            } else {
                sb.append(node.val);
                nextNodes.add(node.left);
                nextNodes.add(node.right);
            }
            sb.append(',');
            if (queue.isEmpty()) {
                queue.addAll(nextNodes);
                nextNodes.clear();
            }
        }
        sb.append('}');
        return sb.toString();
    }

    TreeNode Deserialize(String str) {
        System.out.println("str: " + str);
        if (str.length() == 2) {
            return null;
        }
        final int len = str.length();
        int value = 0;
        List<List<TreeNode>> results = new ArrayList<> ();
        List<TreeNode> floorResults = new ArrayList<> ();
        int idx = 0;
        boolean isNull = false;
        boolean isLeft = true;
        for (int i = 1; i < len - 1; i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                value = value * 10 + c - '0';
            } else if (c == '#') {
                isNull = true;
            } else {
                TreeNode node = null;
                if (!isNull) {
                    node = new TreeNode(value);
                }
                if (node != null) {
                    floorResults.add(node);
                }
                value = 0;
                isNull = false;
                if (!results.isEmpty()) {
                    TreeNode parent = results.get(results.size() - 1).get(idx);
                    if (isLeft) {
                        parent.left = node;
                    } else {
                        parent.right = node;
                        idx++;
                    }
                    isLeft = !isLeft;
                    if (idx >= results.get(results.size() - 1).size()) {
                        idx = 0;
                        results.add(floorResults);
                        floorResults = new ArrayList<> ();
                    }
                } else {
                    results.add(floorResults);
                    floorResults = new ArrayList<> ();
                }

            }
        }
        return results.get(0).get(0);
    }
}
