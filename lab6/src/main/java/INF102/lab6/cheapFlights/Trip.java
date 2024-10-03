package INF102.lab6.cheapFlights;

public class Trip implements Comparable<Trip> {
    ReachInNStops destInNStops;
    Integer totalPrice;

    public Trip(ReachInNStops destInNStops, Integer totalPrice) {
        this.destInNStops = destInNStops;
        this.totalPrice = totalPrice;
    }

    @Override
    public int compareTo(Trip o) {
        return Integer.compare(totalPrice, o.totalPrice);
    }
}
