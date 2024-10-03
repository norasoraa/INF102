package INF102.lab6.cheapFlights;

import java.util.Objects;

public class ReachInNStops {
    City destination;
    Integer nStops;

    public ReachInNStops(City dest, Integer nStops) {
        this.destination = dest;
        this.nStops = nStops;
    }

    @Override 
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReachInNStops)) {
            return false;
        }
        ReachInNStops other = (ReachInNStops) o;
        return this.destination.equals(other.destination) && this.nStops.equals(other.nStops);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, nStops);
    }
}
