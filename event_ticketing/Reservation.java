package event_ticketing;

import java.util.List;
import java.util.UUID;

public class Reservation {
    private final String reservationId;
    private final Customer customer;
    private final EventSession session;
    private final List<SessionSeat> seats;
    private final double totalFare;
    private ReservationStatus status;

    public Reservation(Customer customer, EventSession session, List<SessionSeat> seats, double totalFare) {
        this.reservationId = UUID.randomUUID().toString();
        this.customer = customer;
        this.session = session;
        this.seats = seats;
        this.totalFare = totalFare;
        this.status = ReservationStatus.PENDING;
    }

    public String getReservationId() { return reservationId; }
    public Customer getCustomer() { return customer; }
    public EventSession getSession() { return session; }
    public List<SessionSeat> getSeats() { return seats; }
    public double getTotalFare() { return totalFare; }
    public ReservationStatus getStatus() { return status; }

    public void confirm() { this.status = ReservationStatus.CONFIRMED; }
    public void abort() { this.status = ReservationStatus.ABORTED; }
}
