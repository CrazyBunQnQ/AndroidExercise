package com.exercise.bao.solution;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 *
 * Created by Administrator on 2016/7/7.
 */
public class MaximumDepthOfBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 超慢
     * @param root
     * @return
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Map<TreeNode, Integer> depthMap = new HashMap<>();
        depthMap.put(root, 1);
        int maxDepth = 1;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            int depth = depthMap.get(root);
            maxDepth = Math.max(maxDepth, depthMap.get(root));
            if (root.right != null) {
                depthMap.put(root.right, depth + 1);
                stack.push(root.right);
            }
            if (root.left != null) {
                depthMap.put(root.left, depth + 1);
                stack.push(root.left);
            }
            depthMap.remove(root);
        }
        return maxDepth;
    }

    /**
     * 慢
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null)
            return 0;

        Deque<TreeNode> stack = new LinkedList<TreeNode>();

        stack.push(root);
        int count = 0;

        while (!stack.isEmpty()) {
            int size = stack.size();
            while (size-- > 0) {
                TreeNode cur = stack.pop();
                if (cur.left != null)
                    stack.addLast(cur.left);
                if (cur.right != null)
                    stack.addLast(cur.right);
            }
            count++;

        }
        return count;
    }

    /**
     * 同下
     * @param root
     * @return
     */
    public int maxDepth3(TreeNode root) {
        if (root == null)  return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }

    /**
     * 略快
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
//        if(root==null){
//            return 0;
//        }
//        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
        return root==null? 0: 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
