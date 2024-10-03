package INF102.lab5.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class GraphTest {

    final int N_NODES = 1000;

    Random random = new Random();

    /**
     * Adds edges of all nodes from 0 to N, i.e.:
     * (0 -> 1), (1 -> 2), ..., (N-1 -> N)
     * 
     * @param graph
     */
    public void createConnectedGraph(IGraph<Integer> graph) {
        for (int i = 0; i < N_NODES - 1; i++) {
            graph.addEdge(i, i + 1);
        }
    }

    public void addEdgeToNodeNotInGraph(IGraph<Integer> graph) {
        int u = N_NODES + 1;
        int v = random.nextInt(N_NODES);
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(u, v),
                "You can't add an edge to a node that doesn't exist on the graph.");
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(v, u),
                "You can't add an edge to a node that doesn't exist on the graph.");
    }

    public void addEdgeTest(IGraph<Integer> graph) {
        int u = 0;
        int v = 0;
        for (int i = 0; i < N_NODES; i++) {
            u = random.nextInt(N_NODES);
            v = random.nextInt(N_NODES);
            graph.addEdge(u, v);
            assertTrue(graph.hasEdge(u, v), "Nodes " + u + " and " + v + " should have an edge between them.");
            assertTrue(graph.hasEdge(v, u),
                    "In an undirected graph " + u + " and " + v + " should have an edge both ways.");
        }
    }

    public void removeEdgeTest(IGraph<Integer> graph) {
        for (int i = 0; i < N_NODES; i++) {
            int u = random.nextInt(N_NODES);
            int v = random.nextInt(N_NODES);
            graph.addEdge(u, v);
            assertTrue(graph.hasEdge(u, v), "Nodes " + u + " and " + v + " should have an edge between them.");
            assertTrue(graph.hasEdge(v, u),
                    "In an undirected graph " + u + " and " + v + " should have an edge both ways.");
            graph.removeEdge(u, v);
            assertFalse(graph.hasEdge(u, v), "Nodes " + u + " and " + v + " should not have an edge between them.");
            assertFalse(graph.hasEdge(v, u), "Nodes " + v + " and " + u + " should not have an edge between them.");
        }
    }

    public void getNeighbourhoodTest(IGraph<Integer> graph) {
        int u = random.nextInt(N_NODES);
        Set<Integer> randomNodes = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            int v = random.nextInt(N_NODES);
            graph.addEdge(u, v);
            randomNodes.add(v);
        }

        for (Integer w : graph.getNeighbourhood(u)) {
            if (!randomNodes.contains(w))
                fail("A neighbour of u was not in the list of neigbhours");
        }
    }

    public void addNodeTest(IGraph<Integer> graph) {
        int extraNode = N_NODES + 1;
        assertEquals(N_NODES, graph.size(), "Graph should have " + N_NODES + " nodes.");
        assertFalse(graph.hasNode(extraNode), "Graph should not have node " + extraNode);
        int u = extraNode;
        graph.addNode(u);
        assertTrue(graph.hasNode(u), "Graph should have node " + u);
        assertEquals(extraNode, graph.size(), "Graph should have " + extraNode + " nodes.");
    }

    public void duplicateNodeTest(IGraph<Integer> graph) {
        int node = N_NODES - 2;
        graph.addEdge(node, node + 1);
        graph.addNode(node);
        assertTrue(graph.hasEdge(node, node + 1),
                "Trying to add a duplicate node should not remove the egdes from the original node");

    }

    public void removeNodeTest(IGraph<Integer> graph) {
        addNodeTest(graph);
        int u = random.nextInt(N_NODES);
        assertNotEquals(N_NODES, graph.size(), "Graph should not have " + N_NODES + " nodes.");
        graph.removeNode(u);
        assertFalse(graph.hasNode(u), "Graph should not have node " + u);
        assertEquals(N_NODES, graph.size(), "Graph should have " + N_NODES + " nodes.");
    }

    public void removeEgdesWithNodeTest(IGraph<Integer> graph) {
        int u = random.nextInt(N_NODES);
        int v = random.nextInt(N_NODES);
        int z = random.nextInt(N_NODES);
        graph.addEdge(u, v);
        graph.addEdge(u, z);
        assertTrue(graph.getNeighbourhood(u).size() >= 1);
        assertThrows(NullPointerException.class, () -> {
            graph.removeNode(u);
            assertTrue(graph.getNeighbourhood(u).size() == 0);
        },
                "Removing a node should remove the egdes connencting the node");

    }

}
