import java.util.*;

public class BinaryTree {
    public void sort(RBNode root) {
        System.out.println("Sorted Binary Tree is: " + sortHelper(root));
        height(root);
        newLine();
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
        if (root == null || root.isNullLeaf) {
            System.out.println("The root is empty.");
            return;
        }
        System.out.println("Minimum value in this tree is: " + getMin(root).val);
        height(root);
        newLine();
    }

    private RBNode getMin(RBNode root) {
        while (root.left != null && !root.left.isNullLeaf) {
            root = root.left;
        }
        return root;
    }

    public void max(RBNode root) {
        if (root == null || root.isNullLeaf) {
            System.out.println("The root is empty.");
            return;
        }
        System.out.println("Maximum value in this tree is: " + getMax(root).val);
        height(root);
        newLine();
    }

    private RBNode getMax(RBNode root) {
        while (root.right != null && !root.right.isNullLeaf) {
            root = root.right;
        }
        return root;
    }

    public void search(RBNode root, int target) {
        System.out.println("Searching node with value " + target);
        RBNode result = searchHelper(root, target);
        if (result == null || result.isNullLeaf) {
            System.out.println("No such node with target value " + target);
        }
        else {
            System.out.println("The node has been searched, its value is " + result.val + ", its color is " + result.color);
        }
        height(root);
        newLine();
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

    public void successor(RBNode root, int target) {
        System.out.println("Searching successor of node with value " + target);
        RBNode node = searchHelper(root, target);
        if (node == null || node.isNullLeaf) {
            System.out.println("No such node with target value " + target);
            newLine();
            return;
        }
        RBNode succ = getSuccessor(node);
        if (succ == null || succ.isNullLeaf) {
            System.out.println("The node has no successor");
        }
        else {
            System.out.println("The successor of node with value " + target + " is " + succ.val + ", its color is " + succ.color);
        }
        height(root);
        newLine();
    }

    private RBNode getSuccessor(RBNode node) {
        if (node.right != null && !node.right.isNullLeaf) {
            return getMin(node.right);
        }
        RBNode parent = node.parent;
        while (parent != null && !parent.isNullLeaf && node == parent.right) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    public void predecessor(RBNode root, int target) {
        System.out.println("Searching predecessor of node with value " + target);
        RBNode node = searchHelper(root, target);
        if (node == null || node.isNullLeaf) {
            System.out.println("No such node with target value " + target);
            newLine();
            return;
        }
        RBNode pred = getPredecessor(node);
        if (pred == null || pred.isNullLeaf) {
            System.out.println("The node has no successor");
        }
        else {
            System.out.println("The successor of node with value " + target + " is " + pred.val + ", its color is " + pred.color);
        }
        height(root);
        newLine();
    }

    private RBNode getPredecessor(RBNode node) {
        if (node.left != null && !node.left.isNullLeaf) {
            return getMax(node.left);
        }
        RBNode parent = node.parent;
        while (parent != null && !parent.isNullLeaf && node == parent.left) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    private void newLine() {
        System.out.println(" ");
    }

    public void height(RBNode root) {
        int h = heightHelper(root);
        System.out.println("The height of the tree is " + h);
    }

    private int heightHelper(RBNode root) {
        if (root == null || root.isNullLeaf) {
            return 0;
        }
        int left = heightHelper(root.left);
        int right = heightHelper(root.right);
        return 1 + Math.max(left, right);
    }
}
