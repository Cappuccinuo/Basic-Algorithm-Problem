public class Edge {
    Vertex source;
    Vertex destination;
    int flow;
    int capacity;

    Edge(Vertex source, Vertex destination, int flow, int capacity) {
        this.source = source;
        this.destination = destination;
        this.flow = flow;
        this.capacity = capacity;
    }
}
