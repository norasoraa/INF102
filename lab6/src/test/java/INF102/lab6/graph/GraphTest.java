package INF102.lab6.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {

	DirectedGraph<String> g;

	/**
	 * a -> b -> c -> d -> e -> g
	 *       \            /
	 *         --> f <-- 
	 */
	void makeSampleGraph() {
		g.addVertex("a");
		g.addVertex("b");
		g.addVertex("c");
		g.addVertex("d");
		g.addVertex("e");
		g.addVertex("f");
		g.addVertex("g");
		g.addEdge("a", "b");
		g.addEdge("b", "c");
		g.addEdge("b", "f");
		g.addEdge("c", "d");
		g.addEdge("d", "e");
		g.addEdge("e", "f");
		g.addEdge("e", "g");
	}

	@BeforeEach
	void setUp() throws Exception {
		g = new DirectedGraph<String>();
	}

	@SuppressWarnings("unused")
	@Test
	void testGraph() {
		for (String v : g.vertices()) {
			fail("Initial graph should not have any vertices");
		}
	}

	@Test
	void testAdjacent() {
		makeSampleGraph();
		assertTrue(g.adjacent("a", "b"));
		assertTrue(g.adjacent("c", "d"));
		assertTrue(g.adjacent("e", "g"));
		assertFalse(g.adjacent("a", "c"));
		assertFalse(g.adjacent("f", "d"));
		assertFalse(g.adjacent("g", "d"));
	}

	@Test
	void testAdjacentDirected() {
		makeSampleGraph();
		assertTrue(g.adjacent("a", "b"));
		assertFalse(g.adjacent("b", "a"));

		assertTrue(g.adjacent("c", "d"));
		assertFalse(g.adjacent("d", "c"));

		assertTrue(g.adjacent("e", "g"));
		assertFalse(g.adjacent("g", "e"));

		assertFalse(g.adjacent("a", "c"));
		assertFalse(g.adjacent("c", "a"));

		assertFalse(g.adjacent("f", "d"));
		assertFalse(g.adjacent("d", "f"));

		assertFalse(g.adjacent("g", "d"));
		assertFalse(g.adjacent("d", "g"));
	}

	@Test
	void testOutNeighbours() {
		makeSampleGraph();
		HashSet<String> found = new HashSet<String>();
		for (String s : g.outNeighbours("b")) {
			found.add(s);
		}
		HashSet<String> expected = new HashSet<String>();
		expected.add("c");
		expected.add("f");
		assertEquals(expected, found);

		for (DirectedEdge<String> e : g.outArcs("b")) {
			assertEquals("b",e.from);
			assertTrue(g.adjacent(e.from,e.to));
			assertTrue(expected.contains(e.to));
		}
	}

	@Test
	void testInNeighbours() {
		makeSampleGraph();
		HashSet<String> found = new HashSet<String>();
		for (String s : g.inNeighbours("f")) {
			found.add(s);
		}
		HashSet<String> expected = new HashSet<String>();
		expected.add("b");
		expected.add("e");
		assertEquals(expected, found);

		for (DirectedEdge<String> e : g.inArcs("f")) {
			assertEquals("f",e.to);
			assertTrue(g.adjacent(e.from,e.to));
			assertTrue(expected.contains(e.from));
		}
	}

	@Test
	void testDegree() {
		makeSampleGraph();
		assertEquals(0, g.outDegree("g"));
		assertEquals(1, g.outDegree("c"));
		assertEquals(2, g.outDegree("b"));

		assertEquals(0, g.inDegree("a"));
		assertEquals(1, g.inDegree("b"));
		assertEquals(2, g.inDegree("f"));

	}

	@Test
	void testAddVertex() {
		HashSet<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		for (String v : set) {
			assertTrue(g.addVertex(v), "Should return true when adding a new vertex");
		}

		HashSet<String> found = new HashSet<String>();
		for (String v : g.vertices()) {
			found.add(v);
		}

		assertTrue(set.containsAll(found), "Graph contains node that was not added.");
		assertTrue(found.containsAll(set), "Graph is missing node that was added.");

		assertFalse(g.addVertex("a"), "Should returne false when trying to add an existing vertex");
	}

	@Test
	void testAddOneEdge() {
		HashSet<String> nodes = new HashSet<String>();
		nodes.add("s");
		nodes.add("t");

		for (String v : nodes) {
			g.addVertex(v);
		}
		g.addEdge("s", "t");

		for (DirectedEdge<String> e : g.arcs()) {
			assertTrue(nodes.contains(e.from));
			assertTrue(nodes.contains(e.to));
			for (String v : nodes) {
				assertTrue(v.equals(e.from) || v.equals(e.to));
			}
		}
		assertTrue(g.adjacent("s", "t"));
		assertFalse(g.adjacent("t", "s"));
	}

	@Test
	void testEdgeCount() {
		g.addVertex("s");
		g.addVertex("t");
		g.addEdge("s", "t");
		int edgeCount = 0;
		for (DirectedEdge<String> e : g.arcs()) {
			if (e == null)
				fail("Edges should not be null");
			else
				edgeCount++;
		}
		assertEquals(1, edgeCount);
	}

	@Test
	void testRemoveEdge() {
		makeSampleGraph();
		assertTrue(g.removeEdge("c", "d"));
		assertFalse(g.adjacent("c", "d"));
		assertFalse(g.adjacent("d", "s"));
		assertFalse(g.removeEdge("c", "d"));

		assertFalse(g.removeEdge("c", "h"));
	}

	@Test
	void protectedIterable() {
		makeSampleGraph();
		Iterable<String> iter = g.vertices();
		try {
			HashSet<String> set = (HashSet<String>) iter;
			set.remove("a");
		} catch (Exception e) {
			// This should happen
			return;
		}
		fail("We were able to delete a vertex from the Iterable");
	}

	@Test
	void protectedIterator() {
		makeSampleGraph();
		Iterator<String> iterator = g.vertices().iterator();
		String first = iterator.next();

		try {
			iterator.remove();
		} catch (Exception e) {

			return;
		}
		iterator = g.vertices().iterator();
		assertEquals(first, iterator.next());
	}

	@Test
	void testNum() {
		makeSampleGraph();
		assertEquals(7, g.numVertices());
		assertEquals(7, g.numArcs());
	}

	@Test
	void testIteratePairs() {
		makeSampleGraph();
		int numPairs = 0;
		int numArcs = 0;
		for (String a : g.vertices()) {
			for (String b : g.vertices()) {
				numPairs++;
				if (g.adjacent(a, b))
					numArcs++;
			}
		}
		assertEquals(49, numPairs);
		assertEquals(g.numArcs(), numArcs);
	}

	@Test
	void testAddLoop() {
		g.addVertex("a");
		try {
			g.addEdge("a", "a");
			assertTrue(g.numArcs() <= 1);
		} catch (IllegalArgumentException e) {
			return;
		}
		fail("Trying to add a loop to the graph should give IllegalArgumentException.");
	}

	@Test
	void testAddExistingVertex() {
		g.addVertex("a");
		g.addVertex("b");
		g.addEdge("a", "b");
		assertFalse(g.addVertex("a"));
		assertFalse(g.addEdge("a", "b"));
	}
	
	@Test
	void testRemoveVertex() {
		makeSampleGraph();
		g.removeVertex("b");
		assertEquals(4, g.numArcs());
		assertFalse(g.adjacent("a", "b"));
		assertFalse(g.adjacent("b", "c"));
		assertFalse(g.adjacent("b", "f"));
	}
}
