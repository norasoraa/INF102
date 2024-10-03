package INF102.lab5.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * This class is used to conduct search algorithms of a graph
 */
public class GraphSearch<V> implements IGraphSearch<V> {

    private IGraph<V> graph;

    public GraphSearch(IGraph<V> graph) {
        this.graph = graph;
    }

    @Override
    public boolean connected(V u, V v) {
        if (graph.hasEdge(u, v)) {
            return true;
        }
        return findPathIterative(u, v);
        //return fasitConnected(u, v);
    }

    /**
     * Help method that searches for a path from node u to node v,
     * using a breadth first search iteratively. Returns true if a path was found.
     */
    private boolean findPathIterative(V u, V v) {
        Set<V> found = new HashSet<>();
        Queue<V> toSearch = new LinkedList<>();
        toSearch.add(u);
        while (!toSearch.isEmpty()) {
            V currentNode = toSearch.poll();
            if (graph.hasEdge(currentNode, v)) {
                return true;
            }
            found.add(currentNode);
            for (V node : graph.getNeighbourhood(currentNode)) {
                if (!found.contains(node) && !toSearch.contains(node)) {
                    toSearch.add(node);
                }
            }
        }
        return false;
    }
    
    //fra oppgavegjennomgang 
    public boolean fasitConnected(V u, V v) {
        Map<V, Boolean> visited = new HashMap<>();
        for (V node : graph.getNodes()) {
            visited.put(node, false);
        }
        //return dfs(u, v, visited);
        return bfs(u, v, visited);
    }

    //fra oppgavegjennomgang 
    private boolean bfs(V root, V target, Map<V, Boolean> visited) {
        Queue<V> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            V current = q.poll();
            visited.put(current, true);
            for (V next : graph.getNeighbourhood(current)) {
                if (next.equals(target)) {
                    return true;
                }
                if (visited.get(next)) {
                    continue;
                }
                q.add(next);
            }
        }
        return false;
    }

    //fra oppgavegjennomgang 
    private boolean dfs(V root, V target, Map<V, Boolean> visited) {
        visited.put(root, true);
        for (V next : graph.getNeighbourhood(root)) {
            if (next.equals(target)) {
                return true;
            }
            if (!visited.get(next)) {
                if (dfs(next, target, visited)) {
                    return true;
                }
            }
        }
        return false;
    }


}