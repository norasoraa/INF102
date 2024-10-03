package INF102.lab6.cheapFlights;

public class Flight {

    /**
     * Starting point of flight
     */
    public final City start;
    /**
     * End point of flight
     */
    public final City destination;
    /**
     * Cost of plane ticket
     */
    public final int cost;

    public Flight(City start, City destination, int cost) {
        if (start == destination)
            throw new IllegalArgumentException("You cannot fly to the same city as you are in.");
        if (cost < 0)
            throw new IllegalArgumentException("Cost of a flight cannot be negative.");
        this.start = start;
        this.destination = destination;
        this.cost = cost;
    }

}
