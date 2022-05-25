package MyWeightedGraph;

import java.util.HashSet;
import java.util.Set;

public class MyGraph<T> {
    private final boolean undirected;
    private Set<Vertex<T>> vertices;

    //constructors
    public MyGraph() {
        this.undirected = true;
        vertices = new HashSet<>();
    }
    public MyGraph(boolean undirected) {
        this.undirected = undirected;
        vertices = new HashSet<>();
    }

    //methods
    public void addVertex(T data) {
        Vertex<T> newVertex = new Vertex<>(data);
        vertices.add(newVertex);
    }

    public void addEdge(Vertex<T> source, Vertex<T> dest) {
        source.addEdge(dest, 0D);
        if (undirected) dest.addEdge(source, 0D);
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
