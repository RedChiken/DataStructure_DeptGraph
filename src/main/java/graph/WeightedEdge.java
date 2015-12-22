package graph;

public class WeightedEdge<VertexT> extends Edge<VertexT> {
    private int weight;

    public WeightedEdge(VertexT source, VertexT destination, int weight) {
        super(source, destination);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "WeightedEdge{" +
                "source=" + source +
                ", destination=" + destination +
                ", weight=" + weight +
                '}';
    }
}
