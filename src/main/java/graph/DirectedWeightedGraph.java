package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

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
        this.graph = new HashMap<>();
        //새 vertex를 추가할 때 마다 새로운 '인접한 vertex 리스트'도 같이 넣는다.
        vertices.forEach((vertex) -> graph.putIfAbsent(vertex, new HashMap<>()));
        //edges의 각각의 edge에 대해 addEdge()를 함
        edges.forEach(this::addEdge);
    }

    public DirectedWeightedGraph(Collection<WeightedEdge<VertexT>> edges) {
        HashSet<VertexT> verticeSet = new HashSet<>();//ㅅㅂ 지역변수랑 멤버변수의 이름을 똑같이 쓰지 말라고 헷갈리잖아
        this.edges = edges.size();
        this.graph = new HashMap<>();   //추가한 곳.
        //edge의 src와 dst를 vertex 리스트에 넣기
        edges.forEach(edge -> {
            verticeSet.add(edge.getSource());
            verticeSet.add(edge.getDestination());
        });
        this.vertices = verticeSet.size();//ㅂㄷㅂㄷ 이게 ㄹㅇ 페이탈리티구나... 제발 곱게 말할때 변수명 바꿔라...
        //새 vertex를 추가할 때 마다 새로운 '인접한 vertex 리스트'도 같이 넣는다.
        verticeSet.forEach(vertex -> graph.putIfAbsent(vertex, new HashMap<>()));
        //edges의 각각의 edge에 대해 addEdge()를 함
        edges.forEach(this::addEdge);
    }

    public void addVertex(VertexT vertex) {
        graph.putIfAbsent(vertex, new HashMap<>());
    }

    public void removeVertex(VertexT vertex) {
        graph.remove(vertex);
        --vertices;
        //야 이거 vertices가 아니고 edges에서 빼야 되는거 아니냐???
        //현재 지워야 되는 vertex가 목적지인 모든 edge들의 갯수를 센다
        long deletedEdges = graph.values().stream()
                .filter(edge -> edge.containsKey(vertex))
                .count();//왜 deletedVertices냐;; deletedEdges 아니냐;;;
        //현재 지워야 되는 vertex가 목적지인 모든 edge를 지운다
        graph.values().stream()
                .forEach(edge -> edge.remove(vertex));
        this.edges -= deletedEdges;//여기 원래 this.edges -= deletedEdges 아닌가...?
    }

    public void updateVertex(VertexT target, VertexT replace) {
        //replace라는 vertex가 원래 존재하지 않는다면, target이라는 vertex를 replace로 바꾼다.
        //replace가 존재하면 충돌남
        if (!graph.containsKey(replace)) {
            HashMap<VertexT, Integer> edges = graph.get(target);
            graph.remove(target);
            graph.put(replace, edges);
        }
    }

    public void addEdge(WeightedEdge<VertexT> edge) {
        //만약 이미 src가 그래프 안에 있다면 다음과 같은 람다식을 실행한다.
        graph.computeIfPresent(edge.source,
                (src, edgeHashMap) -> { //만약 이미 존재하는 edge이면 기존 edge와 새 edge의 weight를 더한다.
                    if (edgeHashMap.containsKey(edge.destination)) {
                        edgeHashMap.put(edge.destination, edge.weight + edgeHashMap.get(edge.destination));
                    } else { //그게 아니면 그냥 새 edge를 추가
                        edgeHashMap.put(edge.destination, edge.weight);
                        ++edges;
                    }
                    return edgeHashMap;
                });
    }

    public void removeEdge(VertexT source, VertexT destination) {
        //edge가 이미 존재하면 삭제
        graph.computeIfPresent(source, (vertex, edges) -> {
            edges.remove(destination);
            --this.edges;
            return edges;
        });
    }

    public void updateEdge(VertexT source, VertexT destination, int weight) {
        //해당 src와 dst를 가진 edge가 존재하면 weight를 매개변수 weight로 수정
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
