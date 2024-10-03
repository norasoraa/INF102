package INF102.lab6.cheapFlights;

import java.util.List;

import INF102.lab6.graph.WeightedDirectedGraph;

public interface ICheapestFlight {

    /**
     * Constructs a weighted directed graph from list of flights.
     * Each city is a node and each flight from start to destination is an edge.
     * The weight of an edge is the cost of the plane trip.
     * 
     * @param flights
     * @return weighted graph representing flights
     */
    public WeightedDirectedGraph<City, Integer> constructGraph(List<Flight> flights);

    /**
     * Finds the cheapest cost of flights from <code>start</code> to
     * <code>destination</code>
     * with a maximum number of stops given by <code>nMaxStops</code>.
     * 
     * @param flights     list of flights
     * @param start       starting point of the flight trip
     * @param destination end point of flight trip
     * @param nMaxStops   maximum number of stops in the flight trip
     * @return total cost of flight trip
     */
    public int findCheapestFlights(List<Flight> flights, City start, City destination, int nMaxStops);

}
