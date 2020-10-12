package cn.edu.nju.tree;

public class Diameter {

    private int res = 1;

    private int depth(TreeNode node) {
        if (node == null)
            return 0;
        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);
        res = Math.max(res, leftDepth + rightDepth + 1);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return res - 1;
    }
}
