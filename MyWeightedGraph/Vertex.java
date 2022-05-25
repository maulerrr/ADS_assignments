package MyWeightedGraph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Vertex<T> {
    private T data;
    private Map<Vertex<T>, Double> adjacentVertices;

    public Vertex(T data){
        this.data = data;
        adjacentVertices = new HashMap<>();
    }

    public void addAdjacentVertex(Vertex<T> dest, double weight){
        adjacentVertices.put(dest, weight);
    }

    public void addEdge(Vertex<T> dest, Double weight) {
        adjacentVertices.put(dest, weight);
    }

    public boolean hasAdjacentVertex(Vertex<T> vertex){
        return adjacentVertices.containsKey(vertex);
    }

    public Double getWeight(Vertex<T> vertex){
        return adjacentVertices.get(vertex);
    }

    public T getData() {
        return data;
    }

    public Set<Vertex<T>> getAdjacentVertices() {
        return adjacentVertices.keySet();
    }

    public int degreeOfVertex(){
        int count = 0;
        for (Vertex<T> vertex : adjacentVertices.keySet()) count++;
        return count;
    }
}