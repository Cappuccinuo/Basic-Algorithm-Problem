import java.util.ArrayList;
import java.util.List;

public class Graph {
    int V;
    List<Vertex> vertices;
    List<Edge> edges;

    Graph(int vNum) {
        this.V = vNum;
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
        for (int i = 0; i < vNum; i++) {
            vertices.add(new Vertex(0, 0));
        }
    }

    void addEdge(Vertex source, Vertex destination, int flow, int capacity) {
        edges.add(new Edge(source, destination, flow, capacity));
    }
}
