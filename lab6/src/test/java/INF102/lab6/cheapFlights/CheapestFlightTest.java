package INF102.lab6.cheapFlights;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import INF102.lab6.graph.WeightedDirectedGraph;

public class CheapestFlightTest {

    ICheapestFlight cheapestFlight = new CheapestFlight();

    private List<Flight> flights1() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(City.OSLO, City.TRONDHEIM, 100));
        flights.add(new Flight(City.TRONDHEIM, City.BERGEN, 100));
        flights.add(new Flight(City.BERGEN, City.OSLO, 100));
        flights.add(new Flight(City.TRONDHEIM, City.BODØ, 600));
        flights.add(new Flight(City.BERGEN, City.BODØ, 200));

        return flights;
    }

    private List<Flight> flights2() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(City.OSLO, City.TRONDHEIM, 100));
        flights.add(new Flight(City.TRONDHEIM, City.BERGEN, 100));
        flights.add(new Flight(City.OSLO, City.BERGEN, 500));

        return flights;
    }

    private List<Flight> flights3() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(City.OSLO, City.TRONDHEIM, 100));
        flights.add(new Flight(City.TRONDHEIM, City.BERGEN, 100));
        flights.add(new Flight(City.BERGEN, City.OSLO, 100));
        flights.add(new Flight(City.TRONDHEIM, City.BODØ, 600));
        flights.add(new Flight(City.BERGEN, City.BODØ, 200));
        flights.add(new Flight(City.TRONDHEIM, City.FLÅKLYPA, 10000000));
        flights.add(new Flight(City.BODØ, City.BERGEN, 200));
        flights.add(new Flight(City.TRONDHEIM, City.TROMSØ, 100));
        flights.add(new Flight(City.TROMSØ, City.FLÅKLYPA, 300));

        return flights;
    }

    private List<Flight> flights4() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(City.OSLO, City.TRONDHEIM, 400));
        flights.add(new Flight(City.TRONDHEIM, City.BODØ, 500));
        flights.add( new Flight(City.OSLO, City.BERGEN, 100));
        flights.add(new Flight(City.BERGEN, City.TRONDHEIM, 100));
        flights.add(new Flight(City.TRONDHEIM, City.TROMSØ, 100));
        flights.add(new Flight(City.TROMSØ, City.BODØ, 100));

        return flights;
    }

    private List<Flight> flights5() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(City.OSLO, City.TRONDHEIM, 100));
        flights.add(new Flight(City.TRONDHEIM, City.BERGEN, 100));
        flights.add( new Flight(City.OSLO, City.BERGEN, 300));
        flights.add(new Flight(City.BERGEN, City.BODØ, 100));
        flights.add(new Flight(City.BODØ, City.FLÅKLYPA, 100));
        flights.add(new Flight(City.BERGEN, City.FLÅKLYPA, 400));

        return flights;
    }

    // ############# Task 1 #############
    @Test
    public void constructGraph1() {
        List<Flight> flights = flights1();
        WeightedDirectedGraph<City, Integer> g = cheapestFlight.constructGraph(flights);

        checkFlightGraph(g, flights);
    }

    @Test
    public void constructGraph2() {
        List<Flight> flights = flights2();
        WeightedDirectedGraph<City, Integer> g = cheapestFlight.constructGraph(flights);

        checkFlightGraph(g, flights);
    }

    public void checkFlightGraph(WeightedDirectedGraph<City, Integer> g, List<Flight> flights) {
        for (Flight flight : flights) {
            City start = flight.start;
            City dest = flight.destination;
            int cost = flight.cost;

            assertTrue(g.adjacent(start, dest));
            assertFalse(g.adjacent(dest, start));
            assertEquals(cost, g.getWeight(start, dest));
        }
    }

    // ############# Task 2 #############
    @Test
    public void osloToBodø1stop() {
        List<Flight> flights = flights1();
        City start = City.OSLO;
        City destination = City.BODØ;
        int maxNumberStops = 1;

        int actual = cheapestFlight.findCheapestFlights(flights, start, destination, maxNumberStops);
        int expected = 700;
        assertEquals(expected, actual);
    }

    @Test
    public void osloToBergen1stop() {
        List<Flight> flights = flights2();
        City start = City.OSLO;
        City destination = City.BERGEN;
        int maxNumberStops = 1;

        int actual = cheapestFlight.findCheapestFlights(flights, start, destination, maxNumberStops);
        int expected = 200;
        assertEquals(expected, actual);
    }

    @Test
    public void osloToBergen0stops() {
        List<Flight> flights = flights2();
        City start = City.OSLO;
        City destination = City.BERGEN;
        int maxNumberStops = 0;

        int actual = cheapestFlight.findCheapestFlights(flights, start, destination, maxNumberStops);
        int expected = 500;
        assertEquals(expected, actual);
    }

    @Test
    public void bodøToFlåklypa3stops() {
        List<Flight> flights = flights3();
        City start = City.BODØ;
        City destination = City.FLÅKLYPA;
        int maxNumberStops = 3;

        int actual = cheapestFlight.findCheapestFlights(flights, start, destination, maxNumberStops);
        int expected = 10000400;
        assertEquals(expected, actual);
    }

    @Test
    public void bodøToFlåklypa4stops() {
        List<Flight> flights = flights3();
        City start = City.BODØ;
        City destination = City.FLÅKLYPA;
        int maxNumberStops = 4;

        int actual = cheapestFlight.findCheapestFlights(flights, start, destination, maxNumberStops);
        int expected = 800;
        assertEquals(expected, actual);
    }

    @Test
    public void osloToBodø2stops() {
        List<Flight> flights = flights4();
        City start = City.OSLO;
        City destination = City.BODØ;
        int maxNumberStops = 2;

        int actual = cheapestFlight.findCheapestFlights(flights, start, destination, maxNumberStops);
        int expected = 600;
        assertEquals(expected, actual);
    }

    @Test
    public void osloToFlåklypa() {
    	List<Flight> flights = flights5();
        City start = City.OSLO;
        City destination = City.FLÅKLYPA;
    	
        int actual = cheapestFlight.findCheapestFlights(flights, start, destination, 1);
        int expected = 700;
        assertEquals(expected, actual);

        actual = cheapestFlight.findCheapestFlights(flights, start, destination, 2);
        expected = 500;
    assertEquals(expected, actual);

        actual = cheapestFlight.findCheapestFlights(flights, start, destination, 3);
        expected = 400;
        assertEquals(expected, actual);
}
}
