package INF102.lab6.graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EdgeTest {

	@Test
	void testEdge() {
		DirectedEdge<String> e = new DirectedEdge<String>("a", "b");
		assertEquals("a", e.from);
		assertEquals("b", e.to);
	}

	@Test
	void testEqualsSame() {
		DirectedEdge<String> e1 = new DirectedEdge<String>("a", "b");
		DirectedEdge<String> e2 = new DirectedEdge<String>("a", "b");
		DirectedEdge<String> e3 = new DirectedEdge<String>("a", "c");
		assertEquals(e1, e2);

		assertNotEquals(e1,e3);
	}

	@Test
	void testNotEqualsReversed() {
		DirectedEdge<String> e1 = new DirectedEdge<String>("a", "b");
		DirectedEdge<String> e2 = new DirectedEdge<String>("b", "a");
		assertNotEquals(e1, e2);
	}

	@Test
	void testConstructNull() {

		try {
			@SuppressWarnings("unused")
			DirectedEdge<String> e = new DirectedEdge<String>("b", null);
		} catch (IllegalArgumentException e) {
			return;
		}
		fail("No Exception caught");
	}

	@Test
	void testConstructLoop() {
		try {
			@SuppressWarnings("unused")
			DirectedEdge<String> e = new DirectedEdge<String>("a", "a");
		} catch (IllegalArgumentException e) {
			return;
		}
		fail("No Exception caught");
	}
}
