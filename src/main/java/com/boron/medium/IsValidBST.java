package com.boron.medium;

import java.util.Optional;

/**
 * <pre>
 *  @description:
 *  @author: BruceBoron
 *  @date: 2025/11/10
 * </pre>
 */
public class IsValidBST {

    private static void test3() {
        IsValidBSTSolution.TreeNode node1 = new IsValidBSTSolution.TreeNode(3);
        IsValidBSTSolution.TreeNode node2 = new IsValidBSTSolution.TreeNode(2);
        IsValidBSTSolution.TreeNode node3 = new IsValidBSTSolution.TreeNode(5);
        IsValidBSTSolution.TreeNode node4 = new IsValidBSTSolution.TreeNode(1);
        IsValidBSTSolution.TreeNode node5 = new IsValidBSTSolution.TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        boolean validBST = IsValidBSTSolution.isValidBST(node1);
        System.out.println("result = " + validBST);
    }

    public static void main(String[] args) {
        test3();
    }

}

class IsValidBSTSolution {

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
     * @param root TreeNode类
     * @return bool布尔型
     */
    public static boolean isValidBST (TreeNode root) {
        // write code here
        if (root == null) {
            return true;
        }
        return dfs(root, Optional.empty(), Optional.empty());
    }

    private static boolean dfs(TreeNode node, Optional<Integer> minOpt, Optional<Integer> maxOpt) {
        if (node == null) {
            return true;
        }
        if (minOpt.isPresent() && node.val >= minOpt.get()) {
            return false;
        }
        if (maxOpt.isPresent() && node.val <= maxOpt.get()) {
            return false;
        }
        Optional<Integer> nextMinOpt = Optional.of(node.val);
        Optional<Integer> nextMaxOpt = Optional.of(node.val);
        boolean dfs1 = dfs(node.left, nextMinOpt, maxOpt);
        if (!dfs1) {
            return false;
        } else {
            return dfs(node.right, minOpt, nextMaxOpt);
        }
    }
}
