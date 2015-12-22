package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class DirectedWeightedGraph<VertexT> implements Cloneable {
    private int vertices;
    private int edges;

    private HashMap<VertexT, HashMap<VertexT, Integer>> graph;

    public DirectedWeightedGraph() {
        this.vertices = 0;
        this.edges = 0;
        this.graph = new HashMap<>();
    }

    public DirectedWeightedGraph(Vertices<VertexT> vertices, Collection<WeightedEdge<VertexT>> edges) {
        this.vertices = vertices.size();
        this.edges = edges.size();
        vertices.forEach((vertex) -> graph.putIfAbsent(vertex, new HashMap<>()));
        edges.stream()
                .forEach(this::addEdge);
    }

    public void addVertex(VertexT vertex) {
        graph.putIfAbsent(vertex, new HashMap<>());
    }

    public void removeVertex(VertexT vertex) {
        graph.remove(vertex);
        --vertices;
        long deletedVertices = graph.values().stream()
                .filter(edge -> edge.containsKey(vertex))
                .count();
        graph.values().stream()
                .forEach(edge -> edge.remove(vertex));
        this.vertices -= deletedVertices;
    }

    public void updateVertex(VertexT target, VertexT replace) {
        if (!graph.containsKey(replace)) {
            HashMap<VertexT, Integer> edges = graph.get(target);
            graph.remove(target);
            graph.put(replace, edges);
        }
    }

    public void addEdge(WeightedEdge<VertexT> edge) {
        graph.computeIfPresent(edge.source,
                (src, edgeHashMap) -> {
                    if (edgeHashMap.containsKey(edge.destination)) {
                        edgeHashMap.put(edge.destination, edge.weight + edgeHashMap.get(edge.destination));
                    } else {
                        edgeHashMap.put(edge.destination, edge.weight);
                        ++edges;
                    }
                    return edgeHashMap;
                });
    }

    public void removeEdge(VertexT source, VertexT destination) {
        graph.computeIfPresent(source, (vertex, edges) -> {
            edges.remove(destination);
            return edges;
        });
    }

    public void updateEdge(VertexT source, VertexT destination, int weight) {
        graph.computeIfPresent(source, (src, edges) -> {
            edges.computeIfPresent(destination, (dest, integer) -> weight);
            return edges;
        });
    }

    public Vertices<VertexT> getVertices() {
        return new Vertices<>(this.graph.keySet());
    }

    public ArrayList<WeightedEdge<VertexT>> getEdges() {
        ArrayList<WeightedEdge<VertexT>> edges = new ArrayList<>();
        this.graph.forEach((src, edgeMap) -> edgeMap.forEach((dest, integer) -> edges.add(new WeightedEdge<>(src, dest, integer))));
        return edges;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();
        DirectedWeightedGraph<VertexT> newGraph = new DirectedWeightedGraph<>();
        newGraph.graph = (HashMap<VertexT, HashMap<VertexT, Integer>>) this.graph.clone();
        newGraph.vertices = this.vertices;
        newGraph.edges = this.edges;
        return newGraph;
    }
}
