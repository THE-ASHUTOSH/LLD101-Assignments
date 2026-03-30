package event_ticketing;

public class DemandFareCalculator implements FareCalculator {

    @Override
    public double computeTotal(SessionSeat sessionSeat, EventSession session) {
        PricingTier tier = sessionSeat.getSeat().getTier();
        double baseFare = switch (tier) {
            case VIP -> 500.0;
            case VVIP -> 1000.0;
            case STANDARD -> 200.0;
        };

        // Simulating simple dynamic pricing: Weekend events are 20% more expensive
        return baseFare * 1.20;
    }
}
