package graph;

public class WeightedEdge<VertexT> extends Edge<VertexT> {
    int weight;

    public WeightedEdge(VertexT source, VertexT destination, int weight) {
        super(source, destination);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
