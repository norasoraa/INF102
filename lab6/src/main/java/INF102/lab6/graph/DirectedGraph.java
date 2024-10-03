package INF102.lab6.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This graph is a simple directed graph.
 * This means no loops or multiple arcs are allowed
 * Data invariant:
 * - a is in vertices if and only if a is a key in both outNeighbours and inNeighbours
 * - an arc from a to b can only exist if both a and b are vertices of the graph
 * 
 * @author Martin Vatshelle & Sondre Sæther Bolland
 *
 * @param <V>
 */
public class DirectedGraph<V> implements IDirectedGraph<V> {

    // Edges
    private HashMap<V, HashSet<V>> outNeighbours;
    private HashMap<V, HashSet<V>> inNeighbours;
    private int edges;

    /**
     * Construct an empty graph
     */
    public DirectedGraph() {
        this.outNeighbours = new HashMap<V, HashSet<V>>();
        this.inNeighbours = new HashMap<V, HashSet<V>>();
    }

    @Override
    public Iterable<V> vertices() {
        return Collections.unmodifiableSet(outNeighbours.keySet());
    }

    @Override
    public ArrayList<DirectedEdge<V>> arcs() {
        ArrayList<DirectedEdge<V>> edges = new ArrayList<DirectedEdge<V>>();
        for (V from : vertices()) {
            for (V to : outNeighbours(from)) {
                edges.add(new DirectedEdge<V>(from, to));
            }
        }
        return edges;
    }

    @Override
    public boolean adjacent(V from, V to) {
        try {
            checkVertex(from);
            checkVertex(to);
        } catch (IllegalArgumentException e) {
            return false;
        }

        return outNeighbours.get(from).contains(to);
    }

    /**
     * Finds all arcs from v
     * 
     * @return all edges found
     */
    public Iterable<DirectedEdge<V>> outArcs(V v) {
        ArrayList<DirectedEdge<V>> arcs = new ArrayList<DirectedEdge<V>>();
        for (V neighbour : outNeighbours(v)) {
            arcs.add(new DirectedEdge<V>(v, neighbour));
        }
        return arcs;
    }

    /**
     * Finds all arcs to v
     * 
     * @return all edges found
     */
    public Iterable<DirectedEdge<V>> inArcs(V v) {
        ArrayList<DirectedEdge<V>> arcs = new ArrayList<DirectedEdge<V>>();
        for (V neighbour : inNeighbours(v)) {
            arcs.add(new DirectedEdge<V>(neighbour,v));
        }
        return arcs;
    }

    @Override
    public Iterable<V> outNeighbours(V v) {
        checkVertex(v);        
        return Collections.unmodifiableSet(outNeighbours.get(v));
    }

    @Override
    public Iterable<V> inNeighbours(V v) {
        checkVertex(v);        
        return Collections.unmodifiableSet(inNeighbours.get(v));
    }

    /**
     * @return number of vertices in the graph
     */
    public int numVertices() {
        return outNeighbours.size();
    }

    /**
     * @return number of edges in the graph
     */
    public int numArcs() {
        return edges;
    }

    /**
     * Checks that the node is in this graph, throws exception if not
     */
    private void checkVertex(V v) {
        if (!outNeighbours.containsKey(v))
            throw new IllegalArgumentException("The node " + v + " is not in this graph.");
    }

    @Override
    public int outDegree(V v) {
        return outNeighbours.get(v).size();
    }

    @Override
    public int inDegree(V v) {
        return inNeighbours.get(v).size();
    }

    // Add and remove methods
    /**
     * Adds a vertex to the graph if not already in the graph
     * 
     * @param v the vertex to add
     * @return true if a vertex was added, false otherwise
     */
    public boolean addVertex(V v) {
        if (!outNeighbours.containsKey(v)) {
            outNeighbours.put(v, new HashSet<V>());
            inNeighbours.put(v, new HashSet<V>());
            return true;
        }
        return false;
    }

    /**
     * This method connects two vertices by a directed edge
     * 
     * @param the vertices to connect
     * @return true if an edge was added, false otherwise
     */
    public boolean addEdge(V from, V to) {
        if (from.equals(to))
            throw new IllegalArgumentException("Adding loops to the graph is not supported.");

        if (adjacent(from,to))
            return false;
        else {
            outNeighbours.get(from).add(to);
            inNeighbours.get(to).add(from);
            edges++;
            return true;
        }
    }

    public void addEdge(DirectedEdge<V> e) {
        addEdge(e.from, e.to);
    }

    /**
     * Removes a vertex from the graph if the vertex exist
     * 
     * @param a the vertex to remove
     * @return true if a vertex was removed, false otherwise
     */
    public boolean removeVertex(V a) {
        try {
            checkVertex(a);
        } catch (IllegalArgumentException e) {
            return false;
        }
        ArrayList<DirectedEdge<V>> toRemove = new ArrayList<>();
        for(DirectedEdge<V> e : inArcs(a))
        	toRemove.add(e);
        for(DirectedEdge<V> e : outArcs(a))
        	toRemove.add(e);

        for(DirectedEdge<V> e : toRemove)
        	removeEdge(e);
        
        inNeighbours.remove(a);
        outNeighbours.remove(a);
        return true;
    }

    /**
     * Removes an edge from the graph if the edge exist
     * 
     * @param a,b the pair of vertices to remove the edge from
     * @return true if an edge was removed, false otherwise
     */
    public boolean removeEdge(V a, V b) {
        try {
            checkVertex(a);
            checkVertex(b);
        } catch (IllegalArgumentException e) {
            return false;
        }
        boolean change = outNeighbours.get(a).remove(b);
        change |= inNeighbours.get(b).remove(a);
        if (change)
            edges--;
        return change;
    }

    public boolean removeEdge(DirectedEdge<V> e) {
        return removeEdge(e.from, e.to);
    }

    /**
     * Creates a copy of the graph
     */
    public DirectedGraph<V> clone() {
        DirectedGraph<V> g = new DirectedGraph<V>();
        for (V v : vertices())
            g.addVertex(v);
        for (DirectedEdge<V> e : arcs())
            g.addEdge(e.from, e.to);
        return g;
    }

}
