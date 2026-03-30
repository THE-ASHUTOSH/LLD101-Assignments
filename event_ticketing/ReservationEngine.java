package event_ticketing;

import java.util.List;

/**
 * Main subsystem for orchestrating reservations.
 */
public class ReservationEngine {
    private final ConcurrencyLockManager lockManager;
    private final FareCalculator fareCalculator;

    public ReservationEngine(ConcurrencyLockManager lockManager, FareCalculator fareCalculator) {
        this.lockManager = lockManager;
        this.fareCalculator = fareCalculator;
    }

    public Reservation initiateReservation(Customer customer, EventSession session, List<SessionSeat> desiredSeats) throws Exception {
        boolean seatsSecured = lockManager.holdSeatsForCustomer(desiredSeats, customer);

        if (!seatsSecured) {
            throw new Exception("Unable to secure desired seats. They may be held by others.");
        }

        double totalFare = 0;
        for (SessionSeat seat : desiredSeats) {
            totalFare += fareCalculator.computeTotal(seat, session);
        }

        return new Reservation(customer, session, desiredSeats, totalFare);
    }

    public boolean completePayment(Reservation reservation) {
        if (reservation.getStatus() == ReservationStatus.ABORTED) {
            return false;
        }

        // Validate no locks expired
        for (SessionSeat seat : reservation.getSeats()) {
            if (!lockManager.isSeatOnHold(seat) || !lockManager.getHolderId(seat).equals(reservation.getCustomer().getId())) {
                lockManager.releaseHolds(reservation.getSeats());
                reservation.abort();
                return false;
            }
        }

        // Finalize transaction
        lockManager.finalizeHolds(reservation.getSeats());
        reservation.confirm();
        return true;
    }

    public void cancelReservation(Reservation reservation) {
        if (reservation.getStatus() == ReservationStatus.CONFIRMED || reservation.getStatus() == ReservationStatus.PENDING) {
            lockManager.releaseHolds(reservation.getSeats());
            reservation.abort();
        }
    }
}
