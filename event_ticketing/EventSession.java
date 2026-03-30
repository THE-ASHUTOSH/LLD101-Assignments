package event_ticketing;

import java.util.List;

public class EventSession {
    private final String sessionId;
    private final Event event;
    private final Hall hall;
    private final long startTime;
    private final List<SessionSeat> seats;

    public EventSession(String sessionId, Event event, Hall hall, long startTime, List<SessionSeat> seats) {
        this.sessionId = sessionId;
        this.event = event;
        this.hall = hall;
        this.startTime = startTime;
        this.seats = seats;
    }

    public String getSessionId() { return sessionId; }
    public Event getEvent() { return event; }
    public Hall getHall() { return hall; }
    public long getStartTime() { return startTime; }
    public List<SessionSeat> getSeats() { return seats; }
}
