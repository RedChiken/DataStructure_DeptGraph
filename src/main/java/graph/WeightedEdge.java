package graph;

public class WeightedEdge<VertexT, WeightT> extends Edge<VertexT>{
    private WeightT weight;

    public WeightedEdge(VertexT source, VertexT destination, WeightT weight) {
        super(source, destination);
        this.weight = weight;
    }

    public WeightT getWeight() {
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
