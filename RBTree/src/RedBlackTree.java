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
        return new RBNode();
    }

}
