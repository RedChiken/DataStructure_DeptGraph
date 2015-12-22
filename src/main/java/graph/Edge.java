package graph;

public class Edge<VertexT> {
    VertexT source, destination;

    public Edge(VertexT source, VertexT destination) {
        this.source = source;
        this.destination = destination;
    }

    public VertexT getSource() {
        return source;
    }

    public VertexT getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", destination=" + destination +
                '}';
    }
}