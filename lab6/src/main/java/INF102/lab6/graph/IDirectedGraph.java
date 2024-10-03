package INF102.lab6.graph;

/**
 * This interface represents a graph
 * A graph consists of a set of vertices and edges.
 * Edges connects pairs of vertices.
 * 
 * @author Martin Vatshelle
 *
 * @param <V> The type of vertices in the graph
 */
public interface IDirectedGraph<V> {

    /**
     * Gives a way to iterate through all vertices of this graph
     */
    public Iterable<V> vertices();

    /**
     * Gives a way to iterate through all arcs of this graph
     */
    public Iterable<DirectedEdge<V>> arcs();

    /**
     * Checks if there is an arc from one given vertex to another given vertex
     * 
     * @return true if both vertices are in the graph and there is an arc from
     * the first vertex to the second vertex
     */
    public boolean adjacent(V from, V to);

    /**
     * Gives a way to iterate through the out-neighbours of a vertex
     */
    public Iterable<V> outNeighbours(V v);

    /**
     * Counts the number of out-neighbours a vertex has
     */
    public int outDegree(V v);

    /**
     * Gives a way to iterate through the in-neighbours of a vertex
     */
	Iterable<V> inNeighbours(V v);

	/**
     * Counts the number of in-neighbours a vertex has
     */
    public int inDegree(V v);
}
