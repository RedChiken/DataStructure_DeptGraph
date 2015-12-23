package dept;

import graph.DirectedWeightedGraph;
import graph.Edge;
import graph.WeightedEdge;

import java.util.*;

public class Optimizer {
    HashMap<String, Integer> incomeVertex;
    HashMap<String, Integer> outcomeVertex;
    private DirectedWeightedGraph<String> graph;

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

    public void fragmentize() {
        ArrayList<WeightedEdge<String>> edges = this.graph.getEdges();
        HashMap<String, Integer> optimizedOutcome, optimizedIncome;
        edges.forEach(edge -> {
            outcomeVertex.computeIfPresent(edge.getSource(), (s, integer) -> edge.getWeight() + integer);
            outcomeVertex.putIfAbsent(edge.getSource(), edge.getWeight());
            incomeVertex.computeIfPresent(edge.getDestination(), (s, integer) -> edge.getWeight() + integer);
            incomeVertex.putIfAbsent(edge.getDestination(), edge.getWeight());
        });

        optimizedIncome = new HashMap<>();
        optimizedOutcome = new HashMap<>();

        outcomeVertex.forEach((s, integer) -> {
            int delta = integer - incomeVertex.get(s);
            if (delta > 0) {
                optimizedOutcome.put(s, delta);
            }
        });
        incomeVertex.forEach((s, integer) -> {
            int delta = integer - outcomeVertex.get(s);
            if (delta > 0) {
                optimizedIncome.put(s, delta);
            }
        });

        this.outcomeVertex = optimizedOutcome;
        this.incomeVertex = optimizedIncome;
    }

    public void regenerate() {
        TreeMap<String, Integer> sortedIncomeVertices = new TreeMap<>(new ValueComparator<>(incomeVertex));
        TreeMap<String, Integer> sortedOutcomeVertices = new TreeMap<>(new ValueComparator<>(outcomeVertex));
        ArrayList<WeightedEdge<String>> edges = new ArrayList<>();

        sortedIncomeVertices.putAll(incomeVertex);
        sortedOutcomeVertices.putAll(outcomeVertex);

        while (!(sortedIncomeVertices.isEmpty() && sortedOutcomeVertices.isEmpty())) {
            Map.Entry<String, Integer> incomeEntry = sortedIncomeVertices.firstEntry();
            Map.Entry<String, Integer> outcomeEntry = sortedOutcomeVertices.firstEntry();

            if (incomeEntry.getValue() > outcomeEntry.getValue()) {
                int delta = incomeEntry.getValue() - outcomeEntry.getValue();
                edges.add(new WeightedEdge<>(outcomeEntry.getKey(), incomeEntry.getKey(), outcomeEntry.getValue()));
                sortedIncomeVertices.put(incomeEntry.getKey(), delta);
                sortedOutcomeVertices.remove(outcomeEntry.getKey());
            } else if(incomeEntry.getValue() < outcomeEntry.getValue()) {
                int delta = outcomeEntry.getValue() - incomeEntry.getValue();
                edges.add(new WeightedEdge<>(outcomeEntry.getKey(), incomeEntry.getKey(), incomeEntry.getValue()));
                sortedOutcomeVertices.put(outcomeEntry.getKey(), delta);
                sortedIncomeVertices.remove(incomeEntry.getKey());
            } else {
                edges.add(new WeightedEdge<>(outcomeEntry.getKey(), incomeEntry.getKey(), outcomeEntry.getValue()));
                sortedIncomeVertices.remove(incomeEntry.getKey());
                sortedOutcomeVertices.remove(outcomeEntry.getKey());
            }
        }

        this.graph = new DirectedWeightedGraph<>(edges);
    }

    public DirectedWeightedGraph<String> get() {
        return graph;
    }

    private class ValueComparator<K extends Comparable, V extends Comparable> implements Comparator<K> {
        Map<K, V> base;

        public ValueComparator(Map<K, V> base) {
            this.base = base;
        }

        @Override
        public int compare(K k1, K k2) {
            if (base.get(k2).compareTo(base.get(k1)) == 0) {
                return k2.compareTo(k1);
            } else {
                return base.get(k2).compareTo(base.get(k1));
            }
        }
    }
}
