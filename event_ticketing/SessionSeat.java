package event_ticketing;

public class SessionSeat {
    private final String id;
    private final Seat seat;
    private SeatState state;

    public SessionSeat(String id, Seat seat) {
        this.id = id;
        this.seat = seat;
        this.state = SeatState.FREE;
    }

    public String getId() { return id; }
    public Seat getSeat() { return seat; }
    public SeatState getState() { return state; }
    
    public void setState(SeatState state) {
        this.state = state;
    }
}
