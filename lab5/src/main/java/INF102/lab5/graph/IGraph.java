package INF102.lab5.graph;

import java.util.Set;

public interface IGraph<V> {

    /**
     * Number of nodes in the graph
     * @return number of nodes in graph
     */
    public int size();

    /**
     * Get all nodes in graph
     * @return set of graph nodes
     */
    public Set<V> getNodes();

    /**
     * Add <code>node</code> to graph
     * @param node
     */
    public void addNode(V node);

    /**
     * Remove <code>node</code> from graph
     * @param node
     */
    public void removeNode(V node);

    /**
     * Add edge between node <code>u</code> and <code>v</code>
     * @param u
     * @param v
     * @throws IllegalArgumentException if the nodes given are not in the graph
     */
    public void addEdge(V u, V v);

    /**
     * Remove edge between node <code>u</code> and <code>v</code>
     * @param u
     * @param v
     */
    public void removeEdge(V u, V v);

    /**
     * Checks if there is a node <code>node</code> in the graph
     * @param node
     * @return true if node is in graph
     */
    public boolean hasNode(V node);

    /**
     * Checks if there is an edge between node <code>u</code> and <code>v</code>
     * @param u
     * @param v
     * @return true if there is an edge between <code>u</code> and <code>v</code>
     */
    public boolean hasEdge(V u, V v);

    /**
     * Finds all neighbours of node <code>u</code>. 
     * The neighbours of a node is all nodes which it has an edge to.
     * @param node
     * @return list of all neighbours
     */
    public Set<V> getNeighbourhood(V node);
    
}
