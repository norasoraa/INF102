package lecture13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import graph.Edge;
import graph.WeightedGraph;

public class Kruskal {

	public <T, E extends Comparable<E>> ArrayList<Edge<T>> mst(WeightedGraph<T, E> g){
		ArrayList<Edge<T>> mst = new ArrayList<Edge<T>>();

		UnionFind<T> connected = new UnionFindBySize<>(g.vertices());

		ArrayList<Edge<T>> edges = new ArrayList<>();
		
		for(Edge<T> e: g.edges()){
			edges.add(e);
		}
		
		Collections.sort(edges,g);
		
		for(Edge<T> e : edges) {
			if(!connected.same(e.a, e.b)) {
				mst.add(e);
				connected.union(e.a, e.b);
			}
		}
		
		return mst;
	}
}
