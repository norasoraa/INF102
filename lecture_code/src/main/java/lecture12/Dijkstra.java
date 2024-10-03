package lecture12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import graph.Edge;
import graph.WeightedGraph;

public class Dijkstra<V> {

	WeightedGraph<V, Integer> graph;
	HashMap<V,Integer> distances = new HashMap<>();
	HashSet<V> found = new HashSet<>();
	PriorityQueue<Pair<Edge<V>,Integer>> toSearch = new PriorityQueue<>();
	
	public Dijkstra(WeightedGraph<V, Integer> graph) {
		this.graph = graph;
	}

	void addVertex(V v, int dist) {
		distances.put(v, dist);
		found.add(v);
		addNeighbours(v);
	}

	public void addNeighbours(V newNode) {
		for(V v : graph.neighbours(newNode)) {
			if(found.contains(v))
				continue;

			Edge<V> edge = new Edge<V>(newNode,v);
			int dist = distances.get(newNode)+graph.getWeight(edge);
			toSearch.add(new Pair<Edge<V>, Integer>(edge, dist));
		}
	}

	public HashMap<V,Integer> search(V start){
		addVertex(start,0);
		
		while(!toSearch.isEmpty()) {
			Pair<Edge<V>, Integer> pair = toSearch.poll();
			Edge<V> e = pair.key;
			
			if(found.contains(e.a) && found.contains(e.b)) //cycle found
				continue;
			
			V newNode;
			if(found.contains(e.a)) {
				newNode = e.b;
			}
			else {
				newNode = e.a;				
			}

			addVertex(newNode, pair.value);
		}
		
		return distances;
	}
	
	//static methods to make it easier to use
	
	public static <V> HashMap<V,Integer> shortestPath(WeightedGraph<V, Integer> graph, V start){
		
		return new Dijkstra<V>(graph).search(start);
	}

	public static void main(String[] args) {
		HashMap<String, Integer> shortest = shortestPath(makeSampleGraph(), "a");
		for(String v : shortest.keySet())
			System.out.println("Path from a to "+v+": "+shortest.get(v));
	}
	
	/**
	 *   f - e - g
	 *  / | 
	 * a - b 
	 * | \ | 
	 * c - d
	 */
	static WeightedGraph<String,Integer> makeSampleGraph() {
		WeightedGraph<String,Integer> g = new WeightedGraph<String,Integer>();
		g.addVertex("a");
		g.addVertex("b");
		g.addVertex("c");
		g.addVertex("d");
		g.addVertex("e");
		g.addVertex("f");
		g.addVertex("g");
		g.addEdge("a", "b",5);
		g.addEdge("a", "c",7);
		g.addEdge("a", "d",12);		
		g.addEdge("a", "f",3);		
		g.addEdge("b", "d",9);
		g.addEdge("b", "f",5);
		g.addEdge("c", "d",4);
		g.addEdge("e", "f",11);
		g.addEdge("e", "g",3);
		return g;
	}
}