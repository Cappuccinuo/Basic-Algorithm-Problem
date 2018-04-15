import java.util.ArrayList;
import java.util.List;

public class Vertex {
    int height;
    int excessFlow;
    List<Edge> edges;

    Vertex(int height, int excessFlow) {
        this.height = height;
        this.excessFlow = excessFlow;
        edges = new ArrayList<>();
    }

    void addEdge(Vertex destination, int flow, int capacity) {
        Edge edge = new Edge(this, destination, flow, capacity);
        edges.add(edge);
    }
}
