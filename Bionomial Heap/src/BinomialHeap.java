public class BinomialHeap {
    private BinomialHeapNode Nodes;
    private int size;

    public BinomialHeap() {
        Nodes = null;
        size = 0;
    }

    public boolean isEmpty() {
        return Nodes == null;
    }

    public int getSize() {
        return size;
    }

    /*
        Method: Minimum
        To get the minimum of the heap, just traverse the list of root of
        Binomial Trees and return the minimum key. This operation requires
        O(log n) time. It can be optimized to O(1) by maintaining a pointer
        to minimum key root.
     */

    public int minimum() {
        return Nodes.findMinNode().key;
    }

    /*
        Method: Insert
        Insert a key to Binomial Heap. This operation
     */
    public void insert(int value) {
        BinomialHeapNode node = new BinomialHeapNode(value);
        if (Nodes == null) {
            Nodes = node;
            size = 1;
        }
        else {
            union(node);
            size++;
        }
    }

    public void delete(int value) {
        return;
    }

    public void decreaseKeyValue(int old_value, int new_value) {
        return;
    }

    public int extractMin() {
        if (Nodes == null) {
            System.out.println("Empty heap.");
            return -1;
        }
        BinomialHeapNode current = Nodes;
        BinomialHeapNode prev = null;
        BinomialHeapNode minNode = Nodes.findMinNode();
        while (current.key != minNode.key) {
            prev = current;
            current = current.sibling;
        }

        if (prev == null) {
            Nodes = current.sibling;
        }
        else {
            prev.sibling = current.sibling;
        }
        current = current.child;
        BinomialHeapNode temp = current;
        while (current != null) {
            current.parent = null;
            current = current.sibling;
        }

        if ((Nodes == null) && (temp == null)) {
            size = 0;
        }
        else {
            if ((Nodes == null) && (temp != null)) {
                Nodes = temp.reverse(null);
                size = temp.getSize();
            }
            else {
                union(temp.reverse(null));
                size = Nodes.getSize();
            }
        }
        return minNode.key;
    }

    /* Helper function */

    private void merge(BinomialHeapNode binHeap) {
        BinomialHeapNode h1 = Nodes;
        BinomialHeapNode h2 = binHeap;
        while ((h1 != null) && (h2 != null)) {
            if (h1.degree == h2.degree) {
                BinomialHeapNode tmp = h2;
                h2 = h2.sibling;
                tmp.sibling = h1.sibling;
                h1.sibling = tmp;
                h1 = tmp.sibling;
            }
            else if (h1.degree < h2.degree) {
                if ((h1.sibling == null) || (h1.sibling.degree > h2.degree)) {
                    BinomialHeapNode tmp = h2;
                    h2 = h2.sibling;
                    tmp.sibling = h1.sibling;
                    h1.sibling = tmp;
                    h1 = tmp.sibling;
                }
                else {
                    h1 = h1.sibling;
                }
            }
            else {
                BinomialHeapNode tmp = h1;
                h1 = h2;
                h2 = h2.sibling;
                h1.sibling = tmp;
                if (tmp == Nodes) {
                    Nodes = h1;
                }
            }
        }
        if (h1 == null) {
            h1 = Nodes;
            while (h1.sibling != null) {
                h1 = h1.sibling;
            }
            h1.sibling = h2;
        }
    }

    private void union(BinomialHeapNode binHeap) {
        merge(binHeap);
        BinomialHeapNode prev = null;
        BinomialHeapNode current = Nodes;
        BinomialHeapNode next = Nodes.sibling;

        while (next != null) {
            // Case 1: Orders of x and next-x are not same, we simply move ahead
            // Case 2: Orders of x and next-x are same, order of next-next-x is
            // also same, move ahead
            if ((current.degree != next.degree) ||
                    ((next.sibling != null) && (next.sibling.degree == current.degree))) {
                prev = current;
                current = next;
            }
            else {
                // Case 3: If key of x is smaller than or equal to key of next-x,
                // then make next-x as a child of x by linking it with x.
                if (current.key <= next.key) {
                    current.sibling = next.sibling;
                    next.parent = current;
                    next.sibling = current.child;
                    current.child = next;
                    current.degree++;
                }
                // Case 4: If key of x is greater, then make x as child of next.
                else {
                    if (prev == null) {
                        Nodes = next;
                    }
                    else {
                        prev.sibling = next;
                    }
                    current.parent = next;
                    current.sibling = next.child;
                    next.child = current;
                    next.degree++;
                    current = next;
                }
            }

            next = current.sibling;
        }
    }

    public static void main(String[] args) {
        BinomialHeap bh = new BinomialHeap();
        bh.insert(1);
        System.out.println(bh.minimum());
        bh.insert(2);
        System.out.println(bh.Nodes.child.key);
        bh.extractMin();
        System.out.println(bh.size);
        System.out.println(bh.Nodes.key);
    }
}
