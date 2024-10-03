package INF102.lab6.graph;

import java.util.Comparator;
import java.util.HashMap;

public class WeightedDirectedGraph<V, E extends Comparable<E>> extends DirectedGraph<V> implements Comparator<DirectedEdge<V>>{

    private HashMap<DirectedEdge<V>, E> weights;

    public WeightedDirectedGraph() {
        weights = new HashMap<>();
    }

    public E getWeight(V u, V v) {
        return weights.get(new DirectedEdge<V>(u, v));
    }

	@Override
	public int compare(DirectedEdge<V> arc1, DirectedEdge<V> arc2) {
		return getWeight(arc1).compareTo(getWeight(arc2));
	}

	public E getWeight(DirectedEdge<V> arc) {
		return getWeight(arc.from, arc.to);
	}

	public void addEdge(DirectedEdge<V> e, E weight) {
		super.addEdge(e.from,e.to);
		weights.put(e, weight);
	}

	public boolean addEdge(V from, V to, E weight) {
		DirectedEdge<V> e = new DirectedEdge<V>(from, to);
		weights.put(e, weight);
		return super.addEdge(from,to);
	}

	@Override
	public boolean addEdge(V from, V to) {
		throw new UnsupportedOperationException("Can not add edge without weight");
	}
	
	@Override
	public void addEdge(DirectedEdge<V> e) {
		throw new UnsupportedOperationException("Can not add edge without weight");
	}

}
