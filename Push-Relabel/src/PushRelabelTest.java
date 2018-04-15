public class PushRelabelTest {
    public static void main(String[] args) {
        PushRelabel pr = new PushRelabel(6);
        pr.addEdge(0, 1, 16);
        pr.addEdge(0, 2, 13);
        pr.addEdge(1, 2, 10);
        pr.addEdge(2, 1, 4);
        pr.addEdge(1, 3, 12);
        pr.addEdge(2, 4, 14);
        pr.addEdge(3, 2, 9);
        pr.addEdge(3, 5, 20);
        pr.addEdge(4, 3, 7);
        pr.addEdge(4, 5, 4);
        System.out.println(pr.getMaxFlow());
    }
}
