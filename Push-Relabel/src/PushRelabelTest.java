public class PushRelabelTest {
    public static void main(String[] args) {
        PushRelabel pr = new PushRelabel(6);
        pr.addEdge(0, 1, 11);
        pr.addEdge(0, 2, 12);
        pr.addEdge(2, 1, 1);
        pr.addEdge(1, 3, 12);
        pr.addEdge(2, 4, 11);
        pr.addEdge(3, 5, 19);
        pr.addEdge(4, 3, 7);
        pr.addEdge(4, 5, 4);
        System.out.println(pr.getMaxFlow());//23
    }
}
