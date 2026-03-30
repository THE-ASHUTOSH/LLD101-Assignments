package event_ticketing;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Event Ticketing System (Refactored LLD) ---");

        Venue venue = new Venue("V1", "Madison Square Garden", "New York");
        Hall hall = new Hall("H1", "Main Arena", venue);
        venue.addHall(hall);

        Seat seat1 = new Seat("S1", 1, 1, PricingTier.VIP);
        Seat seat2 = new Seat("S2", 1, 2, PricingTier.VIP);
        Seat seat3 = new Seat("S3", 1, 3, PricingTier.STANDARD);

        SessionSeat sSeat1 = new SessionSeat("SS1", seat1);
        SessionSeat sSeat2 = new SessionSeat("SS2", seat2);
        SessionSeat sSeat3 = new SessionSeat("SS3", seat3);

        Event concert = new Event("E1", "Coldplay Spheres Tour", "Coldplay");
        EventSession session = new EventSession("ES1", concert, hall, System.currentTimeMillis() + 86400000, Arrays.asList(sSeat1, sSeat2, sSeat3));

        ConcurrencyLockManager lockManager = new ConcurrencyLockManager(3000); // 3 seconds timeout
        FareCalculator fareCalc = new DemandFareCalculator();
        ReservationEngine engine = new ReservationEngine(lockManager, fareCalc);

        Customer patrick = new Customer("C1", "Patrick Stewart", "pat@example.com");
        Customer david = new Customer("C2", "David Tennant", "david@example.com");

        List<SessionSeat> requestedSeats = Arrays.asList(sSeat1, sSeat2);

        System.out.println("--> Testing Concurrent Reservations");

        Thread t1 = new Thread(() -> {
            try {
                System.out.println("Patrick is initiating reservation...");
                Reservation res = engine.initiateReservation(patrick, session, requestedSeats);
                System.out.println("Patrick held the seats! Proceeding to payment. Total: $" + res.getTotalFare());

                // Simulated delay to show locking works
                Thread.sleep(1000);

                boolean success = engine.completePayment(res);
                System.out.println("Patrick's payment status: " + (success ? "CONFIRMED" : "FAILED"));
            } catch (Exception e) {
                System.out.println("Patrick failed: " + e.getMessage());
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                // Ensure t2 starts slightly after t1
                Thread.sleep(200);
                System.out.println("David is attempting to hold the same seats...");
                Reservation res = engine.initiateReservation(david, session, requestedSeats);
                System.out.println("David held the seats!"); // Shouldn't happen
            } catch (Exception e) {
                System.out.println("David failed: " + e.getMessage());
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ignored) {}

        System.out.println("--> Final state of Seat 1: " + sSeat1.getState());
        System.out.println("--> Final state of Seat 2: " + sSeat2.getState());
        System.out.println("--> Final state of Seat 3: " + sSeat3.getState());
    }
}
