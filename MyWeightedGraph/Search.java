package MyWeightedGraph;

import java.util.*;

public class Search<T> {
    protected int count;
    protected Set<Vertex<T>> visited;
    protected Map<Vertex<T>, Vertex<T>> edgeTo;
    protected final Vertex<T> source;

    public Search(Vertex<T> source) {
        this.source = source;
        visited = new HashSet<>();
        edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(Vertex<T> vertex) {
        return visited.contains(vertex);
    }

    public LinkedList<Vertex<T>> pathTo(Vertex<T> vertex) {
        if (!hasPathTo(vertex)) return null;
        LinkedList<Vertex<T>> list = new LinkedList<>();

        Vertex<T> cursor = vertex;
        while (cursor != source) {
            list.push(cursor);
            cursor = edgeTo.get(cursor);
        }
        list.push(source);

        return list;
    }

    public int getCount() {
        return count;
    }
}




