package INF102.lab5.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphSearchTest {

    private IGraph<Integer> graph;
    private IGraphSearch<Integer> graphSearch;

    private final int N_NODES = 1000;
    private Random random = new Random();

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

    @BeforeEach
    public void setup() {
        graph = new AdjacencySet<>();
        for (int i = 0; i < N_NODES; i++) {
            graph.addNode(i);
        }
        graphSearch = new GraphSearch<>(graph);
    }
    
    @Test
    public void connectedTest() {
        createConnectedGraph(graph);
        int u = 0;
        int v = N_NODES - 1;

        assertTrue(graphSearch.connected(u, v));
    }

    @Test
    public void notConnectedTest() {
        createConnectedGraph(graph);
        int u = 0;
        int v = N_NODES - 1;
        int w = N_NODES - 2;
        graph.removeEdge(w, v);

        assertFalse(graphSearch.connected(u, v));
    }

    @Test
    public void neighbouringNodesAreConnectedTest() {
        createConnectedGraph(graph);
        for (int i = 0; i < 1000; i++) {
            int u = random.nextInt(N_NODES);
            int v = random.nextInt(N_NODES);
            graph.addEdge(u, v);
            
            assertTrue(graphSearch.connected(u, v));
        }
    }

    @Test
    public void connectedUndirectedTest() {
        for (int i = 0; i < 1000; i++) {
            int u = random.nextInt(N_NODES);
            int v = random.nextInt(N_NODES);
            graph.addEdge(u, v);
        }

        for (int i = 0; i < 10000; i++) {
            int u = random.nextInt(N_NODES);
            int v = random.nextInt(N_NODES);
            boolean uConnectedToV = graphSearch.connected(u, v);
            boolean vConnectedToU = graphSearch.connected(v, u);

            assertEquals(uConnectedToV, vConnectedToU);
        }
    }
}
