package dept;

import graph.DirectedWeightedGraph;
import graph.WeightedEdge;

import java.util.ArrayList;
import java.util.HashMap;

public class Optimizer {
    private DirectedWeightedGraph<String> graph;
    HashMap<String, Integer> incomeVertex;
    HashMap<String, Integer> outcomeVertex;

    public Optimizer(DirectedWeightedGraph<String> graph) {
        incomeVertex = new HashMap<>();
        outcomeVertex = new HashMap<>();
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
        ArrayList<WeightedEdge<String>> edges = this.graph.getEdges();

        edges.forEach(edge-> {
            outcomeVertex.computeIfPresent(edge.getSource(), (s, integer) -> edge.getWeight() + integer);
            outcomeVertex.putIfAbsent(edge.getSource(), edge.getWeight());
            incomeVertex.computeIfPresent(edge.getDestination(), (s, integer) -> edge.getWeight() + integer);
            incomeVertex.putIfAbsent(edge.getDestination(), edge.getWeight());
        });
    }

    public DirectedWeightedGraph<String> get() {
        return graph;
    }
}
