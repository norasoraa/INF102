package INF102.lab6.cheapFlights;

import java.util.Arrays;
import java.util.List;

public enum City {

    OSLO(0),
    TRONDHEIM(1),
    BERGEN(2),
    BODØ(3),
    TROMSØ(4),
    STAVANGER(5),
    KRISTIANSUND(6),
    FLÅKLYPA(7);

    public final int cityID;

    public static List<City> CITIES = Arrays.asList(values());

    private City(int cityID) {
        this.cityID = cityID;
    }
}
