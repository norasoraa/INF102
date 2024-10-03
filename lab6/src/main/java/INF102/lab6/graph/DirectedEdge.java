package INF102.lab6.graph;

import java.util.Objects;

/**
 * Represents an edge in a graph.
 * 
 * @author Martin Vatshelle
 *
 * @param <V>
 */

public class DirectedEdge<V> {

    // We make variables public final because we don't want edges
    // to change once they have been made
    // (we could achieve the same by having get method but not set method)
	public final V from;
	public final V to;
	
    public DirectedEdge(V from, V to) {
        if (from.equals(to))
            throw new IllegalArgumentException("Loops are not allowed");
        if (from == null || to == null)
            throw new IllegalArgumentException("Nodes can not be null");
        this.from = from;
        this.to = to;
    }

    // Two edges are considered equal if they connects the same two vertices.
    // so a--b is equal to b--a
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof DirectedEdge) {
            @SuppressWarnings("unchecked")
            DirectedEdge<V> e = (DirectedEdge<V>) obj;
            return e.from.equals(this.from) && e.to.equals(this.to);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from,to);
    }

    @Override
    public String toString() {
        return "(" + from.toString() + " -> " + to.toString() + ")";
    }
}
