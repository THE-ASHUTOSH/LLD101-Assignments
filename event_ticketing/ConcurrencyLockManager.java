package event_ticketing;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Handles concurrent locking of seats during the reservation process.
 * Separates lock state from the Seat entity itself.
 */
public class ConcurrencyLockManager {
    private final long lockTimeoutMillis;
    private final Map<String, LockInfo> locks;

    public ConcurrencyLockManager(long lockTimeoutMillis) {
        this.lockTimeoutMillis = lockTimeoutMillis;
        this.locks = new ConcurrentHashMap<>();
    }

    public synchronized boolean holdSeatsForCustomer(List<SessionSeat> seats, Customer customer) {
        // First check availability
        for (SessionSeat seat : seats) {
            if (seat.getState() == SeatState.SOLD) {
                return false;
            }
            if (isSeatOnHold(seat) && !getHolderId(seat).equals(customer.getId())) {
                return false;
            }
        }

        // Apply locks
        long expiresAt = System.currentTimeMillis() + lockTimeoutMillis;
        for (SessionSeat seat : seats) {
            locks.put(seat.getId(), new LockInfo(customer.getId(), expiresAt));
            seat.setState(SeatState.ON_HOLD);
        }
        return true;
    }

    public synchronized void releaseHolds(List<SessionSeat> seats) {
        for (SessionSeat seat : seats) {
            locks.remove(seat.getId());
            if (seat.getState() == SeatState.ON_HOLD) {
                seat.setState(SeatState.FREE);
            }
        }
    }
    
    public void finalizeHolds(List<SessionSeat> seats) {
        for (SessionSeat seat : seats) {
            locks.remove(seat.getId());
            seat.setState(SeatState.SOLD);
        }
    }

    public boolean isSeatOnHold(SessionSeat seat) {
        LockInfo info = locks.get(seat.getId());
        return info != null && !info.isExpired();
    }

    public String getHolderId(SessionSeat seat) {
        LockInfo info = locks.get(seat.getId());
        return info != null ? info.getCustomerId() : null;
    }
}
