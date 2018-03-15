import java.util.*;

public class BinaryTree {
    public void sort(RBNode root) {
        System.out.println("Sorted Binary Tree is: " + sortHelper(root));
    }

    private List<Integer> sortHelper(RBNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(RBNode root, List<Integer> result) {
        if (root == null || root.isNullLeaf) {
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    public void min(RBNode root) {
        System.out.println("Minimum value in this tree is: " + getMin(root));
    }

    private int getMin(RBNode root) {
        while (root.left != null && !root.left.isNullLeaf) {
            root = root.left;
        }
        return root.val;
    }

    public void max(RBNode root) {
        System.out.println("Maximum value in this tree is: " + getMax(root));
    }

    private int getMax(RBNode root) {
        while (root.right != null && !root.right.isNullLeaf) {
            root = root.right;
        }
        return root.val;
    }

    public void search(RBNode root, int target) {
        System.out.println("Searching node with value " + target);
        RBNode result = searchHelper(root, target);
        if (result == null || result.isNullLeaf) {
            System.out.println("No such node.");
        }
        else {
            System.out.println("The node has been searched, its value is " + result.val + ", its color is " + result.color);
        }
    }

    private RBNode searchHelper(RBNode root, int target) {
        if (root == null || root.isNullLeaf || root.val == target) {
            return root;
        }
        if (target < root.val) {
            return searchHelper(root.left, target);
        }
        else {
            return searchHelper(root.right, target);
        }
    }
}
