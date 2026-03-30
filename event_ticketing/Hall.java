package event_ticketing;

public class Hall {
    private final String hallId;
    private final String name;
    private final Venue venue;

    public Hall(String hallId, String name, Venue venue) {
        this.hallId = hallId;
        this.name = name;
        this.venue = venue;
    }

    public String getHallId() { return hallId; }
    public String getName() { return name; }
}
