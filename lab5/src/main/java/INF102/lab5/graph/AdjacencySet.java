package INF102.lab5.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AdjacencySet<V> implements IGraph<V> {

    private Set<V> nodes;
    private Map<V, Set<V>> nodeToNode;

    public AdjacencySet() {
        nodes = new HashSet<>();
        nodeToNode = new HashMap<>();
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public Set<V> getNodes() {
        return Collections.unmodifiableSet(nodes);
    }

    @Override
    public void addNode(V node) {
        if (!hasNode(node)) {
            nodes.add(node);
            nodeToNode.put(node, new HashSet<>());
        }
    }

    @Override
    public void removeNode(V node) {
        if (hasNode(node)) {
            nodes.remove(node);
            nodeToNode.remove(node);
        }
    }

    @Override
    public void addEdge(V u, V v) {
        if (!hasNode(u) || !hasNode(v)) {
            throw new IllegalArgumentException("Could not find both nodes in the graph.");
        }
        if (!hasEdge(u, v) && !hasEdge(v, u)) {
            Set<V> neighboursU = nodeToNode.get(u);
            neighboursU.add(v);
            nodeToNode.put(u, neighboursU);
            
            Set<V> neighboursV = nodeToNode.get(v);
            neighboursV.add(u);
            nodeToNode.put(v, neighboursV);
        }
    }

    @Override
    public void removeEdge(V u, V v) {
        if (!hasNode(u) || !hasNode(v)) {
            throw new IllegalArgumentException("Could not find both nodes in the graph.");
        }
        if (hasEdge(u, v) && hasEdge(v, u)) {
            Set<V> neighboursU = nodeToNode.get(u);
            neighboursU.remove(v);
            nodeToNode.put(u, neighboursU);
            
            Set<V> neighboursV = nodeToNode.get(v);
            neighboursV.remove(u);
            nodeToNode.put(v, neighboursV);
        }
    }

    @Override
    public boolean hasNode(V node) {
        return nodes.contains(node);
    }

    @Override
    public boolean hasEdge(V u, V v) {
        return nodeToNode.get(u).contains(v);
    }

    @Override
    public Set<V> getNeighbourhood(V node) {
        return Collections.unmodifiableSet(nodeToNode.get(node));
    }

    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();
        for (V node : nodeToNode.keySet()) {
            Set<V> nodeList = nodeToNode.get(node);

            build.append(node);
            build.append(" --> ");
            build.append(nodeList);
            build.append("\n");
        }
        return build.toString();
    }

}
