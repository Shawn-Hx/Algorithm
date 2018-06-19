package cn.edu.nju.tree;

import cn.edu.nju.util.TreeNode;

import java.util.*;

/**
 * 数的遍历（非递归实现）
 */
public class Traversal {

    /**
     * 前序遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                res.add(node.val);
                stack.push(root.right);
                stack.push(root.left);
            }
        }
        return res;
    }

    /**
     * 中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.empty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        // TODO
        return null;
    }

    public static void main(String[] args) {

    }

}
