package dept;

import graph.DirectedWeightedGraph;
import graph.WeightedEdge;

import java.util.ArrayList;

public class Optimizer {
    private DirectedWeightedGraph<String> graph;

    public Optimizer(DirectedWeightedGraph<String> graph) {
        try {
            this.graph = (DirectedWeightedGraph<String>) graph.clone();
        } catch (CloneNotSupportedException e) {
            this.graph = null;
            e.printStackTrace();
        }
    }

    public void simplify() {
        ArrayList<WeightedEdge<String>> edges = this.graph.getEdges();

        for (WeightedEdge<String> edge : edges) {
            for (WeightedEdge<String> edge1 : edges) {
                if (edge.getSource().equals(edge1.getDestination()) && edge.getDestination().equals(edge1.getSource())) {
                    if (edge.getWeight() > edge1.getWeight()) {
                        edge.setWeight(edge.getWeight() - edge1.getWeight());
                        edge1.setWeight(0);
                    } else {
                        edge1.setWeight(edge1.getWeight() - edge.getWeight());
                        edge.setWeight(0);
                    }
                }
            }
        }
        edges.removeIf(edge -> edge.getWeight() == 0);
        this.graph = new DirectedWeightedGraph<>(graph.getVertices(), edges);
    }

    public void fragmentize(){

    }

    public DirectedWeightedGraph<String> get() {
        return graph;
    }
}
