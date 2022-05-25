package MyWeightedGraph;

import java.util.HashSet;
import java.util.Set;

public class MyWeightedGraph<T> {
    private final boolean undirected;
    private Set<Vertex<T>> vertices;

    //constructors
    public MyWeightedGraph() {
        this.undirected = true;
        vertices = new HashSet<>();
    }
    public MyWeightedGraph(boolean undirected) {
        this.undirected = undirected;
        vertices = new HashSet<>();
    }

    //methods
    public Vertex<T> getVertex(T data){
        for (Vertex<T> vertex: vertices){
            if (vertex.equals(new Vertex<>(data))) return vertex;
        } return null;
    }

    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
    }

//    public void addEdge(T source, T dest, double weight){
//        Vertex<T> sourceVertex, destVertex;
//
//        sourceVertex = getVertex(source) == null ? new Vertex<>(source) : getVertex(source);
//        destVertex = getVertex(dest) == null ? new Vertex<>(dest) : getVertex(dest);
//
//        if (hasVertex(sourceVertex) || hasVertex(destVertex)) return;   //doublecheck
//
//        sourceVertex.addEdge(destVertex, weight);
//        if (undirected) destVertex.addEdge(sourceVertex, weight);
//    }

    public void addEdge(Vertex<T> source, Vertex<T> dest, double weight) {
        source.addEdge(dest, weight);
        if (undirected) dest.addEdge(source, weight);   //if graph is undirected, then add reverse edge
    }

    public int getVerticesNumber() {
        return vertices.size();
    }

    public int getEdgesNumber() {
        int count = 0;

        for (Vertex<T> vertex : vertices) {
            int degree = vertex.degreeOfVertex();
            count += degree;
        }

        if (undirected) return count / 2;

        return count;
    }

    public boolean hasVertex(Vertex<T> vertex) {
        return vertices.contains(vertex);
    }

    public boolean hasEdge(Vertex<T> source, Vertex<T> dest) {
        return hasVertex(source) && source.hasAdjacentVertex(dest);
    }

    public Set<Vertex<T>> adjacencyList(Vertex<T> vertex) {
        return !hasVertex(vertex) ? null : vertex.getAdjacentVertices();
    }
}
