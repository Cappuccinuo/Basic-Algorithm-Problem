import java.util.List;
import java.util.ArrayList;

public class PushRelabel {
    List<Vertex> vertices;

    // The initial height and excessFlow of all vertices is 0.
    public PushRelabel(int verticesCount) {
        vertices = new ArrayList<>();
        for (int i = 0; i < verticesCount; i++) {
            vertices.add(new Vertex(0, 0));
        }
    }

    // Create edge and initialize its flow to 0.
    public void addEdge(int source, int destination, int capacity) {
        vertices.get(source).addEdge(vertices.get(destination), 0, capacity);
    }

    // Preflow:
    // 1. Set source's height to |V|
    // 2. Saturizes flow of edges adjacent to source
    // 3. Add backward edges to residual graph.
    public void preflow(Vertex source) {
        source.height = vertices.size();
        for (Edge edge : source.edges) {
            edge.flow = edge.capacity;
            edge.destination.excessFlow = edge.flow;
            edge.destination.addEdge(source, -edge.flow, 0);
        }
    }

    // Push:
    // 1.
    public boolean push(Vertex v) {
        for (Edge e : v.edges) {
            // Push is only possible if height of adjacent
            // is smaller than height of overflowing vertex
            // And if flow is equal to capacity then no push
            // is possible
            if ((v.height > e.destination.height) && (e.flow != e.capacity)) {
                // Flow to be pushed is equal to minimum of remaining flow
                // on edge and excess flow
                int flow = Math.min(v.excessFlow, e.capacity - e.flow);

                // Increase excess flow for adjacent
                e.destination.excessFlow += flow;

                // Reduce excess flow for overflowing vertex
                v.excessFlow -= flow;

                // Add residual flow
                e.flow += flow;
                updateReverseEdge(e, flow);
                return true;
            }
        }
        return false;
    }

    // Update reverse flow
    private void updateReverseEdge(Edge edge, int flow) {
        for (Edge e : edge.destination.edges) {
            if (e.destination.equals(e.source)) {
                e.flow = -flow;
                return;
            }
        }
        // Adding reverse edge in residual graph
        edge.destination.addEdge(edge.source, -flow, 0);
    }

    private void relabel(Vertex v) {
        // Initialize minimum height of an adjacent
        int minAdjHeight = Integer.MAX_VALUE;

        // Find the adjacent with minimum height
        for (Edge e : v.edges) {
            // If flow is equal to capacity then no relabeling
            if ((e.flow != e.capacity) && (e.destination.height < minAdjHeight)) {
                // Update minimum height
                minAdjHeight = e.destination.height;
                // Update height of v
                v.height = minAdjHeight + 1;
            }
        }
    }

    // Return overflow vertex
    private Vertex getActiveVertex() {
        for (int i = 1; i < vertices.size() - 1; i++) {
            if (vertices.get(i).excessFlow > 0) {
                return vertices.get(i);
            }
        }
        return null;
    }

    public int getMaxFlow() {
        preflow(vertices.get(0));
        Vertex activeVertex = getActiveVertex();
        // Loop until none of the Vertex is overflow
        while (activeVertex != null) {
            if (!push(activeVertex)) {
                relabel(activeVertex);
            }
            activeVertex = getActiveVertex();
        }
        // The last vertex's excessFlow will be final maximum flow
        return vertices.get(vertices.size() - 1).excessFlow;
    }
}
