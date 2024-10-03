package student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import graph.*;

public class ProblemSolver implements IProblem {

	@Override
	public <V, E extends Comparable<E>> ArrayList<Edge<V>> mst(WeightedGraph<V, E> g) {
		V firstNode = g.getFirstNode();
		return mstGenerator(g, firstNode);
	}

	/**
	 * Method that generates a minimum spanning tree from the given startnode. 
	 */
	private <V, E extends Comparable<E>> ArrayList<Edge<V>> mstGenerator(WeightedGraph<V, E> g, V startNode) {
		PriorityQueue<Edge<V>> toSearch = new PriorityQueue<>(g);
		ArrayList<Edge<V>> mst = new ArrayList<>();
		HashSet<V> found = new HashSet<>();
		update(g, startNode, found, toSearch);
		while (!toSearch.isEmpty()) {
			Edge<V> edge = toSearch.poll();
			if (found.contains(edge.a) && found.contains(edge.b)) {
				continue;
			}
			V newNode = edge.a;
			if(found.contains(newNode)) {
				newNode = edge.b;
			}
			update(g, newNode, found, toSearch);
			mst.add(edge);
		}
		return mst;
	}

	// Fra oppgavegjennomgang
	private <V, E extends Comparable<E>> void update(Graph<V> g, V node, Collection<V> found, Collection<Edge<V>> toSearch) {
		found.add(node);
		for (Edge<V> e : g.adjacentEdges(node)) {
			if (found.contains(e.a) && found.contains(e.b)) {
				continue;
			}
			toSearch.add(e);
		}
	}

	/**
	 * Method that finds all edges from a node and add them to the PriorityQueue of edges to search through.
	 */
	private <V, E extends Comparable<E>> void addEdges(WeightedGraph<V, E> g, V node, PriorityQueue<Edge<V>> toSearch) {
		for (Edge<V> e : g.adjacentEdges(node)) {
			toSearch.add(e);
		}
	}

	@Override
	public <V> V lca(Graph<V> g, V root, V u, V v) {
		List<V> pathU = findPath(root, u, g);
		List<V> pathV = findPath(root, v, g);

		V lca = root;

		int pathLen = Math.min(pathU.size(), pathV.size());
		for (int i = 0; i < pathLen; i++) {
			if (pathU.get(i).equals(pathV.get(i))) {
				lca = pathU.get(i);
			}
		}
		return lca;
		/*if (root.equals(u) || root.equals(v)) {
			return root;
		}
		V lca = root;
		Stack<V> pathToU = dfs(g, root, u);
		Stack<V> pathToV = dfs(g, root, v);
		for (V node : pathToU) {
			if (!pathToV.contains(node)) {
				break;
			}
			lca = node;
		}
		return lca;*/
	}

	// Fra oppgavegjennomgang
	private <V> List<V> findPath(V root, V target, Graph<V> g) {
		Collection<V> found = new HashSet<>();
		Stack<Edge<V>> toSearch = new Stack<>();
		
		List<V> path = new ArrayList<>();
		path.add(root);
		if (target.equals(root)) {
			return path;
		}
		update(g, root, found, toSearch);
		while (!toSearch.isEmpty()) {
			Edge<V> edge = toSearch.pop();
			if (found.contains(edge.a) && found.contains(edge.b)) {
				continue;
			}
			V last = path.get(path.size()-1);
			while (!last.equals(edge.a) && !last.equals(edge.b)) {
				path.remove(path.size()-1);
				last = path.get(path.size()-1);
			}
			if (found.contains(edge.b)) {
				update(g, edge.a, found, toSearch);
				path.add(edge.a);
			}
			if (found.contains(edge.a)) {
				update(g, edge.b, found, toSearch);
				path.add(edge.b);
			}
			last = path.get(path.size()-1);
			if (last.equals(target)) {
				return path;
			}
		}
		throw new IllegalArgumentException();
	}

	/**
	 * A method that returns the path from a root node to a target node,
	 * by using depth first search. 
	 */
	private <V> Stack<V> dfs(Graph<V> g, V root, V target) {
		Set<V> visited = new HashSet<>();
		Stack<V> path = new Stack<>();
		dfsRecursive(g, root, target, visited, path);
		return path;
	}

	/**
	 * A method that searches for a path from the root node to a target node,
	 * using a depth first search recursively. Returns true if a path was found.
	 */
	private <V> boolean dfsRecursive(Graph<V> g, V root, V target, Set<V> visited, Stack<V> path) {
		visited.add(root);
		path.push(root);
		if (root.equals(target)) {
			return true;
		}
		for (V node : g.neighbours(root)) {
			if (!visited.contains(node)) {
				if (dfsRecursive(g, node, target, visited, path)) {
					return true;
				}
			}
		}
		path.pop();
		return false;
	}

	@Override
	public <V> Edge<V> addRedundant(Graph<V> g, V root) {
		Map<V, Integer> subtrees = new HashMap<>();
		Set<V> found = new HashSet<>();
		generateTrees(g, root, subtrees, found);
		V largestSubtree = findLargestSubtree(g, root, subtrees);
		found.clear();
		V edgeA = findNode(g, largestSubtree, subtrees, found);
		subtrees.remove(largestSubtree);
		V secondLargestSubtree = findLargestSubtree(g, root, subtrees);
		found.clear();
		V edgeB = findNode(g, secondLargestSubtree, subtrees, found);
		Edge<V> edge = new Edge<>(edgeA, edgeB);
		return edge;
	}

	/**
	 * Method that finds the last node in the largest subtree.
	 */
	private <V> V findNode(Graph<V> g, V node, Map<V, Integer> subtrees, Set<V> found) {
		V largestSubTree = findLargestSubtree(g, node, subtrees);
		if (found.contains(largestSubTree)) {
			return largestSubTree;
		}
		found.add(largestSubTree);
		return findNode(g, largestSubTree, subtrees, found);
	}

	/**
	 * Method that finds the largest subtree of the subtrees generated by finding the subtree
	 * of each child of a node.
	 */
	private <V> V findLargestSubtree(Graph<V> g, V root, Map<V, Integer> subtrees) {
		int size = 0;
		V largestSubtree = root;
		for (V node : g.neighbours(root)) {
			if (subtrees.containsKey(node) && subtrees.get(node) < subtrees.get(root)) {
				if (subtrees.get(node) > size) {
					size = subtrees.get(node);
					largestSubtree = node;
				}
			}
		}
		return largestSubtree;
	}

	/**
	 * Method that generates the subtrees of a tree where the root in each tree is
	 * the child of the root in the original tree. Adding all the subtrees to a map
	 * where the key is the root in the subtree and the value is the size of the subtree.
	 */
	private <V> int generateTrees(Graph<V> g, V node, Map<V, Integer> subtrees, Set<V> found) {
		found.add(node);
		int size = 1;
		for (V neighbour : g.neighbours(node)) {
			if (!found.contains(neighbour)) {
				size += generateTrees(g, neighbour, subtrees, found);
			}
		}
		subtrees.put(node, size);
		return size;
	}
}