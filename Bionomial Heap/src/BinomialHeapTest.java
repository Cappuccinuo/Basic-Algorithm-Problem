import java.util.Scanner;

public class BinomialHeapTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BinomialHeap bh = new BinomialHeap();

        char ch;
        do {
            System.out.println("Binomial Heap Test:");
            System.out.println("1. Insert");
            System.out.println("2. Get Minimum");
            System.out.println("3. Extract Minimum");
            System.out.println("4. Check Size");
            System.out.println("5. Delete");
            System.out.println("6. Decrease key value");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter an integer to insert");
                    bh.insert(scan.nextInt());
                    break;
                case 2:
                    System.out.println("Minimum num in the heap is " + bh.minimum());
                    break;
                case 3:
                    System.out.println("Extract the minimum in heap" + bh.minimum());
                    bh.extractMin();
                    break;
                case 4:
                    System.out.println("Heap size is " + bh.size);
                    break;
                case 5:
                    System.out.println("Enter an integer to delete");
                    bh.delete(scan.nextInt());
                    break;
                case 6:
                    System.out.println("Enter the key value to decrease");
                    int oldVal = scan.nextInt();
                    System.out.println("Enter the new value");
                    int newVal = scan.nextInt();
                    bh.decreaseKeyValue(oldVal, newVal);
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
            bh.printHeap(bh.Nodes);
            System.out.println("Continue? (y/n)");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');

        /*
        BinomialHeapNode b1 = new BinomialHeapNode(1);

        BinomialHeap bb = new BinomialHeap(b1);
        bb.insert(25);
        bb.insert(12);
        bb.insert(16);
        bb.insert(10);
        bb.insert(28);
        bb.insert(13);
        bb.insert(37);
        bb.insert(41);

        //System.out.println(b1.child.key);
        //System.out.println(b1.sibling.key);
        bb.printHeap(bb.Nodes);
        */
    }
}
