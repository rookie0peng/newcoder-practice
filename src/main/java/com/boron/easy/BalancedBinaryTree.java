package com.boron.easy;


import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/10
 * </pre>
 */
public class BalancedBinaryTree {

    private static void test1() {
        BalancedBinaryTreeSolution.TreeNode node1 = new BalancedBinaryTreeSolution.TreeNode(1);
        BalancedBinaryTreeSolution.TreeNode node2 = new BalancedBinaryTreeSolution.TreeNode(2);
        BalancedBinaryTreeSolution.TreeNode node3 = new BalancedBinaryTreeSolution.TreeNode(3);
        BalancedBinaryTreeSolution.TreeNode node4 = new BalancedBinaryTreeSolution.TreeNode(4);
        BalancedBinaryTreeSolution.TreeNode node5 = new BalancedBinaryTreeSolution.TreeNode(5);
        BalancedBinaryTreeSolution.TreeNode node6 = new BalancedBinaryTreeSolution.TreeNode(6);
        BalancedBinaryTreeSolution.TreeNode node7 = new BalancedBinaryTreeSolution.TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        boolean result = new BalancedBinaryTreeSolution().IsBalanced_Solution(node1);
        System.out.println("result: " + result);
    }

    public static void main(String[] args) {
        test1();
    }
}

class BalancedBinaryTreeSolution {

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param pRoot TreeNode类
     * @return bool布尔型
     */
    public boolean IsBalanced_Solution (TreeNode pRoot) {
        // write code here
        if (pRoot == null) {
            return true;
        }
        AtomicBoolean resAto = new AtomicBoolean(true);
        dfs(pRoot, 1, resAto);
        return resAto.get();
    }

    // 返回高度
    private int dfs(TreeNode node, int depth, AtomicBoolean resAto) {
        if (node == null) {
            return 0;
        }
        TreeNode left = node.left;
        TreeNode right = node.right;
        int dfsLeft = dfs(left, depth + 1, resAto);
        if (!resAto.get()) {
            return dfsLeft + 1;
        }
        int dfsRight = dfs(right, depth + 1, resAto);
        if (!resAto.get()) {
            return dfsRight + 1;
        }
        int height = Math.max(dfsLeft, dfsRight) + 1;
        if (Math.abs(dfsLeft - dfsRight) > 1) {
            resAto.set(false);
            return height;
        }
        return height;
    }
}
