package MyWeightedGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DijkstraSearch<T> extends Search<T> {
    private Set<Vertex<T>> unsettledNodes;
    private Map<Vertex<T>, Double> distances;
    private MyWeightedGraph<T> graph;

    public DijkstraSearch(MyWeightedGraph<T> graph, Vertex<T> source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;
        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (unsettledNodes.size() > 0) {
            Vertex<T> vertex = getVertexWithMinimumWeight(unsettledNodes);
            visited.add(vertex);
            unsettledNodes.remove(vertex);

            for (Vertex<T> target : graph.adjacencyList(vertex)) {
                double distance = getShortestDistance(vertex) + getDistance(vertex, target);

                if (getShortestDistance(target) > distance) {
                    distances.put(target, distance);
                    edgeTo.put(target, vertex);
                    unsettledNodes.add(target);
                }
            }
        }
    }

    private double getDistance(Vertex<T> node, Vertex<T> target) {
        return node.getWeight(target);
    }


    private Vertex<T> getVertexWithMinimumWeight(Set<Vertex<T>> vertices) {
        Vertex<T> minimum = null;
        for (Vertex<T> vertex : vertices) {
            if (minimum == null) minimum = vertex;
            else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) minimum = vertex;
            }
        }
        return minimum;
    }

    private double getShortestDistance(Vertex<T> destination) {
        Double distance = distances.get(destination);
        return (distance == null ? Double.MAX_VALUE : distance);
    }
}
