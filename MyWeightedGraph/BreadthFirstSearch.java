package MyWeightedGraph;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<T> extends Search<T>{

    public BreadthFirstSearch(MyWeightedGraph<T> graph, Vertex<T> source) {
        super(source);
        bfs(graph, source);
    }

    private void bfs(MyWeightedGraph<T> graph, Vertex<T> current) {
        visited.add(current);
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(current);
        while (!queue.isEmpty()) {
            Vertex<T> v = queue.remove();

            for (Vertex<T> vertex : graph.adjacencyList(v)) {
                if (!visited.contains(vertex)) {
                    visited.add(vertex);
                    edgeTo.put(vertex, v);
                    queue.add(vertex);
                }
            }
        }
    }
}
