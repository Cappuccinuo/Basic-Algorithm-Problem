public class BinomialHeapNode {
    int key;
    int degree;
    BinomialHeapNode parent;
    BinomialHeapNode sibling;
    BinomialHeapNode child;

    public BinomialHeapNode(int k) {
        key = k;
        degree = 0;
        parent = null;
        sibling = null;
        child = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public BinomialHeapNode getParent() {
        return parent;
    }

    public void setParent(BinomialHeapNode parent) {
        this.parent = parent;
    }

    public BinomialHeapNode getChild() {
        return child;
    }

    public void setChild(BinomialHeapNode child) {
        this.child = child;
    }

    public BinomialHeapNode getSibling() {
        return sibling;
    }

    public void setSibling(BinomialHeapNode sibling) {
        this.sibling = sibling;
    }

    public BinomialHeapNode findMinNode() {
        BinomialHeapNode x = this;
        BinomialHeapNode y = this;
        int min = x.key;
        while (x != null) {
            if (x.key < min) {
                y = x;
                min = x.key;
            }
            x = x.sibling;
        }
        return y;
    }

    public BinomialHeapNode reverse(BinomialHeapNode sib) {
        BinomialHeapNode result;
        if (sibling != null) {
            result = sibling.reverse(this);
        }
        else {
            result = this;
        }
        sibling = sib;
        return result;
    }

    public int getSize() {
        return (1 +
                ((child == null) ? 0 : child.getSize()) +
                ((sibling == null) ? 0 : sibling.getSize()));
    }
}
