import java.util.Optional;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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

    public RBNode delete(RBNode root, int val) {
        AtomicReference<RBNode> rootReference = new AtomicReference<>();
        BinaryTree bt = new BinaryTree();
        System.out.println("Delete node with value " + val);
        delete(root, val, rootReference);
        if (rootReference.get() == null) {
            return root;
        }
        else {
            return rootReference.get();
        }
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

    private void delete(RBNode root, int val, AtomicReference<RBNode> rootReference) {
        if (root == null || root.isNullLeaf) {
            return;
        }
        if (root.val < val) {
            delete(root.right, val, rootReference);
        }
        else if (root.val > val) {
            delete(root.left, val, rootReference);
        }
        else {
            if (root.left.isNullLeaf || root.right.isNullLeaf) {
                deleteOneChild(root, rootReference);
            }
            else {
                RBNode successor = getSuccessor(root.right);
                root.val = successor.val;
                delete(root.right, successor.val, rootReference);
            }
        }
    }

    private void deleteOneChild(RBNode toDelete, AtomicReference<RBNode> rootReference) {
        RBNode child = toDelete.left.isNullLeaf ? toDelete.right : toDelete.left;
        replaceNode(toDelete, child, rootReference);
        if (toDelete.color == Color.BLACK) {
            if (child.color == Color.RED) {
                child.color = Color.BLACK;
            }
            else {
                deleteCase1(child, rootReference);
            }
        }
    }

    private void deleteCase1(RBNode doubleBlackNode, AtomicReference<RBNode> rootReference) {
        if (doubleBlackNode.parent == null) {
            rootReference.set(doubleBlackNode);
            return;
        }
        deleteCase2(doubleBlackNode, rootReference);
    }

    private void deleteCase2(RBNode doubleBlackNode, AtomicReference<RBNode> rootReference) {
        RBNode sibling = findSiblingNode(doubleBlackNode).get();
        if (sibling.color == Color.RED) {
            if (isLeftChild(sibling)) {
                rightRotate(sibling, true);
            }
            else {
                leftRotate(sibling, true);
            }
            if (sibling.parent == null) {
                rootReference.set(sibling);
            }
        }
        deleteCase3(doubleBlackNode, rootReference);
    }

    private void deleteCase3(RBNode doubleBlackNode, AtomicReference<RBNode> rootReference) {
        RBNode sibling = findSiblingNode(doubleBlackNode).get();
        if (sibling.parent.color == Color.BLACK
                && sibling.color == Color.BLACK
                && sibling.left.color == Color.BLACK
                && sibling.right.color == Color.BLACK) {
            sibling.color = Color.RED;
            deleteCase1(doubleBlackNode.parent, rootReference);
        }
        deleteCase4(doubleBlackNode, rootReference);
    }

    private void deleteCase4(RBNode doubleBlackNode, AtomicReference<RBNode> rootReference) {
        RBNode sibling = findSiblingNode(doubleBlackNode).get();
        if (sibling.parent.color == Color.RED
                && sibling.color == Color.BLACK
                && sibling.left.color == Color.BLACK
                && sibling.right.color == Color.BLACK) {
            sibling.color = Color.RED;
            doubleBlackNode.parent.color = Color.BLACK;
            return;
        }
        else {
            deleteCase5(doubleBlackNode, rootReference);
        }
    }

    private void deleteCase5(RBNode doubleBlackNode, AtomicReference<RBNode> rootReference) {
        RBNode sibling = findSiblingNode(doubleBlackNode).get();
        if (sibling.color == Color.BLACK) {
            if (isLeftChild(doubleBlackNode)
                    && sibling.right.color == Color.BLACK
                    && sibling.left.color == Color.RED) {
                rightRotate(sibling.left, true);
            }
            else if (!isLeftChild(doubleBlackNode)
                    && sibling.right.color == Color.RED
                    && sibling.left.color == Color.BLACK) {
                leftRotate(sibling.right, true);
            }
        }
        deleteCase6(doubleBlackNode, rootReference);
    }

    private void deleteCase6(RBNode doubleBlackNode, AtomicReference<RBNode> rootReference) {
        RBNode sibling = findSiblingNode(doubleBlackNode).get();
        sibling.color = sibling.parent.color;
        sibling.parent.color = Color.BLACK;
        if (isLeftChild(doubleBlackNode)) {
            sibling.right.color = Color.BLACK;
            leftRotate(sibling, false);
        }
        else {
            sibling.left.color = Color.BLACK;
            rightRotate(sibling, false);
        }
        if (sibling.parent == null) {
            rootReference.set(sibling);
        }
    }

    private void replaceNode(RBNode toReplace, RBNode replace, AtomicReference<RBNode> rootReference) {
        replace.parent = toReplace.parent;
        if (toReplace.parent == null) {
            rootReference.set(replace);
        }
        else {
            if (isLeftChild(toReplace)) {
                toReplace.parent.left = replace;
            }
            else {
                toReplace.parent.right = replace;
            }
        }
    }

    public void printRedBlackTree(RBNode root) {
        printRedBlackTree(root, 0);
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
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

    private RBNode getSuccessor(RBNode root) {
        RBNode prev = null;
        while (root != null && !root.isNullLeaf) {
            prev = root;
            root = root.left;
        }
        return prev != null ? prev : root;
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

    public boolean isRedBlackTree(RBNode root) {
        if (root == null) {
            return true;
        }
        if (root.color != Color.BLACK) {
            System.out.println("Tree is not red-black tree.");
            return false;
        }
        AtomicInteger blackCount = new AtomicInteger(0);
        return checkBlackNodeCount(root, blackCount, 0) && noRedRed(root, Color.BLACK);
    }

    private boolean checkBlackNodeCount(RBNode root, AtomicInteger blackCount, int current) {
        if (root.color == Color.BLACK) {
            current++;
        }
        if (root.left == null && root.right == null) {
            if (blackCount.get() == 0) {
                blackCount.set(current);
                return true;
            }
            else {
                return current == blackCount.get();
            }
        }
        return checkBlackNodeCount(root.left, blackCount, current)
                && checkBlackNodeCount(root.right, blackCount, current);
    }

    private boolean noRedRed(RBNode root, Color parentColor) {
        if (root == null) {
            return true;
        }
        if (root.color == Color.RED && parentColor == Color.RED) {
            return false;
        }
        return noRedRed(root.left, root.color) && noRedRed(root.right, root.color);
    }

    private static void allOrder() {
        System.out.println("1. Sort");
        System.out.println("2. Search");
        System.out.println("3. Min");
        System.out.println("4. Max");
        System.out.println("5. Successor");
        System.out.println("6. Predecessor");
        System.out.println("7. Insert");
        System.out.println("8. Delete");
        System.out.println("9. Exit");
    }

    private int readValue() {
        Scanner scanner = new Scanner(System.in);
        return Integer.valueOf(scanner.nextLine());
    }

    public static void main(String[] args) throws FileNotFoundException {
        RBNode root = null;
        RedBlackTree redBlackTree = new RedBlackTree();

        Scanner in = new Scanner(new FileReader("val.txt"));
        while (in.hasNext()) {
            String str = in.next();
            int val = Integer.valueOf(str);
            root = redBlackTree.insert(root, val);
        }
        redBlackTree.printRedBlackTree(root);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            allOrder();
            System.out.println("Enter the commond: ");
            int num = redBlackTree.readValue();
            BinaryTree bt = new BinaryTree();
            int n;
            String str;
            if (num == 1) {
                bt.sort(root);
            }
            if (num == 2) {
                System.out.println("Enter the search value: ");
                n = redBlackTree.readValue();
                bt.search(root, n);
            }
            if (num == 3) {
                bt.min(root);
            }
            if (num == 4) {
                bt.max(root);
            }
            if (num == 5) {
                System.out.println("Enter the search value: ");
                n = redBlackTree.readValue();
                bt.successor(root, n);
            }
            if (num == 6) {
                System.out.println("Enter the search value: ");
                n = redBlackTree.readValue();
                bt.predecessor(root, n);
            }
            if (num == 7) {
                System.out.println("Enter the insert value: ");
                n = redBlackTree.readValue();
                root = redBlackTree.insert(root, n);
                redBlackTree.printRedBlackTree(root);
                redBlackTree.isRedBlackTree(root);
            }
            if (num == 8) {
                System.out.println("Enter the delete value: ");
                n = redBlackTree.readValue();
                root = redBlackTree.delete(root, n);
                redBlackTree.printRedBlackTree(root);
                redBlackTree.isRedBlackTree(root);}
            bt.height(root);
            if (num == 9) {
                break;
            }
            if (num > 9 || num < 1) {
                System.out.println("Command Error, re-enter.");
                continue;
            }
            System.out.println("Continue? Y/N");
            scanner = new Scanner(System.in);
            str = scanner.nextLine();
            if (str.equals("Y") || str.length() == 0) {
                continue;
            }
            else {
                break;
            }
        }
    }
}
