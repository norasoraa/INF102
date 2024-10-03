package INF102.lab6.cheapFlights;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import INF102.lab6.graph.WeightedDirectedGraph;

public class CheapestFlight implements ICheapestFlight {


    @Override
    public WeightedDirectedGraph<City, Integer> constructGraph(List<Flight> flights) {
        WeightedDirectedGraph<City, Integer> g = new WeightedDirectedGraph<>();
        for (Flight flight : flights) {
            g.addVertex(flight.start);
            g.addVertex(flight.destination);
            g.addEdge(flight.start, flight.destination, flight.cost);
        }
        return g;
    }

    @Override
    public int findCheapestFlights(List<Flight> flights, City start, City destination, int nMaxStops) {
        WeightedDirectedGraph<City, Integer> g = constructGraph(flights);
        HashMap<City, Integer> prices = search(g, start, nMaxStops);
        return prices.get(destination);
    }

    private HashMap<City, Integer> search(WeightedDirectedGraph<City, Integer> g, City start, int nMaxStops) {
        HashMap<ReachInNStops, Trip> distances = new HashMap<>();
        PriorityQueue<Trip> toSearch = new PriorityQueue<>();
        ReachInNStops startReach = new ReachInNStops(start, 0);
        Trip startTrip = new Trip(startReach, 0);
        addEdges(startTrip, g, toSearch);
        distances.put(startReach, startTrip);
        while (!toSearch.isEmpty()) {
            Trip current = toSearch.poll();
            if (distances.containsKey(current.destInNStops)) {
                continue;
            }
            distances.put(current.destInNStops, current);
            if (current.destInNStops.nStops <= nMaxStops) {
                addEdges(current, g, toSearch);
            }
        }
        HashMap<City, Integer> bestPricesMaxStops = new HashMap<>();
        for (Trip trip : distances.values()) {
            City city = trip.destInNStops.destination;
            int cost = trip.totalPrice;
            if (trip.destInNStops.nStops -1 > nMaxStops) {
                continue;
            }
            if (!bestPricesMaxStops.containsKey(city) || bestPricesMaxStops.get(city) > cost) {
                bestPricesMaxStops.put(city, cost);
            }
        }
        return bestPricesMaxStops;
    }

    private void addEdges(Trip current, WeightedDirectedGraph<City, Integer> g, PriorityQueue<Trip> toSearch) {
        for (City city : g.outNeighbours(current.destInNStops.destination)) {
            ReachInNStops currentReach = new ReachInNStops(city, current.destInNStops.nStops + 1);
            Trip trip = new Trip(currentReach, current.totalPrice + g.getWeight(current.destInNStops.destination, city));
            toSearch.add(trip);
        }
    }
    
    /*@Override
    public int findCheapestFlights(List<Flight> flights, City start, City destination, int nMaxStops) {
        HashMap<City, Integer> search = search(flights, start, nMaxStops);
        int cost = search.get(destination);
        return cost;
    }

    private HashMap<City, Integer> search(List<Flight> flights, City start, int nMaxStops) {
        WeightedDirectedGraph<City, Integer> g = constructGraph(flights);
        PriorityQueue<DirectedEdge<City>> toSearch = new PriorityQueue<>(g);
        HashMap<City, Integer> distances = new HashMap<>();
        Stack<City> found = new Stack<>();
        distances.put(start, 0);
        found.add(start);
        addEdges(g, start, toSearch);
        while (!toSearch.isEmpty()) {
            DirectedEdge<City> next = toSearch.poll();
            if (found.contains(next.to)) {
                continue;
            }
            if (found.size()-1 > nMaxStops) {
                found.pop();
                continue;
            }  
            distances.put(next.to, distances.getOrDefault(next.from, 0) + g.getWeight(next));
            found.add(next.to);
            addEdges(g, next.to, toSearch);
        }
        return distances;
    }

    private void addEdges(WeightedDirectedGraph<City, Integer> g, City city, PriorityQueue<DirectedEdge<City>> toSearch) {
		for (City next : g.outNeighbours(city)) {
			toSearch.add(new DirectedEdge<City>(city, next));
		}
	}*/
}
