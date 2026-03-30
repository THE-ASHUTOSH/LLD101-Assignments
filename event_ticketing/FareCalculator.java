package event_ticketing;

public interface FareCalculator {
    double computeTotal(SessionSeat seat, EventSession session);
}
