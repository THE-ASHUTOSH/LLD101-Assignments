package event_ticketing;

public class Event {
    private final String eventId;
    private final String eventName;
    private final String artist;

    public Event(String eventId, String eventName, String artist) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.artist = artist;
    }

    public String getEventId() { return eventId; }
    public String getEventName() { return eventName; }
    public String getArtist() { return artist; }
}
