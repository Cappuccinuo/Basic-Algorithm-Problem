import java.util.*;

public class BinaryTreeTraversal {
    // Preorder
    public static List<Integer> preOrderIterative(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode current = root;
        while (!stack.isEmpty()) {
            current = stack.pop();
            result.add(current.val);
            if (current.right != null) {
                stack.push(current.right);
            }
            if(current.left != null) {
                stack.push(current.left);
            }
        }
        return result;
    }

    public static List<Integer> preOrderRecursive(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        preHelper(root, result);
        return result;
    }

    private static void preHelper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preHelper(root.left, list);
        preHelper(root.right, list);
    }

    // Inorder
    public static List<Integer> inOrderIterative(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }
        return result;
    }

    public static List<Integer> inOrderRecursive(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        inHelper(root, result);
        return result;
    }

    public static void inHelper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inHelper(root.left, list);
        list.add(root.val);
        inHelper(root.right, list);
    }

    // Postorder
    public static List<Integer> postOrderIterative(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode current = root;
        while (!stack.isEmpty()) {
            current = stack.pop();
            result.addFirst(current.val);
            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }
        return result;
    }

    public static List<Integer> postOrderRecursive(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        postHelper(root, result);
        return result;
    }

    public static void postHelper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postHelper(root.left, list);
        postHelper(root.right, list);
        list.add(root.val);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);
        t5.left = t3;
        t5.right = t8;
        t3.left = t2;
        t3.right = t4;
        t8.left = t6;
        t8.right = t9;
        t2.left = t1;
        t6.right = t7;
        List<Integer> preResultIterative = preOrderIterative(t5);
        System.out.println("PreOrder Result(Iterative): " + preResultIterative);
        List<Integer> preResultRecursive = preOrderRecursive(t5);
        System.out.println("PreOrder Result(Recursive): " + preResultRecursive);
        List<Integer> inResultIterative = inOrderIterative(t5);
        System.out.println("InOrder Result(Iterative): " + inResultIterative);
        List<Integer> inResultRecursive = inOrderRecursive(t5);
        System.out.println("InOrder Result(Recursive): " + inResultRecursive);
        List<Integer> postResultIterative = postOrderIterative(t5);
        System.out.println("PostOrder Result(Iterative): " + postResultIterative);
        List<Integer> postResultRecursive = postOrderRecursive(t5);
        System.out.println("postOrder Result(Recursive): " + postResultRecursive);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}