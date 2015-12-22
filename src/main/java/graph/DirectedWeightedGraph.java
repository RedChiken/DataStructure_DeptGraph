package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

class DirectedWeightedGraph<VertexT, WeightT> {
    private int vertices;
    private int edges;

    private HashMap<VertexT, HashMap<VertexT, WeightT>> graph;

    public DirectedWeightedGraph() {
        this.vertices = 0;
        this.edges = 0;
        this.graph = new HashMap<>();
    }

    public DirectedWeightedGraph(Vertices<VertexT> vertices, WeightedEdge<VertexT, WeightT>[] edges) {
        vertices.forEach((vertex) -> graph.putIfAbsent(vertex, new HashMap<>()));
        Arrays.stream(edges)
                .
    }
}