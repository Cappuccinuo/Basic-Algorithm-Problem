import java.util.Optional;

public class RedBlackTree {
    public enum Color {
        RED,
        BLACK
    }

    public static class RBNode {
        int val;
        RBNode left;
        RBNode right;
        RBNode parent;
        Color color;
        boolean isNullLeaf;
    }

    private static RBNode createNullLeafNode(RBNode parent) {
        RBNode leaf = new RBNode();
        leaf.color = Color.BLACK;
        leaf.isNullLeaf = true;
        leaf.parent = parent;
        return leaf;
    }

    private static RBNode createBlackNode(int val) {
        RBNode node = new RBNode();
        node.val = val;
        node.color = Color.BLACK;
        node.left = createNullLeafNode(node);
        node.right = createNullLeafNode(node);
        return node;
    }

    private static RBNode createRedNode(RBNode parent, int val) {
        RBNode node = new RBNode();
        node.val = val;
        node.color = Color.RED;
        node.parent = parent;
        node.left = createNullLeafNode(node);
        node.right = createNullLeafNode(node);
        return node;
    }

    public RBNode insert(RBNode root, int val) {
        return insert(null, root, val);
    }

    public RBNode insert(RBNode parent, RBNode root, int val) {
        if (root == null || root.isNullLeaf) {
            if (parent != null) {
                createRedNode(parent, val);
            }
            else {
                createBlackNode(val);
            }
        }

        if (root.val == val) {
            throw new IllegalArgumentException("Duplicate val " + val);
        }
        boolean isLeft;
        if (val < root.val) {
            RBNode left = insert(root, root.left, val);
            if (left == root.parent) {
                return left;
            }
            root.left = left;
            isLeft = true;
        }
        else {
            RBNode right = insert(root, root.right, val);
            if (right == root.parent) {
                return right;
            }
            root.right = right;
            isLeft = false;
        }

        if (isLeft) {
            
        }
    }

    private Optional<RBNode> findSiblingNode(RBNode root) {
        RBNode parent = root.parent;
        if (isLeftChild(root)) {
            return Optional.ofNullable(parent.right.isNullLeaf ? null : parent.right);
        }
        else {
            return Optional.ofNullable(parent.left.isNullLeaf ? null : parent.left);
        }
    }

    private boolean isLeftChild(RBNode root) {
        RBNode parent = root.parent;
        if (parent.left == root) {
            return true;
        }
        else {
            return false;
        }
    }

    private void rightRotate(RBNode root, boolean changeColor) {
        RBNode parent = root.parent;
        root.parent = parent.parent;
        if (parent.parent != null) {
            if (parent.parent.left == parent) {
                parent.parent.left = root;
            }
            else {
                parent.parent.right = root;
            }
        }
        RBNode right = root.right;
        root.right = parent;
        parent.parent = root;
        parent.left = right;
        if (right != null) {
            right.parent = parent;
        }
        if (changeColor) {
            root.color = Color.BLACK;
            parent.color = Color.RED;
        }
    }

    private void leftRotate(RBNode root, boolean changeColor) {
        RBNode parent = root.parent;
        root.parent = parent.parent;
        if (parent.parent != null) {
            if (parent.parent.right == parent) {
                parent.parent.right = root;
            }
            else {
                parent.parent.left = root;
            }
        }
        RBNode left = root.left;
        root.left = parent;
        parent.parent = root;
        parent.right = left;
        if (left != null) {
            left.parent = parent;
        }
        if (changeColor) {
            root.color = Color.BLACK;
            parent.color = Color.RED;
        }
    }

}
