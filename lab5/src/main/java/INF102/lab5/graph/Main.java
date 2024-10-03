package INF102.lab5.graph;


public class Main {

    public static final int N_NODES = 5;

    public static void main(String[] args) {
        IGraph<Integer> graph = new AdjacencySet<>();
        createGraph(graph);
        IGraphSearch<Integer> graphSearch = new GraphSearch<>(graph);
        
        int u = 0;
        int v = 2;

        System.out.println("----Expected to be True----");
        System.out.printf("%-17s| Edge %d and %d connected? %5b%n", graph.getClass().getSimpleName(), u, v, graphSearch.connected(u, v));

        v = 4;
        System.out.println("\n----Expected to be False----");
        System.out.printf("%-17s| Edge %d and %d connected? %5b%n", graph.getClass().getSimpleName(), u, v, graphSearch.connected(u, v));
    }

    /**
     * Creates a simple graph with 5 nodes.
     * 
     * 0 -> 1 -> 2      3 -> 4
     * 
     * @param graph
     */
    public static void createGraph(IGraph<Integer> graph) {
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
    }
    
}
