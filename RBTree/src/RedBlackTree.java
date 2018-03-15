import java.util.Optional;

public class RedBlackTree {
    public enum Color {
        RED,
        BLACK
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
                return createRedNode(parent, val);
            }
            else {
                return createBlackNode(val);
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
            if (root.color == Color.RED && root.left.color == Color.RED) {
                Optional<RBNode> sibling = findSiblingNode(root);
                if (!sibling.isPresent() || sibling.get().color == Color.BLACK) {
                    if (isLeftChild(root)) {
                        rightRotate(root, true);
                    }
                    else {
                        rightRotate(root.left, false);
                        root = root.parent;
                        leftRotate(root, true);
                    }
                }
                else {
                    root.color = Color.BLACK;
                    sibling.get().color = Color.BLACK;
                    if (root.parent.parent != null) {
                        root.parent.color = Color.RED;
                    }
                }
            }
        }
        else {
            if (root.color == Color.RED && root.right.color == Color.RED) {
                Optional<RBNode> sibling = findSiblingNode(root);
                if (!sibling.isPresent() || sibling.get().color == Color.BLACK) {
                    if (isLeftChild(root)) {
                        leftRotate(root.right, false);
                        root = root.parent;
                        rightRotate(root, true);
                    }
                    else {
                        leftRotate(root, true);
                    }
                }
                else {
                    root.color = Color.BLACK;
                    sibling.get().color = Color.BLACK;
                    if (root.parent.parent != null) {
                        root.parent.color = Color.RED;
                    }
                }
            }
        }
        return root;
    }

    public void printRedBlackTree(RBNode root) {
        printRedBlackTree(root, 0);
    }

    private void printRedBlackTree(RBNode root, int space) {
        if (root == null || root.isNullLeaf) {
            return;
        }
        printRedBlackTree(root.right, space + 7);
        for (int i = 0; i < space; i++) {
            System.out.print(" ");
        }
        System.out.println(root.val + " " + (root.color == Color.BLACK ? "B" : "R"));
        printRedBlackTree(root.left, space + 7);
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

    public static void main(String[] args) {
        RBNode root = null;
        RedBlackTree redBlackTree = new RedBlackTree();

        root = redBlackTree.insert(root, 10);
        root = redBlackTree.insert(root, 20);
        root = redBlackTree.insert(root, -10);
        root = redBlackTree.insert(root, 15);
        root = redBlackTree.insert(root, 17);
        root = redBlackTree.insert(root, 40);
        root = redBlackTree.insert(root, 50);
        root = redBlackTree.insert(root, 60);
        redBlackTree.printRedBlackTree(root);

        //BTreePrinter bp = new BTreePrinter();
        //bp.printNode(root);
        BinaryTree bt = new BinaryTree();
        bt.sort(root);
        bt.min(root);
        bt.max(root);
        bt.search(root, 10);
        bt.search(root, 110);
        bt.successor(root, 10);
        bt.successor(root, 60);
        bt.successor(root, 600);
        bt.predecessor(root, 15);
        bt.predecessor(root, -10);
        bt.predecessor(root, 100);
    }
}
