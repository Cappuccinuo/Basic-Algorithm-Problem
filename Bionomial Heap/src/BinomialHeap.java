import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
public class BinomialHeap {
    public BinomialHeapNode Nodes;
    public int size;

    public BinomialHeap() {
        Nodes = null;
        size = 0;
    }

    public BinomialHeap(BinomialHeapNode node) {
        Nodes = node;
        size = node.getSize();
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
        Insert a key to Binomial Heap. This operation first creates a Binomial
        Heap with single key, then calls union on H and new Binomial Heap
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
    /*
        Method: Delete
        First reduce the key to minimum - 1 || -infinity, then call extractMin()
     */
    public void delete(int value) {
        if ((Nodes != null) && (Nodes.findNodeWithKey(value) != null)) {
            decreaseKeyValue(value, minimum() - 1);
            extractMin();
        }
    }

    /*
        Method: DecreaseKeyValue
        Compare the decreases key with its parent and if parent's key is more,
        we swap keys and recur for parent. We stop when we either reach a node
        whose parent has smaller key or we hit the root node.
        Time Complexity: O(log n)
     */
    public void decreaseKeyValue(int old_value, int new_value) {
        BinomialHeapNode current = Nodes.findNodeWithKey(old_value);
        if (current == null) {
            System.out.println("No such value");
            return;
        }
        current.key = new_value;
        BinomialHeapNode currentParent = current.parent;

        while ((currentParent != null) && (current.key < currentParent.key)) {
            int temp = current.key;
            current.key = currentParent.key;
            currentParent.key = temp;
            current = currentParent;
            currentParent = currentParent.parent;
        }
    }

    /*
        Method: ExtractMin
        First call minimum to find the minimum key, then remove the node and
        create a new heap by connecting all subtrees of the removed minimum node.
        Finally we call union on original heap and newly create heap.
        Time Complexity: O(logn)
     */
    public int extractMin() {
        if (Nodes == null) {
            System.out.println("Empty heap.");
            return -1;
        }
        BinomialHeapNode current = Nodes;
        BinomialHeapNode prev = null;
        int minValue = minimum();
        //BinomialHeapNode minNode = Nodes.findMinNode();
        while (current.key != minValue) {
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
        return minValue;
    }

    /* Helper function */
    // Merge the two Heaps in non-decreasing order of degrees
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

    public void union(BinomialHeapNode binHeap) {
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

    public void printHeap(BinomialHeapNode node) {
        if (node == this.Nodes) {
            System.out.println("key=" + node.key +
                    " ,degree=" + node.degree +
                    " ,status=root");
        }
        if (node.child != null) {
            System.out.println("key=" + node.child.key +
                    " ,degree=" + node.child.degree +
                    " ,status=child" +
                    " ,previous=" + node.key);
            printHeap(node.child);
        }
        if (node.sibling != null) {
            System.out.println("key=" + node.sibling.key +
                    " ,degree=" + node.sibling.degree +
                    " ,status=sibling" +
                    " ,previous=" + node.key);
            printHeap(node.sibling);
        }
    }
}
