package INF102.lab5.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdjacencySetTest extends GraphTest {
    
    IGraph<Integer> adjacencySet;

    @BeforeEach
    public void setup() {
        adjacencySet = new AdjacencySet<>();
        for (int i = 0; i < N_NODES; i++) {
            adjacencySet.addNode(i);
        }
    }
    
    @Test
    public void setAddEdgeToNodeNotInGraph() {
        addEdgeToNodeNotInGraph(adjacencySet);
    }

    @Test
    public void setAddEdgeTest() {
        addEdgeTest(adjacencySet);
    }

    @Test
    public void setRemoveEdgeTest() {
        removeEdgeTest(adjacencySet);
    }

    @Test
    public void setGetNeighbourhoodTest() {
        getNeighbourhoodTest(adjacencySet);
    }

    @Test
    public void setAddNodeTest() {
        addNodeTest(adjacencySet);
    }

    @Test
    public void setRemoveNodeTest() {
        removeNodeTest(adjacencySet);
    }
    
    @Test
    public void setDuplicateNodeTest() {
        duplicateNodeTest(adjacencySet);
    }
    @Test
    public void setRemoveEgdesWithNodeTest(){
        removeEgdesWithNodeTest(adjacencySet);
    }
}
