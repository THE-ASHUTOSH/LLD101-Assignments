package event_ticketing;

import java.util.ArrayList;
import java.util.List;

public class Venue {
    private final String venueId;
    private final String name;
    private final String city;
    private final List<Hall> halls;

    public Venue(String venueId, String name, String city) {
        this.venueId = venueId;
        this.name = name;
        this.city = city;
        this.halls = new ArrayList<>();
    }

    public void addHall(Hall hall) {
        this.halls.add(hall);
    }

    public String getVenueId() { return venueId; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public List<Hall> getHalls() { return halls; }
}
